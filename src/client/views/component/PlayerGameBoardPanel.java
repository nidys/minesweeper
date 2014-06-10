package client.views.component;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.Color;

public class PlayerGameBoardPanel extends JPanel {
	public PlayerGameBoardPanel() {
		setLayout(null);
		
		JPanel statusPanel = new JPanel();
		statusPanel.setBounds(0, 0, 450, 32);
		add(statusPanel);
		statusPanel.setLayout(null);
		
		JLabel lblUsernick = new JLabel("Usernick");
		lblUsernick.setBounds(0, 11, 46, 14);
		statusPanel.add(lblUsernick);
		
		JLabel lblClock = new JLabel("Clocks");
		lblClock.setForeground(Color.RED);
		lblClock.setBackground(Color.BLUE);
		lblClock.setBounds(362, 0, 88, 25);
		statusPanel.add(lblClock);
		
		JLabel lblBombs = new JLabel("Bombs");
		lblBombs.setForeground(Color.BLUE);
		lblBombs.setBounds(274, 0, 78, 25);
		statusPanel.add(lblBombs);
		
		JPanel boardPanel = new JPanel();
		boardPanel.setBounds(0, 31, 450, 269);
		add(boardPanel);
		GridBagLayout gbl_boardPanel = new GridBagLayout();
		gbl_boardPanel.columnWidths = new int[]{0};
		gbl_boardPanel.rowHeights = new int[]{0};
		gbl_boardPanel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_boardPanel.rowWeights = new double[]{Double.MIN_VALUE};
		boardPanel.setLayout(gbl_boardPanel);
	}
}
