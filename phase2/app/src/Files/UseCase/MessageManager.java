package UseCase;

import Entity.Event;
import Entity.Message;
import Entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class representing a MessageManager.
 *
 * @author Zhongyuan Liang & Jiahao Zhang
 * @version 1.0
 */
public class MessageManager {
    private List<Message> messages = new ArrayList<>();

    public List<Message> getMessages(){
        return messages;
    }

    public MessageManager(ArrayList<Message> messages){
        this.messages.addAll(messages);
    }

    public void singleMessageRequest(String senderEmail, String receiverEmail, String content){
        Message m = new Message(senderEmail, receiverEmail, content);
        messages.add(m);
    }

    public void multipleMessageRequest(String senderEmail, ArrayList<String> receiverEmails, String content){
        for (String e : receiverEmails){
            singleMessageRequest(senderEmail, e, content);
        }
    }

    public boolean validateResponse(String speakerEmail, String receiverEmail){
        for (Message m : messages){
            if (m.getsenderEmail().equals(receiverEmail) && m.getsenderEmail().equals(speakerEmail)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> OrganizerGenerateEmail(String targetIdentity){
        List<User> users = UserAccountManager.userList;
        ArrayList<String> emails = new ArrayList<>();
        for (User u : users){
            if (u.getIdentity().equals(targetIdentity)){
                emails.add(u.getEmail());
            }
        }
        return emails;
    }

    public ArrayList<String> SpeakerGenerateEmail(ArrayList<Integer> eventIds){
        List<Event> events = EventManager.eventpool;
        List<Event> e = new ArrayList<>();
        for (Event temp: events){
            if (eventIds.contains(temp.getId())){
                e.add(temp);
            }
        }
        ArrayList<Integer> userIds = new ArrayList<>();
        for (Event temp2 : e){
            userIds.addAll(temp2.getAllAttendee());
        }
        ArrayList<String> emails = new ArrayList<>();
        for (Integer Id: userIds){
            emails.add(UserAccountManager.idToEmail(Id));
        }
        return emails;
    }

    public ArrayList<Message> readMessages(String receiverEmail, String Status){
        ArrayList<Message> m = new ArrayList<>();
        for (Message temp: messages){
            if (temp.getreceiverEmail().equals(receiverEmail) && temp.getcurrentStatus().equals(Status)){
                m.add(temp);
            }
        }
        return m;
    }

    public void delete(int messageId){
        for (Message m : messages){
            if (m.getmessageId() == messageId){
                m.setCurrentStatus("delete");
            }
        }
    }

    public void archive(int messageId){
        for (Message m : messages){
            if (m.getmessageId() == messageId){
                m.setCurrentStatus("archive");
            }
        }
    }

}
