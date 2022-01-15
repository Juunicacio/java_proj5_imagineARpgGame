package imagineARpgGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
//import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MyGUI extends JFrame implements ActionListener{	
	
	// adding logo
	String logoName = "another_thinking_image.png";		
	String fullyQualifiedLogoName = "imagineARpgGame." + logoName;		
	ImageIcon image = new ImageIcon(fullyQualifiedLogoName);	
	
	// adding border
	Border border = BorderFactory.createLineBorder(Color.green,1);	
	
	//JButton button;
	JButton confirmNameButtom;
	JLabel label;
	
	int frameWidth = 420;
	int frameHeight = 420;	
	
	public MyGUI() {		
		// set title of frame
		this.setTitle("Imagine a RPG Game");		
		this.setIconImage(image.getImage());
		
		// adding label to the frame
		this.add(myLabels());	
		
		// adding panel to the frame
		this.add(myBluePanel());	
		this.add(myRedPanel());
		
		// adding buttons
		this.add(myConfirmNameButton());
		
		// exit out application
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		//sets the x-dimension and y-dimension of the frame
		this.setSize(frameWidth,frameHeight);
		// prevent frame from being resized
		this.setResizable(false);						
		
		// setting layout manager 
		//this.setLayout(null);
		// setting layout manager to a grid
		// we can specify the amount of rows and columns that we want to this grid
		// rows, columns
		// adding horizontal spacing and vertical spacing
		this.setLayout(new GridLayout(3, 3, 10, 10));		
		// frame background color
		//this.getContentPane().setBackground(Color.blue);
		// trying a rgb color
		this.getContentPane().setBackground(new Color(123,50,250));
		
		// make frame visible
		this.setVisible(true);		
				
		// add all components before pack, and disable setSize of the frame, because it will set the screen to the dimensions of the components
		//this.pack();		
	}
	
	public JLabel myLabels() {
		// create a label
		label = new JLabel();		
		// set text
		label.setText("Welcome! Let's try some code");
		//label.setIcon(image);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.TOP);
		label.setForeground(Color.black);
		label.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		
		// if I add a image, set how text will be far from the text
		label.setIconTextGap(-25); // very close
		// set a background color for the text
		label.setBackground(Color.orange);
		label.setOpaque(true); // display background color of the text
		
		// set border for the label
		label.setBorder(border);
		
		// changing positions of the label
		label.setVerticalAlignment(JLabel.CENTER);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		System.out.println(label.getSize().getWidth());		
		System.out.println(label.getSize().getHeight());
		
		// set where I want this label to be
		// because for now, the label is taking the entire screen
		label.setBounds(50, 60, 300, 100);
		
		// set visibility of the text to false, just to turn it to true when I click the button
		label.setVisible(false);
		
		return label;		
	}	
	
	// let's create a panel
	public JPanel myBluePanel() {
		JPanel bluePanel = new JPanel();
		bluePanel.setBackground(Color.blue);
		bluePanel.setBounds(50, 120, 150, 200);		
		return bluePanel;
	}
	
	public JPanel myRedPanel() {
		JPanel redPanel = new JPanel();
		redPanel.setBackground(Color.red);
		redPanel.setBounds(200, 120, 150, 200);
		return redPanel;
	}
	
	// let's create a button
	public JButton myConfirmNameButton() {
		confirmNameButtom = new JButton("OK");
		// set text or use the buttonName
		//toStartTheGameButton.setText("OK");
		
		confirmNameButtom.setBounds(10,0,100,50);
		confirmNameButtom.setFocusable(false);
		
		// add the action here:
		confirmNameButtom.addActionListener(this);
		// or we can do this:
		//button.addActionListener(e -> label.setVisible(true)); //System.out.println("hello"));
				
		// to disable the button
		//button.setEnabled(false);				
		return confirmNameButtom;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Debugging5");
		if(e.getSource() == this.confirmNameButtom) {			
			System.out.println("Hi");
			//label.setVisible(true);
		}
	}	
	

}
