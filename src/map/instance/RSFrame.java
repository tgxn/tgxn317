package map.instance;

// Fully Renamed - gamerx - 14.11.11

import java.applet.Applet;
import java.awt.*;

public class RSFrame extends Frame {
    
    public Applet rsApplet;
    
    public RSFrame(Applet rsApplet1, int width, int height) {
        rsApplet = rsApplet1;
        setTitle("World Map");
        setResizable(false);
        Image icon = Toolkit.getDefaultToolkit().getImage(client.sign.Signlink.findCacheDIR() + client.custom.cSettings.mainIconLocation);
        setIconImage(icon);
        setVisible(true);
        toFront();
        setSize(width + 8, height + 28);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(screenSize.width / 2 - (width + 8) / 2, screenSize.height / 2 - (height + 28) / 2, width + 8, height + 28);
    }

    @Override
    public void paint(Graphics g) {
        rsApplet.paint(g);
    }

    @Override
    public void update(Graphics g) {
        rsApplet.update(g);
    }

    @Override
    public Graphics getGraphics() {
        Graphics graphics = super.getGraphics();
        graphics.translate(4, 24);
        return graphics;
    }
}
