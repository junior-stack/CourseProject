package UseCase;

import Entity.Speaker;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ValidateSpeaker {
    private HashMap<Speaker, ArrayList<Time>> speaker_list;

    public void addSpeaker(String SpeakerName, String Password, String phone, String email){

        Speaker speaker = new Speaker(SpeakerName, Password, phone, email);

        speaker_list.put(speaker, null);
    }

    public boolean validateSpeaker(Speaker speaker, Time start, Time end) {
        Collection<ArrayList<Time>> list = speaker_list.values();
        boolean indicator = false;
        for (ArrayList<Time> i : list) {
            if ((i != null) && (i.get(0).compareTo(start) <= 0) && (i.get(1).compareTo(end) >= 0)) {
                continue;
            } else if (i != null) {
                indicator = true;
            }
        }
        return indicator;
    }

    public void giveSpeakerNewSchedule(Speaker speaker, Time start, Time end){
        ArrayList<Time> temp = new ArrayList<>();
        temp.add(start);
        temp.add(end);
        boolean i = false;
        for (Map.Entry<Speaker, ArrayList<Time>> s : speaker_list.entrySet()) {
            if (s.getKey().getUserId() == speaker.getUserId()) {
                s.setValue(temp);
                i = true;
                break;
            }
        }
        if (!i) {
            speaker_list.put(speaker, temp);
        }
    }

    public void delSpeakerSchedule(Speaker speaker){
        for (Map.Entry<Speaker, ArrayList<Time>> s : speaker_list.entrySet()) {
            if (s.getKey().getUserId() == speaker.getUserId()) {
                s.setValue(null);
                break;
            }
        }
    }

    public HashMap<Speaker, ArrayList<Time>> getSpeakerList(){
        return speaker_list;
    }
}
