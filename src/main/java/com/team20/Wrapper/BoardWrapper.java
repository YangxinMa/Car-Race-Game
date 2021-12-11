package com.team20.Wrapper;

import java.util.List;

public class BoardWrapper {
    public LocationWrapper playerLocation;
    public LocationWrapper pointLocation;
    public List<LocationWrapper> carLocations;
    public boolean[][] hasWalls;

    public static BoardWrapper getBoard() {
        BoardWrapper wrapper = new BoardWrapper();

        return wrapper;
    }
}
