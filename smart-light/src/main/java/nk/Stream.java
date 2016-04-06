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

public class Stream {
	
	public static class Passant implements Serializable {
		
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
	

	public static void streamMethode() throws Exception{		
	    final StreamExecutionEnvironment env =
	        StreamExecutionEnvironment.createLocalEnvironment();
	
	    //Read from a socket stream at map it to Passant objects
	    DataStream<Passant> socketStream = env
	            .socketTextStream("localhost", 9999)
	            .map(new MapFunction<String, Passant>() {
	                private String[] tokens;
	
	                @Override
	                public Passant map(String value) throws Exception {
	                    tokens = value.split(",");
	                    return new Passant(Double.parseDouble(tokens[0]),
	                        Double.parseDouble(tokens[1]));
	                }
	            });
	    
	    AllWindowedStream<Passant, TimeWindow> timeWindowStream  = socketStream.timeWindowAll(Time.seconds(5));
	
	    timeWindowStream.apply (new AllWindowFunction<Passant,Integer, TimeWindow>() {
			@Override
			public void apply(TimeWindow window, Iterable<Passant> passanten, Collector<Integer> out) throws Exception {
				System.out.println("Zwischen " + window.getStart() + " und " + window.getEnd() + " gab es folgende Koordinaten: ");
				for(Passant p : passanten){
					System.out.println("X: " + p.x + " - Y: " + p.y);
				}
			}
	    });
	    
	    env.execute("Stock stream");
	}
}
