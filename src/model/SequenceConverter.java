package model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Class to import a manually defined game sequence et store results in frame
 * list
 *
 */
public class SequenceConverter {

	private String[] pieces;
	private final List<Frame> frameList = new ArrayList<>();
	private final int maxFrameNumber = 10;

	/**
	 * 
	 * @param inputSequence     : the sequence to analyse
	 * @param allowedCharacters : concatenation of allowed characters
	 * @return control that all characters are allowed in input sequence
	 */
	public boolean checkSequence(String inputSequence, String allowedCharacters) {
		char[] allowedCharTable = allowedCharacters.toCharArray();
		char[] inputCharTable = inputSequence.toCharArray();
		boolean compare = false;
		for (int i = 0; i < inputCharTable.length; i++) {
			compare = false;
			for (int j = 0; j < allowedCharTable.length; j++) {
				if (inputCharTable[i] == allowedCharTable[j]) {
					compare = true;
				}
			}
			if (compare == false) {
				return false;
			}
		}
		return true;
	}

	public void splitSequence(String inputSequence) {
		setPieces(inputSequence.split(" "));
	}

	/**
	 * Control each individual frame pair values (or single if Strike)
	 * 
	 * @return a control all frames validity
	 */
	public boolean checkPieces() {
		for (String piece : getPieces()) {
			if (piece.length() < 1 || piece.length() > 2) {
				System.out.println("Invalid fragment length" + piece);
				return false;
			}
			String pos1 = piece.substring(0, 1);
			if ("/".equals(pos1)) {
				System.out.println("Uncorrect '/' spare symbol at first position");
				return false;
			}
			if (piece.length() == 2) {
				boolean isNum1 = checkSequence(pos1, "123456789");
				String pos2 = piece.substring(1, 2);
				boolean isNum2 = checkSequence(pos2, "123456789");
				if (isNum1 && isNum2 && Integer.valueOf(pos1) + Integer.valueOf(pos2) > Constants.MAX_POINTS) {
					System.out.println(
							"Sum of throw scores : " + pos1 + " + " + pos2 + " exceeds " + Constants.MAX_POINTS);
					return false;
				}
			}
		}
		System.out.println("Fragments are correct");
		return true;
	}

	public void populateFrameList() {
		for (int i = 0; i < pieces.length; i++) {
			Frame frame = new Frame(false);
			if (i == maxFrameNumber) {
				frame = new Frame(true);
			}
			String pos1 = pieces[i].substring(0, 1);
			int num1 = 0;
			if ("X".equals(pos1)) {
				num1 = 10;
				frame.setStrike(!frame.isBonusFrame());
			}
			if (checkSequence(pos1, "123456789")) {
				num1 = Integer.valueOf(pos1);
			}
			BowlingThrow throw1 = new BowlingThrow(num1, false);
			frame.setThrow1(throw1);
			if (pieces[i].length() > 1) {
				String pos2 = pieces[i].substring(1, 2);
				int num2 = 0;
				if ("X".equals(pos2)) {
					num2 = 10;
				}
				if ("/".equals(pos2)) {
					num2 = 10 - num1;
					frame.setSpare(!frame.isBonusFrame());
				}
				if (checkSequence(pos2, "123456789")) {
					num2 = Integer.valueOf(pos2);
				}
				BowlingThrow throw2 = new BowlingThrow(num2, false);
				frame.setThrow2(throw2);
			}
			getFrameList().add(frame);
		}
	}

	public String[] getPieces() {
		return pieces;
	}

	public void setPieces(String[] pieces) {
		this.pieces = pieces;
	}

	public List<Frame> getFrameList() {
		return frameList;
	}

}
