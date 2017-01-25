package com.dasha.equipment.bean;

import java.io.Serializable;

/**
 * Created by Даша on 16.12.2016.
 */
public class Equipment implements Serializable {
    private static final long serialVersionUID = 1L;

    //private Category category;
    private int idEquipment;
    private int idCategory;
    private String title;
    private double price;
    private int quantity;

    public Equipment() {
    }

   /* public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }*/

    public int getIdEquipment() {
        return idEquipment;
    }

    public void setIdEquipment(int idEquipment) {
        this.idEquipment = idEquipment;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj) {return true;}
        if (null==obj) {return false;}
        if (getClass()!=obj.getClass()) {return false;}

        Equipment equipment=(Equipment) obj;
        if(idEquipment != equipment.idEquipment) {return false;}
        if (idCategory != equipment.idCategory) {return false;}
        if (null==title) {return title==equipment.title;}
        else {
            if (!title.equals(equipment.title)){return false;}
        }
        if (Double.compare(price,equipment.price)!=0){return false;}
        if (quantity!= equipment.quantity) {return false;}
        return true;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idEquipment;
        result = 31 * result + idCategory;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + quantity;
        return result;
    }
}
