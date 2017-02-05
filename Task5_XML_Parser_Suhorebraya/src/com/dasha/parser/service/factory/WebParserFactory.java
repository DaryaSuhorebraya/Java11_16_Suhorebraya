package com.dasha.parser.service.factory;

import com.dasha.parser.service.WebParser;
import com.dasha.parser.service.exception.ServiceException;
import com.dasha.parser.service.impl.dom.*;
import com.dasha.parser.service.impl.stax.*;
import com.dasha.parser.service.impl.sax.*;


/**
 * Created by Даша on 24.01.2017.
 */
public class WebParserFactory {
    private static final WebParserFactory instance=new WebParserFactory();

    public static WebParserFactory getInstance() {
        return instance;
    }

    public WebParser createWebParser(String parserType) throws ServiceException{
        String type=parserType.toUpperCase();
        switch (type){
            case "SAX": {
                return new WebSAXParser();
            }
            case "STAX": {
                return new WebStAXParser();
            }
            case "DOM": {
                return new WebDOMParser();
            }
            default: {
                throw new ServiceException("Not existed parser type");
            }
        }
    }
}
