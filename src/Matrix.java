
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ashish
 */
public class Matrix {
    int[][] trArray = new int[700][21];
    int[][] r = new int[700][21];
    int nCol = 19;    
    private int tr0,tr1;
    Matrix(){
        readFromTrainingFile();
    }
    public int rlength(){
        //returns the no. of rows of 2-d array
       return 0; 
    }
    public int clength(){
        //returns the no. of columns of 2-d array
       return 0; 
    }
    
    double calculateGain(int a, double ent){
        double ones =0, zeroes = 0, o = 0, z = 0, t = 2, total,total1, fract, ones1 = 0, zeroes1 = 0;
        for(int i=0;i<=7;i++){
            if(r[i][a] == 0)
                z++;
            else
                o++;
        }
        fract = z/(o+z);
        for(int i=0;i<=7;i++){
            if(r[i][a] == 0){
                if(r[i][4] == 1)
                    ones++;
                else
                    zeroes++;
            }
            if(r[i][a] == 1){
                if(r[i][4] == 1)
                    ones1++;
                else
                    zeroes1++;
            }
        }
        total = ones + zeroes;
        total1 = ones1 + zeroes1;
        if(ones == 0)
            ones = total;
        if(zeroes == 0)
            zeroes = total;
        if(ones1 == 0)
            ones1 = total1;
        if(zeroes1 == 0)
            zeroes1 = total1;
        double e1 = fract * ((ones/total)*(Math.log(ones/total)/Math.log(t)) + 
                (zeroes/total)*(Math.log(zeroes/total)/Math.log(t)));
        double e2 = (1-fract)*((ones1/total1)*
                (Math.log(ones1/total1)/Math.log(t)) + 
                (zeroes1/total1)*(Math.log(zeroes1/total1)/Math.log(t)));
        double gain = ent + e1 + e2;
        return gain;
    }
    
    int calculateEntropy(){
        double ones = 0, zeroes = 0, t = 2, gain;
        int attribute = -1;
        for(int i = 0; i<= 7; i++){
            if(trArray[i][nCol] == 1)
                ones++;
            else
                zeroes++;
        }
        double total = ones + zeroes;
        double ent =  -(((ones/total) * Math.log(ones/total))/Math.log(t) + ((zeroes/total) * Math.log(zeroes/total))/Math.log(t));
        System.out.println(ent);
        double maxGain = -3;
        for(int i = 0; i<=(nCol-1); i++){
            gain = calculateGain(i,ent);
            System.out.println(gain);
        if(maxGain < gain){
            maxGain = gain;
            attribute = i;
        }
        
        }
       
        System.out.println(attribute);
        for(int i=0;i<tr1;i++){
                    for(int k=0;k<=nCol;k++)
                    System.out.print(trArray[i][k]);
                   System.out.println();
                }
        
//        createNode(attribute);
        return attribute;
    }
    
    void getDataSet(int attribute, int q){
        
        int one=0,zero=0,g=0,h=0,i;
        for ( i = 0,g=0,h=0;i<=tr0;i++){
            if(r[i][attribute]==q)
                for(int k=0,j=0;k<=nCol;k++){
                
                    if(k!=attribute){
                        trArray[g][j]=r[i][k];
                        j++;
                        zero=1;
                    }
                }
           if(one==1){
                h++;
                one=0;
            }
            if(zero==1){
                g++;
                zero=0;
            }
        }
        tr0=g;
//        tr1=h;
//        return trArray;
    
    }
    private void readFromTrainingFile(){
        //reads from training csv file and stores it in 2-d array of size 700x700
        String csvFile = "C:\\Users\\Ashish\\Documents\\ML_set1\\test_set.csv";
	
        BufferedReader br = null;
	String line = "";
	String csvSplitBy = ",";
        int j=0;

	try {

		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {

		        // use comma as separator
			String[] trainingStr = line.split(csvSplitBy);
                        j++;
                    if(j>1){
                            for(int i=0;i<trainingStr.length;i++){
                                trArray[j-2][i] = Integer.parseInt(trainingStr[i]);
                            }
                        }
                }
                for(int i=0;i<=7;i++){
                    for(int k=0;k<=4;k++)
                    System.out.print(trArray[i][k]);
                   System.out.println();
                }
                r=trArray.clone();
	} catch (FileNotFoundException e) {
	} catch (IOException e) {
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
                        }
		}
	}	
    }
}
