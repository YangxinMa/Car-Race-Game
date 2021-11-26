package com.team20.Model.gameMechanics;

public class Car extends Cell{
    public Car(int i, int j) {
        super(i, j);
    }
    public void chooseNextMove(String direction){
        switch (direction){
            case "W":
            case "w":
                this.setRow(this.getRow() - 1);
                break;
            case "S":
            case "s":
                this.setRow(this.getRow() + 1);
                break;
            case "A":
            case "a":
                this.setColumn(this.getColumn() - 1);
                break;
            default:
                this.setColumn(this.getColumn() + 1);
                break;
        }
    }
    public void printCar(){
        System.out.println("This Car is at :" + getRow() + " Row" + getColumn() + " Column");
    }
}
