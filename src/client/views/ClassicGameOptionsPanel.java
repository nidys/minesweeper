package client.views;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JCheckBox;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSpinner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClassicGameOptionsPanel extends JPanel {
	private JCheckBox chckbxWinWhenSolved;
	private JCheckBox chckbxLivesCount;
	private JCheckBox chckbxTimed;
	private JCheckBox chckbxBoardLimit;

	private JSpinner livesSpinner;
	private JSpinner timeSpinner;
	private JSpinner boarAmountSpinner;

	public ClassicGameOptionsPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		// TODO GUI Internationalization
		chckbxWinWhenSolved = new JCheckBox("Win when solved");
		GridBagConstraints gbc_chckbxWinWhenSolved = new GridBagConstraints();
		gbc_chckbxWinWhenSolved.anchor = GridBagConstraints.WEST;
		gbc_chckbxWinWhenSolved.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxWinWhenSolved.gridx = 0;
		gbc_chckbxWinWhenSolved.gridy = 0;
		add(chckbxWinWhenSolved, gbc_chckbxWinWhenSolved);

		// TODO GUI Internationalization
		chckbxLivesCount = new JCheckBox("Lives count");
		chckbxLivesCount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				livesSpinner.setEnabled(true);
			}
		});
		GridBagConstraints gbc_chckbxLivesCount = new GridBagConstraints();
		gbc_chckbxLivesCount.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxLivesCount.anchor = GridBagConstraints.WEST;
		gbc_chckbxLivesCount.gridx = 0;
		gbc_chckbxLivesCount.gridy = 1;
		add(chckbxLivesCount, gbc_chckbxLivesCount);

		livesSpinner = new JSpinner();
		livesSpinner.setEnabled(false);
		GridBagConstraints gbc_spinner_lives = new GridBagConstraints();
		gbc_spinner_lives.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_lives.gridx = 1;
		gbc_spinner_lives.gridy = 1;
		add(livesSpinner, gbc_spinner_lives);

		// TODO GUI Internationalization
		chckbxTimed = new JCheckBox("Timed");
		chckbxTimed.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				timeSpinner.setEnabled(true);
			}
		});
		GridBagConstraints gbc_chckbxTimed = new GridBagConstraints();
		gbc_chckbxTimed.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxTimed.anchor = GridBagConstraints.WEST;
		gbc_chckbxTimed.gridx = 0;
		gbc_chckbxTimed.gridy = 2;
		add(chckbxTimed, gbc_chckbxTimed);

		timeSpinner = new JSpinner();
		timeSpinner.setEnabled(false);
		GridBagConstraints gbc_spinner_time = new GridBagConstraints();
		gbc_spinner_time.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_time.gridx = 1;
		gbc_spinner_time.gridy = 2;
		add(timeSpinner, gbc_spinner_time);

		// TODO GUI Internationalization
		chckbxBoardLimit = new JCheckBox("Board amount");
		chckbxBoardLimit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boarAmountSpinner.setEnabled(true);
			}
		});
		GridBagConstraints gbc_chckbxBoardLimit = new GridBagConstraints();
		gbc_chckbxBoardLimit.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxBoardLimit.anchor = GridBagConstraints.WEST;
		gbc_chckbxBoardLimit.gridx = 0;
		gbc_chckbxBoardLimit.gridy = 3;
		add(chckbxBoardLimit, gbc_chckbxBoardLimit);

		boarAmountSpinner = new JSpinner();
		boarAmountSpinner.setEnabled(false);
		GridBagConstraints gbc_spinner_boardLimit = new GridBagConstraints();
		gbc_spinner_boardLimit.gridx = 1;
		gbc_spinner_boardLimit.gridy = 3;
		add(boarAmountSpinner, gbc_spinner_boardLimit);
	}

	public boolean isWinWhenSolvedSelected() {
		return chckbxWinWhenSolved.isSelected();
	}

	public boolean isLivesCountSelected() {
		return chckbxLivesCount.isSelected();
	}

	public boolean isTimedSelected() {
		return chckbxTimed.isSelected();
	}

	public boolean isBoardLimitSelected() {
		return chckbxBoardLimit.isSelected();
	}

	public int getLivesCount() {
		return (int) livesSpinner.getValue();
	}

	public int getTimeAmount() {
		return (int) timeSpinner.getValue();
	}

	public int getBoardLimit() {
		return (int) boarAmountSpinner.getValue();
	}
}
