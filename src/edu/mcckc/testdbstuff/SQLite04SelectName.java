package edu.mcckc.testdbstuff;

import java.sql.*;
import org.apache.logging.log4j.*;

public class SQLite04SelectName
{
   private static Logger logger = LogManager.getLogger(SQLite04SelectName.class);

   public static void main(String[] args)
   {
      Connection conn = null;
      Statement stmt = null;
      String     sql  = null;
      ResultSet rs  =  null;

      try
      {
         logger.debug("ABOUT TO CREATE THE DB CONNECTION !!!");
         logger.debug("ABOUT TO RUN THE SQL STATEMENT !!!");

         Class.forName("org.sqlite.JDBC");

         conn = DriverManager.getConnection("jdbc:sqlite:test.db");

         //  if we don't specify this here, we don't need to do a manual commit later
         ////////conn.setAutoCommit(false);

         stmt = conn.createStatement();

         //sql = "SELECT  SALARY, AGE, ID, NAME  FROM  CUSTOMER;  ";
         sql = "SELECT  *  FROM  CUSTOMER;  ";

         rs = stmt.executeQuery(sql);

         while(rs.next())
         {
            int  id  =  rs.getInt("ID");
            String name = rs.getString("NAME");
            int age = rs.getInt("AGE");
            double salary = rs.getDouble("SALARY");

            String output = String.format("RECORD: %d  / %s  / %d  / %.2f", id, name, age, salary);
            logger.debug(output);
         }

         //  if DB is in autocommit more, don't need to do this manual commit
         //////////////conn.commit();


         rs.close();
         stmt.close();
         conn.close();

         logger.debug("SUCCESSFULLY CREATED THE DB CONNECTION !!!");
         logger.debug("SUCCESSFULLY SELECTED DATA FROM THE CUSTOMER TABLE !!!");
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
