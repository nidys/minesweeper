package client.views;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.models.MainModel;

/**
 * View for the Main component (view with the game board). Standard window.
 */
public class MainWindow extends WindowBase implements Observer {

	private JPanel contentPanel;
	private JButton newGameBtn;

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		JPanel navigationPanel = new JPanel();
		navigationPanel.setBounds(10, 11, 82, 82);
		contentPanel.add(navigationPanel);
		navigationPanel.setLayout(null);

		newGameBtn = new JButton("<html><center> New <br> Game </center></html>");
		newGameBtn.setBounds(10, 11, 62, 62);
		navigationPanel.add(newGameBtn);

		JPanel mapPanel = new JPanel();
		mapPanel.setBounds(10, 104, 414, 196);
		contentPanel.add(mapPanel);

		JPanel debugPanel = new JPanel();
		debugPanel.setBounds(176, 11, 248, 82);
		contentPanel.add(debugPanel);

		setLocationRelativeTo(null);
	}

	/**
	 * @param listener
	 *            Listener for an action when the user clicks the "New Game"
	 *            button.
	 */
	public void addNewGameBtnListener(ActionListener listener) {
		newGameBtn.addActionListener(listener);
	}

	/**
	 * Example usage of an Observer pattern (not sure if we'll use it).
	 */
	@Override
	public void update(Observable o, Object arg) {
		MainModel model = (MainModel) o;
		System.out.println("update() from MainWindow.");
	}
}
