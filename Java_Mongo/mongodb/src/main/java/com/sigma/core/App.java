package com.sigma.core;
import java.net.UnknownHostException;
import java.util.*;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * Hello world!
 *
 */
public class App 
{
	
	static Scanner user_input = new Scanner(System.in);
	static String current_input = "INVALID";
	static String desiredDB;
	static String desiredCollection;
	
    public static void main( String[] args )
    {
        System.out.println("Beggining MongoDB test..." );
        while(current_input.equals(new String("INVALID"))){
        	System.out.println("Please enter desired Database:");
        	current_input = user_input.next();
        }
        desiredDB = current_input;
        current_input = "INVALID";
       
        while(current_input.equals(new String("INVALID"))){
        	System.out.println("Please enter desired Collection:");
        	current_input = user_input.next();
        }
        desiredCollection = current_input;
        
        searchMongo(desiredDB, desiredCollection);
        
        
        System.out.println("Finished MongoDB test...");
    }
    
    public static void searchMongo(String targetDB, String targetCollection) 
    {
    	
    	boolean foundDB = false;
    	try {
			MongoClient mongo = new MongoClient( "localhost", 27017);
	        
	        //List All Databases
			System.out.println("Database List (Searching for "+targetDB+"):");
	        List<String> dbList = mongo.getDatabaseNames();
	        for(String db : dbList) {

	        	if(db.equals(targetDB)) {
	        		System.out.println("\t" + db + " - Target.");
	        		foundDB = true;
	        	} else {
		        	System.out.println("\t" + db);	        		
	        	}
	        }
	        
	        //List Out Collections in Database
	        System.out.println("Collection List (Searching for "+targetCollection+"):");
	        if(foundDB) {
	        	DB db = mongo.getDB(targetDB);
	        	DBCollection coll = db.getCollection(targetCollection);
	        	DBObject obj = coll.findOne();
	        	System.out.println("\t" + obj.toString());
	        } else {
	        	System.out.println("Target Database not found.");
	        }
	        
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("!! ERROR: Cannot create Mongo Client. !!");
			e.printStackTrace();
		}        	
    }
}
