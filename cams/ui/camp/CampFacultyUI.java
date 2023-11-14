package cams.ui.camp;

import cams.components.input.GetString;
import cams.interfaces.InputField;
import cams.interfaces.UI;
import cams.utils.Dismiss;
import cams.utils.CampUtil;

public class CampFacultyUI extends GetString implements UI, InputField {
    
    private CampUtil campUtil;
    private String faculty;

    public CampFacultyUI(CampUtil campUtil, String title) {
        super(title);
        this.campUtil = campUtil;
        this.faculty = "";
    }

    public void body() {
        if (!focused()) { return; }
    }

    public boolean focused() {
        faculty = super.getValidString();
        if (faculty.equals(Dismiss.stringOption())) { return false; }
        campUtil.setFaculty(faculty);
        return true;
    }
}
