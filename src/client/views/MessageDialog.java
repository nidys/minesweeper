package client.views;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import client.controllers.MessageDialogBtnController;
import client.internationalization.ButtonNames;
import client.utils.GraphicsFactory;
import common.enums.GameResult;

@SuppressWarnings("serial")
public class MessageDialog extends DialogBase {

	private JLabel pictureLabel;
	private JButton msgButton;
	private JPanel panel;
	private JLabel lblMessage;

	/**
	 * @wbp.parser.constructor
	 */
	public MessageDialog() {
		super(null, false);
		msgButton = new JButton();
	}
	
	
	public void displayLostReasonMsg(JFrame owner, boolean isModal,	common.enums.GameResult gameResult, String winnerName,String message, String title){


		setTitle(title);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0, -18, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);

		pictureLabel = new JLabel();
		GridBagConstraints gbc_pictureLabel = new GridBagConstraints();
		gbc_pictureLabel.insets = new Insets(0, 0, 5, 0);
		gbc_pictureLabel.gridx = 0;
		gbc_pictureLabel.gridy = 1;
		getContentPane().add(pictureLabel, gbc_pictureLabel);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,  TitledBorder.TOP, null, null));
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

		msgButton.setText(ButtonNames.GO_TO_MENU);
		GridBagConstraints gbc_goToMenuButton = new GridBagConstraints();
		gbc_goToMenuButton.gridx = 0;
		gbc_goToMenuButton.gridy = 3;
		getContentPane().add(msgButton, gbc_goToMenuButton);

//		Icon icon = null;
//		Object[] options = {"Go to menu."};
		
		if (gameResult == GameResult.WIN) {
			Toolkit.getDefaultToolkit().getImage(MessageDialog.class.getResource("/resources/images/win.png"));
			pictureLabel = new JLabel(GraphicsFactory.getCoolFaceIcon());
			getContentPane().add(pictureLabel);
		} else if (gameResult == GameResult.LOSE) {
			Toolkit.getDefaultToolkit().getImage(MessageDialog.class.getResource("/resources/images/lose.png"));
			pictureLabel = new JLabel(GraphicsFactory.getDeadFaceIcon());
			getContentPane().add(pictureLabel);
		} else {

		}
		
	
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
				/ 2 - this.getSize().height / 2);
		pack();
		 setVisible(true);
	}
	
	public void displayInfoMsg(JFrame owner, boolean isModal, String message,	String title){
		
		setTitle(title);
		 GridBagLayout gridBagLayout = new GridBagLayout();
		 gridBagLayout.columnWidths = new int[] { 0 };
		 gridBagLayout.rowHeights = new int[] { 0, -18, 0, 0 };
		 gridBagLayout.columnWeights = new double[] { 1.0 };
		 gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 1.0,
		 Double.MIN_VALUE };
		 getContentPane().setLayout(gridBagLayout);
		
		 pictureLabel = new JLabel();
		 GridBagConstraints gbc_pictureLabel = new GridBagConstraints();
		 gbc_pictureLabel.insets = new Insets(0, 0, 5, 0);
		 gbc_pictureLabel.gridx = 0;
		 gbc_pictureLabel.gridy = 1;
		 getContentPane().add(pictureLabel, gbc_pictureLabel);
		
		 panel = new JPanel();
		 panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		 TitledBorder.TOP, null, null));
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
		
		
		 msgButton.setText(ButtonNames.OK);
		 GridBagConstraints gbc_okBtn = new GridBagConstraints();
		 gbc_okBtn.gridx = 0;
		 gbc_okBtn.gridy = 3;
		 getContentPane().add(msgButton, gbc_okBtn);
		
		
		 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		 this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		 pack();
		 setVisible(true);

	}
	
	
	public void addMessageDialogBtnController(ActionListener listener){
		msgButton.addActionListener(listener);
		
	}

}
