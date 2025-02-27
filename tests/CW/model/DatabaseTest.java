package CW.model;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class DatabaseTest {
    String filepathOrder = "resources\\order.txt";
    String filepathStock = "resources\\stock.txt";
    String filepathAdmin = "resources\\adminlogin.txt";
    Database database;
    @BeforeClass
    public static void setUpBefore(){
        System.out.println("** Before class **");
    }

    @Before
    public void setUp(){
        database = new Database();
    }

    @Test
    public void testGetCount(){
        database.loadOrderItems(new File(filepathOrder));

        assertEquals(database.getCount(), 13.29, 0.0);
    }

    @Test
    public void testGetStockItemAt(){
        database.loadStockItems(new File(filepathStock));

        String firstValue = "Meal";

        StockItem item = new StockItem();
        item.setBarcode("AC1827");
        item.setName("Meal");
        item.setPrice(5.98);

        assertEquals(database.getStockItemAt(firstValue).getBarcode(), item.getBarcode());
    }

    @Test
    public void testAdminCheck(){
        database.loadAdmin(new File(filepathAdmin));

        assertEquals(database.adminCheck("july78", "22222w2"),true );
    }

    @Test
    public void testCompareToGetCount(){
        database.loadOrderItems(new File(filepathOrder));

        assertEquals(database.compareToGetCount(19.27),true);
    }

}