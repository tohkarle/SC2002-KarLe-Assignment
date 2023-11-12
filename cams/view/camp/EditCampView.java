package cams.view.camp;

import java.time.LocalDate;

import cams.Main;
import cams.component.CampInput;
import cams.component.ConfirmOrDiscard;
import cams.component.IntInput;
import cams.component.LoadingIndicator;
import cams.component.SelectionInput;
import cams.component.YourSelectionWithBack;

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
        while (true) {
            // Show all created camps
            createdCamps.displayCamps("Select the camp you want to edit:");

            // Let user select the camp to edit
            createdCamps.selectCamp();
            int campID = createdCamps.getSelectedCampID();

            // Edit camp
            this.editCamp(campID);
        }
    }

    private void editCamp(int campID) {
        while (true) {
            SelectionInput.pageHeader("Select the field you want to edit.");
            this.campDetails();
            System.out.println("(6) Update changes");

            IntInput yourSelectionWithBack = new YourSelectionWithBack(1, 6);
            int option = yourSelectionWithBack.getValidInput();
            if (option == SelectionInput.backOptionInt()) { return; }

            switch (option) {
                case 1:
                    // edit name
                    this.campName = CampInput.stringField("Edit name: ");
                    if (this.campName.equals(SelectionInput.backOptionString())) { return; }
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
                    if (this.faculty.equals(SelectionInput.backOptionString())) { return; }
                    break;
                case 5:
                    // get visibility
                    option = CampInput.intField("Edit visibility (1) On (2) Off: ");
                    this.visibility = (option == 1);
                    if (option == SelectionInput.backOptionInt()) { return; }
                    break;
                case 6:
                    // Confirm or discard
                    IntInput confirmOrDiscard = new ConfirmOrDiscard("changes");
                    if (confirmOrDiscard.getValidInput() != 1) { return; }

                    // Update changes
                    Main.campManager.setCampName(campID, this.campName);
                    Main.campManager.setStartDate(campID, this.startDate);
                    Main.campManager.setEndDate(campID, this.endDate);
                    Main.campManager.setUserGroup(campID, this.faculty);
                    Main.campManager.setVisibility(campID, this.visibility);

                    LoadingIndicator.updateLoadingIndicator("camp");
                    return;
            }
        }
    }
}
