package com.team20.Wrapper;

import java.util.ArrayList;
import java.util.List;

public class GameWrapper {
    public int gameId;
    public boolean isGameLost;
    public boolean stopByUser;
    public int currentScore;

    public BoardWrapper board;

    public static GameWrapper getGame(int id) {
        GameWrapper wrapper = new GameWrapper();
        wrapper.gameId = id;

        return wrapper;
    }
}
