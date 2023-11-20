package cams.ui.camp;

import java.time.LocalDate;
import java.util.ArrayList;

import cams.components.LoadingIndicator;
import cams.components.input.GetDateInput;
import cams.components.input.GetIntInput;
import cams.components.input.GetStringInput;
import cams.interfaces.DateInput;
import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.StringInput;
import cams.interfaces.UI;
import cams.manager.CampManager;
import cams.manager.UserManager;
import cams.ui.ChooseBetweenTwoOptionsUI;
import cams.ui.ConfirmOrDiscardUI;
import cams.utils.Dismiss;
import cams.view.camp.CreatedCampsView;

public class CreateCampUI implements UI {

    private Navigation navigation;

    public CreateCampUI(Navigation navigation) {
        this.navigation = navigation;
    }
    
    @Override
    public void body() {

        UserManager userManager = UserManager.getInstance();
        CampManager campManager = CampManager.getInstance();
        StringInput getString = new GetStringInput();
        DateInput getDate = new GetDateInput();
        IntInput confirm = new ConfirmOrDiscardUI();

        // Get name
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
        GetIntInput getOption = new ChooseBetweenTwoOptionsUI("On", "Off");
        int option = getOption.getValidInt("Set visibility");
        boolean visibility = (option == 1);

        // Get start date
        LocalDate startDate = getDate.getValidDate("Enter start date (yyyy-MM-dd): ");

        // Get end date
        LocalDate endDate;
        while (true) {
            endDate = getDate.getValidDate("Enter end date (yyyy-MM-dd): ");
            if (endDate == null) { return; }
            if (endDate.isEqual(startDate) || endDate.isAfter(startDate)) { break; }
            System.out.println("End date cannot be before start date.");
        }

        // Put start and end date in array list
        ArrayList<LocalDate> dates = new ArrayList<>();
        dates.add(startDate);
        dates.add(endDate);

        // Confirm create
        if (confirm.getValidInt("Confirm changes?") != 1) {
            navigation.dismissView();
            return; 
        }

        // Create camp
        if (campManager.createCampSuccessful(userManager.getCurrentUser().getName(), name, dates, faculty, visibility)) {
            LoadingIndicator.createLoadingIndicator("camp"); 
            navigation.navigateTo(new CreatedCampsView(navigation));
            return; 
        }
    }
}
