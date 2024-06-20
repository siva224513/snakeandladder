package com.siva224513.snakeandladdergame;

import java.util.Scanner;
import java.util.Random;

public class BrickBreaker {

    private static final int WIDTH = 40;
    private static final int HEIGHT = 20;
    private static final char EMPTY = ' ';
    private static final char BRICK = '#';
    private static final char PADDLE = '=';
    private static final char BALL = 'O';

    private static char[][] screen = new char[HEIGHT][WIDTH];
    private static int paddleY = HEIGHT - 1;
    private static int paddleX = WIDTH / 2 - 2;
    private static int ballX = WIDTH / 2;
    private static int ballY = HEIGHT - 2;
    private static int ballDirectionX = -1;
    private static int ballDirectionY = -1;
    private static boolean gameOver = false;
    private static int bricksRemaining = 0;

    public static void main(String[] args) {
        initializeScreen();
        Scanner scanner = new Scanner(System.in);

        Thread inputThread = new Thread(() -> {
            while (!gameOver) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("a")) {
                    movePaddleLeft();
                } else if (input.equalsIgnoreCase("d")) {
                    movePaddleRight();
                }
            }
        });
        inputThread.start();

        while (!gameOver) {
            updateGame();
            renderScreen();

            try {
                Thread.sleep(100);  // Adjust to control game speed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Game Over!");
        scanner.close();
    }

    private static void initializeScreen() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                screen[i][j] = EMPTY;
            }
        }

        // Initialize bricks
        Random rand = new Random();
        for (int i = 0; i < HEIGHT / 3; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (rand.nextBoolean()) {
                    screen[i][j] = BRICK;
                    bricksRemaining++;
                }
            }
        }

        // Initialize paddle
        for (int i = paddleX; i < paddleX + 5; i++) {
            screen[paddleY][i] = PADDLE;
        }

        // Initialize ball
        screen[ballY][ballX] = BALL;
    }

    private static void updateGame() {
        // Move the ball
        screen[ballY][ballX] = EMPTY;
        ballX += ballDirectionX;
        ballY += ballDirectionY;

        // Check for collisions with walls
        if (ballX <= 0 || ballX >= WIDTH - 1) {
            ballDirectionX *= -1;
        }
        if (ballY <= 0) {
            ballDirectionY *= -1;
        }

        // Check for collisions with the paddle
        if (ballY == paddleY - 1 && ballX >= paddleX && ballX < paddleX + 5) {
            ballDirectionY *= -1;
        }

        // Check for collisions with bricks
        if (screen[ballY][ballX] == BRICK) {
            screen[ballY][ballX] = EMPTY;
            bricksRemaining--;
            ballDirectionY *= -1;
        }

        // Check for game over
        if (ballY >= HEIGHT - 1) {
            gameOver = true;
        }

        screen[ballY][ballX] = BALL;

        // Check for win
        if (bricksRemaining == 0) {
            gameOver = true;
            System.out.println("You win!");
        }
    }

    private static void renderScreen() {
        // Clear the screen
        System.out.print("\033[H\033[2J");
        System.out.flush();

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                System.out.print(screen[i][j]);
            }
            System.out.println();
        }

        System.out.println("Bricks remaining: " + bricksRemaining);
        System.out.println("Use 'A' to move left, 'D' to move right.");
    }

    private static void movePaddleLeft() {
        if (paddleX > 0) {
            for (int i = paddleX; i < paddleX + 5; i++) {
                screen[paddleY][i] = EMPTY;
            }
            paddleX--;
            for (int i = paddleX; i < paddleX + 5; i++) {
                screen[paddleY][i] = PADDLE;
            }
        }
    }

    private static void movePaddleRight() {
        if (paddleX < WIDTH - 5) {
            for (int i = paddleX; i < paddleX + 5; i++) {
                screen[paddleY][i] = EMPTY;
            }
            paddleX++;
            for (int i = paddleX; i < paddleX + 5; i++) {
                screen[paddleY][i] = PADDLE;
            }
        }
    }
}
