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
	private boolean status;
	
	/*
	 * Erzeuge Laternen auf den Fahrzeugpositionen 100, 200, 300,...
	 */
	public static Map<Integer, Laterne> erzeugeLaternen(){
		Map<Integer, Laterne> laternenListe = new HashMap<>();
		
		for(int i = 0; i < 6000; i++){
			Laterne laterne = new Laterne();
			laterne.setStatus(false);
			laterne.setLat(Double.parseDouble(MyCSVReader.completeList.get(i*100)[3]));
			laterne.setLon(Double.parseDouble(MyCSVReader.completeList.get(i*100)[4]));
			laterne.setLaternenID(i);
			
			laternenListe.put(i, laterne);
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
