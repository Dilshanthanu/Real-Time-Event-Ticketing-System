public class Logger {

    public static void info(String message) {
        System.out.println("[Log]-[INFO]-----"+message);
    }

    public static void error(String message) {
        System.out.println("[Log]-[ERROR]-----"+message);
    }

    public static void warn(String message) {
        System.out.println("[Log]-[WARN]-----"+message);
    }
}
