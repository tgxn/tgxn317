package client.data;

// Fully Renamed - gamerx - 14.11.11
// Further Renaming - gamerx - 15.11.11

import java.io.*;

/**
 * Handles Main Jagex Archive file.
 *
 * @knownas
 * @author gamerx
 */
public final class JagexFileHandle {
    
    private static byte outputBuffer[] = new byte[520];
    private RandomAccessFile dataFile;
    private RandomAccessFile indexFile;
    private int storeID;
    
    public JagexFileHandle(RandomAccessFile fileData, RandomAccessFile fileIndex, int fileStoreID) {
        storeID = fileStoreID;
        dataFile = fileData;
        indexFile = fileIndex;
    }

    public synchronized byte[] decompressFile(int indexOffset) {
        try {
            seekTo(indexFile, indexOffset * 6);
            int offset;
            for (int idx = 0; idx < 6; idx += offset) {
                offset = indexFile.read(outputBuffer, idx, 6 - idx);
                if (offset == -1) {
                    return null;
                }
            }
            int fileSize = ((outputBuffer[0] & 0xff) << 16) + ((outputBuffer[1] & 0xff) << 8) + (outputBuffer[2] & 0xff);
            int sector = ((outputBuffer[3] & 0xff) << 16) + ((outputBuffer[4] & 0xff) << 8) + (outputBuffer[5] & 0xff);
            if (fileSize < 0 ) {
                return null;
            }
            if (sector <= 0 || (long) sector > dataFile.length() / 520L) {
                return null;
            }
            byte buffer[] = new byte[fileSize];
            int read = 0;
            for (int l1 = 0; read < fileSize; l1++) {
                if (sector == 0) {
                    return null;
                }
                seekTo(dataFile, sector * 520);
                int idx_ = 0;
                int unread = fileSize - read;
                if (unread > 512) {
                    unread = 512;
                }
                int off_;
                for (; idx_ < unread + 8; idx_ += off_) {
                    off_ = dataFile.read(outputBuffer, idx_, (unread + 8) - idx_);
                    if (off_ == -1) {
                        return null;
                    }
                }
                int currentFile = ((outputBuffer[0] & 0xff) << 8) + (outputBuffer[1] & 0xff);
                int currentPart = ((outputBuffer[2] & 0xff) << 8) + (outputBuffer[3] & 0xff);
                int nextSector = ((outputBuffer[4] & 0xff) << 16) + ((outputBuffer[5] & 0xff) << 8) + (outputBuffer[6] & 0xff);
                int currentCache = outputBuffer[7] & 0xff;
                if (currentFile != indexOffset || currentPart != l1 || currentCache != storeID) {
                    return null;
                }
                if (nextSector < 0 || (long) nextSector > dataFile.length() / 520L) {
                    return null;
                }
                for (int k3 = 0; k3 < unread; k3++) {
                    buffer[read++] = outputBuffer[k3 + 8];
                }
                sector = nextSector;
            }
            return buffer;
        } catch (IOException ignored) {
            
            ignored.printStackTrace(System.out);
            return null;
        }
    }

    public synchronized boolean put(int totalSize, byte JFS[], int indexPos) {
        boolean exists = doPut(true, indexPos, totalSize, JFS);
        if (!exists) {
            exists = doPut(false, indexPos, totalSize, JFS);
        }
        return exists;
    }

    private synchronized boolean doPut(boolean exists, int index, int length, byte data[]) {
        try {
            int sector;
            if (exists) {
                seekTo(indexFile, index * 6);
                int k1;
                for (int i1 = 0; i1 < 6; i1 += k1) {
                    k1 = indexFile.read(outputBuffer, i1, 6 - i1);
                    if (k1 == -1) {
                        return false;
                    }
                }

                sector = ((outputBuffer[3] & 0xff) << 16) + ((outputBuffer[4] & 0xff) << 8) + (outputBuffer[5] & 0xff);
                if (sector <= 0 || (long) sector > dataFile.length() / 520L) {
                    return false;
                }
            } else {
                sector = (int) ((dataFile.length() + 519L) / 520L);
                if (sector == 0) {
                    sector = 1;
                }
            }
            outputBuffer[0] = (byte) (length >> 16);
            outputBuffer[1] = (byte) (length >> 8);
            outputBuffer[2] = (byte) length;
            outputBuffer[3] = (byte) (sector >> 16);
            outputBuffer[4] = (byte) (sector >> 8);
            outputBuffer[5] = (byte) sector;
            seekTo(indexFile, index * 6);
            indexFile.write(outputBuffer, 0, 6);
            int written = 0;
            for (int l1 = 0; written < length; l1++) {
                int nextSector = 0;
                if (exists) {
                    seekTo(dataFile, sector * 520);
                    int idx;
                    int off;
                    for (idx = 0; idx < 8; idx += off) {
                        off = dataFile.read(outputBuffer, idx, 8 - idx);
                        if (off == -1) {
                            break;
                        }
                    }
                    if (idx == 8) {
                        int currentFile = ((outputBuffer[0] & 0xff) << 8) + (outputBuffer[1] & 0xff);
                        int currentpart = ((outputBuffer[2] & 0xff) << 8) + (outputBuffer[3] & 0xff);
                        nextSector = ((outputBuffer[4] & 0xff) << 16) + ((outputBuffer[5] & 0xff) << 8) + (outputBuffer[6] & 0xff);
                        int currentCache = outputBuffer[7] & 0xff;
                        if (currentFile != index || currentpart != l1 || currentCache != storeID) {
                            return false;
                        }
                        if (nextSector < 0 || (long) nextSector > dataFile.length() / 520L) {
                            return false;
                        }
                    }
                }
                if (nextSector == 0) {
                    exists = false;
                    nextSector = (int) ((dataFile.length() + 519L) / 520L);
                    if (nextSector == 0) {
                        nextSector++;
                    }
                    if (nextSector == sector) {
                        nextSector++;
                    }
                }
                if (length - written <= 512) {
                    nextSector = 0;
                }
                outputBuffer[0] = (byte) (index >> 8);
                outputBuffer[1] = (byte) index;
                outputBuffer[2] = (byte) (l1 >> 8);
                outputBuffer[3] = (byte) l1;
                outputBuffer[4] = (byte) (nextSector >> 16);
                outputBuffer[5] = (byte) (nextSector >> 8);
                outputBuffer[6] = (byte) nextSector;
                outputBuffer[7] = (byte) storeID;
                seekTo(dataFile, sector * 520);
                dataFile.write(outputBuffer, 0, 8);
                int k2 = length - written;
                if (k2 > 512) {
                    k2 = 512;
                }
                dataFile.write(data, written, k2);
                written += k2;
                sector = nextSector;
            }
            return true;
        } catch (IOException ignored) {
            return false;
        }
    }

    public synchronized void seekTo(RandomAccessFile randomaccessfile, int pos) throws IOException {
        if (pos < 0 || pos > 0x3c00000) {
            System.out.println("Badseek - pos:" + pos + " len:" + randomaccessfile.length());
            pos = 0x3c00000;
            try {
                Thread.sleep(1000L);
            } catch (Exception ignored) {
            }
        }
        randomaccessfile.seek(pos);
    }
}
