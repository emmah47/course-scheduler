package ui.gui.view;

import model.Schedule;
import ui.SchedulerApp;

import javax.swing.*;
import java.awt.*;

// SOURCE: This code was based on the demo code in the oracle java documentation

public class SavedScheduleListPanel extends JPanel {
    private SchedulerApp app;
    private JList scheduleJList;
    private JButton create;
    private JButton delete;
    private DefaultListModel listModel;

    SavedScheduleListPanel(SchedulerApp app) {
        this.app = app;
        listModel = new DefaultListModel();
        scheduleJList = new JList(listModel);
        populateJList();
        scheduleJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scheduleJList.setSelectedIndex(0);
        JScrollPane scrollPane = new JScrollPane(scheduleJList);

        create = new JButton("Make New Schedule");
        delete = new JButton("Delete Schedule");

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        "Saved Schedules"),
                BorderFactory.createEmptyBorder(10,10,10,10)));
        this.add(scrollPane);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        this.add(create);
        this.add(delete);
    }

    private void populateJList() {
        for (Schedule schedule : app.getSavedSchedules()) {
            listModel.addElement(schedule.getName());
        }
    }

    public JList getScheduleJList() {
        return scheduleJList;
    }

    public JButton getDeleteBtn() {
        return delete;
    }

    public JButton getCreateBtn() {
        return create;
    }

    public void refreshList() {
        scheduleJList.removeAll();
        scheduleJList.revalidate();
        listModel.clear();
        populateJList();
        scheduleJList.setSelectedIndex(0);
    }
}
