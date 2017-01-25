package com.dasha.equipment.command.impl.shop;

import com.dasha.equipment.command.Command;

/**
 * Created by Даша on 12.01.2017.
 */
public class WrongRequest implements Command {
    @Override
    public String execute(String request) {
        String response="Wrong request";
        return response;
    }
}
