package com.codecool.xvso.model;

import com.codecool.xcso.exception.CellAlreadyHasContentException;
import com.codecool.xcso.exception.CellOutOfRangeException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pgurdek on 12.06.17.
 */
public class Board {
    Cell[][] cells;
    Integer moveCounter;

    public Board() {
        this.cells = new Cell[CellRange.MAXIMAL.getValue()][CellRange.MAXIMAL.getValue()];
    }

    public Cell[][] getCells() {
        return this.cells;
    }

    public Integer getMoveCounter() {
        return null;
    }

    public void setMoveCounter(Integer moveCounter) {
        this.moveCounter = moveCounter;
    }

    public void setMoveCounter(int i) {
    }

    public void init() {
        fillCells();
    }

    private void fillCells() {
        for (Integer row = 0; row < CellRange.MAXIMAL.getValue(); row++) {
            for (Integer col = 0; col < CellRange.MAXIMAL.getValue(); col++) {
                cells[row][col] = new Cell(row + 1, col + 1);
            }
        }
    }

    public void updateCell(Seed nought, int i, int i1) {
    }

    public Boolean hasWon(Seed nought, Integer row, Integer col) throws CellOutOfRangeException, CellAlreadyHasContentException {
        if (!Cell.isRowAndColValid(new int[]{row, col})) {
            throw new CellOutOfRangeException();
        }

        if (cells[row - 1][col - 1].getContent() != Seed.EMPTY) {
            throw new CellAlreadyHasContentException();
        }
        return false;
    }

    public boolean isDraw() {
        return false;
    }

    private Boolean checkColumn(Integer col) {
        List<Cell> column = getColumn(col);
        Cell tempCell = column.get(0);
        for (Cell cell : column) {
            if (cell.getContent().equals(tempCell.getContent()) && !cell.getContent().equals(Seed.EMPTY)) {
                tempCell = cell;
            } else {
                return false;
            }
        }
        return true;
    }

    private Boolean checkRow(Integer rowNumber) {
        Cell[] row = this.getCells()[rowNumber];
        Cell tempCell = row[0];
        for (Cell cell : row) {
            if (cell.getContent().equals(tempCell.getContent()) && !cell.getContent().equals(Seed.EMPTY)) {
                tempCell = cell;
            } else {
                return false;
            }
        }
        return true;
    }

    private List<Cell> getColumn(Integer columnNumber) {
        List<Cell> column = new ArrayList<>();
        for (Cell[] row : this.getCells()) {
            for (Cell cell : row) {
                if (cell.getCol().equals(columnNumber)) {
                    column.add(cell);
                }
            }
        }
        return column;
    }

    private Cell getCell(Integer row, Integer column) {
        return cells[row][column];
    }
}
