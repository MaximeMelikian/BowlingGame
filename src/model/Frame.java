package model;

/**
 * 
 * Class to represent a single frame of the game. Contains storage of throw
 * results. Frame information (spare, strike or bonus frame)
 *
 */
public class Frame {

	private final int frameNumber;
	private final String marks;
	private final FrameType type;
	private final Integer ball1;
	private final Integer ball2;

	public Frame(int frameNumber, String marks) {
		this.frameNumber = frameNumber;
		this.marks = marks;
		this.type = defineFrameType();
		this.ball1 = defineBall1();
		this.ball2 = defineBall2();
	}

	private FrameType defineFrameType() {
		int frameNumber = getFrameNumber();
		if (frameNumber == Constants.FRAME_NUMBER + 1) {
			return FrameType.BONUS;
		}
		String marks = getMarks();
		if ("X".equals(marks.substring(0, 1))) {
			return FrameType.STRIKE;
		}
		if ("/".equals(marks.subSequence(1, 2))) {
			return FrameType.SPARE;
		}
		return FrameType.NORMAL;
	}

	private Integer defineBall1() {
		String pos1 = getMarks().substring(0, 1);
		if ("X".equals(pos1)) {
			return Constants.MAX_POINTS;
		}
		return "-".equals(pos1) ? 0 : Integer.valueOf(pos1);
	}

	private Integer defineBall2() {
		if (getMarks().length() < 2) {
			return null;
		}
		String pos2 = getMarks().substring(1, 2);
		if ("X".equals(pos2)) {
			return Constants.MAX_POINTS;
		}
		if ("/".equals(pos2)) {
			return Constants.MAX_POINTS - getBall1();
		}
		return "-".equals(pos2) ? 0 : Integer.valueOf(pos2);
	}

	public int getFrameNumber() {
		return frameNumber;
	}

	public String getMarks() {
		return marks;
	}

	public FrameType getType() {
		return type;
	}

	public Integer getBall1() {
		return ball1;
	}

	public Integer getBall2() {
		return ball2;
	}

	@Override
	public String toString() {
		String infos1 = "frame " + getFrameNumber() + ", marks " + getMarks() + ", type : " + getType();
		String infos2 = ", ball1 " + getBall1() + " ball2 " + getBall2() + "\n";
		return infos1 + infos2;
	}

}
