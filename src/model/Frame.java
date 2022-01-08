package model;

/**
 * 
 * Class to represent a single frame of the game. Contains storage of throw
 * results. Frame information (spare, strike or bonus frame)
 *
 */
public class Frame {

	private BowlingThrow throw1;
	private BowlingThrow throw2;
	private final int maxNum = 10;
	private boolean isStrike = false;
	private boolean isSpare = false;
	private final boolean isBonusFrame;

	public Frame(boolean bonus) {
		this.isBonusFrame = bonus;
	}

	public void playFrame() {
		throw1 = new BowlingThrow(maxNum, true);
		int result1 = throw1.getScoreBall();
		if (result1 < 10) {
			throw2 = new BowlingThrow(maxNum - result1, true);
		}
		if (result1 == maxNum) {
			setStrike(true);
		}
		if (!isStrike && (result1 + throw2.getScoreBall() == maxNum)) {
			setSpare(true);
		}
	}

	public void playBonusFrame(int numThrows) {
		throw1 = new BowlingThrow(maxNum, true);
		int result1 = throw1.getScoreBall();
		if (numThrows == 2 && result1 < maxNum) {
			throw2 = new BowlingThrow(maxNum - result1, true);
		}
		if (numThrows == 2 && result1 == maxNum) {
			throw2 = new BowlingThrow(maxNum, true);
		}
	}

	public BowlingThrow getThrow1() {
		return throw1;
	}

	public void setThrow1(BowlingThrow throw1) {
		this.throw1 = throw1;
	}

	public BowlingThrow getThrow2() {
		return throw2;
	}

	public void setThrow2(BowlingThrow throw2) {
		this.throw2 = throw2;
	}

	public boolean isStrike() {
		return isStrike;
	}

	public void setStrike(boolean isStrike) {
		this.isStrike = isStrike;
	}

	public boolean isSpare() {
		return isSpare;
	}

	public void setSpare(boolean isSpare) {
		this.isSpare = isSpare;
	}

	public boolean isBonusFrame() {
		return isBonusFrame;
	}

}
