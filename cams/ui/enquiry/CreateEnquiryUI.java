package cams.ui.enquiry;

import cams.components.LoadingIndicator;
import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.StringInput;
import cams.interfaces.UI;
import cams.manager.CampManager;
import cams.manager.EnquiryManager;
import cams.manager.UserManager;
import cams.utils.Dismiss;

public class CreateEnquiryUI implements UI {
    
    private Navigation navigation;
    private UserManager userManager;
    private CampManager campManager;
    private EnquiryManager enquiryManager;
    private StringInput getString;
    private IntInput confirm;

    public CreateEnquiryUI(Navigation navigation, UserManager userManager, CampManager campManager, EnquiryManager enquiryManager, StringInput getString, IntInput confirm) {
        this.navigation = navigation;
        this.userManager = userManager;
        this.campManager = campManager;
        this.enquiryManager = enquiryManager;
        this.getString = getString;
        this.confirm = confirm;
    }

    @Override
    public void body() {

        // Get title
        String title = getString.getValidString("Enter title: ");
        if (title.equals(Dismiss.stringOption())) {
            navigation.dismissView();
            return;
        }

        // Get content
        String content = getString.getValidString("Enter enquiry: ");
        if (content.equals(Dismiss.stringOption())) {
            navigation.dismissView();
            return;
        }

        // Confirm submit
        if (confirm.getValidInt("Confirm submit?") != 1) {
            navigation.dismissView();
            return; 
        }

        // Create enquriry
        enquiryManager.createEnquiry(userManager.getCurrentUser().getName(), campManager.getSelectedCampID(), title, content);
        LoadingIndicator.submitLoadingIndicator("enquiry");
        navigation.dismissView();
    }
}
