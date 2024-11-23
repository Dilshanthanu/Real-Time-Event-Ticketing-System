public class Banner {
    public static void printBanner() {
        int width = 40; // Total width of the banner

        // Print top border
        printLine('*', width);

        // Print second border
        printLine('*', width);

        // Print empty line with borders
        printEmptyLine('*', width);

        // Print the title centered
        printCenteredLine('*', "Ticketing System", width);

        // Print another empty line with borders
        printEmptyLine('*', width);

        // Print bottom borders
        printLine('*', width);
        printLine('*', width);
    }

    // Print a full line of a given character
    public static void printLine(char ch, int width) {
        for (int i = 0; i < width; i++) {
            System.out.print(ch);
        }
        System.out.println();
    }

    // Print an empty line with borders
    public static void printEmptyLine(char border, int width) {
        System.out.print(border);
        for (int i = 0; i < width - 2; i++) {
            System.out.print(" ");
        }
        System.out.println(border);
    }

    // Print a centered line with a message and borders
    public static void printCenteredLine(char border, String message, int width) {
        int padding = (width - message.length() - 2) / 2;
        System.out.print(border);

        // Print left padding
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }

        // Print the message
        System.out.print(message);

        // Print right padding
        for (int i = 0; i < width - message.length() - padding - 2; i++) {
            System.out.print(" ");
        }

        System.out.println(border);

    }
}
