package client.views;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;

/**
 * Base class for standard windows.
 */
@SuppressWarnings("serial")
public abstract class WindowBase extends JFrame {

	public WindowBase() {
		super();
		forceWindowsStyle(); // sets the Windows-like GUI styling
	}

	private void forceWindowsStyle() {
		// try {
		// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		// } catch (ClassNotFoundException | InstantiationException
		// | IllegalAccessException | UnsupportedLookAndFeelException e) {
		// // TODO Replace with Logger.
		// e.printStackTrace();
		// }
	}
}
