import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Klass kaart
//Java harjutuste eeskujul

public class Kaart {
	
	private String tugevus; //2, 3, 4, ..., J, Q, K, A
	private int tugevusarv; //2,3,4,...,10,11,...,14 -> mälus 0-12
	private char mast; // 4 masti
	private static List<String> sobivadTugevused = Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"); //List tugevustest, et neid hiljem arvuna saada
	private String pildinimi;
	
	private static Map<Character, String> mastidenimetused = new HashMap<>();
	
	static { 
		mastidenimetused.put('♣', "clubs");
		mastidenimetused.put('♦', "diamonds");
		mastidenimetused.put('♠', "spades");
		mastidenimetused.put('♥', "hearts");
	}
	
private static Map<String, String> tugevustenimetused = new HashMap<>();
	
	static { 
		tugevustenimetused.put("2", "2");
		tugevustenimetused.put("3", "3");
		tugevustenimetused.put("4", "4");
		tugevustenimetused.put("5", "5");
		tugevustenimetused.put("6", "6");
		tugevustenimetused.put("7", "7");
		tugevustenimetused.put("8", "8");
		tugevustenimetused.put("9", "9");
		tugevustenimetused.put("10", "10");
		tugevustenimetused.put("J", "jack");
		tugevustenimetused.put("Q", "queens");
		tugevustenimetused.put("K", "king");
		tugevustenimetused.put("A", "ace");
	}
	
	
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
		this.pildinimi = tugevustenimetused.get(tugevusk) + "_of_" + mastidenimetused.get(mastk) + ".png";
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
		this.pildinimi = tugevustenimetused.get(tugevusk) + "_of_" + mastidenimetused.get(mastk) + ".png";

	}
	
	public char getMast() {
		return mast;
	}

	public int getTugevusarv() {
		return tugevusarv;
	}

	public String getPildinimi() {
		return pildinimi;
	}

	@Override
	public String toString() {
		return tugevus + mast;
	}
	
	
}
