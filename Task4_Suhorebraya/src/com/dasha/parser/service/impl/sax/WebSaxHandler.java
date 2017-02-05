package com.dasha.parser.service.impl.sax;

import com.dasha.parser.entity.bean.*;
import com.dasha.parser.entity.element.WebTagName;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Даша on 23.01.2017.
 */
public class WebSaxHandler extends DefaultHandler{

    private WebApp webApp;
    private List<String> welcomeFiles=new ArrayList<>();
    private List<InitParam> initParamList=new ArrayList<>();
    private DisplayName displayName;
    private WelcomeFileList welcomeFileList;
    private ErrorPage errorPage;
    private Filter filter;
    private FilterMapping filterMapping;
    private InitParam initParam;
    private Listener listener;
    private Servlet servlet;
    private ServletMapping servletMapping;
    private StringBuilder text;


    public WebApp getWebApp() {
        return webApp;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Parsing started.");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Parsing ended.");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text.append(ch, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {

        text=new StringBuilder();
        if (qName.equals("web-app")) {
            webApp=new WebApp();
            webApp.setId(attributes.getValue("id"));
            webApp.setVersion(attributes.getValue("version"));
        }
        if (qName.equals("display-name")) {
            displayName=new DisplayName();
        }
        if (qName.equals("welcome-file-list")) {
            welcomeFileList=new WelcomeFileList();
        }
        if (qName.equals("filter")) {
            filter=new Filter();
        }
        if (qName.equals("filter-mapping")) {
            filterMapping=new FilterMapping();
        }
        if (qName.equals("listener")) {
            listener=new Listener();
        }
        if (qName.equals("servlet")) {
            servlet=new Servlet();
        }
        if (qName.equals("servlet-mapping")) {
            servletMapping=new ServletMapping();
        }
        if (qName.equals("error-page")) {
            errorPage=new ErrorPage();
        }
        if (qName.equals("init-param")) {
            initParam=new InitParam();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        WebTagName webTagName=WebTagName.valueOf(qName.toUpperCase().replace("-","_"));
        switch (webTagName){
            case DISPLAY_NAME: {
                displayName.setDescription(text.toString());
                webApp.getDisplayNameList().add(displayName);
                displayName=null;
                break;
            }
            case WELCOME_FILE: {
                welcomeFiles.add(text.toString());
                break;
            }
            case WELCOME_FILE_LIST: {
                welcomeFileList.setWelcomeFile(welcomeFiles);
                webApp.getWelcomeFileList().add(welcomeFileList);
                welcomeFileList=null;
                break;
            }
            case FILTER_NAME: {
                if (filter!=null){
                filter.setFilterName(text.toString());
                } else {
                    filterMapping.setFilterName(text.toString());
                }
                break;
            }
            case FILTER_CLASS: {
                filter.setFilterClass(text.toString());
                break;
            }
            case PARAM_NAME: {
                initParam.setParamName(text.toString());
                break;
            }
            case PARAM_VALUE: {
                initParam.setParamValue(text.toString());
                break;
            }
            case INIT_PARAM: {
                initParamList.add(initParam);
                initParam=null;
                break;
            }
            case FILTER: {
                filter.setInitParam(initParamList);
                webApp.getFilterList().add(filter);
                initParamList=null;
                filter=null;
                break;
            }
            case URL_PATTERN: {
                if (filterMapping!=null){
                    filterMapping.setUrlPattern(text.toString());
                } else {
                    servletMapping.setUrlPattern(text.toString());
                }
                break;
            }
            case DISPATCHER: {
                filterMapping.setDispatcher(text.toString());
                break;
            }
            case FILTER_MAPPING: {
                webApp.getFilterMappingList().add(filterMapping);
                filterMapping=null;
                break;
            }
            case LISTENER_CLASS: {
                listener.setListenerClass(text.toString());
                break;
            }
            case LISTENER: {
                webApp.getListenerList().add(listener);
                listener=null;
                break;
            }
            case SERVLET_NAME: {
                if (servlet!=null) {
                    servlet.setServletName(text.toString());
                }
                else {
                    servletMapping.setServletName(text.toString());
                }
                break;
            }
            case SERVLET_CLASS: {
                servlet.setServletClass(text.toString());
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
            case EXCEPTION_TYPE: {
                errorPage.setExceptionType(text.toString());
                break;
            }
            case LOCATION: {
                errorPage.setLocation(text.toString());
                break;
            }
            case ERROR_CODE: {
                errorPage.setErrorCode(Integer.parseInt(text.toString()));
                break;
            }
            case ERROR_PAGE: {
                webApp.getErrorPageList().add(errorPage);
                errorPage=null;
                break;
            }
        }
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
        System.err.println("WARNING: line " + e.getLineNumber() + " : " + e.getMessage());
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        System.err.println("ERROR: line " + e.getLineNumber() + " : " + e.getMessage());
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        System.err.println("FATAL ERROR: line " + e.getLineNumber() + " : " + e.getMessage());
    }
}
