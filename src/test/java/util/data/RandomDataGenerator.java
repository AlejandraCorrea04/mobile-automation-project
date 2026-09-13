package util.data;

import java.util.UUID;

public class RandomDataGenerator {

    public static String generateRandomEmail(){
        String uniqueId = UUID.randomUUID().toString().substring(0,8);
        return "wdio.test."+uniqueId+"@mailinator.com";
    }

    public static String defaultPassword(){
        return "Test1234!";
    }
}
