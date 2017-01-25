package com.dasha.equipment.command.impl.shop;

import com.dasha.equipment.command.Command;
import com.dasha.equipment.service.ShopService;
import com.dasha.equipment.service.exception.ServiceException;
import com.dasha.equipment.service.factory.ServiceFactory;

/**
 * Created by Даша on 13.01.2017.
 */
public class GetAllCategoriesCommand implements Command {
    @Override
    public String execute(String request) {
        String response;
        ServiceFactory serviceFactory=ServiceFactory.getInstance();
        ShopService shopService=serviceFactory.getShopService();
        try {
            shopService.getAllCategories();
            response="All Equipments was received";
        } catch (ServiceException e) {
            response="Error during get procedure";
        }
        return response;
    }
}
