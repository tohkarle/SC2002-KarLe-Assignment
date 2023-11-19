package cams.option.camp;

import java.util.Arrays;

import cams.interfaces.IntInput;
import cams.manager.CampManager;
import cams.ui.GetSelectionWithDismissUI;
import cams.utils.Page;

public class EditCampOptions extends CampInfoOptions {
    
    public EditCampOptions(CampManager campManager) {
        super(campManager);
    }

    @Override
    public void updateCampInfo() {
        super.updateCampInfo();
        super.getOptions().remove(String.format("Staff-in-charge: %s", super.getCampManager().getTempCamp().getStaffInCharge()));
        super.getOptions().addAll(Arrays.asList(
            "Update changes"
        ));
    }

    @Override
    public void display(String title) {
        Page.header(title);
        super.printOptions();
    }

    @Override
    public int selection() {
        IntInput selectionWithDismiss = new GetSelectionWithDismissUI(1, super.getOptionsSize());
        return selectionWithDismiss.getValidInt("Your selection: ");
    }
}
