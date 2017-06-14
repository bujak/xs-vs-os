package com.codecool.xvso.model;

import com.codecool.xcso.exception.CellAlreadyHasContentException;
import com.codecool.xcso.exception.CellOutOfRangeException;

import java.util.ArrayList;
import java.util.Arrays;
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
        return this.moveCounter;
    }

    public void setMoveCounter(Integer moveCounter) {
        this.moveCounter = moveCounter;
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

    public void init() {
        fillCells();
        setMoveCounter(0);
    }

    private void fillCells() {
        for (Integer row = 0; row < CellRange.MAXIMAL.getValue(); row++) {
            for (Integer col = 0; col < CellRange.MAXIMAL.getValue(); col++) {
                cells[row][col] = new Cell(row + 1, col + 1);
            }
        }
    }

    public void updateCell(Seed seed, Integer row, Integer column) throws CellOutOfRangeException, CellAlreadyHasContentException {
        if (!Cell.isRowAndColValid(new int[]{row, column})) {
            throw new CellOutOfRangeException("Cell index out of range");
        }
        if (cells[row - 1][column - 1].getContent() != Seed.EMPTY) {
            throw new CellAlreadyHasContentException("Cell is not empty");
        }
        this.setMoveCounter(this.getMoveCounter() + 1);
        this.getCell(row, column).setContent(seed);
    }

    public Boolean hasWon(Seed seed, Integer row, Integer col) {
        return checkBoard(seed, row, col);
    }

    private Boolean checkBoard(Seed seed, Integer row, Integer col) {
        return checkColumn(seed, col) ||
                checkRow(seed, row) ||
                checkDiagonal(seed) ||
                checkAntiDiagonal(seed);
    }

    public boolean isDraw(Seed seed, Integer row, Integer column) {
        return (this.getMoveCounter().equals((CellRange.MAXIMAL.getValue() * CellRange.MAXIMAL.getValue())))
                &&
                !this.hasWon(seed, row, column);
    }

    private Boolean checkColumn(Seed seed, Integer col) {
        List<Cell> column = getColumn(col);
        return checkCellContent(seed, column);
    }

    private Boolean checkRow(Seed seed, Integer rowNumber) {
        List<Cell> rowArray = Arrays.asList(this.getCells()[rowNumber - 1]);
        return checkCellContent(seed, rowArray);
    }

    private Boolean checkDiagonal(Seed seed) {
        return checkCellContent(seed, getDiagonal());
    }

    private List<Cell> getDiagonal() {
        List<Cell> diagonal = new ArrayList<>();
        for (int i = 0; i < CellRange.MAXIMAL.getValue(); i++) {
            diagonal.add(cells[i][i]);
        }
        return diagonal;
    }


    private Boolean checkAntiDiagonal(Seed seed) {
        return checkCellContent(seed, getAniDiagonal());
    }

    private List<Cell> getAniDiagonal() {
        List<Cell> anitDiagonal = new ArrayList<>();
        for (int i = 0; i < CellRange.MAXIMAL.getValue(); i++) {
            anitDiagonal.add(cells[i][CellRange.MAXIMAL.getValue() - i - 1]);
        }
        return anitDiagonal;
    }


    private Boolean checkCellContent(Seed seed, List<Cell> cells) {
        for (Cell cell : cells) {
            if (!cell.getContent().equals(seed) || cell.getContent().equals(Seed.EMPTY)) {
                return false;
            }
        }
        return true;
    }

    private Cell getCell(Integer row, Integer column) {
        return this.getCells()[row - 1][column - 1];
    }
}
