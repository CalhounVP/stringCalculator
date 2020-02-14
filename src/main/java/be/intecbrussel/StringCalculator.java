package be.intecbrussel;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    //__________________________________Properties__________________________________
    private List<Integer> negativeNumbers = new ArrayList<>();
    private int callCount;

    //____________________________________Methods___________________________________
    public int add(String numbers) {
        callCount++;
        int sum = 0;
        String temp;
        negativeNumbers.clear(); //clear for every new String
        if (numbers == null || numbers.equals("")) {
            return 0;
        } else {
            for (String s : numbers.split(this.getDelimiter(numbers))) {
                temp = s.replace(']',' ');
                if (isNumberValue(temp.trim())) {
                    if (Integer.parseInt(temp.trim()) < 0) {
                        negativeNumbers.add(Integer.parseInt(temp.trim()));
                    } else if (Integer.parseInt(temp.trim()) <= 1000) {
                        sum += Integer.parseInt(temp.trim());
                    }
                }
            }
            hasNegativeNumbers();
        }
        return sum;
    }

    private boolean isNumberValue(String numbers) {
        try {
            Integer.parseInt(numbers);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private String getDelimiter(String numbers) {
        String temp = "";
        if (!numbers.startsWith("//")) {
            return "[,\n]";
        } else if (numbers.charAt(2) != '[') {
            return "[,\n" + numbers.charAt(2) + "]";
        } else {
            temp = numbers.substring(numbers.indexOf('['), numbers.indexOf(']')+1);
            return temp;
        }
    }

    private boolean hasNegativeNumbers() {
        try {
            if (negativeNumbers.isEmpty()) {
                return true;
            } else {
                throw new IllegalArgumentException("Negatives not allowed");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println(negativeNumbers.toString());
        }
        return false;
    }

    public Integer callCount() {
        System.out.println("Add was called " + callCount + " times.");
        return callCount;
    }
}
