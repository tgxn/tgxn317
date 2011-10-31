import java.awt.*;
import javax.swing.*;
import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;


public class RSFrame extends Frame {

	public RSFrame(RSApplet rsApplet1, int width, int height) {
		rsApplet = rsApplet1;
		setTitle("-GaMeR X-'s Client World Map"); //title
		setResizable(false);
		setBackground(Color.BLACK);
		setUndecorated(true);
		setVisible(true);
		toFront();
		setSize(width + 8, height + 28);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(screenSize.width / 2 - (width + 8) / 2, screenSize.height / 2 - (height + 28) / 2, width + 8, height + 28);
	}

	public void paint(Graphics g) {
		rsApplet.paint(g);
	}

	public void update(Graphics g) {
		rsApplet.update(g);
	}

	public Graphics getGraphics() {
		Graphics g = super.getGraphics();
		g.translate(4, 24);
		return g;
	}

	public RSApplet rsApplet;
}
