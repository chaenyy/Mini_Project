package com.kh.chm.miniproject.game.hangman;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Hangman2 {
	  String[] arrays;
	    String english; 
	    String mean; 
	    StringBuffer outputString; 
	    StringBuffer inputString; 
	    int life;
	    String userInput; 
	    DrawMan dm = new DrawMan();
	    int questionNum = 0;
	    
	    Hangman2() throws IOException{
	        FileInputStream file = new FileInputStream(
	                "HangmanQ/WordCollection2.txt");
	        BufferedReader br = new BufferedReader(new InputStreamReader(file));
	        
	        String data = br.readLine();
	        arrays = data.split("/"); 
	        List<String> list = Arrays.asList(arrays);
	        Collections.shuffle(list);
	        
	        life=5;
	 
	    }
	    
	    public void question(){
	    	life = 5;
	        dm.drawMan(life);
	        
	        int half = arrays[questionNum].indexOf(":");
	        english = arrays[questionNum].substring(0,half);
	        mean = arrays[questionNum].substring(half+1);
	        questionNum++;
	        
	        outputString = new StringBuffer();
	        for(int i=0; i<english.length(); i++){
	            if(english.charAt(i) == ' '){ 
	                outputString.append(" ");
	            }
	            else{
	                outputString.append("⭐");
	            }
	        }
	        inputString= new StringBuffer();
	    }
	    
	    public String input(){
	        System.out.println("\n\t\t"+outputString+"\n");
	        System.out.println("\t\t=================");
	        System.out.println("\t\t 단어의 의미: "+mean);
	        System.out.println("\t\t 입력한 문자: "+inputString);
	        System.out.println("\t\t 라이프: "+life);
	        System.out.println("\t\t=================");
	        System.out.print("\t\t문자를 입력하세요(0은 PASS!) → ");
	        
	        Scanner sc = new Scanner(System.in);
	        userInput = sc.next().substring(0,1);
	        
	        return userInput;
	    }
	    
	    public void check(){
	        if(inputString.indexOf(userInput) == -1){
	            inputString.append(" "+userInput);
	            checkHiddenString();
	        }
	        else{ 
	            System.err.println("\t\t이미 입력한 문자입니다.");
	            dm.drawMan(life);
	        }
	    }
	    
	    private void checkHiddenString(){
	        boolean truefalse = false;
	        
	        for(int i=0; i<english.length(); i++){
	            if(english.charAt(i)==userInput.charAt(0)){
	                truefalse = true;
	                outputString.setCharAt(i, userInput.charAt(0));
	            }
	        }
	        
	        if(!truefalse) life--;
	        
	        dm.drawMan(life);
	    }
	    
	    public StringBuffer getOutputString(){
	        return outputString;
	    }
	    
	    public String getHiddenString(){
	        return english;
	    }
	    
	    public String getMean(){
	        return mean;
	    }
	    
	    public int getLife(){
	        return life;
	    }
}
