package model;

/**
 * Class to generate a random sequence of bowling game
 *
 */
public class SequenceGenerator {

	private String sequence;

	public SequenceGenerator() {
		String sequence = playFrames();
		char lastChar = sequence.charAt(sequence.length() - 1);
		// Strike at 10th Frame
		if (lastChar == 'X') {
			sequence = playBonusFrameAfterStrike(sequence);
		}
		// Spare at 10th Frame
		if (lastChar == '/') {
			sequence = playBonusShotAfterSpare(sequence);
		}
		this.sequence = sequence;
	}

	private String playFrames() {
		String sequence = "";
		for (int i = 0; i < Constants.FRAME_NUMBER; i++) {
			int ball1 = randomShot(0);

			// Strike !
			if (ball1 == Constants.MAX_POINTS) {
				sequence += "X";
				if (i < Constants.FRAME_NUMBER - 1) {
					sequence += " ";
				}
				continue;
			}
			sequence += ball1 == 0 ? "-" : ball1;

			// Spare
			int ball2 = randomShot(ball1);
			if (ball1 + ball2 == Constants.MAX_POINTS) {
				sequence += "/";

				// Normal
			} else {
				sequence += ball2 == 0 ? "-" : ball2;
			}
			if (i < Constants.FRAME_NUMBER - 1) {
				sequence += " ";
			}
		}
		return sequence;
	}

	private String playBonusFrameAfterStrike(String sequence) {
		sequence += " ";
		int ball1 = randomShot(0);
		int ball2 = (ball1 == 0 || ball1 == Constants.MAX_POINTS) ? randomShot(0) : randomShot(ball1);

		// Strike at 1st bonus throw
		if (ball1 == Constants.MAX_POINTS) {
			sequence += "X";
			// Strike at 2nd bonus throw
			if (ball2 == Constants.MAX_POINTS) {
				sequence += "X";
			} else {
				sequence += ball2 == 0 ? "-" : ball2;
			}

			// Miss at 1st bonus throw
		} else if (ball1 == 0) {
			sequence += "-";
			if (ball2 == Constants.MAX_POINTS) {
				sequence += "/";
			} else {
				sequence += ball2 == 0 ? "-" : ball2;
			}

			// Other combinations
		} else {
			sequence += ball1;
			if (ball1 + ball2 == Constants.MAX_POINTS) {
				sequence += "/";
			} else {
				sequence += ball2 == 0 ? "-" : ball2;
			}
		}
		return sequence;
	}

	private String playBonusShotAfterSpare(String sequence) {
		sequence += " ";
		int ball1 = randomShot(0);
		if (ball1 == Constants.MAX_POINTS) {
			sequence += "X";
		} else {
			sequence += ball1 == 0 ? "-" : ball1;
		}
		return sequence;
	}

	private int randomShot(int previousShot) {
		return (int) (Math.random() * (Constants.MAX_POINTS - previousShot + 1));
	}

	public String getSequence() {
		return sequence;
	}

}
