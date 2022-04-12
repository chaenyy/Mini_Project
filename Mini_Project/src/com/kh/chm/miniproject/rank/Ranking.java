package com.kh.chm.miniproject.rank;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Ranking {
	File baseballRank = new File("UserScoreDB/baseballScoreDB.txt");
	File hangmanRank = new File("UserScoreDB/hangmanScoreDB.txt");
	File rockcspaperRank = new File("UserScoreDB/rockcspaperScoreDB.txt");
	
	Map<String, Integer> baseballMap = new HashMap<>();
	Map<String, Integer> hangmanMap = new HashMap<>();
	Map<String, Integer> rockcspaperMap = new HashMap<>();
	
	public void finalRank() {
		Ranking rank = new Ranking();
		System.out.print("\n\n\t\t       R A N K I N G\n"
				+ "\t\t=========================\n");
		rank.hangmanRank();
		rank.baseballRank();
		rank.rockcspaperRank();
		System.out.println("\t\t=========================\n"
						+  "\t\t    ~CONGRATULATION~\n");
	}
	
	@SuppressWarnings("unchecked")
	public void baseballRank () {

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(baseballRank))) {
			baseballMap = (Map<String, Integer>)ois.readObject();
		} catch(FileNotFoundException e	 ) {
			System.err.println("\t\t※ 등록된 랭킹이 없습니다.");
		} 
		catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
//		System.out.println(baseballMap);
		List<Entry<String, Integer>> list = new ArrayList<>(baseballMap.entrySet());
		
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> obj1, Entry<String, Integer> obj2) {
				return obj2.getValue().compareTo(obj1.getValue());
			}
		});
		
		System.out.println("\n\t\t  🏆 B A S E B A L L 🏆");
		int rank = 1;
		for(Entry<String, Integer> entry : list) {
			System.out.println("\t\t" + (rank++) + "등 → ID [" + entry.getKey() + "] (총 " + entry.getValue()+"점 달성!)");
			if(rank == 4) {
				break;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void hangmanRank() {
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(hangmanRank))) {
			hangmanMap = (Map<String, Integer>)ois.readObject();
		} catch(FileNotFoundException e) {
			System.err.println("\t\t※ 등록된 랭킹이 없습니다.");
		}
		catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
//		System.out.println(hangmanMap);
		List<Entry<String, Integer>> list = new ArrayList<>(hangmanMap.entrySet());
		
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> obj1, Entry<String, Integer> obj2) {
				return obj2.getValue().compareTo(obj1.getValue());
			}
		});
		
		System.out.println("\t\t  🏆 H A N G M A N 🏆");
		int rank = 1;
		for(Entry<String, Integer> entry : list) {
			System.out.println("\t\t" + (rank++) + "등 → [" + entry.getKey() + "] (총 " + entry.getValue()+"점 달성!)");
			if(rank == 4) {
				break;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void rockcspaperRank() {
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rockcspaperRank))) {
			rockcspaperMap = (Map<String, Integer>)ois.readObject();
		} catch(FileNotFoundException e	 ) {
			System.err.println("\t\t※ 등록된 랭킹이 없습니다.");
		}
		catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
//		System.out.println(rockcspaperMap);
		List<Entry<String, Integer>> list = new ArrayList<>(rockcspaperMap.entrySet());
		
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> obj1, Entry<String, Integer> obj2) {
				return obj2.getValue().compareTo(obj1.getValue());
			}
		});
		
		System.out.println("\n\t\t🏆 R O C K C S P A P E R 🏆");
		int rank = 1;
		for(Entry<String, Integer> entry : list) {
			System.out.println("\t\t" + (rank++) + "등 → [" + entry.getKey() + "] (총 " + entry.getValue()+"점 달성!)");
			if(rank == 4) {
				break;
			}
		}
	}
}
