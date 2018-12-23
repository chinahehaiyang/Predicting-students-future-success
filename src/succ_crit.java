package IA;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class succ_crit{
	private String studdata[][];
	public static String success [][] = new String [read_data.totalrows][3];
	private int x;
	public succ_crit (String[][] data, int a){
		studdata = data;
		x=a;
	}
	public int checkSuccess(){ 
			if (x<=read_data.totalrows){
			success[x][0]=studdata[0][0]; 	//store the name 
			double Sc20 = Double.parseDouble((studdata[0][3]).substring(studdata[0][3].length()-4)); 
			double Sc24 = Double.parseDouble((studdata[0][6]).substring(studdata[0][6].length()-4));
			double Sc30 = Double.parseDouble((studdata[0][4]).substring(studdata[0][4].length()-4));
			double Bio30 = Double.parseDouble((studdata[0][8]).substring(studdata[0][8].length()-4));
			double Chem30=Double.parseDouble((studdata[0][10]).substring(studdata[0][10].length()-4));
			double Phy30=Double.parseDouble((studdata[0][12]).substring(studdata[0][12].length()-4));
			//if passed 20lv course
			if (((Sc20>=50) || (Sc24>=50)) || (((Sc20==0)) && ((Sc24)==0))){
				////if passed 30lv course
				if ((Sc30>=50) || (Bio30>=50)||(Chem30>=50)||(Phy30>=50)){
					//if the student got at least one 30lv course above 75%
					if ((Sc30>=75) || (Bio30>=75)||(Chem30>=75)||(Phy30>=75)){
						// check if a student got >75% on one 30lv course but not the other
						if ((Sc30<75 && Sc30>0) || (Bio30<75 && Bio30>0)||(Chem30<75 && Chem30>0)||(Phy30<75 && Phy30>0)){
							success[x][1]= "Moderately Successful";
							success[x][2]="3";return 0;
						}
						else
						success[x][1]= "Maximally Successful";
						success[x][2]="4";return 0;
					}
					else {
						success[x][1]= "Moderately Successful";
						success[x][2]="3";return 0;
					}
				}
				else{//if did not pass 30lv course
					success[x][1]= "Minimally Successful";
					success[x][2]="2";return 0;
				}
			}
			else{ //if did not pass 20lv course
				success[x][1]= "Not Successful";
				success[x][2]="1";return 0;
			}
		}
			else {
				return 0;
			}
	}	


	public void outputSuc() throws IOException{ 
		FileWriter fw = new FileWriter(octavePath.octPath + "\\data_mark.txt");
		PrintWriter out = new PrintWriter(fw);
		for (int xx=0;xx<studdata.length;xx++){
		double EllSc=Double.parseDouble((studdata[xx][1]).substring(studdata[xx][1].length()-4));
		double Math10=Double.parseDouble((studdata[xx][13]).substring(studdata[xx][13].length()-4));
		double suc = Double.parseDouble(success[xx][2]);
		if(suc<=2 && suc>0)
			out.println("0");
		else
			out.println("1");
			
		}
		out.close();
		fw.close();
	}

}

/*
 * 		if (EllSc>0 && Math10>0 ){
			out.print(EllSc +","+Math10+",");
			if(suc<=2 && suc>0)
				out.println("0");
			else
				out.println("1");
		}
		
		
		
		
		//FileWriter fw = new FileWriter(octavePath.octPath + "\\data_mark.txt");
 */

