import java.util.Scanner;
import java.io.*;

public class WordPuzzle {

    //matrix[][] contains the input matrix
    //whenever a word is found in matrix[][],
    //copy the word to output[][] 
    public static char matrix[][], output[][];

    static int[] x = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static int[] y = { -1, 0, 1, -1, 1, -1, 0, 1 };
    public static int[] directionHolder;

    public static int size = 0;

    public static void popAlt(int row, int col, String word)
    {

    }

    public static void populateOutput(int row,
                                      int col, String word, int i)
    {


        output[row][col] = word.charAt(0);
        int len = word.length();

        boolean completed = false;

        for (int dir = 0; dir < 8; dir++) {
            //int dir = directionHolder[place];
            if(completed){
                break;
            }

            int k, rd = row + x[dir], cd = col + y[dir];


            for (k = 1; k < len ; k++) {
                // If out of bound break
                if (rd >= size || rd < 0 || cd >= size || cd < 0)
                    break;

                // If not matched, break
                if (matrix[rd][cd] == word.charAt(k) && dir == directionHolder[i]){
                    output[rd][cd] = matrix[rd][cd];
                    completed = true;
                }

                // Moving in particular direction
                rd += x[dir];
                cd += y[dir];
            }


        }
    }



    static boolean search2D(int row,
                            int col, String word, int i)
    {

        if (matrix[row][col] != word.charAt(0))
            return false;

        int len = word.length();

        for (int dir = 0; dir < 8; dir++) {

            int k, rd = row + x[dir], cd = col + y[dir];

            // First character is already checked,
            // match remaining characters
            for (k = 1; k < len; k++) {
                // If out of bound break
                if (rd >= size || rd < 0 || cd >= size || cd < 0)
                    break;

                // If not matched, break
                if (matrix[rd][cd] != word.charAt(k))
                    break;

                // Moving in particular direction
                rd += x[dir];
                cd += y[dir];
            }

            if (k == len){
                directionHolder[i] = dir;
                //System.out.println(dir);
                return true;

            }

        }
        return false;
    }

    public static void patternSearch(
            String word, int i)
    {
        // Consider every point as starting
        // point and search given word
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (matrix[row][col]==word.charAt(0)  &&
                        search2D(row, col, word, i)){
                    populateOutput(row, col, word, i);
                }

            }
        }
    }



    //search the word in all 8 directions from each position!
    public static void findWord(String word, int i) {
// WRITE YOUR CODE HERE
        patternSearch(word, i);


    }

    public static void main(String[] args) throws IOException {

        //let us use command line argument for input filename.
        File inputFile = new File(args[0]);
        Scanner finput = new Scanner(inputFile);

        int matrixSize = finput.nextInt();
        size = matrixSize;
        matrix = new char [matrixSize][matrixSize];
        output = new char [matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                matrix[i][j] = finput.next().charAt(0);
                //System.out.print(matrix[i][j] + " ");
                output[i][j] = ' ';
            }
            //System.out.println();
        }

        //read the words and find them in matrix!
        int numWords = finput.nextInt();
        directionHolder = new int [numWords];
        for (int i = 0; i < numWords; i++) {
            String word = finput.next();
            //System.out.println(word);
            findWord(word, i);
        }

        //output the words in matrix format
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++)
                System.out.print(output[i][j] + " ");
            System.out.println();
        }
    }
}
