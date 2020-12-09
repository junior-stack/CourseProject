package Gateway;

import Dao.*;
import Entity.*;
import UseCase.EventManager;
import UseCase.SignupManager;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

public class Test {
    public static void testData() throws Exception{
        testRoomAndUser();
        testEventAndMessage();
        testEventManager();
        testSignUpManager();
    }

    public static void testRoomAndUser() throws Exception{
        Room rm1 = new Room(123, 20);
        RoomDao.getInstance().createOrUpdate(rm1);
        Speaker speaker = new Speaker("John", "pass", "1234", "xx@yy.com");
        UserDao.getInstance().createOrUpdate(speaker);
    }

    public static void testEventAndMessage() throws Exception{
        long now = System.currentTimeMillis();
        Time start = new Time(now);
        Time end = new Time(now+1000000);
        Event event = new Event(123, start, end, "test-topic", 20);
        Event event2 = new Event(123, start, end, "test-topic2", 30);
        EventDao.getInstance().createOrUpdate(event);
        EventDao.getInstance().createOrUpdate(event2);
        Message m = new Message("xx@yy.com", "randomEmail", "test content");
        MessageDao.getInstance().createOrUpdate(m);
    }

    public static void testEventManager() throws Exception{
        long now = System.currentTimeMillis();
        Time start = new Time(now);
        Time end = new Time(now+1000000);
        EventManager evm = new EventManager(new ArrayList<>());
        ArrayList<Integer> speakers = new ArrayList<>();
        speakers.add(1);
        speakers.add(2);
        evm.addEvent(123, start, end, "test-topic", 20, "MultipleSpeakerEvent", speakers);
        evm.saveEvents();
    }

    public static void testSignUpManager() throws Exception{
        Speaker speaker = new Speaker("John 2", "pass", "1234", "xx1@yy.com");
        long now = System.currentTimeMillis();
        Time start = new Time(now);
        Time end = new Time(now+1000000);
        Event event = new Event(123, start, end, "test-topic", 20);
        Event event2 = new Event(123, start, end, "test-topic2", 30);
        SignupManager sm = new SignupManager(UserEventDao.getAsHashMap());
        sm.addUserSchedule(speaker, event);
        sm.addUserSchedule(speaker, event2);
        UserDao.getInstance().createOrUpdate(speaker);
        EventDao.getInstance().createOrUpdate(event);
        EventDao.getInstance().createOrUpdate(event2);
        sm.saveUserSchedule();
        sm.deleteUserschedule(speaker, event2);
        sm.saveUserSchedule();
    }
}
