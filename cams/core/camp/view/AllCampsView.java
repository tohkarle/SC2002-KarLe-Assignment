package cams.core.camp.view;

import java.util.ArrayList;

import cams.Main;
import cams.util.UIComponents;

public class AllCampsView {

    private String faculty;

    public AllCampsView(String faculty) {
        this.faculty = faculty;
    }

    public void show() {
        ArrayList<String> names;

        if (faculty == null) {
            names = Main.campManager.getAllCampNames();
        } else {
            names = Main.campManager.getAllFacultyCampNames(this.faculty);
        }

        if (names.size() == 0) {
            UIComponents.pageHeader("No camp has been created.");
        } else {
            UIComponents.pageHeader("All camps:");
            for (int i = 0; i < names.size(); i++) {
                if (i == names.size() - 1) {
                    System.out.print(names.get(i));
                } else {
                    System.out.print(names.get(i) + ", ");
                }
            }
            System.out.println("");
        }

        if (UIComponents.navigationInput(-1, -1) == UIComponents.backOptionInt()) { return; };
    }
}
