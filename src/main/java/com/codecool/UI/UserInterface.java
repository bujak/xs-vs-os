package com.codecool.UI;

import com.codecool.xvso.model.Cell;
import com.codecool.xvso.model.CellRange;
import com.codecool.xvso.model.Seed;

import java.util.Scanner;

/**
 * Created by pgurdek on 14.06.17.
 */
public class UserInterface {
    public void showMenu() {
        System.out.println(
                "Main Menu:\n " +
                        "Pick Game Mode \n" +
                        "1.Player vs Player\n" +
                        "2.Player vs Ai\n"
        );
    }

    public String getInput() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter command ");
        String getInput = scan.next();
        return getInput;
    }

    public String getInput(String message) {
        Scanner scan = new Scanner(System.in);
        System.out.println(message);
        String getInput = scan.next();
        return getInput;
    }

    public void showPlayerTurn(Seed currentPlayer) {
        System.out.println(currentPlayer + " picks (row,column): ");
    }

    public void draw() {
        System.out.println("DRAW!!");
    }

    public void showWinner(Seed currentPlayer) {
        System.out.println("Winner is : " + currentPlayer);
    }

    public void showGameBoard(Cell[][] board) {
        int lenght = CellRange.MAXIMAL.getValue() * CellRange.MAXIMAL.getValue() + 1;
        String separator = new String(new char[lenght]).replace("\0", "-");
        for (Cell[] row : board) {
            System.out.println(separator);
            System.out.print("|");
            for (Cell cell : row) {
                System.out.print(cell.getContent().getValue());
                System.out.print(" |");
            }
            System.out.println();
        }
        System.out.println(separator);
    }
}
