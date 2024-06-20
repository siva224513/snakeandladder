package com.siva224513.snakeandladdergame;



import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter board size: ");
		int boardSize = scanner.nextInt();
		System.out.print("Enter the number of snakes: ");
		int numSnakes = scanner.nextInt();
		int[][] snakes = new int[numSnakes][2];
		for (int i = 0; i < numSnakes; i++) {
			System.out.print("Enter snake head and tail positions:");
			snakes[i][0] = scanner.nextInt();
			snakes[i][1] = scanner.nextInt();
		}

		System.out.print("Enter the number of ladders: ");
		int numLadders = scanner.nextInt();
		int[][] ladders = new int[numLadders][2];
		for (int i = 0; i < numLadders; i++) {
			System.out.print("Enter ladder bottom and top positions:");
			ladders[i][0] = scanner.nextInt();
			ladders[i][1] = scanner.nextInt();
		}

		
		System.out.print("Enter the number of players: ");
		int numPlayers = scanner.nextInt();
		String players[]= new String[numPlayers];
		for (int i = 0; i < numPlayers; i++) {
		
			System.out.print("Enter player name: ");
			players[i] = scanner.next();
			
			
		}
		Board board = new Board(); 
		board.playGame(boardSize,snakes,ladders,players);

	}

}
