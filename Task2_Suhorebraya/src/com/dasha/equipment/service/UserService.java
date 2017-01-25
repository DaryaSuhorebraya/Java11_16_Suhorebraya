package com.dasha.equipment.service;


import com.dasha.equipment.service.exception.ServiceException;

import java.util.Date;

/**
 * Created by Даша on 16.12.2016.
 */
public interface UserService {
    void addUser(String surname, String name,String phone, int discount) throws ServiceException;
    void deleteUser(int id) throws ServiceException;
    void rentUnit(int idUser, int idEquipment, String dateFrom,String dateTo,double totalPrice,String status) throws ServiceException;
    void confirmReturn(int id) throws ServiceException;
}
