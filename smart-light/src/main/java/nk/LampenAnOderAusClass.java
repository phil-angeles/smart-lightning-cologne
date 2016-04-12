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
		Spark.get("/getLaternenPositionen", (req, res) -> {
			JSONArray jarray = new JSONArray();
			for(int index = 1; index < laternen.size(); index++){
				JSONObject jobj = new JSONObject();
				jobj.put("ID", laternen.get(index).getLaternenID());
				jobj.put("X", laternen.get(index).getX());
				jobj.put("Y", laternen.get(index).getY());
				jarray.put(jobj);
			}
			return jarray;
		});
		Spark.get("/getLaternenAn", (req, res) -> {
			JSONArray jarray = new JSONArray();
			for(int index = 1; index < laternen.size(); index++){
				if(laternen.get(index).isStatus()){
					jarray.put(laternen.get(index).getLaternenID());
				}
			}
			return jarray;
		});
	}
	
	public static void isInDistance(double x, double y) throws ExecutionException {
		int num = 0;
		for(int index = 1; index < laternen.size(); index++){
			double distanz = Math.sqrt(Math.pow(x-laternen.get(index).getX(), 2)+Math.pow(y-laternen.get(index).getY(), 2));
			if(distanz < 100 &&  !laternen.get(index).isStatus()){
				//System.out.println(x + " " + y + " in der Nähe von " + laternen.get(index).getX() + " " + laternen.get(index).getY());
				// Lampe anmachen
				laternen.get(index).setStatus(true);
				num++;
				aktiviertZeit++;
			}
		}
		System.out.println(aktiviertZeit);
	}
	
	public static void disableLaternen(){
		for(int index = 1; index < laternen.size(); index++){
			laternen.get(index).setStatus(false);
		}
	}
}
