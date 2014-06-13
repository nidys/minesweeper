package client.views;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import org.apache.log4j.Logger;

import client.internationalization.ButtonNames;

import static client.internationalization.ButtonNames.NEW_GAME;

import common.network.ServerAddress;

/**
 * View for the Main component (view with the game board). Standard window.
 */
@SuppressWarnings("serial")
public class MainWindow extends WindowBase {
	private static Logger log = Logger.getLogger(MainWindow.class);

	private JButton newGameBtn;
	private JButton btnReset;
	private JTextField serverAddress;
	private JTextField userNick;
	private JButton[] myBombField;
	private JButton[] oponentBombField;

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		drawEntryScreen();
	}

	public void drawGameBoard() {
		clearWindow();
		drawComponents();
	}

	private void clearWindow() {
		getContentPane().removeAll();
		getContentPane().repaint();
	}

	public void drawComponents() {
		setBounds(100, 100, 293, 227);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblMe = new JLabel("Me");
		GridBagConstraints gbc_lblMe = new GridBagConstraints();
		gbc_lblMe.gridwidth = 2;
		gbc_lblMe.insets = new Insets(0, 0, 5, 5);
		gbc_lblMe.gridx = 0;
		gbc_lblMe.gridy = 0;
		getContentPane().add(lblMe, gbc_lblMe);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridheight = 2;
		gbc_separator.gridx = 2;
		gbc_separator.gridy = 0;
		getContentPane().add(separator, gbc_separator);

		JLabel lblOpponent = new JLabel("Opponent");
		GridBagConstraints gbc_lblOpponent = new GridBagConstraints();
		gbc_lblOpponent.gridwidth = 2;
		gbc_lblOpponent.insets = new Insets(0, 0, 5, 5);
		gbc_lblOpponent.gridx = 3;
		gbc_lblOpponent.gridy = 0;
		getContentPane().add(lblOpponent, gbc_lblOpponent);

		myBombField = new JButton[] { // <br>
		createBombFieldBtn("1", 0, 0, 5, 5, 0, 1), // <br>
				createBombFieldBtn("2", 0, 0, 5, 5, 1, 1), // <br>
				createBombFieldBtn("3", 0, 0, 5, 5, 0, 2), // <br>
				createBombFieldBtn("4", 0, 0, 5, 5, 1, 2) // <br>

		};
		oponentBombField = new JButton[] { // <br>
		createBombFieldBtn("1", 0, 0, 5, 5, 3, 1), // <br>
				createBombFieldBtn("2", 0, 0, 5, 0, 4, 1), // <br>
				createBombFieldBtn("3", 0, 0, 5, 5, 3, 2), // <br>
				createBombFieldBtn("4", 0, 0, 5, 0, 4, 2) // <br>

		};

		btnReset = new JButton(ButtonNames.RESET);
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.insets = new Insets(0, 0, 0, 5);
		gbc_btnReset.gridx = 0;
		gbc_btnReset.gridy = 4;
		getContentPane().add(btnReset, gbc_btnReset);
	}

	private JButton createBombFieldBtn(String name, int top, int left, int bottom, int right, int gridx, int gridy) {
		JButton btn = new JButton(name);
		GridBagConstraints gbc_btn = new GridBagConstraints();
		gbc_btn.insets = new Insets(top, left, bottom, right);
		gbc_btn.gridx = gridx;
		gbc_btn.gridy = gridy;
		getContentPane().add(btn, gbc_btn);
		return btn;
	}

	private void drawEntryScreen() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		serverAddress = new JTextField();
		serverAddress.setText(ServerAddress.LOCALHOST.getValue());
		serverAddress.setColumns(10);

		userNick = new JTextField();
		// generate random user name so that user don't need to write it all the
		// time
		userNick.setText("user" + String.valueOf(new Random().nextInt(100)));
		userNick.setColumns(10);

		newGameBtn = new JButton(NEW_GAME);
		newGameBtn.setBackground(Color.BLUE);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addGap(158)
								.addGroup(
										groupLayout
												.createParallelGroup(Alignment.LEADING)
												.addComponent(userNick, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(newGameBtn)
												.addComponent(serverAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)).addContainerGap(176, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout.createSequentialGroup().addGap(83)
						.addComponent(serverAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(userNick, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addGap(29)
						.addComponent(newGameBtn).addContainerGap(78, Short.MAX_VALUE)));
		getContentPane().setLayout(groupLayout);
	}

	/**
	 * @param listener
	 *            Listener for an action when the user clicks the "New Game"
	 *            button.
	 */
	public void addNewGameBtnListener(ActionListener listener) {
		newGameBtn.addActionListener(listener);
	}

	public void addResetBtnListener(ActionListener listener) {
		btnReset.addActionListener(listener);
	}

	public void addBombFieldBtnListener(ActionListener listener) {
		for (int i = 0; i < myBombField.length; i++) {
			myBombField[i].addActionListener(listener);
		}
	}

	public void addOponentFieldBtnListener(ActionListener listener) {
		for (int i = 0; i < myBombField.length; i++) {
			oponentBombField[i].addActionListener(listener);
		}
	}

	public String getServerAddress() {
		return serverAddress.getText();
	}

	public String getUserNick() {
		return userNick.getText();
	}

	public void setMyFieldAsBomb(int pos) {
		// TODO from now shot return list of fields to discover
		myBombField[pos - 1].setBackground(Color.RED);
	}

	public void setMyFieldAsEmpty(int pos) {
		// TODO from now shot return list of fields to discover
		myBombField[pos - 1].setBackground(Color.GRAY);
	}

	/**
	 * TODO PlayerHandler.opponentShot, commented until SHARED,PERKS will be
	 * developed
	 */
	public void setOpponentAsBomb(int pos) {
		oponentBombField[pos - 1].setBackground(Color.RED);
	}

	/**
	 * TODO PlayerHandler.opponentShot, commented until SHARED,PERKS will be
	 * developed
	 */
	public void setOpponentAsEmpty(int pos) {
		oponentBombField[pos - 1].setBackground(Color.GRAY);
	}

	public void resetMyFields() {
		for (int i = 0; i < myBombField.length; i++) {
			myBombField[i].setBackground(null);
		}
	}
}
