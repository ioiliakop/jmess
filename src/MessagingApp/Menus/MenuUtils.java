package MessagingApp.Menus;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

        while (true) {
            System.out.print(message + " (y/n) ");
            input = sc.nextLine().toLowerCase();
            if (input.equals("y") || input.equals("yes"))
                return true;
            else if (input.equals("n") || input.equals("no"))
                return false;
        }
    }

    /*
     * Helper method that returns user input for username
     * while checking character length restrictions
     */
    public static String inputUsername() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        if (username.length() < 2 || username.length() > 20) {
            System.out.println("Password should be at least 2 characters and no more than 20.");
            System.out.println("Please try again.");
            return inputUsername();
        }
        return username;
    }

    public static String inputUsername(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.print(message + "Enter username: ");
        return sc.nextLine();
    }

    /*
     * Helper method that returns user input for password
     * while checking minimum and maximum character length restriction
     */
    public static String inputPassword() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        if (password.length() < 3 || password.length() > 32) {
            System.out.println("Password should be at least 3 characters and no more than 32.");
            System.out.println("Please try again.");
            return inputPassword();
        }
        return password;
    }

    /*
    * Helper method that returns the MD5 hash of the string passed as parameter
    * Used for calculating the MD5 hash of the password
    */
    public static String getMD5Of(String string){
        String myHash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(string.getBytes());
            byte[] digest = md.digest();
            myHash = DatatypeConverter.printHexBinary(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return myHash;
    }

    /* Helper method that returns user input, while checking corresponding character length restriction */
    public static String inputMessageBody() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter message:");
        String inputMessage = sc.nextLine();
        if (inputMessage.length() > 250) {
            System.out.println("Message is limited to 250 characters. Rest will be skipped. Sorry :/");
            inputMessage = inputMessage.substring(0, 249);
        }
        return inputMessage;
    }

    /* Helper method that returns user input, while checking corresponding character length restriction */
    public static String inputMessageSubject() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter subject:");
        String input = sc.nextLine();
        if (input.length() < 1 || input.length() > 100) {
            System.out.println("Subject can't be empty or more than 100 characters.");
            System.out.println("Please try again.");
            return inputMessageSubject();
        }
        return input;
    }

    /* Helper method that returns user input from console after printing message in parameter */
    public static String inputGeneric(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.print(message);
        return sc.nextLine();
    }

}
