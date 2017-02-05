package com.dasha.parser.service.impl.stax;

import com.dasha.parser.entity.bean.*;
import com.dasha.parser.entity.element.WebTagName;
import com.dasha.parser.service.WebParser;
import com.dasha.parser.service.exception.ServiceException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Даша on 24.01.2017.
 */
public class WebStAXParser implements WebParser{
    private static XMLInputFactory xmlInputFactory=XMLInputFactory.newInstance();
    private static XMLStreamReader reader;
    private WebTagName elementName=null;
    private WebApp webApp=null;
    private List<String> welcFiles=new ArrayList<>();
    private List<InitParam> initParamList=new ArrayList<>();
    private DisplayName displayName=null;
    private WelcomeFileList welcomeFileList=null;
    private ErrorPage errorPage=null;
    private Filter filter=null;
    private FilterMapping filterMapping=null;
    private InitParam initParam=null;
    private Listener listener=null;
    private Servlet servlet=null;
    private ServletMapping servletMapping=null;
    @Override
    public WebApp parse(String filePath) throws ServiceException{
        WebApp webApp;
        if (filePath == null || filePath.isEmpty()){
            throw new ServiceException("Incorrect filePath");
        }
        try {
            InputStream inputStream = new FileInputStream(filePath);
            reader=xmlInputFactory.createXMLStreamReader(inputStream);
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new ServiceException(e);
        }
        webApp=setWebApp();
        return webApp;
    }

    private WebApp setWebApp() throws ServiceException{
        try {
            while (reader.hasNext()){
                int type=reader.next();

                switch (type){
                    case XMLStreamConstants.START_ELEMENT: {
                        startElement();
                        break;
                    }
                    case XMLStreamConstants.CHARACTERS: {
                        String text=reader.getText().trim();
                        if (text.isEmpty()){
                            break;
                        }
                        characters(text);
                        break;
                    }
                    case XMLStreamConstants.END_ELEMENT: {
                        endElement();
                        break;
                    }

                }
            }
        } catch (XMLStreamException e) {
            throw new ServiceException(e);
        }


        return webApp;
    }
    private void startElement(){
        elementName = WebTagName.valueOf(reader.getLocalName().toUpperCase().replace("-", "_"));
        switch (elementName){
            case WEB_APP: {
                webApp=new WebApp();
                webApp.setId(reader.getAttributeValue(null,"id"));
                webApp.setVersion(reader.getAttributeValue(null, "version"));
                break;
            }
            case DISPLAY_NAME: {
                displayName=new DisplayName();
                break;
            }
            case WELCOME_FILE_LIST: {
                welcomeFileList=new WelcomeFileList();
                break;
            }
            case FILTER: {
                filter=new Filter();
                break;
            }
            case INIT_PARAM: {
                initParam=new InitParam();
                break;
            }
            case FILTER_MAPPING: {
                filterMapping=new FilterMapping();
                break;
            }
            case LISTENER: {
                listener=new Listener();
                break;
            }
            case SERVLET: {
                servlet=new Servlet();
                break;
            }
            case SERVLET_MAPPING: {
                servletMapping=new ServletMapping();
                break;
            }
            case ERROR_PAGE: {
                errorPage=new ErrorPage();
                break;
            }
        }
    }
    private void characters(String text){

        switch (elementName){
            case DISPLAY_NAME:{
                displayName.setDescription(text);
                break;
            }
            case WELCOME_FILE: {
                welcFiles.add(text);
                break;
            }
            case FILTER_NAME: {
                if (filter!=null){
                    filter.setFilterName(text);
                } else {
                    filterMapping.setFilterName(text);
                }
                break;
            }
            case FILTER_CLASS: {
                filter.setFilterClass(text);
                break;
            }
            case PARAM_NAME: {
                initParam.setParamName(text);
                break;
            }
            case PARAM_VALUE: {
                initParam.setParamValue(text);
                break;
            }
            case URL_PATTERN: {
                if (filterMapping!=null){
                    filterMapping.setUrlPattern(text);
                } else {
                    servletMapping.setUrlPattern(text);
                }
                break;
            }
            case DISPATCHER: {
                filterMapping.setDispatcher(text);
                break;
            }
            case LISTENER_CLASS: {
                listener.setListenerClass(text);
                break;
            }
            case SERVLET_NAME: {
                if (servlet!=null){
                    servlet.setServletName(text);
                } else {
                    servletMapping.setServletName(text);
                }
                break;
            }
            case SERVLET_CLASS: {
                servlet.setServletClass(text);
                break;
            }
            case EXCEPTION_TYPE: {
                errorPage.setExceptionType(text);
                break;
            }
            case LOCATION: {
                errorPage.setLocation(text);
                break;
            }
            case ERROR_CODE: {
                errorPage.setErrorCode(Integer.parseInt(text));
                break;
            }
        }
    }
    private void endElement(){
        elementName = WebTagName.valueOf(reader.getLocalName().toUpperCase().replace("-", "_"));
        switch (elementName){
            case DISPLAY_NAME:{
                webApp.getDisplayNameList().add(displayName);
                displayName=null;
                break;
            }
            case WELCOME_FILE_LIST: {
                welcomeFileList.setWelcomeFile(welcFiles);
                webApp.getWelcomeFileList().add(welcomeFileList);
                welcomeFileList=null;
                break;
            }
            case FILTER: {
                filter.setInitParam(initParamList);
                webApp.getFilterList().add(filter);
                filter=null;
                break;
            }
            case INIT_PARAM: {
                initParamList.add(initParam);
                initParam=null;
                break;
            }
            case FILTER_MAPPING: {
                webApp.getFilterMappingList().add(filterMapping);
                filterMapping=null;
                break;
            }
            case LISTENER: {
                webApp.getListenerList().add(listener);
                listener=null;
                break;
            }
            case SERVLET: {
                webApp.getServletList().add(servlet);
                servlet=null;
                break;
            }
            case SERVLET_MAPPING: {
                webApp.getServletMappingList().add(servletMapping);
                servletMapping=null;
                break;
            }
            case ERROR_PAGE: {
                webApp.getErrorPageList().add(errorPage);
                errorPage=null;
                break;
            }
        }
    }
}
