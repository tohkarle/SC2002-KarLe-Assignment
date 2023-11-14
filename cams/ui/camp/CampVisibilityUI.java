package cams.ui.camp;

import cams.interfaces.InputField;
import cams.interfaces.UI;
import cams.ui.ChooseBetweenTwoOptionsUI;
import cams.utils.Dismiss;
import cams.utils.CampUtil;

public class CampVisibilityUI extends ChooseBetweenTwoOptionsUI implements UI, InputField {

    private CampUtil campUtil;
    private boolean visibility;

    public CampVisibilityUI(CampUtil campUtil, String title) {
        super(title, "On", "Off");
        this.campUtil = campUtil;
    }

    public void body() {
        if (!focused()) { return; }
    }

    public boolean focused() {
        int option = super.getValidInt();
        visibility = (option == 1);
        if (option == Dismiss.intOption()) { return false; }
        campUtil.setVisibility(visibility);
        return true;
    }    
}
