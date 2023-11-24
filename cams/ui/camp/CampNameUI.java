package cams.ui.camp;

import cams.interfaces.Input;
import cams.interfaces.UI;
import cams.model.Camp;
import cams.utils.Dismiss;

/**
 * UI object for editing the name of a camp
 */
public class CampNameUI implements UI {

    private Input getInput;
    private Camp camp;
    private String title;
    
    /**
     * Initialize the CampNameUI
     * @param getInput Input object used to get user input
     * @param camp Camp object to be edited
     * @param title Title of the UI
     */
    public CampNameUI(Input getInput, Camp camp, String title) {
        this.getInput = getInput;
        this.camp = camp;
        this.title = title;
    }

    /**
     * Executes user interaction logic for editing the name of a camp
     */
    @Override
    public void body() {
        String name = getInput.getValidString(title);
        if (name.equals(Dismiss.stringOption())) { return; }
        camp.setCampName(name);
    }
}