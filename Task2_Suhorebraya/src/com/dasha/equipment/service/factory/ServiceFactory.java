package com.dasha.equipment.service.factory;

import com.dasha.equipment.service.impl.ShopServiceImpl;
import com.dasha.equipment.service.impl.UserServiceImpl;
import com.dasha.equipment.service.ShopService;
import com.dasha.equipment.service.UserService;

/**
 * Created by Даша on 16.12.2016.
 */
public class ServiceFactory {
    private static final ServiceFactory instance=new ServiceFactory();

    private UserService userService=new UserServiceImpl();
    private ShopService shopService=new ShopServiceImpl();
    private ServiceFactory() {
    }
    public static ServiceFactory getInstance(){
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public ShopService getShopService() {
        return shopService;
    }
}
