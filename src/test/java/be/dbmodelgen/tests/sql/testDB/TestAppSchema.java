package be.dbmodelgen.tests.sql.testDB;

import java.util.ArrayList;
import java.util.List;

import org.dynamicschema.reification.DBTable;
import org.dynamicschema.reification.Schema;
import org.dynamicschema.sql.RelationCondition;

import be.dbmodelgen.sql.EmptyFilteringCondition;

public class TestAppSchema extends Schema {

	public TestAppSchema() {
		List<DBTable> tables = initTables();
		initFilterings(tables, new EmptyFilteringCondition());
		TestRelationModel model = new TestRelationModel();
		model.updateTables(tables);
		setTables(tables);
		setRelationModel(model);
	}
	
	
	private List<DBTable> initTables(){
		DBTable client, lang, meal, rest, tableC, tableD, card;
		List<DBTable> tables = new ArrayList<DBTable>();
		client = new ClientTable();
		tables.add(client);
		lang = new LanguageTable();
		tables.add(lang);
		meal = new MealTable();
		tables.add(meal);
		rest = new RestaurantTable();
		tables.add(rest);
		tableC = new TableCTable();
		tables.add(tableC);
		tableD = new TableDTable();
		tables.add(tableD);
		card= new FidelityCardTable();
		tables.add(card);
		return tables;
	}
	
	/*
	 * Optional 
	 */
	private void initFilterings(List<DBTable> tables, RelationCondition filtering){
		for (DBTable dbTable : tables) {
			dbTable.setFiltering(filtering);
		}
	}

}
