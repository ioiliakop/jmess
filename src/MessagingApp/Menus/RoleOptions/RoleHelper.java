package MessagingApp.Menus.RoleOptions;

import MessagingApp.DAO.MessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLMessageDAO;
import MessagingApp.Entities.Message;
import MessagingApp.Entities.Roles.Role;
import MessagingApp.Entities.User;
import MessagingApp.MessagingAppException;

import java.util.List;

import static MessagingApp.Entities.Roles.Role.DELETER;
import static MessagingApp.Entities.Roles.Role.EDITOR;
import static MessagingApp.Menus.Utils.inputMessageBody;
import static MessagingApp.Menus.Utils.inputMessageSubject;
import static MessagingApp.Menus.Utils.requestConfirmation;
import static MessagingApp.Menus.MessageServices.*;

public class RoleHelper {

    /* takes a list of messages as parameter
     * performs edit/update of message
     * Edit functionality refers to the update of the subject and body of the message only
     */
    public static void editMessageInList(List<Message> messages) throws MessagingAppException {
        List<Long> messageIDs = getMessageIdsFromMessages(messages);

        // We ask the user to make a selection by message ID
        System.out.println("Which message would you like to edit?");
        long messageId = getMessageIdInList(messageIDs);

        // If the id is valid we proceed
        if (messageId != 0) {
            MessageDAO msgDAO          = new MySQLMessageDAO();
            Message    selectedMessage = msgDAO.getMessage(messageId);
            System.out.println("You have selected to edit message with id: " + messageId);
            System.out.println(getColoredMessageString(selectedMessage));

            Message updatedSelectedMessage = msgDAO.getMessage(messageId);

            if (requestConfirmation("Would you like to edit message subject?")) {
                String newSubject = inputMessageSubject();
                updatedSelectedMessage.setMessageSubject(newSubject);
            }

            if (requestConfirmation("Would you like to edit message body?")) {
                String newBody = inputMessageBody();
                updatedSelectedMessage.setMessageBody(newBody);
            }

            // We check if there are changes before proceeding with the update
            updateMessageIfChanged(msgDAO, selectedMessage, updatedSelectedMessage);
        }
    }

    static void updateMessageIfChanged(MessageDAO msgDAO, Message selectedMessage, Message updatedSelectedMessage) throws MessagingAppException {
        if (!selectedMessage.equals(updatedSelectedMessage)) {
            if (requestConfirmation("Proceed with update? ")) {
                int result = msgDAO.updateMessage(updatedSelectedMessage);
                if (result != 1) throw new MessagingAppException("Unknown Error. Message was not updated.");
                else System.out.println("Message updated successfully");
            } else System.out.println("Update was cancelled");
        } else System.out.println("No changes made");
    }

    public static void deleteMessageInList(List<Message> messages) throws MessagingAppException {
        List<Long> messageIDs = getMessageIdsFromMessages(messages);

        // We ask the user to make a selection by message ID
        System.out.println("Which message would you like to delete?");
        long messageId = getMessageIdInList(messageIDs);

        // If the id is valid we proceed
        if (messageId != 0) {
            MessageDAO msgDAO          = new MySQLMessageDAO();
            Message    selectedMessage = msgDAO.getMessage(messageId);
            System.out.println("You have selected to delete message with id: " + messageId);
            System.out.println(getColoredMessageString(selectedMessage));

            if (requestConfirmation("The message will be permanently deleted. Are you sure?")) {
                int deleteMessage = msgDAO.deleteMessage(selectedMessage);
                if (deleteMessage == 1) System.out.println("Message successfully deleted");
                else throw new MessagingAppException("Unknown error. Message was not deleted");
            } else System.out.println("Operation cancelled.");
        }
    }


    static String getRoleAbilitiesString(Role role) {
        String roleOptions = "View";
        if (role == EDITOR) roleOptions = roleOptions + "/Edit";
        if (role == DELETER) roleOptions = roleOptions + "/Edit/Delete";
        return roleOptions + " ";
    }

    /* helper method with varying functionality based on user role */
    static void viewEditDeleteMessagesInList(User roleUser, List<Message> messagesList) throws MessagingAppException {
        if (!messagesList.isEmpty()) {
            printMessages(messagesList);

            if (roleUser.getRoleId() == EDITOR.ID() || roleUser.getRoleId() == DELETER.ID()) {
                if (requestConfirmation("Do you want to edit any of the above messages?")) {
                    editMessageInList(messagesList);
                } else if (roleUser.getRoleId() == DELETER.ID()) {
                    if (requestConfirmation("Do you want to delete any of the above messages?")) {
                        deleteMessageInList(messagesList);
                    }
                }
            }

        } else System.out.println("No messages found...");
    }

}
