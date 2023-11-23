package cams.view.camp;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.option.camp.FacultyCampsOptions;
import cams.utils.Dismiss;

public class FacultyCampsView implements View {

    private Navigation navigation;

    public FacultyCampsView(Navigation navigation) {
        this.navigation = navigation;
    }

    public void render() {

        // Display camps
        Options facultyCampOptions = new FacultyCampsOptions();
        facultyCampOptions.display("Select camp to view details:");

        // Let user select camp to view details
        int selectedCampID = facultyCampOptions.selection();
        if (selectedCampID == Dismiss.intOption()) { 
            navigation.dismissView();
            return; 
        }

        // Navigate to CampInfoView
        navigation.navigateTo(new CampInfoView(navigation, selectedCampID));
    }
}