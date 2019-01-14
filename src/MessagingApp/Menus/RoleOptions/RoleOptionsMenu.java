package MessagingApp.Menus.RoleOptions;

import MessagingApp.Entities.User;
import MessagingApp.Menus.Menu;

import static MessagingApp.Entities.FinalEntities.Roles.VIEWER;
import static MessagingApp.Entities.FinalEntities.getRoleFromRoleId;

public class RoleOptionsMenu extends Menu {

    public RoleOptionsMenu(User user) {
        super(user);
        this.setMenuLine(getRoleFromRoleId(user.getRoleId()) + " Options");
        this.setMenuTitle(this.getMenuLine());
        if (user.getRoleId()==VIEWER.ID()) this.add(new ViewUserMessagesOption());
    }
}
