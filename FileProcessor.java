import java.io.File;
import java.io.IOException;
import java.util.Properties;
import Fangfa.*;

public class FileProcessor {
    private Properties config;

    public FileProcessor(Properties config) {
        this.config = config;
    }

    public void processFile(File file) throws IOException {
        String fileExtension = getFileExtension(file.getName());
        String handlerNames = config.getProperty("handlers." + fileExtension);

        if (handlerNames != null) {
            String[] handlers = handlerNames.split(",");
            for (String handler : handlers) {
                invokeHandler(handler, file);
            }
        }
    }

    private String getFileExtension(String fileName) {
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            return fileName.substring(i + 1);
        }
        return "";
    }

    private void invokeHandler(String handlerName, File file) throws IOException {
        // 根据handlerName调用相应的方法
        // 这里只是一个示例，你需要根据实际情况实现方法调用逻辑
        switch (handlerName) {
            case "MethodForTwo_1Files":
                Two_1.removeTow_1CommentsFromFile(file);
                break;
            case "MethodForTwo_2fFiles":
                Two_2.removeTow_2CommentsFromFile(file);
                break;
            case "MethodForFour_1Files":
                Four_1.removeFour_1CommentsFromFile(file);
                break;
            case "MethodForFour_2Files":
                Four_2.removeFour_2CommentsFromFile(file);
                break;
            case "MethodForPythonFiles":
                Python.removePYTHONCommentsWithLicense(file);
                break;
                case "MethodForpugFiles":
                pug.removepugCommentsFromFile(file);
                break;
        }
    }
}