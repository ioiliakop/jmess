package MessagingApp.Entities;

import static MessagingApp.Entities.Statuses.Status.ACTIVE;
import static MessagingApp.Entities.Statuses.Status.DELETED;

/**
 * 'Statuses' values are finite and final
 * They are not meant to be altered by any user of this application
 * Only assigned to various users
 * With that in mind, they're implemented here with the use of enums
 * The enum contains the corresponding id number in the db,
 * which is accessible by the ID() method, that is actually a getter.
 * It's abbreviated to ID for convenience and better expression of it's final, non-altering nature
 * More status conditions/codes could easily be added to extend the application (e.g. RESTRICTED etc.)
 */
public class Statuses {

    public enum Status {
        DELETED,
        ACTIVE;

        private int ID;

        static {
            DELETED.ID = 0;
            ACTIVE.ID = 1;
        }

        public int ID() {
            return ID;
        }
    }

    /* Helper method */
    public static Status getStatusFromStatusId(long statusId) {
        if (statusId == 1) return ACTIVE;
        return DELETED;
    }

}
