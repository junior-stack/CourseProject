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
}
