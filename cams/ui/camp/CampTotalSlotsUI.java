package cams.ui.camp;

import cams.Main;
import cams.components.input.GetInt;
import cams.interfaces.InputField;
import cams.interfaces.UI;
import cams.utils.CampUtil;

public class CampTotalSlotsUI extends GetInt implements UI, InputField {

    private CampUtil campUtil;
    private int totalSlots;

    public CampTotalSlotsUI(CampUtil campUtil, String title) {
        super(title);
        this.campUtil = campUtil;
        this.totalSlots = -1;
    }

    public void body() {
        if (!focused()) { return; }
    }

    public boolean focused() {
        totalSlots = super.getValidInt();

        // ensure new total slots will allow at least 1 student to register
        if (totalSlots <= 0){
            System.out.println("\nCamp cannot have no students!");
            return false;
        }

        // slots now less than number of registered
        // kick most recently joined Student till meet total slots
        int numberOfRemoved = 0;
        while (totalSlots < Main.campManager.getRegCount(campUtil.getId())) { 
            Main.campManager.kickLastJoin(campUtil.getId());
            numberOfRemoved++;
        }
        if (numberOfRemoved > 0){
            System.out.printf("\nUnregistered %d Students from camp!\n", numberOfRemoved);
        }
        
        campUtil.setTotalSlots(totalSlots);
        return true;
    }
}
