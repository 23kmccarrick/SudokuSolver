
public class Sudoku {
    int[][] board;

    public Sudoku(){
    }

    // next goal is to optimize a bit more. Once a value has been found to be valid, I'll take that value out
    // of the possible values for its neighbors instead of running through all 9 numbers every iteration
    public boolean sudokuSolver(int[][] arr, int i, int j){
        if(i == 9){ // Once the row is past the last index on the board
            return true;
        }
        if(j == 9){
            return sudokuSolver(arr, i+1, 0);
        }
//        System.out.println("bye");
        if(arr[i][j] == 0){
//            System.out.println("hi");
            for(int a = 1; a < 10; a++){
                if(squareValid(arr, a, i, j) && lineValid(arr[i], a) && lineValid(getColumn(arr, j), a)){
//                    System.out.println("hello");
                    arr[i][j] = a;
                    if(sudokuSolver(arr, i, j+1)){
                        return true;
                    }
                    arr[i][j] = 0;
                }
            }
        }else{
            if(sudokuSolver(arr, i, j+1)){
                return true;
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
            for(int b = j/3*3; b < (j/3*3 + 3); b++){
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
        int[][] easy1 = {
        {5, 3, 0, 0, 7, 0, 0, 0, 0},
        {6, 0, 0, 1, 9, 5, 0, 0, 0},
        {0, 9, 8, 0, 0, 0, 0, 6, 0},
        {8, 0, 0, 0, 6, 0, 0, 0, 3},
        {4, 0, 0, 8, 0, 3, 0, 0, 1},
        {7, 0, 0, 0, 2, 0, 0, 0, 6},
        {0, 6, 0, 0, 0, 0, 2, 8, 0},
        {0, 0, 0, 4, 1, 9, 0, 0, 5},
        {0, 0, 0, 0, 8, 0, 0, 7, 9}};

//        int i = 4;
//        int j = 6;
//        System.out.println(i/3*3);
//        System.out.println(j/3*3);
    }
}
