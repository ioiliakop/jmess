package MessagingApp.Entities;


import static MessagingApp.Entities.Constants.Roles.*;

/**
 * Both the 'Roles' and message 'Containers' values are finite and final
 * They are not meant to be altered by any user of this application
 * Only assigned to various users
 * Therefore, we decided to implement them with the use of enums
 * Both Roles and MessageContainers, Enums contain the corresponding id number in our db,
 * which is accessible by the ID() function, that is actually a getter.
 * It's abbreviated to ID for convenience and better expression of it's final, non-altering nature
 */
public class Constants {

    public enum Roles {
        USER,
        VIEWER,
        EDITOR,
        DELETER,
        ADMIN;

        private int ID;

        static {
            USER.ID = 1;
            VIEWER.ID = 2;
            EDITOR.ID = 3;
            DELETER.ID = 4;
            ADMIN.ID = 5;
        }

        public int ID(){
            return ID;
        }
    }

    public enum MessageContainers {
        INBOX,
        SENTBOX,
        TRASH;

        private int ID;

        static {
            INBOX.ID = 1;
            SENTBOX.ID = 2;
            TRASH.ID = 3;
        }

        public int ID(){
            return ID;
        }
    }

    /* Helper method */
    public static Roles getUserRoleFromRoleId(long roleId){
        if (roleId==5) return ADMIN;
        if (roleId==4) return DELETER;
        if (roleId==3) return EDITOR;
        if (roleId==2) return VIEWER;
        return USER;
    }
    
}
