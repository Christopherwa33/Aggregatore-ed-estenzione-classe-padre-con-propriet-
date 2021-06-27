package model;

import java.util.ArrayList;

public class Docente extends Persona {
	boolean cattedra;
	int anniExp;
	ArrayList<String> materieInsegnate;

	public Docente(String nome, String cognome, String ddn, String residenza, boolean cattedra, int anniExp,
			ArrayList<String> materieInsegnate) {
		super(nome, cognome, ddn, residenza);
		this.cattedra = cattedra;
		this.anniExp = anniExp;
		this.materieInsegnate = materieInsegnate;
	}

	public boolean isCattedra() {
		return cattedra;
	}

	public void setCattedra(boolean cattedra) {
		this.cattedra = cattedra;
	}

	public int getAnniExp() {
		return anniExp;
	}

	public void setAnniExp(int anniExp) {
		this.anniExp = anniExp;
	}

	public ArrayList<String> getMaterieInsegnate() {
		return materieInsegnate;
	}

	public void setMaterieInsegnate(ArrayList<String> materieInsegnate) {
		this.materieInsegnate = materieInsegnate;
	}

	@Override
	public String toString() {
		return "cattedra: " + cattedra + ", anniExp: " + anniExp + ", materieInsegnate: " + materieInsegnate;
	}
/*	
 *  costo => di base partono da 1000, se è di cattedra da 2000
 *	per ogni anno di exp aggiungere 50, e aggiungere 100 per ogni materia insegnata
*/
	@Override
	public double costo() {
		double stipendioIniziale = cattedra ? 2000 : 1000;
		return stipendioIniziale 
				+ 50 * anniExp 
				+ 100 * materieInsegnate.size();
	}
	
	public boolean insegna(String materia) {
		for (String s: materieInsegnate) {
			if (s.equalsIgnoreCase(materia)) {
				return true;
			}
		}
		
		return false;
	}

	
	@Override
	public boolean rispecchiaChiave(String chiave) {
		return super.rispecchiaChiave(chiave) ||
				insegna(chiave);
	}

}
