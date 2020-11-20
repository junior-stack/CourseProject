package UI;

import Controller.ScheduleFacade;
import java.util.Objects;

public class ScheduleFacadeTest1 {

  public static void main(String[] args) {
    ScheduleFacade sf = new ScheduleFacade();
    sf.confirmaddroom(0);
    sf.confirmaddroom(1);
    sf.addSpeaker("Jerry", "111111", "012-345-6789", "j@mail.com");
    sf.addSpeaker("Jim", "111111", "012-345-6789", "j@mail.com");
    System.out.println(sf.ShowAllEvents());

    System.out.println((sf.ConfirmAddEvent(1, "10:00:00", "11:00:00", 0, "learning")));
    System.out.println((sf.ConfirmAddEvent(2, "08:00:00", "09:00:00", 2, "learning")));
    System.out.println(sf.ConfirmAddEvent(0, "11:00:00", "12:00:00", 0, "learning"));
    System.out.println(sf.ShowAllEvents());

    sf.ConfirmAddEvent(0, "10:00:00", "11:00:00", 0, "Learning");
    sf.ConfirmAddEvent(0, "11:00:00", "12:00:00", 0, "learning");
    System.out.println((sf.ConfirmDeleteEvent(0)));
    System.out.println((sf.ConfirmDeleteEvent(0)));

    sf.ConfirmAddEvent(0, "10:00:00", "11:00:00", 0, "Learning");
    sf.ConfirmAddEvent(0, "11:00:00", "12:00:00", 0, "learning");

    System.out.println(sf.ShowAllEvents());
    System.out.println((sf.ConfirmEditEvent(0, 0, "10:00:00", "11:00:00", "learn", 0)));
    System.out.println((sf.ConfirmEditEvent(0, 1, "10:00:00", "11:00:00", "l", 0)));
    System.out.println(sf.get_single_event(0));

    sf.ConfirmAddEvent(0, "10:00:00", "11:00:00", 0, "Learning");
    sf.ConfirmAddEvent(0, "11:00:00", "12:00:00", 0, "learning");
    sf.ConfirmAddEvent(1, "10:00:00", "11:00:00", 2, "learn");
    String tmp = "Event{id=0, speakerId=0, roomId=0, startTime=10:00:00, endTime=11:00:00, topic='Learning', attendees=[]}";
    System.out.println((Objects.equals(sf.get_single_event(0), tmp)));
    System.out.println(sf.get_single_event(3));
  }


}
