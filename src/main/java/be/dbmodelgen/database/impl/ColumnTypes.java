package be.dbmodelgen.database.impl;

public class ColumnTypes {
	
		public static final String INTEGER = "INTEGER";
		public static final String VARCHAR = "VARCHAR";
		public static final String REAL = "REAL";
		
		
		public static String getType(String name){
			
			if (name.equalsIgnoreCase(INTEGER))
				return INTEGER;
		
			if (name.equalsIgnoreCase(VARCHAR))
				return VARCHAR;
			
			if (name.equalsIgnoreCase(REAL))
				return REAL;
			
			return null;
		}
		
}
