package com.dasha.equipment.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Даша on 13.01.2017.
 */
public class RentUnit implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idRentUnit;
    private int idUser;
    private int idEquipment;
    private Date dateFrom;
    private Date dateTo;
    private double totalPrice;
    private String status;


    public int getIdRentUnit() {
        return idRentUnit;
    }

    public void setIdRentUnit(int idRentUnit) {
        this.idRentUnit = idRentUnit;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdEquipment() {
        return idEquipment;
    }

    public void setIdEquipment(int idEquipment) {
        this.idEquipment = idEquipment;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj) {return true;}
        if (null==obj) {return false;}
        if (getClass()!=obj.getClass()) {return false;}

        RentUnit rentUnit=(RentUnit) obj;
        if (idRentUnit!=rentUnit.idRentUnit) {return false;}
        if (idUser!= rentUnit.idUser) {return false;}
        if (idEquipment!=rentUnit.idEquipment) {return false;}
        if (null==dateFrom) {return dateFrom==rentUnit.dateFrom;}
        else {
            if (!dateFrom.equals(rentUnit.dateFrom)) {return false;}
        }
        if (null==dateTo) {return dateTo==rentUnit.dateTo;}
        else {
            if (!dateTo.equals(rentUnit.dateTo)) {return false;}
        }
        if (Double.compare(totalPrice,rentUnit.totalPrice)!=0) {return false;}
        if (null==status) {return status==rentUnit.status;}
        else {
            if (!status.equals(rentUnit.status)) {return false;}
        }
        return true;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idRentUnit;
        result = 31 * result + idUser;
        result = 31 * result + idEquipment;
        result = 31 * result + (dateFrom != null ? dateFrom.hashCode() : 0);
        result = 31 * result + (dateTo != null ? dateTo.hashCode() : 0);
        temp = Double.doubleToLongBits(totalPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
