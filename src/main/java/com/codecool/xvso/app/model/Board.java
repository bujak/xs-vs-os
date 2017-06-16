package com.codecool.xvso.app.model;

import com.codecool.xvso.app.exception.CellAlreadyHasContentException;
import com.codecool.xvso.app.exception.CellOutOfRangeException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pgurdek on 12.06.17.
 */
public class Board {
    private Cell[][] cells;
    private Integer moveCounter;
    private final int ARRAYMODIFIER = CellRange.ARRAYMODIFIER.getValue();

    public Board() {
        this.cells = new Cell[CellRange.MAXIMAL.getValue()][CellRange.MAXIMAL.getValue()];
    }

    public Cell[][] getCells() {
        return this.cells;
    }

    private Integer getMoveCounter() {
        return this.moveCounter;
    }

    private void setMoveCounter(Integer moveCounter) {
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

    void init() {
        fillCells();
        setMoveCounter(0);
    }

    private void fillCells() {
        for (Integer row = 0; row < CellRange.MAXIMAL.getValue(); row++) {
            for (Integer column = 0; column < CellRange.MAXIMAL.getValue(); column++) {
                cells[row][column] = new Cell(row + ARRAYMODIFIER, column + ARRAYMODIFIER);
            }
        }
    }

    void updateCell(Seed seed, Integer row, Integer column) throws CellOutOfRangeException, CellAlreadyHasContentException {
        if (!Cell.isRowAndColValid(new int[]{row, column})) {
            throw new CellOutOfRangeException("Cell index must be integer between " + CellRange.MINIMAL.getValue() + " - " + CellRange.MAXIMAL.getValue());
        }
        if (cells[row - ARRAYMODIFIER][column - ARRAYMODIFIER].getContent() != Seed.EMPTY) {
            throw new CellAlreadyHasContentException("Cell is not empty");
        }
        this.setMoveCounter(this.getMoveCounter() + 1);
        this.getCell(row, column).setContent(seed);
    }

    Boolean hasWon(Seed seed, Integer row, Integer column) {
        return checkBoard(seed, row, column);
    }

    private Boolean checkBoard(Seed seed, Integer row, Integer column) {
        return checkColumn(seed, column) ||
                checkRow(seed, row) ||
                checkDiagonal(seed) ||
                checkAntiDiagonal(seed);
    }

    boolean isDraw(Seed seed, Integer row, Integer column) {
        return (this.getMoveCounter().equals((CellRange.MAXIMAL.getValue() * CellRange.MAXIMAL.getValue())))
                &&
                !this.hasWon(seed, row, column);
    }

    private Boolean checkColumn(Seed seed, Integer column) {
        List<Cell> columnContent = getColumn(column);
        return checkCellContent(seed, columnContent);
    }

    private Boolean checkRow(Seed seed, Integer rowNumber) {
        List<Cell> rowArray = Arrays.asList(this.getCells()[rowNumber - ARRAYMODIFIER]);
        return checkCellContent(seed, rowArray);
    }

    private Boolean checkDiagonal(Seed seed) {
        return checkCellContent(seed, getDiagonalCells());
    }

    private List<Cell> getDiagonalCells() {
        List<Cell> diagonal = new ArrayList<>();
        for (int i = 0; i < CellRange.MAXIMAL.getValue(); i++) {
            diagonal.add(cells[i][i]);
        }
        return diagonal;
    }


    private Boolean checkAntiDiagonal(Seed seed) {
        return checkCellContent(seed, getAniDiagonalCells());
    }

    private List<Cell> getAniDiagonalCells() {
        List<Cell> antiDiagonal = new ArrayList<>();
        for (int i = 0; i < CellRange.MAXIMAL.getValue(); i++) {
            antiDiagonal.add(cells[i][CellRange.MAXIMAL.getValue() - i - ARRAYMODIFIER]);
        }
        return antiDiagonal;
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
        return this.getCells()[row - ARRAYMODIFIER][column - ARRAYMODIFIER];
    }
}
