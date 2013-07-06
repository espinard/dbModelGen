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



public class BelongingTable extends DBTable { 


	public static final String NAME = "Belonging"; 


	public static class BelongingColumns extends ColumnModel { 

		//tables column names
		public static String ID_INGREDIENT = "id_ingredient"; 
		public static String ID_SEASON = "id_season"; 

		public BelongingColumns() {
		//Restore  import   _ID when ready
			setColumnsNames(Arrays.asList(ID_INGREDIENT, ID_SEASON)); 
			setColumnsConstraints(Arrays.asList((ColumnConstraint) new PrimaryKey(Arrays.asList("id_season" ,"id_ingredient")),
 (ColumnConstraint)new ForeignKey(Arrays.asList("id_season"),
"Season",
Arrays.asList("_id")
),
 (ColumnConstraint)new ForeignKey(Arrays.asList("id_ingredient"),
"Ingredient",
Arrays.asList("_id")
)
)); 
		}
	}

	public BelongingTable(){
		super (NAME, new BelongingColumns()); 

	}

}
