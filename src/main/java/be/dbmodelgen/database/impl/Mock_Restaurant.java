package be.dbmodelgen.database.impl;

import be.dbmodelgen.database.Address;
import be.dbmodelgen.database.RestaurantDeprecated;

public class Mock_Restaurant implements RestaurantDeprecated {

	private int rest_id;
	
	/**
	 * @param rest_id
	 */
	public Mock_Restaurant(int rest_id) {
		this.rest_id = rest_id;
	}

	public Mock_Restaurant() {
		// TODO Auto-generated constructor stub
	}

	
	public int getID() {
		// TODO Auto-generated method stub
		return rest_id;
	}

	
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Address getAddress() {
		// TODO Auto-generated method stub
		return null;
	}

}
