package ui.gui.view.calendarcreation;

import model.Course;
import model.Schedule;
import model.Weight;
import model.util.HelperUtil;
import model.util.Scheduler;
import ui.SchedulerApp;
import ui.gui.calendar.CalendarPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// This is the actual panel that holds all the buttons, graphics, etc. for calendar creation.
public class CalendarCreationPanel extends JPanel implements ActionListener, ChangeListener {
    private SchedulerApp app;
    private JPanel coursePane;
    private JPanel weightPane;
    private JPanel previewPane = new JPanel();
    private List<JCheckBox> cblCourse;
    private JComboBox cbStartTime = new JComboBox();
    private JComboBox cbEndTime = new JComboBox();
    private JButton btnGenerateSchedule = new JButton("Generate Schedule ->");
    private JButton btnSavePref = new JButton("Save");
    private JButton btnLoadPref = new JButton("Load");
    private JButton btnSaveSchedule = new JButton("Save Schedule");
    private JRadioButton rbt1 = new JRadioButton("Term 1");
    private JRadioButton rbt2 = new JRadioButton("Term 2");
    private JSlider balanceSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 1);
    private JSlider compactSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 1);
    private List<Schedule> topSchedules = new ArrayList<>();
    private int selectedScheduleIdx = -1;
    private JPanel selectedSchedulePane = new JPanel();
    private JButton btnLeft = new JButton(" << Previous");
    private JButton btnRight = new JButton(" Next >> ");
    private JButton btnSaveAs = new JButton("Save as");
    private JLabel lbBalance = new JLabel("");
    private JLabel lbCompact = new JLabel("");
    private CalendarCreationDialog dialogue;

    // EFFECTS: constructs a new CalendarCreationPanel
    public CalendarCreationPanel(SchedulerApp app, CalendarCreationDialog parent) {
        dialogue = parent;
        this.app = app;
        cblCourse = new ArrayList<>();
        btnGenerateSchedule.addActionListener(this);
        btnSavePref.addActionListener(this);
        btnLoadPref.addActionListener(this);
        btnSaveSchedule.addActionListener(this);
        btnLeft.addActionListener(this);
        btnRight.addActionListener(this);
        btnSaveAs.addActionListener(this);

        setupLayOut();
        setupForm();
        setupPreview();
        showSelectedCalender();
        loadWeights();
    }

    // MODIFIES: this
    // EFFECTS: sets up the schedule preview panel
    private void setupPreview() {
        previewPane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        selectedSchedulePane.setLayout(new GridBagLayout());
        previewPane.add(selectedSchedulePane, c);

        setupPreviewButtons();
    }

    // MODIFIES: this
    // EFFECT: Sets up the preview buttons
    private void setupPreviewButtons() {
        GridBagConstraints c;
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.5;
        c.anchor = GridBagConstraints.WEST;
        previewPane.add(btnLeft, c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;

        c.anchor = GridBagConstraints.CENTER;
        previewPane.add(btnSaveAs, c);

        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 1;
        c.weightx = 0.5;
        c.anchor = GridBagConstraints.EAST;
        previewPane.add(btnRight, c);
    }

    // MODIFIES: this
    // EFFECT: sets up the form on the left side of the panel that contains all the buttons and fields for course
    // selection and preferences
    private void setupForm() {
        addCoursePaneLabels();
        addWeightPaneLabels();
        addCourseCheckBoxes();
        addTermTextField();
        addBalanceSlider();
        addCompactSlider();
        addStartInputField();
        addEndInputField();
        addSaveLoadWeightButtons();
    }

    // MODIFIES: this
    // EFFECT: sets up the main background panels (one for the form and one for the preview)
    private void setupLayOut() {
        JPanel lp = setupLeftPane();
        JPanel rp = setupRightPane();

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        add(lp, c);
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        add(rp, c);

    }

    // MODIFIES: this
    // EFFECT: the part of the setupLayout that handles the left panel
    private JPanel setupLeftPane() {
        JPanel lp = new JPanel();
        addCourseSelectionPaneSection(lp);
        addWeightSettingPaneSection(lp);

        GridBagConstraints c;
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 0.5;
        c.anchor = GridBagConstraints.EAST;
        lp.add(btnGenerateSchedule, c);
        return lp;
    }

    // MODIFIES: this
    // EFFECT: Adds the course selection and set term buttons/fields to the left panel
    private void addCourseSelectionPaneSection(JPanel lp) {
        lp.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.BOTH;
        JPanel courseSettingPane = new JPanel();
        courseSettingPane.setLayout(new BoxLayout(courseSettingPane, BoxLayout.PAGE_AXIS));
        courseSettingPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        "Course Setting"),
                BorderFactory.createEmptyBorder(2, 10, 2, 10)));
        coursePane = new JPanel();
        coursePane.setLayout(new GridBagLayout());
        courseSettingPane.add(coursePane);
        lp.add(courseSettingPane, c);
    }

    // MODIFIES: this
    // EFFECT: adds the weight preferences to the left panel
    private void addWeightSettingPaneSection(JPanel lp) {
        GridBagConstraints c;

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.BOTH;
        JPanel weightSettingPane = new JPanel();
        weightSettingPane.setLayout(new BoxLayout(weightSettingPane, BoxLayout.PAGE_AXIS));
        weightSettingPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        "Weight Settings"),
                BorderFactory.createEmptyBorder(2, 10, 2, 10)));
        weightPane = new JPanel();
        weightPane.setLayout(new GridBagLayout());
        weightSettingPane.add(weightPane);
        lp.add(weightSettingPane, c);
    }

    // MODIFIES: this
    // EFFECT: is part of the setupLayout that handles the right panel
    private JPanel setupRightPane() {
        JPanel rp = new JPanel();
        rp.setLayout(new BoxLayout(rp, BoxLayout.PAGE_AXIS));
        rp.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        "Preview"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        rp.add(previewPane);
        return rp;
    }

    // MODIFIES: this
    // EFFECT: adds course panel labels
    private void addCoursePaneLabels() {
        List<String> labels = Arrays.asList(
                "Select courses:",
                "Term:");

        for (int i = 0; i < labels.size(); i++) {
            JLabel label = new JLabel(labels.get(i));
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = i * 2;
            c.anchor = GridBagConstraints.WEST;
            coursePane.add(label, c);
        }
    }

    // MODIFIES: this
    // EFFECT: adds weight panel labels
    private void addWeightPaneLabels() {
        List<String> labels = getWeightLabels();

        for (int i = 0; i < labels.size(); i++) {

            JLabel label = new JLabel(labels.get(i));
            GridBagConstraints c = new GridBagConstraints();
            Component cp = label;
            if (i == 0) {
                Box b = Box.createHorizontalBox();
                b.add(label);
                b.add(lbBalance);
                cp = b;
            }
            if (i == 1) {
                Box b = Box.createHorizontalBox();
                b.add(label);
                b.add(lbCompact);
                cp = b;
            }

            c.gridx = 0;
            c.gridy = i * 2;
            c.gridwidth = 2;
            c.anchor = GridBagConstraints.WEST;
            weightPane.add(cp, c);
        }

    }

    // EFFECT: returns weight labels as a list of strings
    private List<String> getWeightLabels() {
        List<String> labels = Arrays.asList(
                "Set balance weight:",
                "Set compact weight:",
                "Set start time:",
                "Set end time:");
        return labels;
    }

    // MODIFIES: this
    // EFFECT: adds the course checkboxes to the course panel
    private void addCourseCheckBoxes() {
        List<Course> courses = app.getData().getAllCourse();
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        for (int i = 0; i < courses.size(); i++) {
            JCheckBox box = new JCheckBox(courses.get(i).getCourseID());
            cblCourse.add(box);
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = i % 2;
            c.gridy = i / 2;
            panel.add(box, c);
        }
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0, 20, 0, 0);
        c.anchor = GridBagConstraints.WEST;
        coursePane.add(panel, c);
    }

    // MODIFIES: this
    // EFFECT: adds the term text field to the course panel
    public void addTermTextField() {
        rbt1.setSelected(true);
        ButtonGroup group = new ButtonGroup();
        group.add(rbt1);
        group.add(rbt2);

        Box box = Box.createHorizontalBox();
        box.add(rbt1);
        box.add(rbt2);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(0, 20, 0, 0);
        c.anchor = GridBagConstraints.WEST;
        coursePane.add(box, c);

    }

    // MODIFIES: this
    // EFFECT: adds the balance weight slider to the weight panel
    private void addBalanceSlider() {
        balanceSlider.setMajorTickSpacing(100);
        balanceSlider.setMinorTickSpacing(10);
        balanceSlider.setPaintLabels(true);
        balanceSlider.setPaintTicks(true);
        balanceSlider.setPaintTrack(true);
        balanceSlider.addChangeListener(this);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.insets = new Insets(0, 20, 0, 0);
        c.anchor = GridBagConstraints.WEST;
        weightPane.add(balanceSlider, c);
    }

    // MODIFIES: this
    // EFFECT: adds the compact weight slider to the weight panel
    private void addCompactSlider() {
        compactSlider.setMajorTickSpacing(100);
        compactSlider.setMinorTickSpacing(10);
        compactSlider.setPaintLabels(true);
        compactSlider.setPaintTicks(true);
        compactSlider.setPaintTrack(true);
        compactSlider.addChangeListener(this);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.insets = new Insets(0, 20, 0, 0);
        c.anchor = GridBagConstraints.WEST;
        weightPane.add(compactSlider, c);
    }

    // MODIFIES: this
    // EFFECT: adds the preferred start time input field to the weight panel
    private void addStartInputField() {
        for (int i = 0; i < 22; i++) {
            cbStartTime.addItem(HelperUtil.minutesToTime(480 + 30 * i));
        }
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;

        c.insets = new Insets(0, 20, 0, 0);
        c.anchor = GridBagConstraints.WEST;
        weightPane.add(cbStartTime, c);
    }

    // MODIFIES: this
    // EFFECT: adds the preferred end time input field to the weight panel
    private void addEndInputField() {
        for (int i = 0; i < 22; i++) {
            cbEndTime.addItem(HelperUtil.minutesToTime(480 + 30 * i));
        }
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 2;

        c.insets = new Insets(0, 20, 0, 0);
        c.anchor = GridBagConstraints.WEST;
        weightPane.add(cbEndTime, c);
    }

    // MODIFIES: this
    // EFFECT: adds the save preference and load preference buttons to the weight panel
    private void addSaveLoadWeightButtons() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 14;
        c.anchor = GridBagConstraints.EAST;
        weightPane.add(btnSavePref, c);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 14;
        c.anchor = GridBagConstraints.WEST;
        weightPane.add(btnLoadPref, c);

    }

    // MODIFIES: this
    // EFFECT: method that controls what to do when the save, load, left, right, save schedule buttons are pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnSavePref)) {
            saveWeights();
        } else if (e.getSource().equals(btnLoadPref)) {
            app.loadSavedWeight();
            loadWeights();
        } else if (e.getSource().equals(btnGenerateSchedule)) {
            generateSchedules();
        } else if (e.getSource().equals(btnLeft)) {
            selectedScheduleIdx--;
            showSelectedCalender();
        } else if (e.getSource().equals(btnRight)) {
            selectedScheduleIdx++;
            showSelectedCalender();
        } else if (e.getSource().equals(btnSaveAs)) {
            saveSchedule();
        }
    }

    // MODIFIES: this
    // EFFECT: prompts user to name the schedule and then saves it
    private void saveSchedule() {
        String name = getScheduleNameFromDialog();
        if (name == null) {
            return;
        }
        if (name.equals("")) {
            JOptionPane.showMessageDialog(this,
                    "Schedule Name cannot be empty, save failed!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (app.getSavedScheduleByName(name) != null) {
            JOptionPane.showMessageDialog(this,
                    String.format("Schedule Name \"%s\" has been used, save failed!", name), "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        Schedule schedule = topSchedules.get(selectedScheduleIdx);
        schedule.setName(name);
        app.saveSchedule(schedule);

        dialogue.dispose();

    }


    // EFFECT: returns the name from the dialog
    private String getScheduleNameFromDialog() {
        String name = (String) JOptionPane.showInputDialog(
                this,
                "Please input a name:\n"
                        + "(The name need to be unique in saved schedules list)",
                "Save Schedule...",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");
        return name;
    }


    // MODIFIES: this
    // EFFECT: generates and displays the schedules
    private void generateSchedules() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Weight weight = new Weight(1, 1, 1, 1);
        weight.setBalanceWeight(balanceSlider.getValue());
        weight.setCompactWeight(compactSlider.getValue());
        weight.setPreferredStartTime(HelperUtil.calculateMinutes(cbStartTime.getSelectedItem().toString()));
        weight.setPreferredEndTime(HelperUtil.calculateMinutes(cbEndTime.getSelectedItem().toString()));
        int term = rbt1.isSelected() ? 1 : 2;
        Schedule s = new Schedule("", term, weight, app.getData());
        for (JCheckBox box : cblCourse) {
            if (box.isSelected()) {
                s.addCoursesByID(box.getText());
            }
        }

        selectedScheduleIdx = -1;
        topSchedules = Scheduler.scheduleAndCalculateScore(s);
        showSelectedCalender();
        setCursor(null);

    }

    // EFFECT: switches to and renders selected calendar
    private void showSelectedCalender() {
        if (topSchedules.size() > 0 && selectedScheduleIdx == -1) {
            selectedScheduleIdx = 0;
        }
        selectedSchedulePane.removeAll();
        selectedSchedulePane.revalidate();

        btnLeft.setEnabled(topSchedules.size() > 0 && selectedScheduleIdx > 0);
        btnRight.setEnabled(topSchedules.size() > 0 && selectedScheduleIdx < topSchedules.size() - 1);

        renderCalendar();

    }

    // EFFECT: renders the calendar
    private void renderCalendar() {
        Schedule schedule = null;

        if (topSchedules.size() > 0) {
            btnSaveAs.setText(String.format("Save Schedule %d of %d as...", selectedScheduleIdx + 1,
                    topSchedules.size()));
            btnSaveAs.setVisible(true);
            schedule = topSchedules.get(selectedScheduleIdx);
        } else {
            btnSaveAs.setVisible(false);
        }

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        selectedSchedulePane.add(new CalendarPanel(schedule, false), c);
        selectedSchedulePane.revalidate();
    }

    // MODIFIES: this
    // EFFECT: saves the weights
    private void saveWeights() {
        Weight newWeight = app.getPreferredWeights();
        newWeight.setBalanceWeight(balanceSlider.getValue());
        newWeight.setCompactWeight(compactSlider.getValue());
        newWeight.setPreferredStartTime(HelperUtil.calculateMinutes(cbStartTime.getSelectedItem().toString()));
        newWeight.setPreferredEndTime(HelperUtil.calculateMinutes(cbEndTime.getSelectedItem().toString()));
        app.savePreference();
    }

    // MODIFIES: this
    // EFFECT: loads the weights
    private void loadWeights() {
        Weight weights = app.getPreferredWeights();
        String startTime = HelperUtil.minutesToTime(weights.getPreferredStartTime());
        String endTime = HelperUtil.minutesToTime(weights.getPreferredEndTime());
        balanceSlider.setValue(weights.getBalanceWeight());
        compactSlider.setValue(weights.getCompactWeight());
        cbStartTime.setSelectedItem(startTime);
        cbEndTime.setSelectedItem(endTime);
        lbBalance.setText(String.format("(%d)", balanceSlider.getValue()));
        lbCompact.setText(String.format("(%d)", compactSlider.getValue()));
    }

    // MODIFIES: this
    // EFFECT: controls the weight display on the two weight sliders
    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource().equals(balanceSlider)) {
            lbBalance.setText(String.format("(%d)", balanceSlider.getValue()));

        } else if (e.getSource().equals(compactSlider)) {
            lbCompact.setText(String.format("(%d)", compactSlider.getValue()));
        }
    }
}
