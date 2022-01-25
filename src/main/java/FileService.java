import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    public static List<String> readFile(String path) {
        Path filePath = Paths.get(path);
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}
