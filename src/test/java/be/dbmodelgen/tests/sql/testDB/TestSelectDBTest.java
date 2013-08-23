package be.dbmodelgen.tests.sql.testDB;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import junit.framework.TestCase;

import org.dynamicschema.context.ContextedQueryBuilder;
import org.dynamicschema.context.RelationalContextManager;
import org.dynamicschema.reification.ContextedTable;
import org.dynamicschema.reification.DBTable;
import org.dynamicschema.reification.Relation;
import org.dynamicschema.reification.Schema;
import org.dynamicschema.visitor.context.QueryFilteringSpecifier;

import be.dbmodelgen.tests.sql.ClientAgeFiltering;
import be.dbmodelgen.tests.sql.FoodTypeFiltering;
import be.dbmodelgen.tests.sql.LanguageFilteringTest;
import be.dbmodelgen.tests.sql.testDB.TestRelationModel.ClientRelations;

public class TestSelectDBTest extends TestCase{

	private final String PROP_FILE = "schema.properties";

	private  final String SCHEMA_TYPE_RELATIONAL= "relational";
	private  final String SCHEMA_TYPE_CONCEPTUAL= "conceptual";

	private Properties prop;


	private Schema sch ;

	protected void setUp() throws Exception {

		super.setUp();

		try {

			prop = new Properties();
			prop.load(new FileInputStream(PROP_FILE));	

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			fail(e1.getMessage());
		}  

	}

	protected void tearDown() throws Exception {
		super.tearDown();
		

	}

	
	public final void testSelectRestaurant(){
		
		
		try {
			System.setOut(new PrintStream("Output\\testSelectRestaurant.txt"));
		} catch (FileNotFoundException e) {
			fail("fail: "+ e.getMessage());
		}
		
		Schema sch= new TestAppSchema();
		DBTable rest =  sch.getTable(RestaurantTable.NAME);
		
//		Relation rel = ClientRelations.rel_Restaurant_Client;
//		List<Relation> relations2Traverse = new ArrayList<Relation>();
//		relations2Traverse.add(rel);
		
		ContextedQueryBuilder qb =  null;
		qb = rest.select();
		assert(qb != null);
		
		System.out.println(qb);
		
	}
	
	public final void testSelectRestaurantQuerySpecificFilter(){
		
		
		try {
			System.setOut(new PrintStream("Output\\testSelectRestaurantQueryFilter.txt"));
		} catch (FileNotFoundException e) {
			fail("fail: "+ e.getMessage());
		}
		
		Schema sch= new TestAppSchema();
		DBTable rest =  sch.getTable(RestaurantTable.NAME);
		
		//Set global filterings;
		DBTable lang = sch.getTable(LanguageTable.NAME);
		lang.setFiltering(new LanguageFilteringTest(1));
		
		//Set specific query filters 
		QueryFilteringSpecifier specifier = new QueryFilteringSpecifier();
		specifier.addQuerFiltering(TestRelationModel.MealRelations.rel_Restaurant_Meal, rest, new FoodTypeFiltering("chinese"));
		Relation rel = ClientRelations.rel_Restaurant_Client;
		List<Relation> relations2Traverse = new ArrayList<Relation>();
		relations2Traverse.add(rel);
		
		specifier.addQuerFiltering(rel, sch.getTable(ClientTable.NAME), new ClientAgeFiltering(10));
		
		ContextedQueryBuilder qb =  null;
		qb = rest.select(relations2Traverse,specifier);
		assert(qb != null);
		
		System.out.println(qb);
		
		
		RelationalContextManager ctx =  qb.getRelationalContext();
		Map<ContextedTable, ContextedTable> depend =  ctx.getTablesDependencies();

		for( ContextedTable table : depend.keySet()){
			ContextedTable dependencies = depend.get(table);
			System.out.println("Dependencies for " + table.getAlias() + " " + dependencies.getAlias());

		}	
		
	}
	
	
	public final void testSelectMeal(){
		
		
		try {
			System.setOut(new PrintStream("Output\\testSelectMeal.txt"));
		} catch (FileNotFoundException e) {
			fail("fail: "+ e.getMessage());
		}
		
		Schema sch= new TestAppSchema();
		DBTable meal =  sch.getTable(MealTable.NAME);
		
		Relation rel = ClientRelations.rel_Restaurant_Client;
		List<Relation> relations2Traverse = new ArrayList<Relation>();
		relations2Traverse.add(rel);
		
		ContextedQueryBuilder qb =  null;
		qb = meal.select(relations2Traverse);
		assert(qb != null);
		
		System.out.println(qb);
		
		RelationalContextManager ctx =  qb.getRelationalContext();
		Map<ContextedTable, ContextedTable> depend =  ctx.getTablesDependencies();

		for( ContextedTable table : depend.keySet()){
			ContextedTable dependencies = depend.get(table);
			System.out.println("Dependencies for " + table.getAlias() + " " + dependencies.getAlias());

		}	
		
	}
	
	
	
	public final void testSelectLang(){
		
		
		try {
			System.setOut(new PrintStream("Output\\testSelectLang.txt"));
		} catch (FileNotFoundException e) {
			fail("fail: "+ e.getMessage());
		}
		
		Schema sch= new TestAppSchema();
		DBTable lang =  sch.getTable(LanguageTable.NAME);
		
		Relation rel = ClientRelations.rel_Restaurant_Client;
		List<Relation> relations2Traverse = new ArrayList<Relation>();
		relations2Traverse.add(rel);
		
		ContextedQueryBuilder qb =  null;
		qb = lang.select(relations2Traverse);
		assert(qb != null);
		
		System.out.println(qb);
		
		
		RelationalContextManager ctx =  qb.getRelationalContext();
		Map<ContextedTable, ContextedTable> depend =  ctx.getTablesDependencies();

		for( ContextedTable table : depend.keySet()){
			ContextedTable dependencies = depend.get(table);
			System.out.println("Dependencies for " + table.getAlias() + " " + dependencies.getAlias());

		}	
		
	}
	

}
