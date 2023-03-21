package com.example.sudokusolver.controllers;

import com.example.sudokusolver.exceptions.InvalidInputException;
import com.example.sudokusolver.models.SudokuBoard;
import com.example.sudokusolver.services.contracts.SudokuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sudoku")
@RequiredArgsConstructor
public class SudokuSolverController {

    private final SudokuService sudokuService;


    @PostMapping("/solve")
    public ResponseEntity<Map<String, Object>> solve(@RequestBody SudokuBoard sudokuBoard) {
        try {
            int[][] board = sudokuBoard.getPuzzle();
            sudokuService.solve(board);
            Map<String, Object> response = new HashMap<>();
            response.put("solution", Arrays.stream(board).map(Arrays::toString).collect(Collectors.toList()));
            return ResponseEntity.ok(response);
        } catch (InvalidInputException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


}
