package io.dargenn.external;

import lombok.Data;

import java.io.Serializable;

@Data
public class GameInfo implements Serializable {
    private int winner;
    private boolean gameOver;
}
