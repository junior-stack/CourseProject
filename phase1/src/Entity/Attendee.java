package Entity;

public class Attendee extends User {

  public Attendee(String username, String password, String phone, String email) {
    super(username, password, phone, email);
    this.identity = "Attendee";
  }


  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Attendee)) {
      return false;
    }
    Attendee o = (Attendee) other;
    return o.getUserId() == this.getUserId() && o.getPassword().equals(this.getPassword()) && o
            .getUsername().equals(this.getUsername())
            && o.getEvents().equals(this.getEvents()) && o.getEmail().equals(this.getEmail()) && o
            .getPhone().equals(this.getPhone()) && o.getIdentity().equals(this.getIdentity());
  }
}
