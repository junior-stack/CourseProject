package Entity;

import java.io.Serializable;

public class Speaker extends User {

  public Speaker(String username, String password, String phone, String email) {
    super(username, password, phone, email);
    this.identity = "Speaker";
  }

  @Override
  public boolean equals(Object other){
    if(!(other instanceof Speaker)){
      return false;
    }
    Speaker o = (Speaker) other;
    return o.getUserId() == this.getUserId() && o.getPassword().equals(this.getPassword()) && o.getUsername().equals(this.getUsername())
    && o.getEvents().equals(this.getEvents()) && o.getEmail().equals(this.getEmail()) && o.getPhone().equals(this.getPhone()) && o.getIdentity().equals(this.getIdentity());
  }

  @Override
  public int hashCode(){
    return this.getUserId();
  }
}
