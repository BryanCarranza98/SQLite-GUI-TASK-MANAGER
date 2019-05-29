package edu.mcckc.gui;

import edu.mcckc.dao.TaskDao;
import org.apache.logging.log4j.*;
import edu.mcckc.domain.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



public class PanelEdit
        extends JPanel
        implements ActionListener
{
   private static Logger logger = LogManager.getLogger(PanelEdit.class);
   private Task selectedTask;

   public void fillProductComboBox()
   {
      logger.debug(" !!!!    FILL THE PRODUCT COMBO BOX !!!");
      TaskDao dao = new TaskDao("sqliteconfig.properties");

      Task filter = new Task();
      filter.TASK = "";
      ArrayList<Object> products;
      try
      {
         cboCategories.removeAllItems();
         products = dao.getManyObjects(filter);
         for(Object obj : products)
         {
            cboCategories.addItem(    (Task)obj      );
         }
      }
      catch(Exception ex)
      {
         logger.error(ex.toString());
      }
   }


   @Override
   public void actionPerformed(ActionEvent e)
   {

      if (e.getActionCommand().toLowerCase().equals("delete"))
      {
         logger.debug("PANELEDIT  !!!!    DELETE  BUTTON !!!");
         selectedTask = (Task)cboCategories.getSelectedItem();

         logger.debug("You SELECTED: " + selectedTask.toStringFull());


         try
         {
            TaskDao dao = new TaskDao("sqliteconfig.properties");
            dao.deleteSingleObject(selectedTask);
            fillProductComboBox();
         }

         catch (Exception ex)
         {
            logger.error(ex.toString());
         }
      }





      if (e.getActionCommand().toLowerCase().equals("select"))
      {
         logger.debug("PANELEDIT  !!!!    SELECT  BUTTON !!!");

         selectedTask = (Task)cboCategories.getSelectedItem();

         logger.debug("YOU SELECTED : " + selectedTask.toStringFull());

         datTask.setInputValue(selectedTask.TASK);
         datDescription.setInputValue(selectedTask.description);
         //datPrice.setInputValue(String.valueOf( selectedProduct.price));

         String datePurchased;
         SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
         datePurchased = df.format(selectedTask.datePurchased);
         datDatePurchased.setInputValue(datePurchased);

         chkCompleted.setSelected( selectedTask.complete  );

         if (selectedTask.size == 1)
         {
            rdoLow.setSelected(true);
         }
         if (selectedTask.size == 2)
         {
            rdoMedium.setSelected(true);
         }
         if (selectedTask.size == 3)
         {
            rdoHigh.setSelected(true);
         }


         Categories selectedCategories;

         selectedCategories = Categories.values()[selectedTask.categories];                     ///////////

         logger.debug("SELECTED categories:  " + selectedCategories);

         cboPriority.setSelectedItem(selectedCategories);

      }

      if (e.getActionCommand().toLowerCase().equals("submit"))
      {
         logger.debug("PANELEDIT  !!!!    SUBMIT  BUTTON !!!");



         Task temp = new Task();
         TaskDao dao = new TaskDao("sqliteconfig.properties");


         try
         {
            temp = (Task) cboCategories.getSelectedItem();

            String sName = datTask.getStringValue();
            String sDescription = datDescription.getStringValue();

            //double dPrice = datPrice.getDoubleValue();

            boolean bActive = chkCompleted.isSelected();

            int iSize = -1;
            if (rdoLow.isSelected())
            {
               iSize = 1;
            }
            if (rdoMedium.isSelected())
            {
               iSize = 2;
            }
            if (rdoHigh.isSelected())
            {
               iSize = 3;
            }

            Categories categories = (Categories) cboPriority.getSelectedItem();  /////////

            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String sDatePurchased = datDatePurchased.getStringValue();
            Date dtPurchased = df.parse(sDatePurchased);


//            logger.debug(String.format("ID: %d", temp.id));
//            logger.debug(String.format("NAME: %s", sName));
//            logger.debug(String.format("DESCRIPTION: %s", sDescription));
//            logger.debug(String.format("PRICE: %.2f", dPrice));
//            logger.debug(String.format("SIZE: %d", iSize));
//            logger.debug(String.format("ACTIVE: %b", bActive));
//            logger.debug(String.format("COLOR: %d", color.ordinal()));
//            logger.debug(String.format("DATE PURCH: %s", dtPurchased.toString()));


            ////////////temp.id = temp.id;
            temp.TASK = sName;
            temp.categories = categories.ordinal();
            temp.description = sDescription;
            temp.datePurchased = dtPurchased;
            temp.size = iSize;
            //temp.price = dPrice;
            temp.complete = bActive;


            dao.updateSingleObject(temp);

            fillProductComboBox();
         }
         catch (Exception ex)
         {
            logger.error(ex.toString());
         }


      }
   }

   /////////////public enum ProductColors { Red, Blue, Green, Yellow };

   private JComboBox<Task> cboCategories;
   private JButton btnSelect;
   private JButton btnDelete;


   private JDataInput datTask;
   private JDataInput datDescription;
   //private JDataInput datPrice;
   private JDataInput datDatePurchased;
   private JCheckBox chkCompleted;

   private JRadioButton rdoLow;
   private JRadioButton rdoMedium;
   private JRadioButton rdoHigh;
   private ButtonGroup  grpSizes;

   private JComboBox cboPriority;
   private JButton btnEdit;

   public PanelEdit()
   {
      cboCategories = new JComboBox<Task>();
      btnSelect = new JButton("Select");
      btnDelete = new JButton("Delete");


      datTask = new JDataInput("Task:", false);
      datDescription = new JDataInput("Description:", true);
      //datPrice = new JDataInput("Price:", false);
      datDatePurchased = new JDataInput("Date Purchased:", false);

      datTask.setInputValue("Hammer");
      datDescription.setInputValue("This is a really awesome hammer !!!");
      //datPrice.setInputValue("3.14");

      chkCompleted = new JCheckBox("Completed");
      rdoLow = new JRadioButton("Low");
      rdoMedium = new JRadioButton("Medium");
      rdoHigh = new JRadioButton("High");
      grpSizes = new ButtonGroup();
      grpSizes.add(rdoLow);
      grpSizes.add(rdoMedium);
      grpSizes.add(rdoHigh);
      cboPriority = new JComboBox<Categories>(Categories.values());


      btnSelect = new JButton("Select");
      btnSelect.addActionListener(this);
      btnSelect.setActionCommand("select");

      btnDelete = new JButton("Delete");
      btnDelete.addActionListener(this);
      btnDelete.setActionCommand("delete");


      btnEdit = new JButton("Edit");
      btnEdit.addActionListener(this);
      btnEdit.setActionCommand("Edit");


      fillProductComboBox();


      Box boxVertical = Box.createVerticalBox();
      Box boxHorizontal = Box.createHorizontalBox();

      boxVertical.add(cboCategories);


      boxVertical.add(btnSelect);




      boxVertical.add(datTask);
      boxVertical.add(datDescription);
      //boxVertical.add(datPrice);
      boxVertical.add(datDatePurchased);

      boxVertical.add(chkCompleted);
      boxHorizontal.add(rdoLow);
      boxHorizontal.add(rdoMedium);
      boxHorizontal.add(rdoHigh);
      boxVertical.add(boxHorizontal);
      boxVertical.add(cboPriority);



      Box boxHorizontalButtons = Box.createHorizontalBox();
      boxHorizontalButtons.add(btnDelete);
      boxHorizontalButtons.add(btnEdit);
      boxVertical.add(boxHorizontalButtons);

      add(boxVertical);
   }
}
