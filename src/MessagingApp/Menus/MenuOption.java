package MessagingApp.Menus;

import MessagingApp.Entities.User;

/**
 * MenuOption is the basis of our console menu system
 * The menu system was implemented with the composite design pattern in mind
 * Private fields of MenuOption is a User Object which in most cases is the user that invoked the menu
 * aka. the owner of the menu, but in some case it can be a User Object that is the target of the menu's functionality
 * menuLine is the instantiated option's menu line used by the menu/presented to the user
 * execute() is the abstract method all derived classes will have to implement
 * It "executes" the functionality they provide
 */
public abstract class MenuOption {

    private User   user;
    private String menuLine;

    public MenuOption() {
    }

    public MenuOption(User user) {
        this.user = user;
    }

    /*
     * This constructor is used when the information of the User object that invoked it
     * doesn't affect in any way it's functionality
     */
    public MenuOption(String menuLine) {
        this.menuLine = menuLine;
    }

    public MenuOption(User user, String menuLine) {
        this.user = user;
        this.menuLine = menuLine;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMenuLine() {
        return menuLine;
    }

    public void setMenuLine(String menuLine) {
        this.menuLine = menuLine;
    }

    public abstract void execute();
}
