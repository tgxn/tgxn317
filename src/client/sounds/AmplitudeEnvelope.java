package client.sounds;

// Class 29.

import client.Stream;

// 99% refactored.

public class AmplitudeEnvelope {
    
    private int length;
    private int duration[];
    private int amplitudes[];
    int anInt538;
    int anInt539;
    int form;
    private int updateTick;
    private int pointer;
    private int step;
    private int amplitude;
    private int tick;
    
    public AmplitudeEnvelope() {
    }
    
    public final void method325(Stream stream) {
        form = stream.readUnsignedByte();
        anInt538 = stream.readDWord();
        anInt539 = stream.readDWord();
        readValues(stream);
    }

    public final void readValues(Stream stream) {
        length = stream.readUnsignedByte();
        duration = new int[length];
        amplitudes = new int[length];
        for (int i = 0; i < length; i++) {
            duration[i] = stream.readUnsignedWord();
            amplitudes[i] = stream.readUnsignedWord();
        }

    }

    final void resetValues() {
        updateTick = 0;
        pointer = 0;
        step = 0;
        amplitude = 0;
        tick = 0;
    }

    final int currentAmplitude(int sampleCount) { //linear interpolation method
        if (tick >= updateTick) {
            amplitude = amplitudes[pointer++] << 15;
            if (pointer >= length) {
                pointer = length - 1;
            }
            updateTick = (int) (((double) duration[pointer] / 65536D) * (double) sampleCount);
            if (updateTick > tick) {
                step = ((amplitudes[pointer] << 15) - amplitude) / (updateTick - tick);
            }
        }
        amplitude += step;
        tick++;
        return amplitude - step >> 15;
    }
}
