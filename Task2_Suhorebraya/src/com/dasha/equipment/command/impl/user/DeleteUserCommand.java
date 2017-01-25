package com.dasha.equipment.command.impl.user;

import com.dasha.equipment.command.Command;
import com.dasha.equipment.service.UserService;
import com.dasha.equipment.service.exception.ServiceException;
import com.dasha.equipment.service.factory.ServiceFactory;

/**
 * Created by Даша on 12.01.2017.
 */
public class DeleteUserCommand implements Command {
    @Override
    public String execute(String request) {
        String response;
        String[] parameters=request.split(" ");
        int id=Integer.parseInt(parameters[1]);

        ServiceFactory serviceFactory=ServiceFactory.getInstance();
        UserService userService=serviceFactory.getUserService();
        try {
            userService.deleteUser(id);
            response="User with id="+id+" was deleted";
        } catch (ServiceException e) {
            response="Error during delete procedure";
        }
        return response;
    }
}
