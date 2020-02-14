package be.intecbrussel;

import java.util.regex.*;

public class StringCalculator {
    //__________________________________properties__________________________________
    private String input;

    public int add(String input) {
        int sum = 0;
        String delimiter = "";
        if (input == null) {
            return sum;
        }
        else if (input.startsWith("//")){

            for (String s : input.split("[,\n]")) {
                if (Integer.parseInt(s.trim()) < 0) {
                    try {
                        throw new IllegalArgumentException("Negatives not allowed");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    sum += Integer.parseInt(s.trim());
                }
            }
        }
        return sum;
    }

}
