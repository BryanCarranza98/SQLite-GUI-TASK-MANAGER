package edu.mcckc.dao;

import edu.mcckc.domain.*;
import java.sql.ResultSet;

public class TaskMapper
        extends BaseMapper
{
    public TaskMapper()
    {
        super();
    }

    public TaskMapper(ResultSet rs)
    {
        super(rs);
    }


    @Override
    public Object mapSingleObject()
    {
        Task rtnObj = new Task();

        rtnObj.id = mapValidInt("ID");
        rtnObj.TASK = mapValidString("TASK");
        rtnObj.description = mapValidString("DESCRIPTION");
        rtnObj.datePurchased = mapValidDate("DATEPURCHASED");
        //rtnObj.price = mapValidDouble("PRICE");
        rtnObj.complete = mapValidBoolean("COMPLETE");
        rtnObj.size = mapValidInt("SIZE");
        rtnObj.categories = mapValidInt("CATEGORIES");


        return rtnObj;
    }
}
