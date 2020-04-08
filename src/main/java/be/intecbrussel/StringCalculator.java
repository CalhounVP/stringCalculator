package be.intecbrussel;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

    public int add(String numbers) {
        int sum = 0;

        if (!emptyOrNullString(numbers)) {
            for (String split : numbers.split(",")) {
                sum += Integer.parseInt(split.trim());
            }
        }

        return sum;
    }

    public boolean emptyOrNullString(String numbers) {
        return numbers == null || numbers.isEmpty();
    }

}
