package nk;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public class LampenAnOderAusClass {
	private static Map<Integer, Laterne> laternen;
	
	public static int aktiviertZeit = 0;
	
	static {
		laternen = Laterne.erzeugeLaternen();
	}
	
	public static boolean isInDistance(double x, double y) throws ExecutionException {
		boolean ergebnis = false;
		int num = 0;
		for(int index = 1; index < laternen.size(); index++){
			double distanz = Math.sqrt(Math.pow(x-laternen.get(index).getX(), 2)+Math.pow(y-laternen.get(index).getY(), 2));
			if(distanz < 100 ){
				//System.out.println(x + " " + y + " in der Nähe von " + laternen.get(index).getX() + " " + laternen.get(index).getY());
				// Lampe anmachen
				ergebnis = true;
				num++;
				aktiviertZeit++;
			}
		}
		System.out.println(aktiviertZeit);
		return ergebnis;
	}
}
