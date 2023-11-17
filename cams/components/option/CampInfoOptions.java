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

    public CampInfoOptions() {
        this.id = -1;
    }

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
        this.totalSlots = Main.campManager.getTotalSlots(campID);
        this.totalCommitteeSlots = Main.campManager.getTotalCommitteeSlots(campID);
        this.location = Main.campManager.getCampLocation(campID);
        this.description = Main.campManager.getCampDescription(campID);
    
        this.updateOptions();
    }

    public void updateOptions() {

        // Update remaining slots while user is editing total slots
        // If user is not editing, it means user is creating camp, set remaining slots = total slots
        updateRemainingSlots();

        super.setOptions( new ArrayList<String>(Arrays.asList(
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
        ))
    );
        
    }

    public void changeOptionsForEdit() {
        super.options.remove(String.format("Staff-in-charge: %s", this.staffInCharge));
        super.options.addAll(Arrays.asList(
            "Update changes",
            "Manage enquiries",
            "Manage suggestions",
            "Create report",
            "Delete Camp"
        ));
    }

    public void addWithdrawOption() {
        // Student can withdraw from camp in the registered camp view
        // It will be a view-only display for the camp info so we will need to add number here
        super.options.addAll(Arrays.asList(
            String.format("(%d) Withdraw from camp", 1)
        ));
    }

    public void updateRemainingSlots() {
        if (this.id != -1) {
            this.remainingSlots =  this.totalSlots - Main.campManager.getCampParticipatingStudentIDs(this.id).size();
            this.remainingCommitteeSlots = this.totalCommitteeSlots - Main.campManager.getCampCommitteeMemberIDs(this.id).size();
        } else {
            this.remainingSlots = this.totalSlots;
            this.remainingCommitteeSlots = this.totalCommitteeSlots;
        }
    }
}
