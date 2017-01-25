package com.dasha.equipment.dao;

import com.dasha.equipment.bean.RentUnit;
import com.dasha.equipment.dao.exception.DAOException;

import java.util.List;

/**
 * Created by Даша on 13.01.2017.
 */
public interface RentUnitDAO {
    void rentUnit(RentUnit rentUnit) throws DAOException;
    void confirmReturn(int id) throws DAOException;
    List<RentUnit> getAllRentedUnits() throws DAOException;
    boolean checkAbilityToRent(int idUser) throws DAOException;
}
