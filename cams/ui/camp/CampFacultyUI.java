package cams.ui.camp;

import cams.interfaces.Input;
import cams.interfaces.UI;
import cams.model.Camp;
import cams.utils.Dismiss;

/**
 * UI object for editing the faculty of a camp
 */
public class CampFacultyUI implements UI {
    
    private Input getInput;
    private Camp camp;
    private String title;

    /**
     * Initialize the CampFacultyUI
     * @param getInput Input object used to get user input
     * @param camp Camp object to be edited
     * @param title Title of the UI
     */
    public CampFacultyUI(Input getInput, Camp camp, String title) {
        this.getInput = getInput;
        this.camp = camp;
        this.title = title;
    }

    /**
     * Executes user interaction logic for editing the faculty of a camp
     */
    @Override
    public void body() {
        String faculty = getInput.getValidString(title);
        if (faculty.equals(Dismiss.stringOption())) { return; }
        camp.setUserGroup(faculty);
    }
}
