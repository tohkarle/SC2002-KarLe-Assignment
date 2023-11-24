package cams.ui.camp;

import java.time.LocalDate;
import java.util.ArrayList;

import cams.components.input.ChooseBetweenTwoOptions;
import cams.interfaces.Input;
import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.CampManager;
import cams.manager.UserManager;
import cams.utils.Dismiss;
import cams.utils.FilterCamps;
import cams.utils.LoadingIndicator;
import cams.view.camp.CreatedCampsView;

/**
 * UI object for creating a camp
 */
public class CreateCampUI implements UI {

    private Navigation navigation;
    private Input getInput;
    private FilterCamps filterCamps;

    /**
     * Initialize the CreateCampUI
     * @param navigation Navigation object used to navigate between views
     * @param getInput Input object used to get user input
     * @param filterCamps FilterCamps object containing the applied filters
     */
    public CreateCampUI(Navigation navigation, Input getInput, FilterCamps filterCamps) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.filterCamps = filterCamps;
    }
    
    /**
     * Executes user interaction logic for creating a camp
     */
    @Override
    public void body() {

        UserManager userManager = UserManager.getInstance();
        CampManager campManager = CampManager.getInstance();

        // Get name
        String name = getInput.getValidString("Enter name: ");
        if (name.equals(Dismiss.stringOption())) {
            navigation.dismissView();
            return;
        }

        // Get faculty
        String faculty = getInput.getValidString("Enter faculty: ");
        if (faculty.equals(Dismiss.stringOption())) {
            navigation.dismissView();
            return;
        }

        // Get visibility
        IntInput getOption = new ChooseBetweenTwoOptions("On", "Off");
        int option = getOption.getValidInt("Set visibility");
        boolean visibility = (option == 1);

        // Get start date
        LocalDate startDate = getInput.getValidDate("Enter start date (yyyy-MM-dd): ");
        if (startDate == null) { return; }

        // Get end date
        LocalDate endDate;
        while (true) {
            endDate = getInput.getValidDate("Enter end date (yyyy-MM-dd): ");
            if (endDate == null) { return; }
            if (endDate.isEqual(startDate) || endDate.isAfter(startDate)) { break; }
            System.out.println("End date cannot be before start date.");
        }

        // Put start and end date in array list
        ArrayList<LocalDate> dates = new ArrayList<>();
        dates.add(startDate);
        dates.add(endDate);

        // Confirm create
        if (getInput.confirmOrDiscard("Confirm changes?") != 1) {
            navigation.dismissView();
            return; 
        }

        // Create camp
        if (campManager.createCampSuccessful(userManager.getCurrentUser().getName(), name, dates, faculty, visibility)) {
            LoadingIndicator.createLoadingIndicator("camp"); 
            navigation.navigateTo(new CreatedCampsView(navigation, getInput, filterCamps, name));
            return; 
        }
    }
}
