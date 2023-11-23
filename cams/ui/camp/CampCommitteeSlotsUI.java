package cams.ui.camp;

import cams.interfaces.Input;
import cams.interfaces.UI;
import cams.model.Camp;
import cams.utils.Dismiss;
import cams.utils.LoadingIndicator;

public class CampCommitteeSlotsUI implements UI {

    private Input getInput;
    private Camp camp;
    private String title;

    public CampCommitteeSlotsUI(Input getInput, Camp camp, String title) {
        this.getInput = getInput;
        this.camp = camp;
        this.title = title;
    }

    @Override
    public void body() {

        int committeeSlots = getInput.getValidInt(title);
        if (committeeSlots == Dismiss.intOption()) { return; }

        // Max 10
        if (committeeSlots > 10) {
            LoadingIndicator.customWarningIndicator("Camp cannot have more than 10 committee members.");
            return;
        }

        // ensure new committee slots will allow at least 1 committee to register
        if (committeeSlots <= 0){
            LoadingIndicator.customWarningIndicator("Camp cannot have no committee member.");
            return;
        }

        // check if the camp passed in is actually for editing
        // ensure new committee slots not less than total slots, since committee is counted in total slots
        if (camp.getId() != -1 && committeeSlots > camp.getTotalSlots()) {
            LoadingIndicator.customWarningIndicator("Committee member slots cannot be more than total slots.");
            return;
        }

        // check if the camp passed in is actually for editing
        // ensure new committee slots are not less than number of currently registered committee members
        while (camp.getId() != -1 && committeeSlots < camp.getCommitteeMemberNames().size()) { 
            LoadingIndicator.customWarningIndicator("Committee member slots cannot be less than the number of committee members already registered.");
            return;
        }

        camp.setCommitteeSlots(committeeSlots);
        return;
    }
}