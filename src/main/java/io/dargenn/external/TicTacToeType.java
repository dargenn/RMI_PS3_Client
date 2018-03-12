package io.dargenn.external;

public enum TicTacToeType {
    TIC("O"),
    TAC("X");

    private String symbol;


    TicTacToeType(String x) {

    }

    public String getSymbol() {
        return this.symbol;
    }

    public TicTacToeType negate() {
        return this == TIC ? TAC : TIC;
    }
}