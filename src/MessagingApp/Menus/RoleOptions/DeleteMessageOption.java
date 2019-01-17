package MessagingApp.Menus.RoleOptions;

import MessagingApp.DAO.MessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLMessageDAO;
import MessagingApp.Entities.Message;
import MessagingApp.Menus.MenuOption;

import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.Menus.MenuUtils.requestConfirmation;
import static MessagingApp.Menus.MessageServices.getMessageString;
import static MessagingApp.Menus.MessageServices.inputMessageId;

/*
* This option performs permanent message deletion
* The message is removed from the db and from all users' folders
*/
public class DeleteMessageOption extends MenuOption {

    public DeleteMessageOption() {
        super("Delete a message");
    }

    @Override
    public void execute() {
        long messageId = inputMessageId();

        // Here we first check if the given input is valid to be a message ID (integer/long)
        if (messageId != 0) {
            MessageDAO msgDAO          = new MySQLMessageDAO();
            Message    selectedMessage = msgDAO.getMessage(messageId);

            // Then we check if there is indeed a message with given ID
            if (selectedMessage != null) {
                System.out.println("\nYou have selected to delete message with id: " + messageId);
                System.out.println(getMessageString(selectedMessage));

                // We ask 2 times to confirm as this is an irreversible permanent deletion
                if (requestConfirmation("\nAttention. Message will be permanently deleted. Continue?")) {
                    if (requestConfirmation("All users will permanently lose access to message. Are you sure?")){
                        if (msgDAO.deleteMessage(selectedMessage) == 1) System.out.println("Message successfully deleted");
                        else System.out.println("Unknown error. Message was not deleted");
                    } else System.out.println("Operation cancelled");
                } else System.out.println("Operation cancelled");

            } else System.out.println("Message not found");
        }
        pauseExecution();
    }

}
