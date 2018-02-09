import java.util.Scanner;

public class Player 
{	// The current falling block.
	Block blockFalling;
	// The block that is stored/held (This feature has yet to be implemented).
	Block blockHold;
	
	// Scanner will be used to read the user input.
	Scanner read = new Scanner(System.in);
	
	public void getUserInput() 
	{
		
		// Prompts the user for their input.
		System.out.println("Please enter how you want to move the block ('LEFT', 'RIGHT', or 'SET' it in place): ");
		String move = read.nextLine();
		
		//Converting the user's input to uppercase to prevent complications.
		move = move.toUpperCase();
		
		// Do the user's input move (Or prompt them to input a proper move.)
		switch(move)
		{
		case "LEFT":
			blockFalling.moveLeft();
			break;
		case "RIGHT":
			blockFalling.moveRight();
		    break;
		case "SET":
			blockFalling.placeBlock();
			break;
		
		// Default case in case the user's input dosent satisfy any of the cases.
		default:
			System.out.println("Please enter a valid move.");
			break;
		}
		
	}
}
