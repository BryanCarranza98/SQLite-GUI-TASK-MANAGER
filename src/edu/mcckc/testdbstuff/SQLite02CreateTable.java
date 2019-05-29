package edu.mcckc.testdbstuff;

import java.sql.*;
import org.apache.logging.log4j.*;

public class SQLite02CreateTable
{
   private static Logger logger = LogManager.getLogger(SQLite02CreateTable.class);

   public static void main(String[] args)
   {
      Connection conn = null;
      Statement  stmt = null;
      String     sql  = null;

      try
      {
         logger.debug("ABOUT TO CREATE THE DB CONNECTION !!!");
         logger.debug("ABOUT TO RUN THE SQL STATEMENT !!!");

         Class.forName("org.sqlite.JDBC");

         conn = DriverManager.getConnection("jdbc:sqlite:test.db");
         stmt = conn.createStatement();

         stmt.executeUpdate("DROP TABLE IF EXISTS CUSTOMER;");

         sql = "CREATE TABLE CUSTOMER  " +
                 "(   ID INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL" +
                 "   , NAME     TEXT" +
                 "   , AGE      INTEGER " +
                 "   , SALARY   REAL ); ";

         stmt.executeUpdate(sql);
         stmt.close();
         conn.close();

         logger.debug("SUCCESSFULLY CREATED THE DB CONNECTION !!!");
         logger.debug("SUCCESSFULLY CREATED THE CUSTOMER TABLE !!!");
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
