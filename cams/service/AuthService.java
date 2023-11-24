package cams.service;

import java.util.HashMap;

import cams.model.Staff;
import cams.model.Student;
import cams.model.User;
import cams.utils.Serialize;

/**
 * AuthService is a service class that handles the authentication of users
 * It is responsible for user registration, login, and maintaining all User save data.
 */
// Create user
// Log user in
// Return userID once logged in
// Log user out
public class AuthService {

    private HashMap<String, User> userMap;

    /**
     * Initialize the AuthService and load the userMap save data
     */
    public AuthService() {
        Serialize.checkAndCreateFile("userMap.sav");
        this.load();
    }

    /**
     * Register a new user
     * @param email The email of the new user
     * @param name The name of the new user
     * @param password The password of the new user
     * @param faculty The faculty of the new user
     * @param isStaff boolean of whether the new user is registering as a Staff
     * @return The newly registered User object
     */
    public User registerUser(String email, String name, String password, String faculty, boolean isStaff) {
        User newUser;

        if (isStaff) {
            newUser = new Staff(name, email, password, faculty);
        } else {
            newUser = new Student(name, email, password, faculty);
        }
        
        userMap.put(name, newUser);
        return newUser;
    }

    /**
     * Get a User object from the userMap
     * @param userName The name of the user
     * @return The User object
     */
    public User getUser(String userName) {
        return new User(userMap.get(userName));
    }

    /**
     * Check if a user is a valid user
     * @param email The email of the user
     * @return boolean of whether the user is a valid user
     */
    public Boolean isValidUser(String email){
        for (User user : userMap.values()) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Log a user in
     * @param email The email of the user
     * @return The User object of the logged in user
     */
    public User logIn(String email) {
        for (User user : userMap.values()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Check if a password matches the user's password
     * @param email The email of the user
     * @param password The password to check
     * @return Boolean of whether the password matches the user's password
     */
    public Boolean checkPassword(String email, String password){
        for (User user : userMap.values()) {
            if (user.getEmail().equals(email)) {
                return user.passwordMatches(password);
            }
        }
        return false;
    }

    /**
     * Check if an email already exists
     * @param email The email to check
     * @return boolean of whether the email already exists
     */
    public boolean emailAlreadyExists(String email) {
        for (User user : userMap.values()) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if a name already exists
     * @param name The name to check
     * @return boolean of whether the name already exists
     */
    public boolean nameAlreadyExists(String name) {
        for (User user : userMap.values()) {
            if (user.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Update a user's password
     * @param userName The name of the user
     * @param newPassword The new password
     */
    public void updatePassword(String userName, String newPassword) {
        userMap.get(userName).setPassword(newPassword);
        save();
    }

    /**
     * Save the userMap to the save file containing all User save data
     */
    public void save() {
        Serialize.save("userMap.sav", userMap);
    }

    /**
     * Load the userMap from the save file containing all User save data
     */
    public void load() {
        try {
            @SuppressWarnings("unchecked")
            HashMap<String, User> loadedMap = (HashMap<String, User>) Serialize.load("userMap.sav");
            if (loadedMap != null) {
                userMap = loadedMap;
            } else {
                userMap = new HashMap<>();
            }
            // userMap = loadedMap;
        } catch (Exception e) {
            userMap = new HashMap<>();
        }
    }
}
