package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Scuola {
	private ArrayList<Persona> persone = new ArrayList<>();

	public Scuola(String percorsoFile) throws Exception {
		Scanner dati = new Scanner(new File(percorsoFile));

		while (dati.hasNextLine()) {
			String[] riga = dati.nextLine().split(",");

			Persona p;

			switch (riga[0].toUpperCase()) {
			case "STUDENTE":
				ArrayList<Voto> voti = new ArrayList<>();
				// Italiano:9-Storia:6.5-Informatica:8.25
				String[] votiAppoggio = riga[7].split("-");

				for (String v : votiAppoggio) {
					// v ?? Italiano:9
					String[] votoString = v.split(":");
					// votoString ? 0 => italiano 1 => 9
					voti.add(new Voto(votoString[0], Double.parseDouble(votoString[1])));
				}

				p = new Studente(riga[1], riga[2], riga[3], riga[4], Integer.parseInt(riga[5]), riga[6], voti);
				break;
			case "DOCENTE":
				ArrayList<String> materieInsegnate = new ArrayList<>();
				String[] materie = riga[7].split("-");
				
				for (String materia: materie)
					materieInsegnate.add(materia);
				
				p = new Docente(
						riga[1], riga[2], riga[3], riga[4], 
						riga[5].equalsIgnoreCase("SI"), 
						Integer.parseInt(riga[6]), 
						materieInsegnate
				);
				break;
			default:
				continue;
			}
			persone.add(p);
		}
	}

	public ArrayList<Persona> studenti() {
		ArrayList<Persona> ris = new ArrayList<>();
		
		for (Persona p : persone) {
			if (p instanceof Studente) {
				ris.add(p);
			}
		}
		
		return ris;
	}
//	ArrayList<Persona> docenti()
	ArrayList<Persona> docenti() {
		ArrayList<Persona> ris = new ArrayList<>();
		
		for (Persona p: persone) {
			if (p instanceof Docente) {
				ris.add(p);
			}
		}
		
		return ris;
	}
	
//
//	ArrayList<Persona> studentiConMediaSuperioreA(double media)
	ArrayList<Persona> studentiConMediaSuperioreA(double media) {
		ArrayList<Persona> ris = new ArrayList<>();
		
		for (Persona p: persone) {
			// if in java sono lazy, controllano soltanto lo stretto necessario
			// Il casting dell'oggetto p a Studente verrà effettuato
			// saltanto nel casi in cui la prima condizione (instanceof)
			// risulti essere vera
			if (p instanceof Studente && ((Studente) p).media() > media) {
				ris.add(p);
			}
		}
		
		return ris;
	}
//
//	double costoScuola() costo totale della scuola
	public double costoScuola() {
		double ris = 0;
		
		
		for (Persona p: persone)
			// Non faccio controlli con instanceof
			// né ho necessità di castare gli oggetti
			// perché costo è un metodo che viene definito in persona
			ris += p.costo();
		
		return ris;
	}
//
//	double costoMedioDocente()
	public double costoMedioDocente() {
		ArrayList<Persona> docenti = docenti();
		
		double sommaCosti = 0;
		
		for (Persona p: docenti) {
			sommaCosti += p.costo();
		}
		
		return docenti.isEmpty() ? 0 : sommaCosti / docenti.size();
	}
	
//
//	ArrayList<Persone> bocciate()
	public ArrayList<Persona> bocciate() {
		ArrayList<Persona> ris = new ArrayList<>();
		
		for (Persona p: persone) {
			if (p instanceof Studente && !((Studente) p).promosso()) {
				ris.add(p);
			}
		}
		
		return ris;
	}
//
//	ArrayList<Persone> studentiBilingue(int classe, String sezione)
//	// tutti gli studenti bilingue della specifica classe/sezione, in caso non ci siano,
//	// va bene l'arraylist vuoto
	public ArrayList<Persona> studentiBilingue(int classe, String sezione) {
		ArrayList<Persona> ris = new ArrayList<>();
		
		for (Persona p: persone) {
			if (p instanceof Studente) {
				Studente stud = (Studente) p;
				if (stud.getClasse() == classe 
						&& stud.getSezione().equalsIgnoreCase(sezione)
						&& stud.bilingue()) {
					ris.add(p);
				}
			}
		}
		
		return ris;
	}
//
//	double numeroMedioDiInsufficienze()
	public double numeroMedioDiInsufficienze() {
		double somma = 0;
		int count = 0;
		
		for (Persona p: persone) {
			if (p instanceof Studente) {
				somma += ((Studente) p).nInsufficienze();
				count++;
			}
		}
		
		return count == 0 ? 0 : somma / count;
	}
	
//	
//	Studente scarsone() {
//		Lo studente con la media più bassa
//	}
	public Studente scarsone() {
		Studente ris = null;
		double mediaPiuBassa = 10;
		
		for (Persona p: persone) {
			if (p instanceof Studente) {
				double mediaStudente = ((Studente) p).media();
				if (mediaStudente < mediaPiuBassa) {
					mediaPiuBassa = mediaStudente;
					ris = (Studente) p;
				}
			}
		}
		
		return ris;
	}
	
	public ArrayList<Studente> scarsoni() {
		ArrayList<Studente> ris = new ArrayList<>();
		double mediaPiuBassa = 10;
		
		for (Persona p: persone) {
			if (p instanceof Studente) {
				double mediaStudente = ((Studente) p).media();
				if (mediaStudente <= mediaPiuBassa) {
					if (mediaStudente < mediaPiuBassa) {
						ris.clear();
						mediaPiuBassa = mediaStudente;
					}
					
					ris.add((Studente) p);
				}
			}
		}
		
		return ris;
	}
//
//	Studente secchione() {
//		Lo studente con la media più alta, se c'è più di uno studente con la stessa media
//		scegliere quello con il numero di voti alti (>= 9) maggiore
//	}
	
	public Studente secchione() {
		Studente ris = null;
		
		for (Persona p: persone) {
			if (p instanceof Studente) {
				Studente s = (Studente) p;
				
				if (ris == null) {
					// Appena trovo uno studente lo assegno a ris
					ris = s;
					continue;
				}
				
				if (s.media() > ris.media()) {
					ris = s;
				} else if (s.media() == ris.media()) {
					if (s.nVotoSopraNove() > ris.nVotoSopraNove()) {
						ris = s;
					}
				}
			}
		}
		
		return ris;
	}
//	
//	Docente bigMoneys() // Docente con la paga più alta Prende il primo che capita
	public Docente biMoneys() {
		Docente ris = null;
		
		for (Persona p: persone) {
			if (p instanceof Docente) {
				Docente d = (Docente) p;
				
				if (ris == null) {
					ris = d;
					continue;
				}
				
				if (d.costo() > ris.costo()) {
					ris = d;
				}
			}
		}
		
		return ris;
	}
//	
//	Docente multiTasking() // Docente con il numero di materie insegnate più alte
//
//	ArrayList<Persone> docentiCheInsegnano(String materia)
	public ArrayList<Persona> docentiCheInsegnano(String materia) {
		ArrayList<Persona> ris = new ArrayList<>();
		
		for (Persona p: persone) {
			if (p instanceof Docente && ((Docente) p).insegna(materia)) {
				ris.add(p);
			}
		}
		
		return ris;
	}
//	
//	String mediaVotiMateriaStudenti() la stringa che contiene tutti i mediaVotiMateria di ogni studente
//
//	// BONUS: level 2
//	ArrayList<Persone> studentiOrdinatiPerMedia()
	public ArrayList<Persona> studentiOrdinatiPerMedia() {
		ArrayList<Persona> ris = studenti();
		
		// Il casting è "sicuro" perché il metodo studenti
		// effettua i vari instanceof prima di aggiungere alla lista restituita
		ris.sort(
			(a, b) -> 
			Double.compare(
				((Studente) a).media(), 
				((Studente) b).media()
			)
		);
		
		return ris;
	}
	
	
//	ArrayList<Persone> ricercaPerResidenza(String residenza)
//	ArrayList<Persone> ricercaChiave(String chiave) // ricerca globale! Più globale possibile:
//	es Se cerco Italiano, deve tirarmi fuori gli studenti che hanno un voto con materia Italiano, ma
//		anche Docenti che insegnano quella materia
//	// BONUS: level 4
	
	public ArrayList<Persona> ricercaChiave(String chiave) {
		ArrayList<Persona> ris = new ArrayList<>();
		
		for (Persona p: persone) {
			if (p.rispecchiaChiave(chiave)) {
				ris.add(p);
			}
		}
		
		return ris;
	}
	
//	String classeMigliore() // classe/sezione
//	è la classe che ha la media più alta (Media si calcola sulla media di ogni studente)
	String classeMigliore() {
		ArrayList<CalcolatoreMediaClasseSezione> mediePerClasseSezione = new ArrayList<>();
		
		for (Persona p: persone) {
			if (p instanceof Studente) {
				Studente s = (Studente) p;
				
				// Controllo se nella lista esiste già la media per la classe/sezione
				// dello studente che sto iterando
				
			
				CalcolatoreMediaClasseSezione cEsistente = null;
				
				for (CalcolatoreMediaClasseSezione c: mediePerClasseSezione) {
					if (c.dellaClasse(s.getClasse(), s.getSezione())) {
						cEsistente = c;
						break;
					}
				}
				
				if (cEsistente == null) {
					cEsistente = new CalcolatoreMediaClasseSezione(
							s.getSezione(), s.getClasse()
					);
					mediePerClasseSezione.add(cEsistente);
				}
				
				cEsistente.aggiungiMediaStudente(s.media());
			}
		}
		
		double mediaMigliore = 0;
		String ris = "";
		
		for (CalcolatoreMediaClasseSezione cmcs: mediePerClasseSezione) {
			if (cmcs.mediaClasse() > mediaMigliore) {
				mediaMigliore = cmcs.mediaClasse();
				ris = cmcs.getClasseSezione();
			}
		}
		
		return ris;
	}
	
}
