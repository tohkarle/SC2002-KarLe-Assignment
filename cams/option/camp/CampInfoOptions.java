package cams.option.camp;

import java.util.ArrayList;
import java.util.Arrays;

import cams.components.option.DismissableViewOnlyOptions;
import cams.manager.CampManager;

public class CampInfoOptions extends DismissableViewOnlyOptions {

    private CampManager campManager;

    public CampInfoOptions(CampManager campManager) {
        this.campManager = campManager;
    }

    public void updateCampInfo() {
        super.setOptions(
            new ArrayList<String>(Arrays.asList(
                String.format("Name: %s", campManager.getTempCamp().getCampName()),
                String.format("Faculty: %s", campManager.getTempCamp().getUserGroup()),
                String.format("Location: %s", campManager.getTempCamp().getLocation()),
                String.format("Description: %s", campManager.getTempCamp().getDescription()),
                String.format("Visibility: %s", campManager.getTempCamp().getVisibility() ? "On" : "Off"),
                String.format("Dates: %s to %s", campManager.getTempCamp().getStartDate(), campManager.getTempCamp().getEndDate()),
                String.format("Registration closing date: %s", campManager.getTempCamp().getRegistrationClosingDate()),
                String.format("Remaining slots: %d / %d", (campManager.getTempCamp().getTotalSlots() - (campManager.getTempCamp().getParticipatingStudentNames().size() + campManager.getTempCamp().getCommitteeMemberNames().size())), campManager.getTempCamp().getTotalSlots()),
                String.format("Remaining committee slots: %d / %d", (campManager.getTempCamp().getCommitteeSlots() - campManager.getTempCamp().getCommitteeMemberNames().size()), campManager.getTempCamp().getCommitteeSlots()),
                String.format("Staff-in-charge: %s", campManager.getTempCamp().getStaffInCharge())
            ))
        );
    }

    public void initializeTempCamp() {
        campManager.createTempCamp();
    }

    public CampManager getCampManager() {
        return this.campManager;
    }
}
