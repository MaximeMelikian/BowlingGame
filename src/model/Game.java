package model;

/**
 * 
 * Class to represent a single game with 10 frames plus possible bonus.
 *
 */
public class Game {

	private final String allowedCharacters = "-123456789/X ";

	public void playGame(String inputSequence) {
		// Display sequence
		System.out.println("Game sequence : " + inputSequence);

		// Check validity
		SequenceConverter sequenceConverter = new SequenceConverter();
		boolean sequenceValidity = sequenceConverter.checkSequence(inputSequence, allowedCharacters);
		System.out.println("Valid sequence ? " + sequenceValidity);

		// Split into frames
		sequenceConverter.splitSequence(inputSequence);
		boolean checkPieces = sequenceConverter.checkPieces();
		System.out.println("Correct frames : " + checkPieces);
		sequenceConverter.populateFrameList();

		// Display score table
		ScoreTable scoreTable = new ScoreTable();
		scoreTable.calculateScores(sequenceConverter.getFrameList());
		scoreTable.calculateTotalScore();

	}

	public String simulateRandomSequence() {
		SequenceGenerator sequenceGenerator = new SequenceGenerator();
		return sequenceGenerator.getSequence();
	}

}
