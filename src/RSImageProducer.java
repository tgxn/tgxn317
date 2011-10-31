
import java.awt.*;
import java.awt.image.*;

public final class RSImageProducer implements ImageProducer, ImageObserver {
    
    private boolean aBoolean314;
    public int anIntArray315[];
    public int anInt316;
    public int anInt317;
    ColorModel aColorModel318;
    ImageConsumer anImageConsumer319;
    public Image anImage320;
    
    public RSImageProducer(int i, int j, Component component, int k) {
        aBoolean314 = true;
        anInt316 = i;
        anInt317 = j;
        anIntArray315 = new int[i * j];
        aColorModel318 = new DirectColorModel(32, 0xff0000, 65280, 255);
        anImage320 = component.createImage(this);
        method239();
        component.prepareImage(anImage320, this);
        if (k != 0) {
            aBoolean314 = !aBoolean314;
        }
        method239();
        component.prepareImage(anImage320, this);
        method239();
        component.prepareImage(anImage320, this);
        method237(0);
    }

    public void method237(int i) {
        DrawingArea.initDrawingArea(anInt317, anInt316, anIntArray315);
    }

    public void drawGraphics(int i, int j, Graphics g, int k) {
        method239();
        g.drawImage(anImage320, k, i, this);
    }

    @Override
    public synchronized void addConsumer(ImageConsumer imageconsumer) {
        anImageConsumer319 = imageconsumer;
        imageconsumer.setDimensions(anInt316, anInt317);
        imageconsumer.setProperties(null);
        imageconsumer.setColorModel(aColorModel318);
        imageconsumer.setHints(14);
    }

    @Override
    public synchronized boolean isConsumer(ImageConsumer imageconsumer) {
        return anImageConsumer319 == imageconsumer;
    }

    @Override
    public synchronized void removeConsumer(ImageConsumer imageconsumer) {
        if (anImageConsumer319 == imageconsumer) {
            anImageConsumer319 = null;
        }
    }

    @Override
    public void startProduction(ImageConsumer imageconsumer) {
        addConsumer(imageconsumer);
    }

    @Override
    public void requestTopDownLeftRightResend(ImageConsumer imageconsumer) {
        System.out.println("TDLR");
    }

    public synchronized void method239() {
        if (anImageConsumer319 == null) {
            return;
        } else {
            anImageConsumer319.setPixels(0, 0, anInt316, anInt317, aColorModel318, anIntArray315, 0, anInt316);
            anImageConsumer319.imageComplete(2);
            return;
        }
    }

    @Override
    public boolean imageUpdate(Image image, int i, int j, int k, int l, int i1) {
        return true;
    }
}
