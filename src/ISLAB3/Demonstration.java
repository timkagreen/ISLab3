package ISLAB3;

import java.io.IOException;
import java.util.Scanner;

public class Demonstration {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean flag=true;
		while (flag) {
			String answer;
			System.out.println("��� �� ������� �������?");
			System.out.println("1. �����������");
			System.out.println("2. ������������");
			System.out.println("3. �����");
			answer=in.nextLine();
			switch(answer)
			{
			case "1":
				LSB imageEn = new LSB("E:\\������\\.5 ���\\������ ����������\\Lab3\\ISLab3\\������������ ������ 3_��������\\", "1.bmp", "text.txt");
				try {
					imageEn.encryption();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				System.out.println("��������� �����������.");
				break;
			case "2":
				LSB imageDe = new LSB("E:\\������\\.5 ���\\������ ����������\\Lab3\\ISLab3\\������������ ������ 3_��������\\", "1.bmp", "text.txt");
				try {
					imageDe.decryption();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				System.out.println("��������� ������������.");
				break;
			case "3":
				flag=false;
				break;
			}
		}
		in.close();
	}

}
