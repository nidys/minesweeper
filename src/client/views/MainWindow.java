package client.views;

import static client.internationalization.ButtonNames.NEW_GAME;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import client.controllers.MyBombFielsBtnController;
import client.internationalization.ButtonNames;
import client.internationalization.DialogText;
import client.views.component.PlayerGameBoardPanel;

import common.enums.GameMode;
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
	
	private GamePanelBase gamePanel;
	/**
	 * Create the frame.
	 */
	public MainWindow() {
		drawEntryScreen();
		//drawGameBoard();
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
		setBounds(100, 100, 456, 356);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
			
						btnReset = new JButton(ButtonNames.RESET);
						menuBar.add(btnReset);
						getContentPane().setBounds(0,0,450,301);
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
		
		JLabel lblMinesweeper = new JLabel("Minesweeper");
		lblMinesweeper.setFont(new Font("Tahoma", Font.PLAIN, 56));
		
		JLabel lblServerAdress = new JLabel("Server adress:");
		
		JLabel lblUserName = new JLabel("User name:");

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(94)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblServerAdress)
								.addComponent(lblUserName))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(userNick, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(newGameBtn)
								.addComponent(serverAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(43)
							.addComponent(lblMinesweeper)))
					.addContainerGap(190, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addComponent(lblMinesweeper)
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(serverAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblServerAdress))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(userNick, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUserName))
					.addGap(29)
					.addComponent(newGameBtn)
					.addContainerGap(68, Short.MAX_VALUE))
		);
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
	
	public void addBombFieldBtnListener(MyBombFielsBtnController listener) {
		gamePanel.addBombFieldBtnListener(listener);
	}


	public void addNewPlayerToView(PlayerGameBoardPanel playerGameBoardPanel) {
		gamePanel.addPlayer(playerGameBoardPanel);
	}
	
	public String getServerAddress() {
		return serverAddress.getText();
	}

	public String getUserNick() {
		return userNick.getText();
	}
	
	
	
	public void initializeGameBoard(GameMode mode) {
		if (mode == GameMode.PERKS){
			gamePanel = new PerksGamePanel();
			getContentPane().add(gamePanel);
		}else if (mode == GameMode.CLASSIC){
			// TODO AGA, for testing here
			gamePanel = new ClassicGamePanel(100);
			getContentPane().add(gamePanel);
		}else if (mode == GameMode.SHARED){
			
		}else{
			
		}
	}
	

	public void setFieldAsBomb(int position) {
		gamePanel.setFieldAsBomb(position);
	
		// TODO Aga Delete magic strings (use internationalization)
		displayEndGameMessage();
		
	}
	

	private void displayEndGameMessage()
	{
		int option = JOptionPane.showConfirmDialog(this,
			    DialogText.END_GAME_TITLE,
			    DialogText.END_GAME_MESSAGE,
			    JOptionPane.YES_NO_OPTION);
		
		if (option == 0){ //YES
			
		}else if (option == 1){ //NO
			
		}
		
	}
	
	
	/**
	 * TODO PlayerHandler.opponentShot, commented until SHARED,PERKS will be
	 * developed
	 */
	// TODO MALY COMMIT
	public void setOpponentAsBomb(int pos) {
		//oponentBombField[pos - 1].setBackground(Color.RED);
	}

	public void setFieldAsEmpty(int position) {
		gamePanel.setFieldAsEmpty(position);
	}

	/**
	 * TODO PlayerHandler.opponentShot, commented until SHARED,PERKS will be
	 * developed
	 */
	// TODO MALY COMMIT
	public void setOpponentAsEmpty(int pos) {
		//oponentBombField[pos - 1].setBackground(Color.GRAY);
	}

	public void resetFields() {
		gamePanel.resetFields();
	}
	
	public void setFieldAsFlagged(int position) {
		gamePanel.setFieldAsFlagged(position);
	}
	
	public void setFieldAsEmptyWithValue(int position, int value){
		gamePanel.setFieldAsEmptyWithValue(position, value);
	}
}
