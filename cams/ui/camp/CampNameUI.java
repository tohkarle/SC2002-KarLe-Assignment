package cams.ui.camp;

import cams.components.input.GetStringInput;
import cams.utils.Dismiss;
import cams.utils.CampUtil;

public class CampNameUI extends GetStringInput {

    private CampUtil campUtil;
    private String campName;

    public CampNameUI(String title) {
        this.campName = "";
    }

    public String getValidString() {
        campName = super.getValidString("Enter name: ");
        if (campName.equals(Dismiss.stringOption())) { return null; }
        return campName;
    }
}