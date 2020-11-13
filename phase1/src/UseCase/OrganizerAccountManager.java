package UseCase;

import Entity.Organizer;
import Entity.User;

import java.util.ArrayList;
import java.util.List;
import javax.jws.soap.SOAPBinding.Use;

public class OrganizerAccountManager {

  private static List<User> organizerList = new ArrayList<>();

  public void createOrganizer(List<String> organizers){
    if (organizers.size() <= 1){return ;}
    for (int i = 1; i < organizers.size(); i++){
      String[] temp = organizers.get(i).split(",");
      if (!(existingUser(temp[3]))){
        organizerList.add(new Organizer(temp[0], temp[1], temp[2], temp[3]));
      }
    }
  }

  public boolean existingUser(String email){
    if (organizerList.size() > 0){
      for (User i:organizerList){
        if (i.getEmail().equals(email)){
          return true;
        }
      }
    }
    return false;
  }

  public List<User> getOrganizerList(List<String> organizers){
    createOrganizer(organizers);
    return organizerList;
  }

}
