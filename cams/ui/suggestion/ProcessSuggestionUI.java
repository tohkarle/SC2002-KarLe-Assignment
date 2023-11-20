package cams.ui.suggestion;

import cams.components.input.ConfirmOrDiscard;
import cams.components.input.GetSelectionWithDismiss;
import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.CampManager;
import cams.manager.SuggestionManager;
import cams.manager.UserManager;
import cams.model.Suggestion;
import cams.model.SuggestionStatus;
import cams.utils.Dismiss;
import cams.utils.LoadingIndicator;

public class ProcessSuggestionUI implements UI {
    
    private Navigation navigation;
    private Suggestion suggestion;
    private int selectedSuggestionID;

    public ProcessSuggestionUI(Navigation navigation, Suggestion suggestion, int selectedSuggestionID) {
        this.navigation = navigation;
        this.suggestion = suggestion;
        this.selectedSuggestionID = selectedSuggestionID;
    }

    @Override
    public void body() {

        UserManager userManager = UserManager.getInstance();
        CampManager campManager = CampManager.getInstance();
        SuggestionManager suggestionManager = SuggestionManager.getInstance();
        IntInput confirm  = new ConfirmOrDiscard();

        // Allow staff to go back, approve or reject suggestion
        IntInput selectionWithDismiss = new GetSelectionWithDismiss(-1, 2);
        int option = selectionWithDismiss.getValidInt("Your selection: ");
        if (option == Dismiss.intOption() ) { 
            navigation.dismissView();
            return; 
        }

        // Confirm decision or discard
        if (confirm.getValidInt("Confirm decision?") != 1) { return; }


        // Check if suggestion has been processed before
        if (suggestion.getSuggestionStatus() != SuggestionStatus.PENDING) {
            System.out.println("Suggestion has already been processed.");
            navigation.dismissView();
            return;
        }

        // Process suggestion
        SuggestionStatus suggestionStatus = (option == 1) ? SuggestionStatus.ACCEPTED : SuggestionStatus.REJECTED;
        suggestionManager.processSuggestion(userManager.getCurrentUser().getName(), selectedSuggestionID, suggestionStatus);

        // If approved, updates actual camp
        if (option == 1) {
            if (!campManager.updateCampSuccessful(suggestion.getCamp())) { return; };
        }
        
        LoadingIndicator.processLoadingIndicator("suggestion");
        System.out.println((option == 1) ? "Suggestion accepted!" : "Suggestion rejected!");
        navigation.dismissView();
    }
}
