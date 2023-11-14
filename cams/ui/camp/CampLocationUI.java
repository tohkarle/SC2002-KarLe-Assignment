package cams.ui.camp;

import cams.components.input.GetString;
import cams.interfaces.InputField;
import cams.interfaces.UI;
import cams.utils.Dismiss;
import cams.utils.CampUtil;

public class CampLocationUI extends GetString implements UI, InputField {

    private CampUtil campUtil;
    private String location;
    
    public CampLocationUI(CampUtil campUtil, String title) {
        super(title);
        this.campUtil = campUtil;
        this.location = "";
    }

    public void body() {
        if (!focused()) { return; }
    }

    public boolean focused() {
        location = super.getValidString();
        if (location.equals(Dismiss.stringOption())) { return false; }
        campUtil.setLocation(location);
        return true;
    }
}