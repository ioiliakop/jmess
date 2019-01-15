package MessagingApp.OldMenus.AdminMenu.UpdateUserMenu;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.Roles;
import MessagingApp.Entities.User;

import java.util.ArrayList;
import java.util.List;

import static MessagingApp.Entities.Roles.getRoleFromRoleId;
import static MessagingApp.Menus.MenuUtils.*;


public class UpdateUserRoleOption extends UpdateUserMenuOption {

    private static final String UPDATE_ROLE_MENU_LINE = "Update role";

    public UpdateUserRoleOption(int option, User targetUser) {
        super(option, targetUser);
        this.setMenuLine(UPDATE_ROLE_MENU_LINE);
    }

    @Override // Incomplete
    public void doAction() {

        System.out.println("Available roles are:");
//        System.out.print(rlDAO.getAllRoles());
        List<String> rolesIndexing = new ArrayList<>();
        for (Roles.Role role : Roles.Role.values()) {
            System.out.println(role.ID()+ " - " + role);
            rolesIndexing.add(String.valueOf(role.ID()));
        }
        String newRoleInput = inputGeneric("Select new role: (1-5)\n");
        if (rolesIndexing.contains(newRoleInput)){
            /*
            * newRoleInput String is confirmed to be equal to a role id/index
            * so we don't need to try/catch a NumberFormatException here, when we parse string to Int
            * */
            int        newRoleId = Integer.parseInt(newRoleInput);
            Roles.Role newRole   = getRoleFromRoleId(newRoleId);

            UserDAO usrDAO = new MySQLUserDAO();
            User user = this.getTargetUser();
            usrDAO.updateUserNameRole(user.getUsername(), newRole , user.getId());
            System.out.println("User '"+ user.getUsername() + "' role updated to " + newRole);
        } else System.out.println("false");


//        EnumSet<Role> roleSet = EnumSet.of(Role.values());


/*        if (roleExists(newRoleName)) {

            User user = this.getTargetUser();

            if (requestConfirmation("Roles will be changed from " + getRoleNameById(user.getRoleId()) + " to " + newRoleName + ".")) {
                UserDAO usrDAO = new MySQLUserDAO();
                usrDAO.updateUser(user.getUsername(), user.getPassword(), getRoleFromRoleId(user.getRoleId()) , user.getId());
                System.out.println("USER updated.");
            }
        } else System.out.println("Sorry, unknown role.");
        pauseExecution();*/
    }
}
