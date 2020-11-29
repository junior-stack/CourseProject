package UseCase;

import Entity.Room;
import Entity.Schedulable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yezhou on 2020/11/29
 **/
public class RoomManager {

  List<Room> roomList;

  RoomManager(List<Room> roomList){
    this.roomList = roomList;
  }

  public List<Schedulable> getRoomList(){
    List<Schedulable> tmp = new ArrayList<>();
    for(Room rm: roomList){
      tmp.add(rm);
    }
    return tmp;
  }

  public void addRoom(int roomID, int capacity){
    roomList.add(new Room(roomID, capacity));
  }

  public boolean delRoom(int roomID){
    for(Room rm: roomList){
      if(rm.getRoomName() == roomID){
        roomList.remove(rm);
        return true;
      }
    }
    return false;
  }

  public List<Room> getRooms() {
    return roomList;
  }

  public Integer getCapacity(int roomID){
    for(Room rm: roomList){
      if(rm.getRoomName() == roomID)
        return rm.getCapacity();
    }
    return null;
  }

  public boolean CheckRemainingSpot(Integer roomID, Time start, Time end){
    for(Room rm: roomList){
      if(rm.getRoomName() == roomID){
        return rm.getRemainingSpot(start, end) == 0;
      }
    }
    return false;
  }

  public Integer getRemainingSpot(Integer roomID, Time start, Time end){
    for(Room rm: roomList){
      if(rm.getRoomName() == roomID){
        return rm.getRemainingSpot(start, end);
      }
    }
    return null;
  }

  public boolean IncreaseRemainingSpot(Integer roomID, Time start, Time end){
    for(Room rm: roomList){
      if(rm.getRoomName() == roomID && rm.getRemainingSpot(start, end) < rm.getCapacity()){
        return rm.IncreaseRemainingspot(start, end);
      }
    }
    return false;
  }

  public boolean DecreaseRemainingSpot(Integer roomID, Time start, Time end){
    for(Room rm: roomList){
      if(rm.getRoomName() == roomID && rm.getRemainingSpot(start, end) > 0){
        return rm.DecreaseRemainingspot(start, end);
      }
    }
    return false;
  }

}
