package io.dargenn.external;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameService extends Remote {
    boolean join(Player player) throws RemoteException;

    boolean move(int x, int y, Player player) throws RemoteException;

    void disconnect(Player player) throws RemoteException;

    boolean isGameActive() throws RemoteException;

    int getPlayerNumber(String name) throws RemoteException;

    TicTacToeType getActivePlayerNumber() throws RemoteException;

    int getMoveCount() throws RemoteException;

    String getGameBoard() throws RemoteException;

    boolean isGameOver() throws RemoteException;

    void destroy() throws RemoteException;
}
