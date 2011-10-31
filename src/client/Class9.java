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
	||#             File: Class9.java - Interfaces             #||
	\*##########################################################*/
	
	import sign.signlink;
	
	
public class Class9 { //RSInterface

	public static Class9 addTab(int i) {
		Class9 class9 = aClass9Array210[i] = new Class9();
		class9.anInt250 = i;
		class9.anInt236 = i;
		class9.anInt262 = 0;
		class9.anInt217 = 0;
		class9.anInt214 = 0;
		class9.anInt220 = 512;
		class9.anInt267 = 334;
		class9.aByte254 = 0;
		class9.anInt230 = 0;
		return class9;
	}
	
	public static void addText(int id, String text, TextDrawingClass wid[], int idx, int color) {
		Class9 Tab = addTab(id);
		Tab.anInt250 = id;
		Tab.anInt236 = id;
		Tab.anInt262 = 4;
		Tab.anInt217 = 0;
		Tab.anInt220 = 174;
		Tab.anInt267 = 11;
		Tab.anInt214 = 0;
		Tab.aByte254 = 0;
		Tab.anInt230 = -1;
		Tab.aBoolean223 = false;
		Tab.aBoolean268 = true;
		Tab.aClass30_Sub2_Sub1_Sub4_243 = wid[idx];
		Tab.aString248 = text;
		Tab.aString228 = "";
		Tab.anInt232 = color;
		Tab.anInt219 = 0;
		Tab.anInt216 = 0;
		Tab.anInt239 = 0;	
	}
	
	public static void removeSomething(int i) {
		Class9 class9 = aClass9Array210[i] = new Class9();
		class9.anInt250 = i;
		class9.anInt236 = i;
		class9.anInt262 = 5;
		class9.anInt217 = 1;
		class9.anInt214 = 0;
		class9.aByte254 = 0;
		class9.anInt230 = 52;
	}
	
	public static void addActionButton(int i, int j, int k, int width, int height, String s) {
		Class9 class9 = aClass9Array210[i] = new Class9();
		class9.anInt250 = i;
		class9.anInt236 = i;
		class9.anInt262 = 5;
		class9.anInt217 = 1;
		class9.anInt214 = 0;
		class9.anInt220 = width;
		class9.anInt267 = height;
		class9.aByte254 = 0;
		class9.anInt230 = 52;
		class9.aClass30_Sub2_Sub1_Sub1_207 = new Sprite(sign.signlink.findcachedir() + "sprites/interfaces/new/custom "+j+".png"); 
		class9.aClass30_Sub2_Sub1_Sub1_260 = new Sprite(sign.signlink.findcachedir() + "sprites/interfaces/new/custom "+k+".png"); 
		class9.aString221 = s;
	}
	
	
	
	
	
	
	

	
		public static void testing(TextDrawingClass[] wid) {
		Class9 t = addTab(18000);
		addText(18001, "test", wid, 0, 0xffffff);
		t.anIntArray240 = new int[1];
		t.anIntArray241 = new int[1];
		t.anIntArray272 = new int[1];
		t.anIntArray241[0] = 0; //childX
		t.anIntArray272[0] = 0; //childY
		t.anIntArray240[0] = 18001; //childID
	}
	
	
	

	
	
	
	
	public static void addHoveredButton(int i, String imageName, int j, int w, int h, int IMAGEID) {//hoverable button
Class9 tab = addTab(i);
tab.anInt236 = i;
tab.anInt250 = i;
tab.anInt262 = 0;
tab.anInt217 = 0;
tab.anInt220 = w;
tab.anInt267 = h;
tab.aBoolean266 = true;
tab.aByte254 = 0;
tab.anInt230 = -1;
tab.anInt261 = 0;
addHoverImage(IMAGEID, j, j, imageName);



	tab.anIntArray240 = new int[1];
	tab.anIntArray241 = new int[1];
	tab.anIntArray272 = new int[1];

	tab.anIntArray241[0] = 0; //childX
	tab.anIntArray272[0] = 0; //childY
	tab.anIntArray240[0] = IMAGEID; //childID
	


}
public static void addHoverButton(int i, String imageName, int j, int anInt220, int anInt267, String text, int contentType, int hoverOver, int aT) {//hoverable button
Class9 tab = addTab(i);
tab.anInt250 = i;
tab.anInt236 = i;
tab.anInt262 = 5;
tab.anInt217 = aT;
tab.anInt214 = contentType;
tab.aByte254 = 0;
tab.anInt230 = hoverOver;
tab.aClass30_Sub2_Sub1_Sub1_207= new Sprite(sign.signlink.findcachedir() + "sprites/interfaces/notes/NOTE "+j+".png"); 
tab.aClass30_Sub2_Sub1_Sub1_260= new Sprite(sign.signlink.findcachedir() + "sprites/interfaces/notes/NOTE "+j+".png"); 
tab.anInt220 = anInt220;
tab.anInt267 = anInt267;
tab.aString221 = text;
}
public static void addHoverImage(int i, int j, int k, String name) {
Class9 tab = addTab(i);
tab.anInt250 = i;
tab.anInt236 = i;
tab.anInt262 = 5;
tab.anInt217 = 0;
tab.anInt214 = 0;
tab.anInt220 = 512;
tab.anInt267 = 334;
tab.aByte254 = 0;
tab.anInt230 = 52;
tab.aClass30_Sub2_Sub1_Sub1_207= new Sprite(sign.signlink.findcachedir() + "sprites/interfaces/notes/NOTE "+j+".png"); 
tab.aClass30_Sub2_Sub1_Sub1_260= new Sprite(sign.signlink.findcachedir() + "sprites/interfaces/notes/NOTE "+k+".png"); 
}
	


	
	
	
	public static void addSprite(int ID, int i, String name) { //static sprite
		Class9 Tab = addTab(ID);
		Tab.anInt250 = ID;
		Tab.anInt236 = ID;
		Tab.anInt262 = 5;
		Tab.anInt217 = 0;
		Tab.anInt214 = 0;
		Tab.anInt220 = 512;
		Tab.anInt267 = 334;
		Tab.aByte254 = 0;
		Tab.anInt230 = 0;
		Tab.aClass30_Sub2_Sub1_Sub1_207= new Sprite(sign.signlink.findcachedir() + "sprites/interfaces/"+name+" "+i+".png"); 
		Tab.aClass30_Sub2_Sub1_Sub1_260= new Sprite(sign.signlink.findcachedir() + "sprites/interfaces/"+name+" "+i+".png"); 
	}
	
	
	
	public static void Sidebar7(TextDrawingClass[] tda) {
		Class9 t = addTab(19000);
		addSprite(17351, 0, "notes/NOTE");
		addHoverButton(17352, "NOTE", 1, 200, 30, "Add note", -1, 17353, 1);
		addHoveredButton(17353, "NOTE", 2, 200, 30, 17354);
		addHoverButton(17355, "NOTE", 3, 200, 30, "Delete all", -1, 17356, 1);
		addHoveredButton(17356, "NOTE", 4, 200, 30, 17357);
		
		addText(13800, "Under Construction", tda, 0, 0xffffff);
		
		addText(17801, "", tda, 0, 0xff981f);
		addText(17812, "", tda, 0, 0xff981f);
		addText(17813, "", tda, 0, 0xff981f);
		addText(17814, "", tda, 0, 0xff981f);
		
		t.anIntArray240 = new int[6];
		t.anIntArray241 = new int[6];
		t.anIntArray272 = new int[6];
	
		t.anIntArray241[0] = 0; //childX
		t.anIntArray272[0] = 0; //childY
		t.anIntArray240[0] = 17351; //childID
		
		t.anIntArray241[1] = 8; //childX
		t.anIntArray272[1] = 2; //childY
		t.anIntArray240[1] = 17352; //childID
		
		t.anIntArray241[2] = 8; //childX
		t.anIntArray272[2] = 2; //childY
		t.anIntArray240[2] = 17353; //childID
		
		t.anIntArray241[3] = 165; //childX
		t.anIntArray272[3] = 237; //childY
		t.anIntArray240[3] = 17355; //childID
		
		t.anIntArray241[4] = 165; //childX
		t.anIntArray272[4] = 237; //childY
		t.anIntArray240[4] = 17356; //childID
		
		t.anIntArray241[5] = 40; //childX
		t.anIntArray272[5] = 78; //childY
		t.anIntArray240[5] = 13800; //childID
		

		t = addTab(14000);
		t.anInt220 = 474;
		t.anInt267 = 213;
		t.anInt261 = 305;


		for(int i = 14001; i <= 14030; i++){
			addText(i, "", tda, 0, 0xffffff);
		}
		
		t.anIntArray240 = new int[30];
		t.anIntArray241 = new int[30];
		t.anIntArray272 = new int[30];

		int Y = 5;
		int Child = 0;
		for(int i = 14001; i <= 14030; i++){
			
			t.anIntArray241[Child] = 248; //childX
			t.anIntArray272[Child] = Y; //childY
			t.anIntArray240[Child] = i; //childID
			
			Y += 13;
			Child++;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void LogoutSB(TextDrawingClass[] wid) {
		Class9 class9 = aClass9Array210[2449];
		
		//top line
		class9.anIntArray272[0] = 10;
		class9.anIntArray241[0] = 4; //X pos
		class9.anIntArray240[0] = 2450; //Interface id
		
		//middle line
		class9.anIntArray272[1] = 26;
		class9.anIntArray241[1] = 31; //X pos
		class9.anIntArray240[1] = 2452; //Interface id
		
		//bottom line
		class9.anIntArray272[2] = 41;
		class9.anIntArray241[2] = 16;
		class9.anIntArray240[2] = 2451; //Interface id
		
		
		
		addSprite(17351, 0, "notes/NOTE");
		
		
		
		
		//red box
		class9.anIntArray272[3] = 215; //down
		class9.anIntArray272[4] = 215; //down
		class9.anIntArray272[5] = 215; //down
		class9.anIntArray272[6] = 215; //down
		class9.anIntArray272[7] = 215; //down
		
		//red box TEXT
		class9.anIntArray241[8] = 31; //acroos
		class9.anIntArray272[8] = 225; //down
		
		//top text
		class9 = aClass9Array210[2450];
		class9.anInt232 = 0xff981f;
		class9.aString248 = "When you have finished playing,";
		class9 = aClass9Array210[2452];
		class9.anInt232 = 0xff981f;
		class9.aString248 = "on -GaMeR X-'s Client,";
		class9 = aClass9Array210[2451];
		class9.anInt232 = 0xff981f;
		class9.aString248 = "click below, to logout safely.";
	}
	
		public static void Sidebar4(TextDrawingClass[] wid) {
		Class9 t = aClass9Array210[1644];
		/*addActionButton(ID, SpriteON, SpriteOFF, SpriteX, SpriteY, "SpriteText");*/
		addActionButton(15101, 15101, 15101, 40, 39, "Show Equipment Screen");
		//addActionButton(15102, 15102, 15102, 40, 39, "Items Kept on Death");
		/*removeSomething(ID)*/
		removeSomething(15103);
		removeSomething(15104);
		t.anIntArray240[23] = 15101;
		t.anIntArray241[23] = 78;
		t.anIntArray272[23] = 212;
		t.anIntArray240[24] = 15102;
		t.anIntArray241[24] = 41+39+30;
		t.anIntArray272[24] = 212;
		t.anIntArray240[25] = 15103;
		t.anIntArray241[25] = 41;
		t.anIntArray272[25] = 212;
		t.anIntArray240[26] = 15104;
		t.anIntArray241[26] = 41+39+30;
		t.anIntArray272[26] = 212;
	}
	
	
	public static void Sidebar8(TextDrawingClass[] wid) {
		Class9 class9 = aClass9Array210[5065];
		addText(5070, "Add Friend", wid, 0, 0xff9933);
		addText(5071, "Del Friend", wid, 0, 0xff9933);
		class9.anIntArray241[0] = 4;
		class9.anIntArray272[0] = 20;
		class9.anIntArray241[1] = 53;//friends list - world xxx X
		class9.anIntArray241[4] = 25;//add friend X
		class9.anIntArray241[5] = 106;//del friend X
		class9.anIntArray272[4] = 236;//add friend Y
		class9.anIntArray272[5] = 236;//del friend Y
		class9 = aClass9Array210[5066];
		class9.anInt267 = 194;
		for(int i = 0; i < 400; i++)
		class9.anIntArray272[i] -= 1;
		class9 = aClass9Array210[5067];
		class9.aString248 = "Friends List - World 1";
		class9.aBoolean223 = true;
		class9.anInt232 = 0xff9933;
	}
	
	public static void Sidebar9(TextDrawingClass[] wid) {
		Class9 class9 = aClass9Array210[5715];
		addText(5720, " Add Name", wid, 0, 0xff9933);
		addText(5721, " Del Name", wid, 0, 0xff9933);
		class9.anIntArray241[0] = 4; //offset for the players names..
		class9.anIntArray272[0] = 20; //players from top..
		class9.anIntArray241[1] = 53; //friends list - world
		class9.anIntArray241[4] = 25; //add friend X
		class9.anIntArray241[5] = 106; //del friend X
		class9.anIntArray272[4] = 236; //add friend Y
		class9.anIntArray272[5] = 236; //del friend Y
		class9 = aClass9Array210[5717]; //this makes to we can ser a new text..
		class9.aString248 = "Ignore List";
		class9.aBoolean223 = true; //offsets to old location of the header..
		class9.anInt232 = 0xff9933; //sets the colour
	}
	

	
	
	
	public void method204(int i, byte byte0, int j) { //swapInventoryItems
		int k = anIntArray253[i];
		anIntArray253[i] = anIntArray253[j];
		if(byte0 == 9) {
			byte0 = 0;
		} else {
			anInt229 = -76;
		}
		anIntArray253[j] = k;
		k = anIntArray252[i];
		anIntArray252[i] = anIntArray252[j];
		anIntArray252[j] = k;
	}

	public static void method205(Class44 class44, TextDrawingClass aclass30_sub2_sub1_sub4[], byte byte0, Class44 class44_1) { //unpack
		aClass12_238 = new MRUNodes(50000);
		Stream class30_sub2_sub2 = new Stream(class44.method571("data", null));
		int i = -1;
		int j = class30_sub2_sub2.readUnsignedWord();
		aClass9Array210 = new Class9[20001];
		do {
			if(class30_sub2_sub2.currentOffset >= class30_sub2_sub2.buffer.length) {
				break;
			}
			int k = class30_sub2_sub2.readUnsignedWord();
			if(k == 65535) {
				i = class30_sub2_sub2.readUnsignedWord();
				k = class30_sub2_sub2.readUnsignedWord();
			}
			Class9 class9 = aClass9Array210[k] = new Class9();
			class9.anInt250 = k;
			class9.anInt236 = i;
			class9.anInt262 = class30_sub2_sub2.readUnsignedByte();
			class9.anInt217 = class30_sub2_sub2.readUnsignedByte();
			class9.anInt214 = class30_sub2_sub2.readUnsignedWord();
			class9.anInt220 = class30_sub2_sub2.readUnsignedWord();
			class9.anInt267 = class30_sub2_sub2.readUnsignedWord();
			class9.aByte254 = (byte)class30_sub2_sub2.readUnsignedByte();
			class9.anInt230 = class30_sub2_sub2.readUnsignedByte();
			if(class9.anInt230 != 0) {
				class9.anInt230 = (class9.anInt230 - 1 << 8) + class30_sub2_sub2.readUnsignedByte();
			} else {
				class9.anInt230 = -1;
			}
			int l = class30_sub2_sub2.readUnsignedByte();
			if(l > 0) {
				class9.anIntArray245 = new int[l];
				class9.anIntArray212 = new int[l];
				for(int i1 = 0; i1 < l; i1++) {
					class9.anIntArray245[i1] = class30_sub2_sub2.readUnsignedByte();
					class9.anIntArray212[i1] = class30_sub2_sub2.readUnsignedWord();
				}
			}
			int j1 = class30_sub2_sub2.readUnsignedByte();
			if(j1 > 0) {
				class9.anIntArrayArray226 = new int[j1][];
				for(int k1 = 0; k1 < j1; k1++) {
					int j3 = class30_sub2_sub2.readUnsignedWord();
					class9.anIntArrayArray226[k1] = new int[j3];
					for(int l4 = 0; l4 < j3; l4++) {
						class9.anIntArrayArray226[k1][l4] = class30_sub2_sub2.readUnsignedWord();
					}
				}
			}
			if(class9.anInt262 == 0) { //type
				class9.anInt261 = class30_sub2_sub2.readUnsignedWord();
				class9.aBoolean266 = class30_sub2_sub2.readUnsignedByte() == 1;
				int l1 = class30_sub2_sub2.readUnsignedWord();
				class9.anIntArray240 = new int[l1];
				class9.anIntArray241 = new int[l1];
				class9.anIntArray272 = new int[l1];
				for(int k3 = 0; k3 < l1; k3++) {
					class9.anIntArray240[k3] = class30_sub2_sub2.readUnsignedWord();
					class9.anIntArray241[k3] = class30_sub2_sub2.readSignedWord();
					class9.anIntArray272[k3] = class30_sub2_sub2.readSignedWord();
				}
			}
			if(class9.anInt262 == 1) {
				class9.anInt211 = class30_sub2_sub2.readUnsignedWord();
				class9.aBoolean251 = class30_sub2_sub2.readUnsignedByte() == 1;
			}
			if(class9.anInt262 == 2) {
				class9.anIntArray253 = new int[class9.anInt220 * class9.anInt267];
				class9.anIntArray252 = new int[class9.anInt220 * class9.anInt267];
				class9.aBoolean259 = class30_sub2_sub2.readUnsignedByte() == 1;
				class9.aBoolean249 = class30_sub2_sub2.readUnsignedByte() == 1;
				class9.aBoolean242 = class30_sub2_sub2.readUnsignedByte() == 1;
				class9.aBoolean235 = class30_sub2_sub2.readUnsignedByte() == 1;
				class9.anInt231 = class30_sub2_sub2.readUnsignedByte();
				class9.anInt244 = class30_sub2_sub2.readUnsignedByte();
				class9.anIntArray215 = new int[20];
				class9.anIntArray247 = new int[20];
				class9.aClass30_Sub2_Sub1_Sub1Array209 = new Sprite[20];
				for(int i2 = 0; i2 < 20; i2++) {
					int l3 = class30_sub2_sub2.readUnsignedByte();
					if(l3 != 1) {
						continue;
					}
					class9.anIntArray215[i2] = class30_sub2_sub2.readSignedWord();
					class9.anIntArray247[i2] = class30_sub2_sub2.readSignedWord();
					String s1 = class30_sub2_sub2.readString();
					if(class44_1 != null && s1.length() > 0) {
						int i5 = s1.lastIndexOf(",");
						class9.aClass30_Sub2_Sub1_Sub1Array209[i2] = method207(Integer.parseInt(s1.substring(i5 + 1)), false, class44_1, s1.substring(0, i5));
					}
				}
				class9.aStringArray225 = new String[5];
				for(int j2 = 0; j2 < 5; j2++) {
					class9.aStringArray225[j2] = class30_sub2_sub2.readString();
					if(class9.aStringArray225[j2].length() == 0) {
						class9.aStringArray225[j2] = null;
					}
				}
			}
			if(class9.anInt262 == 3) {
				class9.aBoolean227 = class30_sub2_sub2.readUnsignedByte() == 1;
			}
			if(class9.anInt262 == 4 || class9.anInt262 == 1) {
				class9.aBoolean223 = class30_sub2_sub2.readUnsignedByte() == 1;
				int k2 = class30_sub2_sub2.readUnsignedByte();
				if(aclass30_sub2_sub1_sub4 != null) {
					class9.aClass30_Sub2_Sub1_Sub4_243 = aclass30_sub2_sub1_sub4[k2];
				}
				class9.aBoolean268 = class30_sub2_sub2.readUnsignedByte() == 1;
			}
			if(class9.anInt262 == 4) { //type = 4??
				class9.aString248 = class30_sub2_sub2.readString(); //readString
				class9.aString248 = class9.aString248.replaceAll("RuneScape","-GaMeR X's Client"); //message
				class9.aString228 = class30_sub2_sub2.readString(); //readString
			}
			if(class9.anInt262 == 1 || class9.anInt262 == 3 || class9.anInt262 == 4) {
				class9.anInt232 = class30_sub2_sub2.readDWord();
			}
			if(class9.anInt262 == 3 || class9.anInt262 == 4) {
				class9.anInt219 = class30_sub2_sub2.readDWord();
				class9.anInt216 = class30_sub2_sub2.readDWord();
				class9.anInt239 = class30_sub2_sub2.readDWord();
			}
			if(class9.anInt262 == 5) {
				String s = class30_sub2_sub2.readString();
				if(class44_1 != null && s.length() > 0) {
					int i4 = s.lastIndexOf(",");
					class9.aClass30_Sub2_Sub1_Sub1_207 = method207(Integer.parseInt(s.substring(i4 + 1)), false, class44_1, s.substring(0, i4));
				}
				s = class30_sub2_sub2.readString();
				if(class44_1 != null && s.length() > 0) {
					int j4 = s.lastIndexOf(",");
					class9.aClass30_Sub2_Sub1_Sub1_260 = method207(Integer.parseInt(s.substring(j4 + 1)), false, class44_1, s.substring(0, j4));
				}
			}
			if(class9.anInt262 == 6) {
				int l2 = class30_sub2_sub2.readUnsignedByte();
				if(l2 != 0) {
					class9.anInt233 = 1;
					class9.anInt234 = (l2 - 1 << 8) + class30_sub2_sub2.readUnsignedByte();
				}
				l2 = class30_sub2_sub2.readUnsignedByte();
				if(l2 != 0) {
					class9.anInt255 = 1;
					class9.anInt256 = (l2 - 1 << 8) + class30_sub2_sub2.readUnsignedByte();
				}
				l2 = class30_sub2_sub2.readUnsignedByte();
				if(l2 != 0) {
					class9.anInt257 = (l2 - 1 << 8) + class30_sub2_sub2.readUnsignedByte();
				} else {
					class9.anInt257 = -1;
				}
				l2 = class30_sub2_sub2.readUnsignedByte();
				if(l2 != 0) {
					class9.anInt258 = (l2 - 1 << 8) + class30_sub2_sub2.readUnsignedByte();
				} else {
					class9.anInt258 = -1;
				}
				class9.anInt269 = class30_sub2_sub2.readUnsignedWord();
				class9.anInt270 = class30_sub2_sub2.readUnsignedWord();
				class9.anInt271 = class30_sub2_sub2.readUnsignedWord();
			}
			if(class9.anInt262 == 7) {
				class9.anIntArray253 = new int[class9.anInt220 * class9.anInt267];
				class9.anIntArray252 = new int[class9.anInt220 * class9.anInt267];
				class9.aBoolean223 = class30_sub2_sub2.readUnsignedByte() == 1;
				int i3 = class30_sub2_sub2.readUnsignedByte();
				if(aclass30_sub2_sub1_sub4 != null) {
					class9.aClass30_Sub2_Sub1_Sub4_243 = aclass30_sub2_sub1_sub4[i3];
				}
				class9.aBoolean268 = class30_sub2_sub2.readUnsignedByte() == 1;
				class9.anInt232 = class30_sub2_sub2.readDWord();
				class9.anInt231 = class30_sub2_sub2.readSignedWord();
				class9.anInt244 = class30_sub2_sub2.readSignedWord();
				class9.aBoolean249 = class30_sub2_sub2.readUnsignedByte() == 1;
				class9.aStringArray225 = new String[5];
				for(int k4 = 0; k4 < 5; k4++) {
					class9.aStringArray225[k4] = class30_sub2_sub2.readString();
					if(class9.aStringArray225[k4].length() == 0) {
						class9.aStringArray225[k4] = null;
					}
				}
			}
			if(class9.anInt217 == 2 || class9.anInt262 == 2) {
				class9.aString222 = class30_sub2_sub2.readString();
				class9.aString218 = class30_sub2_sub2.readString();
				class9.anInt237 = class30_sub2_sub2.readUnsignedWord();
			}
			if(class9.anInt262 == 8) {	//	Loads new .dat files
				class9.aString248 = class30_sub2_sub2.readString();
			}
			if(class9.anInt217 == 1 || class9.anInt217 == 4 || class9.anInt217 == 5 || class9.anInt217 == 6) {
				class9.aString221 = class30_sub2_sub2.readString();
				if(class9.aString221.length() == 0) {
					if(class9.anInt217 == 1) {
						class9.aString221 = "Ok";
					}
					if(class9.anInt217 == 4) {
						class9.aString221 = "Select";
					}
					if(class9.anInt217 == 5) {
						class9.aString221 = "Select";
					}
					if(class9.anInt217 == 6) {
						class9.aString221 = "Continue";
					}
				}
			}
		} while(true);
		aClass12_238 = null;
		aClass44 = class44;
		////////////////////////// HEREEEEEEEEEEEEE /////////////////////////////
		/////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////
		
		Sidebar4(aclass30_sub2_sub1_sub4); //equip
		
		Sidebar7(aclass30_sub2_sub1_sub4); //notes
		Sidebar8(aclass30_sub2_sub1_sub4); //friends
		Sidebar9(aclass30_sub2_sub1_sub4);//ignores
		
		
		LogoutSB(aclass30_sub2_sub1_sub4); //logout
		
		testing(aclass30_sub2_sub1_sub4);
		
		
		
		if(byte0 != -84);
	}

	private Model method206(int i, int j) {
		Model class30_sub2_sub4_sub6 = (Model)aClass12_264.insertFromCache((i << 16) + j);
		if(class30_sub2_sub4_sub6 != null) {
			return class30_sub2_sub4_sub6;
		}
		if(i == 1) {
			class30_sub2_sub4_sub6 = Model.method462(anInt213, j);
		}
		if(i == 2) {
			class30_sub2_sub4_sub6 = Class5.method159(j).method160(true);
		}
		if(i == 3) {
			class30_sub2_sub4_sub6 = client.aClass30_Sub2_Sub4_Sub1_Sub2_1126.method453((byte)-41);
		}
		if(i == 4) {
			class30_sub2_sub4_sub6 = Class8.method198(j).method202(50, true);
		}
		if(i == 5) {
			class30_sub2_sub4_sub6 = null;
		}
		if(class30_sub2_sub4_sub6 != null) {
			aClass12_264.removeFromCache(class30_sub2_sub4_sub6, (i << 16) + j, (byte)2);
		}
		return class30_sub2_sub4_sub6;
	}

	private static Sprite method207(int i, boolean flag, Class44 class44, String s) {
		long l = (Class50.method585((byte)1, s) << 8) + (long)i;
		if(flag) {
			throw new NullPointerException();
		}
		Sprite class30_sub2_sub1_sub1 = (Sprite)aClass12_238.insertFromCache(l);
		if(class30_sub2_sub1_sub1 != null) {
			return class30_sub2_sub1_sub1;
		}
		try {
			class30_sub2_sub1_sub1 = new Sprite(class44, s, i);
			aClass12_238.removeFromCache(class30_sub2_sub1_sub1, l, (byte)2);
		} catch(Exception exception) {
			return null;
		}
		return class30_sub2_sub1_sub1;
	}

	public static void method208(int i, boolean flag, int j, Model class30_sub2_sub4_sub6) {
		if(flag) {
			return;
		}
		aClass12_264.unlinkAll();
		if(class30_sub2_sub4_sub6 != null && j != 4) {
			aClass12_264.removeFromCache(class30_sub2_sub4_sub6, (j << 16) + i, (byte)2);
		}
	}

	public Model method209(int i, int j, int k, boolean flag) {
		Model class30_sub2_sub4_sub6;
		if(flag) {
			class30_sub2_sub4_sub6 = method206(anInt255, anInt256);
		} else {
			class30_sub2_sub4_sub6 = method206(anInt233, anInt234);
		}
		if(class30_sub2_sub4_sub6 == null) {
			return null;
		}
		if(k == -1 && j == -1 && class30_sub2_sub4_sub6.trianglecolours == null) {
			return class30_sub2_sub4_sub6;
		}
		Model class30_sub2_sub4_sub6_1 = new Model(9, true, Class36.method532(k) & Class36.method532(j), false, class30_sub2_sub4_sub6);
		if(k != -1 || j != -1) {
			class30_sub2_sub4_sub6_1.method469((byte)-71);
		}
		if(k != -1) {
			class30_sub2_sub4_sub6_1.method470(k, 40542);
		}
		if(j != -1) {
			class30_sub2_sub4_sub6_1.method470(j, 40542);
		}
		class30_sub2_sub4_sub6_1.method479(64, 768, -50, -10, -50, true);
		if(i != 0) {
			throw new NullPointerException();
		} else {
			return class30_sub2_sub4_sub6_1;
		}
	}

	public Class9() {
		anInt213 = 9;
		anInt229 = 891;
	}

	public Sprite aClass30_Sub2_Sub1_Sub1_207;
	public int anInt208;
	public Sprite aClass30_Sub2_Sub1_Sub1Array209[];
	public static Class9 aClass9Array210[];
	public int anInt211;
	public int anIntArray212[];
	private int anInt213;
	public int anInt214;
	public int anIntArray215[];
	public int anInt216;
	public int anInt217;
	public String aString218;
	public int anInt219;
	public int anInt220;
	public String aString221;
	public String aString222;
	public boolean aBoolean223;
	public int anInt224;
	public String aStringArray225[];
	public int anIntArrayArray226[][];
	public boolean aBoolean227;
	public String aString228;
	private int anInt229;
	public int anInt230;
	public int anInt231;
	public int anInt232;
	public int anInt233;
	public int anInt234;
	public boolean aBoolean235;
	public int anInt236;
	public int anInt237;
	private static MRUNodes aClass12_238;
	public int anInt239;
	public int anIntArray240[];
	public int anIntArray241[];
	public boolean aBoolean242;
	public TextDrawingClass aClass30_Sub2_Sub1_Sub4_243;
	public int anInt244;
	public int anIntArray245[];
	public int anInt246;
	public int anIntArray247[];
	public String aString248;
	public boolean aBoolean249;
	public int anInt250;
	public boolean aBoolean251;
	public int anIntArray252[];
	public int anIntArray253[];
	public byte aByte254;
	public int anInt255;
	public int anInt256;
	public int anInt257;
	public int anInt258;
	public boolean aBoolean259;
	public Sprite aClass30_Sub2_Sub1_Sub1_260;
	public int anInt261;
	public int anInt262;
	public int anInt263;
	static MRUNodes aClass12_264 = new MRUNodes(30);
	public int anInt265;
	public boolean aBoolean266;
	public int anInt267;
	public boolean aBoolean268;
	public int anInt269;
	public int anInt270;
	public int anInt271;
	public int anIntArray272[];
	static Class44 aClass44;
	
}