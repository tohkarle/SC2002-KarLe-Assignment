package cams.manager;

import java.util.HashMap;

import cams.model.Staff;
import cams.model.Student;
import cams.model.User;
import cams.util.Serialize;
import cams.util.UniqueKey;

public class UserManager {

    private int uniqueKey = 0;
    protected HashMap<Integer, User> userMap;

    public UserManager(){
        Serialize.checkAndCreateFile("UserManagerKey.sav");
        Serialize.checkAndCreateFile("userMap.sav");
        load();
    }

    public int registerUser(String name, String password, String faculty, boolean isStaff) {
        User newUser;

        int key = UniqueKey.generateNewKey(uniqueKey);
        while(userMap.get(key) != null) key = UniqueKey.generateNewKey(uniqueKey);

        if (isStaff) {
            newUser = new Staff(key, name, password, faculty);
        } else {
            newUser = new Student(key, name, password, faculty);
        }
        
        userMap.put(key, newUser);
        return key;
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
        try {
            this.uniqueKey = (Integer)Serialize.load("UserManagerKey.sav");
            @SuppressWarnings("unchecked")
            HashMap<Integer, User> loadedMap = (HashMap<Integer, User>)Serialize.load("userMap.sav");
            this.userMap = loadedMap;
        } catch (Exception e) {
            this.uniqueKey = 0;
            this.userMap = new HashMap<>();
        }
    }
}