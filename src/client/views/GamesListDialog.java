package client.views;

import java.awt.Toolkit;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;

import client.controllers.JoinRoomBtnController;
import client.internationalization.DialogText;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.GridBagConstraints;

import javax.swing.JList;

import common.model.AvailableGameInfo;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

@SuppressWarnings("serial")
public class GamesListDialog extends DialogBase {

	public class Game {
		String name;
		String address;

		public Game(String gameName, String gameAddress) {
			name = gameName;
			address = gameAddress;
		}

		@Override
		public String toString() {
			return name + " (" + address + ")";
		}
	}

	// TODO Skwara To be replaced Game -> AvailableGameInfo 
	private JList<Game> gamesList;
	private DefaultListModel<Game> model;
	private JButton joinBtn;

	public GamesListDialog(JFrame owner, boolean isModal) {
		super(owner, isModal);
		buildGUI(owner);
	}

	private void buildGUI(JFrame owner) {
		setBounds(100, 100, 400, 300);
		setAlwaysOnTop(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				NewGameDialog.class.getResource("/resources/images/flag.png")));
		setTitle(DialogText.GAMESLIST_TITLE);
		setLocationRelativeTo(owner);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		model = new DefaultListModel<Game>();
		gamesList = new JList<Game>(model);
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 0);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 0;
		getContentPane().add(gamesList, gbc_list);

		joinBtn = new JButton("Join");
		GridBagConstraints gbc_joinBtn = new GridBagConstraints();
		gbc_joinBtn.gridx = 0;
		gbc_joinBtn.gridy = 1;
		getContentPane().add(joinBtn, gbc_joinBtn);
	}

	public void addJoinGameBtnListener(JoinRoomBtnController listener) {
		joinBtn.addActionListener(listener);
	}
	
	public void setGames(List<AvailableGameInfo> games) {
		for(AvailableGameInfo game : games) {
			// TODO Skwara To be replaced Game -> AvailableGameInfo 
			model.addElement(new Game(game.getGameId(), game.getHostUser()));
		}
	}

	public void removeGame(String gameName) {
		for (int i = 0; i < model.getSize(); i++) {
			if (model.elementAt(i).name.equals(gameName)) {
				model.removeElement(model.elementAt(i));
			}
		}
	}

	public String getSelectedGame() {
		return gamesList.getSelectedValue().name;
	}
}
