package cams.ui.camp;

import cams.components.input.GetString;
import cams.interfaces.InputField;
import cams.interfaces.UI;
import cams.utils.Dismiss;
import cams.utils.CampUtil;

public class CampDescriptionUI extends GetString implements UI, InputField {

    private CampUtil campUtil;
    private String description;

    public CampDescriptionUI(CampUtil campUtil, String title) {
        super(title);
        this.campUtil = campUtil;
        this.description = "";
    }

    public void body() {
        if (!focused()) { return; }
    }

    public boolean focused() {
        description = super.getValidString();
        if (description.equals(Dismiss.stringOption())) { return false; }
        campUtil.setDescription(description);
        return true;
    }
}