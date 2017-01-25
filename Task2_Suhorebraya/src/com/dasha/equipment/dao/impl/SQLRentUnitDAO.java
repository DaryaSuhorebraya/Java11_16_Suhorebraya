package com.dasha.equipment.dao.impl;

import com.dasha.equipment.bean.Equipment;
import com.dasha.equipment.bean.RentUnit;
import com.dasha.equipment.dao.RentUnitDAO;
import com.dasha.equipment.dao.dbconnection.DBConnection;
import com.dasha.equipment.dao.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Даша on 13.01.2017.
 */
public class SQLRentUnitDAO implements RentUnitDAO {
    private static final String SQL_RENT_UNIT = "INSERT INTO rent_unit(id_user,id_equipment,date_from,date_to,total_price,status ) VALUES(?, ?, ?, ?,?,?)";
    private static final String SQL_CONFIRM_RETURN = "UPDATE rent_unit SET status='returned' WHERE id_rent_unit=?";
    private static final String SQL_GET_ALL_RENT_UNITS = "SELECT * FROM rent_unit WHERE status='rented'";
    private static final String SQL_GET_COUNT_OF_ORDERS_OF_USER = "SELECT count(rent_unit.status='rented') FROM rent_unit WHERE id_user=?";
    private static final String SQL_UPDATE_COUNT_CASE_RENT= "UPDATE equipment INNER JOIN rent_unit ON equipment.id_equipment=rent_unit.id_equipment"+
            " SET equipment.quantity=quantity-1 WHERE MAX(rent_unit.id_rent_unit)";
    private static final String SQL_UPDATE_COUNT_CASE_CONFIRM= "UPDATE equipment INNER JOIN rent_unit ON equipment.id_equipment=rent_unit.id_equipment"+
            " SET equipment.quantity=quantity+1 WHERE rent_unit.id_rent_unit=?";

    @Override
    public void rentUnit(RentUnit rentUnit) throws DAOException {
        Connection connection= DBConnection.getConnection();
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=connection.prepareStatement(SQL_RENT_UNIT);
            preparedStatement.setInt(1,rentUnit.getIdUser());
            preparedStatement.setInt(2,rentUnit.getIdEquipment());
            preparedStatement.setDate(3,new Date(rentUnit.getDateFrom().getTime()));
            preparedStatement.setDate(4,new Date(rentUnit.getDateTo().getTime()));
            preparedStatement.setDouble(5,rentUnit.getTotalPrice());
            preparedStatement.setString(6,rentUnit.getStatus());
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
        updateCountEquipmentCaseRent();
    }

    @Override
    public void confirmReturn(int id) throws DAOException {
        Connection connection= DBConnection.getConnection();
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=connection.prepareStatement(SQL_CONFIRM_RETURN);
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
        updateCountEquipmentCaseConfirm(id);
    }

    @Override
    public List<RentUnit> getAllRentedUnits() throws DAOException {
        Connection connection=DBConnection.getConnection();
        Statement statement=null;
        try {
            statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(SQL_GET_ALL_RENT_UNITS);
            List<RentUnit> rentUnits=new ArrayList<>();
            while (resultSet.next()){
                RentUnit rentUnit=new RentUnit();
                rentUnit.setIdRentUnit(resultSet.getInt(1));
                rentUnit.setIdUser(resultSet.getInt(2));
                rentUnit.setIdEquipment(resultSet.getInt(3));
                rentUnit.setDateFrom(resultSet.getDate(4));
                rentUnit.setDateTo(resultSet.getDate(5));
                rentUnit.setTotalPrice(resultSet.getDouble(6));
                rentUnit.setStatus(resultSet.getString(7));
                rentUnits.add(rentUnit);
            }
            return rentUnits;
        } catch (SQLException e) {
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
    public boolean checkAbilityToRent(int idUser) throws DAOException {
        Connection connection= DBConnection.getConnection();
        PreparedStatement preparedStatement=null;
        int countOrders=0;
        try {
            preparedStatement=connection.prepareStatement(SQL_GET_COUNT_OF_ORDERS_OF_USER);
            preparedStatement.setInt(1,idUser);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                countOrders=resultSet.getInt(1);
            }
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
        return countOrders<=3;
    }

    private void updateCountEquipmentCaseRent(){
        Connection connection= DBConnection.getConnection();
        Statement statement=null;
        try {
            statement=connection.createStatement();
            statement.executeUpdate(SQL_UPDATE_COUNT_CASE_RENT);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void updateCountEquipmentCaseConfirm(int idEguipment){
        Connection connection= DBConnection.getConnection();
        PreparedStatement preparedStatement=null;
        try {
             preparedStatement=connection.prepareStatement(SQL_UPDATE_COUNT_CASE_CONFIRM);
             preparedStatement.setInt(1,idEguipment);
             preparedStatement.executeUpdate();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                if(preparedStatement!=null){
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (connection!=null){
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


}

