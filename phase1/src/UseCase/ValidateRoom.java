package UseCase;

import Entity.Room;
import javafx.util.Pair;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yezhou on 2020/11/12
 **/
public class ValidateRoom {
    private HashMap<Room, ArrayList<Pair<Time, Time>>> rooms_list;

    public HashMap<Room, ArrayList<Pair<Time, Time>>> get_rooms_list(){
        return rooms_list;
    }

    public void addRoom(int roomID, int capacity){
        Room rm = new Room(roomID, capacity);
        rooms_list.put(rm, new ArrayList<>());
    }

    public boolean validateRoom(Room rm, Time start, Time end){
        if(!rooms_list.containsKey(rm)){
            return false;
        }
        for(Pair<Time, Time> schedule: rooms_list.get(rm)){
            Time start2 = schedule.getKey();
            Time end2 = schedule.getValue();
            if(start.compareTo(start2) >= 0 && start.compareTo(end2)< 0){
                return false;
            }
            else if(start.compareTo(end) >= 0){
                return false;
            }
            else if(end.compareTo(start2) > 0 && end.compareTo(end2) <= 0){
                return false;
            }
        }
        return true;
    }

    public void give_room_schedule(Room rm, Time start, Time end){
        Pair<Time, Time> p = new Pair<>(start, end);
        rooms_list.get(rm).add(p);
    }

    public boolean del_room_schedule(Room rm, Time start, Time end){
        Pair<Time, Time> p = new Pair<>(start, end);
        if(!rooms_list.containsKey(rm)){
            return false;
        }
        for(Pair<Time, Time> o: rooms_list.get(rm)){
            if(o.equals(p)){
                rooms_list.get(rm).remove(p);
                return true;
            }
        }
        return false;
    }
}



