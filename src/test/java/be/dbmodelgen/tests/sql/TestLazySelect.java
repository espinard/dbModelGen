/**
 * 
 */
package be.dbmodelgen.tests.sql;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import junit.framework.TestCase;

import org.dynamicschema.context.ContextedQueryBuilder;
import org.dynamicschema.reification.DBTable;
import org.dynamicschema.reification.Schema;
import org.dynamicschema.reification.Table;
import org.dynamicschema.reification.TableRelation;

import be.dbmodelgen.reification.gen.BelongingTable;
import be.dbmodelgen.reification.gen.ClientTable;
import be.dbmodelgen.reification.gen.ContenanceTable;
import be.dbmodelgen.reification.gen.Description2Table;
import be.dbmodelgen.reification.gen.DescriptionTable;
import be.dbmodelgen.reification.gen.GourmetRelationModel;
import be.dbmodelgen.reification.gen.ClientTable.ClientColumns;
import be.dbmodelgen.reification.gen.GourmetRelationModel.MealRelations;
import be.dbmodelgen.reification.gen.GourmetRelationModel.RegionRelations;
import be.dbmodelgen.reification.gen.IngredientTable;
import be.dbmodelgen.reification.gen.IngredientTable.IngredientColumns;
import be.dbmodelgen.reification.gen.LanguageTable;
import be.dbmodelgen.reification.gen.LanguageTable.LanguageColumns;
import be.dbmodelgen.reification.gen.MealTable;
import be.dbmodelgen.reification.gen.MealTable.MealColumns;
import be.dbmodelgen.reification.gen.Meal_RatingTable;
import be.dbmodelgen.reification.gen.PreferenceTable;
import be.dbmodelgen.reification.gen.ProhibitionTable;
import be.dbmodelgen.reification.gen.RegionTable;
import be.dbmodelgen.reification.gen.RequirementTable;
import be.dbmodelgen.reification.gen.RestaurantTable;
import be.dbmodelgen.reification.gen.RestaurantTable.RestaurantColumns;
import be.dbmodelgen.reification.gen.SeasonTable;
import be.dbmodelgen.reification.gen.TasteTable;
import be.dbmodelgen.reification.gen.Taste_BelongingTable;
import be.dbmodelgen.reification.gen.Taste_PreferenceTable;
import be.dbmodelgen.reification.gen.User_ConstraintTable;
import be.dbmodelgen.reification.gen.VisitTable;
import be.dbmodelgen.tests.DefaultFilteringCondition;

import com.github.dynamicschema.android.config.Configuration;

/**
 * @author esp
 *
 */
public class TestLazySelect extends TestCase {


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

	
	public final void testLazyRelationSelectClassicRestaurant() {

		try {
			System.setOut(new PrintStream("Output\\outputLazySelectRestaurant.txt"));

		}catch (Exception e1) {
			fail(e1.getMessage());
		}  

		sch = new Schema();	
		List<DBTable> tables = initAllBig();
		GourmetRelationModel relModel = new GourmetRelationModel();
		sch.setTables(tables);
		relModel.updateTables(tables);
		sch.setRelationModel(relModel);
		ContextedQueryBuilder qb = null;

		TableRelation tabRelMeal = rest.getTabRelation(MealRelations.RESTAURANT_MEAL_10, null);
		assertTrue(tabRelMeal !=null);
		Map<String,Object> bindings = new HashMap<String, Object>();
		bindings.put(RestaurantColumns._ID, new Integer(1));
		
		//Additional Tables
		List<Table> moreTablesSelection =  null;
		moreTablesSelection = new ArrayList<Table>();
		moreTablesSelection.add(new DescriptionTable());
		
		qb = rest.lazyRelationSelect(tabRelMeal, bindings, moreTablesSelection);

		assertTrue(qb != null);
		System.out.println(qb.toString());

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(qb.toString());

		} catch (SQLException e) {
			fail(e.getMessage());
		}  
	}


	public final void testLazySelectFromTableWithRecursiveRel() {

		try {
			System.setOut(new PrintStream("Output\\outputLazySelectFromTableWithRecursiveRel.txt"));
		}catch (Exception e1) {
			fail(e1.getMessage());
		}  

		sch = new Schema();	
		List<DBTable> tables = initAllBig();
		GourmetRelationModel relModel = new GourmetRelationModel();
		sch.setTables(tables);
		relModel.updateTables(tables);
		sch.setRelationModel(relModel);
		ContextedQueryBuilder qb = null;

		TableRelation tabRelParent = region.getTabRelation(RegionRelations.REGION_REGION_17, "child");
		assertTrue( tabRelParent != null);
		Map<String,Object> bindings = new HashMap<String, Object>();
		bindings.put("Parent", new Integer(1));

		qb = region.lazyRelationSelect(tabRelParent, bindings);

		assertTrue(qb != null);

		System.out.println(qb.toString());

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(qb.toString());
		} catch (SQLException e) {
			fail(e.getMessage());
		}  
	}
	
	
	
	
	private final void lazyRelationSelectTest(TableRelation tabRelation, Map<String,Object> bindings, DBTable tableInTest, int i,
																	List<Table> table2Select){
		
		try {
			
			String fileName = null;
			if(table2Select == null)
				fileName = "Output\\outputLazySelect"+ tableInTest.getName() +i+".txt";
			else
				fileName =  "Output\\outputLazySelect"+ tableInTest.getName() +table2Select.get(0).getName() + i +".txt";
			
			System.setOut(new PrintStream(fileName));

		}catch (Exception e1) {
			fail(e1.getMessage());
		}  

		ContextedQueryBuilder qb = null;
		//Testing
		if(table2Select == null)
			qb = tableInTest.lazyRelationSelect(tabRelation, bindings);
		else
			qb =  tableInTest.lazyRelationSelect(tabRelation, bindings, table2Select);
		
		assertTrue(qb != null);
		System.out.println(qb.toString());

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(qb.toString());

		} catch (SQLException e) {
			fail(e.getMessage());
		}  
		
	}
	
	public final void testLazyRelationSelectClassicMeal() {

		sch = new Schema();	
		List<DBTable> tables = initAllBig();
		GourmetRelationModel relModel = new GourmetRelationModel();
		sch.setTables(tables);
		relModel.updateTables(tables);
		sch.setRelationModel(relModel);

		Map<String,Object> bindings = null;
		List<TableRelation> tabRelations = meal.getTableRelations();
		for (int i = 0; i < tabRelations.size(); i++) {
			
			bindings = new HashMap<String, Object>();
			bindings.put(MealColumns._ID, new Integer(1));
			bindings.put(MealColumns.ID_RESTAURANT, new Integer(1));
			lazyRelationSelectTest(tabRelations.get(i), bindings, meal,i, null);
		}
	}

	public final void testLazyRelationSelectClassicIngredient() {

		sch = new Schema();	
		List<DBTable> tables = initAllBig();
		GourmetRelationModel relModel = new GourmetRelationModel();
		sch.setTables(tables);
		relModel.updateTables(tables);
		sch.setRelationModel(relModel);

		Map<String,Object> bindings = null;
		List<TableRelation> tabRelations = ingr.getTableRelations();
		for (int i = 0; i < tabRelations.size(); i++) {
			
			bindings = new HashMap<String, Object>();
			bindings.put(IngredientColumns._ID, new Integer(1));
			lazyRelationSelectTest(tabRelations.get(i), bindings, ingr,i, null);
		}
	}
	
	

	public final void testLazyRelationSelectClassicUserConstraint() {

		sch = new Schema();	
		List<DBTable> tables = initAllBig();
		GourmetRelationModel relModel = new GourmetRelationModel();
		sch.setTables(tables);
		relModel.updateTables(tables);
		sch.setRelationModel(relModel);

		Map<String,Object> bindings = null;
		List<TableRelation> tabRelations = constraint.getTableRelations();
		for (int i = 0; i < tabRelations.size(); i++) {
			
			bindings = new HashMap<String, Object>();
			bindings.put("_id", new Integer(1));
			lazyRelationSelectTest(tabRelations.get(i), bindings, constraint,i, null);
		}
	}
	
	public final void testLazyRelationSelectClassicClient() {

		sch = new Schema();	
		List<DBTable> tables = initAllBig();
		GourmetRelationModel relModel = new GourmetRelationModel();
		sch.setTables(tables);
		relModel.updateTables(tables);
		sch.setRelationModel(relModel);

		Map<String,Object> bindings = null;
		List<TableRelation> tabRelations = client.getTableRelations();
		for (int i = 0; i < tabRelations.size(); i++) {
			
			bindings = new HashMap<String, Object>();
			bindings.put(ClientColumns._ID, new Integer(1));
			bindings.put(ClientColumns.SPOKEN_BY, new Integer(1));

			lazyRelationSelectTest(tabRelations.get(i), bindings, client,i, null);
		}
	}

	public final void testLazyRelationSelectClassicLanguage() {

		sch = new Schema();	
		List<DBTable> tables = initAllBig();
		GourmetRelationModel relModel = new GourmetRelationModel();
		sch.setTables(tables);
		relModel.updateTables(tables);
		sch.setRelationModel(relModel);

		Map<String,Object> bindings = null;
		List<TableRelation> tabRelations = lang.getTableRelations();
		for (int i = 0; i < tabRelations.size(); i++) {
			
			bindings = new HashMap<String, Object>();
			bindings.put(LanguageColumns._ID, new Integer(1));

			lazyRelationSelectTest(tabRelations.get(i), bindings, lang,i, null);
		}
	}



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
}
