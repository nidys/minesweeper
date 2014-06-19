package common.model;

import java.io.Serializable;
import java.util.List;

public class ShotResult implements Serializable {
	private List<DiscoveredField> unrevealed;
	private int lifeLeft;
	private int timeLeft;
	private boolean canGameBeContinued;

	public ShotResult(List<DiscoveredField> unrevealed, int lifeLeft, int timeLeft,
			boolean canGameBeContinued) {
		super();
		this.unrevealed = unrevealed;
		this.lifeLeft = lifeLeft;
		this.timeLeft = timeLeft;
		this.canGameBeContinued = canGameBeContinued;
	}

	public ShotResult() {
		super();
	}

	public List<DiscoveredField> getUnrevealed() {
		return unrevealed;
	}

	public void setUnrevealed(List<DiscoveredField> unrevealed) {
		this.unrevealed = unrevealed;
	}

	public int getLifeLeft() {
		return lifeLeft;
	}

	public void setLifeLeft(int lifeLeft) {
		this.lifeLeft = lifeLeft;
	}

	public int getTimeLeft() {
		return timeLeft;
	}

	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}

	public boolean isCanGameBeContinued() {
		return canGameBeContinued;
	}

	public void setCanGameBeContinued(boolean canGameBeContinued) {
		this.canGameBeContinued = canGameBeContinued;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4320527015687567544L;

}
