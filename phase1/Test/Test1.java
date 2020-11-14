import Controller.LoginFacade;

import java.util.Scanner;

public class Test1 {

    public static void main(String[] args) {


        LoginFacade facade = new LoginFacade();

        Scanner in = new Scanner(System.in);

        while (true){
            System.out.println("===================================================================================");
            System.out.println("Welcome to XXXX conference Management System, please choose one of the following:");
            System.out.println("1：register");
            System.out.println("2：login");
            System.out.println("0：exit and save");
            System.out.println("===================================================================================");
            int option = in.nextInt();
            if (option == 1) {
                System.out.println("Enter email");
                in.nextLine();
                String username = in.nextLine();
                System.out.println("Enter PassWord");
                String password = in.nextLine();
                System.out.println("Enter Phone");
                String phone = in.nextLine();
                System.out.println("Enter Email");
                String email = in.nextLine();
                facade.register(username,password,phone,email);
            }
            else if (option==2){
                System.out.println("Enter email");
                in.nextLine();
                String email = in.nextLine();
                System.out.println("Enter Password");
                String password = in.nextLine();
                if(facade.login(email,password)){
                    System.out.println("Login Successful！");
                    System.out.println(facade.getUserInfo(email));
                    String userIdentity = facade.getUserIdentity(email);
                    System.out.println("Welcome, You are logged-in as: " + userIdentity);
                    System.out.println("这里需要进入下一层对话， 根据Identity, 给他不同的选择去做（这样就能达到不同的身份登录看到不同的菜单");
                }else {System.out.println("Wrong Username or Password!");}

            }
            else if (option==0){facade.save();break;}
            else {System.out.println("Please choose Again"+"");}

        }
    }

}
