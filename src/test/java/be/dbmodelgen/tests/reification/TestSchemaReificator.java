/**
 * 
 */
package be.dbmodelgen.tests.reification;

import org.dynamicschema.reification.Schema;

import be.dbmodelgen.reification.SchemaReifier;

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
		SchemaReifier schR = new SchemaReifier(path);
		Schema sch = schR.getSchema();
		TestSchema.printSchema(sch);
	}

}
