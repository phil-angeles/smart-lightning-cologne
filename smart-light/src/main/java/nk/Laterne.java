package nk;

import java.util.ArrayList;
import java.util.List;

public class Laterne {
	/*
	 * Attribute
	*/
	private double x;
	private double y;
	private double verbrauch;
	private double leuchtdauer = 0;
	private boolean status;
	
	public static final double MIN_X = 3553.599309083569;
	public static final double MAX_X = 25520.067777469932;
	public static final double MIN_Y = 836.601818791716;
	public static final double MAX_Y = 31921.914833250783;
	
	/*
	 * Erzeuge Laternen mit zufaelligen x,y
	 */
	public static List<Laterne> erzeugeLaternen(){
		List<Laterne> laternenListe = new ArrayList<>();
		
		for(int index = 0; index < 6000; index++){
			Laterne laterne = new Laterne();
			laterne.setStatus(true);
			laterne.setVerbrauch(22.0);
			laterne.setX(randomWithRange(MIN_X, MAX_X));
			laterne.setY(randomWithRange(MIN_Y, MAX_Y));
			
			laternenListe.add(laterne);
		}
		return laternenListe;
	}
	
	static double randomWithRange(double min, double max)
	{
		double range = (max - min) + 1;     
		return (double) (Math.random() * range) + min;
	}
	
	/*
	 * getter, setter
	 */

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVerbrauch() {
		return verbrauch;
	}

	public void setVerbrauch(double verbrauch) {
		this.verbrauch = verbrauch;
	}

	public double getLeuchtdauer() {
		return leuchtdauer;
	}

	public void setLeuchtdauer(double leuchtdauer) {
		this.leuchtdauer = leuchtdauer;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
