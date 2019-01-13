package MessagingApp.Menus;

public abstract class MenuOption {
    private int option;
    private String menuLine;

    public MenuOption() {
    }

    public MenuOption(int option, String menuLine) {
        this.option = option;
        this.menuLine = menuLine;
    }

    public MenuOption(int option) {
        this.option = option;
    }

    public void setMenuLine(String menuLine) {
        this.menuLine = menuLine;
    }

    @Override
    public String toString() {
        return this.option + " - " + this.menuLine;
    }

    public int getOption() {
        return option;
    }

    public boolean matchesInput(String input){
        if (String.valueOf(option).equals(input)) return true;
        return false;
    }

    public abstract void doAction();

}