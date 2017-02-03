package com.company.tools;

import java.io.*;

/**
 *  @author dokuchaev on 30.01.2017.
 */
public class FileReader{
    private File file;
    private final int BUFFER_SIZE = 80000;
    private InputStream inputStream;
    public static final String TEXT_EXTENSION = ".txt";

    public FileReader(File file) throws FileNotFoundException{
        this.file = file;
        inputStream = new BufferedInputStream(new FileInputStream(file));
    }

    public FileReader(String fileName) throws FileNotFoundException{
        this.file = new File(fileName);
        inputStream = new BufferedInputStream(new FileInputStream(file));
    }

    public File getFile(){
        return file;
    }

    public void setFile(File file){
        this.file = file;
    }

    /**
     * Reading bytes
     * @return
     * @throws IOException
     */
    public byte[] read() throws IOException{
        byte[] buffer = new byte[BUFFER_SIZE];

        if (inputStream.available() > 0){
            inputStream.read(buffer);

            return buffer;
        }

        return null;
    }

    /**
     * Check if file contains not read bytes
     * @return
     */
    public boolean available(){
        try{
            if (inputStream.available() > 0)
                return true;
        }catch (IOException e){
            return false;
        }

        return false;
    }

    public void close() throws IOException{
        inputStream.close();
    }

       /**
       *Gets all files with .txt extension
       *
       * @param fileName
       * @param extension
       * @return
       */
        public static File[] getFiles(String fileName, final String extension) {
            File directory = new File(fileName);

            if (directory.isDirectory()) {
                File[] result = directory.listFiles((dir, name) -> name.endsWith(extension));
                if (result != null && result.length > 0) {
                    return result;
                } else {
                    return new File[0];
                }
            }else{
                if (directory.isFile()) {
                    String path = directory.getPath();
                    if (path.endsWith(extension)) return new File[]{new File(fileName)};
                }
            }

            return new File[0];
        }
}
