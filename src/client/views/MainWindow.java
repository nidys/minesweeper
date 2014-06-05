package client.views;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import static client.internationalization.ButtonNames.NEW_GAME;

import common.network.ServerAddress;

/**
 * View for the Main component (view with the game board). Standard window.
 */
@SuppressWarnings("serial")
public class MainWindow extends WindowBase implements Observer {

	private JButton newGameBtn;
	private JTextField serverAddress;
	private JTextField userNick;

	/**
	 * Create the frame.
	 */
	public MainWindow() {
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

	public String getServerAddress() {
		return serverAddress.getText();
	}

	public String getUserNick() {
		return userNick.getText();
	}

	/**
	 * Example usage of an Observer pattern (not sure if we'll use it).
	 */
	@Override
	public void update(Observable o, Object arg) {
		// MainModel model = (MainModel) o;
		System.out.println("update() from MainWindow.");
	}
}
