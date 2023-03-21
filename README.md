
# Sudoku Solver

### Purpose

This code is designed to solve Sudoku puzzles using a backtracking algorithm.

### Dependencies

This implementation uses Java, Spring Boot and Lombok.


### Features and Functionality

The solver uses a recursive backtracking algorithm to solve Sudoku puzzles. It first finds the next empty cell in the puzzle and then tries each possible value until a valid solution is found. If no solution can be found with a given value, the algorithm backtracks to the previous cell and tries the next value.

The solver includes error-checking to ensure that the input puzzle is valid and if it is not valid (e.g., it violates the rules of Sudoku), the solver returns a JSON error message.

## Example

#### Input:

`[`
`[5, 4, 0, 0, 0, 3, 9, 0, 0],`
`[0, 0, 0, 5, 0, 0, 3, 6, 0],`
`[0, 2, 0, 0, 0, 9, 4, 0, 0],`
`[4, 7, 6, 0, 0, 0, 0, 0, 5],`
`[8, 0, 2, 0, 6, 4, 1, 0, 9],`
`[0, 0, 0, 8, 0, 0, 6, 7, 0],`
`[2, 0, 9, 4, 3, 7, 5, 8, 0],`
`[0, 6, 4, 2, 8, 5, 7, 0, 0],`
`[7, 0, 5, 0, 0, 6, 2, 0, 0]`
`]`


#### Output:

`[`
`[5, 4, 8, 6, 2, 3, 9, 1, 7],`
`[1, 9, 7, 5, 4, 8, 3, 6, 2],`
`[6, 2, 3, 1, 7, 9, 4, 5, 8],`
`[4, 7, 6, 3, 9, 1, 8, 2, 5],`
`[8, 5, 2, 7, 6, 4, 1, 3, 9],`
`[9, 3, 1, 8, 5, 2, 6, 7, 4],`
`[2, 1, 9, 4, 3, 7, 5, 8, 6],`
`[3, 6, 4, 2, 8, 5, 7, 9, 1],`
`[7, 8, 5, 9, 1, 6, 2, 4, 3]`
`]`