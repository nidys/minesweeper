package client.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class GraphicsFactory {
	private final static String IMAGE_PREFIX = "images/";
	private final static String BOMB = IMAGE_PREFIX + "bomb.png";
	private final static String FLAG = IMAGE_PREFIX + "flag.png";
	private final static String HAPPY_FACE = IMAGE_PREFIX + "happyFace.png";
	private final static String SAD_FACE = IMAGE_PREFIX + "sadFace.png";
	private final static String COOL_FACE = IMAGE_PREFIX + "coolFace.png";
	private final static String DEAD_FACE = IMAGE_PREFIX + "deadFace.png";

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
		return getAsImageIcon(BOMB);
	}

	public static ImageIcon getFlagIcon() {
		return getAsImageIcon(FLAG);
	}
	
	public static ImageIcon getHappyFaceIcon() {
		return getAsImageIcon(HAPPY_FACE);
	}

	public static ImageIcon getSadFaceIcon() {
		return getAsImageIcon(SAD_FACE);
	}

	public static ImageIcon getCoolFaceIcon() {
		return getAsImageIcon(COOL_FACE);
	}

	public static ImageIcon getDeadFaceIcon() {
		return getAsImageIcon(DEAD_FACE);
	}

	public static ImageIcon getAsImageIcon(String DEAD_FACE) {
		Image image;
		try {
			image = ImageIO.read(ClassLoader.getSystemResourceAsStream(DEAD_FACE));
			image = image.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
			return new ImageIcon(image);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
