package MessagingApp.Menus;

import MessagingApp.Entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Menu extends our MenuOption abstract class
 * So it can be accepted as a MenuOption object in our menuOptions field, and therefore be used as a sub menu
 * We set our exit option to always be '0'
 * The exit prompt will either be 'Exit' or 'Back', if the menu is a root or a sub menu
 * This can be controlled by the constructor we use
 * Another field is the menuTitle, which, in case of a root menu, welcomes user who invoked the menu
 */
public class Menu extends MenuOption {

    private static final int              EXIT_INDEX          = 0;
    private static final String           MENU_TITLE_TEMPLATE = "\n__________  %s  __________";
    private              String           exitPrompt;
    private              String           menuTitle;
    private              List<MenuOption> menuOptions;

    public Menu() {
        this.menuOptions = new ArrayList<>();
    }

    /* Constructor when menu is rootMenu */
    public Menu(User user) {
        super(user);
        this.menuTitle = String.format(MENU_TITLE_TEMPLATE, "Welcome " + user.getUsername());
        this.exitPrompt = "Exit";
        this.menuOptions = new ArrayList<>();
    }

    /* Constructor should be used if Menu is a sub-menu, sets option line on parent menu, and sets exitPrompt to "Back" */
    public Menu(User user, String menuLine) {
        super(user, menuLine);
        this.menuTitle = String.format(MENU_TITLE_TEMPLATE, "Welcome " + user.getUsername());
        this.exitPrompt = "Back";
        this.menuOptions = new ArrayList<>();
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = String.format(MENU_TITLE_TEMPLATE, menuTitle);
    }

    public void setExitPrompt(String exitPrompt) {
        this.exitPrompt = exitPrompt;
    }

    /* Method to add menu options */
    public void add(MenuOption menuOption) {
        menuOptions.add(menuOption);
    }

    /* Prints the menu */
    private void printMenu() {
        int optionIndex = 0;
        System.out.println(this.menuTitle);
        for (MenuOption mOption : menuOptions) {
            System.out.println(++optionIndex + " - " + mOption.getMenuLine());
        }
        System.out.println(EXIT_INDEX + " - " + exitPrompt);
        System.out.print("Select an option\n> ");
    }

    @Override
    public void execute() {
        while (true) {
            printMenu();
            int selectedIndex = getUserInput();
            if (selectedIndex != -1) {
                if (selectedIndex == EXIT_INDEX) return;
                menuOptions.get(selectedIndex - 1).execute();
            }
        }
    }

    /*
     * Helper method that receives and validates user input
     * Returns -1 if user input doesn't correspond to a menu option
     * Returns user input as an int otherwise
     */
    private int getUserInput() {
        Scanner sc    = new Scanner(System.in);
        String  input = sc.nextLine();

        try {
            int option = Integer.parseInt(input);
            if (option >= 0 & option <= menuOptions.size()) return option;
            System.out.println("Not a menu option\n");
        } catch (NumberFormatException e) {
            System.out.println("Not a valid option\n");
        }
        return -1;
    }

}
