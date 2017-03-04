package ontologyTree;

import java.io.InputStream;
import java.util.Iterator;


import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

import nodeList.Position;
import tree.*;

/**
 * Create a tree starting by an ontology.
 * @authors Daniele Vitale, Marianna di Gregorio, Alessandra Orsi
 *
 */

public class OntTree {
		
	LinkedTree<String> ontology;
	
	public OntTree(){ontology = new LinkedTree<String>();}
	
	public String cutSharp(String URI){
		String[] split = URI.split("#");
		return split[1].replace("_", " ");
	}

	public Tree<String> createTree(String filePath){
		
		OntModel model = ModelFactory.createOntologyModel();
		InputStream in = FileManager.get().open(filePath);
		if (in == null) {
			throw new IllegalArgumentException(
			"File: " + filePath + " not found");
		}
		// read the RDF/XML file
		model.read(in, "");
		
		OntClass materie = (OntClass)model.listHierarchyRootClasses().next();
		ontology.addRoot(cutSharp(materie.getURI()));
		Iterator<OntClass> it = materie.listSubClasses(true);
		Position<String> pos;
		while(it.hasNext()){
			String s = it.next().getURI();
			OntClass c = model.getOntClass(s);			
			pos = ontology.addChild(cutSharp(s), ontology.root());
			Iterator<OntClass> its = c.listSubClasses(true);
			while(its.hasNext())
				ontology.addChild(cutSharp(its.next().getURI()), pos);
			
		}
		return ontology;
	}
	
	public static void main(String[] args){
		

		OntTree ot = new OntTree();
		Tree<String> ontology = ot.createTree("/Users/Alessandra/Desktop/Informatica/Intelligenza Artificiale/Progetto IA/Ontologia.owl");
		
		TreePosition<String> root = (TreePosition<String>) ontology.root();
		
		
		for(Position<String> node: root.getChildren()){
			System.out.println(node.element()+":");
			if(ontology.isInternal(node))
				for(Position<String> leaf: ontology.children(node))
					System.out.println("\t\t"+leaf.element());
			else 
				System.out.println("\t\t/////");
		}
		
		
		System.out.println("Fine");
	}
}
