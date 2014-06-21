package client.views;

import static common.utils.LoggingHelper.info;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import client.controllers.ReadyBtnController;
import client.controllers.StartGameBtnController;
import client.internationalization.ButtonNames;
import client.internationalization.DescriptionText;
import client.internationalization.DialogText;

public class GameRoomDialog extends DialogBase {
	private static Logger log = Logger.getLogger(GameRoomDialog.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public class Opponent {
		public int idx;
		public boolean isReady;
		public String playerName;
		public String playerState;
	}
	private JButton startBtn;
	private Map<String, Opponent> opponentsMap = new HashMap<String, Opponent>();
	private JToggleButton readyBtn;
	private JLabel hostNameLbl;

	private boolean isHost = false;
	private JList<String> opponentList;
	private DefaultListModel<String> model;
	private JLabel connectedPlayersLbl;

	public GameRoomDialog(String gameName, String playerName, boolean isHost, JFrame owner,
			boolean isModal) {
		super(owner, isModal);
		this.isHost = isHost;
		initGUI(gameName, playerName, isHost, owner);
	}

	private void initGUI(String gameName, String hostName, boolean isHost, JFrame owner) {
		setBounds(100, 100, 400, 204);

		setAlwaysOnTop(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				NewGameDialog.class.getResource("/resources/images/flag.png")));
		setTitle(DialogText.GAMEROOM_TITLE + " " + gameName);
		setLocationRelativeTo(owner);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{218, -22, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);

		hostNameLbl = new JLabel(hostName);
		GridBagConstraints gbc_hostNameLbl = new GridBagConstraints();
		gbc_hostNameLbl.insets = new Insets(0, 0, 5, 5);
		gbc_hostNameLbl.gridx = 0;
		gbc_hostNameLbl.gridy = 0;
		getContentPane().add(hostNameLbl, gbc_hostNameLbl);
		hostNameLbl.setFont(new Font("Tahoma", Font.BOLD, 15));

		connectedPlayersLbl = new JLabel(DescriptionText.CONNECTED_PLAYERS);
		GridBagConstraints gbc_connectedPlayersLbl = new GridBagConstraints();
		gbc_connectedPlayersLbl.anchor = GridBagConstraints.WEST;
		gbc_connectedPlayersLbl.insets = new Insets(0, 0, 5, 0);
		gbc_connectedPlayersLbl.gridx = 2;
		gbc_connectedPlayersLbl.gridy = 0;
		getContentPane().add(connectedPlayersLbl, gbc_connectedPlayersLbl);

		JPanel playerPanel = new JPanel();
		GridBagConstraints gbc_playerPanel = new GridBagConstraints();
		gbc_playerPanel.anchor = GridBagConstraints.NORTH;
		gbc_playerPanel.insets = new Insets(0, 0, 0, 5);
		gbc_playerPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_playerPanel.gridx = 0;
		gbc_playerPanel.gridy = 1;
		getContentPane().add(playerPanel, gbc_playerPanel);
		GridBagLayout gbl_playerPanel = new GridBagLayout();
		gbl_playerPanel.columnWidths = new int[]{0};
		gbl_playerPanel.rowHeights = new int[]{0, 0, 0};
		gbl_playerPanel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_playerPanel.rowWeights = new double[]{0.0, 0.0, 0.0};
		playerPanel.setLayout(gbl_playerPanel);

		readyBtn = new JToggleButton(DescriptionText.PLAYERSTATE_READY);
		readyBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		GridBagConstraints gbc_readyBtn = new GridBagConstraints();
		gbc_readyBtn.insets = new Insets(5, 0, 5, 0);
		gbc_readyBtn.fill = GridBagConstraints.BOTH;
		gbc_readyBtn.gridx = 0;
		gbc_readyBtn.gridy = 1;
		playerPanel.add(readyBtn, gbc_readyBtn);

		startBtn = new JButton(ButtonNames.START);
		startBtn.setFont(new Font("Dialog", Font.PLAIN, 20));
		GridBagConstraints gbc_startBtn = new GridBagConstraints();
		gbc_startBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_startBtn.insets = new Insets(5, 0, 0, 0);
		gbc_startBtn.gridx = 0;
		gbc_startBtn.gridy = 2;
		playerPanel.add(startBtn, gbc_startBtn);
//		startBtn.setEnabled(false);
		if (!isHost)
			startBtn.setVisible(false);
		else
			startBtn.setVisible(true);

		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.BLACK);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.anchor = GridBagConstraints.NORTH;
		gbc_separator.insets = new Insets(0, 0, 0, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 1;
		getContentPane().add(separator, gbc_separator);

		model = new DefaultListModel<String>();
		opponentList = new JList<String>(model);
		opponentList.setValueIsAdjusting(true);
		GridBagConstraints gbc_opponentList = new GridBagConstraints();
		gbc_opponentList.fill = GridBagConstraints.BOTH;
		gbc_opponentList.gridx = 2;
		gbc_opponentList.gridy = 1;
		getContentPane().add(opponentList, gbc_opponentList);

	}

	public void addStartGameBtnListener(StartGameBtnController listener) {
		startBtn.addActionListener(listener);
	}

	public void addReadyBtnListener(ReadyBtnController listener) {
		readyBtn.addActionListener(listener);
	}

	public void addOpponent(String opponentName) {
		Opponent opponent = new Opponent();

		opponent.playerName = opponentName;
		opponent.playerState = DescriptionText.PLAYERSTATE_WAITING;
		opponent.idx = model.getSize();
		model.addElement(opponent.playerName + "[" + opponent.playerState + "]");

		opponentsMap.put(opponentName, opponent);

		info(log, "Opponent[%s] added to opponnentList", opponentName);
	}

	public void removeOpponent(String opponentName) {
		Opponent opponent = opponentsMap.get(opponentName);

		opponentsMap.remove(opponentName);

		model.remove(opponent.idx);
	}

	public void changeOpponentState(String opponentName) {
		if (opponentName.compareTo(hostNameLbl.getText()) == 0)
			return;

		Opponent op = opponentsMap.get(opponentName);
		if (op == null)
			return;

		op.isReady = !op.isReady;

		if (op.isReady)
			op.playerState = DescriptionText.PLAYERSTATE_READY;
		else
			op.playerState = DescriptionText.PLAYERSTATE_WAITING;

		model.setElementAt(op.playerName + "[" + op.playerState + "]", op.idx);

		if (isEveryoneIsReady()) {
			onEveryoneIsReady();
		}
	}

	public boolean isEveryoneIsReady() {
		for (String key : opponentsMap.keySet()) {
			if (!opponentsMap.get(key).isReady) {
				return false;
			}
		}

		return true;
	}

	// TODO Check somehow if hostPlayer is ready.
	public void onEveryoneIsReady() {

		startBtn.setEnabled(true);

	}

	public void setOpponentWaiting(String opponentName) {
		Opponent op = opponentsMap.get(opponentName);

		op.playerState = DescriptionText.PLAYERSTATE_WAITING;

		model.setElementAt(op.playerName + "[" + op.playerState + "]", op.idx);
	}

	public void simulateStartButtonClick() {

		if (!isHost) {
			info(log, "simulated click!");
			startBtn.doClick();
		}
	}

	public boolean isHost() {
		return isHost;
	}

	public void setHost(boolean isHost) {
		this.isHost = isHost;
	}

	public Map<String, Opponent> getOpponentsMap() {
		return opponentsMap;
	}

	public void setOpponentsMap(Map<String, Opponent> opponentsMap) {
		this.opponentsMap = opponentsMap;
	}
}
