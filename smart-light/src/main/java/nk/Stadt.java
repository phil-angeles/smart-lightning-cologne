package nk;

import java.util.ArrayList;
import java.util.List;

public class Stadt {
	
	public static void main(String[] args) {
		List<Laterne> laternenListe = new ArrayList<>();
		
		laternenListe = Laterne.erzeugeLaternen();
		
		for(int index = 0; index < laternenListe.size(); index++){
			Laterne laterne = laternenListe.get(index);
			System.out.println("Laterne " + index);
			System.out.println("X : " + laterne.getX());
			System.out.println("Y : " + laterne.getY());
		}
	}
}
