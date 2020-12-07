package com.group0014.iconference.UseCase;

import java.util.regex.Pattern;

public interface AccountManager {

    /**
     * This method checks is the email is valid.
     *
     * @param email
     * @return boolean whether email is valid or not.
     */
    public default boolean isValidEmail(String email) {
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
    /**
     * This method checks is the password is valid.
     *
     * @param password
     * @return boolean whether password is valid or not.
     */
    public default boolean isValidPassword(String password) {
        return !password.isEmpty() && !password.contains(" ");
    }
    /**
     * This method checks is the phone is valid.
     *
     * @param phone
     * @return boolean whether phone is valid or not.
     */
    public default boolean isValidPhone(String phone) {
        return phone.matches("^\\(?([0-9]{3})\\)?[-]?([0-9]{3})[-]?([0-9]{4})$");
    }


    boolean existingUser(String email);

    boolean createAttendee(String username, String password, String phone, String email);

    boolean createSpeaker(String username, String password, String phone, String email);

    boolean verifyUser(String email, String password);
}
