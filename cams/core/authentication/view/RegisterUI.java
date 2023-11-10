package cams.core.authentication.view;

import cams.Main;
import cams.core.authentication.controller.RegisterUIController;

public class RegisterUI {
    private String name ;
    private String password;
    private String faculty;
    private RegisterUIController registerUIController;

    public RegisterUI() {
        this.name = null;
        this.password  = null;
        this.faculty = null;
        this.registerUIController = new RegisterUIController();
    }

    public void showUI() {
        System.out.println("\nRegister as a Staff or a Student");

        // get name
        System.out.print("Enter name: ");
        this.name = Main.scanner.nextLine().toUpperCase().trim();

        // get password
        System.out.print("Enter password: ");
        this.password = Main.scanner.nextLine();

        // get faculty
        System.out.print("Enter faculty: ");
        this.faculty = Main.scanner.nextLine();

        // Register user
        registerUIController.register(this.name, this.password, this.faculty);
    }
}
