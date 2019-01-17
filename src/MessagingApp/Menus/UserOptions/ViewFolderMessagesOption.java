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

import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.Menus.MenuUtils.requestConfirmation;
import static MessagingApp.Menus.MessageServices.*;


/* User option that prints all user messages in the folder (INBOX/SENTBOX etc.) passed as parameter */
public class ViewFolderMessagesOption extends MenuOption {

    private Folder folder;

    public ViewFolderMessagesOption(User user, Folder folder) {
        super(user);
        this.folder = folder;
        this.setMenuLine("View messages in " + folder.name());
    }

    @Override
    public void execute() {
        User owner = this.getUser();

//        long                 ownerId        = this.getUser().getId();
        UserFolderMessageDAO ufmDAO         = new MySQLUserFolderMessageDAO();
        List<Long>           messageIdsList = ufmDAO.getUserFolderMessageIDs(owner.getId(), folder);

        if (!messageIdsList.isEmpty()) {
            List<Message> containerMessages = getMessagesFromMessageIds(messageIdsList);
            printMessages(containerMessages);

            // We ask the user if he wishes to view a message from the folder
            // And proceed accordingly
            if (requestConfirmation("Would you like to open a message from the above list?")) {
                long selectedMessageId;
                do {
                    selectedMessageId = getMessageIdInList(messageIdsList);
                    if (selectedMessageId !=0){
                        MessageDAO msgDAO = new MySQLMessageDAO();
                        Message selectedMessage = msgDAO.getMessage(selectedMessageId);
                        System.out.println(getColoredMessageString(selectedMessage));
                    }
                } while (requestConfirmation("Would you like to view another message?"));
            }
            ufmDAO.updateUserFolderMessagesAsRead(owner,folder);

        } else System.out.println("No messages in " + folder);

        pauseExecution();
    }

}
