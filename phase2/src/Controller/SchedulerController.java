package Controller;

import Dao.EventDao;
import Dao.RoomDao;
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

  private final SchedulableManager sm = new SchedulableManager();
  private final UserAccountManager uam;
  private final EventManager em;
  private final RoomManager rmm;

  public SchedulerController(UserAccountManager uam) {

    this.uam = uam;
    rmm = new RoomManager(RoomDao.getAll());
    ArrayList events = new ArrayList<>(EventDao.getAll());

    this.em = new EventManager(events);
    em.setNewCounter(events.size());
  }

  // EventController

  /**
   * This method creates and adds an event to the system
   *
   * @param rm_ID      the roomID of the room where the event takes place
   * @param start      the start time of the event
   * @param end        the end time of the event
   * @param speaker_ID the speakers of the event
   * @param topic      the topic of the event
   * @param max        the capacity of the event
   * @param eventtype  the type of the event, it can be MultipleSpeaker, OneSpeakerEvent or
   * @return true iff the addition is successfull
   */
  public boolean ConfirmAddEvent(int rm_ID, String start, String end, ArrayList<Integer> speaker_ID,
      String topic, int max, String eventtype) {
    Time st = java.sql.Time.valueOf(start);
    Time en = java.sql.Time.valueOf(end);
    List rms = rmm.getRoomList();
    List sps = uam.getSpeakerList();
    if (!sm.CheckSchedulableAvailable(rms, rm_ID, st, en)) {
      return false;
    }
    for (Integer id : speaker_ID) {
      if (!sm.CheckSchedulableAvailable(sps, id, st, en)) {
        return false;
      }
    }
    em.addEvent(rm_ID, st, en, topic, max, eventtype, speaker_ID);
    for (Integer id : speaker_ID) {
      sm.giveSchedulableNewSchedule(sps, id, st, en);
    }
    sm.giveSchedulableNewSchedule(rms, rm_ID, st, en);
    return true;
  }

  /**
   * This method deletes an event by passing the event ID of that event
   *
   * @param eventID the target event ID
   * @return true iff the deletion is successful
   */
  public boolean ConfirmDeleteEvent(int eventID) {
    if (em.get_event(eventID) != null) {
      ArrayList<Time> time = em.gettime(eventID);
      Integer rm = em.getLocation(eventID);
      ArrayList<Integer> sp = em.getSpeaker(eventID);
      List rms = rmm.getRoomList();
      List sps = uam.getSpeakerList();
      for (Integer i : sp) {
        sm.delSchedulableSchedule(sps, i, time.get(0), time.get(1));
      }
      sm.delSchedulableSchedule(rms, rm, time.get(0), time.get(1));
      em.delEvent(eventID);
      return true;
    }
    return false;
  }

  /**
   * This method changes and edit the information of an event
   *
   * @param old_event_ID the ID of the target event
   * @param new_room_ID  the new room ID of the room where the event takes place
   * @param st           the new start time of the event
   * @param en           the new end time of the event
   * @param topic        the new topic of the event
   * @param sp_ID        the new speaker IDs of the event
   * @return boolean true iff the edition is successful
   */
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
    } catch (IndexOutOfBoundsException e) {
      return false;
    }

    sm.delSchedulableSchedule(tmp, old_rm, old_start, old_end);
    if (!sm.CheckSchedulableAvailable(tmp, new_room_ID, start, end)) {
      sm.giveSchedulableNewSchedule(tmp, old_rm, old_start, old_end);
      return false;
    }

    for (Integer id : old_sp) {
      sm.delSchedulableSchedule(tmp2, id, old_start, old_end);
    }
    for (Integer id : sp_ID) {
      if (!sm.CheckSchedulableAvailable(tmp2, id, start, end)) {
        for (Integer ids : old_sp) {
          sm.giveSchedulableNewSchedule(tmp2, ids, old_start, old_end);
        }
        return false;
      }
    }

    sm.giveSchedulableNewSchedule(tmp, new_room_ID, start, end);
    for (Integer ids : sp_ID) {
      sm.giveSchedulableNewSchedule(tmp2, ids, old_start, old_end);
    }
    return em.editEvent(old_event_ID, new_room_ID, start, end, topic, sp_ID);
  }

  /**
   * This method returns changes the capacity of the event
   *
   * @param room_id     the room where the event takes place
   * @param event_id    the target event
   * @param new_maximum the new capacity of the event
   * @return true iff the change is made
   */
  public boolean setEventCapacity(int room_id, int event_id, int new_maximum) {
    int rc = rmm.getCapacity(room_id);
    if (new_maximum > rc) {
      return false;
    }
    return em.setEventCapacity(event_id, new_maximum);
  }

  /**
   * This method returns the hashmap that maps each event ID to the string information of that
   * event
   *
   * @return the hashmap that maps each event ID to the string information of that event
   */
  public List<String> ShowAllEvents() {   //Event -> String;改EventController
    return em.get_events_info();
  }

  /**
   * This methof returns the string information of a single event
   *
   * @param event_ID the target event ID
   * @return String the string information of a single event
   */
  public String get_single_event(int event_ID) {

    return em.get_events_info().get(event_ID);
  }


  /**
   * This method adds the speaker to the system
   *
   * @param SpeakerName the name of the speaker
   * @param Password    the password of the speaker account
   * @param phone       the phone number of the speaker
   * @param email       the email address of the speaker
   */
  public void addSpeaker(String SpeakerName, String Password, String phone, String email) {

    uam.createSpeaker(SpeakerName, Password, phone, email);
  }


  /**
   * This method returns the list of speakers string
   *
   * @return the list of speakers string
   */
  public ArrayList<String> getSpeakers() {  //Speaker_ID
    return sm.get_schedulables_info(uam.getSpeakerList());
  }

  // RoomController

  /**
   * This method adds the room to the system
   *
   * @param roomID   the roomId of the room
   * @param capacity the capacity of the room
   * @return true iff the addition is sucessful
   */
  public boolean confirmaddroom(int roomID, int capacity) {
    return rmm.addRoom(roomID, capacity);
  }


  /**
   * This method deletes the room from the system
   *
   * @param roomID the roomId of the room
   * @return true iff the deletion is sucessful
   */
  public boolean confirmdeleteroom(int roomID) {
    List rmm_list = rmm.getRoomList();
    HashMap<Integer, ArrayList<ArrayList<Time>>> room_schedule = sm
        .getScheduleableSchedulelist(rmm_list);
    if (!room_schedule.containsKey(roomID)) {
      return false;
    }
    if (room_schedule.get(roomID).isEmpty()) {
      return rmm.delRoom(roomID);
    }
    return false;
  }

  /**
   * Returns list of string info of the rooms within the system
   *
   * @return ArrayList<String> list of string info of the rooms within the system
   */
  public ArrayList<String> get_rooms() {
    return sm.get_schedulables_info(rmm.getRoomList());
  }


  /**
   * Returns the EventManager attributes to other classes so that other classes can accesss
   *
   * @return EventManager
   */
  public EventManager getEm() {
    return em;
  }

  /**
   * Returns the RoomManager attributes to other classes so that other classes can accesss
   *
   * @return RoomManafer
   */
  public RoomManager getVr() {
    return rmm;
  }

  /**
   * This method saves the rooms information and event information in the database
   */
  public void savedata() {
    rmm.saveRooms();
    em.saveEvents();
  }


}
