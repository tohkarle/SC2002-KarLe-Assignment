package cams.view.camp;

import java.time.LocalDate;

import cams.Main;
import cams.component.ConfirmOrDiscard;
import cams.component.DateInput;
import cams.component.IntInput;
import cams.component.LoadingIndicator;
import cams.component.StartEndDatesInput;
import cams.component.TwoOptionsInput;
import cams.component.YourSelectionInputWithDismiss;
import cams.util.Dismiss;
import cams.util.Page;

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
            Page.header("Select the field you want to edit.");
            this.campDetails();
            System.out.println("(6) Update changes");

            IntInput yourSelectionInputWithDismiss = new YourSelectionInputWithDismiss(1, 6);
            int option = yourSelectionInputWithDismiss.getValidInput();
            if (option == Dismiss.intOption()) { return; }

            switch (option) {
                case 1:
                    // edit name
                    System.out.println("Edit name: ");
                    this.campName = Main.scanner.nextLine();
                    if (this.campName.equals(Dismiss.stringOption())) { return; }
                    break;
                case 2:
                    // edit start date
                    DateInput startEndDatesInput = new StartEndDatesInput("Edit start date (yyyy-MM-dd): ", "Enter end date (yyyy-MM-dd): ");
                    this.startDate = startEndDatesInput.getValidStartDate();
                    if (this.startDate == null) { return; }
                    break;
                case 3:
                    // edit end date
                    this.endDate = startEndDatesInput.getValidEndDate();
                    if (this.endDate == null) { return; }
                    break;
                case 4:
                    // edit faculty
                    System.out.println("Edit faculty: ");
                    this.faculty = Main.scanner.nextLine();
                    if (this.faculty.equals(Dismiss.stringOption())) { return; }
                    break;
                case 5:
                    // get visibility
                    IntInput twoOptionsInput = new TwoOptionsInput("Edit visibility", "On", "Off");
                    option = twoOptionsInput.getValidInput();
                    this.visibility = (option == 1);
                    if (option == Dismiss.intOption()) { return; }
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
