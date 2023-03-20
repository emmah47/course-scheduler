package ui;

import java.util.Scanner;

// A class representing a UI selector
public class UiSelectorMenu {
    Scanner input;

    // EFFECTS: constructs a UI selector menu
    public UiSelectorMenu() {
        input = new Scanner(System.in);
    }

    // EFFECTS: displays the ui selector menu
    public void display() {
        displayMenu();
        String selection;
        while (true) {
            selection = input.nextLine();
            try {
                if (Integer.parseInt(selection) > 2 || Integer.parseInt(selection) < 0) {
                    System.out.println("Please enter 0, 1, or 2.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number.");
            }
        }
        chooseUI(selection);
    }

    // EFFECTS: prints out the menu selections
    private void displayMenu() {
        System.out.println("Please select a UI:");
        System.out.println("0: Quit");
        System.out.println("1: Console UI");
        System.out.println("2: GUI");
    }

    // EFFECTS: prompts user to choose a UI, and then runs the chosen UI
    private void chooseUI(String selection) {
        int selectionNum = Integer.parseInt(selection);
        SchedulerApp app = null;
        switch (selectionNum) {
            case 0:
                System.exit(0);
                break;
            case 1:
                app = new SchedulerConsoleApp();
                break;
            case 2:
                app = new SchedulerGuiApp();
                break;
        }
        app.run();
    }
}
