package client.views;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import static client.internationalization.ButtonNames.CANCEL;
import static client.internationalization.ButtonNames.HOST;
import static client.internationalization.ButtonNames.JOIN;
import static client.internationalization.ButtonNames.OK;

import common.enums.GameMode;

/**
 * View for the NewGame component (view with the game board). A dialog.
 */
public class NewGameDialog extends DialogBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8080261200762371915L;
	private final JPanel contentPanel = new JPanel();
	private JButton classicGameButton;
	private JButton sharedGameButton;
	private JButton perksGameButton;

	private JButton hostGameButton;
	private JButton joinGameButton;

	public NewGameDialog(JFrame owner, boolean isModal) {
		super(owner, isModal);
		buildGUI();
		setLocationRelativeTo(owner);
	}

	private void buildGUI() {
		setAlwaysOnTop(true);
		setResizable(false);
		setBounds(100, 100, 270, 170);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 265, 95);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		{
			classicGameButton = new JButton(GameMode.CLASSIC.toString());
			classicGameButton.setBounds(10, 10, 75, 75);
		}
		{
			sharedGameButton = new JButton(GameMode.SHARED.toString());
			sharedGameButton.setBounds(95, 10, 75, 75);
		}
		{
			perksGameButton = new JButton(GameMode.PERKS.toString());
			perksGameButton.setBounds(180, 10, 75, 75);
		}
		contentPanel.setLayout(null);
		contentPanel.add(classicGameButton);
		contentPanel.add(sharedGameButton);
		contentPanel.add(perksGameButton);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 100, 265, 35);
			getContentPane().add(buttonPane);
			{
				hostGameButton = new JButton(HOST);
				hostGameButton.setBounds(10, 5, 117, 25);
				buttonPane.setLayout(null);
				hostGameButton.setActionCommand(OK);
				buttonPane.add(hostGameButton);
				getRootPane().setDefaultButton(hostGameButton);
			}
			{
				joinGameButton = new JButton(JOIN);
				joinGameButton.setBounds(138, 5, 117, 25);
				joinGameButton.setActionCommand(CANCEL);
				buttonPane.add(joinGameButton);
			}
		}
	}

	public void addGameModeBtnListener(ActionListener listener) {
		classicGameButton.addActionListener(listener);
		sharedGameButton.addActionListener(listener);
		perksGameButton.addActionListener(listener);
	}

	public void addHostBtnListener(ActionListener listener) {
		hostGameButton.addActionListener(listener);
	}

	public void addJoinBtnListener(ActionListener listener) {
		joinGameButton.addActionListener(listener);
	}
}
