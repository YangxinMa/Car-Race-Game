package com.team20.Model.gameMechanics;

public class Map {
    private final int width = 30;
    private final int height = 30;
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

    public void printMap(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if(getMapAt(i,j))
                    System.out.print("#");
                else{
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------------");
    }
    private void buildWall(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                setMapAt(i,j,(j == 0 || j == width - 1));
            }
        }
    }

    public static void main(String[] args) {
        Map map = new Map();
        map.buildWall();
        map.printMap();
    }
}
