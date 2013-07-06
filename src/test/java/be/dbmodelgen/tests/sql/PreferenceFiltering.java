/**
 * 
 */
package be.dbmodelgen.tests.sql;

import org.dynamicschema.reification.Table;
import org.dynamicschema.sql.RelationCondition;
import org.dynamicschema.sql.SqlCondition;

import be.dbmodelgen.reification.gen.PreferenceTable;

/**
 * @author esp
 *
 */
public class PreferenceFiltering extends RelationCondition{

	private boolean pref;
	
	/**
	 * 
	 */
	public PreferenceFiltering(boolean prefers) {
		this.pref = prefers;
	}

	
	/* (non-Javadoc)
	 * @see org.dynamicschema.sql.RelationCondition#eval(org.dynamicschema.reification.Table[])
	 */
	@Override
	public SqlCondition eval(Table... tables) {
		
		SqlCondition finalCond = new SqlCondition();
		SqlCondition locCond = null;
		for (int i = 0; i < tables.length; i++) {
			
			String preference;
			if(this.pref)
				preference ="1";
			else
				preference = "0";
		
			locCond = new SqlCondition().eq(tables[i].col(PreferenceTable.PreferenceColumns.PREF), preference);	
			finalCond.and(locCond.toString());
		}
		
		return finalCond;
	}


	
	

}
