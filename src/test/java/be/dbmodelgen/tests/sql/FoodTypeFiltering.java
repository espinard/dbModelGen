package be.dbmodelgen.tests.sql;

import org.dynamicschema.reification.Table;
import org.dynamicschema.sql.RelationCondition;
import org.dynamicschema.sql.SqlCondition;

import be.dbmodelgen.tests.sql.testDB.RestaurantTable.RestaurantColumns;

public class FoodTypeFiltering extends RelationCondition {

	private String foodType;
	
	public FoodTypeFiltering(String foodType)  {
		this.foodType = foodType;
	} 

	/* (non-Javadoc)
	 * @see org.dynamicschema.sql.RelationCondition#eval(org.dynamicschema.reification.Table[])
	 */
	@Override
	public SqlCondition eval(Table... tables) {
		
		SqlCondition finalCond = new SqlCondition();
		SqlCondition locCond = null;

		for (int i = 0; i < tables.length; i++) {
			locCond = new SqlCondition().eq(tables[i].col(RestaurantColumns.FOOD_TYPE), this.foodType);
			finalCond.and(locCond.toString());
		}

		return finalCond;	
		
	}
	
	


}
