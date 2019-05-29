package edu.mcckc.testdbstuff;

import java.sql.*;
import org.apache.logging.log4j.*;

public class SQLite05Update
{
   private static Logger logger = LogManager.getLogger(SQLite05Update.class);

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

         conn.setAutoCommit(false);

         stmt = conn.createStatement();

         sql = "UPDATE  CUSTOMER  SET  SALARY = 75000.00  WHERE  ID = 1;  ";
         stmt.executeUpdate(sql);

         conn.commit();




         sql = "SELECT  *  FROM  CUSTOMER;  ";

         rs = stmt.executeQuery(sql);

         while(rs.next())
         {
            int  id  =  rs.getInt(1);
            String name = rs.getString(2);
            int age = rs.getInt(3);
            double salary = rs.getDouble(4);

            String output = String.format("RECORD: %d  / %s  / %d  / %.2f", id, name, age, salary);
            logger.debug(output);
         }



         rs.close();
         stmt.close();
         conn.close();

         logger.debug("SUCCESSFULLY CREATED THE DB CONNECTION !!!");
         logger.debug("SUCCESSFULLY UPDATED AND SELECTED DATA FROM THE CUSTOMER TABLE !!!");
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
