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



public class MealTable extends DBTable { 


	public static final String NAME = "Meal"; 


	public static class MealColumns extends ColumnModel { 

		//tables column names
		public static String MEAL_ID = "Meal_id"; 
		public static String BUD_MIN = "Bud_min"; 
		public static String BUD_MAX = "Bud_max"; 
		public static String PREPARATION_TIME = "Preparation_time"; 
		public static String SERVES = "Serves"; 

		public MealColumns() {
			//setColumnsNames(Arrays.asList(_ID, MEAL_ID, BUD_MIN, BUD_MAX, PREPARATION_TIME, SERVES)); 
			setColumnsConstraints(Arrays.asList((ColumnConstraint) new PrimaryKey(Arrays.asList("Meal_id")),
 (ColumnConstraint)new ForeignKey(Arrays.asList("Serves"),
"Restaurant",
Arrays.asList("Restaurant_id")
)
)); 
		}
	}

	public MealTable(){
		super (NAME, new MealColumns()); 

	}

}
