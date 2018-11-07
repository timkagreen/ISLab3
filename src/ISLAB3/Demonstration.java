package ISLAB3;

import java.io.IOException;
import java.util.Scanner;

public class Demonstration {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean flag=true;
		while (flag) {
			String answer;
			System.out.println("Что вы хотитет сделать?");
			System.out.println("1. Зашифровать");
			System.out.println("2. Расшифровать");
			System.out.println("3. Выйти");
			answer=in.nextLine();
			switch(answer)
			{
			case "1":
				LSB imageEn = new LSB("E:\\учебка\\.5 сем\\защита информации\\Lab3\\ISLab3\\Лабораторная работа 3_варианты\\", "1.bmp", "text.txt");
				try {
					imageEn.encryption();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				System.out.println("Сообщение зашифровано.");
				break;
			case "2":
				LSB imageDe = new LSB("E:\\учебка\\.5 сем\\защита информации\\Lab3\\ISLab3\\Лабораторная работа 3_варианты\\", "1.bmp", "text.txt");
				try {
					imageDe.decryption();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				System.out.println("Сообщение расшифровано.");
				break;
			case "3":
				flag=false;
				break;
			}
		}
		in.close();
	}

}
