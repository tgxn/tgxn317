package client;

public class Class48 implements Runnable {

    public client clientInstance;
    public Object object;
    public int anIntArray807[];
    public boolean aBoolean808;
    public int anIntArray809[];
    public int anInt810;
    
    public Class48(client client1, int i) {
        object = new Object();
        anIntArray807 = new int[500];
        aBoolean808 = true;
        for (anIntArray809 = new int[500]; i >= 0;) {
            throw new NullPointerException();
        }

        clientInstance = client1;
    }
    
    @Override
    public void run() {
        while (aBoolean808) {
            synchronized (object) {
                if (anInt810 < 500) {
                    anIntArray809[anInt810] = ((Applet_Sub1) (clientInstance)).anInt20;
                    anIntArray807[anInt810] = ((Applet_Sub1) (clientInstance)).anInt21;
                    anInt810++;
                }
            }
            try {
                Thread.sleep(50L);
            } catch (Exception _ex) {
            }
        }
    }
}
