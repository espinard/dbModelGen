package be.dbmodelgen.database;

public interface Location {

		double getLatitude();
		double getLongitude();
		void setLatitude(double lat);
		void setLongitude(double longitude);
		double distanceTo(Location loc);
		String getLocationName();
}
