package cams.core.camp.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import cams.Main;
import cams.component.CampInput;
import cams.component.LoadingIndicator;
import cams.component.UserInput;
import cams.core.root.view.RootView;

public class CreateCampView {
    private int staffID;
    private String campName;
    private ArrayList<LocalDate> dates;
    private String faculty;
    private boolean visibility;

    public CreateCampView(RootView rootView) {
        this.staffID = rootView.getCurrentUserID();
        this.dates = new ArrayList<LocalDate>();
    }

    public void show() {
        UserInput.pageHeader("Please enter the name, faculty, visibility and dates of the camp.");

        // get name
        this.campName = CampInput.stringField("Enter name: ");
        if (this.campName.equals(UserInput.backOptionString())) { return; }

        // get faculty
        this.faculty = CampInput.stringField("Enter faculty: ");
        if (this.faculty.equals(UserInput.backOptionString())) { return; }

        // get visibility
        int option = CampInput.intField("Edit visibility (1) On (2) Off: ");
        this.visibility = (option == 1);
        if (option == UserInput.backOptionInt()) { return; }

        // get start date
        LocalDate startDate = CampInput.dateField("Enter start date (yyyy-MM-dd): ");
        if (startDate == null) { return; }

        // get end date
        LocalDate endDate;
        endDate = CampInput.endDateField(startDate, "Enter end date (yyyy-MM-dd): ");
        if (endDate == null) { return; }

        this.dates.add(startDate);
        this.dates.add(endDate);

        // Confirm changes or discard and go back
        UserInput.confirmOrDiscard("changes");
        if (Main.scanner.nextInt() != 1) { return; }

        // Create camp and add to campMap
        Main.campManager.createCamp(this.staffID, this.campName, this.dates, this.faculty, this.visibility);

        LoadingIndicator.createLoadingIndicator("camp");
    }

    private int getDates() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            this.displayAddedDates();
            String dateString = Main.scanner.nextLine();
            if (dateString.equals(UserInput.backOptionString())) { return -1; }
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

    private void displayAddedDates() {
        if (dates.size() == 0) {
            System.out.print("Enter dates (yyyy-MM-dd): ");
        } else {
            System.out.print("Dates: ");
            for (LocalDate date : dates) {
                System.out.print(date + ", ");
            }
            System.out.print("add more or enter 'done' when finished: "); 
        }
    }
}
