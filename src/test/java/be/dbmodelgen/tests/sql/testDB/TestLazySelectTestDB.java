package be.dbmodelgen.tests.sql.testDB;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.dynamicschema.context.ContextedQueryBuilder;
import org.dynamicschema.reification.DBTable;
import org.dynamicschema.reification.Schema;
import org.dynamicschema.reification.TableRelation;

import be.dbmodelgen.tests.sql.testDB.RestaurantTable.RestaurantColumns;
import be.dbmodelgen.tests.sql.testDB.TestRelationModel.ClientRelations;
import be.dbmodelgen.tests.sql.testDB.TestRelationModel.FidelityCardRelations;

public class TestLazySelectTestDB extends TestCase {

	protected void setUp() throws Exception {
		super.setUp(); 
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	
	public final void testLazySelectRestaurantClient(){
		
		try {
			System.setOut(new PrintStream("Output\\testLazySelectRestaurant.txt"));
		} catch (FileNotFoundException e) {
			fail("fail: "+ e.getMessage());
		}
		
		Schema sch= new TestAppSchema();
		DBTable rest =  sch.getTable(RestaurantTable.NAME);
		
//		Relation rel = ClientRelations.rel_Restaurant_Client;
//		List<Relation> relations2Traverse = new ArrayList<Relation>();
//		relations2Traverse.add(rel);
		
		TableRelation tabRelRestCl = rest.getTabRelation(ClientRelations.RESTAURANT_CLIENT, null);
		ContextedQueryBuilder qb =  null;
		Map<String, Object> bindings = new HashMap<String, Object>();
		bindings.put(RestaurantColumns._ID, new Integer(1));
		qb = rest.lazyRelationSelect(tabRelRestCl, bindings);
		
		assert(qb != null);
		
		System.out.println(qb);
		
	}
	
	
	
	public final void testLazySelectRestaurant2(){
		
		try {
			System.setOut(new PrintStream("Output\\testLazySelectRestaurant2.txt"));
		} catch (FileNotFoundException e) {
			fail("fail: "+ e.getMessage());
		}
		
		Schema sch= new TestAppSchema();
		DBTable rest =  sch.getTable(RestaurantTable.NAME);
		
//		Relation rel = ClientRelations.rel_Restaurant_Client;
//		List<Relation> relations2Traverse = new ArrayList<Relation>();
//		relations2Traverse.add(rel);
		
		TableRelation tabRelRestCl = rest.getTabRelation(FidelityCardRelations.RESTAURANT_FIDELITYCARD, null);
		ContextedQueryBuilder qb =  null;
		Map<String, Object> bindings = new HashMap<String, Object>();
		bindings.put(RestaurantColumns._ID, new Integer(10));
		qb = rest.lazyRelationSelect(tabRelRestCl, bindings);
		
		assert(qb != null);
		
		System.out.println(qb);
		
	}
}
