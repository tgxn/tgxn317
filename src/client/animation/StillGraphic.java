package client.animation;

// Fully Renamed.

import client.Entity;
import client.Model;

public final class StillGraphic extends Entity {
    
    public int plane;
    public int z;
    public int y;
    public int worldZ;
    public int stillGraphicsLoopCycle;
    public boolean transformCompleted;
    private SpotAnim spotAnim;
    private int elapsedFrames;
    private int duration;
    
    public StillGraphic(int i, int currentTime, int k, int l, int i1, int j1, int k1, int l1) {
        transformCompleted = false;
        spotAnim = SpotAnim.cache[i1];
        plane = i;
        z = l1;
        y = k1;
        worldZ = j1;
        stillGraphicsLoopCycle = currentTime + l;
    }

    @Override
    public Model getRotatedModel() {
        Model originalModel = spotAnim.getModel();
        if (originalModel == null) {
            return null;
        }
        int frameID = spotAnim.animationSequence.animationFrameID2[elapsedFrames];
        Model rotatedModel = new Model(9, true, AnimationFrame.isNullFrame(frameID), false, originalModel);
        if (!transformCompleted) {
            rotatedModel.createBones((byte) -71);
            rotatedModel.applyTransform(frameID, 40542);
            rotatedModel.triangleSkin = null;
            rotatedModel.vertexSkin = null;
        }
        if (spotAnim.resizeXY != 128 || spotAnim.resizeZ != 128) {
            rotatedModel.scaleT(spotAnim.resizeXY, spotAnim.resizeXY, spotAnim.resizeZ);
        }
        if (spotAnim.rotation != 0) {
            if (spotAnim.rotation == 90) {
                rotatedModel.rotateBy90(360);
            }
            if (spotAnim.rotation == 180) {
                rotatedModel.rotateBy90(360);
                rotatedModel.rotateBy90(360);
            }
            if (spotAnim.rotation == 270) {
                rotatedModel.rotateBy90(360);
                rotatedModel.rotateBy90(360);
                rotatedModel.rotateBy90(360);
            }
        }
        rotatedModel.light(64 + spotAnim.modelBrightness, 850 + spotAnim.modelShadow, -30, -50, -30, true);
        return rotatedModel;
    }

    public final void animationStep(int timePassed) {
        for (duration += timePassed; duration > spotAnim.animationSequence.getFrameLength(elapsedFrames);) {
            duration -= spotAnim.animationSequence.getFrameLength(elapsedFrames) + 1;
            elapsedFrames++;
            if (elapsedFrames >= spotAnim.animationSequence.frameCount && (elapsedFrames < 0 || elapsedFrames >= spotAnim.animationSequence.frameCount)) {
                elapsedFrames = 0;
                transformCompleted = true;
            }
        }
    }
}
