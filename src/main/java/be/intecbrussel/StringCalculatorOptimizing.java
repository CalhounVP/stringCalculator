package be.intecbrussel;

import java.util.ArrayList;
import java.util.List;

public class StringCalculatorOptimizing {
    //__________________________________Properties__________________________________
    private List<Integer> negativeNumbers = new ArrayList<>();
    private List<Integer> convertedNumbers = new ArrayList<>();
    private int callCount;

    //____________________________________Methods___________________________________
    public int add(String numbers) {
        callCount++;
        convertedNumbers.clear(); //make sure the list is empty
        negativeNumbers.clear(); //make sure the list is empty
        //empty list return 0
        if (stringEmpty(numbers)) {
            return 0;
        }
        //collect the numbers in an appropriate list
        collectNumbers(numbers);
        //checks if there were negative numbers in this input and throws exception if there were
        hasNegativeNumbers();
        //returning the sum of the made ArrayList of convertedNumbers
        return makeSum();
    }

    private boolean stringEmpty(String numbers) {
        return numbers == null || numbers.equals("");
    }

    private boolean checkNumber(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private boolean checkCustomDelimiter(String numbers) {
        return numbers.startsWith("//");
    }

    private void checkValidNumber(int number) {
        if (number < 0) {
            negativeNumbers.add(number);
        } else if (number < 1000) {
            convertedNumbers.add(number);
        }
    }

    private void collectNumbers(String numbers) {
        if (checkNumber(numbers)) {
            //if string contains one number return said number
            convertedNumbers.add(Integer.parseInt(numbers));
        } else {
            //string wasn't just a number so check for custom delimiter
            if (checkCustomDelimiter(numbers)) {
                numbers = useCustomDelimiter(numbers);
            }
            for (String s : numbers.split("[,\\n]")) {
                if (checkNumber(s.trim())) {
                    //will check if number is bigger than 0 but smaller than 1000 and add to appropriate list
                    checkValidNumber(Integer.parseInt(s.trim()));
                }
            }
        }
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

    private int makeSum() {
        int sum = 0;
        for (Integer i : convertedNumbers) {
            sum += i;
        }
        return sum;
    }

    private String useCustomDelimiter(String numbers) {
        //split at new line
        String[] temp = numbers.split("\\n");
        //index 0 will hold the delimiter info, index 1 will hold the numbers info
        temp[0] = temp[0].replace("//", "").trim();
        //if delimiter string does not contain multiple delimiters
        if (!temp[0].contains("[") || temp[0].indexOf('[') == temp[0].lastIndexOf('[')) {
            //replace the custom delimiter by the ,
            return temp[1].replaceAll(temp[0], ",");
        }
        //only option left is [ ] custom delimiters
        else {
            String[] delimiters = temp[0].split("(?<=})");
            for (String s : delimiters) {
                temp[1] = temp[1].replaceAll(s, ",");
            }
            System.out.println(temp[1]);
            return temp[1];
        }

    }

    public Integer callCount() {
        System.out.println("Add was called " + callCount + " times.");
        return callCount;
    }
}
