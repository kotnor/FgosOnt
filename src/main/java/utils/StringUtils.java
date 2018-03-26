package utils;

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
}
