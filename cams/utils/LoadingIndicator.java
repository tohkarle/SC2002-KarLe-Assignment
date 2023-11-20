package cams.utils;

import java.io.IOException;

public class LoadingIndicator {

    private static final int DELAY_MILLISECONDS = 666;

    public static void logInLoadingIndicator() {
        System.out.println("\nLogging in...");
        delay();
        System.out.println("Log in successful!");
        delay();
    }

    public static void logOutLoadingIndicator() {
        System.out.println("\nLogging out...");
        delay();
        System.out.println("Log out successful!");
        delay();
    }

    public static void createLoadingIndicator(String title) {
        System.out.println("\nCreating " + title + "...");
        delay();
        System.out.println("Create " + title + " successful!");
        delay();
    }

    public static void deleteLoadingIndicator(String title) {
        System.out.println("\nDeleting " + title + "...");
        delay();
        System.out.println("Delete " + title + " successful!");
        delay();
    }

    public static void registerLoadingIndicator(String title) {
        System.out.println("\nRegistering for " + title + "...");
        delay();
        System.out.println("Register for " + title + " successful!");
        delay();
    }

    public static void updateLoadingIndicator(String title) {
        System.out.println("\nUpdating " + title + "...");
        delay();
        System.out.println("Update " + title + " successful!");
        delay();
    }

    public static void terminateAppLoadingIndicator() {
        System.out.println("\nTerminating app...");
        delay();
        System.out.println("Terminate app successful!");
        delay();
    }

    public static void withdrawLoadingIndicator(String title) {
        System.out.println("\nWithdrawing from " + title + "...");
        delay();
        System.out.println("Withdraw from " + title + " successful!");
        delay();
    }

    public static void editingLoadingIndicator(String title) {
        System.out.println("\nEditing " + title + "...");
        delay();
        System.out.println("Edit " + title + " successful!");
        delay();
    }

    public static void submitLoadingIndicator(String title) {
        System.out.println("\nSubmitting " + title + "...");
        delay();
        System.out.println("Submit " + title + " successful!");
        delay();
    }

    public static void processLoadingIndicator(String title) {
        System.out.println("\nProcessing " + title + "...");
        delay();
        System.out.println("Process " + title + " successful!");
        delay();
    }

    private static void delay() {
        try {
            while (System.in.available() > 0) {
                System.in.read();
            }
            Thread.sleep(DELAY_MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

