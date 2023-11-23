package cams.view.camp;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.option.camp.AllCampsOptions;
import cams.utils.Dismiss;

public class AllCampsView implements View {

    private Navigation navigation;

    public AllCampsView(Navigation navigation) {
        this.navigation = navigation;
    }

    public void render() {

        // Display camps
        Options allCampOptions = new AllCampsOptions();
        allCampOptions.display("Select camp to view details:");

        // Let user select camp to view details
        int selectedCampID = allCampOptions.selection();
        if (selectedCampID == Dismiss.intOption()) { 
            navigation.dismissView();
            return; 
        }

        // Navigate to CampInfoView
        navigation.navigateTo(new CampInfoView(navigation, selectedCampID));
    }
}
