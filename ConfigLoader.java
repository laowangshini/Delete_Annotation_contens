import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    public Properties loadConfig(String filePath) throws IOException {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(filePath)) {
            props.load(in);
        }
        return props;
    }
}