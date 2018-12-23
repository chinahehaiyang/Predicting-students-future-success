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
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class suc_result extends JFrame{
	private JTextArea textArea = new JTextArea(2, 20);
 
	private JButton back;
	private JButton srchB;
	private JButton outputS;
	private ActionListener listener;
	private JTextField nameSrch;
	private String name;
	private GridBagConstraints c;
	private JPanel panel;
	private boolean exist=false;
	private String sucPath;
	public suc_result(){
		 class ChoiceListener implements ActionListener
		 {
			 public void actionPerformed(ActionEvent event)
			 {
				 if (event.getSource()==back){
					 directory dir = new directory();
					 dir.setVisible(true);
					 setVisible(false);
				 }
				 else if (event.getSource()==srchB){
					 name = nameSrch.getText();
					 exist=false;
					 textArea.setText("");
					 getStudSuc();
				 }
				 else if (event.getSource()==outputS){
					 outputSuc();
				 }
			 }
		 }
		listener= new ChoiceListener();
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(503,234,550,350);
		setTitle("Search for student results");	
		textArea.setEditable(false);
		msg();
	}
	public void msg(){
		panel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		getContentPane().add(panel);
		JLabel label = new JLabel("The student's name: ");
		c.insets = new Insets( 8, 8, 8, 8);
		c.gridx=0; c.gridy=0;
		panel.add(label, c);
		nameSrch = new JTextField(30);
		c.gridx=1; c.gridy=0;
		panel.add(nameSrch, c);
		srchB=new JButton("Search");
		srchB.addActionListener(listener);
		c.gridx=0; c.gridy=1;
		c.gridwidth=5;
		panel.add(srchB, c);
        back = new JButton("Back");
        back.addActionListener(listener);
        c.gridx=0; c.gridy=1;
        c.gridwidth=8;
        panel.add(back, c);
        c.gridx=1; c.gridy=2;
        c.gridwidth=1;
        outputS = new JButton("Output the students' success into a designated location");
        outputS.addActionListener(listener);
        panel.add(outputS, c);
        
	}
	public void getStudSuc(){	//display the students' successfulness by searching their name
		c.gridx=0; c.gridy=2; c.gridwidth=1;
		JLabel res = new JLabel("The result: ");
		 panel.add(res, c);
        c.gridx=1; c.gridy=2;
        panel.add(textArea, c);
        c.gridx=0; c.gridy=3;
        c.gridwidth=8;
        panel.add(back, c);
        c.gridx=1; c.gridy=4;
        c.gridwidth=1;
        panel.add(outputS, c);
        for (int i=0;i<succ_crit.success.length;i++){ //search for the student
        	if(succ_crit.success[i][0].equalsIgnoreCase(name)){ //if the student is found
                textArea.append(succ_crit.success[i][0] +": " + succ_crit.success[i][1]);
                exist=true;	//exist becomes true
        	}
        	else if (i==succ_crit.success.length-1 && exist==false){ //if the student is not found
        		if (succ_crit.success[i][0].equalsIgnoreCase(name)==false){
        			textArea.append("This person does exist!");
        			exist=false;	//exist becomes false
        		}
        	}
        			
        }
	}
	public void outputSuc(){
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File("C:/Users/Owner/Desktop")); //initial directory
		fc.setDialogTitle("Please select where you want to save it: ");
		try{
		if(fc.showSaveDialog(outputS) == JFileChooser.APPROVE_OPTION){ 
			sucPath = fc.getSelectedFile().getAbsolutePath(); //get filepath
			FileWriter fw = new FileWriter(sucPath  + ".txt"); //save the file as a text file
			PrintWriter write = new PrintWriter(fw);
			for (int i=0;i<succ_crit.success.length;i++){		//print out each student's successfulness
				write.println(succ_crit.success[i][0] +": " + succ_crit.success[i][1]); 
			}
			JOptionPane.showMessageDialog(null, "The file is saved!"); //pop up that tells the user the file is saved.
			fw.close();
			write.close();
		}
		}
		 catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
}

/*
textArea = new JTextArea(60, 20);
textArea.setEditable(false);
c.gridx=1; c.gridy=0;
panel.add(textArea);
*/