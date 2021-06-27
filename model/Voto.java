package model;

public class Voto {

	private String materia;
	private double punteggio;

	public Voto(String materia, double punteggio) {
		super();
		this.materia = materia;
		this.punteggio = punteggio;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public double getPunteggio() {
		return punteggio;
	}

	public void setPunteggio(double punteggio) {
		this.punteggio = punteggio;
	}

	@Override
	public String toString() {
		return "materia: " + materia + ", punteggio: " + punteggio;
	}

}
