package ui.gui.view;

import model.Schedule;
import ui.SchedulerApp;
import ui.gui.view.calendarcreation.CalendarCreationDialog;
import model.log.Event;
import model.log.EventLog;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// The main window of the gui application (so the window that is first displayed)
public class MainWindow extends JFrame implements ListSelectionListener, ActionListener {
    private SchedulerApp app;
    private SavedScheduleListPanel savedScheduleListPanel;
    private ViewCalendarHolderPanel viewCalendarHolderPanel;
    private CalendarCreationDialog calendarCreationDialog;
    private EventLog eventLog;

    // EFFECTS: constructs a new main window
    public MainWindow(SchedulerApp app) {
        this.app = app;
        this.eventLog = EventLog.getInstance();
        this.savedScheduleListPanel = new SavedScheduleListPanel(app);
        viewCalendarHolderPanel = new ViewCalendarHolderPanel(null, app);
        if (app.getSavedSchedules().size() > 0) {
            displaySelectedCalendar(app.getSavedSchedules().get(0).getName());
        }
        this.setTitle("UBC Course Scheduler");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(dim);
        this.setLocationRelativeTo(null);
        constructLayout();
        printLog();
    }

    // EFFECTS: displays a new main window
    public void display() {
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Constructs the layout of the main window, sets itself as a listener for buttons
    private void constructLayout() {
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.1;
        c.weighty = 1;
        this.add(savedScheduleListPanel, c);
        savedScheduleListPanel.getScheduleJList().addListSelectionListener(this);
        savedScheduleListPanel.getDeleteBtn().addActionListener(this);
        savedScheduleListPanel.getCreateBtn().addActionListener(this);

        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.9;
        c.weighty = 1;
        this.add(viewCalendarHolderPanel, c);
    }

    // MODIFIES: this
    // EFFECTS: displays the selected saved calendar given its name
    public void displaySelectedCalendar(String name) {
        Schedule s = this.app.getSavedScheduleByName(name);
        viewCalendarHolderPanel.removeViewCalendarPanel();
        viewCalendarHolderPanel.addViewCalendarPanel(s);
    }

    // MODIFIES: this
    // EFFECTS: refreshes the saved calendar display every time a new calendar is selected
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            String scheduleName = ((JList) e.getSource()).getSelectedValue().toString();
            displaySelectedCalendar(scheduleName);
        }
    }

    // MODIFIES: this
    // EFFECTS: controls the delete schedule and create schedule buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(savedScheduleListPanel.getDeleteBtn())
                && savedScheduleListPanel.getScheduleJList().getSelectedValue() != null) {
            app.deleteSchedule(app.getSavedScheduleByName(
                    savedScheduleListPanel.getScheduleJList().getSelectedValue().toString()));
            if (app.getSavedSchedules().size() > 0) {
                displaySelectedCalendar(app.getSavedSchedules().get(0).getName());
            } else if (app.getSavedSchedules().size() == 0) {
                viewCalendarHolderPanel.removeViewCalendarPanel();
                viewCalendarHolderPanel.addViewCalendarPanel(null);

            }
            savedScheduleListPanel.refreshList();
        }
        if (e.getSource().equals(savedScheduleListPanel.getCreateBtn())) {
            calendarCreationDialog = new CalendarCreationDialog(this, "create a schedule", app);

            calendarCreationDialog.addWindowListener(new WindowAdapter() {
                public void windowClosed(WindowEvent e) {
                    savedScheduleListPanel.refreshList();
                    displaySelectedCalendar(app.getSavedSchedules().get(0).getName());
                }
            });
            showCalendarCreationDialog();
        }
    }

    private void showCalendarCreationDialog() {
        calendarCreationDialog.pack();
        calendarCreationDialog.setSize(1024,600);
        calendarCreationDialog.setLocationRelativeTo(this);
        calendarCreationDialog.setVisible(true);
    }

    private void printLog() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                for (Event e : eventLog) {
                    System.out.println(e.toString() + "\n\n");
                }
            }
        });
    }

}
