package client.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class GraphicsFactory {

	public ImageIcon getLogoIcon() {
		BufferedImage image;
		try {
			image = ImageIO.read(ClassLoader.getSystemResourceAsStream("images/logo.png"));
			return new ImageIcon(image);
	    } catch (IOException ex) {
	    	ex.printStackTrace();
	    }
		return null;
	}
}
