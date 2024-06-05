## Hexagonal Maze Solver

## Project Execution Instructions

1. Open a terminal in the directory containing this file.
2. Compile the project by entering the following command:
   ```sh
   find . -name "*.java" -print | xargs javac -d ./bin
   ```
3. Execute the main program with the command:
   ```sh
   java -cp ./bin main/Main
   ```

## Project Description

The graphical interface is intuitive and user-friendly.

### 'File' Menu:
- **Load**: Import a `.maze` file.
- **Save as**: Save the current maze to a new file.
- **Quit**: Exit the program via the menu or the close button.
- **Save**: Save modifications to the current maze with a single click.
- **New**: Instantiate a new maze of the desired size.

### Maze Representation:
- **Empty cells**: Light gray.
- **Wall cells**: Dark gray.
- **Start cell**: Green.
- **End cell**: Red.

### Buttons:
- **Generate Path**: Display the shortest path between the start and end cells. Clicking this button again hides the path.
- **Edit**: Switch the cursor to edit mode. Clicking on an empty cell turns it into a wall and vice versa. You can drag the start and end cells to new positions. Releasing the start/end cell on another cell swaps their positions; if the target cell is a wall, it becomes an empty cell.
- **Cursor**: Switch the cursor back to default mode.

### Grid Centering and Sizing:
The display is designed to keep the grid centered with an appropriate border relative to the number of cells. Resizing the window adjusts the grid display to occupy the maximum available space while remaining centered.

### Comments:
The code is heavily commented (in English), making it easy to understand the application's functionality by reading the code.

**Hexagonal Maze Solver** - Project by Adrien Guittard