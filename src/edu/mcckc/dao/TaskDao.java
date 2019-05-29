package edu.mcckc.dao;

import edu.mcckc.domain.*;
import org.apache.logging.log4j.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TaskDao
        extends BaseDao
        implements IPersistable
{
    private static Logger logger = LogManager.getLogger(TaskDao.class);

    public TaskDao()
    {
        super();
    }

    public TaskDao(String configFileName)
    {
        super(configFileName);
    }


    @Override
    public Object getSingleObject(Object obj) throws Exception
    {
        Task task = null;
        Task filter;
        filter = (Task)obj;

        try
        {
            createDBConnnection();
            sql = "SELECT ID, TASK, DESCRIPTION,  DATEPURCHASED,  COMPLETE, SIZE, CATEGORIES  " +
                    " FROM TASKS    "  +
                    " WHERE ID = ?  ";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, filter.id);
            rs = ps.executeQuery();

            TaskMapper mapper = new TaskMapper(rs);
            while (rs.next())
            {
                task = (Task)mapper.mapSingleObject();

                String output = String.format("DB TASKS: %d  /  %s  /  %s", task.id,
                        task.TASK, task.datePurchased); /// <------------
                logger.debug(output);
            }
            cleanUpResources();
        }
        catch (SQLException sqlex)
        {
            logger.error(sqlex.toString());
            throw sqlex;
        }
        return task;
    }

    @Override
    public ArrayList<Object> getManyObjects(Object obj) throws Exception
    {
        ArrayList<Object> tasks = new ArrayList<Object>();
        Task task = null;
        Task filter;
        filter = (Task)obj;

        try
        {
            // where     name    like '     %ham%' ;
            // and       active = true     ;f

            createDBConnnection();

           /* sql = "SELECT ID, NAME, DESCRIPTION, PRICE, ACTIVE, SIZE, COLOR, DATEPURCHASED  " +
                " FROM PRODUCT   "  +
                " WHERE NAME  LIKE   ? " +
                "AND   PRICE   <    ?   "    +           // add a criteria
                "AND   ACTIVE  =    ?    "   +
                "AND   SIZE   =    ?     "   +
                "AND   COLOR  =    ? ;   ";*/


            sql = "SELECT ID, TASK, DESCRIPTION,  DATEPURCHASED,  COMPLETE, SIZE, CATEGORIES  " +
                    " FROM TASKS   "  +
                    " WHERE TASK  LIKE   ? " ;


//            if (filter.price != null)
//            {
//                sql+=  "AND   PRICE   <     ?   ";
//            }

            if (filter.complete != null)
            {
                sql+=  "AND   ACTIVE  =    ?   ";
            }
            if (filter.size != null)
            {
                sql+= "AND   SIZE   =      ?   ";
            }
            if (filter.categories != null)
            {
                sql+=   "AND   CATEGORIES  =    ?   ";
            }

            sql += " ;";




            String nameFilter = "%" +  filter.TASK + "%";

            ps = conn.prepareStatement(sql);

            logger.debug("SQL: " + sql);



            int count = 1;
            ps.setString(count, nameFilter);



//            if (filter.price != null)
//            {
//                count ++;
//                ps.setDouble(count,filter.price);
//            }

            if (filter.complete != null)
            {
                count ++;
                ps.setBoolean(count,filter.complete);

            }
            if (filter.size != null)
            {
                count ++;
                ps.setInt(count,filter.size);

            }
            if (filter.categories != null)
            {
                count ++;
                ps.setInt(count,filter.categories);

            }




            rs = ps.executeQuery();

            TaskMapper mapper = new TaskMapper(rs);
            while (rs.next())
            {
                task = (Task)mapper.mapSingleObject();

                tasks.add(task);

                //String output = String.format("DB PRODUCT: %d  /  %s  /  %s", product.id,
                //       product.name, product.datePurchased);

                logger.debug(task.toStringFull());
            }

            cleanUpResources();
        }
        catch (SQLException sqlex)
        {
            logger.error(sqlex.toString());
            throw sqlex;
        }

        catch (Exception ex)
        {
            logger.error(ex.toString());
            throw ex;

        }
        return tasks;
    }

    @Override
    public void createSingleObject(Object obj) throws Exception
    {
        try
        {
//            "SELECT ID, TASK, DESCRIPTION,  DATEPURCHASED,  COMPLETE, SIZE, CATEGORIES  " +
//                    " FROM TASKS   "  +
            createDBConnnection();
            Task product = (Task)obj;

            sql = "INSERT INTO TASKS " +
                    " (TASK, DESCRIPTION,  DATEPURCHASED,  COMPLETE, SIZE, CATEGORIES) " +
                    " VALUES " +
                    "  ( ?,  ?,  ?,  ?,  ?,  ?  );   ";

            ps = conn.prepareStatement(sql);
            ps.setString(1, product.TASK);
            ps.setString(2, product.description);
            //ps.setDouble(3, product.price);
            ps.setBoolean (3, product.complete);
            ps.setInt(4, product.size);
            ps.setInt(5, product.categories);

            //  ("YYYY-MM-DD HH:MM:SS.SSS")
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String dbDate = df.format(product.datePurchased);
            ps.setString(6, dbDate);

            //ps.setDate(7, product.datePurchased);


            conn.setAutoCommit(true);
            ps.executeUpdate();
            cleanUpResources();
        }



        catch (SQLException sqlex)
        {
            logger.error(sqlex.toString());
            throw sqlex;
        }
        catch (Exception ex)
        {
            logger.error(ex.toString());
            throw ex;
        }
        finally
        {

        }


        //return null;
    }

    @Override
    public void createManyObjects(ArrayList<Object> objList) {
        //return null;
    }

    @Override
    public void updateSingleObject(Object obj) throws Exception
    {
        try
        {
            createDBConnnection();
            Task product = (Task)obj;

//            TASK, DESCRIPTION,  DATEPURCHASED,  COMPLETE, SIZE, CATEGORIES
            sql = "UPDATE TASKS  " +
                    " SET TASK = ? " +
                    " , DESCRIPTION = ? " +
                    " , DATEPURCHASED = ? " +
                    " , COMPLETE = ? " +
                    " , SIZE = ? " +
                    " , CATEGORIES = ? " +
                    " WHERE  ID = ? ; ";
/////////// MIGHT HAVE PROBLEMS BECAUSE THEY ARE NOT IN NUMBER ORDER
            ps = conn.prepareStatement(sql);
            ps.setString(1, product.TASK);
            ps.setString(2, product.description);
            //ps.setDouble(3, product.price);
            ps.setBoolean (3, product.complete);
            ps.setInt(4, product.size);
            ps.setInt(5, product.categories);

            //  ("YYYY-MM-DD HH:MM:SS.SSS")
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String dbDate = df.format(product.datePurchased);
            ps.setString(6, dbDate);

            ps.setInt(7, product.id );


            conn.setAutoCommit(true);
            ps.executeUpdate();
            cleanUpResources();
        }
        catch (SQLException sqlex)
        {
            logger.error(sqlex.toString());
            throw sqlex;
        }
        finally
        {

        }


    }

    @Override
    public void updateManyObjects(ArrayList<Object> objList) {
        //return null;
    }

    @Override
    public void deleteSingleObject(Object obj)  throws Exception
    {

        try
        {
            createDBConnnection();
            Task product = (Task)obj;

            sql = "DELETE FROM TASKS  " +
                    " WHERE  ID = ? ; ";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, product.id );

            conn.setAutoCommit(true);
            ps.executeUpdate();
            cleanUpResources();
        }
        catch (SQLException sqlex)
        {
            logger.error(sqlex.toString());
            throw sqlex;
        }
        catch (Exception ex)
        {
            logger.error(ex.toString());
            throw ex;
        }
        finally
        {

        }

    }

    @Override
    public void deleteManyObjects(ArrayList<Object> objList) {

    }
}
