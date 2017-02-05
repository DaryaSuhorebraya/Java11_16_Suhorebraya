package com.dasha.parser;


import com.dasha.parser.entity.bean.WebApp;
import com.dasha.parser.service.WebParser;
import com.dasha.parser.service.exception.ServiceException;
import com.dasha.parser.service.factory.WebParserFactory;

public class Main {

    public static void main(String[] args) {
        String filePath="src/com/dasha/parser/resource/web.xml";
        try {
            WebParserFactory webParserFactory=WebParserFactory.getInstance();
            WebParser webParser = webParserFactory.createWebParser("sta");
            WebApp webApp = webParser.parse(filePath);
            webApp.printElement();

        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
