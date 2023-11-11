package cams.core.authentication.view;

import cams.Main;
import cams.core.authentication.controller.RegisterUIController;
import cams.core.root.view.RootView;
import cams.util.UserInput;

public class RegisterView {
    private String email;
    private String name ;
    private String password;
    private String faculty;
    private boolean isStaff;
    private RegisterUIController registerUIController;
    private RootView rootUI;

    public RegisterView(RootView rootUI) {
        this.email = null;
        this.name = null;
        this.password  = null;
        this.faculty = null;
        this.isStaff = false;
        this.registerUIController = new RegisterUIController();
        this.rootUI = rootUI;
    }

    public void show() {
        System.out.println("Do you want to register as a Staff or a Student?");
        System.out.println("(1) Staff");
        System.out.println("(2) Student");

        int option = UserInput.optionInput();

        if (option == 1) {
            isStaff = true;
        } else if (option == 2) {
            isStaff = false;
        }

        System.out.println("Please enter your email, name, password and faculty.");

        // get email
        System.out.print("Enter email: ");
        this.email = Main.scanner.nextLine();

        // get name
        System.out.print("Enter name: ");
        this.name = Main.scanner.nextLine();

        // get password
        System.out.print("Enter password: ");
        this.password = Main.scanner.nextLine();

        // get faculty
        System.out.print("Enter faculty: ");
        this.faculty = Main.scanner.nextLine();

        // Register user
        // Set the currentUserID in rootUI after registering user
        rootUI.setCurrentUserID(registerUIController.register(this.email, this.name, this.password, this.faculty, this.isStaff));
    }
}
