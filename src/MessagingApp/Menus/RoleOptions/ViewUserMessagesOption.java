package MessagingApp.Menus.RoleOptions;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.User;
import MessagingApp.Menus.Menu;
import MessagingApp.Menus.MenuOption;
import MessagingApp.Menus.UserOptions.ViewContainerMessagesOption;

import static MessagingApp.Entities.FinalEntities.MessageContainers.INBOX;
import static MessagingApp.Entities.FinalEntities.MessageContainers.SENTBOX;
import static MessagingApp.Entities.FinalEntities.Status.DELETED;
import static MessagingApp.Menus.MenuUtils.inputGeneric;
import static MessagingApp.Menus.MenuUtils.pauseExecution;

public class ViewUserMessagesOption extends MenuOption {

    public ViewUserMessagesOption() {
        this.setMenuLine("View a user's messages");
    }

    @Override
    public void execute() {
        System.out.println("Which user's messages do you want to view?");
        String username = inputGeneric("Enter username:");

        UserDAO usrDAO = new MySQLUserDAO();
        User    user   = usrDAO.getUser(username);

        if (user != null) {
            if (user.getStatusId() != DELETED.ID()) {
                // If user is an active user we create and call a new menu
                // which allows us to view selected user's inbox and sentbox
                Menu viewerOptions = new Menu(user);
                viewerOptions.setMenuTitle("View " + user.getUsername() + "'s messages");
                viewerOptions.setExitPrompt("Back");
                viewerOptions.add(new ViewContainerMessagesOption(user, INBOX));
                viewerOptions.add(new ViewContainerMessagesOption(user, SENTBOX));
                viewerOptions.execute();

            } else System.out.println("Sorry. This user has been deleted");
        } else System.out.println("Sorry. Not a registered user");
        pauseExecution();
    }
}
