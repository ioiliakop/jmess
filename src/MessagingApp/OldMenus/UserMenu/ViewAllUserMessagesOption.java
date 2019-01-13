package MessagingApp.OldMenus.UserMenu;

import MessagingApp.Entities.User;
import MessagingApp.OldMenus.MenuOption;


public class ViewAllUserMessagesOption extends MenuOption {

    private static final String MENU_LINE = "View all messages";
    private              User   owner;

    public ViewAllUserMessagesOption(int option, User owner) {
        super(option, MENU_LINE);
        this.owner = owner;
    }

    public void doAction() {
/*        List<Message> userMessages = getUserMessages(owner);
        if (!userMessages.isEmpty()) {
            System.out.println(userMessages);
        } else System.out.println("You have no messages... :(\nDon't feel lonely, start chatting!! :D");

        pauseExecution();*/
    }



}
