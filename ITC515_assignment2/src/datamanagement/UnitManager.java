package  datamanagement;

import  java.util.List;
import  org.jdom.*;

public  class  UnitManager {

	private  static  UnitManager self  =  null;

	private  UnitMap  unitMap;

	public  static  UnitManager  unitMap() {

		if  (self  ==  null)

			self  =  new  UnitManager();

		return  self;
	}

	private  UnitManager()  {

		unitMap  =  new  UnitMap();
	}

	public  IUnit  getUnit(String  unitCode)  {

		IUnit  unit  =  unitMap.get(unitCode);

		return  unit  !=  null  ?  unit  :  createUnit(unitCode);

	}

	private  IUnit  createUnit(String unitCode) {

		IUnit  unit;

		for  (Element  el  :  (List<Element>)  XMLManager.getXML().getDocument()
				.getRootElement().getChild("unitTable").getChildren("unit"))

			if  (unitCode.equals(el.getAttributeValue("uid")))  {

				StudentUnitRecordList studentlist;

				studentlist = null;

				unit  =  new  Unit(el.getAttributeValue("uid"),
						el.getAttributeValue("name"),  Float.valueOf(
								el.getAttributeValue("ps")).floatValue(),  Float
						.valueOf(el.getAttributeValue("cr"))
						.floatValue(),  Float.valueOf(
								el.getAttributeValue("di")).floatValue(), Float
						.valueOf(el.getAttributeValue("hd"))
						.floatValue(),  Float.valueOf(
								el.getAttributeValue("ae")). floatValue(),
						Integer.valueOf(el.getAttributeValue("asg1wgt"))
						.intValue(),  Integer.valueOf(
								el.getAttributeValue("asg2wgt")).intValue(),
						Integer.valueOf(el.getAttributeValue("examwgt"))
						.intValue(),  StudentUnitRecordManager
						.instance().getRecordsByUnit(unitCode));
				unitMap.put(unit.getUnitCode(),  unit);
				return  unit;
			}

		throw  new  RuntimeException("DBMD:  createUnit  :  unit  not  in  file");
	}

	public  UnitMap  getUnits()  {

		UnitMap  unitMap;
		IUnit  unit;

		unitMap  =  new UnitMap();
		for  (Element  el  :  (List<Element>)  XMLManager.getXML().getDocument()
				.getRootElement().getChild("unitTable").getChildren("unit"))  {

			unit  =  new  UnitProxy(el.getAttributeValue("uid"),
					el.getAttributeValue("name"));
			unitMap.put(unit.getUnitCode(),  unit);

		} // unit maps are filled with PROXY units

		return unitMap;
	}

}
