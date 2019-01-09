package MessagingApp.Menus;

import MessagingApp.DAO.MessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLMessageDAO;
import MessagingApp.DAO.MySQLDAO.MySQLRoleDAO;
import MessagingApp.DAO.MySQLDAO.MySQLUserDAO;
import MessagingApp.DAO.RoleDAO;
import MessagingApp.DAO.UserDAO;
import MessagingApp.Entities.Message;
import MessagingApp.Entities.User;

import java.util.ArrayList;
import java.util.List;

import static MessagingApp.Menus.MenuUtils.pauseExecution;

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

    public static List<User> getAllDAOUsers() {
        UserDAO usrDAO = new MySQLUserDAO();
        return usrDAO.getAllUsers();
    }

/*    public static List<Message> getUserMessages(USER user) {
        MessageDAO    msgDAO       = new MySQLMessageDAO();
        List<Message> allMessages  = msgDAO.getAllMessages();
        List<Message> userMessages = new ArrayList<>();

        for (Message message : allMessages) {
            if (message.getAuthorId() == user.ID() || message.getReceiverId() == user.ID()) {
                userMessages.add(message);
            }
        }

        return userMessages;
    }*/

/*    public static List<USER> getConversingUsers(USER user) {
        List<Message> userMessages        = getUserMessages(user);
        List<USER>    conversingUsersList = new ArrayList<>();

        for (Message message : userMessages) {

            if (message.getAuthorId() == user.ID()) {
                USER conversingUser = getDAOUser(message.getReceiverId());

                if (!conversingUsersList.contains(conversingUser)) {
                    conversingUsersList.add(conversingUser);
                }
            } else {
                USER conversingUser = getDAOUser(message.getAuthorId());
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

    public static void printMessages(List<Message> messages) {
        for (Message m : messages) {
            System.out.print("\nMsgID: " + m.getId() + "\t\t" + getDAOUsernameFromId(m.getAuthorId()) +
                    "\t\tSubject: " + m.getMessageSubject() + "\t\tDateTime: " + m.getMessageDateTime());
        }
        System.out.println("\n");
    }

    public static void printUserInfo(User user) {
        System.out.println("\nid: " + user.getId() + "\tusername: " + user.getUsername() +
                "\tpassword: " + user.getPassword() + "\troleId: " + getRoleNameById(user.getRoleId()));
    }

    public static String getRoleNameById(long id) {
        RoleDAO rlDAO = new MySQLRoleDAO();
        return rlDAO.getRole(id).getRoleName();
    }

    public static boolean roleExists(String roleName) {
        RoleDAO rlDAO = new MySQLRoleDAO();
        if (rlDAO.getRole(roleName) != null) return true;
        return false;
    }

    public static long getRoleIdbyRoleName(String roleName) {
        RoleDAO rlDAO = new MySQLRoleDAO();
        return rlDAO.getRole(roleName).getId();
    }

    public static List<Message> getMessagesFromMessageIds(List<Long> messageIdsList) {
        List<Message> messagesList = new ArrayList<>();
        MessageDAO    msgDAO       = new MySQLMessageDAO();
        for (long messageId : messageIdsList) {
            messagesList.add(msgDAO.getMessage(messageId));
        }
        return messagesList;
    }

}
