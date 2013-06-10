package be.dbmodelgen.tests;








public class ForeignKeyStatsExtractor {
	
//	public int nbrRef = 0;
//	public int nbrFalseRef = 0;
//	public int nbrRefWithNoNameMatch= 0;
//	public int nbrFalseRefWithDifferentTypes=0;
//	public int nbrRefWithNoNameAndNoTypeMatch = 0;
//	public int nbrRefWithAtLeastOnMatchingProblem = 0;
//	public int nbrFalsePositive = 0;
//	public int nbrTruePositive = 0;
//	public int nbrFalseNegative = 0;
//	public static double treshold = 0.2;
//	public int totalNumberOfRefDetected= 0;
//	public Vector<String> alreadyFound = new Vector<String>();
//	
//	Hashtable<Integer,Integer> realImplicitFK = new Hashtable<Integer, Integer>();
//	Vector<String> fkNames = new Vector<String>();
//	Vector<IdInfo> idNames = new Vector<IdInfo>();
//	//JaroWinklerDistance jwd = new JaroWinklerDistance();
//	
//	private boolean yesOrNo(int funct, int flag){
//		int binAnd = funct & flag;
//		if (binAnd == flag){
//			return true;
//		}else{
//			return false;
//		}
//	}
//	
//	private DBMConstraintMember getOrigin(DBMGroup gr){
//		boolean trouve = false;
//		DBMConstraintMember cst = gr.getFirstConstraintMember();
//		while (cst!=null && !trouve){
//			if (cst.getMemberRole()==DBMConstraintMember.OR_MEM_CST){
//				trouve = true;
//			}else
//			    cst = gr.getNextConstraintMember(cst);
//		}
//		return cst;
//	}
//	
//	private DBMConstraintMember getTarget(DBMGroup gr){
//		boolean trouve = false;
//		DBMConstraintMember cst = gr.getFirstConstraintMember();
//		while (cst!=null && !trouve){
//			if (cst.getMemberRole()==DBMConstraintMember.TAR_MEM_CST){
//				trouve = true;
//			}else
//			    cst = gr.getNextConstraintMember(cst);
//		}
//		return cst;
//	}
//	
//	private DBMConstraintMember getTarget(DBMConstraint cons){
//		boolean trouve = false;
//		DBMConstraintMember cst = cons.getFirstConstraintMember();
//		while (cst!=null && !trouve){
//			if (cst.getMemberRole()==DBMConstraintMember.TAR_MEM_CST){
//				trouve = true;
//			}else
//			    cst = cons.getNextConstraintMember(cst);
//		}
//		return cst;
//	}
//	
//	
//	public boolean contains(Vector<IdInfo> v,DBMSimpleAttribute a){
//		ListIterator<IdInfo> infoIt = v.listIterator();
//		while (infoIt.hasNext()){
//			IdInfo info = infoIt.next();
//			if (info.col.getObjectIdentifier() == a.getObjectIdentifier()){
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	public Vector<FKDescriptor> getRefs(DBMSchema sch){
//		Vector<FKDescriptor> refs = new Vector<FKDescriptor>();
//		DBMEntityType ent = sch.getFirstDataObjectEntityType();
//		while (ent!= null){
//			DBMGroup gr = ent.getFirstGroup();
//			while (gr!=null){
//				DBMConstraintMember cm = getOrigin(gr);
//				if (cm!=null){
//					DBMConstraint cst = cm.getConstraint();
//					char grType = cst.getType();
//					if (grType == DBMConstraint.REF_CONSTRAINT){
//						cm = getTarget(cst);
//						if (cm!=null){
//							String targetName = cm.getGroup().getDataObject().getName();
//							//nbrRef++;
//							//System.out.println("      "+ent.getName()+" ---> "+targetName);
//							DBMAttribute colsource = gr.getFirstComponentSimpleAttribute();
//							DBMAttribute coltarget = cm.getGroup().getFirstComponentSimpleAttribute();
//							if (colsource!=null && coltarget!=null){
//								refs.add(new FKDescriptor(ent.getName(),colsource.getName(),targetName,coltarget.getName()));
//							}
//						}
//					}	
//				}
//				DBMLibrary lib = new DBMLibrary();
//				gr = ent.getNextGroup(gr);
//			}
//			ent = sch.getNextDataObjectEntityType(ent);
//		}
//		return refs;	
//	}
//	
//	public Vector<FKDescriptor> getFalseRefs(DBMSchema sch){
//		Vector<FKDescriptor> fr = new Vector<FKDescriptor>();
//		DBMEntityType ent = sch.getFirstDataObjectEntityType();
//		while (ent!= null){
//			DBMGroup gr = ent.getFirstGroup();
//			while (gr!=null){
//				int funct = gr.getFunction();
//				if (yesOrNo(funct, DBMGroup.CST_GR)){
//					String cstName = (String) gr.getMetaPropertyValue("User-constraint");
//					if (cstName.equals("false-ref")){
//						DBMConstraintMember cm = getOrigin(gr);
//						cm = getTarget(cm.getConstraint());
//						if (cm!=null){
//							String targetName = cm.getGroup().getDataObject().getName();
//							DBMSimpleAttribute colsource = gr.getFirstComponentSimpleAttribute();
//							DBMSimpleAttribute coltarget = cm.getGroup().getFirstComponentSimpleAttribute();
//							if (colsource!=null && coltarget!=null){
//								fr.add(new FKDescriptor(ent.getName(),colsource.getName(),targetName,coltarget.getName()));
//							}
//						}else{
//							System.out.println("null in "+ent.getName());
//						}
//						
//					}
//				}
//				gr = ent.getNextGroup(gr);
//			}
//			ent = sch.getNextDataObjectEntityType(ent);
//		}
//		return fr;	
//	}
//	
//	private void getRefFromEntityType(DBMEntityType ent){
//		DBMGroup gr = ent.getFirstGroup();
//		while (gr!=null){
//			DBMConstraintMember cm = getOrigin(gr);
//			if (cm!=null){
//				DBMConstraint cst = cm.getConstraint();
//				char grType = cst.getType();
//				if (grType == DBMConstraint.REF_CONSTRAINT){
//					cm = getTarget(cst);
//					if (cm!=null){
//						String targetName = cm.getGroup().getDataObject().getName();
//						nbrRef++;
//						//System.out.println("      "+ent.getName()+" ---> "+targetName);
//						String sourceColName="", targetColName ="";
//						char sourceType=1,targetType=1;
//						DBMSimpleAttribute source=null, target = null;
//						DBMAttribute col = gr.getFirstComponentSimpleAttribute();
//						while (col!=null){
//							//System.out.print(" "+ent.getName()+"."+col.getName());
//							sourceColName = col.getName();
//							source = (DBMSimpleAttribute)col;
//							sourceType = ((DBMSimpleAttribute)col).getType();
//							col = gr.getNextComponentSimpleAttribute((DBMSimpleAttribute)col);
//						}
//						col = cm.getGroup().getFirstComponentSimpleAttribute();
//						//System.out.print("  ---->  ");
//						while (col!=null){
//							//System.out.print(targetName+"."+col.getName());
//							targetColName = col.getName();
//							if (!contains(idNames, (DBMSimpleAttribute) col)){
//								idNames.add(new IdInfo((DBMEntityType)col.getAttributeOwner(), (DBMSimpleAttribute)col));
//							}
//							target = (DBMSimpleAttribute)col;
//							targetType = ((DBMSimpleAttribute)col).getType();
//							col = cm.getGroup().getNextComponentSimpleAttribute((DBMSimpleAttribute)col);
//						}
//						if(!matches(sourceColName,targetColName)){
//							//System.out.println("\n ==> FK names don't match");
//							nbrRefWithNoNameMatch++;	
//							nbrRefWithAtLeastOnMatchingProblem++;
//						}
//						realImplicitFK.put(new Integer(source.getObjectIdentifier()),new Integer(target.getObjectIdentifier()));
//						//System.out.println("\n string proximity: "+jwd.proximity(sourceColName,targetColName));						
//						//System.out.println("\n\n");
//					}
//				}	
//			}
//			DBMLibrary lib = new DBMLibrary();
//			gr = ent.getNextGroup(gr);
//		}
//	}	
//	private void getFalseRefFromEntityType(DBMEntityType ent){
//		DBMGroup gr = ent.getFirstGroup();
//		while (gr!=null){
//			int funct = gr.getFunction();
//			if (yesOrNo(funct, DBMGroup.CST_GR)){
//				String cstName = (String) gr.getMetaPropertyValue("User-constraint");
//				if (cstName.equals("false-ref")){
//					DBMConstraintMember cm = getOrigin(gr);
//					cm = getTarget(cm.getConstraint());
//					if (cm!=null){
//						String targetName = cm.getGroup().getDataObject().getName();
//						//System.out.println("      "+ent.getName()+" ---> "+targetName);
//						nbrFalseRef++;
//						String sourceColName="", targetColName ="";
//						DBMAttribute col = gr.getFirstComponentSimpleAttribute();
//						char sourceType=1,targetType=1;
//						DBMSimpleAttribute source=null, target = null;
//						while (col!=null){
//							//System.out.print(ent.getName()+"."+col.getName());
//							sourceColName = col.getName();
//							fkNames.add(sourceColName);
//							sourceType = ((DBMSimpleAttribute)col).getType();
//							source  = (DBMSimpleAttribute)col;
//							col = gr.getNextComponentSimpleAttribute((DBMSimpleAttribute)col);
//						}
//						col = cm.getGroup().getFirstComponentSimpleAttribute();
//						//System.out.print("  ---->  ");
//						while (col!=null){
//							//System.out.print(targetName+"."+col.getName());
//							targetType = ((DBMSimpleAttribute)col).getType();
//							target = (DBMSimpleAttribute)col;
//							targetColName = col.getName();
//							if (!contains(idNames, (DBMSimpleAttribute) col)){
//								idNames.add(new IdInfo((DBMEntityType)col.getAttributeOwner(), (DBMSimpleAttribute)col));
//							}
//							col = cm.getGroup().getNextComponentSimpleAttribute((DBMSimpleAttribute)col);
//						}
//						if(!matches(sourceColName,targetColName)){
//									//System.out.println("\n ==> FK names don't match");
//									nbrRefWithNoNameMatch++;
//									nbrRefWithAtLeastOnMatchingProblem++;
//									if (sourceType!=targetType){
//										//System.out.println("\n ==> FK and ID types don't match");
//										nbrRefWithNoNameAndNoTypeMatch++;
//										nbrFalseRefWithDifferentTypes++;
//									}
//						}else{
//							if (sourceType!=targetType){
//								//System.out.println("\n ==> FK types don't match");
//								nbrFalseRefWithDifferentTypes++;
//								nbrRefWithAtLeastOnMatchingProblem++;
//						}
//						}
//						//System.out.println("\n string proximity: "+jwd.proximity(sourceColName,targetColName));
//						realImplicitFK.put(new Integer(source.getObjectIdentifier()),new Integer(target.getObjectIdentifier()));
//						
//						//System.out.println("\n\n");
//					}else{
//						System.out.println("null in "+ent.getName());
//					}
//					
//				}
//			}
//			gr = ent.getNextGroup(gr);
//		}
//	}	
//	
//	public void findImplicitForeignKeys(DBMSchema sch){
//		nbrRef = 0;
//		nbrFalseRef = 0;
//		nbrRefWithNoNameMatch= 0;
//		nbrFalseRefWithDifferentTypes=0;
//		nbrRefWithNoNameAndNoTypeMatch = 0;
//		nbrRefWithAtLeastOnMatchingProblem = 0;
//		realImplicitFK = new Hashtable<Integer, Integer>();
//		fkNames = new Vector<String>();
//		idNames = new Vector<IdInfo>();
//		//System.out.println("REF groups:");
//		int i =0;
//		DBMEntityType ent = sch.getFirstDataObjectEntityType();
//		while (ent!=null){
//			i++;
//			getRefFromEntityType(ent);
//			ent = sch.getNextDataObjectEntityType(ent);
//		}
//		//System.out.println(" there is "+i+" entity types");
//		//System.out.println("FALSE-REF groups:");
//		ent = sch.getFirstDataObjectEntityType();
//		while (ent!=null){
//			getFalseRefFromEntityType(ent);
//			ent = sch.getNextDataObjectEntityType(ent);
//		}
//	}
//	
//	
//	public static boolean matches(String a, String b){
//		
//		JaroWinklerDistance jwd = new JaroWinklerDistance();
//		if (jwd.proximity(a,b) >= treshold || jwd.proximity(b,a) >= treshold){
//			return true;
//		}else{
//			return false;
//		}
//	}
//	
//	public static boolean matches2(String a, String b){
//		if (a.replace("_","").toUpperCase().contains(b.replace("_","").toUpperCase())||
//				b.replace("_","").toUpperCase().contains(a.replace("_","").toUpperCase())){
//			return true;
//		}else{
//			return false;
//		}
//				
//	}
//	
//	public static boolean matches(DBMSimpleAttribute a, DBMSimpleAttribute b){
//			if ( // les noms matchent
//					matches(a.getName(),b.getName()) &&
//					
//				//  les types sont les m�me
//					a.getType() == b.getType() &&
//					
//				//  les objets sont differents
//					a.getObjectIdentifier() != b.getObjectIdentifier()
//			){
//				return true;
//			}else{
//				return false;
//			}
//	}
//	
//	public boolean isARealForeignKey(DBMSimpleAttribute a, DBMSimpleAttribute b){
//		Integer c = realImplicitFK.get(new Integer(a.getObjectIdentifier()));
//		if (c!= null){
//			if (c.intValue()==b.getObjectIdentifier()){
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	
//	private class IdInfo{
//		public DBMEntityType table;
//		public DBMSimpleAttribute col;
//		
//		public IdInfo(DBMEntityType table, DBMSimpleAttribute col){
//			this.table = table;
//			this.col = col;
//		}
//		
//		public boolean matches3(DBMSimpleAttribute fkCandidate){
//			if((fkCandidate.getName().replace("_","").toUpperCase().contains(col.getName().replace("_","").toUpperCase())||
//				col.getName().replace("_","").toUpperCase().contains(fkCandidate.getName().replace("_","").toUpperCase()))&&
//				col.getType()==fkCandidate.getType() &&
//				!((fkCandidate.getAttributeOwner().getObjectIdentifier()==table.getObjectIdentifier()) &&
//				   fkCandidate.getObjectIdentifier()==col.getObjectIdentifier())){
//				//System.out.println(((DBMEntityType)fkCandidate.getAttributeOwner()).getName()+"."+fkCandidate.getName()+" ==> "+table.getName()+"."+col.getName());
//				return true;
//			}else{
//				return false;
//			}
//		}
//		
//		public boolean matches2(DBMSimpleAttribute fkCandidate){
//			//egalit� des noms et des types
//			if(     fkCandidate.getName().equals(col.getName())&&
//					col.getType()==fkCandidate.getType() &&
//					!((fkCandidate.getAttributeOwner().getObjectIdentifier()==table.getObjectIdentifier()) &&
//					   fkCandidate.getObjectIdentifier()==col.getObjectIdentifier())){
//					//System.out.println(((DBMEntityType)fkCandidate.getAttributeOwner()).getName()+"."+fkCandidate.getName()+" ==> "+table.getName()+"."+col.getName());
//					return true;
//				}else{
//					return false;
//				}
//		}
//		
//		public boolean matches(DBMSimpleAttribute fkCandidate){
//			if(ForeignKeyStatsExtractor.matches(fkCandidate,col)){
//					return true;
//				}else{
//					return false;
//				}
//			 
//		}
//		
//		
//	}
//	
//	public void displayRecallAndPrecisionStatistics(DBMSchema sch){
//		findImplicitForeignKeys(sch);
//		findPossibleForeignKeys(sch);
//		int totalRef = nbrFalseRef+nbrRef;
//		float totalImplicitRef = totalRef;
//		float noNameMatchRef= nbrRefWithNoNameMatch;
//		float noTypeMatchRef=nbrFalseRefWithDifferentTypes;
//		float notTypeAndNoNameMatchRef = nbrRefWithNoNameAndNoTypeMatch;
//		float matchingProblemRef = nbrRefWithAtLeastOnMatchingProblem;
//		System.out.println("\n"+totalRef+" implicit refs");
//		System.out.println("\n"+nbrRef+" normal refs");
//		System.out.println("\n"+nbrFalseRef+" false refs\n");
//		float percentageNoNameMatch = (noNameMatchRef/totalImplicitRef)*100;
//		float percentageNoTypeMatch =(noTypeMatchRef/totalImplicitRef)*100;
//		float percentageNoNameAndNoMatch= (notTypeAndNoNameMatchRef/totalImplicitRef)*100;
//		float percentageAtLeastOneMatchingProblem= (matchingProblemRef/totalImplicitRef)*100;
//		System.out.println("\n"+nbrRefWithNoNameMatch+" refs with no name match ("+percentageNoNameMatch+"%)");
//		System.out.println("\n"+nbrFalseRefWithDifferentTypes+" refs with no type match ("+percentageNoTypeMatch+"%)");
//		System.out.println("\n"+nbrRefWithNoNameAndNoTypeMatch+" refs with no name AND no type match ("+percentageNoNameAndNoMatch+"%)");
//		System.out.println("\n"+nbrRefWithAtLeastOnMatchingProblem+" refs with no name OR no type match ("+percentageAtLeastOneMatchingProblem+"%)");
//		float recall = 100 - percentageAtLeastOneMatchingProblem;
//		System.out.println("\n Recall of schema analysis technique: "+recall+"%\n");	
//		float truePositive = nbrTruePositive;
//		float falsePositive = nbrFalsePositive;
//		System.out.println("\n total number of foreign keys detected: "+totalNumberOfRefDetected);
//		System.out.println("\n nbr false positive: "+nbrFalsePositive);
//		System.out.println("\n nbr true positive: "+nbrTruePositive);
//		float precision = (truePositive/(truePositive+falsePositive))*100;
//		System.out.println("\n Precion of schema analysis technique: "+precision+"%");
//	}
//	
//	public String computeRecallAndPrecisionWithRespectToNameDistanceTreshold(DBMSchema sch){
//		treshold = 0.0;
//		String stats = "\\textbf{Treshold}&\\textbf{$P$}&\\textbf{$TP$}&\\textbf{$FP$}&\\textbf{$FN$}&\\textbf{$precision$}&\\textbf{recall}\\";
//		while (treshold <=1){
//			findImplicitForeignKeys(sch);
//			System.out.println(realImplicitFK.size()+" fk's");
//			findPossibleForeignKeys(sch);
//			// precision
//			float truePositive = nbrTruePositive;
//			float falsePositive = nbrFalsePositive;
//			float precision = (truePositive/(truePositive+falsePositive))*100;
//			// recall
//			int totalRef = nbrFalseRef+nbrRef;
//			float totalImplicitRef = totalRef;
//			float noNameMatchRef= nbrRefWithNoNameMatch;
//			float noTypeMatchRef=nbrFalseRefWithDifferentTypes;
//			float notTypeAndNoNameMatchRef = nbrRefWithNoNameAndNoTypeMatch;
//			float matchingProblemRef = nbrRefWithAtLeastOnMatchingProblem;
//			float percentageNoNameMatch = (noNameMatchRef/totalImplicitRef)*100;
//			float percentageNoTypeMatch =(noTypeMatchRef/totalImplicitRef)*100;
//			float percentageNoNameAndNoMatch= (notTypeAndNoNameMatchRef/totalImplicitRef)*100;
//			float percentageAtLeastOneMatchingProblem= (matchingProblemRef/totalImplicitRef)*100;
//			float recall = (truePositive / totalImplicitRef)*100;
//			
//			stats+=""+treshold+"&"+totalNumberOfRefDetected+"&"+nbrTruePositive+"&"+nbrFalsePositive+"&"+nbrFalseNegative+"&"+recall+"&"+precision+"\\\\\n";
//			treshold = treshold + 0.1;
//		}
//		return stats;
//	}
//	
//	public void getNumberOfPossibleForeignKeys(DBMSchema sch, IdInfo idInfo){
//		DBMSimpleAttribute col = sch.getFirstDataObjectSimpleAttribute();
//		alreadyFound = new Vector<String>();
//		while (col != null){
//			if (matches(col, idInfo.col)){
//				totalNumberOfRefDetected++;
//				if (!isARealForeignKey(col, idInfo.col)){
//					nbrFalsePositive++;
//				}else{
//					if (!alreadyFound.contains(new String(""+col.getObjectIdentifier()+"-"+idInfo.col.getObjectIdentifier()))){
//							System.out.println(((DBMEntityType)col.getAttributeOwner()).getName()+"."+col.getName()+" --> "+((DBMEntityType)idInfo.col.getAttributeOwner()).getName()+"."+idInfo.col.getName());
//							nbrTruePositive++;
//							alreadyFound.add(""+col.getObjectIdentifier()+"-"+idInfo.col.getObjectIdentifier());
//					}
//				}
//			}else{
//				if(isARealForeignKey(col, idInfo.col)){
//					nbrFalseNegative++;
//				}
//			}
//			col = sch.getNextDataObjectSimpleAttribute(col);
//		}
//	}
//	
//	public void findPossibleForeignKeys(DBMSchema sch){
//		totalNumberOfRefDetected = 0;
//		nbrFalsePositive = 0;
//		nbrTruePositive = 0;
//		nbrFalseNegative = 0;
//		ListIterator<IdInfo> idNameIt = idNames.listIterator();
//		while (idNameIt.hasNext()){
//			IdInfo idInfo = idNameIt.next();	
//			getNumberOfPossibleForeignKeys(sch, idInfo);
//		}
//	}
//	
//	public static void runDBM (){
//		try {
//			DBMConsole c = new DBMConsole();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		ForeignKeyStatsExtractor statExt = new ForeignKeyStatsExtractor();
//		DBMLibrary lib =new DBMLibrary();
//		DBMProject sys = new DBMProject();
//		DBMSchema sch = lib.getCurrentDBMSchema();
//	//	JaroWinklerDistance jd = new JaroWinklerDistance();
//		//System.out.println("a,a: "+jd.proximity("a", "a"));
//		if (sch != null) {
//			// statExt.displayRecallAndPrecisionStatistics(sch);
//			System.out.println(statExt.computeRecallAndPrecisionWithRespectToNameDistanceTreshold(sch));
//		}	
//	}
//	
//	
	}
