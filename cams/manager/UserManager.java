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

    public int registerUser(String email, String name, String password, String faculty, boolean isStaff) {

        User newUser;

        this.uniqueKey = UniqueKey.generateNewKey(this.uniqueKey);
        while(userMap.get(uniqueKey) != null) this.uniqueKey = UniqueKey.generateNewKey(this.uniqueKey);

        if (isStaff) {
            newUser = new Staff(this.uniqueKey, email, name, password, faculty);
        } else {
            newUser = new Student(this.uniqueKey, email, name, password, faculty);
        }
        
        userMap.put(this.uniqueKey, newUser);
        return this.uniqueKey;
    }

    public Boolean isValidUser(String email){
        for (User user : userMap.values()) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public int getUserID(String email) {
        for (User user : userMap.values()) {
            if (user.getEmail().equals(email)) {
                return user.getUserID();
            }
        }
        return -1;
    }

    public String getName(int userID){
        return ((User)userMap.get(userID)).getName();
    }

    public Boolean checkPassword(String email, String password){
        for (User user : userMap.values()) {
            if (user.getEmail().equals(email)) {
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

    public boolean emailAlreadyExists(String email) {
        for (User user : userMap.values()) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public void save(){
        Serialize.save("UserManagerKey.sav", uniqueKey);
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