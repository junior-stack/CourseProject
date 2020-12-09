package UseCase;

import Entity.Room;
import Entity.Schedulable;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by yezhou on 2020/11/29
 **/
public class RoomManager implements Iterable<Room> {

    List<Room> roomList;

    public RoomManager(List<Room> roomList) {
        this.roomList = roomList;
    }

    public Room getSingleRoom(int roomID) {
        for (Room tmp : this) {
            if (tmp.getRoomName() == roomID) {
                return tmp;
            }
        }
        return null;
    }

    public List<Schedulable> getRoomList() {
        List<Schedulable> tmp = new ArrayList<>();
        for (Room room : this) {
            tmp.add(room);
        }
        return tmp;
    }

    public boolean addRoom(int roomID, int capacity) {
        for (Room curr : this) {
            if (curr.getRoomName() == roomID) {
                return false;
            }
        }
        Room rm2 = new Room(roomID, capacity);
        roomList.add(rm2);
        return true;
    }

    public boolean delRoom(int roomID) {
        Iterator<Room> rmi = this.iterator();
        Room curr = null;
        while (rmi.hasNext()) {
            curr = rmi.next();
            if (curr.getRoomName() == roomID) {
                return false;
            }
        }
        if (curr == null) {
            return false;
        } else {
            return roomList.remove(curr);
        }
    }

    public List<Room> getRooms() {
        return roomList;
    }


    public Integer getCapacity(int roomID) {
        Room tmp = getSingleRoom(roomID);
        if (tmp != null) {
            return tmp.getRoomName();
        }
        return null;
    }

    public boolean CheckRemainingSpot(Integer roomID, Time start, Time end) {
        Integer tmp = getRemainingSpot(roomID, start, end);
        if (tmp == null) {
            return false;
        }
        return tmp != 0;
    }

    public Integer getRemainingSpot(Integer roomID, Time start, Time end) {
        Room rm = getSingleRoom(roomID);
        if (rm == null) {
            return null;
        }
        return rm.getRemainingSpot(start, end);
    }

    public boolean IncreaseRemainingSpot(Integer roomID, Time start, Time end) {
        Room rm = getSingleRoom(roomID);
        if (rm == null)
            return false;
        if (rm.getRemainingSpot(start, end) == rm.getCapacity())
            return false;
        rm.IncreaseRemainingspot(start, end);
        return true;
    }

    public boolean DecreaseRemainingSpot(Integer roomID, Time start, Time end) {
        Room rm = getSingleRoom(roomID);
        if (rm == null)
            return false;
        if (rm.getRemainingSpot(start, end) == 0)
            return false;
        rm.DecreaseRemainingspot(start, end);
        return true;
    }

    @Override
    public Iterator<Room> iterator() {
        return new RoomManagerIterator();
    }

    private class RoomManagerIterator implements Iterator<Room> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < roomList.size();
        }

        @Override
        public Room next() {
            Room res;
            try {
                res = roomList.get(current);
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
            current += 1;
            return res;

        }

        @Override
        public void remove() {
            roomList.remove(current - 1);
        }
    }

}

