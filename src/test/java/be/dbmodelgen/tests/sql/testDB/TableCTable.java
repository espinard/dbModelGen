package be.dbmodelgen.tests.sql.testDB; 


import org.dynamicschema.reification.columnconstraint.PrimaryKey; 
import org.dynamicschema.reification.columnconstraint.ColumnConstraint; 
import org.dynamicschema.reification.RelationMember; 
import org.dynamicschema.reification.Table; 
import org.dynamicschema.reification.Relation; 
import org.dynamicschema.annotation.Role; 
import org.dynamicschema.reification.columnconstraint.ForeignKey; 
import org.dynamicschema.reification.ContextedTable; 
import org.dynamicschema.reification.DBTable; 
import org.dynamicschema.reification.Occurrence; 
import org.dynamicschema.reification.RelationModel; 
import org.dynamicschema.sql.RelationCondition; 
import org.dynamicschema.sql.SqlCondition; 
import java.util.Arrays; 
import java.util.List; 
import org.dynamicschema.reification.ColumnModel; 
import org.dynamicschema.reification.Column; 
//import static android.provider.BaseColumns._ID; 



public class TableCTable extends DBTable { 


	public static final String NAME = "TableC"; 


	public static class TableCColumns extends ColumnModel { 

		//tables column names
		public static String _ID = "_id"; 
		public static String RES__ID = "Res__id"; 
		public static String DESCRIPTION = "Description"; 

		public TableCColumns() {
		//Restore  import   _ID when ready
			setColumnsNames(Arrays.asList(_ID, RES__ID, DESCRIPTION)); 
			setColumnsConstraints(Arrays.asList((ColumnConstraint) new PrimaryKey(Arrays.asList("_id")),
 (ColumnConstraint)new ForeignKey(Arrays.asList("Res__id"),
"Restaurant",
Arrays.asList("_id")
)
)); 
		}
	}

	public TableCTable(){
		super (NAME, new TableCColumns()); 

	}

}
