//Java harjutuste eeskujul
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pakk {

	private List<Kaart> kaardid; //Kaardi isendid listina
	public List<Kaart> pakk1;
	public List<Kaart> pakk2;

	//Konstruktor, millega tehakse etteantud kaartidest pakk
	public Pakk(List<Kaart> kaardid) {
		ArrayList<Kaart> koopia = new ArrayList<>();
		for(Kaart kaart: kaardid){
			koopia.add(kaart);
		}

		for(int i = 0; i < koopia.size(); i++){
			for(int j = 0; j < koopia.size(); j++){
				if(i!=j && koopia.get(i).toString().equals(koopia.get(j).toString())){
					throw new RuntimeException("Topeltkaardid.");
				}
			}
		}
		this.kaardid = koopia;
	}

	//Konstruktor, mis teeb tavalise 52 kaardiga paki
	public Pakk() {
		ArrayList<Kaart> kaardid = new ArrayList<>(); //K♣
		String[] tugevused = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
		char[] mastid = {'♣', '♦', '♠', '♥'};
		for(String tugevus: tugevused) {
			for(char mast: mastid) {
				//System.out.println(tugevus+mast);
				Kaart k = new Kaart(tugevus, mast);
				kaardid.add(k);
			}
		}
		this.kaardid = kaardid;
	}

	//Kaardipaki väljastamine
	public List<Kaart> getKaardid() {
		ArrayList<Kaart> koopia = new ArrayList<>();
		for(Kaart kaart: kaardid){
			koopia.add(kaart);
		}
		return koopia;
	}

	//Segamismeetod
	public void sega() {
		Collections.shuffle(kaardid);
	}

	/*
	//Meetod, mis võtab pealmise kaardi
	public Kaart võtaKaart() {
		if(kaardid.size()==0) {
			throw new RuntimeException("Tühi pakk.");
		}
		Kaart tagastada=kaardid.get(0);
		kaardid.remove(0);
		return tagastada;
	}
	*/
	
	//Meetod, mis võtab pealmise kaardi
	
	
	public Kaart votaKaart1() {
		if(pakk1.size() == 0) {
			throw new RuntimeException("Tühi pakk.");
		}
		Kaart tagastada=pakk1.get(0);
		pakk1.remove(0);
		return tagastada;
	}
	
	//Meetod, mis võtab pealmise kaardi
	public Kaart votaKaart2() {
		if(pakk2.size() == 0) {
			throw new RuntimeException("Tühi pakk.");
		}
		Kaart tagastada = pakk2.get(0);
		pakk2.remove(0);
		return tagastada;
	}

	//Poolitab paki
	public void poolita(){
		ArrayList<Kaart> koopia1 = new ArrayList<>();
		ArrayList<Kaart> koopia2 = new ArrayList<>();
		for(int i = 0; i < kaardid.size(); i++){
			if(i < kaardid.size() / 2){
				koopia1.add(kaardid.get(i));
			}
			else{
				koopia2.add(kaardid.get(i));
			}
		}
		this.pakk1 = koopia1;
		this.pakk2 = koopia2;
	}

	public String valjastaKaardid() {
		String valjastada = "";
		for(Kaart kaart: kaardid) {
			valjastada += kaart + " ";
		}
		return valjastada;
	}
	
	public String valjastaPakk1(){
		String valjastada = "";
		for(Kaart kaart: pakk1){
			valjastada += kaart + " ";
		}
		return valjastada;
	}
	
	public String valjastaPakk2() {
		String valjastada = "";
		for(Kaart kaart: pakk2) {
			valjastada += kaart + " ";
		}
		return valjastada;
	}
	
	
}
