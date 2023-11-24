package cams.option.camp;

import java.util.Arrays;

import cams.components.input.GetSelectionWithDismiss;
import cams.interfaces.IntInput;
import cams.model.Camp;
import cams.utils.Page;

/**
 * Options object for managing edits to a camp in the EditCampView
 */
public class EditCampOptions extends CampInfoOptions {
    
    /**
     * Initialize the camp information options
     * @param camp Camp object to display information of
     */
    public EditCampOptions(Camp camp) {
        super(camp);
        this.changeOption();
    }

    public void changeOption() {
        super.getOptions().remove(String.format("Staff-in-charge: %s", super.getCamp().getStaffInCharge()));
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
        IntInput selectionWithDismiss = new GetSelectionWithDismiss(1, super.getOptionsSize());
        return selectionWithDismiss.getValidInt("Your selection: ");
    }
}
