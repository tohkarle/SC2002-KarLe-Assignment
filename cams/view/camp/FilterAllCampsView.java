package cams.view.camp;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.option.camp.FilterAllCampsOptions;
import cams.ui.camp.FilterAllCampsUI;
import cams.utils.Dismiss;
import cams.utils.FilterCamps;

public class FilterAllCampsView implements View {

    private Navigation navigation;
    private Input getInput;
    private FilterCamps filterCamps;

    public FilterAllCampsView(Navigation navigation, Input getInput, FilterCamps filterCamps) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.filterCamps = filterCamps;
    }

    public void render() {
        Options filterAllCampsOptions = new FilterAllCampsOptions();
        filterAllCampsOptions.display("Please select the field you want to filter by.");

        int option = filterAllCampsOptions.selection();
        if (option == Dismiss.intOption()) { 
            navigation.dismissView();
            return; 
        }

        switch (option) {
            case 0:
                filterCamps.setSelectedFilterBy("");
                break;
            case 1:
                filterCamps.setSelectedFilterBy("campName");
                break;
            case 2:
                filterCamps.setSelectedFilterBy("userGroup");
                break;
            case 3:
                filterCamps.setSelectedFilterBy("location");
                break;
            case 4:
                filterCamps.setSelectedFilterBy("description");
                break;
            case 5:
                filterCamps.setSelectedFilterBy("dates");
                break;
            case 6:
                filterCamps.setSelectedFilterBy("registrationClosingDate");
                break;
            case 7:
                filterCamps.setSelectedFilterBy("totalslots");
                break;
            case 8:
                filterCamps.setSelectedFilterBy("committeeSlots");
                break;
            case 9:
                filterCamps.setSelectedFilterBy("staffInCharge");
                break;
        }

        UI filterCampsUI = new FilterAllCampsUI(navigation, getInput, filterCamps, option);
        filterCampsUI.body();
    }
}
