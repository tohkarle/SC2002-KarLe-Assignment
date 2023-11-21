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
import cams.utils.LoadingIndicator;
import cams.view.camp.CreatedCampsView;

public class CreateCampUI implements UI {

    private Navigation navigation;
    private Input getInput;

    public CreateCampUI(Navigation navigation, Input getInput) {
        this.navigation = navigation;
        this.getInput = getInput;
    }
    
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
            navigation.navigateTo(new CreatedCampsView(navigation, getInput));
            return; 
        }
    }
}
