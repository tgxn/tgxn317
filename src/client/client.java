package client;


import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.zip.*;

import java.applet.AppletContext;
import java.math.BigInteger;
import map.MapMain;
import sign.signlink;

public class client extends Applet_Sub1 {

    public static client client1 = null;

    public void sendQuest(String s, int i) {
        RSInterface.interfaceCache[i].message = s;
        if (RSInterface.interfaceCache[i].parentID == tabInterfaceIDs[tabID]) {
            needDrawTabArea = true;
        }
    }

    private void getCache() { //this is called on every load
        File cacheLocation = new File(sign.signlink.findcachedir()); //cache dir
        File versionFile = new File(sign.signlink.findcachedir() + "version.ini"); //main cache file
        System.out.println("Checking versions, please wait...");
        getLatestRevs(); //gets the latest revisions form the site
        if (latestClientVersion.equals(Csettings.clientVersion)) { //check client version
            if (latestCacheVersion.equals(Csettings.cacheVersion())) {
                System.out.println("Both Client & Cache are up to date, loading Client.");
            } else {
                System.out.println("Client up to date, but cache version outdated, Updating, Please Wait...");
                if (!cacheLocation.exists()) { //c:/cache <- if it dont exist
                    cacheLocation.mkdir(); //make it
                    updateCache();
                } else {
                    deleteFolder(cacheLocation);
                    cacheLocation.mkdir(); //make it
                    updateCache();
                }
            }
        } else {
            System.out.println("Client version is outdated!");
            System.out.println("Opening download page!");
            launchURL(Csettings.clientDownloadURL);
        }
    }

    public void getLatestRevs() {
        try {
            URL url = new URL(Csettings.versionCheck);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = in.readLine();
            while (line != null) {
                if (line.startsWith("cache")) {
                    latestCacheVersion = line.substring(6);
                }
                if (line.startsWith("client")) {
                    latestClientVersion = line.substring(7);
                }
                line = in.readLine();
            }
            in.close();
        } catch (MalformedURLException mue) {
            System.out.println("Error reading new version!");
        } catch (IOException ioe) {
            System.out.println("Error reading new version!");
        } catch (Exception e) {
            System.out.println("Error reading new version!");
        }
    }

    public void updateCache() {
        if (downloadFile()) { //get the file, return true if it did
            if (unZipFile()) { //unzip it
                deleteFile(); //delete the zip file
                System.out.println("Cache has been downloaded and installed sucessfully."); //show message
            } else {
                System.out.println("Cache has been downloaded, but fail to install."); //show message
            }
        } else {
            deleteFile(); //delete the corrup zip
            System.out.println("Failed to download cache, please contact gamerx."); //show message
        }
    }

    private boolean downloadFile() {
        URLConnection connectionOne;
        long connectionLength;
        FileOutputStream fileOut;
        BufferedOutputStream bufferedOut;
        File files;
        System.out.println("Getting cache - Speed depends on connection");
        try {
            connectionOne = new URL(urlLoc).openConnection();
            connectionLength = (long) connectionOne.getContentLength();
            fileOut = new FileOutputStream(saveAs);
            bufferedOut = new BufferedOutputStream(fileOut);
            writeStream(connectionOne.getInputStream(), bufferedOut);
            fileOut.close();
            bufferedOut.close();
            files = new File(saveAs);
            if ((connectionLength - files.length()) != 0) {
                return false;
            }
        } catch (IOException iox1) {
            iox1.printStackTrace();
        }
        try {
            System.out.println("Cache was downloaded!");
            return true;
        } catch (Exception e5) {
            //e5.printStackTrace();
        }
        return false;
    }

    private boolean unZipFile() { //to unzip the cache zip
        ZipFile zipFile;
        Enumeration enumer;
        ZipEntry zipEnter;
        try {
            zipFile = new ZipFile(saveAs); //get the zip file
            enumer = zipFile.entries(); //enumerate!
            while (enumer.hasMoreElements()) { //theres items left in the buffer/que
                zipEnter = (ZipEntry) enumer.nextElement(); //goto next
                if (zipEnter.isDirectory()) { //if the entry is a directory
                    System.out.println("Creating Directory: " + zipEnter.getName());
                    new File(homeDir + zipEnter.getName()).mkdir();
                } else {
                    System.out.println("Extracting File: " + zipEnter.getName());
                    writeStream(zipFile.getInputStream(zipEnter), new BufferedOutputStream(new FileOutputStream(homeDir + zipEnter.getName())));
                }
            }
            zipFile.close(); //close the zip file
            return true;
        } catch (Exception e3) {
            //e3.printStackTrace(); //error
            return false;
        }
    }

    private void deleteFile() { //delete the cache file "cache.zip"
        try {
            File f1 = new File(saveAs);
            f1.delete();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public boolean deleteFolder(File dir) { //delete the cachefolder
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteFolder(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    private void writeStream(InputStream input, OutputStream output) throws IOException {
        byte[] bytes;
        int ints;
        bytes = new byte[1024];
        while ((ints = input.read(bytes)) >= 0) {
            output.write(bytes, 0, ints);
        }
        input.close();
        output.close();
    }

    public void setSidebarInterface(int sidebarID, int interfaceID) {
        tabInterfaceIDs[sidebarID] = interfaceID;
        if (tabID == sidebarID) {
            needDrawTabArea = true;
        }
        tabAreaAltered = true;
    }

    public static void setTab(int id) {
        needDrawTabArea = true;
        tabID = id;
        tabAreaAltered = true;
    }

    public int getOrbFill(int statusInt) {
        if (statusInt <= 100 && statusInt >= 97) {
            return 0;
        } else if (statusInt <= 96 && statusInt >= 93) {
            return 1;
        } else if (statusInt <= 92 && statusInt >= 89) {
            return 2;
        } else if (statusInt <= 88 && statusInt >= 85) {
            return 3;
        } else if (statusInt <= 84 && statusInt >= 81) {
            return 4;
        } else if (statusInt <= 80 && statusInt >= 77) {
            return 5;
        } else if (statusInt <= 76 && statusInt >= 73) {
            return 6;
        } else if (statusInt <= 72 && statusInt >= 69) {
            return 7;
        } else if (statusInt <= 68 && statusInt >= 65) {
            return 8;
        } else if (statusInt <= 64 && statusInt >= 61) {
            return 9;
        } else if (statusInt <= 60 && statusInt >= 57) {
            return 10;
        } else if (statusInt <= 56 && statusInt >= 53) {
            return 11;
        } else if (statusInt <= 52 && statusInt >= 49) {
            return 12;
        } else if (statusInt <= 48 && statusInt >= 45) {
            return 13;
        } else if (statusInt <= 44 && statusInt >= 41) {
            return 14;
        } else if (statusInt <= 40 && statusInt >= 37) {
            return 15;
        } else if (statusInt <= 36 && statusInt >= 33) {
            return 16;
        } else if (statusInt <= 32 && statusInt >= 29) {
            return 17;
        } else if (statusInt <= 28 && statusInt >= 25) {
            return 18;
        } else if (statusInt <= 24 && statusInt >= 21) {
            return 19;
        } else if (statusInt <= 20 && statusInt >= 17) {
            return 20;
        } else if (statusInt <= 16 && statusInt >= 13) {
            return 21;
        } else if (statusInt <= 12 && statusInt >= 9) {
            return 22;
        } else if (statusInt <= 8 && statusInt >= 7) {
            return 23;
        } else if (statusInt <= 6 && statusInt >= 5) {
            return 24;
        } else if (statusInt <= 4 && statusInt >= 3) {
            return 25;
        } else if (statusInt <= 2 && statusInt >= 1) {
            return 26;
        } else if (statusInt <= 0) {
            return 27;
        }
        return 0;
    }

    public int getOrbTextColor(int statusInt) {
        if (statusInt >= 75 && statusInt <= 100) {
            return 0x00FF00;
        } else if (statusInt >= 50 && statusInt <= 74) {
            return 0xFFFF00;
        } else if (statusInt >= 25 && statusInt <= 49) {
            return 0xFF981F;
        } else {
            return 0xFF0000;
        }
    }

    public void loadOrbSprites() {
        String OrbDirectory = sign.signlink.findcachedir() + "sprites/misc/orbs/";
        for (int i = 1; i <= 14; i++) {
            ORBS[i] = new Sprite(OrbDirectory + "ORBS " + i + ".PNG");
        }
    }

    public void drawHP() {
        int health;
        String OrbDirectory = sign.signlink.findcachedir() + "sprites/misc/orbs/";
        String cHP = RSInterface.interfaceCache[4016].message;
        cHP = cHP.replaceAll("%", "");
        int currentHP = Integer.parseInt(cHP);
        String mHP = RSInterface.interfaceCache[4017].message;
        mHP = mHP.replaceAll("%", "");
        int maxHP2 = Integer.parseInt(mHP);
        health = (int) (((double) currentHP / (double) maxHP2) * 100D);
        ORBS[0] = new Sprite(OrbDirectory + "ORBS 0.PNG", 27, getOrbFill(health));
        ORBS[1].method348(174, 14); //normal back bit
        ORBS[2].method348(177, 17); //health red circle (fill)
        ORBS[0].method348(177, 17); //backbit
        if (health <= 20) {
            if (loopCycle % 40 < 20) {
                ORBS[3].method348(183, 25); //flash it
            }
        } else {
            ORBS[3].method348(183, 25); //no flasjhing
        }
        smallFont.drawCenteredText(getOrbTextColor(health), 218, RSInterface.interfaceCache[4016].message, 40, true);
    }

    public void drawPrayer() {
        int prayer;
        String OD = sign.signlink.findcachedir() + "sprites/misc/orbs/";
        String cPR = RSInterface.interfaceCache[4012].message;
        cPR = cPR.replaceAll("%", "");
        int currentPR = Integer.parseInt(cPR);
        String mPR = RSInterface.interfaceCache[4013].message;
        mPR = mPR.replaceAll("%", "");
        int maxPR2 = Integer.parseInt(mPR);
        prayer = (int) (((double) currentPR / (double) maxPR2) * 100D);
        ORBS[0] = new Sprite(OD + "ORBS 0.PNG", 27, getOrbFill(prayer));
        if (!prayClicked) {
            if (prayHover) {
                ORBS[7].method348(190, 53);
            } else if (!prayHover) {
                ORBS[1].method348(190, 53);
            }

            ORBS[4].method348(193, 56);
        } else if (prayClicked) {

            if (prayHover) {
                ORBS[7].method348(190, 53);
            } else if (!prayHover) {
                ORBS[1].method348(190, 53);
            }

            ORBS[5].method348(193, 56);
        }
        ORBS[0].method348(194, 56);
        if (prayer <= 20) {
            if (loopCycle % 40 < 20) {
                ORBS[6].method348(197, 60);
            }
        } else {
            ORBS[6].method348(197, 60);
        }
        int Y = 79;

        smallFont.drawCenteredText(getOrbTextColor(prayer), 234, RSInterface.interfaceCache[4012].message, Y, true);
    }

    public void drawRunOrb() {
        String OD = sign.signlink.findcachedir() + "sprites/misc/orbs/";
        ORBS[0] = new Sprite(OD + "ORBS 0.PNG", 27, getOrbFill(energy));
        if (!runClicked) {
            if (runHover) {
                ORBS[7].method348(190, 92);
            } else {
                ORBS[1].method348(190, 92);
            }

            ORBS[10].method348(193, 95);

            if (energy != 100) {
                ORBS[0].method348(193, 95);
            }

            ORBS[8].method348(199, 100);
        } else {
            if (runHover) {
                ORBS[7].method348(190, 92);
            } else {
                ORBS[1].method348(190, 92);
            }
            ORBS[11].method348(193, 95);
            if (energy != 100) {
                ORBS[0].method348(193, 95);
            }
            ORBS[9].method348(199, 100);
        }
        int Y = 117;

        smallFont.drawCenteredText(getOrbTextColor(energy), 234, "" + energy, Y, true);
    }

    public void launchURL(String s) {
        String s1 = System.getProperty("os.name");
        try {
            if (s1.startsWith("Windows")) {
                Runtime.getRuntime().exec((new StringBuilder()).append("rundll32 url.dll,FileProtocolHandler ").append(s).toString());
            } else {
                String as[] = {
                    "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape"
                };
                String s2 = null;
                for (int i = 0; i < as.length && s2 == null; i++) {
                    if (Runtime.getRuntime().exec(new String[]{"which", as[i]}).waitFor() == 0) {
                        s2 = as[i];
                    }
                }
                if (s2 == null) {
                    throw new Exception("Could not find web browser");
                }
                Runtime.getRuntime().exec(new String[]{s2, s});
            }
        } catch (Exception exception) {
            System.out.println("An error occured while trying to open the web browser!\n");
        }
    }

    public void saveinfo() {
        try {
            File f = new File(sign.signlink.findcachedir() + "user.ini");
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        if (saveUser) { //we save it
            try {
                Properties p = new Properties();
                p.load(new FileInputStream(sign.signlink.findcachedir() + "user.ini"));
                p.put("user", "" + myUsername + "");
                p.put("pass", "" + myPassword + "");
                FileOutputStream out = new FileOutputStream(sign.signlink.findcachedir() + "user.ini");
                p.store(out, "");
            } catch (Exception e) {
                System.out.println(e);
            }
        } else { //save blank
            try {
                Properties p = new Properties();
                p.load(new FileInputStream(sign.signlink.findcachedir() + "user.ini"));
                p.put("user", "");
                p.put("pass", "");
                FileOutputStream out = new FileOutputStream(sign.signlink.findcachedir() + "user.ini");
                p.store(out, "");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void loadinfo() {
        try {
            Properties p = new Properties();
            p.load(new FileInputStream(sign.signlink.findcachedir() + "user.ini"));
            myUsername = p.getProperty("user");
            myPassword = p.getProperty("pass");
            if (myUsername != "") {
                saveUser = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public final String methodR(int i, int j) {
        if (i <= 0) {
            pktType = aClass30_Sub2_Sub2_1083.readUnsignedByte();
        }
        if (j >= 0 && j < 10000) {
            return String.valueOf(j);
        }
        if (j >= 10000 && j < 10000000) {
            return j / 1000 + "K";
        }
        if (j >= 10000000 && j < 999999999) {
            return j / 1000000 + "M";
        }
        if (j >= 999999999) {
            return "*";
        } else {
            return "?";
        }
    }

    public void ResetChatOptions() {
        clickedTab[0] = true;
        for (int i = 1; i < clickedTab.length; i++) {
            clickedTab[i] = false;
        }
        for (int i1 = 0; i1 < flashRequest.length; i1++) {
            flashRequest[i1] = false;
        }
        for (int i2 = 0; i2 < newTextDrawn.length; i2++) {
            newTextDrawn[i2] = false;
        }
        for (int i3 = 0; i3 < 6; i3++) {
            pauseFlash[i3] = false;
            flashTimes[i3] = 4;
        }
    }

    public void processExtraMenus() { //Extra Right-Clisck Menus

        //Prayer Orb
        if (super.anInt20 >= 706 && super.anInt20 <= 762 && super.anInt21 >= 52 && super.anInt21 < 87) {
            if (!prayClicked) {
                menuActionName[1] = "Toggle Quick-Prayers On";
            } else if (prayClicked) {
                menuActionName[1] = "Toggle Quick-Prayers Off";
            }
            menuActionID[1] = 1500;
            menuActionRow = 2;
        }

        //Run Orb
        if (super.anInt20 >= 706 && super.anInt20 <= 762 && super.anInt21 >= 95 && super.anInt21 < 128) {
            if (!runClicked) {
                menuActionName[1] = "Toggle Run-Mode On";
            } else if (runClicked) {
                menuActionName[1] = "Toggle Run-Mode Off";
            }
            menuActionID[1] = 1550;
            menuActionRow = 2;
        }

        //Logout Button
        if (super.anInt20 >= 765 - 20 && super.anInt20 <= 765 - 1 && super.anInt21 >= 1 && super.anInt21 < 23) {
            menuActionName[1] = "Logout";
            menuActionID[1] = 1015;
            menuActionRow++;
        }

        //Help Button
        if (super.anInt20 >= 765 - 42 && super.anInt20 <= 765 - 22 && super.anInt21 >= 1 && super.anInt21 < 23) {
            menuActionName[1] = "Client Help";
            menuActionID[1] = 1050;
            menuActionRow++;
        }

        //QC Button
        if (super.anInt20 >= qcFromLeft && super.anInt20 <= qcFromLeft + 14 && super.anInt21 >= qcFromTop && super.anInt21 <= qcFromTop + 14) {
            menuActionName[1] = "Quick Chat";
            menuActionID[1] = 1050;
            menuActionRow++;
        }

        //Map Button
        if (super.anInt20 >= 522 && super.anInt20 <= 558 && super.anInt21 >= 124 && super.anInt21 < 161) {
            menuActionName[1] = "World Map";
            menuActionID[1] = 1020;
            menuActionRow++;
        }

        //Compass Button
        if (super.anInt20 >= 522 && super.anInt20 <= 558 && super.anInt21 >= 5 && super.anInt21 < 42) {
            menuActionName[1] = "Face North";
            menuActionID[1] = 1150;
            menuActionRow++;
        }

        //Zoom + Button
        if (super.anInt20 >= 529 && super.anInt20 <= 542 && super.anInt21 >= 60 && super.anInt21 <= 73) {
            menuActionName[2] = "Zoom In";
            menuActionID[2] = 1151;
            menuActionRow++;
            menuActionName[1] = "Default Zoom";
            menuActionID[1] = 1153;
            menuActionRow++;
        }

        //Zoom - Button
        if (super.anInt20 >= 527 && super.anInt20 <= 540 && super.anInt21 >= 76 && super.anInt21 <= 89) {
            menuActionName[2] = "Zoom Out";
            menuActionID[2] = 1152;
            menuActionRow++;
            menuActionName[1] = "Default Zoom";
            menuActionID[1] = 1153;
            menuActionRow++;
        }

        //Top Tabs
        if (super.anInt20 >= 524 && super.anInt20 <= 561 && super.anInt21 >= 168 && super.anInt21 < 204 && tabInterfaceIDs[0] != -1) {
            menuActionName[1] = "Combat Styles";
            menuActionID[1] = 1001;
            menuActionRow++;
        } else if (super.anInt20 >= 562 && super.anInt20 <= 594 && super.anInt21 >= 168 && super.anInt21 < 204 && tabInterfaceIDs[1] != -1) {
            menuActionName[1] = "Stats";
            menuActionID[1] = 1002;
            menuActionRow++;
        } else if (super.anInt20 >= 595 && super.anInt20 <= 627 && super.anInt21 >= 168 && super.anInt21 < 204 && tabInterfaceIDs[2] != -1) {
            menuActionName[1] = "Quest List";
            menuActionID[1] = 1003;
            menuActionRow++;
        } else if (super.anInt20 >= 628 && super.anInt20 <= 660 && super.anInt21 >= 168 && super.anInt21 < 204 && tabInterfaceIDs[3] != -1) {
            menuActionName[1] = "Inventory";
            menuActionID[1] = 1004;
            menuActionRow++;
        } else if (super.anInt20 >= 661 && super.anInt20 <= 693 && super.anInt21 >= 168 && super.anInt21 < 204 && tabInterfaceIDs[4] != -1) {
            menuActionName[1] = "Worn Equipment";
            menuActionID[1] = 1005;
            menuActionRow++;
        } else if (super.anInt20 >= 694 && super.anInt20 <= 726 && super.anInt21 >= 168 && super.anInt21 < 204 && tabInterfaceIDs[5] != -1) {
            menuActionName[1] = "Prayer List";
            menuActionID[1] = 1006;
            menuActionRow++;
        } else if (super.anInt20 >= 727 && super.anInt20 <= 763 && super.anInt21 >= 168 && super.anInt21 < 205 && tabInterfaceIDs[6] != -1) {
            menuActionName[1] = "Magic Spellbook";
            menuActionID[1] = 1007;
            menuActionRow++;
        }

        //Bottom tabs
        if (super.anInt20 >= 524 && super.anInt20 <= 561 && super.anInt21 >= 467 && super.anInt21 < 503 && tabInterfaceIDs[8] != -1) {
            menuActionName[1] = "Notes";
            menuActionID[1] = 1008;
            menuActionRow++;
        } else if (super.anInt20 >= 562 && super.anInt20 <= 594 && super.anInt21 >= 467 && super.anInt21 < 503 && tabInterfaceIDs[8] != -1) {
            menuActionName[1] = "Friend List";
            menuActionID[1] = 1009;
            menuActionRow++;
        } else if (super.anInt20 >= 595 && super.anInt20 <= 627 && super.anInt21 >= 467 && super.anInt21 < 503 && tabInterfaceIDs[9] != -1) {
            menuActionName[1] = "Ignore List";
            menuActionID[1] = 1010;
            menuActionRow++;
        } else if (super.anInt20 >= 628 && super.anInt20 <= 660 && super.anInt21 >= 467 && super.anInt21 < 503 && tabInterfaceIDs[10] != -1) {
            menuActionName[1] = "Clan Chat";
            menuActionID[1] = 1011;
            menuActionRow++;
        } else if (super.anInt20 >= 661 && super.anInt20 <= 693 && super.anInt21 >= 467 && super.anInt21 < 503 && tabInterfaceIDs[11] != -1) {
            menuActionName[1] = "Options";
            menuActionID[1] = 1012;
            menuActionRow++;
        } else if (super.anInt20 >= 694 && super.anInt20 <= 726 && super.anInt21 >= 467 && super.anInt21 < 503 && tabInterfaceIDs[12] != -1) {
            menuActionName[1] = "Emotes";
            menuActionID[1] = 1013;
            menuActionRow++;
        } else if (super.anInt20 >= 727 && super.anInt20 <= 763 && super.anInt21 >= 467 && super.anInt21 < 503 && tabInterfaceIDs[13] != -1) {
            menuActionName[1] = "Music Player";
            menuActionID[1] = 1014;
            menuActionRow++;
        }

        //Chat Options
        if (super.anInt20 >= 3 && super.anInt20 <= 59 && super.anInt21 >= 481 && super.anInt21 <= 503) {
            menuActionName[1] = "View All";
            menuActionID[1] = 1450;
            menuActionRow++;
        }
        if (super.anInt20 >= 61 && super.anInt20 <= 116 && super.anInt21 >= 481 && super.anInt21 <= 503) {
            menuActionName[3] = "View Game";
            menuActionID[3] = 1451;
            menuActionRow++;
            menuActionName[2] = "All Game";
            menuActionID[2] = 1490;
            menuActionRow++;
            menuActionName[1] = "Filter Game";
            menuActionID[1] = 1491;
            menuActionRow++;
        }
        if (super.anInt20 >= 118 && super.anInt20 <= 173 && super.anInt21 >= 481 && super.anInt21 <= 503) {
            menuActionName[5] = "View Public";
            menuActionID[5] = 1452;
            menuActionRow++;
            menuActionName[4] = "On Public";
            menuActionID[4] = 1453;
            menuActionRow++;
            menuActionName[3] = "Friends Public";
            menuActionID[3] = 1454;
            menuActionRow++;
            menuActionName[2] = "Off Public";
            menuActionID[2] = 1455;
            menuActionRow++;
            menuActionName[1] = "Hide Public";
            menuActionID[1] = 1456;
            menuActionRow++;
        }
        if (super.anInt20 >= 175 && super.anInt20 <= 230 && super.anInt21 >= 481 && super.anInt21 <= 503) {
            menuActionName[4] = "View Private";
            menuActionID[4] = 1457;
            menuActionRow++;
            menuActionName[3] = "On Private";
            menuActionID[3] = 1458;
            menuActionRow++;
            menuActionName[2] = "Friends Private";
            menuActionID[2] = 1459;
            menuActionRow++;
            menuActionName[1] = "Off Private";
            menuActionID[1] = 1460;
            menuActionRow++;
        }
        if (super.anInt20 >= 232 && super.anInt20 <= 287 && super.anInt21 >= 481 && super.anInt21 <= 503) {
            menuActionName[4] = "View Clan";
            menuActionID[4] = 1461;
            menuActionRow++;
            menuActionName[3] = "On Clan";
            menuActionID[3] = 1462;
            menuActionRow++;
            menuActionName[2] = "Friends Clan";
            menuActionID[2] = 1463;
            menuActionRow++;
            menuActionName[1] = "Off Clan";
            menuActionID[1] = 1464;
            menuActionRow++;
        }
        if (super.anInt20 >= 289 && super.anInt20 <= 344 && super.anInt21 >= 481 && super.anInt21 <= 503) {
            menuActionName[4] = "View Trade";
            menuActionID[4] = 1465;
            menuActionRow++;
            menuActionName[3] = "On Trade";
            menuActionID[3] = 1466;
            menuActionRow++;
            menuActionName[2] = "Friends Trade";
            menuActionID[2] = 1467;
            menuActionRow++;
            menuActionName[1] = "Off Trade";
            menuActionID[1] = 1468;
            menuActionRow++;
        }

        //Options Button
        if (super.anInt20 >= 346 && super.anInt20 <= 401 && super.anInt21 >= 481 && super.anInt21 <= 503) {
            menuActionName[5] = "Toggle Head HP";
            menuActionID[5] = 1469;
            menuActionRow++;
            menuActionName[4] = "Head HP On";
            menuActionID[4] = 1470;
            menuActionRow++;
            menuActionName[3] = "Head HP Off";
            menuActionID[3] = 1472;
            menuActionRow++;
            menuActionName[2] = "@lre@Next @whi@HP Style";
            menuActionID[2] = 1510;
            menuActionRow++;
            menuActionName[1] = "@lre@Prev @whi@HP Stlye";
            menuActionID[1] = 1511;
            menuActionRow++;
        }

        //Report Abuse Button
        if (super.anInt20 >= 403 && super.anInt20 <= 513 && super.anInt21 >= 481 && super.anInt21 <= 503) {
            menuActionName[1] = "Report Abuse";
            menuActionID[1] = 606;
            menuActionRow++;
        }
    }

    public int textDraw(int i) {
        if (i == 0 || i == 18) {
            return 0;
        }
        if (i == 1 || i == 2) {
            return 1;
        }
        if (i == 3 || i == 6 || i == 7) {
            return 2;
        }
        if (i == 16) {
            return 3;
        }
        if (i == 4) {
            return 4;
        }
        if (i == 36) {
            return 5;
        }
        return i;
    }

    public void noclip() {
        if (noclip) {
            for (int k1 = 0; k1 < 4; k1++) {
                for (int i2 = 1; i2 < 103; i2++) {
                    for (int k2 = 1; k2 < 103; k2++) {
                        aClass11Array1230[k1].anIntArrayArray294[i2][k2] = 0;
                    }
                }
            }
        }
    }

    //NON-CUSTOM VOIDS!!
    private static final String intToKOrMilLongName(int i) {
        String s = String.valueOf(i);
        for (int k = s.length() - 3; k > 0; k -= 3) {
            s = s.substring(0, k) + "," + s.substring(k);
        }
        if (s.length() > 8) {
            s = "@gre@" + s.substring(0, s.length() - 8) + " million @gre@(" + s + ")";
        } else if (s.length() > 4) {
            s = "@Whi@" + s.substring(0, s.length() - 4) + "K @whi@(" + s + ")";
        }
        return " " + s;
    }

    public final void stopMidi() {
        signlink.midifade = 0;
        signlink.midi = "stop";
    }

    public final boolean menuHasAddFriend(int j) {
        if (j < 0) {
            return false;
        }
        int k = menuActionID[j];
        if (k >= 2000) {
            k -= 2000;
        }
        return k == 337;
    }

    public final void drawChatArea() {
        aClass15_1166.method237(0);
        Texture.anIntArray1472 = anIntArray1180;
        aClass30_Sub2_Sub1_Sub1_1198.method348(0, 0);
        if (messagePromptRaised) {
            boldFont.drawText(0, aString1121, 56, 260);
            boldFont.drawText(128, aString1212 + "*", 75, 260);
        } else if (inputDialogState == 1) {
            boldFont.drawText(0, "Enter amount:", 56, 260);
            boldFont.drawText(128, amountOrNameInput + "*", 75, 260);
        } else if (inputDialogState == 2) {
            boldFont.drawText(0, "Enter name:", 56, 260);
            boldFont.drawText(128, amountOrNameInput + "*", 75, 260);
        } else if (aString844 != null) { //there is a messagebox open?
            boldFont.drawText(0, aString844, 56, 260);
            boldFont.drawText(128, "Click to continue", 75, 260);
        } else if (backDialogID != -1) { //backDialogID
            method105(0, 0, RSInterface.interfaceCache[backDialogID], 0);
        } else if (dialogID != -1) { //dialogID
            method105(0, 0, RSInterface.interfaceCache[dialogID], 0);
        } else { //NORMAL CHATBOX!!
            TextDrawingClass class30_sub2_sub1_sub4 = regularFont; //drawing area??
            int j = 0; //this increments after every line??
            DrawingArea.setDrawingArea(113, 0, 486, 0);

            for (int k = 0; k < 100; k++) { //max 100 lines looped
                if (chatMessages[k] != null) {
                    int l = chatTypes[k];
                    int i1 = (109 - j * 14) + scrollableChatArea;
                    String s1 = chatNames[k];
                    byte byte0 = 0;


                    String checkHL = chatMessages[k];
                    if (checkHL.toLowerCase().startsWith(hilightCueWord)) {
                        highlightText = true;
                        if (chatMessages[k].length() == hilightCueWord.length()) {
                            highlightText = false;

                        }
                    }


                    if (s1 != null && s1.startsWith("@cr1@")) {
                        s1 = s1.substring(5);
                        byte0 = 1;
                    }
                    if (s1 != null && s1.startsWith("@cr2@")) {
                        s1 = s1.substring(5);
                        byte0 = 2;
                    }

                    if (clickedTab[0] == true || clickedTab[1] == true) {
                        if (l == 0) {
                            if (i1 > 0 && i1 < 120) {
                                class30_sub2_sub1_sub4.drawShadowedText(false, 4, 0, chatMessages[k], i1);
                            }
                            j++;
                        }
                    } else {
                        if (newTextDrawn[0]) {
                            flashRequest[0] = true;
                        }
                    }

                    if (clickedTab[0] == true || clickedTab[2] == true) {
                        if ((l == 1 || l == 2) && (l == 1 || publicChatMode == 0 || publicChatMode == 1 && method109(false, s1))) {
                            if (i1 > 0 && i1 < 120) {
                                int j1 = 4;

                                if (highlightText) {
                                    theMessage = chatMessages[k].substring(hilightCueWord.length());
                                    DrawingArea.drawPixelsWithOpacity(0, i1 - 12, 506, 14, 0xcc0000, 80);
                                    highlightText = false;
                                } else {
                                    theMessage = chatMessages[k];
                                }


                                if (byte0 == 1) {
                                    modIcons[0].method348(j1, i1 - 12);
                                    j1 += 14;
                                }
                                if (byte0 == 2) {
                                    modIcons[1].method348(j1, i1 - 12);
                                    j1 += 14;
                                }
                                class30_sub2_sub1_sub4.method385(0, s1 + ":", i1, 822, j1);

                                j1 += class30_sub2_sub1_sub4.getTextWidth(s1) + 8;
                                class30_sub2_sub1_sub4.method385(255, theMessage, i1, 822, j1);
                            }
                            j++;
                        }
                    } else {
                        if (newTextDrawn[1]) {
                            flashRequest[1] = true;
                        }
                    }

                    if (clickedTab[0] == true || clickedTab[3] == true) {
                        if ((l == 3 || l == 7) && anInt1195 == 0 && (l == 7 || privateChatMode == 0 || privateChatMode == 1 && method109(false, s1))) {
                            if (i1 > 0 && i1 < 120) {
                                int k1 = 4;
                                class30_sub2_sub1_sub4.method385(0x800000, "From", i1, 822, k1);
                                k1 += class30_sub2_sub1_sub4.getTextWidth("From ");
                                if (byte0 == 1) {
                                    modIcons[0].method348(k1, i1 - 12);
                                    k1 += 14;
                                }
                                if (byte0 == 2) {
                                    modIcons[1].method348(k1, i1 - 12);
                                    k1 += 14;
                                }
                                class30_sub2_sub1_sub4.method385(0, s1 + ":", i1, 822, k1);
                                k1 += class30_sub2_sub1_sub4.getTextWidth(s1) + 8;
                                class30_sub2_sub1_sub4.method385(0x800000, chatMessages[k], i1, 822, k1);
                            }
                            j++;
                        }
                    } else {
                        if (newTextDrawn[2]) {
                            flashRequest[2] = true;
                        }
                    }

                    if (clickedTab[0] == true || clickedTab[5] == true) {
                        if (l == 4 && (tradeMode == 0 || tradeMode == 1 && method109(false, s1))) {
                            if (i1 > 0 && i1 < 120) {
                                class30_sub2_sub1_sub4.method385(0x800080, s1 + " " + chatMessages[k], i1, 822, 4);
                            }
                            j++;
                        }
                        if (l == 5 && anInt1195 == 0 && privateChatMode < 2) {
                            if (i1 > 0 && i1 < 120) {
                                class30_sub2_sub1_sub4.method385(0x800000, chatMessages[k], i1, 822, 4);
                            }
                            j++;
                        }
                    } else {
                        if (newTextDrawn[4]) {
                            flashRequest[4] = true;
                        }
                    }

                    if (clickedTab[0] == true || clickedTab[3] == true) {
                        if (l == 6 && anInt1195 == 0 && privateChatMode < 2) {
                            if (i1 > 0 && i1 < 120) {
                                class30_sub2_sub1_sub4.method385(0, "To " + s1 + ":", i1, 822, 4);
                                class30_sub2_sub1_sub4.method385(0x800000, chatMessages[k], i1, 822, 12 + class30_sub2_sub1_sub4.getTextWidth((new StringBuilder()).append("To ").append(s1).toString()));
                            }
                            j++;
                        }
                    } else {
                        if (newTextDrawn[2]) {
                            flashRequest[2] = true;
                        }
                    }

                    if (clickedTab[0] == true || clickedTab[6] == true) {
                        if (AssistButton == 0 || AssistButton == 1 && method109(false, s1)) {
                            if (l == 36) {
                                if (i1 > 0 && i1 < 120) {
                                    class30_sub2_sub1_sub4.method385(0x8A2BE2, s1 + " " + chatMessages[k], i1, 822, 4);
                                }
                                j++;
                            }
                        }
                    } else {
                        if (newTextDrawn[5]) {
                            flashRequest[5] = true;
                        }
                    }
                }
            }

            DrawingArea.defaultDrawingAreaSize();
            scrollPos = j * 14 + 7;
            if (scrollPos < 113) {
                scrollPos = 113;
            }
            drawScrollbar(112, scrollPos - scrollableChatArea - 112, 0, 488, scrollPos);
            String s;
            if (aClass30_Sub2_Sub4_Sub1_Sub2_1126 != null && aClass30_Sub2_Sub4_Sub1_Sub2_1126.name != null) //player and player name are not null..
            {
                s = aClass30_Sub2_Sub4_Sub1_Sub2_1126.name; //allready cleaned????
            } else {
                s = TextClass.fixName(myUsername); //clean it
            }/*
             * s = username
             * inputString = said text
             */
            int across; //define it
            int usernameW = class30_sub2_sub1_sub4.getTextWidth(s);

            if (anInt863 == 1) { //mod
                modIcons[0].method348(4, 113);
                across = 14;
            } else if (anInt863 >= 2) { //admin or more
                modIcons[1].method348(4, 113);
                across = 14;
            } else {
                across = 0;
            }

            class30_sub2_sub1_sub4.method385(0x000000, s, 125, 822, 4 + across); //username put it directly after the modicon (if any)
            quickChat.method348(usernameW + across + 5, 116); //qc icon put it after username
            //some vars for menu
            qcFromLeft = usernameW + across + 13;
            qcFromTop = 116 + 344;
            class30_sub2_sub1_sub4.method385(0x000000, ": ", 125, 822, usernameW + across + 5 + 14); //":" after qc icon
            class30_sub2_sub1_sub4.method385(0x0000ff, inputString, 125, 822, 18 + class30_sub2_sub1_sub4.getTextWidth(s + ": ") + across); //typer
            typer.method348(19 + class30_sub2_sub1_sub4.getTextWidth(s + ": " + inputString) + across, 114); //qc icon put it after username
            DrawingArea.method339(112, 0x807660, 505, 0); //print it?
        }
        if (menuOpen && menuScreenArea == 2) {
            drawMenu();
        }
        aClass15_1166.drawGraphics(344, 23680, super.aGraphics12, 8);
        aClass15_1165.method237(0);
        Texture.anIntArray1472 = anIntArray1182;
    }

    public final void init() {
        try {
            nodeID = 0;
            setHighMem(true);
            isMembers = true;
            signlink.startpriv(InetAddress.getLocalHost());
            setserver(Csettings.server, Csettings.port);
            initClientFrame(503, 765);
        } catch (Exception exception) {
            return;
        }
    }

    public final void startRunnable(Runnable runnable, int i) {
        if (i > 10) {
            i = 10;
        }
        if (signlink.mainapp != null) {
            signlink.startthread(runnable, i);
            return;
        } else {
            super.startRunnable(runnable, i);
            return;
        }
    }

    public final Socket openSocket(int i) throws IOException {
        return new Socket(InetAddress.getByName(server), i);
    }

    //process a click on a menu item
    public boolean processMenuClick() {
        if (activeInterfaceType != 0) {
            return false;
        }

        int j = super.anInt26;

        if (anInt1136 == 1 && super.saveClickX >= 516 && super.saveClickY >= 160 && super.saveClickX <= 765 && super.saveClickY <= 205) {
            j = 0;
        }

        if (menuOpen) {
            if (j != 1) {
                int k = super.anInt20;
                int j1 = super.anInt21;
                if (menuScreenArea == 0) {
                    k -= 4;
                    j1 -= 4;
                }
                if (menuScreenArea == 1) {
                    k -= 553;
                    j1 -= 205;
                }
                if (menuScreenArea == 2) {
                    k -= 7;
                    j1 -= 345;
                }

                //top tabs
                if (menuScreenArea == 3) {
                    k -= 553;
                    j1 -= 205;
                }
                //bottom tabs
                if (menuScreenArea == 4) {
                    k -= 553;
                    j1 -= 205;
                }
                if (menuScreenArea == 5) {
                    k -= 516;
                    j1 -= 0;
                }

                if (k < menuOffsetX - 28 || k > menuOffsetX + menuWidth + 28 || j1 < menuOffsetY - 28 || j1 > menuOffsetY + menuHeight + 28) {
                    menuOpen = false;
                    if (menuScreenArea == 1) {
                        needDrawTabArea = true;
                    }
                    if (menuScreenArea == 2) {
                        inputTaken = true;
                    }
                }
            }

            if (j == 1) {
                int l = menuOffsetX;
                int k1 = menuOffsetY;
                int i2 = menuWidth;
                int k2 = super.saveClickX;
                int l2 = super.saveClickY;
                if (menuScreenArea == 0) {
                    k2 -= 4;
                    l2 -= 4;
                }
                if (menuScreenArea == 1) {
                    k2 -= 553;
                    l2 -= 205;
                }
                if (menuScreenArea == 2) {
                    k2 -= 7;
                    l2 -= 345;
                }
                //top tabs
                if (menuScreenArea == 3) {
                    k2 -= 553;
                    l2 -= 205;
                }
                //bottom tabs
                if (menuScreenArea == 4) {
                    k2 -= 553;
                    l2 -= 205;
                }
                if (menuScreenArea == 5) {
                    k2 -= 516;
                    l2 -= 0;
                }
                int i3 = -1;
                for (int j3 = 0; j3 < menuActionRow; j3++) {
                    int k3 = k1 + 31 + (menuActionRow - 1 - j3) * 15;
                    if (k2 > l && k2 < l + i2 && l2 > k3 - 13 && l2 < k3 + 3) {
                        i3 = j3;
                    }
                }
                if (i3 != -1) {
                    doAction(i3);
                }
                menuOpen = false;
                if (menuScreenArea == 1) {
                    needDrawTabArea = true;
                }
                if (menuScreenArea == 2) {
                    inputTaken = true;
                }
            }
            return true;
        } else {
            if (j == 1 && menuActionRow > 0) {
                int i1 = menuActionID[menuActionRow - 1];
                if (i1 == 632 || i1 == 78 || i1 == 867 || i1 == 431 || i1 == 53 || i1 == 74 || i1 == 454 || i1 == 539 || i1 == 493 || i1 == 847 || i1 == 447 || i1 == 1125) {
                    int l1 = menuActionCmd2[menuActionRow - 1];
                    int j2 = menuActionCmd3[menuActionRow - 1];
                    RSInterface class9 = RSInterface.interfaceCache[j2];
                    if (class9.aBoolean259 || class9.aBoolean235) {
                        aBoolean1242 = false;
                        anInt989 = 0;
                        anInt1084 = j2;
                        anInt1085 = l1;
                        activeInterfaceType = 2;
                        anInt1087 = super.saveClickX;
                        anInt1088 = super.saveClickY;
                        if (RSInterface.interfaceCache[j2].parentID == openInterfaceID) {
                            activeInterfaceType = 1;
                        }
                        if (RSInterface.interfaceCache[j2].parentID == backDialogID) {
                            activeInterfaceType = 3;
                        }
                        return true;
                    }
                }
            }
            if (j == 1 && (anInt1253 == 1 || menuHasAddFriend(menuActionRow - 1)) && menuActionRow > 2) {
                j = 2;
            }
            if (j == 1 && menuActionRow > 0) {
                doAction(menuActionRow - 1);
            }
            if (j == 2 && menuActionRow > 0) {
                determineMenuSize();
            }
            return false;
        }
    }

    public final void method21(boolean flag, int i, byte abyte0[]) {
        signlink.midifade = flag ? 1 : 0;
        signlink.midisave(abyte0, abyte0.length);
        if (i != 0) {
            pktType = aClass30_Sub2_Sub2_1083.readUnsignedByte();
        }
    }

    public final void method22(boolean flag) {
        try {
            anInt985 = -1;
            aClass19_1056.RemoveAll();
            aClass19_1013.RemoveAll();
            Texture.method366();
            method23(false);
            aClass25_946.initToNull(619);
            System.gc();
            for (int i = 0; i < 4; i++) {
                aClass11Array1230[i].method210();
            }

            for (int l = 0; l < 4; l++) {
                for (int k1 = 0; k1 < 104; k1++) {
                    for (int j2 = 0; j2 < 104; j2++) {
                        byteGroundArray[l][k1][j2] = 0;
                    }
                }
            }

            ObjectManager class7 = new ObjectManager(byteGroundArray, -60, 104, 104, intGroundArray);
            int k2 = aByteArrayArray1183.length;
            aClass30_Sub2_Sub2_1192.createFrame(0);
            if (!aBoolean1159) {
                for (int i3 = 0; i3 < k2; i3++) {
                    int i4 = (mapArea[i3] >> 8) * 64 - baseX;
                    int k5 = (mapArea[i3] & 0xff) * 64 - baseY;
                    byte abyte0[] = aByteArrayArray1183[i3];
                    if (abyte0 != null) {
                        class7.method180(abyte0, k5, i4, (anInt1069 - 6) * 8, (anInt1070 - 6) * 8, (byte) 4, aClass11Array1230);
                    }
                }

                for (int j4 = 0; j4 < k2; j4++) {
                    int l5 = (mapArea[j4] >> 8) * 64 - baseX;
                    int k7 = (mapArea[j4] & 0xff) * 64 - baseY;
                    byte abyte2[] = aByteArrayArray1183[j4];
                    if (abyte2 == null && anInt1070 < 800) {
                        class7.method174(k7, 64, 64, l5);
                    }
                }

                anInt1097++;
                if (anInt1097 > 160) {
                    anInt1097 = 0;
                    aClass30_Sub2_Sub2_1192.createFrame(238);
                    aClass30_Sub2_Sub2_1192.writeWordBigEndian(96);
                }
                aClass30_Sub2_Sub2_1192.createFrame(0);
                for (int i6 = 0; i6 < k2; i6++) {
                    byte abyte1[] = aByteArrayArray1247[i6];
                    if (abyte1 != null) {
                        int l8 = (mapArea[i6] >> 8) * 64 - baseX;
                        int k9 = (mapArea[i6] & 0xff) * 64 - baseY;
                        class7.method190(l8, aClass11Array1230, k9, 7, aClass25_946, abyte1);
                    }
                }

            }
            if (aBoolean1159) {
                for (int j3 = 0; j3 < 4; j3++) {
                    for (int k4 = 0; k4 < 13; k4++) {
                        for (int j6 = 0; j6 < 13; j6++) {
                            int l7 = anIntArrayArrayArray1129[j3][k4][j6];
                            if (l7 != -1) {
                                int i9 = l7 >> 24 & 3;
                                int l9 = l7 >> 1 & 3;
                                int j10 = l7 >> 14 & 0x3ff;
                                int l10 = l7 >> 3 & 0x7ff;
                                int j11 = (j10 / 8 << 8) + l10 / 8;
                                for (int l11 = 0; l11 < mapArea.length; l11++) {
                                    if (mapArea[l11] != j11 || aByteArrayArray1183[l11] == null) {
                                        continue;
                                    }
                                    class7.method179(i9, l9, aClass11Array1230, 9, k4 * 8, (j10 & 7) * 8, aByteArrayArray1183[l11], (l10 & 7) * 8, j3, j6 * 8);
                                    break;
                                }

                            }
                        }

                    }

                }

                for (int l4 = 0; l4 < 13; l4++) {
                    for (int k6 = 0; k6 < 13; k6++) {
                        int i8 = anIntArrayArrayArray1129[0][l4][k6];
                        if (i8 == -1) {
                            class7.method174(k6 * 8, 8, 8, l4 * 8);
                        }
                    }

                }

                aClass30_Sub2_Sub2_1192.createFrame(0);
                for (int l6 = 0; l6 < 4; l6++) {
                    for (int j8 = 0; j8 < 13; j8++) {
                        for (int j9 = 0; j9 < 13; j9++) {
                            int i10 = anIntArrayArrayArray1129[l6][j8][j9];
                            if (i10 != -1) {
                                int k10 = i10 >> 24 & 3;
                                int i11 = i10 >> 1 & 3;
                                int k11 = i10 >> 14 & 0x3ff;
                                int i12 = i10 >> 3 & 0x7ff;
                                int j12 = (k11 / 8 << 8) + i12 / 8;
                                for (int k12 = 0; k12 < mapArea.length; k12++) {
                                    if (mapArea[k12] != j12 || aByteArrayArray1247[k12] == null) {
                                        continue;
                                    }
                                    class7.method183(aClass11Array1230, aClass25_946, k10, j8 * 8, (i12 & 7) * 8, true, l6, aByteArrayArray1247[k12], (k11 & 7) * 8, i11, j9 * 8);
                                    break;
                                }

                            }
                        }

                    }

                }

            }
            aClass30_Sub2_Sub2_1192.createFrame(0);
            class7.method171(aClass11Array1230, aClass25_946, 2);
            aClass15_1165.method237(0);
            aClass30_Sub2_Sub2_1192.createFrame(0);
            int k3 = ObjectManager.anInt145;
            if (k3 > plane) {
                k3 = plane;
            }
            if (k3 < plane - 1) {
                k3 = plane - 1;
            }
            if (lowMem) {
                aClass25_946.method275(ObjectManager.anInt145, -34686);
            } else {
                aClass25_946.method275(0, -34686);
            }
            for (int i5 = 0; i5 < 104; i5++) {
                for (int i7 = 0; i7 < 104; i7++) {
                    method25(i5, i7);
                }

            }

            anInt1051++;
            if (anInt1051 > 98) {
                anInt1051 = 0;
                aClass30_Sub2_Sub2_1192.createFrame(150);
            }
            method63(-919);
        } catch (Exception exception) {
        }
        ObjectDef.mruNodes1.unlinkAll();
        loggedIn &= flag;
        if (super.aFrame_Sub1_15 != null) {
            aClass30_Sub2_Sub2_1192.createFrame(210);
            aClass30_Sub2_Sub2_1192.writeDWord(0x3f008edd);
        }
        if (lowMem && signlink.cache_dat != null) {
            int j = aClass42_Sub1_1068.getVersionCount(79, 0);
            for (int i1 = 0; i1 < j; i1++) {
                int l1 = aClass42_Sub1_1068.getModelIndex(i1);
                if ((l1 & 0x79) == 0) {
                    Model.method461(116, i1);
                }
            }

        }
        System.gc();
        Texture.method367(20);
        aClass42_Sub1_1068.method566(0);
        int k = (anInt1069 - 6) / 8 - 1;
        int j1 = (anInt1069 + 6) / 8 + 1;
        int i2 = (anInt1070 - 6) / 8 - 1;
        int l2 = (anInt1070 + 6) / 8 + 1;
        if (aBoolean1141) {
            k = 49;
            j1 = 50;
            i2 = 49;
            l2 = 50;
        }
        for (int l3 = k; l3 <= j1; l3++) {
            for (int j5 = i2; j5 <= l2; j5++) {
                if (l3 == k || l3 == j1 || j5 == i2 || j5 == l2) {
                    int j7 = aClass42_Sub1_1068.method562(0, 0, j5, l3);
                    if (j7 != -1) {
                        aClass42_Sub1_1068.method560(j7, 3, false);
                    }
                    int k8 = aClass42_Sub1_1068.method562(1, 0, j5, l3);
                    if (k8 != -1) {
                        aClass42_Sub1_1068.method560(k8, 3, false);
                    }
                }
            }

        }

    }

    public final void method23(boolean flag) {
        ObjectDef.mruNodes1.unlinkAll();
        ObjectDef.mruNodes2.unlinkAll();
        EntityDef.mruNodes.unlinkAll();
        ItemDef.mruNodes2.unlinkAll();
        ItemDef.mruNodes1.unlinkAll();
        if (flag) {
            pktType = -1;
        }
        Player.mruNodes.unlinkAll();
        SpotAnim.aMRUNodes_415.unlinkAll();
    }

    public final void method24(boolean flag, int i) {
        int ai[] = aClass30_Sub2_Sub1_Sub1_1263.myPixels;
        int j = ai.length;
        for (int k = 0; k < j; k++) {
            ai[k] = 0;
        }

        for (int l = 1; l < 103; l++) {
            int i1 = 24628 + (103 - l) * 512 * 4;
            for (int k1 = 1; k1 < 103; k1++) {
                if ((byteGroundArray[i][k1][l] & 0x18) == 0) {
                    aClass25_946.method309(ai, i1, 512, i, k1, l);
                }
                if (i < 3 && (byteGroundArray[i + 1][k1][l] & 8) != 0) {
                    aClass25_946.method309(ai, i1, 512, i + 1, k1, l);
                }
                i1 += 4;
            }

        }

        int j1 = ((238 + (int) (Math.random() * 20D)) - 10 << 16) + ((238 + (int) (Math.random() * 20D)) - 10 << 8) + ((238 + (int) (Math.random() * 20D)) - 10);
        int l1 = (238 + (int) (Math.random() * 20D)) - 10 << 16;
        aClass30_Sub2_Sub1_Sub1_1263.createSpriteDrawingArea();
        for (int i2 = 1; i2 < 103; i2++) {
            for (int j2 = 1; j2 < 103; j2++) {
                if ((byteGroundArray[i][j2][i2] & 0x18) == 0) {
                    method50(i2, -960, j1, j2, l1, i);
                }
                if (i < 3 && (byteGroundArray[i + 1][j2][i2] & 8) != 0) {
                    method50(i2, -960, j1, j2, l1, i + 1);
                }
            }

        }

        aClass15_1165.method237(0);
        loggedIn &= flag;
        anInt1071 = 0;
        for (int k2 = 0; k2 < 104; k2++) {
            for (int l2 = 0; l2 < 104; l2++) {
                int i3 = aClass25_946.method303(plane, k2, l2);
                if (i3 != 0) {
                    i3 = i3 >> 14 & 0x7fff;
                    int j3 = ObjectDef.forID(i3).anInt746;
                    if (j3 >= 0) {
                        int k3 = k2;
                        int l3 = l2;
                        if (j3 != 22 && j3 != 29 && j3 != 34 && j3 != 36 && j3 != 46 && j3 != 47 && j3 != 48) {
                            byte byte0 = 104;
                            byte byte1 = 104;
                            int ai1[][] = aClass11Array1230[plane].anIntArrayArray294;
                            for (int i4 = 0; i4 < 10; i4++) {
                                int j4 = (int) (Math.random() * 4D);
                                if (j4 == 0 && k3 > 0 && k3 > k2 - 3 && (ai1[k3 - 1][l3] & 0x1280108) == 0) {
                                    k3--;
                                }
                                if (j4 == 1 && k3 < byte0 - 1 && k3 < k2 + 3 && (ai1[k3 + 1][l3] & 0x1280180) == 0) {
                                    k3++;
                                }
                                if (j4 == 2 && l3 > 0 && l3 > l2 - 3 && (ai1[k3][l3 - 1] & 0x1280102) == 0) {
                                    l3--;
                                }
                                if (j4 == 3 && l3 < byte1 - 1 && l3 < l2 + 3 && (ai1[k3][l3 + 1] & 0x1280120) == 0) {
                                    l3++;
                                }
                            }

                        }
                        aClass30_Sub2_Sub1_Sub1Array1140[anInt1071] = mapFunctions[j3];
                        anIntArray1072[anInt1071] = k3;
                        anIntArray1073[anInt1071] = l3;
                        anInt1071++;
                    }
                }
            }

        }

    }

    public final void method25(int i, int j) {
        NodeList class19 = groundArray[plane][i][j];
        if (class19 == null) {
            aClass25_946.method295(plane, i, j);
            return;
        }
        int k = 0xfa0a1f01;
        Object obj = null;
        for (Item class30_sub2_sub4_sub2 = (Item) class19.reverseGetFirst(); class30_sub2_sub4_sub2 != null; class30_sub2_sub4_sub2 = (Item) class19.reverseGetNext()) {
            ItemDef class8 = ItemDef.forID(class30_sub2_sub4_sub2.ID);
            int l = class8.value;
            if (class8.stackable) {
                l *= class30_sub2_sub4_sub2.anInt1559 + 1;
            }
            if (l > k) {
                k = l;
                obj = class30_sub2_sub4_sub2;
            }
        }

        class19.insertTail(((Node) (obj)));
        Object obj1 = null;
        Object obj2 = null;
        for (Item class30_sub2_sub4_sub2_1 = (Item) class19.reverseGetFirst(); class30_sub2_sub4_sub2_1 != null; class30_sub2_sub4_sub2_1 = (Item) class19.reverseGetNext()) {
            if (class30_sub2_sub4_sub2_1.ID != ((Item) (obj)).ID && obj1 == null) {
                obj1 = class30_sub2_sub4_sub2_1;
            }
            if (class30_sub2_sub4_sub2_1.ID != ((Item) (obj)).ID && class30_sub2_sub4_sub2_1.ID != ((Item) (obj1)).ID && obj2 == null) {
                obj2 = class30_sub2_sub4_sub2_1;
            }
        }

        int i1 = i + (j << 7) + 0x60000000;
        aClass25_946.method281((byte) 7, i, i1, ((Animable) (obj1)), method42(plane, j * 128 + 64, true, i * 128 + 64), ((Animable) (obj2)), ((Animable) (obj)), plane, j);
    }

    public final void method26(boolean flag) {
        for (int j = 0; j < npcCount; j++) {
            NPC class30_sub2_sub4_sub1_sub1 = npcArray[anIntArray837[j]];
            int k = 0x20000000 + (anIntArray837[j] << 14);
            if (class30_sub2_sub4_sub1_sub1 == null || !class30_sub2_sub4_sub1_sub1.isVisible() || class30_sub2_sub4_sub1_sub1.desc.aBoolean93 != flag) {
                continue;
            }
            int l = ((Entity) (class30_sub2_sub4_sub1_sub1)).x >> 7;
            int i1 = ((Entity) (class30_sub2_sub4_sub1_sub1)).y >> 7;
            if (l < 0 || l >= 104 || i1 < 0 || i1 >= 104) {
                continue;
            }
            if (((Entity) (class30_sub2_sub4_sub1_sub1)).anInt1540 == 1 && (((Entity) (class30_sub2_sub4_sub1_sub1)).x & 0x7f) == 64 && (((Entity) (class30_sub2_sub4_sub1_sub1)).y & 0x7f) == 64) {
                if (anIntArrayArray929[l][i1] == anInt1265) {
                    continue;
                }
                anIntArrayArray929[l][i1] = anInt1265;
            }
            if (!class30_sub2_sub4_sub1_sub1.desc.aBoolean84) {
                k += 0x80000000;
            }
            aClass25_946.method285(plane, ((Entity) (class30_sub2_sub4_sub1_sub1)).anInt1552, (byte) 6, method42(plane, ((Entity) (class30_sub2_sub4_sub1_sub1)).y, true, ((Entity) (class30_sub2_sub4_sub1_sub1)).x), k, ((Entity) (class30_sub2_sub4_sub1_sub1)).y, (((Entity) (class30_sub2_sub4_sub1_sub1)).anInt1540 - 1) * 64 + 60, ((Entity) (class30_sub2_sub4_sub1_sub1)).x, class30_sub2_sub4_sub1_sub1, ((Entity) (class30_sub2_sub4_sub1_sub1)).aBoolean1541);
        }
    }

    public boolean method27(int i) {
        if (i != 11456) {
            throw new NullPointerException();
        } else {
            for (int j = 1; j < 10; j++) {
                if (!signlink.waveplay[j]) {
                    return signlink.wavereplay(j);
                }
            }
            return false;
        }
    }

    private final void method28(String s) {
        System.out.println(s);
        try {
            getAppletContext().showDocument(new URL(getCodeBase(), "loaderror_" + s + ".html"));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        do {
            try {
                Thread.sleep(1000L);
            } catch (Exception _ex) {
            }
        } while (true);
    }

    public final void buildInterfaceMenu(int i, int j, RSInterface class9, int k, int l, int i1, int j1) { //buildInterfaceMenu
        if (class9.type != 0 || class9.children == null || class9.aBoolean266) {
            return;
        }
        if (k < i || i1 < l || k > i + class9.width || i1 > l + class9.height) {
            return;
        }
        int k1 = class9.children.length;
        if (j != 13037) {
            groundArray = null;
        }
        for (int l1 = 0; l1 < k1; l1++) {
            int i2 = class9.childX[l1] + i;
            int j2 = (class9.childY[l1] + l) - j1;
            RSInterface class9_1 = RSInterface.interfaceCache[class9.children[l1]];
            i2 += class9_1.anInt263;
            j2 += class9_1.anInt265;
            if ((class9_1.anInt230 >= 0 || class9_1.anInt216 != 0) && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height) {
                if (class9_1.anInt230 >= 0) {
                    hoverSpriteid = class9_1.anInt230;
                } else {
                    hoverSpriteid = class9_1.id;
                }
            }
            if (class9_1.type == 0) {
                buildInterfaceMenu(i2, 13037, class9_1, k, j2, i1, class9_1.scrollPosition);
                if (class9_1.scrollMax > class9_1.height) {
                    method65(i2 + class9_1.width, class9_1.height, k, i1, class9_1, j2, true, class9_1.scrollMax, 0);
                }
            } else {
                if (class9_1.atActionType == 1 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height) {
                    boolean flag = false;
                    if (class9_1.anInt214 != 0) {
                        flag = buildFriendsListMenu(class9_1);
                    }
                    if (!flag) {
                        menuActionName[menuActionRow] = class9_1.tooltip;
                        menuActionID[menuActionRow] = 315;
                        menuActionCmd3[menuActionRow] = class9_1.id;
                        menuActionRow++;
                    }
                }
                if (class9_1.atActionType == 2 && anInt1136 == 0 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height) {
                    String s = class9_1.selectedActionName;
                    if (s.indexOf(" ") != -1) {
                        s = s.substring(0, s.indexOf(" "));
                    }


                    menuActionName[menuActionRow] = "Autocast" + "@gre@ "
                            + class9_1.spellName;
                    menuActionID[menuActionRow] = 104;// autocast
                    menuActionCmd3[menuActionRow] = class9_1.id;
                    menuActionRow++;


                    menuActionName[menuActionRow] = s + " @gre@" + class9_1.spellName;
                    menuActionID[menuActionRow] = 626;
                    menuActionCmd3[menuActionRow] = class9_1.id;
                    menuActionRow++;
                }
                if (class9_1.atActionType == 3 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height) {
                    menuActionName[menuActionRow] = "Close";
                    menuActionID[menuActionRow] = 200;
                    menuActionCmd3[menuActionRow] = class9_1.id;
                    menuActionRow++;
                }
                if (class9_1.atActionType == 4 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height) {
                    menuActionName[menuActionRow] = class9_1.tooltip;
                    menuActionID[menuActionRow] = 169;
                    menuActionCmd3[menuActionRow] = class9_1.id;
                    menuActionRow++;
                }
                if (class9_1.atActionType == 5 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height) {
                    menuActionName[menuActionRow] = class9_1.tooltip;
                    menuActionID[menuActionRow] = 646;
                    menuActionCmd3[menuActionRow] = class9_1.id;
                    menuActionRow++;
                }
                if (class9_1.atActionType == 6 && !isInterfaceOpen && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height) {
                    menuActionName[menuActionRow] = class9_1.tooltip;
                    menuActionID[menuActionRow] = 679;
                    menuActionCmd3[menuActionRow] = class9_1.id;
                    menuActionRow++;
                }
                if (class9_1.type == 2) {
                    int k2 = 0;
                    for (int l2 = 0; l2 < class9_1.height; l2++) {
                        for (int i3 = 0; i3 < class9_1.width; i3++) {
                            int j3 = i2 + i3 * (32 + class9_1.invSpritePadX);
                            int k3 = j2 + l2 * (32 + class9_1.invSpritePadY);
                            if (k2 < 20) {
                                j3 += class9_1.spritesX[k2];
                                k3 += class9_1.spritesY[k2];
                            }
                            if (k >= j3 && i1 >= k3 && k < j3 + 32 && i1 < k3 + 32) {
                                mouseInvInterfaceIndex = k2;
                                lastActiveInvInterface = class9_1.id;
                                if (class9_1.inv[k2] > 0) {
                                    ItemDef class8 = ItemDef.forID(class9_1.inv[k2] - 1);
                                    if (anInt1282 == 1 && class9_1.isInventoryInterface) {
                                        if (class9_1.id != anInt1284 || k2 != anInt1283) {
                                            menuActionName[menuActionRow] = "Use " + aString1286 + " with @lre@" + class8.name;
                                            menuActionID[menuActionRow] = 870;
                                            menuActionCmd1[menuActionRow] = class8.id;
                                            menuActionCmd2[menuActionRow] = k2;
                                            menuActionCmd3[menuActionRow] = class9_1.id;
                                            menuActionRow++;
                                        }
                                    } else if (anInt1136 == 1 && class9_1.isInventoryInterface) {
                                        if ((anInt1138 & 0x10) == 16) {
                                            menuActionName[menuActionRow] = aString1139 + " @lre@" + class8.name;
                                            menuActionID[menuActionRow] = 543;
                                            menuActionCmd1[menuActionRow] = class8.id;
                                            menuActionCmd2[menuActionRow] = k2;
                                            menuActionCmd3[menuActionRow] = class9_1.id;
                                            menuActionRow++;
                                        }
                                    } else {
                                        if (class9_1.isInventoryInterface) {
                                            for (int l3 = 4; l3 >= 3; l3--) {
                                                if (class8.actions != null && class8.actions[l3] != null) {
                                                    menuActionName[menuActionRow] = class8.actions[l3] + " @lre@" + class8.name;
                                                    if (l3 == 3) {
                                                        menuActionID[menuActionRow] = 493;
                                                    }
                                                    if (l3 == 4) {
                                                        menuActionID[menuActionRow] = 847;
                                                    }
                                                    menuActionCmd1[menuActionRow] = class8.id;
                                                    menuActionCmd2[menuActionRow] = k2;
                                                    menuActionCmd3[menuActionRow] = class9_1.id;
                                                    menuActionRow++;
                                                } else if (l3 == 4) {
                                                    menuActionName[menuActionRow] = "Drop @lre@" + class8.name;
                                                    menuActionID[menuActionRow] = 847;
                                                    menuActionCmd1[menuActionRow] = class8.id;
                                                    menuActionCmd2[menuActionRow] = k2;
                                                    menuActionCmd3[menuActionRow] = class9_1.id;
                                                    menuActionRow++;
                                                }
                                            }

                                        }
                                        if (class9_1.usableItemInterface) {
                                            menuActionName[menuActionRow] = "Use @lre@" + class8.name;
                                            menuActionID[menuActionRow] = 447;
                                            menuActionCmd1[menuActionRow] = class8.id;
                                            menuActionCmd2[menuActionRow] = k2;
                                            menuActionCmd3[menuActionRow] = class9_1.id;
                                            menuActionRow++;
                                        }
                                        if (class9_1.isInventoryInterface && class8.actions != null) {
                                            for (int i4 = 2; i4 >= 0; i4--) {
                                                if (class8.actions[i4] != null) {
                                                    menuActionName[menuActionRow] = class8.actions[i4] + " @lre@" + class8.name;
                                                    if (i4 == 0) {
                                                        menuActionID[menuActionRow] = 74;
                                                    }
                                                    if (i4 == 1) {
                                                        menuActionID[menuActionRow] = 454;
                                                    }
                                                    if (i4 == 2) {
                                                        menuActionID[menuActionRow] = 539;
                                                    }
                                                    menuActionCmd1[menuActionRow] = class8.id;
                                                    menuActionCmd2[menuActionRow] = k2;
                                                    menuActionCmd3[menuActionRow] = class9_1.id;
                                                    menuActionRow++;
                                                }
                                            }

                                        }
                                        if (class9_1.actions != null) {
                                            for (int j4 = 4; j4 >= 0; j4--) {
                                                if (class9_1.actions[j4] != null) {
                                                    menuActionName[menuActionRow] = class9_1.actions[j4] + " @lre@" + class8.name;
                                                    if (j4 == 0) {
                                                        menuActionID[menuActionRow] = 632;
                                                    }
                                                    if (j4 == 1) {
                                                        menuActionID[menuActionRow] = 78;
                                                    }
                                                    if (j4 == 2) {
                                                        menuActionID[menuActionRow] = 867;
                                                    }
                                                    if (j4 == 3) {
                                                        menuActionID[menuActionRow] = 431;
                                                    }
                                                    if (j4 == 4) {
                                                        menuActionID[menuActionRow] = 53;
                                                    }
                                                    menuActionCmd1[menuActionRow] = class8.id;
                                                    menuActionCmd2[menuActionRow] = k2;
                                                    menuActionCmd3[menuActionRow] = class9_1.id;
                                                    menuActionRow++;
                                                }
                                            }

                                        }

                                        if (anInt863 >= 2) { //admin
                                            menuActionName[menuActionRow] = "Examine (ITE) @lre@" + class8.name + "@gre@(" + "@whi@" + class8.id + "@gre@)";
                                        } else {
                                            menuActionName[menuActionRow] = "Examine @lre@" + class8.name;
                                        }

                                        menuActionID[menuActionRow] = 1125;
                                        menuActionCmd1[menuActionRow] = class8.id;
                                        menuActionCmd2[menuActionRow] = k2;
                                        menuActionCmd3[menuActionRow] = class9_1.id;
                                        menuActionRow++;
                                    }
                                }
                            }
                            k2++;
                        }
                    }
                }
            }
        }
    }

    public final void drawScrollbar(int barHeight, int scrollPos, int yPos, int xPos, int contentHeight) {
        scrollBar[0].method348(xPos, yPos); //top arrow
        scrollBar[1].method348(xPos, (yPos + barHeight) - 16); //bottom arrow

        int scrollPartHeight = ((barHeight - 32) * barHeight) / contentHeight;
        if (scrollPartHeight < 10) {
            scrollPartHeight = 10;
        }
        int scrollPartPos = ((barHeight - 32 - scrollPartHeight) * scrollPos) / (contentHeight - barHeight);

        //background
        DrawingArea.method336(barHeight - 32, yPos + 16, xPos, 0x000001, 16);
        DrawingArea.method336(barHeight - 32, yPos + 16, xPos, 0x3d3426, 15);
        DrawingArea.method336(barHeight - 32, yPos + 16, xPos, 0x342d21, 13);
        DrawingArea.method336(barHeight - 32, yPos + 16, xPos, 0x2e281d, 11);
        DrawingArea.method336(barHeight - 32, yPos + 16, xPos, 0x29241b, 10);
        DrawingArea.method336(barHeight - 32, yPos + 16, xPos, 0x252019, 9);
        DrawingArea.method336(barHeight - 32, yPos + 16, xPos, 0x000001, 1);

        //middle of bar
        DrawingArea.method336(scrollPartHeight, yPos + 16 + scrollPartPos, xPos, anInt1063, 16);
        DrawingArea.method341(yPos + 16 + scrollPartPos, 0x000001, scrollPartHeight, xPos);
        DrawingArea.method341(yPos + 16 + scrollPartPos, 0x817051, scrollPartHeight, xPos + 1);
        DrawingArea.method341(yPos + 16 + scrollPartPos, 0x73654a, scrollPartHeight, xPos + 2);
        DrawingArea.method341(yPos + 16 + scrollPartPos, 0x6a5c43, scrollPartHeight, xPos + 3);
        DrawingArea.method341(yPos + 16 + scrollPartPos, 0x6a5c43, scrollPartHeight, xPos + 4);
        DrawingArea.method341(yPos + 16 + scrollPartPos, 0x655841, scrollPartHeight, xPos + 5);
        DrawingArea.method341(yPos + 16 + scrollPartPos, 0x655841, scrollPartHeight, xPos + 6);
        DrawingArea.method341(yPos + 16 + scrollPartPos, 0x61553e, scrollPartHeight, xPos + 7);
        DrawingArea.method341(yPos + 16 + scrollPartPos, 0x61553e, scrollPartHeight, xPos + 8);
        DrawingArea.method341(yPos + 16 + scrollPartPos, 0x5d513c, scrollPartHeight, xPos + 9);
        DrawingArea.method341(yPos + 16 + scrollPartPos, 0x5d513c, scrollPartHeight, xPos + 10);
        DrawingArea.method341(yPos + 16 + scrollPartPos, 0x594e3a, scrollPartHeight, xPos + 11);
        DrawingArea.method341(yPos + 16 + scrollPartPos, 0x594e3a, scrollPartHeight, xPos + 12);
        DrawingArea.method341(yPos + 16 + scrollPartPos, 0x514635, scrollPartHeight, xPos + 13);
        DrawingArea.method341(yPos + 16 + scrollPartPos, 0x4b4131, scrollPartHeight, xPos + 14);

        //top cornered bit
        DrawingArea.method339(yPos + 16 + scrollPartPos, 0x000001, 15, xPos);
        DrawingArea.method339(yPos + 17 + scrollPartPos, 0x000001, 15, xPos);
        DrawingArea.method339(yPos + 17 + scrollPartPos, 0x655841, 14, xPos);
        DrawingArea.method339(yPos + 17 + scrollPartPos, 0x6a5c43, 13, xPos);
        DrawingArea.method339(yPos + 17 + scrollPartPos, 0x6d5f48, 11, xPos);
        DrawingArea.method339(yPos + 17 + scrollPartPos, 0x73654a, 10, xPos);
        DrawingArea.method339(yPos + 17 + scrollPartPos, 0x76684b, 7, xPos);
        DrawingArea.method339(yPos + 17 + scrollPartPos, 0x7b6a4d, 5, xPos);
        DrawingArea.method339(yPos + 17 + scrollPartPos, 0x7e6e50, 4, xPos);
        DrawingArea.method339(yPos + 17 + scrollPartPos, 0x817051, 3, xPos);
        DrawingArea.method339(yPos + 17 + scrollPartPos, 0x000001, 2, xPos);
        DrawingArea.method339(yPos + 18 + scrollPartPos, 0x000001, 16, xPos);
        DrawingArea.method339(yPos + 18 + scrollPartPos, 0x564b38, 15, xPos);
        DrawingArea.method339(yPos + 18 + scrollPartPos, 0x5d513c, 14, xPos);
        DrawingArea.method339(yPos + 18 + scrollPartPos, 0x625640, 11, xPos);
        DrawingArea.method339(yPos + 18 + scrollPartPos, 0x655841, 10, xPos);
        DrawingArea.method339(yPos + 18 + scrollPartPos, 0x6a5c43, 7, xPos);
        DrawingArea.method339(yPos + 18 + scrollPartPos, 0x6e6046, 5, xPos);
        DrawingArea.method339(yPos + 18 + scrollPartPos, 0x716247, 4, xPos);
        DrawingArea.method339(yPos + 18 + scrollPartPos, 0x7b6a4d, 3, xPos);
        DrawingArea.method339(yPos + 18 + scrollPartPos, 0x817051, 2, xPos);
        DrawingArea.method339(yPos + 18 + scrollPartPos, 0x000001, 1, xPos);
        DrawingArea.method339(yPos + 19 + scrollPartPos, 0x000001, 16, xPos);
        DrawingArea.method339(yPos + 19 + scrollPartPos, 0x514635, 15, xPos);
        DrawingArea.method339(yPos + 19 + scrollPartPos, 0x564b38, 14, xPos);
        DrawingArea.method339(yPos + 19 + scrollPartPos, 0x5d513c, 11, xPos);
        DrawingArea.method339(yPos + 19 + scrollPartPos, 0x61553e, 9, xPos);
        DrawingArea.method339(yPos + 19 + scrollPartPos, 0x655841, 7, xPos);
        DrawingArea.method339(yPos + 19 + scrollPartPos, 0x6a5c43, 5, xPos);
        DrawingArea.method339(yPos + 19 + scrollPartPos, 0x6e6046, 4, xPos);
        DrawingArea.method339(yPos + 19 + scrollPartPos, 0x73654a, 3, xPos);
        DrawingArea.method339(yPos + 19 + scrollPartPos, 0x817051, 2, xPos);
        DrawingArea.method339(yPos + 19 + scrollPartPos, 0x000001, 1, xPos);
        DrawingArea.method339(yPos + 20 + scrollPartPos, 0x000001, 16, xPos);
        DrawingArea.method339(yPos + 20 + scrollPartPos, 0x4b4131, 15, xPos);
        DrawingArea.method339(yPos + 20 + scrollPartPos, 0x544936, 14, xPos);
        DrawingArea.method339(yPos + 20 + scrollPartPos, 0x594e3a, 13, xPos);
        DrawingArea.method339(yPos + 20 + scrollPartPos, 0x5d513c, 10, xPos);
        DrawingArea.method339(yPos + 20 + scrollPartPos, 0x61553e, 8, xPos);
        DrawingArea.method339(yPos + 20 + scrollPartPos, 0x655841, 6, xPos);
        DrawingArea.method339(yPos + 20 + scrollPartPos, 0x6a5c43, 4, xPos);
        DrawingArea.method339(yPos + 20 + scrollPartPos, 0x73654a, 3, xPos);
        DrawingArea.method339(yPos + 20 + scrollPartPos, 0x817051, 2, xPos);
        DrawingArea.method339(yPos + 20 + scrollPartPos, 0x000001, 1, xPos);

        //bottom cornered bit
        DrawingArea.method341(yPos + 16 + scrollPartPos, 0x000001, scrollPartHeight, xPos + 15);
        DrawingArea.method339(yPos + 15 + scrollPartPos + scrollPartHeight, 0x000001, 16, xPos);
        DrawingArea.method339(yPos + 14 + scrollPartPos + scrollPartHeight, 0x000001, 15, xPos);
        DrawingArea.method339(yPos + 14 + scrollPartPos + scrollPartHeight, 0x3f372a, 14, xPos);
        DrawingArea.method339(yPos + 14 + scrollPartPos + scrollPartHeight, 0x443c2d, 10, xPos);
        DrawingArea.method339(yPos + 14 + scrollPartPos + scrollPartHeight, 0x483e2f, 9, xPos);
        DrawingArea.method339(yPos + 14 + scrollPartPos + scrollPartHeight, 0x4a402f, 7, xPos);
        DrawingArea.method339(yPos + 14 + scrollPartPos + scrollPartHeight, 0x4b4131, 4, xPos);
        DrawingArea.method339(yPos + 14 + scrollPartPos + scrollPartHeight, 0x564b38, 3, xPos);
        DrawingArea.method339(yPos + 14 + scrollPartPos + scrollPartHeight, 0x000001, 2, xPos);
        DrawingArea.method339(yPos + 13 + scrollPartPos + scrollPartHeight, 0x000001, 16, xPos);
        DrawingArea.method339(yPos + 13 + scrollPartPos + scrollPartHeight, 0x443c2d, 15, xPos);
        DrawingArea.method339(yPos + 13 + scrollPartPos + scrollPartHeight, 0x4b4131, 11, xPos);
        DrawingArea.method339(yPos + 13 + scrollPartPos + scrollPartHeight, 0x514635, 9, xPos);
        DrawingArea.method339(yPos + 13 + scrollPartPos + scrollPartHeight, 0x544936, 7, xPos);
        DrawingArea.method339(yPos + 13 + scrollPartPos + scrollPartHeight, 0x564b38, 6, xPos);
        DrawingArea.method339(yPos + 13 + scrollPartPos + scrollPartHeight, 0x594e3a, 4, xPos);
        DrawingArea.method339(yPos + 13 + scrollPartPos + scrollPartHeight, 0x625640, 3, xPos);
        DrawingArea.method339(yPos + 13 + scrollPartPos + scrollPartHeight, 0x6a5c43, 2, xPos);
        DrawingArea.method339(yPos + 13 + scrollPartPos + scrollPartHeight, 0x000001, 1, xPos);
        DrawingArea.method339(yPos + 12 + scrollPartPos + scrollPartHeight, 0x000001, 16, xPos);
        DrawingArea.method339(yPos + 12 + scrollPartPos + scrollPartHeight, 0x443c2d, 15, xPos);
        DrawingArea.method339(yPos + 12 + scrollPartPos + scrollPartHeight, 0x4b4131, 14, xPos);
        DrawingArea.method339(yPos + 12 + scrollPartPos + scrollPartHeight, 0x544936, 12, xPos);
        DrawingArea.method339(yPos + 12 + scrollPartPos + scrollPartHeight, 0x564b38, 11, xPos);
        DrawingArea.method339(yPos + 12 + scrollPartPos + scrollPartHeight, 0x594e3a, 10, xPos);
        DrawingArea.method339(yPos + 12 + scrollPartPos + scrollPartHeight, 0x5d513c, 7, xPos);
        DrawingArea.method339(yPos + 12 + scrollPartPos + scrollPartHeight, 0x61553e, 4, xPos);
        DrawingArea.method339(yPos + 12 + scrollPartPos + scrollPartHeight, 0x6e6046, 3, xPos);
        DrawingArea.method339(yPos + 12 + scrollPartPos + scrollPartHeight, 0x7b6a4d, 2, xPos);
        DrawingArea.method339(yPos + 12 + scrollPartPos + scrollPartHeight, 0x000001, 1, xPos);
        DrawingArea.method339(yPos + 11 + scrollPartPos + scrollPartHeight, 0x000001, 16, xPos);
        DrawingArea.method339(yPos + 11 + scrollPartPos + scrollPartHeight, 0x4b4131, 15, xPos);
        DrawingArea.method339(yPos + 11 + scrollPartPos + scrollPartHeight, 0x514635, 14, xPos);
        DrawingArea.method339(yPos + 11 + scrollPartPos + scrollPartHeight, 0x564b38, 13, xPos);
        DrawingArea.method339(yPos + 11 + scrollPartPos + scrollPartHeight, 0x594e3a, 11, xPos);
        DrawingArea.method339(yPos + 11 + scrollPartPos + scrollPartHeight, 0x5d513c, 9, xPos);
        DrawingArea.method339(yPos + 11 + scrollPartPos + scrollPartHeight, 0x61553e, 7, xPos);
        DrawingArea.method339(yPos + 11 + scrollPartPos + scrollPartHeight, 0x655841, 5, xPos);
        DrawingArea.method339(yPos + 11 + scrollPartPos + scrollPartHeight, 0x6a5c43, 4, xPos);
        DrawingArea.method339(yPos + 11 + scrollPartPos + scrollPartHeight, 0x73654a, 3, xPos);
        DrawingArea.method339(yPos + 11 + scrollPartPos + scrollPartHeight, 0x7b6a4d, 2, xPos);
        DrawingArea.method339(yPos + 11 + scrollPartPos + scrollPartHeight, 0x000001, 1, xPos);
    }

    private final void updateNPCs(Stream stream, int i) {
        anInt839 = 0;
        anInt893 = 0;
        method139(stream);
        method46(i, stream);
        method86(stream);
        for (int k = 0; k < anInt839; k++) {
            int l = anIntArray840[k];
            if (((Entity) (npcArray[l])).anInt1537 != loopCycle) {
                npcArray[l].desc = null;
                npcArray[l] = null;
            }
        }
        if (stream.currentOffset != i) {
            signlink.reporterror(myUsername + " size mismatch in getnpcpos - pos:" + stream.currentOffset + " psize:" + i);
            throw new RuntimeException("eek");
        }
        for (int i1 = 0; i1 < npcCount; i1++) {
            if (npcArray[anIntArray837[i1]] == null) {
                signlink.reporterror(myUsername + " null entry in npc list - pos:" + i1 + " size:" + npcCount);
                throw new RuntimeException("eek");
            }
        }
    }

    public final void processChatModeClick() {
        if (super.anInt26 == 1) {
            anInt940++;
            if (anInt940 > 1386) {
                anInt940 = 0;
                aClass30_Sub2_Sub2_1192.createFrame(165);
                aClass30_Sub2_Sub2_1192.writeWordBigEndian(0);
                int j = aClass30_Sub2_Sub2_1192.currentOffset;
                aClass30_Sub2_Sub2_1192.writeWordBigEndian(139);
                aClass30_Sub2_Sub2_1192.writeWordBigEndian(150);
                aClass30_Sub2_Sub2_1192.writeWord(32131);
                aClass30_Sub2_Sub2_1192.writeWordBigEndian((int) (Math.random() * 256D));
                aClass30_Sub2_Sub2_1192.writeWord(3250);
                aClass30_Sub2_Sub2_1192.writeWordBigEndian(177);
                aClass30_Sub2_Sub2_1192.writeWord(24859);
                aClass30_Sub2_Sub2_1192.writeWordBigEndian(119);
                if ((int) (Math.random() * 2D) == 0) {
                    aClass30_Sub2_Sub2_1192.writeWord(47234);
                }
                if ((int) (Math.random() * 2D) == 0) {
                    aClass30_Sub2_Sub2_1192.writeWordBigEndian(21);
                }
                aClass30_Sub2_Sub2_1192.writeBytes(aClass30_Sub2_Sub2_1192.currentOffset - j);
            }
        }
    }

    public final void method33(int i) {
        int j = Varp.cache[i].anInt709;
        if (j == 0) {
            return;
        }
        int k = variousSettings[i];
        if (j == 1) {
            if (k == 1) {
                Texture.method372(0.90000000000000002D);
            }
            if (k == 2) {
                Texture.method372(0.80000000000000004D);
            }
            if (k == 3) {
                Texture.method372(0.69999999999999996D);
            }
            if (k == 4) {
                Texture.method372(0.59999999999999998D);
            }
            ItemDef.mruNodes1.unlinkAll();
            welcomeScreenRaised = true;
        }
        if (j == 3) {
            boolean flag1 = musicEnabled;
            if (k == 0) {
                adjustVolume(musicEnabled, 0);
                musicEnabled = true;
            }
            if (k == 1) {
                adjustVolume(musicEnabled, -400);
                musicEnabled = true;
            }
            if (k == 2) {
                adjustVolume(musicEnabled, -800);
                musicEnabled = true;
            }
            if (k == 3) {
                adjustVolume(musicEnabled, -1200);
                musicEnabled = true;
            }
            if (k == 4) {
                musicEnabled = false;
            }
            if (musicEnabled != flag1 && !lowMem) {
                if (musicEnabled) {
                    nextSong = currentSong;
                    songChanging = true;
                    aClass42_Sub1_1068.method558(2, nextSong);
                } else {
                    stopMidi();
                }
                prevSong = 0;
            }
        }
        if (j == 4) {
            if (k == 0) {
                aBoolean848 = true;
                method111((byte) 2, 0);
            }
            if (k == 1) {
                aBoolean848 = true;
                method111((byte) 2, -400);
            }
            if (k == 2) {
                aBoolean848 = true;
                method111((byte) 2, -800);
            }
            if (k == 3) {
                aBoolean848 = true;
                method111((byte) 2, -1200);
            }
            if (k == 4) {
                aBoolean848 = false;
            }
        }
        if (j == 5) {
            anInt1253 = k;
        }
        if (j == 6) {
            anInt1249 = k;
        }
        if (j == 8) {
            anInt1195 = k;
            inputTaken = true;
        }
        if (j == 9) {
            anInt913 = k;
        }
    }

    public final void method34(int i) { //updateEntities
        anInt974 = 0;
        for (int j = -1; j < anInt891 + npcCount; j++) {
            Object obj;
            if (j == -1) {
                obj = aClass30_Sub2_Sub4_Sub1_Sub2_1126;
            } else if (j < anInt891) {
                obj = playerArray[anIntArray892[j]];
            } else {
                obj = npcArray[anIntArray837[j - anInt891]];
            }
            if (obj == null || !((Entity) (obj)).isVisible()) {
                continue;
            }
            if (obj instanceof NPC) {
                EntityDef class5 = ((NPC) obj).desc;
                if (class5.childrenIDs != null) {
                    class5 = class5.method161(anInt877);
                }
                if (class5 == null) {
                    continue;
                }
            }
            if (j < anInt891) {
                int l = 30;
                Player class30_sub2_sub4_sub1_sub2 = (Player) obj;
                if (class30_sub2_sub4_sub1_sub2.headIcon != 0) {
                    method127(true, ((Entity) (obj)), ((Entity) (obj)).height + 15);
                    if (spriteDrawX > -1) {
                        for (int i2 = 0; i2 < 8; i2++) {
                            if ((class30_sub2_sub4_sub1_sub2.headIcon & 1 << i2) != 0) {
                                headIcons[i2].method348(spriteDrawX - 12, spriteDrawY - l);
                                l -= 25;
                            }
                        }
                    }
                }
                if (j >= 0 && anInt855 == 10 && anInt933 == anIntArray892[j]) {
                    method127(true, ((Entity) (obj)), ((Entity) (obj)).height + 15);
                    if (spriteDrawX > -1) {
                        headIcons[7].method348(spriteDrawX - 12, spriteDrawY - l);
                    }
                }

                if (Csettings.headNames) {
                    method127(true, ((Entity) (obj)), ((Entity) (obj)).height + 15);
                    int col = Csettings.headNamesColour;
                    smallFont.drawText(col, Player.name, spriteDrawY, spriteDrawX);
                }

            } else {
                EntityDef class5_1 = ((NPC) obj).desc;
                if (class5_1.anInt75 >= 0 && class5_1.anInt75 < headIcons.length) {
                    method127(true, ((Entity) (obj)), ((Entity) (obj)).height + 15);
                    if (spriteDrawX > -1) {
                        headIcons[class5_1.anInt75].method348(spriteDrawX - 12, spriteDrawY - 30);
                    }
                }
                if (anInt855 == 1 && anInt1222 == anIntArray837[j - anInt891] && loopCycle % 20 < 10) {
                    method127(true, ((Entity) (obj)), ((Entity) (obj)).height + 15);
                    if (spriteDrawX > -1) {
                        headIcons[2].method348(spriteDrawX - 12, spriteDrawY - 28);
                    }
                }
            }
            if (((Entity) (obj)).aString1506 != null && (j >= anInt891 || publicChatMode == 0 || publicChatMode == 3 || publicChatMode == 1 && method109(false, ((Player) obj).name))) {
                method127(true, ((Entity) (obj)), ((Entity) (obj)).height);
                if (spriteDrawX > -1 && anInt974 < anInt975) {
                    anIntArray979[anInt974] = boldFont.method384(((Entity) (obj)).aString1506) / 2;
                    anIntArray978[anInt974] = boldFont.anInt1497;
                    anIntArray976[anInt974] = spriteDrawX;
                    anIntArray977[anInt974] = spriteDrawY;
                    anIntArray980[anInt974] = ((Entity) (obj)).anInt1513;
                    anIntArray981[anInt974] = ((Entity) (obj)).anInt1531;
                    anIntArray982[anInt974] = ((Entity) (obj)).textCycle;
                    aStringArray983[anInt974++] = ((Entity) (obj)).aString1506;
                    if (anInt1249 == 0 && ((Entity) (obj)).anInt1531 >= 1 && ((Entity) (obj)).anInt1531 <= 3) {
                        anIntArray978[anInt974] += 10;
                        anIntArray977[anInt974] += 5;
                    }
                    if (anInt1249 == 0 && ((Entity) (obj)).anInt1531 == 4) {
                        anIntArray979[anInt974] = 60;
                    }
                    if (anInt1249 == 0 && ((Entity) (obj)).anInt1531 == 5) {
                        anIntArray978[anInt974] += 5;
                    }
                }
            }
            try { //added this, dunno if its right ??
                if (((Entity) (obj)).loopCycleStatus > loopCycle) { //if were fighting (and getting hit??)
                    if (spriteDrawX > -1) { //spriteDrawX
                        method127(true, ((Entity) (obj)), ((Entity) (obj)).height + 15); //npcScreenPos?

                        //HP ABOVE HEADS
                        if (hp == true) //hp above heads
                        {
                            smallFont.drawCenteredText(0xffffff, spriteDrawX, (new StringBuilder()).append(((Entity) (Entity) obj).currentHealth).append("/").append(((Entity) (Entity) obj).maxHealth).toString(), spriteDrawY - 9, true);
                        }

                        //HP BARS
                        if (HPBarID == 0 || HPBarID >= 7) { //old hpbar

                            int i1 = (((Entity) (obj)).currentHealth * 56) / ((Entity) (obj)).maxHealth;
                            if (i1 > 56) {
                                i1 = 56;
                            }

                            ///	height, updown leftright colour width 0??
                            DrawingArea.method336(5, spriteDrawY - 5, spriteDrawX - 28, 65280, i1); //green
                            DrawingArea.method336(5, spriteDrawY - 5, (spriteDrawX - 28) + i1, 0xff0000, 56 - i1); //red


                        } else { // id 1-6
                            int HpPercent = (int) ((((Entity) (obj)).currentHealth * 56) / ((Entity) (obj)).maxHealth);
                            if (HpPercent > 56) {
                                HpPercent = 56;
                            }


                            int toLoad = HPBarID - 1;


                            HPBarEmpty[toLoad].method348(spriteDrawX - 28, spriteDrawY - 5);
                            HPBarFull[toLoad] = new Sprite(sign.signlink.findcachedir() + "sprites/misc/health/full " + toLoad + ".png", HpPercent, 7);
                            HPBarFull[toLoad].method348(spriteDrawX - 28, spriteDrawY - 5);


                        }
                    }
                }

            } catch (Exception e) {
            }

            for (int j1 = 0; j1 < 4; j1++) {
                if (((Entity) (obj)).hitsLoopCycle[j1] > loopCycle) {
                    method127(true, ((Entity) (obj)), ((Entity) (obj)).height / 2);
                    if (spriteDrawX > -1) {
                        if (j1 == 1) {
                            spriteDrawY -= 20;
                        }
                        if (j1 == 2) {
                            spriteDrawX -= 15;
                            spriteDrawY -= 10;
                        }
                        if (j1 == 3) {
                            spriteDrawX += 15;
                            spriteDrawY -= 10;
                        }
                        hitMarks[((Entity) (obj)).hitMarkTypes[j1]].method348(spriteDrawX - 12, spriteDrawY - 12);
                        smallFont.drawText(0, String.valueOf(((Entity) (obj)).hitArray[j1]), spriteDrawY + 4, spriteDrawX);
                        smallFont.drawText(0xffffff, String.valueOf(((Entity) (obj)).hitArray[j1]), spriteDrawY + 3, spriteDrawX - 1);
                    }
                }
            }
        }

        if (i != 0) {
            startUp();
        }
        for (int k = 0; k < anInt974; k++) {
            int k1 = anIntArray976[k];
            int l1 = anIntArray977[k];
            int j2 = anIntArray979[k];
            int k2 = anIntArray978[k];
            boolean flag = true;
            while (flag) {
                flag = false;
                for (int l2 = 0; l2 < k; l2++) {
                    if (l1 + 2 > anIntArray977[l2] - anIntArray978[l2] && l1 - k2 < anIntArray977[l2] + 2 && k1 - j2 < anIntArray976[l2] + anIntArray979[l2] && k1 + j2 > anIntArray976[l2] - anIntArray979[l2] && anIntArray977[l2] - anIntArray978[l2] < l1) {
                        l1 = anIntArray977[l2] - anIntArray978[l2];
                        flag = true;
                    }
                }

            }
            spriteDrawX = anIntArray976[k];
            spriteDrawY = anIntArray977[k] = l1;
            String s = aStringArray983[k];
            if (anInt1249 == 0) {
                int i3 = 0xffff00;
                if (anIntArray980[k] < 6) {
                    i3 = anIntArray965[anIntArray980[k]];
                }
                if (anIntArray980[k] == 6) {
                    i3 = anInt1265 % 20 >= 10 ? 0xffff00 : 0xff0000;
                }
                if (anIntArray980[k] == 7) {
                    i3 = anInt1265 % 20 >= 10 ? 65535 : 255;
                }
                if (anIntArray980[k] == 8) {
                    i3 = anInt1265 % 20 >= 10 ? 0x80ff80 : 45056;
                }
                if (anIntArray980[k] == 9) {
                    int j3 = 150 - anIntArray982[k];
                    if (j3 < 50) {
                        i3 = 0xff0000 + 1280 * j3;
                    } else if (j3 < 100) {
                        i3 = 0xffff00 - 0x50000 * (j3 - 50);
                    } else if (j3 < 150) {
                        i3 = 65280 + 5 * (j3 - 100);
                    }
                }
                if (anIntArray980[k] == 10) {
                    int k3 = 150 - anIntArray982[k];
                    if (k3 < 50) {
                        i3 = 0xff0000 + 5 * k3;
                    } else if (k3 < 100) {
                        i3 = 0xff00ff - 0x50000 * (k3 - 50);
                    } else if (k3 < 150) {
                        i3 = (255 + 0x50000 * (k3 - 100)) - 5 * (k3 - 100);
                    }
                }
                if (anIntArray980[k] == 11) {
                    int l3 = 150 - anIntArray982[k];
                    if (l3 < 50) {
                        i3 = 0xffffff - 0x50005 * l3;
                    } else if (l3 < 100) {
                        i3 = 65280 + 0x50005 * (l3 - 50);
                    } else if (l3 < 150) {
                        i3 = 0xffffff - 0x50000 * (l3 - 100);
                    }
                }
                if (anIntArray981[k] == 0) {
                    boldFont.drawText(0, s, spriteDrawY + 1, spriteDrawX);
                    boldFont.drawText(i3, s, spriteDrawY, spriteDrawX);
                }
                if (anIntArray981[k] == 1) {
                    boldFont.method386(0, s, spriteDrawX, anInt1265, spriteDrawY + 1);
                    boldFont.method386(i3, s, spriteDrawX, anInt1265, spriteDrawY);
                }
                if (anIntArray981[k] == 2) {
                    boldFont.method387(spriteDrawX, s, anInt1265, spriteDrawY + 1, 0);
                    boldFont.method387(spriteDrawX, s, anInt1265, spriteDrawY, i3);
                }
                if (anIntArray981[k] == 3) {
                    boldFont.method388(150 - anIntArray982[k], s, anInt1265, spriteDrawY + 1, spriteDrawX, 0);
                    boldFont.method388(150 - anIntArray982[k], s, anInt1265, spriteDrawY, spriteDrawX, i3);
                }
                if (anIntArray981[k] == 4) {
                    int i4 = boldFont.method384(s);
                    int k4 = ((150 - anIntArray982[k]) * (i4 + 100)) / 150;
                    DrawingArea.setDrawingArea(334, spriteDrawX - 50, spriteDrawX + 50, 0);
                    boldFont.method385(0, s, spriteDrawY + 1, 822, (spriteDrawX + 50) - k4);
                    boldFont.method385(i3, s, spriteDrawY, 822, (spriteDrawX + 50) - k4);
                    DrawingArea.defaultDrawingAreaSize();
                }
                if (anIntArray981[k] == 5) {
                    int j4 = 150 - anIntArray982[k];
                    int l4 = 0;
                    if (j4 < 25) {
                        l4 = j4 - 25;
                    } else if (j4 > 125) {
                        l4 = j4 - 125;
                    }
                    DrawingArea.setDrawingArea(spriteDrawY + 5, 0, 512, spriteDrawY - boldFont.anInt1497 - 1);
                    boldFont.drawText(0, s, spriteDrawY + 1 + l4, spriteDrawX);
                    boldFont.drawText(i3, s, spriteDrawY + l4, spriteDrawX);
                    DrawingArea.defaultDrawingAreaSize();
                }
            } else {
                boldFont.drawText(0, s, spriteDrawY + 1, spriteDrawX);
                boldFont.drawText(0xffff00, s, spriteDrawY, spriteDrawX);
            }
        }

    }

    public final void delFriend(long l) { //delFriend
        try {
            if (l == 0L) {
                return;
            }
            for (int i = 0; i < friendsCount; i++) {
                if (friendsListAsLongs[i] != l) {
                    continue;
                }
                friendsCount--;
                needDrawTabArea = true;
                for (int j = i; j < friendsCount; j++) {
                    friendsList[j] = friendsList[j + 1];
                    friendNodeIDs[j] = friendNodeIDs[j + 1];
                    friendsListAsLongs[j] = friendsListAsLongs[j + 1];
                }
                aClass30_Sub2_Sub2_1192.createFrame(215);
                aClass30_Sub2_Sub2_1192.writeQWord(l);
                break;
            }
        } catch (RuntimeException runtimeexception) {
            signlink.reporterror("18622, " + false + ", " + l + ", " + runtimeexception.toString());
            throw new RuntimeException();
        }
    }

    public final void drawTabArea() { //drawTabArea
        aClass15_1163.method237(0);
        Texture.anIntArray1472 = anIntArray1181;
        aClass30_Sub2_Sub1_Sub1_1196.method348(0, 0);
        if (invOverlayInterfaceID != -1) //invOverlayInterfaceID
        {
            method105(0, 0, RSInterface.interfaceCache[invOverlayInterfaceID], 0);
        } else if (tabInterfaceIDs[tabID] != -1) {
            method105(0, 0, RSInterface.interfaceCache[tabInterfaceIDs[tabID]], 0); //drawInterface
        }
        if (menuOpen) {
            drawMenu();
        }




        aClass15_1163.drawGraphics(205, 23680, super.aGraphics12, 551);
        aClass15_1165.method237(0);
        Texture.anIntArray1472 = anIntArray1182;
    }

    public final void method37(int i, int j) {
        if (i <= 0) {
            pktType = -1;
        }
        if (!lowMem) {
            if (Texture.anIntArray1480[17] >= j) {
                Background class30_sub2_sub1_sub2 = Texture.aClass30_Sub2_Sub1_Sub2Array1474[17];
                int k = class30_sub2_sub1_sub2.myWidth * class30_sub2_sub1_sub2.myHeight - 1;
                int j1 = class30_sub2_sub1_sub2.myWidth * anInt945 * 2;
                byte abyte0[] = class30_sub2_sub1_sub2.pixelInfo;
                byte abyte3[] = aByteArray912;
                for (int i2 = 0; i2 <= k; i2++) {
                    abyte3[i2] = abyte0[i2 - j1 & k];
                }

                class30_sub2_sub1_sub2.pixelInfo = abyte3;
                aByteArray912 = abyte0;
                Texture.method370(17, -477);
                anInt854++;
                if (anInt854 > 1235) {
                    anInt854 = 0;
                    aClass30_Sub2_Sub2_1192.createFrame(226);
                    aClass30_Sub2_Sub2_1192.writeWordBigEndian(0);
                    int l2 = aClass30_Sub2_Sub2_1192.currentOffset;
                    aClass30_Sub2_Sub2_1192.writeWord(58722);
                    aClass30_Sub2_Sub2_1192.writeWordBigEndian(240);
                    aClass30_Sub2_Sub2_1192.writeWord((int) (Math.random() * 65536D));
                    aClass30_Sub2_Sub2_1192.writeWordBigEndian((int) (Math.random() * 256D));
                    if ((int) (Math.random() * 2D) == 0) {
                        aClass30_Sub2_Sub2_1192.writeWord(51825);
                    }
                    aClass30_Sub2_Sub2_1192.writeWordBigEndian((int) (Math.random() * 256D));
                    aClass30_Sub2_Sub2_1192.writeWord((int) (Math.random() * 65536D));
                    aClass30_Sub2_Sub2_1192.writeWord(7130);
                    aClass30_Sub2_Sub2_1192.writeWord((int) (Math.random() * 65536D));
                    aClass30_Sub2_Sub2_1192.writeWord(61657);
                    aClass30_Sub2_Sub2_1192.writeBytes(aClass30_Sub2_Sub2_1192.currentOffset - l2);
                }
            }
            if (Texture.anIntArray1480[24] >= j) {
                Background class30_sub2_sub1_sub2_1 = Texture.aClass30_Sub2_Sub1_Sub2Array1474[24];
                int l = class30_sub2_sub1_sub2_1.myWidth * class30_sub2_sub1_sub2_1.myHeight - 1;
                int k1 = class30_sub2_sub1_sub2_1.myWidth * anInt945 * 2;
                byte abyte1[] = class30_sub2_sub1_sub2_1.pixelInfo;
                byte abyte4[] = aByteArray912;
                for (int j2 = 0; j2 <= l; j2++) {
                    abyte4[j2] = abyte1[j2 - k1 & l];
                }

                class30_sub2_sub1_sub2_1.pixelInfo = abyte4;
                aByteArray912 = abyte1;
                Texture.method370(24, -477);
            }
            if (Texture.anIntArray1480[40] >= j) {
                Background class30_sub2_sub1_sub2_1 = Texture.aClass30_Sub2_Sub1_Sub2Array1474[40];
                int l = class30_sub2_sub1_sub2_1.myWidth * class30_sub2_sub1_sub2_1.myHeight - 1;
                int k1 = (int) (class30_sub2_sub1_sub2_1.myWidth * anInt945 * 4);
                byte abyte1[] = class30_sub2_sub1_sub2_1.pixelInfo;
                byte abyte4[] = aByteArray912;
                for (int j2 = 0; j2 <= l; j2++) {
                    abyte4[j2] = abyte1[j2 - k1 & l];
                }

                class30_sub2_sub1_sub2_1.pixelInfo = abyte4;
                aByteArray912 = abyte1;
                Texture.method370(40, -477);
            }
            if (Texture.anIntArray1480[34] >= j) {
                Background class30_sub2_sub1_sub2_2 = Texture.aClass30_Sub2_Sub1_Sub2Array1474[34];
                int i1 = class30_sub2_sub1_sub2_2.myWidth * class30_sub2_sub1_sub2_2.myHeight - 1;
                int l1 = class30_sub2_sub1_sub2_2.myWidth * anInt945 * 2;
                byte abyte2[] = class30_sub2_sub1_sub2_2.pixelInfo;
                byte abyte5[] = aByteArray912;
                for (int k2 = 0; k2 <= i1; k2++) {
                    abyte5[k2] = abyte2[k2 - l1 & i1];
                }

                class30_sub2_sub1_sub2_2.pixelInfo = abyte5;
                aByteArray912 = abyte2;
                Texture.method370(34, -477);
            }
        }
    }

    public final void method38(byte byte0) {
        if (byte0 != -92) {
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(214);
        }
        for (int i = -1; i < anInt891; i++) {
            int j;
            if (i == -1) {
                j = anInt889;
            } else {
                j = anIntArray892[i];
            }
            Player class30_sub2_sub4_sub1_sub2 = playerArray[j];
            if (class30_sub2_sub4_sub1_sub2 != null && ((Entity) (class30_sub2_sub4_sub1_sub2)).textCycle > 0) {
                class30_sub2_sub4_sub1_sub2.textCycle--;
                if (((Entity) (class30_sub2_sub4_sub1_sub2)).textCycle == 0) {
                    class30_sub2_sub4_sub1_sub2.aString1506 = null;
                }
            }
        }

        for (int k = 0; k < npcCount; k++) {
            int l = anIntArray837[k];
            NPC class30_sub2_sub4_sub1_sub1 = npcArray[l];
            if (class30_sub2_sub4_sub1_sub1 != null && ((Entity) (class30_sub2_sub4_sub1_sub1)).textCycle > 0) {
                class30_sub2_sub4_sub1_sub1.textCycle--;
                if (((Entity) (class30_sub2_sub4_sub1_sub1)).textCycle == 0) {
                    class30_sub2_sub4_sub1_sub1.aString1506 = null;
                }
            }
        }

    }

    public final void calcCameraPos() {
        int i = anInt1098 * 128 + 64;
        int j = anInt1099 * 128 + 64;
        int k = method42(plane, j, true, i) - anInt1100;
        if (xCameraPos < i) {
            xCameraPos += anInt1101 + ((i - xCameraPos) * anInt1102) / 1000;
            if (xCameraPos > i) {
                xCameraPos = i;
            }
        }
        if (xCameraPos > i) {
            xCameraPos -= anInt1101 + ((xCameraPos - i) * anInt1102) / 1000;
            if (xCameraPos < i) {
                xCameraPos = i;
            }
        }
        if (zCameraPos < k) {
            zCameraPos += anInt1101 + ((k - zCameraPos) * anInt1102) / 1000;
            if (zCameraPos > k) {
                zCameraPos = k;
            }
        }
        if (zCameraPos > k) {
            zCameraPos -= anInt1101 + ((zCameraPos - k) * anInt1102) / 1000;
            if (zCameraPos < k) {
                zCameraPos = k;
            }
        }
        if (yCameraPos < j) {
            yCameraPos += anInt1101 + ((j - yCameraPos) * anInt1102) / 1000;
            if (yCameraPos > j) {
                yCameraPos = j;
            }
        }
        if (yCameraPos > j) {
            yCameraPos -= anInt1101 + ((yCameraPos - j) * anInt1102) / 1000;
            if (yCameraPos < j) {
                yCameraPos = j;
            }
        }
        i = anInt995 * 128 + 64;
        j = anInt996 * 128 + 64;
        k = method42(plane, j, true, i) - anInt997;
        int l = i - xCameraPos;
        int i1 = k - zCameraPos;
        int j1 = j - yCameraPos;
        int k1 = (int) Math.sqrt(l * l + j1 * j1);
        int l1 = (int) (Math.atan2(i1, k1) * 325.94900000000001D) & 0x7ff;
        int i2 = (int) (Math.atan2(l, j1) * -325.94900000000001D) & 0x7ff;
        if (l1 < 128) {
            l1 = 128;
        }
        if (l1 > 383) {
            l1 = 383;
        }
        if (yCameraCurve < l1) {
            yCameraCurve += anInt998 + ((l1 - yCameraCurve) * anInt999) / 1000;
            if (yCameraCurve > l1) {
                yCameraCurve = l1;
            }
        }
        if (yCameraCurve > l1) {
            yCameraCurve -= anInt998 + ((yCameraCurve - l1) * anInt999) / 1000;
            if (yCameraCurve < l1) {
                yCameraCurve = l1;
            }
        }
        int j2 = i2 - xCameraCurve;
        if (j2 > 1024) {
            j2 -= 2048;
        }
        if (j2 < -1024) {
            j2 += 2048;
        }
        if (j2 > 0) {
            xCameraCurve += anInt998 + (j2 * anInt999) / 1000;
            xCameraCurve &= 0x7ff;
        }
        if (j2 < 0) {
            xCameraCurve -= anInt998 + (-j2 * anInt999) / 1000;
            xCameraCurve &= 0x7ff;
        }
        int k2 = i2 - xCameraCurve;
        if (k2 > 1024) {
            k2 -= 2048;
        }
        if (k2 < -1024) {
            k2 += 2048;
        }
        if (k2 < 0 && j2 > 0 || k2 > 0 && j2 < 0) {
            xCameraCurve = i2;
        }
    }

    public final void drawMenu() {
        int yPos = menuOffsetY;
        int xPos = menuOffsetX;
        DrawingArea.method336(1, yPos, xPos + 2, 0x6d6a5b, menuWidth - 4);
        DrawingArea.method336(1, yPos + 1, xPos + 1, 0x6d6a5b, menuWidth - 2);
        DrawingArea.method336(menuHeight - 2, yPos + 2, xPos, 0x6d6a5b, menuWidth);
        DrawingArea.method336(menuHeight - 4, yPos + 3, xPos + 1, 0x2b2622, menuWidth - 2);
        DrawingArea.method336(1, yPos + 1, xPos + 3, 0x322e22, menuWidth - 6);
        DrawingArea.method336(1, yPos + 2, xPos + 3, 0x2a291b, menuWidth - 6);
        DrawingArea.method336(1, yPos + 3, xPos + 3, 0x2a261b, menuWidth - 6);
        DrawingArea.method336(1, yPos + 4, xPos + 3, 0x252116, menuWidth - 6);
        DrawingArea.method336(1, yPos + 5, xPos + 2, 0x211e15, menuWidth - 4);
        DrawingArea.method336(1, yPos + 6, xPos + 2, 0x1e1b12, menuWidth - 4);
        DrawingArea.method336(1, yPos + 7, xPos + 2, 0x1a170e, menuWidth - 4);
        DrawingArea.method336(1, yPos + 8, xPos + 2, 0x15120b, menuWidth - 4);
        DrawingArea.method336(1, yPos + 9, xPos + 2, 0x15120b, menuWidth - 4);
        DrawingArea.method336(1, yPos + 10, xPos + 2, 0x100d08, menuWidth - 4);
        DrawingArea.method336(1, yPos + 11, xPos + 2, 0x090a04, menuWidth - 4);
        DrawingArea.method336(1, yPos + 12, xPos + 2, 0x080703, menuWidth - 4);
        DrawingArea.method336(1, yPos + 13, xPos + 2, 0x090a04, menuWidth - 4);
        DrawingArea.method336(1, yPos + 14, xPos + 2, 0x070802, menuWidth - 4);
        DrawingArea.method336(1, yPos + 15, xPos + 2, 0x090a04, menuWidth - 4);
        DrawingArea.method336(1, yPos + 16, xPos + 2, 0x070802, menuWidth - 4);
        DrawingArea.method336(1, yPos + 17, xPos + 2, 0x090a04, menuWidth - 4);
        DrawingArea.method336(1, yPos + 1, xPos + 2, 0x2b271c, 1);
        DrawingArea.method336(1, yPos + 2, xPos + 1, 0x2b271c, 2);
        DrawingArea.method336(1, yPos + 1, xPos + menuWidth - 3, 0x2b271c, 1);
        DrawingArea.method336(1, yPos + 2, xPos + menuWidth - 3, 0x2b271c, 2);
        DrawingArea.method336(menuHeight - 21, yPos + 19, xPos + 2, 0x524a3d, menuWidth - 4);
        DrawingArea.method336(menuHeight - 23, yPos + 20, xPos + 3, 0x2b271c, menuWidth - 6);
        DrawingArea.method336(1, yPos + menuHeight - 1, xPos, 0x2d2822, 1);
        DrawingArea.method336(1, yPos + menuHeight - 2, xPos, 0x524a3d, 1);
        DrawingArea.method336(1, yPos + menuHeight - 1, xPos + 1, 0x524a3d, 1);
        DrawingArea.method336(1, yPos + menuHeight - 3, xPos + 1, 0x6e675f, 1);
        DrawingArea.method336(1, yPos + menuHeight - 2, xPos + 1, 0x6e675f, 2);
        DrawingArea.method336(1, yPos + menuHeight - 3, xPos + 2, 0x2e2a1f, 1);
        DrawingArea.method336(1, yPos + menuHeight - 1, xPos + menuWidth - 1, 0x2d2822, 1);
        DrawingArea.method336(1, yPos + menuHeight - 2, xPos + menuWidth - 1, 0x524a3d, 1);
        DrawingArea.method336(1, yPos + menuHeight - 1, xPos + menuWidth - 2, 0x524a3d, 1);
        DrawingArea.method336(1, yPos + menuHeight - 3, xPos + menuWidth - 2, 0x6e675f, 1);
        DrawingArea.method336(1, yPos + menuHeight - 2, xPos + menuWidth - 3, 0x6e675f, 2);
        DrawingArea.method336(1, yPos + menuHeight - 3, xPos + menuWidth - 3, 0x2e2a1f, 1);

        int mouseX = super.anInt20;
        int mouseY = super.anInt21;

        if (menuScreenArea == 0) {
            mouseX -= 4;
            mouseY -= 4;
        } else if (menuScreenArea == 1) {
            mouseX -= 553;
            mouseY -= 205;
        } else if (menuScreenArea == 2) {
            mouseX -= 7;
            mouseY -= 345;
        }

        //top tabs
        if (menuScreenArea == 3) {
            mouseX -= 524;
            mouseY -= 168;
        }
        //bottom tabs
        if (menuScreenArea == 4) {
            mouseX -= 524;
            mouseY -= 467;
        }

        if (menuScreenArea == 5) {
            mouseX -= 516;
            mouseY -= 0;
        }

        boldFont.drawShadowedText(true, xPos + 3, 0xc6b895, "Choose Option", yPos + 14);
        for (int l1 = 0; l1 < menuActionRow; l1++) {
            int textY = yPos + 31 + (menuActionRow - 1 - l1) * 15;
            if (menuActionName[l1] == null) {
                return;
            }
            int textColor = 0xc6b895;
            if (mouseX > xPos && mouseX < xPos + menuWidth && mouseY > textY - 11 && mouseY < textY + 5) {
                if (menuActionName[l1].equalsIgnoreCase("Cancel")) {
                    DrawingArea.method336(12, textY - 9, xPos + 3, 0x6f695d, menuWidth - 6);
                } else {
                    DrawingArea.method336(16, textY - 10, xPos + 3, 0x6f695d, menuWidth - 6);
                }
                textColor = 0xeee5c6;
            }
            boldFont.drawShadowedText(true, xPos + 3, textColor, menuActionName[l1], textY + 2);
        }
    }

    public final void method41(byte byte0, long l) {
        try {
            if (l == 0L) {
                return;
            }
            if (friendsCount >= 100 && anInt1046 != 1) {
                pushMessage("Your friendlist is full. Max of 100 for free users, and 200 for members", 0, "", aBoolean991);
                return;
            }
            if (friendsCount >= 200) {
                pushMessage("Your friendlist is full. Max of 100 for free users, and 200 for members", 0, "", aBoolean991);
                return;
            }
            String s = TextClass.fixName(TextClass.longForName(l));
            for (int i = 0; i < friendsCount; i++) {
                if (friendsListAsLongs[i] == l) {
                    pushMessage(s + " is already on your friend list", 0, "", aBoolean991);
                    return;
                }
            }

            if (byte0 != 68) {
                pktType = -1;
            }
            for (int j = 0; j < ignoreCount; j++) {
                if (ignoreListAsLongs[j] == l) {
                    pushMessage("Please remove " + s + " from your ignore list first", 0, "", aBoolean991);
                    return;
                }
            }

            if (s.equals(aClass30_Sub2_Sub4_Sub1_Sub2_1126.name)) {
                return;
            } else {
                friendsList[friendsCount] = s;
                friendsListAsLongs[friendsCount] = l;
                friendNodeIDs[friendsCount] = 0;
                friendsCount++;
                needDrawTabArea = true;
                aClass30_Sub2_Sub2_1192.createFrame(188);
                aClass30_Sub2_Sub2_1192.writeQWord(l);
                return;
            }
        } catch (RuntimeException runtimeexception) {
            signlink.reporterror("15283, " + byte0 + ", " + l + ", " + runtimeexception.toString());
        }
        throw new RuntimeException();
    }

    public final int method42(int i, int j, boolean flag, int k) {
        loggedIn &= flag;
        int l = k >> 7;
        int i1 = j >> 7;
        if (l < 0 || i1 < 0 || l > 103 || i1 > 103) {
            return 0;
        }
        int j1 = i;
        if (j1 < 3 && (byteGroundArray[1][l][i1] & 2) == 2) {
            j1++;
        }
        int k1 = k & 0x7f;
        int l1 = j & 0x7f;
        int i2 = intGroundArray[j1][l][i1] * (128 - k1) + intGroundArray[j1][l + 1][i1] * k1 >> 7;
        int j2 = intGroundArray[j1][l][i1 + 1] * (128 - k1) + intGroundArray[j1][l + 1][i1 + 1] * k1 >> 7;
        return i2 * (128 - l1) + j2 * l1 >> 7;
    }

    private static final String intToKOrMil(int j) {
        if (j < 0x186a0) {
            return String.valueOf(j);
        }
        if (j < 0x989680) {
            return j / 1000 + "K";
        } else {
            return j / 0xf4240 + "M";
        }
    }

    public final void resetLogout() { //resetLogout
        try {
            if (aClass24_1168 != null) {
                aClass24_1168.close();
            }
        } catch (Exception _ex) {
        }
        aClass24_1168 = null;
        loggedIn = false;

        method23(false);
        aClass25_946.initToNull(619);
        for (int i = 0; i < 4; i++) {
            aClass11Array1230[i].method210();
        }

        System.gc();
        stopMidi();
        currentSong = -1;
        nextSong = -1;
        prevSong = 0;
        ResetChatOptions();
    }

    public final void method45(int i) {
        if (i != 0) {
            pktType = -1;
        }
        aBoolean1031 = true;
        for (int j = 0; j < 7; j++) {
            anIntArray1065[j] = -1;
            for (int k = 0; k < IDK.length; k++) {
                if (IDK.cache[k].aBoolean662 || IDK.cache[k].anInt657 != j + (aBoolean1047 ? 0 : 7)) {
                    continue;
                }
                anIntArray1065[j] = k;
                break;
            }

        }

    }

    private final void method46(int i, Stream class30_sub2_sub2) {
        while (class30_sub2_sub2.bitPosition + 21 < i * 8) {
            int k = class30_sub2_sub2.readBits(14);
            if (k == 16383) {
                break;
            }
            if (npcArray[k] == null) {
                npcArray[k] = new NPC();
            }
            NPC class30_sub2_sub4_sub1_sub1 = npcArray[k];
            anIntArray837[npcCount++] = k;
            class30_sub2_sub4_sub1_sub1.anInt1537 = loopCycle;
            int l = class30_sub2_sub2.readBits(5);
            if (l > 15) {
                l -= 32;
            }
            int i1 = class30_sub2_sub2.readBits(5);
            if (i1 > 15) {
                i1 -= 32;
            }
            int j1 = class30_sub2_sub2.readBits(1);
            class30_sub2_sub4_sub1_sub1.desc = EntityDef.forID(class30_sub2_sub2.readBits(12));
            int k1 = class30_sub2_sub2.readBits(1);
            if (k1 == 1) {
                anIntArray894[anInt893++] = k;
            }
            class30_sub2_sub4_sub1_sub1.anInt1540 = class30_sub2_sub4_sub1_sub1.desc.aByte68;
            class30_sub2_sub4_sub1_sub1.anInt1504 = class30_sub2_sub4_sub1_sub1.desc.anInt79;
            class30_sub2_sub4_sub1_sub1.anInt1554 = class30_sub2_sub4_sub1_sub1.desc.anInt67;
            class30_sub2_sub4_sub1_sub1.anInt1555 = class30_sub2_sub4_sub1_sub1.desc.anInt58;
            class30_sub2_sub4_sub1_sub1.anInt1556 = class30_sub2_sub4_sub1_sub1.desc.anInt83;
            class30_sub2_sub4_sub1_sub1.anInt1557 = class30_sub2_sub4_sub1_sub1.desc.anInt55;
            class30_sub2_sub4_sub1_sub1.anInt1511 = class30_sub2_sub4_sub1_sub1.desc.anInt77;
            class30_sub2_sub4_sub1_sub1.setPos(((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0] + i1, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0] + l, j1 == 1);
        }
        class30_sub2_sub2.finishBitAccess();
    }

    public final void processGameLoop() {
        if (rsAlreadyLoaded || loadingError || genericLoadingError) {
            return;
        }
        loopCycle++;
        if (!loggedIn) {
            processLoginScreenInput();
        } else {
            mainGameProcessor();
        }
        processOnDemandQueue();
    }

    public final void method47(boolean flag) {
        if (((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).x >> 7 == destX && ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).y >> 7 == destY) {
            destX = 0;
        }
        int j = anInt891;
        if (flag) {
            j = 1;
        }
        for (int l = 0; l < j; l++) {
            Player class30_sub2_sub4_sub1_sub2;
            int i1;
            if (flag) {
                class30_sub2_sub4_sub1_sub2 = aClass30_Sub2_Sub4_Sub1_Sub2_1126;
                i1 = anInt889 << 14;
            } else {
                class30_sub2_sub4_sub1_sub2 = playerArray[anIntArray892[l]];
                i1 = anIntArray892[l] << 14;
            }
            if (class30_sub2_sub4_sub1_sub2 == null || !class30_sub2_sub4_sub1_sub2.isVisible()) {
                continue;
            }
            class30_sub2_sub4_sub1_sub2.aBoolean1699 = false;
            if ((lowMem && anInt891 > 50 || anInt891 > 200) && !flag && ((Entity) (class30_sub2_sub4_sub1_sub2)).anInt1517 == ((Entity) (class30_sub2_sub4_sub1_sub2)).anInt1511) {
                class30_sub2_sub4_sub1_sub2.aBoolean1699 = true;
            }
            int j1 = ((Entity) (class30_sub2_sub4_sub1_sub2)).x >> 7;
            int k1 = ((Entity) (class30_sub2_sub4_sub1_sub2)).y >> 7;
            if (j1 < 0 || j1 >= 104 || k1 < 0 || k1 >= 104) {
                continue;
            }
            if (class30_sub2_sub4_sub1_sub2.aModel_1714 != null && loopCycle >= class30_sub2_sub4_sub1_sub2.anInt1707 && loopCycle < class30_sub2_sub4_sub1_sub2.anInt1708) {
                class30_sub2_sub4_sub1_sub2.aBoolean1699 = false;
                class30_sub2_sub4_sub1_sub2.anInt1709 = method42(plane, ((Entity) (class30_sub2_sub4_sub1_sub2)).y, true, ((Entity) (class30_sub2_sub4_sub1_sub2)).x);
                aClass25_946.method286(60, plane, ((Entity) (class30_sub2_sub4_sub1_sub2)).y, class30_sub2_sub4_sub1_sub2, ((Entity) (class30_sub2_sub4_sub1_sub2)).anInt1552, class30_sub2_sub4_sub1_sub2.anInt1722, ((Entity) (class30_sub2_sub4_sub1_sub2)).x, class30_sub2_sub4_sub1_sub2.anInt1709, class30_sub2_sub4_sub1_sub2.anInt1719, class30_sub2_sub4_sub1_sub2.anInt1721, i1, class30_sub2_sub4_sub1_sub2.anInt1720, (byte) 35);
                continue;
            }
            if ((((Entity) (class30_sub2_sub4_sub1_sub2)).x & 0x7f) == 64 && (((Entity) (class30_sub2_sub4_sub1_sub2)).y & 0x7f) == 64) {
                if (anIntArrayArray929[j1][k1] == anInt1265) {
                    continue;
                }
                anIntArrayArray929[j1][k1] = anInt1265;
            }
            class30_sub2_sub4_sub1_sub2.anInt1709 = method42(plane, ((Entity) (class30_sub2_sub4_sub1_sub2)).y, true, ((Entity) (class30_sub2_sub4_sub1_sub2)).x);
            aClass25_946.method285(plane, ((Entity) (class30_sub2_sub4_sub1_sub2)).anInt1552, (byte) 6, class30_sub2_sub4_sub1_sub2.anInt1709, i1, ((Entity) (class30_sub2_sub4_sub1_sub2)).y, 60, ((Entity) (class30_sub2_sub4_sub1_sub2)).x, class30_sub2_sub4_sub1_sub2, ((Entity) (class30_sub2_sub4_sub1_sub2)).aBoolean1541);
        }

    }

    public final boolean method48(int i, RSInterface class9) {
        if (i <= 0) {
            pktType = -1;
        }
        int j = class9.anInt214;
        if (anInt900 == 2) {
            if (j == 201) {
                inputTaken = true;
                inputDialogState = 0;
                messagePromptRaised = true;
                aString1212 = "";
                anInt1064 = 1;
                aString1121 = "Enter name of friend to add to list";
            }
            if (j == 202) {
                inputTaken = true;
                inputDialogState = 0;
                messagePromptRaised = true;
                aString1212 = "";
                anInt1064 = 2;
                aString1121 = "Enter name of friend to delete from list";
            }
        }
        if (j == 205) {
            anInt1011 = 250;
            return true;
        }
        if (j == 501) {
            inputTaken = true;
            inputDialogState = 0;
            messagePromptRaised = true;
            aString1212 = "";
            anInt1064 = 4;
            aString1121 = "Enter name of player to add to list";
        }
        if (j == 502) {
            inputTaken = true;
            inputDialogState = 0;
            messagePromptRaised = true;
            aString1212 = "";
            anInt1064 = 5;
            aString1121 = "Enter name of player to delete from list";
        }
        if (j >= 300 && j <= 313) {
            int k = (j - 300) / 2;
            int j1 = j & 1;
            int i2 = anIntArray1065[k];
            if (i2 != -1) {
                do {
                    if (j1 == 0 && --i2 < 0) {
                        i2 = IDK.length - 1;
                    }
                    if (j1 == 1 && ++i2 >= IDK.length) {
                        i2 = 0;
                    }
                } while (IDK.cache[i2].aBoolean662 || IDK.cache[i2].anInt657 != k + (aBoolean1047 ? 0 : 7));
                anIntArray1065[k] = i2;
                aBoolean1031 = true;
            }
        }
        if (j >= 314 && j <= 323) {
            int l = (j - 314) / 2;
            int k1 = j & 1;
            int j2 = anIntArray990[l];
            if (k1 == 0 && --j2 < 0) {
                j2 = anIntArrayArray1003[l].length - 1;
            }
            if (k1 == 1 && ++j2 >= anIntArrayArray1003[l].length) {
                j2 = 0;
            }
            anIntArray990[l] = j2;
            aBoolean1031 = true;
        }
        if (j == 324 && !aBoolean1047) {
            aBoolean1047 = true;
            method45(0);
        }
        if (j == 325 && aBoolean1047) {
            aBoolean1047 = false;
            method45(0);
        }
        if (j == 326) {
            aClass30_Sub2_Sub2_1192.createFrame(101);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(aBoolean1047 ? 0 : 1);
            for (int i1 = 0; i1 < 7; i1++) {
                aClass30_Sub2_Sub2_1192.writeWordBigEndian(anIntArray1065[i1]);
            }

            for (int l1 = 0; l1 < 5; l1++) {
                aClass30_Sub2_Sub2_1192.writeWordBigEndian(anIntArray990[l1]);
            }

            return true;
        }
        if (j == 620) {
            canMute = !canMute;
        }
        if (j >= 601 && j <= 613) {
            clearTopInterfaces();
            if (reportAbuseInput.length() > 0) {
                aClass30_Sub2_Sub2_1192.createFrame(218);
                aClass30_Sub2_Sub2_1192.writeQWord(TextClass.method583(reportAbuseInput));
                aClass30_Sub2_Sub2_1192.writeWordBigEndian(j - 601);
                aClass30_Sub2_Sub2_1192.writeWordBigEndian(canMute ? 1 : 0);
            }
        }
        return false;
    }

    private final void method49(int i, byte byte0, Stream class30_sub2_sub2) {
        if (byte0 == 2) {
            byte0 = 0;
        } else {
            return;
        }
        for (int j = 0; j < anInt893; j++) {
            int k = anIntArray894[j];
            Player class30_sub2_sub4_sub1_sub2 = playerArray[k];
            int l = class30_sub2_sub2.readUnsignedByte();
            if ((l & 0x40) != 0) {
                l += class30_sub2_sub2.readUnsignedByte() << 8;
            }
            method107(l, k, class30_sub2_sub2, aByte923, class30_sub2_sub4_sub1_sub2);
        }

    }

    public final void method50(int i, int j, int k, int l, int i1, int j1) {
        int k1 = aClass25_946.method300(j1, l, i);
        if (j >= 0) {
            return;
        }
        if (k1 != 0) {
            int l1 = aClass25_946.method304(j1, l, i, k1);
            int k2 = l1 >> 6 & 3;
            int i3 = l1 & 0x1f;
            int k3 = k;
            if (k1 > 0) {
                k3 = i1;
            }
            int ai[] = aClass30_Sub2_Sub1_Sub1_1263.myPixels;
            int k4 = 24624 + l * 4 + (103 - i) * 512 * 4;
            int i5 = k1 >> 14 & 0x7fff;
            ObjectDef class46_2 = ObjectDef.forID(i5);
            if (class46_2.anInt758 != -1) {
                Background class30_sub2_sub1_sub2_2 = mapScenes[class46_2.anInt758];
                if (class30_sub2_sub1_sub2_2 != null) {
                    int i6 = (class46_2.objectWidth * 4 - class30_sub2_sub1_sub2_2.myWidth) / 2;
                    int j6 = (class46_2.objectHeight * 4 - class30_sub2_sub1_sub2_2.myHeight) / 2;
                    class30_sub2_sub1_sub2_2.method361(48 + l * 4 + i6, 48 + (104 - i - class46_2.objectHeight) * 4 + j6);
                }
            } else {
                if (i3 == 0 || i3 == 2) {
                    if (k2 == 0) {
                        ai[k4] = k3;
                        ai[k4 + 512] = k3;
                        ai[k4 + 1024] = k3;
                        ai[k4 + 1536] = k3;
                    } else if (k2 == 1) {
                        ai[k4] = k3;
                        ai[k4 + 1] = k3;
                        ai[k4 + 2] = k3;
                        ai[k4 + 3] = k3;
                    } else if (k2 == 2) {
                        ai[k4 + 3] = k3;
                        ai[k4 + 3 + 512] = k3;
                        ai[k4 + 3 + 1024] = k3;
                        ai[k4 + 3 + 1536] = k3;
                    } else if (k2 == 3) {
                        ai[k4 + 1536] = k3;
                        ai[k4 + 1536 + 1] = k3;
                        ai[k4 + 1536 + 2] = k3;
                        ai[k4 + 1536 + 3] = k3;
                    }
                }
                if (i3 == 3) {
                    if (k2 == 0) {
                        ai[k4] = k3;
                    } else if (k2 == 1) {
                        ai[k4 + 3] = k3;
                    } else if (k2 == 2) {
                        ai[k4 + 3 + 1536] = k3;
                    } else if (k2 == 3) {
                        ai[k4 + 1536] = k3;
                    }
                }
                if (i3 == 2) {
                    if (k2 == 3) {
                        ai[k4] = k3;
                        ai[k4 + 512] = k3;
                        ai[k4 + 1024] = k3;
                        ai[k4 + 1536] = k3;
                    } else if (k2 == 0) {
                        ai[k4] = k3;
                        ai[k4 + 1] = k3;
                        ai[k4 + 2] = k3;
                        ai[k4 + 3] = k3;
                    } else if (k2 == 1) {
                        ai[k4 + 3] = k3;
                        ai[k4 + 3 + 512] = k3;
                        ai[k4 + 3 + 1024] = k3;
                        ai[k4 + 3 + 1536] = k3;
                    } else if (k2 == 2) {
                        ai[k4 + 1536] = k3;
                        ai[k4 + 1536 + 1] = k3;
                        ai[k4 + 1536 + 2] = k3;
                        ai[k4 + 1536 + 3] = k3;
                    }
                }
            }
        }
        k1 = aClass25_946.method302(j1, l, i);
        if (k1 != 0) {
            int i2 = aClass25_946.method304(j1, l, i, k1);
            int l2 = i2 >> 6 & 3;
            int j3 = i2 & 0x1f;
            int l3 = k1 >> 14 & 0x7fff;
            ObjectDef class46_1 = ObjectDef.forID(l3);
            if (class46_1.anInt758 != -1) {
                Background class30_sub2_sub1_sub2_1 = mapScenes[class46_1.anInt758];
                if (class30_sub2_sub1_sub2_1 != null) {
                    int j5 = (class46_1.objectWidth * 4 - class30_sub2_sub1_sub2_1.myWidth) / 2;
                    int k5 = (class46_1.objectHeight * 4 - class30_sub2_sub1_sub2_1.myHeight) / 2;
                    class30_sub2_sub1_sub2_1.method361(48 + l * 4 + j5, 48 + (104 - i - class46_1.objectHeight) * 4 + k5);
                }
            } else if (j3 == 9) {
                int l4 = 0xeeeeee;
                if (k1 > 0) {
                    l4 = 0xee0000;
                }
                int ai1[] = aClass30_Sub2_Sub1_Sub1_1263.myPixels;
                int l5 = 24624 + l * 4 + (103 - i) * 512 * 4;
                if (l2 == 0 || l2 == 2) {
                    ai1[l5 + 1536] = l4;
                    ai1[l5 + 1024 + 1] = l4;
                    ai1[l5 + 512 + 2] = l4;
                    ai1[l5 + 3] = l4;
                } else {
                    ai1[l5] = l4;
                    ai1[l5 + 512 + 1] = l4;
                    ai1[l5 + 1024 + 2] = l4;
                    ai1[l5 + 1536 + 3] = l4;
                }
            }
        }
        k1 = aClass25_946.method303(j1, l, i);
        if (k1 != 0) {
            int j2 = k1 >> 14 & 0x7fff;
            ObjectDef class46 = ObjectDef.forID(j2);
            if (class46.anInt758 != -1) {
                Background class30_sub2_sub1_sub2 = mapScenes[class46.anInt758];
                if (class30_sub2_sub1_sub2 != null) {
                    int i4 = (class46.objectWidth * 4 - class30_sub2_sub1_sub2.myWidth) / 2;
                    int j4 = (class46.objectHeight * 4 - class30_sub2_sub1_sub2.myHeight) / 2;
                    class30_sub2_sub1_sub2.method361(48 + l * 4 + i4, 48 + (104 - i - class46.objectHeight) * 4 + j4);
                }
            }
        }
    }

    public final void loadtitlescreen() {
        aClass30_Sub2_Sub1_Sub2Array1152 = new Background[12];
        aClass30_Sub2_Sub1_Sub1_1201 = new Sprite(128, 265);
        aClass30_Sub2_Sub1_Sub1_1202 = new Sprite(128, 265);
        for (int i1 = 0; i1 < 33920; i1++) {
            aClass30_Sub2_Sub1_Sub1_1201.myPixels[i1] = aClass15_1110.anIntArray315[i1];
        }
        for (int j1 = 0; j1 < 33920; j1++) {
            aClass30_Sub2_Sub1_Sub1_1202.myPixels[j1] = aClass15_1111.anIntArray315[j1];
        }
        anIntArray851 = new int[256];
        for (int k1 = 0; k1 < 64; k1++) {
            anIntArray851[k1] = k1 * 0x40000;
        }
        for (int l1 = 0; l1 < 64; l1++) {
            anIntArray851[l1 + 64] = 0x660000 + 1024 * l1;
        }
        for (int i2 = 0; i2 < 64; i2++) {
            anIntArray851[i2 + 128] = 0x66600 + 4 * i2;
        }
        for (int j2 = 0; j2 < 64; j2++) {
            anIntArray851[j2 + 192] = 0x666666;
        }
        anIntArray852 = new int[256];
        for (int k2 = 0; k2 < 64; k2++) {
            anIntArray852[k2] = k2 * 1024;
        }
        for (int l2 = 0; l2 < 64; l2++) {
            anIntArray852[l2 + 64] = 65280 + 4 * l2;
        }
        for (int i3 = 0; i3 < 64; i3++) {
            anIntArray852[i3 + 128] = 65535 + 0x40000 * i3;
        }
        for (int j3 = 0; j3 < 64; j3++) {
            anIntArray852[j3 + 192] = 0x666666;
        }
        anIntArray853 = new int[256];
        for (int k3 = 0; k3 < 64; k3++) {
            anIntArray853[k3] = k3 * 4;
        }
        for (int l3 = 0; l3 < 64; l3++) {
            anIntArray853[l3 + 64] = 255 + 0x60000 * l3;
        }
        for (int i4 = 0; i4 < 64; i4++) {
            anIntArray853[i4 + 128] = 0x660066 + 1024 * i4;
        }
        for (int j4 = 0; j4 < 64; j4++) {
            anIntArray853[j4 + 192] = 0x666666;
        }
        anIntArray850 = new int[256];
        anIntArray1190 = new int[32768];
        anIntArray1191 = new int[32768];
        method106(null, -135);
        anIntArray828 = new int[32768];
        anIntArray829 = new int[32768];
        drawLoadingText(10, "Connecting To Fileserver");
        if (!aBoolean831) {
            drawFlames = true;
            aBoolean831 = true;
            startRunnable(this, 2);
        }
    }

    public static final void setHighMem(boolean flag) {
        WorldController.lowMem = false;
        Texture.lowMem = false;
        lowMem = false;
        ObjectManager.lowMem = false;
        if (flag) {
            aBoolean919 = !aBoolean919;
        }
        ObjectDef.lowMem = false;
    }

    public static void main(String args[]) {
        try {
            nodeID = 0;
            setHighMem(false);
            isMembers = true;
            signlink.startpriv(InetAddress.getLocalHost());
            client1 = new client();
            client1.createClientFrame(503, 765);
            System.out.println("Client GUI Loaded.");
            return;
        } catch (Exception exception) {
            return;
        }
    }

    public static void setserver(String s, String p) {
        server = s;
        port = p;
    }

    public final void loadingStages() {
        if (lowMem && loadingStage == 2 && ObjectManager.anInt131 != plane) {
            aClass15_1165.method237(0);
            DrawingArea.fillPixels(1, 130, 23, 0xffffff, 2);
            DrawingArea.method336(21, 3, 2, 0, 128);
            regularFont.drawText(0, "Loading - please wait.", 19, 67);
            regularFont.drawText(0xffffff, "Loading - please wait.", 18, 66);
            aClass15_1165.drawGraphics(4, 23680, super.aGraphics12, 4);
            loadingStage = 1;
            aLong824 = System.currentTimeMillis();
        }

        if (loadingStage == 1) {
            int j = method54();
            if (j != 0 && System.currentTimeMillis() - aLong824 > 0x57e40L) {
                signlink.reporterror(myUsername + " glcfb " + aLong1215 + "," + j + "," + lowMem + "," + aClass14Array970[0] + "," + aClass42_Sub1_1068.getNodeCount() + "," + plane + "," + anInt1069 + "," + anInt1070);
                aLong824 = System.currentTimeMillis();
            }
        }
        if (loadingStage == 2 && plane != anInt985) {
            anInt985 = plane;
            method24(true, plane);
        }
    }

    public final int method54() {
        for (int i = 0; i < aByteArrayArray1183.length; i++) {
            if (aByteArrayArray1183[i] == null && mapId[i] != -1) {
                return -1;
            }
            if (aByteArrayArray1247[i] == null && objectFileID[i] != -1) {
                return -2;
            }
        }

        boolean flag = true;
        for (int j = 0; j < aByteArrayArray1183.length; j++) {
            byte abyte0[] = aByteArrayArray1247[j];
            if (abyte0 != null) {
                int k = (mapArea[j] >> 8) * 64 - baseX;
                int l = (mapArea[j] & 0xff) * 64 - baseY;
                if (aBoolean1159) {
                    k = 10;
                    l = 10;
                }
                flag &= ObjectManager.method189(k, abyte0, l, 6);
            }
        }

        if (!flag) {
            return -3;
        }
        if (aBoolean1080) {
            return -4;
        } else {
            loadingStage = 2;
            ObjectManager.anInt131 = plane;
            method22(true);
            aClass30_Sub2_Sub2_1192.createFrame(121);
            return 0;
        }
    }

    public final void method55() {
        for (Animable_Sub4 class30_sub2_sub4_sub4 = (Animable_Sub4) aClass19_1013.reverseGetFirst(); class30_sub2_sub4_sub4 != null; class30_sub2_sub4_sub4 = (Animable_Sub4) aClass19_1013.reverseGetNext()) {
            if (class30_sub2_sub4_sub4.anInt1597 != plane || loopCycle > class30_sub2_sub4_sub4.anInt1572) {
                class30_sub2_sub4_sub4.unlink();
            } else if (loopCycle >= class30_sub2_sub4_sub4.anInt1571) {
                if (class30_sub2_sub4_sub4.anInt1590 > 0) {
                    NPC class30_sub2_sub4_sub1_sub1 = npcArray[class30_sub2_sub4_sub4.anInt1590 - 1];
                    if (class30_sub2_sub4_sub1_sub1 != null && ((Entity) (class30_sub2_sub4_sub1_sub1)).x >= 0 && ((Entity) (class30_sub2_sub4_sub1_sub1)).x < 13312 && ((Entity) (class30_sub2_sub4_sub1_sub1)).y >= 0 && ((Entity) (class30_sub2_sub4_sub1_sub1)).y < 13312) {
                        class30_sub2_sub4_sub4.method455(loopCycle, ((Entity) (class30_sub2_sub4_sub1_sub1)).y, method42(class30_sub2_sub4_sub4.anInt1597, ((Entity) (class30_sub2_sub4_sub1_sub1)).y, true, ((Entity) (class30_sub2_sub4_sub1_sub1)).x) - class30_sub2_sub4_sub4.anInt1583, ((Entity) (class30_sub2_sub4_sub1_sub1)).x, (byte) -83);
                    }
                }
                if (class30_sub2_sub4_sub4.anInt1590 < 0) {
                    int j = -class30_sub2_sub4_sub4.anInt1590 - 1;
                    Player class30_sub2_sub4_sub1_sub2;
                    if (j == anInt884) {
                        class30_sub2_sub4_sub1_sub2 = aClass30_Sub2_Sub4_Sub1_Sub2_1126;
                    } else {
                        class30_sub2_sub4_sub1_sub2 = playerArray[j];
                    }
                    if (class30_sub2_sub4_sub1_sub2 != null && ((Entity) (class30_sub2_sub4_sub1_sub2)).x >= 0 && ((Entity) (class30_sub2_sub4_sub1_sub2)).x < 13312 && ((Entity) (class30_sub2_sub4_sub1_sub2)).y >= 0 && ((Entity) (class30_sub2_sub4_sub1_sub2)).y < 13312) {
                        class30_sub2_sub4_sub4.method455(loopCycle, ((Entity) (class30_sub2_sub4_sub1_sub2)).y, method42(class30_sub2_sub4_sub4.anInt1597, ((Entity) (class30_sub2_sub4_sub1_sub2)).y, true, ((Entity) (class30_sub2_sub4_sub1_sub2)).x) - class30_sub2_sub4_sub4.anInt1583, ((Entity) (class30_sub2_sub4_sub1_sub2)).x, (byte) -83);
                    }
                }
                class30_sub2_sub4_sub4.method456(anInt945, anInt1020);
                aClass25_946.method285(plane, class30_sub2_sub4_sub4.anInt1595, (byte) 6, (int) class30_sub2_sub4_sub4.aDouble1587, -1, (int) class30_sub2_sub4_sub4.aDouble1586, 60, (int) class30_sub2_sub4_sub4.aDouble1585, class30_sub2_sub4_sub4, false);
            }
        }

    }

    public final AppletContext getAppletContext() {
        if (signlink.mainapp != null) {
            return signlink.mainapp.getAppletContext();
        } else {
            return super.getAppletContext();
        }
    }

    public final void drawLogo() {
        byte abyte0[] = aClass44_1053.getDataForName("title.dat");
        Sprite class30_sub2_sub1_sub1 = new Sprite(this);
        aClass15_1110.method237(0);
        aClass15_1111.method237(0);
        aClass15_1107.method237(0);
        aClass15_1108.method237(0);

        loginArea.method237(0);

        aClass15_1112.method237(0);
        aClass15_1113.method237(0);
        aClass15_1114.method237(0);
        aClass15_1115.method237(0);
        int ai[] = new int[class30_sub2_sub1_sub1.myWidth];
        for (int j = 0; j < class30_sub2_sub1_sub1.myHeight; j++) {
            for (int k = 0; k < class30_sub2_sub1_sub1.myWidth; k++) {
                ai[k] = class30_sub2_sub1_sub1.myPixels[(class30_sub2_sub1_sub1.myWidth - k - 1) + class30_sub2_sub1_sub1.myWidth * j];
            }
            for (int l = 0; l < class30_sub2_sub1_sub1.myWidth; l++) {
                class30_sub2_sub1_sub1.myPixels[l + class30_sub2_sub1_sub1.myWidth * j] = ai[l];
            }
        }
        class30_sub2_sub1_sub1 = null;
        Object obj = null;
        Object obj1 = null;
        System.gc();
        return;
    }

    public final void processOnDemandQueue() {
        do {
            OnDemandData class30_sub2_sub3;
            do {
                class30_sub2_sub3 = aClass42_Sub1_1068.getNextNode();
                if (class30_sub2_sub3 == null) {
                    return;
                }
                if (class30_sub2_sub3.dataType == 0) {
                    Model.readRsModel(class30_sub2_sub3.buffer, -4036, class30_sub2_sub3.ID);
                    if ((aClass42_Sub1_1068.getModelIndex(class30_sub2_sub3.ID) & 0x62) != 0) {
                        needDrawTabArea = true;
                        if (backDialogID != -1) {
                            inputTaken = true;
                        }
                    }
                }
                if (class30_sub2_sub3.dataType == 1 && class30_sub2_sub3.buffer != null) {
                    Class36.method529(class30_sub2_sub3.buffer);
                }
                if (class30_sub2_sub3.dataType == 2 && class30_sub2_sub3.ID == nextSong && class30_sub2_sub3.buffer != null) {
                    method21(songChanging, 0, class30_sub2_sub3.buffer);
                }
                if (class30_sub2_sub3.dataType == 3 && loadingStage == 1) {
                    for (int i = 0; i < aByteArrayArray1183.length; i++) {
                        if (mapId[i] == class30_sub2_sub3.ID) {
                            aByteArrayArray1183[i] = class30_sub2_sub3.buffer;
                            if (class30_sub2_sub3.buffer == null) {
                                mapId[i] = -1;
                            }
                            break;
                        }
                        if (objectFileID[i] != class30_sub2_sub3.ID) {
                            continue;
                        }
                        aByteArrayArray1247[i] = class30_sub2_sub3.buffer;
                        if (class30_sub2_sub3.buffer == null) {
                            objectFileID[i] = -1;
                        }
                        break;
                    }
                }
            } while (class30_sub2_sub3.dataType != 93 || !aClass42_Sub1_1068.method564(class30_sub2_sub3.ID, -520));
            ObjectManager.method173((byte) -107, new Stream(class30_sub2_sub3.buffer), aClass42_Sub1_1068);
        } while (true);
    }

    public boolean saveWave(byte abyte0[], int i) {
        if (abyte0 == null) {
            return true;
        } else {
            for (int j = 1; j < 10; j++) {
                if (!signlink.waveplay[j]) {
                    return signlink.wavesave(abyte0, i, j);
                }
            }
            return false;
        }
    }

    public final void method60(int i) {
        RSInterface class9 = RSInterface.interfaceCache[i];
        for (int j = 0; j < class9.children.length; j++) {
            if (class9.children[j] == -1) {
                break;
            }
            RSInterface class9_1 = RSInterface.interfaceCache[class9.children[j]];
            if (class9_1.type == 1) {
                method60(class9_1.id);
            }
            class9_1.anInt246 = 0;
            class9_1.anInt208 = 0;
        }
    }

    public final void drawHeadIcon() {
        if (anInt855 != 2) {
            return;
        }
        method128((anInt934 - baseX << 7) + anInt937, anInt936 * 2, anInt875, (anInt935 - baseY << 7) + anInt938);
        aBoolean1224 = !aBoolean1224;
        if (spriteDrawX > -1 && loopCycle % 20 < 10) {
            headIcons[2].method348(spriteDrawX - 12, spriteDrawY - 28);
        }
    }

    public final void mainGameProcessor() {
        if (updateTime > 1) {
            updateTime--;
        }
        if (anInt1011 > 0) {
            anInt1011--;
        }
        for (int j = 0; j < 5; j++) {
            if (!parsePacket()) {
                break;
            }
        }
        if (!loggedIn) {
            return;
        }
        synchronized (mouseDetection.object) {
            if (flagged) {
                if (super.anInt26 != 0 || mouseDetection.anInt810 >= 40) {
                    aClass30_Sub2_Sub2_1192.createFrame(45);
                    aClass30_Sub2_Sub2_1192.writeWordBigEndian(0);
                    int j2 = aClass30_Sub2_Sub2_1192.currentOffset;
                    int j3 = 0;
                    for (int j4 = 0; j4 < mouseDetection.anInt810; j4++) {
                        if (j2 - aClass30_Sub2_Sub2_1192.currentOffset >= 240) {
                            break;
                        }
                        j3++;
                        int l4 = mouseDetection.anIntArray807[j4];
                        if (l4 < 0) {
                            l4 = 0;
                        } else if (l4 > 502) {
                            l4 = 502;
                        }
                        int k5 = mouseDetection.anIntArray809[j4];
                        if (k5 < 0) {
                            k5 = 0;
                        } else if (k5 > 764) {
                            k5 = 764;
                        }
                        int i6 = l4 * 765 + k5;
                        if (mouseDetection.anIntArray807[j4] == -1 && mouseDetection.anIntArray809[j4] == -1) {
                            k5 = -1;
                            l4 = -1;
                            i6 = 0x7ffff;
                        }
                        if (k5 == anInt1237 && l4 == anInt1238) {
                            if (anInt1022 < 2047) {
                                anInt1022++;
                            }
                        } else {
                            int j6 = k5 - anInt1237;
                            anInt1237 = k5;
                            int k6 = l4 - anInt1238;
                            anInt1238 = l4;
                            if (anInt1022 < 8 && j6 >= -32 && j6 <= 31 && k6 >= -32 && k6 <= 31) {
                                j6 += 32;
                                k6 += 32;
                                aClass30_Sub2_Sub2_1192.writeWord((anInt1022 << 12) + (j6 << 6) + k6);
                                anInt1022 = 0;
                            } else if (anInt1022 < 8) {
                                aClass30_Sub2_Sub2_1192.writeDWordBigEndian(0x800000 + (anInt1022 << 19) + i6);
                                anInt1022 = 0;
                            } else {
                                aClass30_Sub2_Sub2_1192.writeDWord(0xc0000000 + (anInt1022 << 19) + i6);
                                anInt1022 = 0;
                            }
                        }
                    }
                    aClass30_Sub2_Sub2_1192.writeBytes(aClass30_Sub2_Sub2_1192.currentOffset - j2);
                    if (j3 >= mouseDetection.anInt810) {
                        mouseDetection.anInt810 = 0;
                    } else {
                        mouseDetection.anInt810 -= j3;
                        for (int i5 = 0; i5 < mouseDetection.anInt810; i5++) {
                            mouseDetection.anIntArray809[i5] = mouseDetection.anIntArray809[i5 + j3];
                            mouseDetection.anIntArray807[i5] = mouseDetection.anIntArray807[i5 + j3];
                        }
                    }
                }
            } else {
                mouseDetection.anInt810 = 0;
            }
        }
        if (super.anInt26 != 0) {
            long l = (super.aLong29 - aLong1220) / 50L;
            if (l > 4095L) {
                l = 4095L;
            }
            aLong1220 = super.aLong29;
            int k2 = super.saveClickY;
            if (k2 < 0) {
                k2 = 0;
            } else if (k2 > 502) {
                k2 = 502;
            }
            int k3 = super.saveClickX;
            if (k3 < 0) {
                k3 = 0;
            } else if (k3 > 764) {
                k3 = 764;
            }
            int k4 = k2 * 765 + k3;
            int j5 = 0;
            if (super.anInt26 == 2) {
                j5 = 1;
            }
            int l5 = (int) l;
            aClass30_Sub2_Sub2_1192.createFrame(241);
            aClass30_Sub2_Sub2_1192.writeDWord((l5 << 20) + (j5 << 19) + k4);
        }
        if (anInt1016 > 0) {
            anInt1016--;
        }
        if (super.keyArray[1] == 1 || super.keyArray[2] == 1 || super.keyArray[3] == 1 || super.keyArray[4] == 1) //pressing an arrow ket
        {
            aBoolean1017 = true;
        }
        if (aBoolean1017 && anInt1016 <= 0) {
            anInt1016 = 20;
            aBoolean1017 = false;
            aClass30_Sub2_Sub2_1192.createFrame(86);
            aClass30_Sub2_Sub2_1192.writeWord(anInt1184);
            aClass30_Sub2_Sub2_1192.method432(anInt1185);
        }
        if (super.awtFocus && !aBoolean954) {
            aBoolean954 = true;
            aClass30_Sub2_Sub2_1192.createFrame(3);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(1);
        }
        if (!super.awtFocus && aBoolean954) {
            aBoolean954 = false;
            aClass30_Sub2_Sub2_1192.createFrame(3);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(0);
        }
        loadingStages();
        method115((byte) 8);
        method90(false);
        anInt1009++;
        if (anInt1009 > 750) {
            dropClient();
        }
        method114((byte) -74);
        method95(-8066);
        method38((byte) -92);
        anInt945++;
        if (crossType != 0) {
            crossIndex += 20;
            if (crossIndex >= 400) {
                crossType = 0;
            }
        }
        if (atInventoryInterfaceType != 0) {
            atInventoryLoopCycle++;
            if (atInventoryLoopCycle >= 15) {
                if (atInventoryInterfaceType == 2) {
                    needDrawTabArea = true;
                }
                if (atInventoryInterfaceType == 3) {
                    inputTaken = true;
                }
                atInventoryInterfaceType = 0;
            }
        }
        if (activeInterfaceType != 0) {
            anInt989++;
            if (super.anInt20 > anInt1087 + 5 || super.anInt20 < anInt1087 - 5 || super.anInt21 > anInt1088 + 5 || super.anInt21 < anInt1088 - 5) {
                aBoolean1242 = true;
            }
            if (super.clickMode2 == 0) {
                if (activeInterfaceType == 2) {
                    needDrawTabArea = true;
                }
                if (activeInterfaceType == 3) {
                    inputTaken = true;
                }
                activeInterfaceType = 0;
                if (aBoolean1242 && anInt989 >= 5) {
                    lastActiveInvInterface = -1;
                    processRightClick();
                    if (lastActiveInvInterface == anInt1084 && mouseInvInterfaceIndex != anInt1085) {
                        RSInterface class9 = RSInterface.interfaceCache[anInt1084];
                        int j1 = 0;
                        if (anInt913 == 1 && class9.anInt214 == 206) {
                            j1 = 1;
                        }
                        if (class9.inv[mouseInvInterfaceIndex] <= 0) {
                            j1 = 0;
                        }
                        if (class9.aBoolean235) {
                            int l2 = anInt1085;
                            int l3 = mouseInvInterfaceIndex;
                            class9.inv[l3] = class9.inv[l2];
                            class9.invStackSized[l3] = class9.invStackSized[l2];
                            class9.inv[l2] = -1;
                            class9.invStackSized[l2] = 0;
                        } else if (j1 == 1) {
                            int i3 = anInt1085;
                            for (int i4 = mouseInvInterfaceIndex; i3 != i4;) {
                                if (i3 > i4) {
                                    class9.swapInventoryItems(i3, (byte) 9, i3 - 1);
                                    i3--;
                                } else if (i3 < i4) {
                                    class9.swapInventoryItems(i3, (byte) 9, i3 + 1);
                                    i3++;
                                }
                            }
                        } else {
                            class9.swapInventoryItems(anInt1085, (byte) 9, mouseInvInterfaceIndex);
                        }
                        aClass30_Sub2_Sub2_1192.createFrame(214);
                        aClass30_Sub2_Sub2_1192.method433(anInt1084);
                        aClass30_Sub2_Sub2_1192.method424(j1);
                        aClass30_Sub2_Sub2_1192.method433(anInt1085);
                        aClass30_Sub2_Sub2_1192.method431(mouseInvInterfaceIndex);
                    }
                } else if ((anInt1253 == 1 || menuHasAddFriend(menuActionRow - 1)) && menuActionRow > 2) {
                    determineMenuSize();
                } else if (menuActionRow > 0) {
                    doAction(menuActionRow - 1);
                }
                atInventoryLoopCycle = 10;
                super.anInt26 = 0;
            }
        }
        if (WorldController.anInt470 != -1) {
            int k = WorldController.anInt470;
            int k1 = WorldController.anInt471;
            boolean flag = method85(0, 0, 0, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 0, 0, k1, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], true, k);
            WorldController.anInt470 = -1;
            if (flag) {
                anInt914 = super.saveClickX;
                anInt915 = super.saveClickY;
                crossType = 1;
                crossIndex = 0;
            }
        }
        if (super.anInt26 == 1 && aString844 != null) {
            aString844 = null;
            inputTaken = true;
            super.anInt26 = 0;
        }
        if (!processMenuClick()) {
            processMainScreenClick();
            processTabClick();
            processChatModeClick();
        }
        if (super.clickMode2 == 1 || super.anInt26 == 1) {
            anInt1213++;
        }
        if (loadingStage == 2) {
            method108();
        }
        if (loadingStage == 2 && aBoolean1160) {
            calcCameraPos();
        }
        for (int i1 = 0; i1 < 5; i1++) {
            anIntArray1030[i1]++;
        }
        processPlayerInput();
        super.idleTime++;
        if (super.idleTime == 4500) { //idle time
            anInt1011 = 250;
            super.idleTime -= 500;
            aClass30_Sub2_Sub2_1192.createFrame(202); //send the idle packet
        }
        anInt988++;
        if (anInt988 > 500) {
            anInt988 = 0;
            int l1 = (int) (Math.random() * 8D);
            if ((l1 & 1) == 1) {
                anInt1278 += anInt1279;
            }
            if ((l1 & 2) == 2) {
                anInt1131 += anInt1132;
            }
            if ((l1 & 4) == 4) {
                anInt896 += anInt897;
            }
        }
        if (anInt1278 < -50) {
            anInt1279 = 2;
        }
        if (anInt1278 > 50) {
            anInt1279 = -2;
        }
        if (anInt1131 < -55) {
            anInt1132 = 2;
        }
        if (anInt1131 > 55) {
            anInt1132 = -2;
        }
        if (anInt896 < -40) {
            anInt897 = 1;
        }
        if (anInt896 > 40) {
            anInt897 = -1;
        }
        anInt1254++;
        if (anInt1254 > 500) {
            anInt1254 = 0;
            int i2 = (int) (Math.random() * 8D);
            if ((i2 & 1) == 1) {
                minimapInt2 += anInt1210;
            }
            if ((i2 & 2) == 2) {
                minimapInt3 += anInt1171;
            }
        }
        if (minimapInt2 < -60) {
            anInt1210 = 2;
        }
        if (minimapInt2 > 60) {
            anInt1210 = -2;
        }
        if (minimapInt3 < -20) {
            anInt1171 = 1;
        }
        if (minimapInt3 > 10) {
            anInt1171 = -1;
        }
        anInt1010++;
        if (anInt1010 > 50) {
            aClass30_Sub2_Sub2_1192.createFrame(0);
        }
        try {
            if (aClass24_1168 != null && aClass30_Sub2_Sub2_1192.currentOffset > 0) {
                aClass24_1168.queueBytes(aClass30_Sub2_Sub2_1192.currentOffset, aClass30_Sub2_Sub2_1192.buffer, 0);
                aClass30_Sub2_Sub2_1192.currentOffset = 0;
                anInt1010 = 0;
                return;
            }
        } catch (IOException _ex) {
            dropClient();
            return;
        } catch (Exception exception) {
            resetLogout();
        }
    }

    private final void method63(int i) {
        Class30_Sub1 class30_sub1 = (Class30_Sub1) aClass19_1179.reverseGetFirst();
        while (i >= 0) {
            for (int j = 1; j > 0; j++);
        }
        for (; class30_sub1 != null; class30_sub1 = (Class30_Sub1) aClass19_1179.reverseGetNext()) {
            if (class30_sub1.anInt1294 == -1) {
                class30_sub1.anInt1302 = 0;
                method89(false, class30_sub1);
            } else {
                class30_sub1.unlink();
            }
        }

    }

    public final void method64(int i) { //resetImageProducers

        if (aClass15_1107 != null) {
            return;
        }
        super.aClass15_13 = null;
        aClass15_1166 = null;
        aClass15_1164 = null;
        aClass15_1163 = null;
        aClass15_1165 = null;
        aClass15_1123 = null;
        aClass15_1124 = null;
        aClass15_1125 = null;
        aClass15_1110 = new RSImageProducer(128, 265, getGameComponent(), 0);
        DrawingArea.setAllPixelsToZero();
        aClass15_1111 = new RSImageProducer(128, 265, getGameComponent(), 0);
        DrawingArea.setAllPixelsToZero();
        aClass15_1107 = new RSImageProducer(509, 171, getGameComponent(), 0);
        DrawingArea.setAllPixelsToZero();
        aClass15_1108 = new RSImageProducer(360, 132, getGameComponent(), 0);

        //LARGE LOGIN
        DrawingArea.setAllPixelsToZero();
        loginArea = new RSImageProducer(765, 503, getGameComponent(), 0);


        DrawingArea.setAllPixelsToZero();
        aClass15_1112 = new RSImageProducer(202, 238, getGameComponent(), 0);
        if (i < 0 || i > 0) {
            groundArray = null;
        }
        DrawingArea.setAllPixelsToZero();
        aClass15_1113 = new RSImageProducer(203, 238, getGameComponent(), 0);
        DrawingArea.setAllPixelsToZero();
        aClass15_1114 = new RSImageProducer(74, 94, getGameComponent(), 0);
        DrawingArea.setAllPixelsToZero();
        aClass15_1115 = new RSImageProducer(75, 94, getGameComponent(), 0);
        DrawingArea.setAllPixelsToZero();
        if (aClass44_1053 != null) {
            drawLogo();
            loadtitlescreen();
        }
        welcomeScreenRaised = true;
    }

    public final void drawLoadingText(int i, String s) {
        anInt1079 = i; //percentage
        aString1049 = s; //what its doing
        method64(0);
        if (aClass44_1053 == null) { //the bit where we draw it is null..
            return;
        }
        loginArea.method237(0);
        int totWidth = 765;
        int totHeight = 503;
        int barWidth = Csettings.barWidth;
        int barHeight = Csettings.barHeight;


        if (Csettings.LoadingBarOn) {

            if (Csettings.LoadingBarStyle == 0) {

                barWidth = 300;
                barHeight = 30;
                int ill = (i * barWidth) / 100;
                if (ill > barWidth) {
                    ill = barWidth;
                }
                LoadingText.method348(totWidth / 2 - 170, totHeight / 2 - 50);
                //LoadingEmpty.method348(totWidth / 2 - (barWidth / 2), totHeight / 2  - (barHeight / 2) + 12);
                LoadingFull = new Sprite(sign.signlink.findcachedir() + "sprites/titlescreen/load/custom-full.png", ill, barHeight);
                LoadingFull.method348(totWidth / 2 - (barWidth / 2), totHeight / 2 - (barHeight / 2) + 12);

            } else if (Csettings.LoadingBarStyle == 1) {


                barWidth = 190;
                barHeight = 17;

                int ill = (i * barWidth) / 100;
                if (ill > barWidth) {
                    ill = barWidth;
                }

                NewLoadBase.method348(totWidth / 2 - 163, 50); //bg

                NewLoadFill = new Sprite(sign.signlink.findcachedir() + "sprites/titlescreen/load/rs-full.png", ill, barHeight);
                NewLoadFill.method348(totWidth / 2 - (barWidth / 2) - 4, 72);

                //loading text
                if (Csettings.ShowLoadingText) {
                    smallFont.drawText(0xffffff, s, 67, totWidth / 2); //middle text
                }

            } else if (Csettings.LoadingBarStyle == 2) {

                DrawingArea.fillPixels(totWidth / 2 - (barWidth / 2) - 2, barWidth + 4/*width*/, barHeight + 4/*height*/, 0x2051D2, totHeight / 2); //OUTLINE
                DrawingArea.fillPixels(totWidth / 2 - (barWidth / 2) - 1, barWidth + 2/*width*/, barHeight + 2/*height*/, 0x000000, (totHeight / 2) + 1); //INLINE

                DrawingArea.method336(barHeight/*height*/, (totHeight / 2) + 2, totWidth / 2 - (barWidth / 2), 0x2051D2, i * 3); //MAIN BAR
                DrawingArea.method336(barHeight/*height*/, (totHeight / 2) + 2, (totWidth / 2 - (barWidth / 2)) + i * 3, 0, barWidth - i * 3);

                //loading text
                if (Csettings.ShowLoadingText) {
                    fancyFont.drawText(0xb6a689, "-GaMeR X-'s Client - Loading...", (totHeight / 2) - (barHeight / 2), totWidth / 2);
                    smallFont.drawText(0xb6a689, s, (totHeight / 2) + (barHeight / 2) + 6, totWidth / 2); //middle text
                }
            }
        }
        loginArea.drawGraphics(0, 23680, super.aGraphics12, 0);
    }

    public final void method65(int i, int j, int k, int l, RSInterface class9, int i1, boolean flag,
            int j1, int k1) {
        if (aBoolean972) {
            anInt992 = 32;
        } else {
            anInt992 = 0;
        }
        aBoolean972 = false;
        pktSize += k1;
        if (k >= i && k < i + 16 && l >= i1 && l < i1 + 16) {
            class9.scrollPosition -= anInt1213 * 4;
            if (flag) {
                needDrawTabArea = true;
                return;
            }
        } else if (k >= i && k < i + 16 && l >= (i1 + j) - 16 && l < i1 + j) {
            class9.scrollPosition += anInt1213 * 4;
            if (flag) {
                needDrawTabArea = true;
                return;
            }
        } else if (k >= i - anInt992 && k < i + 16 + anInt992 && l >= i1 + 16 && l < (i1 + j) - 16 && anInt1213 > 0) {
            int l1 = ((j - 32) * j) / j1;
            if (l1 < 8) {
                l1 = 8;
            }
            int i2 = l - i1 - 16 - l1 / 2;
            int j2 = j - 32 - l1;
            class9.scrollPosition = ((j1 - j) * i2) / j2;
            if (flag) {
                needDrawTabArea = true;
            }
            aBoolean972 = true;
        }
    }

    public final boolean method66(int i, int j, int k, int l) {
        int i1 = i >> 14 & 0x7fff;
        int j1 = aClass25_946.method304(plane, k, j, i);
        if (l >= 0) {
            throw new NullPointerException();
        }
        if (j1 == -1) {
            return false;
        }
        int k1 = j1 & 0x1f;
        int l1 = j1 >> 6 & 3;
        if (k1 == 10 || k1 == 11 || k1 == 22) {
            ObjectDef class46 = ObjectDef.forID(i1);
            int i2;
            int j2;
            if (l1 == 0 || l1 == 2) {
                i2 = class46.objectWidth;
                j2 = class46.objectHeight;
            } else {
                i2 = class46.objectHeight;
                j2 = class46.objectWidth;
            }
            int k2 = class46.anInt768;
            if (l1 != 0) {
                k2 = (k2 << l1 & 0xf) + (k2 >> 4 - l1);
            }
            method85(2, 0, j2, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], i2, k2, j, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, k);
        } else {
            method85(2, l1, 0, -11308, k1 + 1, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 0, 0, j, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, k);
        }
        anInt914 = super.saveClickX;
        anInt915 = super.saveClickY;
        crossType = 2;
        crossIndex = 0;
        return true;
    }

    private StreamLoader streamLoaderForName(int i, String s, String s1, int j, byte byte0, int k) {
        byte abyte0[] = null;
        int l = 5;
        try {
            if (aClass14Array970[0] != null) { //decompressors
                abyte0 = aClass14Array970[0].decompress(true, i);
            }
            if (abyte0 == null) {
                drawLoadingText(15, "Downloading Cache");
            }
        } catch (Exception exception) {
        }
        if (abyte0 != null);
        if (abyte0 != null) {
            StreamLoader class44 = new StreamLoader(abyte0);
            return class44;
        }
        int i1 = 0;
        do {
            if (abyte0 != null) {
                break;
            }
            String s2 = "Unknown error";
            drawLoadingText(k, "Client updated - please reload client");
            Object obj = null;
            try {
                int j1 = 0;
                DataInputStream datainputstream = method132((new StringBuilder()).append(s1).append(j).toString());
                byte abyte1[] = new byte[6];
                datainputstream.readFully(abyte1, 0, 6);
                Stream class30_sub2_sub2 = new Stream(abyte1);
                class30_sub2_sub2.currentOffset = 3;
                int l1 = class30_sub2_sub2.read3Bytes() + 6;
                int i2 = 6;
                abyte0 = new byte[l1];
                for (int j2 = 0; j2 < 6; j2++) {
                    abyte0[j2] = abyte1[j2];
                }
                while (i2 < l1) {
                    int k2 = l1 - i2;
                    if (k2 > 1000) {
                        k2 = 1000;
                    }
                    int l2 = datainputstream.read(abyte0, i2, k2);
                    if (l2 < 0) {
                        s2 = (new StringBuilder()).append("Length error: ").append(i2).append("/").append(l1).toString();
                        throw new IOException("EOF");
                    }
                    i2 += l2;
                    int i3 = (i2 * 100) / l1;
                    if (i3 != j1) {
                        drawLoadingText(k, (new StringBuilder()).append("Loading ").append(s).append(" - ").append(i3).append("%").toString());
                    }
                    j1 = i3;
                }
                datainputstream.close();
                try {
                    if (aClass14Array970[0] != null) {
                        aClass14Array970[0].method234(abyte0.length, abyte0, (byte) 2, i);
                    }
                } catch (Exception exception3) {
                    aClass14Array970[0] = null;
                }
            } catch (IOException ioexception) {
                if (s2.equals("Unknown error")) {
                    s2 = "Connection error";
                }
                abyte0 = null;
            } catch (NullPointerException nullpointerexception) {
                s2 = "Null error";
                abyte0 = null;
                if (!signlink.reporterror) {
                    return null;
                }
            } catch (ArrayIndexOutOfBoundsException arrayindexoutofboundsexception) {
                s2 = "Bounds error";
                abyte0 = null;
                if (!signlink.reporterror) {
                    return null;
                }
            } catch (Exception exception1) {
                s2 = "Unexpected error";
                abyte0 = null;
                if (!signlink.reporterror) {
                    return null;
                }
            }
            if (abyte0 == null) {
                for (int k1 = l; k1 > 0; k1--) {
                    if (i1 >= 3) {
                        drawLoadingText(k, "Game updated - please reload page");
                        k1 = 10;
                    } else {
                        drawLoadingText(k, (new StringBuilder()).append(s2).append(" - Retrying in ").append(k1).toString());
                    }
                    try {
                        Thread.sleep(1000L);
                    } catch (Exception exception2) {
                    }
                }

                l *= 2;
                if (l > 60) {
                    l = 60;
                }
                aBoolean872 = !aBoolean872;
            }
        } while (true);
        StreamLoader class44_1 = new StreamLoader(abyte0);
        if (byte0 != -41) {
            throw new NullPointerException();
        } else {
            return class44_1;
        }
    }

    public final void dropClient() {
        if (anInt1011 > 0) {
            resetLogout();
            return;
        }
        aClass15_1165.method237(0);
        DrawingArea.fillPixels(1, 237, 36, 0xffffff, 2);
        DrawingArea.method336(34, 3, 2, 0, 235);
        regularFont.drawText(0, "Connection lost.", 19, 120);
        regularFont.drawText(0xffffff, "Connection lost.", 18, 119);
        regularFont.drawText(0, "Please wait - attempting to reestablish.", 31, 120);
        regularFont.drawText(0xffffff, "Please wait - attempting to reestablish.", 30, 119);
        aClass15_1165.drawGraphics(4, 23680, super.aGraphics12, 4);
        minimapState = 0;
        destX = 0;
        RSSocket class24 = aClass24_1168;
        loggedIn = false;
        loginFailures = 0;
        login(myUsername, myPassword, true);
        if (!loggedIn) {
            resetLogout();
        }
        try {
            class24.close();
            return;
        } catch (Exception _ex) {
            return;
        }
    }

    public final void doAction(int i) { //doAction
        if (i < 0) // if no action..
        {
            return;
        }
        if (inputDialogState != 0) {
            inputDialogState = 0;
            inputTaken = true;
        }
        int j = menuActionCmd2[i];
        int k = menuActionCmd3[i];
        int l = menuActionID[i];
        int i1 = menuActionCmd1[i];
        if (l >= 2000) {
            l -= 2000;
        }





        if (l == 1450) { //View all
            if (clickedTab[0] == false) {
                clickedTab[0] = true;
            }
            for (int a = 0; a < clickedTab.length; a++) {
                if (a != 0) {
                    clickedTab[a] = false;
                }
            }
            for (int t = 0; t < flashRequest.length; t++) {
                flashRequest[t] = false;
            }
            for (int t1 = 0; t1 < newTextDrawn.length; t1++) {
                newTextDrawn[t1] = false;
            }
            for (int t2 = 0; t2 < 6; t2++) {
                pauseFlash[t2] = false;
                flashTimes[t2] = 4;
            }
            needChatAreaRedraw = true;
            inputTaken = true;
        }

        if (l == 1451) { //View game
            if (clickedTab[1] == false) {
                clickedTab[1] = true;
            }
            for (int a = 0; a < clickedTab.length; a++) {
                if (a != 1) {
                    clickedTab[a] = false;
                }
            }
            newTextDrawn[0] = false;
            pauseFlash[0] = false;
            flashTimes[0] = 4;
            flashRequest[0] = false;
            needChatAreaRedraw = true;
            inputTaken = true;
        }

        if (l == 1490) { //On Game
            gameMode = 0;
            needChatAreaRedraw = true;
            inputTaken = true;
            aClass30_Sub2_Sub2_1192.createFrame(95);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(publicChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(privateChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(tradeMode);
        }

        if (l == 1491) { //Filter Game
            gameMode = 1;
            needChatAreaRedraw = true;
            inputTaken = true;
            aClass30_Sub2_Sub2_1192.createFrame(95);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(publicChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(privateChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(tradeMode);
        }

        if (l == 1452) { //View public
            if (clickedTab[2] == false) {
                clickedTab[2] = true;
            }
            for (int a = 0; a < clickedTab.length; a++) {
                if (a != 2) {
                    clickedTab[a] = false;
                }
            }
            newTextDrawn[1] = false;
            flashRequest[1] = false;
            pauseFlash[1] = false;
            flashTimes[1] = 4;
            needChatAreaRedraw = true;
            inputTaken = true;
        }

        if (l == 1453) { //On public
            publicChatMode = 0;
            needChatAreaRedraw = true;
            inputTaken = true;
            aClass30_Sub2_Sub2_1192.createFrame(95);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(publicChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(privateChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(tradeMode);
        }

        if (l == 1454) { //Friends public
            publicChatMode = 1;
            needChatAreaRedraw = true;
            inputTaken = true;
            aClass30_Sub2_Sub2_1192.createFrame(95);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(publicChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(privateChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(tradeMode);
        }

        if (l == 1455) { //Off public
            publicChatMode = 2;
            needChatAreaRedraw = true;
            inputTaken = true;
            aClass30_Sub2_Sub2_1192.createFrame(95);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(publicChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(privateChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(tradeMode);
        }

        if (l == 1456) { //Hide public
            publicChatMode = 3;
            needChatAreaRedraw = true;
            inputTaken = true;
            aClass30_Sub2_Sub2_1192.createFrame(95);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(publicChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(privateChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(tradeMode);
        }

        if (l == 1457) { //View Private
            if (clickedTab[3] == false) {
                clickedTab[3] = true;
            }
            for (int a = 0; a < clickedTab.length; a++) {
                if (a != 3) {
                    clickedTab[a] = false;
                }
            }
            pauseFlash[2] = false;
            flashTimes[2] = 4;
            newTextDrawn[2] = false;
            flashRequest[2] = false;
            needChatAreaRedraw = true;
            inputTaken = true;
        }

        if (l == 1458) { //On Private
            privateChatMode = 0;
            needChatAreaRedraw = true;
            inputTaken = true;
            aClass30_Sub2_Sub2_1192.createFrame(95);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(publicChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(privateChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(tradeMode);
        }

        if (l == 1459) { //Friends Private
            privateChatMode = 1;
            needChatAreaRedraw = true;
            inputTaken = true;
            aClass30_Sub2_Sub2_1192.createFrame(95);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(publicChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(privateChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(tradeMode);
        }

        if (l == 1460) { //Off Private
            privateChatMode = 2;
            needChatAreaRedraw = true;
            inputTaken = true;
            aClass30_Sub2_Sub2_1192.createFrame(95);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(publicChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(privateChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(tradeMode);
        }

        if (l == 1461) { //View Clan
            if (clickedTab[4] == false) {
                clickedTab[4] = true;
            }
            for (int a = 0; a < clickedTab.length; a++) {
                if (a != 4) {
                    clickedTab[a] = false;
                }
            }
            pauseFlash[3] = false;
            flashTimes[3] = 4;
            newTextDrawn[3] = false;
            flashRequest[3] = false;
            needChatAreaRedraw = true;
            inputTaken = true;
        }

        if (l == 1462) { //On Clan
            ClanButton = 0;
            needChatAreaRedraw = true;
            inputTaken = true;
            aClass30_Sub2_Sub2_1192.createFrame(95);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(publicChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(privateChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(tradeMode);
        }

        if (l == 1463) { //Friends Clan
            ClanButton = 1;
            needChatAreaRedraw = true;
            inputTaken = true;
            aClass30_Sub2_Sub2_1192.createFrame(95);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(publicChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(privateChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(tradeMode);
        }

        if (l == 1464) { //Off Clan
            ClanButton = 2;
            needChatAreaRedraw = true;
            inputTaken = true;
            aClass30_Sub2_Sub2_1192.createFrame(95);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(publicChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(privateChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(tradeMode);
        }

        if (l == 1465) { //View trade
            if (clickedTab[5] == false) {
                clickedTab[5] = true;
            }
            for (int a = 0; a < clickedTab.length; a++) {
                if (a != 5) {
                    clickedTab[a] = false;
                }
            }
            pauseFlash[4] = false;
            flashTimes[4] = 4;
            newTextDrawn[4] = false;
            flashRequest[4] = false;
            needChatAreaRedraw = true;
            inputTaken = true;
        }

        if (l == 1466) { //On trade
            tradeMode = 0;
            needChatAreaRedraw = true;
            inputTaken = true;
            aClass30_Sub2_Sub2_1192.createFrame(95);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(publicChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(privateChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(tradeMode);
        }

        if (l == 1467) { //Friends trade
            tradeMode = 1;
            needChatAreaRedraw = true;
            inputTaken = true;
            aClass30_Sub2_Sub2_1192.createFrame(95);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(publicChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(privateChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(tradeMode);
        }

        if (l == 1468) { //Off trade
            tradeMode = 2;
            needChatAreaRedraw = true;
            inputTaken = true;
            aClass30_Sub2_Sub2_1192.createFrame(95);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(publicChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(privateChatMode);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(tradeMode);
        }

        if (l == 1469) { //Toggle HP
            if (hp == true) {
                hp = false;
            } else {
                hp = true;
            }
        }

        if (l == 1470) { //On HP
            hp = true;
        }

        if (l == 1472) { //Off HP
            hp = false;
        }

        if (l == 1510) { //Next Style
            if (HPBarID == 6) {
                HPBarID = 0;
            } else {
                HPBarID++;
            }

            pushMessage("Changed to HP Bar Style: " + HPBarID + ", You can set default in Csettings.java", 0, "", false);

        }

        if (l == 1511) { //Previous Style
            if (HPBarID == 0) {
                HPBarID = 6;
            } else {
                HPBarID--;
            }

            pushMessage("Changed to HP Bar Style: " + HPBarID + ", You can set default in Csettings.java", 0, "", false);
        }



        if (l == 1020) { //world map click
            pushMessage("Opening World Map...", 0, "", false);
            // Hide Main Frame.
            Applet_Sub1.aFrame_Sub1_15.setVisible(false);

            if (mapMain != null) {
                mapMain.rsFrame.setVisible(true);
            } else {
                mapMain = new MapMain();
                mapMain.createFrame(765, 503);
            }
        }




        if (l == 1150) { //face nborth
            anInt1184 = 140;
            anInt1185 = 0;
        }





        if (l == 1151) {
            if (CameraPos1 == 1) {
                pushMessage("You can't zoom in any further", 0, "", false);
            } else if (CameraPos1 > 1) {
                CameraPos1 -= 1;
                CameraPos2 -= 200;
            }
        }

        if (l == 1152) {
            if (CameraPos1 > 6) {
                pushMessage("You can't zoom out any further.", 0, "", false);
            } else if (CameraPos1 < 7) {
                CameraPos1 += 1;
                CameraPos2 += 200;
            }
        }

        if (l == 1153) {
            CameraPos1 = 3;
            CameraPos2 = 600;
        }






        switch (l) {
            case 1001:
                tabID = 0;
                needDrawTabArea = true;
                tabAreaAltered = true;
                break;
            case 1002:
                tabID = 1;
                needDrawTabArea = true;
                tabAreaAltered = true;
                break;
            case 1003:
                tabID = 2;
                needDrawTabArea = true;
                tabAreaAltered = true;
                break;
            case 1004:
                tabID = 3;
                needDrawTabArea = true;
                tabAreaAltered = true;
                break;
            case 1005:
                tabID = 4;
                needDrawTabArea = true;
                tabAreaAltered = true;
                break;
            case 1006:
                tabID = 5;
                needDrawTabArea = true;
                tabAreaAltered = true;
                break;
            case 1007:
                tabID = 6;
                needDrawTabArea = true;
                tabAreaAltered = true;
                break;
            case 1008:
                tabID = 7;
                needDrawTabArea = true;
                tabAreaAltered = true;
                break;
            case 1009:
                tabID = 8;
                needDrawTabArea = true;
                tabAreaAltered = true;
                break;
            case 1010:
                tabID = 9;
                needDrawTabArea = true;
                tabAreaAltered = true;
                break;
            case 1011:
                tabID = 14;
                needDrawTabArea = true;
                tabAreaAltered = true;
                break;
            case 1012:
                tabID = 11;
                needDrawTabArea = true;
                tabAreaAltered = true;
                break;
            case 1013:
                tabID = 12;
                needDrawTabArea = true;
                tabAreaAltered = true;
                break;
            case 1014:
                tabID = 13;
                needDrawTabArea = true;
                tabAreaAltered = true;
                break;
            case 1015:
                tabID = 10;
                needDrawTabArea = true;
                tabAreaAltered = true;
                break;
        }



        if (l == 1500) {
            if (!prayClicked) {
                prayClicked = true;
            } else {
                prayClicked = false;
            }
        }
        if (l == 1550) {
            if (!runClicked) {
                runClicked = true;
                aClass30_Sub2_Sub2_1192.createFrame(185);
                aClass30_Sub2_Sub2_1192.writeWord(153);
            } else {
                runClicked = false;
                aClass30_Sub2_Sub2_1192.createFrame(185);
                aClass30_Sub2_Sub2_1192.writeWord(152);
            }
        }







        if (l == 1050) { //do somehting that isnt added yet :p
            pushMessage("You can't do that! (yet)", 0, "", false);
        }




















        if (l == 20) {
            NPC class30_sub2_sub4_sub1_sub1_1 = npcArray[i1];
            if (class30_sub2_sub4_sub1_sub1_1 != null) {
                method85(2, 0, 1, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 1, 0, ((Entity) (class30_sub2_sub4_sub1_sub1_1)).smallY[0], ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, ((Entity) (class30_sub2_sub4_sub1_sub1_1)).smallX[0]);
                anInt914 = super.saveClickX;
                anInt915 = super.saveClickY;
                crossType = 2;
                crossIndex = 0;
                aClass30_Sub2_Sub2_1192.createFrame(155);
                aClass30_Sub2_Sub2_1192.method431(i1);
            }
        }

        if (l == 27) {
            Player class30_sub2_sub4_sub1_sub2_2 = playerArray[i1];
            if (class30_sub2_sub4_sub1_sub2_2 != null) {
                method85(2, 0, 1, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 1, 0, ((Entity) (class30_sub2_sub4_sub1_sub2_2)).smallY[0], ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, ((Entity) (class30_sub2_sub4_sub1_sub2_2)).smallX[0]);
                anInt914 = super.saveClickX;
                anInt915 = super.saveClickY;
                crossType = 2;
                crossIndex = 0;
                anInt986 += i1;
                if (anInt986 >= 54) {
                    aClass30_Sub2_Sub2_1192.createFrame(189);
                    aClass30_Sub2_Sub2_1192.writeWordBigEndian(234);
                    anInt986 = 0;
                }
                aClass30_Sub2_Sub2_1192.createFrame(73);
                aClass30_Sub2_Sub2_1192.method431(i1);
            }
        }






        if (l == 582) {
            NPC class30_sub2_sub4_sub1_sub1 = npcArray[i1];
            if (class30_sub2_sub4_sub1_sub1 != null) {
                method85(2, 0, 1, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 1, 0, ((Entity) (class30_sub2_sub4_sub1_sub1)).smallY[0], ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, ((Entity) (class30_sub2_sub4_sub1_sub1)).smallX[0]);
                anInt914 = super.saveClickX;
                anInt915 = super.saveClickY;
                crossType = 2;
                crossIndex = 0;
                aClass30_Sub2_Sub2_1192.createFrame(57);
                aClass30_Sub2_Sub2_1192.method432(anInt1285);
                aClass30_Sub2_Sub2_1192.method432(i1);
                aClass30_Sub2_Sub2_1192.method431(anInt1283);
                aClass30_Sub2_Sub2_1192.method432(anInt1284);
            }
        }

        if (l == 234) {
            boolean flag1 = method85(2, 0, 0, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 0, 0, k, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, j);
            if (!flag1) {
                flag1 = method85(2, 0, 1, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 1, 0, k, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, j);
            }
            anInt914 = super.saveClickX;
            anInt915 = super.saveClickY;
            crossType = 2;
            crossIndex = 0;
            aClass30_Sub2_Sub2_1192.createFrame(236);
            aClass30_Sub2_Sub2_1192.method431(k + baseY);
            aClass30_Sub2_Sub2_1192.writeWord(i1);
            aClass30_Sub2_Sub2_1192.method431(j + baseX);
        }

        if (l == 62 && method66(i1, k, j, -770)) {
            aClass30_Sub2_Sub2_1192.createFrame(192);
            aClass30_Sub2_Sub2_1192.writeWord(anInt1284);
            aClass30_Sub2_Sub2_1192.method431(i1 >> 14 & 0x7fff);
            aClass30_Sub2_Sub2_1192.method433(k + baseY);
            aClass30_Sub2_Sub2_1192.method431(anInt1283);
            aClass30_Sub2_Sub2_1192.method433(j + baseX);
            aClass30_Sub2_Sub2_1192.writeWord(anInt1285);
        }

        if (l == 511) {
            boolean flag2 = method85(2, 0, 0, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 0, 0, k, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, j);
            if (!flag2) {
                flag2 = method85(2, 0, 1, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 1, 0, k, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, j);
            }
            anInt914 = super.saveClickX;
            anInt915 = super.saveClickY;
            crossType = 2;
            crossIndex = 0;
            aClass30_Sub2_Sub2_1192.createFrame(25);
            aClass30_Sub2_Sub2_1192.method431(anInt1284);
            aClass30_Sub2_Sub2_1192.method432(anInt1285);
            aClass30_Sub2_Sub2_1192.writeWord(i1);
            aClass30_Sub2_Sub2_1192.method432(k + baseY);
            aClass30_Sub2_Sub2_1192.method433(anInt1283);
            aClass30_Sub2_Sub2_1192.writeWord(j + baseX);
        }

        if (l == 74) {
            aClass30_Sub2_Sub2_1192.createFrame(122);
            aClass30_Sub2_Sub2_1192.method433(k);
            aClass30_Sub2_Sub2_1192.method432(j);
            aClass30_Sub2_Sub2_1192.method431(i1);
            atInventoryLoopCycle = 0;
            anInt1244 = k;
            anInt1245 = j;
            atInventoryInterfaceType = 2;
            if (RSInterface.interfaceCache[k].parentID == openInterfaceID) {
                atInventoryInterfaceType = 1;
            }
            if (RSInterface.interfaceCache[k].parentID == backDialogID) {
                atInventoryInterfaceType = 3;
            }
        }

        if (l == 315) {
            RSInterface class9 = RSInterface.interfaceCache[k];
            boolean flag8 = true;
            if (class9.anInt214 > 0) {
                flag8 = method48(505, class9);
            }
            if (flag8) {
                aClass30_Sub2_Sub2_1192.createFrame(185);
                aClass30_Sub2_Sub2_1192.writeWord(k);
            }
        }

        if (l == 561) {
            Player class30_sub2_sub4_sub1_sub2 = playerArray[i1];
            if (class30_sub2_sub4_sub1_sub2 != null) {
                method85(2, 0, 1, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 1, 0, ((Entity) (class30_sub2_sub4_sub1_sub2)).smallY[0], ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, ((Entity) (class30_sub2_sub4_sub1_sub2)).smallX[0]);
                anInt914 = super.saveClickX;
                anInt915 = super.saveClickY;
                crossType = 2;
                crossIndex = 0;
                anInt1188 += i1;
                if (anInt1188 >= 90) {
                    aClass30_Sub2_Sub2_1192.createFrame(136);
                    anInt1188 = 0;
                }
                aClass30_Sub2_Sub2_1192.createFrame(128);
                aClass30_Sub2_Sub2_1192.writeWord(i1);
            }
        }



        if (l == 779) {
            Player class30_sub2_sub4_sub1_sub2_1 = playerArray[i1];
            if (class30_sub2_sub4_sub1_sub2_1 != null) {
                method85(2, 0, 1, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 1, 0, ((Entity) (class30_sub2_sub4_sub1_sub2_1)).smallY[0], ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, ((Entity) (class30_sub2_sub4_sub1_sub2_1)).smallX[0]);
                anInt914 = super.saveClickX;
                anInt915 = super.saveClickY;
                crossType = 2;
                crossIndex = 0;
                aClass30_Sub2_Sub2_1192.createFrame(153);
                aClass30_Sub2_Sub2_1192.method431(i1);
                System.out.println("run");
            }
        }

        if (l == 516) {
            if (!menuOpen) {
                aClass25_946.method312(false, super.saveClickY - 4, super.saveClickX - 4);
            } else {
                aClass25_946.method312(false, k - 4, j - 4);
            }
        }

        if (l == 1062) {
            anInt924 += baseX;
            if (anInt924 >= 113) {
                aClass30_Sub2_Sub2_1192.createFrame(183);
                aClass30_Sub2_Sub2_1192.writeDWordBigEndian(0xe63271);
                anInt924 = 0;
            }
            method66(i1, k, j, -770);
            aClass30_Sub2_Sub2_1192.createFrame(228);
            aClass30_Sub2_Sub2_1192.method432(i1 >> 14 & 0x7fff);
            aClass30_Sub2_Sub2_1192.method432(k + baseY);
            aClass30_Sub2_Sub2_1192.writeWord(j + baseX);
        }

        if (l == 679 && !isInterfaceOpen) {
            aClass30_Sub2_Sub2_1192.createFrame(40);
            aClass30_Sub2_Sub2_1192.writeWord(k);
            isInterfaceOpen = true;
        }

        if (l == 431) {
            aClass30_Sub2_Sub2_1192.createFrame(129);
            aClass30_Sub2_Sub2_1192.method432(j);
            aClass30_Sub2_Sub2_1192.writeWord(k);
            aClass30_Sub2_Sub2_1192.method432(i1);
            atInventoryLoopCycle = 0;
            anInt1244 = k;
            anInt1245 = j;
            atInventoryInterfaceType = 2;
            if (RSInterface.interfaceCache[k].parentID == openInterfaceID) {
                atInventoryInterfaceType = 1;
            }
            if (RSInterface.interfaceCache[k].parentID == backDialogID) {
                atInventoryInterfaceType = 3;
            }
        }

        if (l == 337 || l == 42 || l == 792 || l == 322) { //add friend & add ignore
            String s = menuActionName[i];
            int k1 = s.indexOf("@lre@");
            if (k1 != -1) {
                long l3 = TextClass.method583(s.substring(k1 + 5).trim());
                if (l == 337) {
                    method41((byte) 68, l3);
                }
                if (l == 42) {
                    method113(l3, 4);
                }
                if (l == 792) {
                    delFriend(l3);
                }
                if (l == 322) {
                    delIgnore(l3);
                }
            }
        }

        if (l == 53) {
            aClass30_Sub2_Sub2_1192.createFrame(135);
            aClass30_Sub2_Sub2_1192.method431(j);
            aClass30_Sub2_Sub2_1192.method432(k);
            aClass30_Sub2_Sub2_1192.method431(i1);
            atInventoryLoopCycle = 0;
            anInt1244 = k;
            anInt1245 = j;
            atInventoryInterfaceType = 2;
            if (RSInterface.interfaceCache[k].parentID == openInterfaceID) {
                atInventoryInterfaceType = 1;
            }
            if (RSInterface.interfaceCache[k].parentID == backDialogID) {
                atInventoryInterfaceType = 3;
            }
        }

        if (l == 539) {
            aClass30_Sub2_Sub2_1192.createFrame(16);
            aClass30_Sub2_Sub2_1192.method432(i1);
            aClass30_Sub2_Sub2_1192.method433(j);
            aClass30_Sub2_Sub2_1192.method433(k);
            atInventoryLoopCycle = 0;
            anInt1244 = k;
            anInt1245 = j;
            atInventoryInterfaceType = 2;
            if (RSInterface.interfaceCache[k].parentID == openInterfaceID) {
                atInventoryInterfaceType = 1;
            }
            if (RSInterface.interfaceCache[k].parentID == backDialogID) {
                atInventoryInterfaceType = 3;
            }
        }

        if (l == 484 || l == 6) {
            String s1 = menuActionName[i];
            int l1 = s1.indexOf("@whi@");
            if (l1 != -1) {
                s1 = s1.substring(l1 + 5).trim();
                String s7 = TextClass.fixName(TextClass.longForName(TextClass.method583(s1)));
                boolean flag9 = false;
                for (int j3 = 0; j3 < anInt891; j3++) {
                    Player class30_sub2_sub4_sub1_sub2_7 = playerArray[anIntArray892[j3]];
                    if (class30_sub2_sub4_sub1_sub2_7 == null || class30_sub2_sub4_sub1_sub2_7.name == null || !class30_sub2_sub4_sub1_sub2_7.name.equalsIgnoreCase(s7)) {
                        continue;
                    }
                    method85(2, 0, 1, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 1, 0, ((Entity) (class30_sub2_sub4_sub1_sub2_7)).smallY[0], ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, ((Entity) (class30_sub2_sub4_sub1_sub2_7)).smallX[0]);
                    if (l == 484) {
                        aClass30_Sub2_Sub2_1192.createFrame(139);
                        aClass30_Sub2_Sub2_1192.method431(anIntArray892[j3]);
                    }
                    if (l == 6) {
                        anInt1188 += i1;
                        if (anInt1188 >= 90) {
                            aClass30_Sub2_Sub2_1192.createFrame(136);
                            anInt1188 = 0;
                        }
                        aClass30_Sub2_Sub2_1192.createFrame(128);
                        aClass30_Sub2_Sub2_1192.writeWord(anIntArray892[j3]);
                    }
                    flag9 = true;
                    break;
                }
                if (!flag9) {
                    pushMessage("Unable to find " + s7, 0, "", aBoolean991);
                }
            }
        }

        if (l == 870) {
            aClass30_Sub2_Sub2_1192.createFrame(53);
            aClass30_Sub2_Sub2_1192.writeWord(j);
            aClass30_Sub2_Sub2_1192.method432(anInt1283);
            aClass30_Sub2_Sub2_1192.method433(i1);
            aClass30_Sub2_Sub2_1192.writeWord(anInt1284);
            aClass30_Sub2_Sub2_1192.method431(anInt1285);
            aClass30_Sub2_Sub2_1192.writeWord(k);
            atInventoryLoopCycle = 0;
            anInt1244 = k;
            anInt1245 = j;
            atInventoryInterfaceType = 2;
            if (RSInterface.interfaceCache[k].parentID == openInterfaceID) {
                atInventoryInterfaceType = 1;
            }
            if (RSInterface.interfaceCache[k].parentID == backDialogID) {
                atInventoryInterfaceType = 3;
            }
        }

        if (l == 847) { // drop item
            aClass30_Sub2_Sub2_1192.createFrame(87);
            aClass30_Sub2_Sub2_1192.method432(i1);
            aClass30_Sub2_Sub2_1192.writeWord(k);
            aClass30_Sub2_Sub2_1192.method432(j);
            atInventoryLoopCycle = 0;
            anInt1244 = k;
            anInt1245 = j;
            atInventoryInterfaceType = 2;
            if (RSInterface.interfaceCache[k].parentID == openInterfaceID) {
                atInventoryInterfaceType = 1;
            }
            if (RSInterface.interfaceCache[k].parentID == backDialogID) {
                atInventoryInterfaceType = 3;
            }
        }

        if (l == 626) {
            RSInterface class9_1 = RSInterface.interfaceCache[k];
            anInt1136 = 1;
            spellID = class9_1.id;
            anInt1137 = k;
            anInt1138 = class9_1.spellUsableOn;
            anInt1282 = 0;
            needDrawTabArea = true;
            String s4 = class9_1.selectedActionName;
            if (s4.indexOf(" ") != -1) {
                s4 = s4.substring(0, s4.indexOf(" "));
            }
            String s8 = class9_1.selectedActionName;
            if (s8.indexOf(" ") != -1) {
                s8 = s8.substring(s8.indexOf(" ") + 1);
            }
            aString1139 = s4 + " " + class9_1.spellName + " " + s8;
            if (anInt1138 == 16) {
                needDrawTabArea = true;
                tabID = 3;
                tabAreaAltered = true;
            }
            return;
        }

        if (l == 78) {
            aClass30_Sub2_Sub2_1192.createFrame(117);
            aClass30_Sub2_Sub2_1192.method433(k);
            aClass30_Sub2_Sub2_1192.method433(i1);
            aClass30_Sub2_Sub2_1192.method431(j);
            atInventoryLoopCycle = 0;
            anInt1244 = k;
            anInt1245 = j;
            atInventoryInterfaceType = 2;
            if (RSInterface.interfaceCache[k].parentID == openInterfaceID) {
                atInventoryInterfaceType = 1;
            }
            if (RSInterface.interfaceCache[k].parentID == backDialogID) {
                atInventoryInterfaceType = 3;
            }
        }

        if (l == 213) {
            boolean flag3 = method85(2, 0, 0, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 0, 0, k, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, j);
            if (!flag3) {
                flag3 = method85(2, 0, 1, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 1, 0, k, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, j);
            }
            anInt914 = super.saveClickX;
            anInt915 = super.saveClickY;
            crossType = 2;
            crossIndex = 0;
            aClass30_Sub2_Sub2_1192.createFrame(79);
            aClass30_Sub2_Sub2_1192.method431(k + baseY);
            aClass30_Sub2_Sub2_1192.writeWord(i1);
            aClass30_Sub2_Sub2_1192.method432(j + baseX);
        }
        if (l == 632) {
            aClass30_Sub2_Sub2_1192.createFrame(145);
            aClass30_Sub2_Sub2_1192.method432(k);
            aClass30_Sub2_Sub2_1192.method432(j);
            aClass30_Sub2_Sub2_1192.method432(i1);
            atInventoryLoopCycle = 0;
            anInt1244 = k;
            anInt1245 = j;
            atInventoryInterfaceType = 2;
            if (RSInterface.interfaceCache[k].parentID == openInterfaceID) {
                atInventoryInterfaceType = 1;
            }
            if (RSInterface.interfaceCache[k].parentID == backDialogID) {
                atInventoryInterfaceType = 3;
            }
        }
        if (l == 493) {
            aClass30_Sub2_Sub2_1192.createFrame(75);
            aClass30_Sub2_Sub2_1192.method433(k);
            aClass30_Sub2_Sub2_1192.method431(j);
            aClass30_Sub2_Sub2_1192.method432(i1);
            atInventoryLoopCycle = 0;
            anInt1244 = k;
            anInt1245 = j;
            atInventoryInterfaceType = 2;
            if (RSInterface.interfaceCache[k].parentID == openInterfaceID) {
                atInventoryInterfaceType = 1;
            }
            if (RSInterface.interfaceCache[k].parentID == backDialogID) {
                atInventoryInterfaceType = 3;
            }
        }
        if (l == 652) {
            boolean flag4 = method85(2, 0, 0, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 0, 0, k, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, j);
            if (!flag4) {
                flag4 = method85(2, 0, 1, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 1, 0, k, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, j);
            }
            anInt914 = super.saveClickX;
            anInt915 = super.saveClickY;
            crossType = 2;
            crossIndex = 0;
            aClass30_Sub2_Sub2_1192.createFrame(156);
            aClass30_Sub2_Sub2_1192.method432(j + baseX);
            aClass30_Sub2_Sub2_1192.method431(k + baseY);
            aClass30_Sub2_Sub2_1192.method433(i1);
        }
        if (l == 94) {
            boolean flag5 = method85(2, 0, 0, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 0, 0, k, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, j);
            if (!flag5) {
                flag5 = method85(2, 0, 1, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 1, 0, k, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, j);
            }
            anInt914 = super.saveClickX;
            anInt915 = super.saveClickY;
            crossType = 2;
            crossIndex = 0;
            aClass30_Sub2_Sub2_1192.createFrame(181);
            aClass30_Sub2_Sub2_1192.method431(k + baseY);
            aClass30_Sub2_Sub2_1192.writeWord(i1);
            aClass30_Sub2_Sub2_1192.method431(j + baseX);
            aClass30_Sub2_Sub2_1192.method432(anInt1137);
        }
        if (l == 646) {
            aClass30_Sub2_Sub2_1192.createFrame(185);
            aClass30_Sub2_Sub2_1192.writeWord(k);
            RSInterface class9_2 = RSInterface.interfaceCache[k];
            if (class9_2.valueIndexArray != null && class9_2.valueIndexArray[0][0] == 5) {
                int i2 = class9_2.valueIndexArray[0][1];
                if (variousSettings[i2] != class9_2.anIntArray212[0]) {
                    variousSettings[i2] = class9_2.anIntArray212[0];
                    method33(i2);
                    needDrawTabArea = true;
                }
            }
        }
        if (l == 225) {
            NPC class30_sub2_sub4_sub1_sub1_2 = npcArray[i1];
            if (class30_sub2_sub4_sub1_sub1_2 != null) {
                method85(2, 0, 1, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 1, 0, ((Entity) (class30_sub2_sub4_sub1_sub1_2)).smallY[0], ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, ((Entity) (class30_sub2_sub4_sub1_sub1_2)).smallX[0]);
                anInt914 = super.saveClickX;
                anInt915 = super.saveClickY;
                crossType = 2;
                crossIndex = 0;
                anInt1226 += i1;
                System.out.println(i1);
                if (anInt1226 >= 85) {
                    aClass30_Sub2_Sub2_1192.createFrame(230);
                    aClass30_Sub2_Sub2_1192.writeWordBigEndian(239);
                    anInt1226 = 0;
                }
                aClass30_Sub2_Sub2_1192.createFrame(17);
                aClass30_Sub2_Sub2_1192.method433(i1);
            }
        }
        if (l == 965) {
            NPC class30_sub2_sub4_sub1_sub1_3 = npcArray[i1];
            if (class30_sub2_sub4_sub1_sub1_3 != null) {
                method85(2, 0, 1, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 1, 0, ((Entity) (class30_sub2_sub4_sub1_sub1_3)).smallY[0], ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, ((Entity) (class30_sub2_sub4_sub1_sub1_3)).smallX[0]);
                anInt914 = super.saveClickX;
                anInt915 = super.saveClickY;
                crossType = 2;
                crossIndex = 0;
                anInt1134++;
                if (anInt1134 >= 96) {
                    aClass30_Sub2_Sub2_1192.createFrame(152);
                    aClass30_Sub2_Sub2_1192.writeWordBigEndian(88);
                    anInt1134 = 0;
                }
                aClass30_Sub2_Sub2_1192.createFrame(21);
                aClass30_Sub2_Sub2_1192.writeWord(i1);
            }
        }
        if (l == 413) {
            NPC class30_sub2_sub4_sub1_sub1_4 = npcArray[i1];
            if (class30_sub2_sub4_sub1_sub1_4 != null) {
                method85(2, 0, 1, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 1, 0, ((Entity) (class30_sub2_sub4_sub1_sub1_4)).smallY[0], ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, ((Entity) (class30_sub2_sub4_sub1_sub1_4)).smallX[0]);
                anInt914 = super.saveClickX;
                anInt915 = super.saveClickY;
                crossType = 2;
                crossIndex = 0;
                aClass30_Sub2_Sub2_1192.createFrame(131);
                aClass30_Sub2_Sub2_1192.method433(i1);
                aClass30_Sub2_Sub2_1192.method432(anInt1137);
            }
        }
        if (l == 200) {
            clearTopInterfaces();
        }
        if (l == 1025) { //examine?
            NPC class30_sub2_sub4_sub1_sub1_5 = npcArray[i1];
            if (class30_sub2_sub4_sub1_sub1_5 != null) {
                EntityDef class5 = class30_sub2_sub4_sub1_sub1_5.desc;
                if (class5.childrenIDs != null) {
                    class5 = class5.method161(anInt877);
                }
                if (class5 != null) {
                    String s9;
                    if (class5.description != null) {
                        s9 = new String(class5.description);
                    } else {
                        s9 = "It's a " + class5.name + ".";
                    }
                    pushMessage(s9, 0, "", aBoolean991);
                }
            }
        }
        if (l == 900) {
            method66(i1, k, j, -770);
            aClass30_Sub2_Sub2_1192.createFrame(252);
            aClass30_Sub2_Sub2_1192.method433(i1 >> 14 & 0x7fff);
            aClass30_Sub2_Sub2_1192.method431(k + baseY);
            aClass30_Sub2_Sub2_1192.method432(j + baseX);
        }
        if (l == 412) {
            NPC class30_sub2_sub4_sub1_sub1_6 = npcArray[i1];
            if (class30_sub2_sub4_sub1_sub1_6 != null) {
                method85(2, 0, 1, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 1, 0, ((Entity) (class30_sub2_sub4_sub1_sub1_6)).smallY[0], ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, ((Entity) (class30_sub2_sub4_sub1_sub1_6)).smallX[0]);
                anInt914 = super.saveClickX;
                anInt915 = super.saveClickY;
                crossType = 2;
                crossIndex = 0;
                aClass30_Sub2_Sub2_1192.createFrame(72);
                aClass30_Sub2_Sub2_1192.method432(i1);
            }
        }
        if (l == 365) {
            Player class30_sub2_sub4_sub1_sub2_3 = playerArray[i1];
            if (class30_sub2_sub4_sub1_sub2_3 != null) {
                method85(2, 0, 1, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 1, 0, ((Entity) (class30_sub2_sub4_sub1_sub2_3)).smallY[0], ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, ((Entity) (class30_sub2_sub4_sub1_sub2_3)).smallX[0]);
                anInt914 = super.saveClickX;
                anInt915 = super.saveClickY;
                crossType = 2;
                crossIndex = 0;
                aClass30_Sub2_Sub2_1192.createFrame(249);
                aClass30_Sub2_Sub2_1192.method432(i1);
                aClass30_Sub2_Sub2_1192.method431(anInt1137);
            }
        }
        if (l == 729) {
            Player class30_sub2_sub4_sub1_sub2_4 = playerArray[i1];
            if (class30_sub2_sub4_sub1_sub2_4 != null) {
                method85(2, 0, 1, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 1, 0, ((Entity) (class30_sub2_sub4_sub1_sub2_4)).smallY[0], ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, ((Entity) (class30_sub2_sub4_sub1_sub2_4)).smallX[0]);
                anInt914 = super.saveClickX;
                anInt915 = super.saveClickY;
                crossType = 2;
                crossIndex = 0;
                aClass30_Sub2_Sub2_1192.createFrame(39);
                aClass30_Sub2_Sub2_1192.method431(i1);
            }
        }
        if (l == 577) {
            Player class30_sub2_sub4_sub1_sub2_5 = playerArray[i1];
            if (class30_sub2_sub4_sub1_sub2_5 != null) {
                method85(2, 0, 1, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 1, 0, ((Entity) (class30_sub2_sub4_sub1_sub2_5)).smallY[0], ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, ((Entity) (class30_sub2_sub4_sub1_sub2_5)).smallX[0]);
                anInt914 = super.saveClickX;
                anInt915 = super.saveClickY;
                crossType = 2;
                crossIndex = 0;
                aClass30_Sub2_Sub2_1192.createFrame(139);
                aClass30_Sub2_Sub2_1192.method431(i1);
            }
        }
        if (l == 956 && method66(i1, k, j, -770)) {
            aClass30_Sub2_Sub2_1192.createFrame(35);
            aClass30_Sub2_Sub2_1192.method431(j + baseX);
            aClass30_Sub2_Sub2_1192.method432(anInt1137);
            aClass30_Sub2_Sub2_1192.method432(k + baseY);
            aClass30_Sub2_Sub2_1192.method431(i1 >> 14 & 0x7fff);
        }
        if (l == 567) {
            boolean flag6 = method85(2, 0, 0, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 0, 0, k, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, j);
            if (!flag6) {
                flag6 = method85(2, 0, 1, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 1, 0, k, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, j);
            }
            anInt914 = super.saveClickX;
            anInt915 = super.saveClickY;
            crossType = 2;
            crossIndex = 0;
            aClass30_Sub2_Sub2_1192.createFrame(23);
            aClass30_Sub2_Sub2_1192.method431(k + baseY);
            aClass30_Sub2_Sub2_1192.method431(i1);
            aClass30_Sub2_Sub2_1192.method431(j + baseX);
        }
        if (l == 867) {
            if ((i1 & 3) == 0) {
                anInt1175++;
            }
            if (anInt1175 >= 59) {
                aClass30_Sub2_Sub2_1192.createFrame(200);
                aClass30_Sub2_Sub2_1192.writeWord(25501);
                anInt1175 = 0;
            }
            aClass30_Sub2_Sub2_1192.createFrame(43);
            aClass30_Sub2_Sub2_1192.method431(k);
            aClass30_Sub2_Sub2_1192.method432(i1);
            aClass30_Sub2_Sub2_1192.method432(j);
            atInventoryLoopCycle = 0;
            anInt1244 = k;
            anInt1245 = j;
            atInventoryInterfaceType = 2;
            if (RSInterface.interfaceCache[k].parentID == openInterfaceID) {
                atInventoryInterfaceType = 1;
            }
            if (RSInterface.interfaceCache[k].parentID == backDialogID) {
                atInventoryInterfaceType = 3;
            }
        }
        if (l == 543) {
            aClass30_Sub2_Sub2_1192.createFrame(237);
            aClass30_Sub2_Sub2_1192.writeWord(j);
            aClass30_Sub2_Sub2_1192.method432(i1);
            aClass30_Sub2_Sub2_1192.writeWord(k);
            aClass30_Sub2_Sub2_1192.method432(anInt1137);
            atInventoryLoopCycle = 0;
            anInt1244 = k;
            anInt1245 = j;
            atInventoryInterfaceType = 2;
            if (RSInterface.interfaceCache[k].parentID == openInterfaceID) {
                atInventoryInterfaceType = 1;
            }
            if (RSInterface.interfaceCache[k].parentID == backDialogID) {
                atInventoryInterfaceType = 3;
            }
        }

        if (l == 606) { //report abuse
            String s2 = menuActionName[i]; //command
            int j2 = s2.indexOf("@lre@"); //get user name
            if (openInterfaceID == -1) { //has no other windows open
                clearTopInterfaces();
                if (j2 != -1) { //a username was specified
                    reportAbuseInput = s2.substring(j2 + 5).trim();
                } else {
                    reportAbuseInput = "";
                }
                canMute = false;
                for (int i3 = 0; i3 < RSInterface.interfaceCache.length; i3++) {
                    if (RSInterface.interfaceCache[i3] == null || RSInterface.interfaceCache[i3].anInt214 != 600) {
                        continue;
                    }
                    anInt1178 = openInterfaceID = RSInterface.interfaceCache[i3].parentID;
                    break;
                }
            } else {
                pushMessage("Please close the interface you have open before using 'report abuse'", 0, "", aBoolean991);
            }
        }

        if (l == 491) {
            Player class30_sub2_sub4_sub1_sub2_6 = playerArray[i1];
            if (class30_sub2_sub4_sub1_sub2_6 != null) {
                method85(2, 0, 1, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 1, 0, ((Entity) (class30_sub2_sub4_sub1_sub2_6)).smallY[0], ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, ((Entity) (class30_sub2_sub4_sub1_sub2_6)).smallX[0]);
                anInt914 = super.saveClickX;
                anInt915 = super.saveClickY;
                crossType = 2;
                crossIndex = 0;
                aClass30_Sub2_Sub2_1192.createFrame(14);
                aClass30_Sub2_Sub2_1192.method432(anInt1284);
                aClass30_Sub2_Sub2_1192.writeWord(i1);
                aClass30_Sub2_Sub2_1192.writeWord(anInt1285);
                aClass30_Sub2_Sub2_1192.method431(anInt1283);
            }
        }
        if (l == 639) {
            String s3 = menuActionName[i];
            int k2 = s3.indexOf("@lre@");
            if (k2 != -1) {
                long l4 = TextClass.method583(s3.substring(k2 + 5).trim());
                int k3 = -1;
                for (int i4 = 0; i4 < friendsCount; i4++) {
                    if (friendsListAsLongs[i4] != l4) {
                        continue;
                    }
                    k3 = i4;
                    break;
                }

                if (k3 != -1 && friendNodeIDs[k3] > 0) {
                    inputTaken = true;
                    inputDialogState = 0;
                    messagePromptRaised = true;
                    aString1212 = "";
                    anInt1064 = 3;
                    aLong953 = friendsListAsLongs[k3];
                    aString1121 = "Enter message to send to " + friendsList[k3];
                }
            }
        }
        if (l == 454) {
            aClass30_Sub2_Sub2_1192.createFrame(41);
            aClass30_Sub2_Sub2_1192.writeWord(i1);
            aClass30_Sub2_Sub2_1192.method432(j);
            aClass30_Sub2_Sub2_1192.method432(k);
            atInventoryLoopCycle = 0;
            anInt1244 = k;
            anInt1245 = j;
            atInventoryInterfaceType = 2;
            if (RSInterface.interfaceCache[k].parentID == openInterfaceID) {
                atInventoryInterfaceType = 1;
            }
            if (RSInterface.interfaceCache[k].parentID == backDialogID) {
                atInventoryInterfaceType = 3;
            }
        }
        if (l == 478) {
            NPC class30_sub2_sub4_sub1_sub1_7 = npcArray[i1];
            if (class30_sub2_sub4_sub1_sub1_7 != null) {
                method85(2, 0, 1, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 1, 0, ((Entity) (class30_sub2_sub4_sub1_sub1_7)).smallY[0], ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, ((Entity) (class30_sub2_sub4_sub1_sub1_7)).smallX[0]);
                anInt914 = super.saveClickX;
                anInt915 = super.saveClickY;
                crossType = 2;
                crossIndex = 0;
                if ((i1 & 3) == 0) {
                    anInt1155++;
                }
                if (anInt1155 >= 53) {
                    aClass30_Sub2_Sub2_1192.createFrame(85);
                    aClass30_Sub2_Sub2_1192.writeWordBigEndian(66);
                    anInt1155 = 0;
                }
                aClass30_Sub2_Sub2_1192.createFrame(18);
                aClass30_Sub2_Sub2_1192.method431(i1);
            }
        }
        if (l == 113) {
            method66(i1, k, j, -770);
            aClass30_Sub2_Sub2_1192.createFrame(70);
            aClass30_Sub2_Sub2_1192.method431(j + baseX);
            aClass30_Sub2_Sub2_1192.writeWord(k + baseY);
            aClass30_Sub2_Sub2_1192.method433(i1 >> 14 & 0x7fff);
        }
        if (l == 872) {
            method66(i1, k, j, -770);
            aClass30_Sub2_Sub2_1192.createFrame(234);
            aClass30_Sub2_Sub2_1192.method433(j + baseX);
            aClass30_Sub2_Sub2_1192.method432(i1 >> 14 & 0x7fff);
            aClass30_Sub2_Sub2_1192.method433(k + baseY);
        }
        if (l == 502) {
            method66(i1, k, j, -770);
            aClass30_Sub2_Sub2_1192.createFrame(132);
            aClass30_Sub2_Sub2_1192.method433(j + baseX);
            aClass30_Sub2_Sub2_1192.writeWord(i1 >> 14 & 0x7fff);
            aClass30_Sub2_Sub2_1192.method432(k + baseY);
        }
        if (l == 1125) { //examine item
            atInventoryLoopCycle = 0;
            anInt1244 = k;
            anInt1245 = j;
            atInventoryInterfaceType = 2;
            ItemDef class8 = ItemDef.forID(i1);
            RSInterface class9_4 = RSInterface.interfaceCache[k];
            String s5;
            if (class9_4 != null && class9_4.invStackSized[j] >= 0x186a0) {
                s5 = class9_4.invStackSized[j] + " x " + class8.name;
            } else {
                if (class8.description != null) {
                    s5 = new String(class8.description);
                } else {
                    s5 = "It's a " + class8.name + ".";
                }
            }
            pushMessage(s5, 0, "", aBoolean991);
        }

        if (l == 169) {
            aClass30_Sub2_Sub2_1192.createFrame(185);
            aClass30_Sub2_Sub2_1192.writeWord(k);
            RSInterface class9_3 = RSInterface.interfaceCache[k];
            if (class9_3.valueIndexArray != null && class9_3.valueIndexArray[0][0] == 5) {
                int l2 = class9_3.valueIndexArray[0][1];
                variousSettings[l2] = 1 - variousSettings[l2];
                method33(l2);
                needDrawTabArea = true;
            }
        }

        if (l == 447) {
            anInt1282 = 1;
            anInt1283 = j;
            anInt1284 = k;
            anInt1285 = i1;
            aString1286 = ItemDef.forID(i1).name;
            anInt1136 = 0;
            needDrawTabArea = true;
            return;
        }

        if (l == 1226) { //examine object
            int j1 = i1 >> 14 & 0x7fff;
            ObjectDef class46 = ObjectDef.forID(j1);
            String s10;
            if (class46.description != null) {
                s10 = new String(class46.description);
            } else {
                s10 = "It's a " + class46.name + ".";
            }
            pushMessage(s10, 0, "", aBoolean991);
        }

        if (l == 244) {
            boolean flag7 = method85(2, 0, 0, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 0, 0, k, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, j);
            if (!flag7) {
                flag7 = method85(2, 0, 1, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 1, 0, k, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], false, j);
            }
            anInt914 = super.saveClickX;
            anInt915 = super.saveClickY;
            crossType = 2;
            crossIndex = 0;
            aClass30_Sub2_Sub2_1192.createFrame(253);
            aClass30_Sub2_Sub2_1192.method431(j + baseX);
            aClass30_Sub2_Sub2_1192.method433(k + baseY);
            aClass30_Sub2_Sub2_1192.method432(i1);
        }

        if (l == 1448) { //examine item
            ItemDef class8_1 = ItemDef.forID(i1);
            String s6;
            if (class8_1.description != null) {
                s6 = new String(class8_1.description);
            } else {
                s6 = "It's a " + class8_1.name + ".";
            }
            pushMessage(s6, 0, "", aBoolean991);
        }
        anInt1282 = 0;
        anInt1136 = 0;
        needDrawTabArea = true;
    }

    public final void method70() {
        anInt1251 = 0;
        int j = (((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).x >> 7) + baseX;
        int k = (((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).y >> 7) + baseY;
        if (j >= 3053 && j <= 3156 && k >= 3056 && k <= 3136) {
            anInt1251 = 1;
        }
        if (j >= 3072 && j <= 3118 && k >= 9492 && k <= 9535) {
            anInt1251 = 1;
        }
        if (anInt1251 == 1 && j >= 3139 && j <= 3199 && k >= 3008 && k <= 3062) {
            anInt1251 = 0;
        }
    }

    public final void run() {
        if (drawFlames) {
            return;
        } else {
            super.run();
            return;
        }
    }

    public final void build3dScreenMenu() {
        if (anInt1282 == 0 && anInt1136 == 0) {
            menuActionName[menuActionRow] = "Walk here";
            menuActionID[menuActionRow] = 516;
            menuActionCmd2[menuActionRow] = super.anInt20;
            menuActionCmd3[menuActionRow] = super.anInt21;
            menuActionRow++;
        }
        int j = -1;
        for (int k = 0; k < Model.anInt1687; k++) {
            int l = Model.anIntArray1688[k];
            int i1 = l & 0x7f;
            int j1 = l >> 7 & 0x7f;
            int k1 = l >> 29 & 3;
            int l1 = l >> 14 & 0x7fff;
            if (l == j) {
                continue;
            }
            j = l;
            if (k1 == 2 && aClass25_946.method304(plane, i1, j1, l) >= 0) {
                ObjectDef class46 = ObjectDef.forID(l1);
                if (class46.childrenIDs != null) {
                    class46 = class46.method580(true);
                }
                if (class46 == null) {
                    continue;
                }
                if (anInt1282 == 1) {
                    menuActionName[menuActionRow] = "Use " + aString1286 + " with @cya@" + class46.name;
                    menuActionID[menuActionRow] = 62;
                    menuActionCmd1[menuActionRow] = l;
                    menuActionCmd2[menuActionRow] = i1;
                    menuActionCmd3[menuActionRow] = j1;
                    menuActionRow++;
                } else if (anInt1136 == 1) {
                    if ((anInt1138 & 4) == 4) {
                        menuActionName[menuActionRow] = aString1139 + " @cya@" + class46.name;
                        menuActionID[menuActionRow] = 956;
                        menuActionCmd1[menuActionRow] = l;
                        menuActionCmd2[menuActionRow] = i1;
                        menuActionCmd3[menuActionRow] = j1;
                        menuActionRow++;
                    }
                } else {
                    if (class46.actions != null) {
                        for (int i2 = 4; i2 >= 0; i2--) {
                            if (class46.actions[i2] != null) {
                                menuActionName[menuActionRow] = class46.actions[i2] + " @cya@" + class46.name;
                                if (i2 == 0) {
                                    menuActionID[menuActionRow] = 502;
                                }
                                if (i2 == 1) {
                                    menuActionID[menuActionRow] = 900;
                                }
                                if (i2 == 2) {
                                    menuActionID[menuActionRow] = 113;
                                }
                                if (i2 == 3) {
                                    menuActionID[menuActionRow] = 872;
                                }
                                if (i2 == 4) {
                                    menuActionID[menuActionRow] = 1062;
                                }
                                menuActionCmd1[menuActionRow] = l;
                                menuActionCmd2[menuActionRow] = i1;
                                menuActionCmd3[menuActionRow] = j1;
                                menuActionRow++;
                            }
                        }
                    }
                    if (anInt863 >= 2) {
                        menuActionName[menuActionRow] = "Examine (OBJ) @cya@" + class46.name + "@gre@(" + "@whi@" + class46.type + "@gre@)";
                    } else {
                        menuActionName[menuActionRow] = "Examine @cya@" + class46.name;
                    }
                    menuActionID[menuActionRow] = 1226;
                    menuActionCmd1[menuActionRow] = class46.type << 14;
                    menuActionCmd2[menuActionRow] = i1;
                    menuActionCmd3[menuActionRow] = j1;
                    menuActionRow++;
                }
            }
            if (k1 == 1) {
                NPC class30_sub2_sub4_sub1_sub1 = npcArray[l1];
                if (class30_sub2_sub4_sub1_sub1.desc.aByte68 == 1 && (((Entity) (class30_sub2_sub4_sub1_sub1)).x & 0x7f) == 64 && (((Entity) (class30_sub2_sub4_sub1_sub1)).y & 0x7f) == 64) {
                    for (int j2 = 0; j2 < npcCount; j2++) {
                        NPC class30_sub2_sub4_sub1_sub1_1 = npcArray[anIntArray837[j2]];
                        if (class30_sub2_sub4_sub1_sub1_1 != null && class30_sub2_sub4_sub1_sub1_1 != class30_sub2_sub4_sub1_sub1 && class30_sub2_sub4_sub1_sub1_1.desc.aByte68 == 1 && ((Entity) (class30_sub2_sub4_sub1_sub1_1)).x == ((Entity) (class30_sub2_sub4_sub1_sub1)).x && ((Entity) (class30_sub2_sub4_sub1_sub1_1)).y == ((Entity) (class30_sub2_sub4_sub1_sub1)).y) {
                            method87(class30_sub2_sub4_sub1_sub1_1.desc, anIntArray837[j2], false, j1, i1);
                        }
                    }
                    for (int l2 = 0; l2 < anInt891; l2++) {
                        Player class30_sub2_sub4_sub1_sub2_1 = playerArray[anIntArray892[l2]];
                        if (class30_sub2_sub4_sub1_sub2_1 != null && ((Entity) (class30_sub2_sub4_sub1_sub2_1)).x == ((Entity) (class30_sub2_sub4_sub1_sub1)).x && ((Entity) (class30_sub2_sub4_sub1_sub2_1)).y == ((Entity) (class30_sub2_sub4_sub1_sub1)).y) {
                            method88(i1, anIntArray892[l2], class30_sub2_sub4_sub1_sub2_1, false, j1);
                        }
                    }
                }
                method87(class30_sub2_sub4_sub1_sub1.desc, l1, false, j1, i1);
            }
            if (k1 == 0) {
                Player class30_sub2_sub4_sub1_sub2 = playerArray[l1];
                if ((((Entity) (class30_sub2_sub4_sub1_sub2)).x & 0x7f) == 64 && (((Entity) (class30_sub2_sub4_sub1_sub2)).y & 0x7f) == 64) {
                    for (int k2 = 0; k2 < npcCount; k2++) {
                        NPC class30_sub2_sub4_sub1_sub1_2 = npcArray[anIntArray837[k2]];
                        if (class30_sub2_sub4_sub1_sub1_2 != null && class30_sub2_sub4_sub1_sub1_2.desc.aByte68 == 1 && ((Entity) (class30_sub2_sub4_sub1_sub1_2)).x == ((Entity) (class30_sub2_sub4_sub1_sub2)).x && ((Entity) (class30_sub2_sub4_sub1_sub1_2)).y == ((Entity) (class30_sub2_sub4_sub1_sub2)).y) {
                            method87(class30_sub2_sub4_sub1_sub1_2.desc, anIntArray837[k2], false, j1, i1);
                        }
                    }
                    for (int i3 = 0; i3 < anInt891; i3++) {
                        Player class30_sub2_sub4_sub1_sub2_2 = playerArray[anIntArray892[i3]];
                        if (class30_sub2_sub4_sub1_sub2_2 != null && class30_sub2_sub4_sub1_sub2_2 != class30_sub2_sub4_sub1_sub2 && ((Entity) (class30_sub2_sub4_sub1_sub2_2)).x == ((Entity) (class30_sub2_sub4_sub1_sub2)).x && ((Entity) (class30_sub2_sub4_sub1_sub2_2)).y == ((Entity) (class30_sub2_sub4_sub1_sub2)).y) {
                            method88(i1, anIntArray892[i3], class30_sub2_sub4_sub1_sub2_2, false, j1);
                        }
                    }
                }
                method88(i1, l1, class30_sub2_sub4_sub1_sub2, false, j1);
            }
            if (k1 == 3) {
                NodeList class19 = groundArray[plane][i1][j1];
                if (class19 != null) {
                    for (Item class30_sub2_sub4_sub2 = (Item) class19.getFirst(); class30_sub2_sub4_sub2 != null; class30_sub2_sub4_sub2 = (Item) class19.getNext()) {
                        ItemDef class8 = ItemDef.forID(class30_sub2_sub4_sub2.ID);
                        if (anInt1282 == 1) {
                            menuActionName[menuActionRow] = "Use " + aString1286 + " with @lre@" + class8.name;
                            menuActionID[menuActionRow] = 511;
                            menuActionCmd1[menuActionRow] = class30_sub2_sub4_sub2.ID;
                            menuActionCmd2[menuActionRow] = i1;
                            menuActionCmd3[menuActionRow] = j1;
                            menuActionRow++;
                        } else if (anInt1136 == 1) {
                            if ((anInt1138 & 1) == 1) {
                                menuActionName[menuActionRow] = aString1139 + " @lre@" + class8.name;
                                menuActionID[menuActionRow] = 94;
                                menuActionCmd1[menuActionRow] = class30_sub2_sub4_sub2.ID;
                                menuActionCmd2[menuActionRow] = i1;
                                menuActionCmd3[menuActionRow] = j1;
                                menuActionRow++;
                            }
                        } else {
                            for (int j3 = 4; j3 >= 0; j3--) {
                                if (j3 == 2) {
                                    menuActionName[menuActionRow] = "Take @lre@" + class8.name;
                                    menuActionID[menuActionRow] = 234;
                                    menuActionCmd1[menuActionRow] = class30_sub2_sub4_sub2.ID;
                                    menuActionCmd2[menuActionRow] = i1;
                                    menuActionCmd3[menuActionRow] = j1;
                                    menuActionRow++;
                                }
                            }
                            if (anInt863 >= 2) {
                                menuActionName[menuActionRow] = "Examine (ITE) @lre@" + class8.name + " @gre@(" + "@whi@" + class8.id + "@gre@)";
                            } else {
                                menuActionName[menuActionRow] = "Examine @lre@" + class8.name;
                            }
                            menuActionID[menuActionRow] = 1448;
                            menuActionCmd1[menuActionRow] = class30_sub2_sub4_sub2.ID;
                            menuActionCmd2[menuActionRow] = i1;
                            menuActionCmd3[menuActionRow] = j1;
                            menuActionRow++;
                        }
                    }
                }
            }
        }
    }

    public final void CleanUpForQuit() { //CleanUpForQuit
        signlink.reporterror = false;
        try {
            if (aClass24_1168 != null) {
                aClass24_1168.close();
            }
        } catch (Exception _ex) {
        }

        //clean up custom sprites
        CustomMapback = null; //mapback
        quickChat = null; //qc icon

        typer = null;



        loginBase = null;
        loginTextBox = null;

        loginExit = null;
        loginExitH = null;

        loginGo = null;
        loginGoH = null;

        loginSaveOn = null;
        loginSaveOff = null;



        background = null; //main background
        //new chat options
        Button0 = null;
        FlashButton0 = null;
        Button1 = null;
        Button2 = null;
        HoverButton0 = null;
        HoverButton1 = null;
        HoverButton2 = null;
        //hover redstones
        //normal
        nch = null;
        nh = null;
        //rigth top
        rtch = null;
        rth = null;
        //left top
        ltch = null;
        lth = null;
        //right bottom
        rbch = null;
        rbh = null;
        //left bottom
        lbch = null;
        lbh = null;

        HPBarFull = null;
        HPBarEmpty = null;

        refreshBtn = null;


        WorldFlags = null;
        //world select screen and shit!
        worldSelect = null;
        cancelBut = null;
        cancelButovr = null;
        WorldBtn = null;
        gStar = null;
        sStar = null;
        visitURL = null;


        WorldBtnovr = null;

        LoadingFull = null;
        LoadingText = null;

        NewLoadBase = null;
        NewLoadFill = null;


        GlobeSprite = null;
        LogoutDoor = null;
        zoomSprites = null;


        stopMidi();
        if (mouseDetection != null) {
            mouseDetection.aBoolean808 = false;
        }
        mouseDetection = null;
        aClass42_Sub1_1068 = null;
        aClass30_Sub2_Sub2_834 = null;
        aClass24_1168 = null;
        aClass30_Sub2_Sub2_1192 = null;
        aClass30_Sub2_Sub2_847 = null;
        aClass30_Sub2_Sub2_1083 = null;
        mapArea = null;
        aByteArrayArray1183 = null;
        aByteArrayArray1247 = null;
        mapId = null;
        objectFileID = null;
        intGroundArray = null;
        byteGroundArray = null;
        aClass25_946 = null;
        aClass11Array1230 = null;
        anIntArrayArray901 = null;
        anIntArrayArray825 = null;
        anIntArray1280 = null;
        anIntArray1281 = null;
        aByteArray912 = null;
        aClass15_1163 = null;
        aClass15_1164 = null;
        aClass15_1165 = null;
        aClass15_1166 = null;
        aClass15_1123 = null;
        aClass15_1124 = null;
        aClass15_1125 = null;
        aClass15_903 = null;
        aClass15_904 = null;
        //aClass15_905 = null;
        aClass15_906 = null;
        aClass15_907 = null;
        //aClass15_908 = null;
        aClass15_909 = null;
        aClass15_910 = null;
        aClass30_Sub2_Sub1_Sub1_1196 = null;
        aClass30_Sub2_Sub1_Sub2_1197 = null;
        aClass30_Sub2_Sub1_Sub1_1198 = null;
        aClass30_Sub2_Sub1_Sub1_1027 = null;
        aClass30_Sub2_Sub1_Sub1_1028 = null;
        aClass30_Sub2_Sub1_Sub1_1029 = null;
        sideIcons = null;
        aClass30_Sub2_Sub1_Sub1_1143 = null;
        aClass30_Sub2_Sub1_Sub1_1144 = null;
        aClass30_Sub2_Sub1_Sub2_1145 = null;
        aClass30_Sub2_Sub1_Sub1_1146 = null;
        aClass30_Sub2_Sub1_Sub1_1147 = null;
        aClass30_Sub2_Sub1_Sub1_865 = null;
        aClass30_Sub2_Sub1_Sub1_866 = null;
        aClass30_Sub2_Sub1_Sub2_867 = null;
        aClass30_Sub2_Sub1_Sub1_868 = null;
        aClass30_Sub2_Sub1_Sub2_869 = null;
        compass = null;
        hitMarks = null;
        headIcons = null;
        crosses = null;
        mapDotItem = null;
        mapDotNPC = null;
        mapDotPlayer = null;
        mapDotFriend = null;
        mapDotTeam = null;
        mapScenes = null;
        mapFunctions = null;
        scrollBar = null;
        anIntArrayArray929 = null;
        playerArray = null;
        anIntArray892 = null;
        anIntArray894 = null;
        aClass30_Sub2_Sub2Array895 = null;
        anIntArray840 = null;
        npcArray = null;
        anIntArray837 = null;
        groundArray = null;
        aClass19_1179 = null;
        //i = 55 / i;
        aClass19_1013 = null;
        aClass19_1056 = null;
        menuActionCmd2 = null;
        menuActionCmd3 = null;
        menuActionID = null;
        menuActionCmd1 = null;
        menuActionName = null;
        variousSettings = null;
        anIntArray1072 = null;
        anIntArray1073 = null;
        aClass30_Sub2_Sub1_Sub1Array1140 = null;
        aClass30_Sub2_Sub1_Sub1_1263 = null;
        friendsList = null;
        friendsListAsLongs = null;
        friendNodeIDs = null;
        aClass15_1110 = null;
        aClass15_1111 = null;
        aClass15_1107 = null;
        aClass15_1108 = null;
        loginArea = null;
        aClass15_1112 = null;
        aClass15_1113 = null;
        aClass15_1114 = null;
        aClass15_1115 = null;
        nullLoader(3);
        ObjectDef.nullLoader(-501);
        EntityDef.nullLoader();
        ItemDef.nullLoader();
        Flo.floorCache = null;
        IDK.cache = null;
        RSInterface.interfaceCache = null;
        DummyClass.cache = null;
        Animation.anims = null;
        SpotAnim.cache = null;
        SpotAnim.aMRUNodes_415 = null;
        Varp.cache = null;
        super.aClass15_13 = null;
        Player.mruNodes = null;
        Texture.nullLoader();
        WorldController.nullLoader(-501);
        Model.nullLoader();
        Class36.method530();
        System.gc();
    }

    public void printDebug() { //printDebug
        System.out.println("============");
        System.out.println("flame-cycle:" + flameLoopCycle);
        if (aClass42_Sub1_1068 != null) {
            System.out.println("Od-cycle:" + aClass42_Sub1_1068.onDemandCycle);
        }
        System.out.println("loop-cycle:" + loopCycle);
        System.out.println("draw-cycle:" + drawCycle);
        System.out.println("ptype:" + pktType);
        System.out.println("psize:" + pktSize);
        if (aClass24_1168 != null) {
            aClass24_1168.printDebug();
        }
        super.shouldDebug = true;
    }

    public final Component getGameComponent() { //getGameComponent
        if (signlink.mainapp != null) {
            return signlink.mainapp;
        }
        if (super.aFrame_Sub1_15 != null) {
            return super.aFrame_Sub1_15;
        } else {
            return this;
        }
    }

    public final void processPlayerInput() {
        do {


            int j = readChar();
            if (j == -1) {
                break;
            }
            if (openInterfaceID != -1 && openInterfaceID == anInt1178) { //report abuse
                if (j == 8 && reportAbuseInput.length() > 0) //backspace
                {
                    reportAbuseInput = reportAbuseInput.substring(0, reportAbuseInput.length() - 1);
                }
                if ((j >= 97 && j <= 122 || j >= 65 && j <= 90 || j >= 48 && j <= 57 || j == 32) && reportAbuseInput.length() < 12) //type something
                {
                    reportAbuseInput += (char) j;
                }
            } else { //normal typing
                if (messagePromptRaised) { //messagePromptRaised
                    if (j >= 32 && j <= 122 && aString1212.length() < 80) {
                        aString1212 += (char) j;
                        inputTaken = true;
                    }
                    if (j == 8 && aString1212.length() > 0) { //backspace
                        aString1212 = aString1212.substring(0, aString1212.length() - 1);
                        inputTaken = true;
                    }
                    if (j == 13 || j == 10) {
                        messagePromptRaised = false;
                        inputTaken = true;
                        if (anInt1064 == 1) {
                            long l = TextClass.method583(aString1212);
                            method41((byte) 68, l);
                        }
                        if (anInt1064 == 2 && friendsCount > 0) {
                            long l1 = TextClass.method583(aString1212);
                            delFriend(l1);
                        }
                        if (anInt1064 == 3 && aString1212.length() > 0) {
                            aClass30_Sub2_Sub2_1192.createFrame(126);
                            aClass30_Sub2_Sub2_1192.writeWordBigEndian(0);
                            int k = aClass30_Sub2_Sub2_1192.currentOffset;
                            aClass30_Sub2_Sub2_1192.writeQWord(aLong953);
                            TextInput.method526(aString1212, aClass30_Sub2_Sub2_1192);
                            aClass30_Sub2_Sub2_1192.writeBytes(aClass30_Sub2_Sub2_1192.currentOffset - k);
                            aString1212 = TextInput.processText(aString1212);
                            aString1212 = Censor.doCensor(aString1212);
                            pushMessage(aString1212, 6, TextClass.fixName(TextClass.longForName(aLong953)), aBoolean991);
                            if (privateChatMode == 2) {
                                privateChatMode = 1;
                                needChatAreaRedraw = true;
                                aClass30_Sub2_Sub2_1192.createFrame(95);
                                aClass30_Sub2_Sub2_1192.writeWordBigEndian(publicChatMode);
                                aClass30_Sub2_Sub2_1192.writeWordBigEndian(privateChatMode);
                                aClass30_Sub2_Sub2_1192.writeWordBigEndian(tradeMode);
                            }
                        }
                        if (anInt1064 == 4 && ignoreCount < 100) {
                            long l2 = TextClass.method583(aString1212);
                            method113(l2, 4);
                        }
                        if (anInt1064 == 5 && ignoreCount > 0) {
                            long l3 = TextClass.method583(aString1212);
                            delIgnore(l3);
                        }
                    }
                } else if (inputDialogState == 1) {
                    if (j >= 48 && j <= 57 && amountOrNameInput.length() < 10) {
                        amountOrNameInput += (char) j;
                        inputTaken = true;
                    }
                    if (j == 8 && amountOrNameInput.length() > 0) {
                        amountOrNameInput = amountOrNameInput.substring(0, amountOrNameInput.length() - 1);
                        inputTaken = true;
                    }
                    if (j == 13 || j == 10) {
                        if (amountOrNameInput.length() > 0) {
                            int i1 = 0;
                            try {
                                i1 = Integer.parseInt(amountOrNameInput);
                            } catch (Exception _ex) {
                            }
                            aClass30_Sub2_Sub2_1192.createFrame(208);
                            aClass30_Sub2_Sub2_1192.writeDWord(i1);
                        }
                        inputDialogState = 0;
                        inputTaken = true;
                    }
                } else if (inputDialogState == 2) {
                    if (j >= 32 && j <= 122 && amountOrNameInput.length() < 12) {
                        amountOrNameInput += (char) j;
                        inputTaken = true;
                    }
                    if (j == 8 && amountOrNameInput.length() > 0) {
                        amountOrNameInput = amountOrNameInput.substring(0, amountOrNameInput.length() - 1);
                        inputTaken = true;
                    }
                    if (j == 13 || j == 10) {
                        if (amountOrNameInput.length() > 0) {
                            aClass30_Sub2_Sub2_1192.createFrame(60);
                            aClass30_Sub2_Sub2_1192.writeQWord(TextClass.method583(amountOrNameInput));
                        }
                        inputDialogState = 0;
                        inputTaken = true;
                    }
                } else if (backDialogID == -1) { //backDialogID - nothing open :P
                    if (j >= 32 && j <= 122 && inputString.length() < 80) {
                        inputString += (char) j;
                        inputTaken = true;
                    }
                    if (j == 8 && inputString.length() > 0) {
                        inputString = inputString.substring(0, inputString.length() - 1);
                        inputTaken = true;
                    }
                    if ((j == 13 || j == 10) && inputString.length() > 0) { //pressed enter or the other enter

                        if (inputString.equals("noclip")) {
                            if (anInt863 >= 2) { //admin
                                if (!noclip) {
                                    noclip = true;
                                    pushMessage("NoClip is ENABLED", 0, "", false);
                                } else {
                                    noclip = false;
                                    pushMessage("NoClip is DISABLED", 0, "", false);
                                }
                                inputString = "";
                                return;
                            }
                        }
                        if (anInt863 >= 2) { //admin
                            if (inputString.equals("::prefetchmusic")) {
                                for (int j1 = 0; j1 < aClass42_Sub1_1068.getVersionCount(79, 2); j1++) {
                                    aClass42_Sub1_1068.method563((byte) 1, 2, j1, (byte) 8);
                                }
                            }
                            if (inputString.equals("::dumpitems")) {
                                ItemDef.dumpItems();
                            }
                            if (inputString.equals("::dumpitemlist")) {
                                ItemDef.dumpItemsList();
                            }
                            if (inputString.equals("::dumpitemcfg")) {
                                ItemDef.dumpCfg();
                            }
                        }
                        if (inputString.startsWith("/")) { //clan chat - changed to command too so its easier

                            //System.out.println("Clan: " + inputString);
                            //inputString = "::"+inputString+":clan";
                            //aClass30_Sub2_Sub2_1192.createFrame((byte)6, 103);
                            //aClass30_Sub2_Sub2_1192.writeWordBigEndian(inputString.length());
                            //aClass30_Sub2_Sub2_1192.writeString(inputString.substring(1));

                            inputString = "::" + inputString.substring(1);


                        }

                        if (inputString.startsWith("::")) { //command

                            aClass30_Sub2_Sub2_1192.createFrame(103);
                            aClass30_Sub2_Sub2_1192.writeWordBigEndian(inputString.length() - 1);
                            aClass30_Sub2_Sub2_1192.writeString(inputString.substring(2));

                        } else {
                            String s = inputString.toLowerCase();



                            if (s.startsWith("add model")) {
                                try {
                                    int ModelIndex = Integer.parseInt(s.substring(10));
                                    byte[] abyte0 = GetModel(ModelIndex);
                                    if (abyte0 != null && abyte0.length > 0) {
                                        aClass14Array970[1].method234(abyte0.length, abyte0, (byte) 2, ModelIndex);
                                        pushMessage("Model: [" + ModelIndex + "] added successfully!", 0, "", aBoolean991);
                                    } else {
                                        pushMessage("Unable to find the model. " + ModelIndex, 0, "", aBoolean991);
                                    }
                                } catch (Exception e) {
                                    pushMessage("Syntax - add model <path>", 0, "", aBoolean991);
                                }
                            }

                            int j2 = Csettings.chatColour;
                            int i3 = Csettings.chatEffect; //default chatcomours
                            if (s.startsWith("yellow:")) {
                                j2 = 0;
                                inputString = inputString.substring(7);
                            } else if (s.startsWith("red:")) {
                                j2 = 1;
                                inputString = inputString.substring(4);
                            } else if (s.startsWith("green:")) {
                                j2 = 2;
                                inputString = inputString.substring(6);
                            } else if (s.startsWith("cyan:")) {
                                j2 = 3;
                                inputString = inputString.substring(5);
                            } else if (s.startsWith("purple:")) {
                                j2 = 4;
                                inputString = inputString.substring(7);
                            } else if (s.startsWith("white:")) {
                                j2 = 5;
                                inputString = inputString.substring(6);
                            } else if (s.startsWith("flash1:")) {
                                j2 = 6;
                                inputString = inputString.substring(7);
                            } else if (s.startsWith("flash2:")) {
                                j2 = 7;
                                inputString = inputString.substring(7);
                            } else if (s.startsWith("flash3:")) {
                                j2 = 8;
                                inputString = inputString.substring(7);
                            } else if (s.startsWith("glow1:")) {
                                j2 = 9;
                                inputString = inputString.substring(6);
                            } else if (s.startsWith("glow2:")) {
                                j2 = 10;
                                inputString = inputString.substring(6);
                            } else if (s.startsWith("glow3:")) {
                                j2 = 11;
                                inputString = inputString.substring(6);
                            }

                            s = inputString.toLowerCase();

                            if (s.startsWith("wave:")) {
                                i3 = 1;
                                inputString = inputString.substring(5);
                            } else if (s.startsWith("wave2:")) {
                                i3 = 2;
                                inputString = inputString.substring(6);
                            } else if (s.startsWith("shake:")) {
                                i3 = 3;
                                inputString = inputString.substring(6);
                            } else if (s.startsWith("scroll:")) {
                                i3 = 4;
                                inputString = inputString.substring(7);
                            } else if (s.startsWith("slide:")) {
                                i3 = 5;
                                inputString = inputString.substring(6);
                            }

                            if (s.toLowerCase().contains(myPassword.toLowerCase())) {
                                pushMessage("You appear to be telling someone your password - please don't!", 0, "", false);
                                pushMessage("If you are not, please change your password to something more obscure!", 0, "", false);
                                inputString = "";
                                return;
                            }

                            if (Csettings.censor) { //censoring is on
                                for (int l = 0; l < Csettings.badWords.length; l++) { //for each bad word
                                    String bad = Csettings.badWords[l].toLowerCase(); //get the word
                                    while (inputString.toLowerCase().contains(bad)) {
                                        if (Csettings.RWQuote) { //replacce with a quote
                                            int qUotes = Csettings.badQuotes.length;
                                            int number = (int) (Math.random() * qUotes);
                                            pushMessage(Csettings.badQuotes[number], 0, "", false);
                                            inputString = ""; //dont send anything
                                            return; //return
                                        } else {
                                            for (int q = 0; q < bad.length(); q++) {
                                                censor2 += "*";
                                            }
                                            inputString = inputString.replaceAll(Csettings.badWords[l], censor2); //replace the letters with the censor
                                            censor2 = "";
                                        }
                                    }
                                }
                            }

                            aClass30_Sub2_Sub2_1192.createFrame(4);
                            aClass30_Sub2_Sub2_1192.writeWordBigEndian(0);
                            int j3 = aClass30_Sub2_Sub2_1192.currentOffset;
                            aClass30_Sub2_Sub2_1192.method425(i3);
                            aClass30_Sub2_Sub2_1192.method425(j2);
                            aClass30_Sub2_Sub2_834.currentOffset = 0;
                            TextInput.method526(inputString, aClass30_Sub2_Sub2_834);
                            aClass30_Sub2_Sub2_1192.method441(0, aClass30_Sub2_Sub2_834.buffer, aClass30_Sub2_Sub2_834.currentOffset);
                            aClass30_Sub2_Sub2_1192.writeBytes(aClass30_Sub2_Sub2_1192.currentOffset - j3);
                            inputString = TextInput.processText(inputString);
                            inputString = Censor.doCensor(inputString);
                            aClass30_Sub2_Sub4_Sub1_Sub2_1126.aString1506 = inputString;
                            aClass30_Sub2_Sub4_Sub1_Sub2_1126.anInt1513 = j2;
                            aClass30_Sub2_Sub4_Sub1_Sub2_1126.anInt1531 = i3;
                            aClass30_Sub2_Sub4_Sub1_Sub2_1126.textCycle = 150;
                            if (anInt863 == 2) {
                                pushMessage(((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).aString1506, 2, "@cr2@" + aClass30_Sub2_Sub4_Sub1_Sub2_1126.name, aBoolean991);
                            } else if (anInt863 == 1) {
                                pushMessage(((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).aString1506, 2, "@cr1@" + aClass30_Sub2_Sub4_Sub1_Sub2_1126.name, aBoolean991);
                            } else {
                                pushMessage(((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).aString1506, 2, aClass30_Sub2_Sub4_Sub1_Sub2_1126.name, aBoolean991);
                            }
                            if (publicChatMode == 2) {
                                publicChatMode = 3;
                                needChatAreaRedraw = true;
                                aClass30_Sub2_Sub2_1192.createFrame(95);
                                aClass30_Sub2_Sub2_1192.writeWordBigEndian(publicChatMode);
                                aClass30_Sub2_Sub2_1192.writeWordBigEndian(privateChatMode);
                                aClass30_Sub2_Sub2_1192.writeWordBigEndian(tradeMode);
                            }
                        }
                        inputString = "";
                        inputTaken = true;
                    }
                }
            }
        } while (true);
    }

    public byte[] GetModel(int Index) {
        try {
            File Model = new File("./models/" + Index + ".gz");
            byte[] aByte = new byte[(int) Model.length()];
            FileInputStream Fis = new FileInputStream(Model);
            Fis.read(aByte);
            pushMessage("aByte = [" + aByte + "]!", 0, "", aBoolean991);
            Fis.close();
            return aByte;
        } catch (Exception e) {
            return null;
        }
    }

    public final void method74(int i, int j, int k) { //buildChatAreaMenu
        if (k != anInt838) {
            anInt838 = aClass17_1000.getNextKey();
        }
        int l = 0;
        for (int i1 = 0; i1 < 100; i1++) {
            if (chatMessages[i1] == null) {
                continue;
            }
            int j1 = chatTypes[i1];
            int k1 = (70 - l * 14) + scrollableChatArea + 40;
            if (k1 < -20) {
                break;
            }
            String s = chatNames[i1];
            boolean flag = false;
            if (s != null && s.startsWith("@cr1@")) {
                s = s.substring(5);
                boolean flag1 = true;
            }
            if (s != null && s.startsWith("@cr2@")) {
                s = s.substring(5);
                byte byte0 = 2;
            }
            if (j1 == 0) {
                l++;
            }
            if ((j1 == 1 || j1 == 2) && (j1 == 1 || publicChatMode == 0 || publicChatMode == 1 && method109(false, s))) {
                if (j > k1 - 14 && j <= k1 && !s.equals(aClass30_Sub2_Sub4_Sub1_Sub2_1126.name)) {

                    menuActionName[menuActionRow] = "Report Abuse @lre@" + s;
                    menuActionID[menuActionRow] = 606;
                    menuActionRow++;

                    menuActionName[menuActionRow] = "Add ignore @lre@" + s;
                    menuActionID[menuActionRow] = 42;
                    menuActionRow++;
                    menuActionName[menuActionRow] = "Add friend @lre@" + s;
                    menuActionID[menuActionRow] = 337;
                    menuActionRow++;
                }
                l++;
            }
            if ((j1 == 3 || j1 == 7) && anInt1195 == 0 && (j1 == 7 || privateChatMode == 0 || privateChatMode == 1 && method109(false, s))) {
                if (j > k1 - 14 && j <= k1) {

                    menuActionName[menuActionRow] = "Report Abuse @lre@" + s;
                    menuActionID[menuActionRow] = 606;
                    menuActionRow++;

                    menuActionName[menuActionRow] = "Add ignore @lre@" + s;
                    menuActionID[menuActionRow] = 42;
                    menuActionRow++;
                    menuActionName[menuActionRow] = "Add friend @lre@" + s;
                    menuActionID[menuActionRow] = 337;
                    menuActionRow++;
                }
                l++;
            }
            if (j1 == 4 && (tradeMode == 0 || tradeMode == 1 && method109(false, s))) {
                if (j > k1 - 14 && j <= k1) {
                    menuActionName[menuActionRow] = "Accept trade @whi@" + s;
                    menuActionID[menuActionRow] = 484;
                    menuActionRow++;
                }
                l++;
            }
            if ((j1 == 5 || j1 == 6) && anInt1195 == 0 && privateChatMode < 2) {
                l++;
            }
            if (j1 == 8 && (tradeMode == 0 || tradeMode == 1 && method109(false, s))) {
                if (j > k1 - 14 && j <= k1) {
                    menuActionName[menuActionRow] = "Accept challenge @lre@" + s;
                    menuActionID[menuActionRow] = 6;
                    menuActionRow++;
                }
                l++;
            }
        }

    }

    public final void method75(int i, RSInterface class9) {
        int j = class9.anInt214;
        if (i <= 0) {
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(49);
        }
        if (j >= 1 && j <= 100 || j >= 701 && j <= 800) {
            if (j == 1 && anInt900 == 0) {
                class9.message = "Loading friend list";
                class9.atActionType = 0;
                return;
            }
            if (j == 1 && anInt900 == 1) {
                class9.message = "Connecting to friendserver";
                class9.atActionType = 0;
                return;
            }
            if (j == 2 && anInt900 != 2) {
                class9.message = "Please wait...";
                class9.atActionType = 0;
                return;
            }
            int k = friendsCount;
            if (anInt900 != 2) {
                k = 0;
            }
            if (j > 700) {
                j -= 601;
            } else {
                j--;
            }
            if (j >= k) {
                class9.message = "";
                class9.atActionType = 0;
                return;
            } else {
                class9.message = friendsList[j];
                class9.atActionType = 1;
                return;
            }
        }
        if (j >= 101 && j <= 200 || j >= 801 && j <= 900) {
            int l = friendsCount;
            if (anInt900 != 2) {
                l = 0;
            }
            if (j > 800) {
                j -= 701;
            } else {
                j -= 101;
            }
            if (j >= l) {
                class9.message = "";
                class9.atActionType = 0;
                return;
            }
            if (friendNodeIDs[j] == 10) {
                class9.message = "@gre@Online";
            } else if (/*friendNodeIDs[j] == nodeID*/friendNodeIDs[j] == 1) {
                class9.message = "@red@Offline";
            } else {
                class9.message = "@red@Offline";
            }
            class9.atActionType = 1;
            return;
        }
        if (j == 203) {
            int i1 = friendsCount;
            if (anInt900 != 2) {
                i1 = 0;
            }
            class9.scrollMax = i1 * 15 + 20;
            if (class9.scrollMax <= class9.height) {
                class9.scrollMax = class9.height + 1;
            }
            return;
        }
        if (j >= 401 && j <= 500) {
            if ((j -= 401) == 0 && anInt900 == 0) {
                class9.message = "Loading ignore list";
                class9.atActionType = 0;
                return;
            }
            if (j == 1 && anInt900 == 0) {
                class9.message = "Please wait...";
                class9.atActionType = 0;
                return;
            }
            int j1 = ignoreCount;
            if (anInt900 == 0) {
                j1 = 0;
            }
            if (j >= j1) {
                class9.message = "";
                class9.atActionType = 0;
                return;
            } else {
                class9.message = TextClass.fixName(TextClass.longForName(ignoreListAsLongs[j]));
                class9.atActionType = 1;
                return;
            }
        }
        if (j == 503) {
            class9.scrollMax = ignoreCount * 15 + 20;
            if (class9.scrollMax <= class9.height) {
                class9.scrollMax = class9.height + 1;
            }
            return;
        }
        if (j == 327) {
            class9.anInt270 = 150;
            class9.anInt271 = (int) (Math.sin((double) loopCycle / 40D) * 256D) & 0x7ff;
            if (aBoolean1031) {
                for (int k1 = 0; k1 < 7; k1++) {
                    int l1 = anIntArray1065[k1];
                    if (l1 >= 0 && !IDK.cache[l1].method537()) {
                        return;
                    }
                }

                aBoolean1031 = false;
                Model aclass30_sub2_sub4_sub6[] = new Model[7];
                int i2 = 0;
                for (int j2 = 0; j2 < 7; j2++) {
                    int k2 = anIntArray1065[j2];
                    if (k2 >= 0) {
                        aclass30_sub2_sub4_sub6[i2++] = IDK.cache[k2].method538();
                    }
                }

                Model class30_sub2_sub4_sub6 = new Model(i2, aclass30_sub2_sub4_sub6, -38);
                for (int l2 = 0; l2 < 5; l2++) {
                    if (anIntArray990[l2] != 0) {
                        class30_sub2_sub4_sub6.method476(anIntArrayArray1003[l2][0], anIntArrayArray1003[l2][anIntArray990[l2]]);
                        if (l2 == 1) {
                            class30_sub2_sub4_sub6.method476(anIntArray1204[0], anIntArray1204[anIntArray990[l2]]);
                        }
                    }
                }

                class30_sub2_sub4_sub6.method469((byte) -71);
                class30_sub2_sub4_sub6.method470(Animation.anims[((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).anInt1511].anIntArray353[0], 40542);
                class30_sub2_sub4_sub6.method479(64, 850, -30, -50, -30, true);
                class9.anInt233 = 5;
                class9.mediaID = 0;
                RSInterface.method208(0, aBoolean994, 5, class30_sub2_sub4_sub6);
            }
            return;
        }
        if (j == 324) {
            if (aClass30_Sub2_Sub1_Sub1_931 == null) {
                aClass30_Sub2_Sub1_Sub1_931 = class9.sprite1;
                aClass30_Sub2_Sub1_Sub1_932 = class9.sprite2;
            }
            if (aBoolean1047) {
                class9.sprite1 = aClass30_Sub2_Sub1_Sub1_932;
                return;
            } else {
                class9.sprite1 = aClass30_Sub2_Sub1_Sub1_931;
                return;
            }
        }
        if (j == 325) {
            if (aClass30_Sub2_Sub1_Sub1_931 == null) {
                aClass30_Sub2_Sub1_Sub1_931 = class9.sprite1;
                aClass30_Sub2_Sub1_Sub1_932 = class9.sprite2;
            }
            if (aBoolean1047) {
                class9.sprite1 = aClass30_Sub2_Sub1_Sub1_931;
                return;
            } else {
                class9.sprite1 = aClass30_Sub2_Sub1_Sub1_932;
                return;
            }
        }
        if (j == 600) {
            class9.message = reportAbuseInput;
            if (loopCycle % 20 < 10) {
                class9.message += "|";
                return;
            } else {
                class9.message += " ";
                return;
            }
        }
        if (j == 620) {
            if (anInt863 >= 1) {
                if (canMute) {
                    class9.textColor = 0xff0000;
                    class9.message = "Moderator option: Mute player for 48 hours: <ON>";
                } else {
                    class9.textColor = 0xffffff;
                    class9.message = "Moderator option: Mute player for 48 hours: <OFF>";
                }
            } else {
                class9.message = "";
            }
        }
        if (j == 650 || j == 655) {
            if (anInt1193 != 0) {
                String s;
                if (daysSinceLastLogin == 0) {
                    s = "earlier today";
                } else if (daysSinceLastLogin == 1) {
                    s = "yesterday";
                } else {
                    s = daysSinceLastLogin + " days ago";
                }
                class9.message = "You last logged in " + s + " from: " + signlink.dns;
            } else {
                class9.message = "";
            }
        }
        if (j == 651) {
            if (unreadMessages == 0) {
                class9.message = "0 unread messages";
                class9.textColor = 0xffff00;
            }
            if (unreadMessages == 1) {
                class9.message = "1 unread message";
                class9.textColor = 65280;
            }
            if (unreadMessages > 1) {
                class9.message = unreadMessages + " unread messages";
                class9.textColor = 65280;
            }
        }
        if (j == 652) {
            if (daysSinceRecovChange == 201) {
                if (membersInt == 1) {
                    class9.message = "@yel@This is a non-members world: @whi@Since you are a member we";
                } else {
                    class9.message = "";
                }
            } else if (daysSinceRecovChange == 200) {
                class9.message = "You have not yet set any password recovery questions.";
            } else {
                String s1;
                if (daysSinceRecovChange == 0) {
                    s1 = "Earlier today";
                } else if (daysSinceRecovChange == 1) {
                    s1 = "Yesterday";
                } else {
                    s1 = daysSinceRecovChange + " days ago";
                }
                class9.message = s1 + " you changed your recovery questions";
            }
        }
        if (j == 653) {
            if (daysSinceRecovChange == 201) {
                if (membersInt == 1) {
                    class9.message = "@whi@recommend you use a members world instead. You may use";
                } else {
                    class9.message = "";
                }
            } else if (daysSinceRecovChange == 200) {
                class9.message = "We strongly recommend you do so now to secure your account.";
            } else {
                class9.message = "If you do not remember making this change then cancel it immediately";
            }
        }
        if (j == 654) {
            if (daysSinceRecovChange == 201) {
                if (membersInt == 1) {
                    class9.message = "@whi@this world but member benefits are unavailable whilst here.";
                    return;
                } else {
                    class9.message = "";
                    return;
                }
            }
            if (daysSinceRecovChange == 200) {
                class9.message = "Do this from the 'account management' area on our front webpage";
                return;
            }
            class9.message = "Do this from the 'account management' area on our front webpage";
        }
    }

    public final void method76(byte byte0) {
        if (anInt1195 == 0) {
            return;
        }
        TextDrawingClass class30_sub2_sub1_sub4 = regularFont;
        if (byte0 != aByte1274) {
            aBoolean1231 = !aBoolean1231;
        }
        int i = 0;
        if (updateTime != 0) {
            i = 1;
        }
        for (int j = 0; j < 100; j++) {
            if (chatMessages[j] != null) {
                int k = chatTypes[j];
                String s = chatNames[j];
                byte byte1 = 0;
                if (s != null && s.startsWith("@cr1@")) {
                    s = s.substring(5);
                    byte1 = 1;
                }
                if (s != null && s.startsWith("@cr2@")) {
                    s = s.substring(5);
                    byte1 = 2;
                }
                if ((k == 3 || k == 7) && (k == 7 || privateChatMode == 0 || privateChatMode == 1 && method109(false, s))) {
                    int l = 329 - i * 13;
                    int k1 = 4;
                    class30_sub2_sub1_sub4.method385(0, "From", l, 822, k1);
                    class30_sub2_sub1_sub4.method385(65535, "From", l - 1, 822, k1);
                    k1 += class30_sub2_sub1_sub4.getTextWidth("From ");
                    if (byte1 == 1) {
                        modIcons[0].method348(k1, l - 12);

                        k1 += 14;
                    }
                    if (byte1 == 2) {
                        modIcons[1].method348(k1, l - 12);

                        k1 += 14;
                    }
                    class30_sub2_sub1_sub4.method385(0, s + ": " + chatMessages[j], l, 822, k1);
                    class30_sub2_sub1_sub4.method385(65535, s + ": " + chatMessages[j], l - 1, 822, k1);
                    if (++i >= 5) {
                        return;
                    }
                }
                if (k == 5 && privateChatMode < 2) {
                    int i1 = 329 - i * 13;
                    class30_sub2_sub1_sub4.method385(0, chatMessages[j], i1, 822, 4);
                    class30_sub2_sub1_sub4.method385(65535, chatMessages[j], i1 - 1, 822, 4);
                    if (++i >= 5) {
                        return;
                    }
                }
                if (k == 6 && privateChatMode < 2) {
                    int j1 = 329 - i * 13;
                    class30_sub2_sub1_sub4.method385(0, "To " + s + ": " + chatMessages[j], j1, 822, 4);
                    class30_sub2_sub1_sub4.method385(65535, "To " + s + ": " + chatMessages[j], j1 - 1, 822, 4);
                    if (++i >= 5) {
                        return;
                    }
                }
            }
        }

    }

    public final void pushMessage(String s, int i, String s1, boolean flag) {
        if (i == 0 && dialogID != -1) {
            aString844 = s;
            super.anInt26 = 0;
        }
        if (backDialogID == -1) {
            inputTaken = true;
        }
        for (int j = 99; j > 0; j--) {
            chatTypes[j] = chatTypes[j - 1];
            chatNames[j] = chatNames[j - 1];
            chatMessages[j] = chatMessages[j - 1];
        }
        chatTypes[0] = i;
        chatNames[0] = s1;
        chatMessages[0] = s;
        if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 6 || i == 7 || i == 16 || i == 18 || i == 36) {
            if (clickedTab[textDraw(i) + 1] == false && clickedTab[0] == false) {
                newTextDrawn[textDraw(i)] = true;
            }
        }
    }

    public final void processTabClick() {
        if (super.anInt26 == 1) { //click


            //top tabs


            if (super.saveClickX >= 524 && super.saveClickX <= 561 && super.saveClickY >= 168 && super.saveClickY < 204 && tabInterfaceIDs[0] != -1) {
                needDrawTabArea = true;
                tabID = 0;
                tabAreaAltered = true;
            }
            if (super.saveClickX >= 562 && super.saveClickX <= 594 && super.saveClickY >= 168 && super.saveClickY < 204 && tabInterfaceIDs[1] != -1) {
                needDrawTabArea = true;
                tabID = 1;
                tabAreaAltered = true;
            }
            if (super.saveClickX >= 595 && super.saveClickX <= 627 && super.saveClickY >= 168 && super.saveClickY < 204 && tabInterfaceIDs[2] != -1) {
                needDrawTabArea = true;
                tabID = 2;
                tabAreaAltered = true;
            }
            if (super.saveClickX >= 628 && super.saveClickX <= 660 && super.saveClickY >= 168 && super.saveClickY < 204 && tabInterfaceIDs[3] != -1) {
                needDrawTabArea = true;
                tabID = 3;
                tabAreaAltered = true;
            }
            if (super.saveClickX >= 661 && super.saveClickX <= 693 && super.saveClickY >= 168 && super.saveClickY < 204 && tabInterfaceIDs[4] != -1) {
                needDrawTabArea = true;
                tabID = 4;
                tabAreaAltered = true;
            }
            if (super.saveClickX >= 694 && super.saveClickX <= 726 && super.saveClickY >= 168 && super.saveClickY < 204 && tabInterfaceIDs[5] != -1) {
                needDrawTabArea = true;
                tabID = 5;
                tabAreaAltered = true;
            }
            if (super.saveClickX >= 727 && super.saveClickX <= 763 && super.saveClickY >= 168 && super.saveClickY < 204 && tabInterfaceIDs[6] != -1) {
                needDrawTabArea = true;
                tabID = 6;
                tabAreaAltered = true;
            }


            //bottom tabs


            if (super.saveClickX >= 524 && super.saveClickX <= 561 && super.saveClickY >= 467 && super.saveClickY < 503 && tabInterfaceIDs[7] != -1) {
                // needDrawTabArea = true;
                //  tabID = 7;
                //   tabAreaAltered = true;
            }
            if (super.saveClickX >= 562 && super.saveClickX <= 594 && super.saveClickY >= 467 && super.saveClickY < 503 && tabInterfaceIDs[8] != -1) {
                needDrawTabArea = true;
                tabID = 8;
                tabAreaAltered = true;
            }
            if (super.saveClickX >= 595 && super.saveClickX <= 627 && super.saveClickY >= 467 && super.saveClickY < 503 && tabInterfaceIDs[9] != -1) {
                needDrawTabArea = true;
                tabID = 9;
                tabAreaAltered = true;
            }
            //clan chat
            if (super.saveClickX >= 628 && super.saveClickX <= 660 && super.saveClickY >= 467 && super.saveClickY < 503 && tabInterfaceIDs[10] != -1) {
                needDrawTabArea = true;
                tabID = 14;
                tabAreaAltered = true;
            }

            //logoout
            if (super.saveClickX >= 765 - 21 && super.saveClickX <= 765 && super.saveClickY >= 1 && super.saveClickY < 23) {
                needDrawTabArea = true;
                tabID = 10;
                tabAreaAltered = true;
            }

            if (super.saveClickX >= 661 && super.saveClickX <= 693 && super.saveClickY >= 467 && super.saveClickY < 503 && tabInterfaceIDs[11] != -1) {
                needDrawTabArea = true;
                tabID = 11;
                tabAreaAltered = true;
            }
            if (super.saveClickX >= 694 && super.saveClickX <= 726 && super.saveClickY >= 467 && super.saveClickY < 503 && tabInterfaceIDs[12] != -1) {
                needDrawTabArea = true;
                tabID = 12;
                tabAreaAltered = true;
            }
            if (super.saveClickX >= 727 && super.saveClickX <= 763 && super.saveClickY >= 467 && super.saveClickY < 503 && tabInterfaceIDs[13] != -1) {
                needDrawTabArea = true;
                tabID = 13;
                tabAreaAltered = true;
            }




        }

    }

    public final void method79(int i) { //resetImageProducers2
        if (aClass15_1166 != null) //chat area`	
        {
            return;
        }
        nullLoader(3); //nullLoader
        super.aClass15_13 = null; //fullGameScreen
        aClass15_1107 = null;
        aClass15_1108 = null;
        loginArea = null;
        aClass15_1110 = null;
        aClass15_1111 = null;
        aClass15_1112 = null;
        aClass15_1113 = null;
        aClass15_1114 = null;
        aClass15_1115 = null;

        //this sets the dimentions of the aresas???

        aClass15_1166 = new RSImageProducer(506, 128, getGameComponent(), 0); //chat area


        aClass15_1164 = new RSImageProducer(250, 168, getGameComponent(), 0); //minimap drawing dimentions


        DrawingArea.setAllPixelsToZero();

        CustomMapback.method348(0, 0);

        aClass15_1163 = new RSImageProducer(192, 261, getGameComponent(), 0); //tab area?
        aClass15_1165 = new RSImageProducer(512, 334, getGameComponent(), 0);//game area??

        DrawingArea.setAllPixelsToZero();

        aClass15_1123 = new RSImageProducer(496, 31, getGameComponent(), 0); //bottom chat bit??

        if (i != 1) {
            startUp(); //startUp
        }
        aClass15_1124 = new RSImageProducer(269, 37, getGameComponent(), 0); //bottom tabs

        aClass15_1125 = new RSImageProducer(250, 37, getGameComponent(), 0); //top tabs

        welcomeScreenRaised = true;
    }

    public final String getDocumentBaseHost() {
        if (signlink.mainapp != null) {
            return signlink.mainapp.getDocumentBase().getHost().toLowerCase();
        }
        if (super.aFrame_Sub1_15 != null) {
            return "";
        } else {
            return super.getDocumentBase().getHost().toLowerCase();
        }
    }

    public final void drawMinimapArrow(Sprite class30_sub2_sub1_sub1, int ySpriteDraw, int xSpriteDraw) {
        int l = xSpriteDraw * xSpriteDraw + ySpriteDraw * ySpriteDraw;
        if (l > 4225 && l < 0x15f90) {
            int i1 = anInt1185 + minimapInt2 & 0x7ff;
            int j1 = Model.modelIntArray1[i1];
            int k1 = Model.modelIntArray2[i1];
            j1 = (j1 * 256) / (minimapInt3 + 256);
            k1 = (k1 * 256) / (minimapInt3 + 256);
            int l1 = ySpriteDraw * j1 + xSpriteDraw * k1 >> 16;
            int i2 = ySpriteDraw * k1 - xSpriteDraw * j1 >> 16;
            double d = Math.atan2(l1, i2);
            int j2 = (int) (Math.sin(d) * 63D);
            int k2 = (int) (Math.cos(d) * 57D);

            mapEdge.method353(87 - k2 - 20, 15, 20, 15, 256, 20, d, (107 + j2 + 4) - 10);


        } else {
            markMinimap(class30_sub2_sub1_sub1, xSpriteDraw, ySpriteDraw, false);

        }
    }

    public final void processRightClick() {
        if (activeInterfaceType != 0) {
            return;
        }
        menuActionName[0] = "Cancel";
        menuActionID[0] = 1107;
        menuActionRow = 1;
        buildSplitPrivateChatMenu();
        hoverSpriteid = 0;
        if (super.anInt20 > 4 && super.anInt21 > 4 && super.anInt20 < 515 && super.anInt21 < 339) {
            if (openInterfaceID != -1) {
                buildInterfaceMenu(4, 13037, RSInterface.interfaceCache[openInterfaceID], super.anInt20, 4, super.anInt21, 0);
            } else {
                build3dScreenMenu();
            }
        }

        if (hoverSpriteid != anInt1026) {
            anInt1026 = hoverSpriteid;
        }

        hoverSpriteid = 0;

        if (super.anInt20 > 553 && super.anInt21 > 205 && super.anInt20 < 743 && super.anInt21 < 466) { //inventory
            if (invOverlayInterfaceID != -1) { //there is an interface over the incventory = invOverlayInterfaceID
                buildInterfaceMenu(553, 13037, RSInterface.interfaceCache[invOverlayInterfaceID], super.anInt20, 205, super.anInt21, 0);
            } else {
                if (tabInterfaceIDs[tabID] != -1) {
                    buildInterfaceMenu(553, 13037, RSInterface.interfaceCache[tabInterfaceIDs[tabID]], super.anInt20, 205, super.anInt21, 0);
                }
            }
        }

        if (hoverSpriteid != anInt1048) {
            needDrawTabArea = true;
            anInt1048 = hoverSpriteid;
        }

        hoverSpriteid = 0;

        if (super.anInt20 > 6 && super.anInt21 > 344 && super.anInt20 < 496 && super.anInt21 < 503) { //chatbox
            if (backDialogID != -1) { //there is an interface in the chatbox open = backDialogID
                buildInterfaceMenu(6, 13037, RSInterface.interfaceCache[backDialogID], super.anInt20, 344, super.anInt21, 0);
            } else {
                if (super.anInt21 < 463 && super.anInt20 < 503) {
                    method74(super.anInt20 - 6, super.anInt21 - 344, 9);
                }
            }
        }

        if (backDialogID != -1 && hoverSpriteid != anInt1039) {
            inputTaken = true;
            anInt1039 = hoverSpriteid;
        }
        processExtraMenus();

        if (super.anInt20 >= 515 && super.anInt21 >= 0 && super.anInt20 <= 765 && super.anInt21 <= 167) {
            if (openInterfaceID != -1) {
                buildInterfaceMenu(0, 13037, RSInterface.interfaceCache[openInterfaceID], super.anInt20, 0, super.anInt21, 0);
            }
        }
        if (hoverSpriteid != anInt1026) {
            anInt1026 = hoverSpriteid;
        }

        hoverSpriteid = 0;
        boolean flag = false;
        while (!flag) {
            flag = true;
            for (int j = 0; j < menuActionRow - 1; j++) {
                if (menuActionID[j] < 1000 && menuActionID[j + 1] > 1000) {
                    String s = menuActionName[j];
                    menuActionName[j] = menuActionName[j + 1];
                    menuActionName[j + 1] = s;
                    int k = menuActionID[j];
                    menuActionID[j] = menuActionID[j + 1];
                    menuActionID[j + 1] = k;
                    k = menuActionCmd2[j];
                    menuActionCmd2[j] = menuActionCmd2[j + 1];
                    menuActionCmd2[j + 1] = k;
                    k = menuActionCmd3[j];
                    menuActionCmd3[j] = menuActionCmd3[j + 1];
                    menuActionCmd3[j + 1] = k;
                    k = menuActionCmd1[j];
                    menuActionCmd1[j] = menuActionCmd1[j + 1];
                    menuActionCmd1[j + 1] = k;
                    flag = false;
                }
            }
        }
    }

    public final int method83(int i, int j, int k) {
        int l = 256 - k;
        return ((i & 0xff00ff) * l + (j & 0xff00ff) * k & 0xff00ff00) + ((i & 0xff00) * l + (j & 0xff00) * k & 0xff0000) >> 8;
    }

    public final void login(String s, String s1, boolean flag) {
        signlink.errorname = s;
        try {
            saveinfo(); //save on login attemt
            if (!flag) {
                loginMessage1 = "Connecting to server,";
                loginMessage2 = "Please Wait...";
                drawLoginScreen(true);
            }
            try {
                aPort = Integer.parseInt(port);
            } catch (NumberFormatException NFE) {
            }
            aClass24_1168 = new RSSocket(this, openSocket(aPort));
            long l = TextClass.method583(s); //TextClass.longForName(s);
            int i = (int) (l >> 16 & 31L);


            aClass30_Sub2_Sub2_1192.currentOffset = 0;
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(14);
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(i);
            aClass24_1168.queueBytes(2, aClass30_Sub2_Sub2_1192.buffer, 0);
            for (int j = 0; j < 8; j++) {
                aClass24_1168.read();
            }
            int k = aClass24_1168.read(); //socketStream.read();
            int i1 = k;
            if (k == 0) {
                aClass24_1168.flushInputStream(aClass30_Sub2_Sub2_1083.buffer, 0, 8);
                aClass30_Sub2_Sub2_1083.currentOffset = 0;
                aLong1215 = aClass30_Sub2_Sub2_1083.readQWord();
                int ai[] = new int[4];
                ai[0] = (int) (Math.random() * 99999999D);
                ai[1] = (int) (Math.random() * 99999999D);
                ai[2] = (int) (aLong1215 >> 32);
                ai[3] = (int) aLong1215;
                aClass30_Sub2_Sub2_1192.currentOffset = 0;
                aClass30_Sub2_Sub2_1192.writeWordBigEndian(10);
                aClass30_Sub2_Sub2_1192.writeDWord(ai[0]);
                aClass30_Sub2_Sub2_1192.writeDWord(ai[1]);
                aClass30_Sub2_Sub2_1192.writeDWord(ai[2]);
                aClass30_Sub2_Sub2_1192.writeDWord(ai[3]);
                aClass30_Sub2_Sub2_1192.writeDWord(signlink.uid);
                aClass30_Sub2_Sub2_1192.writeString(s);
                aClass30_Sub2_Sub2_1192.writeString(s1);
                aClass30_Sub2_Sub2_1192.doKeys();
                aClass30_Sub2_Sub2_847.currentOffset = 0;
                if (flag) {
                    aClass30_Sub2_Sub2_847.writeWordBigEndian(18);
                } else {
                    aClass30_Sub2_Sub2_847.writeWordBigEndian(16);
                }
                aClass30_Sub2_Sub2_847.writeWordBigEndian(aClass30_Sub2_Sub2_1192.currentOffset + 36 + 1 + 1 + 2);
                aClass30_Sub2_Sub2_847.writeWordBigEndian(255);
                aClass30_Sub2_Sub2_847.writeWord(317);
                aClass30_Sub2_Sub2_847.writeWordBigEndian(lowMem ? 1 : 0);
                for (int l1 = 0; l1 < 9; l1++) {
                    aClass30_Sub2_Sub2_847.writeDWord(anIntArray1090[l1]);
                }
                aClass30_Sub2_Sub2_847.writeBytes(aClass30_Sub2_Sub2_1192.buffer, aClass30_Sub2_Sub2_1192.currentOffset, 0);
                aClass30_Sub2_Sub2_1192.encryption = new ISAACRandomGen(ai);
                for (int j2 = 0; j2 < 4; j2++) {
                    ai[j2] += 50;
                }
                aClass17_1000 = new ISAACRandomGen(ai);
                aClass24_1168.queueBytes(aClass30_Sub2_Sub2_847.currentOffset, aClass30_Sub2_Sub2_847.buffer, 0);
                k = aClass24_1168.read();
            }
            if (k == 1) {
                try {
                    Thread.sleep(2000L);
                } catch (Exception _ex) {
                }
                login(s, s1, flag);
                return;
            }
            if (k == 2) {
                anInt863 = aClass24_1168.read();
                flagged = aClass24_1168.read() == 1;
                aLong1220 = 0L;
                anInt1022 = 0;
                mouseDetection.anInt810 = 0;
                super.awtFocus = true;
                aBoolean954 = true;
                loggedIn = true;
                aClass30_Sub2_Sub2_1192.currentOffset = 0;
                aClass30_Sub2_Sub2_1083.currentOffset = 0;
                pktType = -1;
                anInt841 = -1;
                anInt842 = -1;
                anInt843 = -1;
                pktSize = 0;
                anInt1009 = 0;
                updateTime = 0;
                anInt1011 = 0;
                anInt855 = 0;
                menuActionRow = 0;
                menuOpen = false;
                super.idleTime = 0;
                for (int j1 = 0; j1 < 100; j1++) {
                    chatMessages[j1] = null;
                }
                anInt1282 = 0;
                anInt1136 = 0;
                loadingStage = 0;
                anInt1062 = 0;
                anInt1278 = (int) (Math.random() * 100D) - 50;
                anInt1131 = (int) (Math.random() * 110D) - 55;
                anInt896 = (int) (Math.random() * 80D) - 40;
                minimapInt2 = (int) (Math.random() * 120D) - 60;
                minimapInt3 = (int) (Math.random() * 30D) - 20;
                anInt1185 = (int) (Math.random() * 20D) - 10 & 0x7ff;
                minimapState = 0;
                anInt985 = -1;
                destX = 0;
                destY = 0;
                anInt891 = 0;
                npcCount = 0;
                for (int i2 = 0; i2 < maxPlayers; i2++) {
                    playerArray[i2] = null;
                    aClass30_Sub2_Sub2Array895[i2] = null;
                }
                for (int k2 = 0; k2 < 16384; k2++) {
                    npcArray[k2] = null;
                }
                aClass30_Sub2_Sub4_Sub1_Sub2_1126 = playerArray[anInt889] = new Player();
                aClass19_1013.RemoveAll();
                aClass19_1056.RemoveAll();
                for (int l2 = 0; l2 < 4; l2++) {
                    for (int i3 = 0; i3 < 104; i3++) {
                        for (int k3 = 0; k3 < 104; k3++) {
                            groundArray[l2][i3][k3] = null;
                        }
                    }
                }
                aClass19_1179 = new NodeList();
                anInt900 = 0;
                friendsCount = 0;
                dialogID = -1;
                backDialogID = -1;
                openInterfaceID = -1;
                invOverlayInterfaceID = -1;
                wildernessSkullId = -1;
                isInterfaceOpen = false;
                tabID = 3;
                inputDialogState = 0;
                menuOpen = false;
                messagePromptRaised = false;
                aString844 = null;
                multiOverlayIconID = 0;
                flashingSidebarId = -1;
                aBoolean1047 = true;
                method45(0);
                for (int j3 = 0; j3 < 5; j3++) {
                    anIntArray990[j3] = 0;
                }
                for (int l3 = 0; l3 < 5; l3++) {
                    atPlayerActions[l3] = null;
                    atPlayerArray[l3] = false;
                }
                anInt1175 = 0;
                anInt1134 = 0;
                anInt986 = 0;
                anInt1288 = 0;
                anInt924 = 0;
                anInt1188 = 0;
                anInt1155 = 0;
                anInt1226 = 0;
                anInt941 = 0;
                anInt1260 = 0;
                method79(1);
                Frame_Sub1.trayIcon.displayMessage("-GaMeR X-'s Client", "Login sucessfull: " + myUsername + "", TrayIcon.MessageType.INFO);
                return;
            }

            if (k == 3) {
                loginMessage1 = "Invalid username or password.";
                loginMessage2 = "";
                return;
            }

            if (k == 4) {
                loginMessage1 = "Your account has been disabled.";
                loginMessage2 = "";
                return;
            }

            if (k == 5) {
                loginMessage1 = "Your account is already logged in.";
                loginMessage2 = "Try again in 60 secs...";
                return;
            }

            if (k == 6) {
                loginMessage1 = "Server has been updated!";
                loginMessage2 = "Please reload your Client.";
                return;
            }

            if (k == 7) {
                loginMessage1 = "This world is full,";
                loginMessage2 = "Please use a different server.";
                return;
            }

            if (k == 8) {
                loginMessage1 = "Unable to connect,";
                loginMessage2 = "Login server offline.";
                return;
            }

            if (k == 9) {
                loginMessage1 = "Login limit exceeded,";
                loginMessage2 = "Too many connections from your address.";
                return;
            }

            if (k == 10) {
                loginMessage1 = "Unable to connect,";
                loginMessage2 = "Bad session id.";
                return;
            }

            if (k == 11) {
                loginMessage1 = "Login server rejected session,";
                loginMessage2 = "Please try again.";
                return;
            }

            if (k == 12) {
                loginMessage1 = "You need a members account to login to this world,";
                loginMessage2 = "Please subscribe, or use a different world.";
                return;
            }

            if (k == 13) {
                loginMessage1 = "Could not complete login,";
                loginMessage2 = "Please try using a different world.";
                return;
            }

            if (k == 14) {
                loginMessage1 = "The server is being updated,";
                loginMessage2 = "Please restart your Client.";
                return;
            }

            if (k == 15) {
                loggedIn = true;
                aClass30_Sub2_Sub2_1192.currentOffset = 0;
                aClass30_Sub2_Sub2_1083.currentOffset = 0;
                pktType = -1;
                anInt841 = -1;
                anInt842 = -1;
                anInt843 = -1;
                pktSize = 0;
                anInt1009 = 0;
                updateTime = 0;
                menuActionRow = 0;
                menuOpen = false;
                aLong824 = System.currentTimeMillis();
                return;
            }

            if (k == 16) {
                loginMessage1 = "Login attempts exceeded.";
                loginMessage2 = "Please wait 1 minute and try again.";
                return;
            }

            if (k == 17) {
                loginMessage1 = "You are standing in a members-only area,";
                loginMessage2 = "To play on this world move to a free area first.";
                return;
            }

            if (k == 20) {
                loginMessage1 = "Invalid login requested,";
                loginMessage2 = "Please try using a different world.";
                return;
            }

            if (k == 21) {
                for (int k1 = aClass24_1168.read(); k1 >= 0; k1--) {
                    loginMessage1 = "You have only just left another world,";
                    loginMessage2 = "Your profile will be transferred in: " + k1 + " seconds";
                    drawLoginScreen(true);
                    try {
                        Thread.sleep(1000L);
                    } catch (Exception _ex) {
                    }
                }
                login(s, s1, flag);
                return;
            }

            if (k == -1) {
                if (i1 == 0) {
                    if (loginFailures < 2) {
                        try {
                            Thread.sleep(2000L);
                        } catch (Exception _ex) {
                        }
                        loginFailures++;
                        login(s, s1, flag);
                        return;
                    } else {
                        loginMessage1 = "No response from Server,";
                        loginMessage2 = "Please wait 1 minute and try again.";
                        return;
                    }
                } else {
                    loginMessage1 = "No response from Server,";
                    loginMessage2 = "Please try using a different world.";
                    return;
                }
            } else {
                loginMessage1 = "Unexpected server response,";
                loginMessage2 = "Please try using a different world.";
                return;
            }
        } catch (IOException _ex) {
            loginMessage1 = "";
            loginMessage2 = "Unhandled Exeption.";
        }
        loginMessage1 = "";
        loginMessage2 = "Error Connecting to Server.";
    }

    public final boolean method85(int i, int j, int k, int l, int i1, int j1, int k1, int l1, int i2, int j2, boolean flag, int k2) {
        resetWalk();
        int j3 = j2;
        int k3 = j1;
        anIntArrayArray901[j2][j1] = 99;
        anIntArrayArray825[j2][j1] = 0;
        int l3 = 0;
        int i4 = 0;
        anIntArray1280[l3] = j2;
        anIntArray1281[l3++] = j1;
        boolean flag1 = false;
        int j4 = anIntArray1280.length;
        int ai[][] = aClass11Array1230[plane].anIntArrayArray294;
        while (i4 != l3) {
            j3 = anIntArray1280[i4];
            k3 = anIntArray1281[i4];
            i4 = (i4 + 1) % j4;
            if (j3 == k2 && k3 == i2) {
                flag1 = true;
                break;
            }
            if (i1 != 0) {
                if ((i1 < 5 || i1 == 10) && aClass11Array1230[plane].method219(k2, j3, k3, 0, j, i1 - 1, i2)) {
                    flag1 = true;
                    break;
                }
                if (i1 < 10 && aClass11Array1230[plane].method220(k2, i2, k3, i1 - 1, j, j3, 0)) {
                    flag1 = true;
                    break;
                }
            }
            if (k1 != 0 && k != 0 && aClass11Array1230[plane].method221((byte) 1, i2, k2, j3, k, l1, k1, k3)) {
                flag1 = true;
                break;
            }
            int l4 = anIntArrayArray825[j3][k3] + 1;
            if (j3 > 0 && anIntArrayArray901[j3 - 1][k3] == 0 && (ai[j3 - 1][k3] & 0x1280108) == 0) {
                anIntArray1280[l3] = j3 - 1;
                anIntArray1281[l3] = k3;
                l3 = (l3 + 1) % j4;
                anIntArrayArray901[j3 - 1][k3] = 2;
                anIntArrayArray825[j3 - 1][k3] = l4;
            }
            if (j3 < 103 && anIntArrayArray901[j3 + 1][k3] == 0 && (ai[j3 + 1][k3] & 0x1280180) == 0) {
                anIntArray1280[l3] = j3 + 1;
                anIntArray1281[l3] = k3;
                l3 = (l3 + 1) % j4;
                anIntArrayArray901[j3 + 1][k3] = 8;
                anIntArrayArray825[j3 + 1][k3] = l4;
            }
            if (k3 > 0 && anIntArrayArray901[j3][k3 - 1] == 0 && (ai[j3][k3 - 1] & 0x1280102) == 0) {
                anIntArray1280[l3] = j3;
                anIntArray1281[l3] = k3 - 1;
                l3 = (l3 + 1) % j4;
                anIntArrayArray901[j3][k3 - 1] = 1;
                anIntArrayArray825[j3][k3 - 1] = l4;
            }
            if (k3 < 103 && anIntArrayArray901[j3][k3 + 1] == 0 && (ai[j3][k3 + 1] & 0x1280120) == 0) {
                anIntArray1280[l3] = j3;
                anIntArray1281[l3] = k3 + 1;
                l3 = (l3 + 1) % j4;
                anIntArrayArray901[j3][k3 + 1] = 4;
                anIntArrayArray825[j3][k3 + 1] = l4;
            }
            if (j3 > 0 && k3 > 0 && anIntArrayArray901[j3 - 1][k3 - 1] == 0 && (ai[j3 - 1][k3 - 1] & 0x128010e) == 0 && (ai[j3 - 1][k3] & 0x1280108) == 0 && (ai[j3][k3 - 1] & 0x1280102) == 0) {
                anIntArray1280[l3] = j3 - 1;
                anIntArray1281[l3] = k3 - 1;
                l3 = (l3 + 1) % j4;
                anIntArrayArray901[j3 - 1][k3 - 1] = 3;
                anIntArrayArray825[j3 - 1][k3 - 1] = l4;
            }
            if (j3 < 103 && k3 > 0 && anIntArrayArray901[j3 + 1][k3 - 1] == 0 && (ai[j3 + 1][k3 - 1] & 0x1280183) == 0 && (ai[j3 + 1][k3] & 0x1280180) == 0 && (ai[j3][k3 - 1] & 0x1280102) == 0) {
                anIntArray1280[l3] = j3 + 1;
                anIntArray1281[l3] = k3 - 1;
                l3 = (l3 + 1) % j4;
                anIntArrayArray901[j3 + 1][k3 - 1] = 9;
                anIntArrayArray825[j3 + 1][k3 - 1] = l4;
            }
            if (j3 > 0 && k3 < 103 && anIntArrayArray901[j3 - 1][k3 + 1] == 0 && (ai[j3 - 1][k3 + 1] & 0x1280138) == 0 && (ai[j3 - 1][k3] & 0x1280108) == 0 && (ai[j3][k3 + 1] & 0x1280120) == 0) {
                anIntArray1280[l3] = j3 - 1;
                anIntArray1281[l3] = k3 + 1;
                l3 = (l3 + 1) % j4;
                anIntArrayArray901[j3 - 1][k3 + 1] = 6;
                anIntArrayArray825[j3 - 1][k3 + 1] = l4;
            }
            if (j3 < 103 && k3 < 103 && anIntArrayArray901[j3 + 1][k3 + 1] == 0 && (ai[j3 + 1][k3 + 1] & 0x12801e0) == 0 && (ai[j3 + 1][k3] & 0x1280180) == 0 && (ai[j3][k3 + 1] & 0x1280120) == 0) {
                anIntArray1280[l3] = j3 + 1;
                anIntArray1281[l3] = k3 + 1;
                l3 = (l3 + 1) % j4;
                anIntArrayArray901[j3 + 1][k3 + 1] = 12;
                anIntArrayArray825[j3 + 1][k3 + 1] = l4;
            }
        }
        anInt1264 = 0;
        if (!flag1) {
            if (flag) {
                int i5 = 100;
                for (int k5 = 1; k5 < 2; k5++) {
                    for (int i6 = k2 - k5; i6 <= k2 + k5; i6++) {
                        for (int l6 = i2 - k5; l6 <= i2 + k5; l6++) {
                            if (i6 >= 0 && l6 >= 0 && i6 < 104 && l6 < 104 && anIntArrayArray825[i6][l6] < i5) {
                                i5 = anIntArrayArray825[i6][l6];
                                j3 = i6;
                                k3 = l6;
                                anInt1264 = 1;
                                flag1 = true;
                            }
                        }

                    }

                    if (flag1) {
                        break;
                    }
                }

            }
            if (!flag1) {
                return false;
            }
        }
        i4 = 0;
        anIntArray1280[i4] = j3;
        anIntArray1281[i4++] = k3;
        if (l != -11308) {
            for (int j6 = 1; j6 > 0; j6++);
        }
        int l5;
        for (int j5 = l5 = anIntArrayArray901[j3][k3]; j3 != j2 || k3 != j1; j5 = anIntArrayArray901[j3][k3]) {
            if (j5 != l5) {
                l5 = j5;
                anIntArray1280[i4] = j3;
                anIntArray1281[i4++] = k3;
            }
            if ((j5 & 2) != 0) {
                j3++;
            } else if ((j5 & 8) != 0) {
                j3--;
            }
            if ((j5 & 1) != 0) {
                k3++;
            } else if ((j5 & 4) != 0) {
                k3--;
            }
        }

        if (i4 > 0) {
            int k4 = i4;
            if (k4 > 25) {
                k4 = 25;
            }
            i4--;
            int k6 = anIntArray1280[i4];
            int i7 = anIntArray1281[i4];
            anInt1288 += k4;
            if (anInt1288 >= 92) {
                aClass30_Sub2_Sub2_1192.createFrame(36);
                aClass30_Sub2_Sub2_1192.writeDWord(0);
                anInt1288 = 0;
            }
            if (i == 0) {
                aClass30_Sub2_Sub2_1192.createFrame(164);
                aClass30_Sub2_Sub2_1192.writeWordBigEndian(k4 + k4 + 3);
            }
            if (i == 1) {
                aClass30_Sub2_Sub2_1192.createFrame(248);
                aClass30_Sub2_Sub2_1192.writeWordBigEndian(k4 + k4 + 3 + 14);
            }
            if (i == 2) {
                aClass30_Sub2_Sub2_1192.createFrame(98);
                aClass30_Sub2_Sub2_1192.writeWordBigEndian(k4 + k4 + 3);
            }
            aClass30_Sub2_Sub2_1192.method433(k6 + baseX);
            destX = anIntArray1280[0];
            destY = anIntArray1281[0];
            for (int j7 = 1; j7 < k4; j7++) {
                i4--;
                aClass30_Sub2_Sub2_1192.writeWordBigEndian(anIntArray1280[i4] - k6);
                aClass30_Sub2_Sub2_1192.writeWordBigEndian(anIntArray1281[i4] - i7);
            }

            aClass30_Sub2_Sub2_1192.method431(i7 + baseY);
            aClass30_Sub2_Sub2_1192.method424(super.keyArray[5] != 1 ? 0 : 1);
            return true;
        }
        return i != 1;
    }

    public void resetWalk() {
        for (int l2 = 0; l2 < 104; l2++) {
            for (int i3 = 0; i3 < 104; i3++) {
                anIntArrayArray901[l2][i3] = 0;
                anIntArrayArray825[l2][i3] = 0x5f5e0ff;
            }
        }
    }

    private final void method86(Stream class30_sub2_sub2) {
        for (int j = 0; j < anInt893; j++) {
            int k = anIntArray894[j];
            NPC class30_sub2_sub4_sub1_sub1 = npcArray[k];
            int l = class30_sub2_sub2.readUnsignedByte();
            if ((l & 0x10) != 0) {
                int i1 = class30_sub2_sub2.method434((byte) 108);
                if (i1 == 65535) {
                    i1 = -1;
                }
                int i2 = class30_sub2_sub2.readUnsignedByte();
                if (i1 == ((Entity) (class30_sub2_sub4_sub1_sub1)).anim && i1 != -1) {
                    int l2 = Animation.anims[i1].anInt365;
                    if (l2 == 1) {
                        class30_sub2_sub4_sub1_sub1.anInt1527 = 0;
                        class30_sub2_sub4_sub1_sub1.anInt1528 = 0;
                        class30_sub2_sub4_sub1_sub1.anInt1529 = i2;
                        class30_sub2_sub4_sub1_sub1.anInt1530 = 0;
                    }
                    if (l2 == 2) {
                        class30_sub2_sub4_sub1_sub1.anInt1530 = 0;
                    }
                } else if (i1 == -1 || ((Entity) (class30_sub2_sub4_sub1_sub1)).anim == -1 || Animation.anims[i1].anInt359 >= Animation.anims[((Entity) (class30_sub2_sub4_sub1_sub1)).anim].anInt359) {
                    class30_sub2_sub4_sub1_sub1.anim = i1;
                    class30_sub2_sub4_sub1_sub1.anInt1527 = 0;
                    class30_sub2_sub4_sub1_sub1.anInt1528 = 0;
                    class30_sub2_sub4_sub1_sub1.anInt1529 = i2;
                    class30_sub2_sub4_sub1_sub1.anInt1530 = 0;
                    class30_sub2_sub4_sub1_sub1.anInt1542 = ((Entity) (class30_sub2_sub4_sub1_sub1)).smallXYIndex;
                }
            }
            if ((l & 8) != 0) {
                int j1 = class30_sub2_sub2.method426();
                int j2 = class30_sub2_sub2.method427();
                class30_sub2_sub4_sub1_sub1.updateHitData(-35698, j2, j1, loopCycle);
                class30_sub2_sub4_sub1_sub1.loopCycleStatus = loopCycle + 300;
                class30_sub2_sub4_sub1_sub1.currentHealth = class30_sub2_sub2.method426();
                class30_sub2_sub4_sub1_sub1.maxHealth = class30_sub2_sub2.readUnsignedByte();
            }
            if ((l & 0x80) != 0) {
                class30_sub2_sub4_sub1_sub1.anInt1520 = class30_sub2_sub2.readUnsignedWord();
                int k1 = class30_sub2_sub2.readDWord();
                class30_sub2_sub4_sub1_sub1.anInt1524 = k1 >> 16;
                class30_sub2_sub4_sub1_sub1.anInt1523 = loopCycle + (k1 & 0xffff);
                class30_sub2_sub4_sub1_sub1.anInt1521 = 0;
                class30_sub2_sub4_sub1_sub1.anInt1522 = 0;
                if (((Entity) (class30_sub2_sub4_sub1_sub1)).anInt1523 > loopCycle) {
                    class30_sub2_sub4_sub1_sub1.anInt1521 = -1;
                }
                if (((Entity) (class30_sub2_sub4_sub1_sub1)).anInt1520 == 65535) {
                    class30_sub2_sub4_sub1_sub1.anInt1520 = -1;
                }
            }
            if ((l & 0x20) != 0) {
                class30_sub2_sub4_sub1_sub1.interactingEntity = class30_sub2_sub2.readUnsignedWord();
                if (((Entity) (class30_sub2_sub4_sub1_sub1)).interactingEntity == 65535) {
                    class30_sub2_sub4_sub1_sub1.interactingEntity = -1;
                }
            }
            if ((l & 1) != 0) {
                class30_sub2_sub4_sub1_sub1.aString1506 = class30_sub2_sub2.readString();
                class30_sub2_sub4_sub1_sub1.textCycle = 100;
            }
            if ((l & 0x40) != 0) {
                int l1 = class30_sub2_sub2.method427();
                int k2 = class30_sub2_sub2.method428();
                class30_sub2_sub4_sub1_sub1.updateHitData(-35698, k2, l1, loopCycle);
                class30_sub2_sub4_sub1_sub1.loopCycleStatus = loopCycle + 300;
                class30_sub2_sub4_sub1_sub1.currentHealth = class30_sub2_sub2.method428();
                class30_sub2_sub4_sub1_sub1.maxHealth = class30_sub2_sub2.method427();
            }
            if ((l & 2) != 0) {
                class30_sub2_sub4_sub1_sub1.desc = EntityDef.forID(class30_sub2_sub2.method436());
                class30_sub2_sub4_sub1_sub1.anInt1540 = class30_sub2_sub4_sub1_sub1.desc.aByte68;
                class30_sub2_sub4_sub1_sub1.anInt1504 = class30_sub2_sub4_sub1_sub1.desc.anInt79;
                class30_sub2_sub4_sub1_sub1.anInt1554 = class30_sub2_sub4_sub1_sub1.desc.anInt67;
                class30_sub2_sub4_sub1_sub1.anInt1555 = class30_sub2_sub4_sub1_sub1.desc.anInt58;
                class30_sub2_sub4_sub1_sub1.anInt1556 = class30_sub2_sub4_sub1_sub1.desc.anInt83;
                class30_sub2_sub4_sub1_sub1.anInt1557 = class30_sub2_sub4_sub1_sub1.desc.anInt55;
                class30_sub2_sub4_sub1_sub1.anInt1511 = class30_sub2_sub4_sub1_sub1.desc.anInt77;
            }
            if ((l & 4) != 0) {
                class30_sub2_sub4_sub1_sub1.anInt1538 = class30_sub2_sub2.method434((byte) 108);
                class30_sub2_sub4_sub1_sub1.anInt1539 = class30_sub2_sub2.method434((byte) 108);
            }
        }
    }

    public final void method87(EntityDef class5, int i, boolean flag, int j, int k) {
        if (menuActionRow >= 400) {
            return;
        }
        if (class5.childrenIDs != null) {
            class5 = class5.method161(anInt877);
        }
        if (class5 == null) {
            return;
        }
        if (!class5.aBoolean84) {
            return;
        }
        String s = class5.name;
        if (flag) {
            aBoolean919 = !aBoolean919;
        }
        if (class5.combatLevel != 0) {
            s = s + method110(aClass30_Sub2_Sub4_Sub1_Sub2_1126.combatLevel, class5.combatLevel, true) + " (level-" + class5.combatLevel + ")";
        }
        if (anInt1282 == 1) {
            menuActionName[menuActionRow] = "Use " + aString1286 + " with @yel@" + s;
            menuActionID[menuActionRow] = 582;
            menuActionCmd1[menuActionRow] = i;
            menuActionCmd2[menuActionRow] = k;
            menuActionCmd3[menuActionRow] = j;
            menuActionRow++;
            return;
        }
        if (anInt1136 == 1) {
            if ((anInt1138 & 2) == 2) {
                menuActionName[menuActionRow] = aString1139 + " @yel@" + s;
                menuActionID[menuActionRow] = 413;
                menuActionCmd1[menuActionRow] = i;
                menuActionCmd2[menuActionRow] = k;
                menuActionCmd3[menuActionRow] = j;
                menuActionRow++;
                return;
            }
        } else {
            if (class5.actions != null) {
                for (int l = 4; l >= 0; l--) {
                    if (class5.actions[l] != null && !class5.actions[l].equalsIgnoreCase("attack")) {
                        menuActionName[menuActionRow] = class5.actions[l] + " @yel@" + s;
                        if (l == 0) {
                            menuActionID[menuActionRow] = 20;
                        }
                        if (l == 1) {
                            menuActionID[menuActionRow] = 412;
                        }
                        if (l == 2) {
                            menuActionID[menuActionRow] = 225;
                        }
                        if (l == 3) {
                            menuActionID[menuActionRow] = 965;
                        }
                        if (l == 4) {
                            menuActionID[menuActionRow] = 478;
                        }
                        menuActionCmd1[menuActionRow] = i;
                        menuActionCmd2[menuActionRow] = k;
                        menuActionCmd3[menuActionRow] = j;
                        menuActionRow++;
                    }
                }
            }
            if (class5.actions != null) {
                for (int i1 = 4; i1 >= 0; i1--) {
                    if (class5.actions[i1] != null && class5.actions[i1].equalsIgnoreCase("attack")) {
                        char c = '\0';
                        if (class5.combatLevel > aClass30_Sub2_Sub4_Sub1_Sub2_1126.combatLevel) {
                            c = '\u07D0';
                        }
                        menuActionName[menuActionRow] = class5.actions[i1] + " @yel@" + s;
                        if (i1 == 0) {
                            menuActionID[menuActionRow] = 20 + c;
                        }
                        if (i1 == 1) {
                            menuActionID[menuActionRow] = 412 + c;
                        }
                        if (i1 == 2) {
                            menuActionID[menuActionRow] = 225 + c;
                        }
                        if (i1 == 3) {
                            menuActionID[menuActionRow] = 965 + c;
                        }
                        if (i1 == 4) {
                            menuActionID[menuActionRow] = 478 + c;
                        }
                        menuActionCmd1[menuActionRow] = i;
                        menuActionCmd2[menuActionRow] = k;
                        menuActionCmd3[menuActionRow] = j;
                        menuActionRow++;
                    }
                }
            }
            if (anInt863 >= 2) {
                menuActionName[menuActionRow] = "Examine (NPC) @yel@" + s + " @gre@(" + "@whi@" + class5.type + "@gre@)";
            } else {
                menuActionName[menuActionRow] = "Examine @yel@" + s;
            }
            menuActionID[menuActionRow] = 1025;
            menuActionCmd1[menuActionRow] = i;
            menuActionCmd2[menuActionRow] = k;
            menuActionCmd3[menuActionRow] = j;
            menuActionRow++;
        }
    }

    public final void method88(int i, int j, Player class30_sub2_sub4_sub1_sub2, boolean flag, int k) {
        if (class30_sub2_sub4_sub1_sub2 == aClass30_Sub2_Sub4_Sub1_Sub2_1126) {
            return;
        }
        if (menuActionRow >= 400) {
            return;
        }
        if (flag) {
            return;
        }
        String s;
        if (class30_sub2_sub4_sub1_sub2.skill == 0) {
            s = class30_sub2_sub4_sub1_sub2.name + method110(aClass30_Sub2_Sub4_Sub1_Sub2_1126.combatLevel, class30_sub2_sub4_sub1_sub2.combatLevel, true) + " (level-" + class30_sub2_sub4_sub1_sub2.combatLevel + ")";
        } else {
            s = class30_sub2_sub4_sub1_sub2.name + " (skill-" + class30_sub2_sub4_sub1_sub2.skill + ")";
        }
        if (anInt1282 == 1) {
            menuActionName[menuActionRow] = "Use " + aString1286 + " with @whi@" + s;
            menuActionID[menuActionRow] = 491;
            menuActionCmd1[menuActionRow] = j;
            menuActionCmd2[menuActionRow] = i;
            menuActionCmd3[menuActionRow] = k;
            menuActionRow++;
        } else if (anInt1136 == 1) {
            if ((anInt1138 & 8) == 8) {
                menuActionName[menuActionRow] = aString1139 + " @whi@" + s;
                menuActionID[menuActionRow] = 365;
                menuActionCmd1[menuActionRow] = j;
                menuActionCmd2[menuActionRow] = i;
                menuActionCmd3[menuActionRow] = k;
                menuActionRow++;
            }
        } else {
            for (int l = 4; l >= 0; l--) {
                if (atPlayerActions[l] != null) {
                    menuActionName[menuActionRow] = atPlayerActions[l] + " @whi@" + s;
                    char c = '\0';
                    if (atPlayerActions[l].equalsIgnoreCase("attack")) {
                        if (class30_sub2_sub4_sub1_sub2.combatLevel > aClass30_Sub2_Sub4_Sub1_Sub2_1126.combatLevel) {
                            c = '\u07D0';
                        }
                        if (aClass30_Sub2_Sub4_Sub1_Sub2_1126.team != 0 && class30_sub2_sub4_sub1_sub2.team != 0) {
                            if (aClass30_Sub2_Sub4_Sub1_Sub2_1126.team == class30_sub2_sub4_sub1_sub2.team) {
                                c = '\u07D0';
                            } else {
                                c = '\0';
                            }
                        }
                    } else if (atPlayerArray[l]) {
                        c = '\u07D0';
                    }
                    if (l == 0) {
                        menuActionID[menuActionRow] = 561 + c;
                    }
                    if (l == 1) {
                        menuActionID[menuActionRow] = 779 + c;
                    }
                    if (l == 2) {
                        menuActionID[menuActionRow] = 27 + c;
                    }
                    if (l == 3) {
                        menuActionID[menuActionRow] = 577 + c;
                    }
                    if (l == 4) {
                        menuActionID[menuActionRow] = 729 + c;
                    }
                    menuActionCmd1[menuActionRow] = j;
                    menuActionCmd2[menuActionRow] = i;
                    menuActionCmd3[menuActionRow] = k;
                    menuActionRow++;
                }
            }
        }
        for (int i1 = 0; i1 < menuActionRow; i1++) {
            if (menuActionID[i1] == 516) {
                return;
            }
        }
    }

    private final void method89(boolean flag, Class30_Sub1 class30_sub1) {
        int i = 0;
        int j = -1;
        int k = 0;
        int l = 0;
        if (class30_sub1.anInt1296 == 0) {
            i = aClass25_946.method300(class30_sub1.anInt1295, class30_sub1.anInt1297, class30_sub1.anInt1298);
        }
        if (class30_sub1.anInt1296 == 1) {
            i = aClass25_946.method301(class30_sub1.anInt1295, class30_sub1.anInt1297, 0, class30_sub1.anInt1298);
        }
        if (class30_sub1.anInt1296 == 2) {
            i = aClass25_946.method302(class30_sub1.anInt1295, class30_sub1.anInt1297, class30_sub1.anInt1298);
        }
        if (class30_sub1.anInt1296 == 3) {
            i = aClass25_946.method303(class30_sub1.anInt1295, class30_sub1.anInt1297, class30_sub1.anInt1298);
        }
        if (i != 0) {
            int i1 = aClass25_946.method304(class30_sub1.anInt1295, class30_sub1.anInt1297, class30_sub1.anInt1298, i);
            j = i >> 14 & 0x7fff;
            k = i1 & 0x1f;
            l = i1 >> 6;
        }
        class30_sub1.anInt1299 = j;
        class30_sub1.anInt1301 = k;
        if (flag) {
            for (int j1 = 1; j1 > 0; j1++);
        }
        class30_sub1.anInt1300 = l;
    }

    public final void method90(boolean flag) {
        if (flag) {
            pktType = -1;
        }
        for (int i = 0; i < anInt1062; i++) {
            if (anIntArray1250[i] <= 0) {
                boolean flag1 = false;
                try {
                    if (anIntArray1207[i] == anInt874 && anIntArray1241[i] == anInt1289) {
                        if (!method27(11456)) {
                            flag1 = true;
                        }
                    } else {
                        Stream class30_sub2_sub2 = Sounds.method241(anIntArray1241[i], anIntArray1207[i]);
                        if (System.currentTimeMillis() + (long) (class30_sub2_sub2.currentOffset / 22) > aLong1172 + (long) (anInt1257 / 22)) {
                            anInt1257 = class30_sub2_sub2.currentOffset;
                            aLong1172 = System.currentTimeMillis();
                            if (saveWave(class30_sub2_sub2.buffer, class30_sub2_sub2.currentOffset)) {
                                anInt874 = anIntArray1207[i];
                                anInt1289 = anIntArray1241[i];
                            } else {
                                flag1 = true;
                            }
                        }
                    }
                } catch (Exception exception) {
                }
                if (!flag1 || anIntArray1250[i] == -5) {
                    anInt1062--;
                    for (int j = i; j < anInt1062; j++) {
                        anIntArray1207[j] = anIntArray1207[j + 1];
                        anIntArray1241[j] = anIntArray1241[j + 1];
                        anIntArray1250[j] = anIntArray1250[j + 1];
                    }

                    i--;
                } else {
                    anIntArray1250[i] = -5;
                }
            } else {
                anIntArray1250[i]--;
            }
        }

        if (prevSong > 0) {
            prevSong -= 20;
            if (prevSong < 0) {
                prevSong = 0;
            }
            if (prevSong == 0 && musicEnabled && !lowMem) {
                nextSong = currentSong;
                songChanging = true;
                aClass42_Sub1_1068.method558(2, nextSong);
            }
        }
    }

    @Override
    public final void startUp() {
        drawLoadingText(9, "Starting up Client");
        
        getCache();



        LoadingText = new Sprite(sign.signlink.findcachedir() + "sprites/titlescreen/load/custom-bg.png");
        LoadingFull = new Sprite(sign.signlink.findcachedir() + "sprites/titlescreen/load/custom-full.png");

        NewLoadBase = new Sprite(sign.signlink.findcachedir() + "sprites/titlescreen/load/rs-bg.png");
        NewLoadFill = new Sprite(sign.signlink.findcachedir() + "sprites/titlescreen/load/rs-full.png");





        if (signlink.sunjava) {
            super.minDelay = 5;
        }

        //aBoolean993 = true;
        //String s = getDocumentBaseHost();

        if (signlink.cache_dat != null) {
            for (int i = 0; i < 5; i++) {
                aClass14Array970[i] = new Decompressor(0xffffff, signlink.cache_dat, signlink.cache_idx[i], i + 1, true);
            }
        }

        try {
            //load title screen
            aClass44_1053 = streamLoaderForName(1, "title screen", "title", anIntArray1090[1], (byte) -41, 25);
            //load fonts
            smallFont = new TextDrawingClass(false, "p11_full", aClass44_1053);
            regularFont = new TextDrawingClass(false, "p12_full", aClass44_1053);
            boldFont = new TextDrawingClass(false, "b12_full", aClass44_1053);
            fancyFont = new TextDrawingClass(true, "q8_full", aClass44_1053);
            drawLogo();
            loadtitlescreen();
            //load some more stoff :P
            StreamLoader class44 = streamLoaderForName(2, "config", "config", anIntArray1090[2], (byte) -41, 30);
            StreamLoader class44_1 = streamLoaderForName(3, "interface", "interface", anIntArray1090[3], (byte) -41, 35);
            StreamLoader class44_2 = streamLoaderForName(4, "2d graphics", "media", anIntArray1090[4], (byte) -41, 40);
            StreamLoader class44_3 = streamLoaderForName(6, "textures", "textures", anIntArray1090[6], (byte) -41, 45);
            StreamLoader class44_4 = streamLoaderForName(7, "chat system", "wordenc", anIntArray1090[7], (byte) -41, 50);
            StreamLoader class44_5 = streamLoaderForName(8, "sound effects", "sounds", anIntArray1090[8], (byte) -41, 55);
            byteGroundArray = new byte[4][104][104];
            intGroundArray = new int[4][105][105];

            aClass25_946 = new WorldController(104, 104, intGroundArray, 4);


            for (int j = 0; j < 4; j++) {
                aClass11Array1230[j] = new Class11(104, 104, true);
            }
            aClass30_Sub2_Sub1_Sub1_1263 = new Sprite(512, 512);
            StreamLoader class44_6 = streamLoaderForName(5, "update list", "versionlist", anIntArray1090[5], (byte) -41, 60);

            drawLoadingText(18, "Connecting to update server");
            loadinfo();

            //various updates??
            aClass42_Sub1_1068 = new OnDemandFetcher();
            aClass42_Sub1_1068.start(class44_6, this);
            Class36.method528(aClass42_Sub1_1068.getAnimCount());
            Model.method459(aClass42_Sub1_1068.getVersionCount(79, 0), aClass42_Sub1_1068);

            //on demand music maybe?
            if (!lowMem) {
                nextSong = 0;
                try {
                    nextSong = Integer.parseInt(getParameter("music"));
                } catch (Exception _ex) {
                }
                songChanging = true;
                aClass42_Sub1_1068.method558(2, nextSong);
                while (aClass42_Sub1_1068.getNodeCount() > 0) {
                    processOnDemandQueue(); //on demand que
                    try {
                        Thread.sleep(100L);
                    } catch (Exception _ex) {
                    }
                    if (aClass42_Sub1_1068.anInt1349 > 3) {
                        method28("ondemand");
                        return;
                    }
                }
            }

            //ANIMATIONS
            drawLoadingText(27, "Requesting animations");

            int k = aClass42_Sub1_1068.getVersionCount(79, 1);
            for (int i1 = 0; i1 < k; i1++) {
                aClass42_Sub1_1068.method558(1, i1);
            }

            //on demand anims..
            while (aClass42_Sub1_1068.getNodeCount() > 0) {
                int j1 = k - aClass42_Sub1_1068.getNodeCount();
                if (j1 > 0) {
                    drawLoadingText(27, "Loading animations - " + (j1 * 100) / k + "%"); //loading text
                }
                processOnDemandQueue(); //process on demand que.. not
                try {
                    Thread.sleep(100L);
                } catch (Exception _ex) {
                }
                if (aClass42_Sub1_1068.anInt1349 > 3) {
                    method28("ondemand");
                    return;
                }
            }

            //MODELS LOADING
            drawLoadingText(36, "Requesting models");

            k = aClass42_Sub1_1068.getVersionCount(79, 0);
            for (int k1 = 0; k1 < k; k1++) {
                int l1 = aClass42_Sub1_1068.getModelIndex(k1);
                if ((l1 & 1) != 0) {
                    aClass42_Sub1_1068.method558(0, k1);
                }
            }

            k = aClass42_Sub1_1068.getNodeCount();

            //loading text
            while (aClass42_Sub1_1068.getNodeCount() > 0) {
                int i2 = k - aClass42_Sub1_1068.getNodeCount();
                if (i2 > 0) {
                    drawLoadingText(36, "Loading models - " + (i2 * 100) / k + "%");
                }
                processOnDemandQueue();
                try {
                    Thread.sleep(100L);
                } catch (Exception _ex) {
                }
            }

            if (aClass14Array970[0] != null) { //decompressors

                drawLoadingText(45, "Requesting maps");
                aClass42_Sub1_1068.method558(3, aClass42_Sub1_1068.method562(0, 0, 48, 47));
                aClass42_Sub1_1068.method558(3, aClass42_Sub1_1068.method562(1, 0, 48, 47));
                aClass42_Sub1_1068.method558(3, aClass42_Sub1_1068.method562(0, 0, 48, 48));
                aClass42_Sub1_1068.method558(3, aClass42_Sub1_1068.method562(1, 0, 48, 48));
                aClass42_Sub1_1068.method558(3, aClass42_Sub1_1068.method562(0, 0, 48, 49));
                aClass42_Sub1_1068.method558(3, aClass42_Sub1_1068.method562(1, 0, 48, 49));
                aClass42_Sub1_1068.method558(3, aClass42_Sub1_1068.method562(0, 0, 47, 47));
                aClass42_Sub1_1068.method558(3, aClass42_Sub1_1068.method562(1, 0, 47, 47));
                aClass42_Sub1_1068.method558(3, aClass42_Sub1_1068.method562(0, 0, 47, 48));
                aClass42_Sub1_1068.method558(3, aClass42_Sub1_1068.method562(1, 0, 47, 48));
                aClass42_Sub1_1068.method558(3, aClass42_Sub1_1068.method562(0, 0, 148, 48));
                aClass42_Sub1_1068.method558(3, aClass42_Sub1_1068.method562(1, 0, 148, 48));
                k = aClass42_Sub1_1068.getNodeCount();

                //process on demand que
                while (aClass42_Sub1_1068.getNodeCount() > 0) {
                    int j2 = k - aClass42_Sub1_1068.getNodeCount();
                    if (j2 > 0) {
                        drawLoadingText(45, "Loading maps - " + (j2 * 100) / k + "%");
                    }
                    processOnDemandQueue();
                    try {
                        Thread.sleep(100L);
                    } catch (Exception _ex) {
                    }
                }

            }

            k = aClass42_Sub1_1068.getVersionCount(79, 0);
            for (int k2 = 0; k2 < k; k2++) {
                int l2 = aClass42_Sub1_1068.getModelIndex(k2);
                byte byte0 = 0;
                if ((l2 & 8) != 0) {
                    byte0 = 10;
                } else if ((l2 & 0x20) != 0) {
                    byte0 = 9;
                } else if ((l2 & 0x10) != 0) {
                    byte0 = 8;
                } else if ((l2 & 0x40) != 0) {
                    byte0 = 7;
                } else if ((l2 & 0x80) != 0) {
                    byte0 = 6;
                } else if ((l2 & 2) != 0) {
                    byte0 = 5;
                } else if ((l2 & 4) != 0) {
                    byte0 = 4;
                }
                if ((l2 & 1) != 0) {
                    byte0 = 3;
                }
                if (byte0 != 0) {
                    aClass42_Sub1_1068.method563(byte0, 0, k2, (byte) 8);
                }
            }

            aClass42_Sub1_1068.method554(isMembers, 0);
            if (!lowMem) {
                int l = aClass42_Sub1_1068.getVersionCount(79, 2);
                for (int i3 = 1; i3 < l; i3++) {
                    if (aClass42_Sub1_1068.method569(i3, 5)) {
                        aClass42_Sub1_1068.method563((byte) 1, 2, i3, (byte) 8);
                    }
                }
            }

            //MEDIA!!!
            drawLoadingText(55, "Unpacking media");

            //////////////////////////////////////////////////////
            ////////////////// SPRITE LOADING ////////////////////
            /////////////////------------------///////////////////
            //////////////////////////////////////////////////////
            //////////////////////////////////////////////////////
            loadOrbSprites();


            refreshBtn = new Sprite(sign.signlink.findcachedir() + "sprites/titlescreen/refresh.png");


            //world select screen and shit
            worldSelect = new Sprite(sign.signlink.findcachedir() + "sprites/titlescreen/wrldslct.png");
            cancelBut = new Sprite(sign.signlink.findcachedir() + "sprites/titlescreen/cancel.png");
            cancelButovr = new Sprite(sign.signlink.findcachedir() + "sprites/titlescreen/cancelovr.png");
            WorldBtn = new Sprite(sign.signlink.findcachedir() + "sprites/titlescreen/worldBtn.png");
            WorldBtnovr = new Sprite(sign.signlink.findcachedir() + "sprites/titlescreen/worldBtnovr.png");
            gStar = new Sprite(sign.signlink.findcachedir() + "sprites/titlescreen/gstar.png");
            sStar = new Sprite(sign.signlink.findcachedir() + "sprites/titlescreen/sstar.png");
            visitURL = new Sprite(sign.signlink.findcachedir() + "sprites/titlescreen/visitURL.png");

            //for new hpbar


            for (int j = 0; j < 6; j++) {
                HPBarFull[j] = new Sprite(sign.signlink.findcachedir() + "sprites/misc/health/full " + j + ".png");
            }

            for (int l = 0; l < 6; l++) {
                HPBarEmpty[l] = new Sprite(sign.signlink.findcachedir() + "sprites/misc/health/empty " + l + ".png");
            }

            for (int n = 0; n < 5; n++) {
                WorldFlags[n] = new Sprite(sign.signlink.findcachedir() + "sprites/titlescreen/flags/flag " + n + ".png");
            }



            //main client backkground
            background = new Sprite(sign.signlink.findcachedir() + "sprites/titlescreen/background.png");







            loginBase = new Sprite(sign.signlink.findcachedir() + "sprites/titlescreen/login/bg.png");
            loginTextBox = new Sprite(sign.signlink.findcachedir() + "sprites/titlescreen/login/textbox.png");

            loginExit = new Sprite(sign.signlink.findcachedir() + "sprites/titlescreen/login/exit.png");
            loginExitH = new Sprite(sign.signlink.findcachedir() + "sprites/titlescreen/login/exith.png");

            loginGo = new Sprite(sign.signlink.findcachedir() + "sprites/titlescreen/login/loginbutton.png");
            loginGoH = new Sprite(sign.signlink.findcachedir() + "sprites/titlescreen/login/loginbuttonh.png");

            loginSaveOn = new Sprite(sign.signlink.findcachedir() + "sprites/titlescreen/login/saveon.png");
            loginSaveOff = new Sprite(sign.signlink.findcachedir() + "sprites/titlescreen/login/saveoff.png");







            //normal hover/clickedhover redstones
            nch = new Sprite(sign.signlink.findcachedir() + "sprites/gameframe/hoverstones/nch.png");
            nh = new Sprite(sign.signlink.findcachedir() + "sprites/gameframe/hoverstones/nh.png");

            //right top hover/clickedhover redstones
            rtch = new Sprite(sign.signlink.findcachedir() + "sprites/gameframe/hoverstones/rtch.png");
            rth = new Sprite(sign.signlink.findcachedir() + "sprites/gameframe/hoverstones/rth.png");

            //left top hover/clickedhover redstones
            ltch = new Sprite(sign.signlink.findcachedir() + "sprites/gameframe/hoverstones/ltch.png");
            lth = new Sprite(sign.signlink.findcachedir() + "sprites/gameframe/hoverstones/lth.png");

            //right bottom hover/clickedhover redstones
            rbch = new Sprite(sign.signlink.findcachedir() + "sprites/gameframe/hoverstones/rbch.png");
            rbh = new Sprite(sign.signlink.findcachedir() + "sprites/gameframe/hoverstones/rbh.png");

            //left bottom hover/clickedhover redstones
            lbch = new Sprite(sign.signlink.findcachedir() + "sprites/gameframe/hoverstones/lbch.png");
            lbh = new Sprite(sign.signlink.findcachedir() + "sprites/gameframe/hoverstones/lbh.png");

            //new chat buttons
            Button0 = new Sprite(sign.signlink.findcachedir() + "sprites/newchat/normal.png");
            Button1 = new Sprite(sign.signlink.findcachedir() + "sprites/newchat/pressed.png");
            FlashButton0 = new Sprite(sign.signlink.findcachedir() + "sprites/newchat/flashbutton.png");
            HoverButton0 = new Sprite(sign.signlink.findcachedir() + "sprites/newchat/hovernotpressed.png");
            HoverButton1 = new Sprite(sign.signlink.findcachedir() + "sprites/newchat/hoverpressed.png");

            //report abuse
            Button2 = new Sprite(sign.signlink.findcachedir() + "sprites/newchat/ranormal.png");
            HoverButton2 = new Sprite(sign.signlink.findcachedir() + "sprites/newchat/rahover.png");

            //quickchaticon lolz
            quickChat = new Sprite(sign.signlink.findcachedir() + "sprites/misc/quickchat.png");
            typer = new Sprite(sign.signlink.findcachedir() + "sprites/misc/typer.png");

            //gameframe

            aClass30_Sub2_Sub1_Sub2_1197 = new Background(class44_2, "mapback", 0); //map


            GlobeSprite = new Sprite(sign.signlink.findcachedir() + "sprites/misc/globe.png");

            for (int i = 0; i <= 5; i++) {
                LogoutDoor[i] = new Sprite(sign.signlink.findcachedir() + "sprites/misc/logout" + i + ".png");
            }

            zoomSprites[0] = new Sprite(sign.signlink.findcachedir() + "sprites/misc/zoom0.png");
            zoomSprites[1] = new Sprite(sign.signlink.findcachedir() + "sprites/misc/zoom1.png");










            CustomMapback = new Sprite(sign.signlink.findcachedir() + "sprites/gameframe/mapback.png");




            aClass30_Sub2_Sub1_Sub1_1198 = new Sprite(class44_2, "chatback", 0); //chatback
            aClass30_Sub2_Sub1_Sub1_1027 = new Sprite(class44_2, "backbase1", 0); //bottom options
            Sprite class30_sub2_sub1_sub1 = new Sprite(class44_2, "backleft1", 0); //left side border



            aClass30_Sub2_Sub1_Sub1_1029 = new Sprite(sign.signlink.findcachedir() + "sprites/gameframe/toptabs.png");
            aClass30_Sub2_Sub1_Sub1_1196 = new Sprite(class44_2, "invback", 0); //inventory
            aClass30_Sub2_Sub1_Sub1_1028 = new Sprite(class44_2, "backbase2", 0); //bottom tabs



            compass = new Sprite(class44_2, "compass", 0);
            mapEdge = new Sprite(class44_2, "mapedge", 0);
            mapEdge.method345();


            aClass15_903 = new RSImageProducer(class30_sub2_sub1_sub1.myWidth, class30_sub2_sub1_sub1.myHeight, getGameComponent(), 0);
            class30_sub2_sub1_sub1.method346(0, 0);

            class30_sub2_sub1_sub1 = new Sprite(class44_2, "backleft2", 0); //left chat border
            aClass15_904 = new RSImageProducer(8, 134, getGameComponent(), 0);
            class30_sub2_sub1_sub1.method346(0, 0);

            class30_sub2_sub1_sub1 = new Sprite(class44_2, "backhmid2", 0); //chat top border
            aClass15_911 = new RSImageProducer(555, 6, getGameComponent(), 0);
            class30_sub2_sub1_sub1.method346(0, 0);

            //class30_sub2_sub1_sub1 = new Sprite(class44_2, "backright1", 0); //orbs
            //aClass15_905 = new RSImageProducer(class30_sub2_sub1_sub1.myWidth, class30_sub2_sub1_sub1.myHeight, getGameComponent(), 0);
            //class30_sub2_sub1_sub1.method346(0, 0);

            class30_sub2_sub1_sub1 = new Sprite(class44_2, "backright2", 0); //right pillar
            aClass15_906 = new RSImageProducer(class30_sub2_sub1_sub1.myWidth, class30_sub2_sub1_sub1.myHeight, getGameComponent(), 0);
            class30_sub2_sub1_sub1.method346(0, 0);

            class30_sub2_sub1_sub1 = new Sprite(class44_2, "backtop1", 0);
            aClass15_907 = new RSImageProducer(class30_sub2_sub1_sub1.myWidth, class30_sub2_sub1_sub1.myHeight, getGameComponent(), 0);
            class30_sub2_sub1_sub1.method346(0, 0);

            //class30_sub2_sub1_sub1 = new Sprite(class44_2, "backvmid1", 0); //left mini map
            //aClass15_908 = new RSImageProducer(class30_sub2_sub1_sub1.myWidth, class30_sub2_sub1_sub1.myHeight, getGameComponent(), 0);
            //class30_sub2_sub1_sub1.method346(0, 0);

            class30_sub2_sub1_sub1 = new Sprite(class44_2, "backvmid2", 0); //left pillar top
            aClass15_909 = new RSImageProducer(class30_sub2_sub1_sub1.myWidth, class30_sub2_sub1_sub1.myHeight, getGameComponent(), 0);
            class30_sub2_sub1_sub1.method346(0, 0);

            class30_sub2_sub1_sub1 = new Sprite(class44_2, "backvmid3", 0); //left pillar bottom
            aClass15_910 = new RSImageProducer(39, 122, getGameComponent(), 0);
            class30_sub2_sub1_sub1.method346(0, 0);



            aClass30_Sub2_Sub1_Sub1_1143 = new Sprite(class44_2, "redstone1", 0);
            aClass30_Sub2_Sub1_Sub1_1144 = new Sprite(class44_2, "redstone2", 0);
            flip = true;
            aClass30_Sub2_Sub1_Sub2_1145 = new Background(class44_2, "redstone3", 0);
            aClass30_Sub2_Sub1_Sub1_1146 = new Sprite(class44_2, "redstone1", 0);
            aClass30_Sub2_Sub1_Sub1_1147 = new Sprite(class44_2, "redstone2", 0);
            flip = false;
            flip_s = true;
            aClass30_Sub2_Sub1_Sub1_865 = new Sprite(class44_2, "redstone1", 0);
            aClass30_Sub2_Sub1_Sub1_866 = new Sprite(class44_2, "redstone2", 0);
            aClass30_Sub2_Sub1_Sub2_867 = new Background(class44_2, "redstone3", 0);
            flip_s = false;
            flip_r = true;
            aClass30_Sub2_Sub1_Sub2_867.method359();
            aClass30_Sub2_Sub1_Sub1_868 = new Sprite(class44_2, "redstone1", 0);
            aClass30_Sub2_Sub1_Sub2_869 = new Background(class44_2, "redstone2", 0);
            aClass30_Sub2_Sub1_Sub2_867.method359();
            aClass30_Sub2_Sub1_Sub2_869.method358();
            aClass30_Sub2_Sub1_Sub2_869.method359();

            mapFlag = new Sprite(class44_2, "mapmarker", 0);
            mapMarker = new Sprite(class44_2, "mapmarker", 1);
            mapDotItem = new Sprite(class44_2, "mapdots", 0);
            mapDotNPC = new Sprite(class44_2, "mapdots", 1);
            mapDotPlayer = new Sprite(class44_2, "mapdots", 2);
            mapDotFriend = new Sprite(class44_2, "mapdots", 3);
            mapDotTeam = new Sprite(class44_2, "mapdots", 4);
            try {
                for (int id1 = 0; id1 < 2; id1++) {
                    scrollBar[id1] = new Sprite(sign.signlink.findcachedir() + "sprites/gameframe/scroll " + id1 + ".png");
                }
            } catch (Exception _ex) {
            }
            try {
                for (int k3 = 0; k3 < 100; k3++) {
                    mapScenes[k3] = new Background(class44_2, "mapscene", k3);
                }
            } catch (Exception _ex) {
            }
            try {
                for (int l3 = 0; l3 < 100; l3++) {
                    mapFunctions[l3] = new Sprite(class44_2, "mapfunction", l3);
                }
            } catch (Exception _ex) {
            }
            try {
                for (int j3 = 0; j3 <= 14; j3++) {
                    sideIcons[j3] = new Sprite(sign.signlink.findcachedir() + "sprites/tabicons/icon " + j3 + ".png");
                }
            } catch (Exception _ex) {
            }
            try {
                for (int j4 = 0; j4 < 20; j4++) {
                    headIcons[j4] = new Sprite(class44_2, "headicons", j4);
                }
            } catch (Exception _ex) {
            }
            try {
                for (int j4 = 0; j4 < 85; j4++) {
                    headIcons[j4] = new Sprite(class44_2, "headicons_prayer", j4);
                }
            } catch (Exception _ex) {
            }
            try {
                for (int j4 = 0; j4 < 2; j4++) {
                    headIcons[j4] = new Sprite(class44_2, "headicons_pk", j4);
                }
            } catch (Exception _ex) {
            }
            try {
                for (int i4 = 0; i4 < 20; i4++) {
                    hitMarks[i4] = new Sprite(class44_2, "hitmarks", i4);
                }
            } catch (Exception _ex) {
            }
            try {
                for (int k4 = 0; k4 < 8; k4++) {
                    crosses[k4] = new Sprite(class44_2, "cross", k4);
                }
            } catch (Exception _ex) {
            }
            try {
                for (int l4 = 0; l4 < 2; l4++) {
                    modIcons[l4] = new Sprite(class44_2, "mod_icons", l4);
                }
            } catch (Exception _ex) {
            }

            int i5 = (int) (Math.random() * 21D) - 10;
            int j5 = (int) (Math.random() * 21D) - 10;
            int k5 = (int) (Math.random() * 21D) - 10;
            int l5 = (int) (Math.random() * 41D) - 20;

            for (int i6 = 0; i6 < 100; i6++) {
                if (mapFunctions[i6] != null) {
                    mapFunctions[i6].method344(i5 + l5, j5 + l5, k5 + l5, 0);
                }
                if (mapScenes[i6] != null) {
                    mapScenes[i6].method360(i5 + l5, j5 + l5, k5 + l5);
                }
            }





            drawLoadingText(64, "Unpacking textures");
            Texture.method368(class44_3);
            Texture.method372(0.80000000000000004D);
            Texture.method367(20);


            drawLoadingText(73, "Unpacking config");
            Animation.unpackConfig(class44);
            ObjectDef.unpackConfig(class44);
            Flo.unpackConfig(class44);
            ItemDef.unpackConfig(class44);
            EntityDef.unpackConfig(class44);
            IDK.unpackConfig(class44);
            SpotAnim.unpackConfig(class44);
            Varp.unpackConfig(0, class44);
            VarBit.unpackConfig(class44);
            ItemDef.isMembers = isMembers;
            if (!lowMem) {
                drawLoadingText(82, "Unpacking sounds");
                byte abyte0[] = class44_5.getDataForName("sounds.dat");
                Stream class30_sub2_sub2 = new Stream(abyte0);
                Sounds.unpack(0, class30_sub2_sub2);
            }


            drawLoadingText(92, "Unpacking interfaces");
            TextDrawingClass aclass30_sub2_sub1_sub4[] = {
                smallFont, regularFont, boldFont, fancyFont
            };
            RSInterface.unpack(class44_1, aclass30_sub2_sub1_sub4, (byte) -84, class44_2);


            drawLoadingText(100, "Preparing game engine");
            for (int j6 = 0; j6 < 33; j6++) {
                int k6 = 999;
                int i7 = 0;
                for (int k7 = 0; k7 < 34; k7++) {
                    if (aClass30_Sub2_Sub1_Sub2_1197.pixelInfo[k7 + j6 * aClass30_Sub2_Sub1_Sub2_1197.myWidth] == 0) {
                        if (k6 == 999) {
                            k6 = k7;
                        }
                        continue;
                    }
                    if (k6 == 999) {
                        continue;
                    }
                    i7 = k7;
                    break;
                }
                anIntArray968[j6] = k6;
                anIntArray1057[j6] = i7 - k6;
            }




            for (int l6 = 1; l6 < 153; l6++) {
                int j7 = 999;
                int l7 = 0;
                for (int j8 = 24; j8 < 177; j8++) {
                    if (aClass30_Sub2_Sub1_Sub2_1197.pixelInfo[j8 + l6 * aClass30_Sub2_Sub1_Sub2_1197.myWidth] == 0 && (j8 > 34 || l6 > 34)) {
                        if (j7 == 999) {
                            j7 = j8;
                        }
                        continue;
                    }
                    if (j7 == 999) {
                        continue;
                    }
                    l7 = j8;
                    break;
                }
                anIntArray1052[l6 - 1] = j7 - 24;
                anIntArray1229[l6 - 1] = l7 - j7;
            }

            Texture.method365(-950, 506, 128);
            anIntArray1180 = Texture.anIntArray1472;
            Texture.method365(-950, 192, 261);
            anIntArray1181 = Texture.anIntArray1472;
            Texture.method365(-950, 512, 334);
            anIntArray1182 = Texture.anIntArray1472;
            int ai[] = new int[9];
            for (int i8 = 0; i8 < 9; i8++) {
                int k8 = 128 + i8 * 32 + 15;
                int l8 = 600 + k8 * 3;
                int i9 = Texture.anIntArray1470[k8];
                ai[i8] = l8 * i9 >> 16;
            }
            WorldController.method310(500, 800, 512, 334, ai, aBoolean1231);
            Censor.loadConfig(class44_4);
            mouseDetection = new Class48(this, anInt1096);
            startRunnable(mouseDetection, 10);
            Animable_Sub5.clientInstance = this;
            ObjectDef.clientInstance = this;
            EntityDef.clientInstance = this;
            // sendQuest("", 13097); // sendQuest's go here.


            Frame_Sub1.trayIcon.displayMessage("-GaMeR X-'s Client", "Client Loading Complete!", TrayIcon.MessageType.INFO);



            return;
        } catch (Exception exception) {
            signlink.reporterror("loaderror " + aString1049 + " " + anInt1079);
        }
        loadingError = true;
    }

    private final void method91(Stream class30_sub2_sub2, int i, byte byte0) {
        if (byte0 == 8) {
            byte0 = 0;
        } else {
            anInt1119 = -50;
        }
        while (class30_sub2_sub2.bitPosition + 10 < i * 8) {
            int j = class30_sub2_sub2.readBits(11);
            if (j == 2047) {
                break;
            }
            if (playerArray[j] == null) {
                playerArray[j] = new Player();
                if (aClass30_Sub2_Sub2Array895[j] != null) {
                    playerArray[j].updatePlayer(0, aClass30_Sub2_Sub2Array895[j]);
                }
            }
            anIntArray892[anInt891++] = j;
            Player class30_sub2_sub4_sub1_sub2 = playerArray[j];
            class30_sub2_sub4_sub1_sub2.anInt1537 = loopCycle;
            int k = class30_sub2_sub2.readBits(1);
            if (k == 1) {
                anIntArray894[anInt893++] = j;
            }
            int l = class30_sub2_sub2.readBits(1);
            int i1 = class30_sub2_sub2.readBits(5);
            if (i1 > 15) {
                i1 -= 32;
            }
            int j1 = class30_sub2_sub2.readBits(5);
            if (j1 > 15) {
                j1 -= 32;
            }
            class30_sub2_sub4_sub1_sub2.setPos(((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0] + j1, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0] + i1, l == 1);
        }
        class30_sub2_sub2.finishBitAccess();
    }

    public final void processMainScreenClick() {
        if (minimapState != 0) {
            return;
        }

        if (super.anInt26 == 1) { //click
            int i = super.saveClickX - 25 - 527;
            int j = super.saveClickY - 5 - 4;
            if (i >= 0 && j >= 0 && i < 146 && j < 151) {


                i -= 79;
                j -= 76;
                int k = anInt1185 + minimapInt2 & 0x7ff;
                int i1 = Texture.anIntArray1470[k];
                int j1 = Texture.anIntArray1471[k];
                i1 = i1 * (minimapInt3 + 256) >> 8;
                j1 = j1 * (minimapInt3 + 256) >> 8;
                int k1 = j * i1 + i * j1 >> 11;
                int l1 = j * j1 - i * i1 >> 11;
                int i2 = ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).x + k1 >> 7;
                int j2 = ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).y - l1 >> 7;
                boolean flag1 = method85(1, 0, 0, -11308, 0, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0], 0, 0, j2, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0], true, i2);
                if (flag1) {
                    aClass30_Sub2_Sub2_1192.writeWordBigEndian(i);
                    aClass30_Sub2_Sub2_1192.writeWordBigEndian(j);
                    aClass30_Sub2_Sub2_1192.writeWord(anInt1185);
                    aClass30_Sub2_Sub2_1192.writeWordBigEndian(57);
                    aClass30_Sub2_Sub2_1192.writeWordBigEndian(minimapInt2);
                    aClass30_Sub2_Sub2_1192.writeWordBigEndian(minimapInt3);
                    aClass30_Sub2_Sub2_1192.writeWordBigEndian(89);
                    aClass30_Sub2_Sub2_1192.writeWord(((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).x);
                    aClass30_Sub2_Sub2_1192.writeWord(((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).y);
                    aClass30_Sub2_Sub2_1192.writeWordBigEndian(anInt1264);
                    aClass30_Sub2_Sub2_1192.writeWordBigEndian(63);
                }
            }
            anInt1117++;
            if (anInt1117 > 1151) {
                anInt1117 = 0;
                aClass30_Sub2_Sub2_1192.createFrame(246);
                aClass30_Sub2_Sub2_1192.writeWordBigEndian(0);
                int l = aClass30_Sub2_Sub2_1192.currentOffset;
                if ((int) (Math.random() * 2D) == 0) {
                    aClass30_Sub2_Sub2_1192.writeWordBigEndian(101);
                }
                aClass30_Sub2_Sub2_1192.writeWordBigEndian(197);
                aClass30_Sub2_Sub2_1192.writeWord((int) (Math.random() * 65536D));
                aClass30_Sub2_Sub2_1192.writeWordBigEndian((int) (Math.random() * 256D));
                aClass30_Sub2_Sub2_1192.writeWordBigEndian(67);
                aClass30_Sub2_Sub2_1192.writeWord(14214);
                if ((int) (Math.random() * 2D) == 0) {
                    aClass30_Sub2_Sub2_1192.writeWord(29487);
                }
                aClass30_Sub2_Sub2_1192.writeWord((int) (Math.random() * 65536D));
                if ((int) (Math.random() * 2D) == 0) {
                    aClass30_Sub2_Sub2_1192.writeWordBigEndian(220);
                }
                aClass30_Sub2_Sub2_1192.writeWordBigEndian(180);
                aClass30_Sub2_Sub2_1192.writeBytes(aClass30_Sub2_Sub2_1192.currentOffset - l);
            }
        }
    }

    public final String method93(int i, int j) {
        if (i <= 0) {
            pktType = aClass30_Sub2_Sub2_1083.readUnsignedByte();
        }
        if (j < 0x3b9ac9ff) {
            return String.valueOf(j);
        } else {
            return "?";
        }
    }

    public final void showErrorScreen() {
        Graphics g = getGameComponent().getGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, 765, 503);
        method4(false, 1);
        if (loadingError) {
            aBoolean831 = false;
            g.setFont(new Font("Helvetica", 1, 16));
            g.setColor(Color.yellow);
            int k = 35;
            g.drawString("Sorry, an error has occured whilst loading the Client", 30, k);
            k += 50;
            g.setColor(Color.white);
            g.drawString("To fix this try the following (in order):", 30, k);
            k += 50;
            g.setColor(Color.white);
            g.setFont(new Font("Helvetica", 1, 12));
            g.drawString("1: Try closing ALL open web-browser windows, and reloading", 30, k);
            k += 30;
            g.drawString("2: Try clearing your web-browsers cache from tools->internet options", 30, k);
            k += 30;
            g.drawString("3: Try using a different game-world", 30, k);
            k += 30;
            g.drawString("4: Try rebooting your computer", 30, k);
            k += 30;
            g.drawString("5: Try selecting a different version of Java from the play-game menu", 30, k);
        }
        if (genericLoadingError) {
            aBoolean831 = false;
            g.setFont(new Font("Helvetica", 1, 20));
            g.setColor(Color.white);
            g.drawString("Error - unable to load game!", 50, 50);
            g.drawString("", 50, 100);
            g.drawString("", 50, 150);
        }
        if (rsAlreadyLoaded) {
            aBoolean831 = false;
            g.setColor(Color.yellow);
            int l = 35;
            g.drawString("Error a copy of this Client already appears to be loaded", 30, l);
            l += 50;
            g.setColor(Color.white);
            g.drawString("To fix this try the following (in order):", 30, l);
            l += 50;
            g.setColor(Color.white);
            g.setFont(new Font("Helvetica", 1, 12));
            g.drawString("1: Try closing ALL open web-browser windows, and reloading", 30, l);
            l += 30;
            g.drawString("2: Try rebooting your computer, and reloading", 30, l);
            l += 30;
        }
    }

    public URL getCodeBase() {
        try {
            return new URL("http://" + Csettings.server + ":" + Csettings.port);
        } catch (Exception _ex) {
        }
        return super.getCodeBase();
    }

    public final void method95(int i) {
        for (int j = 0; j < npcCount; j++) {
            int k = anIntArray837[j];
            NPC class30_sub2_sub4_sub1_sub1 = npcArray[k];
            if (class30_sub2_sub4_sub1_sub1 != null) {
                method96(46988, class30_sub2_sub4_sub1_sub1.desc.aByte68, class30_sub2_sub4_sub1_sub1);
            }
        }

        if (i != -8066) {
            anInt1218 = 148;
        }
    }

    public final void method96(int i, int j, Entity class30_sub2_sub4_sub1) {
        if (i != 46988) {
            pktType = -1;
        }
        if (class30_sub2_sub4_sub1.x < 128 || class30_sub2_sub4_sub1.y < 128 || class30_sub2_sub4_sub1.x >= 13184 || class30_sub2_sub4_sub1.y >= 13184) {
            class30_sub2_sub4_sub1.anim = -1;
            class30_sub2_sub4_sub1.anInt1520 = -1;
            class30_sub2_sub4_sub1.anInt1547 = 0;
            class30_sub2_sub4_sub1.anInt1548 = 0;
            class30_sub2_sub4_sub1.x = class30_sub2_sub4_sub1.smallX[0] * 128 + class30_sub2_sub4_sub1.anInt1540 * 64;
            class30_sub2_sub4_sub1.y = class30_sub2_sub4_sub1.smallY[0] * 128 + class30_sub2_sub4_sub1.anInt1540 * 64;
            class30_sub2_sub4_sub1.method446(true);
        }
        if (class30_sub2_sub4_sub1 == aClass30_Sub2_Sub4_Sub1_Sub2_1126 && (class30_sub2_sub4_sub1.x < 1536 || class30_sub2_sub4_sub1.y < 1536 || class30_sub2_sub4_sub1.x >= 11776 || class30_sub2_sub4_sub1.y >= 11776)) {
            class30_sub2_sub4_sub1.anim = -1;
            class30_sub2_sub4_sub1.anInt1520 = -1;
            class30_sub2_sub4_sub1.anInt1547 = 0;
            class30_sub2_sub4_sub1.anInt1548 = 0;
            class30_sub2_sub4_sub1.x = class30_sub2_sub4_sub1.smallX[0] * 128 + class30_sub2_sub4_sub1.anInt1540 * 64;
            class30_sub2_sub4_sub1.y = class30_sub2_sub4_sub1.smallY[0] * 128 + class30_sub2_sub4_sub1.anInt1540 * 64;
            class30_sub2_sub4_sub1.method446(true);
        }
        if (class30_sub2_sub4_sub1.anInt1547 > loopCycle) {
            method97(class30_sub2_sub4_sub1, true);
        } else if (class30_sub2_sub4_sub1.anInt1548 >= loopCycle) {
            method98(class30_sub2_sub4_sub1, aByte1012);
        } else {
            method99((byte) 34, class30_sub2_sub4_sub1);
        }
        method100(class30_sub2_sub4_sub1, -843);
        method101(class30_sub2_sub4_sub1, -805);
    }

    public final void method97(Entity class30_sub2_sub4_sub1, boolean flag) {
        int i = class30_sub2_sub4_sub1.anInt1547 - loopCycle;
        int j = class30_sub2_sub4_sub1.anInt1543 * 128 + class30_sub2_sub4_sub1.anInt1540 * 64;
        int k = class30_sub2_sub4_sub1.anInt1545 * 128 + class30_sub2_sub4_sub1.anInt1540 * 64;
        class30_sub2_sub4_sub1.x += (j - class30_sub2_sub4_sub1.x) / i;
        if (!flag) {
            return;
        }
        class30_sub2_sub4_sub1.y += (k - class30_sub2_sub4_sub1.y) / i;
        class30_sub2_sub4_sub1.anInt1503 = 0;
        if (class30_sub2_sub4_sub1.anInt1549 == 0) {
            class30_sub2_sub4_sub1.turnDirection = 1024;
        }
        if (class30_sub2_sub4_sub1.anInt1549 == 1) {
            class30_sub2_sub4_sub1.turnDirection = 1536;
        }
        if (class30_sub2_sub4_sub1.anInt1549 == 2) {
            class30_sub2_sub4_sub1.turnDirection = 0;
        }
        if (class30_sub2_sub4_sub1.anInt1549 == 3) {
            class30_sub2_sub4_sub1.turnDirection = 512;
        }
    }

    public final void method98(Entity class30_sub2_sub4_sub1, byte byte0) {
        if (class30_sub2_sub4_sub1.anInt1548 == loopCycle || class30_sub2_sub4_sub1.anim == -1 || class30_sub2_sub4_sub1.anInt1529 != 0 || class30_sub2_sub4_sub1.anInt1528 + 1 > Animation.anims[class30_sub2_sub4_sub1.anim].method258(class30_sub2_sub4_sub1.anInt1527)) {
            int i = class30_sub2_sub4_sub1.anInt1548 - class30_sub2_sub4_sub1.anInt1547;
            int j = loopCycle - class30_sub2_sub4_sub1.anInt1547;
            int k = class30_sub2_sub4_sub1.anInt1543 * 128 + class30_sub2_sub4_sub1.anInt1540 * 64;
            int l = class30_sub2_sub4_sub1.anInt1545 * 128 + class30_sub2_sub4_sub1.anInt1540 * 64;
            int i1 = class30_sub2_sub4_sub1.anInt1544 * 128 + class30_sub2_sub4_sub1.anInt1540 * 64;
            int j1 = class30_sub2_sub4_sub1.anInt1546 * 128 + class30_sub2_sub4_sub1.anInt1540 * 64;
            class30_sub2_sub4_sub1.x = (k * (i - j) + i1 * j) / i;
            class30_sub2_sub4_sub1.y = (l * (i - j) + j1 * j) / i;
        }
        class30_sub2_sub4_sub1.anInt1503 = 0;
        if (class30_sub2_sub4_sub1.anInt1549 == 0) {
            class30_sub2_sub4_sub1.turnDirection = 1024;
        }
        if (class30_sub2_sub4_sub1.anInt1549 == 1) {
            class30_sub2_sub4_sub1.turnDirection = 1536;
        }
        if (class30_sub2_sub4_sub1.anInt1549 == 2) {
            class30_sub2_sub4_sub1.turnDirection = 0;
        }
        if (class30_sub2_sub4_sub1.anInt1549 == 3) {
            class30_sub2_sub4_sub1.turnDirection = 512;
        }
        class30_sub2_sub4_sub1.anInt1552 = class30_sub2_sub4_sub1.turnDirection;
        if (byte0 != aByte1012) {
            anInt1096 = -383;
        }
    }

    public final void method99(byte byte0, Entity class30_sub2_sub4_sub1) {
        class30_sub2_sub4_sub1.anInt1517 = class30_sub2_sub4_sub1.anInt1511;
        if (class30_sub2_sub4_sub1.smallXYIndex == 0) {
            class30_sub2_sub4_sub1.anInt1503 = 0;
            return;
        }
        if (class30_sub2_sub4_sub1.anim != -1 && class30_sub2_sub4_sub1.anInt1529 == 0) {
            Animation class20 = Animation.anims[class30_sub2_sub4_sub1.anim];
            if (class30_sub2_sub4_sub1.anInt1542 > 0 && class20.anInt363 == 0) {
                class30_sub2_sub4_sub1.anInt1503++;
                return;
            }
            if (class30_sub2_sub4_sub1.anInt1542 <= 0 && class20.anInt364 == 0) {
                class30_sub2_sub4_sub1.anInt1503++;
                return;
            }
        }
        int i = class30_sub2_sub4_sub1.x;
        int j = class30_sub2_sub4_sub1.y;
        int k = class30_sub2_sub4_sub1.smallX[class30_sub2_sub4_sub1.smallXYIndex - 1] * 128 + class30_sub2_sub4_sub1.anInt1540 * 64;
        int l = class30_sub2_sub4_sub1.smallY[class30_sub2_sub4_sub1.smallXYIndex - 1] * 128 + class30_sub2_sub4_sub1.anInt1540 * 64;
        if (k - i > 256 || k - i < -256 || l - j > 256 || l - j < -256) {
            class30_sub2_sub4_sub1.x = k;
            class30_sub2_sub4_sub1.y = l;
            return;
        }
        if (i < k) {
            if (j < l) {
                class30_sub2_sub4_sub1.turnDirection = 1280;
            } else if (j > l) {
                class30_sub2_sub4_sub1.turnDirection = 1792;
            } else {
                class30_sub2_sub4_sub1.turnDirection = 1536;
            }
        } else if (i > k) {
            if (j < l) {
                class30_sub2_sub4_sub1.turnDirection = 768;
            } else if (j > l) {
                class30_sub2_sub4_sub1.turnDirection = 256;
            } else {
                class30_sub2_sub4_sub1.turnDirection = 512;
            }
        } else if (j < l) {
            class30_sub2_sub4_sub1.turnDirection = 1024;
        } else {
            class30_sub2_sub4_sub1.turnDirection = 0;
        }
        int i1 = class30_sub2_sub4_sub1.turnDirection - class30_sub2_sub4_sub1.anInt1552 & 0x7ff;
        if (i1 > 1024) {
            i1 -= 2048;
        }
        int j1 = class30_sub2_sub4_sub1.anInt1555;
        if (i1 >= -256 && i1 <= 256) {
            j1 = class30_sub2_sub4_sub1.anInt1554;
        } else if (i1 >= 256 && i1 < 768) {
            j1 = class30_sub2_sub4_sub1.anInt1557;
        } else if (i1 >= -768 && i1 <= -256) {
            j1 = class30_sub2_sub4_sub1.anInt1556;
        }
        if (j1 == -1) {
            j1 = class30_sub2_sub4_sub1.anInt1554;
        }
        class30_sub2_sub4_sub1.anInt1517 = j1;
        int k1 = 4;
        if (byte0 != 34) {
            anInt1096 = 285;
        }
        if (class30_sub2_sub4_sub1.anInt1552 != class30_sub2_sub4_sub1.turnDirection && class30_sub2_sub4_sub1.interactingEntity == -1 && class30_sub2_sub4_sub1.anInt1504 != 0) {
            k1 = 2;
        }
        if (class30_sub2_sub4_sub1.smallXYIndex > 2) {
            k1 = 6;
        }
        if (class30_sub2_sub4_sub1.smallXYIndex > 3) {
            k1 = 8;
        }
        if (class30_sub2_sub4_sub1.anInt1503 > 0 && class30_sub2_sub4_sub1.smallXYIndex > 1) {
            k1 = 8;
            class30_sub2_sub4_sub1.anInt1503--;
        }
        if (class30_sub2_sub4_sub1.aBooleanArray1553[class30_sub2_sub4_sub1.smallXYIndex - 1]) {
            k1 <<= 1;
        }
        if (k1 >= 8 && class30_sub2_sub4_sub1.anInt1517 == class30_sub2_sub4_sub1.anInt1554 && class30_sub2_sub4_sub1.anInt1505 != -1) {
            class30_sub2_sub4_sub1.anInt1517 = class30_sub2_sub4_sub1.anInt1505;
        }
        if (i < k) {
            class30_sub2_sub4_sub1.x += k1;
            if (class30_sub2_sub4_sub1.x > k) {
                class30_sub2_sub4_sub1.x = k;
            }
        } else if (i > k) {
            class30_sub2_sub4_sub1.x -= k1;
            if (class30_sub2_sub4_sub1.x < k) {
                class30_sub2_sub4_sub1.x = k;
            }
        }
        if (j < l) {
            class30_sub2_sub4_sub1.y += k1;
            if (class30_sub2_sub4_sub1.y > l) {
                class30_sub2_sub4_sub1.y = l;
            }
        } else if (j > l) {
            class30_sub2_sub4_sub1.y -= k1;
            if (class30_sub2_sub4_sub1.y < l) {
                class30_sub2_sub4_sub1.y = l;
            }
        }
        if (class30_sub2_sub4_sub1.x == k && class30_sub2_sub4_sub1.y == l) {
            class30_sub2_sub4_sub1.smallXYIndex--;
            if (class30_sub2_sub4_sub1.anInt1542 > 0) {
                class30_sub2_sub4_sub1.anInt1542--;
            }
        }
    }

    public final void method100(Entity class30_sub2_sub4_sub1, int i) {
        if (i >= 0) {
            return;
        }
        if (class30_sub2_sub4_sub1.anInt1504 == 0) {
            return;
        }
        if (class30_sub2_sub4_sub1.interactingEntity != -1 && class30_sub2_sub4_sub1.interactingEntity < 32768) {
            NPC class30_sub2_sub4_sub1_sub1 = npcArray[class30_sub2_sub4_sub1.interactingEntity];
            if (class30_sub2_sub4_sub1_sub1 != null) {
                int i1 = class30_sub2_sub4_sub1.x - ((Entity) (class30_sub2_sub4_sub1_sub1)).x;
                int k1 = class30_sub2_sub4_sub1.y - ((Entity) (class30_sub2_sub4_sub1_sub1)).y;
                if (i1 != 0 || k1 != 0) {
                    class30_sub2_sub4_sub1.turnDirection = (int) (Math.atan2(i1, k1) * 325.94900000000001D) & 0x7ff;
                }
            }
        }
        if (class30_sub2_sub4_sub1.interactingEntity >= 32768) {
            int j = class30_sub2_sub4_sub1.interactingEntity - 32768;
            if (j == anInt884) {
                j = anInt889;
            }
            Player class30_sub2_sub4_sub1_sub2 = playerArray[j];
            if (class30_sub2_sub4_sub1_sub2 != null) {
                int l1 = class30_sub2_sub4_sub1.x - ((Entity) (class30_sub2_sub4_sub1_sub2)).x;
                int i2 = class30_sub2_sub4_sub1.y - ((Entity) (class30_sub2_sub4_sub1_sub2)).y;
                if (l1 != 0 || i2 != 0) {
                    class30_sub2_sub4_sub1.turnDirection = (int) (Math.atan2(l1, i2) * 325.94900000000001D) & 0x7ff;
                }
            }
        }
        if ((class30_sub2_sub4_sub1.anInt1538 != 0 || class30_sub2_sub4_sub1.anInt1539 != 0) && (class30_sub2_sub4_sub1.smallXYIndex == 0 || class30_sub2_sub4_sub1.anInt1503 > 0)) {
            int k = class30_sub2_sub4_sub1.x - (class30_sub2_sub4_sub1.anInt1538 - baseX - baseX) * 64;
            int j1 = class30_sub2_sub4_sub1.y - (class30_sub2_sub4_sub1.anInt1539 - baseY - baseY) * 64;
            if (k != 0 || j1 != 0) {
                class30_sub2_sub4_sub1.turnDirection = (int) (Math.atan2(k, j1) * 325.94900000000001D) & 0x7ff;
            }
            class30_sub2_sub4_sub1.anInt1538 = 0;
            class30_sub2_sub4_sub1.anInt1539 = 0;
        }
        int l = class30_sub2_sub4_sub1.turnDirection - class30_sub2_sub4_sub1.anInt1552 & 0x7ff;
        if (l != 0) {
            if (l < class30_sub2_sub4_sub1.anInt1504 || l > 2048 - class30_sub2_sub4_sub1.anInt1504) {
                class30_sub2_sub4_sub1.anInt1552 = class30_sub2_sub4_sub1.turnDirection;
            } else if (l > 1024) {
                class30_sub2_sub4_sub1.anInt1552 -= class30_sub2_sub4_sub1.anInt1504;
            } else {
                class30_sub2_sub4_sub1.anInt1552 += class30_sub2_sub4_sub1.anInt1504;
            }
            class30_sub2_sub4_sub1.anInt1552 &= 0x7ff;
            if (class30_sub2_sub4_sub1.anInt1517 == class30_sub2_sub4_sub1.anInt1511 && class30_sub2_sub4_sub1.anInt1552 != class30_sub2_sub4_sub1.turnDirection) {
                if (class30_sub2_sub4_sub1.anInt1512 != -1) {
                    class30_sub2_sub4_sub1.anInt1517 = class30_sub2_sub4_sub1.anInt1512;
                    return;
                }
                class30_sub2_sub4_sub1.anInt1517 = class30_sub2_sub4_sub1.anInt1554;
            }
        }
    }

    public final void method101(Entity class30_sub2_sub4_sub1, int i) {
        if (i >= 0) {
            aBoolean919 = !aBoolean919;
        }
        class30_sub2_sub4_sub1.aBoolean1541 = false;
        if (class30_sub2_sub4_sub1.anInt1517 != -1) {
            Animation class20 = Animation.anims[class30_sub2_sub4_sub1.anInt1517];
            class30_sub2_sub4_sub1.anInt1519++;
            if (class30_sub2_sub4_sub1.anInt1518 < class20.anInt352 && class30_sub2_sub4_sub1.anInt1519 > class20.method258(class30_sub2_sub4_sub1.anInt1518)) {
                class30_sub2_sub4_sub1.anInt1519 = 0;
                class30_sub2_sub4_sub1.anInt1518++;
            }
            if (class30_sub2_sub4_sub1.anInt1518 >= class20.anInt352) {
                class30_sub2_sub4_sub1.anInt1519 = 0;
                class30_sub2_sub4_sub1.anInt1518 = 0;
            }
        }
        if (class30_sub2_sub4_sub1.anInt1520 != -1 && loopCycle >= class30_sub2_sub4_sub1.anInt1523) {
            if (class30_sub2_sub4_sub1.anInt1521 < 0) {
                class30_sub2_sub4_sub1.anInt1521 = 0;
            }
            Animation class20_1 = SpotAnim.cache[class30_sub2_sub4_sub1.anInt1520].aClass20_407;
            for (class30_sub2_sub4_sub1.anInt1522++; class30_sub2_sub4_sub1.anInt1521 < class20_1.anInt352 && class30_sub2_sub4_sub1.anInt1522 > class20_1.method258(class30_sub2_sub4_sub1.anInt1521); class30_sub2_sub4_sub1.anInt1521++) {
                class30_sub2_sub4_sub1.anInt1522 -= class20_1.method258(class30_sub2_sub4_sub1.anInt1521);
            }

            if (class30_sub2_sub4_sub1.anInt1521 >= class20_1.anInt352 && (class30_sub2_sub4_sub1.anInt1521 < 0 || class30_sub2_sub4_sub1.anInt1521 >= class20_1.anInt352)) {
                class30_sub2_sub4_sub1.anInt1520 = -1;
            }
        }
        if (class30_sub2_sub4_sub1.anim != -1 && class30_sub2_sub4_sub1.anInt1529 <= 1) {
            Animation class20_2 = Animation.anims[class30_sub2_sub4_sub1.anim];
            if (class20_2.anInt363 == 1 && class30_sub2_sub4_sub1.anInt1542 > 0 && class30_sub2_sub4_sub1.anInt1547 <= loopCycle && class30_sub2_sub4_sub1.anInt1548 < loopCycle) {
                class30_sub2_sub4_sub1.anInt1529 = 1;
                return;
            }
        }
        if (class30_sub2_sub4_sub1.anim != -1 && class30_sub2_sub4_sub1.anInt1529 == 0) {
            Animation class20_3 = Animation.anims[class30_sub2_sub4_sub1.anim];
            for (class30_sub2_sub4_sub1.anInt1528++; class30_sub2_sub4_sub1.anInt1527 < class20_3.anInt352 && class30_sub2_sub4_sub1.anInt1528 > class20_3.method258(class30_sub2_sub4_sub1.anInt1527); class30_sub2_sub4_sub1.anInt1527++) {
                class30_sub2_sub4_sub1.anInt1528 -= class20_3.method258(class30_sub2_sub4_sub1.anInt1527);
            }

            if (class30_sub2_sub4_sub1.anInt1527 >= class20_3.anInt352) {
                class30_sub2_sub4_sub1.anInt1527 -= class20_3.anInt356;
                class30_sub2_sub4_sub1.anInt1530++;
                if (class30_sub2_sub4_sub1.anInt1530 >= class20_3.anInt362) {
                    class30_sub2_sub4_sub1.anim = -1;
                }
                if (class30_sub2_sub4_sub1.anInt1527 < 0 || class30_sub2_sub4_sub1.anInt1527 >= class20_3.anInt352) {
                    class30_sub2_sub4_sub1.anim = -1;
                }
            }
            class30_sub2_sub4_sub1.aBoolean1541 = class20_3.aBoolean358;
        }
        if (class30_sub2_sub4_sub1.anInt1529 > 0) {
            class30_sub2_sub4_sub1.anInt1529--;
        }
    }

    public final void drawGameScreen() {
        if (welcomeScreenRaised) {
            welcomeScreenRaised = false;
            aClass15_903.drawGraphics(4, 23680, super.aGraphics12, 0);
            aClass15_904.drawGraphics(338, 23680, super.aGraphics12, 0);
            aClass15_906.drawGraphics(205, 23680, super.aGraphics12, 743);
            aClass15_907.drawGraphics(0, 23680, super.aGraphics12, 0);
            aClass15_909.drawGraphics(205, 23680, super.aGraphics12, 516);
            aClass15_910.drawGraphics(344, 23680, super.aGraphics12, 514);
            aClass15_911.drawGraphics(338, 23680, super.aGraphics12, 0);
            needDrawTabArea = true;
            inputTaken = true;
            tabAreaAltered = true;
            needChatAreaRedraw = true;
            if (loadingStage != 2) {
                aClass15_1165.drawGraphics(4, 23680, super.aGraphics12, 4); //gamescreen...
                aClass15_1164.drawGraphics(0, 23680, super.aGraphics12, 516); //there to start drawing the minimap??
            }
        }

        if (loadingStage == 2) {
            loadWorld();
        }

        if (menuOpen && menuScreenArea == 1) {
            needDrawTabArea = true;
        }
        if (invOverlayInterfaceID != -1) {
            boolean flag1 = method119(anInt945, false, invOverlayInterfaceID);
            if (flag1) {
                needDrawTabArea = true;
            }
        }
        if (atInventoryInterfaceType == 2) {
            needDrawTabArea = true;
        }
        if (activeInterfaceType == 2) {
            needDrawTabArea = true;
        }
        if (needDrawTabArea) {
            drawTabArea();
            needDrawTabArea = false;
        }


        if (backDialogID == -1) {
            chatBoxScrollbar.scrollPosition = scrollPos - scrollableChatArea - 112;
            if (super.anInt20 > 448 && super.anInt20 < 560 && super.anInt21 > 332) { //mousex and mousey?? (in chat scroll??)
                method65(482, 112, super.anInt20 - 17, super.anInt21 - 357, chatBoxScrollbar, 0, false, scrollPos, 0);
            }
            int i = scrollPos - 112 - chatBoxScrollbar.scrollPosition;
            if (i < 0) {
                i = 0;
            }
            if (i > scrollPos - 112) {
                i = scrollPos - 112;
            }
            if (scrollableChatArea != i) {
                scrollableChatArea = i;
                inputTaken = true;
            }
        }


        if (backDialogID != -1) {
            boolean flag2 = method119(anInt945, false, backDialogID);
            if (flag2) {
                inputTaken = true;
            }
        }
        if (atInventoryInterfaceType == 3) {
            inputTaken = true;
        }
        if (activeInterfaceType == 3) {
            inputTaken = true;
        }
        if (aString844 != null) {
            inputTaken = true;
        }
        if (menuOpen && menuScreenArea == 2) {
            inputTaken = true;
        }
        if (inputTaken) {
            drawChatArea();
            inputTaken = false;
        }
        if (loadingStage == 2) {
            drawMiniMap();
            aClass15_1164.drawGraphics(0, 23680, super.aGraphics12, 516);
        }
        if (flashingSidebarId != -1) {
            tabAreaAltered = true;
        }
        if (tabAreaAltered) {
            if (flashingSidebarId != -1 && flashingSidebarId == tabID) {
                flashingSidebarId = -1;
                aClass30_Sub2_Sub2_1192.createFrame(120);
                aClass30_Sub2_Sub2_1192.writeWordBigEndian(tabID);
            }
            tabAreaAltered = false;
            aClass15_1125.method237(0);
            aClass30_Sub2_Sub1_Sub1_1029.method348(0, 0);
            if (invOverlayInterfaceID == -1) {
                if (tabInterfaceIDs[tabID] != -1) {
                    if (tabID == 0) {
                        aClass30_Sub2_Sub1_Sub1_1143.method348(7, 0);
                    }
                    if (tabID == 1) {
                        aClass30_Sub2_Sub1_Sub1_1144.method348(46, 0);
                    }
                    if (tabID == 2) {
                        aClass30_Sub2_Sub1_Sub1_1144.method348(79, 0);
                    }
                    if (tabID == 3) {
                        aClass30_Sub2_Sub1_Sub1_1144.method348(112, 0);
                    }
                    if (tabID == 4) {
                        aClass30_Sub2_Sub1_Sub1_1144.method348(145, 0);
                    }
                    if (tabID == 5) {
                        aClass30_Sub2_Sub1_Sub1_1144.method348(178, 0);
                    }
                    if (tabID == 6) {
                        aClass30_Sub2_Sub1_Sub1_1146.method348(211, 0);
                    }
                }








                if (super.anInt20 >= 524 && super.anInt20 <= 561 && super.anInt21 >= 168 && super.anInt21 < 204 && tabInterfaceIDs[0] != -1) { //rt
                    if (tabID == 0) {
                        rtch.method348(7, 0);
                    } else {
                        rth.method348(7, 0);
                    }
                }

                if (super.anInt20 >= 562 && super.anInt20 <= 594 && super.anInt21 >= 168 && super.anInt21 < 204 && tabInterfaceIDs[1] != -1) {
                    if (tabID == 1) {
                        nch.method348(46, 0);
                    } else {
                        nh.method348(46, 0);
                    }
                }

                if (super.anInt20 >= 595 && super.anInt20 <= 627 && super.anInt21 >= 168 && super.anInt21 < 204 && tabInterfaceIDs[2] != -1) {
                    if (tabID == 2) {
                        nch.method348(79, 0);
                    } else {
                        nh.method348(79, 0);
                    }
                }
                if (super.anInt20 >= 628 && super.anInt20 <= 660 && super.anInt21 >= 168 && super.anInt21 < 204 && tabInterfaceIDs[3] != -1) {
                    if (tabID == 3) {
                        nch.method348(112, 0);
                    } else {
                        nh.method348(112, 0);
                    }
                }
                if (super.anInt20 >= 661 && super.anInt20 <= 693 && super.anInt21 >= 168 && super.anInt21 < 204 && tabInterfaceIDs[4] != -1) {
                    if (tabID == 4) {
                        nch.method348(145, 0);
                    } else {
                        nh.method348(145, 0);
                    }
                }
                if (super.anInt20 >= 694 && super.anInt20 <= 726 && super.anInt21 >= 168 && super.anInt21 < 204 && tabInterfaceIDs[5] != -1) {
                    if (tabID == 5) {
                        nch.method348(178, 0);
                    } else {
                        nh.method348(178, 0);
                    }
                }
                if (super.anInt20 >= 727 && super.anInt20 <= 763 && super.anInt21 >= 168 && super.anInt21 < 205 && tabInterfaceIDs[6] != -1) {
                    if (tabID == 6) {
                        ltch.method348(211, 0);
                    } else {
                        lth.method348(211, 0);
                    }
                }

                if (tabInterfaceIDs[0] != -1 && flashingSidebarId == 0) {
                    if (loopCycle % 20 >= 10);
                }
                sideIcons[0].method348(19, 8);
                if (tabInterfaceIDs[1] != -1 && flashingSidebarId == 1) {
                    if (loopCycle % 20 >= 10);
                }
                sideIcons[1].method348(51, 7);
                if (tabInterfaceIDs[2] != -1 && flashingSidebarId == 2) {
                    if (loopCycle % 20 >= 10);
                }
                sideIcons[2].method348(85, 7);
                if (tabInterfaceIDs[3] != -1 && flashingSidebarId == 3) {
                    if (loopCycle % 20 >= 10);
                }
                sideIcons[3].method348(116, 4); //inv
                if (tabInterfaceIDs[4] != -1 && flashingSidebarId == 4) {
                    if (loopCycle % 20 >= 10);
                }
                sideIcons[4].method348(148, 2);
                if (tabInterfaceIDs[5] != -1 && flashingSidebarId == 5) {
                    if (loopCycle % 20 >= 10);
                }
                sideIcons[5].method348(182, 3);
                if (tabInterfaceIDs[6] != -1 && flashingSidebarId == 6) {
                    if (loopCycle % 20 >= 10);
                }
                sideIcons[6].method348(216, 7);
            }


            aClass15_1125.drawGraphics(168, 23680, super.aGraphics12, 516);


            aClass15_1124.method237(0);
            aClass30_Sub2_Sub1_Sub1_1028.method348(0, 0);
            if (!highLight[7]) {
                Button2.method348(-94, 13);//Report Abuse
            } else {
                HoverButton2.method348(-94, 13);//Highlight
            }
            if (invOverlayInterfaceID == -1) {
                if (tabInterfaceIDs[tabID] != -1) {
                    if (tabID == 7) {
                        aClass30_Sub2_Sub1_Sub1_865.method348(28, 0);
                    }
                    if (tabID == 8) {
                        aClass30_Sub2_Sub1_Sub1_866.method348(66, 0);
                    }
                    if (tabID == 9) {
                        aClass30_Sub2_Sub1_Sub1_866.method348(99, 0);
                    }

                    if (tabID == 14) //clan chat
                    {
                        aClass30_Sub2_Sub1_Sub1_866.method348(132, 0);
                    }

                    if (tabID == 11) {
                        aClass30_Sub2_Sub1_Sub1_866.method348(165, 0);
                    }
                    if (tabID == 12) {
                        aClass30_Sub2_Sub1_Sub1_866.method348(198, 0);
                    }
                    if (tabID == 13) {
                        aClass30_Sub2_Sub1_Sub1_868.method348(231, 0);
                    }
                }

                if (super.anInt20 >= 524 && super.anInt20 <= 561 && super.anInt21 >= 467 && super.anInt21 < 503 && tabInterfaceIDs[7] != -1) {
                    if (tabID == 7) {
                        rbch.method348(28, 0);
                    } else {
                        rbh.method348(28, 0);
                    }
                }
                if (super.anInt20 >= 562 && super.anInt20 <= 594 && super.anInt21 >= 467 && super.anInt21 < 503 && tabInterfaceIDs[8] != -1) {
                    if (tabID == 8) {
                        nch.method348(66, 0);
                    } else {
                        nh.method348(66, 0);
                    }
                }
                if (super.anInt20 >= 595 && super.anInt20 <= 627 && super.anInt21 >= 467 && super.anInt21 < 503 && tabInterfaceIDs[9] != -1) {
                    if (tabID == 9) {
                        nch.method348(99, 0);
                    } else {
                        nh.method348(99, 0);
                    }
                }

                if (super.anInt20 >= 628 && super.anInt20 <= 660 && super.anInt21 >= 467 && super.anInt21 < 503 && tabInterfaceIDs[10] != -1) {
                    if (tabID == 14) {
                        nch.method348(132, 0);
                    } else {
                        nh.method348(132, 0);
                    }
                }

                if (super.anInt20 >= 661 && super.anInt20 <= 693 && super.anInt21 >= 467 && super.anInt21 < 503 && tabInterfaceIDs[11] != -1) {
                    if (tabID == 11) {
                        nch.method348(165, 0);
                    } else {
                        nh.method348(165, 0);
                    }
                }
                if (super.anInt20 >= 694 && super.anInt20 <= 726 && super.anInt21 >= 467 && super.anInt21 < 503 && tabInterfaceIDs[12] != -1) {
                    if (tabID == 12) {
                        nch.method348(198, 0);
                    } else {
                        nh.method348(198, 0);
                    }
                }
                if (super.anInt20 >= 727 && super.anInt20 <= 763 && super.anInt21 >= 467 && super.anInt21 < 503 && tabInterfaceIDs[13] != -1) {
                    if (tabID == 13) {
                        lbch.method348(231, 0);
                    } else {
                        lbh.method348(231, 0);
                    }
                }
                if (tabInterfaceIDs[8] != -1 && flashingSidebarId == 8) {
                    if (loopCycle % 20 >= 10);
                }
                sideIcons[14].method348(33, 7); //notes

                if (tabInterfaceIDs[8] != -1 && flashingSidebarId == 8) {
                    if (loopCycle % 20 >= 10);
                }
                sideIcons[7].method348(72, 8); //friends

                if (tabInterfaceIDs[9] != -1 && flashingSidebarId == 9) {
                    if (loopCycle % 20 >= 10);
                }
                sideIcons[8].method348(105, 8); //ignore



                if (tabInterfaceIDs[14] != -1 && flashingSidebarId == 14) {
                    if (loopCycle % 20 >= 10);
                }
                sideIcons[13].method348(137, 6); //cc


                if (tabInterfaceIDs[11] != -1 && flashingSidebarId == 11) {
                    if (loopCycle % 20 >= 10);
                }
                sideIcons[10].method348(170, 6);

                if (tabInterfaceIDs[12] != -1 && flashingSidebarId == 12) {
                    if (loopCycle % 20 >= 10);
                }
                sideIcons[11].method348(205, 5);
                if (tabInterfaceIDs[13] != -1 && flashingSidebarId == 13) {
                    if (loopCycle % 20 >= 10);
                }
                sideIcons[12].method348(237, 6);
            }

            aClass15_1124.drawGraphics(466, 23680, super.aGraphics12, 496);
            aClass15_1165.method237(0);
        }

        if (needChatAreaRedraw) {
            needChatAreaRedraw = false;
            aClass15_1123.method237(0);
            aClass30_Sub2_Sub1_Sub1_1027.method348(0, 0);
            if (clickedTab[0] == false) {
                if (!highLight[0]) {
                    Button0.method348(4, 7);//All
                } else {
                    HoverButton0.method348(4, 7);//Highlight
                }
            } else {
                if (!highLight[0]) {
                    Button1.method348(4, 7);//All
                } else {
                    HoverButton1.method348(4, 7);//Highlight
                }
            }

            if (clickedTab[1] == false) {
                if (!highLight[1]) {
                    if (flashRequest[0] && loopCycle % 60 < 30 || pauseFlash[0]) {
                        if (!pauseFlash[0]) {
                            if (System.currentTimeMillis() - flashRate > 1000) {
                                flashRate = System.currentTimeMillis();
                                flashTimes[0]--;
                            }
                        }
                        FlashButton0.method348(61, 7);//Game
                    } else {
                        Button0.method348(61, 7);//Game
                    }
                } else {
                    HoverButton0.method348(61, 7);//Highlight
                }
            } else {
                if (!highLight[1]) {
                    Button1.method348(61, 7);//Game
                } else {
                    HoverButton1.method348(61, 7);//Highlight
                }
            }


            if (clickedTab[2] == false) {
                if (!highLight[2]) {
                    if (flashRequest[1] && loopCycle % 60 < 30 || pauseFlash[1]) {
                        if (!pauseFlash[1]) {
                            if (System.currentTimeMillis() - flashRate > 1000) {
                                flashRate = System.currentTimeMillis();
                                flashTimes[1]--;
                            }
                        }
                        FlashButton0.method348(118, 7);
                    } else {
                        Button0.method348(118, 7);//Public
                    }
                } else {
                    HoverButton0.method348(118, 7);//Highlight
                }
            } else {
                if (!highLight[2]) {
                    Button1.method348(118, 7);//Public
                } else {
                    HoverButton1.method348(118, 7);//Highlight
                }
            }


            if (clickedTab[3] == false) {
                if (!highLight[3]) {
                    if (flashRequest[2] && loopCycle % 60 < 30 || pauseFlash[2]) {
                        if (!pauseFlash[2]) {
                            if (System.currentTimeMillis() - flashRate > 1000) {
                                flashRate = System.currentTimeMillis();
                                flashTimes[2]--;
                            }
                        }
                        FlashButton0.method348(175, 7);
                    } else {
                        Button0.method348(175, 7);//Private
                    }
                } else {
                    HoverButton0.method348(175, 7);//Highlight
                }
            } else {
                if (!highLight[3]) {
                    Button1.method348(175, 7);//Private
                } else {
                    HoverButton1.method348(175, 7);//Highlight
                }
            }


            if (clickedTab[4] == false) {
                if (!highLight[4]) {
                    if (flashRequest[3] && loopCycle % 60 < 30 || pauseFlash[3]) {
                        if (!pauseFlash[3]) {
                            if (System.currentTimeMillis() - flashRate > 1000) {
                                flashRate = System.currentTimeMillis();
                                flashTimes[3]--;
                            }
                        }
                        FlashButton0.method348(232, 7);
                    } else {
                        Button0.method348(232, 7);//Clan
                    }
                } else {
                    HoverButton0.method348(232, 7);//Highlight
                }
            } else {
                if (!highLight[4]) {
                    Button1.method348(232, 7);//Clan
                } else {
                    HoverButton1.method348(232, 7);//Highlight
                }
            }


            if (clickedTab[5] == false) {
                if (!highLight[5]) {
                    if (flashRequest[4] && loopCycle % 60 < 30 || pauseFlash[4]) {
                        if (!pauseFlash[4]) {
                            if (System.currentTimeMillis() - flashRate > 1000) {
                                flashRate = System.currentTimeMillis();
                                flashTimes[4]--;
                            }
                        }
                        FlashButton0.method348(289, 7);
                    } else {
                        Button0.method348(289, 7);//Trade
                    }
                } else {
                    HoverButton0.method348(289, 7);//Highlight
                }
            } else {
                if (!highLight[5]) {
                    Button1.method348(289, 7);//Trade
                } else {
                    HoverButton1.method348(289, 7);//Highlight
                }
            }








            if (hp == false) { //IF NOT CLICKED
                if (!highLight[6]) {
                    if (flashRequest[5] && loopCycle % 60 < 30 || pauseFlash[5]) {
                        if (!pauseFlash[5]) {
                            if (System.currentTimeMillis() - flashRate > 1000) {
                                flashRate = System.currentTimeMillis();
                                flashTimes[5]--;
                            }
                        }
                        FlashButton0.method348(346, 7);
                    } else {
                        Button0.method348(346, 7);//Assist
                    }
                } else {
                    HoverButton0.method348(346, 7);//Highlight
                }
            } else { //CLICKED
                if (!highLight[6]) {
                    Button1.method348(346, 7);//Assist
                } else {
                    HoverButton1.method348(346, 7);//Highlight
                }
            }
            if (!highLight[7]) {
                Button2.method348(403, 7);//Report Abuse
            } else {
                HoverButton2.method348(403, 7);//Highlight
            }

            smallFont.drawCenteredText(0xffffff, 31, "All", 22, true);

            smallFont.drawCenteredText(0xffffff, 88, "Game", 17, true);

            if (gameMode == 0) {
                smallFont.drawCenteredText(65280, 88, "@gre@All", 28, true);
            }
            if (gameMode == 1) {
                smallFont.drawCenteredText(65280, 88, "@yel@Filter", 28, true);
            }

            smallFont.drawCenteredText(0xffffff, 146, "Public", 17, true);

            if (publicChatMode == 0) {
                smallFont.drawCenteredText(65280, 146, "@gre@On", 28, true);
            }
            if (publicChatMode == 1) {
                smallFont.drawCenteredText(0xffff00, 146, "@yel@Friends", 28, true);
            }
            if (publicChatMode == 2) {
                smallFont.drawCenteredText(0xff0000, 146, "@red@Off", 28, true);
            }
            if (publicChatMode == 3) {
                smallFont.drawCenteredText(65535, 146, "@cya@Hide", 28, true);
            }

            smallFont.drawCenteredText(0xffffff, 203, "Private", 17, true);

            if (privateChatMode == 0) {
                smallFont.drawCenteredText(65280, 203, "@gre@On", 28, true);
            }
            if (privateChatMode == 1) {
                smallFont.drawCenteredText(0xffff00, 203, "@yel@Friends", 28, true);
            }
            if (privateChatMode == 2) {
                smallFont.drawCenteredText(0xff0000, 203, "@red@Off", 28, true);
            }

            smallFont.drawCenteredText(0xffffff, 260, "Clan", 17, true);

            if (ClanButton == 0) {
                smallFont.drawCenteredText(65280, 260, "@gre@On", 28, true);
            }
            if (ClanButton == 1) {
                smallFont.drawCenteredText(0xffff00, 260, "@yel@Friends", 28, true);
            }
            if (ClanButton == 2) {
                smallFont.drawCenteredText(0xff0000, 260, "@red@Off", 28, true);
            }

            smallFont.drawCenteredText(0xffffff, 317, "Trade", 17, true);

            if (tradeMode == 0) {
                smallFont.drawCenteredText(65280, 317, "@gre@On", 28, true);
            }
            if (tradeMode == 1) {
                smallFont.drawCenteredText(0xffff00, 317, "@yel@Friends", 28, true);
            }
            if (tradeMode == 2) {
                smallFont.drawCenteredText(0xff0000, 317, "@red@Off", 28, true);
            }

            smallFont.drawCenteredText(0xffffff, 374, "Head HP", 17, true);

            if (hp == true) {
                smallFont.drawCenteredText(65280, 374, "@gre@On", 28, true);
            }
            if (hp == false) {
                smallFont.drawCenteredText(0xff0000, 374, "@red@Off", 28, true);
            }

            smallFont.drawCenteredText(0xffffff, 458, "Report Abuse", 22, true);

            aClass15_1123.drawGraphics(472, 23680, super.aGraphics12, 0);
            aClass15_1165.method237(0);
            anInt945 = 0;
        }
        anInt945 = 0;
    }

    public final boolean buildFriendsListMenu(RSInterface class9) {
        int i = class9.anInt214;
        if (i >= 1 && i <= 200 || i >= 701 && i <= 900) { //friends list
            if (i >= 801) {
                i -= 701;
            } else if (i >= 701) {
                i -= 601;
            } else if (i >= 101) {
                i -= 101;
            } else {
                i--;
            }
            if (anInt863 >= 1) {
                //add mod options here
            }
            menuActionName[menuActionRow] = "Remove @lre@" + friendsList[i];
            menuActionID[menuActionRow] = 792;
            menuActionRow++;
            menuActionName[menuActionRow] = "Message @lre@" + friendsList[i];
            menuActionID[menuActionRow] = 639;
            menuActionRow++;
            return true;
        }
        if (i >= 401 && i <= 500) { //ignore list
            menuActionName[menuActionRow] = "Remove @lre@" + class9.message;
            menuActionID[menuActionRow] = 322;
            menuActionRow++;
            return true;
        } else {
            return false;
        }
    }

    public final void method104() {
        Animable_Sub3 class30_sub2_sub4_sub3 = (Animable_Sub3) aClass19_1056.reverseGetFirst();
        for (; class30_sub2_sub4_sub3 != null; class30_sub2_sub4_sub3 = (Animable_Sub3) aClass19_1056.reverseGetNext()) {
            if (class30_sub2_sub4_sub3.anInt1560 != plane || class30_sub2_sub4_sub3.aBoolean1567) {
                class30_sub2_sub4_sub3.unlink();
            } else if (loopCycle >= class30_sub2_sub4_sub3.anInt1564) {
                class30_sub2_sub4_sub3.method454(anInt945);
                if (class30_sub2_sub4_sub3.aBoolean1567) {
                    class30_sub2_sub4_sub3.unlink();
                } else {
                    aClass25_946.method285(class30_sub2_sub4_sub3.anInt1560, 0, (byte) 6, class30_sub2_sub4_sub3.anInt1563, -1, class30_sub2_sub4_sub3.anInt1562, 60, class30_sub2_sub4_sub3.anInt1561, class30_sub2_sub4_sub3, false);
                }
            }
        }
    }

    public final void method105(int j, int k, RSInterface class9, int l) {
        if (class9.type != 0 || class9.children == null) {
            return;
        }
        if (class9.aBoolean266 && anInt1026 != class9.id && anInt1048 != class9.id && anInt1039 != class9.id) {
            return;
        }
        int i1 = DrawingArea.topX;
        int j1 = DrawingArea.topY;
        int k1 = DrawingArea.bottomX;
        int l1 = DrawingArea.bottomY;
        DrawingArea.setDrawingArea(l + class9.height, k, k + class9.width, l);
        int i2 = class9.children.length;
        for (int j2 = 0; j2 < i2; j2++) {
            int k2 = class9.childX[j2] + k;
            int l2 = (class9.childY[j2] + l) - j;
            RSInterface class9_1 = RSInterface.interfaceCache[class9.children[j2]];
            k2 += class9_1.anInt263;
            l2 += class9_1.anInt265;
            if (class9_1.anInt214 > 0) {
                method75(950, class9_1);
            }
            if (class9_1.type == 0) {
                if (class9_1.scrollPosition > class9_1.scrollMax - class9_1.height) {
                    class9_1.scrollPosition = class9_1.scrollMax - class9_1.height;
                }
                if (class9_1.scrollPosition < 0) {
                    class9_1.scrollPosition = 0;
                }
                method105(class9_1.scrollPosition, k2, class9_1, l2);
                if (class9_1.scrollMax > class9_1.height) {
                    drawScrollbar(class9_1.height, class9_1.scrollPosition, l2, k2 + class9_1.width, class9_1.scrollMax);
                }
            } else if (class9_1.type != 1) {
                if (class9_1.type == 2) {
                    int i3 = 0;
                    for (int l3 = 0; l3 < class9_1.height; l3++) {
                        for (int l4 = 0; l4 < class9_1.width; l4++) {
                            int k5 = k2 + l4 * (32 + class9_1.invSpritePadX);
                            int j6 = l2 + l3 * (32 + class9_1.invSpritePadY);
                            if (i3 < 20) {
                                k5 += class9_1.spritesX[i3];
                                j6 += class9_1.spritesY[i3];
                            }
                            if (class9_1.inv[i3] > 0) {
                                int k6 = 0;
                                int j7 = 0;
                                int j9 = class9_1.inv[i3] - 1;
                                if (k5 > DrawingArea.topX - 32 && k5 < DrawingArea.bottomX && j6 > DrawingArea.topY - 32 && j6 < DrawingArea.bottomY || activeInterfaceType != 0 && anInt1085 == i3) {
                                    int l9 = 0;
                                    if (anInt1282 == 1 && anInt1283 == i3 && anInt1284 == class9_1.id) {
                                        l9 = 0xffffff;
                                    }
                                    Sprite class30_sub2_sub1_sub1_2 = ItemDef.getSprite(j9, class9_1.invStackSized[i3], l9, 9);
                                    if (class30_sub2_sub1_sub1_2 != null) {
                                        if (activeInterfaceType != 0 && anInt1085 == i3 && anInt1084 == class9_1.id) {
                                            k6 = super.anInt20 - anInt1087;
                                            j7 = super.anInt21 - anInt1088;
                                            if (k6 < 5 && k6 > -5) {
                                                k6 = 0;
                                            }
                                            if (j7 < 5 && j7 > -5) {
                                                j7 = 0;
                                            }
                                            if (anInt989 < 5) {
                                                k6 = 0;
                                                j7 = 0;
                                            }
                                            class30_sub2_sub1_sub1_2.method350(k5 + k6, j6 + j7, 128);
                                            if (j6 + j7 < DrawingArea.topY && class9.scrollPosition > 0) {
                                                int i10 = (anInt945 * (DrawingArea.topY - j6 - j7)) / 3;
                                                if (i10 > anInt945 * 10) {
                                                    i10 = anInt945 * 10;
                                                }
                                                if (i10 > class9.scrollPosition) {
                                                    i10 = class9.scrollPosition;
                                                }
                                                class9.scrollPosition -= i10;
                                                anInt1088 += i10;
                                            }
                                            if (j6 + j7 + 32 > DrawingArea.bottomY && class9.scrollPosition < class9.scrollMax - class9.height) {
                                                int j10 = (anInt945 * ((j6 + j7 + 32) - DrawingArea.bottomY)) / 3;
                                                if (j10 > anInt945 * 10) {
                                                    j10 = anInt945 * 10;
                                                }
                                                if (j10 > class9.scrollMax - class9.height - class9.scrollPosition) {
                                                    j10 = class9.scrollMax - class9.height - class9.scrollPosition;
                                                }
                                                class9.scrollPosition += j10;
                                                anInt1088 -= j10;
                                            }
                                        } else if (atInventoryInterfaceType != 0 && anInt1245 == i3 && anInt1244 == class9_1.id) {
                                            class30_sub2_sub1_sub1_2.method350(k5, j6, 128);
                                        } else {
                                            class30_sub2_sub1_sub1_2.method348(k5, j6);
                                        }
                                        if (class30_sub2_sub1_sub1_2.maxWidth == 33 || class9_1.invStackSized[i3] != 1) {

                                            int amount = class9_1.invStackSized[i3];
                                            smallFont.method385(0, intToKOrMil(amount), j6 + 10 + j7, 822, k5 + 1 + k6);
                                            if (amount >= 1000000000)//1b
                                            {
                                                smallFont.method385(0x00ffff, intToKOrMil(amount), j6 + 9 + j7, 822, k5 + k6);
                                            } else if (amount >= 100000000)//100m
                                            {
                                                smallFont.method385(0x00ff80, intToKOrMil(amount), j6 + 9 + j7, 822, k5 + k6);
                                            } else if (amount >= 10000000)//10m
                                            {
                                                smallFont.method385(0x00ff80, intToKOrMil(amount), j6 + 9 + j7, 822, k5 + k6);
                                            } else if (amount >= 100000)//100k
                                            {
                                                smallFont.method385(0xffffff, intToKOrMil(amount), j6 + 9 + j7, 822, k5 + k6);
                                            } else if (amount >= 1000) //1k
                                            {
                                                smallFont.method385(0xffff00, intToKOrMil(amount), j6 + 9 + j7, 822, k5 + k6);
                                            } else //other
                                            {
                                                smallFont.method385(0xffff00, intToKOrMil(amount), j6 + 9 + j7, 822, k5 + k6);
                                            }
                                        }
                                    }
                                }
                            } else if (class9_1.sprites != null && i3 < 20) {
                                Sprite class30_sub2_sub1_sub1_1 = class9_1.sprites[i3];
                                if (class30_sub2_sub1_sub1_1 != null) {
                                    class30_sub2_sub1_sub1_1.method348(k5, j6);
                                }
                            }
                            i3++;
                        }
                    }
                } else if (class9_1.type == 3) {
                    boolean flag = false;
                    if (anInt1039 == class9_1.id || anInt1048 == class9_1.id || anInt1026 == class9_1.id) {
                        flag = true;
                    }
                    int j3;
                    if (method131(class9_1, false)) {
                        j3 = class9_1.anInt219;
                        if (flag && class9_1.anInt239 != 0) {
                            j3 = class9_1.anInt239;
                        }
                    } else {
                        j3 = class9_1.textColor;
                        if (flag && class9_1.anInt216 != 0) {
                            j3 = class9_1.anInt216;
                        }
                    }
                    if (class9_1.aByte254 == 0) {
                        if (class9_1.aBoolean227) {
                            DrawingArea.method336(class9_1.height, l2, k2, j3, class9_1.width);
                        } else {
                            DrawingArea.fillPixels(k2, class9_1.width, class9_1.height, j3, l2);
                        }
                    } else if (class9_1.aBoolean227) {
                        DrawingArea.method335(j3, l2, class9_1.width, class9_1.height, 256 - (class9_1.aByte254 & 0xff), k2);
                    } else {
                        DrawingArea.method338(l2, class9_1.height, 256 - (class9_1.aByte254 & 0xff), j3, class9_1.width, k2);
                    }
                } else if (class9_1.type == 4) {
                    TextDrawingClass class30_sub2_sub1_sub4 = class9_1.textDrawingAreas;
                    String s = class9_1.message;
                    boolean flag1 = false;
                    if (anInt1039 == class9_1.id || anInt1048 == class9_1.id || anInt1026 == class9_1.id) {
                        flag1 = true;
                    }
                    int i4;
                    if (method131(class9_1, false)) {
                        i4 = class9_1.anInt219;
                        if (flag1 && class9_1.anInt239 != 0) {
                            i4 = class9_1.anInt239;
                        }
                        if (class9_1.aString228.length() > 0) {
                            s = class9_1.aString228;
                        }
                    } else {
                        i4 = class9_1.textColor;
                        if (flag1 && class9_1.anInt216 != 0) {
                            i4 = class9_1.anInt216;
                        }
                    }
                    if (class9_1.atActionType == 6 && isInterfaceOpen) {
                        s = "Please wait...";
                        i4 = class9_1.textColor;
                    }
                    if (DrawingArea.width == 506) {
                        if (i4 == 0xffff00) {
                            i4 = 255;
                        }
                        if (i4 == 49152) {
                            i4 = 0xffffff;
                        }
                    }
                    for (int l6 = l2 + class30_sub2_sub1_sub4.anInt1497; s.length() > 0; l6 += class30_sub2_sub1_sub4.anInt1497) {
                        if (s.indexOf("%") != -1) {
                            do {
                                int k7 = s.indexOf("%1");
                                if (k7 == -1) {
                                    break;
                                }
                                if (class9_1.id < 4000 || class9_1.id > 5000 && class9_1.id != 13921 && class9_1.id != 13922 && class9_1.id != 12171 && class9_1.id != 12172) {
                                    s = s.substring(0, k7) + methodR(369, extractInterfaceValues(class9_1, 0)) + s.substring(k7 + 2);
                                } else {
                                    s = s.substring(0, k7) + method93(369, extractInterfaceValues(class9_1, 0)) + s.substring(k7 + 2);
                                }
                            } while (true);
                            do {
                                int l7 = s.indexOf("%2");
                                if (l7 == -1) {
                                    break;
                                }
                                s = s.substring(0, l7) + method93(369, extractInterfaceValues(class9_1, 1)) + s.substring(l7 + 2);
                            } while (true);
                            do {
                                int i8 = s.indexOf("%3");
                                if (i8 == -1) {
                                    break;
                                }
                                s = s.substring(0, i8) + method93(369, extractInterfaceValues(class9_1, 2)) + s.substring(i8 + 2);
                            } while (true);
                            do {
                                int j8 = s.indexOf("%4");
                                if (j8 == -1) {
                                    break;
                                }
                                s = s.substring(0, j8) + method93(369, extractInterfaceValues(class9_1, 3)) + s.substring(j8 + 2);
                            } while (true);
                            do {
                                int k8 = s.indexOf("%5");
                                if (k8 == -1) {
                                    break;
                                }
                                s = s.substring(0, k8) + method93(369, extractInterfaceValues(class9_1, 4)) + s.substring(k8 + 2);
                            } while (true);
                        }
                        int l8 = s.indexOf("\\n");
                        String s1;
                        if (l8 != -1) {
                            s1 = s.substring(0, l8);
                            s = s.substring(l8 + 2);
                        } else {
                            s1 = s;
                            s = "";
                        }
                        if (class9_1.aBoolean223) {
                            class30_sub2_sub1_sub4.drawCenteredText(i4, k2 + class9_1.width / 2, s1, l6, class9_1.aBoolean268);
                        } else {
                            class30_sub2_sub1_sub4.drawShadowedText(class9_1.aBoolean268, k2, i4, s1, l6);
                        }
                    }
                } else if (class9_1.type == 5) {
                    Sprite class30_sub2_sub1_sub1;
                    if (method131(class9_1, false)) {
                        class30_sub2_sub1_sub1 = class9_1.sprite2;
                    } else {
                        class30_sub2_sub1_sub1 = class9_1.sprite1;
                    }
                    if (anInt1136 == 1 && class9_1.id == spellID && spellID != 0) {
                        class30_sub2_sub1_sub1.drawSprite(k2, l2, 0xffffff);
                    } else {
                        //class30_sub2_sub1_sub1.method348(k2, l2);
                    }
                    if (class30_sub2_sub1_sub1 != null) {
                        class30_sub2_sub1_sub1.method348(k2, l2);
                    }
                } else if (class9_1.type == 6) {
                    int k3 = Texture.textureInt1;
                    int j4 = Texture.textureInt2;
                    Texture.textureInt1 = k2 + class9_1.width / 2;
                    Texture.textureInt2 = l2 + class9_1.height / 2;
                    int i5 = Texture.anIntArray1470[class9_1.anInt270] * class9_1.anInt269 >> 16;
                    int l5 = Texture.anIntArray1471[class9_1.anInt270] * class9_1.anInt269 >> 16;
                    boolean flag2 = method131(class9_1, false);
                    int i7;
                    if (flag2) {
                        i7 = class9_1.anInt258;
                    } else {
                        i7 = class9_1.anInt257;
                    }
                    Model class30_sub2_sub4_sub6;
                    if (i7 == -1) {
                        class30_sub2_sub4_sub6 = class9_1.method209(0, -1, -1, flag2);
                    } else {
                        Animation class20 = Animation.anims[i7];
                        class30_sub2_sub4_sub6 = class9_1.method209(0, class20.anIntArray354[class9_1.anInt246], class20.anIntArray353[class9_1.anInt246], flag2);
                    }
                    if (class30_sub2_sub4_sub6 != null) {
                        class30_sub2_sub4_sub6.method482(0, class9_1.anInt271, 0, class9_1.anInt270, 0, i5, l5);
                    }
                    Texture.textureInt1 = k3;
                    Texture.textureInt2 = j4;
                } else if (class9_1.type == 7) {
                    TextDrawingClass class30_sub2_sub1_sub4_1 = class9_1.textDrawingAreas;
                    int k4 = 0;
                    for (int j5 = 0; j5 < class9_1.height; j5++) {
                        for (int i6 = 0; i6 < class9_1.width; i6++) {
                            if (class9_1.inv[k4] > 0) {
                                ItemDef class8 = ItemDef.forID(class9_1.inv[k4] - 1);
                                String s2 = class8.name;
                                if (class8.stackable || class9_1.invStackSized[k4] != 1) {
                                    s2 = s2 + " x" + intToKOrMilLongName(class9_1.invStackSized[k4]);
                                }
                                int i9 = k2 + i6 * (115 + class9_1.invSpritePadX);
                                int k9 = l2 + j5 * (12 + class9_1.invSpritePadY);
                                if (class9_1.aBoolean223) {
                                    class30_sub2_sub1_sub4_1.drawCenteredText(class9_1.textColor, i9 + class9_1.width / 2, s2, k9, class9_1.aBoolean268);
                                } else {
                                    class30_sub2_sub1_sub4_1.drawShadowedText(class9_1.aBoolean268, i9, class9_1.textColor, s2, k9);
                                }
                            }
                            k4++;
                        }
                    }
                }
            }
        }
        DrawingArea.setDrawingArea(l1, i1, k1, j1);
    }

    public final void method106(Background class30_sub2_sub1_sub2, int i) {
        int j = 256;
        if (i >= 0) {
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(126);
        }
        for (int k = 0; k < anIntArray1190.length; k++) {
            anIntArray1190[k] = 0;
        }

        for (int l = 0; l < 5000; l++) {
            int i1 = (int) (Math.random() * 128D * (double) j);
            anIntArray1190[i1] = (int) (Math.random() * 256D);
        }

        for (int j1 = 0; j1 < 20; j1++) {
            for (int k1 = 1; k1 < j - 1; k1++) {
                for (int i2 = 1; i2 < 127; i2++) {
                    int k2 = i2 + (k1 << 7);
                    anIntArray1191[k2] = (anIntArray1190[k2 - 1] + anIntArray1190[k2 + 1] + anIntArray1190[k2 - 128] + anIntArray1190[k2 + 128]) / 4;
                }

            }

            int ai[] = anIntArray1190;
            anIntArray1190 = anIntArray1191;
            anIntArray1191 = ai;
        }

        if (class30_sub2_sub1_sub2 != null) {
            int l1 = 0;
            for (int j2 = 0; j2 < class30_sub2_sub1_sub2.myHeight; j2++) {
                for (int l2 = 0; l2 < class30_sub2_sub1_sub2.myWidth; l2++) {
                    if (class30_sub2_sub1_sub2.pixelInfo[l1++] != 0) {
                        int i3 = l2 + 16 + class30_sub2_sub1_sub2.anInt1454;
                        int j3 = j2 + 16 + class30_sub2_sub1_sub2.anInt1455;
                        int k3 = i3 + (j3 << 7);
                        anIntArray1190[k3] = 0;
                    }
                }

            }

        }
    }

    private final void method107(int i, int j, Stream class30_sub2_sub2, byte byte0, Player class30_sub2_sub4_sub1_sub2) {
        if (byte0 != 25) {
            groundArray = null;
        }
        if ((i & 0x400) != 0) {
            class30_sub2_sub4_sub1_sub2.anInt1543 = class30_sub2_sub2.method428();
            class30_sub2_sub4_sub1_sub2.anInt1545 = class30_sub2_sub2.method428();
            class30_sub2_sub4_sub1_sub2.anInt1544 = class30_sub2_sub2.method428();
            class30_sub2_sub4_sub1_sub2.anInt1546 = class30_sub2_sub2.method428();
            class30_sub2_sub4_sub1_sub2.anInt1547 = class30_sub2_sub2.method436() + loopCycle;
            class30_sub2_sub4_sub1_sub2.anInt1548 = class30_sub2_sub2.method435() + loopCycle;
            class30_sub2_sub4_sub1_sub2.anInt1549 = class30_sub2_sub2.method428();
            class30_sub2_sub4_sub1_sub2.method446(true);
        }
        if ((i & 0x100) != 0) {
            class30_sub2_sub4_sub1_sub2.anInt1520 = class30_sub2_sub2.method434((byte) 108);
            int k = class30_sub2_sub2.readDWord();
            class30_sub2_sub4_sub1_sub2.anInt1524 = k >> 16;
            class30_sub2_sub4_sub1_sub2.anInt1523 = loopCycle + (k & 0xffff);
            class30_sub2_sub4_sub1_sub2.anInt1521 = 0;
            class30_sub2_sub4_sub1_sub2.anInt1522 = 0;
            if (((Entity) (class30_sub2_sub4_sub1_sub2)).anInt1523 > loopCycle) {
                class30_sub2_sub4_sub1_sub2.anInt1521 = -1;
            }
            if (((Entity) (class30_sub2_sub4_sub1_sub2)).anInt1520 == 65535) {
                class30_sub2_sub4_sub1_sub2.anInt1520 = -1;
            }
        }
        if ((i & 8) != 0) {
            int l = class30_sub2_sub2.method434((byte) 108);
            if (l == 65535) {
                l = -1;
            }
            int i2 = class30_sub2_sub2.method427();
            if (l == ((Entity) (class30_sub2_sub4_sub1_sub2)).anim && l != -1) {
                int i3 = Animation.anims[l].anInt365;
                if (i3 == 1) {
                    class30_sub2_sub4_sub1_sub2.anInt1527 = 0;
                    class30_sub2_sub4_sub1_sub2.anInt1528 = 0;
                    class30_sub2_sub4_sub1_sub2.anInt1529 = i2;
                    class30_sub2_sub4_sub1_sub2.anInt1530 = 0;
                }
                if (i3 == 2) {
                    class30_sub2_sub4_sub1_sub2.anInt1530 = 0;
                }
            } else if (l == -1 || ((Entity) (class30_sub2_sub4_sub1_sub2)).anim == -1 || Animation.anims[l].anInt359 >= Animation.anims[((Entity) (class30_sub2_sub4_sub1_sub2)).anim].anInt359) {
                class30_sub2_sub4_sub1_sub2.anim = l;
                class30_sub2_sub4_sub1_sub2.anInt1527 = 0;
                class30_sub2_sub4_sub1_sub2.anInt1528 = 0;
                class30_sub2_sub4_sub1_sub2.anInt1529 = i2;
                class30_sub2_sub4_sub1_sub2.anInt1530 = 0;
                class30_sub2_sub4_sub1_sub2.anInt1542 = ((Entity) (class30_sub2_sub4_sub1_sub2)).smallXYIndex;
            }
        }
        if ((i & 4) != 0) {
            class30_sub2_sub4_sub1_sub2.aString1506 = class30_sub2_sub2.readString();
            if (((Entity) (class30_sub2_sub4_sub1_sub2)).aString1506.charAt(0) == '~') {
                class30_sub2_sub4_sub1_sub2.aString1506 = ((Entity) (class30_sub2_sub4_sub1_sub2)).aString1506.substring(1);
                pushMessage(((Entity) (class30_sub2_sub4_sub1_sub2)).aString1506, 2, class30_sub2_sub4_sub1_sub2.name, aBoolean991);
            } else if (class30_sub2_sub4_sub1_sub2 == aClass30_Sub2_Sub4_Sub1_Sub2_1126) {
                pushMessage(((Entity) (class30_sub2_sub4_sub1_sub2)).aString1506, 2, class30_sub2_sub4_sub1_sub2.name, aBoolean991);
            }
            class30_sub2_sub4_sub1_sub2.anInt1513 = 0;
            class30_sub2_sub4_sub1_sub2.anInt1531 = 0;
            class30_sub2_sub4_sub1_sub2.textCycle = 150;
        }
        if ((i & 0x80) != 0) {
            int i1 = class30_sub2_sub2.method434((byte) 108);
            int j2 = class30_sub2_sub2.readUnsignedByte();
            int j3 = class30_sub2_sub2.method427();
            int k3 = class30_sub2_sub2.currentOffset;
            if (class30_sub2_sub4_sub1_sub2.name != null && class30_sub2_sub4_sub1_sub2.visible) {
                long l3 = TextClass.method583(class30_sub2_sub4_sub1_sub2.name);
                boolean flag = false;
                if (j2 <= 1) {
                    for (int i4 = 0; i4 < ignoreCount; i4++) {
                        if (ignoreListAsLongs[i4] != l3) {
                            continue;
                        }
                        flag = true;
                        break;
                    }

                }
                if (!flag && anInt1251 == 0) {
                    try {
                        aClass30_Sub2_Sub2_834.currentOffset = 0;
                        class30_sub2_sub2.method442(j3, 0, aClass30_Sub2_Sub2_834.buffer);
                        aClass30_Sub2_Sub2_834.currentOffset = 0;
                        String s = TextInput.method525(j3, aClass30_Sub2_Sub2_834);
                        s = Censor.doCensor(s);
                        class30_sub2_sub4_sub1_sub2.aString1506 = s;
                        class30_sub2_sub4_sub1_sub2.anInt1513 = i1 >> 8;
                        class30_sub2_sub4_sub1_sub2.anInt1531 = i1 & 0xff;
                        class30_sub2_sub4_sub1_sub2.textCycle = 150;
                        if (j2 == 2 || j2 == 3) {
                            pushMessage(s, 1, "@cr2@" + class30_sub2_sub4_sub1_sub2.name, aBoolean991);
                        } else if (j2 == 1) {
                            pushMessage(s, 1, "@cr1@" + class30_sub2_sub4_sub1_sub2.name, aBoolean991);
                        } else {
                            pushMessage(s, 2, class30_sub2_sub4_sub1_sub2.name, aBoolean991);
                        }
                    } catch (Exception exception) {
                        signlink.reporterror("Some Error"); //cde2
                    }
                }
            }
            class30_sub2_sub2.currentOffset = k3 + j3;
        }
        if ((i & 1) != 0) {
            class30_sub2_sub4_sub1_sub2.interactingEntity = class30_sub2_sub2.method434((byte) 108);
            if (((Entity) (class30_sub2_sub4_sub1_sub2)).interactingEntity == 65535) {
                class30_sub2_sub4_sub1_sub2.interactingEntity = -1;
            }
        }
        if ((i & 0x10) != 0) {
            int j1 = class30_sub2_sub2.method427();
            byte abyte0[] = new byte[j1];
            Stream class30_sub2_sub2_1 = new Stream(abyte0);
            class30_sub2_sub2.readBytes(j1, 0, abyte0);
            aClass30_Sub2_Sub2Array895[j] = class30_sub2_sub2_1;
            class30_sub2_sub4_sub1_sub2.updatePlayer(0, class30_sub2_sub2_1);
        }
        if ((i & 2) != 0) {
            class30_sub2_sub4_sub1_sub2.anInt1538 = class30_sub2_sub2.method436();
            class30_sub2_sub4_sub1_sub2.anInt1539 = class30_sub2_sub2.method434((byte) 108);
        }
        if ((i & 0x20) != 0) {
            int k1 = class30_sub2_sub2.readUnsignedByte();
            int k2 = class30_sub2_sub2.method426();
            class30_sub2_sub4_sub1_sub2.updateHitData(-35698, k2, k1, loopCycle);
            class30_sub2_sub4_sub1_sub2.loopCycleStatus = loopCycle + 300;
            class30_sub2_sub4_sub1_sub2.currentHealth = class30_sub2_sub2.method427();
            class30_sub2_sub4_sub1_sub2.maxHealth = class30_sub2_sub2.readUnsignedByte();
        }
        if ((i & 0x200) != 0) {
            int l1 = class30_sub2_sub2.readUnsignedByte();
            int l2 = class30_sub2_sub2.method428();
            class30_sub2_sub4_sub1_sub2.updateHitData(-35698, l2, l1, loopCycle);
            class30_sub2_sub4_sub1_sub2.loopCycleStatus = loopCycle + 300;
            class30_sub2_sub4_sub1_sub2.currentHealth = class30_sub2_sub2.readUnsignedByte();
            class30_sub2_sub4_sub1_sub2.maxHealth = class30_sub2_sub2.method427();
        }
    }

    public final void method108() {
        try {
            int j = ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).x + anInt1278;
            int k = ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).y + anInt1131;
            if (anInt1014 - j < -500 || anInt1014 - j > 500 || anInt1015 - k < -500 || anInt1015 - k > 500) {
                anInt1014 = j;
                anInt1015 = k;
            }
            if (anInt1014 != j) {
                anInt1014 += (j - anInt1014) / 16;
            }
            if (anInt1015 != k) {
                anInt1015 += (k - anInt1015) / 16;
            }
            if (super.keyArray[1] == 1) {
                anInt1186 += (-24 - anInt1186) / 2;
            } else if (super.keyArray[2] == 1) {
                anInt1186 += (24 - anInt1186) / 2;
            } else {
                anInt1186 /= 2;
            }
            if (super.keyArray[3] == 1) {
                anInt1187 += (12 - anInt1187) / 2;
            } else if (super.keyArray[4] == 1) {
                anInt1187 += (-12 - anInt1187) / 2;
            } else {
                anInt1187 /= 2;
            }
            anInt1185 = anInt1185 + anInt1186 / 2 & 0x7ff;
            anInt1184 += anInt1187 / 2;
            if (anInt1184 < 128) {
                anInt1184 = 128;
            }
            if (anInt1184 > 383) {
                anInt1184 = 383;
            }
            int l = anInt1014 >> 7;
            int i1 = anInt1015 >> 7;
            int j1 = method42(plane, anInt1015, true, anInt1014);
            int k1 = 0;
            if (l > 3 && i1 > 3 && l < 100 && i1 < 100) {
                for (int l1 = l - 4; l1 <= l + 4; l1++) {
                    for (int k2 = i1 - 4; k2 <= i1 + 4; k2++) {
                        int l2 = plane;
                        if (l2 < 3 && (byteGroundArray[1][l1][k2] & 2) == 2) {
                            l2++;
                        }
                        int i3 = j1 - intGroundArray[l2][l1][k2];
                        if (i3 > k1) {
                            k1 = i3;
                        }
                    }

                }

            }
            anInt1005++;
            if (anInt1005 > 1512) {
                anInt1005 = 0;
                aClass30_Sub2_Sub2_1192.createFrame(77);
                aClass30_Sub2_Sub2_1192.writeWordBigEndian(0);
                int i2 = aClass30_Sub2_Sub2_1192.currentOffset;
                aClass30_Sub2_Sub2_1192.writeWordBigEndian((int) (Math.random() * 256D));
                aClass30_Sub2_Sub2_1192.writeWordBigEndian(101);
                aClass30_Sub2_Sub2_1192.writeWordBigEndian(233);
                aClass30_Sub2_Sub2_1192.writeWord(45092);
                if ((int) (Math.random() * 2D) == 0) {
                    aClass30_Sub2_Sub2_1192.writeWord(35784);
                }
                aClass30_Sub2_Sub2_1192.writeWordBigEndian((int) (Math.random() * 256D));
                aClass30_Sub2_Sub2_1192.writeWordBigEndian(64);
                aClass30_Sub2_Sub2_1192.writeWordBigEndian(38);
                aClass30_Sub2_Sub2_1192.writeWord((int) (Math.random() * 65536D));
                aClass30_Sub2_Sub2_1192.writeWord((int) (Math.random() * 65536D));
                aClass30_Sub2_Sub2_1192.writeBytes(aClass30_Sub2_Sub2_1192.currentOffset - i2);
            }
            int j2 = k1 * 192;
            if (j2 > 0x17f00) {
                j2 = 0x17f00;
            }
            if (j2 < 32768) {
                j2 = 32768;
            }
            if (j2 > anInt984) {
                anInt984 += (j2 - anInt984) / 24;
                return;
            }
            if (j2 < anInt984) {
                anInt984 += (j2 - anInt984) / 80;
                return;
            }
        } catch (Exception _ex) {
            signlink.reporterror("glfc_ex " + ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).x + "," + ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).y + "," + anInt1014 + "," + anInt1015 + "," + anInt1069 + "," + anInt1070 + "," + baseX + "," + baseY);
            throw new RuntimeException("eek");
        }
    }

    public final void processDrawing() {
        if (rsAlreadyLoaded || loadingError || genericLoadingError) {
            showErrorScreen();
            return;
        }
        drawCycle++;
        if (!loggedIn) {
            drawLoginScreen(false);
        } else {
            drawGameScreen();
        }
        anInt1213 = 0;
    }

    public final boolean method109(boolean flag, String s) {
        if (s == null) {
            return false;
        }
        for (int i = 0; i < friendsCount; i++) {
            if (s.equalsIgnoreCase(friendsList[i])) {
                return true;
            }
        }

        if (flag) {
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(138);
        }
        return s.equalsIgnoreCase(aClass30_Sub2_Sub4_Sub1_Sub2_1126.name);
    }

    public static final String method110(int i, int j, boolean flag) {
        if (!flag) {
            throw new NullPointerException();
        }
        int k = i - j;
        if (k < -9) {
            return "@red@";
        }
        if (k < -6) {
            return "@or3@";
        }
        if (k < -3) {
            return "@or2@";
        }
        if (k < 0) {
            return "@or1@";
        }
        if (k > 9) {
            return "@gre@";
        }
        if (k > 6) {
            return "@gr3@";
        }
        if (k > 3) {
            return "@gr2@";
        }
        if (k > 0) {
            return "@gr1@";
        } else {
            return "@yel@";
        }
    }

    public final void method111(byte byte0, int i) {
        if (byte0 == 2) {
            byte0 = 0;
        } else {
            startUp();
        }
        signlink.wavevol = i;
    }

    public final void method112(int i) { //draw3dScreen
        noclip();
        if (i != 8) {
            anInt939 = 130;
        }
        method76((byte) -13);
        if (crossType == 1) {
            crosses[crossIndex / 100].method348(anInt914 - 8 - 4, anInt915 - 8 - 4);
            anInt1142++;
            if (anInt1142 > 67) {
                anInt1142 = 0;
                aClass30_Sub2_Sub2_1192.createFrame(78);
            }
        }
        if (crossType == 2) {
            crosses[4 + crossIndex / 100].method348(anInt914 - 8 - 4, anInt915 - 8 - 4);
        }
        if (wildernessSkullId != -1) {
            method119(anInt945, false, wildernessSkullId);
            method105(0, 0, RSInterface.interfaceCache[wildernessSkullId], 0);
        }
        if (openInterfaceID != -1) {
            method119(anInt945, false, openInterfaceID);
            method105(0, 0, RSInterface.interfaceCache[openInterfaceID], 0);
        }
        method70();
        if (super.anInt20 >= 3 && super.anInt20 <= 59 && super.anInt21 >= 481 && super.anInt21 <= 503) {
            needChatAreaRedraw = true;
            highLight[0] = true;
        } else {
            needChatAreaRedraw = true;
            highLight[0] = false;
        }

        if (super.anInt20 >= 61 && super.anInt20 <= 116 && super.anInt21 >= 481 && super.anInt21 <= 503) {
            needChatAreaRedraw = true;
            highLight[1] = true;
        } else {
            needChatAreaRedraw = true;
            highLight[1] = false;
        }

        if (super.anInt20 >= 118 && super.anInt20 <= 173 && super.anInt21 >= 481 && super.anInt21 <= 503) {
            needChatAreaRedraw = true;
            highLight[2] = true;
        } else {
            needChatAreaRedraw = true;
            highLight[2] = false;
        }

        if (super.anInt20 >= 175 && super.anInt20 <= 230 && super.anInt21 >= 481 && super.anInt21 <= 503) {
            needChatAreaRedraw = true;
            highLight[3] = true;
        } else {
            needChatAreaRedraw = true;
            highLight[3] = false;
        }

        if (super.anInt20 >= 232 && super.anInt20 <= 287 && super.anInt21 >= 481 && super.anInt21 <= 503) {
            needChatAreaRedraw = true;
            highLight[4] = true;
        } else {
            needChatAreaRedraw = true;
            highLight[4] = false;
        }

        if (super.anInt20 >= 289 && super.anInt20 <= 344 && super.anInt21 >= 481 && super.anInt21 <= 503) {
            needChatAreaRedraw = true;
            highLight[5] = true;
        } else {
            needChatAreaRedraw = true;
            highLight[5] = false;
        }

        if (super.anInt20 >= 346 && super.anInt20 <= 401 && super.anInt21 >= 481 && super.anInt21 <= 503) {
            needChatAreaRedraw = true;
            highLight[6] = true;
        } else {
            needChatAreaRedraw = true;
            highLight[6] = false;
        }

        if (super.anInt20 >= 403 && super.anInt20 <= 513 && super.anInt21 >= 481 && super.anInt21 <= 503) {
            needChatAreaRedraw = true;
            tabAreaAltered = true;
            highLight[7] = true;
        } else {
            needChatAreaRedraw = true;
            tabAreaAltered = true;
            highLight[7] = false;
        }

        for (int i1 = 0; i1 < 6; i1++) {
            if (flashTimes[i1] == 0) {
                pauseFlash[i1] = true;
            }
        }

        if (!menuOpen) {
            processRightClick();
            method125();
        } else {
            if (menuScreenArea == 0) {
                drawMenu();
            }
        }
        if (multiOverlayIconID == 1) {
            headIcons[1].method348(472, 296);
        }

        if (aBoolean1156) {
            char c = '\u01FB';
            int k = 20;
            int i1 = 0xffff00;
            if (super.anInt8 < 15) {
                i1 = 0xff0000;
            }
            regularFont.method380("Fps:" + super.anInt8, c, i1, k);
            k += 15;
            Runtime runtime = Runtime.getRuntime();
            int j1 = (int) ((runtime.totalMemory() - runtime.freeMemory()) / 1024L);
            i1 = 0xffff00;
            if (j1 > 0x2000000 && lowMem) {
                i1 = 0xff0000;
            }
            regularFont.method380("Mem:" + j1 + "k", c, 0xffff00, k);
            k += 15;
        }

        if (nearby) {
            int pplnearby = anInt891; // players nearby
            regularFont.method385(0xc6b895, "                                                                                                                                Players Nearby: " + pplnearby, 13, 822, 4);
        }

        if (updateTime != 0) {
            int j = updateTime / 50;
            int l = j / 60;
            j %= 60;
            if (j < 10) {
                regularFont.method385(0x768251, "Server updating in: " + l + ":0" + j, 329, 822, 4);
            } else {
                regularFont.method385(0x768251, "Server updating in: " + l + ":" + j, 329, 822, 4);
            }
            anInt849++;
            if (anInt849 > 75) {
                anInt849 = 0;
                aClass30_Sub2_Sub2_1192.createFrame(148); //createFrame send packet 148
            }
        }
    }

    public final void method113(long l, int i) {
        try {
            if (l == 0L) {
                return;
            }
            if (ignoreCount >= 100) {
                pushMessage("Your ignore list is full. Max of 100 hit", 0, "", aBoolean991);
                return;
            }
            String s = TextClass.fixName(TextClass.longForName(l));
            for (int j = 0; j < ignoreCount; j++) {
                if (ignoreListAsLongs[j] == l) {
                    pushMessage(s + " is already on your ignore list", 0, "", aBoolean991);
                    return;
                }
            }

            if (i < 4 || i > 4) {
                return;
            }
            for (int k = 0; k < friendsCount; k++) {
                if (friendsListAsLongs[k] == l) {
                    pushMessage("Please remove " + s + " from your friend list first", 0, "", aBoolean991);
                    return;
                }
            }

            ignoreListAsLongs[ignoreCount++] = l;
            needDrawTabArea = true;
            aClass30_Sub2_Sub2_1192.createFrame(133);
            aClass30_Sub2_Sub2_1192.writeQWord(l);
            return;
        } catch (RuntimeException runtimeexception) {
            signlink.reporterror("45688, " + l + ", " + i + ", " + runtimeexception.toString());
        }
        throw new RuntimeException();
    }

    public final void method114(byte byte0) {
        if (byte0 != aByte973) {
            return;
        }
        for (int i = -1; i < anInt891; i++) {
            int j;
            if (i == -1) {
                j = anInt889;
            } else {
                j = anIntArray892[i];
            }
            Player class30_sub2_sub4_sub1_sub2 = playerArray[j];
            if (class30_sub2_sub4_sub1_sub2 != null) {
                method96(46988, 1, class30_sub2_sub4_sub1_sub2);
            }
        }

    }

    private final void method115(byte byte0) {
        if (byte0 == 8) {
            byte0 = 0;
        } else {
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(101);
        }
        if (loadingStage == 2) {
            for (Class30_Sub1 class30_sub1 = (Class30_Sub1) aClass19_1179.reverseGetFirst(); class30_sub1 != null; class30_sub1 = (Class30_Sub1) aClass19_1179.reverseGetNext()) {
                if (class30_sub1.anInt1294 > 0) {
                    class30_sub1.anInt1294--;
                }
                if (class30_sub1.anInt1294 == 0) {
                    if (class30_sub1.anInt1299 < 0 || ObjectManager.method178(class30_sub1.anInt1299, class30_sub1.anInt1301, 8)) {
                        method142(class30_sub1.anInt1298, class30_sub1.anInt1295, class30_sub1.anInt1300, class30_sub1.anInt1301, class30_sub1.anInt1297, class30_sub1.anInt1296, class30_sub1.anInt1299, 4);
                        class30_sub1.unlink();
                    }
                } else {
                    if (class30_sub1.anInt1302 > 0) {
                        class30_sub1.anInt1302--;
                    }
                    if (class30_sub1.anInt1302 == 0 && class30_sub1.anInt1297 >= 1 && class30_sub1.anInt1298 >= 1 && class30_sub1.anInt1297 <= 102 && class30_sub1.anInt1298 <= 102 && (class30_sub1.anInt1291 < 0 || ObjectManager.method178(class30_sub1.anInt1291, class30_sub1.anInt1293, 8))) {
                        method142(class30_sub1.anInt1298, class30_sub1.anInt1295, class30_sub1.anInt1292, class30_sub1.anInt1293, class30_sub1.anInt1297, class30_sub1.anInt1296, class30_sub1.anInt1291, 4);
                        class30_sub1.anInt1302 = -1;
                        if (class30_sub1.anInt1291 == class30_sub1.anInt1299 && class30_sub1.anInt1299 == -1) {
                            class30_sub1.unlink();
                        } else if (class30_sub1.anInt1291 == class30_sub1.anInt1299 && class30_sub1.anInt1292 == class30_sub1.anInt1300 && class30_sub1.anInt1293 == class30_sub1.anInt1301) {
                            class30_sub1.unlink();
                        }
                    }
                }
            }
        }
    }

    public final void determineMenuSize() {
        int i = boldFont.getTextWidth("Choose Option"); //getTextWidth
        for (int j = 0; j < menuActionRow; j++) { //go through each menu option
            int k = boldFont.getTextWidth(menuActionName[j]); //getTextWidth on each mmenu item
            if (k > i) //if the menu item is more than choose option, then the menu width is made bigger
            {
                i = k;
            }
        }
        /*
        i = menu width
        l = menu height
        
        i1 = menu horizontal position
        l1 = menu vertical position
        
         */



        i += 8;
        int l = 15 * menuActionRow + 23; // each menu item is 15 px high + 21 for the top and bottom i guess

        if (super.saveClickX > 4 && super.saveClickY > 4 && super.saveClickX < 516 && super.saveClickY < 338) { //gameframe
            int i1 = super.saveClickX - 4 - i / 2; //determine horixontal pos
            if (i1 + i > 510) {
                i1 = 510 - i;
            }
            if (i1 < 1) {
                i1 = 1;
            }
            int l1 = super.saveClickY - 4;
            if (l1 + l > 333) {
                l1 = 333 - l;
            }
            if (l1 < 0) {
                l1 = 0;
            }
            menuOpen = true;
            menuScreenArea = 0; //position for menu (eg offsetID...)
            menuOffsetX = i1;
            menuOffsetY = l1;
            menuWidth = i;
            menuHeight = l; //height
        }

        if (super.saveClickX > 553 && super.saveClickY > 205 && super.saveClickX < 743 && super.saveClickY < 466) { //inventory
            int j1 = super.saveClickX - 553 - i / 2;
            if (j1 < 0) {
                j1 = 0;
            }
            if (j1 + i > 190) {
                j1 = 190 - i;
            }
            int i2 = super.saveClickY - 205;
            if (i2 < 0) {
                i2 = 0;
            }
            if (i2 + l > 261) {
                i2 = 261 - l;
            }
            menuOpen = true;
            menuScreenArea = 1;
            menuOffsetX = j1;
            menuOffsetY = i2;
            menuWidth = i;
            menuHeight = 15 * menuActionRow + 22;
        }

        if (super.saveClickX > 6 && super.saveClickY > 344 && super.saveClickX < 496 && super.saveClickY < 503) { //chatbox
            int k1 = super.saveClickX - 6 - i / 2;
            if (k1 < 0) {
                k1 = 0;
            } else if (k1 + i > 488) {
                k1 = 488 - i;
            }
            int j2 = super.saveClickY - 344;
            if (j2 < 0) {
                j2 = 0;
            } else if (j2 + l > 128) {
                j2 = 128 - l;
            }
            menuOpen = true;
            menuScreenArea = 2; //
            menuOffsetX = k1;
            menuOffsetY = j2;
            menuWidth = i;
            menuHeight = 15 * menuActionRow + 22;
        }





        /* 
        
        //top tab click area
        super.anInt20 >= 524 && super.anInt20 <= 763 && super.anInt21 >= 168 && super.anInt21 < 205
        //bottom tab clicking area
        super.anInt20 >= 524 && super.anInt20 <= 763 && super.anInt21 >= 467 && super.anInt21 < 503
        
        //top tabs
        if (menuScreenArea == 3) {
        k2 -= 524;
        l2 -= 168;
        }
        //bottom tabs
        if (menuScreenArea == 4) {
        k2 -= 524;
        l2 -= 467;
        }
         */

        //top tasbs
        if (super.saveClickX >= 524 && super.saveClickX <= 763 && super.saveClickY >= 168 && super.saveClickY < 205) {
            int k1 = super.saveClickX - 524 - i / 2;
            if (k1 < 0) {
                k1 = 0;
            } else if (k1 + i > 763) {
                k1 = 763 - i;
            }
            int j2 = super.saveClickY;
            if (j2 < 0) {
                j2 = 0;
            } else if (j2 + l > 168) {
                j2 = 168 - l;
            }
            menuOpen = true;
            menuScreenArea = 3;
            menuOffsetX = k1;
            menuOffsetY = j2;
            menuWidth = i;
            menuHeight = 15 * menuActionRow + 22;
        }
        //bottom tabs
        if (super.saveClickX >= 524 && super.saveClickX <= 763 && super.saveClickY >= 467 && super.saveClickY < 503) {
            int k1 = super.saveClickX - 524 - i / 2;
            if (k1 < 0) {
                k1 = 0;
            } else if (k1 + i > 763) {
                k1 = 763 - i;
            }
            int j2 = super.saveClickY;
            if (j2 < 0) {
                j2 = 0;
            } else if (j2 + l > 168) {
                j2 = 168 - l;
            }
            menuOpen = true;
            menuScreenArea = 4;
            menuOffsetX = k1;
            menuOffsetY = j2;
            menuWidth = i;
            menuHeight = 15 * menuActionRow + 22;
        }




        if (super.saveClickX >= 515 && super.saveClickY >= 0 && super.saveClickX <= 765 && super.saveClickY <= 169) {

            //width
            int k1 = super.saveClickX - 515 - i / 2;
            if (k1 < 2) {
                k1 = 3;
            }
            if (k1 + i > 249) {
                k1 = 249 - i;
            }


            //height
            int j2 = super.saveClickY;
            if (j2 < 0) {
                j2 = 0;
            }
            if (j2 + l > 168) {
                j2 = 168 - l;
            }


            menuOpen = true;
            menuScreenArea = 5;
            menuOffsetX = k1;
            menuOffsetY = j2;
            menuWidth = i;
            menuHeight = 15 * menuActionRow + 22;
        }
    }

    private final void method117(Stream class30_sub2_sub2, int i, byte byte0) {
        class30_sub2_sub2.initBitAccess();
        if (byte0 == 5) {
            byte0 = 0;
        } else {
            pktType = class30_sub2_sub2.readUnsignedByte();
        }
        int j = class30_sub2_sub2.readBits(1);
        if (j == 0) {
            return;
        }
        int k = class30_sub2_sub2.readBits(2);
        if (k == 0) {
            anIntArray894[anInt893++] = anInt889;
            return;
        }
        if (k == 1) {
            int l = class30_sub2_sub2.readBits(3);
            aClass30_Sub2_Sub4_Sub1_Sub2_1126.moveInDir(false, (byte) 20, l);
            int k1 = class30_sub2_sub2.readBits(1);
            if (k1 == 1) {
                anIntArray894[anInt893++] = anInt889;
            }
            return;
        }
        if (k == 2) {
            int i1 = class30_sub2_sub2.readBits(3);
            aClass30_Sub2_Sub4_Sub1_Sub2_1126.moveInDir(true, (byte) 20, i1);
            int l1 = class30_sub2_sub2.readBits(3);
            aClass30_Sub2_Sub4_Sub1_Sub2_1126.moveInDir(true, (byte) 20, l1);
            int j2 = class30_sub2_sub2.readBits(1);
            if (j2 == 1) {
                anIntArray894[anInt893++] = anInt889;
            }
            return;
        }
        if (k == 3) {
            plane = class30_sub2_sub2.readBits(2);
            int j1 = class30_sub2_sub2.readBits(1);
            int i2 = class30_sub2_sub2.readBits(1);
            if (i2 == 1) {
                anIntArray894[anInt893++] = anInt889;
            }
            int k2 = class30_sub2_sub2.readBits(7);
            int l2 = class30_sub2_sub2.readBits(7);
            aClass30_Sub2_Sub4_Sub1_Sub2_1126.setPos(l2, k2, j1 == 1);
        }
    }

    public final void nullLoader(int i) {
        aBoolean831 = false;
        while (drawingFlames) {
            aBoolean831 = false;
            try {
                Thread.sleep(50L);
            } catch (Exception _ex) {
            }
        }
        aClass30_Sub2_Sub1_Sub2Array1152 = null;
        anIntArray850 = null;
        anIntArray851 = null;
        anIntArray852 = null;
        anIntArray853 = null;
        anIntArray1190 = null;
        anIntArray1191 = null;
        anIntArray828 = null;
        anIntArray829 = null;
        aClass30_Sub2_Sub1_Sub1_1201 = null;
        aClass30_Sub2_Sub1_Sub1_1202 = null;
        if (i < 3 || i > 3) {
            groundArray = null;
        }
    }

    public final boolean method119(int i, boolean flag, int j) {
        boolean flag1 = false;
        if (flag) {
            throw new NullPointerException();
        }
        RSInterface class9 = RSInterface.interfaceCache[j];
        for (int k = 0; k < class9.children.length; k++) {
            if (class9.children[k] == -1) {
                break;
            }
            RSInterface class9_1 = RSInterface.interfaceCache[class9.children[k]];
            if (class9_1.type == 1) {
                flag1 |= method119(i, false, class9_1.id);
            }
            if (class9_1.type == 6 && (class9_1.anInt257 != -1 || class9_1.anInt258 != -1)) {
                boolean flag2 = method131(class9_1, false);
                int l;
                if (flag2) {
                    l = class9_1.anInt258;
                } else {
                    l = class9_1.anInt257;
                }
                if (l != -1) {
                    Animation class20 = Animation.anims[l];
                    for (class9_1.anInt208 += i; class9_1.anInt208 > class20.method258(class9_1.anInt246);) {
                        class9_1.anInt208 -= class20.method258(class9_1.anInt246) + 1;
                        class9_1.anInt246++;
                        if (class9_1.anInt246 >= class20.anInt352) {
                            class9_1.anInt246 -= class20.anInt356;
                            if (class9_1.anInt246 < 0 || class9_1.anInt246 >= class20.anInt352) {
                                class9_1.anInt246 = 0;
                            }
                        }
                        flag1 = true;
                    }

                }
            }
        }

        return flag1;
    }

    public final int method120(int i) {
        if (i <= 0) {
            aBoolean1224 = !aBoolean1224;
        }
        int j = 3;
        if (yCameraCurve < 310) {
            int k = xCameraPos >> 7;
            int l = yCameraPos >> 7;
            int i1 = ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).x >> 7;
            int j1 = ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).y >> 7;
            if ((byteGroundArray[plane][k][l] & 4) != 0) {
                j = plane;
            }
            int k1;
            if (i1 > k) {
                k1 = i1 - k;
            } else {
                k1 = k - i1;
            }
            int l1;
            if (j1 > l) {
                l1 = j1 - l;
            } else {
                l1 = l - j1;
            }
            if (k1 > l1) {
                int i2 = (l1 * 0x10000) / k1;
                int k2 = 32768;
                while (k != i1) {
                    if (k < i1) {
                        k++;
                    } else if (k > i1) {
                        k--;
                    }
                    if ((byteGroundArray[plane][k][l] & 4) != 0) {
                        j = plane;
                    }
                    k2 += i2;
                    if (k2 >= 0x10000) {
                        k2 -= 0x10000;
                        if (l < j1) {
                            l++;
                        } else if (l > j1) {
                            l--;
                        }
                        if ((byteGroundArray[plane][k][l] & 4) != 0) {
                            j = plane;
                        }
                    }
                }
            } else {
                int j2 = (k1 * 0x10000) / l1;
                int l2 = 32768;
                while (l != j1) {
                    if (l < j1) {
                        l++;
                    } else if (l > j1) {
                        l--;
                    }
                    if ((byteGroundArray[plane][k][l] & 4) != 0) {
                        j = plane;
                    }
                    l2 += j2;
                    if (l2 >= 0x10000) {
                        l2 -= 0x10000;
                        if (k < i1) {
                            k++;
                        } else if (k > i1) {
                            k--;
                        }
                        if ((byteGroundArray[plane][k][l] & 4) != 0) {
                            j = plane;
                        }
                    }
                }
            }
        }
        if ((byteGroundArray[plane][((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).x >> 7][((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).y >> 7] & 4) != 0) {
            j = plane;
        }
        return j;
    }

    public final int method121(int i) {
        while (i >= 0) {
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(21);
        }
        int j = method42(plane, yCameraPos, true, xCameraPos);
        if (j - zCameraPos < 800 && (byteGroundArray[plane][xCameraPos >> 7][yCameraPos >> 7] & 4) != 0) {
            return plane;
        } else {
            return 3;
        }
    }

    public final void delIgnore(long l) {
        try {
            if (l == 0L) {
                return;
            }
            for (int j = 0; j < ignoreCount; j++) {
                if (ignoreListAsLongs[j] == l) {
                    ignoreCount--;
                    needDrawTabArea = true;
                    for (int k = j; k < ignoreCount; k++) {
                        ignoreListAsLongs[k] = ignoreListAsLongs[k + 1];
                    }
                    aClass30_Sub2_Sub2_1192.createFrame(74);
                    aClass30_Sub2_Sub2_1192.writeQWord(l);
                    return;
                }
            }
            return;
        } catch (RuntimeException runtimeexception) {
            signlink.reporterror("47229, " + 3 + ", " + l + ", " + runtimeexception.toString());
        }
        throw new RuntimeException();
    }

    public String getParameter(String s) {
        if (signlink.mainapp != null) {
            return signlink.mainapp.getParameter(s);
        } else {
            return super.getParameter(s);
        }
    }

    public final void adjustVolume(boolean flag, int i) {
        signlink.midivol = i;
        if (flag) {
            signlink.midi = "voladjust";
        }
    }

    public final int extractInterfaceValues(RSInterface class9, int j) {
        if (class9.valueIndexArray == null || j >= class9.valueIndexArray.length) {
            return -2;
        }
        try {
            int ai[] = class9.valueIndexArray[j];
            int k = 0;
            int l = 0;
            int i1 = 0;
            do {
                int j1 = ai[l++];
                int k1 = 0;
                byte byte0 = 0;
                if (j1 == 0) {
                    return k;
                }
                if (j1 == 1) {
                    k1 = currentStats[ai[l++]];
                }
                if (j1 == 2) {
                    k1 = maxStats[ai[l++]];
                }
                if (j1 == 3) {
                    k1 = currentExp[ai[l++]];
                }
                if (j1 == 4) {
                    RSInterface class9_1 = RSInterface.interfaceCache[ai[l++]];
                    int k2 = ai[l++];
                    if (k2 >= 0 && k2 < ItemDef.totalItems && (!ItemDef.forID(k2).membersObject || isMembers)) {
                        for (int j3 = 0; j3 < class9_1.inv.length; j3++) {
                            if (class9_1.inv[j3] == k2 + 1) {
                                k1 += class9_1.invStackSized[j3];
                            }
                        }
                    }
                }
                if (j1 == 5) {
                    k1 = variousSettings[ai[l++]];
                }
                if (j1 == 6) {
                    k1 = anIntArray1019[maxStats[ai[l++]] - 1];
                }
                if (j1 == 7) {
                    k1 = (variousSettings[ai[l++]] * 100) / 46875;
                }
                if (j1 == 8) {
                    k1 = aClass30_Sub2_Sub4_Sub1_Sub2_1126.combatLevel;
                }
                if (j1 == 9) {
                    for (int l1 = 0; l1 < Skills.skillsCount; l1++) {
                        if (Skills.skillEnabled[l1]) {
                            k1 += maxStats[l1];
                        }
                    }
                }
                if (j1 == 10) {
                    RSInterface class9_2 = RSInterface.interfaceCache[ai[l++]];
                    int l2 = ai[l++] + 1;
                    if (l2 >= 0 && l2 < ItemDef.totalItems && (!ItemDef.forID(l2).membersObject || isMembers)) {
                        for (int k3 = 0; k3 < class9_2.inv.length; k3++) {
                            if (class9_2.inv[k3] != l2) {
                                continue;
                            }
                            k1 = 0x3b9ac9ff;
                            break;
                        }
                    }
                }
                if (j1 == 11) {
                    k1 = energy;
                }
                if (j1 == 12) {
                    k1 = weight;
                }
                if (j1 == 13) {
                    int i2 = variousSettings[ai[l++]];
                    int i3 = ai[l++];
                    k1 = (i2 & 1 << i3) == 0 ? 0 : 1;
                }
                if (j1 == 14) {
                    int j2 = ai[l++];
                    VarBit class37 = VarBit.cache[j2];
                    int l3 = class37.anInt648;
                    int i4 = class37.anInt649;
                    int j4 = class37.anInt650;
                    int k4 = anIntArray1232[j4 - i4];
                    k1 = variousSettings[l3] >> i4 & k4;
                }
                if (j1 == 15) {
                    byte0 = 1;
                }
                if (j1 == 16) {
                    byte0 = 2;
                }
                if (j1 == 17) {
                    byte0 = 3;
                }
                if (j1 == 18) {
                    k1 = (((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).x >> 7) + baseX;
                }
                if (j1 == 19) {
                    k1 = (((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).y >> 7) + baseY;
                }
                if (j1 == 20) {
                    k1 = ai[l++];
                }
                if (byte0 == 0) {
                    if (i1 == 0) {
                        k += k1;
                    }
                    if (i1 == 1) {
                        k -= k1;
                    }
                    if (i1 == 2 && k1 != 0) {
                        k /= k1;
                    }
                    if (i1 == 3) {
                        k *= k1;
                    }
                    i1 = 0;
                } else {
                    i1 = byte0;
                }
            } while (true);
        } catch (Exception _ex) {
            return -1;
        }
    }

    public final void method125() { //drawTooltip
        if (menuActionRow < 2 && anInt1282 == 0 && anInt1136 == 0) {
            return;
        }
        String s;
        if (anInt1282 == 1 && menuActionRow < 2) {
            s = "Use " + aString1286 + " on...";
        } else if (anInt1136 == 1 && menuActionRow < 2) {
            s = aString1139 + "...";
        } else {
            s = menuActionName[menuActionRow - 1]; //menuActionName
        }
        if (menuActionRow > 2) {
            s = s + "@whi@ / " + (menuActionRow - 2) + " more options";
        }
        boldFont.method390(true, 4, 0xffffff, s, loopCycle / 1000, 973, 15);//draw the tooltip

        //if(i != 45706) {
        //	for(int j = 1; j > 0; j++);
        //}

    }

    public void drawMinimapItems() {
        drawHP();
        drawPrayer();
        drawRunOrb();

        if (super.anInt20 >= 706 && super.anInt20 <= 762 && super.anInt21 >= 52 && super.anInt21 < 87) {
            prayHover = true;
        } else {
            prayHover = false;
        }

        if (super.anInt20 >= 706 && super.anInt20 <= 762 && super.anInt21 >= 95 && super.anInt21 < 128) {
            runHover = true;
        } else {
            runHover = false;
        }

        if (super.anInt20 >= 765 - 20 && super.anInt20 <= 765 - 1 && super.anInt21 >= 1 && super.anInt21 < 23) {
            logHover = true;
        } else {
            logHover = false;
        }

        if (super.anInt20 >= 765 - 42 && super.anInt20 <= 765 - 22 && super.anInt21 >= 1 && super.anInt21 < 23) {
            questionHover = true;
        } else {
            questionHover = false;
        }

        if (!questionHover) { //not hoverine logout
            LogoutDoor[3].method348(208, 1);
        } else { //hovering logout
            LogoutDoor[4].method348(208, 1);
        }

        if (tabID != 10) { //not on logout tab
            if (!logHover) { //not hoverine logout
                LogoutDoor[0].method348(229, 1);
            } else { //hovering logout
                LogoutDoor[1].method348(229, 1);
            }
        } else {
            LogoutDoor[2].method348(229, 1);
        }

        if (super.anInt20 >= 522 && super.anInt20 <= 558 && super.anInt21 >= 124 && super.anInt21 < 161) {
            GlobeSprite.method348(11, 124);
        }

        zoomSprites[0].method348(13, 58);
        zoomSprites[1].method348(11, 74);
    }

    public final void drawMiniMap() {
        aClass15_1164.method237(0); //init the minimap drawing area (that i have played with)
        CustomMapback.method348(0, 0); //fixes frame99 (may cause problems..)

        int compassX = 11;
        int compassY = 8;
        int MapX = 45;

        //			compass.method352(33, anInt1185, anIntArray1057, 256, anIntArray968, -236, 25, 8, 11, 33, 25); //compass drawing??


        if (minimapState == 2) {
            compass.method352(33, anInt1185, anIntArray1057, 256, anIntArray968, 25, compassY, compassX - 1, 33, 25);
            compass.method352(33, anInt1185, anIntArray1057, 256, anIntArray968, 25, compassY, compassX, 33, 25);

            if (menuOpen && menuScreenArea == 5) {
                drawMenu();
            }

            aClass15_1165.method237(0);
            return;
        }



        int i = anInt1185 + minimapInt2 & 0x7ff; //minimapInt1


        int j = 48 + ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).x / 32;
        int l2 = 464 - ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).y / 32;

        for (int x = 0; x < anIntArray1229.length; x++) {
            anIntArray1229[x] = 172;
            anIntArray1052[x] = -22;
        }

        aClass30_Sub2_Sub1_Sub1_1263.method352(152, i, anIntArray1229, 256 + minimapInt3, anIntArray1052, l2, 9, MapX, 146, j);
        compass.method352(33, anInt1185, anIntArray1057, 256, anIntArray968, 25, compassY, compassX, 33, 25);




        /* 	
        int i = anInt1185 + minimapInt2 & 0x7ff;
        
        int j = 48 + ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).x / 32; //player position?? x
        int l2 = 464 - ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).y / 32; //y
        
        /*for (int x = 0; x < anIntArray1229.length; x++) {
        anIntArray1229[x] = 172;
        anIntArray1052[x] = -22;
        }
        
        
        for (int x = 0; x < anIntArray1229.length; x++) { //anIntArray1229 = 152
        anIntArray1229[x] = 152;
        anIntArray1052[x] = 10;
        }
        aClass30_Sub2_Sub1_Sub1_1263.method352(152, i, anIntArray1229, 256 + minimapInt3, anIntArray1052, -236, l2, 5, 25, 146, j); 
        
        //method352(int,int,int[] size x??,int,int[] size y??,int randomjunk,int player y,int fromtop ,int fromleft, int ,int player x)
        //aClass30_Sub2_Sub1_Sub1_1263.method352(140, i, anIntArray1229, 256 + minimapInt3, anIntArray1052, -236, l2, 9, 39, 120, j); //mapback, trees etc..
        
        
        
        
         */



        for (int j5 = 0; j5 < anInt1071; j5++) { //your dot??
            int k = (anIntArray1072[j5] * 4 + 2) - ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).x / 32;
            int i3 = (anIntArray1073[j5] * 4 + 2) - ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).y / 32;
            markMinimap(aClass30_Sub2_Sub1_Sub1Array1140[j5], k, i3, false);
        }

        for (int k5 = 0; k5 < 104; k5++) { //items
            for (int l5 = 0; l5 < 104; l5++) {
                NodeList class19 = groundArray[plane][k5][l5];
                if (class19 != null) {
                    int l = (k5 * 4 + 2) - ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).x / 32;
                    int j3 = (l5 * 4 + 2) - ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).y / 32;
                    markMinimap(mapDotItem, l, j3, false);
                }
            }
        }

        for (int i6 = 0; i6 < npcCount; i6++) { //NPC's
            NPC class30_sub2_sub4_sub1_sub1 = npcArray[anIntArray837[i6]];
            if (class30_sub2_sub4_sub1_sub1 != null && class30_sub2_sub4_sub1_sub1.isVisible()) {
                EntityDef class5 = class30_sub2_sub4_sub1_sub1.desc;
                if (class5.childrenIDs != null) {
                    class5 = class5.method161(anInt877);
                }
                if (class5 != null && class5.aBoolean87 && class5.aBoolean84) {
                    int i1 = ((Entity) (class30_sub2_sub4_sub1_sub1)).x / 32 - ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).x / 32;
                    int k3 = ((Entity) (class30_sub2_sub4_sub1_sub1)).y / 32 - ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).y / 32;
                    markMinimap(mapDotNPC, i1, k3, false);
                }
            }
        }
        for (int j6 = 0; j6 < anInt891; j6++) { //players
            Player class30_sub2_sub4_sub1_sub2 = playerArray[anIntArray892[j6]];
            if (class30_sub2_sub4_sub1_sub2 != null && class30_sub2_sub4_sub1_sub2.isVisible()) {
                int j1 = ((Entity) (class30_sub2_sub4_sub1_sub2)).x / 32 - ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).x / 32;
                int l3 = ((Entity) (class30_sub2_sub4_sub1_sub2)).y / 32 - ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).y / 32;
                boolean flag1 = false;
                long l6 = TextClass.method583(class30_sub2_sub4_sub1_sub2.name);
                for (int k6 = 0; k6 < friendsCount; k6++) {
                    if (l6 != friendsListAsLongs[k6] || friendNodeIDs[k6] == 0) {
                        continue;
                    }
                    flag1 = true;
                    break;
                }
                boolean flag2 = false;
                if (aClass30_Sub2_Sub4_Sub1_Sub2_1126.team != 0 && class30_sub2_sub4_sub1_sub2.team != 0 && aClass30_Sub2_Sub4_Sub1_Sub2_1126.team == class30_sub2_sub4_sub1_sub2.team) {
                    flag2 = true;
                }
                if (flag1) { //friend
                    markMinimap(mapDotFriend, j1, l3, false);
                } else if (flag2) { //team
                    markMinimap(mapDotTeam, j1, l3, false);
                } else { //other
                    markMinimap(mapDotPlayer, j1, l3, false);
                }
            }
        }

        if (anInt855 != 0 && loopCycle % 20 < 10) { //arrows?
            if (anInt855 == 1 && anInt1222 >= 0 && anInt1222 < npcArray.length) {
                NPC class30_sub2_sub4_sub1_sub1_1 = npcArray[anInt1222];
                if (class30_sub2_sub4_sub1_sub1_1 != null) {
                    int k1 = ((Entity) (class30_sub2_sub4_sub1_sub1_1)).x / 32 - ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).x / 32;
                    int i4 = ((Entity) (class30_sub2_sub4_sub1_sub1_1)).y / 32 - ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).y / 32;
                    drawMinimapArrow(mapMarker, i4, k1);
                }
            }
            if (anInt855 == 2) {
                int l1 = ((anInt934 - baseX) * 4 + 2) - ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).x / 32;
                int j4 = ((anInt935 - baseY) * 4 + 2) - ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).y / 32;
                drawMinimapArrow(mapMarker, j4, l1);
            }
            if (anInt855 == 10 && anInt933 >= 0 && anInt933 < playerArray.length) {
                Player class30_sub2_sub4_sub1_sub2_1 = playerArray[anInt933];
                if (class30_sub2_sub4_sub1_sub2_1 != null) {
                    int i2 = ((Entity) (class30_sub2_sub4_sub1_sub2_1)).x / 32 - ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).x / 32;
                    int k4 = ((Entity) (class30_sub2_sub4_sub1_sub2_1)).y / 32 - ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).y / 32;
                    drawMinimapArrow(mapMarker, k4, i2);
                }
            }
        }

        if (destX != 0) { //should we draw the flag???
            int j2 = (destX * 4 + 2) - ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).x / 32;
            int l4 = (destY * 4 + 2) - ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).y / 32;
            markMinimap(mapFlag, j2, l4, false);
        }

        CustomMapback.method348(0, 0); //fixes frame99 (may cause problems..)
        //compass -  this stops like those little icon from going over the compass
        compass.method352(33, anInt1185, anIntArray1057, 256, anIntArray968, 25, 8, 11, 33, 25);
        //Class30_Sub2_Sub1.method336(3, 78, 97, 0xffffff, 3, 0);
        DrawingArea.method336(3, 85, 117, 0xffffff, 3);
        //Class30_Sub2_Sub1.method336(3, 85, 110, 0xffffff, 3, 0);//YOUR SQUARE
        drawMinimapItems();

        if (menuOpen && menuScreenArea == 5) //draw minimap menus
        {
            drawMenu();
        }
        aClass15_1165.method237(0); //draw gamescreen
    }

    public final void method127(boolean flag, Entity class30_sub2_sub4_sub1, int i) {
        if (!flag) {
            pktType = aClass30_Sub2_Sub2_1083.readUnsignedByte();
        }
        method128(class30_sub2_sub4_sub1.x, i, anInt875, class30_sub2_sub4_sub1.y);
    }

    public final void method128(int i, int j, int k, int l) {
        if (i < 128 || l < 128 || i > 13056 || l > 13056) {
            spriteDrawX = -1;
            spriteDrawY = -1;
            return;
        }
        int i1 = method42(plane, l, true, i) - j;
        i -= xCameraPos;
        i1 -= zCameraPos;
        l -= yCameraPos;
        int j1 = Model.modelIntArray1[yCameraCurve];
        int k1 = Model.modelIntArray2[yCameraCurve];
        int l1 = Model.modelIntArray1[xCameraCurve];
        int i2 = Model.modelIntArray2[xCameraCurve];
        int j2 = l * l1 + i * i2 >> 16;
        l = l * i2 - i * l1 >> 16;
        i = j2;
        if (k >= 0) {
            aClass30_Sub2_Sub2_1192.writeWordBigEndian(27);
        }
        j2 = i1 * k1 - l * j1 >> 16;
        l = i1 * j1 + l * k1 >> 16;
        i1 = j2;
        if (l >= 50) {
            spriteDrawX = Texture.textureInt1 + (i << 9) / l;
            spriteDrawY = Texture.textureInt2 + (i1 << 9) / l;
            return;
        } else {
            spriteDrawX = -1;
            spriteDrawY = -1;
            return;
        }
    }

    public final void buildSplitPrivateChatMenu() {
        if (anInt1195 == 0) {
            return;
        }
        int i = 0;
        if (updateTime != 0) {
            i = 1;
        }
        for (int j = 0; j < 100; j++) {
            if (chatMessages[j] != null) {
                int k = chatTypes[j];
                String s = chatNames[j];
                boolean flag1 = false;
                if (s != null && s.startsWith("@cr1@")) {
                    s = s.substring(5);
                    boolean flag2 = true;
                }
                if (s != null && s.startsWith("@cr2@")) {
                    s = s.substring(5);
                    byte byte0 = 2;
                }
                if ((k == 3 || k == 7) && (k == 7 || privateChatMode == 0 || privateChatMode == 1 && method109(false, s))) {
                    int l = 329 - i * 13;
                    if (super.anInt20 > 4 && super.anInt21 - 4 > l - 10 && super.anInt21 - 4 <= l + 3) {
                        int i1 = regularFont.getTextWidth("From:  " + s + chatMessages[j]) + 25;
                        if (i1 > 450) {
                            i1 = 450;
                        }
                        if (super.anInt20 < 4 + i1) {

                            menuActionName[menuActionRow] = "Report Abuse @lre@" + s;
                            menuActionID[menuActionRow] = 606;
                            menuActionRow++;
                            menuActionName[menuActionRow] = "Add ignore @lre@" + s;
                            menuActionID[menuActionRow] = 2042;
                            menuActionRow++;
                            menuActionName[menuActionRow] = "Add friend @lre@" + s;
                            menuActionID[menuActionRow] = 2337;
                            menuActionRow++;
                        }
                    }
                    if (++i >= 5) {
                        return;
                    }
                }
                if ((k == 5 || k == 6) && privateChatMode < 2 && ++i >= 5) {
                    return;
                }
            }
        }

    }

    private final void method130(int i, int j, int k, int l, int i1, int j1, int k1,
            int l1, int i2, int j2) {
        Class30_Sub1 class30_sub1 = null;
        for (Class30_Sub1 class30_sub1_1 = (Class30_Sub1) aClass19_1179.reverseGetFirst(); class30_sub1_1 != null; class30_sub1_1 = (Class30_Sub1) aClass19_1179.reverseGetNext()) {
            if (class30_sub1_1.anInt1295 != l1 || class30_sub1_1.anInt1297 != i2 || class30_sub1_1.anInt1298 != j1 || class30_sub1_1.anInt1296 != i1) {
                continue;
            }
            class30_sub1 = class30_sub1_1;
            break;
        }

        if (class30_sub1 == null) {
            class30_sub1 = new Class30_Sub1();
            class30_sub1.anInt1295 = l1;
            class30_sub1.anInt1296 = i1;
            class30_sub1.anInt1297 = i2;
            class30_sub1.anInt1298 = j1;
            method89(false, class30_sub1);
            aClass19_1179.insertHead(class30_sub1);
        }
        class30_sub1.anInt1291 = k;
        class30_sub1.anInt1293 = k1;
        class30_sub1.anInt1292 = l;
        class30_sub1.anInt1302 = j2;
        class30_sub1.anInt1294 = j;
        if (i > 0);
    }

    public final boolean method131(RSInterface class9, boolean flag) {
        if (flag) {
            anInt883 = -211;
        }
        if (class9.anIntArray245 == null) {
            return false;
        }
        for (int i = 0; i < class9.anIntArray245.length; i++) {
            int j = extractInterfaceValues(class9, i);
            int k = class9.anIntArray212[i];
            if (class9.anIntArray245[i] == 2) {
                if (j >= k) {
                    return false;
                }
            } else if (class9.anIntArray245[i] == 3) {
                if (j <= k) {
                    return false;
                }
            } else if (class9.anIntArray245[i] == 4) {
                if (j == k) {
                    return false;
                }
            } else if (j != k) {
                return false;
            }
        }

        return true;
    }

    public final DataInputStream method132(String s)
            throws IOException {
        if (aSocket832 != null) {
            try {
                aSocket832.close();
            } catch (Exception _ex) {
            }
            aSocket832 = null;
        }
        aSocket832 = openSocket(43595);
        aSocket832.setSoTimeout(10000);
        java.io.InputStream inputstream = aSocket832.getInputStream();
        OutputStream outputstream = aSocket832.getOutputStream();
        outputstream.write(("JAGGRAB /" + s + "\n\n").getBytes());
        return new DataInputStream(inputstream);
    }

    private final void method134(byte byte0, int i, Stream class30_sub2_sub2) {
        int j = class30_sub2_sub2.readBits(8);
        if (j < anInt891) {
            for (int k = j; k < anInt891; k++) {
                anIntArray840[anInt839++] = anIntArray892[k];
            }

        }
        if (j > anInt891) {
            signlink.reporterror(myUsername + " Too many players");
            throw new RuntimeException("eek");
        }
        anInt891 = 0;
        for (int l = 0; l < j; l++) {
            int i1 = anIntArray892[l];
            Player class30_sub2_sub4_sub1_sub2 = playerArray[i1];
            int j1 = class30_sub2_sub2.readBits(1);
            if (j1 == 0) {
                anIntArray892[anInt891++] = i1;
                class30_sub2_sub4_sub1_sub2.anInt1537 = loopCycle;
            } else {
                int k1 = class30_sub2_sub2.readBits(2);
                if (k1 == 0) {
                    anIntArray892[anInt891++] = i1;
                    class30_sub2_sub4_sub1_sub2.anInt1537 = loopCycle;
                    anIntArray894[anInt893++] = i1;
                } else if (k1 == 1) {
                    anIntArray892[anInt891++] = i1;
                    class30_sub2_sub4_sub1_sub2.anInt1537 = loopCycle;
                    int l1 = class30_sub2_sub2.readBits(3);
                    class30_sub2_sub4_sub1_sub2.moveInDir(false, (byte) 20, l1);
                    int j2 = class30_sub2_sub2.readBits(1);
                    if (j2 == 1) {
                        anIntArray894[anInt893++] = i1;
                    }
                } else if (k1 == 2) {
                    anIntArray892[anInt891++] = i1;
                    class30_sub2_sub4_sub1_sub2.anInt1537 = loopCycle;
                    int i2 = class30_sub2_sub2.readBits(3);
                    class30_sub2_sub4_sub1_sub2.moveInDir(true, (byte) 20, i2);
                    int k2 = class30_sub2_sub2.readBits(3);
                    class30_sub2_sub4_sub1_sub2.moveInDir(true, (byte) 20, k2);
                    int l2 = class30_sub2_sub2.readBits(1);
                    if (l2 == 1) {
                        anIntArray894[anInt893++] = i1;
                    }
                } else if (k1 == 3) {
                    anIntArray840[anInt839++] = i1;
                }
            }
        }

        if (byte0 != 2) {
            anInt939 = -80;
        }
    }
    public boolean[] worldPingTimes;

    public boolean checkServerUp(String host, int port) throws IOException {
        Socket s = null;
        try {
            s = new Socket(host, port);
        } catch (ConnectException ignore) {
        }

        if (s == null) {
            return false;
        } else {
            return true;
        }
    }

    public void processWorlds() {
        worldPingTimes = new boolean[Csettings.serverList.length];      // allocates memory for 10 integers
        System.out.println("Checking server status, please wait...");
        for (int j = 0; j < Csettings.serverList.length; j++) {

            String sIP = Csettings.serverList[j][4];
            int sPort = Integer.parseInt(Csettings.serverList[j][5]);
            try {
                if (checkServerUp(sIP, sPort)) {
                    worldPingTimes[j] = true;
                } else {
                    worldPingTimes[j] = false;
                }
            } catch (IOException ioe) {
                worldPingTimes[j] = false;
            }
        }
    }

    public final void drawLoginScreen(boolean flag) {
        method64(0); //resetImageProducers();
        int middleWidth = 765 / 2;
        int middleHeight = 503 / 2;
        loginArea.method237(0); //loginArea.initDrawingArea


        if (!faded) { //we havent finished the fade animation
            int fade = 100;
            int loop = 0;
            while (fade > 0) {
                try {
                    background.method346(0, 0); //instead of 348, as we dont want transparency
                    DrawingArea.drawPixelsWithOpacity(0, 0, 765, 503, 0x000000, fade);
                    loginArea.drawGraphics(0, 23680, super.aGraphics12, 0); //finally print it all
                    Thread.sleep(5);
                } catch (Exception e) {
                }
                if (loop > 10) {
                    fade -= 5;
                }
                loop++;
            }
            faded = true;
        }


        if (showWorld) {
            //top bit
            worldSelect.method348(0, 0);
            boldFont.drawShadowedText(false, 19, 0x000000, "Select a Server", 16);
            smallFont.drawShadowedText(false, 152, 0xffffff, "Staff Favorite", 11);
            smallFont.drawShadowedText(false, 152, 0xffffff, "Other Server", 22);
            smallFont.drawShadowedText(false, 350, 0xffffff, "Current: " + server + ":" + port, 16);
            if (super.anInt20 >= 690 && super.anInt20 <= 762 && super.anInt21 >= 5 && super.anInt21 <= 20) {
                cancelButovr.method348(700, 4);
            } else {
                cancelBut.method348(700, 4);
            }
            smallFont.drawShadowedText(false, 713, 0xffffff, "Cancel", 17);
            // height, updown, leftright, colour, width, WTF!
            DrawingArea.method336(2, 29, 0, 0x2C2816, 765);
            DrawingArea.method336(20, 31, 0, 0x1D170B, 765);
            DrawingArea.method336(2, 51, 0, 0x2C2816, 765);
            boldFont.drawShadowedText(false, 30, 0xffffff, "World Name", 45);
            boldFont.drawShadowedText(false, 160, 0xffffff, "Players", 45);
            boldFont.drawShadowedText(false, 250, 0xffffff, "Website", 45);
            boldFont.drawShadowedText(false, 490, 0xffffff, "Location", 45);
            boldFont.drawShadowedText(false, 690, 0xffffff, "Status", 45);
            refreshBtn.method348(737, 28);
            //end top bit
            int down = 39;
            int col1 = 0x574423;
            int col2 = 0x45351B;
            int colHover = 0x776442;
            //this puts the servers in Csettings into the World format
            for (int j = 0; j < Csettings.serverList.length; j++) {
                int sFave = Integer.parseInt(Csettings.serverList[j][0]);
                String sName = Csettings.serverList[j][1];
                String sAddress = Csettings.serverList[j][2];
                String sLocation = Csettings.serverList[j][3];
                String sIP = Csettings.serverList[j][4];
                String sPort = Csettings.serverList[j][5];
                int sLocFlag = Integer.parseInt(Csettings.serverList[j][6]);
                worlds[j] = new World(sFave, sName, sAddress, sLocation, sIP, sPort, sLocFlag);
            }
            //this prints each world
            for (int i = 0; i < worlds.length; i++) {
                World thisWorld = worlds[i];
                String sName = thisWorld.name;
                String sIP = thisWorld.ip;
                String sAddress = thisWorld.url;
                String slocation = thisWorld.location;
                int sFave = thisWorld.type;
                String sPort = thisWorld.port;
                int LocFlag = thisWorld.LocFlag;
                down = down + 20;
                int colour = col1;
                if (i % 2 == 0) {
                    colour = col1;
                } else {
                    colour = col2;
                }
                DrawingArea.method336(20, down, 0, colour, 765); //draw bg

                if (super.anInt21 >= down && super.anInt21 < down + 20) { //hover line
                    DrawingArea.method336(20, down, 0, colHover, 765);
                }

                //star
                if (sFave == 1) {
                    gStar.method348(5, down + 1);
                } else {
                    sStar.method348(5, down + 1);
                }

                //text
                boldFont.drawShadowedText(false, 30, 0xffffff, sName, down + 15);
                boldFont.drawShadowedText(false, 160, 0xffffff, "Unknown", down + 15);
                boldFont.drawShadowedText(false, 250, 0xffffff, sAddress, down + 15);

                WorldFlags[LocFlag].method348(490, down + 4);
                //boldFont.drawShadowedText(false, false, 515, 0xffffff, sIP+":"+sPort, down+15);
                boldFont.drawShadowedText(false, 515, 0xffffff, slocation, down + 15);


                if (worldPingTimes[i]) {
                    boldFont.drawShadowedText(false, 690, 0xffffff, "Online", down + 15);
                } else {
                    boldFont.drawShadowedText(false, 690, 0xffff89, "Offline", down + 15);
                }





                if (super.anInt26 == 1 && super.anInt21 >= down && super.anInt21 < down + 20 && super.anInt20 <= 245) {
                    showWorld = false;
                    server = sIP;
                    port = sPort;
                }
                if (super.anInt21 >= down && super.anInt21 < down + 20 && super.anInt20 > 245 && super.anInt20 < 460) {
                    boldFont.drawShadowedText(false, 250, 0xffff99, sAddress, down + 15);
                    visitURL.method348(470, down + 5);
                    if (super.anInt26 == 1) { //click
                        launchURL(sAddress);
                    }
                }
                if (super.anInt26 == 1 && super.anInt21 >= down && super.anInt21 < down + 20 && super.anInt20 >= 460) {
                    showWorld = false;
                    server = sIP;
                    port = sPort;
                }


            }




        } else {
            //draw the main login sprite
            background.method346(0, 0);

            //draw the servers button
            if (super.anInt20 >= 5 && super.anInt20 <= 90 && super.anInt21 >= 475 && super.anInt21 <= 498) {
                WorldBtnovr.method348(5, 475);
            } else {
                WorldBtn.method348(5, 475);
            }
            boldFont.drawShadowedText(false, 21, 0x000000, "Servers", 491);

            /* 
            
            loginBase - bg
            loginTextBox - text
            
            loginExit - exit
            loginExitH - exit hover
            
            loginGo - login button
            loginGoH - login button hovered
            
            loginSaveOn  save on
            loginSaveOff - save off
            
            
            
             */


            //bg
            loginBase.method348(480, 5);

            //exit button
            if (super.anInt20 >= 740 && super.anInt20 <= 756 && super.anInt21 >= 11 && super.anInt21 <= 27) {
                //login button hover
                loginExitH.method348(740, 11);
            } else { //not hovering
                loginExit.method348(740, 11);
            }

            //text boxes
            fancyFont.drawCenteredText(0xb6a689, 542, "Username", 51, false);
            loginTextBox.method348(600, 35);
            smallFont.drawShadowedText(false, 620, 0xffffff, "" + myUsername + ((anInt1216 == 0) & (loopCycle % 40 < 20) ? "@bla@|" : ""), 52);

            fancyFont.drawCenteredText(0xb6a689, 543, "Password", 86, false);
            loginTextBox.method348(600, 67);
            smallFont.drawShadowedText(false, 620, 0xffffff, "" + TextClass.passwordAsterisks(myPassword) + ((anInt1216 == 1) & (loopCycle % 40 < 20) ? "@bla@|" : ""), 85);


            //user saving
            regularFont.drawText(0x000000, "Save Credentials?", 114, 660);

            if (saveUser) {
                //save user is selected
                loginSaveOn.method348(725, 102);
            } else {
                //save user is selected
                loginSaveOff.method348(725, 102);
            }

            int top = 137;
            int left = 540 + 86;
            if (loginMessage2.length() > 0) { //the second error string
                fancyFont.drawCenteredText(0xb6a689, left, loginMessage1, top, false);
                fancyFont.drawCenteredText(0xb6a689, left, loginMessage2, top + 15, false);
            } else {
                fancyFont.drawCenteredText(0xb6a689, left, loginMessage1, top + 7, false);
            }


            //login button
            if (flag) { //logging in
                loginGo.method348(504, 165);
                fancyFont.drawCenteredText(0x000000, 628, "Please Wait...", 190, false);
            } else {
                if (super.anInt20 >= 504 && super.anInt20 <= 743 && super.anInt21 >= 165 && super.anInt21 <= 204) { //hovering
                    loginGoH.method348(504, 165);
                } else { //not hovering
                    loginGo.method348(504, 165);
                }
                fancyFont.drawCenteredText(0x000000, 628, "Click here to Login.", 190, false);
            }

            //current server
            smallFont.drawShadowedText(false, 565, 0x333333, "Server: " + server + ":" + port, 217);

        }
        loginArea.drawGraphics(0, 23680, super.aGraphics12, 0); //finally print it all
    }

    public final void processLoginScreenInput() {

        /* 
        
        username
        
        542 - (600+148) 748
        
        35- (35+26) 61
        
        
        
        
        password
        
        543 - (600+148) 748
        
        67 - (67+26) 93
        
        
        
        save
        
        
        
        660 - (725+16) 731
        
        102 - (102+13) 115
        
        
        xit
        
        740 756
        
        11 27 */




        if (showWorld) {
            if (super.anInt26 == 1 && super.saveClickX >= 690 && super.saveClickX <= 762 && super.saveClickY >= 5 && super.saveClickY <= 20) { //cancel
                showWorld = false;
            }
        } else {

            if (super.anInt26 == 1 && super.saveClickX >= 5 && super.saveClickX <= 90 && super.saveClickY >= 475 && super.saveClickY <= 498) { //server select
                showWorld = true;
                processWorlds();
            }

            if (super.anInt26 == 1 && super.saveClickX >= 740 && super.saveClickX <= 756 && super.saveClickY >= 11 && super.saveClickY <= 27) { //exit
                Csettings.exitWarningDialog();
            }



            if (super.anInt26 == 1 && super.saveClickX >= 542 && super.saveClickX <= 748 && super.saveClickY >= 35 && super.saveClickY <= 61) { //username
                anInt1216 = 0;
            }

            if (super.anInt26 == 1 && super.saveClickX >= 543 && super.saveClickX <= 748 && super.saveClickY >= 67 && super.saveClickY <= 93) { //password
                anInt1216 = 1;
            }

            if (super.anInt26 == 1 && super.saveClickX >= 660 && super.saveClickX <= 731 && super.saveClickY >= 102 && super.saveClickY <= 115) { //save
                if (saveUser) {
                    saveUser = false;
                } else {
                    saveUser = true;
                    saveinfo();
                }
            }



            if (super.anInt26 == 1 && super.saveClickX >= 504 && super.saveClickX <= 743 && super.saveClickY >= 165 && super.saveClickY <= 204) { //login button
                if (myUsername.length() >= 1 && myUsername.length() <= 12) {
                    if (myPassword.length() >= 1 && myPassword.length() <= 20) {
                        loginFailures = 0;
                        login(myUsername, myPassword, false);
                        if (loggedIn) {
                            return;
                        }
                    } else {
                        loginMessage1 = "You must enter a password.";
                        loginMessage2 = "";
                    }
                } else {
                    loginMessage1 = "You must enter a username.";
                    loginMessage2 = "";
                }
            }





        }
        do { //whenever a character is pressed
            int charT = readChar();
            if (charT == -1) {
                break;
            }
            boolean failT = false;
            for (int i2 = 0; i2 < goodLoginChars.length(); i2++) {
                if (charT != goodLoginChars.charAt(i2)) {
                    continue;
                }
                failT = true;
                break;
            }
            if (anInt1216 == 0) { //username
                if (charT == 8 && myUsername.length() > 0) { //backspace
                    myUsername = myUsername.substring(0, myUsername.length() - 1);
                }
                if (charT == 9 || charT == 10 || charT == 13) { //enter or tab
                    anInt1216 = 1;
                }
                if (failT) {
                    myUsername += (char) charT;
                }
                if (myUsername.length() > 12) { //limit 12 chars
                    myUsername = myUsername.substring(0, 12);
                }
            } else if (anInt1216 == 1) { //password
                if (charT == 8 && myPassword.length() > 0) {
                    myPassword = myPassword.substring(0, myPassword.length() - 1);
                }
                if (charT == 10 || charT == 13) { //enter
                    loginFailures = 0;
                    login(myUsername, myPassword, false);
                }
                if (charT == 9) { //tab
                    anInt1216 = 0;
                }
                if (failT) {
                    myPassword += (char) charT;
                }
                if (myPassword.length() > 20) { //limit 20 chars
                    myPassword = myPassword.substring(0, 20);
                }
            }
        } while (true);
        return;
    }

    public final void raiseWelcomeScreen() { //raiseWelcomeScreen
        welcomeScreenRaised = true;
        return;
    }

    public final void method137(int i, Stream class30_sub2_sub2, int j) { //method137
        while (i >= 0) {
            j = -1;
        }
        if (j == 84) {
            int k = class30_sub2_sub2.readUnsignedByte();
            int j3 = anInt1268 + (k >> 4 & 7);
            int i6 = anInt1269 + (k & 7);
            int l8 = class30_sub2_sub2.readUnsignedWord();
            int k11 = class30_sub2_sub2.readUnsignedWord();
            int l13 = class30_sub2_sub2.readUnsignedWord();
            if (j3 >= 0 && i6 >= 0 && j3 < 104 && i6 < 104) {
                NodeList class19_1 = groundArray[plane][j3][i6];
                if (class19_1 != null) {
                    for (Item class30_sub2_sub4_sub2_3 = (Item) class19_1.reverseGetFirst(); class30_sub2_sub4_sub2_3 != null; class30_sub2_sub4_sub2_3 = (Item) class19_1.reverseGetNext()) {
                        if (class30_sub2_sub4_sub2_3.ID != (l8 & 0x7fff) || class30_sub2_sub4_sub2_3.anInt1559 != k11) {
                            continue;
                        }
                        class30_sub2_sub4_sub2_3.anInt1559 = l13;
                        break;
                    }

                    method25(j3, i6);
                }
            }
            return;
        }
        if (j == 105) {
            int l = class30_sub2_sub2.readUnsignedByte();
            int k3 = anInt1268 + (l >> 4 & 7);
            int j6 = anInt1269 + (l & 7);
            int i9 = class30_sub2_sub2.readUnsignedWord();
            int l11 = class30_sub2_sub2.readUnsignedByte();
            int i14 = l11 >> 4 & 0xf;
            int i16 = l11 & 7;
            if (((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0] >= k3 - i14 && ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0] <= k3 + i14 && ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0] >= j6 - i14 && ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0] <= j6 + i14 && aBoolean848 && !lowMem && anInt1062 < 50) {
                anIntArray1207[anInt1062] = i9;
                anIntArray1241[anInt1062] = i16;
                anIntArray1250[anInt1062] = Sounds.anIntArray326[i9];
                anInt1062++;
            }
        }
        if (j == 215) {
            int i1 = class30_sub2_sub2.method435();
            int l3 = class30_sub2_sub2.method428();
            int k6 = anInt1268 + (l3 >> 4 & 7);
            int j9 = anInt1269 + (l3 & 7);
            int i12 = class30_sub2_sub2.method435();
            int j14 = class30_sub2_sub2.readUnsignedWord();
            if (k6 >= 0 && j9 >= 0 && k6 < 104 && j9 < 104 && i12 != anInt884) {
                Item class30_sub2_sub4_sub2_2 = new Item();
                class30_sub2_sub4_sub2_2.ID = i1;
                class30_sub2_sub4_sub2_2.anInt1559 = j14;
                if (groundArray[plane][k6][j9] == null) {
                    groundArray[plane][k6][j9] = new NodeList();
                }
                groundArray[plane][k6][j9].insertHead(class30_sub2_sub4_sub2_2);
                method25(k6, j9);
            }
            return;
        }
        if (j == 156) {
            int j1 = class30_sub2_sub2.method426();
            int i4 = anInt1268 + (j1 >> 4 & 7);
            int l6 = anInt1269 + (j1 & 7);
            int k9 = class30_sub2_sub2.readUnsignedWord();
            if (i4 >= 0 && l6 >= 0 && i4 < 104 && l6 < 104) {
                NodeList class19 = groundArray[plane][i4][l6];
                if (class19 != null) {
                    for (Item class30_sub2_sub4_sub2 = (Item) class19.reverseGetFirst(); class30_sub2_sub4_sub2 != null; class30_sub2_sub4_sub2 = (Item) class19.reverseGetNext()) {
                        if (class30_sub2_sub4_sub2.ID != (k9 & 0x7fff)) {
                            continue;
                        }
                        class30_sub2_sub4_sub2.unlink();
                        break;
                    }

                    if (class19.reverseGetFirst() == null) {
                        groundArray[plane][i4][l6] = null;
                    }
                    method25(i4, l6);
                }
            }
            return;
        }
        if (j == 160) {
            int k1 = class30_sub2_sub2.method428();
            int j4 = anInt1268 + (k1 >> 4 & 7);
            int i7 = anInt1269 + (k1 & 7);
            int l9 = class30_sub2_sub2.method428();
            int j12 = l9 >> 2;
            int k14 = l9 & 3;
            int j16 = anIntArray1177[j12];
            int j17 = class30_sub2_sub2.method435();
            if (j4 >= 0 && i7 >= 0 && j4 < 103 && i7 < 103) {
                int j18 = intGroundArray[plane][j4][i7];
                int i19 = intGroundArray[plane][j4 + 1][i7];
                int l19 = intGroundArray[plane][j4 + 1][i7 + 1];
                int k20 = intGroundArray[plane][j4][i7 + 1];
                if (j16 == 0) {
                    Object1 class10 = aClass25_946.method296(plane, j4, i7, false);
                    if (class10 != null) {
                        int k21 = class10.uid >> 14 & 0x7fff;
                        if (j12 == 2) {
                            class10.aClass30_Sub2_Sub4_278 = new Animable_Sub5(k21, 4 + k14, 2, i19, (byte) 7, l19, j18, k20, j17, false);
                            class10.aClass30_Sub2_Sub4_279 = new Animable_Sub5(k21, k14 + 1 & 3, 2, i19, (byte) 7, l19, j18, k20, j17, false);
                        } else {
                            class10.aClass30_Sub2_Sub4_278 = new Animable_Sub5(k21, k14, j12, i19, (byte) 7, l19, j18, k20, j17, false);
                        }
                    }
                }
                if (j16 == 1) {
                    Object2 class26 = aClass25_946.method297(j4, 866, i7, plane);
                    if (class26 != null) {
                        class26.aClass30_Sub2_Sub4_504 = new Animable_Sub5(class26.uid >> 14 & 0x7fff, 0, 4, i19, (byte) 7, l19, j18, k20, j17, false);
                    }
                }
                if (j16 == 2) {
                    Object5 class28 = aClass25_946.method298(j4, i7, (byte) 4, plane);
                    if (j12 == 11) {
                        j12 = 10;
                    }
                    if (class28 != null) {
                        class28.aClass30_Sub2_Sub4_521 = new Animable_Sub5(class28.uid >> 14 & 0x7fff, k14, j12, i19, (byte) 7, l19, j18, k20, j17, false);
                    }
                }
                if (j16 == 3) {
                    Object3 class49 = aClass25_946.method299(i7, j4, plane, 0);
                    if (class49 != null) {
                        class49.animable = new Animable_Sub5(class49.uid >> 14 & 0x7fff, k14, 22, i19, (byte) 7, l19, j18, k20, j17, false);
                    }
                }
            }
            return;
        }
        if (j == 147) {
            int l1 = class30_sub2_sub2.method428();
            int k4 = anInt1268 + (l1 >> 4 & 7);
            int j7 = anInt1269 + (l1 & 7);
            int i10 = class30_sub2_sub2.readUnsignedWord();
            byte byte0 = class30_sub2_sub2.method430();
            int l14 = class30_sub2_sub2.method434((byte) 108);
            byte byte1 = class30_sub2_sub2.method429();
            int k17 = class30_sub2_sub2.readUnsignedWord();
            int k18 = class30_sub2_sub2.method428();
            int j19 = k18 >> 2;
            int i20 = k18 & 3;
            int l20 = anIntArray1177[j19];
            byte byte2 = class30_sub2_sub2.readSignedByte();
            int l21 = class30_sub2_sub2.readUnsignedWord();
            byte byte3 = class30_sub2_sub2.method429();
            Player class30_sub2_sub4_sub1_sub2;
            if (i10 == anInt884) {
                class30_sub2_sub4_sub1_sub2 = aClass30_Sub2_Sub4_Sub1_Sub2_1126;
            } else {
                class30_sub2_sub4_sub1_sub2 = playerArray[i10];
            }
            if (class30_sub2_sub4_sub1_sub2 != null) {
                ObjectDef class46 = ObjectDef.forID(l21);
                int i22 = intGroundArray[plane][k4][j7];
                int j22 = intGroundArray[plane][k4 + 1][j7];
                int k22 = intGroundArray[plane][k4 + 1][j7 + 1];
                int l22 = intGroundArray[plane][k4][j7 + 1];
                Model class30_sub2_sub4_sub6 = class46.method578(j19, i20, i22, j22, k22, l22, -1);
                if (class30_sub2_sub4_sub6 != null) {
                    method130(404, k17 + 1, -1, 0, l20, j7, 0, plane, k4, l14 + 1);
                    class30_sub2_sub4_sub1_sub2.anInt1707 = l14 + loopCycle;
                    class30_sub2_sub4_sub1_sub2.anInt1708 = k17 + loopCycle;
                    class30_sub2_sub4_sub1_sub2.aModel_1714 = class30_sub2_sub4_sub6;
                    int i23 = class46.objectWidth;
                    int j23 = class46.objectHeight;
                    if (i20 == 1 || i20 == 3) {
                        i23 = class46.objectHeight;
                        j23 = class46.objectWidth;
                    }
                    class30_sub2_sub4_sub1_sub2.anInt1711 = k4 * 128 + i23 * 64;
                    class30_sub2_sub4_sub1_sub2.anInt1713 = j7 * 128 + j23 * 64;
                    class30_sub2_sub4_sub1_sub2.anInt1712 = method42(plane, class30_sub2_sub4_sub1_sub2.anInt1713, true, class30_sub2_sub4_sub1_sub2.anInt1711);
                    if (byte2 > byte0) {
                        byte byte4 = byte2;
                        byte2 = byte0;
                        byte0 = byte4;
                    }
                    if (byte3 > byte1) {
                        byte byte5 = byte3;
                        byte3 = byte1;
                        byte1 = byte5;
                    }
                    class30_sub2_sub4_sub1_sub2.anInt1719 = k4 + byte2;
                    class30_sub2_sub4_sub1_sub2.anInt1721 = k4 + byte0;
                    class30_sub2_sub4_sub1_sub2.anInt1720 = j7 + byte3;
                    class30_sub2_sub4_sub1_sub2.anInt1722 = j7 + byte1;
                }
            }
        }
        if (j == 151) {
            int i2 = class30_sub2_sub2.method426();
            int l4 = anInt1268 + (i2 >> 4 & 7);
            int k7 = anInt1269 + (i2 & 7);
            int j10 = class30_sub2_sub2.method434((byte) 108);
            int k12 = class30_sub2_sub2.method428();
            int i15 = k12 >> 2;
            int k16 = k12 & 3;
            int l17 = anIntArray1177[i15];
            if (l4 >= 0 && k7 >= 0 && l4 < 104 && k7 < 104) {
                method130(404, -1, j10, k16, l17, k7, i15, plane, l4, 0);
            }
            return;
        }
        if (j == 4) {
            int j2 = class30_sub2_sub2.readUnsignedByte();
            int i5 = anInt1268 + (j2 >> 4 & 7);
            int l7 = anInt1269 + (j2 & 7);
            int k10 = class30_sub2_sub2.readUnsignedWord();
            int l12 = class30_sub2_sub2.readUnsignedByte();
            int j15 = class30_sub2_sub2.readUnsignedWord();
            if (i5 >= 0 && l7 >= 0 && i5 < 104 && l7 < 104) {
                i5 = i5 * 128 + 64;
                l7 = l7 * 128 + 64;
                Animable_Sub3 class30_sub2_sub4_sub3 = new Animable_Sub3(plane, loopCycle, 6, j15, k10, method42(plane, l7, true, i5) - l12, l7, i5);
                aClass19_1056.insertHead(class30_sub2_sub4_sub3);
            }
            return;
        }
        if (j == 44) {
            int k2 = class30_sub2_sub2.method436();
            int j5 = class30_sub2_sub2.readUnsignedWord();
            int i8 = class30_sub2_sub2.readUnsignedByte();
            int l10 = anInt1268 + (i8 >> 4 & 7);
            int i13 = anInt1269 + (i8 & 7);
            if (l10 >= 0 && i13 >= 0 && l10 < 104 && i13 < 104) {
                Item class30_sub2_sub4_sub2_1 = new Item();
                class30_sub2_sub4_sub2_1.ID = k2;
                class30_sub2_sub4_sub2_1.anInt1559 = j5;
                if (groundArray[plane][l10][i13] == null) {
                    groundArray[plane][l10][i13] = new NodeList();
                }
                groundArray[plane][l10][i13].insertHead(class30_sub2_sub4_sub2_1);
                method25(l10, i13);
            }
            return;
        }
        if (j == 101) {
            int l2 = class30_sub2_sub2.method427();
            int k5 = l2 >> 2;
            int j8 = l2 & 3;
            int i11 = anIntArray1177[k5];
            int j13 = class30_sub2_sub2.readUnsignedByte();
            int k15 = anInt1268 + (j13 >> 4 & 7);
            int l16 = anInt1269 + (j13 & 7);
            if (k15 >= 0 && l16 >= 0 && k15 < 104 && l16 < 104) {
                method130(404, -1, -1, j8, i11, l16, k5, plane, k15, 0);
            }
            return;
        }
        if (j == 117) {
            int i3 = class30_sub2_sub2.readUnsignedByte();
            int l5 = anInt1268 + (i3 >> 4 & 7);
            int k8 = anInt1269 + (i3 & 7);
            int j11 = l5 + class30_sub2_sub2.readSignedByte();
            int k13 = k8 + class30_sub2_sub2.readSignedByte();
            int l15 = class30_sub2_sub2.readSignedWord();
            int i17 = class30_sub2_sub2.readUnsignedWord();
            int i18 = class30_sub2_sub2.readUnsignedByte() * 4;
            int l18 = class30_sub2_sub2.readUnsignedByte() * 4;
            int k19 = class30_sub2_sub2.readUnsignedWord();
            int j20 = class30_sub2_sub2.readUnsignedWord();
            int i21 = class30_sub2_sub2.readUnsignedByte();
            int j21 = class30_sub2_sub2.readUnsignedByte();
            if (l5 >= 0 && k8 >= 0 && l5 < 104 && k8 < 104 && j11 >= 0 && k13 >= 0 && j11 < 104 && k13 < 104 && i17 != 65535) {
                l5 = l5 * 128 + 64;
                k8 = k8 * 128 + 64;
                j11 = j11 * 128 + 64;
                k13 = k13 * 128 + 64;
                Animable_Sub4 class30_sub2_sub4_sub4 = new Animable_Sub4(i21, l18, 46883, k19 + loopCycle, j20 + loopCycle, j21, plane, method42(plane, k8, true, l5) - i18, k8, l5, l15, i17);
                class30_sub2_sub4_sub4.method455(k19 + loopCycle, k13, method42(plane, k13, true, j11) - l18, j11, (byte) -83);
                aClass19_1013.insertHead(class30_sub2_sub4_sub4);
            }
        }
    }

    public static final void setLowMem(byte byte0) { //setLowMem
        WorldController.lowMem = true;
        if (byte0 != aByte823) {
            for (int i = 1; i > 0; i++);
        }
        Texture.lowMem = true;
        lowMem = true;
        ObjectManager.lowMem = true;
        ObjectDef.lowMem = true;
    }

    private final void method139(Stream class30_sub2_sub2) {
        class30_sub2_sub2.initBitAccess();
        int k = class30_sub2_sub2.readBits(8);
        if (k < npcCount) {
            for (int l = k; l < npcCount; l++) {
                anIntArray840[anInt839++] = anIntArray837[l];
            }

        }
        if (k > npcCount) {
            signlink.reporterror(myUsername + " Too many npcs");
            throw new RuntimeException("eek");
        }
        npcCount = 0;
        for (int i1 = 0; i1 < k; i1++) {
            int j1 = anIntArray837[i1];
            NPC class30_sub2_sub4_sub1_sub1 = npcArray[j1];
            int k1 = class30_sub2_sub2.readBits(1);
            if (k1 == 0) {
                anIntArray837[npcCount++] = j1;
                class30_sub2_sub4_sub1_sub1.anInt1537 = loopCycle;
            } else {
                int l1 = class30_sub2_sub2.readBits(2);
                if (l1 == 0) {
                    anIntArray837[npcCount++] = j1;
                    class30_sub2_sub4_sub1_sub1.anInt1537 = loopCycle;
                    anIntArray894[anInt893++] = j1;
                } else if (l1 == 1) {
                    anIntArray837[npcCount++] = j1;
                    class30_sub2_sub4_sub1_sub1.anInt1537 = loopCycle;
                    int i2 = class30_sub2_sub2.readBits(3);
                    class30_sub2_sub4_sub1_sub1.moveInDir(false, (byte) 20, i2);
                    int k2 = class30_sub2_sub2.readBits(1);
                    if (k2 == 1) {
                        anIntArray894[anInt893++] = j1;
                    }
                } else if (l1 == 2) {
                    anIntArray837[npcCount++] = j1;
                    class30_sub2_sub4_sub1_sub1.anInt1537 = loopCycle;
                    int j2 = class30_sub2_sub2.readBits(3);
                    class30_sub2_sub4_sub1_sub1.moveInDir(true, (byte) 20, j2);
                    int l2 = class30_sub2_sub2.readBits(3);
                    class30_sub2_sub4_sub1_sub1.moveInDir(true, (byte) 20, l2);
                    int i3 = class30_sub2_sub2.readBits(1);
                    if (i3 == 1) {
                        anIntArray894[anInt893++] = j1;
                    }
                } else if (l1 == 3) {
                    anIntArray840[anInt839++] = j1;
                }
            }
        }
    }

    public final void markMinimap(Sprite class30_sub2_sub1_sub1, int i, int j, boolean flag) {
        int k = anInt1185 + minimapInt2 & 0x7ff;
        int l = i * i + j * j;
        if (l > 6400) {
            return;
        }
        int i1 = Model.modelIntArray1[k];
        int j1 = Model.modelIntArray2[k];
        i1 = (i1 * 256) / (minimapInt3 + 256);
        j1 = (j1 * 256) / (minimapInt3 + 256);
        int k1 = j * i1 + i * j1 >> 16;
        int l1 = j * j1 - i * i1 >> 16;
        class30_sub2_sub1_sub1.method348(((113 + k1) - class30_sub2_sub1_sub1.maxWidth / 2) + 4, 87 - l1 - class30_sub2_sub1_sub1.maxHeight / 2 - 4);
        CustomMapback.method348(0, 0);
    }

    private final void method142(int i, int j, int k, int l, int i1, int j1, int k1, int l1) {
        if (l1 < 4 || l1 > 4) {
            pktType = aClass30_Sub2_Sub2_1083.readUnsignedByte();
        }
        if (i1 >= 1 && i >= 1 && i1 <= 102 && i <= 102) {
            if (lowMem && j != plane) {
                return;
            }
            int i2 = 0;
            byte byte0 = -1;
            boolean flag = false;
            boolean flag1 = false;
            if (j1 == 0) {
                i2 = aClass25_946.method300(j, i1, i);
            }
            if (j1 == 1) {
                i2 = aClass25_946.method301(j, i1, 0, i);
            }
            if (j1 == 2) {
                i2 = aClass25_946.method302(j, i1, i);
            }
            if (j1 == 3) {
                i2 = aClass25_946.method303(j, i1, i);
            }
            if (i2 != 0) {
                int i3 = aClass25_946.method304(j, i1, i, i2);
                int j2 = i2 >> 14 & 0x7fff;
                int k2 = i3 & 0x1f;
                int l2 = i3 >> 6;
                if (j1 == 0) {
                    aClass25_946.method291(i1, j, i, (byte) -119);
                    ObjectDef class46 = ObjectDef.forID(j2);
                    if (class46.aBoolean767) {
                        aClass11Array1230[j].method215(l2, k2, class46.aBoolean757, true, i1, i);
                    }
                }
                if (j1 == 1) {
                    aClass25_946.method292(0, i, j, i1);
                }
                if (j1 == 2) {
                    aClass25_946.method293(j, -978, i1, i);
                    ObjectDef class46_1 = ObjectDef.forID(j2);
                    if (i1 + class46_1.objectWidth > 103 || i + class46_1.objectWidth > 103 || i1 + class46_1.objectHeight > 103 || i + class46_1.objectHeight > 103) {
                        return;
                    }
                    if (class46_1.aBoolean767) {
                        aClass11Array1230[j].method216(l2, class46_1.objectWidth, i1, i, (byte) 6, class46_1.objectHeight, class46_1.aBoolean757);
                    }
                }
                if (j1 == 3) {
                    aClass25_946.method294((byte) 9, j, i, i1);
                    ObjectDef class46_2 = ObjectDef.forID(j2);
                    if (class46_2.aBoolean767 && class46_2.hasActions) {
                        aClass11Array1230[j].method218(360, i, i1);
                    }
                }
            }
            if (k1 >= 0) {
                int j3 = j;
                if (j3 < 3 && (byteGroundArray[1][i1][i] & 2) == 2) {
                    j3++;
                }
                ObjectManager.method188(aClass25_946, k, i, l, j3, aClass11Array1230[j], intGroundArray, i1, k1, j);
            }
        }
    }

    private final void method143(int i, Stream class30_sub2_sub2, int j) {
        anInt839 = 0;
        if (j != 9759) {
            pktType = class30_sub2_sub2.readUnsignedByte();
        }
        anInt893 = 0;
        method117(class30_sub2_sub2, i, (byte) 5);
        method134((byte) 2, i, class30_sub2_sub2);
        method91(class30_sub2_sub2, i, (byte) 8);
        method49(i, (byte) 2, class30_sub2_sub2);
        for (int k = 0; k < anInt839; k++) {
            int l = anIntArray840[k];
            if (((Entity) (playerArray[l])).anInt1537 != loopCycle) {
                playerArray[l] = null;
            }
        }

        if (class30_sub2_sub2.currentOffset != i) {
            signlink.reporterror("Error packet size mismatch in getplayer pos:" + class30_sub2_sub2.currentOffset + " psize:" + i);
            throw new RuntimeException("eek");
        }
        for (int i1 = 0; i1 < anInt891; i1++) {
            if (playerArray[anIntArray892[i1]] == null) {
                signlink.reporterror(myUsername + " null entry in pl list - pos:" + i1 + " size:" + anInt891);
                throw new RuntimeException("eek");
            }
        }

    }

    public final void setCameraPos(int j, int k, int l, int i1, int j1, int k1) {
        int l1 = 2048 - k & 0x7ff;
        int i2 = 2048 - j1 & 0x7ff;
        int j2 = 0;
        int k2 = 0;
        int l2 = j;
        if (l1 != 0) {
            int i3 = Model.modelIntArray1[l1];
            int k3 = Model.modelIntArray2[l1];
            int i4 = k2 * k3 - l2 * i3 >> 16;
            l2 = k2 * i3 + l2 * k3 >> 16;
            k2 = i4;
        }
        if (i2 != 0) {

            int j3 = Model.modelIntArray1[i2];
            int l3 = Model.modelIntArray2[i2];
            int j4 = l2 * j3 + j2 * l3 >> 16;
            l2 = l2 * l3 - j2 * j3 >> 16;
            j2 = j4;
        }
        xCameraPos = l - j2;
        zCameraPos = i1 - k2;
        yCameraPos = k1 - l2;
        yCameraCurve = k;
        xCameraCurve = j1;
    }

    public final boolean parsePacket() {
        if (aClass24_1168 == null) {
            return false;
        }

        try {
            int i = aClass24_1168.available();
            if (i == 0) {
                return false;
            }
            if (pktType == -1) {
                aClass24_1168.flushInputStream(aClass30_Sub2_Sub2_1083.buffer, 0, 1);
                pktType = aClass30_Sub2_Sub2_1083.buffer[0] & 0xff;
                if (aClass17_1000 != null) {
                    pktType = pktType - aClass17_1000.getNextKey() & 0xff;
                }
                pktSize = SizeConstants.packetSizes[pktType];
                i--;
            }
            if (pktSize == -1) {
                if (i > 0) {
                    aClass24_1168.flushInputStream(aClass30_Sub2_Sub2_1083.buffer, 0, 1);
                    pktSize = aClass30_Sub2_Sub2_1083.buffer[0] & 0xff;
                    i--;
                } else {
                    return false;
                }
            }
            if (pktSize == -2) {
                if (i > 1) {
                    aClass24_1168.flushInputStream(aClass30_Sub2_Sub2_1083.buffer, 0, 2);
                    aClass30_Sub2_Sub2_1083.currentOffset = 0;
                    pktSize = aClass30_Sub2_Sub2_1083.readUnsignedWord();
                    i -= 2;
                } else {
                    return false;
                }
            }
            if (i < pktSize) {
                return false;
            }
            aClass30_Sub2_Sub2_1083.currentOffset = 0;
            aClass24_1168.flushInputStream(aClass30_Sub2_Sub2_1083.buffer, 0, pktSize);
            anInt1009 = 0;
            anInt843 = anInt842;
            anInt842 = anInt841;
            anInt841 = pktType;

            if (pktType == 81) { // updates players apparently XD
                method143(pktSize, aClass30_Sub2_Sub2_1083, 9759);
                aBoolean1080 = false;
                pktType = -1;
                return true;
            }

            if (pktType != -1) {
                if (showframeids == 1) {
                    System.out.println("Frameid:" + pktType);
                }
            }

            if (pktType == 176) { //Used for the welcome screen, useless without fullscreen interfaces.
                daysSinceRecovChange = aClass30_Sub2_Sub2_1083.method427();
                unreadMessages = aClass30_Sub2_Sub2_1083.method435();
                membersInt = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                anInt1193 = aClass30_Sub2_Sub2_1083.method440();
                daysSinceLastLogin = aClass30_Sub2_Sub2_1083.readUnsignedWord();
                if (anInt1193 != 0 && openInterfaceID == -1) {
                    signlink.dnslookup(TextClass.method586(anInt1193));
                    clearTopInterfaces();
                    char c = '\u028A';
                    if (daysSinceRecovChange != 201 || membersInt == 1) {
                        c = '\u028F';
                    }
                    reportAbuseInput = "";
                    canMute = false;
                    for (int k9 = 0; k9 < RSInterface.interfaceCache.length; k9++) {
                        if (RSInterface.interfaceCache[k9] == null || RSInterface.interfaceCache[k9].anInt214 != c) {
                            continue;
                        }
                        openInterfaceID = RSInterface.interfaceCache[k9].parentID;
                        break;
                    }
                }
                pktType = -1;
                return true;
            }

            if (pktType == 64) { //I think this sets the higher value ground item higher on the contextmenu click
                anInt1268 = aClass30_Sub2_Sub2_1083.method427();
                anInt1269 = aClass30_Sub2_Sub2_1083.method428();
                for (int j = anInt1268; j < anInt1268 + 8; j++) {
                    for (int l9 = anInt1269; l9 < anInt1269 + 8; l9++) {
                        if (groundArray[plane][j][l9] != null) {
                            groundArray[plane][j][l9] = null;
                            method25(j, l9);
                        }
                    }
                }
                for (Class30_Sub1 class30_sub1 = (Class30_Sub1) aClass19_1179.reverseGetFirst(); class30_sub1 != null; class30_sub1 = (Class30_Sub1) aClass19_1179.reverseGetNext()) {
                    if (class30_sub1.anInt1297 >= anInt1268 && class30_sub1.anInt1297 < anInt1268 + 8 && class30_sub1.anInt1298 >= anInt1269 && class30_sub1.anInt1298 < anInt1269 + 8 && class30_sub1.anInt1295 == plane) {
                        class30_sub1.anInt1294 = 0;
                    }
                }
                pktType = -1;
                return true;
            }

            if (pktType == 185) { //Sends players head with equiped helmet in chatbox?
                int k = aClass30_Sub2_Sub2_1083.method436();
                RSInterface.interfaceCache[k].anInt233 = 3;
                if (aClass30_Sub2_Sub4_Sub1_Sub2_1126.desc == null) {
                    RSInterface.interfaceCache[k].mediaID = (aClass30_Sub2_Sub4_Sub1_Sub2_1126.anIntArray1700[0] << 25) + (aClass30_Sub2_Sub4_Sub1_Sub2_1126.anIntArray1700[4] << 20) + (aClass30_Sub2_Sub4_Sub1_Sub2_1126.equipment[0] << 15) + (aClass30_Sub2_Sub4_Sub1_Sub2_1126.equipment[8] << 10) + (aClass30_Sub2_Sub4_Sub1_Sub2_1126.equipment[11] << 5) + aClass30_Sub2_Sub4_Sub1_Sub2_1126.equipment[1];
                } else {
                    RSInterface.interfaceCache[k].mediaID = (int) (0x12345678L + aClass30_Sub2_Sub4_Sub1_Sub2_1126.desc.type);
                }
                pktType = -1;
                return true;
            }

            if (pktType == 107) { //No fucking clue
                aBoolean1160 = false;
                for (int l = 0; l < 5; l++) {
                    aBooleanArray876[l] = false;
                }
                pktType = -1;
                return true;
            }

            if (pktType == 72) { //Something wtih inventory items again
                int i1 = aClass30_Sub2_Sub2_1083.method434((byte) 108);
                RSInterface class9 = RSInterface.interfaceCache[i1];
                for (int k15 = 0; k15 < class9.inv.length; k15++) {
                    class9.inv[k15] = -1;
                    class9.inv[k15] = 0;
                }
                pktType = -1;
                return true;
            }

            if (pktType == 214) { //Sets ignore text with server side string
                ignoreCount = pktSize / 8;
                for (int j1 = 0; j1 < ignoreCount; j1++) {
                    ignoreListAsLongs[j1] = aClass30_Sub2_Sub2_1083.readQWord();
                }
                pktType = -1;
                return true;
            }

            if (pktType == 166) { //This is used for setting the camera to a set area. Cutscenes again maybe?
                aBoolean1160 = true;
                anInt1098 = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                anInt1099 = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                anInt1100 = aClass30_Sub2_Sub2_1083.readUnsignedWord();
                anInt1101 = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                anInt1102 = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                if (anInt1102 >= 100) {
                    xCameraPos = anInt1098 * 128 + 64;
                    yCameraPos = anInt1099 * 128 + 64;
                    zCameraPos = method42(plane, yCameraPos, true, xCameraPos) - anInt1100;
                }
                pktType = -1;
                return true;
            }

            if (pktType == 134) { //Hmm. Something with your stats. Probably used for calculating total level ?
                needDrawTabArea = true;
                int k1 = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                int i10 = aClass30_Sub2_Sub2_1083.method439((byte) 41);
                int l15 = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                currentExp[k1] = i10;
                currentStats[k1] = l15;
                maxStats[k1] = 1;
                for (int k20 = 0; k20 < 98; k20++) {
                    if (i10 >= anIntArray1019[k20]) {
                        maxStats[k1] = k20 + 2;
                    }
                }
                pktType = -1;
                return true;
            }

            if (pktType == 71) { //Sets Sidebar ID's
                int desiredId = aClass30_Sub2_Sub2_1083.readUnsignedWord();
                int sideBarID = aClass30_Sub2_Sub2_1083.method426();
                if (desiredId == 65535) {
                    desiredId = -1;
                }

                ////////////////

                if (sideBarID == 7) //notes
                {
                    desiredId = 19000;
                }

                if (sideBarID == 10) //logout
                {
                    desiredId = 2449;
                }

                if (sideBarID == 14) //cc
                {
                    desiredId = 18000;
                }

                if (sideBarID == 13) //music
                {
                    desiredId = 18000;
                }

                ////////////////

                tabInterfaceIDs[sideBarID] = desiredId;
                needDrawTabArea = true;
                tabAreaAltered = true;
                pktType = -1;
                return true;
            }

            if (pktType == 74) { //Song changing. Hmm. Should be intersting to check out.
                int song = aClass30_Sub2_Sub2_1083.method434((byte) 108);
                if (song == 65535) {
                    song = -1;
                }
                if (song != currentSong && musicEnabled && !lowMem && prevSong == 0) {
                    nextSong = song;
                    songChanging = true;
                    aClass42_Sub1_1068.method558(2, nextSong);
                }
                currentSong = song;
                pktType = -1;
                return true;
            }

            if (pktType == 121) { //Controls some music somewhere
                int songId = aClass30_Sub2_Sub2_1083.method436();
                int k10 = aClass30_Sub2_Sub2_1083.method435();
                if (musicEnabled && !lowMem) {
                    nextSong = songId;
                    songChanging = false;
                    aClass42_Sub1_1068.method558(2, nextSong);
                    prevSong = k10;
                }
                pktType = -1;
                return true;
            }

            if (pktType == 109) { //Short and Sweet it logs you out.
                resetLogout();
                pktType = -1;
                return false;
            }

            if (pktType == 70) { //Loads an interface child which is a model and its offset.(Special bar and others for example)
                int Xoffset = aClass30_Sub2_Sub2_1083.readSignedWord();
                int Yoffset = aClass30_Sub2_Sub2_1083.method437();
                int id = aClass30_Sub2_Sub2_1083.method434((byte) 108);
                RSInterface class9_5 = RSInterface.interfaceCache[id];
                class9_5.anInt263 = Xoffset;
                class9_5.anInt265 = Yoffset;
                pktType = -1;
                return true;
            }

            if (pktType == 73 || pktType == 241) { /* *Frames 73 and 241 *I have no fucking clue */
                int l2 = anInt1069;
                int i11 = anInt1070;
                if (pktType == 73) {
                    l2 = aClass30_Sub2_Sub2_1083.method435();
                    i11 = aClass30_Sub2_Sub2_1083.readUnsignedWord();
                    aBoolean1159 = false;
                }

                if (pktType == 241) {
                    i11 = aClass30_Sub2_Sub2_1083.method435();
                    aClass30_Sub2_Sub2_1083.initBitAccess();
                    for (int j16 = 0; j16 < 4; j16++) {
                        for (int l20 = 0; l20 < 13; l20++) {
                            for (int j23 = 0; j23 < 13; j23++) {
                                int i26 = aClass30_Sub2_Sub2_1083.readBits(1);
                                if (i26 == 1) {
                                    anIntArrayArrayArray1129[j16][l20][j23] = aClass30_Sub2_Sub2_1083.readBits(26);
                                } else {
                                    anIntArrayArrayArray1129[j16][l20][j23] = -1;
                                }
                            }
                        }
                    }
                    aClass30_Sub2_Sub2_1083.finishBitAccess();
                    l2 = aClass30_Sub2_Sub2_1083.readUnsignedWord();
                    aBoolean1159 = true;
                }
                if (anInt1069 == l2 && anInt1070 == i11 && loadingStage == 2) {
                    pktType = -1;
                    return true;
                }
                anInt1069 = l2;
                anInt1070 = i11;
                baseX = (anInt1069 - 6) * 8;
                baseY = (anInt1070 - 6) * 8;
                aBoolean1141 = false;
                if ((anInt1069 / 8 == 48 || anInt1069 / 8 == 49) && anInt1070 / 8 == 48) {
                    aBoolean1141 = true;
                }
                if (anInt1069 / 8 == 48 && anInt1070 / 8 == 148) {
                    aBoolean1141 = true;
                }
                loadingStage = 1;
                aLong824 = System.currentTimeMillis();

                //loading bar
                aClass15_1165.method237(0);
                DrawingArea.fillPixels(1, 130, 23, 0xffffff, 2);
                DrawingArea.method336(21, 3, 2, 0, 128);
                regularFont.drawText(0, "Loading - please wait.", 19, 67);
                regularFont.drawText(0xffffff, "Loading - please wait.", 18, 66);
                aClass15_1165.drawGraphics(4, 23680, super.aGraphics12, 4);

                if (pktType == 73) {
                    int k16 = 0;
                    for (int i21 = (anInt1069 - 6) / 8; i21 <= (anInt1069 + 6) / 8; i21++) {
                        for (int k23 = (anInt1070 - 6) / 8; k23 <= (anInt1070 + 6) / 8; k23++) {
                            k16++;
                        }
                    }
                    aByteArrayArray1183 = new byte[k16][];
                    aByteArrayArray1247 = new byte[k16][];
                    mapArea = new int[k16];
                    mapId = new int[k16];
                    objectFileID = new int[k16];
                    k16 = 0;
                    for (int l23 = (anInt1069 - 6) / 8; l23 <= (anInt1069 + 6) / 8; l23++) {
                        for (int j26 = (anInt1070 - 6) / 8; j26 <= (anInt1070 + 6) / 8; j26++) {
                            mapArea[k16] = (l23 << 8) + j26;
                            if (aBoolean1141 && (j26 == 49 || j26 == 149 || j26 == 147 || l23 == 50 || l23 == 49 && j26 == 47)) {
                                mapId[k16] = -1;
                                objectFileID[k16] = -1;
                                k16++;
                            } else {
                                int k28 = mapId[k16] = aClass42_Sub1_1068.method562(0, 0, j26, l23);
                                if (k28 != -1) {
                                    aClass42_Sub1_1068.method558(3, k28);
                                }
                                int j30 = objectFileID[k16] = aClass42_Sub1_1068.method562(1, 0, j26, l23);
                                if (j30 != -1) {
                                    aClass42_Sub1_1068.method558(3, j30);
                                }
                                k16++;
                            }
                        }
                    }
                }
                if (pktType == 241) {
                    int l16 = 0;
                    int ai[] = new int[676];
                    for (int i24 = 0; i24 < 4; i24++) {
                        for (int k26 = 0; k26 < 13; k26++) {
                            for (int l28 = 0; l28 < 13; l28++) {
                                int k30 = anIntArrayArrayArray1129[i24][k26][l28];
                                if (k30 != -1) {
                                    int k31 = k30 >> 14 & 0x3ff;
                                    int i32 = k30 >> 3 & 0x7ff;
                                    int k32 = (k31 / 8 << 8) + i32 / 8;
                                    for (int j33 = 0; j33 < l16; j33++) {
                                        if (ai[j33] != k32) {
                                            continue;
                                        }
                                        k32 = -1;
                                        break;
                                    }
                                    if (k32 != -1) {
                                        ai[l16++] = k32;
                                    }
                                }
                            }
                        }
                    }
                    aByteArrayArray1183 = new byte[l16][];
                    aByteArrayArray1247 = new byte[l16][];
                    mapArea = new int[l16];
                    mapId = new int[l16];
                    objectFileID = new int[l16];
                    for (int l26 = 0; l26 < l16; l26++) {
                        int i29 = mapArea[l26] = ai[l26];
                        int l30 = i29 >> 8 & 0xff;
                        int l31 = i29 & 0xff;
                        int j32 = mapId[l26] = aClass42_Sub1_1068.method562(0, 0, l31, l30);
                        if (j32 != -1) {
                            aClass42_Sub1_1068.method558(3, j32);
                        }
                        int i33 = objectFileID[l26] = aClass42_Sub1_1068.method562(1, 0, l31, l30);
                        if (i33 != -1) {
                            aClass42_Sub1_1068.method558(3, i33);
                        }
                    }
                }
                int i17 = baseX - anInt1036;
                int j21 = baseY - anInt1037;
                anInt1036 = baseX;
                anInt1037 = baseY;
                for (int j24 = 0; j24 < 16384; j24++) {
                    NPC class30_sub2_sub4_sub1_sub1 = npcArray[j24];
                    if (class30_sub2_sub4_sub1_sub1 != null) {
                        for (int j29 = 0; j29 < 10; j29++) {
                            ((Entity) (class30_sub2_sub4_sub1_sub1)).smallX[j29] -= i17;
                            ((Entity) (class30_sub2_sub4_sub1_sub1)).smallY[j29] -= j21;
                        }
                        class30_sub2_sub4_sub1_sub1.x -= i17 * 128;
                        class30_sub2_sub4_sub1_sub1.y -= j21 * 128;
                    }
                }
                for (int i27 = 0; i27 < maxPlayers; i27++) {
                    Player class30_sub2_sub4_sub1_sub2 = playerArray[i27];
                    if (class30_sub2_sub4_sub1_sub2 != null) {
                        for (int i31 = 0; i31 < 10; i31++) {
                            ((Entity) (class30_sub2_sub4_sub1_sub2)).smallX[i31] -= i17;
                            ((Entity) (class30_sub2_sub4_sub1_sub2)).smallY[i31] -= j21;
                        }
                        class30_sub2_sub4_sub1_sub2.x -= i17 * 128;
                        class30_sub2_sub4_sub1_sub2.y -= j21 * 128;
                    }
                }
                aBoolean1080 = true;
                byte byte1 = 0;
                byte byte2 = 104;
                byte byte3 = 1;
                if (i17 < 0) {
                    byte1 = 103;
                    byte2 = -1;
                    byte3 = -1;
                }
                byte byte4 = 0;
                byte byte5 = 104;
                byte byte6 = 1;
                if (j21 < 0) {
                    byte4 = 103;
                    byte5 = -1;
                    byte6 = -1;
                }
                for (int k33 = byte1; k33 != byte2; k33 += byte3) {
                    for (int l33 = byte4; l33 != byte5; l33 += byte6) {
                        int i34 = k33 + i17;
                        int j34 = l33 + j21;
                        for (int k34 = 0; k34 < 4; k34++) {
                            if (i34 >= 0 && j34 >= 0 && i34 < 104 && j34 < 104) {
                                groundArray[k34][k33][l33] = groundArray[k34][i34][j34];
                            } else {
                                groundArray[k34][k33][l33] = null;
                            }
                        }
                    }
                }
                for (Class30_Sub1 class30_sub1_1 = (Class30_Sub1) aClass19_1179.reverseGetFirst(); class30_sub1_1 != null; class30_sub1_1 = (Class30_Sub1) aClass19_1179.reverseGetNext()) {
                    class30_sub1_1.anInt1297 -= i17;
                    class30_sub1_1.anInt1298 -= j21;
                    if (class30_sub1_1.anInt1297 < 0 || class30_sub1_1.anInt1298 < 0 || class30_sub1_1.anInt1297 >= 104 || class30_sub1_1.anInt1298 >= 104) {
                        class30_sub1_1.unlink();
                    }
                }
                if (destX != 0) {
                    destX -= i17;
                    destY -= j21;
                }
                aBoolean1160 = false;
                pktType = -1;
                return true;
            }

            if (pktType == 208) { //Loads Skull for wild levels
                int skullId = aClass30_Sub2_Sub2_1083.method437();
                if (skullId >= 0) {
                    method60(skullId);
                }
                wildernessSkullId = skullId;
                pktType = -1;
                return true;
            }

            if (pktType == 99) { //Blacks out Minimap ;)
                minimapState = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                pktType = -1;
                return true;
            }

            if (pktType == 75) { //Chatbox head and Animation
                int modelID = aClass30_Sub2_Sub2_1083.method436();
                int id = aClass30_Sub2_Sub2_1083.method436();
                RSInterface.interfaceCache[id].anInt233 = 2;
                RSInterface.interfaceCache[id].mediaID = modelID;
                pktType = -1;
                return true;
            }

            if (pktType == 114) { //XeroCheez to this one. System update timer
                updateTime = aClass30_Sub2_Sub2_1083.method434((byte) 108) * 30;
                pktType = -1;
                return true;
            }

            if (pktType == 60) { //As far as im concerned no use to me
                anInt1269 = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                anInt1268 = aClass30_Sub2_Sub2_1083.method427();
                while (aClass30_Sub2_Sub2_1083.currentOffset < pktSize) {
                    int k3 = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                    method137(anInt1119, aClass30_Sub2_Sub2_1083, k3);
                }
                pktType = -1;
                return true;
            }

            if (pktType == 35) { //Seems to shake screen
                int l3 = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                int k11 = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                int j17 = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                int k21 = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                aBooleanArray876[l3] = true;
                anIntArray873[l3] = k11;
                anIntArray1203[l3] = j17;
                anIntArray928[l3] = k21;
                anIntArray1030[l3] = 0;
                pktType = -1;
                return true;
            }

            if (pktType == 174) { //Controls Media
                int i4 = aClass30_Sub2_Sub2_1083.readUnsignedWord();
                int l11 = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                int k17 = aClass30_Sub2_Sub2_1083.readUnsignedWord();
                if (aBoolean848 && !lowMem && anInt1062 < 50) {
                    anIntArray1207[anInt1062] = i4;
                    anIntArray1241[anInt1062] = l11;
                    anIntArray1250[anInt1062] = k17 + Sounds.anIntArray326[i4];
                    anInt1062++;
                }
                pktType = -1;
                return true;
            }

            if (pktType == 104) { //Creates player Right click options
                int stringId = aClass30_Sub2_Sub2_1083.method427();
                int i12 = aClass30_Sub2_Sub2_1083.method426();
                String playerAction = aClass30_Sub2_Sub2_1083.readString();
                if (stringId >= 1 && stringId <= 5) {
                    if (playerAction.equalsIgnoreCase("null")) {
                        playerAction = null;
                    }
                    atPlayerActions[stringId - 1] = playerAction;
                    atPlayerArray[stringId - 1] = i12 == 0;
                }
                pktType = -1;
                return true;
            }

            if (pktType == 78) { //Wtf does this do?
                destX = 0;
                pktType = -1;
                return true;
            }

            if (pktType == 253) { //Controls Player Commands
                String playerMessage = aClass30_Sub2_Sub2_1083.readString();
                if (playerMessage.endsWith(":clan")) {
                    String s3 = playerMessage.substring(0, playerMessage.indexOf(":"));
                    long l17 = TextClass.method583(s3);
                    boolean flag9 = false;
                    for (int j27 = 0; j27 < ignoreCount; j27++) {
                        if (ignoreListAsLongs[j27] != l17) {
                            continue;
                        }
                        flag9 = true;
                        break;
                    }
                    if (!flag9 && anInt1251 == 0) {
                        pushMessage(" ", 80, s3, aBoolean991);
                    }
                } else if (playerMessage.endsWith(":tradereq:")) {
                    String s3 = playerMessage.substring(0, playerMessage.indexOf(":"));
                    long l17 = TextClass.method583(s3);
                    boolean flag2 = false;
                    for (int j27 = 0; j27 < ignoreCount; j27++) {
                        if (ignoreListAsLongs[j27] != l17) {
                            continue;
                        }
                        flag2 = true;
                        break;
                    }
                    if (!flag2 && anInt1251 == 0) {
                        pushMessage("wishes to trade with you.", 4, s3, aBoolean991);
                    }
                } else if (playerMessage.endsWith(":duelreq:")) {
                    String s4 = playerMessage.substring(0, playerMessage.indexOf(":"));
                    long l18 = TextClass.method583(s4);
                    boolean flag3 = false;
                    for (int k27 = 0; k27 < ignoreCount; k27++) {
                        if (ignoreListAsLongs[k27] != l18) {
                            continue;
                        }
                        flag3 = true;
                        break;
                    }
                    if (!flag3 && anInt1251 == 0) {
                        pushMessage("wishes to duel with you.", 8, s4, aBoolean991);
                    }
                } else if (playerMessage.endsWith(":filter:")) {
                    String s5 = playerMessage.substring(0, playerMessage.indexOf(":"));
                    if (anInt1251 == 0) {
                        pushMessage(s5, 18, "", aBoolean991);
                    }
                } else if (playerMessage.endsWith(":chalreq:")) {
                    String s5 = playerMessage.substring(0, playerMessage.indexOf(":"));
                    long l19 = TextClass.method583(s5);
                    boolean flag4 = false;
                    for (int l27 = 0; l27 < ignoreCount; l27++) {
                        if (ignoreListAsLongs[l27] != l19) {
                            continue;
                        }
                        flag4 = true;
                        break;
                    }
                    if (!flag4 && anInt1251 == 0) {
                        String s8 = playerMessage.substring(playerMessage.indexOf(":") + 1, playerMessage.length() - 9);
                        pushMessage(s8, 8, s5, aBoolean991);
                    }
                } else {
                    pushMessage(playerMessage, 0, "", aBoolean991);
                }
                pktType = -1;
                return true;
            }

            if (pktType == 1) { //Cancels all animations in an area
                for (int k4 = 0; k4 < playerArray.length; k4++) {
                    if (playerArray[k4] != null) {
                        playerArray[k4].anim = -1;
                    }
                }
                for (int j12 = 0; j12 < npcArray.length; j12++) {
                    if (npcArray[j12] != null) {
                        npcArray[j12].anim = -1;
                    }
                }
                pktType = -1;
                return true;
            }

            if (pktType == 50) { //Loads login and logout messages. Really unneeded
                long l4 = aClass30_Sub2_Sub2_1083.readQWord();
                int i18 = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                String s7 = TextClass.fixName(TextClass.longForName(l4));
                for (int k24 = 0; k24 < friendsCount; k24++) {
                    if (l4 != friendsListAsLongs[k24]) {
                        continue;
                    }
                    if (friendNodeIDs[k24] != i18) {
                        friendNodeIDs[k24] = i18;
                        needDrawTabArea = true;
                        if (i18 > 1) {
                            pushMessage(s7 + " has logged in.", 5, "", aBoolean991);
                        }
                        if (i18 == 1) {
                            pushMessage(s7 + " has logged out.", 5, "", aBoolean991);
                        }
                    }
                    s7 = null;
                    break;
                }
                if (s7 != null && friendsCount < 200) {
                    friendsListAsLongs[friendsCount] = l4;
                    friendsList[friendsCount] = s7;
                    friendNodeIDs[friendsCount] = i18;
                    friendsCount++;
                    needDrawTabArea = true;
                }
                for (boolean flag6 = false; !flag6;) {
                    flag6 = true;
                    for (int k29 = 0; k29 < friendsCount - 1; k29++) {
                        if (friendNodeIDs[k29] != nodeID && friendNodeIDs[k29 + 1] == nodeID || friendNodeIDs[k29] == 0 && friendNodeIDs[k29 + 1] != 0) {
                            int j31 = friendNodeIDs[k29];
                            friendNodeIDs[k29] = friendNodeIDs[k29 + 1];
                            friendNodeIDs[k29 + 1] = j31;
                            String s10 = friendsList[k29];
                            friendsList[k29] = friendsList[k29 + 1];
                            friendsList[k29 + 1] = s10;
                            long l32 = friendsListAsLongs[k29];
                            friendsListAsLongs[k29] = friendsListAsLongs[k29 + 1];
                            friendsListAsLongs[k29 + 1] = l32;
                            needDrawTabArea = true;
                            flag6 = false;
                        }
                    }
                }
                pktType = -1;
                return true;
            }

            if (pktType == 110) { //Sends energy to server, shame we dont use this.
                if (tabID == 12) //player stats, includeing run energy
                {
                    needDrawTabArea = true;
                }
                energy = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                pktType = -1;
                return true;
            }

            if (pktType == 254) { //Will find out later
                anInt855 = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                if (anInt855 == 1) {
                    anInt1222 = aClass30_Sub2_Sub2_1083.readUnsignedWord();
                }
                if (anInt855 >= 2 && anInt855 <= 6) {
                    if (anInt855 == 2) {
                        anInt937 = 64;
                        anInt938 = 64;
                    }
                    if (anInt855 == 3) {
                        anInt937 = 0;
                        anInt938 = 64;
                    }
                    if (anInt855 == 4) {
                        anInt937 = 128;
                        anInt938 = 64;
                    }
                    if (anInt855 == 5) {
                        anInt937 = 64;
                        anInt938 = 0;
                    }
                    if (anInt855 == 6) {
                        anInt937 = 64;
                        anInt938 = 128;
                    }
                    anInt855 = 2;
                    anInt934 = aClass30_Sub2_Sub2_1083.readUnsignedWord();
                    anInt935 = aClass30_Sub2_Sub2_1083.readUnsignedWord();
                    anInt936 = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                }
                if (anInt855 == 10) {
                    anInt933 = aClass30_Sub2_Sub2_1083.readUnsignedWord();
                }
                pktType = -1;
                return true;
            }

            if (pktType == 248) { //Looks to set a sidebar interface with an open itnerface. Possibly like banking?
                int i5 = aClass30_Sub2_Sub2_1083.method435();
                int k12 = aClass30_Sub2_Sub2_1083.readUnsignedWord();
                if (backDialogID != -1) {
                    backDialogID = -1;
                    inputTaken = true;
                }
                if (inputDialogState != 0) {
                    inputDialogState = 0;
                    inputTaken = true;
                }
                openInterfaceID = i5;
                invOverlayInterfaceID = k12;
                needDrawTabArea = true;
                tabAreaAltered = true;
                isInterfaceOpen = false;
                pktType = -1;
                return true;
            }

            if (pktType == 79) { //Seems to set the main interface ID when using interface menus?
                int j5 = aClass30_Sub2_Sub2_1083.method434((byte) 108);
                int l12 = aClass30_Sub2_Sub2_1083.method435();
                RSInterface class9_3 = RSInterface.interfaceCache[j5];
                if (class9_3 != null && class9_3.type == 0) {
                    if (l12 < 0) {
                        l12 = 0;
                    }
                    if (l12 > class9_3.scrollMax - class9_3.height) {
                        l12 = class9_3.scrollMax - class9_3.height;
                    }
                    class9_3.scrollPosition = l12;
                }
                pktType = -1;
                return true;
            }

            if (pktType == 68) { //Turns split chat off
                for (int k5 = 0; k5 < variousSettings.length; k5++) {
                    if (variousSettings[k5] != anIntArray1045[k5]) {
                        variousSettings[k5] = anIntArray1045[k5];
                        method33(k5);
                        needDrawTabArea = true;
                    }
                }
                pktType = -1;
                return true;
            }

            if (pktType == 196) { //Loads Private Messages
                long l5 = aClass30_Sub2_Sub2_1083.readQWord();
                int j18 = aClass30_Sub2_Sub2_1083.readDWord();
                int l21 = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                boolean flag5 = false;
                for (int i28 = 0; i28 < 100; i28++) {
                    if (anIntArray1240[i28] != j18) {
                        continue;
                    }
                    flag5 = true;
                    break;
                }
                if (l21 <= 1) {
                    for (int l29 = 0; l29 < ignoreCount; l29++) {
                        if (ignoreListAsLongs[l29] != l5) {
                            continue;
                        }
                        flag5 = true;
                        break;
                    }
                }
                if (!flag5 && anInt1251 == 0) {
                    try {
                        anIntArray1240[anInt1169] = j18;
                        anInt1169 = (anInt1169 + 1) % 100;
                        String s9 = TextInput.method525(pktSize - 13, aClass30_Sub2_Sub2_1083);
                        if (l21 != 3) {
                            s9 = Censor.doCensor(s9);
                        }
                        if (l21 == 2 || l21 == 3) {
                            pushMessage(s9, 7, "@cr2@" + TextClass.fixName(TextClass.longForName(l5)), aBoolean991);
                        } else if (l21 == 1) {
                            pushMessage(s9, 7, "@cr1@" + TextClass.fixName(TextClass.longForName(l5)), aBoolean991);
                        } else {
                            pushMessage(s9, 3, TextClass.fixName(TextClass.longForName(l5)), aBoolean991);
                        }
                    } catch (Exception exception1) {
                        signlink.reporterror("cde1");
                    }
                }
                pktType = -1;
                return true;
            }

            if (pktType == 85) { //thanks to xerocheez seems to be something with graphics... Hmm.
                anInt1269 = aClass30_Sub2_Sub2_1083.method427();
                anInt1268 = aClass30_Sub2_Sub2_1083.method427();
                pktType = -1;
                return true;
            }

            if (pktType == 24) { //Flashes the selected sidebar
                flashingSidebarId = aClass30_Sub2_Sub2_1083.method428();
                if (flashingSidebarId == tabID) {
                    if (flashingSidebarId == 3) {
                        tabID = 1;
                    } else {
                        tabID = 3;
                    }
                    needDrawTabArea = true;
                }
                pktType = -1;
                return true;
            }

            if (pktType == 246) { //Something to do with loading models onto an interface, possibly for chat dialouges?
                int i6 = aClass30_Sub2_Sub2_1083.method434((byte) 108);
                int i13 = aClass30_Sub2_Sub2_1083.readUnsignedWord();
                int modelId = aClass30_Sub2_Sub2_1083.readUnsignedWord();
                if (modelId == 65535) {
                    RSInterface.interfaceCache[i6].anInt233 = 0;
                    pktType = -1;
                    return true;
                } else {
                    ItemDef class8 = ItemDef.forID(modelId);
                    RSInterface.interfaceCache[i6].anInt233 = 4;
                    RSInterface.interfaceCache[i6].mediaID = modelId;
                    RSInterface.interfaceCache[i6].anInt270 = class8.modelRotation1;
                    RSInterface.interfaceCache[i6].anInt271 = class8.modelRotation2;
                    RSInterface.interfaceCache[i6].anInt269 = (class8.modelZoom * 100) / i13;
                    pktType = -1;
                    return true;
                }
            }

            if (pktType == 171) { //Is wrong server side so usage unknown
                boolean hoverEnabled = aClass30_Sub2_Sub2_1083.readUnsignedByte() == 1;
                int j13 = aClass30_Sub2_Sub2_1083.readUnsignedWord();
                RSInterface.interfaceCache[j13].aBoolean266 = hoverEnabled;
                pktType = -1;
                return true;
            }

            if (pktType == 142) { //Interfaces, seems to draw interfaces into the inventory ?
                int j6 = aClass30_Sub2_Sub2_1083.method434((byte) 108);
                method60(j6);
                if (backDialogID != -1) {
                    backDialogID = -1;
                    inputTaken = true;
                }
                if (inputDialogState != 0) {
                    inputDialogState = 0;
                    inputTaken = true;
                }
                invOverlayInterfaceID = j6;
                needDrawTabArea = true;
                tabAreaAltered = true;
                openInterfaceID = -1;
                isInterfaceOpen = false;
                pktType = -1;
                return true;
            }

            if (pktType == 126) { //Controls clientside interface strings serverside.
                String text = aClass30_Sub2_Sub2_1083.readString();
                int id = aClass30_Sub2_Sub2_1083.method435();
                RSInterface.interfaceCache[id].message = text;
                if (RSInterface.interfaceCache[id].parentID == tabInterfaceIDs[tabID]) {
                    needDrawTabArea = true;
                }
                pktType = -1;
                return true;
            }

            if (pktType == 206) { //Real way to do chat options
                publicChatMode = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                privateChatMode = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                tradeMode = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                needChatAreaRedraw = true;
                inputTaken = true;
                pktType = -1;
                return true;
            }

            if (pktType == 240) { //Sends Server weight amount
                if (tabID == 12) {
                    needDrawTabArea = true;
                }
                weight = aClass30_Sub2_Sub2_1083.readSignedWord();
                pktType = -1;
                return true;
            }

            if (pktType == 8) { //wtf?
                int k6 = aClass30_Sub2_Sub2_1083.method436();
                int l13 = aClass30_Sub2_Sub2_1083.readUnsignedWord();
                RSInterface.interfaceCache[k6].anInt233 = 1;
                RSInterface.interfaceCache[k6].mediaID = l13;
                pktType = -1;
                return true;
            }

            if (pktType == 122) { //Changes colors of the selected interface child
                int textId = aClass30_Sub2_Sub2_1083.method436();
                int i14 = aClass30_Sub2_Sub2_1083.method436();
                int i19 = i14 >> 10 & 0x1f;
                int i22 = i14 >> 5 & 0x1f;
                int l24 = i14 & 0x1f;
                RSInterface.interfaceCache[textId].textColor = (i19 << 19) + (i22 << 11) + (l24 << 3);
                pktType = -1;
                return true;
            }

            if (pktType == 53) { //Something with bank items?
                needDrawTabArea = true;
                int i7 = aClass30_Sub2_Sub2_1083.readUnsignedWord();
                RSInterface class9_1 = RSInterface.interfaceCache[i7];
                int j19 = aClass30_Sub2_Sub2_1083.readUnsignedWord();
                for (int j22 = 0; j22 < j19; j22++) {
                    int i25 = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                    if (i25 == 255) {
                        i25 = aClass30_Sub2_Sub2_1083.method440();
                    }
                    class9_1.inv[j22] = aClass30_Sub2_Sub2_1083.method436();
                    class9_1.invStackSized[j22] = i25;
                }
                for (int j25 = j19; j25 < class9_1.inv.length; j25++) {
                    class9_1.inv[j25] = 0;
                    class9_1.invStackSized[j25] = 0;
                }
                pktType = -1;
                return true;
            }

            if (pktType == 230) { //Controls models on interfaces
                int j7 = aClass30_Sub2_Sub2_1083.method435();
                int j14 = aClass30_Sub2_Sub2_1083.readUnsignedWord();
                int k19 = aClass30_Sub2_Sub2_1083.readUnsignedWord();
                int k22 = aClass30_Sub2_Sub2_1083.method436();
                RSInterface.interfaceCache[j14].anInt270 = k19;
                RSInterface.interfaceCache[j14].anInt271 = k22;
                RSInterface.interfaceCache[j14].anInt269 = j7;
                pktType = -1;
                return true;
            }

            if (pktType == 221) { //Loads Private messages?
                anInt900 = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                needDrawTabArea = true;
                pktType = -1;
                return true;
            }

            if (pktType == 177) { //Something with camera position. Possibly cutscenes?
                aBoolean1160 = true;
                anInt995 = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                anInt996 = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                anInt997 = aClass30_Sub2_Sub2_1083.readUnsignedWord();
                anInt998 = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                anInt999 = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                if (anInt999 >= 100) {
                    int k7 = anInt995 * 128 + 64;
                    int k14 = anInt996 * 128 + 64;
                    int i20 = method42(plane, k14, true, k7) - anInt997;
                    int l22 = k7 - xCameraPos;
                    int k25 = i20 - zCameraPos;
                    int j28 = k14 - yCameraPos;
                    int i30 = (int) Math.sqrt(l22 * l22 + j28 * j28);
                    yCameraCurve = (int) (Math.atan2(k25, i30) * 325.94900000000001D) & 0x7ff;
                    xCameraCurve = (int) (Math.atan2(l22, j28) * -325.94900000000001D) & 0x7ff;
                    if (yCameraCurve < 128) {
                        yCameraCurve = 128;
                    }
                    if (yCameraCurve > 383) {
                        yCameraCurve = 383;
                    }
                }
                pktType = -1;
                return true;
            }

            if (pktType == 249) { //Thanks Ben(2012) - Something with player being a member
                anInt1046 = aClass30_Sub2_Sub2_1083.method426();
                anInt884 = aClass30_Sub2_Sub2_1083.method436();
                pktType = -1;
                return true;
            }

            if (pktType == 65) { //NPC Updating packet
                updateNPCs(aClass30_Sub2_Sub2_1083, pktSize);
                pktType = -1;
                return true;
            }

            if (pktType == 27) { //Removes a prompt from chatbox
                messagePromptRaised = false;
                inputDialogState = 1;
                amountOrNameInput = "";
                inputTaken = true;
                pktType = -1;
                return true;
            }

            if (pktType == 187) { //Prompts user to enter a value
                messagePromptRaised = false;
                inputDialogState = 2;
                amountOrNameInput = "";
                inputTaken = true;
                pktType = -1;
                return true;
            }

            if (pktType == 97) { //Used for displaying interfaces
                int desiredInterfaceId = aClass30_Sub2_Sub2_1083.readUnsignedWord();
                method60(desiredInterfaceId);
                if (invOverlayInterfaceID != -1) {
                    invOverlayInterfaceID = -1;
                    needDrawTabArea = true;
                    tabAreaAltered = true;
                }
                if (backDialogID != -1) {
                    backDialogID = -1;
                    inputTaken = true;
                }
                if (inputDialogState != 0) {
                    inputDialogState = 0;
                    inputTaken = true;
                }
                openInterfaceID = desiredInterfaceId;
                isInterfaceOpen = false;
                pktType = -1;
                return true;
            }

            if (pktType == 218) { //Shows an interface over chatbox
                dialogID = aClass30_Sub2_Sub2_1083.method438();
                inputTaken = true;
                pktType = -1;
                return true;
            }

            if (pktType == 87) { //Will look into at a later time.
                int j8 = aClass30_Sub2_Sub2_1083.method434((byte) 108);
                int l14 = aClass30_Sub2_Sub2_1083.method439((byte) 41);
                anIntArray1045[j8] = l14;
                if (variousSettings[j8] != l14) {
                    variousSettings[j8] = l14;
                    method33(j8);
                    needDrawTabArea = true;
                    if (dialogID != -1) {
                        inputTaken = true;
                    }
                }
                pktType = -1;
                return true;
            }

            if (pktType == 36) { //Used for setting interface configuration frames
                int k8 = aClass30_Sub2_Sub2_1083.method434((byte) 108);
                byte byte0 = aClass30_Sub2_Sub2_1083.readSignedByte();
                anIntArray1045[k8] = byte0;
                if (variousSettings[k8] != byte0) {
                    variousSettings[k8] = byte0;
                    method33(k8);
                    needDrawTabArea = true;
                    if (dialogID != -1) {
                        inputTaken = true;
                    }
                }
                pktType = -1;
                return true;
            }

            if (pktType == 61) { //sets overlay Icons
                multiOverlayIconID = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                pktType = -1;
                return true;
            }

            if (pktType == 200) { //Animates the chatbox head
                int l8 = aClass30_Sub2_Sub2_1083.readUnsignedWord();
                int i15 = aClass30_Sub2_Sub2_1083.readSignedWord();
                RSInterface class9_4 = RSInterface.interfaceCache[l8];
                class9_4.anInt257 = i15;
                if (i15 == -1) {
                    class9_4.anInt246 = 0;
                    class9_4.anInt208 = 0;
                }
                pktType = -1;
                return true;
            }

            if (pktType == 219) { //Clears all interfaces 
                if (invOverlayInterfaceID != -1) {
                    invOverlayInterfaceID = -1;
                    needDrawTabArea = true;
                    tabAreaAltered = true;
                }
                if (backDialogID != -1) {
                    backDialogID = -1;
                    inputTaken = true;
                }
                if (inputDialogState != 0) {
                    inputDialogState = 0;
                    inputTaken = true;
                }
                openInterfaceID = -1;
                isInterfaceOpen = false;
                pktType = -1;
                return true;
            }

            if (pktType == 34) { //displays items for equipment
                needDrawTabArea = true;
                int equipmentId = aClass30_Sub2_Sub2_1083.readUnsignedWord();
                RSInterface class9_2 = RSInterface.interfaceCache[equipmentId];
                while (aClass30_Sub2_Sub2_1083.currentOffset < pktSize) {
                    int j20 = aClass30_Sub2_Sub2_1083.method422();
                    int i23 = aClass30_Sub2_Sub2_1083.readUnsignedWord();
                    int l25 = aClass30_Sub2_Sub2_1083.readUnsignedByte();
                    if (l25 == 255) {
                        l25 = aClass30_Sub2_Sub2_1083.readDWord();
                    }
                    if (j20 >= 0 && j20 < class9_2.inv.length) {
                        class9_2.inv[j20] = i23;
                        class9_2.invStackSized[j20] = l25;
                    }
                }
                pktType = -1;
                return true;
            }

            if (pktType == 105 || pktType == 84 || pktType == 147 || pktType == 215 || pktType == 4 || pktType == 117 || pktType == 156 || pktType == 44 || pktType == 160 || pktType == 101 || pktType == 151) {
                method137(anInt1119, aClass30_Sub2_Sub2_1083, pktType);
                pktType = -1;
                return true;
            }

            if (pktType == 106) { //Sets sidebar interface ID
                tabID = aClass30_Sub2_Sub2_1083.method427();
                needDrawTabArea = true;
                tabAreaAltered = true;
                pktType = -1;
                return true;
            }

            if (pktType == 164) { //CHatBod Dialouge Interfaces XD
                int j9 = aClass30_Sub2_Sub2_1083.method434((byte) 108);
                method60(j9);
                if (invOverlayInterfaceID != -1) {
                    invOverlayInterfaceID = -1;
                    needDrawTabArea = true;
                    tabAreaAltered = true;
                }
                backDialogID = j9;
                inputTaken = true;
                openInterfaceID = -1;
                isInterfaceOpen = false;
                pktType = -1;
                return true;
            }
            signlink.reporterror("T1 - " + pktType + "," + pktSize + " - " + anInt842 + "," + anInt843);
            resetLogout();
        } catch (IOException _ex) {
            dropClient();
        } catch (Exception exception) {



            /** Debugging packet 126 (text frames packet) **/
            int s = aClass30_Sub2_Sub2_1083.method434((byte) 108);
            int i = aClass30_Sub2_Sub2_1083.method435();
            System.out.println();
            System.out.println("---------------------Please report this error on our forums--------------------");
            System.out.println("Text ID: " + i);
            System.out.println(s);
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println();
            /** [EO] Debugging packet 126 (text frames packet) **/
            //String s2 = "T2 - " + pktType + "," + anInt842 + "," + anInt843 + " - " + pktSize + "," + (baseX + ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallX[0]) + "," + (baseY + ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).smallY[0]) + " - ";
            //for(int j15 = 0; j15 < pktSize && j15 < 50; j15++)
            //	s2 = s2 + aClass30_Sub2_Sub2_1083.buffer[j15] + ",";
            //signlink.reporterror(s2);
            //resetLogout();
        }
        return true;
    }

    public final void loadWorld() {
        anInt1265++;
        method47(true);
        method26(true);
        method47(false);
        method26(false);
        method55();
        method104();
        if (!aBoolean1160) {
            int i = anInt1184;
            if (anInt984 / 256 > i) {
                i = anInt984 / 256;
            }
            if (aBooleanArray876[4] && anIntArray1203[4] + 128 > i) {
                i = anIntArray1203[4] + 128;
            }
            int k = anInt1185 + anInt896 & 0x7ff;
            //setCameraPos(600 + i * 3, i, anInt1014, method42(plane, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).y, true, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).x) - 50, k, anInt1015);
            setCameraPos(CameraPos2 + i * CameraPos1, i, anInt1014, method42(plane, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).y, true, ((Entity) (aClass30_Sub2_Sub4_Sub1_Sub2_1126)).x) - 50, k, anInt1015);
        }
        int j;
        if (!aBoolean1160) {
            j = method120(111);
        } else {
            j = method121(anInt1081);
        }
        int l = xCameraPos;
        int i1 = zCameraPos;
        int j1 = yCameraPos;
        int k1 = yCameraCurve;
        int l1 = xCameraCurve;
        for (int i2 = 0; i2 < 5; i2++) {
            if (aBooleanArray876[i2]) {
                int j2 = (int) ((Math.random() * (double) (anIntArray873[i2] * 2 + 1) - (double) anIntArray873[i2]) + Math.sin((double) anIntArray1030[i2] * ((double) anIntArray928[i2] / 100D)) * (double) anIntArray1203[i2]);
                if (i2 == 0) {
                    xCameraPos += j2;
                }
                if (i2 == 1) {
                    zCameraPos += j2;
                }
                if (i2 == 2) {
                    yCameraPos += j2;
                }
                if (i2 == 3) {
                    xCameraCurve = xCameraCurve + j2 & 0x7ff;
                }
                if (i2 == 4) {
                    yCameraCurve += j2;
                    if (yCameraCurve < 128) {
                        yCameraCurve = 128;
                    }
                    if (yCameraCurve > 383) {
                        yCameraCurve = 383;
                    }
                }
            }
        }
        int k2 = Texture.anInt1481;
        Model.aBoolean1684 = true;
        Model.anInt1687 = 0;
        Model.anInt1685 = super.anInt20 - 4;
        Model.anInt1686 = super.anInt21 - 4;
        DrawingArea.setAllPixelsToZero();
        if (Csettings.skySmog) {
            DrawingArea.method336(334, 0, 0, Csettings.SkyColour, 511);
        }
        aClass25_946.method313(xCameraPos, yCameraPos, xCameraCurve, zCameraPos, j, yCameraCurve, false);
        aClass25_946.method288((byte) 104);
        method34(anInt898);
        drawHeadIcon();
        method37(854, k2);
        method112(8);
        aClass15_1165.drawGraphics(4, 23680, super.aGraphics12, 4);
        xCameraPos = l;
        zCameraPos = i1;
        yCameraPos = j1;
        yCameraCurve = k1;
        xCameraCurve = l1;
        return;

    }

    public final void clearTopInterfaces() {
        aClass30_Sub2_Sub2_1192.createFrame(130);
        if (invOverlayInterfaceID != -1) {
            invOverlayInterfaceID = -1;
            needDrawTabArea = true;
            isInterfaceOpen = false;
            tabAreaAltered = true;
        }
        if (backDialogID != -1) {
            backDialogID = -1;
            inputTaken = true;
            isInterfaceOpen = false;
        }
        openInterfaceID = -1;
    }

    public client() {
        anIntArrayArray825 = new int[104][104];
        friendNodeIDs = new int[200];
        groundArray = new NodeList[4][104][104];
        aBoolean830 = true;
        aBoolean831 = false;
        aClass30_Sub2_Sub2_834 = new Stream(new byte[5000]);
        npcArray = new NPC[16384];
        anIntArray837 = new int[16384];
        anInt838 = 9;
        anIntArray840 = new int[1000];
        aClass30_Sub2_Sub2_847 = Stream.create(1, 9);
        aBoolean848 = true;
        openInterfaceID = -1;
        currentExp = new int[Skills.skillsCount];
        aBoolean872 = false;
        anIntArray873 = new int[5];
        anInt874 = -1;
        anInt875 = -680;
        aBooleanArray876 = new boolean[5];
        anInt877 = 1834;
        drawFlames = false;
        reportAbuseInput = "";
        anInt882 = -30815;
        anInt883 = 533;
        anInt884 = -1;
        menuOpen = false;
        inputString = "";
        maxPlayers = 2048;
        anInt889 = 2047;
        playerArray = new Player[maxPlayers];
        anIntArray892 = new int[maxPlayers];
        anIntArray894 = new int[maxPlayers];
        aClass30_Sub2_Sub2Array895 = new Stream[maxPlayers];
        anInt897 = 1;
        anIntArrayArray901 = new int[104][104];
        anInt902 = 0x847254;
        aByteArray912 = new byte[16384];
        aByte920 = 14;
        anInt921 = 732;
        currentStats = new int[Skills.skillsCount];
        aByte923 = 25;
        ignoreListAsLongs = new long[100];
        loadingError = false;
        anInt927 = 0x443c2d;
        anIntArray928 = new int[5];
        anIntArrayArray929 = new int[104][104];
        aCRC32_930 = new CRC32();
        anInt939 = 748;
        chatTypes = new int[100];
        chatNames = new String[100];
        chatMessages = new String[100];
        sideIcons = new Sprite[15];
        aBoolean954 = true;
        friendsListAsLongs = new long[200];
        currentSong = -1;
        drawingFlames = false;
        spriteDrawX = -1;
        spriteDrawY = -1;
        anIntArray968 = new int[33];
        anIntArray969 = new int[256];
        aClass14Array970 = new Decompressor[5];
        variousSettings = new int[2000];
        aBoolean972 = false;
        aByte973 = -74;
        anInt975 = 50;
        anIntArray976 = new int[anInt975];
        anIntArray977 = new int[anInt975];
        anIntArray978 = new int[anInt975];
        anIntArray979 = new int[anInt975];
        anIntArray980 = new int[anInt975];
        anIntArray981 = new int[anInt975];
        anIntArray982 = new int[anInt975];
        aStringArray983 = new String[anInt975];
        anInt985 = -1;
        hitMarks = new Sprite[20];
        anIntArray990 = new int[5];
        aBoolean991 = false;
        aBoolean994 = false;
        anInt1002 = 0x252019;
        amountOrNameInput = "";
        aByte1012 = 24;
        aClass19_1013 = new NodeList();
        aBoolean1017 = false;
        wildernessSkullId = -1;
        anIntArray1030 = new int[5];
        aBoolean1031 = false;
        mapFunctions = new Sprite[100];
        scrollBar = new Sprite[2];
        dialogID = -1;
        aBoolean1043 = false;
        maxStats = new int[Skills.skillsCount];
        anIntArray1045 = new int[2000];
        aBoolean1047 = true;
        anInt1050 = 111;
        anIntArray1052 = new int[152];
        flashingSidebarId = -1;
        aClass19_1056 = new NodeList();
        anIntArray1057 = new int[33];
        anInt1058 = 24869;
        chatBoxScrollbar = new RSInterface();
        mapScenes = new Background[100];
        anInt1063 = 0x594d3a;
        anIntArray1065 = new int[7];
        anIntArray1072 = new int[1000];
        anIntArray1073 = new int[1000];
        aBoolean1080 = false;
        anInt1081 = -733;
        friendsList = new String[200];
        aClass30_Sub2_Sub2_1083 = Stream.create(1, 9);
        anIntArray1090 = new int[9];
        menuActionCmd2 = new int[500];
        menuActionCmd3 = new int[500];
        menuActionID = new int[500];
        menuActionCmd1 = new int[500];
        headIcons = new Sprite[20];
        tabAreaAltered = false;
        anInt1105 = 519;
        aBoolean1106 = false;
        anInt1116 = 445;
        anInt1118 = -29508;
        anInt1119 = -77;
        aString1121 = "";
        atPlayerActions = new String[5];
        atPlayerArray = new boolean[5];
        anIntArrayArrayArray1129 = new int[4][13][13];
        anInt1132 = 2;
        anInt1135 = -12499;
        aClass30_Sub2_Sub1_Sub1Array1140 = new Sprite[1000];
        aBoolean1141 = false;
        isInterfaceOpen = false;
        crosses = new Sprite[8];
        musicEnabled = true;
        needDrawTabArea = false;
        loggedIn = false;
        canMute = false;
        aBoolean1159 = false;
        aBoolean1160 = false;
        anInt1171 = 1;
        myUsername = "";
        myPassword = "";
        server = Csettings.server;
        port = Csettings.port;
        genericLoadingError = false;
        anInt1178 = -1;
        aClass19_1179 = new NodeList();
        anInt1184 = 128;
        invOverlayInterfaceID = -1;
        aClass30_Sub2_Sub2_1192 = Stream.create(1, 9);
        aByte1194 = 5;
        menuActionName = new String[500];
        anIntArray1203 = new int[5];
        aBoolean1206 = true;
        anIntArray1207 = new int[50];
        anInt1210 = 2;
        scrollPos = 78;
        aString1212 = "";
        aByte1217 = 6;
        anInt1218 = -589;
        modIcons = new Sprite[2];
        tabID = 3; //set to equip tab
        inputTaken = false;
        songChanging = true;
        anIntArray1229 = new int[152];
        aClass11Array1230 = new Class11[4];
        needChatAreaRedraw = false;
        anIntArray1240 = new int[100];
        anIntArray1241 = new int[50];
        aBoolean1242 = false;
        anIntArray1250 = new int[50];
        rsAlreadyLoaded = false;
        welcomeScreenRaised = false;
        messagePromptRaised = false;
        loginMessage1 = "Welcome to -GaMeR X-'s Client.";
        loginMessage2 = "We hope you enjoy using it.";
        aByte1274 = -13;
        backDialogID = -1;
        aBoolean1277 = true;
        anInt1279 = 2;
        anIntArray1280 = new int[4000];
        anIntArray1281 = new int[4000];
        anInt1289 = -1;
    }
    
    public void logout(boolean flag) {
        try {
            if (aClass24_1168 != null) {
                aClass24_1168.close();
            }
        } catch (Exception exception) {
        }
        aClass24_1168 = null;
        if (!flag) {
            return;
        }
        loggedIn = false;
        method23(false);
        aClass25_946.initToNull(619);
        for (int i = 0; i < 4; i++) {
            aClass11Array1230[i].method210();
        }
        System.gc();
        stopMidi();
        currentSong = -1;
        nextSong = -1;
        prevSong = 0;

    }
    
    
    public MapMain mapMain = null;
    
    private String homeDir = sign.signlink.findcachedir();
    private String saveAs = sign.signlink.findcachedir() + Csettings.cacheNAME;
    private String urlLoc = Csettings.cacheURL;
    private String latestCacheVersion = Csettings.cacheVersion();
    private String latestClientVersion = Csettings.clientVersion;
    public int CameraPos1 = 3;
    public int CameraPos2 = 600;
    //CUSTOM VARS
    public static boolean flip = false;
    public static boolean flip_s = false;
    public static boolean flip_r = false;
    public boolean prayClicked = false;
    public boolean prayHover = false;
    public boolean runClicked = false;
    public boolean runHover = false;
    public boolean highlightText = false;
    public String theMessage = "";
    //LOGIN SCREEN
    public boolean faded = false;
    public boolean saveUser = false;
    public boolean showServer = false;
    public boolean showWorld = false;
    public boolean logHover = false;
    public boolean questionHover = false;
    //OTHERS
    public int spellID = 0;
    public boolean globeHover = false;
    public boolean noclip = false;
    public static int qcFromLeft;
    public static int qcFromTop;
    //CHAT OPTIONS
    public boolean clickedTab[] = {
        true, false, false, false, false, false, false
    };
    public long flashRate;
    public boolean pauseFlash[] = new boolean[6];
    public int flashTimes[] = {5, 5, 5, 5, 5, 5};
    public boolean newTextDrawn[] = new boolean[6];
    public boolean flashRequest[] = new boolean[6];
    public boolean highLight[] = new boolean[8];
    public int AssistButton = 0, ClanButton = 0, gameMode = 0;
    //SPRITES
    //orb sprites
    public Sprite[] ORBS = new Sprite[15];
    //chat options
    private Sprite Button0;
    private Sprite Button1;
    private Sprite Button2;
    private Sprite FlashButton0;
    private Sprite HoverButton0;
    private Sprite HoverButton1;
    private Sprite HoverButton2;
    //loading bar
    private Sprite LoadingFull;
    private Sprite LoadingText;
    //loading bar
    private Sprite NewLoadBase;
    private Sprite NewLoadFill;
    //hpbars
    private Sprite[] HPBarFull = new Sprite[6];
    private Sprite[] HPBarEmpty = new Sprite[6];
    private Sprite[] WorldFlags = new Sprite[5];
    //minimap
    private Sprite GlobeSprite;
    private Sprite[] zoomSprites = new Sprite[2];
    public Sprite[] LogoutDoor = new Sprite[6];
    private Sprite CustomMapback;
    //tabs
    //normal
    private Sprite nch;
    private Sprite nh;
    //rigth top
    private Sprite rtch;
    private Sprite rth;
    //left top
    private Sprite ltch;
    private Sprite lth;
    //right bottom
    private Sprite rbch;
    private Sprite rbh;
    //left bottom
    private Sprite lbch;
    private Sprite lbh;
    //other
    private Sprite quickChat;
    private Sprite typer; //* after text
    //login button sprite
    private Sprite loginBase;
    private Sprite loginTextBox;
    private Sprite loginExit;
    private Sprite loginExitH;
    private Sprite loginGo;
    private Sprite loginGoH;
    private Sprite loginSaveOn;
    private Sprite loginSaveOff;
    private Sprite refreshBtn;
    private Sprite worldSelect;
    private Sprite cancelBut;
    private Sprite cancelButovr;
    private Sprite gStar;
    private Sprite sStar;
    private Sprite visitURL;
    private Sprite WorldBtn;
    private Sprite WorldBtnovr;
    private Sprite background;
    //LOADED FROM SETTINGS
    public static boolean nearby = Csettings.ShowNearby;
    public World[] worlds = new World[Csettings.serverList.length];
    public boolean hp = Csettings.HeadHP; //head hp
    public int HPBarID = Csettings.HPBarID; //get the setting
    public String hilightCueWord = Csettings.hilightCueWord;
    //NORMAL VARS
    private int ignoreCount;
    public String censor2 = "";
    private static byte aByte823 = 77;
    private long aLong824;
    private int anIntArrayArray825[][];
    private int friendNodeIDs[];
    private NodeList groundArray[][][];
    private int anIntArray828[];
    private int anIntArray829[];
    private boolean aBoolean830;
    private volatile boolean aBoolean831;
    private Socket aSocket832;
    private Stream aClass30_Sub2_Sub2_834;
    private NPC npcArray[];
    private int npcCount;
    int anIntArray837[];
    private int anInt838;
    private int anInt839;
    int anIntArray840[];
    private int anInt841;
    private int anInt842;
    private int anInt843;
    private String aString844;
    private int privateChatMode;
    private static int anInt846;
    private Stream aClass30_Sub2_Sub2_847;
    private boolean aBoolean848;
    private static int anInt849;
    private int anIntArray850[];
    private int anIntArray851[];
    private int anIntArray852[];
    private int anIntArray853[];
    private static int anInt854;
    private int anInt855;
    private static BigInteger aBigInteger856 = new BigInteger("7162900525229798032761816791230527296329313291232324290237849263501208207972894053929065636522363163621000728841182238772712427862772219676577293600221789");
    public static int openInterfaceID;
    private int xCameraPos;
    private int zCameraPos;
    private int yCameraPos;
    private int yCameraCurve;
    private int xCameraCurve;
    public static int anInt863;
    private int currentExp[];
    private Sprite aClass30_Sub2_Sub1_Sub1_865;
    private Sprite aClass30_Sub2_Sub1_Sub1_866;
    private Background aClass30_Sub2_Sub1_Sub2_867;
    private Sprite aClass30_Sub2_Sub1_Sub1_868;
    private Background aClass30_Sub2_Sub1_Sub2_869;
    private Sprite mapFlag;
    private Sprite mapMarker;
    private boolean aBoolean872;
    private int anIntArray873[];
    private int anInt874;
    private int anInt875;
    private boolean aBooleanArray876[];
    private int anInt877;
    private int weight;
    Class48 mouseDetection;
    private volatile boolean drawFlames;
    private String reportAbuseInput;
    private int anInt882;
    private int anInt883;
    private int anInt884;
    private boolean menuOpen;
    private int hoverSpriteid;
    public static String inputString;
    private int maxPlayers;
    private int anInt889;
    private Player playerArray[];
    private int anInt891;
    int anIntArray892[];
    private int anInt893;
    private int anIntArray894[];
    private Stream aClass30_Sub2_Sub2Array895[];
    private int anInt896;
    private int anInt897;
    private int anInt898;
    private int friendsCount;
    private int anInt900;
    private int anIntArrayArray901[][];
    private int anInt902;
    private RSImageProducer aClass15_903;
    private RSImageProducer aClass15_904;
    private RSImageProducer aClass15_906;
    private RSImageProducer aClass15_907;
    private RSImageProducer aClass15_909;
    private RSImageProducer aClass15_910;
    private RSImageProducer aClass15_911;
    private byte aByteArray912[];
    private int anInt913;
    private int anInt914;
    private int anInt915;
    private int crossIndex;
    private int crossType;
    private int plane;
    private static boolean aBoolean919 = true;
    private byte aByte920;
    private int anInt921;
    private int currentStats[];
    private byte aByte923;
    private static int anInt924;
    private long ignoreListAsLongs[];
    private boolean loadingError;
    private int anInt927;
    private int anIntArray928[];
    private int anIntArrayArray929[][];
    private CRC32 aCRC32_930;
    private Sprite aClass30_Sub2_Sub1_Sub1_931;
    private Sprite aClass30_Sub2_Sub1_Sub1_932;
    private int anInt933;
    private int anInt934;
    private int anInt935;
    private int anInt936;
    private int anInt937;
    private int anInt938;
    private int anInt939;
    private static int anInt940;
    private static int anInt941;
    private int chatTypes[];
    private String chatNames[];
    private String chatMessages[];
    private int anInt945;
    private WorldController aClass25_946;
    private Sprite sideIcons[];
    private int menuScreenArea;
    private int menuOffsetX;
    private int menuOffsetY;
    private int menuWidth;
    private int menuHeight;
    private long aLong953;
    boolean aBoolean954;
    private long friendsListAsLongs[];
    private int currentSong;
    private static int nodeID = 10;
    private static boolean isMembers = true;
    private static boolean lowMem;
    private volatile boolean drawingFlames;
    private int spriteDrawX;
    private int spriteDrawY;
    private int anIntArray968[];
    private int anIntArray969[];
    Decompressor aClass14Array970[];
    public int variousSettings[];
    private boolean aBoolean972;
    private byte aByte973;
    private int anInt974;
    private int anInt975;
    private int anIntArray976[];
    private int anIntArray977[];
    private int anIntArray978[];
    private int anIntArray979[];
    private int anIntArray980[];
    private int anIntArray981[];
    private int anIntArray982[];
    private String aStringArray983[];
    private int anInt984;
    private int anInt985;
    private static int anInt986;
    private Sprite hitMarks[];
    private int anInt988;
    private int anInt989;
    private int anIntArray990[];
    private boolean aBoolean991;
    private int anInt992;
    private static boolean aBoolean993;
    private boolean aBoolean994;
    private int anInt995;
    private int anInt996;
    private int anInt997;
    private int anInt998;
    private int anInt999;
    private ISAACRandomGen aClass17_1000;
    private Sprite mapEdge;
    private int anInt1002;
    private String amountOrNameInput;
    private static int anInt1005;
    private int daysSinceLastLogin;
    private int pktSize;
    public int pktType;
    private int anInt1009;
    private int anInt1010;
    private int anInt1011;
    private byte aByte1012;
    private NodeList aClass19_1013;
    private int anInt1014;
    private int anInt1015;
    private int anInt1016;
    private boolean aBoolean1017;
    private int wildernessSkullId;
    private static int anIntArray1019[];
    private int anInt1020;
    private int minimapState;
    int anInt1022;
    private int loadingStage;
    private Sprite scrollBar[];
    private int anInt1026;
    private Sprite aClass30_Sub2_Sub1_Sub1_1027;
    private Sprite aClass30_Sub2_Sub1_Sub1_1028;
    private Sprite aClass30_Sub2_Sub1_Sub1_1029;
    private int anIntArray1030[];
    private boolean aBoolean1031;
    private static BigInteger aBigInteger1032 = new BigInteger("58778699976184461502525193738213253649000149147835990136706041084440742975821");
    private Sprite mapFunctions[];
    private int baseX;
    private int baseY;
    private int anInt1036;
    private int anInt1037;
    private int loginFailures;
    private int anInt1039;
    private int anInt1040;
    private int anInt1041;
    private int dialogID;
    private boolean aBoolean1043;
    private int maxStats[];
    private int anIntArray1045[];
    private int anInt1046;
    private boolean aBoolean1047;
    private int anInt1048;
    private String aString1049;
    private int anInt1050;
    private static int anInt1051;
    private int anIntArray1052[];
    private StreamLoader aClass44_1053;
    private int flashingSidebarId;
    private int multiOverlayIconID;
    private NodeList aClass19_1056;
    private int anIntArray1057[];
    private int anInt1058;
    public static RSInterface chatBoxScrollbar;
    private Background mapScenes[];
    static int drawCycle;
    private int anInt1062;
    private int anInt1063;
    private int anInt1064;
    private int anIntArray1065[];
    private int mouseInvInterfaceIndex;
    private int lastActiveInvInterface;
    private OnDemandFetcher aClass42_Sub1_1068;
    private int anInt1069;
    private int anInt1070;
    private int anInt1071;
    private int anIntArray1072[];
    private int anIntArray1073[];
    private Sprite mapDotItem;
    private Sprite mapDotNPC;
    private Sprite mapDotPlayer;
    private Sprite mapDotFriend;
    private Sprite mapDotTeam;
    private int anInt1079;
    private boolean aBoolean1080;
    private int anInt1081;
    private String friendsList[];
    private Stream aClass30_Sub2_Sub2_1083;
    private int anInt1084;
    private int anInt1085;
    private int activeInterfaceType;
    private int anInt1087;
    private int anInt1088;
    public static int scrollableChatArea;
    private int anIntArray1090[];
    private int menuActionCmd2[];
    private int menuActionCmd3[];
    private int menuActionID[];
    private int menuActionCmd1[];
    private Sprite headIcons[];
    private static int anInt1096 = -192;
    private static int anInt1097;
    private int anInt1098;
    private int anInt1099;
    private int anInt1100;
    private int anInt1101;
    private int anInt1102;
    private static boolean tabAreaAltered;
    private int updateTime;
    private int anInt1105;
    private boolean aBoolean1106;
    private RSImageProducer aClass15_1107;
    private RSImageProducer aClass15_1108;
    private RSImageProducer loginArea;
    private RSImageProducer aClass15_1110;
    private RSImageProducer aClass15_1111;
    private RSImageProducer aClass15_1112;
    private RSImageProducer aClass15_1113;
    private RSImageProducer aClass15_1114;
    private RSImageProducer aClass15_1115;
    private int anInt1116;
    private static int anInt1117;
    private int anInt1118;
    private int anInt1119;
    private int membersInt;
    private String aString1121;
    private Sprite compass;
    private RSImageProducer aClass15_1123;
    private RSImageProducer aClass15_1124;
    private RSImageProducer aClass15_1125;
    static Player aClass30_Sub2_Sub4_Sub1_Sub2_1126;
    private String atPlayerActions[];
    private boolean atPlayerArray[];
    private int anIntArrayArrayArray1129[][][];
    private int anInt1131;
    private int anInt1132;
    private int menuActionRow;
    private static int anInt1134;
    private int anInt1135;
    private int anInt1136;
    private int anInt1137;
    private int anInt1138;
    String aString1139;
    private Sprite aClass30_Sub2_Sub1_Sub1Array1140[];
    private boolean aBoolean1141;
    private static int anInt1142;
    private Sprite aClass30_Sub2_Sub1_Sub1_1143;
    private Sprite aClass30_Sub2_Sub1_Sub1_1144;
    private Background aClass30_Sub2_Sub1_Sub2_1145;
    private Sprite aClass30_Sub2_Sub1_Sub1_1146;
    private Sprite aClass30_Sub2_Sub1_Sub1_1147;
    private int energy = 100;
    private boolean isInterfaceOpen;
    private Sprite crosses[];
    private boolean musicEnabled;
    private Background aClass30_Sub2_Sub1_Sub2Array1152[];
    private static boolean needDrawTabArea;
    private int unreadMessages;
    private static int anInt1155;
    static boolean aBoolean1156;
    public static boolean loggedIn;
    private boolean canMute;
    private boolean aBoolean1159;
    private boolean aBoolean1160;
    static int loopCycle;
    private static String goodLoginChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_. ";
    private RSImageProducer aClass15_1163;
    private RSImageProducer aClass15_1164;
    private RSImageProducer aClass15_1165;
    private RSImageProducer aClass15_1166;
    private int daysSinceRecovChange;
    private RSSocket aClass24_1168;
    private int anInt1169;
    private int minimapInt3;
    private int anInt1171;
    private long aLong1172;
    private String myUsername;
    private String myPassword;
    public static String server;
    public static String port;
    private static int aPort;
    private static int anInt1175;
    private boolean genericLoadingError;
    private int anInt1178;
    private NodeList aClass19_1179;
    private int anIntArray1180[];
    private int anIntArray1181[];
    private int anIntArray1182[];
    private byte aByteArrayArray1183[][];
    private int anInt1184;
    private int anInt1185;
    private int anInt1186;
    private int anInt1187;
    private static int anInt1188;
    private int invOverlayInterfaceID;
    private int anIntArray1190[];
    private int anIntArray1191[];
    private Stream aClass30_Sub2_Sub2_1192;
    private int anInt1193;
    private byte aByte1194;
    private int anInt1195;
    private Sprite aClass30_Sub2_Sub1_Sub1_1196;
    private Background aClass30_Sub2_Sub1_Sub2_1197;
    private Sprite aClass30_Sub2_Sub1_Sub1_1198;
    private String menuActionName[];
    private static byte aByte1200 = 9;
    private Sprite aClass30_Sub2_Sub1_Sub1_1201;
    private Sprite aClass30_Sub2_Sub1_Sub1_1202;
    private int anIntArray1203[];
    public static boolean flagged;
    private boolean aBoolean1206;
    private int anIntArray1207[];
    private int flameLoopCycle;
    private int minimapInt2;
    private int anInt1210;
    public static int scrollPos;
    private String aString1212;
    private int anInt1213;
    private int intGroundArray[][][];
    private long aLong1215;
    private int anInt1216;
    private byte aByte1217;
    private int anInt1218;
    private Sprite modIcons[];
    long aLong1220;
    public static int tabID;
    private int anInt1222;
    public static boolean inputTaken;
    private static boolean aBoolean1224 = true;
    private int inputDialogState;
    private static int anInt1226;
    private int nextSong;
    private boolean songChanging;
    private int anIntArray1229[];
    public Class11 aClass11Array1230[];
    private static boolean aBoolean1231;
    public static int anIntArray1232[];
    public static boolean needChatAreaRedraw;
    private int mapArea[];
    private int mapId[];
    private int objectFileID[];
    int anInt1237;
    int anInt1238;
    private final int anInt1239 = 100;
    private int anIntArray1240[];
    private int anIntArray1241[];
    private boolean aBoolean1242;
    private int atInventoryLoopCycle;
    private int anInt1244;
    private int anInt1245;
    private int atInventoryInterfaceType;
    private byte aByteArrayArray1247[][];
    private int tradeMode;
    private int anInt1249;
    private int anIntArray1250[];
    private int anInt1251;
    private boolean rsAlreadyLoaded;
    private int anInt1253;
    private int anInt1254;
    private boolean welcomeScreenRaised;
    private boolean messagePromptRaised;
    private int anInt1257;
    private byte byteGroundArray[][][];
    private int prevSong;
    private static int anInt1260;
    private int destX;
    private int destY;
    private Sprite aClass30_Sub2_Sub1_Sub1_1263;
    private int anInt1264;
    private int anInt1265;
    private String loginMessage1;
    private String loginMessage2;
    private int anInt1268;
    private int anInt1269;
    private TextDrawingClass smallFont; //1270
    private TextDrawingClass regularFont; //1271
    private TextDrawingClass boldFont; //1272
    private TextDrawingClass fancyFont; //1273
    private byte aByte1274;
    private int anInt1275;
    private int backDialogID;
    private boolean aBoolean1277;
    private int anInt1278;
    private int anInt1279;
    private int anIntArray1280[];
    private int anIntArray1281[];
    private int anInt1282;
    private int anInt1283;
    private int anInt1284;
    private int anInt1285;
    String aString1286;
    private int publicChatMode;
    private static int anInt1288;
    private int anInt1289;
    public static int anInt1290;
    public static int showframeids;
    private int anIntArray965[] = {
        0xffff00, 0xff0000, 65280, 65535, 0xff00ff, 0xd3c3a6
    };
    static final int anIntArrayArray1003[][] = {
        {
            6798, 107, 10283, 16, 4797, 7744, 5799, 4634, 33697, 22433, 2983, 54193
        }, {
            8741, 12, 64030, 43162, 7735, 8404, 1701, 38430, 24094, 10153, 56621, 4783, 1341, 16578, 35003, 25239
        }, {
            25238, 8742, 12, 64030, 43162, 7735, 8404, 1701, 38430, 24094, 10153, 56621, 4783, 1341, 16578, 35003
        }, {
            4626, 11146, 6439, 12, 4758, 10270
        }, {
            4550, 4537, 5681, 5673, 5790, 6806, 8076, 4574
        }
    };
    public static int tabInterfaceIDs[] = {
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1
    };
    private final int anIntArray1177[] = {
        0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2,
        2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3
    };
    static final int anIntArray1204[] = {
        9104, 10275, 7595, 3610, 7975, 8526, 918, 38802, 24466, 10145,
        58654, 5027, 1457, 16565, 34991, 25486
    };

    static {
        anIntArray1019 = new int[99];
        int i = 0;
        for (int j = 0; j < 99; j++) {
            int l = j + 1;
            int i1 = (int) ((double) l + 300D * Math.pow(2D, (double) l / 7D));
            i += i1;
            anIntArray1019[j] = i / 4;
        }
        anIntArray1232 = new int[32];
        i = 2;
        for (int k = 0; k < 32; k++) {
            anIntArray1232[k] = i - 1;
            i += i;
        }

    }
}
/////////////////////////////////
///////////E    O    F///////////
/////////////////////////////////