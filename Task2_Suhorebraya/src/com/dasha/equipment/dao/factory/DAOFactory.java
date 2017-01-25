package com.dasha.equipment.dao.factory;

import com.dasha.equipment.dao.CategoryDAO;
import com.dasha.equipment.dao.EquipmentDAO;
import com.dasha.equipment.dao.RentUnitDAO;
import com.dasha.equipment.dao.UserDAO;
import com.dasha.equipment.dao.impl.SQLCategoryDAO;
import com.dasha.equipment.dao.impl.SQLEquipmentDAO;
import com.dasha.equipment.dao.impl.SQLRentUnitDAO;
import com.dasha.equipment.dao.impl.SQLUserDAO;

/**
 * Created by Даша on 16.12.2016.
 */
public class DAOFactory {
    private static final DAOFactory instance=new DAOFactory();
    private EquipmentDAO equipmentDAO=new SQLEquipmentDAO();
    private UserDAO userDAO=new SQLUserDAO();
    private CategoryDAO categoryDAO=new SQLCategoryDAO();
    private RentUnitDAO rentUnitDAO=new SQLRentUnitDAO();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public EquipmentDAO getEquipmentDAO() {
        return equipmentDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public CategoryDAO getCategoryDAO() {
        return categoryDAO;
    }

    public RentUnitDAO getRentUnitDAO() {
        return rentUnitDAO;
    }
}
