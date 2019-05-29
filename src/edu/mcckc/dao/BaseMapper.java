package edu.mcckc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.logging.log4j.*;

public abstract class BaseMapper
{
   private static Logger logger = LogManager.getLogger(BaseMapper.class);
   protected ResultSet rs;

   public BaseMapper()
   {
      this.rs = null;
   }

   public BaseMapper(ResultSet rs)
   {
      this.rs = rs;
   }


   // THIS METHOD HAS TO BE DEFINED AND FILLED IN WITHIN ALL * CHILD * CLASSES/OBJECTS
   public abstract Object mapSingleObject();


   //  THESE METHODS BELOW ARE COMMON TO * ALL * INSTANCES OF THIS BASE MAPPER

   public int mapValidInt(String columnName)
   {
      int returnValue;
      try
      {
         returnValue = rs.getInt(columnName);
      }
      catch (SQLException sqex)
      {
         returnValue = 0;
         logger.error(sqex.toString());
      }
      logger.debug(String.format("Mapping integer column: %s  -- %d", columnName, returnValue));
      return returnValue;
   }

   public double mapValidDouble(String columnName)
   {
      double returnValue;
      try
      {
         returnValue = rs.getDouble(columnName);
      }
      catch (SQLException sqex)
      {
         returnValue = 0.0;
         logger.error(sqex.toString());
      }
      logger.debug(String.format("Mapping double column: %s  -- %.2f", columnName, returnValue));
      return returnValue;
   }

   public String mapValidString(String columnName)
   {
      String returnValue;
      try
      {
         returnValue = rs.getString(columnName);
      }
      catch (SQLException sqex)
      {
         returnValue = "";
         logger.error(sqex.toString());
      }
      logger.debug(String.format("Mapping string column: %s  -- %s", columnName, returnValue));
      return returnValue;
   }



   public boolean mapValidBoolean(String columnName)
   {
      boolean returnValue;
      try
      {
         returnValue = rs.getBoolean(columnName);
      }
      catch (SQLException sqex)
      {
         returnValue = false;
         logger.error(sqex.toString());
      }
      logger.debug(String.format("Mapping boolean column: %s  -- %b", columnName, returnValue));
      return returnValue;
   }




   public Date mapValidDate(String columnName)
   {
      Date returnValue;
      try
      {
         SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

         String strTemp = rs.getString(columnName);

         returnValue = df.parse(strTemp);

         //returnValue = rs.getDate(columnName);
      }
      catch (ParseException pex)
      {
         returnValue = new Date();
         logger.error(pex.toString());
      }
      catch (SQLException sqex)
      {
         returnValue = new Date();
         logger.error(sqex.toString());
      }
      logger.debug(String.format("Mapping date column: %s  -- %s", columnName, returnValue.toString()));
      return returnValue;
   }
}
