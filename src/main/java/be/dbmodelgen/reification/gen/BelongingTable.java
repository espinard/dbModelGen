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



public class BelongingTable extends DBTable { 


	public static final String NAME = "Belonging"; 


	public static class BelongingColumns extends ColumnModel { 

		//tables column names
		public static String INGREDIENT_ID = "Ingredient_id"; 
		public static String SEASON_ID = "Season_id"; 

		public BelongingColumns() {
			//setColumnsNames(Arrays.asList(_ID, INGREDIENT_ID, SEASON_ID)); 
			setColumnsConstraints(Arrays.asList((ColumnConstraint) new PrimaryKey(Arrays.asList("Season_id" ,"Ingredient_id")),
 (ColumnConstraint)new ForeignKey(Arrays.asList("Season_id"),
"Season",
Arrays.asList("Season_id")
),
 (ColumnConstraint)new ForeignKey(Arrays.asList("Ingredient_id"),
"Ingredient",
Arrays.asList("Ingredient_id")
)
)); 
		}
	}

	public BelongingTable(){
		super (NAME, new BelongingColumns()); 

	}

}
