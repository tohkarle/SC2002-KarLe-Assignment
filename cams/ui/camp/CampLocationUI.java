package cams.ui.camp;

import cams.components.input.GetStringInput;
import cams.interfaces.InputField;
import cams.utils.Dismiss;
import cams.utils.CampUtil;

public class CampLocationUI extends GetStringInput implements InputField {

    private CampUtil campUtil;
    private String location;
    
    public CampLocationUI(CampUtil campUtil, String title) {
        super(title);
        this.campUtil = campUtil;
        this.location = "";
    }

    @Override
    public boolean focused() {
        location = super.getValidString();
        if (location.equals(Dismiss.stringOption())) { return false; }
        campUtil.setLocation(location);
        return true;
    }
}