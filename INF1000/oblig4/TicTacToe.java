import java.util.Scanner;
import java.util.Arrays;

// class name
public class TicTacToe {
    
    // declare static variables for use in methods
    static Scanner input = new Scanner(System.in);
    static boolean legalMove;
    static int rowLength = 3;
    static int[] row0 = new int[rowLength];
    static int[] row1 = new int[rowLength];
    static int[] row2 = new int[rowLength];  
    static int rowNumber;
    static int positionInRow;
    static int playerId;
    static int rowPosition;
    static int  isBoardFull;
    static int numberOfRows = 3;
  
    // main method
    public static void main(String[]args){
        	
	// Output to user
	System.out.print("Hello player 1 please enter your name: ");
	String playerName1 = input.nextLine();
	
	// Output to user
	System.out.print("Hello player 2 please enter your name: ");
	String playerName2 = input.nextLine();
	
	//runs method gameTicTacToe 
	gameTicTacToe(isBoardFull,playerName1,playerName2);
    }   

	
    /**
     * asks user for wich row user wants
     * rowNumber -1 just to mimic arrays.
     * check if row exists
     */
    public static int numberOfRow(String playerNumber){
	
	System.out.print("Hello " + playerNumber + " please choose a row, numbered from 1 to 3: ");
	int rowNumber =(Integer.parseInt(input.nextLine()))-1;
    		
	if (rowNumber > 2 || rowNumber < 0){
	    System.out.println("You have entered an invalid rownumber please try again.");
	    numberOfRow(playerNumber);
	    return rowNumber;
	}
	return rowNumber;
    }
    
    
    /**
     * method ask user for input, user inputs the wanted index in array/
     * placement on gameboard in given row.
     * calls on isLegalRowPosition to check if withing bounds of array
     * returns row position if legal position is entered
     */
    public static int placementInRow(int rowNumber){
        
	if(rowNumber == 0){
	    System.out.print("please choose a placement in row " + (rowNumber+1) + ", numbered from 1 to 3: ");
	    rowPosition = (Integer.parseInt(input.nextLine()))-1;
	    isLegalRowPosition(rowPosition,rowNumber);
	    
	}
	else if(rowNumber == 1){
	    System.out.print("please choose a placement in row " + (rowNumber+1) + ", numbered from 1 to 3: ");
	    rowPosition = (Integer.parseInt(input.nextLine()))-1;
	    isLegalRowPosition(rowPosition, rowNumber);
	}
	else if(rowNumber == 2){
	    System.out.print("please choose a placement in row " + (rowNumber+1) + ", numbered from 1 to 3: ");
	    rowPosition = (Integer.parseInt(input.nextLine()))-1;
	    isLegalRowPosition(rowPosition, rowNumber);
	}
	
	return rowPosition;
    }
    
    /**
     * checks if input position is within row/array length
     */
    public static void isLegalRowPosition(int rowPosition, int rowNumber){
	if (rowPosition < 0 || rowPosition > 3){
	    placementInRow(rowNumber);	    
	}
	
    }
    
    
    /**
     * Method uses rowNUmber to determin wich row it should 
     * store playerId in checks if user has made a legal move
     * takes rowNumber ,and checks if index in
     * given row(rowPosition) is empty, if empty return true 
     * if not empty return false
     */
    public static boolean isMoveLegal(int rowNumber, int rowPosition, int playerId, String playerNumber) {
	
	if(rowNumber == 0) {
	    if(row0[rowPosition] == 0) {
		row0[rowPosition] = playerId;
		return true;
	    }
	    else{
		return false;
	    }
	}
    	    
	if(rowNumber == 1){
	    if(row1[rowPosition] == 0) {
		row1[rowPosition] = playerId;
		return true;
	    }
	    else{
		return false;
	    }
	}
	
	if(rowNumber == 2){
	    if(row2[rowPosition] == 0) {
		row2[rowPosition] = playerId;
		return true;
	    }
	    else{
        	return false;
	    }
	}
	// default return if no action is done by loops
	return true;
}
    
    /**
     * prints out gloriously formatted TicTacToe game board
     */
    public static void printsOutGloriouslyFormattedTicTacToeGameBoard(int[] row0, int[] row1, int[] row2) {
	System.out.println("    1   2   3");
	System.out.println("  *************");        
	System.out.println("1 # "+row0[0]+" # "+row0[1]+" # "+row0[2]+" #");  
	System.out.println("  #-----------#");
	System.out.println("2 # "+row1[0]+" # "+row1[1]+" # "+row1[2]+" #");       
	System.out.println("  #-----------#");
	System.out.println("3 # "+row2[0]+" # "+row2[1]+" # "+row2[2]+" #");
	System.out.println("  *************");
    }

     
    public static void gameTicTacToe(int fullBoard, String player1, String player2) {
	// move counter
	int move = 0; 
	
	// board is full after 9 moves
	fullBoard = 9;
	while(move < fullBoard) {
	
	    // prints board to user before every turn.
	    printsOutGloriouslyFormattedTicTacToeGameBoard(row0,row1,row2);

	    /**
	     * checks if even number, if even player1 turn
	     * if not player 2.
	     */
	     if (move%2 == 0 ) {
		 // Sets symbol for player1 to 1.
		 playerId = 1;
		 // call method NumberOfRow
		 rowNumber = numberOfRow(player1);
		 // call on method placementInRow
		 positionInRow = placementInRow(rowNumber);
		 // checks if player 1 made a legal move
		 legalMove = isMoveLegal(rowNumber,positionInRow,playerId,player1);
	
		 /**
		  * ask player1 for new move until legal move is made
		  * legal move is entering row and position where the value stored = 0.
		  */
		 while(legalMove == false){
		    
		     System.out.println("Position is allready taken, please choose another location on the board");
		     rowNumber = numberOfRow(player1);
		     positionInRow = placementInRow(rowNumber);
		     legalMove = isMoveLegal(rowNumber,positionInRow,playerId,player1);
		 }
	     } 
	     
	     else{
		 // Sets symbol for player2 to 2
		 playerId = 2;
		 // call on method numberOfRow
		 rowNumber = numberOfRow(player2);
		 // call on method placementInRow
		 positionInRow = placementInRow(rowNumber);
		 // checks if move is legal
		 legalMove = isMoveLegal(rowNumber,positionInRow,playerId,player2);

		 /**
		  *  asks player 2 for new move until a legal move is made
		  */
		  while(legalMove == false){
		      System.out.println("Position is allready taken, please choose another location on the board");
		      rowNumber = numberOfRow(player2);
		      positionInRow = placementInRow(rowNumber);
		      legalMove = isMoveLegal(rowNumber,positionInRow,playerId,player2);
		  }
	     
	     }
	     // increment move +1
	     move+=1;
	   
	}
	// print board to user when full.
	printsOutGloriouslyFormattedTicTacToeGameBoard(row0,row1,row2);
	System.out.println("GAME OVER! no winner :(");
    }
    

    /**
    public static boolean checkForWinInRow(){
    
    }

    public static boolean checkDiagonalsForWin(){
    }
    
    public static boolean checkBoardForWin(){
    }
    
    public static boolean checkColumnsForWin(){
    }
    
    */



}

	
