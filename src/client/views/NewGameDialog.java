package client.views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import common.gameRules.GameMode;

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
				JButton hostGameButton = new JButton("Host");
				hostGameButton.setBounds(10, 5, 117, 25);
				buttonPane.setLayout(null);
				hostGameButton.setActionCommand("OK");
				buttonPane.add(hostGameButton);
				getRootPane().setDefaultButton(hostGameButton);
			}
			{
				JButton joinGameButton = new JButton("Join");
				joinGameButton.setBounds(138, 5, 117, 25);
				joinGameButton.setActionCommand("Cancel");
				buttonPane.add(joinGameButton);
			}
		}
	}
}
