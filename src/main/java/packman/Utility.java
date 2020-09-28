package packman;

public final class Utility {
    public static String capitalizedFirstCharOfString(String string) {
        return string.trim().substring(0, 1).toUpperCase() +
                string.trim().substring(1).toLowerCase();
    }

    public static String removeAllNumeric(String string) {
        return string.replaceAll("[\\d]", "");
    }

    public static String removeAllNonNumeric(String string) {
        return string.replaceAll("[\\D]", "");
    }
}
