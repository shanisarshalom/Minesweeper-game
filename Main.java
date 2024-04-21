public class Main {

    private static final int NUM_OF_ARGUMENTS = 3;

    /**
     * Main method to run the MineSweeper game.
     * It parses the command line arguments to determine the size of the game board
     * and the number of mines. Then, it initializes and prints the game board.
     *
     * @param args Command line arguments containing the number of rows, columns, and mines.
     */
    public static void main(String[] args) {
        // Check if the correct number of command line arguments is provided.
        if (args.length != NUM_OF_ARGUMENTS) {
            System.out.println("invalid input. " +
                    "Please enter positive numbers for rows, cols, and number of mines.");
            return;
        }

        try {
            // Parse command line arguments.
            int rows = Integer.parseInt(args[0]);
            int cols = Integer.parseInt(args[1]);
            int numOfMines = Integer.parseInt(args[2]);

            // Create MineSweeperBoard instance with provided arguments and print the game board.
            MineSweeperBoard gameBoard = new MineSweeperBoard(rows, cols, numOfMines);
            gameBoard.print();
        } catch (NumberFormatException e) {
            // Catch and handle NumberFormatException if any command-line argument cannot be parsed into an integer.
            System.out.println("invalid input. " +
                    "Please enter positive numbers for rows, cols, and number of mines.");
        }
    }
}