package MessagingApp.OldMenus.ViewerMenu;

import MessagingApp.Entities.User;
import MessagingApp.OldMenus.CommonOptions.ViewAllUsersOption;
import MessagingApp.OldMenus.Menu;

public class ViewerMenu {
    private User user;

    public ViewerMenu(User user) {
        this.user = user;
    }

    public void viewerMenuExecute() {
        Menu viewerMenu = new Menu(user);

        viewerMenu.add(new ViewAllUsersOption(1));

        viewerMenu.add(new ViewUserMessagesOption(2));

        viewerMenu.run();
    }
}
