package Fangfa;
import java.io.*;
import java.nio.file.*;
import java.util.regex.*;

public class pug {

    public static void removepugCommentsFromFile(File file) throws IOException {
        // 正则表达式匹配以'//-'开始，包含'License'的注释块
        String regex = "//-.*?(License).*?(?=\\n[a-zA-Z])";

        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);

        try {
            // 读取文件内容
            String content = new String(Files.readAllBytes(file.toPath()));

            // 替换包含'License'的注释块
            Matcher matcher = pattern.matcher(content);
            String newContent = matcher.replaceAll("");

            // 将新内容写回文件
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(newContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
