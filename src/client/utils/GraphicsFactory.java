package client.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class GraphicsFactory {

	public static ImageIcon getLogoIcon() {
		BufferedImage image;
		try {
			image = ImageIO.read(ClassLoader.getSystemResourceAsStream("images/logo.png"));
			
			
			return new ImageIcon(image);
	    } catch (IOException ex) {
	    	ex.printStackTrace();
	    }
		return null;
	}
	
	public static ImageIcon getBombIcon() {
		Image image;
		try {
			image = ImageIO.read(ClassLoader.getSystemResourceAsStream("images/bomb.png"));
			image = image.getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH);
			return new ImageIcon(image);
	    } catch (IOException ex) {
	    	ex.printStackTrace();
	    }
		return null;
	}
	
	public static ImageIcon getFlagIcon() {
		Image image;
		try {
			image = ImageIO.read(ClassLoader.getSystemResourceAsStream("images/flag.png"));
			image = image.getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH);
			return new ImageIcon(image);
	    } catch (IOException ex) {
	    	ex.printStackTrace();
	    }
		return null;
	}
	
	public static ImageIcon getHappyFaceIcon() {
		Image image;
		try {
			image = ImageIO.read(ClassLoader.getSystemResourceAsStream("images/happyFace.png"));
			image = image.getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH);
			return new ImageIcon(image);
	    } catch (IOException ex) {
	    	ex.printStackTrace();
	    }
		return null;
	}

	public static ImageIcon getSadFaceIcon() {
		Image image;
		try {
			image = ImageIO.read(ClassLoader.getSystemResourceAsStream("images/sadFace.png"));
			image = image.getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH);
			return new ImageIcon(image);
	    } catch (IOException ex) {
	    	ex.printStackTrace();
	    }
		return null;
	}
	public static ImageIcon getCoolFaceIcon() {
		Image image;
		try {
			image = ImageIO.read(ClassLoader.getSystemResourceAsStream("images/coolFace.png"));
			image = image.getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH);
			return new ImageIcon(image);
	    } catch (IOException ex) {
	    	ex.printStackTrace();
	    }
		return null;
	}

	public static ImageIcon getDeadFaceIcon() {
		Image image;
		try {
			image = ImageIO.read(ClassLoader.getSystemResourceAsStream("images/deadFace.png"));
			image = image.getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH);
			return new ImageIcon(image);
	    } catch (IOException ex) {
	    	ex.printStackTrace();
	    }
		return null;
	}
	
}
