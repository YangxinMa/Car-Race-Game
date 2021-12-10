package com.team20.Model.gameMechanics;

public class Obstacle extends Cell{
    private final int point = 5;

    public Obstacle(int i, int j) {
        super(i, j);
    }

    public int getPoint() {
        return point;
    }

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
