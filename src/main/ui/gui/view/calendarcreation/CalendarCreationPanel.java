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

public class CalendarCreationPanel extends JPanel implements ActionListener, ChangeListener {
    private SchedulerApp app;
    private JPanel coursePane;
    private JPanel weightPane;
    private JPanel previewPane = new JPanel();
    private JTextField txtName;
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
    private CalendarCreationDialogue dialogue;

    public CalendarCreationPanel(SchedulerApp app, CalendarCreationDialogue parent) {
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

    private void setupForm() {
        addCoursePaneLabels();
        addWeightPaneLabels();
        addCourseCheckBoxes();
        addTermTextField();
        addBalanceSlider();
        addCompactSlider();
        addStartTextField();
        addEndTextField();
        addButtons();
    }

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

    private JPanel setupLeftPane() {
        JPanel lp = new JPanel();
        addCourseSettingPaneSection(lp);
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

    private void addCourseSettingPaneSection(JPanel lp) {
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

    private List<String> getWeightLabels() {
        List<String> labels = Arrays.asList(
                "Set balance weight:",
                "Set compact weight:",
                "Set start time:",
                "Set end time:");
        return labels;
    }


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

    private void addStartTextField() {
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

    private void addEndTextField() {
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


    private void addButtons() {
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

    private void saveSchedule() {
        String name = getScheduleNameFromDialog();

        //If a string was returned, say so.
        if (name == null || name.equals("")) {
            JOptionPane.showMessageDialog(this,
                    "Schedule Name cannot be empty, save failed!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (app.getSavedScheduleByName(name) != null) {
            JOptionPane.showMessageDialog(this,
                    String.format("Schedule Name %name has been used, save failed!", name), "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        Schedule schedule = topSchedules.get(selectedScheduleIdx);
        schedule.setName(name);
        app.saveSchedule(schedule);
        dialogue.dispose();

    }

    private String getScheduleNameFromDialog() {
        String name = (String) JOptionPane.showInputDialog(
                this,
                "Please input a name:\n"
                        + "(The name need to be unique in saved schedule list)",
                "Save Schedule...",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");
        return name;
    }


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

    private void saveWeights() {
        Weight newWeight = app.getPreferredWeights();
        newWeight.setBalanceWeight(balanceSlider.getValue());
        newWeight.setCompactWeight(compactSlider.getValue());
        newWeight.setPreferredStartTime(HelperUtil.calculateMinutes(cbStartTime.getSelectedItem().toString()));
        newWeight.setPreferredEndTime(HelperUtil.calculateMinutes(cbEndTime.getSelectedItem().toString()));
        app.savePreference();
    }

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

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource().equals(balanceSlider)) {
            lbBalance.setText(String.format("(%d)", balanceSlider.getValue()));

        } else if (e.getSource().equals(compactSlider)) {
            lbCompact.setText(String.format("(%d)", compactSlider.getValue()));
        }
    }
}
