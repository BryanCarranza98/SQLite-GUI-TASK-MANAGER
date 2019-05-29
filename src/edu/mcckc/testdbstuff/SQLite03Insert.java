package edu.mcckc.testdbstuff;

import java.sql.*;
import org.apache.logging.log4j.*;

public class SQLite03Insert
{
   private static Logger logger = LogManager.getLogger(SQLite03Insert.class);

   public static void main(String[] args)
   {
      Connection conn = null;
      Statement stmt = null;
      String     sql  = null;

      try
      {
         logger.debug("ABOUT TO CREATE THE DB CONNECTION !!!");
         logger.debug("ABOUT TO RUN THE SQL STATEMENT !!!");

         Class.forName("org.sqlite.JDBC");

         conn = DriverManager.getConnection("jdbc:sqlite:test.db");

         //  if we don't specify this here, we don't need to do a manual commit later
         ////////conn.setAutoCommit(false);

         stmt = conn.createStatement();


         sql = "INSERT INTO CUSTOMER (ID, NAME, AGE, SALARY) " +
               " VALUES (1, 'Mickey Mouse', 3, 15000.0);  ";

         stmt.executeUpdate(sql);


         sql = "INSERT INTO CUSTOMER (ID, NAME, AGE, SALARY) " +
                 " VALUES (2, 'Donald Duck', 4, 25000.0);  ";

         stmt.executeUpdate(sql);

         sql = "INSERT INTO CUSTOMER (ID, NAME, AGE, SALARY) " +
                 " VALUES (3, 'Sandy Beach', 29, 95000.0);  ";

         stmt.executeUpdate(sql);


         //  if DB is in autocommit more, don't need to do this manual commit
         //////////////conn.commit();



         stmt.close();
         conn.close();

         logger.debug("SUCCESSFULLY CREATED THE DB CONNECTION !!!");
         logger.debug("SUCCESSFULLY INSERTED DATA INTO THE CUSTOMER TABLE !!!");
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
