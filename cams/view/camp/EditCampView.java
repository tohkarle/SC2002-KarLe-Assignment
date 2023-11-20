package cams.view.camp;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.model.Camp;
import cams.option.camp.EditCampOptions;
import cams.ui.camp.EditCampUI;
import cams.utils.Dismiss;

public class EditCampView implements View {

    private Navigation navigation;
    private Camp camp;

    public EditCampView(Navigation navigation, Camp camp) {
        this.navigation = navigation;
        this.camp = camp;
    }

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

        UI editCampUI = new EditCampUI(navigation, camp, selectedEditField);
        editCampUI.body();
    }
}
