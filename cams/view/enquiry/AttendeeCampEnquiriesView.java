package cams.view.enquiry;

import cams.components.option.Options;
import cams.interfaces.Navigation;
import cams.interfaces.View;
import cams.option.enquiry.AttendeeCampEnquiriesOptions;
import cams.utils.Dismiss;

public class AttendeeCampEnquiriesView implements View {

    private Navigation navigation;
    private boolean viewResolved;
    private int selectedCampID;

    public AttendeeCampEnquiriesView(Navigation navigation, boolean viewResolved, int selectedCampID) {
        this.navigation = navigation;
        this.viewResolved = viewResolved;
        this.selectedCampID = selectedCampID;
    }

    public void render() {

        // Display enquiries
        Options attendeeCampEnquiriesOptions = new AttendeeCampEnquiriesOptions(viewResolved, selectedCampID);
        attendeeCampEnquiriesOptions.display("Select enquiry to view details:");

        // Let user select enquiry to view details
        int selectedEnquiryID = attendeeCampEnquiriesOptions.selection();
        if (selectedEnquiryID == Dismiss.intOption()) { 
            navigation.dismissView();
            return; 
        }

        // Navigate to CreatedEnquiryInfoView
        navigation.navigateTo(new CreatedEnquiryInfoView(navigation, selectedEnquiryID));
    }
}
