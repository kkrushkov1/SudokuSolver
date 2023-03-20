package com.example.sudokusolver.models;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SudokuBoard {

    private int[][] puzzle;

}
