package Entity;

public class Organizer extends User {

  public Organizer(String username, String password, String phone, String email) {
    super(username, password, phone, email);
    this.identity = "Organizer";
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Organizer)) {
      return false;
    }
    Organizer o = (Organizer) other;
    return o.getUserId() == this.getUserId() && o.getPassword().equals(this.getPassword()) && o
            .getUsername().equals(this.getUsername())
            && o.getEvents().equals(this.getEvents()) && o.getEmail().equals(this.getEmail()) && o
            .getPhone().equals(this.getPhone()) && o.getIdentity().equals(this.getIdentity());
  }
}
