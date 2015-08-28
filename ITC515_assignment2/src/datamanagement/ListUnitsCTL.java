package datamanagement;

public class ListUnitsCTL {
    private UnitManager unitManager;
    
    public ListUnitsCTL() {
        unitManager = UnitManager.unitMap();
    }

    public void listUnits(IUnitLister unitLister) {
    	unitLister.clearUnits();
    	UnitMap units = unitManager.getUnits();
        for (String s : units.keySet())
        	unitLister.addUnit(units.get(s));
    }
}
