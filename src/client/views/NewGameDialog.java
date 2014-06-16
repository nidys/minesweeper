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
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

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
	private JLabel lblBoardSize;
	private JTextField txtBoardSize;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnEasy;
	private JRadioButton rdbtnMedium;
	private JRadioButton rdbtnHard;

	public NewGameDialog(JFrame owner, boolean isModal) {
		super(owner, isModal);
		// TODO COMMIT Move to internationalization
		setTitle("Minesweeper: New game");
		buildGUI();
		setLocationRelativeTo(owner);
	}

	private void buildGUI() {
		setAlwaysOnTop(true);
		setResizable(false);
		setBounds(100, 100, 299, 319);
		getContentPane().setLayout(null);
		{
			panelSettings = new JPanel();
			panelSettings.setBounds(0, 92, 293, 95);
			getContentPane().add(panelSettings);
			panelSettings.setLayout(null);
			{
				lblBoardSize = new JLabel("Board size:");
				lblBoardSize.setBounds(10, 14, 53, 14);
				panelSettings.add(lblBoardSize);
			}
			
			txtBoardSize = new JTextField();
			txtBoardSize.setText("2");
			txtBoardSize.setBounds(73, 11, 210, 20);
			panelSettings.add(txtBoardSize);
			txtBoardSize.setColumns(10);
			
			JPanel Type = new JPanel();
			Type.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Type:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			Type.setBounds(10, 38, 273, 46);
			panelSettings.add(Type);
			Type.setLayout(null);
			
			rdbtnEasy = new JRadioButton("Easy");
			rdbtnEasy.setSelected(true);
			rdbtnEasy.setBounds(6, 16, 49, 23);
			Type.add(rdbtnEasy);
			buttonGroup.add(rdbtnEasy);
			
			rdbtnMedium = new JRadioButton("Medium");
			rdbtnMedium.setBounds(100, 16, 61, 23);
			Type.add(rdbtnMedium);
			buttonGroup.add(rdbtnMedium);
			
			rdbtnHard = new JRadioButton("Hard");
			rdbtnHard.setBounds(218, 16, 49, 23);
			Type.add(rdbtnHard);
			buttonGroup.add(rdbtnHard);
		}
		contentPanel.setBounds(0, 0, 293, 95);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		{
			classicGameButton = new JButton(GameMode.CLASSIC.toString());
			classicGameButton.setBounds(10, 10, 75, 75);
		}
		{
			sharedGameButton = new JButton(GameMode.SHARED.toString());
			sharedGameButton.setBounds(107, 10, 75, 75);
		}
		{
			perksGameButton = new JButton(GameMode.PERKS.toString());
			perksGameButton.setBounds(208, 10, 75, 75);
		}
		contentPanel.setLayout(null);
		contentPanel.add(classicGameButton);
		contentPanel.add(sharedGameButton);
		contentPanel.add(perksGameButton);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 255, 293, 35);
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
		panelAdditionalSettings.setBounds(0, 187, 293, 68);
		getContentPane().add(panelAdditionalSettings);
	}

	public int getBoardSize(){
		if (txtBoardSize.getText().isEmpty())
		{
			return -1;
		}
		return Integer.parseInt(txtBoardSize.getText());
	}
	
	public int getBombsNumber(){
		int size = Integer.parseInt(txtBoardSize.getText());
		if( size <= 0)
			return -1;
		if (rdbtnEasy.isSelected()){
			return (int) Math.floor(0.25 * size);
		}else if (rdbtnMedium.isSelected()){
			return (int) Math.floor(0.50 * size);
		}else if (rdbtnHard.isSelected()){
			return (int) Math.floor(0.75 * size);
		}else{
			return -1;
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
