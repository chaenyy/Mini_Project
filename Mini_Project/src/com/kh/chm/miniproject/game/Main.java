package com.kh.chm.miniproject.game;

import java.io.IOException;
import java.util.Scanner;

import com.kh.chm.miniproject.game.baseball.BaseballMain;
import com.kh.chm.miniproject.game.hangman.HangmanMain;
import com.kh.chm.miniproject.game.rockcspaper.RockcspaperMain;
import com.kh.chm.miniproject.member.MemberLogin;
import com.kh.chm.miniproject.member.MemberSignUp;
import com.kh.chm.miniproject.rank.Ranking;

public class Main {
	Scanner sc = new Scanner(System.in);
	MemberLogin login = new MemberLogin();
	MemberSignUp msu = new MemberSignUp();
	HangmanMain hangmanMain = new HangmanMain();
	BaseballMain baseballMain = new BaseballMain();
	RockcspaperMain rockcspaperMain = new RockcspaperMain();
	Ranking rank = new Ranking();
	
	static String userId = "";
	static String userPw = "";
	public static void main(String[] args) {
		Main main = new Main();
		main.signUpLogin();
		main.gameChoice(userId);
	}
	
	public void signUpLogin() {
		OUTER:
		while(true) {
			System.out.println("\n\n"
					+ " ________   ________   __   __   __   ______   ______     \n"
					+ "|  ______| |   __   | |   \\/   \\/  | |   ___| |   ___| \n"
					+ "| |   ___  |  |__|  | |   _    _   | |  |___  |  |___      \n"
					+ "| |  |__ | |   __   | |  | |  | |  | |   ___| |___   |      \n" 
					+ "| |____| | |  |  |  | |  | |__| |  | |  |___   ___|  |       \n"
					+ "|________| |__|  |__| |__|      |__| |______| |______|           \n");
			
			System.out.print("\t\t게임즈에 오신 걸 환영합니다!\n"
							+ "\t\t=================\n"
							+ "\t\t  1   로그인\n"
							+ "\t\t  2   회원가입 \n"
							+ "\t\t  3   종료 \n"
							+ "\t\t=================\n"
							+ "\t\t  번호를 선택하세요 → ");
			String input = sc.nextLine();
			switch(input) {
			case "1" :
				System.out.print("\n\t\t    L O G I N\n"
						+ "\t\t=================\n");
				System.out.print("\t\t아이디  → ");
				userId = sc.next();
				System.out.print("\t\t비밀번호 → ");
				userPw = sc.next();	
				sc.nextLine();
				System.out.println("\t\t=================");
				login.idCheck(userId, userPw); 
				if(login.getDupeIdCount() == 0 || login.getDupePwCount() == 0) continue OUTER;
				else break OUTER;
				
			case "2" :
				msu.join(msu.memberInfo());
				continue OUTER;
			case "3" : System.out.println("\t\t 종료하겠습니다.");return;
			default : System.out.println("\t\t잘못 입력하셨습니다. 다시 선택하세요."); break;
			}
		}
	}
	
	public void gameChoice(String userId) {
		while(true) {
			System.out.print("\n\t\t    Game List\n"
							+ "\t\t=================\n"
							+ "\t\t   1  행맨 게임\n"
							+ "\t\t   2  숫자 야구 게임\n"
							+ "\t\t   3  가위바위보 게임\n"
							+ "\t\t   4  게임별 랭킹\n"
							+ "\t\t   5  종료\n"
							+ "\t\t=================\n"
							+ "\t\t  게임을 선택하세요 → ");
			String input = sc.next();
			
			switch(input) {
			case "1" : try {
					hangmanMain.HangmanStart(userId);
				} catch (IOException e) {
					e.printStackTrace();
				} break;
			case "2" : baseballMain.start(userId); break;
			case "3" : rockcspaperMain.rockcspaperStart(userId); break;
			case "4" : rank.finalRank(); break;
			case "5" : System.out.println("\t\t게임을 종료합니다."); return;
			default : System.out.println("\t\t 잘못 입력하셨습니다. 다시 선택하세요."); break;
			}
		}
	}
}
