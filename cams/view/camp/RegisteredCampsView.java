package cams.view.camp;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.option.camp.UserCampOptions;
import cams.utils.Dismiss;

public class RegisteredCampsView implements View {

    private Navigation navigation;
    private Input getInput;

    public RegisteredCampsView(Navigation navigation, Input getInput) {
        this.navigation = navigation;
        this.getInput = getInput;
    }

    public void render() {

        // Display camps
        Options userCampOptions = new UserCampOptions();

        // Display registered camps
        userCampOptions.display("Select camp to view details:");

        // Let user select camp to view details
        int selectedCampID = userCampOptions.selection();
        if (selectedCampID == Dismiss.intOption()) { 
            navigation.dismissView();
            return; 
        }

        // Navigate to CampInfoView
        navigation.navigateTo(new RegisteredCampInfoView(navigation, getInput, selectedCampID));
    }
}
