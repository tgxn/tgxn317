package map;





public class MapScenes extends DrawingArea {

	public MapScenes(Class6 class6, String s, int i) {
		ByteVector byteVector = new ByteVector(class6.method76(s + ".dat", null));
		ByteVector byteVector_1 = new ByteVector(class6.method76("index.dat", null));
		byteVector_1.currentOffset = byteVector.getShort();
		anInt215 = byteVector_1.getShort();
		anInt216 = byteVector_1.getShort();
		int j = byteVector_1.getUnsignedByte();
		anIntArray210 = new int[j];
		for(int k = 0; k < j - 1; k++)
			anIntArray210[k + 1] = byteVector_1.getShortInt();
		for(int l = 0; l < i; l++) {
			byteVector_1.currentOffset += 2;
			byteVector.currentOffset += byteVector_1.getShort() * byteVector_1.getShort();
			byteVector_1.currentOffset++;
		}
		anInt213 = byteVector_1.getUnsignedByte();
		anInt214 = byteVector_1.getUnsignedByte();
		anInt211 = byteVector_1.getShort();
		anInt212 = byteVector_1.getShort();
		int i1 = byteVector_1.getUnsignedByte();
		int j1 = anInt211 * anInt212;
		aByteArray209 = new byte[j1];
		if(i1 == 0) {
			for(int k1 = 0; k1 < j1; k1++)
				aByteArray209[k1] = byteVector.getByte();
		} else if(i1 == 1) {
			for(int l1 = 0; l1 < anInt211; l1++) {
				for(int i2 = 0; i2 < anInt212; i2++)
					aByteArray209[l1 + i2 * anInt211] = byteVector.getByte();
			}
		}
	}

	public void method50(int i, int j, int k, int l) {
		try {
			int i1 = anInt211;
			int j1 = anInt212;
			int k1 = 0;
			int l1 = 0;
			int i2 = (i1 << 16) / k;
			int j2 = (j1 << 16) / l;
			int k2 = anInt215;
			int l2 = anInt216;
			i2 = (k2 << 16) / k;
			j2 = (l2 << 16) / l;
			i += ((anInt213 * k + k2) - 1) / k2;
			j += ((anInt214 * l + l2) - 1) / l2;
			if((anInt213 * k) % k2 != 0)
				k1 = (k2 - (anInt213 * k) % k2 << 16) / k;
			if((anInt214 * l) % l2 != 0)
				l1 = (l2 - (anInt214 * l) % l2 << 16) / l;
			k = (k * (anInt211 - (k1 >> 16))) / k2;
			l = (l * (anInt212 - (l1 >> 16))) / l2;
			int i3 = i + j * DrawingArea.width;
			int j3 = DrawingArea.width - k;
			if(j < DrawingArea.topY) {
				int k3 = DrawingArea.topY - j;
				l -= k3;
				j = 0;
				i3 += k3 * DrawingArea.width;
				l1 += j2 * k3;
			}
			if(j + l > DrawingArea.bottomY)
				l -= (j + l) - DrawingArea.bottomY;
			if(i < DrawingArea.topX) {
				int l3 = DrawingArea.topX - i;
				k -= l3;
				i = 0;
				i3 += l3;
				k1 += i2 * l3;
				j3 += l3;
			}
			if(i + k > DrawingArea.bottomX) {
				int i4 = (i + k) - DrawingArea.bottomX;
				k -= i4;
				j3 += i4;
			}
			method51(DrawingArea.pixels, aByteArray209, anIntArray210, k1, l1, i3, j3, k, l, i2, j2, i1);
		} catch(Exception exception) {
			System.out.println("error in sprite clipping routine");
		}
	}

	public void method51(int ai[], byte abyte0[], int ai1[], int i, int j, int k, int l, int i1, int j1, int k1, int l1, int i2) {
		try {
			int j2 = i;
			for(int k2 = -j1; k2 < 0; k2++) {
				int l2 = (j >> 16) * i2;
				for(int i3 = -i1; i3 < 0; i3++) {
					byte byte0 = abyte0[(i >> 16) + l2];
					if(byte0 != 0)
						ai[k++] = ai1[byte0 & 0xff];
					else
						k++;
					i += k1;
				}
				j += l1;
				i = j2;
				k += l;
			}
		} catch(Exception exception) {
			System.out.println("error in plot_scale");
		}
	}

	public byte aByteArray209[];
	public int anIntArray210[];
	public int anInt211;
	public int anInt212;
	public int anInt213;
	public int anInt214;
	public int anInt215;
	public int anInt216;
}
