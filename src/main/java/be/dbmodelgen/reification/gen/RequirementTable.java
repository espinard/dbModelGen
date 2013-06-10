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



public class RequirementTable extends DBTable { 


	public static final String NAME = "Requirement"; 


	public static class RequirementColumns extends ColumnModel { 

		//tables column names
		public static String CONSTRAINT_ID = "Constraint_id"; 
		public static String CLIENT_ID = "Client_id"; 

		public RequirementColumns() {
			//setColumnsNames(Arrays.asList(_ID, CONSTRAINT_ID, CLIENT_ID)); 
			setColumnsConstraints(Arrays.asList((ColumnConstraint) new PrimaryKey(Arrays.asList("Constraint_id" ,"Client_id")),
 (ColumnConstraint)new ForeignKey(Arrays.asList("Client_id"),
"Client",
Arrays.asList("Client_id")
),
 (ColumnConstraint)new ForeignKey(Arrays.asList("Constraint_id"),
"Constraint",
Arrays.asList("Constraint_id")
)
)); 
		}
	}

	public RequirementTable(){
		super (NAME, new RequirementColumns()); 

	}

}
