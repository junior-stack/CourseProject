# CSC207_group0014 Phase 1 Conference Project
This project is created by group0014 (course code: CSC207) at the University of Toronto. Copyright to Haohua Ji, Jun Xing,  Zhou Ye,  Zhongyuan Liang, Yufei Wang, Jiahao Zhang, Hanzhi Zhang and Ziwei Jia. This project is a simulation of any conference or chatting apps. There are 3 types of user, and each one have different ability to do things.

## Project Assumption
### Client: Toronto Convention Centre
* Need: Toronto Convention Centre has established relationship with all public universities in Ontario. As part of collaboration, Toronto Convention Centre is providing rooms for conference uses. And now the client is asking our team to create an app (web) to help them streamline this operation.
### System User: 
* Organizer: 22 Ontario’s public universities 
* Speaker: professors/professional individuals
* Attendee: students/public individuals 
Accounts and Login:
* All organizer Accounts Information are given to us by the client, and they will be loaded to the system when it first runs. Organizer Account can not register from User Portal, but they can login in. Assume number of Organizers stay the same in phase 1. To add new Organizer accounts, assume there will be an admin account (in phase 2) who have the authority to do so.  
* Since only organizer knows the Speaker that will be invited to the events, Speaker Accounts can only be created by an Organizer Account in our system.  As a result, Speaker Account can not register from User Portal, but they can login in once the organizer creates their account.
* All Attendee can register and login from the User Portal.
* A User account can only be created with valid(non-empty) email. And same email can only create one account. 
### Events:
* The system is currently design for one conference, which could include multiple events. Only one type of event is available in phase 1, that is OneSpeakerEvent.
* The system is currently scheduling events that on the same day. Different dates will be considered in phase 2.
* Each OneSpeakerEvent can have only 1 speaker, 1 organizer and 0 or more attendees. 
* Assume each OneSpeakerEvent happen from 9am to 5pm. (This will change in phase 2)
* Assume each room has a capacity of 2 besides the speaker. (This will change in phase 2)
There can only be one speaker in the same room for the same time slot.
Same speaker can not show up in different rooms at the same time.
* All events are for promoting education so they are free. 
* If a speaker want to be an attendee or a organizer for different event, he/she would have to switch to different user accounts and login again, and that applies to attendee and organizer. 

## Usage
To use our application, you must have `Java JDK version 1.8` and `IntelliJ IDEA` installed on your machine. To check if you have the correct version of Java installed, run the following command:
``` 
java -version
```
Once your Java version is correct, open `IntelliJ IDEA` and load all the files in through the "Open or Import" option in the welcome screen. If you did not get a welcome screen, you can navigate to **File - Open** to load our project. Once you have loaded our files, navigate to `src\UI` and you will see there's a file named `Main.java`. Open `Main.java` and press "Shift+F10" on Windows or navigate to **Run - Run 'Main'** to run our project. You will then see the following show inside **Run** bar at the bottom:
```
===================================================================
Welcome to our project! 
 Please enter the corresponding number to complete an action: 
1 - Register(Attendee ONLY)
2 - Login
===================================================================
Your choice: 
```
This means you have successfully finished all the setups and ran our project!
#### Here's a list of things you can do:
* If you are an attendee:
    * Register/ Login
    * Sign up event / Cancel event
    * Send messsage / Reply message
    * View all events avaliable to you
    * View all events you have signed up
    * View all messages sent&replied from other users
* If you are a speaker:
    * Login
    * Send message / Reply message
    * View all signed up events
    * View all messages sent&replied from other users
* If you are an organizer:
    * Login
    * Manage events/ rooms/ speakers/ messages
    * Add/ delete/ edit events
    * Add/ delete room
    * Create speaker account
    * View all events avaliable to you
    * View all signed up events
    * View all messages sent&replied from other users

## Common Q&A
* Q: How do I send message? I didn't find the option to send message under *View All Message Menu/ Manage All Message Menu*?
* A: The send message option is under *View All Signed Up Events Menu* so that you can only send message to a certain amount of people in different events.
---
* Q: How do I register to become an organizer or a speaker?
* A: By our assumption, you can't register to become an organizer or a speaker. 
