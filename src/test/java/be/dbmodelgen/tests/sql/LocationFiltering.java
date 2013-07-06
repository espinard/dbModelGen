/**
 * 
 */
package be.dbmodelgen.tests.sql;

import org.dynamicschema.reification.Table;
import org.dynamicschema.sql.RelationCondition;
import org.dynamicschema.sql.Sql;
import org.dynamicschema.sql.SqlCondition;

import com.github.dynamicschema.android.reification.gen.RestaurantTable;


/**
 * @author esp
 *
 */
public class LocationFiltering extends RelationCondition {

	
	private double latitude;
	private double longitude;
	private double range;
	/**
	 * 
	 */
	public LocationFiltering(double latitude, double longitude, double range) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.range = range;
	}
	

	/* (non-Javadoc)
	 * @see org.dynamicschema.sql.RelationCondition#eval(org.dynamicschema.reification.Table[])
	 */
	@Override
	public SqlCondition eval(Table... tables) {
		
		SqlCondition finalCond = new SqlCondition();
		SqlCondition locCond = null;
		String cond ="";
		for (int i = 0; i < tables.length; i++) {
			locCond =new SqlCondition().lEq(Sql.ABS+ "(" + tables[i].col(RestaurantTable.RestaurantColumns.LOC_LATITUDE)+  " - "+ this.latitude + ")", this.range);
			
			cond = new SqlCondition().lEq(Sql.ABS+ "(" + tables[i].col(RestaurantTable.RestaurantColumns.LOC_LONGITUDE)+  " - "+ this.longitude+ ")", this.range).toString();
			locCond.and(cond);
			finalCond.and(locCond.toString());
		}
		
		return finalCond;
	}



}
