package client;

	/*##########################################################*\
	||#========================================================#||
	||#        Copyright 2010 - The -GaMeR X- Network          #||
	||#========================================================#||
	||#       Some Rights Reserved  /  Sources/Credits:        #||
	||#--------------------------------------------------------#||
	||#       Jagex / Runescape [ http://bit.ly/19eUDy  ]      #||
	||#             JGMS [ http://bit.ly/6mipum  ]             #||
	||#          -GaMeR X- [ http://bit.ly/4QZcPI ]            #||
	||#--------------------------------------------------------#||
	||#    I would also like to thank these great friends:     #||
	||#--------------------------------------------------------#||
	||#        luvgirl12345 -  Stewie - Galkon - SWAT          #||
	||#          lolcuz212 - Viruzeno - + many more            #||
	||##########################################################||
	||#   File: FileOperations.java - Read/Write for Sprites   #||
	\*##########################################################*/
//imports
	import java.io.*;

public class FileOperations {

	public FileOperations() {
	}

	public static final byte[] ReadFile(String s) {
		try {
			File file = new File(s);
			int i = (int)file.length();
			byte abyte0[] = new byte[i];
			DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new FileInputStream(s)));
			datainputstream.readFully(abyte0, 0, i);
			datainputstream.close();
			TotalRead++;
			return abyte0;
		} catch(Exception exception) {
			System.out.println((new StringBuilder()).append("Read Error: ").append(s).toString());
		}
		return null;
	}

	public static final void WriteFile(String s, byte abyte0[]) {
		try {
			(new File((new File(s)).getParent())).mkdirs();
			FileOutputStream fileoutputstream = new FileOutputStream(s);
			fileoutputstream.write(abyte0, 0, abyte0.length);
			fileoutputstream.close();
			TotalWrite++;
			CompleteWrite++;
		} catch(Throwable throwable) {
			System.out.println((new StringBuilder()).append("Write Error: ").append(s).toString());
		}
	}

	public static int TotalRead = 0;
	public static int TotalWrite = 0;
	public static int CompleteWrite = 0;
	
}