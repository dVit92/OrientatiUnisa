package tfidf;

import java.io.IOException;

/**
 * Show in the console the result to the users 
 * @authors Daniele Vitale, Marianna di Gregorio, Alessandra Orsi
 *
 */

public class MainRisultati {

	public static void main(String[] args) throws IOException {
		
		Risultati r= new Risultati();
		r.primaParte();
		r.secondaParte();
		r.terzaParte();
		r.risultatoUnico();

	}

}
