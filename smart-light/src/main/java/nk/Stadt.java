package nk;

import java.util.ArrayList;
import java.util.List;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.io.CsvReader;
import org.apache.flink.api.java.tuple.Tuple13;

public class Stadt {
	
	public static void main(String[] args) {
		// set up the execution environment
			final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

		// get input data
			CsvReader csvReader = env.readCsvFile(WordCount.class.getResource("/cologne_data.csv").toString());
			csvReader.ignoreFirstLine();
		
		// data set
		DataSet<Tuple13<String, String, String, Double, Double, Double, 
		Double, Double, Double, Double, Double, Double, Double> > dataSet = 
		csvReader.types(String.class, String.class, String.class, Double.class, 
						Double.class, Double.class, Double.class, Double.class, 
						Double.class, Double.class, Double.class, Double.class, Double.class);
		
		//laternen
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
