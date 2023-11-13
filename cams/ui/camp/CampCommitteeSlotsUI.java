package cams.ui.camp;

import cams.Main;
import cams.components.input.GetInt;
import cams.interfaces.Input;
import cams.utils.CampUtil;

public class CampCommitteeSlotsUI extends GetInt implements Input {

    private CampUtil campUtil;
    private int committeeSlots;

    public CampCommitteeSlotsUI(CampUtil campUtil, String title) {
        super(title);
        this.campUtil = campUtil;
        this.committeeSlots = 0;
    }

    public boolean getInput() {

        committeeSlots = super.getValidInt();

        // Max 10
        if (committeeSlots > 10) {
            System.out.println("\nCamp cannot have more than 10 committee members.");
            return false;
        }

        // ensure new total slots will allow at least 1 committee to register
        if (committeeSlots <= 0){
            System.out.println("\nCamp cannot have no committee member.");
            return false;
        }

        // slots now less than number of registered
        // demote most recently joined committee till meet total slots
        int numberOfRemoved = 0;
        while (committeeSlots < Main.campManager.getRegCount(campUtil.getId())) { 
            Main.campManager.kickLastJoin(campUtil.getId());
            numberOfRemoved++;
        }
        if (numberOfRemoved > 0) {
            System.out.printf("\nUnregistered %d Students from camp!\n", numberOfRemoved);
        }

        campUtil.setTotalCommitteeSlots(committeeSlots);
        return true;
    }
}