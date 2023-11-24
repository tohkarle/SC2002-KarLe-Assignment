package cams.view.camp;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.model.Camp;
import cams.option.camp.EditCampOptions;
import cams.ui.camp.EditCampUI;
import cams.utils.Dismiss;

/**
 * View object for Edit Camp page
 */
public class EditCampView implements View {

    private Navigation navigation;
    private Input getInput;
    private Camp camp;

    /**
     * Initialize the EditCampView
     * @param navigation Navigation object used to control navigation of the application
     * @param getInput Input object used to get user input
     * @param camp Camp object to be edited
     */
    public EditCampView(Navigation navigation, Input getInput, Camp camp) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.camp = camp;
    }

    /**
     * Render the EditCampView
     */
    public void render() {

        Options editCampOptions = new EditCampOptions(camp);

        // Display camp info for editing
        editCampOptions.display("Select the field you want to edit: ");

        // Let user choose the field to edit
        int selectedEditField = editCampOptions.selection();
        if (selectedEditField == Dismiss.intOption()) { 
            navigation.dismissView();
            return; 
        }

        UI editCampUI = new EditCampUI(navigation, getInput, camp, selectedEditField);
        editCampUI.body();
    }
}
