/**
 * 
 */
package be.dbmodelgen.tests.reification;

import org.dynamicschema.reification.Schema;

import be.dbmodelgen.reification.SchemaReificator;

/**
 * @author esp
 *
 */
public class TestSchemaReificator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		String path = "D:\\Documents\\Dropbox\\UCL\\Master22\\Thesis\\DBMain\\projects\\Gourmet.lun";
		SchemaReificator schR = new SchemaReificator(path);
		Schema sch = schR.getSchema();
		TestSchema.printSchema(sch);
	}

}
