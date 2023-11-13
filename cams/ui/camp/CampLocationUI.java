package cams.ui.camp;

import cams.components.input.GetString;
import cams.interfaces.Input;
import cams.utils.Dismiss;
import cams.utils.CampUtil;

public class CampLocationUI extends GetString implements Input {

    private CampUtil campUtil;
    private String location;
    
    public CampLocationUI(CampUtil campUtil, String title) {
        super(title);
        this.campUtil = campUtil;
        this.location = "";
    }

    public boolean getInput() {
        location = super.getValidString();
        if (location.equals(Dismiss.stringOption())) { return false; }
        campUtil.setLocation(location);
        return true;
    }
}