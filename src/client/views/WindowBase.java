package client.views;

import javax.swing.JFrame;

/**
 * Base class for standard windows.
 */
@SuppressWarnings("serial")
public abstract class WindowBase extends JFrame {

	public WindowBase() {
		super();
		forceWindowsStyle(); // sets the Windows-like GUI styling
	}

	// TODO remove or fix for linux
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
