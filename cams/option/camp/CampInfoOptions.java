package cams.option.camp;

import java.util.ArrayList;
import java.util.Arrays;

import cams.components.option.DismissableViewOnlyOptions;
import cams.model.Camp;

/**
 * Options object for camp information selection in the CampInfoView
 */
public class CampInfoOptions extends DismissableViewOnlyOptions {

    private Camp camp;

    /**
     * Initialize the camp information options
     * @param camp Camp object to display information of
     */
    public CampInfoOptions(Camp camp) {
        this.camp = camp;
        super.setOptions(
            new ArrayList<String>(Arrays.asList(
                String.format("Name: %s", camp.getCampName()),
                String.format("Faculty: %s", camp.getUserGroup()),
                String.format("Location: %s", camp.getLocation()),
                String.format("Description: %s", camp.getDescription()),
                String.format("Visibility: %s", camp.getVisibility() ? "On" : "Off"),
                String.format("Dates: %s to %s", camp.getStartDate(), camp.getEndDate()),
                String.format("Registration closing date: %s", camp.getRegistrationClosingDate()),
                String.format("Remaining slots: %d / %d", (camp.getTotalSlots() - (camp.getParticipatingStudentNames().size() + camp.getCommitteeMemberNames().size())), camp.getTotalSlots()),
                String.format("Remaining committee slots: %d / %d", (camp.getCommitteeSlots() - camp.getCommitteeMemberNames().size()), camp.getCommitteeSlots()),
                String.format("Staff-in-charge: %s", camp.getStaffInCharge())
            ))
        );
    }

    public Camp getCamp() {
        return this.camp;
    }
}
