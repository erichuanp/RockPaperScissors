/**
 * TODO: Add your file header
 * Name: Chuan Peng
 * ID: A17239966
 * Email: c1peng@ucsd.edu
 * Sources used: PA 1
 * CSE 12 PA 1: Java and Inheritance review
 * 
 * This program is written for CSE 12 PA 1. The program will determine if the user wins or not in an ordered list,
 * which follows the rule that one of the element will always win the next element, and the last element will win
 * the first element.
 */


import java.util.Scanner;

/**
 * TODO: Add your class header (purpose and capabilities of the class)
 * This class RPS extends from RPSAbstract. It contains a method determineWinner to determine whether it is the player
 * wins or the computer wins. It also contains the main method to execute some test codes for the user.
 */
public class RPS extends RPSAbstract {

    /**
     * TODO: Add method header
     * Initiate the game.
     * @param moves The move sets up the String list.
     */
    public RPS(String[] moves) {
        this.possibleMoves = moves;
        this.playerMoves = new String[MAX_GAMES];
        this.cpuMoves = new String[MAX_GAMES];
    }

    /**
     * Takes the user move, the CPU move, and determines who wins.
     * @param playerMove - move of the player
     * @param cpuMove - move of the CPU
     * @return -1 for invalid move, 0 for tie, 1 for player win, 2 for cpu win
     */
    public int determineWinner(String playerMove, String cpuMove)
    {
        // TODO
        if (!isValidMove(playerMove) || !isValidMove(cpuMove))
            return INVALID_INPUT_OUTCOME;
        int indPM = 0;
        int indCM = 0;
        for (int i = 0; i < possibleMoves.length; i++) {
            if (playerMove.equals(possibleMoves[i]))
                indPM = i;
            if (cpuMove.equals(possibleMoves[i]))
                indCM = i;
        }
        if ((indPM - indCM) == -1 || (indPM - indCM) == possibleMoves.length-1)
            return PLAYER_WIN_OUTCOME;
        else if ((indCM - indPM) == -1 || (indCM - indPM) == possibleMoves.length-1) {
            return CPU_WIN_OUTCOME;
        } else
            return TIE_OUTCOME;
    }

    /**
     * Main method that reads user input, generates cpu move, and plays game
     * 
     * @param args - arguments passed in from command line in String form
     */
    public static void main(String[] args) {
        // If command line args are provided use those as the possible moves
        String[] moves = new String[args.length];
        if (args.length >= MIN_POSSIBLE_MOVES) {
            for (int i = 0; i < args.length; i++) {
                moves[i] = args[i];
            }
        } else {
            moves = RPS.DEFAULT_MOVES;
        }
        // Create new game and scanner
        RPS game = new RPS(moves);
        Scanner in = new Scanner(System.in);
        String input = "";

        // TODO: Insert the code to play the game. 
        // See the writeup for an example of the game play.
        // Hint: call the methods we/you have already written 
        // to do most of the work!
        while (game.numGames < MAX_GAMES) {
            System.out.println(PROMPT_MOVE);
            input = in.next();
            // While user does not input "q", play game
            if (input.equals(QUIT))
                break;
            game.play(input, game.genCPUMove());
        }
        game.end();
        in.close();
    }
}
