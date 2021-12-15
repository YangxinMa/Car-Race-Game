package com.team20.GameLogic.gameMechanics;

public class Obstacle extends Cell{
    private final int point = 5;

    public Obstacle(int i, int j) {
        super(i, j);
    }

    public int getPoint() {
        return point;
    }

    //    Ref： Based on our CMPT 213 Assignment to modify
    public void teleport(Map map){
        int height = map.getHeight();
        int width = map.getWidth();


        int randomRowInInt;
        int randomColumnInInt;
        do {
            double randomRow = (double)(height - 2) * Math.random() + 1;
            randomRowInInt = (int) randomRow;
            double randomColumn = (double)(width - 2) * Math.random() + 1;
            randomColumnInInt = (int) randomColumn;
            this.setColumn(randomColumnInInt);
            this.setRow(randomRowInInt);
        }while (map.getMapAt(randomRowInInt,randomColumnInInt));
    }

}
