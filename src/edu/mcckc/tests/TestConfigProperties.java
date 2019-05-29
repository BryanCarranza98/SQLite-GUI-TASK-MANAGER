package edu.mcckc.tests;

import edu.mcckc.dao.DBConfigProperties;
import org.junit.Assert;
import org.junit.Test;

public class TestConfigProperties
{
   @Test
   public void TestConfigConstructor()
   {
      //  this  code/call  SHOULD CRASH (we don't have that default filename
      try
      {
         DBConfigProperties props = new DBConfigProperties();
         Assert.assertTrue(false);
      }
      catch (Exception ex)
      {
         Assert.assertTrue(true);
      }
   }

   @Test
   public void TestConfigConstructorSupplyFilename()
   {
      //  this  code/call  SHOULD NOT CRASH (we DO have that supplied filename)
      try
      {
         DBConfigProperties props = new DBConfigProperties("sqliteconfig.properties");
         Assert.assertNotNull(props);
         Assert.assertTrue(true);
      }
      catch (Exception ex)
      {
         Assert.assertTrue(false);
      }
   }


   @Test
   public void TestConfigKeyValuePairs()
   {
      //  this  code/call  SHOULD NOT CRASH (we DO have that supplied filename)
      try
      {
         DBConfigProperties props = new DBConfigProperties("sqliteconfig.properties");
         Assert.assertNotNull(props);
         Assert.assertTrue(true);

         String key = "DB_DRIVER";
         String value = props.getProperty(key);
         Assert.assertEquals("org.sqlite.JDBC", value);

         key = "DB_CONNECTION";
         value = props.getProperty(key);
         Assert.assertEquals("jdbc:sqlite:test.db", value);
      }
      catch (Exception ex)
      {
         Assert.assertTrue(false);
      }
   }

}
