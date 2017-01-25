package com.dasha.equipment.dao.impl;

import com.dasha.equipment.bean.Equipment;
import com.dasha.equipment.dao.EquipmentDAO;
import com.dasha.equipment.dao.dbconnection.DBConnection;
import com.dasha.equipment.dao.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Даша on 16.12.2016.
 */
public class SQLEquipmentDAO implements EquipmentDAO {
    private static final String SQL_ADD_EQUIPMENT = "INSERT INTO equipment (id_category,title,price,quantity ) VALUES(?, ?, ?, ?)";
    private static final String SQL_DELETE_EQUIPMENT="DELETE FROM equipment where id_equipment=?";
    private static final String SQL_GET_ALL_EQUIPMENT="SELECT * FROM equipment";
    private static final String SQL_GET_BY_CATEGORY_ID="SELECT * FROM equipment WHERE id_category=?";
    private static final String SQL_GET_BY_TITLE="SELECT * FROM equipment WHERE title=?";

    @Override
    public void addEquipment(Equipment equipment) throws DAOException{
        Connection connection= DBConnection.getConnection();
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=connection.prepareStatement(SQL_ADD_EQUIPMENT);
            preparedStatement.setInt(1,equipment.getIdCategory());
            preparedStatement.setString(2,equipment.getTitle());
            preparedStatement.setDouble(3,equipment.getPrice());
            preparedStatement.setInt(4,equipment.getQuantity());
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
    public void deleteEquipment(int id) throws DAOException{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            connection= DBConnection.getConnection();
            preparedStatement=connection.prepareStatement(SQL_DELETE_EQUIPMENT);
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

    @Override
    public List<Equipment> getAllAvailableEquipment() throws DAOException {
        Connection connection=null;
        Statement statement=null;
        try {
            connection=DBConnection.getConnection();
            statement=connection.createStatement();

            ResultSet resultSet=statement.executeQuery(SQL_GET_ALL_EQUIPMENT);
            List<Equipment> equipments=initializeEquipmentFromResultSet(resultSet);
            /*while (resultSet.next()){
                Equipment equipment=new Equipment();
                equipment.setIdEquipment(resultSet.getInt(1));
                equipment.setIdCategory(resultSet.getInt(2));
                equipment.setTitle(resultSet.getString(3));
                equipment.setPrice(resultSet.getDouble(4));
                equipment.setQuantity(resultSet.getInt(5));

                equipments.add(equipment);
            }*/
            return equipments;
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

    }

    @Override
    public List<Equipment> getEquipmentByCategoryId(int id) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            connection= DBConnection.getConnection();
            preparedStatement=connection.prepareStatement(SQL_GET_BY_CATEGORY_ID);
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            List<Equipment> equipments=initializeEquipmentFromResultSet(resultSet);
            /*while (resultSet.next()){
                Equipment equipment=new Equipment();
                equipment.setIdEquipment(resultSet.getInt(1));
                equipment.setIdCategory(resultSet.getInt(2));
                equipment.setTitle(resultSet.getString(3));
                equipment.setPrice(resultSet.getDouble(4));
                equipment.setQuantity(resultSet.getInt(5));

                equipments.add(equipment);
            }*/
            return equipments;
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
    public Equipment getEquipmentByTitle(String title) throws DAOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            connection= DBConnection.getConnection();
            preparedStatement=connection.prepareStatement(SQL_GET_BY_TITLE);
            preparedStatement.setString(1,title);
            ResultSet resultSet=preparedStatement.executeQuery();
            Equipment equipment=null;
            while (resultSet.next()){
                equipment=new Equipment();
                equipment.setIdEquipment(resultSet.getInt(1));
                equipment.setIdCategory(resultSet.getInt(2));
                equipment.setTitle(resultSet.getString(3));
                equipment.setPrice(resultSet.getDouble(4));
                equipment.setQuantity(resultSet.getInt(5));
            }
            return equipment;
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
    private List<Equipment> initializeEquipmentFromResultSet(ResultSet resultSet)
            throws SQLException {
        List<Equipment> equipments = new ArrayList<>();
        while (resultSet.next()) {
            Equipment equipment = new Equipment();
            equipment.setIdEquipment(resultSet.getInt(1));
            equipment.setIdCategory(resultSet.getInt(2));
            equipment.setTitle(resultSet.getString(3));
            equipment.setPrice(resultSet.getDouble(4));
            equipment.setQuantity(resultSet.getInt(5));
            equipments.add(equipment);
        }
        return equipments;
    }
}
