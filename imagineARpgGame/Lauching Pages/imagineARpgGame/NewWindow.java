package imagineARpgGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class NewWindow extends JFrame implements ActionListener{
	
	JButton toStartTheGameButton;
	JLabel label;
	int frameWidth = 420;
	int frameHeight = 420;
	
	public NewWindow() {			
		// adding buttons
		this.add(myStartButton());
		// adding labels
		this.add(myLabels());	
		
		// this is my frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(frameWidth,frameHeight);
		//this.setLayout(null);
		this.setVisible(true); // the text should show when it's true			
	}
	
	public JLabel myLabels() {
		// create a label
		label = new JLabel();
		label.setText("test");
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.TOP);
		label.setForeground(Color.black);
		label.setFont(new Font("Ubuntu", Font.PLAIN, 20));		
		return label;
	}	
	
	// Create a button
	public JButton myStartButton() {
		toStartTheGameButton = new JButton("Start Game");
		// set text or use the buttonName
		//toStartTheGameButton.setText("Start Game");
		
		toStartTheGameButton.setBounds(10,0,100,50);
		toStartTheGameButton.setFocusable(false);
		// add the action here:
		toStartTheGameButton.addActionListener(this);
		// or we can do this:
		//button.addActionListener(e -> label.setVisible(true)); //System.out.println("hello"));
				
		// to disable the button
		//button.setEnabled(false);				
		return toStartTheGameButton;
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		// everytime I click in a button it prints "Debugging2"
		System.out.println("Debugging2");
		//System.out.println(e);
		if(e.getSource() == this.toStartTheGameButton) {
			System.out.println("Debugging3");
			this.dispose();
			MyGUI startGameGUI = new MyGUI();
			System.out.println("Debugging4");
		}
		
	}
	

}
