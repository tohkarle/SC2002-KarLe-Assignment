package cams.ui.enquiry;

import cams.components.input.ConfirmOrDiscard;
import cams.components.input.GetStringInput;
import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.StringInput;
import cams.interfaces.UI;
import cams.manager.EnquiryManager;
import cams.manager.UserManager;
import cams.utils.Dismiss;
import cams.utils.LoadingIndicator;

/**
 * UI object for creating an enquiry
 */
public class CreateEnquiryUI implements UI {
    
    private Navigation navigation;
    private int selectedCampID;

    /**
     * Initialize the CreateEnquiryUI
     * @param navigation Navigation object used to control navigation of the application
     * @param selectedCampID ID of the camp to create an enquiry for
     */
    public CreateEnquiryUI(Navigation navigation, int selectedCampID) {
        this.navigation = navigation;
        this.selectedCampID = selectedCampID;
    }

    /**
     * Executes user interaction logic for creating an enquiry
     */
    @Override
    public void body() {

        UserManager userManager = UserManager.getInstance();
        EnquiryManager enquiryManager = EnquiryManager.getInstance();
        StringInput getString = new GetStringInput();
        IntInput confirm = new ConfirmOrDiscard();

        // Get title
        String title = getString.getValidString("Enter title: ");
        if (title.equals(Dismiss.stringOption())) {
            navigation.dismissView();
            return;
        }

        // Get content
        String content = getString.getValidString("Enter content: ");
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
        enquiryManager.createEnquiry(userManager.getCurrentUser().getName(), selectedCampID, title, content);
        LoadingIndicator.submitLoadingIndicator("enquiry");
        navigation.dismissView();
    }
}
