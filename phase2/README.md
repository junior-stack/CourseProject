# CSC207 Final Project "iConference"

[![JavaScript](https://img.shields.io/badge/language-Java-ff69b4?style=plastic)]()
[![React](https://img.shields.io/badge/database-SQLite-yellow?style=plastic)]()
[![WebStorm](https://img.shields.io/badge/GUI-Swing-0078d7.svg?style=plastic)](https://www.jetbrains.com/webstorm/)
[![License](https://img.shields.io/badge/license-MIT-green)](https://opensource.org/licenses/MIT)

This project is made by group_0014 during course CSC207 2020F at the University of Toronto. This
project simulates a system such as Eventbrite that the user can do different operations based on
their types. For usage, please see below.

### Demo Video: Click [Here](https://youtu.be/qFRKwuiS5H8).

## Getting Started

By our app's assumption, you may want to import our database first in order to run the complete app.
We only allowed user register as an attendee in the very beginning. The other accounts are imported
from our database. If you have extra knowledge about SQLite, feel free to enter your user details
after creating **at least 1 user**.

### Prerequisites

To use our app, you need to have the followings installed and configured on your devices:

```
git
Java JDK 1.8.x
IntelliJ IDEA or any other Java editors
```

### Download

We assumed you have done the previous step correctly, to download our app, run the following code in
your command line or terminal.

```shell
git clone https://markus.teach.cs.toronto.edu/git/csc207-2020-09/group_0014
```

You will then see git cloned our app into a folder, right click it and
select `Open in IntelliJ IDEA`. Of course, some of you may not have the option. In this case,
open `IntelliJ IDEA` and select `Open/import from existing folder`, locate your folder and apply.
Now you have successfully opened our source code files and ready to move on.

### Installation

To use our database, click on the `File` option in the navigation bar of IntelliJ IDEA, navigation
to `Project Structure`
, open it and navigate to `Modules`. On the right side of `Modules`, click on the `Dependecies` and
select the `+` sign. Then click `Library`,`Java` and navigate to our app's folder. Double click our
app's folder and single click on the `lib` folder and apply. Now, you have finished our app's setup.

### Run

To run our app, navigate to `src\UI` and click on `AppEntry.java`. Open it, and
press `alt shift F10` together, select `AppEntry` and have fun!

## User Guide

You may have many questions to ask when you read our code, or use our app. We suggest you take a
look at this before moving on.

### Basic usage

* Login: Allow user login to our app by entering email and password.
* Register: Allow user to register a new account by entering username, password, phone number and
  email.(Notice that this register is ONLY for attendees)
* Logout: Logout the app, program shut down.

### Attendee usage

By using our app, attendee can do the following operations:

* View all currently available events.
* View all signed up events.
* View all messages received.
* Sign up events.
* Sign off events.
* Send the message to single person.

Organizers in our app can do all the attendee's operations, and:

* Add/delete/edit event
* Add/delete room
* Create the attendee/speaker/organizer account
* Send the message to more than 1 person.

Speaker in our app can:

* View all signed up events.
* Send the message to 1 or more person.

Admin in our app can:

* View app's stats
* View No. of registered speaker.
* View No. of rooms created.
* View No. of events created.

### Input Format

By entering the wrong format input, you may cause unpredictable errors/crashes. Please take a look
at the following table before moving on.

|   Inputs   |       Format      |      Type     |
|:----------:|:-----------------:|:-------------:|
|    Email   | example@gmail.com |     String    |
|    Phone   |    000-000-0000   |     String    |
|  Event ID  |       18465       |      Int      |
|   Room ID  |        4687       |      Int      |
| Start Time |      15:00:00     |     String    |
|  End Time  |      15:00:00     |     String    |
| Speaker ID |       3,4,5       | List<Integer> |
|    Topic   |   "How are you?"  |     String    |
|   Message  |   "How are you?"  |     String    |

### Database Info

|  userType | userId | username            | password  |   userPhone  |      userEmail     |
|:---------:|--------|---------------------|-----------|:------------:|:------------------:|
| Attendee  | 0      | attendee            | 123456789 | 416-289-9999 | attendee@gmail.com |
| Organizer | 1      | UniversityofToronto | UofT      | 416-978-2011 | UofT@gmail.com     |
| Speaker   | 2      | speaker             | speaker   | 111-111-1111 | speaker@gmail.com  |
| Admin     | 3      | admin               | admin     | 222-222-2222 | admin@gmail.com    |
| Attendee  | 4      | student             | student   | 333-333-3333 | student@gmail.com  |

## License

* [MIT License](https://opensource.org/licenses/MIT)
* README created by Haohua Ji.