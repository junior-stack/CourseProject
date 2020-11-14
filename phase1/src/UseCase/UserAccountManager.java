package UseCase;

import Entity.Attendee;
import Entity.Speaker;
import Entity.User;
import Entity.Organizer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class UserAccountManager {

    public static List<User> userList;


    public UserAccountManager(List<User> userList) {

        UserAccountManager.userList = userList;
        SpeakerAccountManager sam = new SpeakerAccountManager();
        userList.addAll(sam.getSpeakerList());

    }

    public boolean createAttendee(String username, String password, String phone, String email) {
        if(existingUser(email)){System.out.println("Please choose different email");}

        if (!this.isValidEmail(email)) {
            System.out.println("The email address is invalid");
            return false;
        }
        if (!this.isValidPassword(password)) {
            System.out.println("Password cannot contain space nor be blank");
            return false;
        }
        if (!this.isValidPhone(phone)) {
            System.out.println("Phone number should only contain numeric characters");
            return false;
        }

        userList.add(new Attendee(username, password, phone, email));
        System.out.println("The User account has been successfully created.");
        return true;
    }


    private boolean isValidEmail(String email) {
        // Create a regular expression format for a valid email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{0,9}$";
        //Check if the email address matches the regex format
        Pattern emailPat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }

        return emailPat.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return !password.isEmpty() && !password.contains(" ");
    }

    private boolean isValidPhone(String phone) {
        return phone.matches("^\\(?([0-9]{3})\\)?[-]?([0-9]{3})[-]?([0-9]{4})$");
    }

    public boolean existingUser(String email) {
        for (User i : userList) {
            if (i.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean verifyUser(String email, String password) {
        for (User i : userList) {
            if ((i.getEmail().equals(email)) && i.getPassword().equals(password)) {
                System.out.println("Provided Information Match With Registered");
                return true;
            }
        }
        System.out.println("Provided Information Does Not Match With Registered");
        return false;
    }

    public List<User> getUserList() {
        return userList;
    }

    public String getUserIdentity(String email) {
        for (User i : userList) {
            if (i.getEmail().equals(email)) {
                return i.getIdentity();
            }
        }
        return "User does not exist";
    }

    public List<String> getAllEmails() {
        ArrayList<String> emailCollection = new ArrayList<String>();
        for (User user : userList) {
            emailCollection.add(user.getEmail());
        }
        return emailCollection;
    }
    public String getUserInfo (String email){
        for (User i: userList){if (i.getEmail()==(email)){return i.toString();}}
        return "User does not exist";
    }




}