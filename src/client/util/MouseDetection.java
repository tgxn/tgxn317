package client.util;

// Fully Renamed.

import client.Client;
import client.instance.GameApplet;


public class MouseDetection implements Runnable {

    public Client clientInstance;
    public Object syncObject;
    public int coordsY[];
    public boolean running;
    public int coordsX[];
    public int coordsIndex;
    
    public MouseDetection(Client client) {
        clientInstance = client;
        syncObject = new Object();
        coordsX = new int[500];
        coordsY = new int[500];
        running = true;
    }
    
    @Override
    public void run() {
        while (running) {
            synchronized (syncObject) {
                if (coordsIndex < 500) {
                    coordsX[coordsIndex] = ((GameApplet) (clientInstance)).mouseEventX;
                    coordsY[coordsIndex] = ((GameApplet) (clientInstance)).mouseEventY;
                    coordsIndex++;
                }
            }
            try {
                Thread.sleep(50L);
            } catch (Exception _ex) {
            }
        }
    }
}
