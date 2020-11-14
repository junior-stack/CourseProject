package UI;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TextUI {

  private void UserMenu() throws InterruptedException {
    while (true) {
      Scanner sc = new Scanner(System.in);
      System.out.println(
          "Welcome to our project! \n Please enter the corresponding number to complete an action: "
              + "\n1 - Register(Attendee ONLY)"
              + "\n2 - Login");

      int choice = 0;
      try {
        System.out.print("Your choice: ");
        choice = sc.nextInt();
      } catch (InputMismatchException e) {
        System.out.println("Invalid input, please try again.");
      }

      switch (choice) {
        case 1:
          RegisterMenu();
          break;
        case 2:
          LoginMenu();
          break;
      }
    }
  }

  private void RegisterMenu() throws InterruptedException {
    while (true) {
      Scanner sc = new Scanner(System.in);
      System.out.println("Hi! Please enter your email and password to continue...");

      String email = null, password = null;
      try {
        System.out.print("Email: ");
        email = sc.nextLine();
        System.out.print("Password: ");
        password = sc.nextLine();
      } catch (InputMismatchException e) {
        System.out.println("Invalid input, please try again.");
      }

      boolean isSuccess = false; /*这个地方加Register的方法*/
      if (isSuccess) {
        System.out.printf("Register successful! Please remember your email and password."
                + "\nEmail: %s"
                + "\nPassword: %s"
                + "\nAutomatically redirect to Login Menu after 2 seconds",
            email, password);
        Thread.sleep(2000);
        LoginMenu(email, password);
        break;
      } else {
        System.out.println("Register failed, please try agagin.");
        RegisterMenu();
        break;
      }
    }
  }

  private void LoginMenu() {

  }

  /*
   * 这是一个semi autofill的登录界面
   */
  private void LoginMenu(String email, String password) {
    while (true) {
      System.out.printf("Please enter the corresponding number to continue..."
              + "Email: %s"
              + "Password: %s"
              + "\n1 - Confirm Login"
              + "\n2 - Has another account?",
          email, password);
    }
  }

  private void AttendeeMenu(String email) {

  }

  private void OrganizerMenu(String email) {

  }

  private void SpeakerMenu(String email) {

  }
}
