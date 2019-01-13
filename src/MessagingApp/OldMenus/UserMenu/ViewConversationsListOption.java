package MessagingApp.OldMenus.UserMenu;

import MessagingApp.Entities.User;
import MessagingApp.OldMenus.MenuOption;

//import static MessagingApp.Menus.Services.getConversingUsers;


public class ViewConversationsListOption extends MenuOption {

    private static final String MENU_LINE = "View list of conversations";
    private User owner;

    public ViewConversationsListOption(int option, User owner) {
        super(option, MENU_LINE);
        this.owner = owner;
    }

    @Override
    public void doAction() {
/*        List<USER> conversingUsersList = getConversingUsers(owner);
        if (!conversingUsersList.isEmpty()){
            System.out.println("You have a conversation with the following users:");
            printUsernamesFromUsers(conversingUsersList);
        } else {
            System.out.println("You have no conversations.");
        }*/
    }


}
