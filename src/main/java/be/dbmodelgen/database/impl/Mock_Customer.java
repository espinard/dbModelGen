package be.dbmodelgen.database.impl;

import be.dbmodelgen.database.Customer;

public class Mock_Customer implements Customer {

	private String name;
	private int cl_id;
	public Mock_Customer(String name, int id) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.cl_id = id;
	}

	
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	
	public int getID() {
		// TODO Auto-generated method stub
		return cl_id;
	}

	
	public int getAge() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public String getDefaultLanguage() {
		// TODO Auto-generated method stub
		return null;
	}

}
