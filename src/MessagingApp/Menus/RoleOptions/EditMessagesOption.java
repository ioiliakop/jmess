package MessagingApp.Menus.RoleOptions;

import MessagingApp.DAO.MessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLMessageDAO;
import MessagingApp.Entities.Message;
import MessagingApp.Menus.MenuOption;

import java.util.List;
import java.util.Scanner;

import static MessagingApp.Menus.MenuUtils.*;
import static MessagingApp.Menus.MessageServices.*;
import static MessagingApp.Menus.RoleOptions.RoleHelper.editMessageInList;

public class EditMessagesOption extends MenuOption {

    public EditMessagesOption() {
        super("Edit a message");
    }

    @Override
    public void execute() {

        long messageId = inputMessageId();

        if (messageId != 0) {
            MessageDAO msgDAO  = new MySQLMessageDAO();
            Message    selectedMessage = msgDAO.getMessage(messageId);

            if (selectedMessage != null) {
                System.out.println("\nYou have selected to edit message with id: " + messageId);
                System.out.println(getMessageString(selectedMessage));
            }

            Message updatedSelectedMessage = msgDAO.getMessage(messageId);

            if (requestConfirmation("\nWould you like to edit message subject?")) {
                String newSubject = inputMessageSubject();
                updatedSelectedMessage.setMessageSubject(newSubject);
            }

            if (requestConfirmation("Would you like to edit message body?")) {
                String newBody = inputMessageBody();
                updatedSelectedMessage.setMessageBody(newBody);
            }

            // We check if there are changes before proceeding with the update
            if (!selectedMessage.equals(updatedSelectedMessage)) {
                if (requestConfirmation("Proceed with update? ")) {
                    int result = msgDAO.updateMessage(updatedSelectedMessage);
                    if (result != 1) System.out.println("Unknown Error. Message was not updated.");
                    else System.out.println("Message updated successfully");
                } else System.out.println("Update was cancelled");
            } else System.out.println("No changes made");

        }


/*        // We get a list of all messages and message IDs
        MessageDAO    msgDAO        = new MySQLMessageDAO();
        List<Message> allMessages   = msgDAO.getAllMessages();

        editMessageInList(allMessages);*/

        pauseExecution();
    }
}
