import java.util.ArrayList;
import java.util.List;

public class Peaklass {
	
	public static void mangi(){
		Pakk pakk = new Pakk();
		System.out.println("Segamata tervikpakk: " + pakk.valjastaKaardid());
		pakk.sega();
		System.out.println("Segatud tervikpakk: " + pakk.valjastaKaardid());
		pakk.poolita();
		System.out.println("Pakk1: " + pakk.valjastaPakk1() + pakk.pakk1.size());
		System.out.println("Pakk2: " + pakk.valjastaPakk2() + pakk.pakk2.size());
		while(true){
			if(pakk.pakk1.size()==0 || pakk.pakk2.size()==0)
				break;
		}
		
	}

	public static void main(String[] args) {
		System.out.println("Linnade põletamise mäng!");
		mangi();

	}

}
