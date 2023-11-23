package cams.ui.camp;

import cams.interfaces.Input;
import cams.interfaces.UI;
import cams.model.Camp;
import cams.utils.Dismiss;

public class CampFacultyUI implements UI {
    
    private Input getInput;
    private Camp camp;
    private String title;

    public CampFacultyUI(Input getInput, Camp camp, String title) {
        this.getInput = getInput;
        this.camp = camp;
        this.title = title;
    }

    @Override
    public void body() {
        String faculty = getInput.getValidString(title);
        if (faculty.equals(Dismiss.stringOption())) { return; }
        camp.setUserGroup(faculty);
    }
}
