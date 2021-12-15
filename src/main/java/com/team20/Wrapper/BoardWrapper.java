package com.team20.Wrapper;

import com.team20.GameLogic.gameMechanics.Game;
import com.team20.GameLogic.gameMechanics.OppositeCar;

import java.util.List;

/**
 *  Ref： Based on our CMPT 213 Assignment to develop and adjust the details to fix out project
 */

public class BoardWrapper {
    public int boardWidth;
    public int boardHeight;
    public LocationWrapper playerLocation;
    public LocationWrapper pointLocation;
    public List<LocationWrapper> carLocations;
    public boolean[][] hasWalls;

    public static BoardWrapper getBoard(Game game) {
        BoardWrapper wrapper = new BoardWrapper();
        // Populate this object!
        wrapper.boardWidth = game.getMap().getWidth();
        wrapper.boardHeight = game.getMap().getHeight();
        wrapper.playerLocation = LocationWrapper.getOneCellLocation(game.getPlayer().getRow(), game.getPlayer().getColumn());
        wrapper.pointLocation = LocationWrapper.getOneCellLocation(game.getScore().getRow(), game.getScore().getColumn());
        OppositeCar[] cars = game.getOpposites();
        int size = game.getOppositeNum();
        int[] X = new int[size];
        int[] Y = new int[size];
        for(int i = 0; i < size; i++){
            X[i] = cars[i].getRow();
            Y[i] = cars[i].getColumn();
        }
        wrapper.carLocations = LocationWrapper.getCellsLocations(X, Y);
        wrapper.hasWalls = game.getMap().getMap();

        return wrapper;
    }
}
