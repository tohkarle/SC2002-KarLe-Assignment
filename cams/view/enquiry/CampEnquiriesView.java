package cams.view.enquiry;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.option.enquiry.CampEnquiryOptions;
import cams.utils.Dismiss;

public class CampEnquiriesView implements View {

    private Navigation navigation;
    private boolean viewResolved;
    private int selectedCampID;

    public CampEnquiriesView(Navigation navigation, boolean viewResolved, int selectedCampID) {
        this.navigation = navigation;
        this.viewResolved = viewResolved;
        this.selectedCampID = selectedCampID;
    }

    public void render() {

        // Display enquiry
        Options campEnquiryOptions = new CampEnquiryOptions(viewResolved, selectedCampID);
        campEnquiryOptions.display("Select enquiry to view details:");

        // Let user select enquiry to view details
        int selectedEnquiryID = campEnquiryOptions.selection();
        if (selectedEnquiryID == Dismiss.intOption()) { 
            navigation.dismissView();
            return; 
        }

        // Navigate to ReplyEnquiryView
        navigation.navigateTo(new ReplyEnquiryView(navigation, selectedEnquiryID));
    }
}
