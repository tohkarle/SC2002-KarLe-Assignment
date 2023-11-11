package cams.core.camp.view;

import java.util.ArrayList;

import cams.Main;
import cams.util.UIComponents;

public class AllCampsView {

    private boolean running;
    private String faculty;

    public AllCampsView(String faculty) {
        this.running = true;
        this.faculty = faculty;
    }

    public void show() {
        ArrayList<String> names = Main.campManager.getAllCampNames(this.faculty);

        do {

            if (names.size() == 0) { 
                System.out.println( "No camp has been created for your faculty. Please come back at a later time."); 
            } else {
                System.out.println("All camps:");
                for (int i = 0; i < names.size(); i++) {
                    System.out.println( "(" + (i+1) + ") " + names.get(i));
                }
            }

            UIComponents.backOption(1);
            int option = UIComponents.userInput();

            switch (option) {
                case 1:
                    this.running = false;
                    break;
                default:
                    UIComponents.invalidUserInput();
                    break;
            }

        } while (running);
    }
}
