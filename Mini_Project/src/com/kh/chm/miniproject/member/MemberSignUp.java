package com.kh.chm.miniproject.member;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class MemberSignUp implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Scanner sc = new Scanner(System.in);
	private File file = new File("MemberDB/memberDB.txt");
	private Set<Member> sList = null;
	
	//회원 정보 입력 - 아이디 영문 아닐 경우, 이름에 숫자가 들어간 경우 등등 예외처리 필요함!
	public Member memberInfo() {
		System.out.print("\n\t\t     J O I N \n"
				+ "\t\t=================\n");
		System.out.print("\t\t아이디를 입력하세요 → ");
		String id = sc.next();
		System.out.print("\t\t비밀번호를 입력하세요 → ");
		String pw = sc.next();
		System.out.print("\t\t이름을 입력하세요 → ");
		String name = sc.next();
		System.out.println("\t\t=================");
//		sc.nextLine();
		return new Member(id, pw, name);
	}
	
	//회원 정보 관리
	@SuppressWarnings("unchecked")
	public void join(Member memberInfo) {
		Member member = memberInfo;
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
				sList = (Set<Member>)ois.readObject();
				
//				System.out.println(sList.size());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException |EOFException e) {
			sList = new LinkedHashSet<>();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//아이디 중복 값 제거
		Iterator<Member> iter = sList.iterator();
		while(iter.hasNext()) {
			String id = iter.next().getId();
			if(member.getId().equals(id)) {
				System.err.println("\t\t※ 중복된 아이디가 있습니다. 다른 아이디를 입력해주세요.");
				return;
			}
		}
		sList.add(member);
		
		//입력 받은 정보 파일에 입력
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));) {
			oos.writeObject(sList);
			System.out.println("\t\t S U C C E S S !");
		} catch(IOException e) {
			e.printStackTrace();
		} 
		//확인용
//		for(Member m : sList) {
//			System.out.println(m);
//		}
	}
}






