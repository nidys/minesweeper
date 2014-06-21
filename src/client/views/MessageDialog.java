package client.views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import common.enums.GameResult;
import client.internationalization.ButtonNames;
import client.utils.GraphicsFactory;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class MessageDialog extends DialogBase {


	private JLabel pictureLabel;
	private JButton goToMenuButton;
	private JPanel panel;
	private JLabel lblMessage;

	/**
	 * @wbp.parser.constructor
	 */
	public MessageDialog(JFrame owner, boolean isModal, common.enums.GameResult gameResult, String winnerName, String message, String title) {
		super(owner, isModal);
		
		setTitle(title);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0 };
		gridBagLayout.rowHeights = new int[] { 0, -18, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		pictureLabel = new JLabel();
		GridBagConstraints gbc_pictureLabel = new GridBagConstraints();
		gbc_pictureLabel.insets = new Insets(0, 0, 5, 0);
		gbc_pictureLabel.gridx = 0;
		gbc_pictureLabel.gridy = 1;
		getContentPane().add(pictureLabel, gbc_pictureLabel);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblMessage = new JLabel(message);
		GridBagConstraints gbc_lblMessage = new GridBagConstraints();
		gbc_lblMessage.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMessage.gridx = 0;
		gbc_lblMessage.gridy = 0;
		panel.add(lblMessage, gbc_lblMessage);

		goToMenuButton = new JButton(ButtonNames.GO_TO_MENU);
		GridBagConstraints gbc_goToMenuButton = new GridBagConstraints();
		gbc_goToMenuButton.gridx = 0;
		gbc_goToMenuButton.gridy = 3;
		getContentPane().add(goToMenuButton, gbc_goToMenuButton);

		if (gameResult == GameResult.WIN) {
			setIconImage(Toolkit.getDefaultToolkit().getImage(MessageDialog.class.getResource("/resources/images/win.png")));
			pictureLabel = new JLabel(GraphicsFactory.getCoolFaceIcon());
			getContentPane().add(pictureLabel);
		} else if (gameResult == GameResult.LOSE) {
			setIconImage(Toolkit.getDefaultToolkit().getImage(MessageDialog.class.getResource("/resources/images/lose.png")));
			pictureLabel = new JLabel(GraphicsFactory.getDeadFaceIcon());
			getContentPane().add(pictureLabel);
		} else {

		}
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2
				- this.getSize().height / 2);
		pack();
//		setVisible(true);
	}
	
	
	public MessageDialog(JFrame owner, boolean isModal, String message, String title) {
super(owner, isModal);
		
		setTitle(title);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0 };
		gridBagLayout.rowHeights = new int[] { 0, -18, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		pictureLabel = new JLabel();
		GridBagConstraints gbc_pictureLabel = new GridBagConstraints();
		gbc_pictureLabel.insets = new Insets(0, 0, 5, 0);
		gbc_pictureLabel.gridx = 0;
		gbc_pictureLabel.gridy = 1;
		getContentPane().add(pictureLabel, gbc_pictureLabel);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblMessage = new JLabel(message);
		GridBagConstraints gbc_lblMessage = new GridBagConstraints();
		gbc_lblMessage.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMessage.gridx = 0;
		gbc_lblMessage.gridy = 0;
		panel.add(lblMessage, gbc_lblMessage);


		JButton okBtn = new JButton(ButtonNames.OK);
		okBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//TODO Dispose dialog.
				
			}

		});
		
		GridBagConstraints gbc_okBtn = new GridBagConstraints();
		gbc_okBtn.gridx = 0;
		gbc_okBtn.gridy = 3;
		getContentPane().add(okBtn, gbc_okBtn);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2
				- this.getSize().height / 2);
		pack();
		setVisible(true);
	}

}
