package io.dargenn.external;

import lombok.Data;

import java.io.Serializable;

@Data
public class Player implements Serializable {
    public static final long serialVersionUID = 1L;

    private int number;
    private String name;
    private TicTacToeType ticTacToeType;
    private GameInfo gameInfo = new GameInfo();

    private Player() {
    }

    public Player(String name) {
        this.name = name;
    }

    public void notifyGameOver(int playerNumber) {
        this.gameInfo.setWinner(playerNumber);
        this.gameInfo.setGameOver(true);
    }
}