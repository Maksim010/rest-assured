package reqresTests.utils;

public class RandomNumber {

    public static String randomNumber() {
        int digit =  Math.abs((int) (Math.random() * 100));
        return String.valueOf(digit);
    }
}
