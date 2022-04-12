package com.kh.chm.miniproject.game.hangman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.kh.chm.miniproject.member.Member;

import java.util.Scanner;
import java.util.Set;

public class HangmanMain {
	Map<String, Integer> hangmanMap = new HashMap<>();
	String userId = "";
	File file = new File("UserScoreDB/hangmanScoreDB.txt");
	int point = 0;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@SuppressWarnings("unchecked")
	public void HangmanStart(String userId) throws IOException {
		this.userId = userId;
		
		Scanner sc = new Scanner(System.in);

		Hangman function = new Hangman();
		Hangman2 function2 = new Hangman2();
		Hangman3 function3 = new Hangman3();
		Member m = new Member();
		
		int userChoice;
		String level;
		int correctedQuestion = 0;
		int playNumber = 0;
		
		OUTER:
		while (playNumber < 6) {
			playNumber++;
			if(playNumber == 6) break;
			System.out.print("\n\n\t\t  HANGMAN GAME\n"
							+ "\t\t=================\n"
							+ "\t\t  1   난이도 (하)\n"
							+ "\t\t  2   난이도 (중)\n"
							+ "\t\t  3   난이도 (상)\n"
							+ "\t\t=================\n"
							+ "\t\t 난이도를 선택하세요 → "); 
			level = sc.next();
			if(!("1".equals(level) && "2".equals(level) && "3".equals(level) && "4".equals(level))) {
				System.out.println("\t\t※ 잘못 입력하셨습니다. 다시 입력해주세요.");
			}
			//난이도 1
			if ("1".equals(level)) {
				System.out.println("\n\n\t\t 🔷🔷 " + playNumber + "번째 판 🔷🔷");
				function.question();
				while (function.getOutputString().indexOf("⭐") != -1 && function.getLife() != 0) {
					String userInput = function.input();
					System.out.println("");
					if ("0".equals(userInput)) {
						playNumber--;
						break;
					}
					else
						function.check();
				}
				System.out.print("\t\t=================\n"
								+"\t\t 정답 → " + function.getHiddenString() + "\n"
								+"\t\t 의미 → " + function.getMean() + "\n"
								+ "\t\t=================");
				System.out.println("");
				if (function.getOutputString().indexOf("⭐") == -1) {
					System.out.println("\t\t     "+ (++correctedQuestion) + "점 획득!");
				}
				if (function.getLife() == 0) {
					System.out.println("\t\t    Game over");
				}
				
			//난이도 2
			} else if ("2".equals(level)) {
				System.out.println("\n\n\t\t 🔷🔷 " + playNumber + "번째 판 🔷🔷");
				function2.question();
				while (function2.getOutputString().indexOf("⭐") != -1 && function2.getLife() != 0) {
					String userInput = function2.input();
					System.out.println("");
					if ("0".equals(userInput)) {
						playNumber--;
						break;
					}
					else
						function2.check();
				}
				System.out.print("\t\t=================\n"
						+"\t\t 정답 → " + function2.getHiddenString() + "\n"
						+"\t\t 의미 → " + function2.getMean() + "\n"
						+ "\t\t=================");
				System.out.println("");
				if (function2.getOutputString().indexOf("*") == -1) {
					correctedQuestion += 2;
					System.out.println("\t\t    " + correctedQuestion +"점 획득!");
					
				}
				if (function2.getLife() == 0) {
					System.out.println("\t\t    GAME OVER!");
				}
				
			//난이도 3
			} else if ("3".equals(level)) {
				System.out.println("\n\n\t\t 🔷🔷 " + playNumber + "번째 판 🔷🔷");
				function3.question();
				while (function3.getOutputString().indexOf("⭐") != -1 && function3.getLife() != 0) {
					String userInput = function3.input();
					System.out.println("");
					if ("0".equals(userInput)) {
						playNumber--;
						break;
					}
					else
						function3.check();
				}
				System.out.print("\t\t=================\n"
						+"\t\t 정답 → " + function3.getHiddenString() + "\n"
						+"\t\t 의미 → " + function3.getMean() + "\n"
						+ "\t\t=================");
				System.out.println("");
				if (function3.getOutputString().indexOf("⭐") == -1) {
					correctedQuestion += 3;
					System.out.println("\t\t    " + correctedQuestion +"점 획득!");
				}
				if (function3.getLife() == 0) {
					System.out.println("\t\t    GAME OVER!");
				}

			}
			else if ("4".equals(level)) break;
		}
		System.out.println("\n\n\t\t    GAME OVER!");
		System.out.println("\t\t    총점 → " + correctedQuestion +"점");
		
		//포인트 관리를 위한 파일 입출력
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			hangmanMap = (Map<String, Integer>)ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(FileNotFoundException e	 ) {
			hangmanMap = new HashMap<>();
		}
		
		if(hangmanMap.get(userId) == null) {
			hangmanMap.put(userId, 0);
		}
		
		int scoreUpdate =  hangmanMap.get(userId) + correctedQuestion;
		hangmanMap.put(userId, scoreUpdate);
		
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(hangmanMap);
		}
//		System.out.println(hangmanMap);
		}
}
