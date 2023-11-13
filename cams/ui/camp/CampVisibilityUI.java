package cams.ui.camp;

import cams.interfaces.Input;
import cams.ui.ChooseBetweenTwoOptionsUI;
import cams.utils.Dismiss;
import cams.utils.CampUtil;

public class CampVisibilityUI extends ChooseBetweenTwoOptionsUI implements Input {

    private CampUtil campUtil;
    private boolean visibility;

    public CampVisibilityUI(CampUtil campUtil, String title) {
        super(title, "On", "Off");
        this.campUtil = campUtil;
    }

    public boolean getInput() {
        int option = super.getValidInt();
        visibility = (option == 1);
        if (option == Dismiss.intOption()) { return false; }
        campUtil.setVisibility(visibility);
        return true;
    }    
}
