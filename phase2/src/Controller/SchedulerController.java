package Controller;

import UseCase.EventManager;
import UseCase.RoomManager;
import UseCase.SchedulableManager;
import UseCase.UserAccountManager;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A class representing a ScheduleFacade.
 *
 * @author Ye Zhou & Hanzhi Zhang
 * @version 1.0
 **/
public class SchedulerController {
  private EventManager em;


  private SchedulableManager sm = new SchedulableManager();
  private UserAccountManager uam;
  private RoomManager rmm;

  public SchedulerController(UserAccountManager uam) {

    this.uam = uam;
    // TODO
//    rmm = new RoomManager(ig.read()); //check later
//    this.em = new EventManager(ig.read()); ////check later
  }

  // EventController
  public boolean ConfirmAddEvent(int rm_ID, String start, String end, ArrayList<Integer> speaker_ID,
      String topic, int max, String eventtype) {
    Time st = java.sql.Time.valueOf(start);
    Time en = java.sql.Time.valueOf(end);
    List rms= rmm.getRoomList();
    List sps = uam.getSpeakerList();
    if (!sm.CheckSchedulableAvailable(rms, rm_ID, st, en)){
      return false;
    }
    for(Integer id: speaker_ID){
      if(!sm.CheckSchedulableAvailable(sps, id, st, en)){
        return false;
      }
    }
    em.addEvent(rm_ID, st, en, topic, max, eventtype, speaker_ID);
    for(Integer id: speaker_ID){
      sm.giveSchedulableNewSchedule(sps, id, st, en);
    }
    sm.giveSchedulableNewSchedule(rms, rm_ID, st, en);
    return true;
  }

  public boolean ConfirmDeleteEvent(int eventID) {
    if(em.delEvent(eventID)) {
      ArrayList<Time> time = em.gettime(eventID);
      Integer rm = em.getLocation(eventID);
      ArrayList<Integer> sp = em.getSpeaker(eventID);
      List rms= rmm.getRoomList();
      List sps = uam.getSpeakerList();
      for (Integer i : sp) {
        sm.delSchedulableSchedule(sps, i, time.get(0), time.get(1));
      }
      sm.delSchedulableSchedule(rms, rm, time.get(0), time.get(1));
      return true;
    }
    return false;
  }

  public boolean ConfirmEditEvent(int old_event_ID, int new_room_ID, String st, String en,
      String topic, ArrayList<Integer> sp_ID) {

    Time start = java.sql.Time.valueOf(st);
    Time end = java.sql.Time.valueOf(en);
    Time old_start, old_end;
    ArrayList<Integer> old_sp;
    Integer old_rm;
    List tmp = rmm.getRoomList();
    List tmp2 = uam.getSpeakerList();
    try {
      old_start = em.gettime(old_event_ID).get(0);
      old_end = em.gettime(old_event_ID).get(1);
      old_sp = em.getSpeaker(old_event_ID);
      old_rm = em.getLocation(old_event_ID);
    }catch (NullPointerException e){
      return false;
    }

    sm.delSchedulableSchedule(tmp, old_rm, old_start, old_end);
    if(!sm.CheckSchedulableAvailable(tmp, new_room_ID, start, end)){
      sm.giveSchedulableNewSchedule(tmp, old_rm, old_start, old_end);
      return false;
    }

    for(Integer id: old_sp){
      sm.delSchedulableSchedule(tmp2, id, old_start, old_end);
    }
    for(Integer id: sp_ID){
      if(!sm.CheckSchedulableAvailable(tmp2, id, start, end)){
        for(Integer ids: old_sp){
          sm.giveSchedulableNewSchedule(tmp2, ids, old_start, old_end);
        }
        return false;
      }
    }

    sm.giveSchedulableNewSchedule(tmp, new_room_ID, start, end);
    for(Integer ids: sp_ID){
      sm.giveSchedulableNewSchedule(tmp2, ids, old_start, old_end);
    }
    return em.editEvent(old_event_ID, new_room_ID, start, end, topic, sp_ID);
  }

  public boolean setEventCapacity(int room_id, int event_id, int new_maximum) {
    int rc = rmm.getCapacity(room_id);
    if(new_maximum > rc){
      return false;
    }
    return em.setEventCapacity(event_id, new_maximum);
  }

  public HashMap<Integer, String> ShowAllEvents() {   //Event -> String;改EventController
    return em.get_events_info();
  }

  public String get_single_event(int event_ID) {

    return em.get_events_info().get(event_ID);
  }


  public void addSpeaker(String SpeakerName, String Password, String phone, String email) {

    uam.createSpeaker(SpeakerName, Password, phone, email);
  }


  public ArrayList<String> getSpeakers() {  //Speaker_ID
    return sm.get_schedulables_info(uam.getSpeakerList());
  }


  // RoomController
  public boolean confirmaddroom(int roomID, int capacity) {
    return rmm.addRoom(roomID, capacity);
  }


  public boolean confirmdeleteroom(int roomID) {
    List rmm_list = rmm.getRoomList();
    HashMap<Integer, ArrayList<ArrayList<Time>>> room_schedule = sm.getScheduleableSchedulelist(rmm_list);
    if(!room_schedule.containsKey(roomID)){
      return false;
    }
    if (room_schedule.get(roomID).isEmpty()) {
      return rmm.delRoom(roomID);
    }
    return false;
  }

  public ArrayList<String> get_rooms() {
    return sm.get_schedulables_info(rmm.getRoomList());
  }


  public EventManager getEm() {
    return em;
  }

  public RoomManager getVr() {
    return rmm;
  }

  public void savedata() {
    // TODO
  }




}
