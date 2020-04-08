package be.intecbrussel;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

    public int add(String numbers) {
        int sum = 0;
        String delimiter = "[,\\n]";

        if (!emptyOrNullString(numbers)) {
            if (numbers.startsWith("//")) {
                String[] splitInput = numbers.split("\\n");
                delimiter = settingCustomDelimiters(splitInput[0]);
                numbers = splitInput[1];
            }

            for (String split : numbers.split(delimiter)) {
                checkNumberValidity(split);
                sum += Integer.parseInt(split.trim());
            }
        }

        return sum;
    }

    public boolean emptyOrNullString(String numbers) {
        return numbers == null || numbers.isEmpty();
    }

    public String settingCustomDelimiters (String delimiters) {
        return delimiters.replaceFirst("//", "");
    }

    public void checkNumberValidity (String split) {
        try {
            if (Integer.parseInt(split.trim()) < 0) {
                throw new Throwable("negatives not allowed");
            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

}
