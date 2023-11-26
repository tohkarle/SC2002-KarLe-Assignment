package cams.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import cams.model.Camp;

/**
 * Utility class for filtering Camp objects
 * It stores the filters applied by the current user
 */
public class FilterCamps {

    private String selectedFilterBy;
    private Camp camp;

    /**
     * Initialize FilterCamps object with default values
     * selectedFilterBy is the type of filter selected, e.g. campName, userGroup, location, etc.
     * camp is the Camp object that contains the filter values, used for filtering
     */
    public FilterCamps() {
        this.selectedFilterBy = "";
        this.camp = new Camp(-1, "Default", new ArrayList<>(), "NTU", false, "Default staff", LocalDate.now());
    }

    /**
     * Get list of filtered camps based on the type of filter selected in selectedFilterBy
     * @param camps The list of camp objects to be filtered from
     * @return ArrayList<Camp> filtered camps based on the type of filter selected in selectedFilterBy
     */
    public ArrayList<Camp> filteredCamps(ArrayList<Camp> camps) {
        switch (selectedFilterBy) {
            case "campName":
                return filteredByName(camps);
            case "userGroup":
                return filteredByFaculty(camps);
            case "location":
                return filteredByLocation(camps);
            case "description":
                return filteredByDescription(camps);
            case "dates":
                return filteredByDates(camps);
            case "registrationClosingDate":
                return filteredByRegistrationClosingDate(camps);
            case "totalslots":
                return filteredByTotalSlots(camps);
            case "committeeSlots":
                return filteredByCommitteeSlots(camps);
            case "staffInCharge":
                return filteredByStaffInCharge(camps);
            default:
                return camps;
        }
    }

    /**
     * To filter the camps by name
     * @param camps The list of camp objects to be filtered from
     * @return ArrayList<Camp> filtered camps based on camp name
     */
    private ArrayList<Camp> filteredByName(ArrayList<Camp> camps) {
        return camps.stream()
                .filter(camp -> camp.getCampName().contains(this.camp.getCampName()))
                .collect(Collectors.toCollection(ArrayList::new));
    }


    /**
     * Filters the camp list by faculty
     * @param camps The list of camp objects to be filtered from
     * @return ArrayList<Camp> filtered camps based on camp faculty
     */
    private ArrayList<Camp> filteredByFaculty(ArrayList<Camp> camps) {
        return camps.stream()
                .filter(camp -> camp.getUserGroup().equals(this.camp.getUserGroup()))
                .collect(Collectors.toCollection(ArrayList::new));
    }


    /**
     * Filters the camp list by location
     * @param camps The list of camp objects to be filtered from
     * @return ArrayList<Camp> filtered camps based on camp location
     */
    private ArrayList<Camp> filteredByLocation(ArrayList<Camp> camps) {
        return camps.stream()
                .filter(camp -> camp.getLocation().contains(this.camp.getLocation()))
                .collect(Collectors.toCollection(ArrayList::new));
    }


    /**
     * Filters the camp list by description
     * @param camps The list of camp objects to be filtered from
     * @return ArrayList<Camp> filtered camps based on camp description
     */
    private ArrayList<Camp> filteredByDescription(ArrayList<Camp> camps) {
        return camps.stream()
                .filter(camp -> camp.getDescription().contains(this.camp.getDescription()))
                .collect(Collectors.toCollection(ArrayList::new));
    }
    

    /**
     * Filters the camp list by date
     * @param camps The list of camp objects to be filtered from
     * @return ArrayList<Camp> filtered camps based on camp date
     */
    private ArrayList<Camp> filteredByDates(ArrayList<Camp> camps) {
        
        if (this.camp.getDates().isEmpty()) { return new ArrayList<Camp>(); }

        LocalDate filterStartDate = this.camp.getDates().get(0);
        LocalDate filterEndDate = this.camp.getDates().get(1);
    
        return camps.stream()
                .filter(camp -> {
                    LocalDate campStartDate = camp.getDates().get(0);
                    LocalDate campEndDate = camp.getDates().get(1);
                    return (campStartDate.isAfter(filterStartDate) || campStartDate.isEqual(filterStartDate)) &&
                           (campEndDate.isBefore(filterEndDate) || campEndDate.isEqual(filterEndDate));
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }


    /**
     * Filters the camp list by registration close date
     * @param camps The list of camp objects to be filtered from
     * @return ArrayList<Camp> filtered camps based on camp registration close date
     */
    private ArrayList<Camp> filteredByRegistrationClosingDate(ArrayList<Camp> camps) {
        return camps.stream()
                .filter(camp -> camp.getRegistrationClosingDate().equals(this.camp.getRegistrationClosingDate()))
                .collect(Collectors.toCollection(ArrayList::new));
    }


    /**
     * Filters the camp list by max registerable slots
     * @param camps The list of camp objects to be filtered from
     * @return ArrayList<Camp> filtered camps based on camp max registerable slots
     */
    private ArrayList<Camp> filteredByTotalSlots(ArrayList<Camp> camps) {
        return camps.stream()
                .filter(camp -> camp.getTotalSlots() == this.camp.getTotalSlots())
                .collect(Collectors.toCollection(ArrayList::new));
    }


    /**
     * Filters the camp list by max registerable committee slots
     * @param camps The list of camp objects to be filtered from
     * @return ArrayList<Camp> filtered camps based on camp max registerable committee slots
     */
    private ArrayList<Camp> filteredByCommitteeSlots(ArrayList<Camp> camps) {
        return camps.stream()
                .filter(camp -> camp.getCommitteeSlots() == this.camp.getCommitteeSlots())
                .collect(Collectors.toCollection(ArrayList::new));
    }


    /**
     * Filters the camp list by name of staff in charge
     * @param camps The list of camp objects to be filtered from
     * @return ArrayList<Camp> filtered camps based on name of staff in charge
     */
    private ArrayList<Camp> filteredByStaffInCharge(ArrayList<Camp> camps) {
        return camps.stream()
                .filter(camp -> camp.getStaffInCharge().equals(this.camp.getStaffInCharge()))
                .collect(Collectors.toCollection(ArrayList::new));
    }


    /**
     * Gets the choosen filter
     * @return
     */
    public String getSelectedFilterBy() {
        return this.selectedFilterBy;
    }


    /**
     * Sets the filter
     * @param selectedFilterBy
     */
    public void setSelectedFilterBy(String selectedFilterBy) {
        this.selectedFilterBy = selectedFilterBy;
    }


    /**
     * Retrieve the camp object
     * @return
     */
    public Camp getCamp() {
        return this.camp;
    }


    /**
     * Set the camp
     * @param camp
     */
    public void setCamp(Camp camp) {
        this.camp = camp;
    }
}
