package ISLAB3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class LSB {
	File lsbImage;
	File lsbText;
	private RandomAccessFile rwImage;
	private RandomAccessFile rwText;
	private byte [] bitsPerPixel;
	private int imageShift;
	private byte [] compression;
	private Pixel pixel;
	public LSB(String dir, String imageName, String textName)
	{
		pixel=new Pixel();
		lsbImage=new File(dir, imageName);
		lsbText=new File(dir, textName);
		byte [] imageShift=new byte[4];
		bitsPerPixel=new byte[2];
		compression =new byte[4];
		try {
			rwImage=new RandomAccessFile(lsbImage, "rw");
			rwText=new RandomAccessFile(lsbText, "rw");
		} catch (FileNotFoundException e) {
			System.out.println("Невозможно осуществить чтение файла!!!");
		}
		try {
			rwImage.seek(28);
			rwImage.read(bitsPerPixel);
			rwImage.seek(10);
			rwImage.read(imageShift);
			rwImage.seek(30);
			rwImage.read(compression);
		} catch (IOException e) {
			System.out.println("Невозможно прочитать информацию файла!!!");
		}
		try {
			for (int i=0;i<4;i++)
				if((int)compression[i]!=0)
					throw new Exception("Файл является сжатым");
			if(!(((int)bitsPerPixel[0]==24)&&((int)bitsPerPixel[1]==0)))
					throw new Exception("Не true color!!!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		this.imageShift=(((int)imageShift[0])&255)+(((int)imageShift[1])&255)*(int)Math.pow(2, 1*8)+(((int)imageShift[2])&255)*(int)Math.pow(2, 2*8)+(((int)imageShift[3])&255)*(int)Math.pow(2, 3*8);
	}
	public void readerClose()
	{
		try {
			rwImage.close();
			rwText.close();
		} catch (IOException e) {
			System.out.println("Файл не может быть закрыт!!!");
		}
	}
	public void decryption() throws IOException
	{
		rwImage.seek(imageShift);
		while(rwImage.getFilePointer()<rwImage.length()-3)
		{
			rwText.writeByte(pixel.getCharDecryption(rwImage));
		}
	}
	public void encryption() throws IOException
	{
		rwImage.seek(imageShift);
		byte [] text=new byte[(int) rwText.length()];
		rwText.read(text);
		for(byte symbol:text)
		{
			pixel.putCharEncryption(rwImage, symbol);
		}
	}
}
