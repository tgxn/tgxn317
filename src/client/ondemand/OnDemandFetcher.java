package client.ondemand;


import client.Client;
import client.node.NodeList;
import client.node.Queue;
import client.data.Stream;
import client.data.JagexArchive;
import client.custom.cSettings;
import java.io.*;
import java.net.Socket;
import java.util.zip.CRC32;
import java.util.zip.GZIPInputStream;
import client.sign.Signlink;

public class OnDemandFetcher extends OnDemandFetcherParent implements Runnable {
    
    private int totalFiles;
    private NodeList requested;
    private int anInt1332;
    public String statusString;
    private int writeLoopCycle;
    private long openSocketTime;
    private boolean aBoolean1336;
    private int mapIndices3[];
    private CRC32 crc32;
    private byte ioBuffer[];
    private int anInt1340;
    public int onDemandCycle;
    private byte fileStatus[][];
    private Client clientInstance;
    private NodeList aClass19_1344;
    private static int anInt1345;
    private int completedSize;
    private int expectedSize;
    private int anIntArray1348[];
    public int anInt1349;
    private int mapIndices2[];
    private int filesLoaded;
    private int anInt1352;
    private boolean running;
    private OutputStream outputStream;
    private boolean aBoolean1355;
    private int mapIndices4[];
    private boolean waiting;
    private NodeList aClass19_1358;
    private byte gzipInputBuffer[];
    private int anIntArray1360[];
    private Queue nodeSubList;
    private InputStream inputStream;
    private Socket socket;
    private int versions[][];
    private int crcs[][];
    private int uncompletedCount;
    private int completedCount;
    private NodeList aClass19_1368;
    private OnDemandData current;
    private NodeList aClass19_1370;
    private int mapIndices1[];
    private byte modelIndices[];
    private int loopCycle;
    
    public OnDemandFetcher() {
        requested = new NodeList();
        statusString = "";
        aBoolean1336 = true;
        crc32 = new CRC32();
        ioBuffer = new byte[500];
        anInt1340 = 923;
        fileStatus = new byte[4][];
        aClass19_1344 = new NodeList();
        anInt1352 = 13603;
        running = true;
        aBoolean1355 = false;
        waiting = false;
        aClass19_1358 = new NodeList();
        gzipInputBuffer = new byte[65000];
        nodeSubList = new Queue();
        versions = new int[4][];
        crcs = new int[4][];
        aClass19_1368 = new NodeList();
        aClass19_1370 = new NodeList();
    }
    
    @Override
    public final void run() {
        try {
            while (running) {
                onDemandCycle++;
                int i = 20;
                if (anInt1332 == 0 && clientInstance.jagexFileStores[0] != null) {
                    i = 50;
                }
                try {
                    Thread.sleep(i);
                } catch (Exception _ex) {
                }
                waiting = true;
                for (int j = 0; j < 100; j++) {
                    if (!waiting) {
                        break;
                    }
                    waiting = false;
                    checkRecieved(true);
                    handleFailed(false);
                    if (uncompletedCount == 0 && j >= 5) {
                        break;
                    }
                    method568((byte) -56);
                    if (inputStream != null) {
                        readData();
                    }
                }

                boolean flag = false;
                for (OnDemandData class30_sub2_sub3 = (OnDemandData) requested.reverseGetFirst(); class30_sub2_sub3 != null; class30_sub2_sub3 = (OnDemandData) requested.reverseGetNext()) {
                    if (class30_sub2_sub3.incomplete) {
                        flag = true;
                        class30_sub2_sub3.loopCycle++;
                        if (class30_sub2_sub3.loopCycle > 50) {
                            class30_sub2_sub3.loopCycle = 0;
                            closeRequest(class30_sub2_sub3);
                        }
                    }
                }

                if (!flag) {
                    for (OnDemandData class30_sub2_sub3_1 = (OnDemandData) requested.reverseGetFirst(); class30_sub2_sub3_1 != null; class30_sub2_sub3_1 = (OnDemandData) requested.reverseGetNext()) {
                        flag = true;
                        class30_sub2_sub3_1.loopCycle++;
                        if (class30_sub2_sub3_1.loopCycle > 50) {
                            class30_sub2_sub3_1.loopCycle = 0;
                            closeRequest(class30_sub2_sub3_1);
                        }
                    }

                }
                if (flag) {
                    loopCycle++;
                    if (loopCycle > 750) {
                        try {
                            socket.close();
                        } catch (Exception _ex) {
                        }
                        socket = null;
                        inputStream = null;
                        outputStream = null;
                        expectedSize = 0;
                    }
                } else {
                    loopCycle = 0;
                    statusString = "";
                }
                if (Client.loggedIn && socket != null && outputStream != null && (anInt1332 > 0 || clientInstance.jagexFileStores[0] == null)) {
                    writeLoopCycle++;
                    if (writeLoopCycle > 500) {
                        writeLoopCycle = 0;
                        ioBuffer[0] = 0;
                        ioBuffer[1] = 0;
                        ioBuffer[2] = 0;
                        ioBuffer[3] = 10;
                        try {
                            outputStream.write(ioBuffer, 0, 4);
                        } catch (IOException _ex) {
                            loopCycle = 5000;
                        }
                    }
                }
            }
            return;
        } catch (Exception exception) {
            Signlink.reportError("od_ex " + exception.getMessage());
        }
    }
    
    private boolean crcMatches(int i, int j, byte abyte0[]) {
        if (abyte0 == null || abyte0.length < 2) {
            return false;
        }
        int k = abyte0.length - 2;
        int l = ((abyte0[k] & 0xff) << 8) + (abyte0[k + 1] & 0xff);
        crc32.reset();
        crc32.update(abyte0, 0, k);
        int i1 = (int) crc32.getValue();
        if (l != i) {
            return false;
        }
        return i1 == j;
    }

    private void readData() {
        try {
            int j = inputStream.available();
            if (expectedSize == 0 && j >= 6) {
                waiting = true;
                for (int k = 0; k < 6; k += inputStream.read(ioBuffer, k, 6 - k));
                int l = ioBuffer[0] & 0xff;
                int j1 = ((ioBuffer[1] & 0xff) << 8) + (ioBuffer[2] & 0xff);
                int l1 = ((ioBuffer[3] & 0xff) << 8) + (ioBuffer[4] & 0xff);
                int i2 = ioBuffer[5] & 0xff;
                current = null;
                for (OnDemandData class30_sub2_sub3 = (OnDemandData) requested.reverseGetFirst(); class30_sub2_sub3 != null; class30_sub2_sub3 = (OnDemandData) requested.reverseGetNext()) {
                    if (class30_sub2_sub3.dataType == l && class30_sub2_sub3.ID == j1) {
                        current = class30_sub2_sub3;
                    }
                    if (current != null) {
                        class30_sub2_sub3.loopCycle = 0;
                    }
                }
                if (current != null) {
                    loopCycle = 0;
                    if (l1 == 0) {
                        Signlink.reportError("Rej: " + l + "," + j1);
                        current.buffer = null;
                        if (current.incomplete) {
                            synchronized (aClass19_1358) {
                                aClass19_1358.insertHead(current);
                            }
                        } else {
                            current.unlink();
                        }
                        current = null;
                    } else {
                        if (current.buffer == null && i2 == 0) {
                            current.buffer = new byte[l1];
                        }
                        if (current.buffer == null && i2 != 0) {
                            throw new IOException("missing start of file");
                        }
                    }
                }
                completedSize = i2 * 500;
                expectedSize = 500;
                if (expectedSize > l1 - i2 * 500) {
                    expectedSize = l1 - i2 * 500;
                }
            }
            if (expectedSize > 0 && j >= expectedSize) {
                waiting = true;
                byte abyte0[] = ioBuffer;
                int i1 = 0;
                if (current != null) {
                    abyte0 = current.buffer;
                    i1 = completedSize;
                }
                for (int k1 = 0; k1 < expectedSize; k1 += inputStream.read(abyte0, k1 + i1, expectedSize - k1));
                if (expectedSize + completedSize >= abyte0.length && current != null) {
                    if (clientInstance.jagexFileStores[0] != null) {
                        clientInstance.jagexFileStores[current.dataType + 1].put(abyte0.length, abyte0, current.ID);
                    }
                    if (!current.incomplete && current.dataType == 3) {
                        current.incomplete = true;
                        current.dataType = 93;
                    }
                    if (current.incomplete) {
                        synchronized (aClass19_1358) {
                            aClass19_1358.insertHead(current);
                        }
                    } else {
                        current.unlink();
                    }
                }
                expectedSize = 0;
                return;
            }
        } catch (IOException ioe) {
            try {
                socket.close();
            } catch (Exception _ex) {
            }
            socket = null;
            inputStream = null;
            outputStream = null;
            expectedSize = 0;
        }
    }

    public final void start(JagexArchive class44, Client client) {
        String as[] = {
            "model_version", "anim_version", "midi_version", "map_version"
        };
        for (int i = 0; i < 4; i++) {
            byte abyte0[] = class44.getDataForName(as[i]);
            int j = abyte0.length / 2;
            Stream class30_sub2_sub2 = new Stream(abyte0);
            versions[i] = new int[j];
            fileStatus[i] = new byte[j];
            for (int l = 0; l < j; l++) {
                versions[i][l] = class30_sub2_sub2.readUnsignedWord();
            }
        }
        String as1[] = {
            "model_crc", "anim_crc", "midi_crc", "map_crc"
        };
        for (int k = 0; k < 4; k++) {
            byte abyte1[] = class44.getDataForName(as1[k]);
            int i1 = abyte1.length / 4;
            Stream class30_sub2_sub2_1 = new Stream(abyte1);
            crcs[k] = new int[i1];
            for (int l1 = 0; l1 < i1; l1++) {
                crcs[k][l1] = class30_sub2_sub2_1.readDWord();
            }

        }
        byte abyte2[] = class44.getDataForName("model_index");
        int j1 = versions[0].length;
        modelIndices = new byte[j1];
        for (int k1 = 0; k1 < j1; k1++) {
            if (k1 < abyte2.length) {
                modelIndices[k1] = abyte2[k1];
            } else {
                modelIndices[k1] = 0;
            }
        }
        abyte2 = class44.getDataForName("map_index");
        Stream class30_sub2_sub2_2 = new Stream(abyte2);
        j1 = abyte2.length / 7;
        mapIndices1 = new int[j1];
        mapIndices2 = new int[j1];
        mapIndices3 = new int[j1];
        mapIndices4 = new int[j1];
        for (int i2 = 0; i2 < j1; i2++) {
            mapIndices1[i2] = class30_sub2_sub2_2.readUnsignedWord();
            mapIndices2[i2] = class30_sub2_sub2_2.readUnsignedWord();
            mapIndices3[i2] = class30_sub2_sub2_2.readUnsignedWord();
            mapIndices4[i2] = class30_sub2_sub2_2.readUnsignedByte();
        }
        abyte2 = class44.getDataForName("anim_index");
        class30_sub2_sub2_2 = new Stream(abyte2);
        j1 = abyte2.length / 2;
        anIntArray1360 = new int[j1];
        for (int j2 = 0; j2 < j1; j2++) {
            anIntArray1360[j2] = class30_sub2_sub2_2.readUnsignedWord();
        }

        abyte2 = class44.getDataForName("midi_index");
        class30_sub2_sub2_2 = new Stream(abyte2);
        j1 = abyte2.length;
        anIntArray1348 = new int[j1];
        for (int k2 = 0; k2 < j1; k2++) {
            anIntArray1348[k2] = class30_sub2_sub2_2.readUnsignedByte();
        }
        clientInstance = client;
        running = true;
        clientInstance.startRunnable(this, 2);
    }

    public final int getNodeCount() {
        synchronized (nodeSubList) {
            int i = nodeSubList.getNodeCount();
            return i;
        }
    }

    public final void Disable() {
        running = false;
    }

    public final void method554(boolean flag, int i) {
        int j = mapIndices1.length;
        if (i != 0) {
            anInt1345 = 20;
        }
        for (int k = 0; k < j; k++) {
            if (flag || mapIndices4[k] != 0) {
                method563((byte) 2, 3, mapIndices3[k], (byte) 8);
                method563((byte) 2, 3, mapIndices2[k], (byte) 8);
            }
        }

    }

    public final int getVersionCount(int i, int j) {
        if (i <= 0) {
            aBoolean1355 = !aBoolean1355;
        }
        int returnable = versions[j].length;
        return returnable;
    }

    private void closeRequest(OnDemandData onDemandData) {
        try {
            if (socket == null) {
                long l = System.currentTimeMillis();
                if (l - openSocketTime < 4000L) {
                    return;
                }
                openSocketTime = l;
                socket = clientInstance.openSocket(Integer.parseInt(cSettings.port));
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
                outputStream.write(15);
                for (int j = 0; j < 8; j++) {
                    inputStream.read();
                }

                loopCycle = 0;
            }
            ioBuffer[0] = (byte) onDemandData.dataType;
            ioBuffer[1] = (byte) (onDemandData.ID >> 8);
            ioBuffer[2] = (byte) onDemandData.ID;
            if (onDemandData.incomplete) {
                ioBuffer[3] = 2;
            } else if (!clientInstance.loggedIn) {
                ioBuffer[3] = 1;
            } else {
                ioBuffer[3] = 0;
            }
            outputStream.write(ioBuffer, 0, 4);
            writeLoopCycle = 0;
            anInt1349 = -10000;
            return;
        } catch (IOException ioe) {
        }
        try {
            socket.close();
        } catch (Exception _ex) {
        }
        socket = null;
        inputStream = null;
        outputStream = null;
        expectedSize = 0;
        anInt1349++;
    }

    public final int getAnimCount() {
        return anIntArray1360.length;
    }

    public final void loadToCache(int i, int j) {
        if (i < 0 || i > versions.length || j < 0 || j > versions[i].length) {
            return;
        }
        if (versions[i][j] == 0) {
            return;
        }
        synchronized (nodeSubList) {
            for (OnDemandData class30_sub2_sub3 = (OnDemandData) nodeSubList.reverseGetFirst(); class30_sub2_sub3 != null; class30_sub2_sub3 = (OnDemandData) nodeSubList.reverseGetNext()) {
                if (class30_sub2_sub3.dataType == i && class30_sub2_sub3.ID == j) {
                    return;
                }
            }

            OnDemandData class30_sub2_sub3_1 = new OnDemandData();
            class30_sub2_sub3_1.dataType = i;
            class30_sub2_sub3_1.ID = j;
            class30_sub2_sub3_1.incomplete = true;
            synchronized (aClass19_1370) {
                aClass19_1370.insertHead(class30_sub2_sub3_1);
            }
            nodeSubList.insertHead(class30_sub2_sub3_1);
        }
    }

    public final int getModelIndex(int i) {
        return modelIndices[i] & 0xff;
    }

    public final void method560(int i, int j, boolean flag) {
        if (clientInstance.jagexFileStores[0] == null) {
            return;
        }
        if (versions[j][i] == 0) {
            return;
        }
        if (fileStatus[j][i] == 0) {
            return;
        }
        if (anInt1332 == 0) {
            return;
        }
        OnDemandData class30_sub2_sub3 = new OnDemandData();
        class30_sub2_sub3.dataType = j;
        class30_sub2_sub3.ID = i;
        if (flag) {
            anInt1345 = -423;
        }
        class30_sub2_sub3.incomplete = false;
        synchronized (aClass19_1344) {
            aClass19_1344.insertHead(class30_sub2_sub3);
        }
    }

    public final OnDemandData getNextNode() {
        OnDemandData class30_sub2_sub3;
        synchronized (aClass19_1358) {
            class30_sub2_sub3 = (OnDemandData) aClass19_1358.popHead();
        }
        if (class30_sub2_sub3 == null) {
            return null;
        }
        synchronized (nodeSubList) {
            class30_sub2_sub3.unlinkSub();
        }
        if (class30_sub2_sub3.buffer == null) {
            return class30_sub2_sub3;
        }
        int i = 0;
        try {
            GZIPInputStream gzipinputstream = new GZIPInputStream(new ByteArrayInputStream(class30_sub2_sub3.buffer));
            do {
                if (i == gzipInputBuffer.length) {
                    throw new RuntimeException("buffer overflow!");
                }
                int k = gzipinputstream.read(gzipInputBuffer, i, gzipInputBuffer.length - i);
                if (k == -1) {
                    break;
                }
                i += k;
            } while (true);
        } catch (IOException _ex) {
            throw new RuntimeException("error unzipping");
        }
        class30_sub2_sub3.buffer = new byte[i];
        for (int j = 0; j < i; j++) {
            class30_sub2_sub3.buffer[j] = gzipInputBuffer[j];
        }

        return class30_sub2_sub3;
    }

    public final int getMapIndex(int i, int j, int k, int l) {
        if (j != 0) {
            return anInt1345;
        }
        int i1 = (l << 8) + k;
        for (int j1 = 0; j1 < mapIndices1.length; j1++) {
            if (mapIndices1[j1] == i1) {
                if (i == 0) {
                    return mapIndices2[j1];
                } else {
                    return mapIndices3[j1];
                }
            }
        }

        return -1;
    }

    @Override
    public final void requestData(int i) {
        loadToCache(0, i);
    }

    public final void method563(byte byte0, int i, int j, byte byte1) {
        if (byte1 == 8) {
            byte1 = 0;
        } else {
            anInt1340 = 237;
        }
        if (clientInstance.jagexFileStores[0] == null) {
            return;
        }
        if (versions[i][j] == 0) {
            return;
        }
        byte abyte0[] = clientInstance.jagexFileStores[i + 1].decompressFile(j);
        if (byte0 > anInt1332) {
            anInt1332 = byte0;
        }
        totalFiles++;
    }

    public final boolean method564(int i, int j) {
        while (j >= 0) {
            throw new NullPointerException();
        }
        for (int k = 0; k < mapIndices1.length; k++) {
            if (mapIndices3[k] == i) {
                return true;
            }
        }

        return false;
    }

    private final void handleFailed(boolean flag) {
        uncompletedCount = 0;
        if (flag) {
            return;
        }
        completedCount = 0;
        for (OnDemandData class30_sub2_sub3 = (OnDemandData) requested.reverseGetFirst(); class30_sub2_sub3 != null; class30_sub2_sub3 = (OnDemandData) requested.reverseGetNext()) {
            if (class30_sub2_sub3.incomplete) {
                uncompletedCount++;
            } else {
                completedCount++;
            }
        }

        while (uncompletedCount < 10) {
            OnDemandData class30_sub2_sub3_1 = (OnDemandData) aClass19_1368.popHead();
            if (class30_sub2_sub3_1 == null) {
                break;
            }
            if (fileStatus[class30_sub2_sub3_1.dataType][class30_sub2_sub3_1.ID] != 0) {
                filesLoaded++;
            }
            fileStatus[class30_sub2_sub3_1.dataType][class30_sub2_sub3_1.ID] = 0;
            requested.insertHead(class30_sub2_sub3_1);
            uncompletedCount++;
            closeRequest(class30_sub2_sub3_1);
            waiting = true;
        }
    }

    public final void method566(int i) {
        if (i != 0) {
            for (int j = 1; j > 0; j++);
        }
        synchronized (aClass19_1344) {
            aClass19_1344.RemoveAll();
        }
    }

    private final void checkRecieved(boolean flag) {
        if (!flag) {
            return;
        }
        OnDemandData class30_sub2_sub3;
        synchronized (aClass19_1370) {
            class30_sub2_sub3 = (OnDemandData) aClass19_1370.popHead();
        }
        while (class30_sub2_sub3 != null) {
            waiting = true;
            byte abyte0[] = null;
            if (clientInstance.jagexFileStores[0] != null) {
                abyte0 = clientInstance.jagexFileStores[class30_sub2_sub3.dataType + 1].decompressFile(class30_sub2_sub3.ID);
            }
            //   if(!method549(anIntArrayArray1364[class30_sub2_sub3.dataType][class30_sub2_sub3.ID], (byte)3, anIntArrayArray1365[class30_sub2_sub3.dataType][class30_sub2_sub3.ID], abyte0))
            // abyte0 = null;
            synchronized (aClass19_1370) {
                if (abyte0 == null) {
                    aClass19_1368.insertHead(class30_sub2_sub3);
                } else {
                    class30_sub2_sub3.buffer = abyte0;
                    synchronized (aClass19_1358) {
                        aClass19_1358.insertHead(class30_sub2_sub3);
                    }
                }
                class30_sub2_sub3 = (OnDemandData) aClass19_1370.popHead();
            }
        }
    }

    private final void method568(byte byte0) {
        if (byte0 != -56) {
            for (int i = 1; i > 0; i++);
        }
        while (uncompletedCount == 0 && completedCount < 10) {
            if (anInt1332 == 0) {
                break;
            }
            OnDemandData class30_sub2_sub3;
            synchronized (aClass19_1344) {
                class30_sub2_sub3 = (OnDemandData) aClass19_1344.popHead();
            }
            while (class30_sub2_sub3 != null) {
                if (fileStatus[class30_sub2_sub3.dataType][class30_sub2_sub3.ID] != 0) {
                    fileStatus[class30_sub2_sub3.dataType][class30_sub2_sub3.ID] = 0;
                    requested.insertHead(class30_sub2_sub3);
                    closeRequest(class30_sub2_sub3);
                    waiting = true;
                    if (filesLoaded < totalFiles) {
                        filesLoaded++;
                    }
                    statusString = "Loading extra files - " + (filesLoaded * 100) / totalFiles + "%";
                    completedCount++;
                    if (completedCount == 10) {
                        return;
                    }
                }
                synchronized (aClass19_1344) {
                    class30_sub2_sub3 = (OnDemandData) aClass19_1344.popHead();
                }
            }
            for (int j = 0; j < 4; j++) {
                byte abyte0[] = fileStatus[j];
                int k = abyte0.length;
                for (int l = 0; l < k; l++) {
                    if (abyte0[l] == anInt1332) {
                        abyte0[l] = 0;
                        OnDemandData class30_sub2_sub3_1 = new OnDemandData();
                        class30_sub2_sub3_1.dataType = j;
                        class30_sub2_sub3_1.ID = l;
                        class30_sub2_sub3_1.incomplete = false;
                        requested.insertHead(class30_sub2_sub3_1);
                        closeRequest(class30_sub2_sub3_1);
                        waiting = true;
                        if (filesLoaded < totalFiles) {
                            filesLoaded++;
                        }
                        statusString = "Loading extra files - " + (filesLoaded * 100) / totalFiles + "%";
                        completedCount++;
                        if (completedCount == 10) {
                            return;
                        }
                    }
                }

            }

            anInt1332--;
        }
    }

    public final boolean method569(int i, int j) {
        if (j != 5) {
            anInt1345 = 169;
        }
        return anIntArray1348[i] == 1;
    }
}
