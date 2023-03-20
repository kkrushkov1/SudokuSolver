package com.example.sudokusolver.controllers;

import com.example.sudokusolver.exceptions.InvalidInputException;
import com.example.sudokusolver.models.SudokuBoard;
import com.example.sudokusolver.services.contracts.SudokuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/sudoku")
@RequiredArgsConstructor
public class SudokuSolverController {

    private final SudokuService sudokuService;


    @PostMapping("/solve")
    public SudokuBoard solve(@RequestBody SudokuBoard sudokuBoard) {
//        int[][] board = sudokuBoard.getBoard();
//        if (solveSudoku(board)) {
//            sudokuBoard.setBoard(board);
//            return sudokuBoard;
//        } else {
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST, "Invalid Sudoku puzzle");
//        }
        try {
            int[][] board = sudokuBoard.getPuzzle();
            sudokuService.solve(board);
            return sudokuBoard;
        } catch (InvalidInputException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


}
