package cams.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import cams.Main;
import cams.components.LoadingIndicator;
import cams.components.option.CampInfoOptions;

public class CampUtil extends CampInfoOptions {

    public CampUtil() {
        super();
    }

    public CampUtil(int campID) {
        super(campID);
    }

    public void createCamp(int staffID) {
        ArrayList<LocalDate> dates = new ArrayList<LocalDate>();
        dates.add(super.startDate);
        dates.add(super.endDate);

        Main.campManager.createCamp(staffID, super.name, dates, super.faculty, super.visibility);
        Main.campManager.save();
        LoadingIndicator.createLoadingIndicator("camp");
    }

    public void updateCamp() {
        Main.campManager.setCampName(super.id, super.name);
        Main.campManager.setStartDate(super.id, super.startDate);
        Main.campManager.setEndDate(super.id, super.endDate);
        Main.campManager.setUserGroup(super.id, super.faculty);
        Main.campManager.setVisibility(super.id, super.visibility);
        Main.campManager.setTotalSlots(super.id, super.totalSlots);
        Main.campManager.setMaxCommSlots(super.id, super.totalCommitteeSlots);
        Main.campManager.setCampLocation(super.id, super.location);
        Main.campManager.setCampDescription(super.id, super.description);

        Main.campManager.save();
        LoadingIndicator.updateLoadingIndicator("camp");
    }

    public void updateAndChangeOptions() {
        super.updateOptions();
        super.changeOptionsForEdit();
    }

    public int getId() {
        return super.id;
    }

    public void setName(String name) {
        super.name = name;
        this.updateAndChangeOptions();
    }
    
    public void setStartDate(LocalDate startDate) {
        super.startDate = startDate;
        this.updateAndChangeOptions();
    }
    
    public void setEndDate(LocalDate endDate) {
        super.endDate = endDate;
        this.updateAndChangeOptions();
    }
    
    public void setFaculty(String faculty) {
        super.faculty = faculty;
        this.updateAndChangeOptions();
    }
    
    public void setVisibility(boolean visibility) {
        super.visibility = visibility;
        this.updateAndChangeOptions();
    }
    
    public void setStaffInCharge(String staffInCharge) {
        super.staffInCharge = staffInCharge;
        this.updateAndChangeOptions();
    }
    
    public void setRemainingSlots(int remainingSlots) {
        super.remainingSlots = remainingSlots;
        this.updateAndChangeOptions();
    }
    
    public void setTotalSlots(int totalSlots) {
        super.totalSlots = totalSlots;
        this.updateAndChangeOptions();
    }
    
    public void setRemainingCommitteeSlots(int remainingCommitteeSlots) {
        super.remainingCommitteeSlots = remainingCommitteeSlots;
        this.updateAndChangeOptions();
    }
    
    public void setTotalCommitteeSlots(int totalCommitteeSlots) {
        super.totalCommitteeSlots = totalCommitteeSlots;
        this.updateAndChangeOptions();
    }
    
    public void setLocation(String location) {
        super.location = location;
        this.updateAndChangeOptions();
    }
    
    public void setDescription(String description) {
        super.description = description;
        this.updateAndChangeOptions();
    }

    public void setRegistrationClosingDate(LocalDate registrationClosingDate) {
        super.registrationClosingDate = registrationClosingDate;
        this.updateAndChangeOptions();
    }
    
}
