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



public class TasteTable extends DBTable { 


	public static final String NAME = "Taste"; 


	public static class TasteColumns extends ColumnModel { 

		//tables column names
		public static String TASTE_ID = "Taste_id"; 
		public static String NAME = "Name"; 

		public TasteColumns() {
			//setColumnsNames(Arrays.asList(_ID, TASTE_ID, NAME)); 
			setColumnsConstraints(Arrays.asList((ColumnConstraint) new PrimaryKey(Arrays.asList("Taste_id"))
)); 
		}
	}

	public TasteTable(){
		super (NAME, new TasteColumns()); 

	}

}
