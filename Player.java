import java.util.Scanner;

public class Player 
{	//Setting the blocks. One will be for the block falling and the latter is for the blocks that will be held.(blockHold is WIP)
	Block blockFalling;
	Block blockHold;
	
	//Scanner will be used to read the user input.
	Scanner read = new Scanner(System.in);
	
	public void getUserInput() 
	{
		
		//Getting the user's input and reading it.
		System.out.println("Please enter how you want to move the block: ");
		String move = read.nextLine();
		
		//Converting the user's input to uppercase to prevent complications.
		move = move.toUpperCase();
		
		// Get input, left, right, or set, respond accordingly
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
		
		//Default case in case the user's input dosent satisfy any of the cases.
		default:
			System.out.println("Please enter a valid move.");
			break;
		}
		
	}
}
