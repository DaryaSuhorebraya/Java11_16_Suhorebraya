package com.dasha.parser.service.impl.dom;

import com.dasha.parser.entity.bean.*;
import com.dasha.parser.service.WebParser;
import com.dasha.parser.service.exception.ServiceException;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Даша on 24.01.2017.
 */
public class WebDOMParser implements WebParser{

    @Override
    public WebApp parse(String filePath) throws ServiceException{
        if (filePath==null || filePath.isEmpty()){
            throw new ServiceException("Incorrect filePath");
        }
        InputSource inputSource=new InputSource(filePath);
        DOMParser domParser=new DOMParser();
        try {
            domParser.parse(inputSource);
        } catch (SAXException | IOException e) {
            throw new ServiceException(e);
        }
        Document document=domParser.getDocument();
        Element root=document.getDocumentElement();
        WebApp webApp=new WebApp();
        setWebAppAttr(webApp,root);
        setWebApp(webApp,root);

        return webApp;
    }
    private Element getSingleChild(Element element,String childName){
        NodeList nodeList=element.getElementsByTagName(childName);
        Element child=(Element) nodeList.item(0);
        return child;
    }
    private List<Element> getChildren(Element element,String childName){
        NodeList nodeList=element.getElementsByTagName(childName);
        List<Element> elements=new ArrayList<>();
        for (int i=0; i<nodeList.getLength();i++){
            Element child=(Element) nodeList.item(i);
            elements.add(child);
        }
        return elements;
    }
    private void setWebAppAttr(WebApp webApp, Element root){
        webApp.setId(root.getAttribute("id"));
        webApp.setVersion(root.getAttribute("version"));
    }
    private void setDisplayName(WebApp webApp,Element root){
        NodeList displayNameNodeList = root.getElementsByTagName("display-name");
        DisplayName displayName;

        for (int i = 0 ; i < displayNameNodeList.getLength(); i++) {
            displayName = new DisplayName();
            Element displayNameElement = (Element) displayNameNodeList.item(i);
            displayName.setDescription(displayNameElement.getTextContent().trim());
            webApp.getDisplayNameList().add(displayName);
        }
    }
    private void setWelcomeFileList(WebApp webApp,Element root){
        NodeList welcomeFileNodeList = root.getElementsByTagName("welcome-file-list");
        WelcomeFileList welcomeFileList;
        List<String> welcomeFiles=new ArrayList<>();
        for (int i = 0 ; i < welcomeFileNodeList.getLength(); i++) {
            welcomeFileList=new WelcomeFileList();
            Element welcomeFileListElement = (Element) welcomeFileNodeList.item(i);

            List<Element> welcomeFileElements=getChildren(welcomeFileListElement,"welcome-file");
            for (Element element:welcomeFileElements){
                welcomeFiles.add(element.getTextContent().trim());
            }
            welcomeFileList.setWelcomeFile(welcomeFiles);
            webApp.getWelcomeFileList().add(welcomeFileList);
        }
    }
    private void setFilter(WebApp webApp,Element root){
        NodeList filterNodeList = root.getElementsByTagName("filter");
        Filter filter;

        for (int i = 0 ; i < filterNodeList.getLength(); i++) {
            filter = new Filter();
            Element filterElement = (Element) filterNodeList.item(i);
            filter.setFilterName(getSingleChild(filterElement,"filter-name").getTextContent().trim());
            filter.setFilterClass(getSingleChild(filterElement,"filter-class").getTextContent().trim());

            filter.setInitParam(getInitParamList(filterElement));
            webApp.getFilterList().add(filter);
        }
    }
    private void setFilterMapping(WebApp webApp,Element root){
        NodeList filterMappingNodeList = root.getElementsByTagName("filter-mapping");
        FilterMapping filterMapping;

        for (int i = 0 ; i < filterMappingNodeList.getLength(); i++) {
            filterMapping = new FilterMapping();
            Element filterMappingElement = (Element) filterMappingNodeList.item(i);
            filterMapping.setFilterName(getSingleChild(filterMappingElement,"filter-name").getTextContent().trim());
            filterMapping.setUrlPattern(getSingleChild(filterMappingElement,"url-pattern").getTextContent().trim());
            filterMapping.setDispatcher(getSingleChild(filterMappingElement,"dispatcher").getTextContent().trim());

            webApp.getFilterMappingList().add(filterMapping);
        }
    }
    private void setListener(WebApp webApp,Element root){
        NodeList listenerNodeList = root.getElementsByTagName("listener");
        Listener listener;
        for (int i = 0 ; i < listenerNodeList.getLength(); i++) {
            listener = new Listener();
            Element listenerElement = (Element) listenerNodeList.item(i);
            listener.setListenerClass(getSingleChild(listenerElement,"listener-class").getTextContent().trim());
            webApp.getListenerList().add(listener);
        }
    }
    private void setServlet(WebApp webApp,Element root){
        NodeList servletNodeList = root.getElementsByTagName("servlet");
        Servlet servlet;

        for (int i = 0 ; i < servletNodeList.getLength(); i++) {
            servlet=new Servlet();
            Element servletElement = (Element) servletNodeList.item(i);
            servlet.setServletName(getSingleChild(servletElement,"servlet-name").getTextContent().trim());
            servlet.setServletClass(getSingleChild(servletElement,"servlet-class").getTextContent().trim());

            servlet.setInitParams(getInitParamList(servletElement));
            webApp.getServletList().add(servlet);
        }
    }
    private List<InitParam> getInitParamList(Element parentElement){

        List<Element> initParamElementList=getChildren(parentElement,"init-param");
        List<InitParam> initParamList=new ArrayList<>();
        for (Element element:initParamElementList){
            InitParam initParam=new InitParam();
            initParam.setParamName(getSingleChild(element,"param-name").getTextContent().trim());
            initParam.setParamValue(getSingleChild(element,"param-value").getTextContent().trim());
            initParamList.add(initParam);
        }
        return initParamList;
    }
    private void setServletMapping(WebApp webApp,Element root){
        NodeList servletNodeList = root.getElementsByTagName("servlet-mapping");
        ServletMapping servletMapping;

        for (int i = 0 ; i < servletNodeList.getLength(); i++) {
            servletMapping=new ServletMapping();
            Element servletMappingElement = (Element) servletNodeList.item(i);
            servletMapping.setServletName(getSingleChild(servletMappingElement,"servlet-name").getTextContent().trim());
            servletMapping.setUrlPattern(getSingleChild(servletMappingElement,"url-pattern").getTextContent().trim());

            webApp.getServletMappingList().add(servletMapping);
        }
    }
    private void setErrorPage(WebApp webApp,Element root){
        NodeList errorPageNodeList = root.getElementsByTagName("error-page");
        ErrorPage errorPage;
        for (int i = 0 ; i < errorPageNodeList.getLength(); i++) {
            errorPage = new ErrorPage();
            Element errorPageElement = (Element) errorPageNodeList.item(i);
            Element exceptionTypeElement=getSingleChild(errorPageElement,"exception-type");
            if (exceptionTypeElement!=null){
                errorPage.setExceptionType(exceptionTypeElement.getTextContent().trim());
            }else {
                errorPage.setErrorCode(Integer.parseInt(getSingleChild(errorPageElement,"error-code").getTextContent().trim()));
            }
            errorPage.setLocation(getSingleChild(errorPageElement,"location").getTextContent().trim());
            webApp.getErrorPageList().add(errorPage);
        }
    }
    private void setWebApp(WebApp webApp,Element root){
        setDisplayName(webApp,root);
        setWelcomeFileList(webApp,root);
        setFilter(webApp, root);
        setFilterMapping(webApp, root);
        setListener(webApp, root);
        setServlet(webApp, root);
        setServletMapping(webApp, root);
        setErrorPage(webApp,root);
    }
}
