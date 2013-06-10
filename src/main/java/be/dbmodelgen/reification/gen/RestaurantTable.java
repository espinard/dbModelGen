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



public class RestaurantTable extends DBTable { 


	public static final String NAME = "Restaurant"; 


	public static class RestaurantColumns extends ColumnModel { 

		//tables column names
		public static String RESTAURANT_ID = "Restaurant_id"; 
		public static String NAME = "Name"; 
		public static String DESCRIPTION = "Description"; 
		public static String FOOD_TYPE = "Food_Type"; 
		public static String ADD_STREET = "Add_street"; 
		public static String ADD_NUMBER = "Add_number"; 
		public static String ADD_LOCALITY = "Add_Locality"; 
		public static String ADD_POSTAL_CODE = "Add_postal_code"; 
		public static String LOC_LATITUDE = "Loc_latitude"; 
		public static String LOC_LONGITUDE = "Loc_longitude"; 

		public RestaurantColumns() {
			//setColumnsNames(Arrays.asList(_ID, RESTAURANT_ID, NAME, DESCRIPTION, FOOD_TYPE, ADD_STREET, ADD_NUMBER, ADD_LOCALITY, ADD_POSTAL_CODE, LOC_LATITUDE, LOC_LONGITUDE)); 
			setColumnsConstraints(Arrays.asList((ColumnConstraint) new PrimaryKey(Arrays.asList("Restaurant_id"))
)); 
		}
	}

	public RestaurantTable(){
		super (NAME, new RestaurantColumns()); 

	}

}
