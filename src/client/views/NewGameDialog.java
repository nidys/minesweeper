package client.views;

import static client.internationalization.ButtonNames.CANCEL;
import static client.internationalization.ButtonNames.HOST;
import static client.internationalization.ButtonNames.JOIN;
import static client.internationalization.ButtonNames.OK;

import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


import client.internationalization.DialogText;


import org.apache.log4j.Logger;

import static client.internationalization.ButtonNames.CANCEL;
import static client.internationalization.ButtonNames.HOST;
import static client.internationalization.ButtonNames.JOIN;
import static client.internationalization.ButtonNames.OK;
import common.enums.GameDifficulty;
import common.enums.GameMode;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;


import java.awt.event.ActionEvent;

/**
 * View for the NewGame component (view with the game board). A dialog.
 */
public class NewGameDialog extends DialogBase {
	private static final long serialVersionUID = 8080261200762371915L;
	private final JPanel contentPanel = new JPanel();
	private JButton classicGameButton;
	private JButton sharedGameButton;
	private JButton perksGameButton;
	private JButton hostGameButton;
	private JButton joinGameButton;
	private JPanel panelSettings;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton easyRbtn;
	private JRadioButton mediumRbtn;
	private JRadioButton hardRbtn;

	public NewGameDialog(JFrame owner, boolean isModal) {
		super(owner, isModal);
		setTitle(DialogText.NEW_GAME_TITLE);
		buildGUI();
		setLocationRelativeTo(owner);
	}

	private void buildGUI() {
		setAlwaysOnTop(true);
		setBounds(100, 100, 311, 243);
		getContentPane().setLayout(null);
		{
			panelSettings = new JPanel();
			panelSettings.setBounds(0, 56, 293, 69);
			getContentPane().add(panelSettings);
			panelSettings.setLayout(null);


			// Currently not used.
//			{
//				lblBoardSize = new JLabel("Board size:");
//				lblBoardSize.setBounds(10, 14, 53, 14);
//				panelSettings.add(lblBoardSize);
//			}
//			
//			txtBoardSize = new JTextField();
//			txtBoardSize.setText("2");
//			txtBoardSize.setBounds(73, 11, 210, 20);
//			panelSettings.add(txtBoardSize);
//			txtBoardSize.setColumns(10);

			
			JPanel Type = new JPanel();
			Type.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Difficulty:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			Type.setBounds(10, 11, 273, 46);
			panelSettings.add(Type);
			Type.setLayout(null);
						easyRbtn = new JRadioButton("Easy");
			easyRbtn.setSelected(true);
			easyRbtn.setBounds(6, 16, 49, 23);
			Type.add(easyRbtn);
			buttonGroup.add(easyRbtn);
			
			mediumRbtn = new JRadioButton("Medium");
			mediumRbtn.setBounds(100, 16, 61, 23);
			Type.add(mediumRbtn);
			buttonGroup.add(mediumRbtn);
			
			hardRbtn = new JRadioButton("Hard");
			hardRbtn.setBounds(218, 16, 49, 23);
			Type.add(hardRbtn);
			buttonGroup.add(hardRbtn);
		}
		contentPanel.setBounds(0, 0, 293, 55);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		{
			classicGameButton = new JButton(GameMode.CLASSIC.toString());
			classicGameButton.setBounds(10, 10, 75, 32);
		}
		{
			sharedGameButton = new JButton(GameMode.SHARED.toString());
			sharedGameButton.setBounds(107, 10, 75, 32);
		}
		{
			perksGameButton = new JButton(GameMode.PERKS.toString());
			perksGameButton.setBounds(208, 10, 75, 32);
		}
		contentPanel.setLayout(null);
		contentPanel.add(classicGameButton);
		contentPanel.add(sharedGameButton);
		contentPanel.add(perksGameButton);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 169, 293, 35);
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
				joinGameButton.setBounds(166, 5, 117, 25);
				joinGameButton.setActionCommand(CANCEL);
				buttonPane.add(joinGameButton);
			}
		}
		
		JPanel panelAdditionalSettings = new JPanel();
		panelAdditionalSettings.setBounds(0, 123, 293, 35);
		getContentPane().add(panelAdditionalSettings);
	}


// Currently not used, as we use Easy/Normal/Hard tpye of the game. May be used in a future.
//	public int getBoardSize(){

//		if (txtBoardSize.getText().isEmpty())
//		{
//			return -1;
//		}
//		return Integer.parseInt(txtBoardSize.getText());


//	}
	
	// Currently not used, as we use Easy/Normal/Hard tpye of the game. May be used in a future.
//	public int getBombsNumber(){

//		int size = Integer.parseInt(txtBoardSize.getText());
//		if( size <= 0)
//			return -1;
//		if (rdbtnEasy.isSelected()){
//			return (int) Math.floor(0.25 * size);
//		}else if (rdbtnMedium.isSelected()){
//			return (int) Math.floor(0.50 * size);
//		}else if (rdbtnHard.isSelected()){
//			return (int) Math.floor(0.75 * size);
//		}else{
//			return -1;
//		}
//	}

	
	
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
	
	public void addGameDifficultyListeners(
			ActionListener easyListener, ActionListener mediumListener, ActionListener hardListener) {
		easyRbtn.addActionListener(easyListener);
		mediumRbtn.addActionListener(mediumListener);
		hardRbtn.addActionListener(hardListener);
	}
}
