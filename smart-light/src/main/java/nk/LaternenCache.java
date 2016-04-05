package nk;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class LaternenCache {
	private static final long MAX_SIZE = 6000;
	static Map<Integer, Laterne> laternenListe = new HashMap<>();
	 
	private static LoadingCache<Integer, Laterne> cache;
	
	static {
			  cache = CacheBuilder.newBuilder()
			  .maximumSize(MAX_SIZE)
			  .expireAfterAccess(10, TimeUnit.MINUTES)
			  .build(new CacheLoader<Integer, Laterne>(){ // build the cacheloader
				  
				@Override
				public Laterne load(Integer arg0) throws Exception {
					System.out.println("load");
					return getLaterneByID().get(arg0);
				}

				@Override
				public Map<Integer, Laterne> loadAll(Iterable<? extends Integer> keys) throws Exception {
					System.out.println("loadAll");
					return getLaterneByID();
				}
			  });
			  
			  cache.putAll(Laterne.erzeugeLaternen());
	}
	  
	  private static Map<Integer, Laterne> getLaterneByID(){
		  laternenListe = Laterne.erzeugeLaternen();
		  System.out.println("durchlaufen getLaterneID");
		  return laternenListe;
	  }
	  
	  public static LoadingCache<Integer, Laterne> getLoadingCache() {
		    System.out.println("durchlaufen");
			return cache;
	  }
	 
	  public Laterne getEntry( Integer key ) {
	    return cache.getUnchecked( key );
	  }
}
