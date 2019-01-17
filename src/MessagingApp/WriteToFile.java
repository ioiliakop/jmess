package MessagingApp;

import MessagingApp.Entities.Message;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static MessagingApp.Menus.MessageServices.getMessageString;

public class WriteToFile {

    private              FileWriter fw;
    private static final String     FILEPATH = System.getProperty("user.home") + File.separator + "Messenger";
    private static final String     FILENAME = "messenger.txt";

    public void initFile() {
        try {
            new File(FILEPATH).mkdirs();
            File file = new File(FILEPATH, FILENAME);
            if (!file.exists()) {
                file.createNewFile();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(Message message) {
        try {
            fw.append(getMessageString(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
