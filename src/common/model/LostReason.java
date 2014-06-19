package common.model;

import java.io.Serializable;

import common.enums.LostReasonMessage;

public class LostReason implements Serializable {

	String playerNick;
	LostReasonMessage reasonMsg;

	public LostReason(String playerNick, LostReasonMessage reason) {
		super();
		this.playerNick = playerNick;
		this.reasonMsg = reason;
	}

	public String getPlayerNick() {
		return playerNick;
	}

	public void setPlayerNick(String playerNick) {
		this.playerNick = playerNick;
	}

	public LostReasonMessage getReasonMsg() {
		return reasonMsg;
	}

	public void setReasonMsg(LostReasonMessage reasonMsg) {
		this.reasonMsg = reasonMsg;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2424439576143175339L;
}
