# Minesweeper

This project implements a basic version of the game [minesweeper](https://en.wikipedia.org/wiki/Microsoft_Minesweeper).

There is a menu at the top of the GUI that allows the user to start a new game, change the grid size for the game (3x3 up to 12x12; or pre-defined difficulty levels), quit the program, or get help.

The number of mines are either explicitly set via difficulty level or randomly generated based on the grid size (anywhere between 2 and 1/2 the total number of cells).

_Design decisions:_
- The first click is never a mine
    - If the first click is a mine, the program moves this mine somewhere else on the grid so the game will never immediately end on the first click
- Neighboring mines
    - The number of neighboring mines is defined as any adjacent cell to the current cell
    - Corner cells have 3 neighbors
    - Edge cells have 5 neighbors
    - Interior cells have 8 neighbors (maximum)
    - Cells that do not have any neighboring mines show as blank (instead of 0)

