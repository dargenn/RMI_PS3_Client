package io.dargenn.client;

import io.dargenn.common.SecurityUtils;
import io.dargenn.external.GameService;
import io.dargenn.external.Player;
import io.dargenn.external.TicTacToeType;
import lombok.SneakyThrows;

import java.rmi.Naming;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Client {
    @SneakyThrows
    public static void main(String[] args) {
        SecurityUtils.prepareSecurity();
        GameService gameService = getGameService();
        showWelcomeScreen();
        String playerName = getPlayerName();
        Player player = new Player(playerName);
        if(gameService.join(player)) {
            System.out.println("Player " + playerName + " joined. Waiting for game to start.");
            player.setNumber(gameService.getPlayerNumber(playerName));
            String symbol = player.getNumber() == 1 ? "O" : "X";
            player.setTicTacToeType(TicTacToeType.valueOf(player.getNumber() == 1 ? "TIC" : "TAC"));
            System.out.println("Your player number is: " + player.getNumber() + ", you play with: " + symbol);
        } else {
            System.out.println("Game is currently full, try again.");
        }

        int moveCount = -1;
        while(true) {
            TimeUnit.SECONDS.sleep(1);

            if(gameService.isGameOver()) {
                System.out.println("GAME IS OVER!!!");
                gameService.destroy();
                break;
            }

            if(gameService.getMoveCount() == 9) {
                System.out.println("GAME OVER, DRAW!");
                gameService.destroy();
                break;
            }

            if(gameService.isGameActive()) {
                if(moveCount != gameService.getMoveCount()) {
                    if (player.getTicTacToeType() == gameService.getActivePlayerNumber()) {
                        while(true) {
                            printBoard(gameService);
                            System.out.println("It's your move! Insert x and y: ");
                            Scanner scanner = new Scanner(System.in);
                            int x = scanner.nextInt();
                            int y = scanner.nextInt();
                            if(gameService.move(x, y, player)) {
                                break;
                            } else {
                                System.out.println("!!!!!!!!!!!!! WRONG X AND/OR Y, TRY AGAIN !!!!!!!!!!!!!");
                            }
                        }
                    } else {
                        System.out.println("It's your opponents turn. Please wait!");
                        printBoard(gameService);
                    }
                    moveCount++;
                }
            }
        }
    }

    private static String getPlayerName() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static void showWelcomeScreen() {
        System.out.println("-----------------------------------------");
        System.out.println("Welcome to best Tic Tac Toe!");
        System.out.println("To start, please enter your name: ");
    }

    @SneakyThrows
    private static void printBoard(GameService gameService) {
        System.out.println("Current board state:");
        System.out.println(gameService.getGameBoard());
    }

    @SneakyThrows
    private static GameService getGameService() {
        return (GameService) Naming.lookup("//192.168.0.103/TicTacToe");
    }
}
