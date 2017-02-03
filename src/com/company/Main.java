package com.company;

import com.company.service.CounterService;
import com.company.tools.TimeConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class Main {
    public static void main(String[] args){

        if (args.length > 1){
            Character userChar = args[1].charAt(0);

            CounterService service = new CounterService();

            File file = new File(args[0]);
            try{
                System.out.println("File name: " + args[0]);

                Long start = new Date().getTime();
                long counts = service.countChars(file, userChar);
                Long end = new Date().getTime();

                System.out.println("Counts of '" + userChar + "': " + counts);
                System.out.println("Used time: " + TimeConverter.convertMs(end - start));
            }catch (InterruptedException | UnsupportedEncodingException e){
                System.out.println("Inner error");
            }catch(FileNotFoundException e){
                System.out.println("File '" + args[0] + "' not found!");
            }catch (IOException e){
                System.out.println("Some problems with reading file");
            }
        }else{
            System.out.println("Wrong input parameters!");
        }
    }
}
