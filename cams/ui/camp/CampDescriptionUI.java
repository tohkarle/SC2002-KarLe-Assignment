package cams.ui.camp;

import cams.components.input.GetStringInput;
import cams.interfaces.InputField;
import cams.utils.Dismiss;
import cams.utils.CampUtil;

public class CampDescriptionUI extends GetStringInput implements InputField {

    private CampUtil campUtil;
    private String description;

    public CampDescriptionUI(CampUtil campUtil, String title) {
        super(title);
        this.campUtil = campUtil;
        this.description = "";
    }

    @Override
    public boolean focused() {
        description = super.getValidString();
        if (description.equals(Dismiss.stringOption())) { return false; }
        campUtil.setDescription(description);
        return true;
    }
}