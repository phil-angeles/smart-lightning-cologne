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
					.expireAfterAccess(10, TimeUnit.SECONDS)
					.build(new CacheLoader<Integer, Laterne>(){ // build the cacheloader

					@Override
					public Laterne load(Integer arg0) throws Exception {
						return getLaterneByID(arg0);
					} 
			            });
	  }
	  
	  private static Laterne getLaterneByID(Integer arg0){
		  laternenListe = Laterne.erzeugeLaternen();
		  
		  return laternenListe.get(arg0.intValue());
	  }
	  
	  public static LoadingCache<Integer, Laterne> getLoadingCache() {
			return cache;
	  }
	 
	  public Laterne getEntry( Integer key ) {
	    return cache.getUnchecked( key );
	  }
}
