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

import com.google.common.cache.LoadingCache;

public class Stream implements Serializable{
	
	
	public static class Passant implements Serializable {

		private static final long serialVersionUID = 5057669295633869950L;
		public Double x;
	    public Double y;

	    public Passant() {
	    }

	    public Passant(Double x, Double y) {
	        this.x = y;
	        this.y = y;
	    }

		@Override
		public String toString() {
			return "X: " + x + " - Y: " + y;
		}
	}
	

	public void streamMethode() throws Exception{		
	    final StreamExecutionEnvironment env =
	        StreamExecutionEnvironment.createLocalEnvironment();
	    	
	    //Read from a socket stream at map it to Passant objects
	    DataStream<Passant> socketStream = env
	            .socketTextStream("localhost", 9999)
	            .map(new MapFunction<String, Passant>() {
	                /**
					 * 
					 */
					private static final long serialVersionUID = -392827836269632226L;
					private String[] tokens;
	
	                @Override
	                public Passant map(String value) throws Exception {
	                    tokens = value.split(",");
	                    return new Passant(Double.parseDouble(tokens[0]),
	                        Double.parseDouble(tokens[1]));
	                }
	            });
	    
	    AllWindowedStream<Passant, TimeWindow> timeWindowStream  = socketStream.timeWindowAll(Time.seconds(10));
	
	    timeWindowStream.apply (new AllWindowFunction<Passant,Integer, TimeWindow>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -1100458040685523280L;

			@Override
			public void apply(TimeWindow window, Iterable<Passant> passanten, Collector<Integer> out) throws Exception {
				System.out.println("\nZwischen " + window.getStart() + " und " + window.getEnd() + " gab es folgende Koordinaten: ");
				for(Passant p : passanten){
					LampenAnOderAusClass.isInDistance(p.x, p.y);
				}
				LampenAnOderAusClass.disableLaternen();
			}
	    });
	    
	    env.execute("Stock stream");
	}
}
