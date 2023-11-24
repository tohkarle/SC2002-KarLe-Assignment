package cams.view.enquiry;

import cams.components.option.Options;
import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.manager.EnquiryManager;
import cams.model.Enquiry;
import cams.option.enquiry.ReplyEnquiryOptions;
import cams.ui.enquiry.ReplyEnquiryUI;
import cams.utils.Dismiss;

/**
 * View object for Reply Enquiry page
 */
public class ReplyEnquiryView implements View {

    private Navigation navigation;
    private Input getInput;
    private int selectedEnquiryID;

    /**
     * Initialize the ReplyEnquiryView
     * @param navigation Navigation object used to control navigation of the application
     * @param getInput Input object used to get input from user
     * @param selectedEnquiryID int of the selected enquiry ID
     */
    public ReplyEnquiryView(Navigation navigation, Input getInput, int selectedEnquiryID) {
        this.navigation = navigation;
        this.getInput = getInput;
        this.selectedEnquiryID = selectedEnquiryID;
    }

    /**
     * Render the ReplyEnquiryView
     */
    public void render() {
        EnquiryManager enquiryManager = EnquiryManager.getInstance();
        Enquiry enquiry = enquiryManager.getEnquiry(selectedEnquiryID);
        Options replyEnquiryOptions = new ReplyEnquiryOptions(enquiry);

        // Display enquiry details
        replyEnquiryOptions.display("Enquiry details: ");

        // Let user choose to go back or reply enquiry
        if (replyEnquiryOptions.selection() == Dismiss.intOption()) { 
            navigation.dismissView();
            return; 
        }

        // Reply enquiry
        UI replyEnquiryUI = new ReplyEnquiryUI(getInput, enquiry);
        replyEnquiryUI.body();
    }
}
