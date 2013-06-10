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



public class ConstraintTable extends DBTable { 


	public static final String NAME = "Constraint"; 


	public static class ConstraintColumns extends ColumnModel { 

		//tables column names
		public static String CONSTRAINT_ID = "Constraint_id"; 
		public static String DESCRIPTION = "Description"; 
		public static String TYPE = "Type"; 

		public ConstraintColumns() {
			//setColumnsNames(Arrays.asList(_ID, CONSTRAINT_ID, DESCRIPTION, TYPE)); 
			setColumnsConstraints(Arrays.asList((ColumnConstraint) new PrimaryKey(Arrays.asList("Constraint_id"))
)); 
		}
	}

	public ConstraintTable(){
		super (NAME, new ConstraintColumns()); 

	}

}
