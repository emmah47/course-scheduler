package ui.gui.view;

import model.Schedule;
import ui.SchedulerApp;
import ui.gui.view.calendarcreation.CalendarCreationDialogue;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame implements ListSelectionListener, ActionListener {
    private SchedulerApp app;
    private SavedScheduleListPanel savedScheduleListPanel;
    private ViewCalendarHolderPanel viewCalendarHolderPanel;
    CalendarCreationDialogue calendarCreationDialogue;

    // EFFECTS: constructor
    public MainWindow(SchedulerApp app) {
        this.app = app;
        this.savedScheduleListPanel = new SavedScheduleListPanel(app);
        viewCalendarHolderPanel = new ViewCalendarHolderPanel(null, app);
        displaySelectedCalendar(app.getSavedSchedules().get(0).getName());
        this.setTitle("UBC Course Scheduler");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(dim);
        this.setLocationRelativeTo(null);
        constructLayout();
    }

    // EFFECTS: displays a new main window
    public void display() {
        this.setVisible(true);
    }

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

    public void displaySelectedCalendar(String name) {
        Schedule s = this.app.getSavedScheduleByName(name);
        viewCalendarHolderPanel.removeViewCalendarPanel();
        viewCalendarHolderPanel.addViewCalendarPanel(s);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            String scheduleName = ((JList) e.getSource()).getSelectedValue().toString();
            displaySelectedCalendar(scheduleName);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(savedScheduleListPanel.getDeleteBtn())) {
            app.deleteSchedule(app.getSavedScheduleByName(
                    savedScheduleListPanel.getScheduleJList().getSelectedValue().toString()));
            displaySelectedCalendar(app.getSavedSchedules().get(0).getName());
            savedScheduleListPanel.refreshList();
        }
        if (e.getSource().equals(savedScheduleListPanel.getCreateBtn())) {
            calendarCreationDialogue = new CalendarCreationDialogue(this, "create a schedule", app);

            // add a window listener
            calendarCreationDialogue.addWindowListener(new WindowAdapter()
            {
                public void windowClosed(WindowEvent e)
                {
                    savedScheduleListPanel.refreshList();
                }

            });


            calendarCreationDialogue.pack();
            calendarCreationDialogue.setSize(1024,600);
            calendarCreationDialogue.setLocationRelativeTo(this);
            calendarCreationDialogue.setVisible(true);
        }
    }
}
