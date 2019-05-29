package edu.mcckc.domain;

import java.util.Date;

public class Task
{
    /*
product
--------------------
id     int
name   string
price  double
active boolean
size   int/choice  radio button (large / med / sml)
color  int/choice  combobox (red/green/blue/yellow)
    */


    /* PRIMITIVE DATA TYPES CANNOT BE SET TO NULL (DARN)
     public int id;
    public String name;
    public String description;
    public double price;
    public boolean active;
    public int size;
    public int color;
    public Date datePurchased;
     */

    //      THIS ARE ALL OBJECTS

    public Integer id;
    public String TASK;
    public String description;
    public Date datePurchased;

    //public Double price;
    public Boolean complete;
    public Integer size;
    public Integer categories;


    @Override
    public String toString()
    {
        return "TASKS{" +
                "id=" + id +
                ", Task ='" + TASK + '\'' +
                '}';
    }


    public String toStringFull()
    {
        return "TASKS{" +
                "id=" + id +
                ", task='" + TASK + '\'' +
                ", description='" + description + '\'' +
                ", completed=" + complete +
                ", priority=" + size +
                ", categories=" + categories +
                ", dateDue=" + datePurchased +
               // ", price=" + price +


                '}';
    }
}
