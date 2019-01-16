package MessagingApp.Menus.RoleOptions;

import MessagingApp.DAO.MessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLMessageDAO;
import MessagingApp.Entities.Message;
import MessagingApp.Menus.MenuOption;

import static MessagingApp.Menus.MenuUtils.pauseExecution;
import static MessagingApp.Menus.MenuUtils.requestConfirmation;
import static MessagingApp.Menus.MessageServices.getMessageString;
import static MessagingApp.Menus.MessageServices.inputMessageId;

public class DeleteMessageOption extends MenuOption {

    public DeleteMessageOption() {
        super("Delete a message");
    }

    @Override
    public void execute() {
        long messageId = inputMessageId();

        if (messageId != 0) {
            MessageDAO msgDAO          = new MySQLMessageDAO();
            Message    selectedMessage = msgDAO.getMessage(messageId);

            if (selectedMessage != null) {
                System.out.println("\nYou have selected to delete message with id: " + messageId);
                System.out.println(getMessageString(selectedMessage));

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
