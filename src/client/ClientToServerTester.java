package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.List;
import java.util.Random;

import client.network.PlayerHandlerImpl;

import common.enums.GameDifficulty;
import common.enums.GameMode;
import common.exceptions.create.InvalidGameNameException;
import common.exceptions.create.MaxOpponentSizeIsTooLarge;
import common.exceptions.create.MaximumRoomExceededException;
import common.exceptions.join.MaximumPlayerExceededException;
import common.exceptions.join.PlayerWithIdenticalNickAlreadyInGame;
import common.exceptions.shot.PositionOutOfRange;
import common.model.Config;
import common.model.GameSettings;
import common.model.ShotResult;
import common.network.protocols.GameLogic;
import common.network.protocols.GameManager;

public class ClientToServerTester {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) {

		while (true) {
			try {
				System.out.println("Get");
				String s = br.readLine();
				if (s.equals("1")) {
					connect(1);
				} else if (s.equals("q")) {
					System.exit(0);
				} else if (s.equals("2")) {
					connect(2);
				} else if (s.equals("3")) {
					connect(3);
				} else {
					connect(0);
				}
			} catch (Exception e) {
				System.err.println("error" + e.getClass().getName().toString());
				e.printStackTrace();
			}
		}
	}

	public static void connect(int choice) throws NotBoundException, InvalidGameNameException,
			MaximumRoomExceededException, MaxOpponentSizeIsTooLarge,
			MaximumPlayerExceededException, PlayerWithIdenticalNickAlreadyInGame, IOException,
			NumberFormatException, PositionOutOfRange {
		int val = new Random(System.currentTimeMillis()).nextInt(100);
		String userId = "user" + String.valueOf(val);
		String gameId = "game" + String.valueOf(val);

		GameManager remoteGameManager;
		remoteGameManager = (GameManager) Naming.lookup("rmi://" + "127.0.0.1:1099/roommanager");
		remoteGameManager.tmpMsg(userId);

		PlayerHandlerImpl handler = new PlayerHandlerImpl(null);

		int maxPlayers = new Random(System.currentTimeMillis()).nextInt(4) + 1;
		if (choice == 1) {
			print("maxPlayers[%d]", maxPlayers);
			remoteGameManager.createNewGame(new Config(userId, GameMode.CLASSIC, handler,
					GameDifficulty.EASY, gameId, false, false, false, 0, 1, maxPlayers));
		} else if (choice == 2) {
			System.out.println("Podaj Id(ebz game):");
			remoteGameManager.joinGame(userId, "game" + br.readLine(), handler);
		} else if (choice == 3) {
			// bad bad, itd be better to shot for prevoious
			System.out.println("Podaj Id(ebz game):");
			GameSettings set = remoteGameManager.joinGame(userId, "game" + br.readLine(), handler);
			GameLogic engine = set.getEngine();
			System.out.println("Podaj pozycje:");
			List<ShotResult> res = engine.shot(userId, Integer.valueOf(br.readLine()));

			for (ShotResult sr : res) {
				System.out.println(String.format("Pos[%d], field[%d]", sr.getPosition(),
						sr.getValue()));
			}
		} else {
			remoteGameManager.getGameList();
		}
	}

	public static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}

}
