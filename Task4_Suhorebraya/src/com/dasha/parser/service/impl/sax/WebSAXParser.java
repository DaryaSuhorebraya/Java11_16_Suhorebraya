package com.dasha.parser.service.impl.sax;

import com.dasha.parser.entity.bean.WebApp;
import com.dasha.parser.service.WebParser;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

/**
 * Created by Даша on 23.01.2017.
 */
public class WebSAXParser implements WebParser{
    private WebSaxHandler webSaxHandler=new WebSaxHandler();
    @Override
    public WebApp parse(String filePath) {

        InputSource inputSource=new InputSource(filePath);
        try {
            XMLReader xmlReader= XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(webSaxHandler);
            xmlReader.parse(inputSource);
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
        return  webSaxHandler.getWebApp();
    }
}
