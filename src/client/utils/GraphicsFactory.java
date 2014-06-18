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
	
	public ImageIcon getBombIcon() {
		BufferedImage image;
		try {
			image = ImageIO.read(ClassLoader.getSystemResourceAsStream("images/bomb.png"));
			return new ImageIcon(image);
	    } catch (IOException ex) {
	    	ex.printStackTrace();
	    }
		return null;
	}
	
	public ImageIcon getFlagIcon() {
		BufferedImage image;
		try {
			image = ImageIO.read(ClassLoader.getSystemResourceAsStream("images/flag.png"));
			return new ImageIcon(image);
	    } catch (IOException ex) {
	    	ex.printStackTrace();
	    }
		return null;
	}
	
	public ImageIcon getWinIcon() {
		BufferedImage image;
		try {
			image = ImageIO.read(ClassLoader.getSystemResourceAsStream("images/win.png"));
			return new ImageIcon(image);
	    } catch (IOException ex) {
	    	ex.printStackTrace();
	    }
		return null;
	}
	
	public ImageIcon getLoseIcon() {
		BufferedImage image;
		try {
			image = ImageIO.read(ClassLoader.getSystemResourceAsStream("images/lose.png"));
			return new ImageIcon(image);
	    } catch (IOException ex) {
	    	ex.printStackTrace();
	    }
		return null;
	}
}
