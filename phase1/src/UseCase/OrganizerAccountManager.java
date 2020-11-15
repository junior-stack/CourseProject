package UseCase;

import Entity.Organizer;
import Entity.User;

import java.util.ArrayList;
import java.util.List;

public class OrganizerAccountManager {

    private static List<User> organizerList;


    public OrganizerAccountManager(List<User> organizerList) {OrganizerAccountManager.organizerList=organizerList;}

    public OrganizerAccountManager() {};

    public void createOrganizer(List<String> organizers){
        if (organizers.size()<=1){return ;}
        for (int i = 1; i < organizers.size(); i++) {
            String[] temp = organizers.get(i).split(",");
            Organizer neworganizer =new Organizer(temp[0], temp[1], temp[2], temp[3]);
            OrganizerAccountManager.organizerList.add(neworganizer);
        }
    }


    public List filterexistingspeaker(List<User>list) {
        ArrayList speakers = new ArrayList();
        if (list != null && list.size() > 0) {
            for (User i:list){if (i.getIdentity().equals("Speaker")){speakers.add(i);}
            }
        }
        return speakers;

    }

}
