package MessagingApp.Menus.RoleOptions;

import MessagingApp.Entities.User;
import MessagingApp.Menus.Menu;

import static MessagingApp.Entities.Roles.Role.DELETER;
import static MessagingApp.Entities.Roles.Role.EDITOR;
import static MessagingApp.Entities.Roles.getRoleFromRoleId;

public class RoleOptionsMenu extends Menu {

    public RoleOptionsMenu(User roleOwner) {
        super(roleOwner);
        this.setMenuLine(getRoleFromRoleId(roleOwner.getRoleId()) + " Options");
        this.setExitPrompt("Back");
        this.setMenuTitle(this.getMenuLine());
        this.add(new ViewAllMessagesOption());
        if (roleOwner.getRoleId()>=EDITOR.ID()) this.add(new EditMessageOption());
        if (roleOwner.getRoleId()>= DELETER.ID()) this.add(new DeleteMessageOption());
//        if (roleOwner.getRoleId()>= DELETER.ID()) this.add(new DeleteMessagesMenu());
        this.add(new RoleMessagesSentByUserOption(roleOwner));
        this.add(new RoleMessagesSentToUserOption(roleOwner));
        this.add(new RoleMessagesOfUserOption(roleOwner));
//        this.add(new RoleUserMessagesOption(roleOwner));
    }
}
