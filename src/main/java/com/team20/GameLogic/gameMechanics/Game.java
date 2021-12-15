package com.team20.GameLogic.gameMechanics;

public class Game {

    private Map map = new Map();
    private Car player;
    private Cell[][] board;
    private final int oppositeNum = 5;
    private final OppositeCar[] opposites = new OppositeCar[oppositeNum];
    private final Obstacle score = new Obstacle(1,1);
    private int point = 0;
    private boolean quit = false;
    public Game (){
        this.formUp();
    }

    public boolean isQuit() {
        return quit;
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }

    public int getPoint() {
        return point;
    }

    public int getOppositeNum() {
        return oppositeNum;
    }

    public OppositeCar[] getOpposites() {
        return opposites;
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

    public Obstacle getScore() {
        return score;
    }
    public Cell getBoardAt(int i, int j) {
        return board[i][j];
    }

    public boolean isOppositeOrNot(int row, int column){
        for(int i = 0; i < oppositeNum; i++){
            if(opposites[i].getRow() == row && opposites[i].getColumn() == column){
                return true;
            }
        }
        return false;
    }
    public void moveOpposites(){
        for(int i = 0; i < oppositeNum; i++){
            opposites[i].forceOppositeCarMove();
        }
    }
    public void restartOpposites(){
        for(int i = 0; i < oppositeNum; i++){
            if(opposites[i].getRow() == map.getHeight())
                opposites[i].restart(map);
        }
    }
    public void pointGotten(){
        if(player.getColumn() == score.getColumn() && player.getRow() == score.getRow()) {
            this.point += score.getPoint();
            score.teleport(map);
        }
    }

    //    Ref： Based on our CMPT 213 Assignment to modify
    public boolean CarCrash(){
        if(player.getColumn() == 0
                || player.getColumn() == map.getWidth() - 1){
//                || player.getRow() == 0
//                || player.getRow() == map.getHeight() - 1) {
//            System.out.println("You have walked over the range!!");
            return false;
        }
        else if(isOppositeOrNot(player.getRow(), player.getColumn())){
//            System.out.println("Crash");
            return false;
        }
        else if(this.isQuit()){
//            System.out.println("Quit!!");
            return false;
        }
        return true;
    }

    //    Ref： Based on our CMPT 213 Assignment to modify
    public boolean canMoveOrNot(String c, Car player)
    {
        if(c.length() > 1)
            return true;
        switch (c)
        {
            case "w":
            case "W":
                if((player.getRow() - 1) < 0) {
                    return false;
                }
                return !map.getMapAt(player.getRow() - 1, player.getColumn());

            case "S":
            case "s":
                if((player.getRow() + 1) > map.getHeight() - 1) {
                    return false;
                }
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
        int startRow = map.getHeight() - 2;
        int startCol = map.getWidth() / 2;
        this.player = new Car(startRow, startCol);
        for(int i = 0; i < oppositeNum; i++){
            opposites[i] = new OppositeCar(this.map);
        }
        score.teleport(this.map);
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
    //To decide which move should be doing next.
    private int moveOrQuit(String s)
    {
        switch (s)
        {
            case "W":
            case "w":
            case "A":
            case "a":
            case "S":
            case "s":
            case "D":
            case "d":
                return 1;
            case "q":
            case "Q":
                return 2;
        }
        return 0;
    }



    public void playGame(String s) {
        switch (this.moveOrQuit(s)) {
            case 1:
                player.chooseNextMove(s);
                moveOpposites();
                restartOpposites();
                pointGotten();
                break;
            case 2:
                this.setQuit(true);
                break;
        }
    }


}
