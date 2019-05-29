package edu.mcckc.dao;

import java.sql.SQLException;
import org.apache.logging.log4j.*;

public class SetupDao
    extends BaseDao
{
    private static Logger logger = LogManager.getLogger(SetupDao.class);

    public SetupDao()
    {
        super();
    }

    public SetupDao(String configFileName)
    {
        super(configFileName);
    }

    public void createDatabaseTables()
    {
        createUserTable();
       // createCustomerTable();
        createTaskTable();
    }

    private void createUserTable()
    {
    }

//    private void createCustomerTable()
//    {
//    }



    private void createTaskTable()
    {
        try
        {
            createDBConnnection();
            stmt = conn.createStatement();

            stmt.executeUpdate("DROP TABLE IF EXISTS TASKS;");

            sql = "CREATE TABLE TASKS  " +
                    "(   ID INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL" +
                    "   , TASK    TEXT" +
                    "   , DESCRIPTION    TEXT" +
                    "   , DATEPURCHASED   TEXT " +
                    "   , COMPLETE  INTEGER " +
                    "   , SIZE    INTEGER " +
                    "   , CATEGORIES   INTEGER ); " ;

            stmt.executeUpdate(sql);
            stmt.close();
        }
        catch(SQLException sqlex)
        {
            logger.error(sqlex.toString());
        }
    }
}
