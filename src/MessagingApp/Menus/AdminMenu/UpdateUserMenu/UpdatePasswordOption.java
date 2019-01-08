package MessagingApp.Menus.AdminMenu.UpdateUserMenu;

import MessagingApp.Entities.User;

public class UpdatePasswordOption extends UpdateUserMenuOption {

    private static final String UPDATE_PASSWORD_MENU_LINE = "Update password";

    public UpdatePasswordOption(int option, User targetUser) {
        super(option, targetUser);
        this.setMenuLine(UPDATE_PASSWORD_MENU_LINE);
    }

    @Override // Incomplete
    public void doAction() {

    }

}

