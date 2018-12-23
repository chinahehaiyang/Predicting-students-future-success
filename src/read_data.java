package IA;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.OldExcelFormatException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class read_data extends JFrame
{
private String filePath;
private  JPanel panel = new JPanel(new GridBagLayout());
private GridBagConstraints c;
private ActionListener listener;
private JButton fileopen;
private JButton addFile;
private JButton cont;
private String coursNames[] = {"ELLSc", "Sc10","Sc20","Sc30","Sc14","Sc24","Bio20","Bio30","Chm20","Chm30","Phys20","Phys30","Math10C","Math103","Math201","Math202","Math203","Math301","Math302","Math31"};
private int Rows;
public static int totalrows;
public String stud_data[][];
private ArrayList <String> excelPath = new ArrayList <String>();
int rowC=0;
//private RandomAccessFile excels;
private FileInputStream fileInputStream; //is where to read excel files
private FileWriter fw;
public int i=0;
//public static int excelNum=0;
//public static int excelNumLeft=1;
private PrintWriter output;

public read_data()
{
	 class ChoiceListener implements ActionListener
	 {
		 public void actionPerformed(ActionEvent event)
		 {
			 if(event.getSource()==fileopen){	//if click "open excel"
			 chooseFile();	// go choose
			 }
			 else if(event.getSource()==addFile){ //if click "add more excel"
				 chooseNewFile();	//choose again
			 }
			 else if(event.getSource()==cont){	//go to the next class
				 next();
			 }
		 }
	 }
	 listener = new ChoiceListener();
	 setBounds(553,284,300,200);
	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 setTitle("Program");
	 setVisible(true);
	 add(panel);
	 fileOpenB();
}

public void fileOpenB(){
	c = new GridBagConstraints();
	
	fileopen = new JButton("Please choose the excel document");
	fileopen.addActionListener(listener);
	c.gridx=0; c.gridy=0;
	panel.add(fileopen, c);
	 //return panel;
}

public void chooseFile(){ // read the number of files and store them like: data1, data2 if only 2 files are selected. make am integer a static thing
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File("C:/Users/Owner/Desktop")); //initial directory
		fc.setDialogTitle("Please choose the excel document: ");
		// allowed types of Excel format
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel file","xls","xlt","xlsx","xlsm","xltx","xltm","xlam"); 
		fc.setFileFilter(filter);
		//try{
		if(fc.showOpenDialog(fileopen) == JFileChooser.APPROVE_OPTION){ 
			filePath = fc.getSelectedFile().getAbsolutePath(); //get filepath
			//excelNum++;
			//System.out.println(filePath);
			excelPath.add(filePath);
			fileopen.setVisible(false);
			c.insets = new Insets( 4, 4,4, 4);
			addFile = new JButton ("add another excel file");
			c.gridx=0; c.gridy=0;
			panel.add(addFile, c);
			addFile.addActionListener(listener);
			cont = new JButton("continue");
			c.gridx=0; c.gridy=1;
			cont.addActionListener(listener);
			panel.add(cont, c);
		}
}

public void chooseNewFile(){ // read the number of files and store them like: data1, data2 if only 2 files are selected. make am integer a static thing
	JFileChooser fc = new JFileChooser();
	fc.setCurrentDirectory(new java.io.File("C:/Users/Owner/Desktop")); //initial directory
	fc.setDialogTitle("Please choose the excel document: ");
	// allowed types of Excel format
	FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel file","xls","xlt","xlsx","xlsm","xltx","xltm","xlam"); 
	fc.setFileFilter(filter);
	//try{
	if(fc.showOpenDialog(fileopen) == JFileChooser.APPROVE_OPTION){ 
		filePath = fc.getSelectedFile().getAbsolutePath(); //get filepath
		//excelNum++;
		//System.out.println(filePath);
		excelPath.add(filePath);
		fileopen.setVisible(false);
		/*c.insets = new Insets( 4, 4,4, 4);
		addFile = new JButton ("add another excel file");
		c.gridx=0; c.gridy=0;
		panel.add(addFile, c);
		addFile.addActionListener(listener);
		cont = new JButton("continue");
		c.gridx=0; c.gridy=1;
		cont.addActionListener(listener);
		panel.add(cont, c);*/
	}
}

public void next(){
	try{
		for (int a= 1;a<=excelPath.size();a++){	//to get the totalrows
			int rows=0;	//the number of rows in this excel document
			String path = excelPath.get(a-1);
			String lastLet = path.substring(path.length()-2, path.length());
			fileInputStream = new FileInputStream(new File(excelPath.get(a-1)));
			if(lastLet.equals("sx") || lastLet.equals("sm") || lastLet.equals("tx")|| lastLet.equals("tm") || lastLet.equals("am")){ //checking the excel version 
				XSSFWorkbook wb = new XSSFWorkbook(fileInputStream);
				XSSFSheet sheet = wb.getSheetAt(0);
			    rows=sheet.getPhysicalNumberOfRows()-2;
			    totalrows += rows;
			}
				else if (lastLet.equals("ls") || lastLet.equals("lt")){ //right now ignore..
					HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
					 HSSFSheet sheet = workbook.getSheetAt(0);
					    rows=sheet.getPhysicalNumberOfRows()-2;
					    totalrows += rows;
					}

		}

	    System.out.println("Totalrowsss " + totalrows);
	    
	for (int aa=1;aa<=excelPath.size();aa++){	//think about excelNumLeft is static so need to change..
		String path = excelPath.get(aa-1); //get the files
		String lastLet = path.substring(path.length()-2, path.length()); //get the last letter of the filepath
		//System.out.println("Lastlet: "+ lastLet);
		fileInputStream = new FileInputStream(new File(excelPath.get(aa-1))); //read this file
	    fw = new FileWriter(octavePath.octPath + "\\data_mark" + aa +".txt"); //output the file to
		output = new PrintWriter(fw);
		if(lastLet.equals("sx") || lastLet.equals("sm") || lastLet.equals("tx")|| lastLet.equals("tm") || lastLet.equals("am")) //checking the excel version 
		readFile2();
		else if (lastLet.equals("ls") || lastLet.equals("lt")) //right now ignore..
			readFile1();
		else
			JOptionPane.showMessageDialog(null, "No files support the excel reader. Please restart program and select a newer version of excel file!");
		 //excels = new RandomAccessFile("studata.txt", "rw");
	}

	combineFile(); //combine the files
	directory dir = new directory();
	dir.setVisible(true);
	setVisible(false);
	}
	 catch (IOException e) {			
		e.printStackTrace();
	}
	catch (OfficeXmlFileException e){  //cant read the file type
		e.printStackTrace();
	}
	catch (OldExcelFormatException e){ //if the Excel version too old
		JOptionPane.showMessageDialog(null, "The version of Excel is outdated. Please choose a newer version of Excel!");
	}
	//else{	//if the excel type is not applicable to the apache.poi reader
	//	 JOptionPane.showMessageDialog(null, "The version of Excel is outdated. Please choose a newer version of Excel!");
	//}

	
}
//LOOOOOOOOOOOOOOOOOOOOOOOK BELOWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW
//LOOOOOOOOOOOOOOOOOOOOOOOK BELOWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW

public void combineFile() throws IOException{		// do RAF laterr
    fw = new FileWriter(octavePath.octPath + "\\data_mark.txt"); //output the file as "data_mark" so octave can read it
	output = new PrintWriter(fw);
	for (int q=1;q<=excelPath.size();q++){
		BufferedReader buf = new BufferedReader(new FileReader(octavePath.octPath + "\\data_mark" + q +".txt")); //read this file
		String line  ;
        while ((line = buf.readLine()) != null) {   
        	//line = buf.readLine();
        	output.println(line);
          }
        buf.close();
	}
	output.close();
	fw.close();
	
}

public void readFile1() throws IOException{  //do the same as readFile2()!!!!
	//fileInputStream = new FileInputStream(new File(filePath));
	HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
	 HSSFSheet sheet = workbook.getSheetAt(0);
	 Iterator<Row> rowIterator = sheet.iterator();
	    Rows=sheet.getPhysicalNumberOfRows()-2;
	    stud_data= new String [Rows][21]; //make a totalrows x 21 size matrix
	    rowIterator.next();
		rowIterator.next();
		while (rowIterator.hasNext()) //assume the file is correctly chosen
	    {
	        Row row = rowIterator.next();
	        // Iterating through Each column of Each Row
	        for (int i=0;i<21;i++) // Checking the cell format
	        {
	        	//System.out.println("rowC: " + rowC);
	        	Cell cell = row.getCell(i);
	        	String cellValue;
	              // Checking the cell format
	        	if(cell==null || cell.getCellType()==Cell.CELL_TYPE_BLANK){
	        		cellValue="0.00";
	           	 stud_data[rowC][i]= coursNames[i-1] + "-" + cellValue;
	           	 //System.out.println(stud_data[rowC][i]);
	           	 //System.out.print(cellValue + "\t");
	        	}
	        	else {
	             if(cell.getCellType()==Cell.CELL_TYPE_STRING){
	            	 cellValue=cell.getStringCellValue();
	            	 stud_data[rowC][i]= cellValue;
	               	 //System.out.println(stud_data[rowC][i]);
	               	 //System.out.print(cellValue + "\t");
	             }
	             else if (cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
	            	 cellValue=Double.toString(cell.getNumericCellValue());
	               	 stud_data[rowC][i]= coursNames[i-1] + "-" + cellValue;
	               	// System.out.println(stud_data[rowC][i]);
	               	 //System.out.print(cellValue + "\t");
	             } 
	        	}
	        }
	        System.out.println("");
	        rowC++;
	}
	    rowC=0;
	    fileInputStream.close();

	    fw.close();
	    output.close();
	    //System.out.println(Arrays.deepToString(stud_data));
}
public void readFile2() throws IOException{
	//fileInputStream = new FileInputStream(new File(excelPath.get(excelNumLeft-1)));
	XSSFWorkbook wb = new XSSFWorkbook(fileInputStream);
    XSSFSheet sheet = wb.getSheetAt(0);
    Iterator<Row> rowIterator = sheet.iterator();
    Rows=sheet.getPhysicalNumberOfRows()-2;
    //totalrows += Rows;
    stud_data= new String [1][21]; //make a totalrows x 21 size matrix
    rowIterator.next();
	rowIterator.next();
    while (rowIterator.hasNext()) //assume the file is correctly chosen
    {
        Row row = rowIterator.next();
        // Iterating through Each column of Each Row
        for (int i=0;i<21;i++) // Checking the cell format
        {
        	Cell cell = row.getCell(i);
        	String cellValue;
              // Checking the cell format
        	if(cell==null || cell.getCellType()==Cell.CELL_TYPE_BLANK){
        		cellValue="0.00";
           	 stud_data[0][i]= coursNames[i-1] + "-" + cellValue;

        	}
        	else {
             if(cell.getCellType()==Cell.CELL_TYPE_STRING){
            	 cellValue=cell.getStringCellValue();
            	 stud_data[0][i]= cellValue;

             }
             else if (cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
            	 cellValue=Double.toString(cell.getNumericCellValue());
               	 stud_data[0][i]= coursNames[i-1] + "-" + cellValue;
 
             } 
        	}
        }
       succ_crit succrit = new succ_crit(stud_data, i);
       succrit.checkSuccess();
       i++;
		double EllSc=Double.parseDouble((stud_data[0][1]).substring(stud_data[0][1].length()-4));
		double Math10=Double.parseDouble((stud_data[0][13]).substring(stud_data[0][13].length()-4));
		double Math103=Double.parseDouble((stud_data[0][14]).substring(stud_data[0][14].length()-4));
		double Math201=Double.parseDouble((stud_data[0][15]).substring(stud_data[0][15].length()-4));
		double Math202=Double.parseDouble((stud_data[0][16]).substring(stud_data[0][16].length()-4));
		double Math203=Double.parseDouble((stud_data[0][17]).substring(stud_data[0][17].length()-4));
		double Sc14=Double.parseDouble((stud_data[0][5]).substring(stud_data[0][5].length()-4));
		double Sc10=Double.parseDouble((stud_data[0][2]).substring(stud_data[0][2].length()-4));
		if (EllSc>0 && (Math10>0||Math103>0||Math201>0||Math202>0||Math203>0)){ //if the student took a math course and ELLScience
			output.print(EllSc +",");
			if(Math10>0){ 	//if the student took math10
				output.print(Math10+",");
			}
			else if (Math10==0){	//if the student did not take math10 but took another math course
				if(Math103>0)
					output.print(Math103+",");
				else if (Math201>0)
					output.print(Math201+",");
				else if (Math202>0)
					output.print(Math202+",");
				else if (Math203>0)
					output.print(Math203+",");
			}

			if (Sc10>0){	//if the student took Sc10, and 1 being true
				if(Sc14>0) //if the student took Sc14 during their second semester, 0 being false
					output.println("0");
				else
					output.println("1");
			}
			else
				output.println("0"); //if the student did not take Sc10, and 0 being false
		}
}
    fileInputStream.close();
    fw.close();
    output.close();

    
}
}
/*
 * if want to do inserting data, just read the file and then store it.. 
 * so store 1 line then outputtt.. but do that after the octave file
 * make loop on the read file thing
 * 
 * YYY output student record to octave txt
 
	if want to do RAF, success will be put into RAF and read.


*/
