package nk;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import spark.Spark;

public class LampenAnOderAusClass {
	private static Map<Integer, Laterne> laternen;
	
	public static int aktiviertZeit = 0;
	
	static {
		laternen = Laterne.erzeugeLaternen();
		
		Spark.externalStaticFileLocation("src/main/resources/webroot");
		Spark.get("/getLaternenPositionen", (req, res) -> {
			JSONArray jarray = new JSONArray();
			for(int index = 1; index < laternen.size(); index++){
				JSONObject jobj = new JSONObject();
				jobj.put("lat", laternen.get(index).getLat());
				jobj.put("lon", laternen.get(index).getLon());
				jarray.put(jobj);
			}
			return jarray;
		});
		Spark.get("/getLaternen", (req, res) -> {
			JSONArray jarray = new JSONArray();
			for(int index = 1; index < laternen.size(); index++){
				JSONObject jobj = new JSONObject();
				jobj.put("lat", laternen.get(index).getLat());
				jobj.put("lon", laternen.get(index).getLon());
				jobj.put("an", laternen.get(index).isStatus());
				jarray.put(jobj);
			}
			return jarray;
		});
	}
	
	public static void isInDistance(double lat, double lon) throws ExecutionException {
		for(int index = 1; index < laternen.size(); index++){
			double distanz = distanceInKm(lat, lon, laternen.get(index).getLat(), laternen.get(index).getLon());
			if(distanz < 0.1 &&  !laternen.get(index).isStatus()){
				//System.out.println(x + " " + y + " in der Nähe von " + laternen.get(index).getX() + " " + laternen.get(index).getY());
				// Lampe anmachen
				laternen.get(index).setStatus(true);
				aktiviertZeit++;
			}
		}
	}
	
	public static double distanceInKm(double lat1, double lon1, double lat2, double lon2) {
	    int radius = 6371;

	    double lat = Math.toRadians(lat2 - lat1);
	    double lon = Math.toRadians(lon2- lon1);
	    
	    double latSin = Math.sin(lat / 2);
	    double lonSin = Math.sin(lon / 2);
	    
	    double a = latSin * latSin + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * lonSin * lonSin;
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double d = radius * c;

	    return Math.abs(d);
	}
	
	public static void disableLaternen(){
		for(int index = 1; index < laternen.size(); index++){
			laternen.get(index).setStatus(false);
		}
	}
}
