package server;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import common.gameRules.GameMode;
import common.model.Board;
import common.model.Result;
import common.network.GameManager;
import common.network.PlayerHandler;

public class GameManagerImpl implements GameManager {
	Map<String, BoardDispatcher> players = new HashMap<String, BoardDispatcher>();

	@Override
	public String tmpMsg(String msg) throws RemoteException {
		System.out.println("User: " + msg + ", connected...");
		return msg + String.valueOf(msg.length() + 1);
	}

	@Override
	public Boolean createNewGame(String userNick, GameMode gm, PlayerHandler playerHandler) throws RemoteException {
		System.out.println("Create game for user = " + userNick);
		players.put(userNick, new BoardDispatcher(new Board(1), playerHandler));
		return true;
	}

	@Override
	public Boolean joinGame(String userNick, PlayerHandler playerHandler) throws RemoteException {
		players.put(userNick, new BoardDispatcher(new Board(1), playerHandler));
		return true;
	}

	@Override
	public Result shot(String userNick, int position) throws RemoteException {
		System.out.println("Got shor for user=" + userNick + ", pos = " + position);
		System.out.println("Players amount = " + players.size());
		Result res = players.get(userNick).shot(position);
		for (String nick : players.keySet()) {
			if (!nick.equals(userNick)) {
				players.get(nick).informOpponent(position, res);
			}
		}
		return res;
	}

	@Override
	public void resetBoard(String userNick) throws RemoteException {
		players.get(userNick).generateBombs(1);
	}

}
