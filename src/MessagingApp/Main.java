package MessagingApp;


import static MessagingApp.Menus.MenuUtils.getMD5OfString;

public class Main {

    public static void main(String[] args) {

        String teststr = "admin";
        System.out.println(getMD5OfString(teststr));


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

        System.out.println(FinalEntities.Tests.TEST1.);*/

        //enum tet

/*        if ("USER".equals(FinalEntities.Roles.USER.name())) {
            System.out.println(FinalEntities.Roles.USER.ID());
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
