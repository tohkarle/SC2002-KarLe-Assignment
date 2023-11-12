package cams.view.camp;

import java.time.LocalDate;
import java.util.ArrayList;

import cams.Main;
import cams.component.ConfirmOrDiscard;
import cams.component.DateInput;
import cams.component.IntInput;
import cams.component.LoadingIndicator;
import cams.component.StartEndDatesInput;
import cams.component.StringInput;
import cams.component.StringInputField;
import cams.component.TwoOptionsInput;
import cams.component.View;
import cams.util.Dismiss;
import cams.util.Page;
import cams.view.root.RootView;

public class CreateCampView implements View {

    private int staffID;

    // Input fields
    private StringInput campNameInput;
    private StringInput facultyInput;
    private DateInput startEndDatesInput;
    private IntInput visibilityInput;

    public CreateCampView(RootView rootView) {
        this.staffID = rootView.getCurrentUserID();
        this.campNameInput = new StringInputField("Enter name: ");
        this.facultyInput = new StringInputField("Enter faculty: ");
        this.startEndDatesInput = new StartEndDatesInput("Enter start date (yyyy-MM-dd): ", "Enter end date (yyyy-MM-dd): ");
        this.visibilityInput = new TwoOptionsInput("Enter visibility", "On", "Off");
    }

    public void show() {
        Page.header("Please enter the name, faculty, visibility and dates of the camp.");

        // get name
        String campName = this.campNameInput.getValidInput();
        if (campName.equals(Dismiss.stringOption())) { return; }

        // get faculty
        String faculty = this.facultyInput.getValidInput();
        if (faculty.equals(Dismiss.stringOption())) { return; }

        // get visibility
        int option = visibilityInput.getValidInput();
        Boolean visibility = (option == 1);
        if (option == Dismiss.intOption()) { return; }

        // get start date
        LocalDate startDate = startEndDatesInput.getValidStartDate();
        if (startDate == null) { return; }

        // get end date
        LocalDate endDate = startEndDatesInput.getValidEndDate();
        if (endDate == null) { return; }

        ArrayList<LocalDate> dates = new ArrayList<LocalDate>();
        dates.add(startDate);
        dates.add(endDate);

        // Confirm changes or discard and go back
        IntInput confirmOrDiscard = new ConfirmOrDiscard("changes");
        if (confirmOrDiscard.getValidInput() != 1) { return; }

        // Create camp and add to campMap
        Main.campManager.createCamp(this.staffID, campName, dates, faculty, visibility);

        LoadingIndicator.createLoadingIndicator("camp");
    }
}
