package MessagingApp.Menus.AdminMenu.UpdateUserMenu;


import MessagingApp.Entities.User;
import MessagingApp.Menus.Menu;

public class UpdateUserMenu {

    private String titleTemplate = "------  Update %s  ------";

    private User targetUser;

    public UpdateUserMenu(User targetUser) {
        this.targetUser = targetUser;
    }

    public void run() {

        Menu updUser = new Menu(targetUser);

        updUser.setTitle(String.format(titleTemplate, targetUser.getUsername()));

        updUser.add(new UpdateUsernameOption(1, targetUser));

        updUser.add(new UpdatePasswordOption(2, targetUser));

        updUser.add(new UpdateUserRoleOption(3, targetUser));

        updUser.run();
    }
}
