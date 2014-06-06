package guiGeneratorsTemp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MainWindowPlanszaGenerator {

	private static final String NEW_BUTTON = "    ";
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindowPlanszaGenerator window = new MainWindowPlanszaGenerator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindowPlanszaGenerator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 293, 227);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblMe = new JLabel("Me");
		GridBagConstraints gbc_lblMe = new GridBagConstraints();
		gbc_lblMe.gridwidth = 2;
		gbc_lblMe.insets = new Insets(0, 0, 5, 5);
		gbc_lblMe.gridx = 0;
		gbc_lblMe.gridy = 0;
		frame.getContentPane().add(lblMe, gbc_lblMe);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.BOTH;
		gbc_separator.ipady = 10;
		gbc_separator.ipadx = 1;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridheight = 2;
		gbc_separator.gridx = 2;
		gbc_separator.gridy = 0;
		frame.getContentPane().add(separator, gbc_separator);
		
		JLabel lblOpponent = new JLabel("Opponent");
		GridBagConstraints gbc_lblOpponent = new GridBagConstraints();
		gbc_lblOpponent.gridwidth = 2;
		gbc_lblOpponent.insets = new Insets(0, 0, 5, 5);
		gbc_lblOpponent.gridx = 3;
		gbc_lblOpponent.gridy = 0;
		frame.getContentPane().add(lblOpponent, gbc_lblOpponent);
		
		JButton btnNewButton = new JButton(NEW_BUTTON);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 1;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton(NEW_BUTTON);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 1;
		frame.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton_6 = new JButton(NEW_BUTTON);
		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_6.gridx = 3;
		gbc_btnNewButton_6.gridy = 1;
		frame.getContentPane().add(btnNewButton_6, gbc_btnNewButton_6);
		
		JButton btnNewButton_4 = new JButton(NEW_BUTTON);
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_4.gridx = 4;
		gbc_btnNewButton_4.gridy = 1;
		frame.getContentPane().add(btnNewButton_4, gbc_btnNewButton_4);
		
		JButton btnNewButton_2 = new JButton(NEW_BUTTON);
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 2;
		frame.getContentPane().add(btnNewButton_2, gbc_btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton(NEW_BUTTON);
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 1;
		gbc_btnNewButton_3.gridy = 2;
		frame.getContentPane().add(btnNewButton_3, gbc_btnNewButton_3);
		
		JButton button = new JButton(NEW_BUTTON);
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 3;
		gbc_button.gridy = 2;
		frame.getContentPane().add(button, gbc_button);
		
		JButton btnNewButton_5 = new JButton(NEW_BUTTON);
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_5.gridx = 4;
		gbc_btnNewButton_5.gridy = 2;
		frame.getContentPane().add(btnNewButton_5, gbc_btnNewButton_5);
		
		JButton btnReset = new JButton("Reset");
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.insets = new Insets(0, 0, 0, 5);
		gbc_btnReset.gridx = 0;
		gbc_btnReset.gridy = 4;
		frame.getContentPane().add(btnReset, gbc_btnReset);
	}

}
