package nk;

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
		
		//Ausgangslage: an 6000 Stellen in der Stadt, haben wir Laternen.
		//Stream von Positionsdaten der Autos kommt rein ... dann muss abgefragt werden ob die in der Nähe der Laternen ein Auto vorbeifährt ...
		//Daten über Socket
		
		//x und y der Autos sind f3 und f4
		//falls JA --> Laterne anmachen
		//DIe Laternen durch den Stream schieben und auf Match abfragen		
				
		
	}
}
