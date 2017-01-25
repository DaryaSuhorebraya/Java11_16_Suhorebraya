package com.dasha.equipment.dao;

import com.dasha.equipment.bean.Equipment;
import com.dasha.equipment.dao.exception.DAOException;

import java.util.List;

/**
 * Created by Даша on 16.12.2016.
 */
public interface EquipmentDAO {
    void addEquipment(Equipment equipment) throws DAOException;
    void deleteEquipment(int id) throws DAOException;
    List<Equipment> getAllAvailableEquipment() throws DAOException;
    List<Equipment> getEquipmentByCategoryId(int id) throws DAOException;
    Equipment getEquipmentByTitle(String title) throws DAOException;
}
