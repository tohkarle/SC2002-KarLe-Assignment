package cams.ui.camp;

import cams.components.input.GetStringInput;
import cams.interfaces.InputField;
import cams.utils.Dismiss;
import cams.utils.CampUtil;

public class CampFacultyUI extends GetStringInput implements InputField {
    
    private CampUtil campUtil;
    private String faculty;

    public CampFacultyUI(CampUtil campUtil, String title) {
        super(title);
        this.campUtil = campUtil;
        this.faculty = "";
    }

    @Override
    public boolean focused() {
        faculty = super.getValidString();
        if (faculty.equals(Dismiss.stringOption())) { return false; }
        campUtil.setFaculty(faculty);
        return true;
    }
}
