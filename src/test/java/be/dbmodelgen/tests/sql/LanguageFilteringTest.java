/**
 * 
 */
package be.dbmodelgen.tests.sql;

import org.dynamicschema.reification.Table;
import org.dynamicschema.sql.RelationCondition;
import org.dynamicschema.sql.SqlCondition;

import be.dbmodelgen.tests.sql.testDB.LanguageTable.LanguageColumns;

/**
 * @author esp
 *
 */
public class LanguageFilteringTest extends RelationCondition {

private int idLang;
	
	public LanguageFilteringTest(int idLang)  {
		this.idLang = idLang;
	} 

	/* (non-Javadoc)
	 * @see org.dynamicschema.sql.RelationCondition#eval(org.dynamicschema.reification.Table[])
	 */
	@Override
	public SqlCondition eval(Table... tables) {
		
		SqlCondition finalCond = new SqlCondition();
		SqlCondition locCond = null;

		for (int i = 0; i < tables.length; i++) {
			locCond = new SqlCondition().eq(tables[i].col(LanguageColumns._ID), this.idLang);
			finalCond.and(locCond.toString());
		}

		return finalCond;	
		
	}
	
}
