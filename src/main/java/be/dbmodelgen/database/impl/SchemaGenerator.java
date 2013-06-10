package be.dbmodelgen.database.impl;

import java.util.ArrayList;
import java.util.List;

import org.dynamicschema.reification.Column;
import org.dynamicschema.reification.ColumnModel;
import org.dynamicschema.reification.DBTable;
import org.dynamicschema.reification.Occurrence;
import org.dynamicschema.reification.Relation;
import org.dynamicschema.reification.RelationMember;
import org.dynamicschema.reification.RelationModel;
import org.dynamicschema.reification.Schema;
import org.dynamicschema.reification.columnconstraint.ColumnConstraint;
import org.dynamicschema.reification.columnconstraint.PrimaryKey;

import be.dbmodelgen.database.RestaurantDAO;

public class SchemaGenerator {


	private List<TableInfo> tblInfoList;
	private List<DBTable> setTables;
	/**
	 * 
	 */
	public SchemaGenerator(RestaurantDAO dao) {
		this.tblInfoList = dao.getTableList();
	}


	public Schema getSchema(){

		if(this.tblInfoList.size()== 0 )
			return null;	

		Schema schema =  new Schema();
		List<DBTable> tables = createListDBTables();
		RelationModel relModel = createRelationModel();
		schema.setTables(tables);
		schema.setRelationModel(relModel);
		return schema;

	}

	//TODO: Take into account dependencies between tables when generating tables!
	private List<DBTable> createListDBTables(){

		List<DBTable> lTables = new  ArrayList<DBTable>();

		for (int i = 0; i < this.tblInfoList.size(); i++) {
			TableInfo currTabInfo = this.tblInfoList.get(i);
			String name = currTabInfo.getName();
			ColumnModel model = createColumnModel(currTabInfo);
			DBTable dbTab = new DBTable(name, model);
			this.setTables.add(dbTab);
			lTables.add(dbTab);
		}

		return lTables;
	}

	private ColumnModel createColumnModel(TableInfo tableInfo){

		ColumnModel colmodel = null;
		List<ColumnInfo> listColInfo = tableInfo.getColumnInfo();
		List<Column> lColumns = new ArrayList<Column>();
		List<ColumnConstraint> constrList = new ArrayList<ColumnConstraint>();

		for (int i = 0; i < listColInfo.size(); i++) {
			ColumnInfo currColInfo = listColInfo.get(i);
			lColumns.add(currColInfo.getColumn());
			ColumnConstraint constr = null;
			if(currColInfo.isPrimaryKey()){
				List<String> colNames = new ArrayList<String>();
				colNames.add(currColInfo.getColumn().getSimpleName());
				constr = new PrimaryKey(colNames);
			}else{ //TODO Handle the case of foreign key !!!
//				constr = new ColumnConstraint();
				constr = null;
			}
			constrList.add(i, constr); //adding corresponding constraint

		}
		colmodel= new ColumnModel(lColumns, constrList);
		return colmodel;
	}


	/*
	 * create the relational model of the schema
	 */
	private RelationModel createRelationModel(){

		RelationModel model = null;
		List<Relation> lRel = getAllRelations();
		System.out.println("Number relation="+ lRel.size());
		model  = new RelationModel();
		model.setRelations(lRel);
		
		return model;
	}


	//TODO (Not USEFUL RIGHT NOW): Determine the unicity of the columns by seeing whether there is a UNICITY constraint on a column ( See in sqlite whether no 
	//Like keyword UNIQUE => Help determine whether relation between 2 tables ONE-TO-ONE) 
	private List<Relation> getAllRelations(){

		List<Relation> listRel = new ArrayList<Relation>();

		for (int i = 0; i < this.tblInfoList.size(); i++) {

			TableInfo currTabInfo = tblInfoList.get(i);
			RelationMember currMember = null; 
			List<String> relTab = getRelatedTable(currTabInfo);
			
			//System.out.println("#Method: getAllRelations() - Number of related Table: "+currTabInfo.getName()+ " -> "+ relTab.size());
		
			
			if(relTab.size() > 0){
				//Normally in this case, it should be a intermediary table handling Many-to-Many relation between two other tables
				// for example: Having a table Many-to-Many relation between Tab1 and Tab2, there should be an intermediary table Tab1_Tab2. Here the "currMember.getName() == "Tab1_Tab2"
				currMember = new RelationMember(getDBTable(currTabInfo.getName()), Occurrence.MANY); 
				//Link the current table
				for (String tabName : relTab) {
					List<RelationMember> members = new ArrayList<RelationMember>();
					members.add(currMember);
					members.add(new RelationMember(getDBTable(tabName), Occurrence.ONE));
					Relation rel = new Relation(currMember.getTable().getName()+"-"+tabName, members, null);
					listRel.add(rel);		
				}
			}
		//TODO: Pay attention about 2 relations between the same table : give different name (number for ex)
		}
		return listRel;
	}

	/*
	 * Retrieve collection of table names of tables related to the one whose info is specified as argument
	 */
	private List<String> getRelatedTable(TableInfo tabInfo){

		List<String> reltables = new ArrayList<String>();
		String sqlCode = tabInfo.getSqlCode();
		
		//System.out.println("SQLCode for table: "+tabInfo.getName());
		System.out.print(sqlCode);
		
		String [] lines = sqlCode.split("\n");

//		System.out.println("Splitted lines");
//		for (String string : lines) {
//			System.out.println(string);
//			System.out.println("#");
//		}
		
//		System.out.println("==End splitted lines");
	
		
		for (int i = 0; i < lines.length; i++) {
			if(lines[i].trim().startsWith("FOREIGN KEY")){
				
				System.out.println("Found line: "+ lines[i]);
				
				String [] lineKeywords = lines[i].split(" ");
				
				System.out.println("Start print Keywords");
				for (String string : lineKeywords) {
					System.out.println(string);
					System.out.println("##");
				}
				System.out.println("==End print keywords");
				
				String lastKeyword = lineKeywords[lineKeywords.length - 1];
				
				System.out.println("Last keyword: "+lastKeyword);
				String tableName = lastKeyword.substring(0, lastKeyword.indexOf('(')); 
				System.out.println("tableName:"+tableName);
				reltables.add(tableName);
			}else
				System.out.println("Not filtered Line:"+lines[i]);
		}

		return reltables;

	}


 	
	
	public String testSchemaScript(Schema schema){
		return schema.createSchemaScript();
	}

	
	
	
	
	
	
	
	//NEXT TODO: Generate the classes(At application-level)  corresponding to the DBTables
	//See code sent by Sergio
	//Ex: PoiTable
	/*
	 * package com.mobilecity.persistency.mobilecitydbmodel;

		import static android.provider.BaseColumns._ID;
		import static java.util.Arrays.asList;
		
		import org.dynamicschema.reification.ColumnModel;
		import org.dynamicschema.reification.DBTable;
		
		public class PoiTable extends DBTable {
		
		    public static final String NAME = "poi";
		
		    public static class PoiColumns extends ColumnModel {
		        //table column names
		        public static String URL = "url";
		        public static String LATITUDE = "latitude";
		        public static String LONGITUDE = "longitude";
		        public static String ADDRESS = "address";
		        public static String RANGE = "range";
		        public static String ID_PARENT_POI = "parent";
		
		        public PoiColumns() {
		            setColumnsNames(asList(_ID, ID_PARENT_POI, URL, LATITUDE, LONGITUDE, ADDRESS, RANGE));
		        }
		    }
		
		    public PoiTable() {
		        super(NAME, new PoiColumns());
		    }
		
		}

	 * 
	 */
	
	//TODO( Android Part): Screens with corresponding context-dependent queries. Specify the query that will be used for a specific screen


	/*
	 * Get the DBTable object whose name matches the one passed as parameter
	 */
	private DBTable getDBTable(String name){

		for (DBTable tab: this.setTables) {
			if(tab.getName().equals(name))
				return  tab;
		}
		return null;
	}

}
