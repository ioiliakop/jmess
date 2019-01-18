package MessagingApp.Menus.RoleOptions;

import MessagingApp.DAO.MessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLMessageDAO;
import MessagingApp.Entities.Message;
import MessagingApp.Menus.MenuOption;
import MessagingApp.MessagingAppException;

import static MessagingApp.Menus.Utils.*;
import static MessagingApp.Menus.MessageServices.*;
import static MessagingApp.Menus.RoleOptions.RoleHelper.updateMessageIfChanged;

/**
 * Provides functionality to edit any stored message in the db's `messages` table
 * Available to 'EDITOR' and 'DELETER' roles
 */
public class EditMessageOption extends MenuOption {

    public EditMessageOption() {
        super("Edit a message");
    }

    @Override
    public void execute() throws MessagingAppException {

        long messageId = inputMessageId();

        if (messageId != 0) {
            MessageDAO msgDAO          = new MySQLMessageDAO();
            Message    selectedMessage = msgDAO.getMessage(messageId);

            if (selectedMessage != null) {
                System.out.println("\nYou have selected to edit message with id: " + messageId);
                System.out.println(getColoredMessageString(selectedMessage));

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
                updateMessageIfChanged(msgDAO, selectedMessage, updatedSelectedMessage);
            } else System.out.println("Message not found");
        }
        pauseExecution();
    }

}
