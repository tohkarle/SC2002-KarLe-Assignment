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


    /**
     * The method to display the options
     * @param title The title of what the options are about
     */
    @Override
    public void display(String title) {
        Page.header(title);
        super.printOptions();
    }


    /**
     * A method to get the user to choose from the presented options
     * @return int of the option the user selected
     */
    @Override
    public int selection() {
        IntInput selectionWithDismiss = new GetSelectionWithDismiss(1, super.getOptionsSize());
        return selectionWithDismiss.getValidInt("Your selection: ");
    }
}
