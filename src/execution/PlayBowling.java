package execution;

import model.Game;

public class PlayBowling {

	// Please edit your sequence below
	private static final String INPUT_SEQUENCE = "12 34 5/ 6/ 7/ X 9/ -8 X X XX";
	// End of edition zone
	private static final String ALL_STRIKES = "X X X X X X X X X X XX";
	private static final String ALL_9_MISS = "9- 9- 9- 9- 9- 9- 9- 9- 9- 9-";
	private static final String ALL_5_SPARE = "5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5";

	public static void main(String[] args) {
		Game game = new Game();

		System.out.println("Begin new game");
		game.playGame(INPUT_SEQUENCE);

		System.out.print("\nPlay all strikes ! ");
		game.playGame(ALL_STRIKES);

		System.out.print("\nPlay all 9 plus miss ! ");
		game.playGame(ALL_9_MISS);

		System.out.print("\nPlay all 5 plus spare ! ");
		game.playGame(ALL_5_SPARE);

	}

}
