import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Peaklass {

	public static int vordleKaarte (Kaart kaart1, Kaart kaart2) {
		if(kaart1.getTugevusarv()>kaart2.getTugevusarv())
			return 1; //kaart1 on suurem kui kaart2
		if (kaart1.getTugevusarv()<kaart2.getTugevusarv())
			return 2; //kaart 2 on suurem kui kaart1
		return 0; //võrdsed
	}

	public static boolean onKorrektne(String str, int min, int max)  
	{  
		try  
		{  
			double d = Integer.parseInt(str);  
		}  
		catch(NumberFormatException nfe)  
		{  
			System.out.println("Palun sisesta arv!");
			return false;  
		}  

		//System.out.println(str + " min " +  min + " max " +  max);
		if(Integer.parseInt(str) < min || Integer.parseInt(str) > max){

			System.out.println();
			if(Double.parseDouble(str) < min){
				System.out.println("Arv ei tohi olla väiksem kui " + min + ".");
				return false;
			}
			else{
				System.out.println("Arv ei tohi olla suurem kui " + max + ".");
				return false;
			}
		}

		return true;  
	}

	public static void kaartideJarjekord(List<Kaart> kaardid, Pakk pakk, int mangijaNr, Scanner sisend, String manguvorm) { //pakk - sinna lisatakse uued kaardid, mangijaNr - kumma mängija kaardid

		List<Kaart> kaardidKoopia =  new ArrayList<>();
		kaardidKoopia.addAll(kaardid);
		for(int i = 0; i < kaardid.size(); i++) {
			System.out.print("Valikusolevad kaardid: ");
			for(int j = 0; j < kaardidKoopia.size(); j++) {


				//Kui on veel mitme kaardi vahel valida
				System.out.print((j + 1) + ". kaart: " + kaardidKoopia.get(j) + "\t");



			}



			if (i == kaardid.size() - 1) { // Kui järel on ainult üks kaart
				System.out.println("\nViimasena lisan: " + kaardidKoopia.get(0));
				if(mangijaNr == 1){
					pakk.pakk1.add(kaardidKoopia.remove(0));
				}
				if(mangijaNr == 2){
					pakk.pakk2.add(kaardidKoopia.remove(0));
				}

				break;
			}

			System.out.println();
			System.out.println();
			System.out.println(mangijaNr + ". mängija, palun sisesta järgmisena kaardipaki põhja lisatava kaardi järjekorranumber: ");

			int indeksNumbrina;


			if(mangijaNr == 2 && manguvorm.equals("1")){
				Random suvaline = new Random();
				indeksNumbrina = 1 + suvaline.nextInt(kaardidKoopia.size());
				System.out.println("[ARVUTI]: Valisin, et järgmisena lisan kaardipaki põhja kaardi järjekorranumbriga " + indeksNumbrina + ".");
				System.out.println();
			}
			
			else{
				String indeksSonena = sisend.nextLine();
				while(!onKorrektne(indeksSonena, 1, kaardidKoopia.size())){
					indeksSonena = sisend.nextLine();
				}

				indeksNumbrina = Integer.parseInt(indeksSonena);
			}


			if(mangijaNr == 1){
				pakk.pakk1.add(kaardidKoopia.remove(indeksNumbrina - 1));
			}
			if(mangijaNr == 2){
				pakk.pakk2.add(kaardidKoopia.remove(indeksNumbrina - 1));
			}

			System.out.println("Mängija 1 pakk peale kaardi lisamist: " + pakk.valjastaPakk1() + pakk.pakk1.size());
			System.out.println("Mängija 2 pakk peale kaardi lisamist: " + pakk.valjastaPakk2() + pakk.pakk2.size());

			//			System.out.println(i);
		}

	}

	public static void kaartidePohjaLisamine(int kaartideVordlus, Kaart kaart1, Kaart kaart2, Pakk pakk, Scanner sisend, List<Kaart> voidetavadKaardid, List<Kaart> mangija1Kaardid, List<Kaart> mangija2Kaardid, String manguvorm) {
		if(kaartideVordlus == 1){
			//kaartideJarjekord(Arrays.asList(kaart1, kaart2), pakk, 1, sisend);
			kaartideJarjekord(voidetavadKaardid, pakk, 1, sisend, manguvorm);
		}
		if(kaartideVordlus == 2){
			//kaartideJarjekord(Arrays.asList(kaart1, kaart2), pakk, 2, sisend);
			kaartideJarjekord(voidetavadKaardid, pakk, 2, sisend, manguvorm);
		}
		if(kaartideVordlus == 0){

			//System.out.println("võrdsed");
			//kaartideJarjekord(Arrays.asList(kaart1, kaart2), pakk, 0);
			List<Kaart> kaardid1 = new ArrayList<>();
			List<Kaart> kaardid2 = new ArrayList<>();
			kaardid1.add(kaart1);
			kaardid2.add(kaart2);

			if(pakk.pakk1.size() == 0){
				pakk.pakk1.add(kaardid1.remove(0));
			}
			if(pakk.pakk2.size() == 0){
				pakk.pakk2.add(kaardid2.remove(0));
			}
			Kaart tagurpidiKaart1 = pakk.votaKaart1();
			Kaart tagurpidiKaart2 = pakk.votaKaart2();
			kaardid1.add(tagurpidiKaart1);
			kaardid2.add(tagurpidiKaart2);

			if(pakk.pakk1.size() == 0){
				pakk.pakk1.add(kaardid1.remove(0));
			}
			if(pakk.pakk2.size() == 0){
				pakk.pakk2.add(kaardid2.remove(0));
			}
			Kaart uusKaart1 = pakk.votaKaart1();
			Kaart uusKaart2 = pakk.votaKaart2();
			kaardid1.add(uusKaart1);
			kaardid2.add(uusKaart2);

			List<Kaart> kokkuKaardid = new ArrayList<>();
			kokkuKaardid.addAll(kaardid1);
			kokkuKaardid.addAll(kaardid2);

			kaartidePohjaLisamine(vordleKaarte(uusKaart1, uusKaart2), uusKaart1, uusKaart2, pakk, sisend, kokkuKaardid, kaardid1, kaardid2, manguvorm);
		}
	}


	public static void mangi(String manguvorm) {


		Scanner sisend = new Scanner(System.in);

		Pakk pakk = new Pakk();
		System.out.println("Segamata tervikpakk: " + pakk.valjastaKaardid());
		pakk.sega();
		System.out.println("Segatud tervikpakk: " + pakk.valjastaKaardid());
		pakk.poolita();
		System.out.println("Mängija 1 esialgne pakk: " + pakk.valjastaPakk1() + pakk.pakk1.size());
		System.out.println("Mängija 2 esialgne pakk: " + pakk.valjastaPakk2() + pakk.pakk2.size());

		while(true){
			if(pakk.pakk1.size()==0 || pakk.pakk2.size()==0) {
				if(pakk.pakk1.size()==0)
					System.out.println("Võitis mängija 1.");
				if(pakk.pakk2.size()==0)
					System.out.println("Võitis mängija 2.");
				break;
			}

			Kaart kaart1 = pakk.votaKaart1();
			Kaart kaart2 = pakk.votaKaart2();
			System.out.println();
			System.out.print("Esimene mängija käis kaardi: " + kaart1 + " ning teine mängija: " + kaart2);
			if(kaart1.getTugevusarv() > kaart2.getTugevusarv()){
				System.out.println(", esimene mängija saab kaardid endale.");
			}
			if(kaart1.getTugevusarv() < kaart2.getTugevusarv()){
				System.out.println(", teine mängija saab kaardid endale.");
			}
			if(kaart1.getTugevusarv() == kaart2.getTugevusarv()){
				System.out.println(", kaardid osutusid tugevuselt võrdseteks!");
			}

			System.out.println();

			int kaartideVordlus = vordleKaarte(kaart1, kaart2);

			kaartidePohjaLisamine(kaartideVordlus, kaart1, kaart2, pakk, sisend, Arrays.asList(kaart1, kaart2), Arrays.asList(kaart1), Arrays.asList(kaart2), manguvorm);

			System.out.println();
			System.out.println("Mängija 1 pakk peale kaardi lisamist: " + pakk.valjastaPakk1() + pakk.pakk1.size());
			System.out.println("Mängija 2 pakk peale kaardi lisamist: " + pakk.valjastaPakk2() + pakk.pakk2.size());
			System.out.println();

		}

		sisend.close();
	}

	public static void main(String[] args) {
		System.out.println("Linnade põletamise mäng!");
		Scanner scan = new Scanner(System.in);
		System.out.println("Kas te soovite mängida arvuti vastu (1) või kahekesi (2)?");
		

		String manguvorm = scan.nextLine();
		while(!onKorrektne(manguvorm, 1, 2))
			manguvorm = scan.nextLine();
		
		if(manguvorm.equals("1"))
			System.out.println("Nüüdsest mängite arvuti vastu!");

		mangi(manguvorm);

	}

}
