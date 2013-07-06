package be.dbmodelgen.reification.gen; 


import org.dynamicschema.reification.RelationMember; 
import org.dynamicschema.reification.Column; 
import org.dynamicschema.reification.columnconstraint.ForeignKey; 
import org.dynamicschema.reification.Occurrence; 
import org.dynamicschema.reification.ColumnModel; 
import java.util.List; 
import org.dynamicschema.reification.DBTable; 
import org.dynamicschema.reification.Relation; 
import org.dynamicschema.reification.columnconstraint.ColumnConstraint; 
import org.dynamicschema.reification.columnconstraint.PrimaryKey; 
import org.dynamicschema.reification.ContextedTable; 
import java.util.Arrays; 
import org.dynamicschema.sql.RelationCondition; 
import org.dynamicschema.reification.RelationModel; 
import org.dynamicschema.sql.SqlCondition; 
import org.dynamicschema.reification.Table; 
import org.dynamicschema.annotation.Role; 
//import static android.provider.BaseColumns._ID; 



public class MealTable extends DBTable { 


	public static final String NAME = "Meal"; 


	public static class MealColumns extends ColumnModel { 

		//tables column names
		public static String _ID = "_id"; 
		public static String BUD_MIN = "Bud_min"; 
		public static String BUD_MAX = "Bud_max"; 
		public static String PREPARATION_TIME = "Preparation_time"; 
		public static String ID_RESTAURANT = "id_restaurant"; 

		public MealColumns() {
		//Restore  import   _ID when ready
			setColumnsNames(Arrays.asList(_ID, BUD_MIN, BUD_MAX, PREPARATION_TIME, ID_RESTAURANT)); 
			setColumnsConstraints(Arrays.asList((ColumnConstraint) new PrimaryKey(Arrays.asList("_id")),
 (ColumnConstraint)new ForeignKey(Arrays.asList("id_restaurant"),
"Restaurant",
Arrays.asList("_id")
)
)); 
		}
	}

	public MealTable(){
		super (NAME, new MealColumns()); 

	}

}
