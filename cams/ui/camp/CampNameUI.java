package cams.ui.camp;

import cams.components.input.GetString;
import cams.interfaces.InputField;
import cams.utils.Dismiss;
import cams.utils.CampUtil;

public class CampNameUI extends GetString implements InputField {

    private CampUtil campUtil;
    private String campName;

    public CampNameUI(CampUtil campUtil, String title) {
        super(title);
        this.campUtil = campUtil;
        this.campName = "";
    }

    @Override
    public boolean focused() {
        campName = super.getValidString();
        if (campName.equals(Dismiss.stringOption())) { return false; }
        campUtil.setName(campName);
        return true;
    }
}