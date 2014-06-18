package client.views;

import java.awt.Toolkit;

import javax.swing.JFrame;

import client.internationalization.DialogText;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Component;

import javax.swing.Box;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JSeparator;

import java.awt.Color;

import javax.swing.SwingConstants;

public class GameRoomDialog extends DialogBase {

	public class Opponent {
		public JLabel nameLabel;
		public JLabel stateLabel;
	}
	
	private JPanel panel_opponents;
	
	private Map<String, Opponent> opponentsMap = new HashMap<String, Opponent>();
	
	public GameRoomDialog(String gameName, String playerName, JFrame owner, boolean isModal) {
		super(owner, isModal);
		
		buildGUI(gameName, playerName, owner);
	}
	
	private void buildGUI(String gameName, String playerName, JFrame owner) {
		setBounds(100, 100, 400, 300);;
		setAlwaysOnTop(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				NewGameDialog.class.getResource("/resources/images/flag.png")));
		setTitle(DialogText.GAMEROOM_TITLE + " " + gameName);
		setLocationRelativeTo(owner);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panel_player = new JPanel();
		GridBagConstraints gbc_panel_player = new GridBagConstraints();
		gbc_panel_player.insets = new Insets(0, 0, 0, 5);
		gbc_panel_player.fill = GridBagConstraints.BOTH;
		gbc_panel_player.gridx = 0;
		gbc_panel_player.gridy = 0;
		getContentPane().add(panel_player, gbc_panel_player);
		GridBagLayout gbl_panel_player = new GridBagLayout();
		gbl_panel_player.columnWidths = new int[]{0};
		gbl_panel_player.rowHeights = new int[]{0, 0, 0};
		gbl_panel_player.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_panel_player.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_player.setLayout(gbl_panel_player);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 0);
		gbc_verticalStrut.gridx = 0;
		gbc_verticalStrut.gridy = 0;
		panel_player.add(verticalStrut, gbc_verticalStrut);
		
		JLabel lblPlayername = new JLabel(playerName);
		GridBagConstraints gbc_lblPlayername = new GridBagConstraints();
		gbc_lblPlayername.insets = new Insets(0, 0, 5, 0);
		gbc_lblPlayername.gridx = 0;
		gbc_lblPlayername.gridy = 1;
		panel_player.add(lblPlayername, gbc_lblPlayername);
		
		JButton btnReady = new JButton(DialogText.PLAYERSTATE_READY);
		btnReady.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setPlayerReady();
			}
		});
		btnReady.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		GridBagConstraints gbc_btnReady = new GridBagConstraints();
		gbc_btnReady.insets = new Insets(50, 50, 50, 50);
		gbc_btnReady.fill = GridBagConstraints.BOTH;
		gbc_btnReady.gridx = 0;
		gbc_btnReady.gridy = 2;
		panel_player.add(btnReady, gbc_btnReady);
		
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
		
		panel_opponents = new JPanel();
		GridBagConstraints gbc_panel_opponents = new GridBagConstraints();
		gbc_panel_opponents.fill = GridBagConstraints.BOTH;
		gbc_panel_opponents.gridx = 2;
		gbc_panel_opponents.gridy = 0;
		getContentPane().add(panel_opponents, gbc_panel_opponents);
		GridBagLayout gbl_panel_opponents = new GridBagLayout();
		gbl_panel_opponents.columnWidths = new int[]{0};
		gbl_panel_opponents.rowHeights = new int[]{0};
		gbl_panel_opponents.columnWeights = new double[]{0.0};
		gbl_panel_opponents.rowWeights = new double[]{0.0};
		panel_opponents.setLayout(gbl_panel_opponents);
	}
	
	public void setPlayerReady() {
		//TODO send playerIsReady message
	}
	
	public void addOpponent(String opponentName) {
		Opponent opponent = new Opponent();
		
		opponent.nameLabel = new JLabel(opponentName);
		GridBagConstraints gbc_opponentNameLabel = new GridBagConstraints();
		gbc_opponentNameLabel.insets = new Insets(10, 10, 10, 10);
		gbc_opponentNameLabel.anchor = GridBagConstraints.NORTH;
		gbc_opponentNameLabel.gridx = 0;
		gbc_opponentNameLabel.gridy = opponentsMap.size();
		panel_opponents.add(opponent.nameLabel, gbc_opponentNameLabel);
		
		opponent.stateLabel = new JLabel("Waiting...");
		GridBagConstraints gbc_opponentPointsLabel = new GridBagConstraints();
		gbc_opponentPointsLabel.insets = new Insets(10, 10, 10, 10);
		gbc_opponentPointsLabel.anchor = GridBagConstraints.NORTH;
		gbc_opponentPointsLabel.gridx = 1;
		gbc_opponentPointsLabel.gridy = opponentsMap.size();
		panel_opponents.add(opponent.stateLabel, gbc_opponentPointsLabel);
		
		opponentsMap.put(opponentName, opponent);		
	}
	
	public void removeOpponent(String opponentName) {
		Opponent opponent = opponentsMap.get(opponentName);
		
		opponentsMap.remove(opponentName);
		panel_opponents.remove(opponent.nameLabel);
		panel_opponents.remove(opponent.stateLabel);
	}
	
	public void setOpponentReady(String opponentName) {
		opponentsMap.get(opponentName).stateLabel.setText(DialogText.PLAYERSTATE_READY);
		if (isEveryoneIsReady()) {
			onEveryoneIsReady();
		}
	}
	
	public boolean isEveryoneIsReady() {
		for (String key : opponentsMap.keySet()) {
			if (!opponentsMap.get(key).nameLabel.getText().equals(DialogText.PLAYERSTATE_READY)) {
				return false;
			}
		}
		return true;
	}
	
	public void onEveryoneIsReady() {
		//TODO send everyoneIsReady message
	}
	
	public void setOpponentWaiting(String opponentName) {
		opponentsMap.get(opponentName).stateLabel.setText(DialogText.PLAYERSTATE_WAITING);
	}
}
