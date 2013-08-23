package be.dbmodelgen.tests.sql.testDB;


public class GenInsert {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int cpt= 0;

		for (int i = 0; i < 3; i++) {
				int langID = i+1;
				String lang = getLang(i+1);

				for (int j = 11; j < 20; j++) {
					int ingrID = j+1; 
					String ingreName = "Ingr " + (j+1) + " " + lang;
					String str = "INSERT INTO \"Description2\" (\"id_ingredient\",\"id_language\",\"description\") VALUES ("+ingrID+ ","+ langID+ ",\" "+ ingreName +"\")";

					System.out.println(str);
					cpt++;
				}
			
			
		}
		
		System.out.println(cpt);

	}
	
	private static String getLang(int i){
		String lang ;
		if(i == 1)
			lang = " FR";
		else
			if(i == 2)
				lang = " EN";
			else
				lang = " NL";
		return lang;
	}

}
