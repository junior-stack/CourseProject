package UseCase;

import Entity.Organizer;
import Entity.User;
import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a OrganizerAccountManager.
 *
 * @author Ziwei Jia & Yufei Wang
 * @version 1.0
 */
public class OrganizerAccountManager {

  private static List<User> organizerList;

  /**
   * Create an OrganizerAccountManager with given organizerList.
   *
   * @param organizerList
   */
  public OrganizerAccountManager(List<User> organizerList) {
    OrganizerAccountManager.organizerList = organizerList;
  }


  /**
   * Create an empty OrganizerAccountManager.
   */
  public OrganizerAccountManager() {
  }

  ;

  /**
   * This method creates organizers by adding all the organizers from the list.
   *
   * @param organizers
   */
  public void createOrganizer(List<String> organizers) {
    if (organizers.size() <= 1) {
      return;
    }
    for (int i = 1; i < organizers.size(); i++) {
      String[] temp = organizers.get(i).split(",");
      Organizer neworganizer = new Organizer(temp[0], temp[1], temp[2], temp[3]);
      OrganizerAccountManager.organizerList.add(neworganizer);
    }
  }


  /**
   * return a List contains all the speakers in the given list of Users.
   *
   * @param list
   * @return list of all speakers in the User list
   */
  public List filterexistingspeaker(List<User> list) {
    ArrayList speakers = new ArrayList();
    if (list != null && list.size() > 0) {
      for (User i : list) {
        if (i.getIdentity().equals("Speaker")) {
          speakers.add(i);
        }
      }
    }
    return speakers;

  }

}
