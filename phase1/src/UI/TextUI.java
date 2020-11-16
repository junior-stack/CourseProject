package UI;

import Controller.LoginFacade;
import Controller.MessageController;
import Controller.ScheduleFacade;
import Controller.SignUpController;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TextUI {

  private String email;

  LoginFacade lf = new LoginFacade();
  ScheduleFacade sf = new ScheduleFacade();
  SignUpController suc = new SignUpController();
  MessageController mc = new MessageController(email);

  public void run() throws InterruptedException {
    UserMenu();
  }

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
          System.out.print("Your choice: ");
          sc.nextInt();
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
      System.out.println("[Register Menu] Please enter your email and password to continue...");

      String username = null, password = null, phone = null;
      while (true) {
        try {
          System.out.print("Username: ");
          username = sc.nextLine();
          System.out.print("Password: ");
          password = sc.nextLine();
          System.out.print("Phone: ");
          phone = sc.nextLine();
          System.out.print("Email: ");
          email = sc.nextLine();
          break;
        } catch (InputMismatchException e) {
          System.out.println("Invalid input, please try again.");
          System.out.print("Username: ");
          sc.nextLine();
          System.out.print("Password: ");
          sc.nextLine();
          System.out.print("Phone: ");
          sc.nextLine();
          System.out.print("Email: ");
          sc.nextLine();
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
      System.out.println("[Login Menu] Please enter your email and password to continue...");

      String password = null;
      while (true) {
        try {
          System.out.print("Email: ");
          email = sc.nextLine();
          System.out.print("Password: ");
          password = sc.nextLine();
          break;
        } catch (InputMismatchException e) {
          System.out.println("Invalid input, please try again.");
          System.out.print("Email: ");
          sc.nextLine();
          System.out.print("Password: ");
          sc.nextLine();
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
        }
      } else {
        System.out.println("Login failed, please try agagin.");
        UserMenu();
        break;
      }
    }
  }

<<<<<<< HEAD
  /*
   * 这是一个semi autofill的登录界面
   */
=======
>>>>>>> b53af07d1f11133b418e70dd03cc12a5bed0082e

  private void LoginMenu(String email, String password) throws InterruptedException {
    while (true) {
      Scanner sc = new Scanner(System.in);
      System.out.printf("[Login Menu] Please enter the corresponding number to continue..."
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
          System.out.print("Your choice: ");
          sc.nextInt();
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
            }
          } else {
            System.out.println("Login failed, please try agagin.");
            UserMenu();
            break;
          }
        case 2:
          LoginMenu();
          break;
      }
    }
  }

  private void AttendeeMenu(String email) throws InterruptedException {
    while (true) {
      Scanner sc = new Scanner(System.in);
      System.out.println(
          "[Attendee Menu] Please enter the corresponding number to complete an action"
              + "\n1 - View All Events"
              + "\n2 - View All Signed Up Events"
              + "\n3 - View All Messages"
              + "\n4 - Sign Out");

      int choice;
      while (true) {
        try {
          System.out.print("Your choice: ");
          choice = sc.nextInt();
          break;
        } catch (InputMismatchException e) {
          System.out.println("Invalid input, please try again.");
          System.out.print("Your choice: ");
          sc.nextInt();
        }
      }

      switch (choice) {
        case 1:
          if (sf.ShowAllEvents() != null) {
            ViewAllEventsMenu(email);
            break;
          }
          System.out.println("There are current no available events. Please check again later.");
        case 2:
          if (suc.ViewAllEvents() != null) {
            ViewAllSignedUpEventsMenu(email);
            break;
          }
          System.out.println("You have not signed up any events yet, please sign up.");
        case 3:
          if (mc.readAllMessages() != null) {
            ViewAllMessagesMenu(email);
            break;
          }
          System.out.println("You don't have any messages yet. Please check again later.");
        case 4:
          SignOutRedirect();
          break;
      }
    }
  }

  private void ViewAllEventsMenu(String email) throws InterruptedException {
    while (true) {
      Scanner sc = new Scanner(System.in);
      System.out.println("[All Events Menu] Please enter the corresponding number to continue..."
          + "\n1 - View All Events"
          + "\n2 - Go Back");

      int choice;
      while (true) {
        try {
          System.out.print("Your choice: ");
          choice = sc.nextInt();
          break;
        } catch (InputMismatchException e) {
          System.out.println("Invalid input, please try again.");
          System.out.print("Your choice: ");
          sc.nextInt();
        }
      }

      String userType = lf.getUserIdentity(email);

      switch (choice) {
        case 1:
          SignUpEventsMenu(email);
          break;
        case 2:
          if (userType.equals("Attendee")) {
            AttendeeMenu(email);
            break;
          } else if (userType.equals("Organizer")) {
            OrganizerMenu(email);
            break;
          } else {
            System.out.println("You do not have permission to view all events");
            break;
          }
      }
    }
  }

  private void SignUpEventsMenu(String email) throws InterruptedException {
    while (true) {
      Scanner sc = new Scanner(System.in);
      sf.ShowAllEvents();
      System.out.println("The form of event is <ID> - <NAME>"
          + "\n Please enter the corresponding event <ID> to sign up or 'BACK' to go back");

      String input;
      while (true) {
        try {
          System.out.print("Event <ID> or 'BACK'");
          input = sc.nextLine();
          if (input.equals("BACK")) {
            ViewAllEventsMenu(email);
            break;
          }
          break;
        } catch (InputMismatchException e) {
          System.out.println("Invalid input, please try again.");
          System.out.print("Event <ID> or 'BACK'");
          sc.nextLine();
        }
      }

      int event_ID;
      while (true) {
        try {
          event_ID = Integer.parseInt(input);
          break;
        } catch (NumberFormatException e) {
          System.out.println("Invalid <ID>, please try again.");
          System.out.print("<ID>: ");
          sc.nextLine();
        }
      }

<<<<<<< HEAD
      isSuceess = suc.signup(event_ID); /*Room id拿不到*/
=======
>>>>>>> b53af07d1f11133b418e70dd03cc12a5bed0082e
      if (isSuccess) {
        System.out.printf("[%s] Signed Up Successful!"
            + "\nAutomatically redirect to main menu after 2 second.");
        Thread.sleep(2000);
        AttendeeMenu(email);
        break;
      } else {
        System.out.printf("[%s] Signed Up Failed!"
            + "\nAutomatically redirect to main menu after 2 second.");
        Thread.sleep(2000);
        AttendeeMenu(email);
        break;
      }
    }
  }

  private void ViewAllSignedUpEventsMenu(String email) throws InterruptedException {
    while (true) {
      Scanner sc = new Scanner(System.in);
      System.out.println(
          "[Signed Up Events Menu] Please enter the corresponding number to continue..."
              + "\n1 - View all signed up events"
              + "\n2 - Go Back");

      int choice;
      while (true) {
        try {
          System.out.print("Your choice: ");
          choice = sc.nextInt();
          break;
        } catch (InputMismatchException e) {
          System.out.println("Invalid input, please try again.");
          System.out.print("Your choice: ");
          sc.nextInt();
        }
      }

      switch (choice) {
        case 1:
          ManageSignUpEventsMenu(email);
          break;
        case 2:
          AttendeeMenu(email);
          break;
      }
    }
  }

  private void ManageSignUpEventsMenu(String email) throws InterruptedException {
    while (true) {
      Scanner sc = new Scanner(System.in);
      System.out.println(suc.ViewAllEvents());
      System.out.println("The form of event is <ID> - <NAME>"
          + "\nPlease enter the corresponding number to continue..."
          + "\n1 - Cancel Event"
          + "\n2 - Send Message"
          + "\n3 - Go Back");

      int choice;
      while (true) {
        try {
          System.out.println("Your choice: ");
          choice = sc.nextInt();
          break;
        } catch (InputMismatchException e) {
          System.out.println("Invalid input, please try again.");
          System.out.println("Your choice: ");
          sc.nextInt();
        }
      }

      switch (choice) {
        case 1:
          int event_id;
          while (true) {
            try {
              System.out.println("Please enter the event <ID> you want to cancel: ");
              event_id = sc.nextInt();
              break;
            } catch (InputMismatchException e) {
              System.out.println("Invalid input, please try again.");
              System.out.println("Please enter the event <ID> you want to cancel: ");
              sc.nextInt();
            }
          }
<<<<<<< HEAD
          boolean isSuccess = suc.cancelEvent(event_id); /*拿不了Room*/
=======
>>>>>>> b53af07d1f11133b418e70dd03cc12a5bed0082e

          if (isSuccess) {
            System.out.printf("[%d] Cancel Successful!"
                + "\nAutomatically redirect to main menu after 2 second.", event_id);
            Thread.sleep(2000);
            AttendeeMenu(email);
            break;
          } else {
            System.out.printf("[%d] Cancel Failed!"
                + "\nAutomatically redirect to main menu after 2 second.", event_id);
            Thread.sleep(2000);
            AttendeeMenu(email);
            break;
          }
        case 2:
          String targetEmail, message;
          while (true) {
            try {
              System.out.println("Please enter target's email and your message: ");
              System.out.print("Target Email: ");
              targetEmail = sc.nextLine();
              System.out.print("Message: ");
              message = sc.nextLine();
              break;
            } catch (InputMismatchException e) {
              System.out.println("Invalid input, please try again.");
              System.out.println("Please enter target's email and your message: ");
              System.out.print("Target Email: ");
              sc.nextLine();
              System.out.print("Message: ");
              sc.nextLine();
            }
          }
          List<Integer> evetIds = new ArrayList<>();
          boolean isSent = mc.sendMessages("Single", message, targetEmail, "", evetIds);
          if (isSent) {
            System.out.printf("[%s: %s] Message Sent Successful!"
                + "\nAutomatically redirect to main menu after 2 second.", targetEmail, message);
            Thread.sleep(2000);
            AttendeeMenu(email);
            break;
          } else {
            System.out.printf("[%s: %s] Message Sent Failed!"
                + "\nAutomatically redirect to main menu after 2 second.", targetEmail, message);
            Thread.sleep(2000);
            AttendeeMenu(email);
            break;
          }
        case 3:
          SignUpEventsMenu(email);
      }
    }
  }

  private void ViewAllMessagesMenu(String email) {
    Scanner sc = new Scanner(System.in);
    System.out.println("[All Message Menu] Please enter the corresponding number to continue..."
        + "\n1"
        + "\n2");
  }

  private void SignOutRedirect() throws InterruptedException {
    System.out.println("Signing Out...");
    Thread.sleep(2000);
    UserMenu();
  }

  private void OrganizerMenu(String email) {
    Scanner sc = new Scanner(System.in);
    System.out.println(
        "Welcome to Organizer Menu! Please enter the corresponding number to complete an action"
            + "\n"
            + "\n");
  }

  private void SpeakerMenu(String email) {
    Scanner sc = new Scanner(System.in);
    System.out.println(
        "Welcome to Speaker Menu! Please enter the corresponding number to complete an action"
            + "\n"
            + "\n");
  }
}
