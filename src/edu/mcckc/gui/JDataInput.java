package edu.mcckc.gui;

import javax.swing.*;
import java.awt.*;

public class JDataInput
    extends JPanel
{
    private JLabel lblInput;
    private JTextField txtInput;
    private JTextArea taInput;
    private JScrollPane spInput;
    private JLabel lblMessage;
    private boolean multiLine;

    public JDataInput()
    {

    }

    public JDataInput(String caption, boolean multiLine)
    {
        this.multiLine = multiLine;

        lblInput = new JLabel(caption);
        txtInput = new JTextField(20);
        taInput = new JTextArea(4, 20);
        spInput = new JScrollPane(taInput);
        lblMessage = new JLabel("");
        lblMessage.setForeground(Color.red);

        JPanel row1 = new JPanel();
        JPanel row2 = new JPanel();

        row1.add(lblInput);

        if (multiLine)
        {
            row1.add(spInput);
        }
        else
        {
            row1.add(txtInput);
        }

        row2.add(lblMessage);

        setLayout(new GridLayout(2, 1));
        this.add(row1);
        this.add(row2);
    }

    public void setInputValue(String value)
    {
        if (multiLine)
        {
            taInput.setText(value);
        }
        else
        {
            txtInput.setText(value);
        }
    }

    public double getDoubleValue()
    {
        double returnValue = 0.00;
        lblMessage.setText("");
        try
        {
            if (multiLine)
            {
                returnValue = Double.parseDouble( taInput.getText() );
            }
            else
            {
                returnValue = Double.parseDouble( txtInput.getText() );
            }
        }
        catch (NumberFormatException  nfex)
        {
            lblMessage.setText(nfex.toString());
            throw nfex;
        }
        return returnValue ;
    }


    public int getIntegerValue()
    {
        int returnValue = 0;
        lblMessage.setText("");
        try
        {
            if (multiLine)
            {
                returnValue = Integer.parseInt( taInput.getText() );
            }
            else
            {
                returnValue = Integer.parseInt( txtInput.getText() );
            }
        }
        catch (NumberFormatException  nfex)
        {
            lblMessage.setText(nfex.toString());
            throw nfex;
        }
        return returnValue ;
    }


    public String getStringValue()
    {
        //return txtInput.getText();

        String returnValue = "";
        lblMessage.setText("");
        try
        {
            if (multiLine)
            {
                returnValue = taInput.getText();
            }
            else
            {
                returnValue = txtInput.getText();
            }
        }
        catch (NumberFormatException  nfex)
        {
            lblMessage.setText(nfex.toString());
            throw nfex;
        }
        return returnValue ;
    }



    public void setMessage(String message)
    {
        lblMessage.setText(message);
    }
}
