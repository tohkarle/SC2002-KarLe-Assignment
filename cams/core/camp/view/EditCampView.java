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
    private LocalDate startDate;
    private LocalDate endDate;
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
            this.startDate = Main.campManager.getStartDate(campID);
            this.endDate = Main.campManager.getEndDate(campID);
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

            System.out.println("(2) Start date: " + this.startDate);

            System.out.println("(3) End date: " + this.endDate);

            System.out.println("(4) Faculty: " + this.faculty);

            System.out.print("(5) Visibility: ");
            System.out.print(this.visibility ? "On\n" : "Off\n");

            System.out.println("(6) Update changes");

            int option = UserInput.selectionInputField(1, 6);
            if (option == UserInput.backOptionInt()) { return; }

            switch (option) {
                case 1:
                    // edit name
                    this.campName = CampInput.stringField("Edit name: ");
                    if (this.campName.equals(UserInput.backOptionString())) { return; }
                    break;
                case 2:
                    // edit start date
                    this.startDate = CampInput.dateField("Edit start date (yyyy-MM-dd): ");
                    if (this.startDate == null) { return; }
                    break;
                case 3:
                    // edit end date
                    this.endDate = CampInput.endDateField(this.startDate, "Enter end date (yyyy-MM-dd): ");
                    if (this.startDate == null) { return; }
                    break;
                case 4:
                    // edit faculty
                    this.faculty = CampInput.stringField("Edit faculty: ");
                    if (this.faculty.equals(UserInput.backOptionString())) { return; }
                    break;
                case 5:
                    // get visibility
                    option = CampInput.intField("Edit visibility (1) On (2) Off: ");
                    this.visibility = (option == 1);
                    if (option == UserInput.backOptionInt()) { return; }
                    break;
                case 6:
                    // Confirm or discard
                    UserInput.confirmOrDiscard("changes");
                    if (Main.scanner.nextInt() != 1) { return; }

                    // Update changes
                    Main.campManager.setCampName(campID, this.campName);
                    Main.campManager.setStartDate(campID, this.startDate);
                    Main.campManager.setEndDate(campID, this.endDate);
                    Main.campManager.setUserGroup(campID, this.faculty);
                    Main.campManager.setVisibility(campID, this.visibility);
                    return;
            }
        }
    }
}
