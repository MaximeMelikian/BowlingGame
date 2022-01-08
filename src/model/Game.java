package model;

import java.util.ArrayList;
import java.util.List;

public class Game {

	List<Frame> frames = new ArrayList<>();

	public void playGame() {
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
