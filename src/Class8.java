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
	||#              File: Class8.java - Items                 #||
	\*##########################################################*/
	
//imports
	import sign.signlink;
	import java.io.*;

final class Class8 {

	public static void dumpItems() {
		for(int i = 0; i < 9250; i++) {
			Class8 class8 = method198(i);
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter("itemdump.cfg", true));
				bw.write("====================");
				bw.newLine();
				bw.write("Item Name: "+class8.aString170);
				bw.newLine();
				bw.write("Item ID: "+i);
				bw.newLine();
				if(class8.anIntArray156 != null) {
					for(int i2 = 0; i2 < class8.anIntArray156.length; i2++) {
						if(i2 == 0) {
							bw.write("Original model colors: ");
						}
						if(i2 != class8.anIntArray156.length - 1) {
							bw.write(""+class8.anIntArray156[i2]+", ");
						} else {
							bw.write(""+class8.anIntArray156[i2]);
							bw.newLine();
						}
					}
				}
				if(class8.anIntArray160 != null) {
					for(int i2 = 0; i2 < class8.anIntArray160.length; i2++) {
						if(i2 == 0) {
							bw.write("Changed model colors: ");
						}
						if(i2 != class8.anIntArray160.length - 1) {
							bw.write(""+class8.anIntArray160[i2]+", ");
						} else {
							bw.write(""+class8.anIntArray160[i2]);
							bw.newLine();
						}
					}
				}
				bw.write("class8.anInt174 = "+class8.anInt174+";");
				bw.newLine();
				bw.write("class8.anInt181 = "+class8.anInt181+";");
				bw.newLine();
				bw.write("class8.anInt190 = "+class8.anInt190+";");
				bw.newLine();
				bw.write("class8.anInt198 = "+class8.anInt198+";");
				bw.newLine();
				bw.write("class8.anInt204 = "+class8.anInt204+";");
				bw.newLine();
				bw.write("class8.anInt169 = "+class8.anInt169+";");
				bw.newLine();
				bw.write("class8.anInt194 = "+class8.anInt194+";");
				bw.newLine();
				bw.write("class8.anInt165 = "+class8.anInt165+";");
				bw.newLine();
				bw.write("class8.anInt200 = "+class8.anInt200+";");
				bw.newLine();
				bw.write("class8.anInt188 = "+class8.anInt188+";");
				bw.newLine();
				bw.write("class8.anInt164 = "+class8.anInt164+";");
				bw.newLine();
				bw.write("class8.anInt175 = "+class8.anInt175+";");
				bw.newLine();
				bw.write("class8.anInt197 = "+class8.anInt197+";");
				bw.newLine();
				bw.flush();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				if (bw != null) try {
					bw.close();
				} catch (IOException ioe2) { }
			}
		}
	}

	public static void dumpCfg() {
		boolean delete = (new File("item.cfg")).delete();
		for(int i = 0; i < 9200; i++) {
			Class8 class8 = method198(i);
			BufferedWriter bw = null;
			String des = "";
			if(class8.aByteArray178 != null)
				des = new String(class8.aByteArray178);
			else
				des = "Its a "+class8.aString170;
			try {
				BufferedWriter bufferedwriter;
				bufferedwriter = null;
				bufferedwriter = new BufferedWriter(new FileWriter("item.cfg", true));
				bufferedwriter.write((new StringBuilder()).append("item = ").append(i).append("	").append(class8.aString170).append("	").append(des).append("	").append(class8.anInt155).append("	").append(class8.anInt155).append("	").append(class8.anInt155).append("	").append("0").append("	").append("0").append("	").append("0").append("	").append("0").append("	").append("0").append("	").append("0").append("	").append("0").append("	").append("0").append("	").append("0").append("	").append("0").append("	").append("0").append("	").append("0").toString());
				bufferedwriter.newLine();
				bufferedwriter.flush();
			} catch(Exception e) { }
		}
	}

	public static void dumpItemsList() {
		for(int i = 0; i < 9200; i++) {
			Class8 class8 = method198(i);
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter("ItemList.txt", true));
				if(class8.aString170 != null) {
					bw.write("ID: "+i+"\t\tName: "+class8.aString170);
					bw.newLine();
					bw.flush();
					bw.close();
				}
			} catch (IOException ioe2) { }
		}
	}

	public static void dumpNewItems() {
		for(int i = 9000; i < 9200; i++) {
			Class8 class8 = method198(i);
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter("Item Dump.txt", true));
				if(class8.aString170 != null) {
					bw.write("<item members='true'  name='" + class8.aString170 + "'  type='"+i+"'> </item>");
					bw.newLine();
					bw.flush();
					bw.close();
				}
			} catch (IOException ioe2) { }
		}
	}

	public static final void method191(int i) { //nullLoader
		aClass12_159 = null;
		aClass12_158 = null;
		anIntArray195 = null;
		if(i >= 0)
			aBoolean187 = !aBoolean187;
		aClass8Array172 = null;
		aClass30_Sub2_Sub2_183 = null;
	}

	public final boolean method192(int i, int j) {
		if(i != -2836)
			aBoolean186 = !aBoolean186;
		int k = anInt175;
		int l = anInt166;
		if(j == 1) {
			k = anInt197;
			l = anInt173;
		}
		if(k == -1)
			return true;
		boolean flag = true;
		if(!Model.method463(k))
			flag = false;
		if(l != -1 && !Model.method463(l))
			flag = false;
		return flag;
	}

	public static final void method193(Class44 class44) { //unpackConfig
		aClass30_Sub2_Sub2_183 = new Stream(class44.method571("obj.dat", null));
		Stream class30_sub2_sub2 = new Stream(class44.method571("obj.idx", null));
		anInt203 = class30_sub2_sub2.readUnsignedWord();
		anIntArray195 = new int[anInt203+400000];
		int i = 2;
		for(int j = 0; j < anInt203; j++) {
			anIntArray195[j] = i;
			i += class30_sub2_sub2.readUnsignedWord();
		}
		aClass8Array172 = new Class8[10];
		for(int k = 0; k < 10; k++)
			aClass8Array172[k] = new Class8();
	}

	public final Model method194(int i, int j) {
		while(i >= 0)
			aBoolean186 = !aBoolean186;
		int k = anInt175;
		int l = anInt166;
		if(j == 1) {
			k = anInt197;
			l = anInt173;
		}
		if(k == -1)
			return null;
		Model class30_sub2_sub4_sub6 = Model.method462(anInt171, k);
		if(l != -1) {
			Model class30_sub2_sub4_sub6_1 = Model.method462(anInt171, l);
			Model aclass30_sub2_sub4_sub6[] = {
				class30_sub2_sub4_sub6, class30_sub2_sub4_sub6_1
			};
			class30_sub2_sub4_sub6 = new Model(2, aclass30_sub2_sub4_sub6, -38);
		}
		if(anIntArray156 != null) {
			for(int i1 = 0; i1 < anIntArray156.length; i1++)
				class30_sub2_sub4_sub6.method476(anIntArray156[i1], anIntArray160[i1]);
		}
		return class30_sub2_sub4_sub6;
	}

	public final boolean method195(int i, int j) {
		if(i != 40903)
			aBoolean206 = !aBoolean206;
		int k = anInt165;
		int l = anInt188;
		int i1 = anInt185;
		if(j == 1) {
			k = anInt200;
			l = anInt164;
			i1 = anInt162;
		}
		if(k == -1)
			return true;
		boolean flag = true;
		if(!Model.method463(k))
			flag = false;
		if(l != -1 && !Model.method463(l))
			flag = false;
		if(i1 != -1 && !Model.method463(i1))
			flag = false;
		return flag;
	}

	public final Model method196(boolean flag, int i) {
		if(flag)
			throw new NullPointerException();
		int j = anInt165;
		int k = anInt188;
		int l = anInt185;
		if(i == 1) {
			j = anInt200;
			k = anInt164;
			l = anInt162;
		}
		if(j == -1)
			return null;
		Model class30_sub2_sub4_sub6 = Model.method462(anInt171, j);
		if(k != -1)
			if(l != -1) {
				Model class30_sub2_sub4_sub6_1 = Model.method462(anInt171, k);
				Model class30_sub2_sub4_sub6_3 = Model.method462(anInt171, l);
				Model aclass30_sub2_sub4_sub6_1[] = {
					class30_sub2_sub4_sub6, class30_sub2_sub4_sub6_1, class30_sub2_sub4_sub6_3
				};
				class30_sub2_sub4_sub6 = new Model(3, aclass30_sub2_sub4_sub6_1, -38);
			} else {
				Model class30_sub2_sub4_sub6_2 = Model.method462(anInt171, k);
				Model aclass30_sub2_sub4_sub6[] = {
					class30_sub2_sub4_sub6, class30_sub2_sub4_sub6_2
				};
				class30_sub2_sub4_sub6 = new Model(2, aclass30_sub2_sub4_sub6, -38);
			}
		if(i == 0 && aByte205 != 0)
			class30_sub2_sub4_sub6.method475(0, aByte205, 16384, 0);
		if(i == 1 && aByte154 != 0)
			class30_sub2_sub4_sub6.method475(0, aByte154, 16384, 0);
		if(anIntArray156 != null) {
			for(int i1 = 0; i1 < anIntArray156.length; i1++)
				class30_sub2_sub4_sub6.method476(anIntArray156[i1], anIntArray160[i1]);
		}
		return class30_sub2_sub4_sub6;
	}

	public final void method197() { //setDefaults
		anInt174 = 0;
		aString170 = null;
		aByteArray178 = null;
		anIntArray156 = null;
		anIntArray160 = null;
		anInt181 = 12000;
		anInt190 = 0;
		anInt198 = 0;
		anInt204 = 0;
		anInt169 = 0;
		anInt194 = 0;
		anInt199 = -1;
		aBoolean176 = false;
		anInt155 = 1;
		aBoolean161 = false;
		aStringArray168 = null;
		aStringArray189 = null;
		anInt165 = -1;
		anInt188 = -1;
		aByte205 = 0;
		anInt200 = -1;
		anInt164 = -1;
		aByte154 = 0;
		anInt185 = -1;
		anInt162 = -1;
		anInt175 = -1;
		anInt166 = -1;
		anInt197 = -1;
		anInt173 = -1;
		anIntArray193 = null;
		anIntArray201 = null;
		anInt179 = -1;
		anInt163 = -1;
		anInt167 = 128;
		anInt192 = 128;
		anInt191 = 128;
		anInt196 = 0;
		anInt184 = 0;
		anInt202 = 0;
	}

	public static final Class8 method198(int i) { //forID
		for(int j = 0; j < 10; j++) {
			if(aClass8Array172[j].anInt157 == i) {
				return aClass8Array172[j];
			}
		}
		anInt180 = (anInt180 + 1) % 10;
		Class8 class8 = aClass8Array172[anInt180];
		aClass30_Sub2_Sub2_183.currentOffset = anIntArray195[i];
		class8.anInt157 = i;
		class8.method197();
		class8.method203(true, aClass30_Sub2_Sub2_183);
		
		
		
		
		
if(i == 9001) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray160[0] = 926;
class8.anIntArray156[1] = 54503;
class8.anIntArray160[1] = 926;
class8.anIntArray156[2] = 54183;
class8.anIntArray160[2] = 926;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[3] = 926;
class8.anInt174 = 4158; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4181; //Male
class8.anInt200 = 7178; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Attack Cape";
class8.aByteArray178 = "Its A attack Cape".getBytes();
}
if(i == 9002) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 9152;
class8.anIntArray160[1] = 7104;
class8.anIntArray160[2] = 7104;
class8.anIntArray160[3] = 796;
class8.anInt174 = 4158; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4181; //Male
class8.anInt200 = 7178; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Attack Cape(t)";
class8.aByteArray178 = "Its A attack Cape(t)".getBytes();
}
if(i == 9003)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray160[0] = 912;
class8.anIntArray156[1] = 22464;
class8.anIntArray160[1] = 912;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[2] = 912;
class8.anInt174 = 4203; //Model ID
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Attack Hood";
class8.aByteArray178 = "Its A attack Hood".getBytes();
}
if(i == 9092) {
	class8.aStringArray189 = new String[5];
	class8.aStringArray189[1] = "Wear";
	class8.anIntArray156 = new int[1];
    class8.anIntArray160 = new int [1];
	class8.anIntArray156[0] = 933;
	class8.anIntArray160[0] = 36133;
	class8.anInt174 = 2537;
	class8.anInt181 = 540;
	class8.anInt190 = 72;
	class8.anInt198 = 136;
	class8.anInt204 = 0;
	class8.anInt169 = 0;
	class8.anInt194 = -3;
	class8.anInt165 = 189;
	class8.anInt200 = 366;
	class8.anInt175 = 69;
	class8.anInt197 = 127;
	class8.aString170 = "Rune hat";
	class8.aByteArray178 = "A gift from santa".getBytes();
}
if(i == 6570){
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[1];
            class8.anIntArray160 = new int[1];
			class8.anIntArray156[0] = 0;
			class8.anIntArray160[0] = 0;
			class8.anInt174 = 9631;//item look
			class8.anInt181 = 2000;
			class8.anInt190 = 400;
			class8.anInt198 = 0;
			class8.anInt204 = 2047;
			class8.anInt169 = -8;
			class8.anInt194 = 12;
			class8.anInt197 = -1;
            class8.aString170 = "Fire Cape";
            class8.aByteArray178 = "A cape of fire.".getBytes();
        }
        if(i == 9093)    //WH1P!!!!
		        {
		            class8.aStringArray189 = new String[5];
		            class8.aStringArray189[1] = "Wear"; //this is the new option to equipt, can change to w/e u want
		            class8.anIntArray156 = new int[1];
		        class8.anIntArray160 = new int[1];
		class8.anIntArray156[0] = 528;  //original color
		class8.anIntArray160[0] = 926; //changed color
		class8.anInt174 =  5412;
		class8.anInt181 =  840;
		class8.anInt190 =  280;
		class8.anInt198 =  0;
		class8.anInt204 =  0;
		class8.anInt169 =  -2;
		class8.anInt194 =  56;
		class8.anInt165 =  5409;
		class8.anInt200 =  5409;
		class8.anInt188 =  -1;
		class8.anInt164 =  -1;
		class8.anInt175 =  -1;
		class8.anInt197 =  -1;
		            class8.aString170 = "Dragon Whip";
		            class8.aByteArray178 = "A whip made of Dragon".getBytes();
		        }
		        if(i == 9095){
				            class8.aStringArray189 = new String[5];
				            class8.aStringArray189[1] = "Wear";
				            class8.anIntArray156 = new int[1];
				            class8.anIntArray160 = new int[1];
							class8.anIntArray156[0] = 0;
							class8.anIntArray160[0] = 0;
							class8.anInt174 = 7354;//item look
							class8.anInt181 = 1957;
							class8.anInt190 = 498;
							class8.anInt198 = 484;
							class8.anInt204 = 0;
							class8.anInt169 = -1;
							class8.anInt194 = -1;
							class8.anInt165 = 7355;
							class8.anInt200 = 7244;
							class8.anInt175 = -1;
							class8.anInt197 = -1;
				            class8.aString170 = "Bandos godsword ";
				            class8.aByteArray178 = "A great weapon, used by the best warriors.".getBytes();
        }
        if(i == 9103){
		            class8.aStringArray189 = new String[5];
		            class8.aStringArray189[1] = "Wear";
		            class8.anIntArray156 = new int[1];
		            class8.anIntArray160 = new int[1];
					class8.anIntArray156[0] = 0;
					class8.anIntArray160[0] = 0;
					class8.anInt174 = 13422;//item look
					class8.anInt181 = 724;
					class8.anInt190 = 171;
					class8.anInt198 = 2039;
					class8.anInt204 = 144;
					class8.anInt169 = 3;
					class8.anInt194 = 3;
					class8.anInt165 = 13423;
					class8.anInt200 = 19951;
					class8.anInt175 = 28259;
					class8.anInt197 = 28260;
		            class8.aString170 = "Bandos boots";
		            class8.aByteArray178 = "A nice set of Bandos boots, worn once by the god Bandos.".getBytes();
        }
             if(i == 9105)
		        {
		            class8.aStringArray189 = new String[5];
		            class8.aStringArray189[1] = "Wear";
		            class8.anIntArray156 = new int[1];
		            class8.anIntArray160 = new int[1];
		            class8.anIntArray156[0] = 0;
		            class8.anIntArray160[0] = 0;
		            class8.anInt174 = 13426;
		            class8.anInt181 = 1957;
		            class8.anInt190 = 498;
		            class8.anInt198 = 484;
		            class8.anInt204 = 0;
		            class8.anInt169 = -1;
		            class8.anInt194 = -1;
		            class8.anInt165 = 13427;
		            class8.anInt200 = 7295;
		            class8.anInt175 = -1;
		            class8.anInt197 = -1;
		            class8.aString170 = "Saradomin Godsword";
		            class8.aByteArray178 = "A saradomin godsword, once used by saradomin himself.".getBytes();
		        }
		       if(i == 9106)
		        {
		            class8.aStringArray189 = new String[5];
		            class8.aStringArray189[1] = "Wear";
		            class8.anIntArray156 = new int[1];
		            class8.anIntArray160 = new int[1];
		            class8.anIntArray156[0] = 528;
		            class8.anIntArray160[0] = 100;
		            class8.anInt174 = 5412;
		            class8.anInt181 = 840;
		            class8.anInt190 = 280;
		            class8.anInt198 = 0;
		            class8.anInt204 = 0;
		            class8.anInt169 = -2;
		            class8.anInt194 = 56;
		            class8.anInt165 = 5409;
		            class8.anInt200 = 5409;
		            class8.anInt188 = -1;
		            class8.anInt164 = -1;
		            class8.anInt175 = -1;
		            class8.anInt197 = -1;
		            class8.aString170 = "White whip";
		            class8.aByteArray178 = "Made By The White Knights.".getBytes();
        }
        if(i == 9107)  // change this if you need to "item number"
		{
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Wield";//New option
		class8.anIntArray156 = new int[0];
		class8.anIntArray160 = new int[0];
		class8.anInt174 = 7212; //Model ID
		class8.anInt181 = 1957;
		class8.anInt190 = 498;
		class8.anInt198 = 484;
		class8.anInt204 = 0;
		class8.anInt169 = -1;
		class8.anInt194 = -1;
		class8.anInt175 = -1;
		class8.anInt197 = -1;
		class8.anInt165 = 7209;//male wearing
		class8.anInt200 = 7209;//female wearing
		class8.aBoolean176 = false;//Stackable
		class8.aString170 = "Armadyl Godsword";//Name of the new item
		class8.aByteArray178 = "A heavy sword".getBytes();//examin info
		        }
		if(i == 9108)  // change this if you need to "item number"
		{
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Wield";//New option
		class8.anIntArray156 = new int[0];
		class8.anIntArray160 = new int[0];
		class8.anInt174 = 7213; //Model ID
		class8.anInt181 = 1957;
		class8.anInt190 = 498;
		class8.anInt198 = 484;
		class8.anInt204 = 0;
		class8.anInt169 = -1;
		class8.anInt194 = -1;
		class8.anInt175 = -1;
		class8.anInt197 = -1;
		class8.anInt165 = 7206;//male wearing
		class8.anInt200 = 7206;//female wearing
		class8.aBoolean176 = false;//Stackable
		class8.aString170 = "Zamorak Godsword";//Name of the new item
		class8.aByteArray178 = "A heavy sword".getBytes();//examin info
        }
        if(i == 9109) {
				class8.aStringArray189 = new String[5];
				class8.aStringArray189[1] = "Wear";
				class8.anIntArray156 = new int[3];
				class8.anIntArray160 = new int [3];
				class8.anIntArray156[0] = 43127;
				class8.anIntArray160[0] = 43968;
				class8.anIntArray156[1] = 38119;
				class8.anIntArray160[1] = 43968;
				class8.anIntArray156[2] = 36975;
				class8.anIntArray160[2] = 43968;
			class8.anInt174 = 5198;//Drop model & inv model
			class8.anInt181 = 1900;
			class8.anInt190 = 500;
			class8.anInt198 = 500;
			class8.anInt204 = 0;
		        class8.anInt169 = 3;
		        class8.anInt194 = 6;
			class8.anInt200 = 5196;//female wearing
			class8.anInt165 = 5196;//male wearing
		        class8.anInt175 = -1;
		        class8.anInt197 = -1;
			class8.aString170 = "Blue Crystal shield";
			class8.aByteArray178 = "Blue Crystal shield by Ric914".getBytes();
}
if(i == 9110) { //D legs
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Wear";
		class8.anIntArray156 = new int[2];
		class8.anIntArray160 = new int [2];
		class8.anIntArray156[0] = 926;
		class8.anIntArray160[0] = 43934;
		class8.anIntArray156[1] = 912;
		class8.anIntArray160[1] = 391;//43920
	class8.anInt181 = 1740;
	class8.anInt190 = 444;
	class8.anInt198 = 0;
	class8.anInt204 = 0;
	class8.anInt169 = 0;
	class8.anInt194 = -8;
		class8.anInt165 = 5024;
		class8.anInt200 = 5025;
		class8.anInt174 = 5026;
	class8.anInt188 = -1;
	class8.anInt164 = -1;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
	class8.aString170 = "Dragon Platelegs [b]";
	class8.aByteArray178 = "Blue dragon platelegs by jukk".getBytes();
}
if(i == 9111) { //D chain
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Wear";
		class8.anIntArray156 = new int[5];
		class8.anIntArray160 = new int [5];
		class8.anIntArray156[0] = 914;
		class8.anIntArray160[0] = 43920;
		class8.anIntArray156[1] = 918;
		class8.anIntArray160[1] = 43920;
		class8.anIntArray156[2] = 922;
		class8.anIntArray160[2] = 43934;
		class8.anIntArray156[3] = 391;
		class8.anIntArray160[3] = 43934;
		class8.anIntArray156[4] = 917;
		class8.anIntArray160[4] = 43934;
	class8.anInt181 = 1100;
	class8.anInt190 = 568;
	class8.anInt198 = 0;
	class8.anInt204 = 0;
	class8.anInt169 = 0;
	class8.anInt194 = 2;
		class8.anInt165 = 3820;
		class8.anInt200 = 3821;
		class8.anInt174 = 3823;
	class8.anInt188 = 156;
	class8.anInt164 = 337;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
	class8.aString170 = "Dragon Chain [b]";
	class8.aByteArray178 = "Blue dragon Chain by jukk".getBytes();
}
if(i == 9112) {
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Wear";
		class8.anIntArray156 = new int[22];
		class8.anIntArray160 = new int [22];
		class8.anIntArray156[0] = 78;
		class8.anIntArray160[0] = 910;
		class8.anIntArray156[1] = 20;
		class8.anIntArray160[1] = 910;
		class8.anIntArray156[2] = 24;
		class8.anIntArray160[2] = 920;
		class8.anIntArray156[3] = 82;
		class8.anIntArray160[3] = 920;
		class8.anIntArray156[4] = 86;
		class8.anIntArray160[4] = 930;
		class8.anIntArray156[5] = 28;
		class8.anIntArray160[5] = 930;
		class8.anIntArray156[6] = 53;
		class8.anIntArray160[6] = 905;
		class8.anIntArray156[7] = 49;
		class8.anIntArray160[7] = 920;
		class8.anIntArray156[8] = 57;
		class8.anIntArray160[8] = 920;
		class8.anIntArray156[9] = 45;
		class8.anIntArray160[9] = 920;
		class8.anIntArray156[10] = 33;
		class8.anIntArray160[10] = 920;
		class8.anIntArray156[11] = 37;
		class8.anIntArray160[11] = 905;
		class8.anIntArray156[12] = 41;
		class8.anIntArray160[12] = 920;
		class8.anIntArray156[13] = 305;
		class8.anIntArray160[13] = 10;
		class8.anIntArray156[14] = 22;
		class8.anIntArray160[14] = 1;
		class8.anIntArray156[15] = 3546;
		class8.anIntArray160[15] = 30;//903
		class8.anIntArray156[16] = 3776;
		class8.anIntArray160[16] = 30;//903
		class8.anIntArray156[17] = 3996;
		class8.anIntArray160[17] = 30;//901
		class8.anIntArray156[18] = 99;
		class8.anIntArray160[18] = 920;
		class8.anIntArray156[19] = 3895;
		class8.anIntArray160[19] = 30;//903
		class8.anIntArray156[20] = 947;
		class8.anIntArray160[20] = 100;
		class8.anIntArray156[21] = 51;
		class8.anIntArray160[21] = 920;
	class8.anInt181 = 1800;
	class8.anInt190 = 400;
	class8.anInt198 = 100;
	class8.anInt204 = 20;
        class8.anInt169 = 1;
        class8.anInt194 = 1;
		class8.anInt174 = 8;
		class8.anInt200 = 7;
		class8.anInt165 = 7;
        class8.anInt175 = -1;
        class8.anInt197 = -1;
	class8.aString170 = "Red Dragonfire Shield";
	class8.aByteArray178 = "Red Dragonfire Shield".getBytes();
}
if(i == 9113)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anInt174 = 10910;
class8.anInt181 = 1744;
class8.anInt190 = 285;
class8.anInt198 = 1508;
class8.anInt169 = -1;
class8.anInt194 = 46;
class8.anInt165 = 10911;
class8.anInt200 = 10911;
class8.aString170 = "Pharaoh's sceptre";
class8.aByteArray178 = "This sceptre is fully charged.".getBytes();
	}

if(i == 9114) {
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Wear";
		class8.anIntArray156 = new int[2];
		class8.anIntArray160 = new int [2];
		class8.anIntArray156[0] = 29456;
		class8.anIntArray160[0] = 0;
		class8.anIntArray156[1] = 127;
		class8.anIntArray160[1] = 7097;
	class8.anInt181 = 1000;
	class8.anInt190 = 0;
	class8.anInt198 = 300;
	class8.anInt204 = 0;
        class8.anInt169 = -14;
        class8.anInt194 = 27;
		class8.anInt174 = 1;
		class8.anInt200 = 1;
		class8.anInt165 = 1;
        class8.anInt175 = -1;
        class8.anInt197 = -1;
	class8.aString170 = "Briefcase";
	class8.aByteArray178 = "Briefcase by jukk".getBytes();
}

if(i == 9115) {
				class8.aStringArray189 = new String[5];
				class8.aStringArray189[1] = "Wear";
				class8.anIntArray156 = new int[3];
				class8.anIntArray160 = new int [3];
				class8.anIntArray156[0] = 43127;
				class8.anIntArray160[0] = 128;
				class8.anIntArray156[1] = 38119;
				class8.anIntArray160[1] = 128;
				class8.anIntArray156[2] = 36975;
				class8.anIntArray160[2] = 128;
			class8.anInt174 = 5198;//Drop model & inv model
			class8.anInt181 = 1900;
			class8.anInt190 = 500;
			class8.anInt198 = 500;
			class8.anInt204 = 0;
		        class8.anInt169 = 3;
		        class8.anInt194 = 6;
			class8.anInt200 = 5196;//female wearing
			class8.anInt165 = 5196;//male wearing
		        class8.anInt175 = -1;
		        class8.anInt197 = -1;
			class8.aString170 = "Black Crystal shield";
			class8.aByteArray178 = "Black Crystal shield by Ric914".getBytes();
}

if(i == 9116) {
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Wear";
		class8.anIntArray156 = new int[22];
		class8.anIntArray160 = new int [22];
		class8.anIntArray156[0] = 78;
		class8.anIntArray160[0] = 43934;
		class8.anIntArray156[1] = 20;
		class8.anIntArray160[1] = 43934;
		class8.anIntArray156[2] = 24;
		class8.anIntArray160[2] = 43934;
		class8.anIntArray156[3] = 82;
		class8.anIntArray160[3] = 43934;
		class8.anIntArray156[4] = 86;
		class8.anIntArray160[4] = 43934;
		class8.anIntArray156[5] = 28;
		class8.anIntArray160[5] = 43934;
		class8.anIntArray156[6] = 53;
		class8.anIntArray160[6] = 905;
		class8.anIntArray156[7] = 49;
		class8.anIntArray160[7] = 43934;
		class8.anIntArray156[8] = 57;
		class8.anIntArray160[8] = 43934;
		class8.anIntArray156[9] = 45;
		class8.anIntArray160[9] = 43934;
		class8.anIntArray156[10] = 33;
		class8.anIntArray160[10] = 43934;
		class8.anIntArray156[11] = 37;
		class8.anIntArray160[11] = 905;
		class8.anIntArray156[12] = 41;
		class8.anIntArray160[12] = 43934;
		class8.anIntArray156[13] = 305;
		class8.anIntArray160[13] = 10;
		class8.anIntArray156[14] = 22;
		class8.anIntArray160[14] = 1;
		class8.anIntArray156[15] = 3546;
		class8.anIntArray160[15] = 30;//903
		class8.anIntArray156[16] = 3776;
		class8.anIntArray160[16] = 30;//903
		class8.anIntArray156[17] = 3996;
		class8.anIntArray160[17] = 30;//901
		class8.anIntArray156[18] = 99;
		class8.anIntArray160[18] = 43934;
		class8.anIntArray156[19] = 3895;
		class8.anIntArray160[19] = 30;//903
		class8.anIntArray156[20] = 947;
		class8.anIntArray160[20] = 100;
		class8.anIntArray156[21] = 51;
		class8.anIntArray160[21] = 43934;
	class8.anInt181 = 1800;
	class8.anInt190 = 400;
	class8.anInt198 = 100;
	class8.anInt204 = 20;
        class8.anInt169 = 1;
        class8.anInt194 = 1;
		class8.anInt174 = 8;
		class8.anInt200 = 7;
		class8.anInt165 = 7;
        class8.anInt175 = -1;
        class8.anInt197 = -1;
	class8.aString170 = "Blue Dragonfire Shield";
	class8.aByteArray178 = "Blue Dragonfire Shield".getBytes();
}

		if(i == 9117)
		        {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear"; //this is the new option to equipt, can change to w/e u want
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 926;  //original color
class8.anIntArray160[0] = 0; //changed color
class8.anInt174 =   2438;
class8.anInt181 =   730;
class8.anInt190 =   516;
class8.anInt198 =   0;
class8.anInt204 =   0;
class8.anInt169 =   0;
class8.anInt194 =   -10;
class8.anInt165 =   3188;
class8.anInt200 =   3192;
class8.anInt188 =   -1;
class8.anInt164 =   -1;
class8.anInt175 =   1755;
class8.anInt197 =   3187;
	class8.aString170 = "Black h'ween Mask";
	class8.aByteArray178 = "Aaaarrrghhh... I'm a monster.".getBytes();
}
if(i == 9118)
		        {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear"; //this is the new option to equipt, can change to w/e u want
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 926;  //original color
class8.anIntArray160[0] = 11200; //changed color
class8.anInt174 =   2438;
class8.anInt181 =   730;
class8.anInt190 =   516;
class8.anInt198 =   0;
class8.anInt204 =   0;
class8.anInt169 =   0;
class8.anInt194 =   -10;
class8.anInt165 =   3188;
class8.anInt200 =   3192;
class8.anInt188 =   -1;
class8.anInt164 =   -1;
class8.anInt175 =   1755;
class8.anInt197 =   3187;
	class8.aString170 = "Yellow h'ween Mask";
	class8.aByteArray178 = "Aaaarrrghhh... I'm a monster.".getBytes();
}

if(i == 9119) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int [3];
class8.anIntArray156[0] = 0xD4E7;
class8.anIntArray156[1] = 0xDFC0;
class8.anIntArray156[2] = 0xD3A7;
class8.anIntArray160[0] = 8128;
class8.anIntArray160[1] = 9126;
class8.anIntArray160[2] = 8128;
class8.anInt174 = 7022; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 7021; //Male
class8.anInt200 = 7021; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "Summoning Cape(t)";
class8.aByteArray178 = "You've received a Summoning Cape(t)".getBytes();
}
if(i == 9120) {
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Wear";
		class8.anIntArray156 = new int[0];
		class8.anIntArray160 = new int [0];
	class8.anInt181 = 1200;
	class8.anInt190 = 1;
	class8.anInt198 = 1;
	class8.anInt204 = 1;
        class8.anInt169 = 1;
        class8.anInt194 = 50;
		class8.anInt174 = 25;		// Drop model & inv model
		class8.anInt200 = 25;		// Female wearing
		class8.anInt165 = 25;		// Male wearing
		class8.anInt164 = 26;		// Female arms/sleeves
		class8.anInt188 = 26;		// male arms/sleeves
        class8.anInt175 = -1;
        class8.anInt197 = -1;
	class8.aString170 = "Jacket";
	class8.aByteArray178 = "A Jacket made by Quint".getBytes();
}
if(i == 9121){ //White Mask
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[1];
              class8.anIntArray160 = new int [1];
              class8.anIntArray156[0] = 926;
              class8.anIntArray160[0] = 100;
  	class8.anInt174 = 2438;
	class8.anInt181 = 730;
	class8.anInt190 = 516;
	class8.anInt198 = 0;
	class8.anInt204 = 0;
        class8.anInt169 = 0;
        class8.anInt194 = -10;
        class8.anInt165 = 3188;
	class8.anInt200 = 3192;
	class8.anInt175 = 1755;
	class8.anInt197 = 3187;
              class8.aString170 = "White h'ween Mask";
              class8.aByteArray178 = "It's a white h'ween mask.".getBytes();
              }
if(i == 9122){
		class8.aStringArray189 = new String[5];
        class8.aStringArray189[1] = "Teleport";
		class8.anIntArray156 = new int[1];
		class8.anIntArray160 = new int[1];
		class8.anIntArray156[0] = 0xFFFFFF;
		class8.anIntArray160[0] = 0;
		class8.anInt174 = 12325;
		class8.anInt190 = 600;
		class8.anInt181 = 440;
		class8.anInt169 = 0;
		class8.anInt194 = 1;
		class8.aBoolean176 = true;
		class8.aString170 = "Varrock Teleport";
		class8.aByteArray178 = "A Teleportation Tablet ".getBytes();
	}
if(i == 9123){
		class8.aStringArray189 = new String[5];
        class8.aStringArray189[1] = "Teleport";
		class8.anIntArray156 = new int[1];
		class8.anIntArray160 = new int[1];
		class8.anIntArray156[0] = 0xFFFFFF;
		class8.anIntArray160[0] = 0;
		class8.anInt174 = 12324;
		class8.anInt190 = 500;
		class8.anInt181 = 440;
		class8.anInt169 = 0;
		class8.anInt194 = 1;
		class8.aBoolean176 = true;
		class8.aString170 = "Home Teleport";
		class8.aByteArray178 = "A Teleportation Tablet ".getBytes();
	}
	if(i == 9124){
		class8.aStringArray189 = new String[5];
        class8.aStringArray189[1] = "Teleport";
		class8.anIntArray156 = new int[1];
		class8.anIntArray160 = new int[1];
		class8.anIntArray156[0] = 0xFFFFFF;
		class8.anIntArray160[0] = 0;
		class8.anInt174 = 12322;
		class8.anInt190 = 500;
		class8.anInt181 = 440;
		class8.anInt169 = 0;
		class8.anInt194 = 1;
		class8.aBoolean176 = true;
		class8.aString170 = "Lumbridge Teleport";
		class8.aByteArray178 = "A Teleportation Tablet ".getBytes();
	}
	if(i == 9125){
		class8.aStringArray189 = new String[5];
        class8.aStringArray189[1] = "Teleport";
		class8.anIntArray156 = new int[1];
		class8.anIntArray160 = new int[1];
		class8.anIntArray156[0] = 0xFFFFFF;
		class8.anIntArray160[0] = 0;
		class8.anInt174 = 12321;
		class8.anInt190 = 600;
		class8.anInt181 = 440;
		class8.anInt169 = 0;
		class8.anInt194 = 1;
		class8.aBoolean176 = true;
		class8.aString170 = "Falador Teleport";
		class8.aByteArray178 = "A Teleportation Tablet ".getBytes();
	}
if(i == 9126){
		class8.aStringArray189 = new String[5];
        class8.aStringArray189[1] = "Teleport";
		class8.anIntArray156 = new int[1];
		class8.anIntArray160 = new int[1];
		class8.anIntArray156[0] = 0xFFFFFF;
		class8.anIntArray160[0] = 0;
		class8.anInt174 = 12314;
		class8.anInt190 = 600;
		class8.anInt181 = 440;
		class8.anInt169 = 0;
		class8.anInt194 = 1;
		class8.aBoolean176 = true;
		class8.aString170 = "Camelot Teleport";
		class8.aByteArray178 = "A Teleportation Tablet".getBytes();
	}
if(i == 9127){
		class8.aStringArray189 = new String[5];
        class8.aStringArray189[1] = "Teleport";
		class8.anIntArray156 = new int[1];
		class8.anIntArray160 = new int[1];
		class8.anIntArray156[0] = 0xFFFFFF;
		class8.anIntArray160[0] = 0;
		class8.anInt174 = 12311;
		class8.anInt190 = 600;
		class8.anInt181 = 440;
		class8.anInt169 = 0;
		class8.anInt194 = 1;
		class8.aBoolean176 = true;
		class8.aString170 = "Ardy Teleport";
		class8.aByteArray178 = "A Teleportation Tablet ".getBytes();
	}
if(i == 9128) //Saradomin sword
 {
    	class8.aStringArray189 = new String[5];
    	class8.aStringArray189[1] ="Wield";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 0;
class8.anIntArray160[0] = 0;
    	class8.anInt174 = 32;//Item Look
    	class8.anInt181 = 2151;
    	class8.anInt190 = 1521;
    	class8.anInt198 = 484;
    	class8.anInt169 = -1;
    	class8.anInt194 = -1;
    	class8.anInt165 = 8032;
    	class8.anInt200 = 8032;
    	class8.aString170 = "Saradomin sword";
    	class8.aByteArray178 = "The magnificent blade of Saradomin!".getBytes();
    }
if(i == 9129)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anInt174 = 10076;//15615
class8.anInt181 = 854;
class8.anInt190 = 387;
class8.anInt198 = 1892;
class8.anInt204 = 138;
class8.anInt169 = -1;
class8.anInt194 = 3;
class8.anInt165 = 10077;//15935
class8.anInt200 = 10078;//15946
class8.anInt188 = -1;
class8.anInt164 = -1;
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "Black Mask";
class8.aByteArray178 = "A valuable slayer item.".getBytes();
}
if(i == 9130)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anInt174 = 10082;//16876
class8.anInt181 = 926;
class8.anInt190 = 432;
class8.anInt198 = 258;
class8.anInt204 = 83;
class8.anInt169 = 3;
class8.anInt194 = 3;
class8.anInt165 = 10083;//16846
class8.anInt200 = 10083;//16846
class8.anInt188 = -1;
class8.anInt164 = -1;
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "Bronze c'bow";
class8.aByteArray178 = "It's powerfull for bronze.".getBytes();
}
if(i == 9131)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anInt174 = 10082;//16876
class8.anInt181 = 926;
class8.anInt190 = 432;
class8.anInt198 = 258;
class8.anInt204 = 83;
class8.anInt169 = 3;
class8.anInt194 = 3;
class8.anInt165 = 10083;//16846
class8.anInt200 = 10083;//16846
class8.anInt188 = -1;
class8.anInt164 = -1;
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "Iron c'bow";
class8.aByteArray178 = "It's powerfull for iron.".getBytes();
class8.anIntArray156 = new int[1];//Start of old colors
class8.anIntArray156[0] = 5656;
class8.anIntArray160 = new int [1];//Start of new colors
class8.anIntArray160[0] = 33;
}
if(i == 9132)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anInt174 = 10082;//16876
class8.anInt181 = 926;
class8.anInt190 = 432;
class8.anInt198 = 258;
class8.anInt204 = 83;
class8.anInt169 = 3;
class8.anInt194 = 3;
class8.anInt165 = 10083;//16846
class8.anInt200 = 10083;//16846
class8.anInt188 = -1;
class8.anInt164 = -1;
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "Steel c'bow";
class8.aByteArray178 = "It's powerfull for steel.".getBytes();
class8.anIntArray156 = new int[1];//Start of old colors
class8.anIntArray156[0] = 5656;
class8.anIntArray160 = new int [1];//Start of new colors
class8.anIntArray160[0] = 43072;
}
if(i == 9133)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anInt174 = 10082;//16876
class8.anInt181 = 926;
class8.anInt190 = 432;
class8.anInt198 = 258;
class8.anInt204 = 83;
class8.anInt169 = 3;
class8.anInt194 = 3;
class8.anInt165 = 10083;//16846
class8.anInt200 = 10083;//16846
class8.anInt188 = -1;
class8.anInt164 = -1;
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "Black c'bow";
class8.aByteArray178 = "It's powerfull for black.".getBytes();
class8.anIntArray156 = new int[1];//Start of old colors
class8.anIntArray156[0] = 5656;
class8.anIntArray160 = new int [1];//Start of new colors
class8.anIntArray160[0] = 0;
}
if(i == 9134)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anInt174 = 10082;//16876
class8.anInt181 = 926;
class8.anInt190 = 432;
class8.anInt198 = 258;
class8.anInt204 = 83;
class8.anInt169 = 3;
class8.anInt194 = 3;
class8.anInt165 = 10083;//16846
class8.anInt200 = 10083;//16846
class8.anInt188 = -1;
class8.anInt164 = -1;
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "Adamant c'bow";
class8.aByteArray178 = "It's powerfull for steel.".getBytes();
class8.anIntArray156 = new int[1];//Start of old colors
class8.anIntArray156[0] = 5656;
class8.anIntArray160 = new int [1];//Start of new colors
class8.anIntArray160[0] = 21662;
}
if(i == 9135)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anInt174 = 10082;//16876
class8.anInt181 = 926;
class8.anInt190 = 432;
class8.anInt198 = 258;
class8.anInt204 = 83;
class8.anInt169 = 3;
class8.anInt194 = 3;
class8.anInt165 = 10083;//16846
class8.anInt200 = 10083;//16846
class8.anInt188 = -1;
class8.anInt164 = -1;
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "Rune c'bow";
class8.aByteArray178 = "It's powerfull for rune.".getBytes();
class8.anIntArray156 = new int[1];//Start of old colors
class8.anIntArray156[0] = 5656;
class8.anIntArray160 = new int [1];//Start of new colors
class8.anIntArray160[0] = 36133;
}
if(i == 9136){
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anIntArray156 = new int[0];
class8.anIntArray160 = new int[0];
class8.anInt174 = 1851; //Model ID
class8.anInt181 = 1851;
class8.anInt190 = 498;
class8.anInt198 = 484;
class8.anInt204 = 0;
class8.anInt169 = -1;
class8.anInt194 = -1;
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt165 = 1851;//male wearing
class8.anInt200 = 1851;//female wearing
class8.aBoolean176 = false;//Stackable
    class8.aString170 = "Narsil";
    class8.aByteArray178 = "The sword of a King...".getBytes();
    }
        if(i == 9137)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[1];
            class8.anIntArray160 = new int[1];
            class8.anIntArray156[0] = 0;
            class8.anIntArray160[0] = 0;
            class8.anInt174 = 14565;
            class8.anInt181 = 1178;
            class8.anInt190 = 498;
            class8.anInt198 = 1840;
            class8.anInt204 = 0;
            class8.anInt169 = 0;
            class8.anInt194 = 2;
            class8.anInt165 = 14561;
            class8.anInt200 = 14561;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.aBoolean176 = false;
            class8.aString170 = "Armadyl Helm";
            class8.aByteArray178 = "The Armadyl Helm".getBytes();
        }
        if(i == 9138)
		{
		    class8.aStringArray189 = new String[5];
		    class8.aStringArray189[1] = "Wield";
		    class8.anIntArray156 = new int[1];
		    class8.anIntArray160 = new int [1];
		    class8.anInt174 = 13047;
		    class8.anInt181 = 1940;
		    class8.anInt190 = 572;
		    class8.anInt198 = 0;
		    class8.anInt204 = 0;
		    class8.anInt169 = 0;
		    class8.anInt194 = 1;
		    class8.anInt165 = 13046;
		    class8.anInt200 = 13046;
		    class8.anInt175 = -1;
		    class8.anInt197 = -1;
		    class8.aString170 = "Yarnova's scythe";
		    class8.aByteArray178 = "A rare and powerfull weapon.".getBytes();
}
if(i == 9139) // ItemID
        {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anInt174 = 8;
class8.anInt181 = 2022;
class8.anInt190 = 540;
class8.anInt198 = 123;
class8.anInt204 = 0;
class8.anInt199 = 0;
class8.anInt169 = 3;
class8.anInt194 = 0;
class8.anInt165 = 7;
class8.anInt200 = 7;
class8.anInt175 = 56;
class8.anInt197 = 116;
class8.aBoolean176 = false;
class8.aString170 = "Dragonfire Shield";
class8.aByteArray178 = "A dragonfire shield!!".getBytes();
}
if(i == 9140){ //Skele top;
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[2];
class8.anIntArray160 = new int[2];
class8.anInt174 = 13015;
class8.anInt181 = 1000;
class8.anInt190 = 543;
class8.anInt198 = 0;
class8.anInt169 = 0;
class8.anInt194 = 0;
class8.anInt165 = 13016;
class8.anInt200 = 13017;
class8.anInt188 = 13018;
class8.anInt164 = 13019;
class8.aBoolean176 = false;
class8.aString170 = "Skele top";
class8.aByteArray178 = "Skele top from halloween".getBytes();
 }
 if(i == 9141){ //Skele legs;
 class8.aStringArray189 = new String[5];
 class8.aStringArray189[1] = "Wear";
 class8.anInt174 = 13020;
 class8.anInt181 = 1750;
 class8.anInt190 = 572;
 class8.anInt198 = 0;
 class8.anInt169 = 4;
 class8.anInt194 = 11;
 class8.anInt165 = 13022;
 class8.anInt200 = 13021;
 class8.anInt164 = -1;
 class8.anInt188 = -1;
 class8.aBoolean176 = false;
 class8.aString170 = "Skele legs";
 class8.aByteArray178 = "Skele legs from halloween".getBytes();
 }
 if(i == 9142){ //Skele helm;
 class8.aStringArray189 = new String[5];
 class8.aStringArray189[1] = "Wear";
 class8.anInt174 = 13023;
 class8.anInt181 = 548;
 class8.anInt190 = 148;
 class8.anInt198 = 120;
 class8.anInt169 = 0;
 class8.anInt194 = 0;
 class8.anInt204 = 24;
 class8.anInt165 = 13024;
 class8.anInt200 = 13025;
 class8.anInt175 = -1;
 class8.aBoolean176 = false;
 class8.aString170 = "Skele helm";
 class8.aByteArray178 = "Skele helm from halloween".getBytes();
 }
 if(i == 9143){ //Skele boots;
 class8.aStringArray189 = new String[5];
 class8.aStringArray189[1] = "Wear";
 class8.anInt174 = 13026;
 class8.anInt181 = 585;
 class8.anInt190 = 0;
 class8.anInt198 = 0;
 class8.anInt204 = 0;
 class8.anInt199 = 0;
 class8.anInt169 = 1;
 class8.anInt194 = 1;
 class8.anInt204 = 24;
 class8.anInt165 = 13027;
 class8.anInt200 = 13028;
 class8.anInt175 = 56;
 class8.anInt197 = 116;
 class8.anInt164 = -1;
 class8.anInt188 = -1;
 class8.aBoolean176 = false;
 class8.aString170 = "Skele boots";
 class8.aByteArray178 = "Skele boots from halloween".getBytes();
 }
 if(i == 9144){ //Skele gloves;
 class8.aStringArray189 = new String[5];
 class8.aStringArray189[1] = "Wear";
 class8.anIntArray156 = new int[2];
 class8.anIntArray160 = new int[2];
 class8.anIntArray156[0] = 10004;
 class8.anIntArray156[1] = 5532;
 class8.anIntArray160[0] = 0;
 class8.anIntArray160[1] = 0;
 class8.anInt190 = 420;
 class8.anInt198 = 828;
 class8.anInt169 = 8;
 class8.anInt194 = -8;
 class8.anInt181 = 540;
 class8.anInt204 = 138;
 class8.anInt188 = -1;
 class8.anInt164 = -1;
 class8.anInt175 = -1;
 class8.anInt197 = -1;
 class8.anInt174 = 13029;
 class8.anInt165 = 13030;
 class8.anInt200 = 13031;
 class8.aBoolean176 = false;
 class8.aString170 = "Skele gloves";
 class8.aByteArray178 = "Skele gloves from halloween".getBytes();
 }
		if(i == 9094)    //WH1P!!!!
		        {
		            class8.aStringArray189 = new String[5];
		            class8.aStringArray189[1] = "Wear"; //this is the new option to equipt, can change to w/e u want
		            class8.anIntArray156 = new int[1];
		        class8.anIntArray160 = new int[1];
		class8.anIntArray156[0] = 528;  //original color
		class8.anIntArray160[0] = 36133; //changed color
		class8.anInt174 =  5412;
		class8.anInt181 =  840;
		class8.anInt190 =  280;
		class8.anInt198 =  0;
		class8.anInt204 =  0;
		class8.anInt169 =  -2;
		class8.anInt194 =  56;
		class8.anInt165 =  5409;
		class8.anInt200 =  5409;
		class8.anInt188 =  -1;
		class8.anInt164 =  -1;
		class8.anInt175 =  -1;
		class8.anInt197 =  -1;
		            class8.aString170 = "Rune Whip";
		            class8.aByteArray178 = "A whip made of Rune".getBytes();
        }
        if(i == 9096) //Replace with the id you want
		{
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Wield";//New option
		class8.anInt174 = 6944; //Model ID
		class8.anInt181 = 1050; //1200 zoom increase will make it smaller
		class8.anInt190 = 572; //model rotate up+down increase to move doen away from you
		class8.anInt198 = 0; //model rotate side ways increase to move right in circle
		class8.anInt169 = 0; // model offset increase to move to the right
		class8.anInt194 = 1; //model offset increase to move up
		class8.anInt165 = 6940;//male wearing
		class8.anInt200 = 6943;//female wearing
		class8.anInt164 = 6942;//Female arms/sleeves
		class8.anInt188 = 6941;//male arms/sleeves
		class8.aBoolean176 = false;//Stackable
		class8.aString170 = "Zamorak Robe top";//Name of the new item
		class8.aByteArray178 = "A Robe Top Worn By the Gods.".getBytes();//examin info
		}

		if(i == 9097) //Replace with the id you want
		{
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Wield";//New option
		class8.anInt174 = 6947; //Model ID
		class8.anInt181 = 1800; //1690 zoom increase will make it smaller
		class8.anInt190 = 408; //model rotate up+down increase to move doen away from you
		class8.anInt198 = 0; //2024 model rotate side ways increase to move right in circle
		class8.anInt169 = 5; // model offset increase to move to the right
		class8.anInt194 = 0; //model offset increase to move up
		class8.anInt165 = 6945;//male wearing
		class8.anInt200 = 6946;//female wearing
		class8.aBoolean176 = false;//Stackable
		class8.aString170 = "Zamorak Robe Bottom";//Name of the new item
		class8.aByteArray178 = "A Robe Bottom Worn By the Gods.".getBytes();//examin info
		}

		if(i == 9098) //Replace with the id you want
		{
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Wield";//New option
		class8.anInt174 = 6948; //Model ID
		class8.anInt181 = 600; //980 zoom increase will make it smaller
		class8.anInt190 = 208; //model rotate up+down increase to move doen away from you
		class8.anInt198 = 220; //model rotate side ways increase to move right in circle
		class8.anInt169 = 0; // model offset increase to move to the right
		class8.anInt194 = -18; //model offset increase to move up
		class8.anInt165 = 6949;//male wearing
		class8.anInt200 = 6950;//female wearing
		class8.aBoolean176 = false;//Stackable
		class8.aString170 = "Zamorak Mitre";//Name of the new item
		class8.aByteArray178 = "A Hat Worn By the Gods.".getBytes();//examin info
		}


		if(i == 9099) //Replace with the id you want
		{
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Wield";//New option
		class8.anInt174 = 6953; //Model ID
		class8.anInt181 = 3000; //zoom increase will make it smaller
		class8.anInt190 = 600; //model rotate up+down increase to move doen away from you
		class8.anInt198 = 1000; //model rotate side ways increase to move right in circle
		class8.anInt169 = 0; // model offset increase to move to the right
		class8.anInt194 = 2; //model offset increase to move up
		class8.anInt165 = 6951;//male wearing
		class8.anInt200 = 6952;//female wearing
		class8.aBoolean176 = false;//Stackable
		class8.aString170 = "Zamorak Cape";//Name of the new item
		class8.aByteArray178 = "A Cape Worn By the Gods.".getBytes();//examin info
		}


		if(i == 9100) //Replace with the id you want
		{
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Wield";//New option
		class8.anInt174 = 6954; //Model ID
		class8.anInt181 = 2000; //1200 zoom increase will make it smaller
		class8.anInt190 = 572; //model rotate up+down increase to move doen away from you
		class8.anInt198 = 0; //model rotate side ways increase to move right in circle
		class8.anInt169 = 0; // model offset increase to move to the right
		class8.anInt194 = 1; //model offset increase to move up
		class8.anInt165 = 6955;//male wearing
		class8.anInt200 = 6955;//female wearing
		class8.aBoolean176 = false;//Stackable
		class8.aString170 = "Zamorak Crozier";//Name of the new item
		class8.aByteArray178 = "A Staff Used By the Gods.".getBytes();//examin info
		}


		if(i == 9101) //Replace with the id you want
		{
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Wield";//New option
		class8.anInt174 = 6956; //Model ID
		class8.anInt181 = 1700; //1200 zoom increase will make it smaller
		class8.anInt190 = 490; //572 model rotate up+down increase to move doen away from you
		class8.anInt198 = 60; //0 model rotate side ways increase to move right in circle
		class8.anInt169 = 0; // model offset increase to move to the right
		class8.anInt194 = -10; //model offset increase to move up
		class8.anInt165 = 6982;//male wearing
		class8.anInt200 = 6984;//female wearing
		class8.aBoolean176 = false;//Stackable
		class8.aString170 = "Zamorak Stole";//Name of the new item
		class8.aByteArray178 = "A Stole Worn By the Gods.".getBytes();//examin info
		}


		if(i == 9102) //Replace with the id you want
		{
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Wield";//New option
		class8.anInt174 = 6983; //Model ID
		class8.anInt181 = 620; //620 zoom increase will make it smaller
		class8.anInt190 = 424; //424 model rotate up+down increase to move doen away from you
		class8.anInt198 = 8; //model rotate side ways increase to move right in circle
		class8.anInt169 = 1; // model offset increase to move to the right
		class8.anInt194 = 16; //model offset increase to move up
		class8.anInt165 = 6986;//male wearing
		class8.anInt200 = 6987;//female wearing
		class8.anInt175 = -1;
		class8.anInt197 = -1;
		class8.aBoolean176 = false;//Stackable
		class8.aString170 = "Amulet of Strength";//Name of the new item
		class8.aByteArray178 = "An amulet that gives of a strong feeling of strength.".getBytes();//examin info
}
if(i == 9004)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[6];
class8.anIntArray160 = new int[6];
class8.anIntArray156 = new int[6];
class8.anIntArray160 = new int[6];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray160[0] = 22424;
class8.anIntArray160[1] = 22424;
class8.anIntArray160[2] = 22424;
class8.anInt174 = 4177; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4199; //Male
class8.anInt200 = 7197; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "Strength Cape";
class8.aByteArray178 = "Its A Strenght Cape".getBytes();
}
if(i == 9091){
	class8.aStringArray189 = new String[5];
	class8.aStringArray189[1] = "Wear";
	class8.anIntArray156 = new int[1];
        class8.anIntArray160 = new int [1];
      //Purple: 926-127
        class8.anIntArray156[0] = 926;
        class8.anIntArray160[0] = 128;
	class8.anInt174 = 2635;//Item Look
	class8.anInt181 = 440;
	class8.anInt190 = 76;
	class8.anInt198 = 1850;
	class8.anInt204 = 0;
        class8.anInt169 = 1;
        class8.anInt194 = 1;
        class8.anInt165 = 187;
	class8.anInt200 = 363;
	class8.anInt175 = 29;
	class8.anInt197 = 87;
	class8.aString170 = "Black Party Hat";
	class8.aByteArray178 = "A Black Party Hat Say Thanks to frugoo <3.".getBytes();
        }
if(i == 9005) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[6];
class8.anIntArray160 = new int[6];
class8.anIntArray156 = new int[6];
class8.anIntArray160 = new int[6];
class8.anIntArray156[0] = 8256;
class8.anIntArray160[0] = 926;
class8.anIntArray156[1] = 54183;
class8.anIntArray160[1] = 926;
class8.anIntArray156[2] = 57280;
class8.anIntArray160[2] = 926;
class8.anIntArray156[3] = 960;
class8.anIntArray160[3] = 926;
class8.anIntArray156[4] = 22464;
class8.anIntArray160[4] = 926;
class8.anIntArray156[5] = 21568;
class8.anIntArray160[5] = 926;
class8.anInt174 = 4177; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4199; //Male
class8.anInt200 = 7197; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "strength Cape(t)";
class8.aByteArray178 = "Its A Strenght Cape(t)".getBytes();
}
if(i == 9006)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 22424;
class8.anIntArray160[1] = 22424;
class8.anIntArray160[2] = 22424;
class8.anInt174 = 4203; //Model ID
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Strength Hood";
class8.aByteArray178 = "Its A Strength Hood".getBytes();
}
if(i == 9007) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray160[0] = 41416;
class8.anIntArray156[1] = 54503;
class8.anIntArray160[1] = 41416;
class8.anIntArray156[2] = 54183;
class8.anIntArray160[2] = 41416;
class8.anIntArray156[3] = 127;
class8.anIntArray160[3] = 41416;
class8.anInt174 = 4162; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4184; //Male
class8.anInt200 = 7182; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Defence Cape";
class8.aByteArray178 = "Its A Defence Cape".getBytes();
}
if(i == 9008) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 57280;
class8.anIntArray160[0] = 107;
class8.anIntArray156[1] = 54503;
class8.anIntArray160[1] = 107;
class8.anIntArray156[2] = 54183;
class8.anIntArray160[2] = 107;
class8.anInt174 = 4162; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4184; //Male
class8.anInt200 = 7182; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Defence Cape(t)";
class8.aByteArray178 = "Its A Defence Cape(t)".getBytes();
}
if(i == 9009)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray160[0] = 41416;
class8.anIntArray156[1] = 22464;
class8.anIntArray160[1] = 41416;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[2] = 41416;
class8.anInt174 = 4203; //Model ID
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Defence Hood";
class8.aByteArray178 = "Its A Defence Hood".getBytes();
}
if(i == 9010) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[6];
class8.anIntArray160 = new int[6];
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray160[0] = 13214;
class8.anIntArray160[1] = 13214;
class8.anIntArray160[2] = 13214;
class8.anInt174 = 4173; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4195; //Male
class8.anInt200 = 7193; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Ranging Cape";
class8.aByteArray178 = "Its A Ranging Cape".getBytes();
}
if(i == 9011) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray160[0] = 8111;
class8.anIntArray160[1] = 8111;
class8.anIntArray160[2] = 8111;
class8.anInt174 = 4173; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4195; //Male
class8.anInt200 = 7193; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Ranging Cape(t)";
class8.aByteArray178 = "Its A Ranging Cape(t)".getBytes();
}
if(i == 9012)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 13214;
class8.anIntArray160[1] = 13214;
class8.anIntArray160[2] = 13214;
class8.anInt174 = 4203; //Model ID
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Range Hood";
class8.aByteArray178 = "A Range Hood".getBytes();
}
if(i == 9013) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 107;
class8.anIntArray160[1] = 107;
class8.anIntArray160[2] = 107;
class8.anIntArray160[3] = 107;
class8.anInt174 = 4171; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4193; //Male
class8.anInt200 = 7191; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "Prayer Cape";
class8.aByteArray178 = "Its A Prayer Cape".getBytes();
}
if(i == 9014) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 9152;
class8.anIntArray160[1] = 7104;
class8.anIntArray160[2] = 7104;
class8.anIntArray160[3] = 796;
class8.anInt174 = 4171; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4193; //Male
class8.anInt200 = 7191; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Prayer Cape(t)";
class8.aByteArray178 = "Its A Prayer Cape(t)".getBytes();
}
if(i == 9015)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 107;
class8.anIntArray160[1] = 107;
class8.anIntArray160[2] = 107;
class8.anInt174 = 4203; //Model ID
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Prayer Hood";
class8.aByteArray178 = "A Prayer Hood".getBytes();
}
if(i == 9016) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 6348;
class8.anIntArray160[1] = 6340;
class8.anIntArray160[2] = 6331;
class8.anIntArray160[3] = 6331;
class8.anInt174 = 4169; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4191; //Male
class8.anInt200 = 7189; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Magic Cape";
class8.aByteArray178 = "Its A magic Cape".getBytes();
}
if(i == 9017) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 43934;
class8.anIntArray160[1] = 43934;
class8.anIntArray160[2] = 43934;
class8.anIntArray160[3] = 43934;
class8.anInt174 = 4169; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4191; //Male
class8.anInt200 = 7189; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Magic Cape(t)";
class8.aByteArray178 = "Its A magic Cape(t)".getBytes();
}
if(i == 9018)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 6348;
class8.anIntArray160[1] = 6340;
class8.anIntArray160[2] = 6331;
class8.anInt174 = 4203; //Model ID
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Magic Hood";
class8.aByteArray178 = "A Magic Hood".getBytes();
}
if(i == 9019) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 6073;
class8.anIntArray160[1] = 6073;
class8.anIntArray160[2] = 6073;
class8.anIntArray160[3] = 6073;
class8.anInt174 = 4159; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4186; //Male
class8.anInt200 = 7184; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Firemaking Cape";
class8.aByteArray178 = "Its A firemaking Cape(t)".getBytes();
}
if(i == 9020) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 9152;
class8.anIntArray160[1] = 7104;
class8.anIntArray160[2] = 7104;
class8.anIntArray160[3] = 796;
class8.anInt174 = 4159; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4186; //Male
class8.anInt200 = 7184; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Firemaking Cape(t)";
class8.aByteArray178 = "Its A firemaking Cape(t)".getBytes();
}
if(i == 9021)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 6073;
class8.anIntArray160[1] = 6073;
class8.anIntArray160[2] = 6073;
class8.anInt174 = 4203; //Model ID
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "FireMaking Hood";
class8.aByteArray178 = "Its A FireMaking Hood".getBytes();
}
if(i == 9022) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 99;
class8.anIntArray160[1] = 99;
class8.anIntArray160[2] = 99;
class8.anIntArray160[3] = 99;
class8.anInt174 = 4164; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4196; //Male
class8.anInt200 = 7194; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "RuneCrafting Cape";
class8.aByteArray178 = "Its A firemaking Cape(t)".getBytes();
}
if(i == 9023) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 9152;
class8.anIntArray160[1] = 7104;
class8.anIntArray160[2] = 7104;
class8.anIntArray160[3] = 796;
class8.anInt174 = 4164; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4196; //Male
class8.anInt200 = 7194; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "RuneCrafting Cape(t)";
class8.aByteArray178 = "Its A firemaking Cape(t)".getBytes();
}
if(i == 9024)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 99;
class8.anIntArray160[1] = 99;
class8.anIntArray160[2] = 99;
class8.anInt174 = 4203; //Model ID
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "RuneCrafting Hood";
class8.aByteArray178 = "Its A RuneCrafting Hood".getBytes();
}
if(i == 9025) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 99;
class8.anIntArray160[1] = 99;
class8.anIntArray160[2] = 99;
class8.anIntArray160[3] = 99;
class8.anInt174 = 4168; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4190; //Male
class8.anInt200 = 7187; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Hitpoint Cape";
class8.aByteArray178 = "Its A Hitpoint Cape".getBytes();
}
if(i == 9026) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 926;
class8.anIntArray160[1] = 926;
class8.anIntArray160[2] = 926;
class8.anIntArray160[3] = 926;
class8.anInt174 = 4168; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4190; //Male
class8.anInt200 = 7187; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Hitpoint Cape(t)";
class8.aByteArray178 = "Its A Hitpoint Cape(t)".getBytes();
}
if(i == 9027)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 99;
class8.anIntArray160[1] = 99;
class8.anIntArray160[2] = 99;
class8.anInt174 = 4203; //Model ID
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Hitpoint Hood";
class8.aByteArray178 = "Its A Hitpoint Hood".getBytes();
}
if(i == 9028) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 43932;
class8.anIntArray160[1] = 43932;
class8.anIntArray160[2] = 43932;
class8.anIntArray160[3] = 43932;
class8.anInt174 = 4157; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4180; //Male
class8.anInt200 = 7177; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Agility Cape";
class8.aByteArray178 = "Its A Agility Cape".getBytes();
}
if(i == 9029) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 924;
class8.anIntArray160[1] = 924;
class8.anIntArray160[2] = 924;
class8.anIntArray160[3] = 924;
class8.anInt174 = 4157; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4180; //Male
class8.anInt200 = 7177; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Agility Cape(t)";
class8.aByteArray178 = "Its A Agility Cape(t)".getBytes();
}
if(i == 9030)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 43934;
class8.anIntArray160[1] = 43934;
class8.anIntArray160[2] = 43934;
class8.anInt174 = 4203; //Model ID
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Agility Hood";
class8.aByteArray178 = "Its A Agility Hood".getBytes();
}
if(i == 9031) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 22416;
class8.anIntArray160[1] = 22416;
class8.anIntArray160[2] = 22416;
class8.anIntArray160[3] = 22416;
class8.anInt174 = 4167; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4189; //Male
class8.anInt200 = 7187; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Herblore Cape";
class8.aByteArray178 = "Its A Herblore Cape".getBytes();
}
if(i == 9032) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 9152;
class8.anIntArray160[1] = 7104;
class8.anIntArray160[2] = 7104;
class8.anIntArray160[3] = 796;
class8.anInt174 = 4167; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4189; //Male
class8.anInt200 = 7187; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Herblore Cape(t)";
class8.aByteArray178 = "Its A Herblore Cape(t)".getBytes();
}
if(i == 9033)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 22416;
class8.anIntArray160[1] = 22416;
class8.anIntArray160[2] = 22416;
class8.anInt174 = 4203; //Model ID
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Herblore Hood";
class8.aByteArray178 = "Its A Herblore Hood".getBytes();
}
if(i == 9034)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 51120;
class8.anIntArray160[1] = 51120;
class8.anIntArray160[2] = 51120;
class8.anIntArray160[3] = 51120;
class8.anInt174 = 4178; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4201; //Male
class8.anInt200 = 7198; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Thieving Cape";
class8.aByteArray178 = "Its A thieving Cape".getBytes();
}
if(i == 9035)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 16000;
class8.anIntArray160[1] = 16000;
class8.anIntArray160[2] = 16000;
class8.anIntArray160[3] = 16000;
class8.anInt174 = 4178; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4201; //Male
class8.anInt200 = 7198; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Thieving Cape(t)";
class8.aByteArray178 = "Its A Thieving Cape(t)".getBytes();
}
if(i == 9036)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 51120;
class8.anIntArray160[1] = 51120;
class8.anIntArray160[2] = 51120;
class8.anInt174 = 4203; //Model ID
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Thieving Hood";
class8.aByteArray178 = "Its A Thieving Hood".getBytes();
}
if(i == 9037)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 4769;
class8.anIntArray160[1] = 4769;
class8.anIntArray160[2] = 4769;
class8.anIntArray160[3] = 4769;
class8.anInt174 = 4161; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4183; //Male
class8.anInt200 = 7181; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Crafting Cape";
class8.aByteArray178 = "Its A Crafting Cape".getBytes();
}
if(i == 9038)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 9152;
class8.anIntArray160[1] = 7104;
class8.anIntArray160[2] = 7104;
class8.anIntArray160[3] = 796;
class8.anInt174 = 4161; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4183; //Male
class8.anInt200 = 7181; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "crafting Cape(t)";
class8.aByteArray178 = "Its A Crafting Cape(t)".getBytes();
}
if(i == 9039)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 4769;
class8.anIntArray160[1] = 4769;
class8.anIntArray160[2] = 4769;
class8.anInt174 = 4203; //Model ID
class8.aBoolean176 = false;//Stackable
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Crafting Hood";
class8.aByteArray178 = "Its A Crafting Hood".getBytes();
}
if(i == 9040)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 9152;
class8.anIntArray160[1] = 7104;
class8.anIntArray160[2] = 7104;
class8.anIntArray160[3] = 796;
class8.anInt174 = 4166; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4188; //Male
class8.anInt200 = 7186; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Fletching Cape(t)";
class8.aByteArray178 = "Its A Fletching Cape(t)".getBytes();
}
if(i == 9041)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 22430;
class8.anIntArray160[1] = 22430;
class8.anIntArray160[2] = 22430;
class8.anIntArray160[3] = 22430;
class8.anInt174 = 4166; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4188; //Male
class8.anInt200 = 7186; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Fletching Cape";
class8.aByteArray178 = "Its A Fletching Cape".getBytes();
}
if(i == 9042)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 22430;
class8.anIntArray160[1] = 22430;
class8.anIntArray160[2] = 22430;
class8.anInt174 = 4203; //Model ID
class8.aBoolean176 = false;//Stackable
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Fletching Hood";
class8.aByteArray178 = "Its A Fletching Hood".getBytes();
}
if(i == 9043)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 6;
class8.anIntArray160[1] = 6;
class8.anIntArray160[2] = 6;
class8.anIntArray160[3] = 6;
class8.anInt174 = 4175; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4197; //Male
class8.anInt200 = 7195; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Slayer Cape";
class8.aByteArray178 = "Its A slayer Cape".getBytes();
}
if(i == 9044)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 912;
class8.anIntArray160[1] = 912;
class8.anIntArray160[2] = 912;
class8.anIntArray160[3] = 912;
class8.anInt174 = 4175; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4197; //Male
class8.anInt200 = 7195; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Slayer Cape";
class8.aByteArray178 = "Its A slayer Cape".getBytes();
}
if(i == 9045)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 0;//Back
class8.anIntArray160[1] = 0;//front Line
class8.anIntArray160[2] = 6;//Inside
class8.anInt174 = 4203; //Model ID
class8.aBoolean176 = false;//Stackable
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Slayer Hood";
class8.aByteArray178 = "Its A Slayer Hood".getBytes();
}
if(i == 9046) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 10512;
class8.anIntArray160[1] = 10512;
class8.anIntArray160[2] = 10512;
class8.anIntArray160[3] = 10512;
class8.anInt174 = 4170; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4192; //Male
class8.anInt200 = 7190; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Mining Cape";
class8.aByteArray178 = "Its A mining Cape".getBytes();
}
if(i == 9047) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 38226;
class8.anIntArray160[1] = 38226;
class8.anIntArray160[2] = 38226;
class8.anIntArray160[3] = 38226;
class8.anInt174 = 4170; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4192; //Male
class8.anInt200 = 7190; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Mining Cape(t)";
class8.aByteArray178 = "Its A mining Cape(t)".getBytes();
}

if(i == 9048)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 10512;//Back
class8.anIntArray160[1] = 10512;//front Line
class8.anIntArray160[2] = 10512;//Inside
class8.anInt174 = 4203; //Model ID
class8.aBoolean176 = false;//Stackable
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Mining Hood";
class8.aByteArray178 = "Its A Mining Hood".getBytes();
}


if(i == 9049) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 10512;
class8.anIntArray160[1] = 10512;
class8.anIntArray160[2] = 10512;
class8.anIntArray160[3] = 10512;
class8.anInt174 = 4176; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4198; //Male
class8.anInt200 = 7196; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Smithing Cape";
class8.aByteArray178 = "Its A smithing Cape".getBytes();
}
if(i == 9050) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 9152;
class8.anIntArray160[1] = 7104;
class8.anIntArray160[2] = 7104;
class8.anIntArray160[3] = 796;
class8.anInt174 = 4176; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4198; //Male
class8.anInt200 = 7196; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Smithing Cape(t)";
class8.aByteArray178 = "Its A smithing Cape(t)".getBytes();
}
if(i == 9051)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 10512;//Back
class8.anIntArray160[1] = 10512;//front Line
class8.anIntArray160[2] = 10512;//Inside
class8.anInt174 = 4203; //Model ID
class8.aBoolean176 = false;//Stackable
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Smithing Hood";
class8.aByteArray178 = "Its A Smithing Hood".getBytes();
}
if(i == 9052) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 38226;
class8.anIntArray160[1] = 38226;
class8.anIntArray160[2] = 38226;
class8.anIntArray160[3] = 38226;
class8.anInt174 = 4165; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4187; //Male
class8.anInt200 = 7185; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Fishing Cape";
class8.aByteArray178 = "Its A fishing Cape".getBytes();
}
if(i == 9053) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 9152;
class8.anIntArray160[1] = 7104;
class8.anIntArray160[2] = 7104;
class8.anIntArray160[3] = 796;
class8.anInt174 = 4165; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4187; //Male
class8.anInt200 = 7185; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Fishing Cape(t)";
class8.aByteArray178 = "Its A Fishing Cape(t)".getBytes();
}
if(i == 9054)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 38226;//Back
class8.anIntArray160[1] = 38226;//front Line
class8.anIntArray160[2] = 38226;//Inside
class8.anInt174 = 4203; //Model ID
class8.aBoolean176 = false;//Stackable
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Fishing Hood";
class8.aByteArray178 = "Its A Fishing Hood".getBytes();
}
if(i == 9055) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 51000;
class8.anIntArray160[1] = 51000;
class8.anIntArray160[2] = 51000;
class8.anIntArray160[3] = 51000;
class8.anInt174 = 4160; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4182; //Male
class8.anInt200 = 7180; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "cooking Cape";
class8.aByteArray178 = "Its A Cooking Cape".getBytes();
}
if(i == 9056) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 926;
class8.anIntArray160[1] = 926;
class8.anIntArray160[2] = 926;
class8.anIntArray160[3] = 926;
class8.anInt174 = 4160; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4182; //Male
class8.anInt200 = 7180; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "cooking Cape(t)";
class8.aByteArray178 = "Its A Cooking Cape(t)".getBytes();
}
if(i == 9057)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 51136;//Back
class8.anIntArray160[1] = 51136;//front Line
class8.anIntArray160[2] = 51136;//Inside
class8.anInt174 = 4203; //Model ID
class8.aBoolean176 = false;//Stackable
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Cooking Hood";
class8.aByteArray178 = "Its A Cooking Hood".getBytes();
}

if(i == 9058)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 5560;
class8.anIntArray160[1] = 5560;
class8.anIntArray160[2] = 5560;
class8.anIntArray160[3] = 5560;
class8.anInt174 = 4174; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4200; //Male
class8.anInt200 = 7199; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "woodcutting Cape";
class8.aByteArray178 = "Its A woodcutting Cape".getBytes();
}
if(i == 9059)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 22430;
class8.anIntArray160[1] = 22430;
class8.anIntArray160[2] = 22430;
class8.anIntArray160[3] = 22430;
class8.anInt174 = 4174; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4200; //Male
class8.anInt200 = 7199; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "woodcutting Cape";
class8.aByteArray178 = "Its A woodcutting Cape".getBytes();
}
if(i == 9060)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 5560;//Back
class8.anIntArray160[1] = 5560;//front Line
class8.anIntArray160[2] = 5560;//Inside
class8.anInt174 = 4203; //Model ID
class8.aBoolean176 = false;//Stackable
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Woodcutting Hood";
class8.aByteArray178 = "Its A woodcutting Hood".getBytes();
}
if(i == 9061)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 22416;
class8.anIntArray160[1] = 22416;
class8.anIntArray160[2] = 22416;
class8.anIntArray160[3] = 22416;
class8.anInt174 = 4163; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4185; //Male
class8.anInt200 = 7183; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Farming Cape";
class8.aByteArray178 = "Its A Farming Cape".getBytes();
}

if(i == 9062)
{
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 14792;
class8.anIntArray160[1] = 14784;
class8.anIntArray160[2] = 14784;
class8.anIntArray160[3] = 14792;
class8.anInt174 = 4163; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4185; //Male
class8.anInt200 = 7183; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Farming Cape(t)";
class8.aByteArray178 = "Its A Farming Cape(t)".getBytes();
}
if(i == 9063)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 22416;//Back
class8.anIntArray160[1] = 22416;//front Line
class8.anIntArray160[2] = 22416;//Inside
class8.anInt174 = 4203; //Model ID
class8.aBoolean176 = false;//Stackable
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Farming Hood";
class8.aByteArray178 = "Its A Farming Hood".getBytes();
}
if(i == 9064)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 38226;
class8.anIntArray160[1] = 38226;
class8.anIntArray160[2] = 38226;
class8.anIntArray160[3] = 38226;
class8.anInt174 = 4172; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4194; //Male
class8.anInt200 = 7192; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Quest Cape";
class8.aByteArray178 = "Its A quest Cape".getBytes();
}
if(i == 9065)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 38226;//Back
class8.anIntArray160[1] = 38226;//front Line
class8.anIntArray160[2] = 38226;//Inside
class8.anInt174 = 4203; //Model ID
class8.aBoolean176 = false;//Stackable
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Quest Hood";
class8.aByteArray178 = "Its A Quest Hood".getBytes();
}


if(i == 9066) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 6340;
class8.anIntArray160[1] = 6338;
class8.anIntArray160[2] = 6338;
class8.anIntArray160[3] = 6340;
class8.anInt190 = 0;//ModelRotation1
class8.anInt198 = 0;//modelrotate2 side ways increase to move right in circle
class8.anInt169 = 0;// modeloffset1 increase to move to the right
class8.anInt194 = 0;//modeloffset2 increase to move up
class8.anInt174 = 4156; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4179; //Male
class8.anInt200 = 7179; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Const. Cape";
class8.aByteArray178 = "Its A ConstructionCape".getBytes();
}
if(i == 9067) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 9152;
class8.anIntArray160[1] = 7104;
class8.anIntArray160[2] = 7104;
class8.anIntArray160[3] = 796;
class8.anInt190 = 0;//ModelRotation1
class8.anInt198 = 0;//modelrotate2 side ways increase to move right in circle
class8.anInt169 = 0;// modeloffset1 increase to move to the right
class8.anInt194 = 0;//modeloffset2 increase to move up
class8.anInt174 = 4156; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4179; //Male
class8.anInt200 = 7179; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Const. Cape(t)";
class8.aByteArray178 = "Its A ConstructionCape".getBytes();
}
if(i == 9068)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 6340;//Back
class8.anIntArray160[1] = 6340;//front Line
class8.anIntArray160[2] = 6340;//Inside
class8.anInt174 = 4203; //Model ID
class8.aBoolean176 = false;//Stackable
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Const. Hood";
class8.aByteArray178 = "Its A Quest Hood".getBytes();
}









if(i == 6570)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anInt174 = 9631; //Model ID

class8.aBoolean176 = false;//Stackable
}
if(i == 9080)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 28; // colors
class8.anIntArray156[0] = 74; // colors
class8.anIntArray160[0] = 38676; // colors
class8.anIntArray160[0] = 38313; // colors
class8.anInt174 = 4671; //Model ID
class8.anInt181 = 490;//Model Zoom
class8.anInt190 = 344;//Model Rotation
class8.anInt198 = 192;//Unknown
class8.anInt204 = 138;//Unknown
class8.anInt199 = -1;//noteable
class8.anInt169 = 1;//Unknown
class8.anInt194 = 20;//Unknown
class8.anInt165 = 4672;//male wearing
class8.anInt200 = 4672;//female wearing
class8.anInt175 = 56;//Unknown
class8.anInt197 = 116;//Unknown
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Rune defender";//Name of the new item
class8.aByteArray178 = "A defensive weapon.".getBytes();//examin info
        }
              if(i == 9104)  // change this if you need to "item number"
		        {
		            class8.aStringArray189 = new String[5];
		            class8.aStringArray189[1] = "Wield";
		            class8.anIntArray156 = new int[1];
		            class8.anIntArray160 = new int[1];
		class8.anIntArray156[0] = 28; // colors
		class8.anIntArray156[0] = 74; // colors
		class8.anIntArray160[0] = 38676; // colors
		class8.anIntArray160[0] = 924; // colors
		class8.anInt174 = 4671; //Model ID - 15335 -These are the models I over wrote for it
			class8.anInt181 = 490;
			class8.anInt190 = 344;
			class8.anInt198 = 192;
			class8.anInt204 = 138;
		        class8.anInt169 = 1;
		        class8.anInt194 = 20;
		class8.anInt165 = 4672;//male wearing 15413
		class8.anInt200 = 4672;//female wearing 15413
		class8.anInt175 = 56;
		class8.anInt197 = 116;
		            class8.aString170 = "Dragon defender";
		            class8.aByteArray178 = "A defensive weapon.".getBytes();
        }
if(i == 9081)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 4673; //Model ID
class8.anInt181 = 1200; //zoom increase will make it smaller
class8.anInt190 = 510; //model rotate up+down increase to move doen away from you
class8.anInt198 = 1840; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 5; //model offset increase to move up
class8.anInt165 = 4674;//male wearing
class8.anInt200 = 4675;//female wearing
class8.anInt164 = 337;//Female arms/sleeves
class8.anInt188 = 156;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Fighter Torso";//Name of the new item
class8.aByteArray178 = "Its A fighter Torso".getBytes();//examin info
        }
if(i == 9082)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 4676; //Model ID
class8.anInt181 = 2128;//Model Zoom
class8.anInt190 = 591;//Model Rotation
class8.anInt198 = 1034;//model rotate side ways increase to move right in circle
class8.anInt204 = 138;//Unknown
class8.anInt199 = -1;//noteable
class8.anInt169 = 8;// model offset increase to move to the right
class8.anInt194 = 11;//model offset increase to move up
class8.anInt165 = 4677;//male wearing
class8.anInt200 = 4677;//female wearing
class8.anInt175 = -1;//Unknown
class8.aBoolean176 = false;//Stackable//Unknown
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Dark bow";//Name of the new item
class8.aByteArray178 = "A very PowerFull Bow".getBytes();//examin info
        }
        if(i == 9083)  // change this if you need to "item number"
		{
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Wield";//New option
		class8.anInt174 = 5000; //Model ID
		class8.anInt181 = 1500;//Model Zoom
		class8.anInt190 = 432;//Model Rotation
		class8.anInt198 = 180;//model rotate side ways increase to move right in circle
		class8.anInt204 = 2006;//Unknown
		class8.aBoolean161 = true;
		class8.aByte205 = 1;
		class8.aByte154 = 1;
		class8.anInt169 = -1;// model offset increase to move to the right
		class8.anInt194 = -8;//model offset increase to move up
		class8.anInt165 = -1;//male wearing
		class8.anInt200 = -1;//female wearing
		class8.anInt175 = -1;//Unknown
		class8.aBoolean176 = false;//Stackable//Unknown
		class8.anInt164 = 337;//Female arms/sleeves
		class8.anInt188 = 156;//male arms/sleeves
		class8.aBoolean176 = true;//Stackable
		class8.aString170 = "Dragon arrow";//Name of the new item
		class8.aByteArray178 = "Dragon arrow".getBytes();//examin info
        }
if(i == 9084)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 4678; //Model ID
class8.anInt181 = 595;//Model Zoom
class8.anInt190 = 0;//Model Rotation
class8.anInt198 = 0;//model rotate side ways increase to move right in circle
class8.anInt204 = 0;//Unknown
class8.anInt199 = 0;//noteable
class8.anInt169 = 0;// model offset increase to move to the right
class8.anInt194 = 0;//model offset increase to move up
class8.anInt165 = 4679;//male wearing
class8.anInt200 = 4680;//female wearing
class8.anInt175 = 56;//Unknown
class8.anInt197 = 116;//Unknown
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Dragon Boots";//Name of the new item
class8.aByteArray178 = "A nice pair Of boots".getBytes();//examin info
        }
if(i == 9085)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 4681; //Model ID
class8.anInt181 = 900;//Model Zoom
class8.anInt190 = 540;//Model Rotation
class8.anInt198 = 2039;//model rotate side ways increase to move right in circle
class8.anInt199 = 0;//noteable
class8.anInt169 = 0;// model offset increase to move to the right
class8.anInt194 = 0;//model offset increase to move up
class8.anInt165 = 4682;//male wearing
class8.anInt200 = 4683;//female wearing
class8.anInt164 = 4685;//Female arms/sleeves
class8.anInt188 = 4684;//male arms/sleeves
class8.anInt175 = -1;//Unknown
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Bandos Chestplate";//Name of the new item
class8.aByteArray178 = "Its A Bandos Chestplate".getBytes();//examin info
        }
if(i == 9086)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 4686; //Model ID
class8.anInt181 = 854;//Model Zoom
class8.anInt190 = 540;//Model Rotation
class8.anInt198 = 2039;//model rotate side ways increase to move right in circle
class8.anInt204 = 144;//Unknown
class8.anInt199 = -1;//noteable
class8.anInt169 = 9;// model offset increase to move to the right
class8.anInt194 = 5;//model offset increase to move up
class8.anInt165 = 4687;//male wearing
class8.anInt200 = 4688;//female wearing
class8.anInt175 = -1;//Unknown
class8.aBoolean176 = false;//Stackable
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Bandos tassets";//Name of the new item
class8.aByteArray178 = "Its A Bandos Tassets".getBytes();//examin info
        }
if(i == 9087)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 4689; //Model ID
class8.anInt181 = 900;//Model Zoom
class8.anInt190 = 540;//Model Rotation
class8.anInt198 = 2039;//model rotate side ways increase to move right in circle
class8.anInt199 = 0;//noteable
class8.anInt169 = 0;// model offset increase to move to the right
class8.anInt194 = 0;//model offset increase to move up
class8.anInt165 = 4690;//male wearing
class8.anInt200 = 4691;//female wearing
class8.anInt175 = -1;//Unknown
class8.aBoolean176 = false;//Stackable
class8.anInt164 = 4693;//Female arms/sleeves
class8.anInt188 = 4692;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Armadyl chestplate";//Name of the new item
class8.aByteArray178 = "Its A Armadyl Chestplate".getBytes();//examin info
        }
if(i == 9088)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 4694; //Model ID
class8.anInt181 = 1600;//Model Zoom
class8.anInt190 = 510;//Model Rotation
class8.anInt198 = 0;//model rotate side ways increase to move right in circle
class8.anInt204 = 138;//Unknown
class8.anInt199 = -1;//noteable
class8.anInt169 = 0;// model offset increase to move to the right
class8.anInt194 = 0;//model offset increase to move up
class8.anInt165 = 4695;//male wearing
class8.anInt200 = 4696;//female wearing
class8.anInt175 = -1;//Unknown
class8.aBoolean176 = false;//Stackable
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Armadyl plateskirt";//Name of the new item
class8.aByteArray178 = "Its A Armadyl plateskirt".getBytes();//examin info
      }
if(i == 9089)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 7202; //Model ID
class8.aBoolean176 = false;//Stackable
class8.anInt181 = 984; //zoom increase will make it smaller
class8.anInt190 = 126; //model rotate up+down increase to move doen away from you
class8.anInt198 = 129; //model rotate side ways increase to move right in circle
class8.anInt169 = -1; // model offset increase to move to the right
class8.anInt194 = 1; //model offset increase to move up
class8.anInt204 = 28;
class8.anInt165 = 7200;//male wearing
class8.anInt200 = 7201;//female wearing
class8.anInt204 = 28;
class8.anInt175 = -1;//Unknown
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Helm of neitiznot";//Name of the new item
class8.aByteArray178 = "Its A Helm of neitiznot".getBytes();//examin info
      }
if(i == 9090)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 7205; //Model ID
class8.anInt181 = 844; //zoom increase will make it smaller
class8.anInt190 = 126; //model rotate up+down increase to move doen away from you
class8.anInt198 = 129; //model rotate side ways increase to move right in circle
class8.anInt169 = -1; // model offset increase to move to the right
class8.anInt194 = 1; //model offset increase to move up
class8.anInt204 = 28;
class8.anInt165 = 7204;//male wearing
class8.anInt200 = 7203;//female wearing
class8.anInt175 = -1;//Unknown
class8.aBoolean176 = false;//Stackable
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Dragon Full Helm";//Name of the new item
class8.aByteArray178 = "Its A Dragon Full Helm".getBytes();//examin info
      }
if(i == 9069)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 7169; //Model ID
class8.anInt181 = 1200; //zoom increase will make it smaller
class8.anInt190 = 572; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 1; //model offset increase to move up
class8.anInt165 = 7148;//male wearing
class8.anInt200 = 7163;//female wearing
class8.anInt164 = 7152;//Female arms/sleeves
class8.anInt188 = 7138;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "3rd Age plateBody";//Name of the new item
class8.aByteArray178 = "A ancient PlateBody".getBytes();//examin info
        }
if(i == 9070)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 7167; //Model ID
class8.anInt181 = 1740; //zoom increase will make it smaller
class8.anInt190 = 572; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 4; // model offset increase to move to the right
class8.anInt194 = 11; //model offset increase to move up
class8.anInt165 = 7145;//male wearing
class8.anInt200 = 7159;//female wearing
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "3rd Age plateLegs";//Name of the new item
class8.aByteArray178 = "A ancient Platelegs".getBytes();//examin info
        }
if(i == 9071)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 7166; //Model ID
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 28; //model rotate up+down increase to move doen away from you
class8.anInt198 = 1892; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 7142;//male wearing
class8.anInt200 = 7156;//female wearing
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "3rd Age Full helm";//Name of the new item
class8.aByteArray178 = "A ancient Full helm".getBytes();//examin info
        }
if(i == 9072)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 7168; //Model ID
class8.anInt181 = 2030; //zoom increase will make it smaller
class8.anInt190 = 364; //model rotate up+down increase to move doen away from you
class8.anInt198 = 56; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 7151;//male wearing
class8.anInt200 = 7151;//female wearing
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "3rd Age KiteShield";//Name of the new item
class8.aByteArray178 = "A ancient Kiteshield".getBytes();//examin info
        }
if(i == 9073)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 7172; //Model ID
class8.anInt181 = 2083; //zoom increase will make it smaller
class8.anInt190 = 572; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 7149;//male wearing
class8.anInt200 = 7164;//female wearing
class8.anInt164 = 7153;//Female arms/sleeves
class8.anInt188 = 7139;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "3rd Age Robe Top";//Name of the new item
class8.aByteArray178 = "A ancient Robe Top".getBytes();//examin info
        }
if(i == 9074)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 7171; //Model ID
class8.anInt181 = 2083; //zoom increase will make it smaller
class8.anInt190 = 572; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 1; //model offset increase to move up
class8.anInt165 = 7146;//male wearing
class8.anInt200 = 7160;//female wearing
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "3rd Age Robe";//Name of the new item
class8.aByteArray178 = "A ancient Robe".getBytes();//examin info
        }
if(i == 9075)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 7170; //Model ID
class8.anInt181 = 917; //zoom increase will make it smaller
class8.anInt190 = 212; //model rotate up+down increase to move doen away from you
class8.anInt198 = 1883; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 7143;//male wearing
class8.anInt200 = 7157;//female wearing
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "3rd Age Mage Hat";//Name of the new item
class8.aByteArray178 = "A ancient hat".getBytes();//examin info
        }
if(i == 9076)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 7175; //Model ID
class8.anInt181 = 1200; //zoom increase will make it smaller
class8.anInt190 = 572; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 1; //model offset increase to move up
class8.anInt165 = 7150;//male wearing
class8.anInt200 = 7165;//female wearing
class8.anInt164 = 7154;//Female arms/sleeves
class8.anInt188 = 7140;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "3rd Age Range Top";//Name of the new item
class8.aByteArray178 = "A ancient Range Top".getBytes();//examin info
        }
if(i == 9077)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 7174; //Model ID
class8.anInt181 = 1827; //zoom increase will make it smaller
class8.anInt190 = 572; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 1; //model offset increase to move up
class8.anInt165 = 7147;//male wearing
class8.anInt200 = 7161;//female wearing
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "3rd Age Range Legs";//Name of the new item
class8.aByteArray178 = "A ancient Range Legs".getBytes();//examin info
        }
if(i == 9078)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 7173; //Model ID
class8.anInt181 = 917; //zoom increase will make it smaller
class8.anInt190 = 212; //model rotate up+down increase to move doen away from you
class8.anInt198 = 1883; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 7144;//male wearing
class8.anInt200 = 7158;//female wearing
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "3rd Age Range Coif";//Name of the new item
class8.aByteArray178 = "A ancient Range coif".getBytes();//examin info
        }
if(i == 9079)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 7176; //Model ID
class8.anInt181 = 740; //zoom increase will make it smaller
class8.anInt190 = 196; //model rotate up+down increase to move doen away from you
class8.anInt198 = 1784; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 7141;//male wearing
class8.anInt200 = 7155;//female wearing
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "3rd Age vambraces";//Name of the new item
class8.aByteArray178 = "A ancient vambraces".getBytes();//examin info
        }

		
		
		
		
		switch(i) {
		
		
		 case 91:
            class8.aString170 = "Guam potion (unf)";
        break;
        case 92:
            class8.aString170 = "Guam potion (unf)";
        break;
        case 93:
            class8.aString170 = "Marrentil potion (unf)";
        break;
        case 94:
            class8.aString170 = "Marrentil potion (unf)";
        break;
        case 95:
            class8.aString170 = "Tarromin potion (unf)";
        break;
        case 96:
            class8.aString170 = "Tarromin potion (unf)";
        break;
        case 97:
            class8.aString170 = "Harralander potion (unf)";
        break;
        case 98:
            class8.aString170 = "Harralander potion (unf)";
        break;
        case 99:
            class8.aString170 = "Ranarr potion (unf)";
        break;
        case 100:
            class8.aString170 = "Ranarr potion (unf)";
        break;
        case 101:
            class8.aString170 = "Irit potion (unf)";
        break;
        case 102:
            class8.aString170 = "Irit potion (unf)";
        break;
        case 103:
            class8.aString170 = "Avantoe potion (unf)";
        break;
        case 104:
            class8.aString170 = "Avantoe potion (unf)";
        break;
        case 105:
            class8.aString170 = "Kwuarm potion (unf)";
        break;
        case 106:
            class8.aString170 = "Kwuarm potion (unf)";
        break;
        case 107:
            class8.aString170 = "Cadantine potion (unf)";
        break;
        case 108:
            class8.aString170 = "Cadantine potion (unf)";
        break;
        case 109:
            class8.aString170 = "Dwarf weed potion (unf)";
        break;
        case 110:
            class8.aString170 = "Dwarf weed potion (unf)";
        break;
        case 111:
            class8.aString170 = "Torstol potion (unf)";
        break;
        case 2483:
            class8.aString170 = "Lantadyme potion (unf)";
        break;
        case 2484:
            class8.aString170 = "Lantadyme potion (unf)";
        break;
        case 3002:
            class8.aString170 = "Toadflax potion (unf)";
        break;
        case 3003:
            class8.aString170 = "Toadflax potion (unf)";
        break;
        case 3004:
            class8.aString170 = "Snapdragon potion (unf)";
        break;
        case 3005:
            class8.aString170 = "Snapdragon potion (unf)";
        break;
		
		
		
		
		
		
		
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		if(class8.anInt163 != -1)
			class8.method199((byte)61);
		if(!aBoolean182 && class8.aBoolean161) {
			class8.aString170 = "Members Object";
			class8.aByteArray178 = "Login to a members' server to use this object.".getBytes();
			class8.aStringArray168 = null;
			class8.aStringArray189 = null;
			class8.anInt202 = 0;
		}
		return class8;
	}

	public void method199(byte byte0) { //toNode
		Class8 class8 = method198(anInt163);
		anInt174 = class8.anInt174;
		anInt181 = class8.anInt181;
		anInt190 = class8.anInt190;
		anInt198 = class8.anInt198;
		anInt204 = class8.anInt204;
		anInt169 = class8.anInt169;
		anInt194 = class8.anInt194;
		if(byte0 != 61)
			aBoolean186 = !aBoolean186;
		anIntArray156 = class8.anIntArray156;
		anIntArray160 = class8.anIntArray160;
		Class8 class8_1 = method198(anInt179);
		aString170 = class8_1.aString170;
		aBoolean161 = class8_1.aBoolean161;
		anInt155 = class8_1.anInt155;
		String s = "a";
		char c = class8_1.aString170.charAt(0);
		if(c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')
			s = "an";
		aByteArray178 = ("Swap this note at any bank for " + s + " " + class8_1.aString170 + ".").getBytes();
		aBoolean176 = true;
	}

	public static final Sprite method200(int i, int j, int k, int l) { //getSprite
		if(k == 0) {
			Sprite class30_sub2_sub1_sub1 = (Sprite)aClass12_158.insertFromCache(i);
			if(class30_sub2_sub1_sub1 != null && class30_sub2_sub1_sub1.maxHeight != j && class30_sub2_sub1_sub1.maxHeight != -1) {
				class30_sub2_sub1_sub1.unlink();
				class30_sub2_sub1_sub1 = null;
			}
			if(class30_sub2_sub1_sub1 != null)
				return class30_sub2_sub1_sub1;
		}
		Class8 class8 = method198(i);
		if(class8.anIntArray193 == null)
			j = -1;
		if(j > 1) {
			int i1 = -1;
			for(int j1 = 0; j1 < 10; j1++)
				if(j >= class8.anIntArray201[j1] && class8.anIntArray201[j1] != 0)
					i1 = class8.anIntArray193[j1];
			if(i1 != -1)
				class8 = method198(i1);
		}
		Model class30_sub2_sub4_sub6 = class8.method201(1);
		if(class30_sub2_sub4_sub6 == null)
			return null;
		Sprite class30_sub2_sub1_sub1_2 = null;
		if(class8.anInt163 != -1) {
			class30_sub2_sub1_sub1_2 = method200(class8.anInt179, 10, -1, 9);
			if(class30_sub2_sub1_sub1_2 == null)
				return null;
		}
		Sprite class30_sub2_sub1_sub1_1 = new Sprite(32, 32);
		int k1 = Texture.textureInt1;
		int l1 = Texture.textureInt2;
		int ai[] = Texture.anIntArray1472;
		int ai1[] = DrawingArea.pixels;
		int i2 = DrawingArea.width;
		int j2 = DrawingArea.height;
		int k2 = DrawingArea.topX;
		int l2 = DrawingArea.bottomX;
		int i3 = DrawingArea.topY;
		int j3 = DrawingArea.bottomY;
		Texture.aBoolean1464 = false;
		DrawingArea.initDrawingArea(32, 32, class30_sub2_sub1_sub1_1.myPixels);
		DrawingArea.method336(32, 0, 0, 0, 32);
		Texture.method364();
		int k3 = class8.anInt181;
		if(k == -1)
			k3 = (int)((double)k3 * 1.5D);
		if(k > 0)
			k3 = (int)((double)k3 * 1.04D);
		int l3 = Texture.anIntArray1470[class8.anInt190] * k3 >> 16;
		int i4 = Texture.anIntArray1471[class8.anInt190] * k3 >> 16;
		class30_sub2_sub4_sub6.method482(0, class8.anInt198, class8.anInt204, class8.anInt190, class8.anInt169, l3 + ((Animable) (class30_sub2_sub4_sub6)).modelHeight / 2 + class8.anInt194, i4 + class8.anInt194);
		for(int i5 = 31; i5 >= 0; i5--) {
			for(int j4 = 31; j4 >= 0; j4--)
				if(class30_sub2_sub1_sub1_1.myPixels[i5 + j4 * 32] == 0)
					if(i5 > 0 && class30_sub2_sub1_sub1_1.myPixels[(i5 - 1) + j4 * 32] > 1)
						class30_sub2_sub1_sub1_1.myPixels[i5 + j4 * 32] = 1;
					else
					if(j4 > 0 && class30_sub2_sub1_sub1_1.myPixels[i5 + (j4 - 1) * 32] > 1)
						class30_sub2_sub1_sub1_1.myPixels[i5 + j4 * 32] = 1;
					else
					if(i5 < 31 && class30_sub2_sub1_sub1_1.myPixels[i5 + 1 + j4 * 32] > 1)
						class30_sub2_sub1_sub1_1.myPixels[i5 + j4 * 32] = 1;
					else
					if(j4 < 31 && class30_sub2_sub1_sub1_1.myPixels[i5 + (j4 + 1) * 32] > 1)
						class30_sub2_sub1_sub1_1.myPixels[i5 + j4 * 32] = 1;
		}
		if(k > 0) {
			for(int j5 = 31; j5 >= 0; j5--) {
				for(int k4 = 31; k4 >= 0; k4--)
					if(class30_sub2_sub1_sub1_1.myPixels[j5 + k4 * 32] == 0)
						if(j5 > 0 && class30_sub2_sub1_sub1_1.myPixels[(j5 - 1) + k4 * 32] == 1)
							class30_sub2_sub1_sub1_1.myPixels[j5 + k4 * 32] = k;
						else
						if(k4 > 0 && class30_sub2_sub1_sub1_1.myPixels[j5 + (k4 - 1) * 32] == 1)
							class30_sub2_sub1_sub1_1.myPixels[j5 + k4 * 32] = k;
						else
						if(j5 < 31 && class30_sub2_sub1_sub1_1.myPixels[j5 + 1 + k4 * 32] == 1)
							class30_sub2_sub1_sub1_1.myPixels[j5 + k4 * 32] = k;
						else
						if(k4 < 31 && class30_sub2_sub1_sub1_1.myPixels[j5 + (k4 + 1) * 32] == 1)
							class30_sub2_sub1_sub1_1.myPixels[j5 + k4 * 32] = k;
			}
		} else if(k == 0) {
			for(int k5 = 31; k5 >= 0; k5--) {
				for(int l4 = 31; l4 >= 0; l4--)
					if(class30_sub2_sub1_sub1_1.myPixels[k5 + l4 * 32] == 0 && k5 > 0 && l4 > 0 && class30_sub2_sub1_sub1_1.myPixels[(k5 - 1) + (l4 - 1) * 32] > 0)
						class30_sub2_sub1_sub1_1.myPixels[k5 + l4 * 32] = 0x302020;
			}
		}
		if(class8.anInt163 != -1) {
			int l5 = class30_sub2_sub1_sub1_2.maxWidth;
			int j6 = class30_sub2_sub1_sub1_2.maxHeight;
			class30_sub2_sub1_sub1_2.maxWidth = 32;
			class30_sub2_sub1_sub1_2.maxHeight = 32;
			class30_sub2_sub1_sub1_2.method348(0, 0);
			class30_sub2_sub1_sub1_2.maxWidth = l5;
			class30_sub2_sub1_sub1_2.maxHeight = j6;
		}
		if(k == 0)
			aClass12_158.removeFromCache(class30_sub2_sub1_sub1_1, i, (byte)2);
		DrawingArea.initDrawingArea(j2, i2, ai1);
		DrawingArea.setDrawingArea(j3, k2, l2, i3);
		Texture.textureInt1 = k1;
		Texture.textureInt2 = l1;
		Texture.anIntArray1472 = ai;
		Texture.aBoolean1464 = true;
		if(l < 9 || l > 9) {
			for(int i6 = 1; i6 > 0; i6++);
		}
		if(class8.aBoolean176)
			class30_sub2_sub1_sub1_1.maxWidth = 33;
		else
			class30_sub2_sub1_sub1_1.maxWidth = 32;
		class30_sub2_sub1_sub1_1.maxHeight = j;
		return class30_sub2_sub1_sub1_1;
	}

	public final Model method201(int i) {
		if(anIntArray193 != null && i > 1) {
			int j = -1;
			for(int k = 0; k < 10; k++)
				if(i >= anIntArray201[k] && anIntArray201[k] != 0)
					j = anIntArray193[k];
			if(j != -1)
				return method198(j).method201(1);
		}
		Model class30_sub2_sub4_sub6 = (Model)aClass12_159.insertFromCache(anInt157);
		if(class30_sub2_sub4_sub6 != null)
			return class30_sub2_sub4_sub6;
		class30_sub2_sub4_sub6 = Model.method462(anInt171, anInt174);
		if(class30_sub2_sub4_sub6 == null)
			return null;
		if(anInt167 != 128 || anInt192 != 128 || anInt191 != 128)
			class30_sub2_sub4_sub6.method478(anInt167, anInt191, anInt177, anInt192);
		if(anIntArray156 != null) {
			for(int l = 0; l < anIntArray156.length; l++)
				class30_sub2_sub4_sub6.method476(anIntArray156[l], anIntArray160[l]);
		}
		class30_sub2_sub4_sub6.method479(64 + anInt196, 768 + anInt184, -50, -10, -50, true);
		class30_sub2_sub4_sub6.aBoolean1659 = true;
		aClass12_159.removeFromCache(class30_sub2_sub4_sub6, anInt157, (byte)2);
		return class30_sub2_sub4_sub6;
	}

	public final Model method202(int i, boolean flag) {
		if(anIntArray193 != null && i > 1) {
			int j = -1;
			for(int k = 0; k < 10; k++)
				if(i >= anIntArray201[k] && anIntArray201[k] != 0)
					j = anIntArray193[k];
			if(j != -1)
				return method198(j).method202(1, true);
		}
		Model class30_sub2_sub4_sub6 = Model.method462(anInt171, anInt174);
		if(!flag)
			throw new NullPointerException();
		if(class30_sub2_sub4_sub6 == null)
			return null;
		if(anIntArray156 != null) {
			for(int l = 0; l < anIntArray156.length; l++)
				class30_sub2_sub4_sub6.method476(anIntArray156[l], anIntArray160[l]);
		}
		return class30_sub2_sub4_sub6;
	}

	public final void method203(boolean flag, Stream class30_sub2_sub2) { //readValues
		if(!flag)
			throw new NullPointerException();
		do {
			int i = class30_sub2_sub2.readUnsignedByte();
			if(i == 0)
				return;
			if(i == 1)
				anInt174 = class30_sub2_sub2.readUnsignedWord();
			else
			if(i == 2)
				aString170 = class30_sub2_sub2.readString();
			else
			if(i == 3)
				aByteArray178 = class30_sub2_sub2.readBytes();
			else
			if(i == 4)
				anInt181 = class30_sub2_sub2.readUnsignedWord();
			else
			if(i == 5)
				anInt190 = class30_sub2_sub2.readUnsignedWord();
			else
			if(i == 6)
				anInt198 = class30_sub2_sub2.readUnsignedWord();
			else
			if(i == 7) {
				anInt169 = class30_sub2_sub2.readUnsignedWord();
				if(anInt169 > 32767)
					anInt169 -= 0x10000;
			} else if(i == 8) {
				anInt194 = class30_sub2_sub2.readUnsignedWord();
				if(anInt194 > 32767)
					anInt194 -= 0x10000;
			} else if(i == 10)
				anInt199 = class30_sub2_sub2.readUnsignedWord();
			else
			if(i == 11)
				aBoolean176 = true;
			else
			if(i == 12)
				anInt155 = class30_sub2_sub2.readDWord();
			else
			if(i == 16)
				aBoolean161 = true;
			else
			if(i == 23) {
				anInt165 = class30_sub2_sub2.readUnsignedWord();
				aByte205 = class30_sub2_sub2.readSignedByte();
			} else if(i == 24)
				anInt188 = class30_sub2_sub2.readUnsignedWord();
			else
			if(i == 25) {
				anInt200 = class30_sub2_sub2.readUnsignedWord();
				aByte154 = class30_sub2_sub2.readSignedByte();
			} else if(i == 26)
				anInt164 = class30_sub2_sub2.readUnsignedWord();
			else
			if(i >= 30 && i < 35) {
				if(aStringArray168 == null)
					aStringArray168 = new String[5];
				aStringArray168[i - 30] = class30_sub2_sub2.readString();
				if(aStringArray168[i - 30].equalsIgnoreCase("hidden"))
					aStringArray168[i - 30] = null;
			} else if(i >= 35 && i < 40) {
				if(aStringArray189 == null)
					aStringArray189 = new String[5];
				aStringArray189[i - 35] = class30_sub2_sub2.readString();
			} else if(i == 40) {
				int j = class30_sub2_sub2.readUnsignedByte();
				anIntArray156 = new int[j];
				anIntArray160 = new int[j];
				for(int k = 0; k < j; k++) {
					anIntArray156[k] = class30_sub2_sub2.readUnsignedWord();
					anIntArray160[k] = class30_sub2_sub2.readUnsignedWord();
				}
			} else if(i == 78)
				anInt185 = class30_sub2_sub2.readUnsignedWord();
			else
			if(i == 79)
				anInt162 = class30_sub2_sub2.readUnsignedWord();
			else
			if(i == 90)
				anInt175 = class30_sub2_sub2.readUnsignedWord();
			else
			if(i == 91)
				anInt197 = class30_sub2_sub2.readUnsignedWord();
			else
			if(i == 92)
				anInt166 = class30_sub2_sub2.readUnsignedWord();
			else
			if(i == 93)
				anInt173 = class30_sub2_sub2.readUnsignedWord();
			else
			if(i == 95)
				anInt204 = class30_sub2_sub2.readUnsignedWord();
			else
			if(i == 97)
				anInt179 = class30_sub2_sub2.readUnsignedWord();
			else
			if(i == 98)
				anInt163 = class30_sub2_sub2.readUnsignedWord();
			else
			if(i >= 100 && i < 110) {
				if(anIntArray193 == null) {
					anIntArray193 = new int[10];
					anIntArray201 = new int[10];
				}
				anIntArray193[i - 100] = class30_sub2_sub2.readUnsignedWord();
				anIntArray201[i - 100] = class30_sub2_sub2.readUnsignedWord();
			} else if(i == 110)
				anInt167 = class30_sub2_sub2.readUnsignedWord();
			else
			if(i == 111)
				anInt192 = class30_sub2_sub2.readUnsignedWord();
			else
			if(i == 112)
				anInt191 = class30_sub2_sub2.readUnsignedWord();
			else
			if(i == 113)
				anInt196 = class30_sub2_sub2.readSignedByte();
			else
			if(i == 114)
				anInt184 = class30_sub2_sub2.readSignedByte() * 5;
			else
			if(i == 115)
				anInt202 = class30_sub2_sub2.readUnsignedByte();
		} while(true);
	}

	Class8() {
		anInt157 = -1;
		anInt171 = 9;
		anInt177 = 9;
		aBoolean186 = false;
		aBoolean206 = false;
	}
	
	private byte aByte154;
	public int anInt155;
	private int anIntArray156[];
	public int anInt157;
	static MRUNodes aClass12_158 = new MRUNodes(100);
	public static MRUNodes aClass12_159 = new MRUNodes(50);
	private int anIntArray160[];
	public boolean aBoolean161;
	public int anInt162;
	public int anInt163;
	private int anInt164;
	private int anInt165;
	public int anInt166;
	private int anInt167;
	public String aStringArray168[];
	private int anInt169;
	public String aString170;
	private int anInt171;
	private static Class8 aClass8Array172[];
	public int anInt173;
	private int anInt174;
	private int anInt175;
	public boolean aBoolean176;
	private int anInt177;
	public byte aByteArray178[];
	public int anInt179;
	private static int anInt180;
	public int anInt181;
	public static boolean aBoolean182 = true;
	private static Stream aClass30_Sub2_Sub2_183;
	private int anInt184;
	public int anInt185;
	private boolean aBoolean186;
	private static boolean aBoolean187;
	private int anInt188;
	public String aStringArray189[];
	public int anInt190;
	private int anInt191;
	private int anInt192;
	public int anIntArray193[];
	private int anInt194;
	private static int anIntArray195[];
	private int anInt196;
	public int anInt197;
	public int anInt198;
	public int anInt199;
	private int anInt200;
	public int anIntArray201[];
	public int anInt202;
	public static int anInt203;
	public static int frugooItems = 5;
	private int anInt204;
	private byte aByte205;
	private boolean aBoolean206;

}