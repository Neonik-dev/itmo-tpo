package utils;

import lombok.Data;
import lombok.SneakyThrows;

import java.io.*;

@Data
public class CsvOutput {
    private String filePath;

    public void setFilePath(String fileName) {
        this.filePath = fileName;
    }

//    @SneakyThrows
//    public void clearFile() {
//        try (PrintWriter writer = new PrintWriter(filePath)) {
//            writer.print("");
//        }
//    }

    @SneakyThrows
    public void logging(double x, double y) {
        try (PrintStream printStream = new PrintStream(new FileOutputStream(filePath, true))) {
            printStream.printf("%s, %s\n", x, y);
        }
    }
}