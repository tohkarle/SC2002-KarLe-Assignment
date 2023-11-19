package cams.view.camp;

import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.option.camp.CampInfoOptions;
import cams.option.camp.CreatedCampInfoOptions;
import cams.ui.GetSelectionWithDismissUI;
import cams.utils.Dismiss;

public class CreatedCampInfoView extends View {
    
    // Options for this view:
    private CampInfoOptions createdCampInfoOptions;

    // UIs for this view:
    private UI deleteCampUI;

    public CreatedCampInfoView(Navigation navigation) {
        super(navigation);
    }

    public void render() {

        createdCampInfoOptions = (CreatedCampInfoOptions) super.getOptions("camp.CreatedCampInfoOptions");

        // Update created camp details to latest
        createdCampInfoOptions.updateCampInfo();

        // Display created camp details
        createdCampInfoOptions.viewOnly("Camp details: ");

        // Allow staff to go back or edit camp, manage enquiries etc
        IntInput selectionWithDismiss = new GetSelectionWithDismissUI(-1, 5);
        int option = selectionWithDismiss.getValidInt("Your selection: ");
        if (option == Dismiss.intOption() ) { 
            super.getNavigation().dismissView();
            return; 
        }

        switch (option) {
            case 1:
                super.getNavigation().navigateTo("camp.EditCampView");
                break;
            case 2:
                super.getNavigation().navigateTo("enquiry.EnquiryStatusView");
                break;
            case 3:
                super.getNavigation().navigateTo("suggestion.SuggestionStatusView");
                break;
            case 4:
                break;
            case 5:
                deleteCampUI = super.getUI("camp.DeleteCampUI");
                deleteCampUI.body();
                break;
        }
    }
}
