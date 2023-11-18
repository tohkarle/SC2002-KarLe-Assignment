package cams.manager;

import cams.model.Student;
import cams.service.AuthService;

public class StudentManager extends UserManager {

    public StudentManager(AuthService authService) {
        super(authService);
    }

    public int getPoint(String studentName){
        return ((Student) super.getAuthService().getUser(studentName)).getPoint();
    }

    public void addOnePoint(String studentName) {
        ((Student) super.getAuthService().getUser(studentName)).addOnePoint();
        super.getAuthService().save();
    }
}
