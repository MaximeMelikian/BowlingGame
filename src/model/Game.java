package model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Class to represent a single game with 10 frames plus possible bonus.
 *
 */
public class Game {

	private final List<Frame> frames = new ArrayList<>();
	private final String allowedCharacters = "-123456789/X ";
	private boolean randomSequence = false;

	public void playGame(String inputSequence) {
		System.out.println("Game sequence : " + inputSequence);
		SequenceConverter sequenceConverter = new SequenceConverter();
		boolean sequenceValidity = sequenceConverter.checkSequence(inputSequence, allowedCharacters);
		System.out.println("Valid sequence ? " + sequenceValidity);
		sequenceConverter.splitSequence(inputSequence);
		boolean checkPieces = sequenceConverter.checkPieces();
		System.out.println("Correct frames : " + checkPieces);
		sequenceConverter.populateFrameList();

		ScoreTable scoreTable = new ScoreTable();
		scoreTable.calculateScores(sequenceConverter.getFrameList());
		scoreTable.calculateTotalScore();

		if (randomSequence) {
			simulateRandomSequence();
		}

	}

	private void simulateRandomSequence() {
		// Random sequence
		for (int i = 0; i < 10; i++) {
			Frame frame = new Frame(false);
			frame.playFrame();
			frames.add(frame);
		}

		Frame lastFrame = frames.get(frames.size() - 1);
		if (lastFrame.isSpare()) {
			Frame bonusFrame = new Frame(true);
			bonusFrame.playBonusFrame(1);
			frames.add(bonusFrame);
		}
		if (lastFrame.isStrike()) {
			Frame bonusFrame = new Frame(true);
			bonusFrame.playBonusFrame(2);
			frames.add(bonusFrame);
		}

		ScoreTable scoreTable = new ScoreTable();
		scoreTable.calculateScores(frames);
		scoreTable.calculateTotalScore();
	}

}
