package MessagingApp.Menus.EditorMenu;

import MessagingApp.Entities.User;
import MessagingApp.Menus.CommonOptions.ViewAllUsersOption;
import MessagingApp.Menus.Menu;
import MessagingApp.Menus.ViewerMenu.ViewUserMessagesOption;

public class EditorMenu {

    private User user;

    public EditorMenu(User user) {
        this.user = user;
    }

    public void editorMenuExecute() {
        Menu editorMenu = new Menu(user);

        editorMenu.add(new ViewAllUsersOption(1));

        editorMenu.add(new ViewUserMessagesOption(2));

        editorMenu.add(new EditUserMessagesOption(3));

        editorMenu.run();
    }
}
