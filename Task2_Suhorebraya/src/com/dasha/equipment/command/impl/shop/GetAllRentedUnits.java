package com.dasha.equipment.command.impl.shop;

import com.dasha.equipment.bean.RentUnit;
import com.dasha.equipment.command.Command;
import com.dasha.equipment.service.ShopService;
import com.dasha.equipment.service.exception.ServiceException;
import com.dasha.equipment.service.factory.ServiceFactory;
import com.dasha.equipment.view.util.PrintCollectionUtil;

import java.util.List;

/**
 * Created by Даша on 14.01.2017.
 */
public class GetAllRentedUnits implements Command {
    @Override
    public String execute(String request) {
        String response;
        ServiceFactory serviceFactory=ServiceFactory.getInstance();
        ShopService shopService=serviceFactory.getShopService();
        try {
            List<RentUnit> rentUnitList=shopService.getAllRentedUnits();
            response="All rented units were received";
            PrintCollectionUtil.printRentedUnits(rentUnitList);
        } catch (ServiceException e) {
            response="Error during get procedure";
        }
        return response;
    }
}
