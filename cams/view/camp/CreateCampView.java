package cams.view.camp;

import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.ui.camp.CreateCampUI;
import cams.utils.FilterCamps;
import cams.utils.Page;

/**
 * View object for Create Camp page
 */
public class CreateCampView implements View {

    private Navigation navigation;
    private Input getInput;
    private FilterCamps filterCamps;

    /**
     * Initialize the CreateCampView
     * @param navigation Navigation object used to control navigation of the application
     * @param getInput Input object used to get user input
     * @param filterCamps FilterCamps object containing the applied filters
     */
    public CreateCampView(Navigation navigation, Input getInput, FilterCamps filterCamps) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.filterCamps = filterCamps;
    }

    /**
     * Render the CreateCampView
     */
    public void render() {
        Page.header("Please enter the name, faculty, visibility and dates of the camp.");
        UI getCampInfoUI = new CreateCampUI(navigation, getInput, filterCamps);
        getCampInfoUI.body();
    }
}
