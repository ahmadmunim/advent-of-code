package FileReading;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    public static ArrayList<String> getFileContentAsList(String fileName) {
        ArrayList<String> data = new ArrayList<>();

        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                data.add(reader.nextLine());
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Can't find file");
            e.getMessage();
        }

        return data;
    }

    public static String getFileContentAsString(String fileName) {
        String data = "";
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);

            while (reader.hasNext()) {
                data += reader.next();
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Can't find file");
            e.getMessage();
        }

        return data;
    }
}
