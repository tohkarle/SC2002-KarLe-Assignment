package cams.view.camp;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.option.camp.UserCampsOptions;
import cams.utils.Dismiss;

public class CreatedCampsView implements View {

    private Navigation navigation;
    private Input getInput;
    private Options userCampOptions;

    public CreatedCampsView(Navigation navigation, Input getInput) {
        this.navigation = navigation;
        this.getInput = getInput;
    }

    public void render() {

        userCampOptions = new UserCampsOptions();

        // Label camp as new if staff just created camp
        if (justCreatedCamp()) { labelCampAsNew(); }

        // Display camps
        userCampOptions.display("Select camp to view details:");

        // Let user select camp to view details
        int selectedCampID = userCampOptions.selection();
        if (selectedCampID == Dismiss.intOption()) { 
            navigation.popToRoot();
            return; 
        }

        // Navigate to EditCampView
        navigation.navigateTo(new CreatedCampInfoView(navigation, getInput, selectedCampID));
    }

    public void labelCampAsNew() {
        String name = userCampOptions.getOption(userCampOptions.getOptionsSize() - 1);
        System.out.println("Labeling: " + name);
        userCampOptions.replaceOption(name, name + " (New)");
    }

    public boolean justCreatedCamp() {
        return (navigation.getPreviousView().equals(CreateCampView.class));
    }
}
