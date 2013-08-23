package be.dbmodelgen.tests.sql;

import org.dynamicschema.reification.Table;
import org.dynamicschema.sql.RelationCondition;
import org.dynamicschema.sql.SqlCondition;

import be.dbmodelgen.tests.sql.testDB.ClientTable.ClientColumns;

public class ClientAgeFiltering extends RelationCondition {

	private int age;

	public ClientAgeFiltering(int age)  {
		this.age = age;
	} 

	/* (non-Javadoc)
	 * @see org.dynamicschema.sql.RelationCondition#eval(org.dynamicschema.reification.Table[])
	 */
	@Override
	public SqlCondition eval(Table... tables) {

		SqlCondition finalCond = new SqlCondition();
		SqlCondition locCond = null;

		for (int i = 0; i < tables.length; i++) {
			locCond = new SqlCondition().eq(tables[i].col(ClientColumns.AGE), this.age);
			finalCond.and(locCond.toString());
		}

		return finalCond;	

	}


}
