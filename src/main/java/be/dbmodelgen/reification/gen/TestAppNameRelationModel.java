package be.dbmodelgen.reification.gen; 


import org.dynamicschema.sql.RelationCondition; 
import org.dynamicschema.reification.columnconstraint.PrimaryKey; 
import org.dynamicschema.reification.DBTable; 
import org.dynamicschema.reification.Table; 
import org.dynamicschema.reification.columnconstraint.ForeignKey; 
import java.util.List; 
import org.dynamicschema.reification.RelationMember; 
import org.dynamicschema.reification.ColumnModel; 
import java.util.Arrays; 
import org.dynamicschema.reification.columnconstraint.ColumnConstraint; 
import org.dynamicschema.annotation.Role; 
import org.dynamicschema.reification.RelationModel; 
import org.dynamicschema.sql.SqlCondition; 
import org.dynamicschema.reification.Column; 
import org.dynamicschema.reification.Relation; 
import org.dynamicschema.reification.Occurrence; 
//import static android.provider.BaseColumns._ID; 


import be.dbmodelgen.reification.gen.BelongingTable.BelongingColumns; 
import be.dbmodelgen.reification.gen.BelongingTable; 
import be.dbmodelgen.reification.gen.ClientTable.ClientColumns; 
import be.dbmodelgen.reification.gen.ClientTable; 
import be.dbmodelgen.reification.gen.ContenanceTable.ContenanceColumns; 
import be.dbmodelgen.reification.gen.ContenanceTable; 
import be.dbmodelgen.reification.gen.DescriptionTable.DescriptionColumns; 
import be.dbmodelgen.reification.gen.DescriptionTable; 
import be.dbmodelgen.reification.gen.Description2Table.Description2Columns; 
import be.dbmodelgen.reification.gen.Description2Table; 
import be.dbmodelgen.reification.gen.MealTable.MealColumns; 
import be.dbmodelgen.reification.gen.MealTable; 
import be.dbmodelgen.reification.gen.Meal_RatingTable.Meal_RatingColumns; 
import be.dbmodelgen.reification.gen.Meal_RatingTable; 
import be.dbmodelgen.reification.gen.PreferenceTable.PreferenceColumns; 
import be.dbmodelgen.reification.gen.PreferenceTable; 
import be.dbmodelgen.reification.gen.ProhibitionTable.ProhibitionColumns; 
import be.dbmodelgen.reification.gen.ProhibitionTable; 
import be.dbmodelgen.reification.gen.RequirementTable.RequirementColumns; 
import be.dbmodelgen.reification.gen.RequirementTable; 
import be.dbmodelgen.reification.gen.Taste_BelongingTable.Taste_BelongingColumns; 
import be.dbmodelgen.reification.gen.Taste_BelongingTable; 
import be.dbmodelgen.reification.gen.Taste_PreferenceTable.Taste_PreferenceColumns; 
import be.dbmodelgen.reification.gen.Taste_PreferenceTable; 
import be.dbmodelgen.reification.gen.VisitTable.VisitColumns; 
import be.dbmodelgen.reification.gen.VisitTable; 


public  class TestAppNameRelationModel extends RelationModel { 





public static class BelongingRelations extends RelationModel { 
public static final String SEASON_BELONGING_1 = "Season has (or is in) a Belonging"; 

public Relation rel_Season_Belonging_1 = new Relation(SEASON_BELONGING_1,
Arrays.asList(new RelationMember(new SeasonTable(),Occurrence.ONE),
new RelationMember(new BelongingTable(),Occurrence.MANY)),
new RelationCondition() {
	public SqlCondition eval(@Role("produces") Table Season,  Table Belonging){//Add annotation if needed
//Temporary Decl. for make it compile. Remove when porting in android Dev
//TODO FOR REMOVAL  SEE CODE GENERATOR
String _ID = null; 
		return new SqlCondition().eq(Season.col(_ID),Belonging.col(BelongingColumns.SEASON_ID)); 
	}}); 



public static final String INGREDIENT_BELONGING_2 = "Ingredient has (or is in) a Belonging"; 

public Relation rel_Ingredient_Belonging_2 = new Relation(INGREDIENT_BELONGING_2,
Arrays.asList(new RelationMember(new IngredientTable(),Occurrence.ONE),
new RelationMember(new BelongingTable(),Occurrence.MANY)),
new RelationCondition() {
	public SqlCondition eval(@Role("produced in") Table Ingredient,  Table Belonging){//Add annotation if needed
//Temporary Decl. for make it compile. Remove when porting in android Dev
//TODO FOR REMOVAL  SEE CODE GENERATOR
String _ID = null; 
		return new SqlCondition().eq(Ingredient.col(_ID),Belonging.col(BelongingColumns.INGREDIENT_ID)); 
	}}); 



} //End of BelongingRelations


public static class ClientRelations extends RelationModel { 
public static final String LANGUAGE_CLIENT_3 = "Language has (or is in) a Client"; 

public Relation rel_Language_Client_3 = new Relation(LANGUAGE_CLIENT_3,
Arrays.asList(new RelationMember(new LanguageTable(),Occurrence.ONE),
new RelationMember(new ClientTable(),Occurrence.MANY)),
new RelationCondition() {
	public SqlCondition eval(@Role("spoken by") Table Language, @Role("speaks") Table Client){//Add annotation if needed
//Temporary Decl. for make it compile. Remove when porting in android Dev
//TODO FOR REMOVAL  SEE CODE GENERATOR
String _ID = null; 
		return new SqlCondition().eq(Language.col(_ID),Client.col(ClientColumns.SPOKEN_BY)); 
	}}); 



} //End of ClientRelations


public static class ContenanceRelations extends RelationModel { 
public static final String MEAL_CONTENANCE_4 = "Meal has (or is in) a Contenance"; 

public Relation rel_Meal_Contenance_4 = new Relation(MEAL_CONTENANCE_4,
Arrays.asList(new RelationMember(new MealTable(),Occurrence.ONE),
new RelationMember(new ContenanceTable(),Occurrence.MANY)),
new RelationCondition() {
	public SqlCondition eval(@Role("contains") Table Meal,  Table Contenance){//Add annotation if needed
//Temporary Decl. for make it compile. Remove when porting in android Dev
//TODO FOR REMOVAL  SEE CODE GENERATOR
String _ID = null; 
		return new SqlCondition().eq(Meal.col(_ID),Contenance.col(ContenanceColumns.MEAL_ID)); 
	}}); 



public static final String INGREDIENT_CONTENANCE_5 = "Ingredient has (or is in) a Contenance"; 

public Relation rel_Ingredient_Contenance_5 = new Relation(INGREDIENT_CONTENANCE_5,
Arrays.asList(new RelationMember(new IngredientTable(),Occurrence.ONE),
new RelationMember(new ContenanceTable(),Occurrence.MANY)),
new RelationCondition() {
	public SqlCondition eval(@Role("contained in") Table Ingredient,  Table Contenance){//Add annotation if needed
//Temporary Decl. for make it compile. Remove when porting in android Dev
//TODO FOR REMOVAL  SEE CODE GENERATOR
String _ID = null; 
		return new SqlCondition().eq(Ingredient.col(_ID),Contenance.col(ContenanceColumns.INGREDIENT_ID)); 
	}}); 



} //End of ContenanceRelations


public static class DescriptionRelations extends RelationModel { 
public static final String LANGUAGE_DESCRIPTION_6 = "Language has (or is in) a Description"; 

public Relation rel_Language_Description_6 = new Relation(LANGUAGE_DESCRIPTION_6,
Arrays.asList(new RelationMember(new LanguageTable(),Occurrence.ONE),
new RelationMember(new DescriptionTable(),Occurrence.MANY)),
new RelationCondition() {
	public SqlCondition eval(@Role("describes") Table Language,  Table Description){//Add annotation if needed
//Temporary Decl. for make it compile. Remove when porting in android Dev
//TODO FOR REMOVAL  SEE CODE GENERATOR
String _ID = null; 
		return new SqlCondition().eq(Language.col(_ID),Description.col(DescriptionColumns.LANGUAGE_ID)); 
	}}); 



public static final String MEAL_DESCRIPTION_7 = "Meal has (or is in) a Description"; 

public Relation rel_Meal_Description_7 = new Relation(MEAL_DESCRIPTION_7,
Arrays.asList(new RelationMember(new MealTable(),Occurrence.ONE),
new RelationMember(new DescriptionTable(),Occurrence.MANY)),
new RelationCondition() {
	public SqlCondition eval(@Role("described by") Table Meal,  Table Description){//Add annotation if needed
//Temporary Decl. for make it compile. Remove when porting in android Dev
//TODO FOR REMOVAL  SEE CODE GENERATOR
String _ID = null; 
		return new SqlCondition().eq(Meal.col(_ID),Description.col(DescriptionColumns.MEAL_ID)); 
	}}); 



} //End of DescriptionRelations


public static class Description2Relations extends RelationModel { 
public static final String LANGUAGE_DESCRIPTION2_8 = "Language has (or is in) a Description2"; 

public Relation rel_Language_Description2_8 = new Relation(LANGUAGE_DESCRIPTION2_8,
Arrays.asList(new RelationMember(new LanguageTable(),Occurrence.ONE),
new RelationMember(new Description2Table(),Occurrence.MANY)),
new RelationCondition() {
	public SqlCondition eval(@Role("describes") Table Language,  Table Description2){//Add annotation if needed
//Temporary Decl. for make it compile. Remove when porting in android Dev
//TODO FOR REMOVAL  SEE CODE GENERATOR
String _ID = null; 
		return new SqlCondition().eq(Language.col(_ID),Description2.col(Description2Columns.LANGUAGE_ID)); 
	}}); 



public static final String INGREDIENT_DESCRIPTION2_9 = "Ingredient has (or is in) a Description2"; 

public Relation rel_Ingredient_Description2_9 = new Relation(INGREDIENT_DESCRIPTION2_9,
Arrays.asList(new RelationMember(new IngredientTable(),Occurrence.ONE),
new RelationMember(new Description2Table(),Occurrence.MANY)),
new RelationCondition() {
	public SqlCondition eval(@Role("described by") Table Ingredient,  Table Description2){//Add annotation if needed
//Temporary Decl. for make it compile. Remove when porting in android Dev
//TODO FOR REMOVAL  SEE CODE GENERATOR
String _ID = null; 
		return new SqlCondition().eq(Ingredient.col(_ID),Description2.col(Description2Columns.INGREDIENT_ID)); 
	}}); 



} //End of Description2Relations


public static class MealRelations extends RelationModel { 
public static final String RESTAURANT_MEAL_10 = "Restaurant has (or is in) a Meal"; 

public Relation rel_Restaurant_Meal_10 = new Relation(RESTAURANT_MEAL_10,
Arrays.asList(new RelationMember(new RestaurantTable(),Occurrence.ONE),
new RelationMember(new MealTable(),Occurrence.MANY)),
new RelationCondition() {
	public SqlCondition eval(@Role("serves") Table Restaurant, @Role("served in") Table Meal){//Add annotation if needed
//Temporary Decl. for make it compile. Remove when porting in android Dev
//TODO FOR REMOVAL  SEE CODE GENERATOR
String _ID = null; 
		return new SqlCondition().eq(Restaurant.col(_ID),Meal.col(MealColumns.SERVES)); 
	}}); 



} //End of MealRelations


public static class Meal_RatingRelations extends RelationModel { 
public static final String CLIENT_MEAL_RATING_11 = "Client has (or is in) a Meal_Rating"; 

public Relation rel_Client_Meal_Rating_11 = new Relation(CLIENT_MEAL_RATING_11,
Arrays.asList(new RelationMember(new ClientTable(),Occurrence.ONE),
new RelationMember(new Meal_RatingTable(),Occurrence.MANY)),
new RelationCondition() {
	public SqlCondition eval(@Role("rates") Table Client,  Table Meal_Rating){//Add annotation if needed
//Temporary Decl. for make it compile. Remove when porting in android Dev
//TODO FOR REMOVAL  SEE CODE GENERATOR
String _ID = null; 
		return new SqlCondition().eq(Client.col(_ID),Meal_Rating.col(Meal_RatingColumns.CLIENT_ID)); 
	}}); 



public static final String MEAL_MEAL_RATING_12 = "Meal has (or is in) a Meal_Rating"; 

public Relation rel_Meal_Meal_Rating_12 = new Relation(MEAL_MEAL_RATING_12,
Arrays.asList(new RelationMember(new MealTable(),Occurrence.ONE),
new RelationMember(new Meal_RatingTable(),Occurrence.MANY)),
new RelationCondition() {
	public SqlCondition eval(@Role("rated by") Table Meal,  Table Meal_Rating){//Add annotation if needed
//Temporary Decl. for make it compile. Remove when porting in android Dev
//TODO FOR REMOVAL  SEE CODE GENERATOR
String _ID = null; 
		return new SqlCondition().eq(Meal.col(_ID),Meal_Rating.col(Meal_RatingColumns.MEAL_ID)); 
	}}); 



} //End of Meal_RatingRelations


public static class PreferenceRelations extends RelationModel { 
public static final String CLIENT_PREFERENCE_13 = "Client has (or is in) a Preference"; 

public Relation rel_Client_Preference_13 = new Relation(CLIENT_PREFERENCE_13,
Arrays.asList(new RelationMember(new ClientTable(),Occurrence.ONE),
new RelationMember(new PreferenceTable(),Occurrence.MANY)),
new RelationCondition() {
	public SqlCondition eval(@Role("prefers") Table Client,  Table Preference){//Add annotation if needed
//Temporary Decl. for make it compile. Remove when porting in android Dev
//TODO FOR REMOVAL  SEE CODE GENERATOR
String _ID = null; 
		return new SqlCondition().eq(Client.col(_ID),Preference.col(PreferenceColumns.CLIENT_ID)); 
	}}); 



public static final String INGREDIENT_PREFERENCE_14 = "Ingredient has (or is in) a Preference"; 

public Relation rel_Ingredient_Preference_14 = new Relation(INGREDIENT_PREFERENCE_14,
Arrays.asList(new RelationMember(new IngredientTable(),Occurrence.ONE),
new RelationMember(new PreferenceTable(),Occurrence.MANY)),
new RelationCondition() {
	public SqlCondition eval(@Role("preferred by") Table Ingredient,  Table Preference){//Add annotation if needed
//Temporary Decl. for make it compile. Remove when porting in android Dev
//TODO FOR REMOVAL  SEE CODE GENERATOR
String _ID = null; 
		return new SqlCondition().eq(Ingredient.col(_ID),Preference.col(PreferenceColumns.INGREDIENT_ID)); 
	}}); 



} //End of PreferenceRelations


public static class ProhibitionRelations extends RelationModel { 
public static final String CONSTRAINT_PROHIBITION_15 = "Constraint has (or is in) a Prohibition"; 

public Relation rel_Constraint_Prohibition_15 = new Relation(CONSTRAINT_PROHIBITION_15,
Arrays.asList(new RelationMember(new ConstraintTable(),Occurrence.ONE),
new RelationMember(new ProhibitionTable(),Occurrence.MANY)),
new RelationCondition() {
	public SqlCondition eval(@Role("prohibits") Table Constraint,  Table Prohibition){//Add annotation if needed
//Temporary Decl. for make it compile. Remove when porting in android Dev
//TODO FOR REMOVAL  SEE CODE GENERATOR
String _ID = null; 
		return new SqlCondition().eq(Constraint.col(_ID),Prohibition.col(ProhibitionColumns.CONSTRAINT_ID)); 
	}}); 



public static final String INGREDIENT_PROHIBITION_16 = "Ingredient has (or is in) a Prohibition"; 

public Relation rel_Ingredient_Prohibition_16 = new Relation(INGREDIENT_PROHIBITION_16,
Arrays.asList(new RelationMember(new IngredientTable(),Occurrence.ONE),
new RelationMember(new ProhibitionTable(),Occurrence.ONE)),
new RelationCondition() {
	public SqlCondition eval(@Role("prohibited by") Table Ingredient,  Table Prohibition){//Add annotation if needed
//Temporary Decl. for make it compile. Remove when porting in android Dev
//TODO FOR REMOVAL  SEE CODE GENERATOR
String _ID = null; 
		return new SqlCondition().eq(Ingredient.col(_ID),Prohibition.col(ProhibitionColumns.INGREDIENT_ID)); 
	}}); 



} //End of ProhibitionRelations


public static class RequirementRelations extends RelationModel { 
public static final String CLIENT_REQUIREMENT_17 = "Client has (or is in) a Requirement"; 

public Relation rel_Client_Requirement_17 = new Relation(CLIENT_REQUIREMENT_17,
Arrays.asList(new RelationMember(new ClientTable(),Occurrence.ONE),
new RelationMember(new RequirementTable(),Occurrence.MANY)),
new RelationCondition() {
	public SqlCondition eval(@Role("requires") Table Client,  Table Requirement){//Add annotation if needed
//Temporary Decl. for make it compile. Remove when porting in android Dev
//TODO FOR REMOVAL  SEE CODE GENERATOR
String _ID = null; 
		return new SqlCondition().eq(Client.col(_ID),Requirement.col(RequirementColumns.CLIENT_ID)); 
	}}); 



public static final String CONSTRAINT_REQUIREMENT_18 = "Constraint has (or is in) a Requirement"; 

public Relation rel_Constraint_Requirement_18 = new Relation(CONSTRAINT_REQUIREMENT_18,
Arrays.asList(new RelationMember(new ConstraintTable(),Occurrence.ONE),
new RelationMember(new RequirementTable(),Occurrence.MANY)),
new RelationCondition() {
	public SqlCondition eval(@Role("required by") Table Constraint,  Table Requirement){//Add annotation if needed
//Temporary Decl. for make it compile. Remove when porting in android Dev
//TODO FOR REMOVAL  SEE CODE GENERATOR
String _ID = null; 
		return new SqlCondition().eq(Constraint.col(_ID),Requirement.col(RequirementColumns.CONSTRAINT_ID)); 
	}}); 



} //End of RequirementRelations


public static class Taste_BelongingRelations extends RelationModel { 
public static final String TASTE_TASTE_BELONGING_19 = "Taste has (or is in) a Taste_Belonging"; 

public Relation rel_Taste_Taste_Belonging_19 = new Relation(TASTE_TASTE_BELONGING_19,
Arrays.asList(new RelationMember(new TasteTable(),Occurrence.ONE),
new RelationMember(new Taste_BelongingTable(),Occurrence.MANY)),
new RelationCondition() {
	public SqlCondition eval(@Role("possessed by") Table Taste,  Table Taste_Belonging){//Add annotation if needed
//Temporary Decl. for make it compile. Remove when porting in android Dev
//TODO FOR REMOVAL  SEE CODE GENERATOR
String _ID = null; 
		return new SqlCondition().eq(Taste.col(_ID),Taste_Belonging.col(Taste_BelongingColumns.TASTE_ID)); 
	}}); 



public static final String INGREDIENT_TASTE_BELONGING_20 = "Ingredient has (or is in) a Taste_Belonging"; 

public Relation rel_Ingredient_Taste_Belonging_20 = new Relation(INGREDIENT_TASTE_BELONGING_20,
Arrays.asList(new RelationMember(new IngredientTable(),Occurrence.ONE),
new RelationMember(new Taste_BelongingTable(),Occurrence.MANY)),
new RelationCondition() {
	public SqlCondition eval(@Role("has") Table Ingredient,  Table Taste_Belonging){//Add annotation if needed
//Temporary Decl. for make it compile. Remove when porting in android Dev
//TODO FOR REMOVAL  SEE CODE GENERATOR
String _ID = null; 
		return new SqlCondition().eq(Ingredient.col(_ID),Taste_Belonging.col(Taste_BelongingColumns.INGREDIENT_ID)); 
	}}); 



} //End of Taste_BelongingRelations


public static class Taste_PreferenceRelations extends RelationModel { 
public static final String CLIENT_TASTE_PREFERENCE_21 = "Client has (or is in) a Taste_Preference"; 

public Relation rel_Client_Taste_Preference_21 = new Relation(CLIENT_TASTE_PREFERENCE_21,
Arrays.asList(new RelationMember(new ClientTable(),Occurrence.ONE),
new RelationMember(new Taste_PreferenceTable(),Occurrence.MANY)),
new RelationCondition() {
	public SqlCondition eval(@Role("prefers") Table Client,  Table Taste_Preference){//Add annotation if needed
//Temporary Decl. for make it compile. Remove when porting in android Dev
//TODO FOR REMOVAL  SEE CODE GENERATOR
String _ID = null; 
		return new SqlCondition().eq(Client.col(_ID),Taste_Preference.col(Taste_PreferenceColumns.CLIENT_ID)); 
	}}); 



public static final String TASTE_TASTE_PREFERENCE_22 = "Taste has (or is in) a Taste_Preference"; 

public Relation rel_Taste_Taste_Preference_22 = new Relation(TASTE_TASTE_PREFERENCE_22,
Arrays.asList(new RelationMember(new TasteTable(),Occurrence.ONE),
new RelationMember(new Taste_PreferenceTable(),Occurrence.MANY)),
new RelationCondition() {
	public SqlCondition eval(@Role("preferred by") Table Taste,  Table Taste_Preference){//Add annotation if needed
//Temporary Decl. for make it compile. Remove when porting in android Dev
//TODO FOR REMOVAL  SEE CODE GENERATOR
String _ID = null; 
		return new SqlCondition().eq(Taste.col(_ID),Taste_Preference.col(Taste_PreferenceColumns.TASTE_ID)); 
	}}); 



} //End of Taste_PreferenceRelations


public static class VisitRelations extends RelationModel { 
public static final String CLIENT_VISIT_23 = "Client has (or is in) a Visit"; 

public Relation rel_Client_Visit_23 = new Relation(CLIENT_VISIT_23,
Arrays.asList(new RelationMember(new ClientTable(),Occurrence.ONE),
new RelationMember(new VisitTable(),Occurrence.MANY)),
new RelationCondition() {
	public SqlCondition eval(@Role("visits") Table Client,  Table Visit){//Add annotation if needed
//Temporary Decl. for make it compile. Remove when porting in android Dev
//TODO FOR REMOVAL  SEE CODE GENERATOR
String _ID = null; 
		return new SqlCondition().eq(Client.col(_ID),Visit.col(VisitColumns.CLIENT_ID)); 
	}}); 



public static final String RESTAURANT_VISIT_24 = "Restaurant has (or is in) a Visit"; 

public Relation rel_Restaurant_Visit_24 = new Relation(RESTAURANT_VISIT_24,
Arrays.asList(new RelationMember(new RestaurantTable(),Occurrence.ONE),
new RelationMember(new VisitTable(),Occurrence.MANY)),
new RelationCondition() {
	public SqlCondition eval(@Role("visited by") Table Restaurant,  Table Visit){//Add annotation if needed
//Temporary Decl. for make it compile. Remove when porting in android Dev
//TODO FOR REMOVAL  SEE CODE GENERATOR
String _ID = null; 
		return new SqlCondition().eq(Restaurant.col(_ID),Visit.col(VisitColumns.RESTAURANT_ID)); 
	}}); 



} //End of VisitRelations

} //End of TestAppNameRelationModel