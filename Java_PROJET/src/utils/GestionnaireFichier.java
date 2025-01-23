package utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class GestionnaireFichier {
    public static void createFileIfNotExists(String fileName, String header) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
            try (PrintWriter out = new PrintWriter(file)) {
                out.println(header);
            }
        }
    }
}
