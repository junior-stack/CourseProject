package Controller;


import Entity.Schedulable;
import Gateway.EventDataAccess;
import Gateway.Igateway;
import Gateway.MapGateway;
import Gateway.RoomDataAccess;
import UseCase.EventManager;
import UseCase.RoomManager;
import UseCase.SchedulableManager;
import UseCase.SpeakerScheduleManager;

import UseCase.UserAccountManager;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class representing a ScheduleFacade.
 *
 * @author Ye Zhou & Hanzhi Zhang
 * @version 1.0
 **/
public class Scheduler {
  private EventManager em;


  private SchedulableManager rooms_list;
  private SchedulableManager speakers_list;

  private Igateway ig = new EventDataAccess();
  private MapGateway rm = new RoomDataAccess();

  public Scheduler(UserAccountManager uam) {
    this.rooms_list = new SchedulableManager(rm.read());
    this.speakers_list = new SchedulableManager((ArrayList<Schedulable>) uam.getSpeakerList());
    speakers_list.addObserver(uam);
    this.em = new EventManager(this.rooms_list, this.speakers_list, ig.read());
  }

  // EventController
  public boolean ConfirmAddEvent(int rm_ID, String start, String end, ArrayList<Integer> speaker_ID,
      String topic, int max, String eventtype) {
    Time st = java.sql.Time.valueOf(start);
    Time en = java.sql.Time.valueOf(end);
    if (em.checkIsEventValid(rm_ID, st, en, speaker_ID)){
      em.addEvent(rm_ID, st, en, topic, max, eventtype, speaker_ID);
      return true;
    }
    return false;
  }

  public boolean ConfirmDeleteEvent(int eventID) {
    return em.delEvent(em.get_event(eventID));
  }

  public boolean ConfirmEditEvent(int old_event_ID, int new_room_ID, String st, String en,
      String topic, ArrayList<Integer> sp_ID) {

    Time start = java.sql.Time.valueOf(st);
    Time end = java.sql.Time.valueOf(en);
    Time beggining = java.sql.Time.valueOf("09:00:00");
    Time ending = java.sql.Time.valueOf("17:00:00");

    if (start.compareTo(beggining) < 0 | end.compareTo(ending) > 0) {
      return false;
    }
    return em
        .editEvent(old_event_ID, new_room_ID, start, end, topic,
            sp_ID);
  }

  public boolean setEventCapacity(int room_id, int event_id, int new_maximum) {
    return em.setEventCapacity(room_id, event_id, new_maximum);
  }

  public HashMap<Integer, String> ShowAllEvents() {   //Event -> String;改EventController
    return em.get_events_info();
  }

  public String get_single_event(int event_ID) {

    return em.get_events_info().get(event_ID);
  }


  public void addSpeaker(String SpeakerName, String Password, String phone, String email) {

    speakers_list.addSchedulable(SpeakerName, Password, phone, email);
  }


  public ArrayList<String> getSpeakers() {  //Speaker_ID
    return speakers_list.get_schedulables_info();
  }


  // RoomController
  public boolean confirmaddroom(int roomID, int capacity) {
    for(Schedulable sch: rooms_list){
      if(sch.give_id() == roomID){
        return false;
      }
    }
    rooms_list.addSchedulabe(roomID, capacity);
    return true;
  }

  public boolean confirmdeleteroom(int roomID) {
    HashMap<Integer, ArrayList<ArrayList<Time>>> room_schedule = rooms_list.getScheduleableSchedulelist();
    if(!room_schedule.containsKey(roomID)){
      return false;
    }
    if (room_schedule.get(roomID).isEmpty()) {
      rooms_list.delSchedulable(roomID);
      return true;
    }
    return false;
  }

  public ArrayList<String> get_rooms() {
    return rooms_list.get_schedulables_info();
  }

  public HashMap<Integer, ArrayList<ArrayList<Time>>> get_rooms_schedule() {
    return rooms_list.getScheduleableSchedulelist();
  }

  public EventManager getEm() {
    return em;
  }

  public SchedulableManager getVr() {
    return rooms_list;
  }

  public void saveevents() {
    ig.write(EventManager.eventpool);
  }

  public void saverooms() {
    rm.write(RoomManager.rooms_list);
  }



}
