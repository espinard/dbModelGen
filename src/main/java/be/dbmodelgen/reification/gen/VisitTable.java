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



public class VisitTable extends DBTable { 


	public static final String NAME = "Visit"; 


	public static class VisitColumns extends ColumnModel { 

		//tables column names
		public static String RESTAURANT_ID = "Restaurant_id"; 
		public static String CLIENT_ID = "Client_id"; 

		public VisitColumns() {
			//setColumnsNames(Arrays.asList(_ID, RESTAURANT_ID, CLIENT_ID)); 
			setColumnsConstraints(Arrays.asList((ColumnConstraint) new PrimaryKey(Arrays.asList("Client_id" ,"Restaurant_id")),
 (ColumnConstraint)new ForeignKey(Arrays.asList("Client_id"),
"Client",
Arrays.asList("Client_id")
),
 (ColumnConstraint)new ForeignKey(Arrays.asList("Restaurant_id"),
"Restaurant",
Arrays.asList("Restaurant_id")
)
)); 
		}
	}

	public VisitTable(){
		super (NAME, new VisitColumns()); 

	}

}
