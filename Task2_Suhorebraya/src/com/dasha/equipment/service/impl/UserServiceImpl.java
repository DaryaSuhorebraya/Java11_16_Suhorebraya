package com.dasha.equipment.service.impl;

import com.dasha.equipment.bean.RentUnit;
import com.dasha.equipment.bean.User;
import com.dasha.equipment.dao.RentUnitDAO;
import com.dasha.equipment.dao.UserDAO;
import com.dasha.equipment.dao.exception.DAOException;
import com.dasha.equipment.dao.factory.DAOFactory;
import com.dasha.equipment.service.UserService;
import com.dasha.equipment.service.exception.ServiceException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Даша on 16.12.2016.
 */
public class UserServiceImpl implements UserService {
    @Override
    public void addUser(String surname, String name,String phone, int discount)
            throws ServiceException {
        if(surname == null || surname.isEmpty()){
            throw new ServiceException("Incorrect surname");
        }
        if(name == null || name.isEmpty()){
            throw new ServiceException("Incorrect name");
        }
        if(phone == null || phone.isEmpty()){
            throw new ServiceException("Incorrect phone");
        }
        User user=new User();
        user.setSurname(surname);
        user.setName(name);
        user.setPhoneNumber(phone);
        user.setDiscount(discount);
        try{
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            userDAO.addUser(user);
        }catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteUser(int id) throws ServiceException{
        if(id==0){
            throw new ServiceException("Incorrect id");
        }
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            UserDAO userDAO=daoFactory.getUserDAO();
            userDAO.deleteUser(id);
        }
        catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void rentUnit(int idUser, int idEquipment, String dateFromStr, String dateToStr, double totalPrice, String status)
            throws ServiceException {
        Date dateFrom=null;
        Date dateTo=null;
        if(idUser==0){
            throw new ServiceException("Incorrect id_user");
        }
        if(idEquipment==0){
            throw new ServiceException("Incorrect id_equipment");
        }
        if(dateFromStr == null|| dateFromStr.isEmpty()){
            throw new ServiceException("Incorrect date_from");
        }
        if(dateToStr == null|| dateToStr.isEmpty()){
            throw new ServiceException("Incorrect date_to");
        }
        if(totalPrice==0){
            throw new ServiceException("Incorrect total_price");
        }
        if(status == null || status.isEmpty()){
            throw new ServiceException("Incorrect status");
        }
        //String s="05.09.2013";
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        try {
            dateFrom=format.parse(dateFromStr);
            dateTo=format.parse(dateToStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (canRentUnit(idUser)) {
            RentUnit rentUnit = new RentUnit();
            rentUnit.setIdUser(idUser);
            rentUnit.setIdEquipment(idEquipment);
            rentUnit.setDateFrom(dateFrom);
            rentUnit.setDateTo(dateTo);
            rentUnit.setTotalPrice(totalPrice);
            rentUnit.setStatus(status);
            try {
                DAOFactory daoFactory = DAOFactory.getInstance();
                RentUnitDAO rentUnitDAO = daoFactory.getRentUnitDAO();
                rentUnitDAO.rentUnit(rentUnit);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
    }

    @Override
    public void confirmReturn(int id) throws ServiceException {
        if(id==0){
            throw new ServiceException("Incorrect id_rent_unit");
        }
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            RentUnitDAO rentUnitDAO=daoFactory.getRentUnitDAO();
            rentUnitDAO.confirmReturn(id);
        }
        catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    // по логике задания пользователь может взять в прокат не более 3-х товаров
    private boolean canRentUnit(int idUser) throws ServiceException{
        boolean abilityToRent;
        try {
            DAOFactory daoFactory=DAOFactory.getInstance();
            RentUnitDAO rentUnitDAO=daoFactory.getRentUnitDAO();
            abilityToRent=rentUnitDAO.checkAbilityToRent(idUser);
        }
        catch (DAOException e){
            throw new ServiceException("User can't rent more than 3 units",e);
        }
        return abilityToRent;
    }
}
