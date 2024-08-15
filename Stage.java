import java.io.*;
import java.util.Properties;

import Fangfa.*;

public class Stage {
    private static Properties handlers;

    public static void main(String[] args) {
        try {
            // 加载配置文件
            handlers = new ConfigLoader().loadConfig(args[0]);
            String directoryPath = (args[1]);
            processDirectory(new File(directoryPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processDirectory(File directory) throws IOException {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    processDirectory(file); // 递归处理子目录
                } else {
                    processFile(file);
                }
            }
        }
    }

    private static void processFile(File file) throws IOException {
        String fileExtension = getFileExtension(file.getName());
        String handlerNames = handlers.getProperty("handlers." + fileExtension);

        if (handlerNames != null) {
            String[] handlers = handlerNames.split(",");
            for (String handler : handlers) {
                try {
                    invokeHandler(handler, file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String getFileExtension(String fileName) {
        int i = fileName.lastIndexOf('.');
        return (i > 0) ? fileName.substring(i + 1) : "";
    }

    private static void invokeHandler(String handlerName, File file) throws Exception {
        // 根据handlerName调用相应的方法
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

    private static void methodForTxtFiles(File file) throws IOException {
        // 处理txt文件的示例逻辑
        System.out.println("Processing TXT file: " + file.getAbsolutePath());
        // 实际的处理逻辑将在这里实现
    }

    // 其他文件类型的处理方法将在这里添加
}

class ConfigLoader {
    public Properties loadConfig(String filePath) throws IOException {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(filePath)) {
            props.load(in);
        }
        return props;
    }
}