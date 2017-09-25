package edu.jsu.mcis;

public class TicTacToeModel{
    
    private static final int DEFAULT_WIDTH = 3;
    
    /* Mark (represents X, O, or an empty square */
    
    public enum Mark {
        
        X("X"), 
        O("O"), 
        EMPTY("-");

        private String message;
        
        private Mark(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* Result (represents the final state of the game: X wins, O wins, a tie,
       or NONE if the game is not yet over) */
    
    public enum Result {
        
        X("X"), 
        O("O"), 
        TIE("Tie"), 
        NONE("none");

        private String message;
        
        private Result(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    private Mark[][] grid; /* Game grid */
    private boolean xTurn; /* True if X is current player */
    private int width;     /* Size of game grid */
    
    /* DEFAULT CONSTRUCTOR */
    
    public TicTacToeModel() {
        
        /* No arguments (call main constructor; use default size) */
        
        this(DEFAULT_WIDTH);
        
    }
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel(int width) {
        
        /* Initialize width; X goes first */
        
        this.width = width;
        xTurn = true;
        
        /* Create grid (width x width) as a 2D Mark array */

        grid = new Mark[width][width];

        /* Initialize grid by filling every square with empty marks */

        for (int i = 0; i < width; i++) {
			for (int j = 0; j < width; j++) {
				grid[i][j] = Mark.EMPTY;
			}
		}
        
    }
	
    public boolean makeMark(int row, int col) {
        
        /* Place the current player's mark in the square at the specified
           location, but only if the location is valid and if the square is
           empty! */
        
        if (isValidSquare(row, col) == true) {
			
			if (isSquareMarked(row, col) == false) {
				
				if (isXTurn() == true) {
					grid[row][col] = Mark.X;
					xTurn = false;
					return true;
				}
				else {
					grid[row][col] = Mark.O;
					xTurn = true;
					return true;
				}
			}
		}
        //Can not place mark
		return false;
    }
	
    private boolean isValidSquare(int row, int col) {
        
        /* Return true if specified location is within grid bounds */
		
		int width = getWidth();
        
        if (row >= width || row < 0) {
			System.out.println("Row is not within grid bounds");
			return false;
		}
		if (col >= width || col < 0) {
			System.out.println("Col is not within grind bounds");
			return false;
		}
		else {
			return true;
        }
		
    }
	
    private boolean isSquareMarked(int row, int col) {
        
        /* Return true if square at specified location is marked */
        
        if (grid[row][col] == Mark.EMPTY) {
			return false;
		}
		else {
			return true;
        }  
    }
	
    public Mark getMark(int row, int col) {
        
        /* Return mark from the square at the specified location */
        
        Mark mark = grid[row][col];

        return mark;
            
    }
	
    public Result getResult() {
        
        /* Use isMarkWin() to see if X or O is the winner, if the game is a
           tie, or if the game is not over, and return the corresponding Result
           value */
        
		Result result = Result.NONE;
		
		//Check each mark to see if any are winners. If they are, that player is the winner.
		if (isMarkWin(Mark.X) == true) {
			result = Result.X;
		}
		
		if (isMarkWin(Mark.O) == true) {
			result = Result.O;
		}
		
		//If there are no winners, check to see if game is tied
		if (isTie() == true) {
			result = Result.TIE;
		}
       
		return result;

    }
	
    private boolean isMarkWin(Mark mark) {
        
        /* Check the squares of the board to see if the specified mark is the
           winner */
		   
		int width = getWidth();
		int gridSize = width * width;
		
		int counter = 0;
		int counter2 = width; //for-loop counters
		int counter3 = 0;  //for-loop counters
		
		Mark[] gridMarks = new Mark[gridSize];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < width; j++) {
				gridMarks[counter] = getMark(i, j);
				counter++;
			}
		}
		
		if (mark == mark.X) {
			//Wins on rows
			while (counter2 > 0) {
				int counter4 = counter3 + 1; //additional counter for for-loop
				int check = 0;
				for (int i = (width*counter3) ; i < (width*counter4); i++) {
					if (gridMarks[i] == Mark.X) {
						check++;
					}
				}
				if (check == width) {
					return true;
				}
				counter3++;
				counter2--;
			}
			
			//Wins on cols
			counter2 = width;
			counter3 = 0;
			
			while (counter2 > 0) {
				int check = 0;
				for (int i = 0 ; i < width; i++) {
					int index = (width*i)+counter3;
					if (gridMarks[index] == Mark.X) {
						check++;
					}
				}
				if (check == width) {
					return true;
				}
				counter3++;
				counter2--;
			}
			
			//Diag wins
			int check = 0;
			for (int i = 1; i < width+1; i++) {
				int index = (width*i)-i;
				if (gridMarks[index] == Mark.X) {
					check++;
				}
			}
			if (check == width) {
				return true;
			}
			
			check = 0;
			for (int i = 0; i < width; i++) {
				int index = (width*i)+i;
				if (gridMarks[index] == Mark.X) {
					check++;
				}
			}
			if (check == width) {
				return true;
			}
		}
		
		
		if (mark == mark.O) {
			//Wins on rows
			while (counter2 > 0) {
				int counter4 = counter3 + 1; //additional counter for for-loop
				int check = 0;
				for (int i = (width*counter3) ; i < (width*counter4); i++) {
					if (gridMarks[i] == Mark.O) {
						check++;
					}
				}
				if (check == width) {
					return true;
				}
				counter3++;
				counter2--;
			}
			
			//Wins on cols
			counter2 = width;
			counter3 = 0;
			
			while (counter2 > 0) {
				int check = 0;
				for (int i = 0 ; i < width; i++) {
					int index = (width*i)+counter3;
					if (gridMarks[index] == Mark.O) {
						check++;
					}
				}
				if (check == width) {
					return true;
				}
				counter3++;
				counter2--;
			}
			
			//Diag wins
			int check = 0;
			for (int i = 1; i < width+1; i++) {
				int index = (width*i)-i;
				if (gridMarks[index] == Mark.O) {
					check++;
				}
			}
			if (check == width) {
				return true;
			}
			
			check = 0;
			for (int i = 0; i < width; i++) {
				int index = (width*i)+i;
				if (gridMarks[index] == Mark.O) {
					check++;
				}
			}
			if (check == width) {
				return true;
			}
		}
		
		return false;
		
		
    }
	
    private boolean isTie() {
        
        /* Check the squares of the board to see if the game is a tie */

		Mark mark;
		
		//If there are any empty spaces, there is no tie
        for (int i = 0; i < width; i++) {
			for (int j = 0; j < width; j++) {
				mark = getMark(i,j);
				if (mark == Mark.EMPTY) {
					return false;
				}
			}
		}

        return true;
        
    }

    public boolean isGameover(){
        
        /* Return true if the game is over */
        
        return Result.NONE != getResult();
        
    }

    public boolean isXTurn(){
        
        /* Getter for xTurn */
        
        return xTurn;
        
    }

    public int getWidth(){
        
        /* Getter for width */
        
        return width;
        
    }
    
}