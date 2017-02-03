package com.company.tools;

import java.io.UnsupportedEncodingException;

/**
 * @author dokuchaev on 30.01.2017.
 */
public class CharFinder{
    public static long charCount(byte[] array, Character symbol){
        if (array != null && symbol != null){
            try{
                byte bSymbol = symbol.toString().getBytes("UTF8")[0];

                return charCount(array, bSymbol);
            }catch(IndexOutOfBoundsException e){
                System.out.println("Problems with reading user symbol");
            }catch (UnsupportedEncodingException e){
                System.out.println("Couldn't read symbol in UTF-8 encoding");
            }
        }

        return -1L;
    }

    public static long charCount(byte[] array, byte symbol){
        long count = 0L;

        if (array != null){
            for (byte element : array){
                if (element == symbol){
                    count++;
                }
            }
        }

        return count;
    }
}
