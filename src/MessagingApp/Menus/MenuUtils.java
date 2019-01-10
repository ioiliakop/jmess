package MessagingApp.Menus;

import java.util.List;
import java.util.Scanner;

public class MenuUtils {

    /*
     * This method will force execution to stop and wait until the user presses enter. It prompts the
     * user to press enter to continue. It is irrelevant if the user types any text, execution will
     * continue after 'Enter'.
     */
    public static void pauseExecution() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Press Enter to Continue... ");
        sc.nextLine();
    }

    /*
     * This method can be used if a particular operation requires confirmation, it is useful for delete
     * or irreversible operations. It forces that the uses explicitly enters "y/yes" or "n/no", any
     * other input will fail and the confirmation request will be presented again.
     */
    public static boolean requestConfirmation(String message) {
        String  input = "";
        Scanner sc    = new Scanner(System.in);
//        System.out.print(message);

        while (true) {
            System.out.print(message + " (y/n)");
            input = sc.nextLine().toLowerCase();
            if (input.equals("y") || input.equals("yes"))
                return true;
            else if (input.equals("n") || input.equals("no"))
                return false;
        }
    }

    public static String inputUsername() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter username: ");
        return sc.nextLine();
    }

    public static String inputUsername(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.print(message + "Enter username: ");
        return sc.nextLine();
    }

    public static String inputPassword() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter password: ");
        return sc.nextLine();
    }

    public static String inputMessageBody(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter message:");
        String inputMessage = sc.nextLine();
        if (inputMessage.length() > 250) {
            System.out.println("Message is limited to 250 characters. Rest will be skipped. Sorry :/");
            inputMessage = inputMessage.substring(0, 249);
        }
        return inputMessage;
    }

    public static String inputMessageSubject(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter subject:");
        String inputMessage = sc.nextLine();
        if (inputMessage.length() > 100) {
            System.out.println("Subject is limited to 100 characters. Rest will be skipped. Sorry :/");
            inputMessage = inputMessage.substring(0, 99);
        }
        return inputMessage;
    }

    /* Helper method that returns user input from console after printing message in parameter */
    public static String inputGeneric(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.print(message);
        return sc.nextLine();
    }

    /*
     * Helper method that gets a message Id from user input
     * First validates input if it's valid number type, returns 0 if not
     * Then compares it against list of message IDs passed as parameter
     * Returns the message ID if it is contained in the list or 0 otherwise
     */
    public static long getMessageIdInList(List<Long> messageIds) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter messageId: ");
        String input = sc.nextLine();

        long messageId = 0;
        try {
            messageId = Long.parseLong(input);
        } catch (NumberFormatException e) {
            System.out.println("Sorry, not a valid message id.");
        }

        if (messageId != 0) {
            if (!messageIds.contains(messageId)) {
                System.out.println("Selected message not available.");
                messageId = 0;
            }
        }

        return messageId;
    }

}
