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

public class Services {

    public static boolean usernameExists(String username) {
        UserDAO userDAO = new MySQLUserDAO();
        if (userDAO.getUser(username) != null) return true;
        return false;
    }

    public static boolean userExists(User user) {
        if (user == null) return false;
        return true;
    }

    public static User getDAOUser(String username) {
        UserDAO usrDAO = new MySQLUserDAO();
        return usrDAO.getUser(username);
    }

    public static User getDAOUser(long id) {
        UserDAO usrDAO = new MySQLUserDAO();
        return usrDAO.getUser(id);
    }

    public static String getDAOUsernameFromId(long id) {
        UserDAO usrDAO = new MySQLUserDAO();
        return usrDAO.getUser(id).getUsername();
    }


/*    public static List<Message> getUserMessages(USER user) {
        MessageDAO    msgDAO       = new MySQLMessageDAO();
        List<Message> allMessages  = msgDAO.getAllMessages();
        List<Message> userMessages = new ArrayList<>();

        for (Message message : allMessages) {
            if (message.getSenderId() == user.ID() || message.getReceiverId() == user.ID()) {
                userMessages.add(message);
            }
        }

        return userMessages;
    }*/

/*    public static List<USER> getConversingUsers(USER user) {
        List<Message> userMessages        = getUserMessages(user);
        List<USER>    conversingUsersList = new ArrayList<>();

        for (Message message : userMessages) {

            if (message.getSenderId() == user.ID()) {
                USER conversingUser = getDAOUser(message.getReceiverId());

                if (!conversingUsersList.contains(conversingUser)) {
                    conversingUsersList.add(conversingUser);
                }
            } else {
                USER conversingUser = getDAOUser(message.getSenderId());
                if (!conversingUsersList.contains(conversingUser)) {
                    conversingUsersList.add(conversingUser);
                }
            }
        }
        return conversingUsersList;
    }*/

    public static List<String> getUsernamesFromUsers(List<User> userList) {
        List<String> usernames = new ArrayList<>();
        for (User user : userList) {
            usernames.add(user.getUsername());
        }
        return usernames;
    }

    public static void printUsernamesFromUsers(List<User> userList) {
        for (User user : userList) {
            System.out.println(user.getUsername() + "\n");
        }
    }

/*    public static boolean conversationExists(USER user, USER conversingUser){
        if (getConversingUsers(user).contains(conversingUser)){
            return true;
        }
        return false;
    }*/

//    public static String getMessageString(Message message) {
//
//        return "\nMsgID: " + message.getId() + "\t\tFrom: " + senderName + "\t\tTo: " + receiverName +
//                "\t\tSubject: " + m.getMessageSubject() + "\t\tDateTime: " + m.getMessageDateCreated() +
//                "\n\tMessage: " + m.getMessageBody();
//    }

/*    public static void printMessages(List<Message> messages) {
        if (!messages.isEmpty()) {
            UserDAO             usrDAO = new MySQLUserDAO();
            MessageReceiversDAO mrDAO  = new MySQLMessageReceiversDAO();
            for (Message m : messages) {
                String        senderName  = assignUsernameFromUserId(m.getSenderId());
                List<Long>    receiverIds = mrDAO.getMessageReceiverIds(m.getId());
                StringBuilder receiversSb = new StringBuilder();

                for (long receiverId : receiverIds) {
                    User receiver = usrDAO.getUser(receiverId);
                    receiversSb.append(receiver.getUsername() + " ");
                }

                System.out.print("\nMsgID: " + m.getId() + "\t\tFrom: " + senderName + "\t\tTo: " + receiversSb +
                        "\t\t\tSubject: " + m.getMessageSubject() + "\t\tDateTime: " + m.getMessageDateCreated());
            }
            System.out.println("\n");
        }
    }*/

    public static void printMessages(List<Message> messages) {
        if (!messages.isEmpty()) {

            for (Message m : messages) {
                String senderName           = assignUsernameFromUserId(m.getSenderId());
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
     */
    public static String getMessageString(Message message) {
        UserDAO             usrDAO = new MySQLUserDAO();
        MessageReceiversDAO mrDAO  = new MySQLMessageReceiversDAO();

        String        senderName  = assignUsernameFromUserId(message.getSenderId());
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

/*    public static void printUserInfo(User user) {
        System.out.println("\nid: " + user.getId() + "\tusername: " + user.getUsername() +
                "\tpassword: " + user.getPassword() + "\trole: " + user.getRoleId()) + "\tstatus: " + ;
    }*/

    public static List<Message> getMessagesFromMessageIds(List<Long> messageIdsList) {
        List<Message> messagesList = new ArrayList<>();
        MessageDAO    msgDAO       = new MySQLMessageDAO();
        for (long messageId : messageIdsList) {
            messagesList.add(msgDAO.getMessage(messageId));
        }
        return messagesList;
    }

    public static List<Long> getMessageIdsFromMessages(List<Message> messagesList) {
        List<Long> messageIdsList = new ArrayList<>();
        for (Message m : messagesList) {
            messageIdsList.add(m.getId());
        }
        return messageIdsList;
    }

    public static String assignUsernameFromUserId(long userId) {
        UserDAO usrDAO = new MySQLUserDAO();
        User    user   = usrDAO.getUser(userId);
        if (user == null) return "UNKNOWN USER";
        else return user.getUsername();
    }

    /*
     * Helper method that gets a message Id from user input
     * First validates input if it's valid number type, returns 0 if not
     * Then compares it against list of message IDs passed as parameter
     * Returns the message ID if it is contained in the list or 0 otherwise
     */
    public static long getMessageIdInList(List<Long> messageIds) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter messageId: ");
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
