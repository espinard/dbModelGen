package be.dbmodelgen.tests.sql.testDB; 


import java.util.Arrays;
import java.util.List;

import org.dynamicschema.annotation.Role;
import org.dynamicschema.reification.DBTable;
import org.dynamicschema.reification.Occurrence;
import org.dynamicschema.reification.Relation;
import org.dynamicschema.reification.RelationMember;
import org.dynamicschema.reification.RelationModel;
import org.dynamicschema.reification.Table;
import org.dynamicschema.sql.RelationCondition;
import org.dynamicschema.sql.SqlCondition;

import be.dbmodelgen.tests.sql.testDB.ClientTable.ClientColumns;
import be.dbmodelgen.tests.sql.testDB.FidelityCardTable.FidelityCardColumns;
import be.dbmodelgen.tests.sql.testDB.LanguageTable.LanguageColumns;
import be.dbmodelgen.tests.sql.testDB.MealTable.MealColumns;
import be.dbmodelgen.tests.sql.testDB.RestaurantTable.RestaurantColumns;
import be.dbmodelgen.tests.sql.testDB.TableCTable.TableCColumns;
import be.dbmodelgen.tests.sql.testDB.TableDTable.TableDColumns;


public  class TestRelationModel extends RelationModel { 


public TestRelationModel(){
List<Relation> relations = Arrays.asList(
TestRelationModel.ClientRelations.rel_Restaurant_Client,
TestRelationModel.FidelityCardRelations.rel_Restaurant_FidelityCard,
TestRelationModel.FidelityCardRelations.rel_Client_FidelityCard,
TestRelationModel.MealRelations.rel_Restaurant_Meal,
TestRelationModel.MealRelations.rel_Language_Meal,
TestRelationModel.TableCRelations.rel_Restaurant_TableC,
TestRelationModel.TableDRelations.rel_Restaurant_TableD,
TestRelationModel.TableDRelations.rel_Language_TableD
); 
setRelations(relations); 
}

public void updateTables(List<DBTable> tables){


	for (DBTable dbTable : tables){
		updateAllRelationMembers(dbTable); 
	}
}


private void updateAllRelationMembers(DBTable table){
	for(Relation relation : this.getRelations()){
		List<RelationMember> members=relation.getRelationMembers(); 

		for(RelationMember member : members){
			if(member.getTable().getName().equals(table.getName()))
				member.setTable(table); 
		}
	}
}



public static class ClientRelations extends RelationModel { 
public static final String RESTAURANT_CLIENT = "Restaurant has (or is in) a Client"; 

public static Relation rel_Restaurant_Client = new Relation(RESTAURANT_CLIENT,
Arrays.asList(new RelationMember(new RestaurantTable(),Occurrence.ONE),
new RelationMember(new ClientTable(),Occurrence.MANY)),
new RelationCondition() {
	public SqlCondition eval(@Role("visited by") Table restaurant, @Role("visits") Table client){//Add annotation if needed
		return new SqlCondition().eq(restaurant.col(RestaurantColumns._ID),client.col(ClientColumns.VISITED_BY)); 
	}}); 



} //End of ClientRelations


public static class FidelityCardRelations extends RelationModel { 
public static final String RESTAURANT_FIDELITYCARD = "Restaurant has (or is in) a FidelityCard"; 

public static Relation rel_Restaurant_FidelityCard = new Relation(RESTAURANT_FIDELITYCARD,
Arrays.asList(new RelationMember(new RestaurantTable(),Occurrence.ONE),
new RelationMember(new FidelityCardTable(),Occurrence.MANY)),
new RelationCondition() {
	public SqlCondition eval( Table restaurant,  Table fidelitycard){//Add annotation if needed
		return new SqlCondition().eq(restaurant.col(RestaurantColumns._ID),fidelitycard.col(FidelityCardColumns.HAS__ID)); 
	}}); 



public static final String CLIENT_FIDELITYCARD = "Client has (or is in) a FidelityCard"; 

public static Relation rel_Client_FidelityCard = new Relation(CLIENT_FIDELITYCARD,
Arrays.asList(new RelationMember(new ClientTable(),Occurrence.ONE),
new RelationMember(new FidelityCardTable(),Occurrence.ONE)),
new RelationCondition() {
	public SqlCondition eval( Table client,  Table fidelitycard){//Add annotation if needed
		return new SqlCondition().eq(client.col(ClientColumns._ID),fidelitycard.col(FidelityCardColumns.BEL__ID)); 
	}}); 



} //End of FidelityCardRelations


public static class MealRelations extends RelationModel { 
public static final String RESTAURANT_MEAL = "Restaurant has (or is in) a Meal"; 

public static Relation rel_Restaurant_Meal = new Relation(RESTAURANT_MEAL,
Arrays.asList(new RelationMember(new RestaurantTable(),Occurrence.ONE),
new RelationMember(new MealTable(),Occurrence.ONE)),
new RelationCondition() {
	public SqlCondition eval(@Role("serves") Table restaurant, @Role("served in") Table meal){//Add annotation if needed
		return new SqlCondition().eq(restaurant.col(RestaurantColumns._ID),meal.col(MealColumns.SERVES)); 
	}}); 



public static final String LANGUAGE_MEAL = "Language has (or is in) a Meal"; 

public static Relation rel_Language_Meal = new Relation(LANGUAGE_MEAL,
Arrays.asList(new RelationMember(new LanguageTable(),Occurrence.ONE),
new RelationMember(new MealTable(),Occurrence.ONE)),
new RelationCondition() {
	public SqlCondition eval(@Role("describes") Table language, @Role("described by") Table meal){//Add annotation if needed
		return new SqlCondition().eq(language.col(LanguageColumns._ID),meal.col(MealColumns.DESCRIBES)); 
	}}); 



} //End of MealRelations


public static class TableCRelations extends RelationModel { 
public static final String RESTAURANT_TABLEC = "Restaurant has (or is in) a TableC"; 

public static Relation rel_Restaurant_TableC = new Relation(RESTAURANT_TABLEC,
Arrays.asList(new RelationMember(new RestaurantTable(),Occurrence.ONE),
new RelationMember(new TableCTable(),Occurrence.ONE)),
new RelationCondition() {
	public SqlCondition eval( Table restaurant,  Table tablec){//Add annotation if needed
		return new SqlCondition().eq(restaurant.col(RestaurantColumns._ID),tablec.col(TableCColumns.RES__ID)); 
	}}); 



} //End of TableCRelations


public static class TableDRelations extends RelationModel { 
public static final String RESTAURANT_TABLED = "Restaurant has (or is in) a TableD"; 

public static Relation rel_Restaurant_TableD = new Relation(RESTAURANT_TABLED,
Arrays.asList(new RelationMember(new RestaurantTable(),Occurrence.ONE),
new RelationMember(new TableDTable(),Occurrence.ONE)),
new RelationCondition() {
	public SqlCondition eval( Table restaurant,  Table tabled){//Add annotation if needed
		return new SqlCondition().eq(restaurant.col(RestaurantColumns._ID),tabled.col(TableDColumns.RES__ID)); 
	}}); 



public static final String LANGUAGE_TABLED = "Language has (or is in) a TableD"; 

public static Relation rel_Language_TableD = new Relation(LANGUAGE_TABLED,
Arrays.asList(new RelationMember(new LanguageTable(),Occurrence.ONE),
new RelationMember(new TableDTable(),Occurrence.ONE)),
new RelationCondition() {
	public SqlCondition eval( Table language,  Table tabled){//Add annotation if needed
		return new SqlCondition().eq(language.col(LanguageColumns._ID),tabled.col(TableDColumns.LAN__ID)); 
	}}); 



} //End of TableDRelations

} //End of TestRelationModel