package client;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class CheckWorldTime implements Runnable {
    
    public boolean[] worldPingTimes;
    public String serverList[][];

    public boolean[] getPingTimes() {
        for (int j = 0; j < serverList.length; j++) {

            String sIP = serverList[j][4];
            int sPort = Integer.parseInt(serverList[j][5]);
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
        return worldPingTimes;
    }
    
    
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

    @Override
    public void run() {
        serverList = Csettings.serverList;
        worldPingTimes = new boolean[serverList.length];
        for(int i = 0; i < serverList.length; i++)
            worldPingTimes[i] = false;
        getPingTimes();
    }
}
