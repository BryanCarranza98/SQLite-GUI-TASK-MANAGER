package edu.mcckc.gui;

import edu.mcckc.dao.TaskDao;
import edu.mcckc.domain.Task;
import edu.mcckc.domain.Categories;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelDisplay
        extends JPanel
        implements ActionListener
{
   private static Logger logger = LogManager.getLogger(PanelDisplay.class);





   private JDataInput datTask;
   //private JDataInput datPrice;


   private JCheckBox chkUseComplete;

   //private JCheckBox chkUsePrice;
   private JCheckBox chkUseSize;
   private JCheckBox chkUseCategory;




   private JCheckBox chkComplete;



   private JRadioButton rdoLow;
   private JRadioButton rdoMedium;
   private JRadioButton rdoHigh;
   private ButtonGroup  grpSizes;

   private JComboBox cboCategories;
   private JButton btnSubmit;


   private JTextArea taOutput;
   private JScrollPane spOutput;

   public PanelDisplay()
   {



      datTask = new JDataInput("Task:", false);
      //datPrice = new JDataInput("Price:", false);

      datTask.setInputValue("");
      //datPrice.setInputValue("");

      chkUseComplete = new JCheckBox("Use Complete");
      chkUseCategory = new JCheckBox(" Use Category");
      //chkUsePrice = new JCheckBox("Use Price ");
      chkUseSize = new JCheckBox("Use Size");





      chkComplete = new JCheckBox("Complete");
      rdoLow = new JRadioButton("Low");
      rdoMedium = new JRadioButton("Medium");
      rdoHigh = new JRadioButton("High");
      grpSizes = new ButtonGroup();
      grpSizes.add(rdoLow);
      grpSizes.add(rdoMedium);
      grpSizes.add(rdoHigh);
      cboCategories = new JComboBox<Categories>(Categories.values());


      btnSubmit = new JButton("Submit");
      btnSubmit.addActionListener(this);
      btnSubmit.setActionCommand("submit");


      taOutput = new JTextArea(20, 70);
      spOutput = new JScrollPane(taOutput);

      Box boxVertical = Box.createVerticalBox();

      boxVertical.add(datTask);

      Box boxHorizontalUse = Box.createHorizontalBox();
      //boxHorizontalUse.add(chkUsePrice);
      boxHorizontalUse.add(chkUseComplete);
      boxHorizontalUse.add(chkUseSize);
      boxHorizontalUse.add(chkUseCategory);
      boxVertical.add(boxHorizontalUse);



      Box boxHorizontalCriteria = Box.createHorizontalBox();
      //boxHorizontalCriteria.add(datPrice);
      boxHorizontalCriteria.add(chkComplete);
      boxHorizontalCriteria.add(rdoLow);
      boxHorizontalCriteria.add(rdoMedium);
      boxHorizontalCriteria.add(rdoHigh);
      boxHorizontalCriteria.add(cboCategories);
      boxVertical.add(boxHorizontalCriteria);


      boxVertical.add(btnSubmit);

      boxVertical.add(spOutput);


      add(boxVertical);
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      logger.debug("     ABOUT TO DO FILTERING !!!!!!!!!!!!!!!!! ");


      TaskDao dao = new TaskDao("sqliteconfig.properties");
      Task filter = new Task();

      logger.debug("DEFAULT FILTER VALUEES: " + filter.toStringFull());

      filter.TASK = datTask.getStringValue();

//      if(chkUsePrice.isSelected())
//      {
//         filter.price=datPrice.getDoubleValue();
//      }


      if(chkUseComplete.isSelected())
      {
         filter.complete= chkComplete.isSelected();
      }


      if(chkUseSize.isSelected())
      {
         if (rdoLow.isSelected())
            {
            filter.size=1;
         }

         if (rdoMedium.isSelected())
         {
            filter.size=2;
         }


         if (rdoHigh.isSelected())
         {
            filter.size=3;
         }
      }


      if(chkUseCategory.isSelected())
      {

         Categories selectedColor =(Categories) cboCategories.getSelectedItem();
         filter.categories= selectedColor.ordinal();
      }


      taOutput.setText("");



      try
      {
         ArrayList<Object> products = dao.getManyObjects(filter);
         for(Object temp : products)
         {

            Task prd = (Task) temp;
            taOutput.append(prd.toStringFull());
            taOutput.append("\n");
         }

      }catch (Exception ex)
      {
         logger.error(ex.toString());

      }


   }






}
