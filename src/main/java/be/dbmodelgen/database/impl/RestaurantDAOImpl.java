package be.dbmodelgen.database.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.dynamicschema.reification.Column;

import be.dbmodelgen.database.Customer;
import be.dbmodelgen.database.Location;
import be.dbmodelgen.database.Meal;
import be.dbmodelgen.database.RestaurantDeprecated;
import be.dbmodelgen.database.RestaurantDAO;

public class RestaurantDAOImpl implements RestaurantDAO {

	private final String DATABASE_PATH = "jdbc:sqlite:C:\\SQLite\\restaurant.db";
	private final String DBMAIN_PROJECT_FILE_PATH= "D:\\Documents\\Dropbox\\UCL\\Master22\\Thesis\\DBMain\\projects\\Gourmet.lun";

	
	private Connection connection;


	public RestaurantDAOImpl() {
	}


	public List<Customer> getUsersInterestedForMenu( Map<String, Object> contextData, List<Meal> mList) {

		/*
		 * -- the restaurant wants to know all the users that could be interested in a particular menu
		 A menu is composed of several dishes/meals . The query below is going to be executed for all  

		 */

		List<Customer> resList = null;
		ResultSet resultSet = null;
		Statement statement = null;


		resList = new ArrayList<Customer>();
		RestaurantDeprecated  rest = (RestaurantDeprecated) contextData.get("restaurant");


		try {
			for (Meal meal : mList) {
				int id = meal.getID();
				String q = buildQuery_1(id,rest.getID());
				statement = (Statement) this.connection.createStatement();
				resultSet = statement.executeQuery(q);
				while(resultSet.next()){

					String cl_name = resultSet.getString("first_name");
					int cl_id = resultSet.getInt("cl_id");
					Customer c = new  Mock_Customer(cl_name, cl_id);
					resList.add(c);

				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return resList;
	}

	private String buildQuery_1(int param_Meal_ID, int param_Restaurant_ID){

		String query = "SELECT cl.client_id, cl.first_name, cl.last_name" + 
						"FROM Client as cl, Ingredients as ing, Ingredient_Client_Preference as ingClientPref " +
						"WHERE cl.client_id = ingClientPref.id_client" + 
						"AND  ing.ingredient_id = ingClientPref.id_ingredient" +
						"AND   ing.ingredient_id IN ( SELECT ing2.ingredient_id" + 
													"FROM Ingredient as ing2, Meal as m, Restaurant as rest, Meal_Ingredient as ml" + 
													"WHERE ing2.ingredient_id = ml.id_ingredient" +
													"AND "+param_Meal_ID +"= ml.id_meal" +
													"AND " + param_Restaurant_ID+ "= m.id_restaurant )";

		return query;
	}


	
	public List<Meal> getMealsFromNearRestaurant(Map<String, Object> contextData) {


		List<Meal> resList = null;
		ResultSet resultSet = null;
		Statement statement = null;


		resList = new ArrayList<Meal>();
		Date date = (Date)contextData.get("date");
		Location user_loc = (Location) contextData.get("user-location");
		Customer cust = (Customer) contextData.get("customer");
		String q = buildQuery_2(date, cust.getID(), user_loc);

		try {
			statement = this.connection.createStatement();
			resultSet = statement.executeQuery(q);

			while (resultSet.next()) {
				String descr =resultSet.getString("description");
				int m_id = resultSet.getInt("meal_id");
				Meal m = new Mock_Meal(m_id, descr);
				resList.add(m);

			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resList;
	}

	private String buildQuery_2(Date date, int param_user_ID, Location user_loc){

		String currDate  = date.toString();
		String param_customer_location = user_loc.getLocationName();//actually only the city, not the precise location

		String q="SELECT m.meal_id, ml.description" +
		"FROM Meal as m, Meal_Lang as ml  " +
		"WHERE m.meal_id = ml.id_meal " +
		"AND  param_user_lang_id = Meal_Lang.id_lang" +
		" AND EXISTS (	SELECT ing.ingredient_id " +
						"FROMIngredient as ing, Season as seas, Ingredient_Season as ingSeas, Restaurant as res, Meal_Ingredient as mIng, Client as cl1" +
						"WHERE res.restaurant_id = m.id_restaurant" +
						"AND "+param_customer_location+ " = res.city" +
						"AND ing.ingredient_id = ingSeas.id_ingredient" +
						"AND ingSeas.id_season = seas.season_id" +
						"AND Datetime("+currDate+") >= Datetime(season_begin) and Datetime(currDate) <= Datetime(season_end)" +
						"AND mIng.id_ingredient = ing.ingredient_id" +
						"AND m.meal_id = mIng.id_meal" +
						"AND ing.ingredient_id IN (SELECT ing2.ingredient_id" +
													"FROM Ingredients as ing2, Client as cl, Ingredient_Client_Preference as ingClientPref" +
													"WHERE "+param_user_ID + "= ingClientPref.id_client" +
													"AND ing2.ingredient_id = ingClientPref.id_ingredient))";

		return q;
	}

	
	public List<Meal> getMealsWithSeasonIngredients( Map<String, Object> contextData) {
		/*

		 * -- A bio restaurant wants to know meals (among the one offered) which contain ingredients of the season) 
		-- The meal description depends on the language of the restaurant
		 */
		List<Meal> resList = null;
		ResultSet resultSet = null;
		Statement statement = null;

		Date date = (Date)contextData.get("date");
		RestaurantDeprecated rest = (RestaurantDeprecated)contextData.get("restaurant");
		String query = buildQuery_3(date, rest.getID());

		resList = new ArrayList<Meal>();

		try {
			statement = this.connection.createStatement();
			resultSet = statement.executeQuery(query);

			while (resultSet.next()) {

				String descr =resultSet.getString("description");
				int m_id = resultSet.getInt("meal_id");
				Meal m = new Mock_Meal(m_id, descr);
				resList.add(m);

			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resList;

	}


	private String buildQuery_3(Date date, int restaurantID){
		String currDate  = date.toString(); 

		String q = "SELECT m.meal_id, ml.description" +
					"FROM Meal as m , Meal_Lang as ml, Restaurant as res,Ingredient as ing, Meal_Ingredient as mIng" +
					"WHERE  m.id_restaurant ="+ restaurantID +
					"AND ml.id_lang = res.rest_lang_id " +
					"and m.meal_id = ml.id_meal " +
					"AND m.meal_id = mIng.id_meal" +
					"AND ing.ingredient = mIng.id_ingredient" +
					"AND ing.ingredient  IN (SELECT ing2.ingredient_id" +
											"FROM Ingredients as ing2, Season as seas,Ingredient_Season as IngSeas" +
											"WHERE  ing2.ingredient_id = IngSeas.id_ingredient" +
											"AND seas.season_id = ingSeas.id_season" +
											"AND Datetime("+currDate+") >= Datetime(season_begin) and Datetime("+currDate+") <= Datetime(season_end)";

		return q;
	}

	
	public List<RestaurantDeprecated> getRestaurantOfGivenType(Map<String, Object> contextData) {


		List<RestaurantDeprecated> resList = null;
		ResultSet resultSet = null;
		Statement statement = null;

		Customer cust= (Customer)contextData.get("customer");
		String query = buildQuery_4(cust.getID());

		resList = new ArrayList<RestaurantDeprecated>();

		try {
			statement = this.connection.createStatement();
			resultSet = statement.executeQuery(query);

			while (resultSet.next()) {

				int rest_id=resultSet.getInt("restaurant_id");

				RestaurantDeprecated res =  new Mock_Restaurant(rest_id);
				resList.add(res);

			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resList;
	}


	private String buildQuery_4(int userID){


		String q = "SELECT res.restaurant_id" +
					"FROM  Restaurant as res" +
					"WHERE res.food_type='chinese'" +
					"AND EXISTS (SELECT ml.meal_id" +
								"FROM Meal ml, Ingredient ing, Meal_Ingredient mIng" +
								" WHERE ml.id_restaurant =  res.restaurant_id" +
								"AND	ml.meal_id = mIng.id_meal" +
								"AND  	ing.ingredient_id = mIng.id_ingredient" +
								"AND  ing.ingredient_id IN (SELECT ing2.ingredient_id" +
															"FROM Client as cl, Ingredient as ing2, Ingredient_Client_Preference as IngPref" +
															"WHERE "+userID+" = IngPref.id_client" +
															"AND ing2.ingredient_id = IngPref.id_ingredient))";

		return q;
	}



	
	public List<RestaurantDeprecated> getRestaurantsWithOnlyPreferredMeals(Map<String, Object> contextData) {

		List<RestaurantDeprecated> resList = null;
		ResultSet resultSet = null;
		Statement statement = null;

		Customer cust= (Customer)contextData.get("customer");
		Location loc = (Location) contextData.get("user-location");

		String query = buildQuery_5(loc,cust.getID());

		resList = new ArrayList<RestaurantDeprecated>();

		try {
			statement = this.connection.createStatement();
			resultSet = statement.executeQuery(query);

			while (resultSet.next()) {

				int rest_id=resultSet.getInt("restaurant_id");
				RestaurantDeprecated res =  new Mock_Restaurant(rest_id);
				resList.add(res);

			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resList;

	}


	private String buildQuery_5(Location user_loc,int userID){


		String q = "SELECT res.restaurant_id " +
					"FROM  Restaurant as res, Meal as ml, Ingredient as Ing, Meal_Ingredient as mIng" +
					"WHERE  res.restaurant_id = ml.id_restaurant_id" +
					"AND  ml.meal_id = mIng.id_meal" +
					"AND  Ing.ingredient_id = mIng.id_ingredient" +
					"AND res.city ="+ user_loc.getLocationName() +
					"AND Ing.ingredient_id NOT IN( SELECT ing2.ingredient_id" +
													" FROM Ingredient as ing2, Client as cl, Ingredient_Client_Dislike as IngDis" +
													" WHERE ing2.ingredient_id = IngDis.id_ingredient" +
													"AND "+userID+" = IngDis.id_client" +
													")";

		return q;

	}

	

	/*
	 * Returns List of tables belonging to the database with specified connection (SQLite)
	 */
	public List<TableInfo> getTableList(){
		
		
		Connection connect  = null;
		try {  
			Class.forName("org.sqlite.JDBC");  

		} catch (Exception e) {  
			System.err.println("Could not find JDBC");
			e.printStackTrace();  
		}		

		String query = "SELECT * FROM sqlite_master where type='table'" ;

		ResultSet resultSet = null;
		Statement statement = null;
		List<TableInfo> tblList = new ArrayList<TableInfo>();

		try {
		
			this.connection = DriverManager.getConnection(DATABASE_PATH);
			statement = this.connection.createStatement();
			resultSet = statement.executeQuery(query);

			while (resultSet.next()) {

				String  tableName =resultSet.getString("name");
				if(!tableName.equals("sqlite_sequence")){// Not interested in this table
					String sqlCode = resultSet.getString("sql");

					List<ColumnInfo> listColInfo = getColumnInfo(tableName);
					TableInfo tbInfo = new TableInfo(tableName, listColInfo, sqlCode);
					tblList.add(tbInfo);

				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tblList;
	}

	/*
	 * Retrieve ColumnInformation about a given table
	 */

	private List<ColumnInfo> getColumnInfo(String tableName){

		ResultSet resultSet = null;
		Statement statement = null;
		String query = "PRAGMA table_info("+tableName+")";

		List<ColumnInfo> listCol= new ArrayList<ColumnInfo>();

		try {
			this.connection = DriverManager.getConnection(DATABASE_PATH);
			statement = this.connection.createStatement();
			resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				String colName= resultSet.getString("name");
				String colType = resultSet.getString("type");
				Column currCol = new Column(colName, ColumnTypes.getType(colType));
				int pkFlag = resultSet.getInt("pk");
				int notNullFlag = resultSet.getInt("notnull");
				ColumnInfo currColInfo = new ColumnInfo(currCol, pkFlag == 1, notNullFlag == 1);
				listCol.add(currColInfo);

			}	

		} catch (SQLException e) {
			e.printStackTrace();
		}


		return listCol;
	}

}
