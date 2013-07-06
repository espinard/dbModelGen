package be.dbmodelgen.tests.experiment;

import java.io.IOException;
import com.dbmain.jidbm.*;


public class StatisticGenerator {

	static int na_max;

	public static void runDBM ()  throws IOException {
		new DBMConsole();
		DBMLibrary lib = new DBMLibrary();
		if (lib.getCurrentSchema() >= 0) {
			DBMSchema sch = new DBMSchema(lib.getCurrentSchema());

			int nRel = 0;
			int ne = 0;
			int na  = 0;
			int nr = 0;
			na_max = 0;
			DBMDataObject d = sch.getFirstDataObject();
			while (d != null) {
				switch (d.getObjectType()) {
				case DBMGenericObject.ENTITY_TYPE:
					ne = ne+1;
					setAttributeMaximum((DBMEntityRelationshipType)d);
					break;
				case DBMGenericObject.REL_TYPE:
					nr = nr + 1;
					break;

				case DBMGenericObject.SI_ATTRIBUTE:
				case DBMGenericObject.CO_ATTRIBUTE:
					na = na + 1;
					break;
				}
				d = sch.getNextDataObject(d);
			}
			System.out.println("\nSTATISTICS:"+"\n-----------\n"+
					"#Entity types:\t"+ne+"\n#Rel-types:\t"+nr+
					"\n#Attributes:\t"+na+
					"\nMax attributes per entity: "+na_max+"\n"+
					"Number of Rel Element: "+ nRel+ "\n");
		}
		else {
			System.out.println("\nNo selected schema!\n");
		}
		System.out.println("\nEnd...\n");
	}

	public static void setAttributeMaximum(DBMEntityRelationshipType e) {
		int max_at = 0;
		DBMAttribute a = e.getFirstAttribute();
		while (a != null) {
			max_at = max_at + 1;
			a = e.getNextAttribute(a);
		}
		if (max_at > na_max) { 
			na_max  = max_at; 
		}
	}
}
