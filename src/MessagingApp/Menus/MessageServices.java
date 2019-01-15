package MessagingApp.Menus;

import MessagingApp.DAO.MessageDAO;
import MessagingApp.DAO.MessageReceiversDAO;
import MessagingApp.DAO.MySQLDAO.MySQLMessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLMessageReceiversDAO;
import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.Message;
import MessagingApp.Entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MessageServices {

    /* Method that takes a list of messages and prints them on the screen in user-friendly format */
    public static void printMessages(List<Message> messages) {
        if (!messages.isEmpty()) {

            for (Message m : messages) {
                String senderName           = Services.assignUsernameFromUserId(m.getSenderId());
                String messageReceiverNames = getMessageReceiverNames(m);

                System.out.print("\nMsgID: " + m.getId() + "\t\tFrom: " + senderName + "\t\tTo: " + messageReceiverNames +
                        "\t\t\tSubject: " + m.getMessageSubject() + "\t\tDateTime: " + m.getMessageDateCreated());
            }
            System.out.println("\n");
        }
    }

    /*
     * Helper method that returns a string with all message info
     * So it can be more understandable and user friendly
     * It calls other helper methods to
     */
    public static String getMessageString(Message message) {
        UserDAO             usrDAO = new MySQLUserDAO();
        MessageReceiversDAO mrDAO  = new MySQLMessageReceiversDAO();

        String        senderName  = Services.assignUsernameFromUserId(message.getSenderId());
        List<Long>    receiverIds = mrDAO.getMessageReceiverIds(message.getId());
        StringBuilder receiversSb = new StringBuilder();

        for (long receiverId : receiverIds) {
            User receiver = usrDAO.getUser(receiverId);
            receiversSb.append(receiver.getUsername() + " ");
        }

        return "\nMsgID: " + message.getId() + "\t\tFrom: " + senderName + "\t\tTo: " + receiversSb +
                "\t\tDateTime: " + message.getMessageDateCreated() + "\n\tSubject: " + message.getMessageSubject() +
                "\n\tMessage: " + message.getMessageBody();
    }

    /*
     * method that returns a string of the receiver names of a message, seperated by a space
     * used when we want ot print message info, either on screen, or on a file
     */
    public static String getMessageReceiverNames(Message message) {
        UserDAO             usrDAO = new MySQLUserDAO();
        MessageReceiversDAO mrDAO  = new MySQLMessageReceiversDAO();

        List<Long>    receiverIds       = mrDAO.getMessageReceiverIds(message.getId());
        StringBuilder receiversSbuilder = new StringBuilder();

        for (long receiverId : receiverIds) {
            User receiver = usrDAO.getUser(receiverId);
            receiversSbuilder.append(receiver.getUsername() + " ");
        }
        return receiversSbuilder.toString();
    }

    /*
     * Helper method that returns a String array carrying 2 vales
     * The first one is the username of the sender of the message
     * The second is a String with all the usernames of the receivers of the message
     */
    public static String[] getMessageSenderAndReceiverNames(Message message) {
        UserDAO             usrDAO = new MySQLUserDAO();
        MessageReceiversDAO mrDAO  = new MySQLMessageReceiversDAO();

        User     sender = usrDAO.getUser(message.getSenderId());
        String[] names  = new String[2];
        names[0] = sender.getUsername();

        List<Long>    receiverIds = mrDAO.getMessageReceiverIds(message.getId());
        StringBuilder receiversSb = new StringBuilder();

        for (long receiverId : receiverIds) {
            User receiver = usrDAO.getUser(receiverId);
            receiversSb.append(receiver.getUsername() + " ");
        }

        names[1] = receiversSb.toString();
        return names;
    }

    public static List<Message> getMessagesFromMessageIds(List<Long> messageIdsList) {
        List<Message> messagesList = new ArrayList<>();
        MessageDAO    msgDAO       = new MySQLMessageDAO();
        for (long messageId : messageIdsList) {
            messagesList.add(msgDAO.getMessage(messageId));
        }
        return messagesList;
    }

    /*
     * method that takes a list of messages as parameter
     * and returns a list of the corresponding message IDs only
     * useful when we expect a user to select a message id from a list of messages for further action
     * we quickly have a list of the IDs of those messages
     * */
    public static List<Long> getMessageIdsFromMessages(List<Message> messagesList) {
        List<Long> messageIdsList = new ArrayList<>();
        for (Message m : messagesList) {
            messageIdsList.add(m.getId());
        }
        return messageIdsList;
    }

    /*
     * Helper method that takes a list of message IDs as parameter
     * Then takes user input for
     * First validates input if it's valid number type, returns 0 if not
     * Then compares it against list of message IDs passed as parameter
     * Returns the message ID if it is contained in the list or 0 otherwise
     */
    public static long getMessageIdInList(List<Long> messageIds) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter message id: ");
        String input = sc.nextLine();

        long messageId = 0;
        try {
            messageId = Long.parseLong(input);
        } catch (NumberFormatException e) {
            System.out.println("Sorry, not a valid message id.");
        }

        if (messageId != 0) {
            if (!messageIds.contains(messageId)) {
                System.out.println("Selected message not available.");
                messageId = 0;
            }
        }

        return messageId;
    }
}
