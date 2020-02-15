package be.intecbrussel;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {
    //__________________________________Properties__________________________________
    private List<Integer> negativeNumbers = new ArrayList<>();
    private int callCount;

    //____________________________________Methods___________________________________
    public int add(String numbers) {
        callCount++;
        int sum = 0;
        negativeNumbers.clear(); //clear for every new String
        if (numbers == null || numbers.equals("")) {
            return 0;
        } else {
            numbers = getSplitReadyString(numbers);
            for (String s : numbers.split(" ")) {
                if (isNumberValue(s.trim())) {
                    System.out.println(s);
                    if (Integer.parseInt(s.trim()) < 0) {
                        negativeNumbers.add(Integer.parseInt(s.trim().trim()));
                    } else if (Integer.parseInt(s.trim()) <= 1000) {
                        sum += Integer.parseInt(s.trim());
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

    private String replaceDelimiters(String numbers, ArrayList<String> delimiters) {
        numbers = numbers.replace("//", "");
        String temp = "";
        for (String s : delimiters) {
            if (s.length() > 1) {
                //split the multi symbol delimiters in single character strings
                String[] tempArray = s.split("");
                for (String t : tempArray) {
                    //put every character with a backslash in front of it to filter
                    temp += String.format("\\%s", t);
                }
                s = temp;
            } else {
                //TODO single metacharacter characters
            }
            numbers = numbers.replaceAll(String.format("\\%s", s), " ");
        }
        System.out.println("test " + numbers);
        return numbers;
    }

    private String getSplitReadyString(String numbers) {
        ArrayList<String> delimiters = new ArrayList<>();
        String tempNumbers = numbers;
        String tempDelimiter;
        //if custom delimiter is not demanded add standard delimiter regex
        if (!numbers.startsWith("//")) {
            delimiters.add(",");
            delimiters.add("n");
        }
        //when custom delimiter is demanded add the single character delimiter
        else if (numbers.charAt(2) != '[') {
            delimiters.add(String.format("%c", numbers.charAt(2)));
            tempNumbers = tempNumbers.replaceFirst(String.format("%c", numbers.charAt(2)), "");
        }
        //if the [customDelimiter] is present add it as long as there is one
        else {
            do {
                //get the first [delimiter] in the string
                tempDelimiter = tempNumbers.substring(tempNumbers.indexOf('['), tempNumbers.indexOf(']') + 1);
                //remove the [delimiter]
                tempNumbers = tempNumbers.replace(tempDelimiter, "");
                //add the delimiter without the [ ] to the ArrayList
                delimiters.add(String.format("%s%s",
                        tempDelimiter.length()==3?"\\":"",
                        tempDelimiter.substring(1, tempDelimiter.length() - 2)));
            } while (tempNumbers.contains("["));
        }
        return replaceDelimiters(tempNumbers, delimiters);
    }

    private void hasNegativeNumbers() {
        try {
            if (!negativeNumbers.isEmpty()) {
                throw new IllegalArgumentException("Negatives not allowed");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println(negativeNumbers.toString());
        }
    }

    public Integer callCount() {
        System.out.println("Add was called " + callCount + " times.");
        return callCount;
    }
}
