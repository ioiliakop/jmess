package MessagingApp.Menus.RoleOptions;

import MessagingApp.Entities.User;
import MessagingApp.Menus.Menu;

import static MessagingApp.Entities.Roles.Role.EDITOR;
import static MessagingApp.Entities.Roles.getRoleFromRoleId;

public class RoleOptionsMenu extends Menu {

    public RoleOptionsMenu(User user) {
        super(user);
        this.setMenuLine(getRoleFromRoleId(user.getRoleId()) + " Options");
        this.setExitPrompt("Back");
        this.setMenuTitle(this.getMenuLine());
        this.add(new ViewAllMessagesOption());
        this.add(new ViewMessagesSentByUserOption());
        if (user.getRoleId()>=EDITOR.ID()) this.add(new EditMessagesOption());
        this.add(new RoleUserMessagesOption(user));
    }
}
