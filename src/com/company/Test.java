package com.company;

import com.company.service.CounterService;
import com.company.tools.FileReader;
import com.company.tools.TimeConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by user on 31.01.2017.
 */
public class Test{
    public static void main(String[] args){
        if (args.length > 1){
            Character userChar = args[1].charAt(0);

            CounterService service = new CounterService();

            File[] files = FileReader.getFiles("tests/", FileReader.TEXT_EXTENSION);

            for (File file : files){
                Long start = new Date().getTime();
                try{
                    System.out.println(file.getName());
                    System.out.println("Counts of '" + userChar + "': " + service.countChars(file, userChar));
                }catch (InterruptedException | IOException e){
                    e.printStackTrace();
                }

                Long end = new Date().getTime();

                System.out.println("Used time: " + TimeConverter.convertMs(end - start) + "\n");
            }
        }
    }
}
