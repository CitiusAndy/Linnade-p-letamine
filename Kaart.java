//import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Klass kaart
//Java harjutuste eeskujul

public class Kaart {
	private String tugevus; //2, 3, 4, ..., J, Q, K, A
	private int tugevusarv; //2,3,4,...,10,11,...,14 -> mälus 0-12
	private char mast; // 4 masti
	private static List<String> sobivadTugevused = Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"); //List tugevustest, et neid hiljem arvuna saada

	
	public Kaart(String tugevusk, char mastk) {
		String mastid = "♣♦♠♥";
		String tugevused = "2345678910JQKA";

		//System.out.println("Konstruktorisse 1 saadud kaardid: " + tugevusk + mastk);
		
		if(mastid.indexOf(mastk) == -1 || tugevused.indexOf(tugevusk) == -1 || tugevusk.equals("1") || tugevusk.equals("")) {
			throw new RuntimeException("Ebasobivad kaardid");
		}
		
		this.tugevus = tugevusk;
		this.mast = mastk;
		this.tugevusarv = sobivadTugevused.lastIndexOf(tugevus);
	}
	
	public Kaart(String kombineeritud) {
		int pikkus = kombineeritud.length();
		String mastid = "♣♦♠♥";
		String tugevused = "2345678910JQKA";
		String tugevusk;
		char mastk;
		
		if(pikkus == 2) {
			tugevusk = Character.toString(kombineeritud.charAt(0));
			mastk = kombineeritud.charAt(1);
		}
		
		else { //pikkus==3
			String esimene = Character.toString(kombineeritud.charAt(0));
			String teine = Character.toString(kombineeritud.charAt(1));
			tugevusk = esimene+teine;
			mastk = kombineeritud.charAt(2);
		}
		
		//System.out.println("Konstruktorisse 2 saadud kaardid: " + tugevusk + mastk);
		
		if(mastid.indexOf(mastk) == -1 || tugevused.indexOf(tugevusk) == -1 || tugevusk.equals("1") || tugevusk.equals("")) {
			throw new RuntimeException("Ebasobivad kaardid");
		}
		
		this.tugevus = tugevusk;
		this.mast = mastk;
		this.tugevusarv = sobivadTugevused.lastIndexOf(tugevus);

	}
	
	public char getMast() {
		return mast;
	}

	public int getTugevusarv() {
		return tugevusarv;
	}

	@Override
	public String toString() {
		return tugevus + mast;
	}
	
	

}
