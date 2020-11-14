package Controller;

import Entity.Event;
import Entity.Room;
import Entity.Speaker;
import UseCase.EventManager;
import java.sql.Time;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by yezhou on 2020/11/13
 **/
public class EventController {
  private EventManager em;

  public EventController(EventManager em){
    this.em = em;
  }

  public boolean ConfirmAddEvent(int speaker_ID, String topic){
    Scanner sc = new Scanner(System.in);

    System.out.println("Enter the room ID where you want to hold the event:");
    int rm_ID = sc.nextInt();
    Room rm = em.get_vr().get_rm(rm_ID);

    System.out.println("Enter the start time:");
    String start = sc.nextLine();
    Time st = java.sql.Time.valueOf(start);

    System.out.println("Enter the end time:");
    String end = sc.nextLine();
    Time en = java.sql.Time.valueOf(end);

    System.out.println("Enter the speaker ID for this event:");
    Speaker sp = em.get_vs().get_sp(speaker_ID);
    if(em.checkIsEventValid(rm,st, en, sp)){
      em.addEvent(rm, st, en, sp, topic);
      return true;
    }
    return false;
  }

  public boolean ConfirmDeleteEvent(int eventID){
    Event e = em.get_event(eventID);
    return em.delEvent(e);
  }

  public boolean ConfirmEditEvent(int old_event_ID, int new_room_ID, String st, String en, String topic, int sp_ID){
    Event old = em.get_event(old_event_ID);
    Room rm = em.get_vr().get_rm(new_room_ID);
    Time start = java.sql.Time.valueOf(st);
    Time end = java.sql.Time.valueOf(en);
    Speaker sp = em.get_vs().get_sp(sp_ID);
    return em.editEvent(old, rm, start, end, topic, sp);

  }

  public HashMap<Integer, Event> ShowAllEvents(){
    return em.get_events();
  }

  public Event get_single_event(int event_ID){
    return em.get_event(event_ID);
  }

}
