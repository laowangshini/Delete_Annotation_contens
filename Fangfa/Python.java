package Fangfa;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Python {
    public static void removePYTHONCommentsWithLicense(File file) throws IOException {
        String filePath = file.getAbsolutePath();
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        List<String> newLines = new ArrayList<>();
        
        boolean inCommentBlock = false;

        for (String line : lines) {
            String trimmedLine = line.trim();
            
            // 检查是否是注释行
            if (trimmedLine.startsWith("#")) {
                if (trimmedLine.toLowerCase().contains("license")) {
                    inCommentBlock = true;
                    continue;
                }
                if (!inCommentBlock) {
                    newLines.add(line);
                }
            } else {
                inCommentBlock = false;
                newLines.add(line);
            }
        }

        // 将处理后的内容写回文件
        Files.write(Paths.get(filePath), newLines);
    }
}
