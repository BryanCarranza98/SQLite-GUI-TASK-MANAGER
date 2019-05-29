package edu.mcckc.dao;

import org.apache.logging.log4j.*;

import java.sql.*;

//public class BaseDao
public abstract class BaseDao
{
   private static Logger logger = LogManager.getLogger(BaseDao.class);

   private static final String DEFAULT_CONFIG_FILE = "dbconfig.properties";

   private static final String DB_DRIVER_KEY = "DB_DRIVER";
   private static final String DB_CONNECTION_KEY = "DB_CONNECTION";
   private static final String DB_USER_KEY = "DB_USER";
   private static final String DB_PASSWORD_KEY = "DB_PASSWORD";


   private String configFileName;
   private DBConfigProperties dbConfigProperties;
   private String DB_DRIVER;
   private String DB_CONNECTION;
   private String DB_USER;
   private String DB_PASSWORD;

   protected Connection conn = null;
   protected Statement stmt = null;
   protected PreparedStatement ps = null;
   protected String     sql  = null;
   protected ResultSet rs  =  null;

   public BaseDao()
   {
      this(DEFAULT_CONFIG_FILE);
   }

   public BaseDao(String configFileName)
   {
      try
      {
         this.configFileName = configFileName;
         dbConfigProperties = new DBConfigProperties(this.configFileName);
         dbConfigProperties.displayProperties();
         storeProperties();
      }
      catch (Exception ex)
      {
         logger.error(ex.toString());
         logger.error("!!!  PROGRAM CANNOT CONTINUE !!!");
         System.exit(16);
      }
   }

   private void storeProperties()
   {
      DB_DRIVER = dbConfigProperties.getProperty(DB_DRIVER_KEY);
      DB_CONNECTION = dbConfigProperties.getProperty(DB_CONNECTION_KEY);
      DB_USER = dbConfigProperties.getProperty(DB_USER_KEY);
      DB_PASSWORD = dbConfigProperties.getProperty(DB_PASSWORD_KEY);

      logger.debug(String.format("KEY: %s  =  VAL: %s", DB_DRIVER_KEY, DB_DRIVER));
      logger.debug(String.format("KEY: %s  =  VAL: %s", DB_CONNECTION_KEY, DB_CONNECTION));
      logger.debug(String.format("KEY: %s  =  VAL: %s", DB_USER_KEY, DB_USER));
      logger.debug(String.format("KEY: %s  =  VAL: %s", DB_PASSWORD_KEY, DB_PASSWORD));
   }

   public Connection getConnection()
   {
      return conn;
   }

   public void createDBConnnection()
   {
      logger.debug(String.format("!!  INSIDE createDBConnnection  !!"));
      logger.debug(String.format("DRIVER: %s  ", DB_DRIVER));
      logger.debug(String.format("CONN: %s  ", DB_CONNECTION));
      try
      {
         Class.forName(DB_DRIVER);
         conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
      }
      catch(ClassNotFoundException cnfex)
      {
         logger.error(cnfex.toString());
         logger.error("PROGRAM CANNOT CONTINUE !!!");
         System.exit(16);
      }
      catch(SQLException sqex)
      {
         logger.error(sqex.toString());
         logger.error("PROGRAM CANNOT CONTINUE !!!");
         System.exit(16);
      }
   }

   public void cleanUpResources() throws Exception
   {

      try {

         if (rs != null) {
            rs.close();

         }

         if (ps != null) {
            ps.close();

         }

         if (conn != null) {
            conn.close();

         }

      }
      catch (SQLException sqlex)
      {
         logger.error(sqlex.toString());
         throw sqlex;

      }

   }




}
