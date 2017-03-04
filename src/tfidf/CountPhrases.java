package tfidf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Calculate TF of multiple-word subjects 
 * @authors Daniele Vitale, Marianna di Gregorio, Alessandra Orsi
 *
 */
public class CountPhrases {
	
	

	 public static double[] parseFiles2 (String filePath,List<String> materieComposte) throws FileNotFoundException, IOException {
		 File[] allfiles = new File(filePath).listFiles();
	     BufferedReader in = null;
	     DocumentParser p= new DocumentParser(filePath);
	     int kk=0;
	     List<String[]> list = p.getTerms();
	     double toRet[] = new double[allfiles.length];
	     
	     
	     for (File f : allfiles) {
	            if (f.getName().endsWith(".txt")) {
	                in = new BufferedReader(new FileReader(f));
	                String s = null;
	                String input="";
	                while ((s = in.readLine()) != null) {
	                    input+=s;
	                }
	                
	                String[] split = input.split(" ");
	                if((split.length>1)){
		                Map<String, Integer> counts = new HashMap<String,Integer>(split.length*(split.length-1)/2,1.0f);
		                int idx0 = 0;
			                for(int i=0; i<split.length-1; i++){
			                    int splitIpos = input.indexOf(split[i],idx0);
			                    int newPhraseLen = splitIpos-idx0+split[i].length();
			                    String phrase = input.substring(idx0, idx0+newPhraseLen);
			                    for(int j=i+1; j<split.length; j++){
			                        newPhraseLen = phrase.length()+split[j].length()+1;
			                        phrase=input.substring(idx0, idx0+newPhraseLen);
			                        Integer count = counts.get(phrase);
			                        if(phrase.length()<=30){
			                        	if(count==null){
			                             counts.put(phrase, 1);
			                        	} else {
			                             counts.put(phrase, count+1);
			                        	}
			                        }	
			                    }
			                    idx0 = splitIpos+split[i].length()+1;
			                }
		                
		                @SuppressWarnings("unchecked")
						Map.Entry<String, Integer>[] entries =  counts.entrySet().toArray(new Map.Entry[0]);
		                Arrays.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
		                    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
		                        return o2.getValue().compareTo(o1.getValue());
		                    }
		                });
		                
		                double[] somma= new double[materieComposte.size()];
		                int j;
		                for(Map.Entry<String,Integer> entry:entries){
		                    double count=entry.getValue();
		                    j = 0;
		                    for(String st: materieComposte){			//Questo ciclo è per sommare i tf delle parole ignorando maiuscole e minuscole
			                    if(entry.getKey().equalsIgnoreCase(st)){
			                    	somma[j]+=count;
			                    }
			                    j++;
		                    }
		                }
		                for(int i = 0; i<somma.length; i++)
		                	toRet[kk]+=somma[i]/list.get(kk).length; // calcolo il TF
	                	toRet[kk] = toRet[kk]/somma.length;
	                }
	            }  
	            kk++;
	     }
	     return toRet;
	 }

}
