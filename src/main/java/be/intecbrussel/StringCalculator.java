package be.intecbrussel;

import java.util.ArrayList;
import java.util.Arrays;
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
                    System.out.println(temp);
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
        //if custom delimiter is not demanded return standard
        if (!numbers.startsWith("//")) {
            return "[,\\n]";
        }
        //when custom delimiter is demanded return it
        else if (numbers.charAt(2) != '[') {
            return "[" + numbers.charAt(2) + "]";
        }
        //if only one appearance of the [customDelimiter] is present return it
        else if (numbers.indexOf('[') == numbers.lastIndexOf('[')) {
            temp = numbers.substring(numbers.indexOf('['), numbers.indexOf(']')+1);
            numbers = numbers.replace(temp, "");
            return temp.substring(1, temp.length()-2);
        }
            //do {
        //                temp = numbers.substring(numbers.indexOf('['), numbers.indexOf(']')+1);
        //                numbers = numbers.replace(temp, "");
        //                temp = temp.substring(1, temp.length()-2);
        //            } while (numbers.contains("["));
            //temp = numbers.substring(numbers.indexOf('['), numbers.indexOf(']')+1);
            return temp;
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
