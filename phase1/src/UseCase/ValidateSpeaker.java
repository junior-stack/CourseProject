package UseCase;

import Entity.Room;
import Entity.Speaker;
import javafx.util.Pair;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ValidateSpeaker {

    private HashMap<Speaker, ArrayList<Pair<Time, Time>>> speaker_list;

    public void addSpeaker(String SpeakerName, String Password, String phone, String email){

        Speaker speaker = new Speaker(SpeakerName, Password, phone, email);

        speaker_list.put(speaker, null);
    }

    public boolean validateSpeaker(Speaker speaker, Time start, Time end) {
        if (!speaker_list.containsKey(speaker)) {
            return false;
        }
        for (Pair<Time, Time> schedule : speaker_list.get(speaker)) {
            Time start2 = schedule.getKey();
            Time end2 = schedule.getValue();
            if ((start.compareTo(start2) >= 0 && start.compareTo(end2) < 0) | start.compareTo(end) >= 0 |
                    (end.compareTo(start2) > 0 && end.compareTo(end2) <= 0)) {
                return false;
            }
        }
        return true;
    }

    public void giveSpeakerNewSchedule(Speaker speaker, Time start, Time end){
        Pair<Time, Time> temp = new Pair<>(start, end);
        ArrayList<Pair<Time, Time>> temp1 = speaker_list.get(speaker);
        int indicator = 0;
        for (Pair<Time, Time> time : temp1){
            if (time.equals(temp)) {
                indicator = 1;
                break;
            }
        }
        if (indicator == 0){
            temp1.add(temp);
        }
    }

    public boolean delSpeakerSchedule(Speaker speaker, Time start, Time end) {
        Pair<Time, Time> p = new Pair<>(start, end);
        if (!speaker_list.containsKey(speaker)) {
                return false;
        }
        for (Pair<Time, Time> o : speaker_list.get(speaker)) {
            if (o.equals(p)) {
                speaker_list.get(speaker).remove(p);
                return true;
            }
        }
        return false;
    }

    public HashMap<Speaker, ArrayList<Pair<Time, Time>>> getSpeakerList(){

        return speaker_list;

    }
}
