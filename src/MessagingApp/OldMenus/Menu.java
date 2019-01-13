package MessagingApp.Menus;

import MessagingApp.Entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static final String           OPTION_EXIT   = "0";
    private              String           titleTemplate;
    private              List<MenuOption> menuOptions   = new ArrayList<>();
    private              String           title;
    private              User             owner;

    {
        titleTemplate = "------- Welcome %s -------";
    }

    public Menu() {
    }

    public Menu(User owner) {
        this.owner = owner;
        this.title = String.format(titleTemplate, owner.getUsername());
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void add(MenuOption menuOption) {
        this.menuOptions.add(menuOption);
    }

    public void run() {
        while (true) {
            printMenu();
            Scanner sc    = new Scanner(System.in);
            String  input = sc.nextLine();

            if (input.equals(OPTION_EXIT)) {
                return;
            }

            boolean foundOption = false;

            for (MenuOption menuOption : menuOptions) {
                if (menuOption.matchesInput(input)) {
                    System.out.println();
                    menuOption.doAction();
                    foundOption = true;
                }
            }

            if (!foundOption) {
                System.out.println("Sorry, unrecognized option.");
            }
        }
    }

    /* A multiline string presenting all menu options */
    private void printMenu() {
        StringBuilder sb = new StringBuilder();
        if (this.title.equals("") == false) {
            sb.append("\n" + this.title + "\n");
        } else sb.append("\n");
        for (MenuOption menuOption : menuOptions) {
            sb.append(menuOption + "\n");
        }
        sb.append(OPTION_EXIT + " - Exit");
        sb.append("\nChoose an option: ");
        System.out.print(sb);
    }

}