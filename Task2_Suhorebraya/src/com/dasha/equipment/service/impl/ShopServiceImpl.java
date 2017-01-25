package com.dasha.equipment.service.impl;
import com.dasha.equipment.bean.Category;
import com.dasha.equipment.bean.Equipment;
import com.dasha.equipment.bean.RentUnit;
import com.dasha.equipment.bean.User;
import com.dasha.equipment.dao.CategoryDAO;
import com.dasha.equipment.dao.EquipmentDAO;
import com.dasha.equipment.dao.RentUnitDAO;
import com.dasha.equipment.dao.UserDAO;
import com.dasha.equipment.dao.exception.DAOException;
import com.dasha.equipment.dao.factory.DAOFactory;
import com.dasha.equipment.service.ShopService;
import com.dasha.equipment.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Даша on 16.12.2016.
 */
public class ShopServiceImpl implements ShopService {

    @Override
    public void addEquipment(int idCategory, String title, double price, int quantity)
            throws ServiceException{
        if(idCategory == 0){
        throw new ServiceException("Incorrect id_category");
        }
        if(title == null || title.isEmpty()){
            throw new ServiceException("Incorrect title");
        }
        if(price == 0){
            throw new ServiceException("Incorrect price");
        }
        if(quantity == 0){
            throw new ServiceException("Incorrect quantity");
        }
        Equipment equipment=new Equipment();
        equipment.setIdCategory(idCategory);
        equipment.setTitle(title);
        equipment.setPrice(price);
        equipment.setQuantity(quantity);
        try{
            DAOFactory daoFactory = DAOFactory.getInstance();
            EquipmentDAO equipmentDAO=daoFactory.getEquipmentDAO();
            equipmentDAO.addEquipment(equipment);
        }catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteEquipment(int id)  throws ServiceException{
        if(id==0){
            throw new ServiceException("Incorrect id");
        }
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            EquipmentDAO equipmentDAO=daoFactory.getEquipmentDAO();
            equipmentDAO.deleteEquipment(id);
        }
        catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Equipment> getAllEquipment() throws ServiceException{
        List<Equipment> equipments;
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            EquipmentDAO equipmentDAO=daoFactory.getEquipmentDAO();
            equipments=equipmentDAO.getAllAvailableEquipment();
        }
        catch (DAOException e){
            throw new ServiceException(e);
        }
        return equipments;
    }

    @Override
    public List<Equipment> getEquipmentByCategoryId(int idCategory)
            throws ServiceException {
        if(idCategory == 0){
            throw new ServiceException("Incorrect id_category");
        }
        List<Equipment> equipments;
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            EquipmentDAO equipmentDAO=daoFactory.getEquipmentDAO();
            equipments=equipmentDAO.getEquipmentByCategoryId(idCategory);
        }
        catch (DAOException e){
            throw new ServiceException(e);
        }
        return equipments;
    }

    @Override
    public Equipment getEquipmentByTitle(String title)
            throws ServiceException {
        if(title == null || title.isEmpty()){
            throw new ServiceException("Incorrect title");
        }
        Equipment equipment;
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            EquipmentDAO equipmentDAO=daoFactory.getEquipmentDAO();
            equipment=equipmentDAO.getEquipmentByTitle(title);
        }
        catch (DAOException e){
            throw new ServiceException(e);
        }
        return equipment;
    }

    @Override
    public List<Category> getAllCategories() throws ServiceException {
        List<Category> categories;
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            CategoryDAO categoryDAO=daoFactory.getCategoryDAO();
            categories=categoryDAO.getAllCategories();
        }
        catch (DAOException e){
            throw new ServiceException(e);
        }
        return categories;
    }

    @Override
    public void addCategory(String title) throws ServiceException {
        if(title == null || title.isEmpty()){
            throw new ServiceException("Incorrect title");
        }
        Category category=new Category();
        category.setTitle(title);
        try{
            DAOFactory daoFactory = DAOFactory.getInstance();
            CategoryDAO categoryDAO=daoFactory.getCategoryDAO();
            categoryDAO.addCategory(category);
        }catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteCategory(int id) throws ServiceException {
        if(id==0){
            throw new ServiceException("Incorrect id");
        }
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            CategoryDAO categoryDAO=daoFactory.getCategoryDAO();
            categoryDAO.deleteCategory(id);
        }
        catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<RentUnit> getAllRentedUnits() throws ServiceException {
        List<RentUnit> rentUnits;
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            RentUnitDAO rentUnitDAO=daoFactory.getRentUnitDAO();
            rentUnits=rentUnitDAO.getAllRentedUnits();
        }
        catch (DAOException e){
            throw new ServiceException(e);
        }
        return rentUnits;
    }
}
