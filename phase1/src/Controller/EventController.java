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

  public boolean ConfirmAddEvent(int rm_ID, String start, String end, int speaker_ID,
      String topic) {
    Time st = java.sql.Time.valueOf(start);
    Time en = java.sql.Time.valueOf(end);
    if (em.checkIsEventValid(em.get_vr().get_rm(rm_ID), st, en, em.get_vs().get_sp(speaker_ID))) {
      em.addEvent(em.get_vr().get_rm(rm_ID), st, en, em.get_vs().get_sp(speaker_ID), topic);
      return true;
    }
    return false;
  }

  public boolean ConfirmDeleteEvent(int eventID) {
    return em.delEvent(em.get_event(eventID));
  }


  public boolean ConfirmEditEvent(int old_event_ID, int new_room_ID, String st, String en,
      String topic, int sp_ID) {
    Time start = java.sql.Time.valueOf(st);
    Time end = java.sql.Time.valueOf(en);
    return em
        .editEvent(em.get_event(old_event_ID), em.get_vr().get_rm(new_room_ID), start, end, topic,
            em.get_vs().get_sp(sp_ID));

  }

  public HashMap<Integer, String> ShowAllEvents() {
    return em.get_events_info();
  }

  public String get_single_event(int event_ID) {
    return em.get_events_info().get(event_ID);
  }

  /*Return an arrayList of strings that prints all events in "eventId-event.topic*/
  public ArrayList<String> get_events_lst() {
    return em.get_events_lst();
  }

}
