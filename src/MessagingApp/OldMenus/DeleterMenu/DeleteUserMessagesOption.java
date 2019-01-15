package MessagingApp.OldMenus.DeleterMenu;

import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.User;
import MessagingApp.OldMenus.MenuOption;

import static MessagingApp.Menus.MenuUtils.*;

public class DeleteUserMessagesOption extends MenuOption {
    private static final String MENU_LINE = "Delete a user's messages";

    public DeleteUserMessagesOption(int option) {
        super(option, MENU_LINE);
    }

    @Override
    public void doAction() {
        String  username = inputUsername("Which user's messages would you like to delete? \n");
        UserDAO usrDAO   = new MySQLUserDAO();
        User    user     = usrDAO.getUser(username);

/*        if (user != null) {
            MessageDAO    msgDAO       = new MySQLMessageDAO();
            List<Message> userMessages = msgDAO.getAllUserMessages(user.getId());

            if (!userMessages.isEmpty()) {
                printMessages(userMessages);

                List<Long> userMessageIds    = getMessageIdsFromMessages(userMessages);
                long       selectedMessageId = getMessageIdInList(userMessageIds);

                if (selectedMessageId != 0) {
                    Message selectedMessage = msgDAO.getMessage(selectedMessageId);
                    Message updatedMessage  = msgDAO.getMessage(selectedMessageId);

                    if (requestConfirmation("Do you want to edit message subject? ")) {
                        updatedMessage.setMessageSubject(inputGeneric("Enter new subject: "));
                    }

                    if (requestConfirmation("Do you want to edit message body? ")) {
                        updatedMessage.setMessageBody(inputGeneric("Enter new message body: "));
                    }

                    if (!updatedMessage.equals(selectedMessage)) {
                        if (msgDAO.updateMessageSubjectAndBody(updatedMessage.getMessageSubject(), updatedMessage.getMessageBody(), selectedMessageId) == 1) {
                            System.out.println("Message successfully updated.");
                        } else System.out.println("Unknown error. Message was not updated.");
                    }
                }

//                        if (ucmDAO.updateUserFolderMessage(owner.getId(), TRASH, selectedMessageId) == 1) {
//                            System.out.println("Message successfully moved to trash");
            } else System.out.println("User has no messages, either sent or received.");

        } else {
            System.out.println("Sorry, username not found.");
        }*/

        pauseExecution();
    }
}
