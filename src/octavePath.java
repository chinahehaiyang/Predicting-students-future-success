package IA;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class octavePath extends JFrame{
	private JPanel panel = new JPanel();
	private ActionListener listenerO;
	private JButton octpathB;
	public static String octPath;
	public octavePath(){
		 class ChoiceListener implements ActionListener
		 {
			 public void actionPerformed(ActionEvent event)
			 {
				 if(event.getSource()==octpathB){
				 try {
					getOctPath();
				} catch (IOException e) {
					e.printStackTrace();
				}
				 }
			 }
		 }
		 listenerO= new ChoiceListener();
		 setVisible(true);
		 setBounds(503,234,600,130);
		 setTitle("Octave path");
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 octpathGUI();
	}
	public void octpathGUI(){
		 add(panel, BorderLayout.NORTH);
		 octpathB = new JButton("Please choose the location of the octave files: ");
		 octpathB.addActionListener(listenerO);
		 panel.add(octpathB);
		
	}
	public void getOctPath() throws IOException{	//pops up filechooser to let the user to select the path of octave files
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("the location of the octave files: ");
		 fileChooser.setCurrentDirectory(new java.io.File("C:/Users/Owner/Desktop"));
		 fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if(fileChooser.showOpenDialog(octpathB) == JFileChooser.APPROVE_OPTION){ //if the location is confirmed
			octPath=fileChooser.getSelectedFile().getAbsolutePath();
			moveToRead();
		}
	}
	public void moveToRead() throws IOException{		
		read_data read = new read_data();
		setVisible(false);
		read.setVisible(true);

	}

}
/*
 * 		succ_crit sc = new succ_crit();
		sc.checkSuccess();
		sc.outputMark();
		sc.outputSuc();
		directory dir = new directory();
		dir.setVisible(true);
 */ 
