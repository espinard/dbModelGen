package be.dbmodelgen.database;

import java.util.List;
import java.util.Map;

import be.dbmodelgen.database.impl.TableInfo;

public interface RestaurantDAO {
	
	/*
	 * Restaurant wants to know all the users that could be interested in a particular menu.
	
	 *	Context: 
	 *		Location of restaurant
	 *		Date
	 *		User preferences 
	 *				set of disliked ingredients
	 *				set of liked ingredients
	 *
	 */
	
	List<Customer> getUsersInterestedForMenu(Map<String, Object> contextData, List<Meal> mList);
	

	/*User wants to see availability of meals in near restaurants

		Assumption: assuming providing all the preferences to the query.
		
		contextData:
			- Location of the user
			- Date
			- Language
			- User preferences:
			 	* Set of Unliked ingredients. For each ingredient
			 	* Set of Disliked ingredients
			
	*/
	List<Meal> getMealsFromNearRestaurant(Map<String, Object> contextData);

	
	/*
	 * A bio restaurant wants to know which of its offered meals include only ingredients of the season. 
	 * In this way other meals will not be offered.
	 * 
	 * 	context data:
	 * 		* Date ( for the season) 
	 */
	
	List<Meal> getMealsWithSeasonIngredients(Map<String, Object> contextData);
	
	
	
	/*
	 * A User wants to  know all chinese restaurants that server at least a meal with at least one
	 * one of its preferered ingredients
	 * 
	 * 	context data:
	 * 	
	 *  User preferences 
	 * 	  
	 */ 
	
	List<RestaurantDeprecated> getRestaurantOfGivenType(Map<String, Object> contextData);

	
	
	
	/*
	 * A User wants to  know all local restaurants that which do not offer any meal with disliked
	 * ingredients
	 * 
	 * 	context data: 
	 * 			Location
	 * 	
	 *  User preferences 
	 * 	  
	 */ 
	
	List<RestaurantDeprecated> getRestaurantsWithOnlyPreferredMeals(Map<String, Object> contextData);
	
	
	
	/* 
	 * Retrieve collection of information about current tables in the database
	 * 
	 */
	
	public List<TableInfo> getTableList();
}
