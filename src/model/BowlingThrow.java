package model;

public class BowlingThrow {

	private final int scoreBall;

	public BowlingThrow(int maxScore) {
		this.scoreBall = (int) Math.round(maxScore * Math.random());
	}

	public int getScoreBall() {
		return scoreBall;
	}

}
