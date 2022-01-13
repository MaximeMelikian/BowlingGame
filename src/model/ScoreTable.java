package model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Class to calculate and display score results from the game
 *
 */
public class ScoreTable {

	private final List<Integer> scores = new ArrayList<>();
	private final int bonus = Constants.MAX_POINTS;

	public void calculateScores(List<Frame> frames) {
		for (int numFrame = 0; numFrame < frames.size(); numFrame++) {
			Frame actual = frames.get(numFrame);
			String extraSpace = numFrame < Constants.FRAME_NUMBER - 1 ? " " : "";
			String prefix = "Frame : " + extraSpace + (numFrame + 1);

			if (FrameType.NORMAL.equals(actual.getType())) {
				int sum = actual.getBall1() + actual.getBall2();
				scores.add(sum);
				String middle = ", Throws : " + actual.getMarks();
				String additionalSpace = sum < Constants.MAX_POINTS ? " " : "";
				System.out.println(prefix + middle + ", Score :" + additionalSpace + (sum));
			}

			if (FrameType.SPARE.equals(actual.getType())) {
				Frame next = frames.get(numFrame + 1);
				int score1 = next.getBall1();
				scores.add(bonus + score1);
				String middle = ", Throws : " + actual.getMarks();
				System.out.println(prefix + middle + ", Score :" + (bonus + score1) + ", Spare !");
			}

			if (FrameType.STRIKE.equals(actual.getType())) {
				Frame next = frames.get(numFrame + 1);
				int score1 = next.getBall1();
				int score2 = 0;
				// Double strike !!
				if (FrameType.STRIKE.equals(next.getType())) {
					Frame second = frames.get(numFrame + 2);
					score2 = second.getBall1();
				}
				if (!FrameType.STRIKE.equals(next.getType())) {
					score2 = next.getBall2();
				}
				scores.add(bonus + score1 + score2);
				String middle = ", Throws : X ";
				System.out.println(prefix + middle + ", Score :" + (bonus + score1 + score2) + ", Strike !");
			}

			if (FrameType.BONUS.equals(actual.getType())) {
				System.out.println("Frame bonus, Throws : " + actual.getMarks());
			}
		}
	}

	public void calculateTotalScore() {
		int sum = getScores().stream().mapToInt(Integer::intValue).sum();
		System.out.println("Total Score of the game : " + sum);
	}

	public List<Integer> getScores() {
		return scores;
	}

}
