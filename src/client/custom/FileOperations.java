package client.custom;

// Fully Renamed.

import java.io.*;

public class FileOperations {

    public static int totalRead = 0;
    public static int totalWrite = 0;
    public static int completeWrite = 0;

    public FileOperations() {
    }
    
    public static boolean FileExists(String filePath) {
        return new File(filePath).exists();
    }
    
    public static byte[] ReadFile(String fileToRead) {
        try {
            File file = new File(fileToRead);
            int fileLength = (int) file.length();
            byte read[] = new byte[fileLength];
            DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new FileInputStream(fileToRead)));
            datainputstream.readFully(read, 0, fileLength);
            datainputstream.close();
            totalRead++;
            return read;
        } catch (Exception ignored) {
            System.out.println((new StringBuilder()).append("Read Error: ").append(fileToRead).toString());
            return null;
        }
    }

    public static void WriteFile(String fileToWrite, byte dataToWrite[]) {
        try {
            (new File((new File(fileToWrite)).getParent())).mkdirs();
            FileOutputStream fileoutputstream = new FileOutputStream(fileToWrite);
            fileoutputstream.write(dataToWrite, 0, dataToWrite.length);
            fileoutputstream.close();
            totalWrite++;
            completeWrite++;
        } catch (Throwable ignored) {
            System.out.println((new StringBuilder()).append("Write Error: ").append(fileToWrite).toString());
        }
    }
}
