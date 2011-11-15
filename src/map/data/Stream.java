package map.data;

// Fully Renamed - gamerx - 14.11.11

public class Stream {
    
    public byte streamBuffer[];
    public int currentOffset;
    
    public Stream() {
    }

    public Stream(byte byteArray[]) {
        streamBuffer = byteArray;
        currentOffset = 0;
    }

    public int getInt() {
        currentOffset += 4;
        return ((streamBuffer[currentOffset - 4] & 0xff) << 24)
                + ((streamBuffer[currentOffset - 3] & 0xff) << 16)
                + ((streamBuffer[currentOffset - 2] & 0xff) << 8)
                + (streamBuffer[currentOffset - 1] & 0xff);
    }

    public int getUnsignedByte() {
        return streamBuffer[currentOffset++] & 0xff;
    }

    public byte getByte() {
        return streamBuffer[currentOffset++];
    }

    public int getShort() {
        currentOffset += 2;
        return ((streamBuffer[currentOffset - 2] & 0xff) << 8)
                + (streamBuffer[currentOffset - 1] & 0xff);
    }
    
    public String getString() {
        int offset = currentOffset;
        while (streamBuffer[currentOffset++] != 10);
        return new String(streamBuffer, offset, currentOffset - offset - 1);
    }
    
    public int getShortInt() {
        currentOffset += 3;
        return ((streamBuffer[currentOffset - 3] & 0xff) << 16)
                + ((streamBuffer[currentOffset - 2] & 0xff) << 8)
                + (streamBuffer[currentOffset - 1] & 0xff);
    }
}
