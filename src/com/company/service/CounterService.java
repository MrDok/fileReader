package com.company.service;

import com.company.tools.CharFinder;
import com.company.tools.FileReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by user on 31.01.2017.
 */

/**
 * Executor service
 */
public class CounterService{
    public static final int THREAD_POOL_SIZE = 5;
    private static AtomicLong count;
    public long countChars(File file, Character userChar) throws IOException, InterruptedException{
        count = new AtomicLong(0);
        if (file != null && userChar != null){

            FileReader fileReader = null;

            fileReader = new FileReader(file);
            ExecutorService service = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
            List<Future<?>> futures = new ArrayList<>(THREAD_POOL_SIZE);
            while(fileReader.available()){
                final byte[] bytes = fileReader.read();
                Future<?> future = service.submit(new Runnable(){
                    @Override
                    public void run(){
                        count.addAndGet(CharFinder.charCount(bytes, userChar));
                    }
                });

                futures.add(future);
            }

            boolean allDone = false;
            while(!allDone){
                allDone = !fileReader.available();
                for (Future future : futures){
                    allDone &= future.isDone();
//                        Thread.sleep(10);
                }
            }

            service.shutdown();

            try{
                if (fileReader != null){
                    fileReader.close();
                }
            }catch (IOException e){
                System.out.println("Problems with closing file " + fileReader.getFile().getName());
            }
        }

        return count.get();
    }
}
