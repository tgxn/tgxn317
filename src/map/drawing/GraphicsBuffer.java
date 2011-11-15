package map.drawing;

// Fully Renamed - gamerx - 14.11.11

import java.awt.*;
import java.awt.image.*;

public class GraphicsBuffer implements ImageProducer, ImageObserver {
    
    private int[] componentPixels;
    private int canvasWidth;
    private int canvasHeight;
    private ColorModel colorModel;
    private ImageConsumer imageConsumer;
    private Image image;
    
    public GraphicsBuffer(int width, int height, Component component) {
        canvasWidth = width;
        canvasHeight = height;
        componentPixels = new int[width * height];
        colorModel = new DirectColorModel(32, 0xff0000, 65280, 255);
        image = component.createImage(this);
        sendToConsumer();
        component.prepareImage(image, this);
        initDrawingArea();
    }
        
    public void initDrawingArea() {
        DrawingArea.initializeDrawingArea(componentPixels, canvasWidth, canvasHeight);
    }
    
    public void drawGraphics(Graphics graphics, int i, int j) {
        sendToConsumer();
        graphics.drawImage(image, i, j, this);
    }

    public synchronized void sendToConsumer() {
        if (imageConsumer == null) {
            return;
        } else {
            imageConsumer.setPixels(0, 0, canvasWidth, canvasHeight, colorModel, componentPixels, 0, canvasWidth);
            imageConsumer.imageComplete(2);
            return;
        }
    }

    @Override
    public boolean imageUpdate(Image image, int i, int j, int k, int l, int i1) {
        return true;
    }

    @Override
    public void requestTopDownLeftRightResend(ImageConsumer imageconsumer) {
        System.out.println("TDLR");
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
    public synchronized void addConsumer(ImageConsumer consumer) {
        imageConsumer = consumer;
        consumer.setDimensions(canvasWidth, canvasHeight);
        consumer.setProperties(null);
        consumer.setColorModel(colorModel);
        consumer.setHints(14);
    }
}
