package view;

import java.util.ArrayList;
import java.util.Arrays;

import model.Docente;
import model.Persona;
import model.Studente;
import model.Voto;

/*
 * Lo scopo di questa classe è fornire un oggetto che sia in grado di
 * "graficare" gli elementi della nostra applicazione
 * Racchiuderemo tutto ciò che fa parte delle VIEW
 */
public class ConsoleView {
	
	private String menuPrincipale = 
			"************************\n"
			+ "Inserisci il comando\n"
			+ "************************\n";
	
	private String menu = "[1] Lista degli studenti\n"
						+ "[2] Altro\n" 
						+ "[5] Ricerca\n"
						+ "[6] Studenti ordinati per media\n"
						+ "[0] Uscita";
	
	private String cmdSconosciuto = "Comando sconosciuto, digita help per la lista delle opzioni";

	public String primoMenu() {
		return menuPrincipale;
	}

	public String menuApplicazione() {
		return menu;
	}

	public String comandoSconosciuto() {
		return cmdSconosciuto;
	}

	public String renderPersone(ArrayList<Persona> persone) {
		String ris = "";
		
		for (Persona p : persone) {
			ris += renderPersona(p) + "\n\n";
		}
		
		return ris;
	}

	private String renderPersona(Persona p) {
		String ris =
					"#####################################\n" +
					"Nome: " + p.getNome() + "\n" +
					"Cognome: " + p.getCognome() + "\n" +
					"DDN: " + p.getDdn() + "\n" +
					"Residenza: " + p.getResidenza() + "\n";
				
		if (p instanceof Studente) {
			ris += renderStudente((Studente) p);
		}
		if (p instanceof Docente) {
			ris += renderDocente((Docente) p);
		}
		
		return ris + "#####################################\n";
	}
	
	private String renderDocente(Docente d) {
		String ris = "Anni di esperienza: " + d.getAnniExp() + "\n" +
					"Cattedra: " + (d.isCattedra() ? "SI" : "NO") + "\n" +
					"Materie insegnate: " + d.getMaterieInsegnate() + "\n";
		
		return ris;
	}
	
	
	private String renderStudente(Studente s) {
		String ris = 
				"Classe: " + s.getClasse() + " " + s.getSezione() + "\n" +
				"Voti: \n" +
				"===============================\n" 
				+ renderVoti(s.getVoti()) + 
				"===============================\n";
			
		return ris;
	}

	private String renderVoti(ArrayList<Voto> voti) {
		String ris = "";
		
		for (Voto voto: voti) {
			ris += renderVoto(voto) + "\n";
		}
		
		return ris;
	}

	private String renderVoto(Voto voto) {
		return voto.getMateria() + ": " + voto.getPunteggio();
	}

	public String richiestaChiave() {
		return "Inserire la chiave di ricerca:";
	}
	
}
