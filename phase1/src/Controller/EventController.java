package Controller;


import UseCase.EventManager;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yezhou on 2020/11/13
 **/
public class EventController {

  private final EventManager em;

  public EventController(EventManager em) {
    this.em = em;
  }


  /**
   * This method return whether the event is successfully arranged and confirmed.
   * It takes in information of the event (room, time, speaker) and try to arrange it.
   * It only returns true when the event is a valid one and is successfully arranged.
   * @param rm_ID room id where the event will take place
   * @param start the start time of this event
   * @param end the end time of this event
   * @param speaker_ID the id of the speaker of this event
   * @return boolean of whether the event is confirmed.
   */
  public boolean ConfirmAddEvent(int rm_ID, String start, String end, int speaker_ID,
      String topic) {
    Time st = java.sql.Time.valueOf(start);
    Time en = java.sql.Time.valueOf(end);
    long time_difference = en.getTime() - st.getTime();
    if(time_difference > 3600000){
      return false;
    }
    if (em.checkIsEventValid(em.get_vr().get_rm(rm_ID), st, en, em.get_vs().get_sp(speaker_ID))) {
      em.addEvent(em.get_vr().get_rm(rm_ID), st, en, em.get_vs().get_sp(speaker_ID), topic);
      return true;
    }
    return false;
  }

  /**
   * This method return whether a particular event is successfully deleted according the the id of this event.
   * @param eventID the id of a particular event
   * @return boolean of whether the event is deleted.
   */
  public boolean ConfirmDeleteEvent(int eventID) {
    return em.delEvent(em.get_event(eventID));
  }


  /**
   * This method return all information of the event after editing.
   * It includes event Id, room id, time of the event, topic, speaker.
   * @param old_event_ID event id
   * @param new_room_ID room id where the event will take place
   * @param st the start time of this event
   * @param en the end time of this event
   * @param topic the topic of the event
   * @param sp_ID speaker id
   * @return all information of the event.
   */
  public boolean ConfirmEditEvent(int old_event_ID, int new_room_ID, String st, String en,
      String topic, int sp_ID) {
    Time start = java.sql.Time.valueOf(st);
    Time end = java.sql.Time.valueOf(en);
    Time beggining = java.sql.Time.valueOf("09:00:00");
    Time ending = java.sql.Time.valueOf("17:00:00");

    if (start.compareTo(beggining) < 0 | end.compareTo(ending) > 0) {
      return false;
    }
    return em
        .editEvent(em.get_event(old_event_ID), em.get_vr().get_rm(new_room_ID), start, end, topic,
            em.get_vs().get_sp(sp_ID));

  }

  /**
   * This method return a list of all events.
   * @return a map of all events.
   */
  public HashMap<Integer, String> ShowAllEvents() {
    return em.get_events_info();
  }


  /**
   * This method return all information of a particular event.
   * It includes event Id, room id, time of the event, topic, speaker.
   * @return all information of the event.
   */
  public String get_single_event(int event_ID) {
    return em.get_events_info().get(event_ID);
  }

  /*Return an arrayList of strings that prints all events in "eventId-event.topic*/
  public ArrayList<String> get_events_lst() {
    return em.get_events_lst();
  }

  public ArrayList<Integer> get_spots(Integer event_id){
    return em.get_event_spots(em.get_event(event_id));
  }
}
