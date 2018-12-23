package IA;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class directory extends JFrame{
	private JPanel panel = new JPanel(new GridBagLayout());
	private ActionListener listener;
	private JButton suCrit;
	private JButton rec;
	public directory(){
		 class ChoiceListener implements ActionListener
		 {
			 public void actionPerformed(ActionEvent event)
			 {
				 if(event.getSource()==suCrit){
						suc_result res = new suc_result();
						setVisible(false);
						res.setVisible(true);
				 }
				 else if (event.getSource()==rec){
					 call_octave callO = new call_octave();
					 callO.setVisible(true);
					 setVisible(false);
				 }
			 }
		 }
		 listener= new ChoiceListener();
		 setVisible(true);
		 setBounds(503,234,600,230);
		 setTitle("Directory");
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 choices();
	}

	public void choices(){// let user choose 2 choices

		add(panel, BorderLayout.NORTH);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets( 15, 8, 15, 8);
		JLabel label = new JLabel("Please select one of the following: ");
		c.gridx=0; c.gridy=0;
		c.gridwidth=10;
		panel.add(label, c);
		suCrit = new JButton("To check students' successfulness");
		rec = new JButton("To receive a recommendation made by this prorgam");
		c.gridx=0; c.gridy=1; 
		c.gridwidth=2; 
		panel.add(suCrit, c);
		c.gridx=2; c.gridy=1;
		c.gridwidth=2;
		panel.add(rec, c);
		suCrit.addActionListener(listener);
		rec.addActionListener(listener);
	}

}
