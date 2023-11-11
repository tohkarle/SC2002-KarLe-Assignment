package cams.core.camp.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import cams.Main;
import cams.core.root.view.RootView;
import cams.manager.StaffManager;
import cams.util.UIComponents;

public class CreateCampView {
    private int staffID;
    private String campName;
    private ArrayList<LocalDate> dates;
    private String faculty;
    private boolean visibility;
    private StaffManager manager;

    public CreateCampView(RootView rootView) {
        this.staffID = rootView.getCurrentUserID();
        this.dates = new ArrayList<LocalDate>();
        this.manager = (StaffManager) rootView.getManager();
    }

    public void show() {
        UIComponents.pageHeader("Please enter the name, faculty, visibility and dates of the camp.");

        // get name
        System.out.print("Enter name: ");
        this.campName = Main.scanner.nextLine();
        if (this.campName.equals(UIComponents.backOptionString())) { return; }

        // get faculty
        System.out.print("Enter faculty: ");
        this.faculty = Main.scanner.nextLine();
        if (this.faculty.equals(UIComponents.backOptionString())) { return; }

        // get visibility
        int option = UIComponents.intInputField("Enter visibility (1) Turn on (2) Turn off: ", 1, 2);
        if (option == UIComponents.backOptionInt()) { return; }
        this.visibility = (option == 1);

        // get dates
        option = this.getDates();
        if (option == UIComponents.backOptionInt()) { return; }

        // Confirm changes or discard and go back
        UIComponents.confirmOrDiscard("changes");
        if (Main.scanner.nextInt() != 1) { return; }

        // Create camp and add to campMap
        int campID = Main.campManager.createCamp(this.staffID, this.campName, this.dates, this.faculty, this.visibility);

        // Add camp to user
        manager.addToUserCampIDs(staffID, campID);

        UIComponents.createLoadingIndicator("camp");
    }

    public int getDates() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            this.displayAddedDates();
            String dateString = Main.scanner.nextLine();
            if (dateString.equals(UIComponents.backOptionString())) { return -1; }
            if (dateString.equalsIgnoreCase("done")) {
                break;
            }
            try {
                LocalDate date = LocalDate.parse(dateString, formatter);
                if (this.dates.contains(date)) {
                    System.out.println("This date has already been added. Please enter a different date.");
                } else {
                    this.dates.add(date);
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in the format 'yyyy-MM-dd'.");
            }
        }
        return 1;
    }

    public void displayAddedDates() {
        if (dates.size() == 0) {
            System.out.print("Enter dates (yyyy-MM-dd): ");
        } else {
            System.out.print("Dates: ");
            for (int i = 0; i < dates.size(); i++) {
                System.out.print(dates.get(i) + ", ");
            }
            System.out.print("add more or enter 'done' when finished: "); 
        }
    }
}
