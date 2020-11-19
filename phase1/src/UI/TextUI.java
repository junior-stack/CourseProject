package UI;

import Controller.LoginFacade;
import Controller.MessageController;
import Controller.ScheduleFacade;
import Controller.SignUpController;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class TextUI {


  /**
   * Global variables
   */
  private String email;

  LoginFacade lf = new LoginFacade();
  ScheduleFacade sf = new ScheduleFacade();
  SignUpController suc = new SignUpController(email, lf.getUam(), sf.getVr(), sf.getEm());
  MessageController mc = new MessageController(email);

  /**
   * Method to run the whole program
   */
  public void run() {
    UserMenu();
  }

  /**
   * The first menu user will see, contains 2 options. One for register(attendee only), and one for
   * anyone to login. The user must enter the number shows before "-" to continue.
   */
  private void UserMenu() {
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

  /**
   * This is the register menu after user enter "1" in the user menu. This menu only allows attendee
   * to register. User have to enter their username, password, phone and email to continue. If the
   * input type doesn't match the program, users have to enter their info again until the type is
   * correct. If they failed to login, the program will display a message to tell them login failed
   * and automatically redirect them back to user menu and start again.
   */
  private void RegisterMenu() {
    while (true) {
      Scanner sc = new Scanner(System.in);
      System.out.println("[Register Menu] Please enter your email and password to continue...");

      String username, password, phone;
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
                + "\nRedirecting to login menu...",
            email, password);
        LoginMenu(email, password);
        break;
      } else {
        System.out.println("Register failed, please try again.");
        UserMenu();
        break;
      }
    }
  }

  /**
   * This is a manually login menu for users to enter their information by hands to login. If the
   * input type doesn't match the program, users have to enter their info again until the type is
   * correct. If they failed to login, the program will display a message to tell them login failed
   * and automatically redirect them back to user menu and start again.
   */
  private void LoginMenu() {
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
        System.out.println("Login failed, please try again.");
        UserMenu();
        break;
      }
    }
  }

  /**
   * This is a semi-autofill login menu. The reason I called it semi is because it can only be used
   * after register menu. User won't get access to this menu after enter "2" in the user menu.
   * However, user do get a chance to manually input their info if they have another account by just
   * simply enter "2" in the console.
   *
   * @param email    - email automatically inputted from RegisterMenu.
   * @param password - password automatically inputted from RegisterMenu.
   */
  private void LoginMenu(String email, String password) {
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
            System.out.println("Login failed, please try again.");
            UserMenu();
            break;
          }
        case 2:
          LoginMenu();
          break;
      }
    }
  }

  /**
   * This is the first big screen section, used for attendees. Once attendee logged in successfully
   * the program will prompt more options for them to select.
   *
   * @param email - email is inputted in login menu.
   */
  private void AttendeeMenu(String email) {
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

  /**
   * This is one of the three large sections under attendee menu.
   *
   * @param email - email is inputted in login menu.
   */
  private void ViewAllEventsMenu(String email) {
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

  /**
   * This is a small menu under view all events menu.
   *
   * @param email - email is inputted in login menu.
   */
  private void SignUpEventsMenu(String email) {
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

      boolean isSuccess = suc.signup(event_ID); /*Room id拿不到*/
      if (isSuccess) {
        System.out.printf("[%s] Signed Up Successful!"
            + "\nRedirecting to main menu...", event_ID);
        AttendeeMenu(email);
        break;
      } else {
        System.out.printf("[%s] Signed Up Failed!"
            + "\nRedirecting to main menu...", event_ID);
        AttendeeMenu(email);
        break;
      }
    }
  }

  /**
   * This is the second large sections under attendee menu.
   *
   * @param email - email is inputted in login menu.
   */
  private void ViewAllSignedUpEventsMenu(String email) {
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

  /**
   * This is the second large sections under attendee menu.
   *
   * @param email - email is inputted in login menu.
   */
  private void ManageSignUpEventsMenu(String email) {
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

          boolean isSuccess = suc.cancelEvent(event_id);
          if (isSuccess) {
            System.out.printf("[%d] Cancel Successful!"
                + "\nRedirecting to main menu...", event_id);
            break;
          } else {
            System.out.printf("[%d] Cancel Failed!"
                + "\nRedirecting to main menu...", event_id);
            AttendeeMenu(email);
            break;
          }
        case 2:
          String targetEmail, message;
          while (true) {
            System.out.println(mc.generateEmailList());
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
          List<Integer> eventIds = new ArrayList<>();
          boolean isSent = mc.sendMessages("Single", message, targetEmail, "",
              eventIds);
          if (isSent) {
            System.out.printf("[%s: %s] Message Sent Successful!"
                + "\nRedirecting to main menu...", targetEmail, message);
            AttendeeMenu(email);
            break;
          } else {
            System.out.printf("[%s: %s] Message Sent Failed!"
                + "\nRedirecting to main menu...", targetEmail, message);
            AttendeeMenu(email);
            break;
          }
        case 3:
          SignUpEventsMenu(email);
      }
    }
  }

  /**
   * This is the third large sections under attendee menu.
   *
   * @param email - email is inputted in login menu.
   */
  private void ViewAllMessagesMenu(String email) {
    Scanner sc = new Scanner(System.in);
    System.out.println("[All Message Menu] Please enter the corresponding number to continue..."
        + "\n1 - Show all messages"
        + "\n2 - Go back");
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
        System.out.println(mc.readAllMessages());
        System.out.println("Please enter the target email and your message to reply: ");
        String targetEmail = "", message = "";
        try {
          System.out.print("Target Email: ");
          targetEmail = sc.nextLine();
          System.out.print("Your Message: ");
          message = sc.nextLine();
          break;
        } catch (InputMismatchException e) {
          System.out.println("Invalid input, please try again.");
          System.out.print("Target Email: ");
          sc.nextLine();
          System.out.print("Your Message: ");
          sc.nextLine();
        }

        List<Integer> eventIds = new ArrayList<>();
        boolean isSuccess = mc.sendMessages("Single", message, targetEmail, "",
            eventIds);

        if (isSuccess) {
          System.out.printf("[%s : %s] Message replied successful!"
              + "\nRedirecting to main menu...", targetEmail, message);
          AttendeeMenu(email);
        } else {
          System.out.printf("[%s : %s] Message replied failed!"
              + "\nRedirecting to main menu...", targetEmail, message);
          AttendeeMenu(email);
        }
      case 2:
        ManageSignUpEventsMenu(email);
        break;
    }
  }

  /**
   * This is the third big screen section, used for attendees. Once attendee logged in successfully
   * the program will prompt more options for them to select.
   *
   * @param email - email is inputted in login menu.
   */
  private void SpeakerMenu(String email) {
    Scanner sc = new Scanner(System.in);
    System.out.println(
        "Welcome to Speaker Menu! Please enter the corresponding number to complete an action"
            + "\n"
            + "\n");
  }



  /**
   * This is the second big screen section, used for attendees. Once attendee logged in successfully
   * the program will prompt more options for them to select.
   *
   * @param email - email is inputted in login menu.
   */
  private void OrganizerMenu(String email) {
    Scanner sc = new Scanner(System.in);
    System.out.println(
        "Welcome to Organizer Menu! Please enter the corresponding number to complete an action"
            + "\n"
            + "\n");
  }




  /**
   * This is a method to tell the user its account will be signing out.
   */
  private void SignOutRedirect() {
    System.out.println("Signing Out...");
    UserMenu();
  }
}
