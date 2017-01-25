package com.dasha.equipment.controller;

import com.dasha.equipment.command.Command;
import com.dasha.equipment.command.CommandName;
import com.dasha.equipment.command.impl.shop.*;
import com.dasha.equipment.command.impl.user.AddUserCommand;
import com.dasha.equipment.command.impl.user.ConfirmReturnCommand;
import com.dasha.equipment.command.impl.user.DeleteUserCommand;
import com.dasha.equipment.command.impl.user.RentUnitCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Даша on 12.01.2017.
 */
 final class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider(){
        repository.put(CommandName.ADD_USER,
                new AddUserCommand());
        repository.put(CommandName.DELETE_USER,
                new DeleteUserCommand());
        repository.put(CommandName.RENT_UNIT,
                new RentUnitCommand());
        repository.put(CommandName.CONFIRM_RETURN,
                new ConfirmReturnCommand());
        repository.put(CommandName.GET_RENTED_UNITS,
                new GetAllRentedUnits());
        repository.put(CommandName.WRONG_REQUEST,
                new WrongRequest());
        repository.put(CommandName.ADD_EQUIPMENT,
                new AddEquipmentCommand());
        repository.put(CommandName.DELETE_EQUIPMENT,
                new DeleteEquipmentCommand());
        repository.put(CommandName.GET_AVAILABLE_EQUIPMENT,
                new GetAvailableEquipmentCommand());
        repository.put(CommandName.GET_EQUIPMENT_BY_CATEGORY_ID,
                new GetEquipmentByCategoryCommand());
        repository.put(CommandName.GET_EQUIPMENT_BY_TITLE,
                new GetEquipmentByTitleCommand());
        repository.put(CommandName.ADD_CATEGORY,
                new AddCategoryCommand());
        repository.put(CommandName.DELETE_CATEGORY,
                new DeleteCategoryCommand());
        repository.put(CommandName.GET_ALL_CATEGORIES,
                new GetAllCategoriesCommand());
    }

    Command getCommand(String name){
        CommandName commandName;
        Command command;

        try{
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        }catch(IllegalArgumentException | NullPointerException e){
            command = repository.get(CommandName.WRONG_REQUEST);
        }

        return command;
    }

}
