package com.dasha.equipment.bean;

import java.io.Serializable;

/**
 * Created by Даша on 16.12.2016.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idUser;
    private String surname;
    private String name;
    private String phoneNumber;
    private int discount;

    public User() {
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj) {return true;}
        if (null==obj) {return false;}
        if (getClass()!=obj.getClass()) {return false;}

        User user=(User) obj;
        if (idUser!=user.idUser) {return false;}
        if (null==surname) {return surname==user.surname;}
        else {
            if (!surname.equals(user.surname)) {return false;}
        }
        if (null==name) {return name==user.name;}
        else {
            if (!name.equals(user.name)) {return false;}
        }
        if (null==phoneNumber) {return phoneNumber==user.phoneNumber;}
        else {
            if (!phoneNumber.equals(user.phoneNumber)) {return false;}
        }
        if (discount!=user.discount) {return false;}
        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + discount;
        return result;
    }
}
