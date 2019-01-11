package MessagingApp.FileAccess;

import MessagingApp.Entities.Message;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileAccess {

    public static void appendMessageToFile(Message message) {

        try {
            //Specify the file name and path here
            File file = new File("messages.txt");

            /* This logic is to create the file if the
             * file is not already present
             */
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file, true);

            fw.write(message.toString());

            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}