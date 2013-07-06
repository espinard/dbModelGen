package be.dbmodelgen.tests.experiment;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import com.dbmain.jidbm.DBMAttribute;
import com.dbmain.jidbm.DBMCluster;
import com.dbmain.jidbm.DBMCompoundAttribute;
import com.dbmain.jidbm.DBMDataObject;
import com.dbmain.jidbm.DBMEntityType;
import com.dbmain.jidbm.DBMGenericObject;
import com.dbmain.jidbm.DBMGroup;
import com.dbmain.jidbm.DBMLibrary;
import com.dbmain.jidbm.DBMMetaObject;
import com.dbmain.jidbm.DBMMetaProperty;
import com.dbmain.jidbm.DBMNote;
import com.dbmain.jidbm.DBMProject;
import com.dbmain.jidbm.DBMRelationshipType;
import com.dbmain.jidbm.DBMRole;
import com.dbmain.jidbm.DBMSchema;
import com.dbmain.jidbm.DBMSimpleAttribute;
import com.dbmain.jidbm.DBMSubType;
import com.dbmain.jidbm.DBMUserObject;
import com.dbmain.jidbm.DBMUserView;


//import static java.nio.charset.Charset.*;
//import static java.nio.file.Files.*;
//import static java.nio.file.Paths.*;



public class MarcoSchema {
	DBMLibrary lib;
	static DBMSchema sch;
	static DBMProject pro;
	static DBMUserView userView;
	static DBMUserObject userObject;
	
	/*Instantiate the project at a specific Path*/
	public MarcoSchema(String Path){
		
		lib = new DBMLibrary();
		int i = lib.loadLUN(Path);
		
		if (i < 0) {
			System.out.println("The lun file does not exist.");
			System.exit(-1);
		}
		pro = new DBMProject(); //COPIED  
	}
	/*Create the Schema*/
	public void create(String name, String shortName, String version, String date){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date d;
		try {
		 d = df.parse(date);
		} catch (Exception ex) {
		 d = null;
        }
		sch = pro.createSchema(name,shortName,version,d,d,DBMSchema.ERASCHEMA,null);
	}
	/* Create an Entity Type  */
	public static DBMEntityType createEntityType(DBMSchema sch, String name,String shortName){
		DBMEntityType ent = sch.createEntityType(name,shortName);
        return ent;	
	}
	/* Add a simple attribute to an Entity Type  */
	public static DBMAttribute addSimpleAttribute(DBMSchema sch,DBMEntityType ent, String name, String shortName, int minRep, int maxRep, char attType, boolean stable, boolean recyclable, int leght, short decim,  DBMAttribute prev){
		DBMSimpleAttribute att = ent.createSimpleAttribute(name,shortName,minRep,maxRep,' ',attType,stable, recyclable,leght,decim,sch,prev);
		return att;
	}
	/*Create graphical object*/
	public static void createUserView(DBMSchema schema,String name, short type, short zoom, short reduce,int xgrid, int ygrid, short fontSize, String fontName, short textFontSize, String textFontName, int markPlan, DBMUserView prevUserView ){
		userView = schema.createUserView(name,type,zoom,reduce,xgrid,ygrid, fontSize,fontName,textFontSize,textFontName,markPlan,prevUserView);
	}
	/* Draw an object to a specific position in the schema*/
	public static void drawEntity(DBMGenericObject obj, int posx, int posy, int color){
		 userObject = userView.createUserObject(posx,posy,color,obj);
	}
	
	
	/*Create a relationship type starting from a set of entity type along with their cardinality
	 * Values admitted as cardinality are: "0..1","1","1..*","*".                                */
	public void createRelationship(String name, String shortName,List<DBMEntityType> dbmEntity, List<String> multiplicity){
		DBMRole rol=null;
		DBMRelationshipType rel=null;
		
		rel = sch.createRelationshipType(name, shortName);
		
		for(int i=0; i< dbmEntity.size();i++){
	    	System.out.println("Multiplicity: "+multiplicity.get(i));
	    	if (multiplicity.get(i).equals("0..1")){
				rol = rel.createRole("",0,1,' ');
				rol.addFirstEntityType(dbmEntity.get(i));
				System.out.println("01");
			}
			else{
				if (multiplicity.get(i).equals("1")){
					rol = rel.createRole("",1,1,' ');
					rol.addFirstEntityType(dbmEntity.get(i));
					System.out.println("11");
				}
				else{
					if (multiplicity.get(i).equals("1..*")){
						rol = rel.createRole("",1,DBMRole.N_CARD,' ');
						rol.addFirstEntityType(dbmEntity.get(i));
						System.out.println("1*");
					}
				    else{
				    	if (multiplicity.get(i).equals("*")){
				    		rol = rel.createRole("",0,DBMRole.N_CARD,' ');
				    		rol.addFirstEntityType(dbmEntity.get(i));
				    		System.out.println("0*");
				    	}
				    	else{
				    		System.out.println("FATAL ERROR: Cardinality not recognized: "+multiplicity.get(i) );
				    	}
				    }
				}
			}	
		}
	}
	/*Create an is-a relatioship between a child and a list of parents*/
	public void createIsARelatioship(DBMEntityType child, List<DBMEntityType> parents){
		//Creation of is-a relationship
	    for (DBMEntityType parent:parents){
	    	//cus subtype of ord
			DBMCluster cl = parent.createCluster("1", DBMCluster.TOTAL_CLU, "");
			parent.addFirstCluster(cl);
			DBMSubType st = child.createSubType("", cl);
			child.addFirstSubType(st);
		}
	}
	/*Unload Project and exit*/
	private static void saveProjectIntoLun(DBMProject pro,DBMLibrary lib, String lunFilePath) {
		lib.unloadLUN(pro.getProjectIdentifier(),lunFilePath);
		pro.deleteProject();
		
	}
	
	
	/*set Primary Key to a certain entity type*/
	public static DBMEntityType addAllAttributes(DBMEntityType ent,DBMAttribute id){
		DBMGroup gr = ent.createGroup("ID"+ent.getName(),DBMGroup.ASS_GROUP,DBMGroup.PRIM_GR,0,1,null);
		gr.addFirstComponent(id);
		return ent;
	}
	/*Create a simple attribute*/
	public static DBMAttribute addSimpleAttribute(DBMEntityType ent, String name, String shortName, int minRep, int maxRep, char attType, boolean stable, boolean recyclable, int leght, short decim,  DBMAttribute prev){
		DBMSimpleAttribute att = ent.createSimpleAttribute(name,shortName,minRep,maxRep,DBMAttribute.SET_ATT,attType,stable, recyclable,leght,decim,sch,null);
		ent.addNextAttribute(att,prev);
		return att;
	}
	/*Create a compound attribute*/
	public static DBMCompoundAttribute addCompoundAttribute(DBMEntityType ent,String name, String shortName, int minRep, int maxRep,DBMAttribute prev){
		DBMCompoundAttribute co = ent.createCompoundAttribute(name,shortName,minRep,maxRep,' ',sch,null);
		ent.addNextAttribute(co,prev);	
		return co;
	}
	/*Create a sub attribute of a Compound attribute*/
	public static DBMAttribute addSubAttribute(DBMCompoundAttribute co, String name, String shortName, int minRep, int maxRep, char attType, boolean stable, boolean recyclable, int leght, short decim,  DBMAttribute prev){
		DBMSimpleAttribute si = co.createSimpleAttribute(name,shortName,minRep,maxRep,' ',attType,stable, recyclable,leght,decim,sch,prev);
		return si;
	}
	
	
	public void createMetProperty(DBMEntityType ent){
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	
	
	
	
	
}
