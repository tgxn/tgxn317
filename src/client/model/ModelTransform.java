package client.model;

import client.data.Stream;


public class ModelTransform {
    
    public int anInt341;
    public int anIntArray342[];
    public int anIntArrayArray343[][];
    
    public ModelTransform(Stream class30_sub2_sub2) {
        anInt341 = class30_sub2_sub2.readUnsignedByte();
        anIntArray342 = new int[anInt341];
        anIntArrayArray343 = new int[anInt341][];
        
        for (int j = 0; j < anInt341; j++) {
            anIntArray342[j] = class30_sub2_sub2.readUnsignedByte();
        }

        for (int k = 0; k < anInt341; k++) {
            int l = class30_sub2_sub2.readUnsignedByte();
            anIntArrayArray343[k] = new int[l];
            for (int i1 = 0; i1 < l; i1++) {
                anIntArrayArray343[k][i1] = class30_sub2_sub2.readUnsignedByte();
            }
        }
    }
}
