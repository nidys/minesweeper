package client.views;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JCheckBox;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JSpinner;

import client.internationalization.DescriptionText;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.ButtonGroup;

public class ClassicGameOptionsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JCheckBox chckbxWinWhenSolved;
	private JCheckBox chckbxLifesAmount;
	private JCheckBox chckbxTimed;
	private JCheckBox chckbxBoardLimit;

	private JSpinner lifesAmountSpinner;
	private JSpinner timeSpinner;
	private JSpinner boardAmountSpinner;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public ClassicGameOptionsPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		
		chckbxWinWhenSolved = new JCheckBox(DescriptionText.WIN_WHEN_SOLVED);
		buttonGroup.add(chckbxWinWhenSolved);
		GridBagConstraints gbc_chckbxWinWhenSolved = new GridBagConstraints();
		gbc_chckbxWinWhenSolved.anchor = GridBagConstraints.WEST;
		gbc_chckbxWinWhenSolved.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxWinWhenSolved.gridx = 0;
		gbc_chckbxWinWhenSolved.gridy = 0;
		add(chckbxWinWhenSolved, gbc_chckbxWinWhenSolved);

		
		chckbxLifesAmount = new JCheckBox(DescriptionText.LIVES_COUNT);
		buttonGroup.add(chckbxLifesAmount);
		chckbxLifesAmount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lifesAmountSpinner.setEnabled(!lifesAmountSpinner.isEnabled());
				
			}
		});
		GridBagConstraints gbc_chckbxLivesCount = new GridBagConstraints();
		gbc_chckbxLivesCount.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxLivesCount.anchor = GridBagConstraints.WEST;
		gbc_chckbxLivesCount.gridx = 0;
		gbc_chckbxLivesCount.gridy = 1;
		add(chckbxLifesAmount, gbc_chckbxLivesCount);

		lifesAmountSpinner = new JSpinner();
		lifesAmountSpinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		lifesAmountSpinner.setEnabled(false);
		GridBagConstraints gbc_spinner_lives = new GridBagConstraints();
		gbc_spinner_lives.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_lives.gridx = 1;
		gbc_spinner_lives.gridy = 1;
		add(lifesAmountSpinner, gbc_spinner_lives);

		
		chckbxTimed = new JCheckBox(DescriptionText.TIMED);
		buttonGroup.add(chckbxTimed);
		chckbxTimed.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				timeSpinner.setEnabled(!timeSpinner.isEnabled());
			}
		});
		GridBagConstraints gbc_chckbxTimed = new GridBagConstraints();
		gbc_chckbxTimed.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxTimed.anchor = GridBagConstraints.WEST;
		gbc_chckbxTimed.gridx = 0;
		gbc_chckbxTimed.gridy = 2;
		add(chckbxTimed, gbc_chckbxTimed);

		timeSpinner = new JSpinner();
		timeSpinner.setModel(new SpinnerNumberModel(new Long(0), null, null, new Long(1)));
		timeSpinner.setEnabled(false);
		GridBagConstraints gbc_spinner_time = new GridBagConstraints();
		gbc_spinner_time.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_time.gridx = 1;
		gbc_spinner_time.gridy = 2;
		add(timeSpinner, gbc_spinner_time);


		chckbxBoardLimit = new JCheckBox(DescriptionText.BOARD_AMOUNT);
		buttonGroup.add(chckbxBoardLimit);
		chckbxBoardLimit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boardAmountSpinner.setEnabled(!boardAmountSpinner.isEnabled());
			}
		});
		GridBagConstraints gbc_chckbxBoardLimit = new GridBagConstraints();
		gbc_chckbxBoardLimit.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxBoardLimit.anchor = GridBagConstraints.WEST;
		gbc_chckbxBoardLimit.gridx = 0;
		gbc_chckbxBoardLimit.gridy = 3;
		add(chckbxBoardLimit, gbc_chckbxBoardLimit);

		boardAmountSpinner = new JSpinner();
		boardAmountSpinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		boardAmountSpinner.setEnabled(false);
		GridBagConstraints gbc_spinner_boardLimit = new GridBagConstraints();
		gbc_spinner_boardLimit.gridx = 1;
		gbc_spinner_boardLimit.gridy = 3;
		add(boardAmountSpinner, gbc_spinner_boardLimit);
	}

	public boolean isWinWhenSolvedSelected() {
		return chckbxWinWhenSolved.isSelected();
	}

	public boolean isLifesAmountSelected() {
		return chckbxLifesAmount.isSelected();
	}

	public boolean isTimedSelected() {
		return chckbxTimed.isSelected();
	}

	public boolean isBoardLimitSelected() {
		return chckbxBoardLimit.isSelected();
	}

	public int getLifesAmount() {
		return (int) lifesAmountSpinner.getValue();
	}

	public long getGameDuration() {
		return (long) timeSpinner.getValue();
	}

	public int getBoardAmount() {
		return (int) boardAmountSpinner.getValue();
	}
}
