package client;

import client.drawing.DrawingArea;
import java.awt.*;
import java.awt.image.*;

public final class GraphicsBuffer implements ImageProducer, ImageObserver {
    
    public int areaPixels[];
    public int areaWidth;
    public int areaHeight;
    ColorModel colorModel;
    ImageConsumer imageConsumer;
    public Image image;
    
    public GraphicsBuffer(int i, int j, Component component) {
        areaWidth = i;
        areaHeight = j;
        areaPixels = new int[i * j];
        colorModel = new DirectColorModel(32, 0xff0000, 65280, 255);
        image = component.createImage(this);
        method239();
        component.prepareImage(image, this);
        method239();
        component.prepareImage(image, this);
        method239();
        component.prepareImage(image, this);
        initDrawingArea();
    }

    public void initDrawingArea() {
        DrawingArea.initDrawingArea(areaHeight, areaWidth, areaPixels);
    }

    public void drawGraphics(int x, int y, Graphics graphics) {
        method239();
        graphics.drawImage(image, x, y, this);
    }

    @Override
    public synchronized void addConsumer(ImageConsumer imageconsumer) {
        imageConsumer = imageconsumer;
        imageconsumer.setDimensions(areaWidth, areaHeight);
        imageconsumer.setProperties(null);
        imageconsumer.setColorModel(colorModel);
        imageconsumer.setHints(14);
    }

    @Override
    public synchronized boolean isConsumer(ImageConsumer imageconsumer) {
        return imageConsumer == imageconsumer;
    }

    @Override
    public synchronized void removeConsumer(ImageConsumer imageconsumer) {
        if (imageConsumer == imageconsumer) {
            imageConsumer = null;
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
        if (imageConsumer == null) {
            return;
        } else {
            imageConsumer.setPixels(0, 0, areaWidth, areaHeight, colorModel, areaPixels, 0, areaWidth);
            imageConsumer.imageComplete(2);
            return;
        }
    }

    @Override
    public boolean imageUpdate(Image image, int i, int j, int k, int l, int i1) {
        return true;
    }
}
