package MessagingApp.Menus.AdminMenu.UpdateUserMenu;

import MessagingApp.DAO.MySQLDAO.MySQLRoleDAO;
import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.RoleDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.Constants;
import MessagingApp.Entities.Constants.Roles;
import MessagingApp.Entities.Role;
import MessagingApp.Entities.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import static MessagingApp.Entities.Constants.getUserRoleFromRoleId;
import static MessagingApp.Menus.MenuUtils.*;
import static MessagingApp.Menus.Services.getRoleNameById;
import static MessagingApp.Menus.Services.roleExists;

public class UpdateUserRoleOption extends UpdateUserMenuOption {

    private static final String UPDATE_ROLE_MENU_LINE = "Update role";

    public UpdateUserRoleOption(int option, User targetUser) {
        super(option, targetUser);
        this.setMenuLine(UPDATE_ROLE_MENU_LINE);
    }

    @Override // Incomplete
    public void doAction() {

//        RoleDAO rlDAO = new MySQLRoleDAO();
        System.out.println("Available roles are:");
//        System.out.print(rlDAO.getAllRoles());
        List<String> rolesIndexing = new ArrayList<>();
        for (Roles role : Roles.values()) {
            System.out.println(role.ID()+ " - " + role);
            rolesIndexing.add(String.valueOf(role.ID()));
        }
        String newRoleInput = inputGeneric("Select new role: (1-5)\n");
        if (rolesIndexing.contains(newRoleInput)){
            /*
            * newRoleInput String is confirmed to be equal to a role id/index
            * so we don't need to try/catch a NumberFormatException here, when we parse string to Int
            * */
            int newRoleId = Integer.parseInt(newRoleInput);
            Roles newRole = getUserRoleFromRoleId(newRoleId);

            UserDAO usrDAO = new MySQLUserDAO();
            User user = this.getTargetUser();
            usrDAO.updateUserNameRole(user.getUsername(), newRole , user.getId());
            System.out.println("User '"+ user.getUsername() + "' role updated to " + newRole);
        } else System.out.println("false");


//        EnumSet<Roles> roleSet = EnumSet.of(Roles.values());


/*        if (roleExists(newRoleName)) {

            User user = this.getTargetUser();

            if (requestConfirmation("Role will be changed from " + getRoleNameById(user.getRoleId()) + " to " + newRoleName + ".")) {
                UserDAO usrDAO = new MySQLUserDAO();
                usrDAO.updateUser(user.getUsername(), user.getPassword(), getUserRoleFromRoleId(user.getRoleId()) , user.getId());
                System.out.println("USER updated.");
            }
        } else System.out.println("Sorry, unknown role.");
        pauseExecution();*/
    }
}
