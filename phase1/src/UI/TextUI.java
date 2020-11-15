package UI;

import Controller.LoginFacade;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TextUI {

  LoginFacade lf = new LoginFacade();

  private void UserMenu() throws InterruptedException {
    while (true) {
      Scanner sc = new Scanner(System.in);
      System.out.println(
          "Welcome to our project! \n Please enter the corresponding number to complete an action: "
              + "\n1 - Register(Attendee ONLY)"
              + "\n2 - Login");

      int choice = 0;
      while (true) {
        try {
          System.out.print("Your choice: ");
          choice = sc.nextInt();
          break;
        } catch (InputMismatchException e) {
          System.out.println("Invalid input, please try again.");
        }
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

      String username = null, password = null, phone = null, email = null;
      while (true) {
        try {
          System.out.print("Username: ");
          username = sc.nextLine();
          System.out.print("Password: ");
          password = sc.nextLine();
          System.out.print("Phone: ");
          phone = sc.nextLine();
          System.out.print("Phone: ");
          phone = sc.nextLine();
          break;
        } catch (InputMismatchException e) {
          System.out.println("Invalid input, please try again.");
        }
      }

      boolean isSuccess = lf.register(username, password, phone, email);
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
        UserMenu();
        break;
      }
    }
  }

  private void LoginMenu() throws InterruptedException {
    while (true) {
      Scanner sc = new Scanner(System.in);
      System.out.println("Please enter your email and password to continue...");

      String email = null, password = null;
      while (true) {
        try {
          System.out.print("Email: ");
          email = sc.nextLine();
          System.out.print("Password: ");
          password = sc.nextLine();
          break;
        } catch (InputMismatchException e) {
          System.out.println("Invalid input, please try again.");
        }
      }

      boolean isSuccess = lf.login(email, password);
      if (isSuccess) {
        String userType = lf.getUserIdentity(email);
        switch (userType) {
          case "Attendee":
            AttendeeMenu(email);
            break;
          case "Organizer":
            OrganizerMenu(email);
            break;
          case "Speaker":
            SpeakerMenu(email);
            break;
          default:
            System.out.println(
                "Fatal Error: User type not found!" + "\nPlease contact our customer support");
            break;
        }
      } else {
        System.out.println("Register failed, please try agagin.");
        UserMenu();
        break;
      }
    }
  }

  /*
   * 这是一个semi autofill的登录界面
   */
  private void LoginMenu(String email, String password) throws InterruptedException {
    while (true) {
      Scanner sc = new Scanner(System.in);
      System.out.printf("Please enter the corresponding number to continue..."
              + "Email: %s"
              + "Password: %s"
              + "\n1 - Confirm Login"
              + "\n2 - Has another account?",
          email, password);

      int choice = 0;
      while (true) {
        try {
          System.out.print("Your choice: ");
          choice = sc.nextInt();
          break;
        } catch (InputMismatchException e) {
          System.out.println("Invalid input, please try again.");
        }
      }

      boolean isSuccess = false;
      switch (choice) {
        case 1:
          isSuccess = lf.login(email, password);
          if (isSuccess) {
            String userType = lf.getUserIdentity(email);
            switch (userType) {
              case "Attendee":
                AttendeeMenu(email);
                break;
              case "Organizer":
                OrganizerMenu(email);
                break;
              case "Speaker":
                SpeakerMenu(email);
                break;
              default:
                System.out.println(
                    "Fatal Error: User type not found!" + "\nPlease contact our customer support");
                break;
            }
          } else {
            System.out.println("Register failed, please try agagin.");
            UserMenu();
            break;
          }
        case 2:
          LoginMenu();
          break;
      }
    }
  }

  private void AttendeeMenu(String email) {

  }

  private void OrganizerMenu(String email) {

  }

  private void SpeakerMenu(String email) {

  }
}
