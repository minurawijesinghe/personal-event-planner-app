# Personal Event Planner App

A simple Android application to manage personal events. This project was developed as a university assignment to demonstrate CRUD operations, local data persistence with Room, and modern navigation using the Jetpack Navigation Component.

## Project Structure

- **MainActivity**: The single activity hosting the `NavHostFragment` and `BottomNavigationView`.
- **Fragments**:
  - `EventListFragment`: Displays a list of all events in a `RecyclerView`.
  - `AddEventFragment`: Used for both creating new events and editing/deleting existing ones.
- **Room Database**:
  - `Event`: Entity class representing an event.
  - `EventDao`: Data Access Object for CRUD operations.
  - `AppDatabase`: The main database class using the Singleton pattern.
- **Adapter**:
  - `EventAdapter`: Handles displaying event items in the `RecyclerView`.
- **Navigation**:
  - `nav_graph.xml`: Defines the navigation flow between fragments.
- **UI**:
  - XML layouts using `ConstraintLayout` and `Material Components`.
  - `BottomNavigationView` for easy switching between screens.

## Assignment Requirements Satisfaction

1.  **CREATE**: Implemented in `AddEventFragment`. Users can enter title, category, location, date, and time.
2.  **READ**: `EventListFragment` fetches all events from Room and displays them in a `RecyclerView`, sorted by date.
3.  **UPDATE**: Clicking an event in the list passes its ID to `AddEventFragment`, which loads the data for editing.
4.  **DELETE**: An optional "Delete Event" button appears in `AddEventFragment` when editing an existing event.
5.  **Room Database**: Implemented using `Event`, `EventDao`, and `AppDatabase` classes in Java.
6.  **Navigation Component**: Used `NavHostFragment`, `nav_graph.xml`, and `BottomNavigationView` for fragment-based navigation.
7.  **Validation**:
    - Checks for empty title.
    - `DatePickerDialog` prevents selecting past dates (via `setMinDate`).
    - Toasts provide feedback for validation errors and successful deletions.

## Verification Checklist

- [x] Java only (No Kotlin)
- [x] XML layouts only (No Compose)
- [x] Room Database implementation
- [x] CRUD operations (Create, Read, Update, Delete)
- [x] Navigation Component with Bottom Bar
- [x] Fragments used (Single Activity)
- [x] Sorted by date (Ascending)
- [x] Validation (Empty title, past dates)
- [x] Toasts/Snackbars for feedback
- [x] Simple student-like structure and comments

## Demo Script (approx. 4-5 minutes)

1.  **Introduction (30s)**: Introduce the app as a "Personal Event Planner" designed for easy event management. Mention the tech stack: Java, Room, and Navigation Component.
2.  **Navigation (30s)**: Demonstrate the `BottomNavigationView`. Click between "Events" and "Add Event" to show fragment switching.
3.  **Create Event (1m)**: 
    - Go to "Add Event".
    - Try to save with an empty title to show validation.
    - Use the Date Picker to show that past dates are disabled or show an error.
    - Create a "Work" event for tomorrow.
    - Create a "Social" event for next week.
4.  **Read & Sort (1m)**: 
    - Switch to the "Events" tab.
    - Show the list of events.
    - Point out that the event for tomorrow appears before the event for next week (ascending date sort).
5.  **Update & Delete (1m)**:
    - Click on one of the created events.
    - Change its title or location and save. Show the update in the list.
    - Click it again and use the "Delete Event" button. Show the Toast confirmation and the updated empty/shorter list.
6.  **Conclusion (30s)**: Briefly mention how Room ensures data persists even if the app is closed and reopened.

## LLM Declaration Statement
"I used an LLM (Google Gemini) to assist in generating the boilerplate code, XML layout structures, and project organization for this assignment. All logic, validation rules, and integration of the Room database and Navigation component were reviewed and refined to meet the specific requirements of the project."
