package cams.ui.camp;

import cams.interfaces.Input;
import cams.interfaces.UI;
import cams.model.Camp;
import cams.utils.LoadingIndicator;

public class CampTotalSlotsUI implements UI {

    private Input getInput;
    private Camp camp;
    private String title;

    public CampTotalSlotsUI(Input getInput, Camp camp, String title) {
        this.getInput = getInput;
        this.camp = camp;
        this.title = title;
    }

    @Override
    public void body() {
        int totalSlots = getInput.getValidInt(title);

        // ensure new total slots will allow at least 1 student to register
        if (totalSlots <= 0){
            LoadingIndicator.customWarningIndicator("Camp cannot have no students.");
            return;
        }

        // check if the camp passed in is actually for editing
        // ensure new total slots is not less than committee slots
        if (camp.getId() != -1 && totalSlots < camp.getCommitteeSlots()) {
            LoadingIndicator.customWarningIndicator("Total slots cannot be less than committee slots.");
            return;
        }
        
        // check if the camp passed in is actually for editing
        // ensure new total slots can accommodate all currently registered students
        if (camp.getId() != -1 && totalSlots < camp.getCommitteeMemberNames().size() + camp.getParticipatingStudentNames().size()) {
            LoadingIndicator.customWarningIndicator("Total slots must not be less than the number of student already registered.");
            return;
        }
        
        camp.setTotalSlots(totalSlots);
        return;
    }
}
