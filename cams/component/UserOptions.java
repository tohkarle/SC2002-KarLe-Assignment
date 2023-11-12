package cams.component;

public class UserOptions implements Options {

    protected String[] options;

    public void display() {
        System.out.println("\nChoose your action:");
        for (int i = 0; i < this.options.length; i++) {
            System.out.println("(" + (i + 1) + ") " + options[i]);
        }
    }

    public int selection() {
        IntInput yourSelectionInput = new YourSelectionInput(1, this.options.length);
        return yourSelectionInput.getValidInput();
    }
}
