package MessagingApp;

import MessagingApp.Entities.Message;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static MessagingApp.Menus.MessageServices.getMessageString;

public class FileAccess {

    // The path where the the file storing the messages will be saved
    // Currently set as a directory named 'ConsoleMessenger' inside 'user.home' folder
    private static final String FILEPATH = System.getProperty("user.home") + File.separator + "ConsoleMessenger";
    private static final String FILENAME = "messenger.txt";


    public static void appendMessageToFile(Message message) {

        try {
            // Create the folder if it doesn't exist
            new File(FILEPATH).mkdirs();
            // Specify the file name and path here
            File file = new File(FILEPATH, FILENAME);

             // Create the file if the file is not already present
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file, true);

            fw.write(getMessageString(message));

            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}