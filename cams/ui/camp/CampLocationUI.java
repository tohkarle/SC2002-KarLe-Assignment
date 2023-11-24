package cams.ui.camp;

import cams.interfaces.Input;
import cams.interfaces.UI;
import cams.model.Camp;
import cams.utils.Dismiss;

/**
 * UI object for editing the location of a camp
 */
public class CampLocationUI implements UI {

    private Input getInput;
    private Camp camp;
    private String title;
    
    /**
     * Initialize the CampLocationUI
     * @param getInput Input object used to get user input
     * @param camp Camp object to be edited
     * @param title Title of the UI
     */
    public CampLocationUI(Input getInput, Camp camp, String title) {
        this.getInput = getInput;
        this.camp = camp;
        this.title = title;
    }

    /**
     * Executes user interaction logic for editing the location of a camp
     */
    @Override
    public void body() {
        String location = getInput.getValidString(title);
        if (location.equals(Dismiss.stringOption())) { return; }
        camp.setLocation(location);
    }
}