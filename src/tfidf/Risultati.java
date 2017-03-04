package tfidf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

/**
 * Calculate the result of the three section of the questionnaire 
 * @authors Daniele Vitale, Marianna di Gregorio, Alessandra Orsi
 *
 */

public class Risultati {
	
	private ArrayList<Double> fine3;
	private Map<String, Double> mappa;
	private ArrayList<Double> fineTerza;
	private Map<String, Double> fineSeconda;


	public void primaParte() throws IOException{
		
		BufferedReader buffer = new BufferedReader(new FileReader("/Users/Alessandra/Desktop/Informatica/Intelligenza Artificiale/Progetto IA/OrientatiUnisa.csv"));
		BufferedReader ramo = new BufferedReader(new FileReader("/Users/Alessandra/Desktop/Informatica/Intelligenza Artificiale/Progetto IA/Rami.txt"));
		ArrayList<String> rami = new ArrayList<String>();
		dictionary.Dictionary<String, Double> dizionario = new dictionary.LogFile<String, Double>();	
		double gradimento=0;
		double scientifico=0;
		double umanistico=0;
		
		buffer.readLine();
		buffer.readLine();
		


		String r= "";

		String answer= buffer.readLine();
		answer= answer.replaceAll(",", " ");
		String[] split1 = answer.split("\"");
		System.out.println("Utente: " + split1[5]);

		while((r=ramo.readLine())!=null){

			rami.add(r);
		}
		
		int j=0;
		for(int i=17; i<36; i=i+2){
			if(split1[i].equals("Poco")){
				gradimento=0.30;
			}
			else if(split1[i].equals("Abbastanza")){
				gradimento=0.60;
			}
			else if(split1[i].equals("Molto")){
				gradimento=0.80;
			}
			else if(split1[i].equals("Moltissimo")){
				gradimento=1;
			}
			
			dizionario.insert(rami.get(j), gradimento);
			j++;
		}
		
		
		for(Entry<String, Double> e: dizionario.entries()){
			if(e.getKey().equals("Scientifico")){
				scientifico+=e.getValue();
			}
			else if(e.getKey().equals("Umanistico")){
				umanistico+=e.getValue();
			}
		}
		
		scientifico= (scientifico/(scientifico+umanistico))*100;
		umanistico= 100-scientifico;
		System.out.format("Predisposizione ramo scientifico: %.2f%s  Predisposizione ramo umanistico: %.2f%s\n", scientifico,"%",umanistico,"%");
		
		if(scientifico > umanistico){
			System.out.println("Sei predisposto per il ramo scientifico!");
		}
		else{
			System.out.println("Sei predisposto per il ramo umanistico!");
		}
		
		buffer.close();
		ramo.close();
		
	}

	


	public void secondaParte() throws IOException{
		
		BufferedReader risp = new BufferedReader(new FileReader("/Users/Alessandra/Desktop/Informatica/Intelligenza Artificiale/Progetto IA/OrientatiUnisa.csv"));
		BufferedReader corsi= new BufferedReader(new FileReader("/Users/Alessandra/Desktop/Informatica/Intelligenza Artificiale/Progetto IA/Facoltˆ.txt"));
		ArrayList<String> corsiLaurea = new ArrayList<String>();
		mappa = new TreeMap<String, Double>();
		fineSeconda = new TreeMap<String, Double>();
		double stella=0;

		risp.readLine();
		risp.readLine();
		


		String risposta= risp.readLine();
		risposta = risposta.replaceAll(","," ");
		String[] split2 = risposta.split("\"");
		String s= "";

		while((s=corsi.readLine())!=null){

			corsiLaurea.add(s);
		}


		int j=0;
		for(int i=37; i<116; i= i+2){
			if(split2[i].equals("â˜…")){
				stella=0.2;

			}
			else if(split2[i].equals("â˜…â˜…")){
				stella=0.4;

			}
			else if(split2[i].equals("â˜…â˜…â˜…")){
				stella=0.6;

			}
			else if(split2[i].equals("â˜…â˜…â˜…â˜…")){
				stella=0.8;

			}
			else if(split2[i].equals("â˜…â˜…â˜…â˜…â˜…")){
				stella=1;

			}


			mappa.put(corsiLaurea.get(j), stella);
			j++;
		}

		
		// calcolo il max //
		ArrayList<Double> valoriSecondaParte= new ArrayList<Double>();
		double massimo=0;
		for(java.util.Map.Entry<String, Double> en : mappa.entrySet()){
			valoriSecondaParte.add(en.getValue());
		}
		massimo= Collections.max(valoriSecondaParte);
		
		// aggiorno la mappa //
		for(java.util.Map.Entry<String, Double> x : mappa.entrySet()){
			fineSeconda.put(x.getKey(), x.getValue()/massimo);
		}
		
		System.out.println("Risultati seconda parte: " + fineSeconda);

		
	
		// valori pi� alti per la seconda parte //	
		/*System.out.println("Valori pi� alti della seconda parte: ");
		for(java.util.Map.Entry<String, Double> e : mappa.entrySet()){
			if(e.getValue()==1){
				System.out.println(e.getKey() + " " + e.getValue());
			}
		}*/
		risp.close();
		corsi.close();

	}


	public void terzaParte() throws IOException{

		BufferedReader risp = new BufferedReader(new FileReader("/Users/Alessandra/Desktop/Informatica/Intelligenza Artificiale/Progetto IA/OrientatiUnisa.csv"));
		BufferedReader tf = new BufferedReader(new FileReader("/Users/Alessandra/Documents/workspace_IA/ProgettoIA/TFMATERIE2.csv"));

		String materia;
		String s;
		Map<String, Integer> questionario = new HashMap<String, Integer>();
		Map<String, ArrayList<Double>> TF = new HashMap<String, ArrayList<Double>>();

		materia = risp.readLine();
		risp.readLine();
		

		

		s = risp.readLine();
		s = s.replaceAll(",", " ");
		materia = materia.replaceAll(",", " ");
		String[] split = s.split("\"");
		String[] materie = materia.split("\"");

		//insert all entries <materia-risposta> in a map
		for(int i = 117; i<materie.length; i = i + 2){
			questionario.put(materie[i],Integer.parseInt(split[i]));
		}


		//insert all <materia-tf> in a second map
		while((materia = tf.readLine()) != null){
			split = materia.split("---|:");
			ArrayList<Double> sommatf= new ArrayList<Double>();

			for(int i = 1; i<split.length;i++){

				//sommatf[i-1] = Double.parseDouble(split[i]);
				sommatf.add(Double.parseDouble(split[i]));

			}

			TF.put(split[0], sommatf);
		}





		ArrayList<Double> aggiorno = new ArrayList<Double>();

		for(String sa:questionario.keySet()){

			if(TF.containsKey(sa)){

				int valore=questionario.get(sa);
				ArrayList<Double> dd=TF.get(sa);
				for(Double xx:dd){
					// System.out.println(sa +" "+valore+"*"+xx+"="+valore*xx);
					double l= (valore*xx)/10.0; 
					aggiorno.add(l);
				}
			}
		}
		
		fine3= new ArrayList<Double>();


		int i=0;
		double sum = 0;
		while(i!=40){
			for(int k=i; k<=aggiorno.size()-1; k+=40){
				sum+=aggiorno.get(k);
			}
			fine3.add(sum);
			i++;
			sum=0;
		}


		// valore pi� alto per la terza parte //
		double maxi=0;
		maxi= Collections.max(fine3);
		//System.out.println("Risultato pi� alto: " + Collections.max(fine3));
		
		// aggiorno fine3 //
		fineTerza = new ArrayList<Double>();
		for(Double d : fine3){
			fineTerza.add(d/maxi);
		}
		System.out.println("Risultati terza parte: " + fineTerza.toString());


		risp.close();
		tf.close();
	}
	
	
	public void risultatoUnico(){
		
		ArrayList<Double> SecondaETerzaParte= new ArrayList<Double>();
		ArrayList<Double> risultatoUnico= new ArrayList<Double>();
		ArrayList<String> corsi = new ArrayList<String>();

		
		for(Entry<String, Double> e: fineSeconda.entrySet()){
			SecondaETerzaParte.add(e.getValue());	
			corsi.add(e.getKey());
			
		}
		
		for(int i=0; i<fineTerza.size();i++){
			SecondaETerzaParte.add(fineTerza.get(i));
		}
		
		int i=0;
		double sum = 0;
		while(i!=40){
			for(int k=i; k<=SecondaETerzaParte.size()-1; k+=40){
				sum+=SecondaETerzaParte.get(k);
			}
			risultatoUnico.add(sum/2);
			i++;
			sum=0;
		}
		System.out.println("Risultati seconda e terza parte: " + risultatoUnico.toString());
		
		
		// 3 valori pi� alti seconda e terza parte //
		double max;
		System.out.println("I tre risultati pi� alti della seconda e terza parte: ");

		for(int j=0;j<3;j++){
			max = Collections.max(risultatoUnico);
			for(int k = 0; k<risultatoUnico.size();k++){
				if(max==risultatoUnico.get(k)){
					System.out.println(corsi.get(k) + " con " + risultatoUnico.get(k));
					risultatoUnico.remove(k);
					break;
				}
			}
	
		}		
		
	}



}
