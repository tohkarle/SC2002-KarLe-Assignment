package cams.ui.camp;

import cams.components.input.GetStringInput;
import cams.interfaces.UI;
import cams.model.Camp;
import cams.utils.Dismiss;

public class EditCampDescriptionUI extends GetStringInput implements UI {

    private Camp camp;
    private String title;

    public EditCampDescriptionUI(Camp camp, String title) {
        this.camp = camp;
        this.title = title;
    }

    @Override
    public void body() {
        String description = super.getValidString(title);
        if (description.equals(Dismiss.stringOption())) { return; }
        camp.setDescription(description);
    }
}