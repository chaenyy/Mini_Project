package com.kh.chm.miniproject.member;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class MemberLogin {
	private File file = new File("MemberDB/memberDB.txt");
	private Set<Member> sList = null;
	private int dupeIdCount = 0;
	private int dupePwCount = 0;
	private Scanner sc = new Scanner(System.in);
	
	public int getDupeIdCount() {
		return dupeIdCount;
	}
	public void setDupeIdCount(int dupeIdCount) {
		this.dupeIdCount = dupeIdCount;
	}
	public int getDupePwCount() {
		return dupePwCount;
	}
	public void setDupePwCount(int dupePwCount) {
		this.dupePwCount = dupePwCount;
	}

	@SuppressWarnings("unchecked")
	public void idCheck(String id, String pw) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));) {
			sList = (Set<Member>)ois.readObject();
			//정보확인용
//			for(Member m : sList) {
//				System.out.println(m);
//			}
			for(Member m : sList) {
				String listId = m.getId();
				String listPw = m.getPw();
				
				if(id.equals(listId)) dupeIdCount++;
				if(id.equals(listId) && pw.equals(listPw)) dupePwCount++;
			}

			if(dupeIdCount == 0) {
				System.err.println("\t\t※ 등록된 아이디가 없습니다. 회원가입을 진행해주세요.");
			}
			else if (dupePwCount == 0) {
				System.err.println("\t\t※ 비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
			}
			else {
				System.out.println("\t\t S U C C E S S ! ");
			}
		} catch(FileNotFoundException e) {
			System.err.println("\t\t※ 등록된 아이디가 없습니다. 회원가입을 진행해주세요.");
		}
		catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} 
	}
}
