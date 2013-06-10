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



public class SeasonTable extends DBTable { 


	public static final String NAME = "Season"; 


	public static class SeasonColumns extends ColumnModel { 

		//tables column names
		public static String SEASON_ID = "Season_id"; 
		public static String NAME = "Name"; 
		public static String RAN_BEGIN = "Ran_begin"; 
		public static String RAN_END = "Ran_end"; 

		public SeasonColumns() {
			//setColumnsNames(Arrays.asList(_ID, SEASON_ID, NAME, RAN_BEGIN, RAN_END)); 
			setColumnsConstraints(Arrays.asList((ColumnConstraint) new PrimaryKey(Arrays.asList("Season_id"))
)); 
		}
	}

	public SeasonTable(){
		super (NAME, new SeasonColumns()); 

	}

}
