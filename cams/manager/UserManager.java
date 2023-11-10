package cams.manager;

import java.util.HashMap;

import cams.model.Staff;
import cams.model.Student;
import cams.model.User;
import cams.util.Serialize;

public class UserManager {

    protected HashMap<String, User> userMap;

    public UserManager(){
        load();
    }

    public Boolean isValidUser(String userID){
        return userMap.get(userID) != null;
    }

    public String getName(String userID){
        return ((User)userMap.get(userID)).getName();
    }

    public Boolean checkPassword(String userID, String password){
        return ((User)userMap.get(userID)).checkPassword(password);
    }

    public Boolean hasDefaultPassword(String userID){
        return ((User)userMap.get(userID)).checkPassword("password");
    }

    public void setPassword(String userID, String password){
        ((User)userMap.get(userID)).setPassword(password);
    }

    public boolean isStudent(String userID) {
        return (userMap.get(userID) instanceof Student);
    }

    public boolean isStaff(String userID) {
        return (userMap.get(userID) instanceof Staff);
    }

    public void save(){
        Serialize.save("userMap.sav", userMap);
    }

    public void load(){
        @SuppressWarnings("unchecked")
        HashMap<String, User> loadedMap = (HashMap<String, User>)Serialize.load("userMap.sav");
        userMap = loadedMap;
    }
}