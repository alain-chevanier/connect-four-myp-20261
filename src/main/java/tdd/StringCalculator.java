package tdd;

import java.util.Arrays;

public class StringCalculator {

    public int add(String string) {
        if (string == null || string.isEmpty()) {
            return 0;
        }

        // int result = 0;
        // for (String s : string.split(",")) {
        //     result += Integer.parseInt(s);
        // }
        // return result;

        return Arrays.stream(string.split(","))
            .map(s -> Integer.parseInt(s))
            .reduce(0, (a, b) -> a + b);
    }

}
