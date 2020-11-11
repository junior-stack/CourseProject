package Entity;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class OneSpeakerEvent extends Event {


    public OneSpeakerEvent(int speakerId, int roomId, Time startTime, Time endTime, String topic) {
        super(roomId, startTime, endTime, topic);
        this.speakerId = speakerId;
    }
    public int getSpeakerId() {return speakerId;}

    public void setSpeakerId(int speakerId) {this.speakerId = speakerId; }

}
