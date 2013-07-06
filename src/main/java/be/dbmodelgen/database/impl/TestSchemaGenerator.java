package be.dbmodelgen.database.impl;

import java.sql.Connection;
import java.sql.DriverManager;

import org.dynamicschema.reification.Schema;

import be.dbmodelgen.database.RestaurantDAO;
import be.dbmodelgen.tests.reification.TestSchema;

public class TestSchemaGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Connection connection  = null;
		try {  
			Class.forName("org.sqlite.JDBC");  
			connection = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\restaurant.db");  

		} catch (Exception e) {  
			e.printStackTrace();  
		} finally {  
			try {  
				connection.close();  
			} catch (Exception e) {  
				e.printStackTrace();  
			}  
		}  
			
		RestaurantDAO dao = new RestaurantDAOImpl();
		SchemaGenerator gen = new SchemaGenerator(dao);
		Schema sch = gen.getSchema();
		TestSchema.printSchema(sch);
		//String script = gen.testSchemaScript(sch);
		//System.out.println(script);

	}

}
