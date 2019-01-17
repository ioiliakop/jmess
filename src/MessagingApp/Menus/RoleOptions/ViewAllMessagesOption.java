package MessagingApp.Menus.RoleOptions;

import MessagingApp.DAO.MessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLMessageDAO;
import MessagingApp.Entities.Message;
import MessagingApp.Menus.MenuOption;

import java.util.List;

import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.Menus.MessageServices.printMessages;

/*
* Option that gives the ability to view all messages stored in the db
* Even messages deleted from all users' personal folders will appear by this option
* It is in some respect a global log of all messages ever sent within this app
* Only messages specifically deleted by the 'DELETER' role are permanently and irreversibly deleted by the db
*/

public class ViewAllMessagesOption extends MenuOption {

    public ViewAllMessagesOption() {
        super("View all messages");
    }

    @Override
    public void execute() {

        MessageDAO msgDAO = new MySQLMessageDAO();
        List<Message> allMessages = msgDAO.getAllMessages();
        printMessages(allMessages);

        pauseExecution();
    }

}
