package MessagingApp.Entities;

/**
 * 'Roles', 'MessageFolders' and 'Statuses' values are finite and final
 * They are not meant to be altered by any user of this application
 * Only assigned to various users
 * With that in mind, they're implemented here with the use of enums
 * All three enums contain the corresponding id number in the db,
 * which is accessible by the ID() method, that is actually a getter.
 * It's abbreviated to ID for convenience and better expression of it's final, non-altering nature
 */
public class MessageFolders {

    public enum Folder {
        INBOX,
        SENTBOX,
        TRASH;

        private int ID;

        static {
            INBOX.ID = 1;
            SENTBOX.ID = 2;
            TRASH.ID = 3;
        }

        public int ID() {
            return ID;
        }
    }
}
