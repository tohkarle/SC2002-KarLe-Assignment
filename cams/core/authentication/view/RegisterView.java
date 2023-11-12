package cams.core.authentication.view;

import cams.Main;
import cams.component.LoadingIndicator;
import cams.component.SelectionInput;
import cams.core.root.view.RootView;

public class RegisterView {
    private int option;
    private boolean isStaff;
    private String email;
    private String name ;
    private String password;
    private String faculty;
    private RootView rootUI;

    public RegisterView(RootView rootUI) {
        this.option = -1;
        this.isStaff = false;
        this.email = null;
        this.name = null;
        this.password  = null;
        this.faculty = null;
        this.rootUI = rootUI;
    }

    public void show() {

        chooseStaffOrStudent();

        // Go back to previous page if user enters -1
        if (this.option == SelectionInput.backOptionInt()) { return; }

        regiatrationFields();
    }

    public void chooseStaffOrStudent() {
        SelectionInput.pageHeader("Do you want to register as a Staff or a Student?");
        System.out.println("(1) Staff");
        System.out.println("(2) Student");

        this.option = SelectionInput.selectionInputFieldWithBack(1, 2);
    }

    public void regiatrationFields() {
        this.isStaff = (this.option == 1);

        SelectionInput.pageHeader("Please enter your email, name, password and faculty.");

        // get email
        System.out.print("Enter email: ");
        this.email = Main.scanner.nextLine();
        if (this.email.equals(SelectionInput.backOptionString())) { return; }

        // get name
        System.out.print("Enter name: ");
        this.name = Main.scanner.nextLine();
        if (this.name.equals(SelectionInput.backOptionString())) { return; }

        // get password
        System.out.print("Enter password: ");
        this.password = Main.scanner.nextLine();
        if (this.password.equals(SelectionInput.backOptionString())) { return; }

        // get faculty
        System.out.print("Enter faculty: ");
        this.faculty = Main.scanner.nextLine();
        if (this.faculty.equals(SelectionInput.backOptionString())) { return; }

        // Register user
        this.register();
    }

    private void register() {
        if (Main.authManager.emailAlreadyExists(this.email)) {
            System.out.println("Email already used, please enter another email.");
            return;
        }

        // Set the currentUserID in rootUI after registering user
        this.rootUI.setCurrentUserID(Main.authManager.registerUser(this.email, this.name, this.password, this.faculty, this.isStaff));
        LoadingIndicator.logInLoadingIndicator();
    }
}
