
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Graafika extends Application {

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

	public static void kaartideJarjekord(List<Kaart> kaardid, Pakk pakk, int mangijaNr, String manguvorm, 
			Stage peaLava, GraphicsContext sisu) throws InterruptedException { //pakk - sinna lisatakse uued kaardid, mangijaNr - kumma mängija kaardid
		


		List<Kaart> kaardidKoopia =  new ArrayList<>();
		kaardidKoopia.addAll(kaardid);
		for(int i = 0; i < kaardid.size(); i++) {
			
			System.out.print("Valikusolevad kaardid: ");
			for(int j = 0; j < kaardidKoopia.size(); j++) {

				//Kui on veel mitme kaardi vahel valida
				System.out.print((j + 1) + ". kaart: " + kaardidKoopia.get(j) + "\t");
				sisu.drawImage(kaardidKoopia.get(j).getPilt(), 50, 40, 300, 700);
				TimeUnit.SECONDS.sleep(10);
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

			else{ //Kasutaja sisestamine


				//String indeksSonena = sisend.nextLine();

				/*
				while(!onKorrektne(indeksSonena, 1, kaardidKoopia.size())){
					//indeksSonena = sisend.nextLine();
				}

				indeksNumbrina = Integer.parseInt(indeksSonena);
			}


			if(mangijaNr == 1){
				pakk.pakk1.add(kaardidKoopia.remove(indeksNumbrina - 1));
			}
			if(mangijaNr == 2){
				pakk.pakk2.add(kaardidKoopia.remove(indeksNumbrina - 1));
			}
				 */


				System.out.println("Mängija 1 pakk peale kaardi lisamist: " + pakk.valjastaPakk1() + pakk.pakk1.size());
				System.out.println("Mängija 2 pakk peale kaardi lisamist: " + pakk.valjastaPakk2() + pakk.pakk2.size());

			}
		}
	}

	public static void kaartidePohjaLisamine(int kaartideVordlus, Kaart kaart1, Kaart kaart2, Pakk pakk, List<Kaart> voidetavadKaardid, List<Kaart> mangija1Kaardid, 
			List<Kaart> mangija2Kaardid, String manguvorm, Stage peaLava, GraphicsContext sisu) throws InterruptedException {

		if(kaartideVordlus == 1) {
			//kaartideJarjekord(Arrays.asList(kaart1, kaart2), pakk, 1, sisend);
			kaartideJarjekord(voidetavadKaardid, pakk, 1, manguvorm, peaLava, sisu);
		}
		if(kaartideVordlus == 2) {
			//kaartideJarjekord(Arrays.asList(kaart1, kaart2), pakk, 2, sisend);
			kaartideJarjekord(voidetavadKaardid, pakk, 2, manguvorm, peaLava, sisu);
		}
		if(kaartideVordlus == 0) {

			//kaartideJarjekord(Arrays.asList(kaart1, kaart2), pakk, 0);
			List<Kaart> kaardid1 = new ArrayList<>();
			List<Kaart> kaardid2 = new ArrayList<>();
			kaardid1.add(kaart1);
			kaardid2.add(kaart2);

			if(pakk.pakk1.size() == 0) {
				pakk.pakk1.add(kaardid1.remove(0));
			}
			if(pakk.pakk2.size() == 0) {
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

			kaartidePohjaLisamine(vordleKaarte(uusKaart1, uusKaart2), uusKaart1, uusKaart2, pakk, kokkuKaardid, 
					kaardid1, kaardid2, manguvorm, peaLava, sisu);
		}
	}


	public static void mangi(String manguvorm, Stage peaLava) throws InterruptedException {

		double esialgneLavaLaius = 1000;
		double esialgneLavaKorgus = 600;
		double KAARDISUHE = 726.0/500;
		double KAUGUSSERVAST = 0;
		double esialgneKaardiLaius = (esialgneLavaLaius - 2*KAUGUSSERVAST) / 30 * 5;
		double esialgneKaardiKorgus = esialgneKaardiLaius * KAARDISUHE;
		double esialgneNihe = esialgneKaardiLaius / 5; 



		Group juur = new Group();
		Canvas louend = new Canvas(esialgneLavaLaius, esialgneLavaKorgus);
		GraphicsContext sisu = louend.getGraphicsContext2D(); 





		//		for(int i=0; i<26; i++){
		//			sisu.drawImage(c2, KAUGUSSERVAST + i*esialgneNihe,  10,  esialgneKaardiLaius,  esialgneKaardiKorgus);
		//			sisu.drawImage(c2, esialgneLavaLaius - KAUGUSSERVAST - i*esialgneNihe - esialgneKaardiLaius,  10 + esialgneKaardiKorgus,  esialgneKaardiLaius,  esialgneKaardiKorgus);
		//		}

		juur.getChildren().add(louend);

		Scene stseen = new Scene(juur, esialgneLavaLaius, esialgneLavaKorgus, Color.SNOW);

		peaLava.setTitle("Linnade põletamine");
		peaLava.setScene(stseen);
		peaLava.show();

		stseen.widthProperty().addListener(new ChangeListener<Number>() {
			@Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
				System.out.println("Width: " + newSceneWidth);
				liigutaKaarte(newSceneWidth.doubleValue());
			}

			private void liigutaKaarte(double doubleValue) {
				double uuslaius = 7;


			}


		});

		stseen.heightProperty().addListener(new ChangeListener<Number>() {
			@Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
				System.out.println("Height: " + newSceneHeight);
			}
		});


		Pakk pakk = new Pakk();
		//System.out.println("Segamata tervikpakk: " + pakk.valjastaKaardid());
		pakk.sega();
		//System.out.println("Segatud tervikpakk: " + pakk.valjastaKaardid());
		pakk.poolita();
		System.out.println("Mängija 1 esialgne pakk: " + pakk.valjastaPakk1() + pakk.pakk1.size());


		for(int i = 0; i < pakk.pakk1.size(); i++) {
			sisu.drawImage(pakk.pakk1.get(i).getPilt(), KAUGUSSERVAST + i*esialgneNihe,  0,  esialgneKaardiLaius,  esialgneKaardiKorgus);
		}
		for(int i = 0; i < pakk.pakk2.size(); i++) {
			sisu.drawImage(pakk.pakk2.get(i).getPilt(), esialgneLavaLaius - KAUGUSSERVAST - i*esialgneNihe - esialgneKaardiLaius,  esialgneLavaKorgus - esialgneKaardiKorgus,  esialgneKaardiLaius,  esialgneKaardiKorgus);
		}
		//System.out.println("Mängija 2 esialgne pakk: " + pakk.valjastaPakk2() + pakk.pakk2.size());




		while(true){
			if(pakk.pakk1.size()==0 || pakk.pakk2.size()==0) {
				if(pakk.pakk1.size()==0){
					System.out.println("Võitis mängija 1.");
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Võiduteade");
					alert.setHeaderText("Õnnitlused!");
					alert.setContentText("Võitis mängija 1.");

					alert.showAndWait();
				}
				if(pakk.pakk2.size()==0){
					System.out.println("Võitis mängija 2.");
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Võiduteade");
					alert.setHeaderText("Õnnitlused!");
					alert.setContentText("Võitis mängija 2.");

					alert.showAndWait();
				}
				
				Platform.exit();
				break;
			}

			
			Kaart kaart1 = pakk.votaKaart1();
			Kaart kaart2 = pakk.votaKaart2();
			
			Rectangle taust = new Rectangle(0, 0, esialgneLavaLaius, esialgneLavaKorgus);
			FadeTransition ft = new FadeTransition(Duration.millis(10000), taust);  // luuakse uus haihtumine
			 
			ft.setFromValue(1.0);  // määratakse algväärtus (1.0 - täiesti selge)
			 
			ft.setToValue(0.5); // määratakse lõppväärtus (0 - täiesti haihtunud)
			 
			ft.setCycleCount(1); // lõpmatu tsüklite arv
			 
			ft.setAutoReverse(true); // lõppu jõudes tagasi, algusest jälle edasi
			 
			ft.play(); // animatsioon mängima
			
			System.out.println();
			System.out.print("Esimene mängija käis kaardi: " + kaart1 + " ning teine mängija: " + kaart2); //VÄLJATRÜKK



			if(kaart1.getTugevusarv() > kaart2.getTugevusarv()) {
				System.out.println(", esimene mängija saab kaardid endale.");
			}
			if(kaart1.getTugevusarv() < kaart2.getTugevusarv()) {
				System.out.println(", teine mängija saab kaardid endale.");

			}
			if(kaart1.getTugevusarv() == kaart2.getTugevusarv()) {
				System.out.println(", kaardid osutusid tugevuselt võrdseteks!");
			}



			System.out.println();

			int kaartideVordlus = vordleKaarte(kaart1, kaart2);

			kaartidePohjaLisamine(kaartideVordlus, kaart1, kaart2, pakk, Arrays.asList(kaart1, kaart2), Arrays.asList(kaart1), Arrays.asList(kaart2), manguvorm, peaLava, sisu);

			System.out.println();
			System.out.println("Mängija 1 pakk peale kaardi lisamist: " + pakk.valjastaPakk1() + pakk.pakk1.size());
			System.out.println("Mängija 2 pakk peale kaardi lisamist: " + pakk.valjastaPakk2() + pakk.pakk2.size());
			System.out.println();

		}

	}


	@Override
	public void start(Stage peaLava) {

		// Luuakse hüpikakna jaoks teine lava
		Stage kusimus = new Stage();

		// Küsimuse ja kolme nupu loomine
		Label label = new Label("Kelle vastu tahad mängida?");
		Button arvuti = new Button("Arvuti");
		Button mangija = new Button("Mängija");
		Button valju = new Button("Välju");

		// Sündmuse lisamine nupule arvuti
		arvuti.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				kusimus.hide();
				try {
					mangi("1", peaLava);
				} catch (InterruptedException e) {
					throw new RuntimeException();
				}
			}
		});


		// Sündmuse lisamine nupule mängija
		mangija.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				kusimus.hide();
				try {
					mangi("2", peaLava);
				} catch (InterruptedException e) {
					throw new RuntimeException();
				}
			}
		});

		mangija.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER))
					kusimus.hide();
				try {
					mangi("2", peaLava);
				} catch (InterruptedException e) {
					throw new RuntimeException();
				}
			}
		});

		// Sündmuse lisamine nupule valju
		valju.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Platform.exit(); //Mäng läheb kinni
			}
		});


		// Nuppude grupeerimine
		FlowPane pane = new FlowPane(10, 20); //Üleval vahe 10px, kõrvalt vahe 20px
		pane.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(mangija, arvuti, valju);

		// Küsimuse ja nuppude vertikaalselt paigutamine
		VBox vBox = new VBox(20);
		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().addAll(label, pane);

		// Stseeni loomine ja näitamine
		Scene hupikStseen = new Scene(vBox);
		kusimus.setScene(hupikStseen);
		kusimus.setTitle("Vastase valimine");
		kusimus.show();


		//Hüpikakna taga oleva akna loomine
		/*
		Group juur = new Group();
		Canvas louend = new Canvas();
		GraphicsContext sisu = louend.getGraphicsContext2D(); 
		juur.getChildren().add(louend);
		Scene avaStseen = new Scene(juur, 600, 300, Color.DARKGREEN);
		peaLava.setTitle("Linnade põletamine");
		peaLava.setScene(avaStseen);
		 */

		//peaLava.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
