package model;

/**
 * Class to represent bowling throw
 */
public class BowlingThrow {

	private final int scoreBall;

	public BowlingThrow(int inputScore, boolean autoScore) {
		this.scoreBall = autoScore ? (int) Math.round(inputScore * Math.random()) : inputScore;
	}

	public int getScoreBall() {
		return scoreBall;
	}

}
