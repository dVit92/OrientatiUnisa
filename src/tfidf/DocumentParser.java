/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tfidf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to read documents
 *
 * @author Mubin Shrestha
 */
/** Gestiamo le materie singole  **/
public class DocumentParser {

    //This variable will hold all terms of each document in an array.
    private List<String[]> termsDocsArray = new ArrayList<String[]>();
    private List<double[]> tfidfDocsVector = new ArrayList<double[]>();
    private File[] allfiles;
    
    
    public DocumentParser(String filePath){
        try {
			parseFiles(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Method to read files and store in array.
     * @param filePath : source file path
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void parseFiles(String filePath) throws FileNotFoundException, IOException {
        allfiles = new File(filePath).listFiles();
        BufferedReader in = null;
       

        for (File f : allfiles) {
            if (f.getName().endsWith(".txt")) {
                in = new BufferedReader(new FileReader(f));
                StringBuilder sb = new StringBuilder();
                String s = null;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                }
                String[] tokenizedTerms = sb.toString().replaceAll("[\\W&&[^\\s]]", " ").split("\\W+");   //to get individual terms
                termsDocsArray.add(tokenizedTerms);
            }
        }
    }
    
    public List<String[]> getTerms(){
    	
    	return termsDocsArray;
    }

    /**
     * Method to create termVector according to its tfidf score.
     * @throws IOException 
     * @throws FileNotFoundException 
     */

	public double[] tfIdfCalculator(String filePath, List<String> allTerms) throws FileNotFoundException, IOException {
        double[] tf = new double[allfiles.length]; //term frequency 
        int i = 0;
        for (String[] docTermsArray : termsDocsArray) {
			for (String terms : allTerms) {
				tf[i] += new TfIdf().tfCalculator(docTermsArray, terms);
           }
			i++;
        }
        return tf;	
    }

    /**
     * Method to calculate cosine similarity between all the documents.
     */
    public void getCosineSimilarity() {
        for (int i = 0; i < tfidfDocsVector.size(); i++) {
            for (int j = 0; j < tfidfDocsVector.size(); j++) {
                System.out.println("between " + i + " and " + j + "  =  "
                                   + new CosineSimilarity().cosineSimilarity
                                       (
                                         tfidfDocsVector.get(i), 
                                         tfidfDocsVector.get(j)
                                       )
                                  );
            }
        }
    }
    
}
