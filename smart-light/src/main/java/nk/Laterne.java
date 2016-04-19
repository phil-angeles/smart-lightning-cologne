package nk;

import java.util.HashMap;
import java.util.Map;

public class Laterne {
	/*
	 * Attribute
	*/
	private int laternenID;
	private double lat;
	private double lon;
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
	public static Map<Integer, Laterne> erzeugeLaternen(){
		Map<Integer, Laterne> laternenListe = new HashMap<>();
		Integer zaehler = 1;
		
		for(int index = 0; index < 6000; index++){
			Laterne laterne = new Laterne();
			laterne.setStatus(false);
			laterne.setVerbrauch(22.0);
			getKoordinaten(laterne, zaehler);
			laterne.setLaternenID(zaehler);
			
			laternenListe.put(zaehler.intValue(), laterne);
			zaehler++;
		}
		return laternenListe;
	}
	
	static double randomWithRange(double min, double max)
	{
		double range = (max - min) + 1;     
		return (double) (Math.random() * range) + min;
	}
	
	
	public static Laterne getKoordinaten(Laterne laterne, int i) {
		laterne.setLat(Double.parseDouble(MyCSVReader.completeList.get(i*100)[3]));
		laterne.setLon(Double.parseDouble(MyCSVReader.completeList.get(i*100)[4]));
			
		return laterne;
	}
	
	
	/*
	 * getter, setter
	 */

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
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

	public int getLaternenID() {
		return laternenID;
	}

	public void setLaternenID(int laternenID) {
		this.laternenID = laternenID;
	}
	
}
