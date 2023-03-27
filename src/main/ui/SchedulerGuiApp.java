package ui;


import ui.gui.calendar.CalendarPanel;
import ui.gui.view.MainWindow;


// A class that represents the course scheduler gui app
public class SchedulerGuiApp extends SchedulerApp {

    // EFFECTS: runs the course scheduler gui app
    @Override
    public void run() {
        MainWindow mainWindow = new MainWindow(this);
        mainWindow.display();
    }
}
