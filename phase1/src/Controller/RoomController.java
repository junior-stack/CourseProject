package Controller;

import Entity.Room;
import UseCase.ValidateRoom;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import javafx.util.Pair;

/**
 * Created by yezhou on 2020/11/12
 **/
public class RoomController {
    private ValidateRoom vr;

    public RoomController(ValidateRoom vr){
        this.vr = vr;
    }

    public void confirmaddroom(int roomID, int capacity){
        vr.addRoom(roomID, capacity);
    }

    public boolean confirmdeleteroom(int roomID){
        for(Room o:vr.get_rooms_list().keySet()){
            if(o.getRoomId() == roomID){
                if(vr.get_rooms_list().get(o).isEmpty()){
                    vr.get_rooms_list().remove(o);
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return false;
    }

    public Set<Room> get_rooms(){
        return vr.get_rooms_list().keySet();
    }

    public HashMap<Room, ArrayList<ArrayList<Time>>> get_rooms_schedule(){
        return vr.get_rooms_list();
    }
}
