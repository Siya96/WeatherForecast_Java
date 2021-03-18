package lab2.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.w3c.dom.Document;

/**
 * 
 * @author Siyabend Revend
 *
 * @param <K>
 * @param <T>
 */

/*
 * This class only handles cache/ caching data.
 */
public class Cache<K, T> {

	
	private HashMap<K, CacheElement> Hash_Map;
	private long timeToLive;
	private long timeInterval;
	private boolean isEmpty;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
	}
	/**
	 * 
	 * @author Siyabend Revend
	 *
	 * @param <T>
	 */
	
	/*
	 * This is a private class. An object of this class stores a time give in seconds and a value of the generic type <T>.
	 */
	private class  CacheElement<T> {
		
		private T value;
		private long time;
		
		
		public CacheElement(T value) {
			
			this.value = value;
			this.time = System.currentTimeMillis();
			
			
		}
		
	}
	
	
	/**
	 * This is the constructor. In the constructor we declare our hash-map, and create a thread of the type Thread.
	 * @param cacheTimeToLive 
	 * @param cacheTimerInterval 
	 */
	public Cache(long cacheTimeToLive,  long cacheTimerInterval) {
		
		timeToLive = (cacheTimeToLive * 1000);
		timeInterval = (cacheTimerInterval * 1000);
		
		Hash_Map = new HashMap<>();
		
		if (timeToLive > 0 && timeInterval > 0) {
			
			//Creating a thread to create a thread of execution throughout the program. Threads with higher priority are executed
			//in preference to threads with lower priority.
			Thread thread = new Thread(new Runnable() {
				
				public void run() {
					
					while (true) {
						
						try {
							
							Thread.sleep(timeInterval);
						}
						catch (InterruptedException e) {
							
						}
						cleanup();
					}
				}
				
			});
			//setDaemon has to be invoked for the thread to shutdown when now "higher priority" program is running anymore.
			thread.setDaemon(true);
			thread.start();
		}
		
		
	}
	
	/**
	 * This method stores a key (the city name) and a value (the Xml-document).
	 * @param key
	 * @param value
	 */
	public void put(K key, T value) {
		
		//Synchronized is used so that the method that first called upon Hash_Map will have access to it so that everything is done 
		//in order and not simultaneously.
		synchronized(Hash_Map) {
			
			CacheElement newValue = new CacheElement (value);
			Hash_Map.put(key,  newValue);
			
		}
	}
	
	/**
	 * 
	 * @param key
	 * @return the value of a given key
	 */
	@SuppressWarnings("unchecked")
	public T get(K key) {
		
		synchronized(Hash_Map) {
			
			CacheElement c = (CacheElement) Hash_Map.get(key);
			
			if (c == null) {
				
				return null;
				
			}
			else {
				
//				c.time = System.currentTimeMillis();

				return (T) c.value;
			}
		}
	}
	
	/**
	 * Removes the key and any particular values mapped to the given key.
	 * @param key
	 */
	public void remove(K key) {
		
		synchronized(Hash_Map) {
			
			Hash_Map.remove(key);
		}
	}
	
	/**
	 * 
	 * @return the size to the Hash_Map
	 */
	public int size() {
		
		synchronized(Hash_Map) {
			
			return Hash_Map.size();
		}
	}
	
	/**
	 * This method deletes the key and the value after a certain time (time to live). This method is also called upon
	 * in the constructor.
	 */
	public void cleanup() {
		
		long now = System.currentTimeMillis();
		ArrayList<K> deleteKey = null;
		
		synchronized(Hash_Map) {
			
			//Creating an iterator object so that it may be possible to iterate through every pair in the HashMap.
			Iterator<Map.Entry<K, CacheElement>> iterator = Hash_Map.entrySet().iterator();
			
			
			
			//Setting the 
			deleteKey = new ArrayList<K> ((Hash_Map.size() / 2) + 1);
			K key = null;
			CacheElement value = null;
			
			while (iterator.hasNext()) {
				
				
				//Retrieves the pair which we eventually extract a key and value from
				Map.Entry<K, CacheElement> entry = iterator.next();
				key = entry.getKey();
				value = entry.getValue();
				
				//Checks in case we pass the "timeToLive"-Mark
				if (value != null && (now > (timeToLive + value.time))) {
					
					deleteKey.add(key);
				}
			}
		}
		
		
		for (K key : deleteKey) {
			
			synchronized(Hash_Map) {
				
				Hash_Map.remove(key);
			}
			Thread.yield();
		}
	}
	
	
	/**
	 * This method sets a new "time to live".
	 * @param newTime
	 */
	public void setNewTimeToLive(int newTime) {
		
		timeToLive = (newTime * 1000);
	}
	
	
	
	
		
		
		
		
		
	
	
	
	
	

}
