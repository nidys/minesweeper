package client.views;

import static common.utils.LoggingHelper.info;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import server.GameManagerImpl;
import client.controllers.ReadyBtnController;
import client.controllers.StartGameBtnController;
import client.internationalization.ButtonNames;
import client.internationalization.DialogText;

public class GameRoomDialog extends DialogBase {
	private static Logger log = Logger.getLogger(GameRoomDialog.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public class Opponent {
		public JLabel nameLabel;
		public JLabel stateLabel;
	}

	private JPanel opponentsPanel;
	private JButton startBtn;
	private Map<String, Opponent> opponentsMap = new HashMap<String, Opponent>();
	private JButton readyBtn;
	private JLabel hostNameLbl;
	
	private boolean isHost = false;

	public GameRoomDialog(String gameName, String playerName, boolean isHost, JFrame owner,
			boolean isModal) {
		super(owner, isModal);
		this.isHost = isHost;
		initGUI(gameName, playerName, isHost, owner);
	}

	private void initGUI(String gameName, String hostName, boolean isHost, JFrame owner) {
		setBounds(100, 100, 400, 300);
		
		setAlwaysOnTop(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				NewGameDialog.class.getResource("/resources/images/flag.png")));
		setTitle(DialogText.GAMEROOM_TITLE + " " + gameName);
		setLocationRelativeTo(owner);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JPanel playerPanel = new JPanel();
		GridBagConstraints gbc_playerPanel = new GridBagConstraints();
		gbc_playerPanel.insets = new Insets(0, 0, 0, 5);
		gbc_playerPanel.fill = GridBagConstraints.BOTH;
		gbc_playerPanel.gridx = 0;
		gbc_playerPanel.gridy = 0;
		getContentPane().add(playerPanel, gbc_playerPanel);
		GridBagLayout gbl_playerPanel = new GridBagLayout();
		gbl_playerPanel.columnWidths = new int[] { 0 };
		gbl_playerPanel.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_playerPanel.columnWeights = new double[] { Double.MIN_VALUE };
		gbl_playerPanel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE,
				0.0 };
		playerPanel.setLayout(gbl_playerPanel);

		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 0);
		gbc_verticalStrut.gridx = 0;
		gbc_verticalStrut.gridy = 0;
		playerPanel.add(verticalStrut, gbc_verticalStrut);

		hostNameLbl = new JLabel(hostName);
		GridBagConstraints gbc_hostNameLbl = new GridBagConstraints();
		gbc_hostNameLbl.insets = new Insets(0, 0, 5, 0);
		gbc_hostNameLbl.gridx = 0;
		gbc_hostNameLbl.gridy = 1;
		playerPanel.add(hostNameLbl, gbc_hostNameLbl);

		readyBtn = new JButton(DialogText.PLAYERSTATE_READY);
		readyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setPlayerReady();
			}
		});
		readyBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		GridBagConstraints gbc_readyBtn = new GridBagConstraints();
		gbc_readyBtn.insets = new Insets(50, 50, 50, 50);
		gbc_readyBtn.fill = GridBagConstraints.BOTH;
		gbc_readyBtn.gridx = 0;
		gbc_readyBtn.gridy = 2;
		playerPanel.add(readyBtn, gbc_readyBtn);

		startBtn = new JButton(ButtonNames.START);
		startBtn.setFont(new Font("Dialog", Font.PLAIN, 20));
		GridBagConstraints gbc_startBtn = new GridBagConstraints();
		gbc_startBtn.gridx = 0;
		gbc_startBtn.gridy = 3;
		playerPanel.add(startBtn, gbc_startBtn);
		if (!isHost)
			startBtn.setVisible(false);
		else
			startBtn.setVisible(true);

		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.BLACK);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.VERTICAL;
		gbc_separator.insets = new Insets(0, 0, 0, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 0;
		getContentPane().add(separator, gbc_separator);

		opponentsPanel = new JPanel();
		GridBagConstraints gbc_opponentsPanel = new GridBagConstraints();
		gbc_opponentsPanel.anchor = GridBagConstraints.NORTH;
		gbc_opponentsPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_opponentsPanel.gridx = 2;
		gbc_opponentsPanel.gridy = 0;
		getContentPane().add(opponentsPanel, gbc_opponentsPanel);
		GridBagLayout gbl_opponentsPanel = new GridBagLayout();
		gbl_opponentsPanel.columnWidths = new int[] { 0, 0 };
		gbl_opponentsPanel.rowHeights = new int[] { 0 };
		gbl_opponentsPanel.columnWeights = new double[] { 1.0, 1.0 };
		gbl_opponentsPanel.rowWeights = new double[] { 1.0 };
		opponentsPanel.setLayout(gbl_opponentsPanel);
		
		// JList<Game> opponentsList = new JList<Game>((ListModel) null);
		// GridBagConstraints gbc_opponentsList = new GridBagConstraints();
		// gbc_opponentsList.fill = GridBagConstraints.BOTH;
		// gbc_opponentsList.gridx = 0;
		// gbc_opponentsList.gridy = 0;
		// opponentsPanel.add(opponentsList, gbc_opponentsList);
	}

	public void addStartGameBtnListener(StartGameBtnController listener) {
		startBtn.addActionListener(listener);
	}

	public void addReadyBtnListener(ReadyBtnController listener) {
		readyBtn.addActionListener(listener);
	}

	public void setPlayerReady() {
		// TODO send playerIsReady message
	}

	public void addOpponent(String opponentName) {
		Opponent opponent = new Opponent();

		opponent.nameLabel = new JLabel(opponentName);
		GridBagConstraints gbc_opponentNameLabel = new GridBagConstraints();
		gbc_opponentNameLabel.insets = new Insets(10, 10, 10, 10);
		gbc_opponentNameLabel.anchor = GridBagConstraints.NORTH;
		gbc_opponentNameLabel.gridx = 0;
		gbc_opponentNameLabel.gridy = opponentsMap.size();
		opponentsPanel.add(opponent.nameLabel, gbc_opponentNameLabel);

		opponent.stateLabel = new JLabel(DialogText.PLAYERSTATE_WAITING);
		GridBagConstraints gbc_opponentPointsLabel = new GridBagConstraints();
		gbc_opponentPointsLabel.insets = new Insets(10, 10, 10, 10);
		gbc_opponentPointsLabel.anchor = GridBagConstraints.NORTH;
		gbc_opponentPointsLabel.gridx = 1;
		gbc_opponentPointsLabel.gridy = opponentsMap.size();
		opponentsPanel.add(opponent.stateLabel, gbc_opponentPointsLabel);

		opponentsMap.put(opponentName, opponent);
		
		info(log, "addOpponent[%s] to panel !!!!!!!!!!!!!!!!!!!!!", opponentName);
	}

	public void removeOpponent(String opponentName) {
		Opponent opponent = opponentsMap.get(opponentName);

		opponentsMap.remove(opponentName);
		opponentsPanel.remove(opponent.nameLabel);
		opponentsPanel.remove(opponent.stateLabel);
	}

	public void setOpponentReady(String opponentName) {
		if (opponentName.compareTo(hostNameLbl.getText()) == 0)
			return;
		
		Opponent op = opponentsMap.get(opponentName);
		
		if (op == null){
			info(log, "Something wrong, no opponent in map[%s], your name is [%s]", opponentName, hostNameLbl.getText());
			return;
		}
			
		op.stateLabel
				.setText(DialogText.PLAYERSTATE_READY);
		if (isEveryoneIsReady()) {
			onEveryoneIsReady();
		}
	}

	public boolean isEveryoneIsReady() {
		for (String key : opponentsMap.keySet()) {
			if (!opponentsMap.get(key).nameLabel.getText().equals(
					DialogText.PLAYERSTATE_READY)) {
				return false;
			}
		}
		return true;
	}

	public void onEveryoneIsReady() {
		// TODO send everyoneIsReady message
		
		startBtn.setEnabled(true);
		
		
	}

	public void setOpponentWaiting(String opponentName) {
		opponentsMap.get(opponentName).stateLabel
				.setText(DialogText.PLAYERSTATE_WAITING);
	}
	
	public void simulateStartButtonClick(){
		
		if (!isHost){
			info(log, "click!");
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
