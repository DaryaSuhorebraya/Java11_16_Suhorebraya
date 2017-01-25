package com.dasha.equipment.command.impl.user;


import com.dasha.equipment.command.Command;
import com.dasha.equipment.service.UserService;
import com.dasha.equipment.service.exception.ServiceException;
import com.dasha.equipment.service.factory.ServiceFactory;

/**
 * Created by Даша on 12.01.2017.
 */
public class AddUserCommand implements Command {
    @Override
    public String execute(String request) {
        String response;
        String[] parameters=request.split(" ");
        String surname=parameters[1];
        String name=parameters[2];
        String phone=parameters[3];
        int discount=Integer.parseInt(parameters[4]);

        ServiceFactory serviceFactory=ServiceFactory.getInstance();
        UserService userService=serviceFactory.getUserService();
        try {
            userService.addUser(surname,name,phone,discount);
            response="New user was added";
        }
        catch (ServiceException e){
            response=" Error during adding new user procedure";
        }
        return response;
    }
}
