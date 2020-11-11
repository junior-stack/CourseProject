package Entity;

public class Room {
    final int roomId;
    private int capacity;
    private int remainingSpot;

    public Room(int roomId, int capacity) {
        this.roomId = roomId;
        this.capacity = capacity;
        //this.remainingSpot = capacity;
    }

    public int getRoomId() {
        return roomId;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    //public int getRemainingSpot() {return remainingSpot; }
    //public void setRemainingSpot(int remainingSpot) {this.remainingSpot = remainingSpot;}

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", capacity=" + capacity +
                '}';
    }
}
