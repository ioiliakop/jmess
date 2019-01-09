package MessagingApp;

import MessagingApp.Entities.Constants;
import MessagingApp.Login.LoginScreen;

public class Main {

    public static void main(String[] args) {

/*        public enum Tests{
            TEST1(1),
            TEST2(2);

            private int numVal;

            Tests(int numVal) {
                this.numVal = numVal;
            }

            public int getNumVal() {
                return numVal;
            }
        }

        System.out.println(Constants.Tests.TEST1.);*/

        //enum tet

/*        if ("USER".equals(Constants.Roles.USER.name())) {
            System.out.println(Constants.Roles.USER.ID());
        }*/


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
