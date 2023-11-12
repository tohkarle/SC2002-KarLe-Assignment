package cams.component;

public class StudentOptions {

    private String[] options;

    public StudentOptions() {
        this.options = new String[] {
            "View profile",
            "Change password",
            "View all camps",
            "View registered camps",
            "Register for camps",
            "Log out"
        };
    }

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
