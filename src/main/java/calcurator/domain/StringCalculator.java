package calcurator.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    private static final String DETAULF_DELIMITER = ",|:";
    private static final int NUMBER_ZERO = 0;
    public int add(String inputString) {

        if (checkEmptyOrNull(inputString)) {
            return NUMBER_ZERO;
        }

        String customDelimiter = findCustomDelimiter(inputString);

        String[] textNumbers;
        if (customDelimiter == null) {
            textNumbers = inputString.split(DETAULF_DELIMITER);
        }
        if (customDelimiter != null) {
            textNumbers = inputString.split(customDelimiter);
        }

        return getSum(textNumbers);
    }

    private boolean checkEmptyOrNull(String inputString) {
        return (inputString == null || inputString.isEmpty());
    }

    private void validateNegativeNumber(int num) {
        if (num < 0) {
            throw new RuntimeException("문자열 계산기에 음수 입력 불가");
        }
    }

    private String findCustomDelimiter(String inputString) {
        String customDelimiter = null;
        Matcher matcher = Pattern.compile("//(.)\n(.*)").matcher(inputString);
        if (matcher.find()) {
            customDelimiter = matcher.group(1);
            inputString = matcher.group(2);
        }
        return customDelimiter;
    }

    private int getSum(String[] texts) {
        int result = 0;
        for (String text : texts) {
            int num = Integer.parseInt(text);
            validateNegativeNumber(num);
            result += num;
        }
        return result;
    }

}

