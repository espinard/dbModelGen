package be.dbmodelgen.tests;

import org.dynamicschema.reification.Table;
import org.dynamicschema.sql.RelationCondition;
import org.dynamicschema.sql.SqlCondition;

public class DefaultFilteringCondition extends RelationCondition {
		

	
	public SqlCondition eval(Table ...tables) {
		Table t = tables[0];
		String cond ="";
		return new SqlCondition(new String[]{cond});
	}

}
