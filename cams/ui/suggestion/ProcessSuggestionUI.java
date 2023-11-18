package cams.ui.suggestion;

import cams.components.LoadingIndicator;
import cams.interfaces.IntInput;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.manager.SuggestionManager;
import cams.manager.UserManager;
import cams.model.SuggestionStatus;
import cams.ui.GetSelectionWithDismissUI;
import cams.utils.Dismiss;

public class ProcessSuggestionUI implements UI {
    
    private Navigation navigation;
    private UserManager userManager;
    private SuggestionManager suggestionManager;
    private IntInput confirm;

    public ProcessSuggestionUI(Navigation navigation, UserManager userManager, SuggestionManager suggestionManager, IntInput confirm) {
        this.navigation = navigation;
        this.userManager = userManager;
        this.suggestionManager = suggestionManager;
        this.confirm = confirm;
    }

    @Override
    public void body() {
        // Allow staff to go back, approve or reject suggestion
        IntInput selectionWithDismiss = new GetSelectionWithDismissUI(-1, 2);
        int option = selectionWithDismiss.getValidInt("Your selection: ");
        if (option == Dismiss.intOption() ) { 
            navigation.dismissView();
            return; 
        }

        // Confirm decision or discard
        if (confirm.getValidInt("Confirm decision?") != 1) { return; }


        // Check if suggestion has been processed before
        if (suggestionManager.getTempSuggestion().getSuggestionStatus() != SuggestionStatus.PENDING) {
            System.out.println("Suggestion has already been processed.");
            navigation.dismissView();
            return;
        }

        // Process suggestion
        SuggestionStatus suggestionStatus = (option == 1) ? SuggestionStatus.ACCEPTED : SuggestionStatus.REJECTED;
        suggestionManager.processSuggestion(userManager.getCurrentUser().getName(), suggestionManager.getSelectedSuggestionID(), suggestionStatus);
        LoadingIndicator.processLoadingIndicator("suggestion");
        System.out.println((option == 1) ? "Suggestion accepted!" : "Suggestion rejected!");
        navigation.dismissView();
    }
}
