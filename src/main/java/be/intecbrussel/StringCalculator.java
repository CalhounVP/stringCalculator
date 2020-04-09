package be.intecbrussel;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

    private List<Integer> negativeNumbers = new ArrayList<>();
    private int callCount;

    public int add(String numbers) {
        int sum = 0;
        negativeNumbers.clear();
        String delimiter = "[,\\n]";

        callCount++;

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

        try {
            if (negativeNumbers.size() > 0) {
                throw new Throwable("negatives not allowed \n" + negativeNumbers.toString());
            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
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
        int toCheck = Integer.parseInt(split.trim());
        try {
            if ( toCheck < 0) {
                throw new Throwable();
            }
        } catch (Throwable t) {
            negativeNumbers.add(toCheck);
        }
    }

    public int callCount() {
        return callCount;
    }
}
