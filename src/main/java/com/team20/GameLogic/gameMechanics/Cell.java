package com.team20.GameLogic.gameMechanics;

public class Cell {
    private int row;
    private int column;
    private boolean wallOrNot = false;
    public Cell(int i, int j){
        this.row = i;
        this.column = j;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isWallOrNot() {
        return wallOrNot;
    }

    public void setWallOrNot(boolean wallOrNot) {
        this.wallOrNot = wallOrNot;
    }
}
