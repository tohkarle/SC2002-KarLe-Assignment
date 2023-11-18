package cams.ui.camp;

import cams.components.input.GetIntInput;
import cams.interfaces.UI;
import cams.manager.CampManager;
import cams.model.Camp;
import cams.utils.Dismiss;

public class EditCampCommitteeSlotsUI extends GetIntInput implements UI {

    private Camp camp;
    private CampManager campManager;
    private String title;

    public EditCampCommitteeSlotsUI(Camp camp, CampManager campManager, String title) {
        this.camp = camp;
        this.campManager = campManager;
        this.title = title;
    }

    @Override
    public void body() {

        int committeeSlots = super.getValidInt(title);
        if (committeeSlots == Dismiss.intOption()) { return; }

        // Max 10
        if (committeeSlots > 10) {
            System.out.println("\nCamp cannot have more than 10 committee members.");
            return;
        }

        // ensure new total slots will allow at least 1 committee to register
        if (committeeSlots <= 0){
            System.out.println("\nCamp cannot have no committee member.");
            return;
        }

        // slots now less than number of registered
        // demote most recently joined committee till meet total slots
        int numberOfRemoved = 0;
        while (committeeSlots < campManager.getRegCount(camp.getId())) { 
            campManager.demoteLastJoin(camp.getId());
            numberOfRemoved++;
        }
        if (numberOfRemoved > 0) {
            System.out.printf("\nUnregistered %d Students from camp!\n", numberOfRemoved);
        }

        camp.setCommitteeSlots(committeeSlots);
        return;
    }
}