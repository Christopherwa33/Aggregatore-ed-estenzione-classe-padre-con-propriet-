package model;

import java.util.ArrayList;

public class Studente extends Persona {
	private int classe;
	private String sezione;
	private ArrayList<Voto> voti;

	public Studente(String nome, String cognome, String ddn, String residenza, int classe, String sezione,
			ArrayList<Voto> voti) {
		super(nome, cognome, ddn, residenza);
		this.classe = classe;
		this.sezione = sezione;
		this.voti = voti;
	}

	public int getClasse() {
		return classe;
	}

	public void setClasse(int classe) {
		this.classe = classe;
	}

	public String getSezione() {
		return sezione;
	}

	public void setSezione(String sezione) {
		this.sezione = sezione;
	}

	public ArrayList<Voto> getVoti() {
		return voti;
	}

	public void setVoti(ArrayList<Voto> voti) {
		this.voti = voti;
	}

	@Override
	public String toString() {
		return "classe: " + classe + ", sezione: " + sezione + ", voti: " + voti;
	}
	
//	double media() la media a partire da tutti i voti
	public double media() {
		double somma = 0;
		
		for (Voto voto: voti)
			somma += voto.getPunteggio();
		
		return  voti.isEmpty() ? 0 : somma / voti.size();
	}
	
//	ArrayList<Voto> voti(String materia) I voti di una determinata materia
	public ArrayList<Voto> voti(String materia) {
		ArrayList<Voto> ris = new ArrayList<>();
		
		for (Voto voto: voti) {
			if (voto.getMateria().equalsIgnoreCase(materia)) {
				ris.add(voto);
			}
		}
		
		return ris;
	}
	
//	double media(String materia) la media di una determinata materia
	public double media(String materia) {
		ArrayList<Voto> votiDiMateria = voti(materia);
		
		double somma = 0;
		
		for (Voto v: votiDiMateria)
			somma += v.getPunteggio();
		
		return votiDiMateria.isEmpty() ? 0 : somma / votiDiMateria.size();
	}
//	
//	// Bonus
//	String mediaVotiPerMateria() // la rappresentazione della media di tutti i voti dello studente raggruppati per materie
	public String mediaVotiPerMateria() {
		String ris = "";
		
		// Vado a salvare tutte le materia dei vari voti
		// Andando però a skippare le ripetizioni
		ArrayList<String> materieStudente = new ArrayList<>();
		
		// Attraverso questo ciclo for, vado a prendere tutte le materia
		// e "isolarle" nella mia lista, in modo da saltare i doppioni
		for (Voto v: voti) {
			if (!materieStudente.contains(v.getMateria())) {
				materieStudente.add(v.getMateria());
			}
		}
		
		for (String materia: materieStudente) {
			ris += materia + ": " + media(materia) + "\n";
		}
		
		return ris;
	}
	
//	double costo() si intende la borsa di studio
//	Se la media è sotto il 6 sarà 0
//	Per ogni voto che è >= al 9 aggiungiamo 50	
	@Override
	public double costo() {
		if (media() < 6) return 0;
		
		double ris = 0;
		
		for (Voto v: voti) {
			if (v.getPunteggio() >= 9) {
				ris += 50;
			}
		}
		
		return ris;
	}

//	// AGGIUNTE
//	boolean promosso()
//		è promosso se le seguenti condizioni sono vere:
//		se è in quinta NON può avere insufficienze
//		Negli altri casi deve avere la media >= 6, ma al massimo 2 insufficienze (< 6)
//		Non può avere più di 1 insufficienza grave (< 5)
//	
	public int nInsufficienze() {
		int ris = 0;
		
		for (Voto v: voti) {
			if (v.getPunteggio() < 6) {
				ris++;
			}
		}
		
		return ris;
	}
	
	public int nVotoSopraNove() {
		int ris = 0;
		
		for (Voto v: voti) {
			if (v.getPunteggio() > 9) {
				ris++;
			}
		}
		
		return ris;
	}
	
	
	public boolean promosso() {
		int insufficienze = 0;
		int insuffGravi = 0;
		
		for (Voto v: voti) {
			if (v.getPunteggio() < 6) {
				insufficienze++;
			}
			if (v.getPunteggio() < 5) {
				insuffGravi++;
			}
		}
		
		if (classe == 5) {
			// se è in 5 come risultato sarà il risultato dell'espressione insuf == 0;
			return insufficienze == 0;
		} 
		
//	Negli altri casi deve avere la media >= 6, ma al massimo 2 insufficienze (< 6)
//	Non può avere più di 1 insufficienza grave (< 5)
		return media() >= 6 
				&& insufficienze <= 2 
				&& insuffGravi <= 1;
		
	}
//	// difficoltà media
//	boolean bilingue()
//		se ha almeno 2 tra le seguenti lingue con la media superiore al 9
//		Italiano, Francese, Inglese, Tedesco
	public boolean bilingue() {
		String[] lingue = {"Italiano", "Francese", "Inglese", "Tedesco"};
		
		int medieSopraNove = 0;
		// KISS keep it simple stupid
		for (String lingua: lingue) {
			if (media(lingua) > 9) {
				medieSopraNove++;
			}
		}
		
		return medieSopraNove >= 2;
	}
	
	private boolean votiInChiave(String chiave) {
		for (Voto v: voti) {
			if (v.getMateria().equalsIgnoreCase(chiave)) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public boolean rispecchiaChiave(String chiave) {
		return super.rispecchiaChiave(chiave) || 
				sezione.equalsIgnoreCase(chiave) || 
				votiInChiave(chiave);
	}

}
