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



public class FidelityCardTable extends DBTable { 


	public static final String NAME = "FidelityCard"; 


	public static class FidelityCardColumns extends ColumnModel { 

		//tables column names
		public static String _ID = "_id"; 
		public static String BEL__ID = "Bel__id"; 
		public static String NUMBER = "Number"; 
		public static String HAS__ID = "Has__id"; 

		public FidelityCardColumns() {
		//Restore  import   _ID when ready
			setColumnsNames(Arrays.asList(_ID, BEL__ID, NUMBER, HAS__ID)); 
			setColumnsConstraints(Arrays.asList((ColumnConstraint) new PrimaryKey(Arrays.asList("_id")),
 (ColumnConstraint)new ForeignKey(Arrays.asList("Has__id"),
"Restaurant",
Arrays.asList("_id")
),
 (ColumnConstraint)new ForeignKey(Arrays.asList("Bel__id"),
"Client",
Arrays.asList("_id")
)
)); 
		}
	}

	public FidelityCardTable(){
		super (NAME, new FidelityCardColumns()); 

	}

}
