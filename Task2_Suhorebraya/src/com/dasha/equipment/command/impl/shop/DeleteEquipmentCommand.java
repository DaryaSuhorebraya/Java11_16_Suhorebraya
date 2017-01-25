package com.dasha.equipment.command.impl.shop;

import com.dasha.equipment.command.Command;
import com.dasha.equipment.service.ShopService;
import com.dasha.equipment.service.exception.ServiceException;
import com.dasha.equipment.service.factory.ServiceFactory;

/**
 * Created by Даша on 13.01.2017.
 */
public class DeleteEquipmentCommand implements Command {
    @Override
    public String execute(String request) {
        String response;
        String[] parameters=request.split(" ");
        int id=Integer.parseInt(parameters[1]);

        ServiceFactory serviceFactory=ServiceFactory.getInstance();
        ShopService shopService=serviceFactory.getShopService();
        try {
            shopService.deleteEquipment(id);
            response="Equipment with id="+id+" was deleted";
        } catch (ServiceException e) {
            response="Error during delete procedure";
        }
        return response;
    }
}
