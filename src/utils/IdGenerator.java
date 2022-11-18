package utils;

public class IdGenerator {

    private static int ids;

    public static int getIds() {
        return ids;
    }

    public static void increaseIds(){
        ids++;
    }

}
