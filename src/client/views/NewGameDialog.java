package client.views;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Random;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

import client.internationalization.DialogText;
import common.enums.GameDifficulty;
import common.enums.GameMode;

import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * View for the NewGame component (view with the game board). A dialog.
 */
public class NewGameDialog extends DialogBase {
	private static final long serialVersionUID = 8080261200762371915L;

	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel contentPanel;
	private JPanel difficultyPanel;
	private JPanel buttonPanel;

	private JToggleButton classicGameButton;
	private JToggleButton sharedGameButton;
	private JToggleButton perksGameButton;

	private JPanel panelSettings;

	private JRadioButton easyRbtn;
	private JRadioButton mediumRbtn;
	private JRadioButton hardRbtn;
	private JButton hostGameButton;
	private JButton joinGameButton;
	private final ButtonGroup gameTypeButtonGroup = new ButtonGroup();
	private JPanel namePanel;
	private JLabel gameNameLbl;
	private JTextField gameNameTextField;

	public NewGameDialog(JFrame owner, boolean isModal) {
		super(owner, isModal);
		setAlwaysOnTop(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				NewGameDialog.class.getResource("/resources/images/flag.png")));
		setTitle(DialogText.NEW_GAME_TITLE);
		setLocationRelativeTo(owner);
		buildGUI();

	}

	private void buildGUI() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 20, 0, 10, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		contentPanel = new JPanel();
		GridBagConstraints gbc_contentPanel = new GridBagConstraints();
		gbc_contentPanel.insets = new Insets(0, 0, 5, 0);
		gbc_contentPanel.fill = GridBagConstraints.BOTH;
		gbc_contentPanel.gridx = 0;
		gbc_contentPanel.gridy = 0;
		getContentPane().add(contentPanel, gbc_contentPanel);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 15, 15, 15, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		classicGameButton = new JToggleButton("Classic");
		gameTypeButtonGroup.add(classicGameButton);
		GridBagConstraints gbc_classicGameButton = new GridBagConstraints();
		gbc_classicGameButton.fill = GridBagConstraints.BOTH;
		gbc_classicGameButton.gridheight = 2;
		gbc_classicGameButton.insets = new Insets(0, 0, 5, 5);
		gbc_classicGameButton.gridx = 0;
		gbc_classicGameButton.gridy = 0;
		contentPanel.add(classicGameButton, gbc_classicGameButton);

		sharedGameButton = new JToggleButton("Shared");
		gameTypeButtonGroup.add(sharedGameButton);
		GridBagConstraints gbc_sharedGameButton = new GridBagConstraints();
		gbc_sharedGameButton.fill = GridBagConstraints.BOTH;
		gbc_sharedGameButton.gridheight = 2;
		gbc_sharedGameButton.insets = new Insets(0, 0, 5, 5);
		gbc_sharedGameButton.gridx = 1;
		gbc_sharedGameButton.gridy = 0;
		contentPanel.add(sharedGameButton, gbc_sharedGameButton);

		perksGameButton = new JToggleButton("Perks");
		gameTypeButtonGroup.add(perksGameButton);
		GridBagConstraints gbc_perksGameButton = new GridBagConstraints();
		gbc_perksGameButton.fill = GridBagConstraints.BOTH;
		gbc_perksGameButton.gridheight = 2;
		gbc_perksGameButton.insets = new Insets(0, 0, 5, 0);
		gbc_perksGameButton.gridx = 2;
		gbc_perksGameButton.gridy = 0;
		contentPanel.add(perksGameButton, gbc_perksGameButton);

		difficultyPanel = new JPanel();
		difficultyPanel.setBorder(new TitledBorder(null, "Difficulty", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		GridBagConstraints gbc_difficultyPanel = new GridBagConstraints();
		gbc_difficultyPanel.insets = new Insets(0, 0, 5, 0);
		gbc_difficultyPanel.fill = GridBagConstraints.BOTH;
		gbc_difficultyPanel.gridx = 0;
		gbc_difficultyPanel.gridy = 1;
		getContentPane().add(difficultyPanel, gbc_difficultyPanel);
		GridBagLayout gbl_difficultyPanel = new GridBagLayout();
		gbl_difficultyPanel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_difficultyPanel.rowHeights = new int[] { 0, 0 };
		gbl_difficultyPanel.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_difficultyPanel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		difficultyPanel.setLayout(gbl_difficultyPanel);

		easyRbtn = new JRadioButton("Easy");
		easyRbtn.setSelected(true);
		buttonGroup.add(easyRbtn);
		GridBagConstraints gbc_easyRbtn = new GridBagConstraints();
		gbc_easyRbtn.fill = GridBagConstraints.BOTH;
		gbc_easyRbtn.insets = new Insets(0, 0, 0, 5);
		gbc_easyRbtn.gridx = 0;
		gbc_easyRbtn.gridy = 0;
		difficultyPanel.add(easyRbtn, gbc_easyRbtn);

		mediumRbtn = new JRadioButton("Medium");
		buttonGroup.add(mediumRbtn);
		GridBagConstraints gbc_mediumRbtn = new GridBagConstraints();
		gbc_mediumRbtn.fill = GridBagConstraints.BOTH;
		gbc_mediumRbtn.insets = new Insets(0, 0, 0, 5);
		gbc_mediumRbtn.gridx = 1;
		gbc_mediumRbtn.gridy = 0;
		difficultyPanel.add(mediumRbtn, gbc_mediumRbtn);

		hardRbtn = new JRadioButton("Hard");
		buttonGroup.add(hardRbtn);
		GridBagConstraints gbc_hardRbtn = new GridBagConstraints();
		gbc_hardRbtn.fill = GridBagConstraints.BOTH;
		gbc_hardRbtn.gridx = 2;
		gbc_hardRbtn.gridy = 0;
		difficultyPanel.add(hardRbtn, gbc_hardRbtn);

		buttonPanel = new JPanel();
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.insets = new Insets(0, 0, 5, 0);
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.gridx = 0;
		gbc_buttonPanel.gridy = 2;
		getContentPane().add(buttonPanel, gbc_buttonPanel);
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_buttonPanel.rowHeights = new int[] { 0, 0, 0 };
		gbl_buttonPanel.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_buttonPanel.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		buttonPanel.setLayout(gbl_buttonPanel);

		hostGameButton = new JButton("Host");
		GridBagConstraints gbc_hostGameButton = new GridBagConstraints();
		gbc_hostGameButton.fill = GridBagConstraints.BOTH;
		gbc_hostGameButton.gridheight = 2;
		gbc_hostGameButton.insets = new Insets(0, 0, 5, 5);
		gbc_hostGameButton.gridx = 0;
		gbc_hostGameButton.gridy = 0;
		buttonPanel.add(hostGameButton, gbc_hostGameButton);

		joinGameButton = new JButton("Join");
		GridBagConstraints gbc_joinGameButton = new GridBagConstraints();
		gbc_joinGameButton.fill = GridBagConstraints.BOTH;
		gbc_joinGameButton.gridheight = 2;
		gbc_joinGameButton.insets = new Insets(0, 0, 5, 0);
		gbc_joinGameButton.gridx = 2;
		gbc_joinGameButton.gridy = 0;
		buttonPanel.add(joinGameButton, gbc_joinGameButton);
		
		namePanel = new JPanel();
		GridBagConstraints gbc_namePanel = new GridBagConstraints();
		gbc_namePanel.fill = GridBagConstraints.BOTH;
		gbc_namePanel.gridx = 0;
		gbc_namePanel.gridy = 3;
		getContentPane().add(namePanel, gbc_namePanel);
		
		gameNameLbl = new JLabel("Nazwa gry:");
		namePanel.add(gameNameLbl);
		
		gameNameTextField = new JTextField();
		namePanel.add(gameNameTextField);
		// generate random game name so that user don't need to write it all the
		// time
		gameNameTextField.setText("Game Name " + String.valueOf(new Random().nextInt(100)));
		
		Dimension d = this.getLayout().preferredLayoutSize(this);
		this.setSize(new Dimension(280, 210));
	}

	// Currently not used, as we use Easy/Normal/Hard tpye of the game. May be
	// used in a future.
	// public int getBoardSize(){

	// if (txtBoardSize.getText().isEmpty())
	// {
	// return -1;
	// }
	// return Integer.parseInt(txtBoardSize.getText());

	// }

	// Currently not used, as we use Easy/Normal/Hard tpye of the game. May be
	// used in a future.
	// public int getBombsNumber(){

	// int size = Integer.parseInt(txtBoardSize.getText());
	// if( size <= 0)
	// return -1;
	// if (rdbtnEasy.isSelected()){
	// return (int) Math.floor(0.25 * size);
	// }else if (rdbtnMedium.isSelected()){
	// return (int) Math.floor(0.50 * size);
	// }else if (rdbtnHard.isSelected()){
	// return (int) Math.floor(0.75 * size);
	// }else{
	// return -1;
	// }
	// }

	public void addHostBtnListener(ActionListener listener) {
		hostGameButton.addActionListener(listener);
	}

	public void addJoinBtnListener(ActionListener listener) {
		joinGameButton.addActionListener(listener);
	}

	public String getGameId() {
		return gameNameTextField.getText();
	}

	public GameDifficulty getGameDifficulty() {
		for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons
				.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				return GameDifficulty.valueOf(button.getText().toUpperCase());
			}
		}
		
		// DEBUG For debug purposes. 
		return GameDifficulty.EASY;
	}

	public GameMode getGameMode() {
		for (Enumeration<AbstractButton> buttons = gameTypeButtonGroup.getElements(); buttons
				.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				return GameMode.valueOf(button.getText().toUpperCase());
			}
		}
		// DEBUG For debug purposes. 
		return GameMode.CLASSIC;
	}
}
