package IA;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import dk.ange.octave.exception.OctaveEvalException;
import dk.ange.octave.type.OctaveDouble;
public class call_octave extends JFrame{
	private ActionListener listenerC;
	private JButton back;
	private JButton getP;
	private JTextField mathG;
	private JTextField sciG;
	private GridBagConstraints c;
	private JPanel panel;
	private JTextArea textArea = new JTextArea(2, 10);
	private JTextArea acuText = new JTextArea(2, 10);
	private double MathMark, ScMark;
	private  DecimalFormat df = new DecimalFormat(".##");
	private boolean first = true;
	public call_octave() {
		 class ChoiceListener implements ActionListener
		 {
			 public void actionPerformed(ActionEvent event)
			 {
				 try {
				 if (event.getSource()==getP){
					 ScMark=Double.parseDouble(sciG.getText());
					 MathMark=Double.parseDouble(mathG.getText());
					 textArea.setText("");
					 if(first==true){
					 	callMain();
					 }
						getProb();
				 }
				 else if (event.getSource()==back){
						//tester_IA.octave.close();
					 directory dir = new directory();
					 dir.setVisible(true);
					 setVisible(false);
				 }
			 }
				 catch (IOException e) {
					e.printStackTrace();
				 }
				 catch (NumberFormatException e){
					 JOptionPane.showMessageDialog(null, "Please enter numbers only!");
				 }
					/*catch (OctaveEvalException e){ //if the path is wrong
						JOptionPane.showMessageDialog(null, "The octave file location is not correct! Please restart program!");
					}*/
			 }
		 }
		listenerC= new ChoiceListener();
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(503,234,550,350);
		setTitle("Search for student results");	
		textArea.setEditable(false);
		acuText.setEditable(false);
		gui();
	}
	public void gui(){
		panel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		add(panel);
		JLabel label = new JLabel("Science grade: ");
		c.insets = new Insets( 8, 8, 8, 8);
		c.gridx=0; c.gridy=0;
		panel.add(label, c);
		c.gridx=1; c.gridy=0;
		sciG = new JTextField(8);
		panel.add(sciG, c);
		JLabel label1 = new JLabel("Math grade: ");
		c.gridx=2; c.gridy=0;
		panel.add(label1, c);
		c.gridx=3; c.gridy=0;
		mathG = new JTextField(8);
		panel.add(mathG, c);
		getP = new JButton("get the probability");
		c.gridx=1; c.gridy=1;
		getP.addActionListener(listenerC);
		panel.add(getP, c);
		c.gridx=1; c.gridy=2;
		back = new JButton("back");
		back.addActionListener(listenerC);
		panel.add(back, c);
	}
	public void callMain(){
		System.out.println(octavePath.octPath);
		tester_IA.octave.eval("cd " + octavePath.octPath);
		tester_IA.octave.eval("main");
		first=false;
	}
	
	public void getProb() throws IOException{
		FileWriter faw = new FileWriter(octavePath.octPath + "\\probability.txt");
		PrintWriter out = new PrintWriter(faw);
		out.print(ScMark +","+ MathMark);
		out.close();
		faw.close();
		//tester_IA.octave.eval("cd " + octavePath.octPath);
		//tester_IA.octave.eval("main");
		tester_IA.octave.eval("getProb");
		OctaveDouble prob = (OctaveDouble)tester_IA.octave.get("prob");
		OctaveDouble acu = (OctaveDouble)tester_IA.octave.get("accuracy");
		JLabel labe = new JLabel("Probability of getting into Sc10: ");
		c.gridx=0; c.gridy=2;
		panel.add(labe, c);
		JLabel labe1 = new JLabel("The accuracy of this prediction:");
		c.gridx=0; c.gridy=3;
		panel.add(labe1, c);
		c.gridx=1; c.gridy=2;
		panel.add(textArea, c);
		c.gridx=1; c.gridy=3;
		panel.add(acuText, c);
		c.gridx=1; c.gridy=4;
		panel.add(back, c);
		textArea.append(df.format(prob.get(1)*100) + "%");
		acuText.append(df.format(acu.get(1)) + "%");
		
	}

}
//C:\Octave\Octave-4.2.1\bin

