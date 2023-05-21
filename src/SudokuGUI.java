import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuGUI extends JFrame {
    private JTextField[][] textFields;


    public SudokuGUI() {
        setTitle("Sudoku Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(500, 500);

        JPanel boardPanel = new JPanel(new GridLayout(9, 9));
        textFields = new JTextField[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                textFields[i][j] = new JTextField();
                textFields[i][j].setFont(new Font("Arial", Font.PLAIN, 20));
                textFields[i][j].setPreferredSize(new Dimension(40, 40));
                boardPanel.add(textFields[i][j]);
            }
        }
        add(boardPanel, BorderLayout.WEST);


        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int[][] board = getBoard();
                Sudoku solver = new Sudoku();
                if (solver.sudokuSolver(board, 0, 0)) {
                    updateBoard(board);
                    JOptionPane.showMessageDialog(SudokuGUI.this, "Sudoku solved successfully!");
                } else {
                    JOptionPane.showMessageDialog(SudokuGUI.this, "Invalid Sudoku puzzle!");
                }
            }
        });

        JButton check = new JButton("Check");
        check.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int[][] board = getBoard();
                Sudoku solver = new Sudoku();
                if(solver.sudokuSolver(board, 0, 0)){
                    JOptionPane.showMessageDialog(SudokuGUI.this, "You're on the right track!");
                }else{
                    JOptionPane.showMessageDialog(SudokuGUI.this, "Looks like you made a mistake somewhere. Try again!");
                }
            }
        });

        JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        textFields[i][j].setText("");
                    }
                }
            }
        });

        JButton easyPuzzle = new JButton("Generate Easy Puzzle");
        easyPuzzle.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

            }
        });

        JButton hint = new JButton("Hint");
        hint.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Sudoku solver = new Sudoku();
                int[][] solvedBoard = getBoard();
                solver.sudokuSolver(solvedBoard, 0, 0);
                int[][] currentBoard = getBoard();

                outerLoop:
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if(currentBoard[i][j] == 0){
                            textFields[i][j].setText(String.valueOf(solvedBoard[i][j]));
                            break outerLoop;
                        }
                    }
                }

            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(solveButton);
        buttonPanel.add(check);
        buttonPanel.add(reset);
        buttonPanel.add(easyPuzzle);
        buttonPanel.add(hint);
        add(buttonPanel, BorderLayout.CENTER);


        setLocationRelativeTo(null);
        setVisible(true);
    }

    private int[][] getBoard() {
        int[][] board = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String value = textFields[i][j].getText();
                if (value.isEmpty()) {
                    board[i][j] = 0;
                } else {
                    board[i][j] = Integer.parseInt(value);
                }
            }
        }

        return board;
    }

    private void updateBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                textFields[i][j].setText(String.valueOf(board[i][j]));
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SudokuGUI();
            }
        });
    }
}