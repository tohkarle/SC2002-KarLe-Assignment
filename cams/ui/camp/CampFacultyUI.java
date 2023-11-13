package cams.ui.camp;

import cams.components.input.GetString;
import cams.interfaces.Input;
import cams.utils.Dismiss;
import cams.utils.CampUtil;

public class CampFacultyUI extends GetString implements Input {
    
    private CampUtil campUtil;
    private String faculty;

    public CampFacultyUI(CampUtil campUtil, String title) {
        super(title);
        this.campUtil = campUtil;
        this.faculty = "";
    }

    public boolean getInput() {
        faculty = super.getValidString();
        if (faculty.equals(Dismiss.stringOption())) { return false; }
        campUtil.setFaculty(faculty);
        return true;
    }
}
