import java.util.InputMismatchException;

import states.TriviaMaze;

public class SnippetTests {

	public static void main(String[] args) {
		int input = 0;
		String menu = "1.) Start a new game\n"
				+ "2.) Load a game\n"
				+ "3.) Login to the question database\n"
				+ "4.) Exit game";
		
		while(input != 4) {
			do {
				System.out.println(menu);
				try {
					input = TriviaMaze.KB.nextInt();
					if(input < 1 || input > 4) {
						throw new IndexOutOfBoundsException("Value entered is out of range of the menu.");
					}
					break;
				}
				catch(InputMismatchException e) {
					System.out.println("Value entered is not an integer.");
					TriviaMaze.KB.next();
					continue;
				}
				catch(IndexOutOfBoundsException i) {
					System.out.println(i.getMessage());
				}
			} while(input < 1 || input > 4);
		}
	}

}
