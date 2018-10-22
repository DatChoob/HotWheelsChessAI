package com.josh;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {

//        runAIvsAI(1);
//        if (true) return;
//        start game
        Scanner scanner = new Scanner(System.in);

        Boolean playerStarts = null;
        do {
            System.out.println("Do you, the human, want to go first? Y/N");
            String input = scanner.nextLine();

            if (StringUtils.trimToEmpty(input).equalsIgnoreCase("Y")) {
                playerStarts = true;
            } else if (StringUtils.trimToEmpty(input).equalsIgnoreCase("N")) {
                playerStarts = false;
            }
        } while (playerStarts == null);


        GameBoard board = new GameBoard();
        board.init();


        Game controller = new Game(board, playerStarts);
        controller.play();

    }

    private static void runAIvsAI(int numGames) {
        final AtomicInteger gamesPlayed = new AtomicInteger(0);

        final AtomicInteger userWon = new AtomicInteger(0);
        final AtomicInteger aiWon = new AtomicInteger(0);
        List<Thread> threads = new ArrayList<Thread>();
        long startTime = System.nanoTime();
        while (numGames-- > 0) {
            Thread t = new Thread(new Runnable() {

                public void run() {
                    GameBoard board = new GameBoard();
                    board.init();


                    Game controller = new Game(board, new Random().nextBoolean());
                    controller.play();
                    gamesPlayed.incrementAndGet();

                    if (controller.userWon()) {
                        userWon.incrementAndGet();
                        System.out.println("User won game " + gamesPlayed);

                    } else if (controller.aiWon()) {
                        aiWon.incrementAndGet();
                        System.out.println("AI won game " + gamesPlayed);
                    }
                    System.out.println(Thread.currentThread().getName());
                    controller.board.printBoardA();
//                try {
//                    Thread.currentThread().sleep(1000L);
//                } catch (Exception e) {
//                }
                }

            });
            threads.add(t);
            t.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();

            } catch (Exception e) {
            }
        }


        System.out.println("Finished (ms): " + (System.nanoTime() - startTime) / 1000000.0);
        System.out.println("Total Games played " + gamesPlayed.get());
        System.out.println("User Won: " + userWon.get() + "\t AI Won: " + aiWon.get());


    }

}
