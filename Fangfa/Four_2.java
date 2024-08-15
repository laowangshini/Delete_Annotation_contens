package Fangfa;
import java.io.*;
import java.nio.file.*;
import java.util.Arrays;

public class Four_2 {
    public static void removeFour_2CommentsFromFile(File file) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(file.toURI()));

        int start = -1;
        int end = -1;
        boolean found = false;

        for (int i = 0; i < bytes.length - 1; i++) {
            if (bytes[i] == '{' && bytes[i + 1] == '{' && bytes[i + 2] == '/' && bytes[i + 3] == '*') {
                start = i;
            }

            if (bytes[i] == '*' && bytes[i + 1] == '/' && bytes[i + 2] == '}' && bytes[i + 3] == '}') {
                end = i + 3;

                byte[] commentBytes = Arrays.copyOfRange(bytes, start, end + 1);
                String comment = new String(commentBytes);
                if (comment.contains("ASF")) {
                    found = true;
                    break;
                } else {
                    start = -1;
                    end = -1;
                }
            }
        }

        if (found) {
            byte[] resultBytes = new byte[bytes.length - (end - start + 1)];
            System.arraycopy(bytes, 0, resultBytes, 0, start);
            System.arraycopy(bytes, end + 1, resultBytes, start, bytes.length - end - 1);

            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(resultBytes);
            outputStream.close();
        }
    }
}
