package Controller;


import Gateway.EventDataAccess;
import Gateway.Igateway;
import Gateway.MapGateway;
import Gateway.RoomDataAccess;
import Gateway.SpeakerScheduleDataAccess;
import UseCase.EventManager;
import UseCase.ValidateRoom;
import UseCase.ValidateSpeaker;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class representing a ScheduleFacade.
 *
 * @author Ye Zhou & Hanzhi Zhang
 * @version 1.0
 **/
public class ScheduleFacade {

  private EventController ec;
  private SpeakerController sc;
  private RoomController rc;
  private EventManager em;


  private ValidateRoom vr;
  private ValidateSpeaker vs;

  private Igateway ig = new EventDataAccess();
  private MapGateway rm = new RoomDataAccess();
  private MapGateway ss = new SpeakerScheduleDataAccess();

  public ScheduleFacade() {
    ArrayList events = ig.read();
    HashMap rooms_list = rm.read();
    HashMap speaker_list = ss.read();

    vr = new ValidateRoom(rooms_list);
    rc = new RoomController(vr);
    vs = new ValidateSpeaker(speaker_list);

    sc = new SpeakerController(vs);
    em = new EventManager(vr, vs, events);
    ec = new EventController(em);
  }

  // EventController
  public boolean ConfirmAddEvent(int rm_ID, String start, String end, int speaker_ID,
      String topic) {
    return ec.ConfirmAddEvent(rm_ID, start, end, speaker_ID, topic);
  }

  public boolean ConfirmDeleteEvent(int eventID) {
    return ec.ConfirmDeleteEvent(eventID);
  }

  public boolean ConfirmEditEvent(int old_event_ID, int new_room_ID, String st, String en,
      String topic, int sp_ID) {

    return ec.ConfirmEditEvent(old_event_ID, new_room_ID, st, en, topic, sp_ID);
  }

  public HashMap<Integer, String> ShowAllEvents() {   //Event -> String;改EventController

    return ec.ShowAllEvents();
  }

  public String get_single_event(int event_ID) {

    return ec.get_single_event(event_ID);
  }

  // SpeakerController
  public void delSpeakerSchedule(int speakerID, Time start, Time end) {

    sc.delSpeakerSchedule(speakerID, start, end);
  }

  public boolean addSpeaker(String SpeakerName, String Password, String phone, String email) {

    return sc.addSpeaker(SpeakerName, Password, phone, email);
  }

  public HashMap<Integer, ArrayList<ArrayList<Time>>> getSpeakerSchedule() {
    return sc.getSpeakerSchedule();
  }

  public HashMap<Integer, String> getSpeakers() {  //Speaker_ID
    return sc.getSpeakers();
  }


  // RoomController
  public boolean confirmaddroom(int roomID) {
    return rc.confirmaddroom(roomID, 2);
  }

  public boolean confirmdeleteroom(int roomID) {
    return rc.confirmdeleteroom(roomID);
  }

  public HashMap<Integer, String> get_rooms() {
    return rc.get_rooms();
  }

  public HashMap<Integer, ArrayList<ArrayList<Time>>> get_rooms_schedule() {
    return rc.get_rooms_schedule();
  }

  public EventManager getEm() {
    return em;
  }

  public ValidateRoom getVr() {
    return vr;
  }

  public void saveevents() {
    ig.write(EventManager.eventpool);
  }

  public void saverooms() {
    rm.write(ValidateRoom.rooms_list);
  }

  public void savespeakerschedule() {
    ss.write(ValidateSpeaker.speaker_list);
  }


}
