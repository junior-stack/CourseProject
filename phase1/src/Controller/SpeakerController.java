package Controller;

import Entity.Speaker;
import UseCase.ValidateRoom;
import UseCase.ValidateSpeaker;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class SpeakerController {

  private ValidateSpeaker vs;

  public SpeakerController() {
    ValidateSpeaker vs = new ValidateSpeaker();
  }

  public void delSpeakerSchedule() {
    Scanner sc = new Scanner(System.in);
    System.out.println("enter an integer");
    int speakerID = sc.nextInt();
    System.out.println("enter start time (format: hh: mm: ss)");
    String time1 = sc.nextLine(); //default format: hh:mm:ss.
    Time start = java.sql.Time.valueOf(time1);
    System.out.println("enter end time (format: hh: mm: ss)");
    String time2 = sc.nextLine();
    Time end = java.sql.Time.valueOf(time2);//default format: hh:mm:ss.
    vs.delSpeakerSchedule(speakerID, start, end);
  }

  public void addSpeaker() {
    Scanner keyboard = new Scanner(System.in);
    System.out.println("enter an integer");
    String SpeakerName = keyboard.nextLine();
    System.out.println("enter a password");
    String Password = keyboard.nextLine();
    System.out.println("enter a phone");
    String phone = keyboard.nextLine();
    System.out.println("enter a email");
    String email = keyboard.nextLine();
    vs.addSpeaker(SpeakerName, Password, phone, email);
  }

  public HashMap<Speaker, ArrayList<ArrayList<Time>>> getSpeakerSchedule() {
    return vs.getSpeakerList();
  }

  public Collection<Speaker> getSpeakers() {
    return vs.getSpeakerList().keySet();
  }
}
