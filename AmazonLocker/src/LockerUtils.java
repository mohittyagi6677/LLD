class LockerUtils {
    public static String generateCode() {
        return String.valueOf((int)(Math.random() * 9000 + 1000)); // 4-digit
    }
}