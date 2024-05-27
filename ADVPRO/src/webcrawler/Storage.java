package webcrawler;

import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    public void saveData(String data, String filename) throws IOException {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(data + "\n");
        }
    }
}
