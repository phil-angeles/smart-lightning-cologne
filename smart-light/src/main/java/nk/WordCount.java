package nk;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.io.CsvReader;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple13;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.client.FlinkYarnSessionCli;
import org.apache.flink.optimizer.plan.FlinkPlan;
import org.apache.flink.runtime.FlinkActor;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.util.Collector;

/**
 * Implements the "WordCount" program that computes a simple word occurrence histogram
 * over some sample data
 *
 * <p>
 * This example shows how to:
 * <ul>
 * <li>write a simple Flink program.
 * <li>use Tuple data types.
 * <li>write and use user-defined functions.
 * </ul>
 *
 */
public class WordCount {

	//
	//	Program
	//

	
	
	public static void main(String[] args) throws Exception {
		// set up the execution environment
		final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

		// get input data
		CsvReader csvReader = env.readCsvFile(WordCount.class.getResource("/cologne_data.csv").toString());
		csvReader.ignoreFirstLine();


		/** ID Feldname Beispielwert
		 *
		 * 0 timestamp 21601000				geht von 21601000
		 * 										 bis 22100000
		 * 1 vehicle-id 34652_34652_352_0
		 * 2 vehicle-type pkw
		 * 3 location-x 20464.451731775236	geht von 3553.599309083569
		 * 										 bis 25520.067777469932
		 * 4 location-y 14788.902214063552  geht von 836.601818791716
		 * 										 bis 31921.914833250783
		 * 5 speed 0.0
		 * 6 fuel-consumption 0.23988551336146274
		 * 7 co2-emission 601.6927777777778
		 * 8 co-emission 6.37156111111111
		 * 9 hc-emission 0.34941999999999995
		 * 10 noise-emission 55.94027641010836
		 * 11 nox-emission 0.83951
		 * 12 pmx-emission 0.02126148611111111
		 */
		DataSet<Tuple13<String, String, String, Double, Double, Double, 
					Double, Double, Double, Double, Double, Double, Double> > dataSet = 
				csvReader.types(String.class, String.class, String.class, Double.class, 
								Double.class, Double.class, Double.class, Double.class, 
								Double.class, Double.class, Double.class, Double.class, Double.class);
		dataSet.max(4).print();
		dataSet.min(4).print(); 
		dataSet.max(3).print(); 
		dataSet.min(3).print(); 
		dataSet.min(0).print(); 
		dataSet.max(0).print();			
	}

	//
	// 	User Functions
	//

	/**
	 * Implements the string tokenizer that splits sentences into words as a user-defined
	 * FlatMapFunction. The function takes a line (String) and splits it into
	 * multiple pairs in the form of "(word,1)" (Tuple2<String, Integer>).
	 */
	public static final class LineSplitter implements FlatMapFunction<String, Tuple2<String, Integer>> {

		@Override
		public void flatMap(String value, Collector<Tuple2<String, Integer>> out) {
			// normalize and split the line
			String[] tokens = value.toLowerCase().split("\\W+");

			// emit the pairs
			for (String token : tokens) {
				if (token.length() > 0) {
					out.collect(new Tuple2<String, Integer>(token, 1));
				}
			}
		}
	}
}
