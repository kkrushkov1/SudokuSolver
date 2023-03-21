package com.example.sudokusolver.services;

import com.example.sudokusolver.exceptions.InvalidInputException;
import com.example.sudokusolver.models.SudokuBoard;
import com.example.sudokusolver.services.contracts.SudokuService;
import org.springframework.stereotype.Service;

@Service
public class SudokuServiceImpl implements SudokuService {

    private static final int BOARD_SIZE = 9;
    private static final int BOX_SIZE = 3;


    public void solve(int[][] board) {
        if (isValidSudoku(board)) {
            SudokuBoard sudokuBoard = new SudokuBoard();
            if (solveSudoku(board)) {
                sudokuBoard.setPuzzle(board);
            } else {
                throw new InvalidInputException("The puzzle has no solution.");
            }
        } else {
            throw new InvalidInputException("The puzzle is not valid.");
        }
    }

    public boolean solveSudoku(int[][] board) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= BOARD_SIZE; num++) {
                        if (isPossible(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveSudoku(board)) {
                                return true;
                            } else {
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }

            }
        }
        return true;
    }

    public boolean isPossible(int[][] board, int row, int col, int num) {
        return isPossibleRow(board, row, num) &&
                isPossibleCol(board, col, num) &&
                isPossibleBox(board, row, col, num);
    }

    public boolean isPossibleRow(int[][] board, int row, int num) {
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (board[row][col] == num) {
                return false;
            }
        }
        return true;
    }

    public boolean isPossibleCol(int[][] board, int col, int num) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (board[row][col] == num) {
                return false;
            }
        }
        return true;
    }

    public boolean isPossibleBox(int[][] board, int row, int col, int num) {
        int boxRow = row - row % BOX_SIZE;
        int boxCol = col - col % BOX_SIZE;
        for (int r = boxRow; r < boxRow + BOX_SIZE; r++) {
            for (int c = boxCol; c < boxCol + BOX_SIZE; c++) {
                if (board[r][c] == num) {
                    return false;
                }
            }
        }
        return true;
    }


    public boolean isValidSudoku(int[][] board) {

        for (int row = 0; row < BOARD_SIZE; row++) {
            if (!isValidRow(board, row)) {
                return false;
            }
        }

        for (int col = 0; col < BOARD_SIZE; col++) {
            if (!isValidColumn(board, col)) {
                return false;
            }
        }


        for (int boxRow = 0; boxRow < BOARD_SIZE; boxRow += BOX_SIZE) {
            for (int boxCol = 0; boxCol < BOARD_SIZE; boxCol += BOX_SIZE) {
                if (!isValidBox(board, boxRow, boxCol)) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isValidRow(int[][] board, int row) {
        boolean[] used = new boolean[BOARD_SIZE];
        for (int col = 0; col < BOARD_SIZE; col++) {
            int num = board[row][col];
            if (num != 0) {
                if (used[num - 1]) {
                    return false;
                }
                used[num - 1] = true;
            }
        }
        return true;
    }

    public boolean isValidColumn(int[][] board, int col) {
        boolean[] used = new boolean[BOARD_SIZE];
        for (int row = 0; row < BOARD_SIZE; row++) {
            int num = board[row][col];
            if (num != 0) {
                if (used[num - 1]) {
                    return false;
                }
                used[num - 1] = true;
            }
        }
        return true;
    }

    public boolean isValidBox(int[][] board, int boxRow, int boxCol) {
        boolean[] used = new boolean[BOARD_SIZE];
        for (int row = boxRow; row < boxRow + BOX_SIZE; row++) {
            for (int col = boxCol; col < boxCol + BOX_SIZE; col++) {
                int num = board[row][col];
                if (num != 0) {
                    if (used[num - 1]) {
                        return false;
                    }
                    used[num - 1] = true;
                }
            }
        }
        return true;
    }
}
