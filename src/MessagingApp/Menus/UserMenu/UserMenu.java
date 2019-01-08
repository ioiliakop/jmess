package MessagingApp.Menus.UserMenu;

import MessagingApp.Entities.User;
import MessagingApp.Menus.Menu;

public class UserMenu {

    private User owner;

    public UserMenu(User owner) {
        this.owner = owner;
    }

    public void userMenuExecute(){
        Menu userMenu = new Menu(owner);

        userMenu.add(new SendMessageOption(1,owner));

        userMenu.add(new ViewInboxOption(2,owner));

        userMenu.add(new ViewSentboxOption(3,owner));

/*        userMenu.add(new ViewAllUserMessagesOption(2,owner));

        userMenu.add(new ViewConversationsListOption(3,owner));

        userMenu.add(new ViewConversationMessagesOption(4,owner));*/

        userMenu.run();



    }
}
