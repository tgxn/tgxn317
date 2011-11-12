package map.data;

import java.io.*;

public class FileOperations {

    public static int TotalRead = 0;
    public static int TotalWrite = 0;
    public static int CompleteWrite = 0;

    public FileOperations() {
    }

    public static byte[] ReadFile(String s) {
        try {
            File file = new File(s);
            int i = (int) file.length();
            byte abyte0[] = new byte[i];
            DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new FileInputStream(s)));
            datainputstream.readFully(abyte0, 0, i);
            datainputstream.close();
            TotalRead++;
            return abyte0;
        } catch (Exception e) {
            System.out.println("Read Error: " + s);
        }
        return null;
    }

    public static void WriteFile(String s, byte abyte0[]) {
        try {
            (new File((new File(s)).getParent())).mkdirs();
            FileOutputStream fileoutputstream = new FileOutputStream(s);
            fileoutputstream.write(abyte0, 0, abyte0.length);
            fileoutputstream.close();
            TotalWrite++;
            CompleteWrite++;
        } catch (Throwable t) {
            System.out.println("Write Error: " + s);
        }
    }

    public static boolean FileExists(String file) {
        File f = new File(file);
        return f.exists();
    }
}