package com.company.tools;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;
import java.util.Date;
import java.util.Random;

/**
 *  @author dokuchaev on 31.01.2017.
 */
public class FileGenerator{
    /**
     * file generating method
     * @param size in megabytes
     * @return
     */
    public static void generateFile(int size) throws IOException{
        String fileName = (new Date()).getTime() + ".txt";

        generateFile(fileName, size);
    }

    public static void generateFile(String name, int size) throws IOException{
        OutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(Paths.get(name), CREATE_NEW, APPEND));
        Random random = new Random();

        for (int i = 0; i < size; i++){
            for (int j = 0; j < 1024; j++){
                for (int k = 0; k < 1024; k++){
                    outputStream.write((byte) random.nextInt(122));
                }
                outputStream.write(new byte[]{13, 10});
            }
        }

        outputStream.close();
    }

    public static void main(String[] args){
       try{
           FileGenerator.generateFile("5Mbytes.txt", 5);
           FileGenerator.generateFile("20Mbytes.txt", 20);
           FileGenerator.generateFile("100Mbytes.txt", 100);
           FileGenerator.generateFile("500Mbytes.txt", 500);
       }catch (IOException e){
           System.out.println("Problems with creating files!!");
       }
    }
}
