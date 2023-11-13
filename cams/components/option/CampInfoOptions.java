package cams.components.option;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import cams.Main;

public class CampInfoOptions extends Options {

    protected int id;
    protected String name;
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected LocalDate registrationClosingDate;
    protected String faculty;
    protected boolean visibility;
    protected String staffInCharge;
    protected int remainingSlots;
    protected int totalSlots;
    protected int remainingCommitteeSlots;
    protected int totalCommitteeSlots;
    protected String location;
    protected String description;

    public CampInfoOptions() { }

    public CampInfoOptions(int campID) {

        // Fetch and initialize these values
        this.id = campID;
        this.name = Main.campManager.getCampName(campID);
        this.startDate = Main.campManager.getStartDate(campID);
        this.endDate = Main.campManager.getEndDate(campID);
        this.registrationClosingDate = Main.campManager.getRegistrationClosingDate(campID);
        this.faculty = Main.campManager.getUserGroup(campID);
        this.visibility = Main.campManager.getVisibility(campID);
        this.staffInCharge = Main.authManager.getName(Main.campManager.getCampStaffInCharge(campID));
        this.remainingSlots = Main.campManager.getRemainingSlots(campID);
        this.totalSlots = Main.campManager.getTotalSlots(campID);
        this.remainingCommitteeSlots = Main.campManager.getRemainingCommitteeSlots(campID);
        this.totalCommitteeSlots = Main.campManager.getTotalCommitteeSlots(campID);
        this.location = Main.campManager.getCampLocation(campID);
        this.description = Main.campManager.getCampDescription(campID);
    
        this.updateOptions();
    }

    public void updateOptions() {
        super.options = new ArrayList<String>(Arrays.asList(
            String.format("Name: %s", this.name),
            String.format("Faculty: %s", this.faculty),
            String.format("Location: %s", this.location),
            String.format("Description: %s", this.description),
            String.format("Visibility: %s", this.visibility ? "On" : "Off"),
            String.format("Dates: %s to %s", this.startDate, this.endDate),
            String.format("Registration closing date: %s", this.registrationClosingDate),
            String.format("Remaining slots: %d / %d", this.remainingSlots, this.totalSlots),
            String.format("Remaining committee slots: %d / %d", this.remainingCommitteeSlots, this.totalCommitteeSlots),
            String.format("Staff-in-charge: %s", this.staffInCharge)
        ));
    }

    public void changeOptionsForEdit() {
        super.options.remove(String.format("Staff-in-charge: %s", this.staffInCharge));
        super.options.addAll(Arrays.asList(
            "Update changes",
            "Manage Enquiries",
            "Manage Suggestions",
            "Create report",
            "Delete Camp"
        ));
    }
}
