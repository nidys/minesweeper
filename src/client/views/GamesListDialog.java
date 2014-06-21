package client.views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import client.controllers.CancelBtnController;
import client.controllers.JoinRoomBtnController;
import client.controllers.RefreshGamesListBtnController;
import client.internationalization.ButtonNames;
import client.internationalization.DialogText;
import common.enums.GameMode;
import common.model.AvailableGameInfo;

import javax.swing.JPanel;

import java.awt.Font;

@SuppressWarnings("serial")
public class GamesListDialog extends DialogBase {


	private JList<AvailableGameInfo> gamesList;
	private DefaultListModel<AvailableGameInfo> model;
	private JButton joinBtn;
	private JButton refreshBtn;

	public GamesListDialog(JFrame owner, boolean isModal) {
		super(owner, isModal);
		buildGUI(owner);
	}

	private void buildGUI(JFrame owner) {
		setBounds(100, 100, 322, 300);
		setAlwaysOnTop(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				NewGameDialog.class.getResource("/resources/images/flag.png")));
		setTitle(DialogText.GAMESLIST_TITLE);
		setLocationRelativeTo(owner);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{181};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel btnPanel = new JPanel();
		GridBagConstraints gbc_btnPanel = new GridBagConstraints();
		gbc_btnPanel.fill = GridBagConstraints.BOTH;
		gbc_btnPanel.gridx = 0;
		gbc_btnPanel.gridy = 1;
		getContentPane().add(btnPanel, gbc_btnPanel);
		GridBagLayout gbl_btnPanel = new GridBagLayout();
		gbl_btnPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_btnPanel.rowHeights = new int[]{0, 0};
		gbl_btnPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_btnPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		btnPanel.setLayout(gbl_btnPanel);

		joinBtn = new JButton(ButtonNames.JOIN);
		joinBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_joinBtn = new GridBagConstraints();
		gbc_joinBtn.insets = new Insets(0, 0, 0, 5);
		gbc_joinBtn.gridx = 1;
		gbc_joinBtn.gridy = 0;
		btnPanel.add(joinBtn, gbc_joinBtn);
		joinBtn.setEnabled(false);
		
		refreshBtn = new JButton(ButtonNames.REFRESH);
		GridBagConstraints gbc_refreshBtn = new GridBagConstraints();
		gbc_refreshBtn.insets = new Insets(0, 0, 0, 5);
		gbc_refreshBtn.gridx = 3;
		gbc_refreshBtn.gridy = 0;
		btnPanel.add(refreshBtn, gbc_refreshBtn);
		
		JButton cancelBtn = new JButton(ButtonNames.CANCEL);
		GridBagConstraints gbc_cancelBtn = new GridBagConstraints();
		gbc_cancelBtn.insets = new Insets(0, 0, 0, 5);
		gbc_cancelBtn.gridx = 5;
		gbc_cancelBtn.gridy = 0;
		btnPanel.add(cancelBtn, gbc_cancelBtn);
		
		model = new DefaultListModel<AvailableGameInfo>();
		gamesList = new JList<AvailableGameInfo>(model);
		gamesList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				 joinBtn.setEnabled(true);
			}
		});
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 0);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 0;
		getContentPane().add(gamesList, gbc_list);

	}

	public void addGame(String gameId, GameMode gameMode, String hostPlayerName, int maxPlayers,
			int currentlyConnectedPlayers) {
		AvailableGameInfo game = new AvailableGameInfo(gameId, gameMode, hostPlayerName, maxPlayers, currentlyConnectedPlayers);
		model.addElement(game);
	}

	public void addJoinGameBtnListener(JoinRoomBtnController listener) {
		joinBtn.addActionListener(listener);

	}

	
	public void addCanceltnListener(CancelBtnController listener) {
		joinBtn.addActionListener(listener);

	}

	public void addRefreshGamesListBtnListener(RefreshGamesListBtnController listener) {
		joinBtn.addActionListener(listener);

	}

	
	
	public void setGames(List<AvailableGameInfo> games) {
		for (AvailableGameInfo game : games) {
			model.addElement(game);
		}
	}

	public void removeGame(String gameId) {
		for (int i = 0; i < model.getSize(); i++) {
			if (model.elementAt(i).getGameId().equals(gameId)) {
				model.removeElement(model.elementAt(i));
			}
		}
	}

	public void setPlayersAmount(String gameId, int playersAmount) {
		for (int i = 0; i < model.getSize(); i++) {
			if (model.elementAt(i).getGameId().equals(gameId)) {
				model.elementAt(i).setCurrentlyConnectedPlayers(playersAmount);
			}
		}
	}

	public String getSelectedGame() {
		return gamesList.getSelectedValue().getGameId();
	}

}
