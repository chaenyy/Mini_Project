package com.kh.chm.miniproject.game.baseball;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class BaseballMain {
	private Scanner sc = new Scanner(System.in);
	private List<Integer> computerList = null;
	private int[] playerArr = new int[3];
	private Map<String, Integer> baseballMap = new HashMap<>();
	private File file = new File("UserScoreDB/baseballScoreDB.txt");

	private int strikeCount = 0;
	private int ballCount = 0;
	private int chance = 5;
	private int score = 0;
	private String userId = null;
	private int userPlayNum = 2;
	private String inputNum = null;

	@SuppressWarnings("unchecked")
	public void start(String userId) {
		
		int playNum = 5;
		this.userId = userId;

		Set<Integer> set = new HashSet<>();
		System.out.print("\n\n\t\t     BASEBALL GAME\n" + "\t===========================================\n"
				+ "\t\t  🔷🔷 1 번째 배팅 🔷🔷\n" + "\t===========================================\n");

		POS: while (playNum > 0) {
			if (chance == 5) {
				//투수가 공을 던지는 중
				System.out.println("\n\t\t 투수가 공을 던집니다!");
				for (int i = 0; i < 2; i++) {
					try {
						Thread.sleep(1000);
						System.out.print("\t\t ...\n");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				try {
					Thread.sleep(1000);
					System.out.println("\t\t 투수가 공을 던졌습니다!!");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// 컴퓨터 난수 생성
			while (set.size() < 3) {
				int number = (int) (Math.random() * 10);
				set.add(number);
				computerList = new ArrayList<>(set);
			}
//			System.out.println("컴퓨터 " + computerList);

			// 사용자 입력
			System.out.println("\t ===========================================");
			System.out.println("\t | 남은 기회 → " + chance + " | 남은 판 수 → " + playNum + " | 현재 점수 → " + score + " |");
			System.out.println("\t ===========================================");
			System.out.print("\n\t 투수가 던진 야구공 숫자를 맞춰보세요!\n" + "\t (※ 단, 한 번에 중복된 수는 입력할 수 없습니다.)\n");
			System.out.print("\t 숫자 배팅 → ");
			inputNum = sc.next();

			// 3자리가 아닐 때 다시 시작
			if (inputNum.length() != 3) {
				System.err.println("\t※ 세자리의 수만 입력해야합니다. 게임을 다시 시작합니다.");
				continue POS;
			}

			// 중복숫자확인
				for (int i = 0; i < inputNum.length(); i++) {
					for (int j = i + 1; j < inputNum.length(); j++) {
						if (inputNum.charAt(i) == inputNum.charAt(j)) {
							System.err.println("\t\t※ 중복된 수를 입력할 수 없습니다. 게임을 다시 시작합니다.");
							continue POS;
						}
					}
					playerArr[i] = Integer.parseInt(inputNum.charAt(i) + "");
				}

			// 스트라이크, 볼 카운트
			for (int i = 0; i < computerList.size(); i++) {
				if (computerList.get(i) == playerArr[i])
					strikeCount++;
				else if (computerList.get(i) != playerArr[i]) {
					if (computerList.contains(playerArr[i]))
						ballCount++;
				}
			}

			// 야구공 숫자 출력 및 스트라이크, 볼 카운트 표시
			System.err.print("\n\n\t\t      배 팅 결 과\n" + "\t\t ⚾⚾⚾ " + strikeCount + "S " + ballCount + "B ⚾⚾⚾ \n\n");
			chance--;

			// 3S 시 점수 획득, 종료
			if (strikeCount == 3) {
				System.out.println("\t\t 3 스트라이크 성공!");
				score += 3;
				System.out.println("\t\t 현재 점수 → " + score + "점\n\n");
				playNum--;
				chance = 5;
				set.removeAll(set);
				strikeCount = 0;
				ballCount = 0;
				if (userPlayNum < 6) {
					System.out.print("\t===========================================\n" + "\t\t  🔷🔷 " + (userPlayNum++)
							+ " 번째 배팅 🔷🔷\n" + "\t===========================================\n");
				}
				continue POS;
				
				// 5번 내에 못 맞추면 다음 판으로 넘어감
			} else if (chance == 0) {
				System.out.println("\t\t OUT! 다음 판으로 넘어갑니다.");
				playNum--;
				System.out.println("\t\t 현재 점수 → " + score + "점\n\n");
				chance = 5;
				set.removeAll(set);
				strikeCount = 0;
				ballCount = 0;
				if (userPlayNum < 6) {
					System.out.print("\t===========================================\n" + "\t\t  🔷🔷 " + (userPlayNum++)
							+ " 번째 배팅 🔷🔷\n" + "\t===========================================\n");
				}
				continue POS;
			}

			// 리셋
			strikeCount = 0;
			ballCount = 0;
		}
		System.out.println("\t\t    GAME OVER!");
		System.out.println("\t\t    총점 → " + score +"점");
		
		//랭킹 구현을 위한 파일 입출력
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			baseballMap = (Map<String, Integer>) ois.readObject();

		} catch (FileNotFoundException e) {
			baseballMap = new HashMap<>();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//파일이 비어있다면 userId, 0으로 초기화
		if (baseballMap.get(userId) == null)
			baseballMap.put(userId, 0);

		int scoreUpdata = baseballMap.get(userId) + score;
		baseballMap.put(userId, scoreUpdata);

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(baseballMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
