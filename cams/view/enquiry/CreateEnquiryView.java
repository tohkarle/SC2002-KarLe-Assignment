package cams.view.enquiry;

import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.utils.Page;

public class CreateEnquiryView extends View {

    // UIs in this view:
    private UI createEnquiryUi;

    public CreateEnquiryView(Navigation navigation) {
        super(navigation);
    }

    public void render() {
        Page.header("Please enter a title and content for the enquiry.");
        createEnquiryUi = super.getUI("enquiry.CreateEnquiryUI");
        createEnquiryUi.body();
    }
}
