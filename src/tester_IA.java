package IA;
import java.io.*;
import dk.ange.octave.OctaveEngine;
import dk.ange.octave.OctaveEngineFactory;
	
	public class tester_IA {
		public static OctaveEngineFactory factory = new OctaveEngineFactory();
		public static OctaveEngine octave;
		public static void main (String args[]) throws IOException{
			octave = new OctaveEngineFactory().getScriptEngine();
		    octavePath octpath = new octavePath();
		    octpath.setVisible(true);

		}

	}
    
    //setVisible(false);
	/*result read = new result();
	read.setTitle("Program");
	read.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	read.setVisible(true);*/