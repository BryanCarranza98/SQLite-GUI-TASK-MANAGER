package edu.mcckc.tests;

import edu.mcckc.dao.SetupDao;
import org.junit.Assert;
import org.junit.Test;

public class TestSetupDao
{
    @Test
    public void TestConstructor()
    {
        SetupDao dao = new SetupDao("sqliteconfig.properties");
        Assert.assertNotNull(dao);
    }

    @Test
    public void TestCreateTables()
    {
        SetupDao dao = new SetupDao("sqliteconfig.properties");
        Assert.assertNotNull(dao);

        dao.createDatabaseTables();
    }
}
