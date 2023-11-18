package cams.ui.camp;

import cams.components.input.GetStringInput;
import cams.interfaces.UI;
import cams.model.Camp;
import cams.utils.Dismiss;

public class EditCampFacultyUI extends GetStringInput implements UI {
    
    private Camp camp;
    private String title;

    public EditCampFacultyUI(Camp camp, String title) {
        this.camp = camp;
        this.title = title;
    }

    @Override
    public void body() {
        String faculty = super.getValidString(title);
        if (faculty.equals(Dismiss.stringOption())) { return; }
        camp.setUserGroup(faculty);
    }
}
