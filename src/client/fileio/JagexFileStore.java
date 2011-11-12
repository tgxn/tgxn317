package client.fileio;

// Fully Renamed.

import java.io.*;

public final class JagexFileStore {
    
    private static byte buffer[] = new byte[520];
    private RandomAccessFile dataFile;
    private RandomAccessFile indexFile;
    private int storeId;
    
    public JagexFileStore(RandomAccessFile dataFile_, RandomAccessFile indexFile_, int storeId_) {
        storeId = storeId_;
        dataFile = dataFile_;
        indexFile = indexFile_;
    }

    public synchronized byte[] decompress(int i) {
        try {
            seekTo(indexFile, i * 6);
            int offset;
            for (int idx = 0; idx < 6; idx += offset) {
                offset = indexFile.read(buffer, idx, 6 - idx);
                if (offset == -1) {
                    return null;
                }
            }
            int fileSize = ((buffer[0] & 0xff) << 16) + ((buffer[1] & 0xff) << 8) + (buffer[2] & 0xff);
            int sector = ((buffer[3] & 0xff) << 16) + ((buffer[4] & 0xff) << 8) + (buffer[5] & 0xff);
            // 
            if (fileSize < 0 ) {
                return null;
            }
            if (sector <= 0 || (long) sector > dataFile.length() / 520L) {
                return null;
            }
            byte buf[] = new byte[fileSize];
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
                    off_ = dataFile.read(buffer, idx_, (unread + 8) - idx_);
                    if (off_ == -1) {
                        return null;
                    }
                }
                int currentFile = ((buffer[0] & 0xff) << 8) + (buffer[1] & 0xff);
                int currentPart = ((buffer[2] & 0xff) << 8) + (buffer[3] & 0xff);
                int nextSector = ((buffer[4] & 0xff) << 16) + ((buffer[5] & 0xff) << 8) + (buffer[6] & 0xff);
                int currentCache = buffer[7] & 0xff;
                if (currentFile != i || currentPart != l1 || currentCache != storeId) {
                    return null;
                }
                if (nextSector < 0 || (long) nextSector > dataFile.length() / 520L) {
                    return null;
                }
                for (int k3 = 0; k3 < unread; k3++) {
                    buf[read++] = buffer[k3 + 8];
                }
                sector = nextSector;
            }
            return buf;
        } catch (IOException ignored) {
            
            ignored.printStackTrace(System.out);
            return null;
        }
    }

    public synchronized boolean put(int i, byte abyte0[], int j) {
        boolean exists = put_(true, j, i, abyte0);
        if (!exists) {
            exists = put_(false, j, i, abyte0);
        }
        return exists;
    }

    private synchronized boolean put_(boolean exists, int index, int length, byte data[]) {
        try {
            int sector;
            if (exists) {
                seekTo(indexFile, index * 6);
                int k1;
                for (int i1 = 0; i1 < 6; i1 += k1) {
                    k1 = indexFile.read(buffer, i1, 6 - i1);
                    if (k1 == -1) {
                        return false;
                    }
                }

                sector = ((buffer[3] & 0xff) << 16) + ((buffer[4] & 0xff) << 8) + (buffer[5] & 0xff);
                if (sector <= 0 || (long) sector > dataFile.length() / 520L) {
                    return false;
                }
            } else {
                sector = (int) ((dataFile.length() + 519L) / 520L);
                if (sector == 0) {
                    sector = 1;
                }
            }
            buffer[0] = (byte) (length >> 16);
            buffer[1] = (byte) (length >> 8);
            buffer[2] = (byte) length;
            buffer[3] = (byte) (sector >> 16);
            buffer[4] = (byte) (sector >> 8);
            buffer[5] = (byte) sector;
            seekTo(indexFile, index * 6);
            indexFile.write(buffer, 0, 6);
            int written = 0;
            for (int l1 = 0; written < length; l1++) {
                int nextSector = 0;
                if (exists) {
                    seekTo(dataFile, sector * 520);
                    int idx;
                    int off;
                    for (idx = 0; idx < 8; idx += off) {
                        off = dataFile.read(buffer, idx, 8 - idx);
                        if (off == -1) {
                            break;
                        }
                    }
                    if (idx == 8) {
                        int currentFile = ((buffer[0] & 0xff) << 8) + (buffer[1] & 0xff);
                        int currentpart = ((buffer[2] & 0xff) << 8) + (buffer[3] & 0xff);
                        nextSector = ((buffer[4] & 0xff) << 16) + ((buffer[5] & 0xff) << 8) + (buffer[6] & 0xff);
                        int currentCache = buffer[7] & 0xff;
                        if (currentFile != index || currentpart != l1 || currentCache != storeId) {
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
                buffer[0] = (byte) (index >> 8);
                buffer[1] = (byte) index;
                buffer[2] = (byte) (l1 >> 8);
                buffer[3] = (byte) l1;
                buffer[4] = (byte) (nextSector >> 16);
                buffer[5] = (byte) (nextSector >> 8);
                buffer[6] = (byte) nextSector;
                buffer[7] = (byte) storeId;
                seekTo(dataFile, sector * 520);
                dataFile.write(buffer, 0, 8);
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
