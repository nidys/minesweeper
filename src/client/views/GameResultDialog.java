package client.views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.utils.GraphicsFactory;

@SuppressWarnings("serial")
public class GameResultDialog extends JPanel {

	private JLabel pictureLabel;
	private JButton goToMenuButton;

	public enum GameResult {
		SUCCESS, FAIL
	}

	public GameResultDialog(GameResult gameResult, String winnerName) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		pictureLabel = new JLabel("Pic label");
		GridBagConstraints gbc_pictureLabel = new GridBagConstraints();
		gbc_pictureLabel.insets = new Insets(0, 0, 5, 5);
		gbc_pictureLabel.gridx = 0;
		gbc_pictureLabel.gridy = 0;
		add(pictureLabel, gbc_pictureLabel);

		goToMenuButton = new JButton("Go to menu");
		GridBagConstraints gbc_goToMenuButton = new GridBagConstraints();
		gbc_goToMenuButton.insets = new Insets(0, 0, 5, 5);
		gbc_goToMenuButton.gridx = 0;
		gbc_goToMenuButton.gridy = 1;
		add(goToMenuButton, gbc_goToMenuButton);

		if (gameResult == GameResult.SUCCESS) {
			pictureLabel = new JLabel(GraphicsFactory.getLoseIcon());
			add(pictureLabel);
		} else if (gameResult == GameResult.FAIL) {
			pictureLabel = new JLabel(GraphicsFactory.getDeadFaceIcon());
			add(pictureLabel);
		} else {

		}
	}
}
