public class Sudoku {
    int[][] board;

    public Sudoku(){
        board = new int[9][9];
    }

    public void runSudoku(int[][] arr){
        sudokuSolver(arr, new int[3], 0, 0);
    }

    // not exactly sure where to go, I originally had the array int to store the last used index and valu I changed,
    // but realized that I can just use the for loop to account for everything. still troubleshooting
    public boolean sudokuSolver(int[][] arr, int[] ind, int i, int j){
        if(i == 9){ // Once the row is past the last index on the board
            printBoard(arr);
            return true;
        }

        if(arr[i][j] == 0){
            for(int a = 1; a < 10; a++){
                if(squareValid(arr, a, i, j) && lineValid(arr[i], a) && lineValid(getColumn(arr, j), a)){
                    arr[i][j] = a;
                    if(j==8){ // checking if the counter has reached the end of the board
                        if(sudokuSolver(arr, ind, i+1, 0)){
                            return true;
                        }
                    }else{
                        if(sudokuSolver(arr, ind, i, j+1)){
                            return true;
                        }
                    }
                }
            }
            arr[i][j] = 0;
        }else{
            if(j==8){
                if(sudokuSolver(arr, ind, i+1, 0)){
                    return true;
                }
            }else{
                if(sudokuSolver(arr, ind, i, j+1)){
                    return true;
                }
            }
        }
        return false;
    }

    // returns an array containing the desired column of a 2D array
    public int[] getColumn(int[][] arr2, int column){
        int[] arr1 = new int[9];
        for(int row = 0; row < 9; row++) {
            arr1[row] = arr2[row][column];
        }
        return arr1;
    }

    public boolean squareValid(int[][] arr, int num, int i, int j){
        for(int a = i/3*3; a < (i/3*3 + 3); a++){
            for(int b = j/3*3; b < (j/3*3 + 3); j++){
                if(arr[a][b] == num){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean lineValid(int[] arr, int num){
        for(int i = 0; i < 9; i++){
            if(arr[i] == num){
                return false;
            }
        }
        return true;
    }

    public static void printBoard(int[][] arr){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(arr[i][j] != 0){
                    System.out.print(arr[i][j] + " | ");
                }else{
                    System.out.print("  | ");
                }
            }
            System.out.println();
            System.out.println("------------------------------------");
        }
    }

    public static void main(String[] args){
        Sudoku a = new Sudoku();
        int[][] testBoard = new int[9][9];
        testBoard[0][2] = 4; testBoard[0][4] = 5;
        testBoard[1][0] = 9; testBoard[1][3] = 7; testBoard[1][4] = 3; testBoard[1][5] = 4; testBoard[1][6] = 6;
        testBoard[2][2] = 3; testBoard[2][4] = 2; testBoard[2][5] = 1; testBoard[2][7] = 4; testBoard[2][8] = 9;
        testBoard[3][1] = 3; testBoard[3][2] = 5; testBoard[3][4] = 9; testBoard[3][6] = 4; testBoard[3][7] = 8;
        testBoard[4][1] = 9; testBoard[4][7] = 3;
        testBoard[5][1] = 7; testBoard[5][2] = 6; testBoard[5][4] = 1; testBoard[5][6] = 9; testBoard[5][7] = 2;
        testBoard[6][0] = 3; testBoard[6][1] = 1; testBoard[6][3] = 9; testBoard[6][4] = 7; testBoard[6][6] = 2;
        testBoard[7][2] = 9; testBoard[7][3] = 1; testBoard[7][4] = 8; testBoard[7][5] = 2; testBoard[7][8] = 3;
        testBoard[8][4] = 6; testBoard[8][6] = 1;
        a.printBoard(testBoard);
        a.runSudoku(testBoard);
        a.printBoard(testBoard);
    }
}
