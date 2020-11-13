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

  @Override
  public boolean equals(Object other){
    if(!(other instanceof Room)){
      return false;
    }
    return this.getRoomId() == ((Room) other).getRoomId() && this.getCapacity() == ((Room) other).getCapacity();
  }

  @Override
  public int hashCode(){
    return this.getRoomId();
  }
}
