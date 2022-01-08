package execution;

import model.Game;

public class PlayBowling {

	public static void main(String[] args) {
		System.out.println("Begin new game");
		Game game = new Game();
		game.playGame();
	}

}
