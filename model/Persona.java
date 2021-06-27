package model;

import java.time.LocalDate;
// java.util.Date;

/*
 * MVC: Model View Controller
 * � un pattern* (Una soluzione astratta ad un problema ricorrente)
 * Che ci consiglia di suddividere in 3 filoni principali l'intera applicazione
 * 
 * 1) Model: Tutte le classi che rientrato nella gestione della logica applicativa
 * 	Le classi modello, le classi aggregatrici
 * 2) View: Tutte le classi il cui compito � quello di gestire la parte grafica, in programma
 * 	su console, consister� soltanto nelle stringhe che verranno stampate su console
 * 3) Controller: Classi che gestiscono input e output, nel caso di Programma console
 * 	� praticamente il nostro main nel quale andiamo a chiedere input all'utente e ne 
 * restituiamo l'output
 */

/*
 * La classe Modello Persona quindi rientra nell'insieme dei Model
 * Nello specifico, le classi Modello vengono anche chiamate Entity
 */
public abstract class Persona {
	// Propriet� private perch� voglio sfruttare l'incapsulamento
	// 3� principio della OOP, che offre capacit� di nascondere parte
	// Del funzionamento/implementazione degli elementi

	/*
	 * Grazie all'incapsulamento riesco a rafforzare il principio di Separation of
	 * concerns, separazione delle responsabilit�
	 * 
	 * Visto che le propriet� sono private, solo l'oggetto di per se � in grado di
	 * altersi in modo DIRETTO lo stato
	 */
	private String nome;
	private String cognome;
	private String ddn;
	private String residenza;

	// Costruttore public perch� devo rendere possibile la creazione dell'oggetto
	// al di fuori di questa classe
	public Persona(String nome, String cognome, String ddn, String residenza) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.ddn = ddn;
		this.residenza = residenza;
	}

	// Metodo che espongono in lettura/scrittura
	// Ma alla fine sar� l'oggetto stesso a modificare le propriet�
	// Gli "utilizzatori" andranno soltanto ad invocare metodi a cui, in scrittura
	// passano le propriet� da assegnare, ma sar� l'oggetto il responsabile finale
	// perch� � lui che prende i parametri e le assegna alle propriet�
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome.equalsIgnoreCase("pippo"))
			return;
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getDdn() {
		return ddn;
	}

	public void setDdn(String ddn) {
		this.ddn = ddn;
	}

	public String getResidenza() {
		return residenza;
	}

	public void setResidenza(String residenza) {
		this.residenza = residenza;
	}
	
	private int annoNascita() {
		return Integer.parseInt(ddn.split("-")[2]);
	}
	
	public int eta() {
		// A differenza di Date, il metodo getYear di LocalDate
		// non � deprecato (Vecchio, sconsigliato dagli autori delle libreria,
		// in quanto esistono metodi migliori)
		LocalDate localDate = LocalDate.now();
		return  localDate.getYear() - annoNascita();
	}
	
	// Metodo Astratto visibile al di fuori di questo package
	public abstract double costo();

	@Override
	public String toString() {
		return "nome: " + nome + ", cognome: " + cognome + ", ddn: " + ddn + ", residenza: " + residenza;
	}
	
	public boolean rispecchiaChiave(String chiave) {
		return nome.equalsIgnoreCase(chiave) 
				|| cognome.equalsIgnoreCase(chiave) 
				|| ddn.equalsIgnoreCase(chiave)
				|| residenza.equalsIgnoreCase(chiave);
	}

}
