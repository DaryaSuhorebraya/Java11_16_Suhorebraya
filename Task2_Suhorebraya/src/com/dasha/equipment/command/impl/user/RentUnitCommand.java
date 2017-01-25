package com.dasha.equipment.command.impl.user;

import com.dasha.equipment.command.Command;
import com.dasha.equipment.service.UserService;
import com.dasha.equipment.service.exception.ServiceException;
import com.dasha.equipment.service.factory.ServiceFactory;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Даша on 13.01.2017.
 */
public class RentUnitCommand implements Command{
    @Override
    public String execute(String request) {
        String response;
        String[] parameters=request.split(" ");
        int idUser=Integer.parseInt(parameters[1]);
        int idEquipment=Integer.parseInt(parameters[2]);
        String dateFrom=parameters[3];
        String dateTo=parameters[4];
        double totalPrice=Double.parseDouble(parameters[5]);
        String status=parameters[6];

        ServiceFactory serviceFactory=ServiceFactory.getInstance();
        UserService userService=serviceFactory.getUserService();
        try {
            userService.rentUnit(idUser,idEquipment,dateFrom,dateTo,totalPrice,status);
            response="Unit was rented";
        }
        catch (ServiceException e){
            response=" Error during renting unit";
        }
        return response;
    }
}
