package com.siva224513.snakeandladdergame;



import java.util.Random;

public class Board {
	
	

	public void playGame(int boardSize, int[][] snakes, int[][] ladders, String[] players) {
	    int[] playerPositions = new int[players.length];
	    Random random = new Random();

	    while (true) {
	        for (int i = 0; i < players.length; i++) {
	            int consecutiveSixesCount = 0;

	            while (true) {
	                int roll = random.nextInt(6) + 1;

	                
	                if (roll == 6) {
	                    consecutiveSixesCount++;
	                    if (consecutiveSixesCount == 3) {
	                        System.out.println(players[i] + " rolled three consecutive sixes. No turn for this user.");
	                        break; // End the turn
	                    }
	                } else {
	                    consecutiveSixesCount = 0;
	                }

	                int currentPosition = playerPositions[i];
	                int newPosition = currentPosition + roll;

	                
	              
	                if (newPosition > boardSize * boardSize) {
	                    continue;
	                }

	               
	                while (contains(snakes, newPosition) || contains(ladders, newPosition)) {
	                    for (int[] snake : snakes) {
	                        if (snake[0] == newPosition) {
	                            newPosition = snake[1];
	                            break;
	                        }
	                    }
	                    for (int[] ladder : ladders) {
	                        if (ladder[0] == newPosition) {
	                            newPosition = ladder[1];
	                            break;
	                        }
	                    }
	                }

	                playerPositions[i] = newPosition;
	                System.out.println(players[i] + " rolled a " + roll + " and moved from " + currentPosition + " to " + newPosition + ".");

	                if (newPosition == boardSize * boardSize) {
	                    System.out.println(players[i] + " wins the game!");
	                    return;
	                }

	               
	                if (roll != 6) {
	                    break;
	                }
	            }
	        }
	    }
	}

	

	public static boolean contains(int[][] array, int value) {
        for (int[] row : array) {
            if (row[0] == value) {
                return true;
            }
        }
        return false;
    }

}
