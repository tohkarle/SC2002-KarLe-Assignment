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
     * @param camps
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

    private ArrayList<Camp> filteredByName(ArrayList<Camp> camps) {
        return camps.stream()
                .filter(camp -> camp.getCampName().contains(this.camp.getCampName()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<Camp> filteredByFaculty(ArrayList<Camp> camps) {
        return camps.stream()
                .filter(camp -> camp.getUserGroup().equals(this.camp.getUserGroup()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<Camp> filteredByLocation(ArrayList<Camp> camps) {
        return camps.stream()
                .filter(camp -> camp.getLocation().contains(this.camp.getLocation()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<Camp> filteredByDescription(ArrayList<Camp> camps) {
        return camps.stream()
                .filter(camp -> camp.getDescription().contains(this.camp.getDescription()))
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    private ArrayList<Camp> filteredByDates(ArrayList<Camp> camps) {
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

    private ArrayList<Camp> filteredByRegistrationClosingDate(ArrayList<Camp> camps) {
        return camps.stream()
                .filter(camp -> camp.getRegistrationClosingDate().equals(this.camp.getRegistrationClosingDate()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<Camp> filteredByTotalSlots(ArrayList<Camp> camps) {
        return camps.stream()
                .filter(camp -> camp.getTotalSlots() == this.camp.getTotalSlots())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<Camp> filteredByCommitteeSlots(ArrayList<Camp> camps) {
        return camps.stream()
                .filter(camp -> camp.getCommitteeSlots() == this.camp.getCommitteeSlots())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<Camp> filteredByStaffInCharge(ArrayList<Camp> camps) {
        return camps.stream()
                .filter(camp -> camp.getStaffInCharge().equals(this.camp.getStaffInCharge()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public String getSelectedFilterBy() {
        return this.selectedFilterBy;
    }

    public void setSelectedFilterBy(String selectedFilterBy) {
        this.selectedFilterBy = selectedFilterBy;
    }

    public Camp getCamp() {
        return this.camp;
    }

    public void setCamp(Camp camp) {
        this.camp = camp;
    }
}
