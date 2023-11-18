package cams.ui.camp;

import cams.components.input.GetIntInput;
import cams.interfaces.UI;
import cams.manager.CampManager;
import cams.model.Camp;

public class EditCampTotalSlotsUI extends GetIntInput implements UI {

    private Camp camp;
    private CampManager campManager;
    private String title;

    public EditCampTotalSlotsUI(Camp camp, CampManager campManager, String title) {
        this.camp = camp;
        this.campManager = campManager;
        this.title = title;
    }

    @Override
    public void body() {
        int totalSlots = super.getValidInt(title);

        // ensure new total slots will allow at least 1 student to register
        if (totalSlots <= 0){
            System.out.println("\nCamp cannot have no students!");
            return;
        }

        // slots now less than number of registered
        // kick most recently joined Student till meet total slots
        int numberOfRemoved = 0;
        while (totalSlots < campManager.getRegCount(camp.getId())) { 
            campManager.kickLastJoin(camp.getId());
            numberOfRemoved++;
        }
        if (numberOfRemoved > 0){
            System.out.printf("\nUnregistered %d Students from camp!\n", numberOfRemoved);
        }
        
        camp.setTotalSlots(totalSlots);
        return;
    }
}
