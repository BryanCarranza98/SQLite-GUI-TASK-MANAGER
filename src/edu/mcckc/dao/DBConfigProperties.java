package edu.mcckc.dao;

import org.apache.logging.log4j.*;
import java.io.*;
import java.util.Properties;

public class DBConfigProperties
{
   private static Logger logger = LogManager.getLogger(DBConfigProperties.class);
   private static final String DEFAULT_CONFIG_FILE = "dbconfig.properties";
   private String configFileName;
   private Properties dbProperties;


   public DBConfigProperties() throws Exception
   {
      this(DEFAULT_CONFIG_FILE);
   }

   public DBConfigProperties(String configFileName) throws Exception
   {
      logger.debug("INSIDE DBConfigProperties  using file:" + configFileName);
      this.configFileName = configFileName;
      dbProperties = new Properties();
      loadProperties();
      ///////////displayProperties();
   }

   public String getProperty(String key)
   {
      return dbProperties.getProperty(key);
   }

   public void displayProperties()
   {
      for (Object key : dbProperties.keySet())
      {
         logger.debug(String.format("KEY:  %s   VALUE:  %s", key, dbProperties.get(key)));
         System.out.println(dbProperties.get(key));
      }
   }

   private void loadProperties() throws Exception
   {
      logger.debug("INSIDE DBConfigProperties  loadProperties");
      try
      {
         FileInputStream inputStream = new FileInputStream(configFileName);
         dbProperties.load(inputStream);
         inputStream.close();
      }
      catch (FileNotFoundException fnfex)
      {
         logger.error(fnfex.toString());
         logger.error("!!!  PROGRAM CANNOT CONTINUE !!!");
         throw fnfex;
         //System.exit(16);
      }
      catch (IOException ioex)
      {
         logger.error(ioex.toString());
         logger.error("!!!  PROGRAM CANNOT CONTINUE !!!");
         throw ioex;
         //System.exit(16);
      }
   }
}
