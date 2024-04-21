import java.util.Random;

/**
 * Class responsible for initializing the mine sweeper game board.
 */
public class MineSweeperBoard {

    private final int rows;
    private final int cols;
    private final int[][] board;
    private final int numOfMines;
    private static final int MINE = -1;
    private static final Random RAND = new Random();

    /**
     * Constructor.
     * @param rows The number of rows in the game board.
     * @param cols The number of columns in the game board.
     * @param numOfMines The number of mines to be placed on the game board.
     * @throws IllegalArgumentException if any of the input parameters are negative or numOfMines > rows * cols.
     */
    public MineSweeperBoard(int rows, int cols, int numOfMines) {
        // Make sure the numbers positive and the number of mines is valid.
        if (rows < 0 || cols < 0 || numOfMines < 0 || numOfMines > rows * cols) {
            throw new IllegalArgumentException("\"Invalid input. " +
                    "Please enter positive numbers for rows, cols, and number of mines." +
                    "mines should be between 0 and " + (rows * cols));
        }
        this.rows = rows;
        this.cols = cols;
        this.numOfMines = numOfMines;
        this.board = new int[rows][cols];
        initialize();
    }

    /**
     * Randomly initializes the MineSweeper game board by assigning counts to cells and placing mines.
     */
    private void initialize() {
        // Calculate the total number of cells in the board once to avoid recalculating it in each iteration.
        int numOfCells = rows*cols;
        // For each mine, assign counts to cells, pick a random cell, and place the mine.
        for (int i = 0; i < numOfMines; i++) {
            initCountOfCell();
            int mineCell = getRandomCell(numOfCells - i);
            placeMine(mineCell);
        }
    }

    /**
     * Assigns a count to each cell that does not contain a mine.
     */
    private void initCountOfCell() {
        int nonMineCellCount = 0;
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (this.board[i][j] != MINE) {
                    this.board[i][j] = nonMineCellCount;
                    nonMineCellCount++;
                }
            }
        }
    }

    /**
     * Generates a random cell count based on the number of non-mine cells.
     * @param nonMineCellCount The number of non-mine cells on the game board.
     * @return The count assigned to the randomly selected cell.
     */
    public int getRandomCell(int nonMineCellCount) {
        // Generate a random value in range [0,1).
        double randomValue = RAND.nextDouble();
        // Calculate the cell to locate mine by taking the lower whole number of randomValue*nonMineCellCount.
        return (int)Math.floor(randomValue * nonMineCellCount);
    }

    /**
     * Places a mine on the game board at the cell with the specified count.
     * @param mineCell The count assigned to the cell where the mine will be placed.
     */
    public void placeMine(int mineCell) {
        // Find the specified cell to place the mine.
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (this.board[i][j] == mineCell ) {
                    this.board[i][j] = MINE;
                    // Exit the method once the mine is placed.
                    return;
                }
            }
        }
    }

    /**
     * Prints the game board,  representing mines as 'X' and non-mines as '.'.
     */
    public void print() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                System.out.print(this.board[i][j] == MINE ? "X " : ". ");
            }
            System.out.println();
        }
    }
}
