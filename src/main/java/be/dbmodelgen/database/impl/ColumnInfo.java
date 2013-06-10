package be.dbmodelgen.database.impl;

import org.dynamicschema.reification.Column;

public class ColumnInfo {
	
		private Column column;
		private boolean primaryKey;
		private boolean notNull; 

		/**
		 * @param colName
		 * @param primaryKey
		 */
		public ColumnInfo(Column colName, boolean primaryKey, boolean notNull) {
			this.column = colName;
			this.primaryKey = primaryKey;
			this.notNull = notNull;
		}

		/**
		 * @return the colName
		 */
		public Column getColumn() {
			return column;
		}

		/**
		 * @param colName the colName to set
		 */
		public void setColName(Column colName) {
			this.column = colName;
		}

		/**
		 * @return the primaryKey
		 */
		public boolean isPrimaryKey() {
			return primaryKey;
		}

		/**
		 * @param primaryKey the primaryKey to set
		 */
		public void setPrimaryKey(boolean primaryKey) {
			this.primaryKey = primaryKey;
		}

		/**
		 * @return the notNull
		 */
		public boolean isNotNull() {
			return notNull;
		}

		/**
		 * @param notNull the notNull to set
		 */
		public void setNotNull(boolean notNull) {
			this.notNull = notNull;
		} 
		
		

}
