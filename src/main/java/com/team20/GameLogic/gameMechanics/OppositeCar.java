package com.team20.GameLogic.gameMechanics;

public class OppositeCar extends Cell{
    public OppositeCar(Map map) {
        super(0, 0);
        int width = map.getWidth();
        int height = map.getHeight();
        int i = 0;
        int j = 0;
        while(map.getMapAt(i, j)){
            i = (int) (Math.random() * height);
            j = (int) (Math.random() * width);
        }
        this.setRow(i);
        this.setColumn(j);

    }
    public void forceOppositeCarMove(){
        this.setRow(this.getRow() + 1);
    }
    public void restart(Map map){
        if(this.getRow() == map.getHeight()){
            this.setRow(0);
            int j = 0;
            while(map.getMapAt(0, j)){
//            i = (int) (Math.random() * height);
                j = (int) (Math.random() * map.getWidth());
            }
            this.setColumn(j);
        }
    }
}
