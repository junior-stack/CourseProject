package Controller;

import Entity.Room;
import UseCase.ValidateRoom;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by yezhou on 2020/11/12
 **/
public class RoomController {

  private ValidateRoom vr;

  public RoomController(ValidateRoom vr) {
    this.vr = vr;
  }

  public void confirmaddroom() {
    Scanner keyboard = new Scanner(System.in);
    System.out.println("enter an integer");
    int roomID = keyboard.nextInt();
    System.out.println("enter room capacity");
    String capacity = keyboard.nextLine();
    vr.addRoom(roomID, capacity);
  }

  public boolean confirmdeleteroom() {
    Scanner keyboard = new Scanner(System.in);
    System.out.println("enter an integer");
    int roomID = keyboard.nextInt();
    for (Room o : vr.get_rooms_list().keySet()) {
      if (o.getRoomId() == roomID) {
        if (vr.get_rooms_list().get(o).isEmpty()) {
          vr.get_rooms_list().remove(o);
          return true;
        } else {
          return false;
        }
      }
    }
    return false;
  }

  public Set<Room> get_rooms() {

    return vr.get_rooms_list().keySet();
  }

  public HashMap<Room, ArrayList<ArrayList<Time>>> get_rooms_schedule() {
    return vr.get_rooms_list();
  }
}
