# CPSC 210 Personal Project

## UBC Course Scheduler

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







