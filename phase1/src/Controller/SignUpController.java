package Controller;

import UseCase.EventManager;
import UseCase.UserAccountManager;
import UseCase.Userschedule;
import UseCase.ValidateRoom;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yezhou on 2020/11/14
 **/
public class SignUpController {
  private ValidateRoom vr;
  private Userschedule us;
  private UserAccountManager uam;
  private EventManager em;
  private Integer user_id;

  public ArrayList<String> vieweventRegister(){
    try {
      return us.get_user_schedule_info(uam.get_user(user_id));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public HashMap<Integer, String> ViewAllEvents(){
    return em.get_events_info();
  }

  public ArrayList<String> browse(String topic){
    return em.browse(topic);
  }

  public boolean signup(int event_id)

}
