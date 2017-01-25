package com.dasha.equipment.dao.impl;

import com.dasha.equipment.bean.User;
import com.dasha.equipment.dao.UserDAO;
import com.dasha.equipment.dao.dbconnection.DBConnection;
import com.dasha.equipment.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Даша on 16.12.2016.
 */
public class SQLUserDAO implements UserDAO {
    private static final String SQL_ADD_USER = "INSERT INTO user (surname,name,phone_number,discount ) VALUES(?, ?, ?, ?)";
    private static final String SQL_DELETE_USER="DELETE FROM user where id_user=?";
    @Override
    public void addUser(User user) throws DAOException {
        Connection connection=DBConnection.getConnection();
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=connection.prepareStatement(SQL_ADD_USER);
            preparedStatement.setString(1,user.getSurname());
            preparedStatement.setString(2,user.getName());
            preparedStatement.setString(3,user.getPhoneNumber());
            preparedStatement.setInt(4,user.getDiscount());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        finally {
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
            }
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
            }
        }
    }

    @Override
    public void deleteUser(int id) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            connection= DBConnection.getConnection();
            preparedStatement=connection.prepareStatement(SQL_DELETE_USER);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        finally {
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
            }
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
            }
        }
    }
}
