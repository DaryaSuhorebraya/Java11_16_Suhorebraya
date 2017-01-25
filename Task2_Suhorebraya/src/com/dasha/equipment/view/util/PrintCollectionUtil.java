package com.dasha.equipment.view.util;

import com.dasha.equipment.bean.Category;
import com.dasha.equipment.bean.Equipment;
import com.dasha.equipment.bean.RentUnit;

import java.util.List;

/**
 * Created by Даша on 14.01.2017.
 */
public class PrintCollectionUtil {
    public static void printCategories(List<Category> categoryList){
        System.out.println("List of all categories:");
        for (Category category:categoryList) {
            System.out.println("Title: "+category.getTitle());
        }
    }
    public static void printRentedUnits(List<RentUnit> rentUnitList){
        System.out.println("List of rented units:");
        for (RentUnit rentUnit: rentUnitList){
            System.out.println("id_user= "+rentUnit.getIdUser()+" id_equipment= "
                    +rentUnit.getIdUser()+ " dateFrom: " + rentUnit.getDateFrom()+
                    " dateTo: "+rentUnit.getDateTo()+" totalPrice= "+
                    rentUnit.getTotalPrice()+" status: "+rentUnit.getStatus());
        }
    }
    public static void printEquipments(List<Equipment> equipmentList){
        System.out.println("List of required equipment:");
        for (Equipment equipment: equipmentList){
            System.out.println("id_category= "+equipment.getIdCategory()+" title= "
                    +equipment.getTitle()+ " price= " + equipment.getPrice()+
                    " quantity: "+equipment.getQuantity());
        }
    }
}
