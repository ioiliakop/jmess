package MessagingApp;

import MessagingApp.DBConnection.MySQLConnection;
import MessagingApp.Login.LoginScreen;
import MessagingApp.Menus.AdminMenu.AdminMenu;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

/*        try (Connection conn = MySQLConnection.getConnection()) {

            // print out a message
            System.out.println(String.format("Connected to database %s "
                    + "successfully.", conn.getCatalog()));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/

        LoginScreen.printLoginScreen();

/*        AdminMenu adMenu = new AdminMenu();
        adMenu.adminMenuExecute();*/

    }
}
