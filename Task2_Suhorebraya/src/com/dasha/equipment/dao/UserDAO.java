package com.dasha.equipment.dao;

import com.dasha.equipment.bean.User;
import com.dasha.equipment.dao.exception.DAOException;

/**
 * Created by Даша on 16.12.2016.
 */
public interface UserDAO {
    void addUser(User user) throws DAOException;
    void deleteUser(int id) throws DAOException;

}
