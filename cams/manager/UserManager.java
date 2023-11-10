package cams.manager;

import java.util.HashMap;

import cams.model.Staff;
import cams.model.Student;
import cams.model.User;
import cams.util.Serialize;
import cams.util.UniqueKey;

public class UserManager {

    private static int uniqueKey = 0;
    protected HashMap<Integer, User> userMap;

    public UserManager(){
        Serialize.checkAndCreateFile("userMap.sav");
        load();
    }

    public void registerUser(String name, String password, String faculty) {
        int key = UniqueKey.generateNewKey(uniqueKey);
        while(userMap.get(key) != null) key = UniqueKey.generateNewKey(uniqueKey);
        User newUser = new User(key, name, password, faculty);
        userMap.put(key, newUser);
    }

    public Boolean isValidUser(String name){
        for (User user : userMap.values()) {
            if (user.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public int getUserID(String name) {
        for (User user : userMap.values()) {
            if (user.getName().equals(name)) {
                return user.getUserID();
            }
        }
        return -1;
    }

    public String getName(int userID){
        return ((User)userMap.get(userID)).getName();
    }

    public Boolean checkPassword(String name, String password){
        for (User user : userMap.values()) {
            if (user.getName().equals(name)) {
                return user.checkPassword(password);
            }
        }
        return false;
    }

    public Boolean hasDefaultPassword(int userID){
        return ((User)userMap.get(userID)).checkPassword("password");
    }

    public void setPassword(int userID, String password){
        ((User)userMap.get(userID)).setPassword(password);
    }

    public boolean isStudent(int userID) {
        return (userMap.get(userID) instanceof Student);
    }

    public boolean isStaff(int userID) {
        return (userMap.get(userID) instanceof Staff);
    }

    public boolean nameAlreadyExists(String name) {
        for (User user : userMap.values()) {
            if (user.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void save(){
        Serialize.save("userMap.sav", userMap);
    }

    public void load(){
        @SuppressWarnings("unchecked")
        HashMap<Integer, User> loadedMap = (HashMap<Integer, User>)Serialize.load("userMap.sav");
        userMap = loadedMap;
    }
}