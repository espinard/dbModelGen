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



public class ContenanceTable extends DBTable { 


	public static final String NAME = "Contenance"; 


	public static class ContenanceColumns extends ColumnModel { 

		//tables column names
		public static String INGREDIENT_ID = "Ingredient_id"; 
		public static String MEAL_ID = "Meal_id"; 

		public ContenanceColumns() {
			//setColumnsNames(Arrays.asList(_ID, INGREDIENT_ID, MEAL_ID)); 
			setColumnsConstraints(Arrays.asList((ColumnConstraint) new PrimaryKey(Arrays.asList("Ingredient_id" ,"Meal_id")),
 (ColumnConstraint)new ForeignKey(Arrays.asList("Meal_id"),
"Meal",
Arrays.asList("Meal_id")
),
 (ColumnConstraint)new ForeignKey(Arrays.asList("Ingredient_id"),
"Ingredient",
Arrays.asList("Ingredient_id")
)
)); 
		}
	}

	public ContenanceTable(){
		super (NAME, new ContenanceColumns()); 

	}

}
