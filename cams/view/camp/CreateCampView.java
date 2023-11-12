package cams.view.camp;

import java.time.LocalDate;
import java.util.ArrayList;

import cams.Main;
import cams.component.ConfirmOrDiscard;
import cams.component.DateInput;
import cams.component.IntInput;
import cams.component.LoadingIndicator;
import cams.component.StartEndDatesInput;
import cams.component.TwoOptionsInput;
import cams.util.Dismiss;
import cams.util.Page;
import cams.view.root.RootView;

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
        Page.header("Please enter the name, faculty, visibility and dates of the camp.");

        // get name
        System.out.println("Enter name: ");
        this.campName = Main.scanner.nextLine();
        if (this.campName.equals(Dismiss.stringOption())) { return; }

        // get faculty
        System.out.println("Enter faculty: ");
        this.faculty = Main.scanner.nextLine();
        if (this.faculty.equals(Dismiss.stringOption())) { return; }

        // get visibility
        IntInput twoOptionsInput = new TwoOptionsInput("Enter visibility", "On", "Off");
        int option = twoOptionsInput.getValidInput();
        this.visibility = (option == 1);
        if (option == Dismiss.intOption()) { return; }

        // get start date
        DateInput startEndDatesInput = new StartEndDatesInput("Edit start date (yyyy-MM-dd): ", "Enter end date (yyyy-MM-dd): ");
        LocalDate startDate = startEndDatesInput.getValidStartDate();
        if (startDate == null) { return; }

        // get end date
        LocalDate endDate = startEndDatesInput.getValidEndDate();
        if (endDate == null) { return; }

        this.dates.add(startDate);
        this.dates.add(endDate);

        // Confirm changes or discard and go back
        IntInput confirmOrDiscard = new ConfirmOrDiscard("changes");
        if (confirmOrDiscard.getValidInput() != 1) { return; }

        // Create camp and add to campMap
        Main.campManager.createCamp(this.staffID, this.campName, this.dates, this.faculty, this.visibility);

        LoadingIndicator.createLoadingIndicator("camp");
    }
}
