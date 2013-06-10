package be.dbmodelgen.database.impl;

import be.dbmodelgen.database.Meal;

public class Mock_Meal implements Meal {

	private int meal_id;
	private String description;
	
	
	/**
	 * @param meal_id
	 * @param description
	 */
	public Mock_Meal(int meal_id, String description) {
		this.meal_id = meal_id;
		this.description = description;
	}

	
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
