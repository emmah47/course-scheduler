package ui.gui.view.calendarcreation;

import model.Course;
import model.Schedule;
import model.Weight;
import model.util.HelperUtil;
import model.util.Scheduler;
import ui.SchedulerApp;
import ui.gui.calendar.CalendarPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalendarCreationPanel extends JPanel implements ActionListener {
    private SchedulerApp app;
    private JPanel rootPane;
    private JTextField txtName;
    private List<JCheckBox> cblCourse;
    private JTextField txtTerm;
    private JTextField txtWeightBalance;
    private JTextField txtWeightCompact;
    private JTextField txtStartTime;
    private JTextField txtEndTime;
    private JButton buttonCreate = new JButton("Create");
    private JButton buttonLoad = new JButton("Load weights");
    private JButton buttonSave = new JButton("Save weights as default");
    private JButton btnSaveSchedule = new JButton("Save Schedule");
    private Schedule currentSchedule;


    public CalendarCreationPanel(SchedulerApp app) {
        this.app = app;
        cblCourse = new ArrayList<>();
        buttonCreate.addActionListener(this);
        buttonLoad.addActionListener(this);
        buttonSave.addActionListener(this);
        btnSaveSchedule.addActionListener(this);

        setup();

        addLabels();
        addNameTextField();
        addCourseCheckBoxes();
        addTermTextField();
        addBalanceTextField();
        addCompactTextField();
        addStartTextField();
        addEndTextField();
        addButtons();
    }

    private void setup() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        "Create New Schedule"),
                BorderFactory.createEmptyBorder(10,10,10,10)));
        rootPane = new JPanel();
        add(rootPane);
        rootPane.setLayout(new GridBagLayout());
    }

    private void addNameTextField() {
        txtName = new JTextField(16);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        rootPane.add(txtName, c);
    }

    private void addLabels() {
        List<String> labels = Arrays.asList("Schedule name:",
                "Select courses:",
                "Term",
                "Set balance weight:",
                "Set compact weight:",
                "Set start time",
                "Set end time");

        for (int i = 0; i < labels.size(); i++) {
            JLabel label = new JLabel(labels.get(i));
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = i;
            c.anchor = GridBagConstraints.EAST;
            rootPane.add(label, c);
        }
    }

    public void addTermTextField() {
        txtTerm = new JTextField(4);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.WEST;
        rootPane.add(txtTerm, c);
    }

    private void addCourseCheckBoxes() {
        List<Course> courses = app.getData().getAllCourse();
        JPanel panel = new JPanel();
        for (int i = 0; i < courses.size(); i++) {
            JCheckBox box = new JCheckBox(courses.get(i).getCourseID());
            cblCourse.add(box);
            panel.add(box);
        }
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.WEST;
        rootPane.add(panel, c);
    }

    private void addBalanceTextField() {
        txtWeightBalance = new JTextField(16);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 3;
        c.anchor = GridBagConstraints.WEST;
        rootPane.add(txtWeightBalance, c);
    }

    private void addCompactTextField() {
        txtWeightCompact = new JTextField(16);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 4;
        c.anchor = GridBagConstraints.WEST;
        rootPane.add(txtWeightCompact, c);
    }

    private void addStartTextField() {
        txtStartTime = new JTextField(5);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 5;
        c.anchor = GridBagConstraints.WEST;
        rootPane.add(txtStartTime, c);
    }

    private void addEndTextField() {
        txtEndTime = new JTextField(5);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 6;
        c.anchor = GridBagConstraints.WEST;
        rootPane.add(txtEndTime, c);
    }


    private void addButtons() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 8;
        c.anchor = GridBagConstraints.WEST;
        rootPane.add(buttonCreate, c);

        c.gridx = 1;
        c.gridy = 8;
        c.anchor = GridBagConstraints.EAST;
        rootPane.add(buttonLoad, c);

        c.gridx = 1;
        c.gridy = 9;
        c.anchor = GridBagConstraints.EAST;
        rootPane.add(buttonSave, c);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(buttonLoad)) {
            loadWeights();
        } else if (e.getSource().equals(buttonSave)) {
            saveWeights();
        } else if (e.getSource().equals(buttonCreate)) {
            Schedule s = createSchedule();
            displayFinishedSchedule(s);
        } else {
            app.saveSchedule(currentSchedule);
        }
    }

    private void displayFinishedSchedule(Schedule s) {

        CalendarPanel calendar = new CalendarPanel(s);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 0.9;
        c.fill = GridBagConstraints.BOTH;

        rootPane.removeAll();
        rootPane.revalidate();
        rootPane.add(calendar, c);

        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 0.1;
        c.fill = GridBagConstraints.HORIZONTAL;
        rootPane.add(btnSaveSchedule);
    }

    private Schedule createSchedule() {
        Weight weight = new Weight(1,1,1,1);
        weight.setBalanceWeight(Integer.parseInt(txtWeightBalance.getText()));
        weight.setCompactWeight(Integer.parseInt(txtWeightCompact.getText()));
        weight.setPreferredStartTime(HelperUtil.calculateMinutes(txtStartTime.getText()));
        weight.setPreferredEndTime(HelperUtil.calculateMinutes(txtEndTime.getText()));

        Schedule s = new Schedule(txtName.getText(), Integer.parseInt(txtTerm.getText()), weight, app.getData());
        for (JCheckBox box : cblCourse) {
            if (box.isSelected()) {
                s.addCoursesByID(box.getText());
            }
        }
        currentSchedule = Scheduler.scheduleAndCalculateScore(s).get(0);
        return currentSchedule;
    }

    private void saveWeights() {
        Weight newWeight = app.getPreferredWeights();
        newWeight.setBalanceWeight(Integer.parseInt(txtWeightBalance.getText()));
        newWeight.setCompactWeight(Integer.parseInt(txtWeightCompact.getText()));
        newWeight.setPreferredStartTime(HelperUtil.calculateMinutes(txtStartTime.getText()));
        newWeight.setPreferredEndTime(HelperUtil.calculateMinutes(txtEndTime.getText()));
        app.savePreference();
    }

    private void loadWeights() {
        Weight weights = app.getPreferredWeights();
        String startTime = HelperUtil.minutesToTime(weights.getPreferredStartTime());
        String endTime = HelperUtil.minutesToTime(weights.getPreferredEndTime());
        txtWeightBalance.setText(String.valueOf(weights.getBalanceWeight()));
        txtWeightCompact.setText(String.valueOf(weights.getCompactWeight()));
        txtStartTime.setText(startTime);
        txtEndTime.setText(endTime);
    }


}
