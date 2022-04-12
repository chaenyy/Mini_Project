package com.kh.chm.miniproject.game.hangman;

public class DrawMan {
	
	 void drawMan(int life){
	        switch(life){
	        case 0:
	            System.out.println("\t\t  _______ ");
	            System.out.println("\t\t  |     | ");
	            System.out.println("\t\t  |     | ");
	            System.out.println("\t\t        | ");
	            System.out.println("\t\t        | ");
	            System.out.println("\t\t        | ");
	            break;
	        case 1:
	            System.out.println("\t\t  _______ ");
	            System.out.println("\t\t  |     | ");
	            System.out.println("\t\t  |     | ");
	            System.out.println("\t\t  ●     | ");
	            System.out.println("\t\t        | ");
	            System.out.println("\t\t        | ");
	            break;
	        case 2:
	            System.out.println("\t\t  _______ ");
	            System.out.println("\t\t  |     | ");
	            System.out.println("\t\t  |     | ");
	            System.out.println("\t\t  ●     | ");
	            System.out.println("\t\t  |     | ");
	            System.out.println("\t\t        | ");
	            break;
	        case 3:
	            System.out.println("\t\t  _______ ");
	            System.out.println("\t\t  |     | ");
	            System.out.println("\t\t  |     | ");
	            System.out.println("\t\t  ●     | ");
	            System.out.println("\t\t -|     | ");
	            System.out.println("\t\t        | ");
	            break;
	        case 4:
	            System.out.println("\t\t  _______ ");
	            System.out.println("\t\t  |     | ");
	            System.out.println("\t\t  |     | ");
	            System.out.println("\t\t  ●     | ");
	            System.out.println("\t\t -|-    | ");
	            System.out.println("\t\t        | ");
	            break;
	        case 5:
	            System.out.println("\t\t  _______ ");
	            System.out.println("\t\t  |     | ");
	            System.out.println("\t\t  |     | ");
	            System.out.println("\t\t  ●     | ");
	            System.out.println("\t\t -|-    | ");
	            System.out.println("\t\t  |     | ");
	            System.out.println("\t\t / \\    | ");
	        }
	    }
}
