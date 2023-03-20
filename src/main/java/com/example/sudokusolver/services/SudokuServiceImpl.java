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
        SudokuBoard sudokuBoard = new SudokuBoard();
        if (solveSudoku(board)) {
            sudokuBoard.setPuzzle(board);
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
}
