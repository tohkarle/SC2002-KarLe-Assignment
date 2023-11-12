package cams.view.camp;

import java.time.LocalDate;

import cams.Main;
import cams.component.AllCamps;
import cams.component.CampDetails;
import cams.component.ConfirmOrDiscard;
import cams.component.DateInput;
import cams.component.IntInput;
import cams.component.LoadingIndicator;
import cams.component.StartEndDatesInput;
import cams.component.StringInput;
import cams.component.StringInputField;
import cams.component.TwoOptionsInput;
import cams.component.View;
import cams.component.YourSelectionInputWithDismiss;
import cams.util.Dismiss;

public class EditCampView implements View {

    private String campName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String faculty;
    private boolean visibility;
    private AllCamps createdCamps;

    // Input fields
    private StringInput campNameInput;
    private StringInput facultyInput;
    private DateInput startEndDatesInput;
    private IntInput visibilityInput;
    private IntInput confirmOrDiscard;

    public EditCampView(AllCamps createdCamps) {
        this.createdCamps = createdCamps;
        this.campNameInput = new StringInputField("Edit name: ");
        this.facultyInput = new StringInputField("Edit faculty: ");
        this.startEndDatesInput = new StartEndDatesInput("Edit start date (yyyy-MM-dd): ", "Edit end date (yyyy-MM-dd): ");
        this.visibilityInput = new TwoOptionsInput("Edit visibility", "On", "Off");
        this.confirmOrDiscard = new ConfirmOrDiscard("changes");
    }

    public void show() {
        while (true) {
            // Show all created camps
            createdCamps.displayCamps("Select the camp you want to edit:");

            // Let user select the camp to edit
            int campID = createdCamps.selectCamp();
            if (campID == Dismiss.intOption()) { return; }

            // Edit camp
            this.editCamp(campID);
        }
    }

    private void editCamp(int campID) {
        CampDetails campDetails = new CampDetails(campID);
        while (true) {
            // Display camp details
            campDetails.displayDetails("Select the field you want to edit.", true);

            System.out.println("(5) Update changes");

            IntInput yourSelectionInputWithDismiss = new YourSelectionInputWithDismiss(1, 6);
            int option = yourSelectionInputWithDismiss.getValidInput();
            if (option == Dismiss.intOption()) { return; }

            switch (option) {
                case 1:
                    // edit name
                    this.campName = this.campNameInput.getValidInput();
                    if (this.campName.equals(Dismiss.stringOption())) { return; }
                    campDetails.setCampName(this.campName);
                    break;
                case 2:
                    // edit start date
                    this.startDate = this.startEndDatesInput.getValidStartDate();
                    if (this.startDate == null) { return; }
                    campDetails.setStartDate(this.startDate);

                    // edit end date
                    this.endDate = this.startEndDatesInput.getValidEndDate();
                    if (this.endDate == null) { return; }
                    campDetails.setEndDate(this.endDate);
                    break;
                case 3:
                    // edit faculty
                    this.faculty = this.facultyInput.getValidInput();
                    if (this.faculty.equals(Dismiss.stringOption())) { return; }
                    campDetails.setFaculty(this.faculty);
                    break;
                case 4:
                    // get visibility
                    option = this.visibilityInput.getValidInput();
                    this.visibility = (option == 1);
                    if (option == Dismiss.intOption()) { return; }
                    campDetails.setVisibility(this.visibility);
                    break;
                case 5:
                    // Confirm or discard
                    if (this.confirmOrDiscard.getValidInput() != 1) { return; }

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
