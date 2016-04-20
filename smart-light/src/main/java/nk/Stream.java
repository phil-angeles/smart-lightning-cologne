package nk;

import java.io.Serializable;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.AllWindowedStream;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.AllWindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

public class Stream implements Serializable{
	private static final long serialVersionUID = -6849652885122711975L;
	public static final int WAITTIME = 500;
	
	
	public class Passant implements Serializable {
		private static final long serialVersionUID = 5057669295633869950L;
		public Double lat;
	    public Double lon;

	    public Passant() {
	    }

	    public Passant(Double lat, Double lon) {
	        this.lat = lat;
	        this.lon = lon;
	    }

		@Override
		public String toString() {
			return "Lat: " + lat + " - Lon: " + lon;
		}
	}
	

	public void start() {		
		
	    final StreamExecutionEnvironment env =
	        StreamExecutionEnvironment.createLocalEnvironment(4);
	    	
	    //Read from a socket stream at map it to Passant objects
	    DataStream<Passant> socketStream = env
	            .socketTextStream("localhost", 9999)
	            .map(new MapFunction<String, Passant>() {
					private static final long serialVersionUID = -392827836269632226L;
					private String[] tokens;
	
	                @Override
	                public Passant map(String value) throws Exception {
	                    tokens = value.split(",");
	                    return new Passant(Double.parseDouble(tokens[0]),
	                        Double.parseDouble(tokens[1]));
	                }
	            });
	    
	    AllWindowedStream<Passant, TimeWindow> timeWindowStream  = socketStream.timeWindowAll(Time.milliseconds(WAITTIME));
	
	    timeWindowStream.apply(new AllWindowFunction<Passant,Integer, TimeWindow>() {
			private static final long serialVersionUID = -1100458040685523280L;

			@Override
			public void apply(TimeWindow window, Iterable<Passant> passanten, Collector<Integer> out) throws Exception {
				// Laternen abschalten
				LaternenVerwaltung.disableLaternen();
				
				// Die Laternen anschalten, in deren Nähe ein Passant ist
				for(Passant p : passanten){
					LaternenVerwaltung.checkLighting(p.lat, p.lon);
				}
				
				// Rückgabearray zusammenbauen
				LaternenVerwaltung.buildLaternenArray();
				
				// Gesamte Zeit, die alle Laternen zusammen aktiviert waren
				System.out.println(LaternenVerwaltung.aktiviertZeit);
			}
	    });
	    
	    try {
			env.execute("Lighting stream");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
