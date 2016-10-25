package com.sigma.core;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	
	static String desiredDB = "testBase";
	static String desiredCollection = "Games";
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    // Test Connection to DataBase
    public void testDataBaseConnection()
    {	
    	try {
			MongoClient mongo = new MongoClient( "localhost", 27017);
			assertTrue( true );
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("!! ERROR: Cannot create Mongo Client. !!");
			e.printStackTrace();
			assertFalse( true );
		}        	
    }
    
    // Test fetching databases
    public void testFetchDataBases() {
    	boolean foundDB = false;
    	try {
	    	MongoClient mongo = new MongoClient( "localhost", 27017);
	        
	        //List All Databases
			System.out.println("Database List (Searching for "+desiredDB+"):");
	        List<String> dbList = mongo.getDatabaseNames();
	        for(String db : dbList) {
	
	        	if(db.equals(desiredDB)) {
	        		System.out.println("\t" + db + " - Target.");
	        		foundDB = true;
	        	} else {
		        	System.out.println("\t" + db);	        		
	        	}
	        }
    	} catch(Exception e) {
    		System.out.println("!! ERROR: Cannot list databases !!");
        	assertTrue( false );
    	}
        
        if(foundDB) {
        	assertTrue( true );
        } else {
        	System.out.println("!! ERROR: Cannot list databases !!");
        	assertTrue( false );
        }
    }
    
    // Test fetching Collections.
    public void testFetchCollections() {
    	boolean foundDB = false;
    	try {
			MongoClient mongo = new MongoClient( "localhost", 27017);
	        
	        //List All Databases
			System.out.println("Database List (Searching for "+desiredDB+"):");
	        List<String> dbList = mongo.getDatabaseNames();
	        for(String db : dbList) {

	        	if(db.equals(desiredDB)) {
	        		System.out.println("\t" + db + " - Target.");
	        		foundDB = true;
	        	} else {
		        	System.out.println("\t" + db);	        		
	        	}
	        }
	        
	        //List Out Collections in Database
	        System.out.println("Collection List (Searching for "+desiredCollection+"):");
	        if(foundDB) {
	        	DB db = mongo.getDB(desiredDB);
	        	DBCollection coll = db.getCollection(desiredCollection);
	        	DBObject obj = coll.findOne();
	        	System.out.println("\t" + obj.toString());
	        	assertTrue( true );
	        } else {
	        	System.out.println("Target Database not found.");
	        }
	        
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("!! ERROR: Cannot List Collections !!");
	    	assertFalse( true );
			e.printStackTrace();
		}        	
    	
    }
    
}
