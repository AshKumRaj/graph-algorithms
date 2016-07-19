
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ashish
 */
public class LogisticRegression {
    String fPath;
    List<String> filterStr = new ArrayList<>();	
    boolean isStopWord = false,testing = false;

    public void setIsStopWord(boolean isStopWord) {
        this.isStopWord = isStopWord;
    }
    Set<String> stopWord = new HashSet<>();
    ArrayList<HashMap <String, Integer>> myList = new ArrayList<HashMap <String, Integer>>(1000);
    int ind = 0,ham = 0,spam=0;;
    HashMap total = new HashMap();
    HashMap weightMap = new HashMap();
    HashMap testMap = new HashMap();
    HashMap probMap = new HashMap();
    HashMap dwMap = new HashMap();
    
    public static void main(String[] args) throws IOException  {
        LogisticRegression l = new LogisticRegression();
        l.readAllTextFiles("C:\\Users\\Ashish\\Documents\\ML2\\train\\ham");
        l.readAllTextFiles("C:\\Users\\Ashish\\Documents\\ML2\\train\\spam");
        l.initLR();
        l.initTestLR();
        System.out.println("With Stopword excluded:");
        LogisticRegression l2 = new LogisticRegression();
        l2.setIsStopWord(true);
        l2.getStopWordList();
        l2.readAllTextFiles("C:\\Users\\Ashish\\Documents\\ML2\\train\\ham");
        l2.readAllTextFiles("C:\\Users\\Ashish\\Documents\\ML2\\train\\spam");
        l2.initLR();
        l2.initTestLR();
    }
    
    void readAllTextFiles(String folderPath) throws IOException{
        fPath = folderPath;
        File f = new File(folderPath);//"C:\\Users\\Ashish\\Documents\\ML2\\train\\ham"
//        File f = new File("C:\\Users\\Ashish\\Documents\\ML2\\train\\ham");//"C:\\Users\\Ashish\\Documents\\ML2\\train\\ham"
        FilenameFilter textFilter;
        textFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".txt");
            }
        };

        File[] files = f.listFiles(textFilter);
        for (File file : files) {
//            System.out.println(file.getPath());
            extractWordsFromFile(file.getPath());
        }

    }

    private void extractWordsFromFile(String path) throws FileNotFoundException, IOException {
        String csvSplitBy = " ";
        for(String line : Files.readAllLines(Paths.get(path) , StandardCharsets.ISO_8859_1)){		
            for(String s : line.split(csvSplitBy)){
                Pattern p = Pattern.compile("[^A-Za-z0-9]");
                Matcher m = p.matcher(s);
                boolean b = m.find();
                if (b == false){
                    if(fPath.contains("train")){
                        filterStr.add(s);
                        if(isStopWord)
                            if(stopWord.contains(s))
                                filterStr.remove(s);
                        
                    }
                    else {
                        filterStr.add(s);
                        if(isStopWord)
                            if(stopWord.contains(s))
                                filterStr.remove(s);
                    }
                       
                }
            }
      }
        Iterator i = filterStr.listIterator();
        HashMap<String,Integer> myMap1 = new HashMap<String, Integer>();
        while(i.hasNext()){
            String k = (String) i.next();
            if(myMap1.containsKey(k))
                myMap1.replace(k, myMap1.get(k)+1);
            else
                myMap1.put(k, 1);
            if(!testing)
                weightMap.put(k, 0.1);        //initialize all weights to 1
        }
//        System.out.println(weightMap);
        myList.ensureCapacity(1000);
        myList.add(ind, myMap1);
        probMap.put(ind, 0.5);      //initialize all Pr[i] to 0.5
        ind++;
//        System.out.println(myMap1);
        total.putAll(myMap1);
        
//        myMap1.clear();
        filterStr.clear();
        
    }
    
    public void initLR(){
        dwMap.putAll(weightMap);
        dwMap.replaceAll((k,v) -> 0);
//        System.out.println(myList);
        for(int i = 0;i<100;i++){
            calculateProbMap(i);
            System.out.println(probMap);
            calculatedWMap();
            calculateWeightMap(i);
        }
//        System.out.println(weightMap);
    }

    public void getStopWordList() throws IOException{
        String csvFile = "C:\\Users\\Ashish\\Documents\\ML2\\stopword.txt";
	
        BufferedReader br = null;
	String line = "";
	String csvSplitBy = " ";
        int j=0;

	try {

		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {

		        // use comma as separator
                    String[] trainingStr = line.split(csvSplitBy);
                    stopWord.addAll(Arrays.asList(trainingStr));        //note this step. Good method to convert into arraylist
                    
                }
                
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

    private void calculateProbMap(int i) {
        int index = 0;
        while(index!=ind){
            double theta = summation(index,i);
            double prob = sigmoid(theta);
            probMap.replace(index, prob);
            index++;
        }
        
    }

    private double summation(int index,int count) {
        double sum = 0,n;
        Set s = weightMap.entrySet();
        Iterator i = s.iterator();
        HashMap<String, Integer> tmpData = (HashMap<String, Integer>) myList.get(index);
        while(i.hasNext()){
            Map.Entry m = (Map.Entry) i.next();
            String k = (String) m.getKey();
            if(tmpData.containsKey(k))    
                n=tmpData.get(k);
            else 
                n = 0;
            double p = 0;
            if(count==0)
                p = (double)m.getValue()*n;
            else
                p = (double)m.getValue()*n;
            sum+=p;
        }
        return sum+1;
    }

    private double sigmoid(double theta) {
        if(theta>=100)
            return 1;
        if(theta<-100)
            return 0;
        theta*=-1;
        double num = Math.exp(theta);
        return num/(1.0+num);
    }

    private void calculatedWMap() {
        //initialize dwMap
        dwMap.replaceAll((k,v)->0);
        //now calculate dw
        Set s = weightMap.entrySet();
        
        Iterator it = s.iterator();
        while(it.hasNext()){
            Map.Entry m = (Map.Entry) it.next();
            String k = (String) m.getKey();
            
                
            int index = 0;
            double dw = 0.0;
            while(index!=ind){
                HashMap<String, Integer> tmpData = (HashMap<String, Integer>) myList.get(index);
                int dataji = 0,datajn = 1;
                if(tmpData.containsKey(k)){
                    dataji = tmpData.get(k);
                }
                if(index>=340)
                    datajn = 0;
                double prj = (double) probMap.get(index);
                if (index == 0){
                    dw = dw + (double)(dataji*(datajn-prj));
                }
                else
                    dw = dw + (double)(dataji*(datajn-prj));
                
                
                index++;
            }
            dwMap.replace(k, dw);
        }
//        System.out.println(dwMap);
    }

    private void calculateWeightMap(int count) {
        double neta = 0.05, lambda = 0.005;
        double wi = 0.0;
        Set s = weightMap.entrySet();
        Iterator it = s.iterator();
        while(it.hasNext()){
            Map.Entry m = (Map.Entry) it.next();
            String k = (String) m.getKey();
            if(count == 0)
                wi = (double)m.getValue() + (double)neta*((double)dwMap.get(k) - lambda*(double)m.getValue());
            else
                wi = (double)m.getValue() + (double)neta*((double)dwMap.get(k) - lambda*(double)m.getValue());
            weightMap.replace(k, wi);
        }
//        System.out.println(weightMap);
    }
    
    public void initTestLR() throws IOException{
        testing = true;
        ind = 0;
        myList.clear();
        probMap.clear();
        readAllTextFilesTest("C:\\Users\\Ashish\\Documents\\ML2\\test\\ham");
        double acc = (ham/348d) *100;
        int sp = spam,h = ham;
//        System.out.println("Ham="+ham+"\nSpam="+spam+" Ham Accuracy="+acc);
        
        readAllTextFilesTest("C:\\Users\\Ashish\\Documents\\ML2\\test\\spam");
        acc = (spam - sp)/130d *100;
//        System.out.println("Ham="+ham+"\nSpam="+spam+" Spam accuracy="+acc);
        acc = (h+spam-sp)/478d * 100;
        System.out.println("Overall accuracy="+acc);
        
        myList.clear();
        
    }

    private void readAllTextFilesTest(String folderPath) throws IOException {
        fPath = folderPath;
        File f = new File(folderPath);//"C:\\Users\\Ashish\\Documents\\ML2\\train\\ham"
//        File f = new File("C:\\Users\\Ashish\\Documents\\ML2\\train\\ham");//"C:\\Users\\Ashish\\Documents\\ML2\\train\\ham"
        FilenameFilter textFilter;
        textFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".txt");
            }
        };

        File[] files = f.listFiles(textFilter);
        for (File file : files) {
//            System.out.println(file.getPath());
            extractWordsFromFileTest(file.getPath());
        }
    }

    private void extractWordsFromFileTest(String path) throws IOException {
        String csvSplitBy = " ";
        for(String line : Files.readAllLines(Paths.get(path) , StandardCharsets.ISO_8859_1)){		
            for(String s : line.split(csvSplitBy)){
                Pattern p = Pattern.compile("[^A-Za-z0-9]");
                Matcher m = p.matcher(s);
                boolean b = m.find();
                if (b == false){
                    if(fPath.contains("train")){
                        filterStr.add(s);
                        if(isStopWord)
                            if(stopWord.contains(s))
                                filterStr.remove(s);
                        
                    }
                    else {
                        filterStr.add(s);
                        if(isStopWord)
                            if(stopWord.contains(s))
                                filterStr.remove(s);
                    }
                       
                }
            }
      }
        Iterator i = filterStr.listIterator();
        HashMap<String,Integer> myMap1 = new HashMap<String, Integer>();
        while(i.hasNext()){
            String k = (String) i.next();
            if(myMap1.containsKey(k))
                myMap1.replace(k, myMap1.get(k)+1);
            else
                myMap1.put(k, 1);
            
        }
//        System.out.println(weightMap);
        ind++;
        testMap.putAll(myMap1);
        //create separate probability calculatoin method for this
        calculateTestProb();
        testMap.clear();
        filterStr.clear();
    }

    private void calculateTestProb() {
        Set s = weightMap.entrySet();
        double sum = 0.0,w;
        int x = 0;
        Iterator i = s.iterator();
        while(i.hasNext()){
            Map.Entry m = (Map.Entry) i.next();
            if(testMap.containsKey(m.getKey()))
                x = (int)testMap.get(m.getKey());
            w = (double)m.getValue();
            sum+=w*x;
        }
        double sig = sigmoid(sum+1);//TO DO:: create new sigmoid using log. Prob values are underflowing.
        if(sig>=0.5)
            ham++;
        else
            spam++;
    }
}

