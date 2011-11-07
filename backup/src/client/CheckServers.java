package client;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class CheckServers implements Runnable {
    
    public String serverList[][];
    public boolean serverStatus[];
    public int playersOnline[];
    public boolean isLoading[];

    public boolean getPingTime(String sIP, int sPort) {
        try {
            if (checkServerUp(sIP, sPort)) {
                return true;
            } else {
                return false;
            }
        } catch (IOException ioe) {
            return false;
        }
    }
    
    private int getOnlinePlayers(String sIP, int sPort) {
        try {
            return getPlayersForServer(sIP, sPort);
        } catch (IOException ioe) {
            return -1;
        }
    }
    
    public int getPlayersForServer(String host, int port) throws IOException {
        
        // We need to do some tricky shit with the server and packets here.
        
        return -1;
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
        serverStatus = new boolean[serverList.length];
        playersOnline = new int[serverList.length];
        isLoading = new boolean[serverList.length];
        for (int i = 0; i < serverList.length; i++) {
            isLoading[i] = true;
            serverStatus[i] = false;
            playersOnline[i] = -1;
        }
        for (int j = 0; j < serverList.length; j++) {
            String sIP = serverList[j][4];
            int sPort = Integer.parseInt(serverList[j][5]);
            serverStatus[j] = getPingTime(sIP, sPort);
            getOnlinePlayers(sIP, sPort);
            isLoading[j] = false;
        }
    }
}
