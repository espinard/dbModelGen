package be.dbmodelgen.tests.sql;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import junit.framework.TestCase;

import org.dynamicschema.context.ContextedQueryBuilder;
import org.dynamicschema.reification.DBTable;
import org.dynamicschema.reification.Relation;
import org.dynamicschema.reification.Schema;
import org.dynamicschema.reification.Table;
import org.dynamicschema.sql.SqlCondition;

import be.dbmodelgen.reification.gen.BelongingTable;
import be.dbmodelgen.reification.gen.ClientTable;
import be.dbmodelgen.reification.gen.ContenanceTable;
import be.dbmodelgen.reification.gen.Description2Table;
import be.dbmodelgen.reification.gen.DescriptionTable;
import be.dbmodelgen.reification.gen.GourmetRelationModel;
import be.dbmodelgen.reification.gen.GourmetRelationModel.DescriptionRelations;
import be.dbmodelgen.reification.gen.IngredientTable;
import be.dbmodelgen.reification.gen.LanguageTable;
import be.dbmodelgen.reification.gen.MealTable;
import be.dbmodelgen.reification.gen.Meal_RatingTable;
import be.dbmodelgen.reification.gen.PreferenceTable;
import be.dbmodelgen.reification.gen.ProhibitionTable;
import be.dbmodelgen.reification.gen.RegionTable;
import be.dbmodelgen.reification.gen.RequirementTable;
import be.dbmodelgen.reification.gen.RestaurantTable;
import be.dbmodelgen.reification.gen.SeasonTable;
import be.dbmodelgen.reification.gen.TasteTable;
import be.dbmodelgen.reification.gen.Taste_BelongingTable;
import be.dbmodelgen.reification.gen.Taste_PreferenceTable;
import be.dbmodelgen.reification.gen.User_ConstraintTable;
import be.dbmodelgen.reification.gen.VisitTable;
import be.dbmodelgen.tests.DefaultFilteringCondition;

public class TestSelect extends TestCase {

	//	private final String DB_PATH_SMALL = "jdbc:sqlite:D:\\Documents\\Dropbox\UCL\\Master22\\Thesis\\GourmetTestFiles\\gourmetSmall.sqlite";
	private final String DB_PATH_BIG_RECURSIVE = "jdbc:sqlite:D:\\Documents\\Dropbox\\UCL\\Master22\\Thesis\\GourmetTestFiles\\gourmetBigRecursive.sqlite";
	private final String PROP_FILE = "schema.properties";

	private  final String SCHEMA_TYPE_RELATIONAL= "relational";
	private  final String SCHEMA_TYPE_CONCEPTUAL= "conceptual";


	private Properties prop;
	private Properties propOut;

	private DBTable rest, meal, ingr, belonging, client, constraint, contenance,
	descrip, description2, lang, mealRating, preference, prohib,
	req, season,tasteBelong, tastePref, taste, visit, region;



	private Connection connection = null;  
	private ResultSet resultSet = null;  
	private Statement statement = null;  


	private Schema sch ;

	protected void setUp() throws Exception {

		super.setUp();

		try {

			prop = new Properties();
			propOut = new Properties();

			prop.load(new FileInputStream(PROP_FILE));	
			Class.forName("org.sqlite.JDBC");  

			propOut.setProperty(SCHEMA_TYPE_CONCEPTUAL, "ConceptualAndro");
			propOut.setProperty(SCHEMA_TYPE_RELATIONAL, "RelationalAndro");
			propOut.store(new FileOutputStream(PROP_FILE),null);
			connection = DriverManager.getConnection(DB_PATH_BIG_RECURSIVE);


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
		
		try {
			if(connection != null)
				connection.close();
			if(statement != null)
				statement.close();
			if(resultSet != null)
				resultSet.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public final void testEagerSelectFromRestaurant() {

		try {
			System.setOut(new PrintStream("Output\\outputSelectRest.txt"));

		}catch (Exception e1) {
			// TODO Auto-generated catch block

			fail(e1.getMessage());
		}  

		sch = new Schema();	
		List<DBTable> tables = initAllBig();
		GourmetRelationModel relModel = new GourmetRelationModel();
		sch.setTables(tables);
		relModel.updateTables(tables);
		sch.setRelationModel(relModel);
		ContextedQueryBuilder qb = null;
		qb = rest.select();
		assertTrue(qb != null);

		System.out.println(qb.toString());


		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(qb.toString());



			int cptCol = 0;
			int offset = qb.getRelationalContext().getQueryOffset();
			
//			while(resultSet.next()){
//				
//				for (int i = 0; i < offset; i++) {
//					System.out.print(resultSet.getString(i+1) + "\t");
//				}
//				System.out.println();
//				//				System.out.println("Row "+ cptCol);
//				//				System.out.println(resultSet.getString(2));
//				cptCol++;
//			}
//			System.out.println("Nb Rows: "+ cptCol);

		} catch (SQLException e) {
			fail(e.getMessage());
		}  
	}

	public final void testEagerSelectFromMeal() {

		try {

			System.setOut(new PrintStream("Output\\outputSelectMeal.txt"));

		}catch (Exception e1) {
			// TODO Auto-generated catch block

			fail(e1.getMessage());
		}  


		sch = new Schema();	
		List<DBTable> tables = initAllBig();
		GourmetRelationModel relModel = new GourmetRelationModel();
		sch.setTables(tables);
		relModel.updateTables(tables);
		sch.setRelationModel(relModel);
		ContextedQueryBuilder qb = null;
		
		qb = meal.select();
		
		assertTrue(qb != null);	
		System.out.println(qb.toString());
		
		System.out.println("Offset: " + qb.getRelationalContext().getQueryOffset());

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(qb.toString());

		} catch (SQLException e) {
			fail(e.getMessage());
		}  



	}
	
	
	public final void testEagerSelectFromMealDescription() {

		try {

			System.setOut(new PrintStream("Output\\outputSelectMealDescription.txt"));

		}catch (Exception e1) {
			// TODO Auto-generated catch block

			fail(e1.getMessage());
		}  


		sch = new Schema();	
		List<DBTable> tables = initAllBig();
		GourmetRelationModel relModel = new GourmetRelationModel();
		sch.setTables(tables);
		relModel.updateTables(tables);
		sch.setRelationModel(relModel);
		ContextedQueryBuilder qb = null;
		
		List<Table> tables2Select = new ArrayList<Table>();
		tables2Select.add(new DescriptionTable());
		
		lang.setFiltering(new LanguageFiltering(1));
	
		
		qb = meal.select(tables2Select);
	
		Relation rel = relModel.getRelation(MealTable.NAME, DescriptionTable.NAME, DescriptionRelations.MEAL_DESCRIPTION_7);
		SqlCondition cond = rel.getCondition().eval(qb.getRelationalContext().getFirstContextedTable(meal), qb.getRelationalContext().getFirstContextedTable(descrip));
		qb.addWhere(cond);
		
		assertTrue(qb != null);	
		System.out.println(qb.toString());
		
		System.out.println("Offset: " + qb.getRelationalContext().getQueryOffset());

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(qb.toString());

		} catch (SQLException e) {
			fail(e.getMessage());
		}  



	}
	public final void testEagerSelectFromClient() {

		try {

			System.setOut(new PrintStream("Output\\outputSelectClient.txt"));

		}catch (Exception e1) {
			// TODO Auto-generated catch block

			fail(e1.getMessage());
		}  


		sch = new Schema();	
		List<DBTable> tables = initAllBig();
		GourmetRelationModel relModel = new GourmetRelationModel();
		sch.setTables(tables);
		relModel.updateTables(tables);
		sch.setRelationModel(relModel);
		ContextedQueryBuilder qb = null;
		qb = client.select();
		assertTrue(qb != null);	
		System.out.println(qb.toString());

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(qb.toString());

			int cptCol = 0;
			//			int offset = qb.getRelationalContext().getQueryOffset();
//			while(resultSet.next()){
//				//				System.out.println("Row "+ cptCol);
//				//				System.out.println(resultSet.getString(2));
//				cptCol++;
//			}
//			System.out.println("Nb Rows: "+ cptCol);

		} catch (SQLException e) {
			fail(e.getMessage());
		}  


	}
	
	public final void testEagerSelectFromRegion() {

		try {

			System.setOut(new PrintStream("Output\\outputSelectRegion.txt"));

		}catch (Exception e1) {
			// TODO Auto-generated catch block

			fail(e1.getMessage());
		}  


		sch = new Schema();	
		List<DBTable> tables = initAllBig();
		GourmetRelationModel relModel = new GourmetRelationModel();
		sch.setTables(tables);
		relModel.updateTables(tables);
		sch.setRelationModel(relModel);
		ContextedQueryBuilder qb = null;
		qb = region.select();
		assertTrue(qb != null);	
		System.out.println(qb.toString());

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(qb.toString());

			int cptCol = 0;
			//			int offset = qb.getRelationalContext().getQueryOffset();
//			while(resultSet.next()){
//				//				System.out.println("Row "+ cptCol);
//				//				System.out.println(resultSet.getString(2));
//				cptCol++;
//			}
//			System.out.println("Nb Rows: "+ cptCol);

		} catch (SQLException e) {
			fail(e.getMessage());
		}  

	}
	
	
	public final void testEagerSelectFromIngredient() {

		try {

			System.setOut(new PrintStream("Output\\outputSelectIngr.txt"));

		}catch (Exception e1) {
			// TODO Auto-generated catch block

			fail(e1.getMessage());
		}  


		sch = new Schema();	
		List<DBTable> tables = initAllBig();
		GourmetRelationModel relModel = new GourmetRelationModel();
		sch.setTables(tables);
		relModel.updateTables(tables);
		sch.setRelationModel(relModel);
		ContextedQueryBuilder qb = null;
		qb = ingr.select();
		assertTrue(qb != null);	
		System.out.println(qb.toString());

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(qb.toString());

			int cptCol = 0;
			//			int offset = qb.getRelationalContext().getQueryOffset();
//			while(resultSet.next()){
//				//				System.out.println("Row "+ cptCol);
//				//				System.out.println(resultSet.getString(2));
//				cptCol++;
//			}
//			System.out.println("Nb Rows: "+ cptCol);

		} catch (SQLException e) {
			fail(e.getMessage());
		}  

	}
	
	
	public final void testEagerGetNearbyRestos() {

		try {

			System.setOut(new PrintStream("Output\\testEagerGetNearbyRestos.txt"));

		}catch (Exception e1) {
			// TODO Auto-generated catch block

			fail(e1.getMessage());
		}  

		
		sch = new Schema();	
		List<DBTable> tables = initAllBig();
		GourmetRelationModel relModel = new GourmetRelationModel();
		sch.setTables(tables);
		relModel.updateTables(tables);
		sch.setRelationModel(relModel);
		ContextedQueryBuilder qb = null;
	
	
		
		double latitude = 50.85711 ;
		double longitude = 4.351906; 
		double range = 0.2;
	
		LocationFiltering filter = new LocationFiltering(latitude, longitude, range);
		rest.setFiltering(filter);
		
		qb = rest.select();
		assert(qb != null );
		
		System.out.println(qb);
		

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(qb.toString());

			while(resultSet.next()){
				System.out.println(resultSet.getString(2));
			}
			
		} catch (SQLException e) {
			fail(e.getMessage());
		}  
		
		
	}
	
	
	public final void testEagerGetNearestRestaurants() {

		try {

			System.setOut(new PrintStream("Output\\testEagerGetNearestRestaurants.txt"));

		}catch (Exception e1) {
			// TODO Auto-generated catch block

			fail(e1.getMessage());
		}  

		

		sch = new Schema();	
		List<DBTable> tables = initAllBig();
		GourmetRelationModel relModel = new GourmetRelationModel();
		sch.setTables(tables);
		relModel.updateTables(tables);
		sch.setRelationModel(relModel);
		ContextedQueryBuilder qb = null;
	
		
		
		//User preferences 
		int cliId = 1;
		int langId = 1;
		boolean pref = true;
	
		
		double latitude = 50.85711 ;
		double longitude = 4.351906; 
		double range = 0.5;
	
		LocationFiltering filter = new LocationFiltering(latitude, longitude, range);
		rest.setFiltering(filter);
		getTable(tables, ClientTable.NAME).setFiltering(new ClientFiltering(cliId));
		getTable(tables, LanguageTable.NAME).setFiltering(new LanguageFiltering(langId));
		getTable(tables, PreferenceTable.NAME).setFiltering(new PreferenceFiltering(pref));
		getTable(tables, SeasonTable.NAME).setFiltering(new SeasonRangeFiltering());
		
		qb = rest.select();
		assert(qb != null );
		
		System.out.println(qb);
		

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(qb.toString());

			
		} catch (SQLException e) {
			fail(e.getMessage());
		}  
		
		
	}
	
	
	
	
	
	
	private final DBTable getTable(List<DBTable> tables, String name){
		for (DBTable table : tables) {
			if(table.getName().equals(name))
				return table;
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	//	public final void testSelectSmall() {
	//		
	//		try {
	//			propOut.setProperty(SCHEMA_TYPE_CONCEPTUAL, "ConceptDebug");
	//			propOut.setProperty(SCHEMA_TYPE_RELATIONAL, "RelDebug");
	//			propOut.store(new FileOutputStream(PROP_FILE),null);
	//			connection = DriverManager.getConnection(DB_PATH_SMALL);
	//		
	//		}catch (Exception e1) {
	//			// TODO Auto-generated catch block
	//			e1.printStackTrace();
	//		}  
	//		
	//		sch = new Schema();	
	//		List<DBTable> tables = initAllBig();
	//		GourmetRelationModel relModel = new GourmetRelationModel();
	//		sch.setTables(tables);
	//		relModel.updateTables(tables);
	//		sch.setRelationModel(relModel);
	//		ContextedQueryBuilder qb = null;
	//		qb = rest.select();
	//		assertTrue(qb != null);
	//		
	//		System.out.println("Query from Small DB");
	//		System.out.println(qb.toString());
	//		
	//		try {
	//			statement = connection.createStatement();
	//			resultSet = statement.executeQuery(qb.toString());
	//			
	////			while(resultSet.next()){
	////				System.out.print(resultSet.getString("_id"));
	////				System.out.print(resultSet.getString("Name"));
	////				System.out.print(resultSet.getString("Description"));
	////				System.out.println();
	////			}
	//			
	//			
	//		} catch (SQLException e) {
	//			fail(e.getMessage());
	//		}  
	//		
	//		try {
	//			connection.close();
	//			statement.close();
	//			resultSet.close();
	//			
	//		} catch (Exception e) {
	//			// TODO Auto-generated catch block
	//			fail(e.getMessage());
	//		}
	//	}


	//	public final void testQuery1(){
	//		fail("ToDo");
	//	}
	//	
	//	public final void testQuery2(){
	//		fail("ToDo");
	//	}


	private List<DBTable> initAllBig(){

		List<DBTable> tables = new ArrayList<DBTable>();
		rest = new RestaurantTable();
		tables.add(rest);
		rest.setFiltering(new DefaultFilteringCondition());
		meal = new MealTable();
		meal.setFiltering(new DefaultFilteringCondition());
		tables.add(meal);
		ingr = new IngredientTable();
		ingr.setFiltering(new DefaultFilteringCondition());
		tables.add(ingr);
		belonging = new BelongingTable();
		belonging.setFiltering(new DefaultFilteringCondition());
		tables.add(belonging);
		client =  new ClientTable();
		client.setFiltering(new DefaultFilteringCondition());
		tables.add(client);
		constraint = new User_ConstraintTable();
		constraint.setFiltering(new DefaultFilteringCondition());
		tables.add(constraint);
		contenance = new ContenanceTable();
		contenance.setFiltering(new DefaultFilteringCondition());
		tables.add(contenance);
		description2 = new Description2Table();
		description2.setFiltering(new DefaultFilteringCondition());
		tables.add(description2);
		descrip = new DescriptionTable();
		descrip.setFiltering(new DefaultFilteringCondition());
		tables.add(descrip);
		lang = new LanguageTable();
		lang.setFiltering(new DefaultFilteringCondition());
		tables.add(lang);
		mealRating = new Meal_RatingTable();
		mealRating.setFiltering(new DefaultFilteringCondition());
		tables.add(mealRating);
		preference  = new PreferenceTable();
		preference.setFiltering(new DefaultFilteringCondition());
		tables.add(preference);
		prohib = new ProhibitionTable();
		prohib.setFiltering(new DefaultFilteringCondition());
		tables.add(prohib);
		req = new RequirementTable();
		req.setFiltering(new DefaultFilteringCondition());
		tables.add(req);
		season  = new SeasonTable();
		season.setFiltering(new DefaultFilteringCondition());
		tables.add(season);
		tasteBelong = new Taste_BelongingTable();
		tasteBelong.setFiltering(new DefaultFilteringCondition());
		tables.add(tasteBelong);
		tastePref = new Taste_PreferenceTable();
		tastePref.setFiltering(new DefaultFilteringCondition());
		tables.add(tastePref);
		taste  =new TasteTable();
		taste.setFiltering(new DefaultFilteringCondition());
		tables.add(taste);
		visit = new VisitTable();
		visit.setFiltering(new DefaultFilteringCondition());
		tables.add(visit);

		region = new RegionTable();
		region.setFiltering(new DefaultFilteringCondition());
		tables.add(region);

		return tables;

	}
	private List<DBTable> initAllSmall(){

		List<DBTable> tables = new ArrayList<DBTable>();
		rest = new RestaurantTable();
		tables.add(rest);
		rest.setFiltering(new DefaultFilteringCondition());
		meal = new MealTable();
		meal.setFiltering(new DefaultFilteringCondition());
		tables.add(meal);
		belonging = new BelongingTable();
		belonging.setFiltering(new DefaultFilteringCondition());
		tables.add(belonging);
		client =  new ClientTable();
		client.setFiltering(new DefaultFilteringCondition());
		tables.add(client);

		descrip = new DescriptionTable();
		descrip.setFiltering(new DefaultFilteringCondition());
		tables.add(descrip);
		lang = new LanguageTable();
		lang.setFiltering(new DefaultFilteringCondition());
		tables.add(lang);
		mealRating = new Meal_RatingTable();
		mealRating.setFiltering(new DefaultFilteringCondition());
		tables.add(mealRating);

		season  = new SeasonTable();
		season.setFiltering(new DefaultFilteringCondition());
		tables.add(season);

		visit = new VisitTable();
		visit.setFiltering(new DefaultFilteringCondition());
		tables.add(visit);
		return tables;

	}
}
