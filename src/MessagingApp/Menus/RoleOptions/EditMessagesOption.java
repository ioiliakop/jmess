package MessagingApp.Menus.RoleOptions;

import MessagingApp.DAO.MessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLMessageDAO;
import MessagingApp.Entities.Message;
import MessagingApp.Menus.MenuOption;

import java.util.List;

import static MessagingApp.Menus.MenuUtils.*;
import static MessagingApp.Menus.MessageServices.getMessageIdInList;
import static MessagingApp.Menus.MessageServices.getMessageIdsFromMessages;
import static MessagingApp.Menus.MessageServices.getMessageString;
import static MessagingApp.Menus.RoleOptions.RoleHelper.editMessageInList;

public class EditMessagesOption extends MenuOption {

    public EditMessagesOption() {
        super("Edit a message");
    }

    @Override
    public void execute() {
        // We get a list of all messages and message IDs
        MessageDAO    msgDAO        = new MySQLMessageDAO();
        List<Message> allMessages   = msgDAO.getAllMessages();

        editMessageInList(allMessages);

        pauseExecution();
    }
}
