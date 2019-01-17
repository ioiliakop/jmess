package MessagingApp.Menus.RoleOptions;

import MessagingApp.Entities.User;
import MessagingApp.Menus.Menu;

import static MessagingApp.Entities.Roles.Role.DELETER;
import static MessagingApp.Entities.Roles.Role.EDITOR;
import static MessagingApp.Entities.Roles.getRoleFromRoleId;

/**
 * This is a submenu that will appear for viewer/editor/deleter roles
 * All these roles extra options are included here
 */
public class RoleOptionsMenu extends Menu {

    public RoleOptionsMenu(User roleOwner) {
        super(roleOwner);
        this.setMenuLine(getRoleFromRoleId(roleOwner.getRoleId()) + " Options");
        this.setExitPrompt("Back");
        this.setMenuTitle(this.getMenuLine());

        // Lowest role viewer. Above roles (editor, viewer) have this option
        this.add(new ViewAllMessagesOption());
        // Both editor and deleter roles have this option
        if (roleOwner.getRoleId() == EDITOR.ID() || roleOwner.getRoleId() == DELETER.ID())
            this.add(new EditMessageOption());
        // only deleter role has this option
        if (roleOwner.getRoleId() == DELETER.ID()) this.add(new DeleteMessageOption());

        // the following role options adjust functionality based on role
        // so they are common for viewer, editor and deleter classes
        // but offer functionality dependent on user role
        this.add(new RoleMessagesSentByUserOption(roleOwner));
        this.add(new RoleMessagesSentToUserOption(roleOwner));
        this.add(new RoleMessagesOfUserOption(roleOwner));
    }
}
