package MessagingApp.Menus;

import MessagingApp.DAO.MessageDAO;
import MessagingApp.DAO.MessageReceiversDAO;
import MessagingApp.DAO.MySQLDAO.MySQLMessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLMessageReceiversDAO;
import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.MySQLDAO.MySQLUserFolderMessageDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.DAO.UserFolderMessageDAO;
import MessagingApp.Entities.Message;
import MessagingApp.Entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static MessagingApp.Entities.MessageFolders.Folder.INBOX;
import static MessagingApp.Entities.Statuses.Status.DELETED;
import static MessagingApp.Menus.MenuUtils.pauseExecution;

public class MessageServices {

    public static final String ANSI_RESET  = "\u001B[0m";
    public static final String ANSI_BLACK  = "\u001B[30m";
    public static final String ANSI_RED    = "\u001B[31m";
    public static final String ANSI_GREEN  = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE   = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN   = "\u001B[36m";
    public static final String ANSI_WHITE  = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND  = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND    = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND  = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND   = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND   = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND  = "\u001B[47m";

    /*    *//* Method that takes a list of messages and prints them on the screen in user-friendly format *//*
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
    }*/

    /* Method that takes a list of messages and prints them on the screen in user-friendly format */
    public static void printMessages(List<Message> messages) {
        if (!messages.isEmpty()) {

            for (Message message : messages) {
                System.out.println(getColoredMessageString(message));
            }
            System.out.println();
        }
    }

    /*
     * Helper method that returns a string with all message info
     * So it can be more understandable and user friendly
     * It calls respective helper method to get the sender and receiver names
     */
    public static String getMessageString(Message message) {
        String[] names = getMessageSenderAndReceiverNames(message);

        String senderName    = names[0];
        String receiverNames = names[1];

        return "MsgID: " + message.getId() + "\t\tFrom: " + senderName + "\t\tTo: " + receiverNames +
                "\t\tDateTime: " + message.getMessageDateCreated() + "\n\tSubject: " + message.getMessageSubject() +
                "\n\tMessage: " + message.getMessageBody() + "\n";
    }

    /*
     * provides ease to the user to focus quickly on desired information bits
     * to be used for console output only
     */
    public static String getColoredMessageString(Message message) {
        String[] names = getMessageSenderAndReceiverNames(message);

        String senderName    = names[0];
        String receiverNames = names[1];

        return ANSI_CYAN + "MsgID: " + ANSI_YELLOW + message.getId() + ANSI_BLUE + "\t\tFrom: " + ANSI_GREEN + senderName + ANSI_BLUE + "\t\tTo: " + ANSI_GREEN + receiverNames + ANSI_CYAN +
                "\t\tDateTime: " + ANSI_YELLOW + message.getMessageDateCreated() + ANSI_CYAN + "\n\tSubject: " + ANSI_RED + message.getMessageSubject() +
                ANSI_CYAN + "\n\tMessage: " + ANSI_PURPLE + message.getMessageBody() + ANSI_RESET + "\n";
    }

    /*
     * method that returns a string of the receiver names of a message, separated by a space
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
     * Helper method that returns a String array carrying 2 values
     * The first one is the username of the sender of the message
     * The second is a String with all the usernames of the receivers of the message
     * 'deleted' is appended to each deleted user's username to denote user's status
     */
    public static String[] getMessageSenderAndReceiverNames(Message message) {
        UserDAO             usrDAO = new MySQLUserDAO();
        MessageReceiversDAO mrDAO  = new MySQLMessageReceiversDAO();

        User     sender = usrDAO.getUser(message.getSenderId());
        String[] names  = new String[2];
        names[0] = sender.getUsername();
        if (sender.getStatusId() == DELETED.ID()) names[0] = names[0] + "(deleted)";

        List<Long>    receiverIds = mrDAO.getMessageReceiverIds(message.getId());
        StringBuilder receiversSb = new StringBuilder();

        for (long receiverId : receiverIds) {
            User receiver = usrDAO.getUser(receiverId);
            receiversSb.append(receiver.getUsername());
            if (receiver.getStatusId() == DELETED.ID()) receiversSb.append("(deleted)");
            receiversSb.append(" ");
        }

        names[1] = receiversSb.toString();
        return names;
    }

    /* Returns a list of messages from respective list of message IDs */
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
     * Then compares it against list of message IDs passed as parameter
     * Returns the message ID if it is contained in the list or 0 otherwise
     */
    public static long getMessageIdInList(List<Long> messageIds) {
        long messageId = inputMessageId();

        if (messageId != 0) {
            if (!messageIds.contains(messageId)) {
                System.out.println("Selected message not available.");
                messageId = 0;
            }
        }

        return messageId;
    }

    /*
     * Helper method that takes user input for message id
     * Validates input if it's valid number type for message id (integer/long)
     * Returns the input given as 'long', if it is
     * Returns 0 otherwise
     */
    public static long inputMessageId() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter message id: ");
        String input = sc.nextLine();

        long messageId = 0;
        try {
            messageId = Long.parseLong(input);
        } catch (NumberFormatException e) {
            System.out.println("Sorry, not a valid message id.");
        }
        return messageId;
    }

    /* Checks if logged in user has unread messages in inbox since last visit */
    public static void unreadMessagesPrompt(User user){
        UserFolderMessageDAO ufmDAO = new MySQLUserFolderMessageDAO();
        long numberOfUnreadMessages = ufmDAO.getUnreadMessagesCountInFolder(user,INBOX);
        if (numberOfUnreadMessages>0) {
            System.out.println("You have " + numberOfUnreadMessages + " unread messages. Please check your INBOX");
            pauseExecution();
        }
    }


}
