package com.team20.GameLogic.ui;

import com.team20.GameLogic.gameMechanics.Game;

import java.util.Scanner;

public class UI {
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    private final Game game = new Game();
    private final Scanner input = new Scanner(System.in);
    public void printMap()
    {
        for(int i = 0; i < game.getMap().getHeight(); i++)
        {
            for(int j = 0; j < game.getMap().getWidth(); j++)
            {
                if(game.getBoardAt(i,j).isWallOrNot())
                {
                    System.out.print(ANSI_BLUE + "# ");
                }
                else
                {
                    if(game.getBoardAt(i,j).getRow() == game.getPlayer().getRow() &&
                            game.getBoardAt(i,j).getColumn() == game.getPlayer().getColumn())
                    {
                        if(!game.CarCrash()){
                            System.out.print(ANSI_YELLOW + "X ");
                        }
                        else{
                            System.out.print(ANSI_YELLOW + "@ ");
                        }

                    }
                    else if(game.isOppositeOrNot(i,j))
                    {
                        System.out.print(ANSI_RED + "! ");
                    }
                    else if(game.getBoardAt(i,j).getRow() == game.getScore().getRow() &&
                            game.getBoardAt(i,j).getColumn() == game.getScore().getColumn()){
                        System.out.print(ANSI_WHITE + "$ ");
                    }
                    else
                    {
                        System.out.print("  ");
                    }
                }
            }

            System.out.println();
        }
        System.out.println("-----------------------------------------------------------");
    }
    public boolean ValidInput(String s) {
        switch (s) {
            case "W":
            case "w":
            case "A":
            case "a":
            case "S":
            case "s":
            case "D":
            case "d":
                return game.canMoveOrNot(s, game.getPlayer());
            case "q":
            case "Q":
                return true;
        }
        return false;
    }
    public void interFace(){

        do
        {
            System.out.println("You have got " + game.getPoint() + " Points So Far!!");
            this.printMap();
            System.out.print("Enter your Move [WASD?], Q to Quit!: ");
            String operation = input.nextLine();
            while(!this.ValidInput(operation))
            {
                if(!game.canMoveOrNot(operation,game.getPlayer()))
                {
                    System.out.println("Invalid move: you cannot move through walls!");
                    break;
                }
                else
                {
                    System.out.println("Invalid move. Please enter just A (left), S (down), D (right), or W (up).");
                }
                System.out.print("Enter your Move [WASD?], Q to Quit: ");
                operation = input.nextLine();

            }
            game.playGame(operation);
        }while (game.CarCrash());

        System.out.println("I'm sorry, you have crashed!");
        System.out.println(" ");
        this.printMap();
        System.out.println("Your Final Score is " + game.getPoint() + " Points!!");
        System.out.println("GAME OVER; please try again.");


    }
}
