package com.dasha.equipment.view.util;

import com.dasha.equipment.controller.Controller;

import java.util.Scanner;

/**
 * Created by Даша on 16.01.2017.
 */
public class ConsoleInputUtil {
    private static Scanner scanner=new Scanner(System.in);

    public static void run() {
        Controller controller=new Controller();
        String request;
        System.out.println("Введите команду либо для выходы введите 'exit'");
        while (!(request = scanner.nextLine()).equals("exit")) {
            String response = controller.executeTask(request);
            System.out.println(response);
        }
    }
}
