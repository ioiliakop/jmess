package MessagingApp.Entities;

import static MessagingApp.Entities.Roles.Role.*;

/**
 * 'Roles' values are finite and final
 * They are not meant to be altered by any user of this application
 * Only assigned to various users
 * With that in mind, they're implemented here with the use of enums
 * The enum contains the corresponding id number in the db,
 * which is accessible by the ID() method, that is actually a getter.
 * It's abbreviated to ID for convenience and better expression of it's final, non-altering nature
 * More roles could easily be added to extend our application if needed
 */
public class Roles {

    public enum Role {
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

        public int ID() {
            return ID;
        }
    }

    /* Helper method */
    public static Role getRoleFromRoleId(long roleId) {
        if (roleId == 5) return ADMIN;
        if (roleId == 4) return DELETER;
        if (roleId == 3) return EDITOR;
        if (roleId == 2) return VIEWER;
        if (roleId == 1) return USER;
        return USER;
    }
}
