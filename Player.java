import java.util.Scanner;

public class Player 
{
	Block blockFalling;
	Block blockHold;
	
	Scanner read = new Scanner(System.in);
	
	public void getUserInput() 
	{
		
		
		System.out.println("Please enter how you want to move the block: ");
		
		String move = read.nextLine();
		
		move = move.toUpperCase();
		
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
		default:
			System.out.println("Please enter a valid move.");
			break;
		}
		// Get input, left, right, or set, respond accordingly
	}
}