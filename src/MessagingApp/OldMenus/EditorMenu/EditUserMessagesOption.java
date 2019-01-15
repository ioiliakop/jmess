package MessagingApp.OldMenus.EditorMenu;

import MessagingApp.DAO.MessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLMessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.Message;
import MessagingApp.Entities.User;
import MessagingApp.Menus.MessageServices;
import MessagingApp.OldMenus.MenuOption;

import java.util.List;

import static MessagingApp.Menus.MenuUtils.*;
import static MessagingApp.Menus.MessageServices.getMessageIdsFromMessages;
import static MessagingApp.Menus.MessageServices.printMessages;


public class EditUserMessagesOption extends MenuOption {

    private static final String MENU_LINE = "Edit a user's messages";

    public EditUserMessagesOption(int option) {
        super(option, MENU_LINE);
    }

    @Override
    public void doAction() {
        String  username = inputUsername("Which user's messages would you like to edit? \n");
        UserDAO usrDAO   = new MySQLUserDAO();
        User    user     = usrDAO.getUser(username);

        if (user != null) {
            MessageDAO    msgDAO       = new MySQLMessageDAO();
            List<Message> userMessages = msgDAO.getAllUserMessages(user.getId());

            if (!userMessages.isEmpty()) {
                printMessages(userMessages);

                List<Long> userMessageIds    = getMessageIdsFromMessages(userMessages);
                long       selectedMessageId = MessageServices.getMessageIdInList(userMessageIds);

                if (selectedMessageId != 0) {
                    Message selectedMessage = msgDAO.getMessage(selectedMessageId);
                    Message updatedMessage  = msgDAO.getMessage(selectedMessageId);

                    if (requestConfirmation("Do you want to edit message subject? ")) {
                        updatedMessage.setMessageSubject(inputMessageSubject());
                    }

                    if (requestConfirmation("Do you want to edit message body? ")) {
                        updatedMessage.setMessageBody(inputMessageBody());
                    }

                    if (!updatedMessage.equals(selectedMessage)) {
                        if(msgDAO.updateMessageSubjectAndBody(updatedMessage.getMessageSubject(), updatedMessage.getMessageBody(),selectedMessageId)==1){
                            System.out.println("Message successfully updated.");
                        } else System.out.println("Unknown error. Message was not updated.");
                    }
                }

//                        if (ufmDAO.updateUserFolderMessage(owner.getId(), TRASH, selectedMessageId) == 1) {
//                            System.out.println("Message successfully moved to trash");
            } else System.out.println("User has no messages, either sent or received.");

        } else {
            System.out.println("Sorry, username not found.");
        }

        pauseExecution();
    }
}
