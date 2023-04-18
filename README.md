# UBC Course Scheduler

This course scheduler will schedule UBC courses according to user preferences, 
using a rule-based scoring system to generate optimized schedules. Users will be 
able to view and select courses, read course descriptions, and set time 
preferences. The scheduler will visualize the result in a calendar.

All UBC students who don't need STT courses can use this scheduler to make their
course registration experience slightly less stressful.

Throughout my (very short) time at UBC, the most stressful
thing I have experienced so far is not a final or group presentation, but 
course registration. Watching the last spot in a lecture get taken while
waiting for your registration time to open and then having to redo half the schedule 
is not very fun. This is why I want to create a course scheduler which will not 
only reduce the stress and time of manually scheduling courses, but also 
optimize the courses, which would be difficult to do by hand.


### User Stories:

- As a user, I want to be able to browse available courses and their descriptions
- As a user, I want to be able to add courses to my schedule
- As a user, I want to be able to set schedule preferences such as:
    - choosing the earliest start time and latest end time
    - limiting the difference of total hours of class per day between each day
    - limiting the amount of spacing between individual courses
- As a user, I want to be able to choose how many schedules are displayed
- As a user, I want to be able to view the schedule 
- As a user, I want to be able to save my schedules and preferences to file (if I so choose)
- As a user, I want to be able to load my saved schedules and preferences from file (if I so choose)
- As a user, I want to be able to delete my saved schedules from file
- As a user, I want to be able to delete courses from the calendar preview


### Instructions for Grader:

- You can generate the first required action related to adding Xs (Courses) to a Y (Schedule) by clicking on the "Make New Schedule" button,
selecting the desired courses and setting the preferences, then clicking the "Generate Schedule" button.
- You can generate the second required action related to adding Xs to a Y by first generating a schedule, and then 
you can remove courses from the preview schedule by clicking on a section block in the preview, and then clicking on
"Delete Course" popup menu. Deleting a section will delete all the sections in the schedule with the same courseId.
- You can locate my visual component by selecting a saved schedule to view the schedule, or by creating a new schedule
like described in the bullet point above, and then looking through the previews.
- You can save the state of my application by pressing the save button on the schedule creation panel to save the 
preferences, and also by clicking on the "Save Schedule <number> of <number> as..." button that will appear
under the schedule preview.
- You can reload the state of my application by clicking on the "Load" button in the schedule creation window to load
the saved preferences. The saved schedules load automatically.


### Phase 4: Task 2:
#### Actions performed: (where the "Demo Schedule" is the only saved schedule)
- open app
- generate schedule with CPSC 210, CPSC 121, MATH 101
- delete CPSC 210 from the generated schedule
- delete CPSC 121 from generated schedule
- saved the schedule
- close app

#### Log:

Sat Apr 08 15:28:12 PDT 2023
Added CPSC 110 to Schedule.


Sat Apr 08 15:28:12 PDT 2023
Added CPSC 121 to Schedule.


Sat Apr 08 15:28:12 PDT 2023
Added PHYS 117 to Schedule.


Sat Apr 08 15:28:12 PDT 2023
Added ENGL 110 to Schedule.


Sat Apr 08 15:28:12 PDT 2023
Added MATH 180 to Schedule.


Sat Apr 08 15:30:00 PDT 2023
Added CPSC 121 to Schedule.


Sat Apr 08 15:30:00 PDT 2023
Added CPSC 210 to Schedule.


Sat Apr 08 15:30:00 PDT 2023
Added MATH 101 to Schedule.


Sat Apr 08 15:30:40 PDT 2023
Removed CPSC 210 from Schedule.


Sat Apr 08 15:31:11 PDT 2023
Removed CPSC 121 from Schedule.


### Phase 4: Task 3: 
In my UML, everything that has an association arrow pointing to my abstract SchedulerApp class is related to the gui, 
meaning that all the implementation for the console app is inside either the SchedulerApp class or the 
SchedulerConsoleApp, making the methods inside very hard to find and very messy. Right now, if I want to find a method 
inside the SchedulerConsoleApp, I have to start from the displayMainMenu() method and click through the chain of 
functions to get to the one I need, which is bad. This can be improved by making it more object-oriented and making
the menus objects.

The schedule class contains a courses field that holds a list of Courses. It used to contain a list of strings that 
were course identifiers, like the sections field that contains a list of strings that are section identifiers. I was 
forced to make this change to fulfill the "add multiple Xs to Y" requirement, but I think that holding a list of
Strings that are course identifiers instead of a list of the courses themselves is better. This is because the search 
function for scheduling the courses requires me to make copies of the schedule, and I feel like its better to make and
store thousands (or more) copies of a Schedule that holds a list of Strings rather than a Schedule that holds a list of
other more complicated objects (Courses).

One last part that I feel like could be refactored is the very repetitive JsonReader classes. It would be nice to make
a more general JSON reader class that can be used for all the reader class right now, similar to the JsonWriter. 
However, I can't think of a good way to implement this at the moment.










