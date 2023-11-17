package cams.ui.camp;

import java.time.LocalDate;
import java.util.ArrayList;

import cams.components.LoadingIndicator;
import cams.components.input.GetDate;
import cams.components.input.GetInt;
import cams.components.input.GetString;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.CampManager;
import cams.manager.UserManager;
import cams.ui.ChooseBetweenTwoOptionsUI;
import cams.utils.Dismiss;

public class GetCampInfoUI implements UI {
    
    private Navigation navigation;
    private UserManager userManager;
    private CampManager campManager;

    public GetCampInfoUI(Navigation navigation, UserManager userManager, CampManager campManager) {
        this.navigation = navigation;
        this.userManager = userManager;
        this.campManager = campManager;
    }

    @Override
    public void body() {

        // Get name
        GetString getString = new GetString();
        String name = getString.getValidString("Enter name: ");
        if (name.equals(Dismiss.stringOption())) {
            navigation.dismissView();
            return;
        }

        // Get faculty
        String faculty = getString.getValidString("Enter faculty: ");
        if (faculty.equals(Dismiss.stringOption())) {
            navigation.dismissView();
            return;
        }

        // Get visibility
        GetInt getOption = new ChooseBetweenTwoOptionsUI("Set visibility", "On", "Off");
        int option = getOption.getValidInt();
        boolean visibility = (option == 1);

        // Get start date
        GetDate getdate = new GetDate();
        LocalDate startDate = getdate.getValidDate("Enter start date (yyyy-MM-dd): ");

        // Get end date
        LocalDate endDate;
        while (true) {
            endDate = getdate.getValidDate("Enter end date (yyyy-MM-dd): ");
            if (endDate == null) { return; }
            if (endDate.isEqual(startDate) || endDate.isAfter(startDate)) { break; }
            System.out.println("End date cannot be before start date.");
        }

        // Put start and end date in array list
        ArrayList<LocalDate> dates = new ArrayList<>();
        dates.add(startDate);
        dates.add(endDate);

        // Create camp
        if (campManager.createCampSuccessful(userManager.getCurrentUser().getName(), name, dates, faculty, visibility)) {
            LoadingIndicator.createLoadingIndicator("camp"); 
            navigation.dismissView();
            return; 
        }
    }
}
