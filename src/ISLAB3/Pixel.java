package ISLAB3;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Pixel {
	private byte rgbBlue;
	private byte rgbGreen;
	private byte rgbRed;
	private byte rgbReserved;
	private byte result;
	public Pixel()
	{
		rgbBlue=0;
		rgbGreen=0;
		rgbRed=0;
		rgbReserved=0;
		result=0;
	}
	private void initPixel(RandomAccessFile reader) throws IOException//?????????
	{
			rgbBlue=reader.readByte();
			rgbGreen=reader.readByte();
			rgbRed=reader.readByte();
			rgbReserved=reader.readByte();
	}
	private byte getByteDecryption()//?????????
	{
		rgbBlue=(byte)(rgbBlue&((byte)3));
		rgbBlue=(byte)(rgbBlue<<6);
		rgbGreen=(byte)(rgbGreen&((byte)3));
		rgbGreen=(byte)(rgbGreen<<4);
		rgbRed=(byte)(rgbRed&((byte)3));
		rgbRed=(byte)(rgbRed<<2);
		rgbReserved=(byte)(rgbReserved&((byte)3));
		rgbReserved=(byte)(rgbReserved<<0);
		result=(byte)(rgbBlue|rgbGreen|rgbRed|rgbReserved);
		return result;
	}
	public byte getCharDecryption(RandomAccessFile reader) throws IOException//?????????
	{
		initPixel(reader);
			return getByteDecryption();
	}
	private void initPixelEncryption(RandomAccessFile rw, byte symbol) throws IOException
	{
		initPixel(rw);
		rgbBlue=(byte)(rgbBlue&(byte)252);
		rgbGreen=(byte)(rgbGreen&(byte)252);
		rgbRed=(byte)(rgbRed&(byte)252);
		rgbReserved=(byte)(rgbReserved&(byte)252);
		byte blue=(byte)(((byte)(symbol>>6))&((byte)3));
		byte green=(byte)(((byte)(symbol>>4))&((byte)3));
		byte red=(byte)(((byte)(symbol>>2))&((byte)3));
		byte reserved=(byte)(((byte)(symbol>>0))&((byte)3));
		rgbBlue=(byte)(rgbBlue|blue);
		rgbGreen=(byte)(rgbGreen|green);
		rgbRed=(byte)(rgbRed|red);
		rgbReserved=(byte)(rgbReserved|reserved);
		/*
		initPixel(rw);
		rgbBlue=(byte)(rgbBlue&(byte)63);
		rgbGreen=(byte)(rgbGreen&(byte)63);
		rgbRed=(byte)(rgbRed&(byte)63);
		rgbReserved=(byte)(rgbReserved&(byte)63);
		byte blue=(byte)(((byte)(symbol<<0))&((byte)192));
		byte green=(byte)(((byte)(symbol<<2))&((byte)192));
		byte red=(byte)(((byte)(symbol<<4))&((byte)192));
		byte reserved=(byte)(((byte)(symbol<<6))&((byte)192));
		rgbBlue=(byte)(rgbBlue|blue);
		rgbGreen=(byte)(rgbGreen|green);
		rgbRed=(byte)(rgbRed|red);
		rgbReserved=(byte)(rgbReserved|reserved);*/
	}
	private void putByteEncryption(RandomAccessFile rw) throws IOException
	{
		rw.seek(rw.getFilePointer()-4);
		byte [] bytes= {rgbBlue, rgbGreen,rgbRed,rgbReserved};
		rw.write(bytes);
	}
	public void putCharEncryption(RandomAccessFile rw, byte symbol) throws IOException
	{
		initPixelEncryption(rw, symbol);
		putByteEncryption(rw);
	}
}
