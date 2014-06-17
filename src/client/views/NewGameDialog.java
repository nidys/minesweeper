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
	private JPanel panelSettings;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnEasy;
	private JRadioButton rdbtnMedium;
	private JRadioButton rdbtnHard;

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
			
			JPanel Type = new JPanel();
			Type.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Difficulty:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			Type.setBounds(10, 11, 273, 46);
			panelSettings.add(Type);
			Type.setLayout(null);
			
			rdbtnEasy = new JRadioButton("Easy");
			rdbtnEasy.setSelected(true);
			rdbtnEasy.setBounds(6, 16, 78, 23);
			Type.add(rdbtnEasy);
			buttonGroup.add(rdbtnEasy);
			
			rdbtnMedium = new JRadioButton("Medium");
			rdbtnMedium.setBounds(86, 16, 97, 23);
			Type.add(rdbtnMedium);
			buttonGroup.add(rdbtnMedium);
			
			rdbtnHard = new JRadioButton("Hard");
			rdbtnHard.setBounds(185, 16, 82, 23);
			Type.add(rdbtnHard);
			buttonGroup.add(rdbtnHard);
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

	public int getBoardSize(){
//		if (txtBoardSize.getText().isEmpty())
//		{
//			return -1;
//		}
//		return Integer.parseInt(txtBoardSize.getText());
		return -1;
	}
	
	public int getBombsNumber(){
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
		
			return -1;
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
