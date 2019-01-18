package MessagingApp;

public class Main {

    public static void main(String[] args) {

        try {
            LoginScreen.printLoginScreen();
        } catch (MessagingAppException e) {
            e.printStackTrace();
        }

    }

}
