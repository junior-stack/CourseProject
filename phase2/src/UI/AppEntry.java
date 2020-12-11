package UI;

import Controller.LoginFacade;
import Gateway.Bootstrap;
import javax.swing.JFrame;

/**
 * @author Haohua Ji
 **/

public class AppEntry {

  /**
   * Start our app from here.
   */
  public static void main(String[] args) throws Exception {
    Bootstrap.bootstrap();
    LoginFacade loginFacade = new LoginFacade();
    LoginMenu loginMenu = new LoginMenu(loginFacade);
    loginMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    loginMenu.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        loginFacade.exit();
      }
    });
    loginMenu.setVisible(true);
  }
}
