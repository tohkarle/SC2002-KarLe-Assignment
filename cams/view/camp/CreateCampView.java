package cams.view.camp;

import java.time.LocalDate;
import java.util.ArrayList;

import cams.Main;
import cams.component.CampInput;
import cams.component.ConfirmOrDiscard;
import cams.component.IntInput;
import cams.component.LoadingIndicator;
import cams.component.SelectionInput;
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
        SelectionInput.pageHeader("Please enter the name, faculty, visibility and dates of the camp.");

        // get name
        this.campName = CampInput.stringField("Enter name: ");
        if (this.campName.equals(SelectionInput.backOptionString())) { return; }

        // get faculty
        this.faculty = CampInput.stringField("Enter faculty: ");
        if (this.faculty.equals(SelectionInput.backOptionString())) { return; }

        // get visibility
        int option = CampInput.intField("Edit visibility (1) On (2) Off: ");
        this.visibility = (option == 1);
        if (option == SelectionInput.backOptionInt()) { return; }

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
        IntInput confirmOrDiscard = new ConfirmOrDiscard("changes");
        if (confirmOrDiscard.getValidInput() != 1) { return; }

        // Create camp and add to campMap
        Main.campManager.createCamp(this.staffID, this.campName, this.dates, this.faculty, this.visibility);

        LoadingIndicator.createLoadingIndicator("camp");
    }
}
