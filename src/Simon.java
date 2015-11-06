import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.io.IOException;

public class Simon extends KeyAdapter {


	JFrame Frame = new JFrame();

	HashMap<Integer, String> images = new HashMap<Integer, String>();
	boolean simonSays = false;
	private int imageIndex;
	private int tries = 0;
	Date timeAtStart;

	private void makeAlbum() {
		images.put(new Integer(KeyEvent.VK_UP), "Up-Arrow.jpg");
		images.put(new Integer(KeyEvent.VK_RIGHT), "Right-Arrow.jpg");
		images.put(new Integer(KeyEvent.VK_DOWN), "Down-Arrow.jpg");
		images.put(new Integer(KeyEvent.VK_LEFT), "Left-Arrow.jpeg");
		JOptionPane pressOption = new JOptionPane();
		pressOption
				.showMessageDialog(null,
						"Press the matching key when 'Simon says' otherwise press a different key");
		showImage();
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		int points = 0;
		// 16. make a points variable to track the score. tell the user their
		// score at the end.
		// 17. if the keyCode matches the imageIndex and "Simon says..."
		// increase their score
		if(keyCode == imageIndex && simonSays == true)
		{
			points = points+1;
			speak("Yes!");
		}
		// 18. if the keyCode doesn't match the imageIndex and
		// "Simon didn't say..." increase their score
		else if(keyCode != imageIndex && simonSays == false)
		{
			points = points+1;
			speak("Yes!");
		}
		else
		{
			speak("NO!");
		}
		// 19. Use the speak method to tell the user if they were correct or not
		// 13. increment tries by 1

		tries = tries+1;
		
		// 14. if tries is greater than 9 (or however many you want)
		
		if(tries > 9)
		{
			System.exit(0);
		}

		// 15. exit the program
		// 11. dispose of the frame
		Frame.dispose();
		// 12. call the method to show an image
		showImage();
	}

	private void showImage() {
		Frame = new JFrame();
		Frame.setVisible(true);
		Frame.add(getNextRandomImage());
		Frame.setSize(500, 500);
		Frame.addKeyListener(this);
		Random random = new Random();
		simonSays = random.nextBoolean();
		if (simonSays) {
			speak("Simon says press");
		} else {
			speak("Press");
		}
	}

	private Component getNextRandomImage() {
		this.imageIndex = new Random().nextInt(4) + 37;
		return loadImage(images.get(imageIndex));
	}

	private JLabel loadImage(String fileName) {
		URL imageURL = getClass().getResource(fileName);
		Icon icon = new ImageIcon(imageURL);
		return new JLabel(icon);
	}

	void speak(String words) {
		try {
			Runtime.getRuntime().exec("say " + words).waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		new Simon().makeAlbum();
	}
}

/*
 * 20. add a timer ~~~ where the code starts running ~~~ timeAtStart = new
 * Date();
 * 
 * ~~~ where the code ends ~~~ Date timeAtEnd = new Date();
 * System.out.println((timeAtEnd.getTime()-timeAtStart.getTime())/1000);
 * System.exit(0);
 */

