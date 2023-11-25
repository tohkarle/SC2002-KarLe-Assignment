package cams.components.input;

import cams.Main;
import cams.manager.UserManager;


/**
 * Input object to get a int input for a given range
 */
public class GetSelection extends GetOptionInput {


    /**
     * Initialize the input object
     * @param min The inclusive lower bound for the range of options
     * @param max The inclusive upper bound for the range of options
     */
    public GetSelection(int min, int max) {
        super.setMinMax(min, max);
    }


    /**
     * A method to get a valid int input for the specified range
     * @param title The title of what the input is for
     * @return int of the option selected
     */
    @Override
    public int getValidInt(String title) {
        System.out.println("");
        while (true) {
            askForPasswordChange();
            System.out.print(title);
            super.input = Main.scanner.nextLine();
            if (super.inputIsEmpty() || super.inputIsNotInt() || super.inputIsNotBetweenMinMax()) { continue; }
            return super.number;
        }
    }

    private void askForPasswordChange() {
        UserManager userManager = UserManager.getInstance();
        if (userManager.getCurrentUser() != null && userManager.getCurrentUser().passwordMatches("password")) {
            System.out.println("You are still using the default password, please go to 'Change password' to change to a new password.");
        }
    }
}
