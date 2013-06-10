package be.dbmodelgen.database.impl;

import java.util.List;

public class TableInfo {

	private String name; 
	private List<ColumnInfo> columns; // list of columns belonging to this table
	private String sqlCode; // code used to create this table in SQLite 
	
	
	/**
	 * @param name
	 * @param columnNames
	 * @param sqlCode
	 */
	public TableInfo(String name, List<ColumnInfo> columns, String sqlCode) {
		this.name = name;
		this.columns = columns;
		this.sqlCode = sqlCode;
	}
	/**
	 * 
	 */

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @return the columnNames
	 */
	public List<ColumnInfo> getColumnInfo() {
		return columns;
	}



	/**
	 * @return the sqlCode
	 */
	public String getSqlCode() {
		return sqlCode;
	}

	
		
}
