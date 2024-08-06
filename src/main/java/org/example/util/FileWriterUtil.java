package org.example.util;

import java.io.File;
import java.io.IOException;

public class FileWriterUtil {

    public static void writeToFile(String fileName, String content) {
        try {
            File file=new File(fileName);
            if(!file.exists() && !file.createNewFile()){
                throw new RuntimeException("can't create File "+fileName);
            }
            java.io.FileWriter writer = new java.io.FileWriter(fileName, true); // true для дозаписи в конец файла
            writer.write(content);
            writer.close();
            System.out.println("Успешно записано в файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
