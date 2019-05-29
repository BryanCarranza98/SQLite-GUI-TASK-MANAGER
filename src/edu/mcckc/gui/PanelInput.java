package edu.mcckc.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.mcckc.dao.TaskDao;
import edu.mcckc.domain.*;
import org.apache.logging.log4j.*;


public class PanelInput
    extends JPanel
    implements ActionListener
{
    private static Logger logger = LogManager.getLogger(PanelInput.class);


    private PanelEdit pnlEdit;
    public void setEditPanelReference(PanelEdit pnlEdit)
    {
        this.pnlEdit = pnlEdit;
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        logger.debug("INSIDE   PanelInput   actionPerformed !!!");

        Task temp = new Task();
        TaskDao dao = new TaskDao("sqliteconfig.properties");

        try
        {
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

            Categories categories =  (Categories)cboPriority.getSelectedItem();

            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String sDatePurchased = datDatePurchased.getStringValue();
            Date dtPurchased = df.parse(sDatePurchased);


            logger.debug(String.format("NAME: %s", sName));
            logger.debug(String.format("DESCRIPTION: %s", sDescription));
            logger.debug(String.format("DATE PURCH: %s", dtPurchased.toString()));

            //logger.debug(String.format("PRICE: %.2f", dPrice));
            logger.debug(String.format("SIZE: %d", iSize));
            logger.debug(String.format("Complete: %b", bActive));
            logger.debug(String.format("Categories: %d", categories.ordinal()));


            temp.id = -1;
            temp.TASK = sName;
            temp.categories = categories.ordinal();
            temp.description = sDescription;
            temp.datePurchased = dtPurchased;

            temp.size = iSize;
            //temp.price = dPrice;
            temp.complete = bActive;

            dao.createSingleObject(temp);

            pnlEdit.fillProductComboBox();
        }
        catch(Exception ex)
        {
            logger.error(ex.toString());
        }
    }

    ////////public enum ProductColors { Red, Blue, Green, Yellow };

    private JDataInput datTask;
    private JDataInput datDescription;
    private JDataInput datDatePurchased;
    //private JDataInput datPrice;
    private JCheckBox chkCompleted;

    private JRadioButton rdoLow;
    private JRadioButton rdoMedium;
    private JRadioButton rdoHigh;
    private ButtonGroup  grpSizes;

    private JComboBox cboPriority;
    private JButton btnSubmit;

    public PanelInput()
    {
        datTask = new JDataInput("Task:", false);
        datDescription = new JDataInput("Description:", true);
        //datPrice = new JDataInput("Price:", false);
        datDatePurchased = new JDataInput("(mm/dd/yyyy)Date Due:", false);
        chkCompleted = new JCheckBox("Completed");
        rdoLow = new JRadioButton("Low");
        rdoMedium = new JRadioButton("Medium");
        rdoHigh = new JRadioButton("High");
        grpSizes = new ButtonGroup();
        grpSizes.add(rdoLow);
        grpSizes.add(rdoMedium);
        grpSizes.add(rdoHigh);
        cboPriority = new JComboBox<Categories>(Categories.values());

        btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(this);
        btnSubmit.setActionCommand("submit");


        Box boxVertical = Box.createVerticalBox();
        Box boxHorizontal = Box.createHorizontalBox();

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
        boxVertical.add(btnSubmit);

        add(boxVertical);
    }


}
