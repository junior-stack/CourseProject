import Controller.ScheduleFacade;

/**
 * Created by yezhou on 2020/11/17
 **/
public class Test {

  public static void main(String[] args) {
    ScheduleFacade sf = new ScheduleFacade();
    //add room
    System.out.println(sf.confirmaddroom(0));
    System.out.println(sf.confirmaddroom(1));
    System.out.println(sf.confirmaddroom(2));
    System.out.println(sf.confirmaddroom(2));
    System.out.println(sf.confirmaddroom(3));
    System.out.println(sf.confirmdeleteroom(3));
    System.out.println(sf.get_rooms());

    //add speaker
    System.out.println(sf.addSpeaker("Jack", "111", "012-345-6789", "k@mail.com"));
    System.out.println(sf.addSpeaker("Jerry", "111", "012-345-6789", "j@mail.com"));
    System.out.println(sf.addSpeaker("Jim", "111", "012-345-6789", "j@mail.com"));

    //schedule event
    System.out.println(sf.ShowAllEvents());

    //delete a non-existing event
    System.out.println(sf.ConfirmDeleteEvent(0));

    //add_event
    System.out.println(sf.ConfirmAddEvent(0, "10:00:00", "11:00:00", 0, "l"));
    System.out.println(sf.ConfirmAddEvent(1, "10:00:00", "11:00:00", 0, "l"));
    System.out.println(sf.ConfirmAddEvent(0, "11:00:00", "12:00:00", 2, "l"));
    System.out.println(sf.get_rooms_schedule());
    System.out.println(sf.getSpeakerSchedule());
    System.out.println("\n");

    //add two conflict events
    System.out.println(sf.ConfirmAddEvent(0, "10:00:00", "11:00:00", 1, "l"));
    System.out.println(sf.ConfirmAddEvent(1, "10:00:00", "11:00:00", 0, "l"));
    System.out.println(sf.ShowAllEvents());
    System.out.println(sf.getSpeakerSchedule());
    System.out.println(sf.get_rooms_schedule());
    System.out.println("\n");

    //edit two events
    System.out.println(sf.ConfirmEditEvent(0, 0, "13:00:00", "14:00:00", "l",0));
    System.out.println(sf.getSpeakerSchedule());
    System.out.println(sf.get_rooms_schedule());
    System.out.println(sf.ShowAllEvents());
    System.out.println("\n");

    //fail to edit event
    System.out.println(sf.ConfirmEditEvent(0, 0, "11:00:00", "12:00:00", "l", 0));
    System.out.println(sf.getSpeakerSchedule());
    System.out.println(sf.get_rooms_schedule());
    System.out.println(sf.ShowAllEvents());
    System.out.println("\n");

    //delete event
    System.out.println(sf.ConfirmDeleteEvent(0));
    System.out.println(sf.getSpeakerSchedule());
    System.out.println(sf.get_rooms_schedule());
    System.out.println(sf.ShowAllEvents());
    System.out.println("\n");

    sf.saveevents();
    sf.saverooms();
    sf.savespeakerschedule();

    ScheduleFacade sf2 = new ScheduleFacade();
    System.out.println(sf2.ShowAllEvents());
    System.out.println(sf2.getSpeakerSchedule());
    System.out.println(sf2.get_rooms_schedule());







  }

}
