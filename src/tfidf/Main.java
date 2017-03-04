package tfidf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import nodeList.Position;
import ontologyTree.OntTree;
import tree.Tree;
import tree.TreePosition;

/**
 * Create the file TFMATERIE.csv
 * @authors Daniele Vitale, Marianna di Gregorio, Alessandra Orsi
 *
 */
public class Main {
	
	public static void main(String[] arg) throws IOException{
		
		String filePath = "/Users/Alessandra/Desktop/Informatica/Intelligenza Artificiale/Progetto IA/Corsi_di_Laurea";
		
		File[] files = new File(filePath).listFiles();
		String [] allfiles = new String[files.length];
		for(int i = 0; i<files.length;i++){
			 allfiles[i] = files[i].getPath().split("/")[8];
		} 
		OntTree ot = new OntTree();
		Tree<String> ontology = ot.createTree("/Users/Alessandra/Desktop/Informatica/Intelligenza Artificiale/Progetto IA/Ontologia.owl");
		DocumentParser dp = new DocumentParser(filePath);
		TreePosition<String> root = (TreePosition<String>) ontology.root();
		double[] tfComp = new double[allfiles.length];
		double[] tfSempl = new double[allfiles.length];
		double[] tf = new double[allfiles.length];
		List<String> materieComposte = new ArrayList<String>();
		List<String> materieSemplici = new ArrayList<String>();
		

		FileOutputStream f = new FileOutputStream("TFMATERIE2.csv");
		PrintStream ps = new PrintStream(f);
		
		for(Position<String> node: root.getChildren()){
			materieComposte.clear();
			materieSemplici.clear();
			
			String split[] = node.element().split(" ");
			if(split.length>1)
				materieComposte.add(node.element());
			else
				materieSemplici.add(node.element());
			if(ontology.isInternal(node)){
				for(Position<String> leaf: ontology.children(node)){
					split = leaf.element().split(" "); 
					if(split.length>1)
						materieComposte.add(leaf.element());
					else
						materieSemplici.add(leaf.element());
				}
			}
			
			System.out.println(node.element());
			if(materieSemplici.size()>0){
				tfSempl = dp.tfIdfCalculator(filePath, materieSemplici);
			}/*
			if(materieComposte.size()>0){
				tfComp = CountPhrases.parseFiles2(filePath, materieComposte);
			}*/
			for(int i = 0; i<tfComp.length;i++)
				tf[i] = (tfComp[i]+tfSempl[i])/2;

			for(int i = 0; i < tf.length;i++) 
				System.out.println(allfiles[i] + " " + tf[i]);
			
			ps.print(node.element()+": ");
			for(int j = 0; j< tf.length;j++){
				ps.print(tf[j] + "---");
			}
			ps.println();
		}
		ps.close();
		
		
		
	}
}
