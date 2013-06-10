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



public class ClientTable extends DBTable { 


	public static final String NAME = "Client"; 


	public static class ClientColumns extends ColumnModel { 

		//tables column names
		public static String CLIENT_ID = "Client_id"; 
		public static String CLI_NAME = "Cli_Name"; 
		public static String CLI_SURNAME = "Cli_Surname"; 
		public static String LOC_LATITUDE = "Loc_latitude"; 
		public static String LOC_LONGITUDE = "Loc_longitude"; 
		public static String AGE = "Age"; 
		public static String ADD_STREET = "Add_street"; 
		public static String ADD_NUMBER = "Add_number"; 
		public static String ADD_POSTALCODE = "Add_postalCode"; 
		public static String ADD_LOCALITY = "Add_Locality"; 
		public static String SPOKEN_BY = "Spoken_by"; 

		public ClientColumns() {
			//setColumnsNames(Arrays.asList(_ID, CLIENT_ID, CLI_NAME, CLI_SURNAME, LOC_LATITUDE, LOC_LONGITUDE, AGE, ADD_STREET, ADD_NUMBER, ADD_POSTALCODE, ADD_LOCALITY, SPOKEN_BY)); 
			setColumnsConstraints(Arrays.asList((ColumnConstraint) new PrimaryKey(Arrays.asList("Client_id")),
 (ColumnConstraint)new ForeignKey(Arrays.asList("Spoken_by"),
"Language",
Arrays.asList("Language_id")
)
)); 
		}
	}

	public ClientTable(){
		super (NAME, new ClientColumns()); 

	}

}
