package utils;

import model.MinMax;

public class StringUtils {
    public static int indexOfNextDigit(String string, int startIndex) {
        if (string == null) {
            return -1;
        }
        for (int i = startIndex; i < string.length(); i++) {
            if (Character.isDigit(string.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    public static int nextNumber(String string, int startIndex) {
        if (string == null) {
            return -1;
        }

        int startIndexNumber = indexOfNextDigit(string, startIndex);
        if (startIndexNumber == -1) {
            return -1;
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = startIndexNumber; i < string.length(); i++) {
            if (Character.isDigit(string.charAt(i))) {
                stringBuilder.append(string.charAt(i));
            } else {
                break;
            }
        }

        return Integer.parseInt(stringBuilder.toString());
    }

    // text has type <value> or <value - value>
    public static MinMax getMinMax(String text) {
//    System.out.println("GetMinMax: " + text);
        try {
            int value = Integer.parseInt(text);
            return new MinMax(value, value);
        } catch (Exception e) {
            Integer minNumber = nextNumber(text, 0);
            Integer maxNumber = nextNumber(text, text.indexOf(minNumber) + minNumber.toString().length() + 1);
            return new MinMax(minNumber, maxNumber);
        }
    }
}
