package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.Persona;
import model.Scuola;
import view.ConsoleView;

public class Main {

	public static void main(String[] args) throws Exception {
		/*
		 * Controller cioè elemento che si prende la briga di gestire input/output
		 * sfruttando model e view
		 * Dal model va a prendere i dati (PURI)
		 * quest'ultimi verranno poi passatti alla view che
		 * andrà a renderizzare sull'applicazione
		 */
		Scuola scuola = new Scuola("src/source/scuola.txt");
		ConsoleView view = new ConsoleView();
		Scanner tastiera = new Scanner(System.in);
		String cmd = "";
		String ris = "";
		
		do {
			System.out.println(view.primoMenu());
			cmd = tastiera.nextLine();
			
			switch(cmd) {
			case "1":
				// Il controller chiede i dati al Model
				ArrayList<Persona> persone = scuola.studenti();
				// Prende i dati restituiti dal model e li passa alla view
				String personeGraficate = view.renderPersone(persone);
				// prende il risultato della view e lo stampa su console
				ris = personeGraficate;
				break;
			case "5":
				System.out.println(view.richiestaChiave());
				String chiave = tastiera.nextLine();
				ris = view.renderPersone(scuola.ricercaChiave(chiave));
				break;
			case "6":
				ris = view.renderPersone(scuola.studentiOrdinatiPerMedia());
				break;
			case "help":
				ris = view.menuApplicazione();
				break;
			case "0":
				
				break;
			default:
				ris = view.comandoSconosciuto();
				break;
			}
			System.out.println(ris);
		} while (!cmd.equalsIgnoreCase("0"));
		
		
		

	}

}
