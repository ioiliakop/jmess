package MessagingApp.Menus.UserOptions;

import MessagingApp.DAO.MessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLMessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLUserFolderMessageDAO;
import MessagingApp.DAO.UserFolderMessageDAO;
import MessagingApp.Entities.MessageFolders.Folder;
import MessagingApp.Entities.Message;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MenuOption;

import java.util.List;

import static MessagingApp.Menus.MessageServices.getMessageIdInList;
import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.Menus.MenuUtils.requestConfirmation;
import static MessagingApp.Menus.MessageServices.getMessageString;
import static MessagingApp.Menus.MessageServices.getMessagesFromMessageIds;
import static MessagingApp.Menus.MessageServices.printMessages;


/* User option that prints all user messages in the container (Inbox/Sentbox etc.) passed as parameter */
public class ViewContainerMessagesOption extends MenuOption {

    private Folder container;

    public ViewContainerMessagesOption(User user, Folder container) {
        super(user);
        this.container = container;
        this.setMenuLine("View messages in " + container.name());
    }

    @Override
    public void execute() {
        long                 ownerId        = this.getUser().getId();
        UserFolderMessageDAO ucmDAO         = new MySQLUserFolderMessageDAO();
        List<Long>           messageIdsList = ucmDAO.getUserFolderMessages(ownerId, container);

        if (!messageIdsList.isEmpty()) {
            List<Message> containerMessages = getMessagesFromMessageIds(messageIdsList);
            printMessages(containerMessages);

            // We ask the user if he wishes to view a message from the container
            // And proceed accordingly
            if (requestConfirmation("Would you like to open a message from the above list?")) {
                long selectedMessageId;
                do {
                    selectedMessageId = getMessageIdInList(messageIdsList);
                    if (selectedMessageId !=0){
                        MessageDAO msgDAO = new MySQLMessageDAO();
                        Message selectedMessage = msgDAO.getMessage(selectedMessageId);
                        System.out.println(getMessageString(selectedMessage));
                    }
                } while (requestConfirmation("Would you like to view another message?"));
            }

        } else System.out.println("No messages in " + container);

        pauseExecution();
    }

}
