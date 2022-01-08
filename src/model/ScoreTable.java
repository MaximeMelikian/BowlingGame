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
	private final int bonus = 10;

	public void calculateScores(List<Frame> frames) {
		for (int numFrame = 0; numFrame < frames.size(); numFrame++) {
			Frame actual = frames.get(numFrame);
			String extraSpace = numFrame < 9 ? " " : "";
			String prefix = "Frame : " + extraSpace + (numFrame + 1);
			if (!actual.isStrike() && !actual.isSpare() && !actual.isBonusFrame()) {
				int score1 = actual.getThrow1().getScoreBall();
				int score2 = actual.getThrow2().getScoreBall();
				int sum = score1 + score2;
				scores.add(sum);
				String middle = ", Throws : " + score1 + score2;
				middle = middle.replace("0", "-");
				String additionalSpace = sum < 10 ? " " : "";
				System.out.println(prefix + middle + ", Score :" + additionalSpace + (sum));
			}
			if (actual.isSpare() && !actual.isBonusFrame()) {
				Frame next = frames.get(numFrame + 1);
				int throw1 = actual.getThrow1().getScoreBall();
				int score1 = next.getThrow1().getScoreBall();
				scores.add(bonus + score1);
				String middle = ", Throws : " + throw1 + "/";
				System.out.println(prefix + middle + ", Score :" + (bonus + score1) + ", Spare !");
			}
			if (actual.isStrike() && numFrame < frames.size() - 1) {
				Frame next = frames.get(numFrame + 1);
				int score1 = next.getThrow1().getScoreBall();
				int score2 = 0;
				if (!next.isStrike() || numFrame == frames.size() - 2) {
					score2 = next.getThrow2().getScoreBall();
				}
				if (next.isStrike() && numFrame < frames.size() - 2) {
					Frame second = frames.get(numFrame + 2);
					score2 = second.getThrow1().getScoreBall();
				}
				scores.add(bonus + score1 + score2);
				String middle = ", Throws : X ";
				System.out.println(prefix + middle + ", Score :" + (bonus + score1 + score2) + ", Strike !");
			}
			if (actual.isBonusFrame()) {
				int score1 = actual.getThrow1().getScoreBall();
				String score1Display = (score1 == 0 ? "-" : score1 == 10 ? "X" : String.valueOf(score1));
				int score2 = 0;
				String score2Display = "";
				if (actual.getThrow2() != null) {
					score2 = actual.getThrow2().getScoreBall();
					score2Display = (score2 == 0 ? "-"
							: score1 + score2 == 10 ? "/" : score2 == 10 ? "X" : String.valueOf(score2));
				}
				System.out.println("Frame bonus, Throws : " + score1Display + score2Display);
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
