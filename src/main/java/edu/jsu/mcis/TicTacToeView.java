package edu.jsu.mcis;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToeView extends JPanel implements ActionListener {
    
    TicTacToeModel model;

    private JButton[][] squares;
    private JPanel squaresPanel;
    private JLabel resultLabel;
	private int width;

    public TicTacToeView(TicTacToeModel model) {

        this.model = model;

        width = model.getWidth();

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        squares = new JButton[width][width];
        squaresPanel = new JPanel(new GridLayout(width,width));
        resultLabel = new JLabel();
        resultLabel.setName("ResultLabel");
        
        /* Loop through all rows and columns of the grid and: create a new
           JButton and assign it to the current grid element, add "this"
           (the View object) as the ActionListener, set the JButton's name
           to "SquareXY" (where X is the row and Y is the column), set the
           preferred size to 64x64 pixels, and add the new square to the
           "squaresPanel" JPanel created earlier. */
        
        for (int i = 0; i < width; i++) {
			for (int j = 0; j < width; j++) {
				squares[i][j] = new JButton();
				squares[i][j].setName("Square"+i+j);
				squares[i][j].addActionListener(this);
				squares[i][j].setText("-");
				squares[i][j].setPreferredSize(new Dimension(60,60));
				squaresPanel.add(squares[i][j]);
			
			}
		}
        
        add(squaresPanel);
        add(resultLabel);
        
        resultLabel.setText("Welcome to Tic-Tac-Toe!");

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        
        String name = ((JButton) event.getSource()).getName();
        
        /* Extract the ROW and COL from the string name (remember, the squares
           should all be named "SquareXY", so the ROW should be extracted from
           position 6 of this string, and the COL from position 7.  Remember to
           convert both to Integers!) */

		char x = name.charAt(6);
		char y = name.charAt(7);
		   
        int ROW = Character.getNumericValue(x);
		int COL = Character.getNumericValue(y);
           
        /* Call makeMark() to place the mark in the Model */
        
        model.makeMark(ROW, COL);

        /* Update the squares of the View using the "updateSquares()" method
           (see below); this is the equivalent of re-printing the grid to the
           console in Part One. */

        updateSquares();

        /* Clear the "showResult" label at the bottom of the View */
        
        showResult(" ");
        
        /* If the game is over (that is, if the "getResult()" method returns
           a result other than Result.NONE), loop through all the JButtons in
           the View and disable them to prevent further entries.  (Hint: the
           JButton class provides a "setEnabled()" method for this.) */
           
        if (model.getResult().toString() != "none") {
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < width; j++) {
				squares[i][j].setEnabled(false);
				}
			}
		}
        
    }
        
    public void updateSquares() {

        /* Loop through all of the rows and columns in the JButton grid and
           reset the button text to match the corresponding marks as returned
           by "getMark()" (to update the View to match the Model) */
           
         for (int i = 0; i < width; i++) {
			for (int j = 0; j < width; j++) {
				squares[i][j].setText(model.getMark(i, j).toString());
			}
		}

    }
        
    public void showResult(String message) {
        resultLabel.setText(message);
    }

}