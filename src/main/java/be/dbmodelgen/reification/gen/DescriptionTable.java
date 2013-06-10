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



public class DescriptionTable extends DBTable { 


	public static final String NAME = "Description"; 


	public static class DescriptionColumns extends ColumnModel { 

		//tables column names
		public static String MEAL_ID = "Meal_id"; 
		public static String LANGUAGE_ID = "Language_id"; 
		public static String DESCRIPTION = "description"; 

		public DescriptionColumns() {
			//setColumnsNames(Arrays.asList(_ID, MEAL_ID, LANGUAGE_ID, DESCRIPTION)); 
			setColumnsConstraints(Arrays.asList((ColumnConstraint) new PrimaryKey(Arrays.asList("Meal_id" ,"Language_id")),
 (ColumnConstraint)new ForeignKey(Arrays.asList("Language_id"),
"Language",
Arrays.asList("Language_id")
),
 (ColumnConstraint)new ForeignKey(Arrays.asList("Meal_id"),
"Meal",
Arrays.asList("Meal_id")
)
)); 
		}
	}

	public DescriptionTable(){
		super (NAME, new DescriptionColumns()); 

	}

}
