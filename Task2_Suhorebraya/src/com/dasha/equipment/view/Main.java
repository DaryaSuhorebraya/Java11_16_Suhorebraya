package com.dasha.equipment.view;

import com.dasha.equipment.controller.Controller;
import com.dasha.equipment.view.util.ConsoleInputUtil;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Controller controller=new Controller();
        String response;
        response=controller.executeTask("add_category Boxing");
        System.out.println(response);
        response=controller.executeTask("delete_category 4");
        System.out.println(response);
        response=controller.executeTask("add_equipment 3 Surfboard 45 5");
        System.out.println(response);
        response=controller.executeTask("rent_unit 3 1 05.01.2017 14.01.2017 45 rented");
        System.out.println(response);
        response=controller.executeTask("confirm_return 6");
        System.out.println(response);
        response=controller.executeTask("get_rented_units ");
        System.out.println(response);
        response=controller.executeTask("get_available_equipment ");
        System.out.println(response);


        //Для ввода с консоли можно использовать следующий метод
        // ConsoleInputUtil.run();
    }
}
