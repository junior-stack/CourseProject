package Controller;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Objects;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by yezhou on 2020/11/16
 **/
public class ScheduleFacadeTest {
  private ScheduleFacade sf;
  @Before
  public void setUp() throws Exception {
    sf = new ScheduleFacade();
    sf.confirmaddroom(0);
    sf.confirmaddroom(1);
    sf.confirmaddroom(2);
    sf.addSpeaker("Jack", "111111", "012-345-6789", "j@mail.com");
    sf.addSpeaker("Jerry", "111111", "012-345-6789", "j@mail.com");
    sf.addSpeaker("Jim", "111111", "012-345-6789", "j@mail.com");
    System.out.println(sf.ShowAllEvents());

  }


  @Test
  public void confirmAddEvent() {
    assertTrue(sf.ConfirmAddEvent(0, "10:00:00", "11:00:00", 0, "Learning"));
    assertFalse(sf.ConfirmAddEvent(0, "10:00:00", "11:00:00", 1, "Learning"));
    assertFalse(sf.ConfirmAddEvent(1, "10:00:00", "11:00:00", 0, "learning"));
    assertFalse(sf.ConfirmAddEvent(0, "11:00:00", "13:00:00", 1, "learning"));
    assertFalse(sf.ConfirmAddEvent(2, "08:00:00", "09:00:00", 2, "learning"));
    assertTrue(sf.ConfirmAddEvent(0, "11:00:00", "12:00:00", 0, "learning"));
    System.out.println(sf.ShowAllEvents());

  }

  @Test
  public void confirmDeleteEvent() {
    sf.ConfirmAddEvent(0, "10:00:00", "11:00:00", 0, "Learning");
    sf.ConfirmAddEvent(0, "11:00:00", "12:00:00", 0, "learning");
    assertFalse(sf.ConfirmDeleteEvent(2));
    assertTrue(sf.ConfirmDeleteEvent(1));
    assertFalse(sf.ConfirmDeleteEvent(1));
    assertTrue(sf.ConfirmDeleteEvent(0));
    assertFalse(sf.ConfirmDeleteEvent(0));

  }

  @Test
  public void confirmEditEvent() {
    sf.ConfirmAddEvent(0, "10:00:00", "11:00:00", 0, "Learning");
    sf.ConfirmAddEvent(0, "11:00:00", "12:00:00", 0, "learning");
    sf.ConfirmAddEvent(1, "10:00:00", "11:00:00", 2, "learn");
    sf.ConfirmAddEvent(2, "10:00:00", "11:00:00", 1, "learn");
    sf.ConfirmAddEvent(1, "11:00:00", "12:00:00", 1, "learn");
    sf.ConfirmAddEvent(2, "11:00:00", "12:00:00", 2, "l");

    System.out.println(sf.ShowAllEvents());
    assertTrue(sf.ConfirmEditEvent(0, 0, "10:00:00", "11:00:00", "learn", 0));
    assertFalse(sf.ConfirmEditEvent(0, 1, "10:00:00", "11:00:00", "l", 0));
    assertFalse(sf.ConfirmEditEvent(4, 1, "11:00:00", "12:00:00", "l", 2));
    assertFalse(sf.ConfirmEditEvent(5, 2, "10:00:00","11:00:00", "l", 2));
    assertFalse(sf.ConfirmEditEvent(5, 3, "11:00:00", "12:00:00", "l", 0));
    assertFalse(sf.ConfirmEditEvent(5, 2, "11:00:00", "12:00:00", "l", 3));
    assertFalse(sf.ConfirmEditEvent(0, 0, "6:00:00", "7:00:00", "learn", 0));
    assertFalse(sf.ConfirmEditEvent(0, 0, "12:00:00", "14:00:00", "learn", 0));
    assertTrue(sf.ConfirmEditEvent(0, 0, "12:00:00", "13:00:00", "l", 0));
    assertFalse(sf.ConfirmEditEvent(6, 0, "12:00:00", "13:00:00", "l", 0));
    System.out.println(sf.get_single_event(0));
  }

  @Test
  public void showAllEvents() {
    System.out.println(sf.ShowAllEvents());
  }

  @Test
  public void get_single_event() {
    sf.ConfirmAddEvent(0, "10:00:00", "11:00:00", 0, "Learning");
    sf.ConfirmAddEvent(0, "11:00:00", "12:00:00", 0, "learning");
    sf.ConfirmAddEvent(1, "10:00:00", "11:00:00", 2, "learn");
    String tmp = "Event{id=0, speakerId=0, roomId=0, startTime=10:00:00, endTime=11:00:00, topic='Learning', attendees=[]}";
    assertTrue(Objects.equals(sf.get_single_event(0), tmp));
    System.out.println(sf.get_single_event(3));
  }

  @Test
  public void delSpeakerSchedule() {

  }

  @Test
  public void addSpeaker() {
    assertTrue(sf.addSpeaker("Tim", "111", "012-345-6789", "t@mail.com"));
  }

  @Test
  public void getSpeakerSchedule() {
  }

  @Test
  public void getSpeakers() {
    HashMap<Integer, String> tmp = new HashMap<>();
    String tmp1 = "User{userId=0, username='Jack', password='111111', phone='111111', email='j@mail.com', events=[], Identity=Speaker}";
    tmp.put(0,tmp1);
    String tmp2 = "User{userId=1, username='Jerry', password='111111', phone='111111', email='j@mail.com', events=[], Identity=Speaker}";
    tmp.put(1, tmp2);
    String tmp3 = "User{userId=2, username='Jim', password='111111', phone='111111', email='j@mail.com', events=[], Identity=Speaker}";
    tmp.put(2,tmp3);
    assertEquals(sf.getSpeakers(), tmp);
  }

  @Test
  public void confirmaddroom() {
    assertFalse(sf.confirmaddroom(1));
    assertTrue(sf.confirmaddroom(3));

  }

  @Test
  public void confirmdeleteroom() {
    sf.ConfirmAddEvent(0, "10:00:00", "11:00:00", 0, "l");
    assertTrue(sf.confirmdeleteroom(2));
    assertFalse(sf.confirmdeleteroom(3));
    assertFalse(sf.confirmdeleteroom(0));
  }

  @Test
  public void get_rooms() {
  }

  @Test
  public void get_rooms_schedule() {
  }
}