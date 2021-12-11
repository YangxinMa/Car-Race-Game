package com.team20.GameLogic.gameMechanics;

public class Map {
    private final int width = 11;
    private final int height = 11;
    private final boolean[][] map = new boolean[height][width];

    public Map(){
        this.buildWall();
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public boolean[][] getMap() {
        return map;
    }
    private void setMapAt(int i, int j, boolean bool){
        this.map[i][j] = bool;
    }
    public boolean getMapAt(int i, int j){
        return this.map[i][j];
    }
    private void buildWall(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                setMapAt(i,j,(j == 0 || j == width - 1));
            }
        }
    }
}
