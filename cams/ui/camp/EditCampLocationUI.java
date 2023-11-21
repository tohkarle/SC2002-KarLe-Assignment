package cams.ui.camp;

import cams.interfaces.Input;
import cams.interfaces.UI;
import cams.model.Camp;
import cams.utils.Dismiss;

public class EditCampLocationUI implements UI {

    private Input getInput;
    private Camp camp;
    private String title;
    
    public EditCampLocationUI(Input getInput, Camp camp, String title) {
        this.getInput = getInput;
        this.camp = camp;
        this.title = title;
    }

    @Override
    public void body() {
        String location = getInput.getValidString(title);
        if (location.equals(Dismiss.stringOption())) { return; }
        camp.setLocation(location);
    }
}