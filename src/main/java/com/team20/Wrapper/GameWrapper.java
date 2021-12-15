package com.team20.Wrapper;

import com.team20.GameLogic.gameMechanics.Game;

import java.util.ArrayList;
import java.util.List;

/**
 *  Ref： Based on our CMPT 213 Assignment to develop and adjust the details to fix out project
 */

public class GameWrapper {
    public int gameId;
    public boolean isGameLost;
    public boolean stopByUser;
    public int currentScore;

    public BoardWrapper board;

    public static GameWrapper getGame(Game game, int id) {
        GameWrapper wrapper = new GameWrapper();
        wrapper.gameId = id;
        wrapper.isGameLost = !game.CarCrash();
        wrapper.currentScore = game.getPoint();
        wrapper.stopByUser = game.isQuit();
        wrapper.board = BoardWrapper.getBoard(game);
        return wrapper;
    }
}
