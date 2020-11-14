package UseCase;

import Entity.Speaker;
import Entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SpeakerAccountManager {

  private static List<Speaker> speakerList=new ArrayList<>();

  public SpeakerAccountManager(List<Speaker> speakerList) {
    if (speakerList!=null && speakerList.size()>0) {SpeakerAccountManager.speakerList.addAll(speakerList);}
  }

  public List<Speaker> getSpeakerList() {
    return speakerList;}

  public boolean createSpeaker(String username, String password, String phone, String email){
    if (!this.isValidEmail(email)){
      System.out.println("The email address is invalid"); return false;}
    if (!this.isValidPassword(password)){
      System.out.println("Password cannot contain space nor be blank"); return false;}
    if (!this.isValidPhone(phone)){
      System.out.println("Phone number should only contain numeric characters"); return false;}

    speakerList.add(new Speaker(username, password, phone, email));
    System.out.println("The Speaker account has been successfully created");
    return true;
  }

  private boolean isValidEmail(String email){
    // Create a regular expression format for a valid email
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
        "[a-zA-Z0-9_+&*-]+)*@" +
        "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
        "A-Z]{0,9}$";
    //Check if the email address matches the regex format
    Pattern emailPat = Pattern.compile(emailRegex);
    if (email == null){
      return false;}

    return emailPat.matcher(email).matches();
  }

  private boolean isValidPassword(String password){
    return !password.isEmpty() && !password.contains(" ");
  }

  private boolean isValidPhone(String phone) {
    return phone.matches("^\\(?([0-9]{3})\\)?[-]?([0-9]{3})[-]?([0-9]{4})$");
  }

  public List helperfilterspeaker(List list) {
    ArrayList speakers = new ArrayList();
    if (list != null && list.size() > 0) {
      for (i:list) {
      }
    }
    return speakers;


  }
}
