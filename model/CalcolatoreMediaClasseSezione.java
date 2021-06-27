package model;

public class CalcolatoreMediaClasseSezione {
	private String sezione;
	private int classe;
	private double sommaMedie;
	private int nStudenti;

	public CalcolatoreMediaClasseSezione(String sezione, int classe) {
		super();
		this.sezione = sezione;
		this.classe = classe;
		this.sommaMedie = 0;
		this.nStudenti = 0;
	}
	
	public boolean dellaClasse(int classe, String sezione) {
		return this.classe == classe && this.sezione.equals(sezione);
	}
	
	public double mediaClasse() {
		return sommaMedie / nStudenti;
	}
	
	public void aggiungiMediaStudente(double mediaStudente) {
		sommaMedie += mediaStudente;
		nStudenti++;
	}
	
	public String getClasseSezione() {
		return classe + " " + sezione;
	}

}
