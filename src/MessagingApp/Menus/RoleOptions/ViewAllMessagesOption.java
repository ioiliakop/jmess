package MessagingApp.Menus.RoleOptions;

import MessagingApp.DAO.MessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLMessageDAO;
import MessagingApp.Entities.Message;
import MessagingApp.Menus.MenuOption;

import java.util.List;

import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.Menus.MessageServices.printMessages;

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
