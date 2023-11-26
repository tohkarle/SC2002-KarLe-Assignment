package cams.utils;

/**
 * Utility class that generates a unique key for an object
 */
public class UniqueKey {
    /**
     * Generate a new unique key
     * @param uniqueKey int of the current unique key
     * @return int of the new unique key, incremented by 1
     */
    public static int generateNewKey(int uniqueKey){
        return uniqueKey = (uniqueKey + 1) % 10000009;
    }
}
