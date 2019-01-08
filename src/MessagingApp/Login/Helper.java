package MessagingApp.Login;

import MessagingApp.DAO.MySQLDAO.MySQLRoleDAO;
import MessagingApp.DAO.RoleDAO;
import MessagingApp.Entities.Role;
import MessagingApp.Entities.User;

public class Helper {

    public static boolean isAdmin(User user){
        RoleDAO rlDAO = new MySQLRoleDAO();
        Role role = rlDAO.getRole("admin");
        if(user.getRoleId()==role.getId()) return true;
        return false;
    }
}
