package edu.mcckc.dao;

import java.util.ArrayList;

public interface IPersistable
{
   public Object getSingleObject(Object obj)   throws Exception;
   public ArrayList<Object> getManyObjects(Object obj) throws  Exception;

   public void createSingleObject(Object obj)  throws Exception;
   public void createManyObjects(ArrayList<Object> objList);

   public void updateSingleObject(Object obj)  throws Exception;
   public void updateManyObjects(ArrayList<Object> objList);

   public void deleteSingleObject(Object obj)throws Exception;
   public void deleteManyObjects(ArrayList<Object> objList);


//   public Object getSingleObject(Object obj);
//   public ArrayList<Object> getManyObjects(Object obj);
//
//   public Object createSingleObject(Object obj);
//   public ArrayList<Object> createManyObjects(ArrayList<Object> objList);
//
//   public Object updateSingleObject(Object obj);
//   public ArrayList<Object> updateManyObjects(ArrayList<Object> objList);
//
//   public void deleteSingleObject(Object obj);
//   public void deleteManyObjects(ArrayList<Object> objList);
}
