package cams.ui.camp;

import cams.components.input.GetString;
import cams.interfaces.Input;
import cams.utils.Dismiss;
import cams.utils.CampUtil;

public class CampDescriptionUI extends GetString implements Input {

    private CampUtil campUtil;
    private String description;

    public CampDescriptionUI(CampUtil campUtil, String title) {
        super(title);
        this.campUtil = campUtil;
        this.description = "";
    }

    public boolean getInput() {
        description = super.getValidString();
        if (description.equals(Dismiss.stringOption())) { return false; }
        campUtil.setDescription(description);
        return true;
    }
}