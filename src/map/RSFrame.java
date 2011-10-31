package map;

import java.awt.*;

public class RSFrame extends Frame {
    
    public RSApplet rsApplet;
    
    public RSFrame(RSApplet rsApplet1, int width, int height) {
        rsApplet = rsApplet1;
        setTitle("-GaMeR X-'s Client World Map");
        setResizable(false);
        setBackground(Color.BLACK);
        setUndecorated(true);
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
        Graphics g = super.getGraphics();
        g.translate(4, 24);
        return g;
    }
}
