package MessagingApp.Menus;

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
        System.out.println(message);

        while (true) {
            System.out.print("Continue with the operation? (y/n)");
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

    public static String inputGeneric(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.print(message);
        return sc.nextLine();
    }

}
