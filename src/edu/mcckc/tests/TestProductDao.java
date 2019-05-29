package edu.mcckc.tests;

import edu.mcckc.dao.TaskDao;
import edu.mcckc.domain.Task;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

public class TestProductDao
{
    @Test
    public void TestConstructor()
    {
        TaskDao dao = new TaskDao("sqliteconfig.properties");
        Assert.assertNotNull(dao);
    }

    @Test
    public void TestInsert()
    {
        TaskDao dao = new TaskDao("sqliteconfig.properties");
        Assert.assertNotNull(dao);

        Task temp = new Task();
        temp.id = -1;
        temp.TASK = "LAUNDRY";
        temp.description = "DO ALL THE DIRTY CLOTHES !!!";
        temp.datePurchased = new Date();
        temp.complete = true;
        temp.categories = 1;
        temp.size = 1;

        try
        {
            dao.createSingleObject(temp);
        }
        catch (Exception ex)
        {

        }
    }

    @Test
    public void TestInsertMoreTestData()
    {
        TaskDao dao = new TaskDao("sqliteconfig.properties");
        Assert.assertNotNull(dao);

        Task temp = new Task();

        try
        {
            temp.id = -1;
            temp.TASK = "FEED #1";
            temp.description = "DO IT EARLY MORNING";
            temp.complete = false;
            temp.size = 3;
            temp.categories = 2;
            temp.datePurchased = new Date();

            dao.createSingleObject(temp);
//
//            temp.id = -1;
//            temp.name = "Level #1";
//            temp.description = "This is an awesome Level !!!";
//            temp.price = 13.14;
//            temp.active = true;
//            temp.color = 0;
//            temp.size = 2;
//            temp.datePurchased = new Date();
//            dao.createSingleObject(temp);
//
//            temp.id = -1;
//            temp.name = "Ladder #1";
//            temp.description = "This is an awesome Ladder !!!";
//            temp.price = 23.14;
//            temp.active = true;
//            temp.color = 3;
//            temp.size = 1;
//            temp.datePurchased = new Date();
//            dao.createSingleObject(temp);
//
//
//            temp.id = -1;
//            temp.name = "GlueGun #1";
//            temp.description = "This is an awesome GlueGun !!!";
//            temp.price = 33.14;
//            temp.active = false;
//            temp.color = 2;
//            temp.size = 1;
//            temp.datePurchased = new Date();
//            dao.createSingleObject(temp);
//
//
//            temp.id = -1;
//            temp.name = "RayGun #1";
//            temp.description = "This is an awesome RayGun !!!";
//            temp.price = 433.14;
//            temp.active = true;
//            temp.color = 2;
//            temp.size = 2;
//            temp.datePurchased = new Date();
//            dao.createSingleObject(temp);
//
//
//            temp.id = -1;
//            temp.name = "ToolBelt #1";
//            temp.description = "This is an awesome ToolBelt !!!";
//            temp.price = 73.14;
//            temp.active = true;
//            temp.color = 1;
//            temp.size = 1;
//            temp.datePurchased = new Date();
//            dao.createSingleObject(temp);
//
        }
        catch (Exception ex)
        {

        }
    }





    @Test
    public void TestGetOne()
    {
        TaskDao dao = new TaskDao("sqliteconfig.properties");
        Assert.assertNotNull(dao);

        Task temp = new Task();
        temp.id = 2;

        try
        {
            dao.getSingleObject(temp);
            Assert.assertTrue(true);
        }
        catch(Exception ex)
        {
            Assert.assertTrue(false);
        }
    }







    @Test
    public void TestGetMany()
    {
//        TaskDao dao = new TaskDao("sqliteconfig.properties");
//        Assert.assertNotNull(dao);
//
//        ArrayList<Object> products;
//        Task filter = new Task();
//        filter.id = -999;
//        filter.name = "";
//        filter.price = null; // means do not use
//        filter.active = false;
//        filter.size = null;
//        filter.color = 2;
//
//
//        try
//        {
//
//            products= dao.getManyObjects(filter);
//
//            Assert.assertTrue(true);
//
//            Assert.assertTrue(products.size() > 0);
//        }
//        catch(Exception ex)
//        {
//            Assert.assertTrue(false);
//        }
     }


}
