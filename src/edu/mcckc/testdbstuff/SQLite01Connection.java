package edu.mcckc.testdbstuff;

import java.sql.*;
import org.apache.logging.log4j.*;

public class SQLite01Connection
{
   private static Logger logger = LogManager.getLogger(SQLite01Connection.class);

   public static void main(String[] args)
   {
      Connection conn = null;

      try
      {
         logger.debug("ABOUT TO CREATE THE DB CONNECTION !!!");

         Class.forName("org.sqlite.JDBC");
         conn = DriverManager.getConnection("jdbc:sqlite:test.db");

         logger.debug("SUCCESSFULLY CREATED THE DB CONNECTION !!!");
      }
      catch (ClassNotFoundException cnfex)
      {
         logger.error(cnfex.toString());
         System.exit(16);
      }
      catch(SQLException sqex)
      {
         logger.error(sqex.toString());
         System.exit(16);
      }
      catch(Exception ex)
      {
         logger.error(ex.toString());
         System.exit(16);
      }

   }
}
