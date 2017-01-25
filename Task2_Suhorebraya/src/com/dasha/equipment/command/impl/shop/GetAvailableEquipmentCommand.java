package com.dasha.equipment.command.impl.shop;

import com.dasha.equipment.bean.Equipment;
import com.dasha.equipment.command.Command;
import com.dasha.equipment.service.ShopService;
import com.dasha.equipment.service.exception.ServiceException;
import com.dasha.equipment.service.factory.ServiceFactory;
import com.dasha.equipment.view.util.PrintCollectionUtil;

import java.util.List;

/**
 * Created by Даша on 13.01.2017.
 */
public class GetAvailableEquipmentCommand implements Command {
    @Override
    public String execute(String request) {
        String response;
        ServiceFactory serviceFactory=ServiceFactory.getInstance();
        ShopService shopService=serviceFactory.getShopService();
        try {
            List<Equipment> equipmentList=shopService.getAllEquipment();
            response="All Equipments were received";
            PrintCollectionUtil.printEquipments(equipmentList);
        } catch (ServiceException e) {
            response="Error during get procedure";
        }
        return response;
    }
}
