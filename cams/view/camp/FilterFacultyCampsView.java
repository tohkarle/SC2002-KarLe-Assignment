package cams.view.camp;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.option.camp.FilterFacultyCampsOptions;
import cams.ui.camp.FilterFacultyCampsUI;
import cams.utils.Dismiss;
import cams.utils.FilterCamps;

public class FilterFacultyCampsView implements View {

    private Navigation navigation;
    private Input getInput;
    private FilterCamps filterCamps;

    public FilterFacultyCampsView(Navigation navigation, Input getInput, FilterCamps filterCamps) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.filterCamps = filterCamps;
    }

    public void render() {

        Options filterFacultyCampsOptions = new FilterFacultyCampsOptions();
        filterFacultyCampsOptions.display("Please select the field you want to filter by.");

        int option = filterFacultyCampsOptions.selection();
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
                filterCamps.setSelectedFilterBy("location");
                break;
            case 3:
                filterCamps.setSelectedFilterBy("description");
                break;
            case 4:
                filterCamps.setSelectedFilterBy("dates");
                break;
            case 5:
                filterCamps.setSelectedFilterBy("registrationClosingDate");
                break;
            case 6:
                filterCamps.setSelectedFilterBy("totalslots");
                break;
            case 7:
                filterCamps.setSelectedFilterBy("committeeSlots");
                break;
            case 8:
                filterCamps.setSelectedFilterBy("staffInCharge");
                break;
        }

        UI filterFacultyCampsUI = new FilterFacultyCampsUI(navigation, getInput, filterCamps, option);
        filterFacultyCampsUI.body();
    }
}
