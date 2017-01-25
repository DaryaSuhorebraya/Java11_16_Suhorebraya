package com.dasha.equipment.dao.impl;

import com.dasha.equipment.bean.Category;
import com.dasha.equipment.bean.Equipment;
import com.dasha.equipment.dao.CategoryDAO;
import com.dasha.equipment.dao.dbconnection.DBConnection;
import com.dasha.equipment.dao.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Даша on 12.01.2017.
 */
public class SQLCategoryDAO implements CategoryDAO {
    private static final String SQL_ADD_CATEGORY = "INSERT INTO category (title) VALUES(?)";
    private static final String SQL_DELETE_CATEGORY="DELETE FROM category where id_category=?";
    private static final String SQL_GET_ALL_CATEGORY="SELECT * FROM category";
    @Override
    public List<Category> getAllCategories() throws DAOException {
        Connection connection=null;
        Statement statement=null;
        try {
            connection=DBConnection.getConnection();
            statement=connection.createStatement();

            ResultSet resultSet=statement.executeQuery(SQL_GET_ALL_CATEGORY);
            List<Category> categories=new ArrayList<>();
            while (resultSet.next()){
                Category category=new Category();
                category.setIdCategory(resultSet.getInt(1));
                category.setTitle(resultSet.getString(2));
                categories.add(category);
            }
        }
        catch (SQLException e){
            throw new DAOException(e);
        }
        finally {
            if(statement!=null){
                try {
                    statement.close();
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
        return null;
    }

    @Override
    public void addCategory(Category category) throws DAOException {
        Connection connection= DBConnection.getConnection();
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=connection.prepareStatement(SQL_ADD_CATEGORY);
            preparedStatement.setString(1,category.getTitle());
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
    public void deleteCategory(int id) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            connection= DBConnection.getConnection();
            preparedStatement=connection.prepareStatement(SQL_DELETE_CATEGORY);
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
