package imagineARpgGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import javax.swing.JButton;
import javax.swing.JFrame;

public class LaunchPage implements ActionListener{	
	JFrame currentFrame;
	JButton newWindowButton;
	InputStream input;
	int frameWidth = 420;
	int frameHeight = 420;
	
	LaunchPage(){	
		currentFrame = new JFrame();
		newWindowButton = new JButton("New Window"); // text written in the button
		
		this.newWindowButton.setBounds(100,160,200,40);
		this.newWindowButton.setFocusable(false);
		this.newWindowButton.addActionListener(this);
		
		this.input = ClassLoader.getSystemResourceAsStream("another_thinking_image");
		
		this.currentFrame.add(newWindowButton);
		// exit out application
		this.currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		//sets the x-dimension and y-dimension of the frame
		this.currentFrame.setSize(frameWidth,frameHeight);	
		this.currentFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == newWindowButton) {
			this.currentFrame.dispose();
			NewWindow myNextWindow = new NewWindow();
			System.out.println("Debugging1");
		}
	}

}
