package ca.mcgill.ecse321.foodtruck.persistence;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.foodtruck.model.Equipment;
import ca.mcgill.ecse321.foodtruck.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruck.model.MenuItem;
import ca.mcgill.ecse321.foodtruck.model.Supply;

public class TestPersistenceXStream {

	@Before
	public void setUp() throws Exception {
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		
		//create food items
		MenuItem burger = new MenuItem("Burger",4.50,0);
		MenuItem fries = new MenuItem("French Fries",2.00,0);
		
		//add to menu
		ftms.addMenuItem(burger);
		ftms.addMenuItem(fries);
		
		//Create supply and add to list
		Supply patty = new Supply("Patty",0);
		ftms.addSupply(patty);
	
		//Create equipment and add to list
		Equipment Spatula = new Equipment("Spatula", 0);
		ftms.addEquipment(Spatula);
	}

	@After
	public void tearDown() throws Exception {
		//clear menu
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		ftms.delete();
	}

	@Test
	public void test() {
		//save model
		FoodTruckManager ftms = FoodTruckManager.getInstance();
		PersistenceXStream.setFilename("test"+File.separator+"ca"+File.separator+"mcgill"+File.separator+"ecse321"+File.separator+"foodtruck"+File.separator+"persistence"+File.separator+"data.xml");
		PersistenceXStream.setAlias("item",MenuItem.class);
		PersistenceXStream.setAlias("manager",FoodTruckManager.class);
		
		if (!PersistenceXStream.saveToXMLwithXStream(ftms)) {
			fail("Could not load file.");
		}
		
		//clear model in memory
		ftms.delete();
		assertEquals(0, ftms.getMenuItems().size());
		
		//load model
		ftms = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		if (ftms == null) {
			fail("Could not load file");
		}
		
		//check menu items
		assertEquals(2, ftms.getMenuItems().size());
		assertEquals("Burger", ftms.getMenuItem(0).getName());
		assertEquals("French Fries", ftms.getMenuItem(1).getName());
		assertEquals(4.50, ftms.getMenuItem(0).getPrice(),0.004);
		assertEquals(2.00, ftms.getMenuItem(1).getPrice(),0.004);
		assertEquals(0, ftms.getMenuItem(0).getAmountSold());
		assertEquals(0, ftms.getMenuItem(1).getAmountSold());
		
		//check Supplies
		assertEquals(1, ftms.getSupplies().size());
		assertEquals("Patty", ftms.getSupply(0).getName());
		assertEquals(0, ftms.getSupply(0).getCount());
		
		//check equipments
		assertEquals(1, ftms.getEquipment().size());
		assertEquals("Spatula", ftms.getEquipment(0).getName());
		assertEquals(0, ftms.getEquipment(0).getCount());
	}

}