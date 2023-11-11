package cams.core.camp.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import cams.Main;
import cams.component.CampInput;
import cams.component.LoadingIndicator;
import cams.component.UserInput;

public class EditCampView {
    private String campName;
    private ArrayList<LocalDate> dates;
    private String faculty;
    private boolean visibility;
    private AllCampsView createdCamps;

    public EditCampView(AllCampsView createdCamps) {
        this.createdCamps = createdCamps;
    }

    public void show() {

        // Show all created camps
        createdCamps.displayCamps("Select the camp you want to edit:");

        // Let user select the camp to edit
        if (createdCamps.getIds().size() == 0) {
            if (UserInput.selectionInputField(-1, -1) == UserInput.backOptionInt()) { return; };
        } else {
            int option = UserInput.selectionInputField(1, createdCamps.getIds().size());

            // Go back if user selects back
            if (option == UserInput.backOptionInt()) { return; }

            int campID = createdCamps.getIds().get(option - 1);
            this.campName = Main.campManager.getCampName(campID);
            this.dates = Main.campManager.getCampDates(campID);
            this.faculty = Main.campManager.getUserGroup(campID);
            this.visibility = Main.campManager.getVisibility(campID);
            this.editCamp(campID);

            LoadingIndicator.updateLoadingIndicator("camp");
        }
    }

    private void editCamp(int campID) {
        while (true) {

            UserInput.pageHeader("Select the field you want to edit.");

            System.out.println("(1) Name: " + this.campName);

            System.out.print("(2) Dates: ");
            for (int i = 0; i < dates.size(); i++) {
                System.out.print(dates.get(i) + ((i == dates.size() - 1) ? "\n" : ", "));
            }

            System.out.println("(3) Faculty: " + this.faculty);

            System.out.print("(4) Visibility: ");
            System.out.print(this.visibility ? "On\n" : "Off\n");

            System.out.println("(5) Confirm changes");


            int option = UserInput.selectionInputField(1, 5);
            if (option == UserInput.backOptionInt()) { return; }

            switch (option) {
                case 1:
                    // edit name
                    this.campName = CampInput.stringField("Edit name: ");
                    if (this.campName.equals(UserInput.backOptionString())) { return; }
                    break;
                case 2:
                    // get dates
                    this.dates.clear();
                    option = this.getDates();
                    if (option == UserInput.backOptionInt()) { return; }
                    break;
                case 3:
                    // edit faculty
                    this.faculty = CampInput.stringField("Edit faculty: ");
                    if (this.faculty.equals(UserInput.backOptionString())) { return; }
                    break;
                case 4:
                    // get visibility
                    option = CampInput.intField("Edit visibility (1) On (2) Off: ");
                    this.visibility = (option == 1);
                    if (option == UserInput.backOptionInt()) { return; }
                    break;
                case 5:
                    // Update changes
                    Main.campManager.setCampName(campID, this.campName);
                    Main.campManager.setCampDates(campID, this.dates);
                    Main.campManager.setUserGroup(campID, this.faculty);
                    Main.campManager.setVisibility(campID, this.visibility);
                    return;
            }
        }
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
            System.out.print("Edit dates (yyyy-MM-dd): ");
        } else {
            System.out.print("Dates: ");
            for (LocalDate date : dates) {
                System.out.print(date + ", ");
            }
            System.out.print("add more or enter 'done' when finished: "); 
        }
    }
}
