# Liftify  Project 🏋️‍♂️

## Purpose and Motive 💪

**Liftify** is an app that tracks the progress of people who go to the gym and workout. The progress will be tracked through the inputs of personal records for different kind of lifts and display the users workout routine for each day of the week. 🗓️

https://github.com/user-attachments/assets/a992944b-0fcc-4d1b-b792-ced295d22cd4

I was motivated to create this project because this is an application I would personally use. I have gone to the gym for 2 years now, and I have a bad habit of not tracking my exact numbers for personal records so this application would allow to me focus on my goals and guide my workouts.🔩




6-daa152c00db9




**Main Uses:** 💡

- Track personal records and put them in categories of push, pull, or legs
- Create workout routines
- Show schedule of workouts

## User Stories: 📝

- As a user, I want to be able to add a personal record to my records
- As a user, I want to be able to view a list of my records
- As a user, I want to be able to put my personal records into different categories of push, pull, and leg workouts
- As a user, I want to be able to remove workouts from my records list
- As a user, I want to be able to have the option to save my all of the categories inside of my records folder
- As a user, I want to be able to have the option to load all of my records if I choose to do so

## Instructions for Grader ✅

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by going to the "Remove" tab and removing a personal record         depending on the inputted category and exercise
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by going to remove, inputting a category and pressing the "search   category for workouts" button to display a subset of workouts related to the category
- You can locate my visual component by going to "Home" tab and seeing the introduction screen
- You can save the state of my application by going to view and saving the contents of the records
- You can reload the state of my application by going to view and loading the contents of old records

## Phase 4: Task 2
An image representation of the events that may be printed out in the console when program is closed after being ran
- Create Tab -> Record -> createRecord -> EventLog
- Remove Tab -> Record -> removeRecord -> EventLog

![image](https://media.github.students.cs.ubc.ca/user/25805/files/041802c1-0493-4206-aaac-4803bc7056dc)

## Phase 4: Task 3
Reflection on the design presented in my UML class diagram.  
- If I had more time to work on the project, what refactoring may I have done to improve the design?

One of the key aspects of my project that I began to notice when creating my UML diagram is making Records an interface. If I were to refactor records into an interface, I would be able to create multiple inplementations to manipulate the way I handle records based on the application I want to create. Allowing me to have more flexibility and maintanability as the application grows. Furthermore, as the software expands, I would combine RecordPanel and UserPanel. They are extremely similiar classes that delivery and carry out the same logic. Combining these classes would reduce class coupling and increase cohesion, promoting better readability for future developers.
