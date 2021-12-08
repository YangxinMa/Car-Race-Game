package com.team20.Model.gameMechanics;

public class Game {

    private Map map = new Map();
    private Car player;
    private Cell[][] board;
    private final int oppositeNum = 5;
    private final OppositeCar[] opposites = new OppositeCar[oppositeNum];
    public Game (){
        this.formUp();
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Car getPlayer() {
        return player;
    }

    public Cell getBoardAt(int i, int j) {
        return board[i][j];
    }

    public int getOppositeNum() {
        return oppositeNum;
    }
    public boolean isOppositeOrNot(int row, int column){
        for(int i = 0; i < oppositeNum; i++){
            if(opposites[i].getRow() == row && opposites[i].getColumn() == column){
                return true;
            }
        }
        return false;
    }
    public boolean isGameLose(){return true;}
    public boolean CarCrash(){
        if(player.getColumn() == 0 || player.getColumn() == map.getWidth() - 1)
            return false;
        return true;
    }

    public boolean canMoveOrNot(String c, Car player)
    {
        if(c.length() > 1)
            return true;
        switch (c)
        {
            case "w":
            case "W":
                return !map.getMapAt(player.getRow() - 1, player.getColumn());
            case "S":
            case "s":
                return !map.getMapAt(player.getRow() + 1, player.getColumn());
            case "A":
            case "a":
                return !map.getMapAt(player.getRow(), player.getColumn() - 1);
            default:
                return !map.getMapAt(player.getRow(), player.getColumn() + 1);
        }
    }


    private void formUp()
    {
        this.board = new Cell[map.getHeight()][map.getWidth()];
        int height = map.getHeight();
        int width = map.getWidth();
        int startRow = map.getHeight() / 2;
        int startCol = map.getWidth() / 2;
        this.player = new Car(startRow, startCol);
        for(int i = 0; i < oppositeNum; i++){
            opposites[i] = new OppositeCar(this.map);
        }

        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                board[i][j] = new Cell(i,j);
                if(map.getMapAt(i,j))
                    board[i][j].setWallOrNot(true);
            }
        }
    }

    public void playGame(String s) {
        player.chooseNextMove(s);
    }


}
