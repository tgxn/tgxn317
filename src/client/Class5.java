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
	||#              File: Class5.java - Npc's                 #||
	\*##########################################################*/
	
//imports
	import sign.signlink;

final class Class5 {

	public static final Class5 method159(int i) {
		for(int j = 0; j < 20; j++)
			if(aClass5Array80[j].aLong78 == (long)i)
				return aClass5Array80[j];
		anInt56 = (anInt56 + 1) % 20;
		Class5 class5 = aClass5Array80[anInt56] = new Class5();
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[i];
		class5.aLong78 = i;
		class5.method165(true, aClass30_Sub2_Sub2_60);

	// START OF NPCS - START OF GODWARS BOSSES //

	if(i == 2169)
		{ 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[2]; 
		class5.anIntArray94[0] = 5000; 
		class5.anIntArray94[1] = 5001; 
		class5.aString65 = "General graardor"; 
		class5.anInt61 = 624; 
		class5.aByteArray89 = "A huge war chief of bandos.".getBytes(); 
		}

	// END OF GOD WARS BOSSES - START OF TITANS //

	if(i == 2178) 
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[1]; 
		class5.anIntArray94[0] = 8405; 
		class5.aString65 = "Steel titan"; 
		class5.anInt61 = 230; 
		class5.aByteArray89 = "A mighty titan made of steel.".getBytes(); 
		 }
	if(i == 2179) 
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[1]; 
		class5.anIntArray94[0] = 8404; 
		class5.aString65 = "Moss titan"; 
		class5.anInt61 = 152; 
		class5.aByteArray89 = "A mighty titan made of moss.".getBytes(); 
		 }
	if(i == 2180) 
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[1]; 
		class5.anIntArray94[0] = 8403; 
		class5.aString65 = "Ice titan"; 
		class5.anInt61 = 139; 
		class5.aByteArray89 = "A mighty titan made of ice.".getBytes(); 
		 }

	// END OF TITANS - START OF REVENANTS //

	if(i == 2170) 
		  { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[1]; 
		class5.anIntArray94[0] = 8395; 
		class5.aString65 = "Revenant knight"; 
		class5.anInt61 = 126; 
		class5.aByteArray89 = "The ghost of a knight slain during the god wars.".getBytes(); 
		 }
	if(i == 2171) 
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[1]; 
		class5.anIntArray94[0] = 8396; 
		class5.aString65 = "Revenant knight"; 
		class5.anInt61 = 126; 
		class5.aByteArray89 = "The ghost of a knight slain during the god wars.".getBytes(); 
		 }
	if(i == 2172) 
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[1]; 
		class5.anIntArray94[0] = 8397; 
		class5.aString65 = "Revenant werewolf"; 
		class5.anInt61 = 75; 
		class5.aByteArray89 = "The ghost of a werewolf slain during the god wars.".getBytes(); 
		 }
	if(i == 2173) 
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[1]; 
		class5.anIntArray94[0] = 8398; 
		class5.aString65 = "Revenant ork"; 
		class5.anInt61 = 105; 
		class5.aByteArray89 = "The ghost of a ork slain during the god wars.".getBytes(); 
		 }
	if(i == 2174) 
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[1]; 
		class5.anIntArray94[0] = 8399; 
		class5.aString65 = "Revenant hellhound"; 
		class5.anInt61 = 90; 
		class5.aByteArray89 = "The ghost of a hellhound slain during the god wars.".getBytes(); 
		 }
	if(i == 2175) 
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[1]; 
		class5.anIntArray94[0] = 8400; 
		class5.aString65 = "Revenant dark beast"; 
		class5.anInt61 = 120; 
		class5.aByteArray89 = "The ghost of a dark beast slain during the god wars.".getBytes(); 
		 }
	if(i == 2176) 
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[1]; 
		class5.anIntArray94[0] = 8401; 
		class5.aString65 = "Revenant imp"; 
		class5.anInt61 = 7; 
		class5.aByteArray89 = "The ghost of a imp slain during the god wars.".getBytes(); 
		 }
	if(i == 2177) 
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[1]; 
		class5.anIntArray94[0] = 8402; 
		class5.aString65 = "Revenant cyclops"; 
		class5.anInt61 = 82; 
		class5.aByteArray89 = "The ghost of a cyclops slain during the god wars.".getBytes(); 
		 }

	// END OF REVENANTS - START OF DRAGONS //

	if(i == 1596) 
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		int changedColors = 6;
		class5.anIntArray76 = new int[changedColors];
		class5.anIntArray70 = new int[changedColors];
		class5.anIntArray76[0] = 43280;
		class5.anIntArray70[0] = 950;
		class5.anIntArray76[1] = 38172;
		class5.anIntArray70[1] = 920;
		class5.anIntArray76[2] = 38177;
		class5.anIntArray70[2] = 925;
		class5.anIntArray76[3] = 38057;
		class5.anIntArray70[3] = 930;
		class5.anIntArray76[4] = 38065;
		class5.anIntArray70[4] = 935;
		class5.anIntArray76[5] = 38090;
		class5.anIntArray70[5] = 945;
		class5.anIntArray94 = new int[4]; 
		class5.anIntArray94[0] = 8414; 
		class5.anIntArray94[1] = 8415; 
		class5.anIntArray94[2] = 8416; 
		class5.anIntArray94[3] = 8417; 
		class5.aString65 = "Dragonite dragon"; 
		class5.anInt61 = 801; 
		class5.aByteArray89 = "Dragon dragon, is that possible?".getBytes(); 
		 }
	if(i == 1595) 
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		int changedColors = 6;
		class5.anIntArray76 = new int[changedColors];
		class5.anIntArray70 = new int[changedColors];
		class5.anIntArray76[0] = 43280;
		class5.anIntArray70[0] = 36160;
		class5.anIntArray76[1] = 38172;
		class5.anIntArray70[1] = 36130;
		class5.anIntArray76[2] = 38177;
		class5.anIntArray70[2] = 36135;
		class5.anIntArray76[3] = 38057;
		class5.anIntArray70[3] = 36140;
		class5.anIntArray76[4] = 38065;
		class5.anIntArray70[4] = 36145;
		class5.anIntArray76[5] = 38090;
		class5.anIntArray70[5] = 36155;
		class5.anIntArray94 = new int[4]; 
		class5.anIntArray94[0] = 8418; 
		class5.anIntArray94[1] = 8419; 
		class5.anIntArray94[2] = 8416; 
		class5.anIntArray94[3] = 8417; 
		class5.aString65 = "Runite dragon"; 
		class5.anInt61 = 503; 
		class5.aByteArray89 = "Expirimenting with rune gone very wrong!".getBytes(); 
		 }
	if(i == 1594) 
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		int changedColors = 6;
		class5.anIntArray76 = new int[changedColors];
		class5.anIntArray70 = new int[changedColors];
		class5.anIntArray76[0] = 43280;
		class5.anIntArray70[0] = 21690;
		class5.anIntArray76[1] = 38172;
		class5.anIntArray70[1] = 21660;
		class5.anIntArray76[2] = 38177;
		class5.anIntArray70[2] = 21665;
		class5.anIntArray76[3] = 38057;
		class5.anIntArray70[3] = 21670;
		class5.anIntArray76[4] = 38065;
		class5.anIntArray70[4] = 21675;
		class5.anIntArray76[5] = 38090;
		class5.anIntArray70[5] = 21685;
		class5.anIntArray94 = new int[4]; 
		class5.anIntArray94[0] = 8414;
		class5.anIntArray94[1] = 8419;
		class5.anIntArray94[2] = 8416;
		class5.anIntArray94[3] = 8417; 
		class5.aString65 = "Adamant dragon"; 
		class5.anInt61 = 409; 
		class5.aByteArray89 = "Expirimenting with adamant gone wrong!".getBytes(); 
		 }
	if(i == 1593) 
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[4]; 
		class5.anIntArray94[0] = 8420;
		class5.anIntArray94[1] = 8419;
		class5.anIntArray94[2] = 8416;
		class5.anIntArray94[3] = 8417;
		class5.aString65 = "Mithril dragon"; 
		class5.anInt61 = 304; 
		class5.aByteArray89 = "Expirimenting with mithril gone bad!".getBytes(); 
		 }
	if(i == 1592) 
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		int changedColors = 6;
		class5.anIntArray76 = new int[changedColors];
		class5.anIntArray70 = new int[changedColors];
		class5.anIntArray76[0] = 43280;
		class5.anIntArray70[0] = 30;
		class5.anIntArray76[1] = 38172;
		class5.anIntArray70[1] = 40;
		class5.anIntArray76[2] = 38177;
		class5.anIntArray70[2] = 45;
		class5.anIntArray76[3] = 38057;
		class5.anIntArray70[3] = 50;
		class5.anIntArray76[4] = 38065;
		class5.anIntArray70[4] = 55;
		class5.anIntArray76[5] = 38090;
		class5.anIntArray70[5] = 65;
		class5.anIntArray94 = new int[4];
		class5.anIntArray94[0] = 8414;
		class5.anIntArray94[1] = 8419;
		class5.anIntArray94[2] = 8421;
		class5.anIntArray94[3] = 8417;
		class5.aString65 = "Steel dragon"; 
		class5.anInt61 = 246; 
		class5.aByteArray89 = "It's scales seem to be made of steel.".getBytes(); 
		 }
	if(i == 1591) 
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		int changedColors = 6;
		class5.anIntArray76 = new int[changedColors];
		class5.anIntArray70 = new int[changedColors];
		class5.anIntArray76[0] = 43280;
		class5.anIntArray70[0] = 10;
		class5.anIntArray76[1] = 38172;
		class5.anIntArray70[1] = 20;
		class5.anIntArray76[2] = 38177;
		class5.anIntArray70[2] = 25;
		class5.anIntArray76[3] = 38057;
		class5.anIntArray70[3] = 30;
		class5.anIntArray76[4] = 38065;
		class5.anIntArray70[4] = 35;
		class5.anIntArray76[5] = 38090;
		class5.anIntArray70[5] = 45;
		class5.anIntArray94 = new int[4]; 
		class5.anIntArray94[0] = 8420;
		class5.anIntArray94[1] = 8415;
		class5.anIntArray94[2] = 8422;
		class5.anIntArray94[3] = 8417;
		class5.aString65 = "Iron dragon"; 
		class5.anInt61 = 189; 
		class5.aByteArray89 = "It's scales seem to be made of iron.".getBytes(); 
		 }
	if(i == 1590) 
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		int changedColors = 6;
		class5.anIntArray76 = new int[changedColors];
		class5.anIntArray70 = new int[changedColors];
		class5.anIntArray76[0] = 43280;
		class5.anIntArray70[0] = 5640;
		class5.anIntArray76[1] = 38172;
		class5.anIntArray70[1] = 5650;
		class5.anIntArray76[2] = 38177;
		class5.anIntArray70[2] = 5655;
		class5.anIntArray76[3] = 38057;
		class5.anIntArray70[3] = 5660;
		class5.anIntArray76[4] = 38065;
		class5.anIntArray70[4] = 5665;
		class5.anIntArray76[5] = 38090;
		class5.anIntArray70[5] = 5675;
		class5.anIntArray94 = new int[4]; 
		class5.anIntArray94[0] = 8418;
		class5.anIntArray94[1] = 8423;
		class5.anIntArray94[2] = 8416;
		class5.anIntArray94[3] = 8417;
		class5.aString65 = "Bronze dragon"; 
		class5.anInt61 = 131; 
		class5.aByteArray89 = "It's scales seem to be made of bronze.".getBytes(); 
		 }
	if(i == 51) 
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		int changedColors = 6;
		class5.anIntArray76 = new int[changedColors];
		class5.anIntArray70 = new int[changedColors];
		class5.anIntArray76[0] = 2469;
		class5.anIntArray70[0] = 40;
		class5.anIntArray76[1] = 2588;
		class5.anIntArray70[1] = 35;
		class5.anIntArray76[2] = 910;
		class5.anIntArray70[2] = 15;
		class5.anIntArray76[3] = 912;
		class5.anIntArray70[3] = 20;
		class5.anIntArray76[4] = 1938;
		class5.anIntArray70[4] = 25;
		class5.anIntArray76[5] = 1814;
		class5.anIntArray70[5] = 30;
		class5.anIntArray94 = new int[4];
		class5.anIntArray94[0] = 8424;
		class5.anIntArray94[1] = 8425;
		class5.anIntArray94[2] = 8426;
		class5.anIntArray94[3] = 8427;
		class5.aString65 = "Baby black dragon"; 
		class5.anInt61 = 83; 
		class5.aByteArray89 = "Cute and young, but still dangerous.".getBytes(); 
		 }
	if(i == 52) 
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		int changedColors = 6;
		class5.anIntArray76 = new int[changedColors];
		class5.anIntArray70 = new int[changedColors];
		class5.anIntArray76[0] = 2469;
		class5.anIntArray70[0] = 43940;
		class5.anIntArray76[1] = 2588;
		class5.anIntArray70[1] = 43935;
		class5.anIntArray76[2] = 910;
		class5.anIntArray70[2] = 43915;
		class5.anIntArray76[3] = 912;
		class5.anIntArray70[3] = 43920;
		class5.anIntArray76[4] = 1938;
		class5.anIntArray70[4] = 43925;
		class5.anIntArray76[5] = 1814;
		class5.anIntArray70[5] = 43930;
		class5.anIntArray94 = new int[4];
		class5.anIntArray94[0] = 8424;
		class5.anIntArray94[1] = 8425;
		class5.anIntArray94[2] = 8426;
		class5.anIntArray94[3] = 8427;
		class5.aString65 = "Baby blue dragon"; 
		class5.anInt61 = 48; 
		class5.aByteArray89 = "Cute and young, but still dangerous.".getBytes(); 
		 }
	if(i == 1589) 
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[4];
		class5.anIntArray94[0] = 8424;
		class5.anIntArray94[1] = 8425;
		class5.anIntArray94[2] = 8426;
		class5.anIntArray94[3] = 8427;
		class5.aString65 = "Baby red dragon"; 
		class5.anInt61 = 48; 
		class5.aByteArray89 = "Cute and young, but still dangerous.".getBytes(); 
		 }
	if(i == 54)
		{ 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		int changedColors = 6;
		class5.anIntArray76 = new int[changedColors];
		class5.anIntArray70 = new int[changedColors];
		class5.anIntArray76[0] = 2469;
		class5.anIntArray70[0] = 20;
		class5.anIntArray76[1] = 2588;
		class5.anIntArray70[1] = 15;
		class5.anIntArray76[2] = 910;
		class5.anIntArray70[2] = 3;
		class5.anIntArray76[3] = 912;
		class5.anIntArray70[3] = 5;
		class5.anIntArray76[4] = 1938;
		class5.anIntArray70[4] = 7;
		class5.anIntArray76[5] = 1814;
		class5.anIntArray70[5] = 10;
		class5.anIntArray94 = new int[4]; 
		class5.anIntArray94[0] = 8448; 
		class5.anIntArray94[1] = 8449; 
		class5.anIntArray94[2] = 8447; 
		class5.anIntArray94[3] = 8450; 
		class5.aString65 = "Black dragon"; 
		class5.anInt61 = 227; 
		class5.aByteArray89 = "A fierce dragon with black scales!".getBytes(); 
		}
	if(i == 55)
		{ 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		int changedColors = 6;
		class5.anIntArray76 = new int[changedColors];
		class5.anIntArray70 = new int[changedColors];
		class5.anIntArray76[0] = 2469;
		class5.anIntArray70[0] = 43940;
		class5.anIntArray76[1] = 2588;
		class5.anIntArray70[1] = 43935;
		class5.anIntArray76[2] = 910;
		class5.anIntArray70[2] = 43915;
		class5.anIntArray76[3] = 912;
		class5.anIntArray70[3] = 43920;
		class5.anIntArray76[4] = 1938;
		class5.anIntArray70[4] = 43925;
		class5.anIntArray76[5] = 1814;
		class5.anIntArray70[5] = 43930;
		class5.anIntArray94 = new int[4]; 
		class5.anIntArray94[0] = 8448; 
		class5.anIntArray94[1] = 8449; 
		class5.anIntArray94[2] = 8447; 
		class5.anIntArray94[3] = 8450; 
		class5.aString65 = "Blue dragon"; 
		class5.anInt61 = 111; 
		class5.aByteArray89 = "A mother dragon.".getBytes(); 
		}
	if(i == 53)
		{ 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[4]; 
		class5.anIntArray94[0] = 8448; 
		class5.anIntArray94[1] = 8449; 
		class5.anIntArray94[2] = 8447; 
		class5.anIntArray94[3] = 8450; 
		class5.aString65 = "Red dragon"; 
		class5.anInt61 = 152; 
		class5.aByteArray89 = "A huge powerful dragon.".getBytes(); 
		}
	if(i == 941)
		{ 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[4]; 
		class5.anIntArray94[0] = 8451; 
		class5.anIntArray94[1] = 8452; 
		class5.anIntArray94[2] = 8453; 
		class5.anIntArray94[3] = 8450; 
		class5.aString65 = "Green dragon"; 
		class5.anInt61 = 79; 
		class5.aByteArray89 = "Must be relasted to Elvarg.".getBytes(); 
		}
	if(i == 50)
		{ 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[6]; 
		class5.anIntArray94[0] = 8454; 
		class5.anIntArray94[1] = 8455; 
		class5.anIntArray94[2] = 8456; 
		class5.anIntArray94[3] = 8457; 
		class5.anIntArray94[4] = 8458; 
		class5.anIntArray94[5] = 8459; 
		class5.aString65 = "King black dragon"; 
		class5.anInt61 = 279; 
		class5.aByteArray89 = "A fierce dragon with black scales and three heads.".getBytes(); 
		}

	// END OF DRAGONS - STAR OF KALPHITES //

	// END OF KALPHITES - STAR OF OTHER NPCS //

	if(i == 1582)
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[3];
		class5.anIntArray94[0] = 8433;
		class5.anIntArray94[1] = 8434;
		class5.anIntArray94[2] = 8430;
		class5.aString65 = "Fire giant"; 
		class5.anInt61 = 86; 
		class5.aByteArray89 = "A very large elemental adversary.".getBytes(); 
		 }
	if(i == 1583)
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[3];
		class5.anIntArray94[0] = 8433;
		class5.anIntArray94[1] = 8434;
		class5.anIntArray94[2] = 8430;
		class5.aString65 = "Fire giant"; 
		class5.anInt61 = 85; 
		class5.aByteArray89 = "A very large elemental adversary.".getBytes(); 
		 }
	if(i == 1584)
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[3];
		class5.anIntArray94[0] = 8433;
		class5.anIntArray94[1] = 8434;
		class5.anIntArray94[2] = 8430;
		class5.aString65 = "Fire giant"; 
		class5.anInt61 = 86; 
		class5.aByteArray89 = "A very large elemental adversary.".getBytes(); 
		 }
	if(i == 1585)
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[3];
		class5.anIntArray94[0] = 8433;
		class5.anIntArray94[1] = 8434;
		class5.anIntArray94[2] = 8430;
		class5.aString65 = "Fire giant"; 
		class5.anInt61 = 89; 
		class5.aByteArray89 = "A very large elemental adversary.".getBytes(); 
		 }
	if(i == 1586)
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[3];
		class5.anIntArray94[0] = 8433;
		class5.anIntArray94[1] = 8434;
		class5.anIntArray94[2] = 8430;
		class5.aString65 = "Fire giant"; 
		class5.anInt61 = 86; 
		class5.aByteArray89 = "A very large elemental adversary.".getBytes(); 
		 }
	if(i == 110)
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[3];
		class5.anIntArray94[0] = 8433;
		class5.anIntArray94[1] = 8434;
		class5.anIntArray94[2] = 8430;
		class5.aString65 = "Fire giant"; 
		class5.anInt61 = 81; 
		class5.aByteArray89 = "A very large elemental adversary.".getBytes(); 
		 }
	if(i == 117)
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[5];
		class5.anIntArray94[0] = 8435;
		class5.anIntArray94[1] = 8436;
		class5.anIntArray94[2] = 8430;
		class5.anIntArray94[3] = 8437;
		class5.anIntArray94[4] = 8438;
		class5.aString65 = "Hill giant"; 
		class5.anInt61 = 28; 
		class5.aByteArray89 = "A very large foe.".getBytes(); 
		 }
	if(i == 111)
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[3];
		class5.anIntArray94[0] = 8431;
		class5.anIntArray94[1] = 8432;
		class5.anIntArray94[2] = 8430;
		class5.aString65 = "Ice giant"; 
		class5.anInt61 = 53; 
		class5.aByteArray89 = "He's got icicles in his beard.".getBytes(); 
		 }
	if(i == 112)
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[3];
		class5.anIntArray94[0] = 8428;
		class5.anIntArray94[1] = 8429;
		class5.anIntArray94[2] = 8430;
		class5.aString65 = "Moss giant"; 
		class5.anInt61 = 42; 
		class5.aByteArray89 = "His beard seems to have a life of it's own.".getBytes(); 
		 }
	if(i == 1587)
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[3];
		class5.anIntArray94[0] = 8428;
		class5.anIntArray94[1] = 8429;
		class5.anIntArray94[2] = 8430;
		class5.aString65 = "Moss giant"; 
		class5.anInt61 = 42; 
		class5.aByteArray89 = "His beard seems to have a life of it's own.".getBytes(); 
		 }
	if(i == 1588)
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[3];
		class5.anIntArray94[0] = 8428;
		class5.anIntArray94[1] = 8429;
		class5.anIntArray94[2] = 8430;
		class5.aString65 = "Moss giant"; 
		class5.anInt61 = 42; 
		class5.aByteArray89 = "His beard seems to have a life of it's own.".getBytes(); 
		 }
	if(i == 1681)
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[3];
		class5.anIntArray94[0] = 8428;
		class5.anIntArray94[1] = 8429;
		class5.anIntArray94[2] = 8430;
		class5.aString65 = "Moss giant"; 
		class5.anInt61 = 84; 
		class5.aByteArray89 = "His beard seems to have a life of it's own.".getBytes(); 
		 }
	if(i == 83)
		{ 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[5]; 
		class5.anIntArray94[0] = 8442;
		class5.anIntArray94[1] = 8443;
		class5.anIntArray94[2] = 8444;
		class5.anIntArray94[3] = 8445;
		class5.anIntArray94[4] = 8446;
		class5.aString65 = "Greater demon"; 
		class5.anInt61 = 92; 
		class5.aByteArray89 = "Big. red, and incredibly evil.".getBytes(); 
		}
	if(i == 752)
		{ 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[3]; 
		class5.anIntArray94[0] = 8439; 
		class5.anIntArray94[1] = 8440; 
		class5.anIntArray94[2] = 8441; 
		class5.aString65 = "Lesser demon"; 
		class5.anInt61 = 82; 
		class5.aByteArray89 = "Lesser, but still pretty huge.".getBytes(); 
		}
	if(i == 82)
		{ 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[3]; 
		class5.anIntArray94[0] = 8439; 
		class5.anIntArray94[1] = 8440; 
		class5.anIntArray94[2] = 8441; 
		class5.aString65 = "Lesser demon"; 
		class5.anInt61 = 82; 
		class5.aByteArray89 = "Lesser, but still pretty huge.".getBytes(); 
		}
	if(i == 2783) 
		 { 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[1]; 
		class5.anIntArray94[0] = 8413; 
		class5.aString65 = "Dark beast"; 
		class5.anInt61 = 139; 
		class5.aByteArray89 = "A beast from the dark dimension.".getBytes(); 
		 }
	if(i == 2181)
		{ 
		aClass30_Sub2_Sub2_60.currentOffset = anIntArray72[630]; 
		class5.method165(true, aClass30_Sub2_Sub2_60); 
		class5.aStringArray66 = new String[5];
		class5.aStringArray66[1] = "Attack";
		class5.anIntArray94 = new int[7]; 
		int changedColors = 1; 
		class5.anIntArray94[0] = 4909; 
		class5.anIntArray94[1] = 4911; 
		class5.anIntArray94[2] = 4912;
		class5.anIntArray94[3] = 4913;
		class5.anIntArray94[4] = 4914;
		class5.anIntArray94[5] = 4915;
		class5.anIntArray94[6] = 4916;
		class5.aString65 = "Pirate"; 
		class5.anInt61 = 76; 
		class5.aByteArray89 = "Arrgh, examain info.".getBytes(); 
		}


	// END OF OTHER NPCS - END OF NPCS //

		return class5;
	}

	public final Model method160(boolean flag) {
		if(anIntArray88 != null) {
			Class5 class5 = method161(anInt64);
			if(class5 == null)
				return null;
			else
				return class5.method160(true);
		}
		if(anIntArray73 == null)
			return null;
		boolean flag1 = false;
		if(!flag)
			anInt64 = 303;
		for(int i = 0; i < anIntArray73.length; i++)
			if(!Model.method463(anIntArray73[i]))
				flag1 = true;
		if(flag1)
			return null;
		Model aclass30_sub2_sub4_sub6[] = new Model[anIntArray73.length];
		for(int j = 0; j < anIntArray73.length; j++)
			aclass30_sub2_sub4_sub6[j] = Model.method462(anInt69, anIntArray73[j]);
		Model class30_sub2_sub4_sub6;
		if(aclass30_sub2_sub4_sub6.length == 1)
			class30_sub2_sub4_sub6 = aclass30_sub2_sub4_sub6[0];
		else
			class30_sub2_sub4_sub6 = new Model(aclass30_sub2_sub4_sub6.length, aclass30_sub2_sub4_sub6, -38);
		if(anIntArray76 != null) {
			for(int k = 0; k < anIntArray76.length; k++)
				class30_sub2_sub4_sub6.method476(anIntArray76[k], anIntArray70[k]);
		}
		return class30_sub2_sub4_sub6;
	}

	public final Class5 method161(int i) {
		int j = -1;
		if(i != 1834)
			aBoolean81 = !aBoolean81;
		if(anInt57 != -1) {
			VarBit class37 = VarBit.cache[anInt57];
			int k = class37.anInt648;
			int l = class37.anInt649;
			int i1 = class37.anInt650;
			int j1 = client.anIntArray1232[i1 - l];
			j = aClient82.variousSettings[k] >> l & j1;
		} else {
			if(anInt59 != -1) {
				j = aClient82.variousSettings[anInt59];
			}
		}
		if(j < 0 || j >= anIntArray88.length || anIntArray88[j] == -1)
			return null;
		else
			return method159(anIntArray88[j]);
	}

	public static final void method162(Class44 class44) {
		aClass30_Sub2_Sub2_60 = new Stream(class44.method571("npc.dat", null));
		Stream class30_sub2_sub2 = new Stream(class44.method571("npc.idx", null));
		anInt62 = class30_sub2_sub2.readUnsignedWord();
		anIntArray72 = new int[anInt62];
		int i = 2;
		for(int j = 0; j < anInt62; j++) {
			anIntArray72[j] = i;
			i += class30_sub2_sub2.readUnsignedWord();
		}
		aClass5Array80 = new Class5[20];
		for(int k = 0; k < 20; k++)
			aClass5Array80[k] = new Class5();
	}

	public static final void method163(int i) {
		aClass12_95 = null;
		anIntArray72 = null;
		if(i >= 0)
			anInt74 = 60;
		aClass5Array80 = null;
		aClass30_Sub2_Sub2_60 = null;
	}

	public final Model method164(int i, int j, int k, int ai[]) {
		if(anIntArray88 != null) {
			Class5 class5 = method161(anInt64);
			if(class5 == null)
				return null;
			else
				return class5.method164(0, j, k, ai);
		}
		Model class30_sub2_sub4_sub6 = (Model)aClass12_95.insertFromCache(aLong78);
		if(i != 0) {
			for(int l = 1; l > 0; l++);
		}
		if(class30_sub2_sub4_sub6 == null) {
			boolean flag = false;
			for(int i1 = 0; i1 < anIntArray94.length; i1++)
				if(!Model.method463(anIntArray94[i1]))
					flag = true;
			if(flag)
				return null;
			Model aclass30_sub2_sub4_sub6[] = new Model[anIntArray94.length];
			for(int j1 = 0; j1 < anIntArray94.length; j1++)
				aclass30_sub2_sub4_sub6[j1] = Model.method462(anInt69, anIntArray94[j1]);
			if(aclass30_sub2_sub4_sub6.length == 1)
				class30_sub2_sub4_sub6 = aclass30_sub2_sub4_sub6[0];
			else
				class30_sub2_sub4_sub6 = new Model(aclass30_sub2_sub4_sub6.length, aclass30_sub2_sub4_sub6, -38);
			if(anIntArray76 != null) {
				for(int k1 = 0; k1 < anIntArray76.length; k1++)
					class30_sub2_sub4_sub6.method476(anIntArray76[k1], anIntArray70[k1]);
			}
			class30_sub2_sub4_sub6.method469((byte)-71);
			class30_sub2_sub4_sub6.method479(64 + anInt85, 850 + anInt92, -30, -50, -30, true);
			aClass12_95.removeFromCache(class30_sub2_sub4_sub6, aLong78, (byte)2);
		}
		Model class30_sub2_sub4_sub6_1 = Model.aModel_1621;
		class30_sub2_sub4_sub6_1.method464(7, class30_sub2_sub4_sub6, Class36.method532(k) & Class36.method532(j));
		if(k != -1 && j != -1)
			class30_sub2_sub4_sub6_1.method471(-20491, ai, j, k);
		else
		if(k != -1)
			class30_sub2_sub4_sub6_1.method470(k, 40542);
		if(anInt91 != 128 || anInt86 != 128)
			class30_sub2_sub4_sub6_1.method478(anInt91, anInt91, anInt63, anInt86);
		class30_sub2_sub4_sub6_1.method466(false);
		class30_sub2_sub4_sub6_1.anIntArrayArray1658 = null;
		class30_sub2_sub4_sub6_1.anIntArrayArray1657 = null;
		if(aByte68 == 1)
			class30_sub2_sub4_sub6_1.aBoolean1659 = true;
		return class30_sub2_sub4_sub6_1;
	}

	private final void method165(boolean flag, Stream class30_sub2_sub2) {
		if(!flag)
			throw new NullPointerException();
		do {
			int i = class30_sub2_sub2.readUnsignedByte();
			if(i == 0)
				return;
			if(i == 1) {
				int j = class30_sub2_sub2.readUnsignedByte();
				anIntArray94 = new int[j];
				for(int j1 = 0; j1 < j; j1++)
					anIntArray94[j1] = class30_sub2_sub2.readUnsignedWord();
			} else
			if(i == 2)
				aString65 = class30_sub2_sub2.readString();
			else
			if(i == 3)
				aByteArray89 = class30_sub2_sub2.readBytes();
			else
			if(i == 12)
				aByte68 = class30_sub2_sub2.readSignedByte();
			else
			if(i == 13)
				anInt77 = class30_sub2_sub2.readUnsignedWord();
			else
			if(i == 14)
				anInt67 = class30_sub2_sub2.readUnsignedWord();
			else
			if(i == 17) {
				anInt67 = class30_sub2_sub2.readUnsignedWord();
				anInt58 = class30_sub2_sub2.readUnsignedWord();
				anInt83 = class30_sub2_sub2.readUnsignedWord();
				anInt55 = class30_sub2_sub2.readUnsignedWord();
			} else if(i >= 30 && i < 40) {
				if(aStringArray66 == null)
					aStringArray66 = new String[5];
				aStringArray66[i - 30] = class30_sub2_sub2.readString();
				if(aStringArray66[i - 30].equalsIgnoreCase("hidden"))
					aStringArray66[i - 30] = null;
			} else if(i == 40) {
				int k = class30_sub2_sub2.readUnsignedByte();
				anIntArray76 = new int[k];
				anIntArray70 = new int[k];
				for(int k1 = 0; k1 < k; k1++) {
					anIntArray76[k1] = class30_sub2_sub2.readUnsignedWord();
					anIntArray70[k1] = class30_sub2_sub2.readUnsignedWord();
				}
			} else if(i == 60) {
				int l = class30_sub2_sub2.readUnsignedByte();
				anIntArray73 = new int[l];
				for(int l1 = 0; l1 < l; l1++)
					anIntArray73[l1] = class30_sub2_sub2.readUnsignedWord();
			} else
			if(i == 90)
				anInt96 = class30_sub2_sub2.readUnsignedWord();
			else
			if(i == 91)
				anInt71 = class30_sub2_sub2.readUnsignedWord();
			else
			if(i == 92)
				anInt90 = class30_sub2_sub2.readUnsignedWord();
			else
			if(i == 93)
				aBoolean87 = false;
			else
			if(i == 95)
				anInt61 = class30_sub2_sub2.readUnsignedWord();
			else
			if(i == 97)
				anInt91 = class30_sub2_sub2.readUnsignedWord();
			else
			if(i == 98)
				anInt86 = class30_sub2_sub2.readUnsignedWord();
			else
			if(i == 99)
				aBoolean93 = true;
			else
			if(i == 100)
				anInt85 = class30_sub2_sub2.readSignedByte();
			else
			if(i == 101)
				anInt92 = class30_sub2_sub2.readSignedByte() * 5;
			else
			if(i == 102)
				anInt75 = class30_sub2_sub2.readUnsignedWord();
			else
			if(i == 103)
				anInt79 = class30_sub2_sub2.readUnsignedWord();
			else if(i == 106) {
				anInt57 = class30_sub2_sub2.readUnsignedWord();
				if(anInt57 == 65535)
					anInt57 = -1;
				anInt59 = class30_sub2_sub2.readUnsignedWord();
				if(anInt59 == 65535)
					anInt59 = -1;
				int i1 = class30_sub2_sub2.readUnsignedByte();
				anIntArray88 = new int[i1 + 1];
				for(int i2 = 0; i2 <= i1; i2++) {
					anIntArray88[i2] = class30_sub2_sub2.readUnsignedWord();
					if(anIntArray88[i2] == 65535)
						anIntArray88[i2] = -1;
					}
			} else 
			if(i == 107)
				aBoolean84 = false;
		} while(true);
	}

	Class5() {
		anInt55 = -1;
		anInt57 = -1;
		anInt58 = -1;
		anInt59 = -1;
		anInt61 = -1;
		anInt63 = 9;
		anInt64 = 1834;
		anInt67 = -1;
		aByte68 = 1;
		anInt69 = 9;
		anInt71 = -1;
		anInt75 = -1;
		anInt77 = -1;
		aLong78 = -1L;
		anInt79 = 32;
		aBoolean81 = false;
		anInt83 = -1;
		aBoolean84 = true;
		anInt86 = 128;
		aBoolean87 = true;
		anInt90 = -1;
		anInt91 = 128;
		aBoolean93 = false;
		anInt96 = -1;
	}

	public int anInt55;
	private static int anInt56;
	public int anInt57;
	public int anInt58;
	public int anInt59;
	private static Stream aClass30_Sub2_Sub2_60;
	public int anInt61;
	public static int anInt62;
	private int anInt63;
	private int anInt64;
	public String aString65;
	public String aStringArray66[];
	public int anInt67;
	public byte aByte68;
	private int anInt69;
	private int anIntArray70[];
	public int anInt71;
	private static int anIntArray72[];
	private int anIntArray73[];
	private static int anInt74 = 748;
	public int anInt75;
	private int anIntArray76[];
	public int anInt77;
	public long aLong78;
	public int anInt79;
	private static Class5 aClass5Array80[];
	private boolean aBoolean81;
	public static client aClient82;
	public int anInt83;
	public boolean aBoolean84;
	private int anInt85;
	private int anInt86;
	public boolean aBoolean87;
	public int anIntArray88[];
	public byte aByteArray89[];
	public int anInt90;
	private int anInt91;
	private int anInt92;
	public boolean aBoolean93;
	private int anIntArray94[];
	public static MRUNodes aClass12_95 = new MRUNodes(30);
	public int anInt96;
}
