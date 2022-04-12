package com.kh.chm.miniproject.game.rockcspaper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class RockcspaperMain {

	public void rockcspaperStart(String userId) {
		Scanner sc = new Scanner(System.in);
		Random rnd = new Random();
		int count = 0;
		int score = 0;
		final int MAX_COUNT = 5;
		File file = new File("UserScoreDB/rockcspaperScoreDB.txt");
		Map<String, Integer> rockcspaperMap = new HashMap<>();

		for (;;) {
			
			try {
				System.out.print("\n\n\t     ROCK_PAPER_SCISSORS GAME\n"
								+"\t\t=================\n"
								+ "\t\t   1 가위 내기\n"
								+ "\t\t   2 바위 내기\n"
								+ "\t\t   3 보  내기\n"
								+ "\t\t=================\n"
								+ "\t\t 숫자를 입력하세요 → ");

				int userNo = sc.nextInt();
				int comNo = rnd.nextInt(3) + 1;
				if ((userNo < 0) || (userNo > 3)) {
					throw new WrongInputNumberException();
				}
				count++;
	
				String user = userNo == 1 ? "가위" : (userNo == 2 ? "바위" : "보");
				String com = comNo == 1 ? "가위" : (comNo == 2 ? "바위" : "보");
	  
				System.err.print("\n\n\t     ✌✌ 가 위 바 위 보 결 과 ✌✌\n"
								+ "\t       당신은 [" + user + "]를 냈습니다!\n"
								+ "\t      컴퓨터는 [" + com + "]를 냈습니다!\n\n");
	
				// 실행결과
				if (comNo == userNo) {
					System.out.println("\t\t 결과 → 비겼습니다.");
				} else if ((comNo == 1 && userNo == 2) || (comNo == 2 && userNo == 3) || (comNo == 3 && userNo == 1)) {
					score++; // 이길 때마다 점수 1점씩 부여
					System.out.println("\t    결과 → 당신이 이겼습니다. 1점 획득! ");
					System.out.println("\t      현재 획득 점수 → " + score +"점");
				} else if ((comNo == 1 && userNo == 3) || (comNo == 2 && userNo == 1) || (comNo == 3 && userNo == 2)) {
					System.out.println("\t\t 결과 → 당신이 졌습니다.");
				}
				// 남은 판수 출력
				// 0보다 크면 n판 남았습니다.
				// 0이면 끝났습니다. break;
				if (MAX_COUNT - count > 0) {
					System.out.println("\t      현재 남은 판 수 → " + (MAX_COUNT - count) +"판");
				} else {
					System.out.println("\n\t\t    GAME OVER!");
					System.out.println("\t\t   당신의 총점 → " + score+"점");
					try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
						rockcspaperMap = (Map<String, Integer>)ois.readObject();
						
					}  catch(FileNotFoundException e) {
						rockcspaperMap = new HashMap<>();
					} 
					catch(IOException e) {
						e.printStackTrace();
					}
					catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					
					if(rockcspaperMap.get(userId) == null) {
						rockcspaperMap.put(userId, 0);
					}
					
					int scoreUpdate =  rockcspaperMap.get(userId) + score;
					rockcspaperMap.put(userId, scoreUpdate);
					
					
					try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
						oos.writeObject(rockcspaperMap);
					} catch(IOException e) {
						e.printStackTrace();
					}
					break;
				}
			} catch (WrongInputNumberException e) {
				System.err.println("\t\t 잘못 입력 하셨습니다. 다음의 숫자를 선택하세요 (1.가위/2.바위/3.보)");
				
			} catch (InputMismatchException e) {
				System.err.println("\t\t 문자를 입력하셨습니다. 숫자를 선택하세요 (1.가위/2.바위/3.보)");
				
			}

		}

	}
}
