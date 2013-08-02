package be.dbmodelgen.tests.sql;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import junit.framework.TestCase;

import org.dynamicschema.reification.DBTable;
import org.dynamicschema.reification.Schema;

import be.dbmodelgen.config.Configuration;

public class TestRestrictedSelect extends TestCase {

	
	private final String DB_PATH_BIG_RECURSIVE = "jdbc:sqlite:D:\\Documents\\Dropbox\\UCL\\Master22\\Thesis\\GourmetTestFiles\\gourmetBigRecursive.sqlite";
	private final String PROP_FILE = Configuration.PROP_FILE;

	private Properties prop;

	private DBTable rest, meal, ingr, belonging, client, constraint, contenance,
	descrip, description2, lang, mealRating, preference, prohib,
	req, season,tasteBelong, tastePref, taste, visit, region;

	private Schema sch ;


	private Connection connection = null;  
	private ResultSet resultSet = null;  
	private Statement statement = null;  	

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();

		try {

			prop = new Properties();
			prop.load(new FileInputStream(PROP_FILE));	
			Class.forName("org.sqlite.JDBC");  
			connection = DriverManager.getConnection(DB_PATH_BIG_RECURSIVE);


		} catch (FileNotFoundException e) {
			fail(e.getMessage());

		} catch (Exception e1) {

			fail("Message:" +  e1.getMessage());
		}  
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
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
			fail("Message" + e.getMessage());
		}
	}

	
//	public final void testRestrictedSelectRestaurant() {
//
//		try {
//			System.setOut(new PrintStream("Output\\outputRestrictedResto.txt"));
//
//		}catch (Exception e1) {
//			fail(e1.getMessage());
//		}  
//
//		sch = new Schema();	
//		List<DBTable> tables = initAllBig();
//		GourmetRelationModel relModel = new GourmetRelationModel();
//		sch.setTables(tables);
//		relModel.updateTables(tables);
//		sch.setRelationModel(relModel);
//		ContextedQueryBuilder qb = null;
//
//		List<Relation> relations2Visit = new ArrayList<Relation>();
//		relations2Visit.add(MealRelations.rel_Restaurant_Meal_10);
//		relations2Visit.add(ContenanceRelations.rel_Meal_Contenance_4);
//		relations2Visit.add(ContenanceRelations.rel_Ingredient_Contenance_5);
//		relations2Visit.add(DescriptionRelations.rel_Meal_Description_7);
//		
//		qb = rest.restrictedSelect(relations2Visit);
//		assertTrue(qb != null);
//		System.out.println(qb.toString());
//
//		try {
//			statement = connection.createStatement();
//			resultSet = statement.executeQuery(qb.toString());
//
//		} catch (SQLException e) {
//			fail(e.getMessage());
//		}  
//	}
//	
//	public final void testRestrictedSelectMeal() {
//
//		try {
//			System.setOut(new PrintStream("Output\\outputRestrictedMeal.txt"));
//
//		}catch (Exception e1) {
//			fail(e1.getMessage());
//		}  
//
//		sch = new Schema();	
//		List<DBTable> tables = initAllBig();
//		GourmetRelationModel relModel = new GourmetRelationModel();
//		sch.setTables(tables);
//		relModel.updateTables(tables);
//		sch.setRelationModel(relModel);
//		ContextedQueryBuilder qb = null;
//
//		List<Relation> relations2Visit = new ArrayList<Relation>();
//		relations2Visit.add(MealRelations.rel_Restaurant_Meal_10);
//		relations2Visit.add(ContenanceRelations.rel_Meal_Contenance_4);
//		relations2Visit.add(ContenanceRelations.rel_Ingredient_Contenance_5);
//		relations2Visit.add(DescriptionRelations.rel_Meal_Description_7);
//		List<Table> tables2Select = new ArrayList<Table>();
//		tables2Select.add(descrip);
//		
//		qb = meal.restrictedSelect(relations2Visit,tables2Select);
//
//		assertTrue(qb != null);
//		System.out.println(qb.toString());
//
//		try {
//			statement = connection.createStatement();
//			resultSet = statement.executeQuery(qb.toString());
//
//		} catch (SQLException e) {
//			fail(e.getMessage());
//		}  
//	}
//	
//	public final void testRestrictedSelectIngredient() {
//
//		try {
//			System.setOut(new PrintStream("Output\\outputRestrictedIngredient.txt"));
//
//		}catch (Exception e1) {
//			fail(e1.getMessage());
//		}  
//
//		sch = new Schema();	
//		List<DBTable> tables = initAllBig();
//		GourmetRelationModel relModel = new GourmetRelationModel();
//		sch.setTables(tables);
//		relModel.updateTables(tables);
//		sch.setRelationModel(relModel);
//		ContextedQueryBuilder qb = null;
//
//		List<Relation> relations2Visit = new ArrayList<Relation>();
//		relations2Visit.add(MealRelations.rel_Restaurant_Meal_10);
//		relations2Visit.add(ContenanceRelations.rel_Meal_Contenance_4);
//		relations2Visit.add(ContenanceRelations.rel_Ingredient_Contenance_5);
//		relations2Visit.add(Description2Relations.rel_Ingredient_Description2_9);
//		List<Table> tables2Select = new ArrayList<Table>();
//		tables2Select.add(description2);
//		
//		qb = ingr.restrictedSelect(relations2Visit,tables2Select);
//
//		assertTrue(qb != null);
//		System.out.println(qb.toString());
//
//		try {
//			statement = connection.createStatement();
//			resultSet = statement.executeQuery(qb.toString());
//
//		} catch (SQLException e) {
//			fail(e.getMessage());
//		}  
//	}
//	
//	public final void testRestrictedNonNullSelectRestaurant() {
//
//		try {
//			System.setOut(new PrintStream("Output\\outputRestrictedNonNullJoiningResto.txt"));
//
//		}catch (Exception e1) {
//			fail(e1.getMessage());
//		}  
//
//		sch = new Schema();	
//		List<DBTable> tables = initAllBig();
//		GourmetRelationModel relModel = new GourmetRelationModel();
//		sch.setTables(tables);
//		relModel.updateTables(tables);
//		sch.setRelationModel(relModel);
//		ContextedQueryBuilder qb = null;
//
//		List<Relation> relations2Visit = new ArrayList<Relation>();
//		relations2Visit.add(MealRelations.rel_Restaurant_Meal_10);
//		relations2Visit.add(ContenanceRelations.rel_Meal_Contenance_4);
//		relations2Visit.add(ContenanceRelations.rel_Ingredient_Contenance_5);
//		relations2Visit.add(DescriptionRelations.rel_Meal_Description_7);
//		
//		qb = rest.restrictedNonNullSelect(relations2Visit);
//		assertTrue(qb != null);
//		System.out.println(qb.toString());
//
//		try {
//			statement = connection.createStatement();
//			resultSet = statement.executeQuery(qb.toString());
//
//		} catch (SQLException e) {
//			fail(e.getMessage());
//		}  
//	}
//	
//	public final void testRestrictedNonNullSelectMeal() {
//
//		try {
//			System.setOut(new PrintStream("Output\\outputRestrictedNonNullJoiningMeal.txt"));
//
//		}catch (Exception e1) {
//			fail(e1.getMessage());
//		}  
//
//		sch = new Schema();	
//		List<DBTable> tables = initAllBig();
//		GourmetRelationModel relModel = new GourmetRelationModel();
//		sch.setTables(tables);
//		relModel.updateTables(tables);
//		sch.setRelationModel(relModel);
//		ContextedQueryBuilder qb = null;
//
//		List<Relation> relations2Visit = new ArrayList<Relation>();
//		relations2Visit.add(MealRelations.rel_Restaurant_Meal_10);
//		relations2Visit.add(ContenanceRelations.rel_Meal_Contenance_4);
//		relations2Visit.add(ContenanceRelations.rel_Ingredient_Contenance_5);
//		relations2Visit.add(DescriptionRelations.rel_Meal_Description_7);
//		List<Table> tables2Select = new ArrayList<Table>();
//		tables2Select.add(descrip);
//		
//		qb = meal.restrictedNonNullSelect(relations2Visit,tables2Select);
//
//		assertTrue(qb != null);
//		System.out.println(qb.toString());
//
//		try {
//			statement = connection.createStatement();
//			resultSet = statement.executeQuery(qb.toString());
//
//		} catch (SQLException e) {
//			fail(e.getMessage());
//		}  
//	}
//	
//	public final void testRestrictedNonNullSelectIngredient() {
//
//		try {
//			System.setOut(new PrintStream("Output\\outputRestrictedNonNullJoining_Ingredient.txt"));
//
//		}catch (Exception e1) {
//			fail(e1.getMessage());
//		}  
//
//		sch = new Schema();	
//		List<DBTable> tables = initAllBig();
//		GourmetRelationModel relModel = new GourmetRelationModel();
//		sch.setTables(tables);
//		relModel.updateTables(tables);
//		sch.setRelationModel(relModel);
//		ContextedQueryBuilder qb = null;
//
//		List<Relation> relations2Visit = new ArrayList<Relation>();
//		relations2Visit.add(MealRelations.rel_Restaurant_Meal_10);
//		relations2Visit.add(ContenanceRelations.rel_Meal_Contenance_4);
//		relations2Visit.add(ContenanceRelations.rel_Ingredient_Contenance_5);
//		relations2Visit.add(Description2Relations.rel_Ingredient_Description2_9);
//		List<Table> tables2Select = new ArrayList<Table>();
//		tables2Select.add(description2);
//		
//		qb = ingr.restrictedNonNullSelect(relations2Visit,tables2Select);
//
//		assertTrue(qb != null);
//		System.out.println(qb.toString());
//
//		try {
//			statement = connection.createStatement();
//			resultSet = statement.executeQuery(qb.toString());
//
//		} catch (SQLException e) {
//			fail(e.getMessage());
//		}  
//	}
//	
//	
//	public final void testRestrictedSelectNearbyRestaurant() {
//
//		try {
//			System.setOut(new PrintStream("Output\\outputRestrictedNearbyResto.txt"));
//
//		}catch (Exception e1) {
//			fail(e1.getMessage());
//		}  
//
//		sch = new Schema();	
//		List<DBTable> tables = initAllBig();
//		GourmetRelationModel relModel = new GourmetRelationModel();
//		sch.setTables(tables);
//		relModel.updateTables(tables);
//		sch.setRelationModel(relModel);
//		ContextedQueryBuilder qb = null;
//
//		List<Relation> relations2Visit = new ArrayList<Relation>();
//		relations2Visit.add(MealRelations.rel_Restaurant_Meal_10);
//		relations2Visit.add(ContenanceRelations.rel_Meal_Contenance_4);
//		relations2Visit.add(ContenanceRelations.rel_Ingredient_Contenance_5);
//		relations2Visit.add(DescriptionRelations.rel_Meal_Description_7);
//		
//		
//		double latitude = 50.85711 ;
//		double longitude = 4.351906; 
//		double range = 0.2;
//	
//		LocationFiltering filter = new LocationFiltering(latitude, longitude, range);
//		rest.setFiltering(filter);
//		
//		
//		qb = rest.restrictedSelect(relations2Visit);
//		assertTrue(qb != null);
//		System.out.println(qb.toString());
//
//		try {
//			statement = connection.createStatement();
//			resultSet = statement.executeQuery(qb.toString());
//
//		} catch (SQLException e) {
//			fail(e.getMessage());
//		}  
//	}
//	
//	
//	public final void testRestrictedSelectNonNullNearbyRestaurant() {
//
//		try {
//			System.setOut(new PrintStream("Output\\outputRestrictedNonNullJoiningNearbyResto.txt"));
//
//		}catch (Exception e1) {
//			fail(e1.getMessage());
//		}  
//
//		sch = new Schema();	
//		List<DBTable> tables = initAllBig();
//		GourmetRelationModel relModel = new GourmetRelationModel();
//		sch.setTables(tables);
//		relModel.updateTables(tables);
//		sch.setRelationModel(relModel);
//		ContextedQueryBuilder qb = null;
//
//		List<Relation> relations2Visit = new ArrayList<Relation>();
//		relations2Visit.add(MealRelations.rel_Restaurant_Meal_10);
//		relations2Visit.add(ContenanceRelations.rel_Meal_Contenance_4);
//		relations2Visit.add(ContenanceRelations.rel_Ingredient_Contenance_5);
//		relations2Visit.add(DescriptionRelations.rel_Meal_Description_7);
//		
//		
//		double latitude = 50.85711 ;
//		double longitude = 4.351906; 
//		double range = 0.2;
//	
//		LocationFiltering filter = new LocationFiltering(latitude, longitude, range);
//		rest.setFiltering(filter);
//		
//		
//		qb = rest.restrictedNonNullSelect(relations2Visit);
//		assertTrue(qb != null);
//		System.out.println(qb.toString());
//
//		try {
//			statement = connection.createStatement();
//			resultSet = statement.executeQuery(qb.toString());
//
//		} catch (SQLException e) {
//			fail(e.getMessage());
//		}  
//	}
//	
//	private List<DBTable> initAllBig(){
//
//		List<DBTable> tables = new ArrayList<DBTable>();
//		rest = new RestaurantTable();
//		tables.add(rest);
//		rest.setFiltering(new DefaultFilteringCondition());
//		meal = new MealTable();
//		meal.setFiltering(new DefaultFilteringCondition());
//		tables.add(meal);
//		ingr = new IngredientTable();
//		ingr.setFiltering(new DefaultFilteringCondition());
//		tables.add(ingr);
//		belonging = new BelongingTable();
//		belonging.setFiltering(new DefaultFilteringCondition());
//		tables.add(belonging);
//		client =  new ClientTable();
//		client.setFiltering(new DefaultFilteringCondition());
//		tables.add(client);
//		constraint = new User_ConstraintTable();
//		constraint.setFiltering(new DefaultFilteringCondition());
//		tables.add(constraint);
//		contenance = new ContenanceTable();
//		contenance.setFiltering(new DefaultFilteringCondition());
//		tables.add(contenance);
//		description2 = new Description2Table();
//		description2.setFiltering(new DefaultFilteringCondition());
//		tables.add(description2);
//		descrip = new DescriptionTable();
//		descrip.setFiltering(new DefaultFilteringCondition());
//		tables.add(descrip);
//		lang = new LanguageTable();
//		lang.setFiltering(new DefaultFilteringCondition());
//		tables.add(lang);
//		mealRating = new Meal_RatingTable();
//		mealRating.setFiltering(new DefaultFilteringCondition());
//		tables.add(mealRating);
//		preference  = new PreferenceTable();
//		preference.setFiltering(new DefaultFilteringCondition());
//		tables.add(preference);
//		prohib = new ProhibitionTable();
//		prohib.setFiltering(new DefaultFilteringCondition());
//		tables.add(prohib);
//		req = new RequirementTable();
//		req.setFiltering(new DefaultFilteringCondition());
//		tables.add(req);
//		season  = new SeasonTable();
//		season.setFiltering(new DefaultFilteringCondition());
//		tables.add(season);
//		tasteBelong = new Taste_BelongingTable();
//		tasteBelong.setFiltering(new DefaultFilteringCondition());
//		tables.add(tasteBelong);
//		tastePref = new Taste_PreferenceTable();
//		tastePref.setFiltering(new DefaultFilteringCondition());
//		tables.add(tastePref);
//		taste  =new TasteTable();
//		taste.setFiltering(new DefaultFilteringCondition());
//		tables.add(taste);
//		visit = new VisitTable();
//		visit.setFiltering(new DefaultFilteringCondition());
//		tables.add(visit);
//
//		region = new RegionTable();
//		region.setFiltering(new DefaultFilteringCondition());
//		tables.add(region);
//
//		return tables;
//
//	}

}
