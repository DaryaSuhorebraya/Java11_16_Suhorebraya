package com.dasha.equipment.service;

import com.dasha.equipment.bean.Category;
import com.dasha.equipment.bean.Equipment;
import com.dasha.equipment.bean.RentUnit;
import com.dasha.equipment.bean.User;
import com.dasha.equipment.dao.exception.DAOException;
import com.dasha.equipment.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Даша on 16.12.2016.
 */
public interface ShopService {
    void addEquipment(int idCategory,String title, double price,int qauantity) throws ServiceException;
    void deleteEquipment(int id) throws ServiceException;
    List<Equipment> getAllEquipment() throws ServiceException;
    List<Equipment> getEquipmentByCategoryId(int idCategory) throws ServiceException;
    Equipment getEquipmentByTitle(String title) throws ServiceException;
    List<Category> getAllCategories() throws ServiceException;
    void addCategory(String title) throws ServiceException;
    void deleteCategory(int id) throws ServiceException;
    List<RentUnit> getAllRentedUnits() throws ServiceException;
}
