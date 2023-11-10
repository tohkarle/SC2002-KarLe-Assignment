package cams.util;

public class UniqueKey {
    public static int generateNewKey(int uniqueKey){
        return uniqueKey = (uniqueKey + 1) % 10000009;
    }
}
